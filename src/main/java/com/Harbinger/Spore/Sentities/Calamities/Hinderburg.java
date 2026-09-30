package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.CalamityInfectedCommand;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SporeBurstSupport;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.SummonScentInCombat;
import com.Harbinger.Spore.Sentities.AI.FlyingWanderAround;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.ColdEndurance;
import com.Harbinger.Spore.Sentities.FlyingInfected;
import com.Harbinger.Spore.Sentities.HitboxesForParts;
import com.Harbinger.Spore.Sentities.Projectile.ThrownTumor;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.Sentities.Utility.TumoroidNuke;
import com.Harbinger.Spore.core.SAttributes;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Hinderburg extends Calamity implements FlyingInfected , TrueCalamity , RangedAttackMob {
    public static final EntityDataAccessor<Boolean> ADAPTATION = SynchedEntityData.defineId(Hinderburg.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DROPPED_BOMBS = SynchedEntityData.defineId(Hinderburg.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> BOMB = SynchedEntityData.defineId(Hinderburg.class, EntityDataSerializers.INT);
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart lowerbody;
    public final CalamityMultipart forwardbody;
    public final CalamityMultipart rightcannon;
    public final CalamityMultipart leftcannon;
    public final CalamityMultipart mouth;
    public Hinderburg(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.lowerbody = new CalamityMultipart(this, "lowerbody", 4.0F, 4.0F);
        this.forwardbody = new CalamityMultipart(this, "forwardbody", 4.0F, 4.0F);
        this.rightcannon = new CalamityMultipart(this, "rightcannon", 1.5F, 1.5F);
        this.leftcannon = new CalamityMultipart(this, "leftcannon", 1.5F, 1.5F);
        this.mouth = new CalamityMultipart(this, "mouth", 3.0F, 0.5F);
        this.subEntities = new CalamityMultipart[]{ this.lowerbody, this.forwardbody,this.rightcannon,this.leftcannon,this.mouth};
        this.moveControl = new HindenMovementController(this );
        this.lookControl = new HindenLookControl(this);
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }
    public void travel(Vec3 vec) {
        if (this.onGround()){
            this.setDeltaMovement(this.getDeltaMovement().add(0,0.1,0));
        }
        if (this.isEffectiveAi() && !this.onGround()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95D));
        } else {
            super.travel(vec);
        }
    }

    @Override
    public boolean canCalcify(Entity entity) {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }

    @Override
    public double setInflation() {
        return 1.0;
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount % 20 == 0){
            if (isAdapted() && getHealth() < getMaxHealth()){
                this.heal(1f);
            }
            if (this.getKills() >= 50 && this.getDroppedBombs() >= 5 && !this.isAdapted()){
                this.entityData.set(ADAPTATION,true);
            }
            if (this.isAdapted()){
                AABB aabb = this.getBoundingBox().inflate(8);
                List<Entity> entities = level().getEntities(this,aabb);
                for (Entity entity : entities){
                    if (entity instanceof LivingEntity living && Utilities.TARGET_SELECTOR.Test(living)){
                        living.setRemainingFireTicks(100);
                    }
                }
            }
        }
        if(!isArmed()){
            int value = this.isAdapted() ? 2 : 1;
            this.setBomb(this.getBomb() + value);
        }
    }
    public int getDroppedBombs(){
        return this.entityData.get(DROPPED_BOMBS);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.hinden_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FLYING_SPEED, 0.2)
                .add(Attributes.ARMOR, SConfig.SERVER.hinden_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.hinden_damage.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(SAttributes.TOXICITY, 0.0D)
                .add(SAttributes.REJUVENATION, 0.0D)
                .add(SAttributes.LOCALIZATION, 0.0D)
                .add(SAttributes.LACERATION, 0.0D)
                .add(SAttributes.CORROSIVES, 0.0D)
                .add(SAttributes.BALLISTIC, 0.0D)
                .add(SAttributes.GRINDING, 0.0D);

    }

    @Override
    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        if (calamityMultipart == this.mouth){
            this.hurt(source,value * 2f);
        }else if(calamityMultipart == this.rightcannon || calamityMultipart == this.leftcannon){
            this.hurt(source,value * 3f);
        }else {
            this.hurt(source,value);
        }
        return true;
    }

    @Override
    public int chemicalRange() {
        return 32;
    }

    @Override
    public List<? extends String> buffs() {
        return SConfig.SERVER.hinden_buffs.get();
    }

    @Override
    public List<? extends String> debuffs() {
        return SConfig.SERVER.hinden_debuffs.get();
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.hindie_loot.get();
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(2, new HindenNukeGoal(this));
        this.goalSelector.addGoal(3, new HindenBombAndRamGoal(this, 1.0D, 32.0F));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, TumoroidNuke.class, 10.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(6, new AOEMeleeAttackGoal(this,1,true,2,6,livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}));
        this.goalSelector.addGoal(6,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(7,new SummonScentInCombat(this));
        this.goalSelector.addGoal(8,new SporeBurstSupport(this));
        this.goalSelector.addGoal(9,new FlyingWanderAround(this,0.5));
        super.registerGoals();
    }

    @Override
    public void ActivateAdaptation() {
        this.setKills(this.getKills()+50);
        this.entityData.set(DROPPED_BOMBS,entityData.get(DROPPED_BOMBS)+5);
    }

    @Override
    public void aiStep() {
        float f14 = this.getYRot() * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        Vec3[] avec3 = new Vec3[this.subEntities.length];
        for(int j = 0; j < this.subEntities.length; ++j) {
            avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
        }

        this.tickPart(this.forwardbody, (double)(f2 * -5.0F), 0.0D, (double)(f15 * 5.0F));
        this.tickPart(this.lowerbody, (double)(f2 * 5.0F), 0.0D, (double)(f15 * -5.0F));
        this.tickPart(this.mouth, (double)(f2 * -0.5F), -0.5D, (double)(f15 * 0.5F));

        this.tickPart(this.rightcannon, new Vec3(0D,0D,4D),0.3D);
        this.tickPart(this.leftcannon, new Vec3(0D,0D,-4D),0.3D);
        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.aiStep();
        if (this.isAdapted()){
            for (int i = 0; i < 360; i++) {
                if (i % 40 == 0) {
                    this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                            this.getX() , this.getY(), this.getZ() ,
                            Math.cos(i) * 0.25d, 0.25d, Math.sin(i) * 0.25d);
                    this.level().addParticle(ParticleTypes.LARGE_SMOKE,
                            this.getX() , this.getY(), this.getZ() ,
                            Math.sin(i) * 0.25d,  -0.25d, Math.cos(i) * 0.25d);
                }
            }
        }
    }

    public CalamityMultipart[] getSubEntities() {
        return this.subEntities;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return subEntities;
    }

    public void recreateFromPacket(ClientboundAddEntityPacket p_218825_) {
        super.recreateFromPacket(p_218825_);
        if (true) return;
        CalamityMultipart[] calamityMultiparts = this.getSubEntities();

        for(int i = 0; i < calamityMultiparts.length; ++i) {
            calamityMultiparts[i].setId(i + p_218825_.getId());
        }

    }
    public boolean isAdapted(){
        return this.entityData.get(ADAPTATION);
    }

    @Override
    public double getDamageCap() {
        return SConfig.SERVER.hinden_dpsr.get();
    }

    public  boolean tryToSummonNUKE(Entity entity){
        if (entity != null && this.isArmed()){
            double x = Math.abs(entity.getX())  - Math.abs(this.getX());
            double z = Math.abs(entity.getZ()) - Math.abs(this.getZ());
            return entity.getY() < this.getY() && (Math.abs(x) < 10) && (Math.abs(z) < 10);
        }
        return false;
    }
    public void SummonNuke(){
            TumoroidNuke tnt = new TumoroidNuke(this.level(),this);
            tnt.setOverclocked(this.entityData.get(ADAPTATION));
            tnt.setBuster(Math.random() < 0.2);
            this.entityData.set(DROPPED_BOMBS,entityData.get(DROPPED_BOMBS)+1);
            this.level().addFreshEntity(tnt);
            this.setBomb(0);
    }

    private static class HindenMovementController extends MoveControl{
        private final Hinderburg mob;
        private int floatDuration;

        public HindenMovementController(Hinderburg mob) {
            super(mob);
            this.mob = mob;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.mob.getRandom().nextInt(4) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.mob.getX(), this.wantedY - this.mob.getY(), this.wantedZ - this.mob.getZ());
                    vec3 = vec3.normalize();
                    this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(vec3.scale(0.1D)));
                }

            }
            if (this.operation == Operation.WAIT){
                if (!this.hasWanted() && this.mob.getTarget() == null){
                    this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0,-0.01,0));
                }
            }
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.HINDEN_AMBIENT.value();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.value();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.RAVAGER_STEP;
    }

    private static class HindenLookControl extends LookControl{
        public HindenLookControl(Mob mob) {
            super(mob);
        }

        @Override
        public void tick() {
            super.tick();
            if (this.mob.getTarget() == null) {
                if (this.mob.tickCount % 40 == 0){
                    Vec3 vec3 = this.mob.getDeltaMovement();
                    this.mob.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                    this.mob.yBodyRot = this.mob.getYRot();
                }
            } else {
                LivingEntity livingentity = this.mob.getTarget();
                if (livingentity.distanceToSqr(this.mob) < 4096.0D) {
                    double d1 = livingentity.getX() - this.mob.getX();
                    double d2 = livingentity.getZ() - this.mob.getZ();
                    this.mob.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.mob.yBodyRot = this.mob.getYRot();
                }
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BOMB, 0);
        builder.define(DROPPED_BOMBS, 0);
        builder.define(ADAPTATION, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("bomb", entityData.get(BOMB));
        tag.putInt("dropped_bombs", entityData.get(DROPPED_BOMBS));
        tag.putBoolean("adaptation", entityData.get(ADAPTATION));
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(BOMB, tag.getInt("bomb"));
        entityData.set(DROPPED_BOMBS, tag.getInt("dropped_bombs"));
        entityData.set(ADAPTATION, tag.getBoolean("adaptation"));
    }
    public int getBomb(){
        return entityData.get(BOMB);
    }
    public void setBomb(int i){
        entityData.set(BOMB,i);
    }


    public boolean isArmed(){
        return this.getBomb() >= 1200;
    }


    @Override
    public boolean tryToDigDown() {
        return super.tryToDigDown() || this.verticalCollision;
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float p_33318_) {
        if(!level().isClientSide){
            ThrownTumor tumor = new ThrownTumor(level(), this);
            double dx = livingEntity.getX() - this.getX();
            double dy = livingEntity.getY() + livingEntity.getEyeHeight() - 1.5;
            double dz = livingEntity.getZ() - this.getZ();
            Vec3 vec3;
            if (random.nextFloat() < 0.3f){
                vec3 = (new Vec3(2D, 1.3D, 5D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            }else if (random.nextFloat() < 0.3f){
                vec3 = (new Vec3(2D, 1.3D, -5D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            }else{
                vec3 = (new Vec3(0D, -2.0D, 0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            }

            if (SConfig.SERVER.hinden_explosive_effects != null){
                List<? extends String> ev = SConfig.SERVER.hinden_explosive_effects.get();
                int randomIndex = random.nextInt(ev.size());
                ResourceLocation randomElement1 = ResourceLocation.parse(ev.get(randomIndex));
                Holder<MobEffect> randomElement = Utilities.tryToCreateEffect(randomElement1);
                tumor.setMobEffect(randomElement);
            }
            tumor.setExplode(Level.ExplosionInteraction.MOB);
            tumor.moveTo(this.getX() +vec3.x(),this.getY()+vec3.y(),this.getZ() + vec3.z());
            tumor.shoot(dx, dy - tumor.getY() + Math.hypot(dx, dz) * 0.05F, dz, 1f * 2, 12.0F);
            level().addFreshEntity(tumor);
        }
    }


    @Override
    public boolean doHurtTarget(Entity entity) {
        this.playSound(Ssounds.SIEGER_BITE.value());
        return super.doHurtTarget(entity);
    }
    @Override
    public String getMutation() {
        if (isAdapted()){
            return "spore.entity.variant.overclocked";
        }
        return super.getMutation();
    }
    @Override
    public boolean getAdaptation() {
        return isAdapted();
    }

    private final List<HitboxesForParts> innatePartList = List.of(HitboxesForParts.HINDEN_FRONT,
            HitboxesForParts.HINDEN_BACK, HitboxesForParts.MAW,HitboxesForParts.RIGHT_CANNON, HitboxesForParts.LEFT_CANNON);
    @Override
    public List<HitboxesForParts> parts() {
        List<HitboxesForParts> values = new ArrayList<>();
        for (HitboxesForParts hitboxes : innatePartList){
            HitboxesForParts part = calculateChance(hitboxes,0.75f);
            if (part != null){
                values.add(part);
            }
        }
        return values;
    }

    @Override
    public ColdEndurance getEndurance() {
        return getAdaptation() ? ColdEndurance.ADAPTED_CALAMITY : super.getEndurance();
    }

    public class HindenBombAndRamGoal extends Goal {
        private final Hinderburg mob;
        private final double speedModifier;
        private final float attackRadiusSqr;

        private int clusterCooldown = 0;

        private int targetAirborneTicks = 0;
        private static final int AIRBORNE_THRESHOLD = 60;

        private boolean ramming = false;
        private int ramCooldown = 0;

        private int orbitDirection = 1;
        private int orbitSwitchTimer = 0;
        private static final int ORBIT_SWITCH_TICKS = 200;

        private static final double MIN_ALTITUDE = 10.0D;
        private static final double IDEAL_ALTITUDE = 14.0D;
        private static final double ORBIT_RADIUS = 12.0D;


        public HindenBombAndRamGoal(Hinderburg mob, double speedModifier, float attackRadius) {
            this.mob = mob;
            this.speedModifier = speedModifier;
            this.attackRadiusSqr = attackRadius * attackRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            if (target == null || !target.isAlive()) return false;
            return !this.mob.isArmed();
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.mob.getTarget();
            if (target == null || !target.isAlive()) return false;
            return !this.mob.isArmed();
        }

        @Override
        public void start() {
            this.clusterCooldown = 0;
            this.targetAirborneTicks = 0;
            this.ramming = false;
            this.ramCooldown = 0;
            this.orbitDirection = this.mob.getRandom().nextBoolean() ? 1 : -1;
            this.orbitSwitchTimer = 0;
        }

        @Override
        public void stop() {
            this.ramming = false;
            this.mob.getNavigation().stop();
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

            if (!target.onGround() && !target.isInWater()) {
                this.targetAirborneTicks++;
            } else {
                this.targetAirborneTicks = 0;
            }

            if (++this.orbitSwitchTimer >= ORBIT_SWITCH_TICKS) {
                this.orbitSwitchTimer = 0;
                if (Math.random() < 0.5f){
                    this.orbitDirection *= -1;
                }
            }

            if (this.ramCooldown > 0) this.ramCooldown--;

            if (this.targetAirborneTicks > AIRBORNE_THRESHOLD && this.ramCooldown <= 0) {
                this.ramming = true;
            }

            if (this.ramming) {
                tickRam(target);
                return;
            }

            tickOrbitAndBomb(target);

        }

        private void tickRam(LivingEntity target) {

            Vec3 targetPos = target.position().add(0, target.getBbHeight() * 0.5D, 0);
            Vec3 dir = targetPos.subtract(this.mob.position());

            if (dir.lengthSqr() > 0.01D) {
                Vec3 norm = dir.normalize();
                this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(norm.scale(0.35D)));
            }

            if (this.mob.getBoundingBox().inflate(1.5D).intersects(target.getBoundingBox())) {
                performRamImpact(target);
                this.ramCooldown = 80;
            }

            if (target.onGround() || target.isInWater()) {
                this.ramming = false;
                this.targetAirborneTicks = 0;
            }

            if (this.mob.tickCount % 200 == 0) {
                this.ramming = false;
            }
        }

        private void performRamImpact(LivingEntity target) {
            this.mob.doHurtTarget(target);

            var aabb = this.mob.getBoundingBox().inflate(3.0D);
            for (var entity : this.mob.level().getEntities(this.mob, aabb)) {
                if (entity == this.mob) continue;
                if (!(entity instanceof LivingEntity living && Utilities.TARGET_SELECTOR.Test(living))) continue;

                Vec3 away = living.position().subtract(this.mob.position());
                if (away.lengthSqr() < 0.01D) {
                    away = new Vec3(1, 0, 0);
                }
                away = away.normalize();

                living.knockback(3.0D, -away.x, -away.z);
                living.setDeltaMovement(living.getDeltaMovement().add(0, 0.4D, 0));
                living.hurtMarked = true;
            }

            this.mob.playSound(Ssounds.SIEGER_BITE.value(), 2.0F, 0.8F);
        }

        public int extraShots(){
            AttributeInstance instance = mob.getAttribute(SAttributes.BALLISTIC);
            if (instance != null){
                return (int) instance.getValue();
            }
            return 0;
        }
        private void tickOrbitAndBomb(LivingEntity target) {
            double dy = this.mob.getY() - target.getY();

            double angle = Math.atan2(this.mob.getZ() - target.getZ(), this.mob.getX() - target.getX());
            angle += this.orbitDirection * 0.035D;

            double orbitX = target.getX() + Math.cos(angle) * ORBIT_RADIUS;
            double orbitZ = target.getZ() + Math.sin(angle) * ORBIT_RADIUS;
            double orbitY = target.getY() + IDEAL_ALTITUDE;

            if (dy < MIN_ALTITUDE) {
                orbitY = this.mob.getY() + 8.0D;
            }

            this.mob.getMoveControl().setWantedPosition(orbitX, orbitY, orbitZ, this.speedModifier);

            double distSqr = this.mob.distanceToSqr(target);
            if (distSqr <= this.attackRadiusSqr && this.mob.hasLineOfSight(target) && clusterCooldown <= 0){
                int extra = extraShots();
                for (int i = 0;i<random.nextInt(6,13+extra);i++){
                    this.mob.performRangedAttack(target, 1.0F);
                }
                this.clusterCooldown = mob.isAdapted() ? 20 : 40;
            }else {
                this.clusterCooldown--;
            }
        }
    }


    public class HindenNukeGoal extends Goal {
        private final Hinderburg mob;
        private static final double OVERSHOOT_DISTANCE = 25.0D;
        private Vec3 savedTargetPos = null;
        private int bombTimer = 0;
        private int collateralCooldown = 0;

        private static final int BOMB_RELEASE_TICKS = 40;
        private static final double SAFE_ALTITUDE = 8.0D;

        public HindenNukeGoal(Hinderburg mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            return target != null
                    && target.isAlive()
                    && this.mob.isArmed();
        }

        @Override
        public boolean canContinueToUse() {
            return this.savedTargetPos != null && this.bombTimer < BOMB_RELEASE_TICKS * 2;
        }

        @Override
        public void start() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            this.savedTargetPos = target.position();
            Vec3 direction = savedTargetPos.subtract(mob.position()).multiply(1,0,1).normalize();
            this.savedTargetPos = savedTargetPos.add(direction.scale(OVERSHOOT_DISTANCE));

            this.bombTimer = 0;
            this.collateralCooldown = 0;
            target.playSound(Ssounds.HINDEN_NUKE.value());
        }

        @Override
        public void stop() {
            this.savedTargetPos = null;
            this.bombTimer = 0;
            this.collateralCooldown = 0;
        }
        public int extraShots(){
            AttributeInstance instance = mob.getAttribute(SAttributes.BALLISTIC);
            if (instance != null){
                return (int) instance.getValue();
            }
            return 0;
        }
        @Override
        public void tick() {
            if (this.savedTargetPos == null) return;

            this.bombTimer++;

            double desiredY = this.savedTargetPos.y + SAFE_ALTITUDE;

            double moveX = this.savedTargetPos.x;
            double moveZ = this.savedTargetPos.z;
            Vec3 vec3 = new Vec3(moveX, desiredY, moveZ);

            this.mob.getMoveControl().setWantedPosition(vec3.x,vec3.y,vec3.z, 1.0D);

            this.mob.getLookControl().setLookAt(vec3.x,vec3.y,vec3.z, 30.0F, 30.0F);

            if (this.collateralCooldown > 0) {
                this.collateralCooldown--;
            } else if (this.bombTimer % 4 == 0) {
                for (int e  = 0; e<random.nextInt(3,8+extraShots());e++){
                    fireCollateralTumor();
                }
                this.collateralCooldown = 2;
            }

            if (this.bombTimer == BOMB_RELEASE_TICKS) {
                releaseNuke();
            }
        }

        private void fireCollateralTumor() {
            if (this.mob.level().isClientSide) return;

            Level level = this.mob.level();

            boolean useRightCannon = this.mob.getRandom().nextBoolean();

            double rx = (this.mob.getRandom().nextDouble() - 0.5D) * 2.0D;
            double ry = -this.mob.getRandom().nextDouble() * 0.5D - 0.2D;
            double rz = (this.mob.getRandom().nextDouble() - 0.5D) * 2.0D;
            Vec3 dir = new Vec3(rx, ry, rz).normalize();

            double sideOffset = useRightCannon ? 4.0D : -4.0D;
            double cosYaw = Math.cos(-this.mob.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
            double sinYaw = Math.sin(-this.mob.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));

            double spawnX = this.mob.getX() + (cosYaw * 2.0D) + (sinYaw * sideOffset);
            double spawnY = this.mob.getY() + 0.3D;
            double spawnZ = this.mob.getZ() + (sinYaw * 2.0D) - (cosYaw * sideOffset);

            ThrownTumor tumor = new ThrownTumor(level, this.mob);
            if (SConfig.SERVER.hinden_explosive_effects != null){
                List<? extends String> ev = SConfig.SERVER.hinden_explosive_effects.get();
                int randomIndex = random.nextInt(ev.size());
                ResourceLocation randomElement1 = ResourceLocation.parse(ev.get(randomIndex));
                Holder<MobEffect> randomElement = Utilities.tryToCreateEffect(randomElement1);
                tumor.setMobEffect(randomElement);
            }
            tumor.moveTo(spawnX, spawnY, spawnZ);
            tumor.setExplode(Level.ExplosionInteraction.MOB);

            tumor.shoot(dir.x, dir.y, dir.z, 1.5F, 8.0F);

            level.addFreshEntity(tumor);
        }


        private void releaseNuke() {
            if (this.mob.level().isClientSide) return;
            this.mob.SummonNuke();
        }
    }
}
