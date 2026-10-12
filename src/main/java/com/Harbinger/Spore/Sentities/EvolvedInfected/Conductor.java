package com.Harbinger.Spore.Sentities.EvolvedInfected;


import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AmbientSparks;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;

import com.Harbinger.Spore.Sentities.Projectile.ChainProjectile;
import com.Harbinger.Spore.Sentities.VariantKeeper;
import com.Harbinger.Spore.Sentities.Variants.ConductorVariants;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Conductor extends EvolvedInfected implements VariantKeeper {
    private int attackAnimationTick;
    protected List<AmbientSparks> sparks = new ArrayList<>();
    private static final EntityDataAccessor<Float> CHARGE = SynchedEntityData.defineId(Conductor.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_ID = SynchedEntityData.defineId(Conductor.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Conductor.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> ITEM_DATA = SynchedEntityData.defineId(Conductor.class, EntityDataSerializers.STRING);
    private int beamTicks;
    private List<FloatingItem> itemVectors = new ArrayList<>();

    public Conductor(EntityType<? extends EvolvedInfected> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new CustomMeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 3.0 + entity.getBbWidth() * entity.getBbWidth();}
        });

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }
    public List<AmbientSparks> getSparks(){
        return sparks;
    }
    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.conductor_loot.get();
    }
    public float getCharge(){
        return entityData.get(CHARGE);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.conductor_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.conductor_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.conductor_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32);

    }
    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
        }else if (value == 7) {
            this.beamTicks = 20;
        } else {
            super.handleEntityEvent(value);
        }
    }
    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficulty, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        this.populateDefaultEquipmentSlots(this.random, difficulty);
        setVariant(Util.getRandom(ConductorVariants.values(), random));
        initializeDataSpawn();
        return super.finalizeSpawn(serverLevelAccessor, difficulty, p_21436_, p_21437_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHARGE,0f);
        builder.define(DATA_ID,-1);
        builder.define(DATA_ID_TYPE_VARIANT,0);
        builder.define(ITEM_DATA,createData());
    }

    public String createData(){
        StringBuilder string = new StringBuilder();
        int val = random.nextInt(2,5);
        for (int i = 0;i<val;i++){
            if (i > 0) string.append("|");
            string.append(random.nextInt(3));
        }
        return string.toString();
    }
    public void initializeDataSpawn(){
        entityData.set(ITEM_DATA,createData());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
        this.entityData.set(ITEM_DATA,tag.getString("itemData"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
        tag.putString("itemData",entityData.get(ITEM_DATA));
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        return super.doHurtTarget(entity);
    }

    public void shootChain(LivingEntity target){
        if (level().isClientSide){
            return;
        }
        double spawnX = this.getX();
        double spawnY = this.getY() + 1.5D;
        double spawnZ = this.getZ();
        ChainProjectile chainProjectile = new ChainProjectile(level(),this);
        chainProjectile.moveTo(spawnX, spawnY, spawnZ);
        double dx = target.getX() - this.getX();
        double dy = target.getY() + target.getEyeHeight() - 2;
        double dz = target.getZ() - this.getZ();
        chainProjectile.shoot(dx, dy - chainProjectile.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2, 4.0F);
        level().addFreshEntity(chainProjectile);
    }
    public ItemStack getItemFromString(String val){
        if (Objects.equals(val, "1")){
            return new ItemStack(Items.IRON_SWORD);
        }
        if (Objects.equals(val, "2")){
            return new ItemStack(Items.IRON_AXE);
        }
        return new ItemStack(Items.IRON_PICKAXE);
    }

    public static class FloatingItem {
        private final Conductor conductor;
        private final ItemStack stack;
        private final float orbitRadius;
        private final float orbitHeight;
        private final float orbitSpeed;
        private final float baseAngle;

        private Vec3 position;
        private Vec3 prevPosition;

        private boolean attacking;
        private int attackTicks;
        private int comingBackTicks;
        private Entity attackTargetPos;

        private static final int ATTACK_DURATION = 80;

        public FloatingItem(Conductor conductor, ItemStack stack, float baseAngle) {
            this.conductor = conductor;
            this.stack = stack;
            this.baseAngle = baseAngle;
            this.orbitRadius = 1.5f;
            this.orbitHeight = 1.0f;
            this.orbitSpeed = 0.05f;
        }

        public ItemStack getStack() { return stack; }
        public Vec3 getPosition() { return position; }
        public Vec3 getPrevPosition() { return prevPosition; }
        public boolean isAttacking() { return attacking; }

        public void startAttack(Entity targetPos) {
            this.attacking = true;
            this.attackTicks = 0;
            this.attackTargetPos = targetPos;
        }

        public void tick(Vec3 ownerPos, int worldTick) {
            this.prevPosition = this.position;
            if (attacking) {
                attackTicks++;
                float t = (float) attackTicks / ATTACK_DURATION;
                float lerp = t < 0.5f ? (t * 2f) : (1f - (t - 0.5f) * 2f);
                Vec3 start = ownerPos.add(0, orbitHeight, 0);
                if (attackTargetPos != null){
                    Vec3 target = attackTargetPos.position().add(0,attackTargetPos.getBbHeight() * 0.5,0);
                    this.position = start.lerp(target, lerp);
                    if (position.distanceTo(target) <= 0.5){
                        AABB aabb = attackTargetPos.getBoundingBox().inflate(2);
                        List<LivingEntity> targets = conductor.level().getEntitiesOfClass(LivingEntity.class,aabb);
                        for (LivingEntity living : targets){
                            if (Utilities.TARGET_SELECTOR.Test(living)){
                                conductor.harmWithItem(stack,living);
                            }
                        }
                        attacking = false;
                        attackTicks = 0;
                        attackTargetPos = null;
                        comingBackTicks = 20;
                    }
                }
                if (attackTicks >= ATTACK_DURATION) {
                    attacking = false;
                    attackTicks = 0;
                    attackTargetPos = null;
                    comingBackTicks = 20;
                }
            } else {
                boolean comeBack = comingBackTicks > 0;
                if (comeBack){
                    comingBackTicks--;
                }
                float angle = baseAngle + worldTick * orbitSpeed;
                double x = ownerPos.x + Math.cos(angle) * orbitRadius;
                double z = ownerPos.z + Math.sin(angle) * orbitRadius;
                double y = ownerPos.y + orbitHeight;
                Vec3 vec3 = new Vec3(x, y, z);
                this.position = comeBack ? position.lerp(vec3,0.25) : vec3;
            }
        }

        public boolean canAttack() {
            return !attacking;
        }
    }
    public List<FloatingItem> getItemVectors(){
        return itemVectors;
    }
    private boolean hasAttackingItem() {
        for (FloatingItem item : itemVectors) {
            if (item.isAttacking()) return true;
        }
        return false;
    }
    public void harmWithItem(ItemStack stack, LivingEntity victim) {
        if (level().isClientSide) {
            return;
        }

        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.25f;
        var itemModifiers = stack.getOrDefault(
                net.minecraft.core.component.DataComponents.ATTRIBUTE_MODIFIERS,
                net.minecraft.world.item.component.ItemAttributeModifiers.EMPTY
        );

        for (var entry : itemModifiers.modifiers()) {
            if (entry.slot().test(EquipmentSlot.MAINHAND)) {
                if (entry.attribute().is(Attributes.ATTACK_DAMAGE)) {
                    damage += (float) entry.modifier().amount();
                }
            }
        }

        DamageSource source = level().damageSources().mobAttack(this);
        victim.hurt(source, damage);
    }

    @Nullable
    private FloatingItem pickAttackItem() {
        List<FloatingItem> available = new ArrayList<>();
        for (FloatingItem item : itemVectors) {
            if (item.canAttack()) available.add(item);
        }
        if (available.isEmpty()) return null;
        return available.get(random.nextInt(available.size()));
    }
    @Override
    public void tick() {
        super.tick();
        boolean client = level().isClientSide;
        if (getTypeVariant() == 4){
            if (!client){
                setAttackId(getTarget());
            }
            Entity entity = level().getEntity(getAttackedId());
            if (itemVectors.isEmpty()) {
                String[] array = entityData.get(ITEM_DATA).split("\\|");
                int count = 0;
                for (String string : array) {
                    float baseAngle = (float) (2 * Math.PI * count / array.length);
                    itemVectors.add(new FloatingItem(this,getItemFromString(string), baseAngle));
                    count++;
                }
            }else {
                if (entity != null && !hasAttackingItem()) {
                    FloatingItem chosen = pickAttackItem();
                    if (chosen != null && hasLineOfSight(entity)) {
                        chosen.startAttack(entity);

                        if (!client) {
                            setAttackId(-1);
                        }
                    }
                }
                Vec3 ownerPos = this.position();
                for (FloatingItem item : itemVectors) {
                    item.tick(ownerPos, tickCount);
                }
            }

            if (entityData.get(CHARGE) < 1) {
                entityData.set(CHARGE, 1f);
            }

            return;
        }
        if (getTypeVariant() == 1){
            Entity entity = level().getEntity(getAttackedId());
            if (tickCount % 40 == 0 && entity == null){
                LivingEntity target = getTarget();
                if (target != null && hasLineOfSight(target)){
                    shootChain(target);
                }
            }
            if (tickCount % 20 == 0){
                if (entity != null && hasLineOfSight(entity) && entity instanceof LivingEntity living){
                    float dmg = (float) (SConfig.SERVER.conductor_el_small_damage.get() * SConfig.SERVER.global_damage.get());
                    if (living.hurt(level().damageSources().lightningBolt(), dmg)) {
                        if (random.nextFloat() < 0.2f) {
                            living.setRemainingFireTicks(40);
                        }
                        this.playSound(Ssounds.ELECTRIC.value());
                    }
                    entityData.set(CHARGE, 1f);
                }else {
                    setAttackId(-1);
                    entityData.set(CHARGE, 0f);
                }
            }
            return;
        }


        // ----- Passive charge gain from nearby entities (variant 2) -----
        if (tickCount % 20 == 0 && getTypeVariant() == 2) {
            List<LivingEntity> nearby = level().getEntitiesOfClass(
                    LivingEntity.class,
                    this.getBoundingBox().inflate(6, 1, 6),
                    entity -> entity != this && TARGET_SELECTOR.test(entity));
            for (LivingEntity livingEntity : nearby) {
                livingEntity.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false));
                if (!client) {
                    entityData.set(CHARGE, Mth.clamp(getCharge() + 1f, 0f, 20f));
                }
            }
        }

        // ----- Charge drift based on aggression -----
        if (isAggressive()) {
            if (getCharge() < 20f) {
                entityData.set(CHARGE, Math.min(20f, getCharge() + 0.1f));
            }
        } else if (getCharge() > 0f) {
            entityData.set(CHARGE, Math.max(0f, getCharge() - 0.1f));
        }

        // ----- Ambient electric sparks (visual + minor damage) -----
        if (tickCount % 5 == 0 && getCharge() > 0f) {
            float range = Math.abs(getCharge() * 0.15f);
            double inflation = 1.0 + range;
            List<Entity> nearbyEntities = level().getEntities(
                    this, this.getBoundingBox().inflate(inflation));
            int charge = (int) range * 3;
            this.playSound(Ssounds.ELECTRIC.value());

            int sparkCount = random.nextInt(3 + Math.max(0, charge));
            for (int i = 0; i < sparkCount; i++) {
                Vec3 vec3 = Utilities.generatePositionAway(
                        this.position().add(0, 2.2, 0), 1 + range);

                Entity entity = nearbyEntities.isEmpty()
                        ? null
                        : nearbyEntities.get(random.nextInt(nearbyEntities.size()));

                LivingEntity living = null;
                if (entity instanceof LivingEntity livingEntity
                        && livingEntity != this
                        && TARGET_SELECTOR.test(livingEntity)
                        && this.hasLineOfSight(livingEntity)) {
                    living = livingEntity;
                }

                if (client) {
                    AmbientSparks ambientSparks = new AmbientSparks(
                            vec3, living, this, random.nextInt(5, 10));
                    sparks.add(ambientSparks);
                } else if (living != null) {
                    entityData.set(CHARGE, Math.max(0f, getCharge() - 1f));
                    this.playSound(Ssounds.ELECTRIC_SPARK.value());
                    float dmg = (float) (SConfig.SERVER.conductor_el_small_damage.get()
                            * SConfig.SERVER.global_damage.get());
                    if (living.hurt(level().damageSources().lightningBolt(), dmg)) {
                        if (random.nextFloat() < 0.2f) {
                            living.setRemainingFireTicks(40);
                        }
                    }
                }
            }
        }


        // ----- Surge discharge (the big attack) -----
        if (getCharge() >= isSurge()) {
            LivingEntity target = getTarget();
            if (target != null && hasLineOfSight(target)){
                setAttackId(target.getId());
            }

            if (getAttackedId() != -1) {
                Entity clientTarget = level().getEntity(getAttackedId());

                int voltageModifier = target instanceof IronGolem ? 3 : 1;
                if (getTypeVariant() == 2) {
                    voltageModifier *= 2;
                }
                if (clientTarget == null){
                    return;
                }
                if (getTypeVariant() == 3) {
                    // ----- Chain lightning (variant 3) -----
                    List<LivingEntity> victims = level().getEntitiesOfClass(
                            LivingEntity.class, clientTarget.getBoundingBox().inflate(6, 1, 6),
                            entity -> entity != this && TARGET_SELECTOR.test(entity));
                    victims.sort(Comparator.comparingDouble(this::distanceToSqr));

                    LivingEntity owner = this;
                    int chains = Math.min(victims.size(), 3);
                    float dmg = (float) (SConfig.SERVER.conductor_el_discharge_damage.get()
                            * SConfig.SERVER.global_damage.get()
                            * voltageModifier * 0.5);
                    for (int i = 0; i < chains; i++) {
                        LivingEntity living = victims.get(i);

                        if (client) {
                            Vec3 vec3 = Utilities.generatePositionAway(
                                    living.position().add(0, living.getBbHeight() * 0.9, 0),
                                    2);
                            AmbientSparks ambientSparks = new AmbientSparks(
                                    vec3, living, owner, 20);
                            sparks.add(ambientSparks);
                            owner = living;
                        } else {
                            if (living.hurt(level().damageSources().lightningBolt(), dmg)) {
                                living.setRemainingFireTicks(40);
                            }
                        }
                    }
                } else {
                    // ----- Single-target discharge -----
                    if (!client) {
                        beamTicks = 20;
                        level().broadcastEntityEvent(this, (byte) 7);
                        float dmg = (float) (SConfig.SERVER.conductor_el_discharge_damage.get()
                                * SConfig.SERVER.global_damage.get()
                                * voltageModifier);
                        if (clientTarget.hurt(level().damageSources().lightningBolt(), dmg)) {
                            clientTarget.setRemainingFireTicks(100);
                        }
                    }
                }

                if (!client) {
                    entityData.set(CHARGE, Math.max(0f, getCharge() - 10f));
                }
                this.playSound(Ssounds.ELECTRIC_DISCHARGE.value());
            }
        }

        // ----- Client-side spark lifecycle -----
        if (client && !sparks.isEmpty()) {
            Iterator<AmbientSparks> it = sparks.iterator();
            while (it.hasNext()) {
                AmbientSparks spark = it.next();
                spark.TickSpark();
                if (spark.life >= spark.maxLife) {
                    it.remove();
                }
            }
        }
    }
    public int isSurge(){
        return getTypeVariant() == 2 ? 20 : 10;
    }

    public int getAttackedId(){
        return entityData.get(DATA_ID);
    }
    public void setAttackId(int i){
        entityData.set(DATA_ID,i);
    }
    public void setAttackId(LivingEntity i){
        entityData.set(DATA_ID,i == null ? -1 : i.getId());
    }
    public int getBeamTicks(){
        return beamTicks;
    }
    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
        if(beamTicks > 0){
            beamTicks--;
            if (beamTicks <= 1){
                setAttackId(-1);
            }
        }
    }

    @Override
    public boolean addEffect(MobEffectInstance effectInstance, @Nullable Entity entity) {
        if (effectInstance.getEffect().value().isBeneficial()){
            return super.addEffect(effectInstance, entity);
        }
        return false;
    }


    public int getAttackAnimationTick(){
        return attackAnimationTick;
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.SCIENTIST_AMBIENT.value();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.value();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public ConductorVariants getVariant() {
        return ConductorVariants.byId(this.getTypeVariant() & 255);
    }

    public int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    @Override
    public void setVariant(int i) {
        this.entityData.set(DATA_ID_TYPE_VARIANT,
                (i >= 0 && i < ConductorVariants.values().length) ? i : 0);
    }

    @Override
    public int amountOfMutations() {
        return ConductorVariants.values().length;
    }

    private void setVariant(ConductorVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }
}
