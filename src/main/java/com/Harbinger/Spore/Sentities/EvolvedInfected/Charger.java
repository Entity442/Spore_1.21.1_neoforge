package com.Harbinger.Spore.Sentities.EvolvedInfected;

import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.SdamageTypes;
import com.Harbinger.Spore.core.Sentities;
import com.Harbinger.Spore.core.Ssounds;
import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.AI.HurtTargetGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.BufferAI;
import com.Harbinger.Spore.Sentities.ArmedInfected;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;
import com.Harbinger.Spore.Sentities.BaseEntities.Infected;
import com.Harbinger.Spore.Sentities.BasicInfected.InfectedPlayer;
import com.Harbinger.Spore.Sentities.Projectile.Echo;
import com.Harbinger.Spore.Sentities.SporeVibrationParameters;
import com.Harbinger.Spore.Sentities.SporeVibrationUser;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;

public class Charger extends EvolvedInfected implements VibrationSystem , SporeVibrationParameters, ArmedInfected,HasUsableSlot {
    private final DynamicGameEventListener<Listener> dynamicGameEventListener = new DynamicGameEventListener<>(new Listener(this));
    private final User vibrationUser = new SporeVibrationUser(this,this);
    private final Data vibrationData = new Data();
    public static final EntityDataAccessor<BlockPos> ATTACK_POSITION = SynchedEntityData.defineId(Charger.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Charger.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VIBRATION_LIFE = SynchedEntityData.defineId(Charger.class, EntityDataSerializers.INT);
    private int earAnimationTick;
    private int meleeAttackTicks;
    private int meleeAttackTicksAnimation;

    public Charger(EntityType<? extends EvolvedInfected> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        this.navigation = new WallClimberNavigation(this, level());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true) {
            @Override
            public boolean canUse() {
                return super.canUse() && SConfig.SERVER.higher_thinking.get();
            }
        });
        this.goalSelector.addGoal(2, new HurtTargetGoal(this , livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}, Infected.class).setAlertOthers(Infected.class));
        this.goalSelector.addGoal(2,new CustomMeleeAttackGoal(this,1.5,true)
        {
            @Override
            public boolean canUse() {
                return super.canUse() && meleeAttackTicks > 0;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && meleeAttackTicks > 0;
            }
        });
        this.goalSelector.addGoal(3, new RushToSoundGoal(this));
        this.goalSelector.addGoal(4 ,new BufferAI(this ));
        this.goalSelector.addGoal(1,new RandomStrollGoal(this,1){
            @Override
            public void start() {
                super.start();
                playSound(Ssounds.CHARGER_ECO.value());
                Locate(random.nextInt(2,7),4);
            }
        });
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        playMeleeAnimation();
        return super.doHurtTarget(entity);
    }
    private void Locate(int count, float spread) {
        Level level = this.level();
        if (level.isClientSide()) return;
        for (int i = 0; i < count; i++) {
            Echo echo = new Echo(Sentities.ECHO.get(), level);
            float yaw = this.random.nextFloat() * 360.0F;
            float pitch = (this.random.nextFloat() - 0.5F) * spread * 2;
            float radYaw = (float)Math.toRadians(yaw);
            float radPitch = (float)Math.toRadians(pitch);
            double x = Math.cos(radPitch) * Math.cos(radYaw);
            double y = Math.sin(radPitch);
            double z = Math.cos(radPitch) * Math.sin(radYaw);
            echo.setOwner(this);
            echo.moveTo(this.getX(),this.getY()+1.25,this.getZ());
            Vec3 direction = new Vec3(x, y, z).normalize();
            echo.shoot(direction);
            level.addFreshEntity(echo);
        }
    }
    public void playMeleeAnimation(){
        meleeAttackTicksAnimation = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
    }

    public int getMeleeAttackTicks(){return meleeAttackTicksAnimation;}

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        if (living != null && living.isAlive()){
            meleeAttackTicks = 40;
        }
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.charger_loot.get();
    }

    @Override
    public DamageSource getCustomDamage(LivingEntity entity) {
        if (Math.random() < 0.3){
            return SdamageTypes.knight_damage(this);
        }
        return super.getCustomDamage(entity);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.charger_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.charger_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.charger_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        InfectedPlayer.createName(this,SConfig.DATAGEN.name.get());
        InfectedPlayer.createItems(this,EquipmentSlot.HEAD,SConfig.DATAGEN.player_h.get());
        InfectedPlayer.createItems(this,EquipmentSlot.CHEST,SConfig.DATAGEN.player_c.get());
        InfectedPlayer.createItems(this,EquipmentSlot.LEGS,SConfig.DATAGEN.player_l.get());
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(this.random, instance);
        return super.finalizeSpawn(serverLevelAccessor, instance, p_21436_, p_21437_);
    }

    @Override
    public void tick() {
        super.tick();
        handleVibrationLife();
        if (this.level() instanceof ServerLevel serverLevel) {
            Ticker.tick(serverLevel, this.vibrationData, this.vibrationUser);
        }
        if (earAnimationTick > 0){
            earAnimationTick--;
        }
        if (meleeAttackTicks > 0){
            meleeAttackTicks--;
        }
        if (meleeAttackTicksAnimation > 0){
            meleeAttackTicksAnimation--;
        }
        if (tickCount % 20 == 0){
            if (!level().isClientSide()){
                attackNearby();
            }
            int variant = getTypeVariant();
            if (horizontalCollision || (verticalCollision && !verticalCollisionBelow)){
                setVariant(variant < 3 ? variant+1 : 2);
            }else {
                if (variant == 1 && !level().getBlockState(getOnPos().above(3)).isAir()){
                    setVariant(1);
                }else if (variant == 2 && !level().getBlockState(getOnPos().above(2)).isAir()){
                    setVariant(2);
                }else {
                    setVariant(variant > 0 ? variant-1 : 0);
                }
            }
        }
    }
    public void attackNearby(){
        List<LivingEntity> entities = this.level().getEntitiesOfClass(
                LivingEntity.class,
                this.getBoundingBox().inflate(1.3),
                living -> {return Utilities.TARGET_SELECTOR.Test(living);}
        );
        if (entities.isEmpty()){
            return;
        }else {
            setTarget(entities.get(0));
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_WALL)){
            int variant = getTypeVariant();
            setVariant(variant < 3 ? variant+1 : 2);
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_POSITION, BlockPos.ZERO);
        builder.define(DATA_ID_TYPE_VARIANT, 0);
        builder.define(VIBRATION_LIFE, 0);
    }

    public void setVibrationLife(int val){
        entityData.set(VIBRATION_LIFE,val);
    }
    public int getVibrationLife(){
        return entityData.get(VIBRATION_LIFE);
    }
    public void handleVibrationLife(){
        int i = getVibrationLife();
        if (i>0){
            setVibrationLife(i-1);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    public CHARGER_WALK_MODIFIER getVariant() {
        return CHARGER_WALK_MODIFIER.byId(this.getTypeVariant() & 255);
    }

    public int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public void setVariant(CHARGER_WALK_MODIFIER variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
        this.refreshDimensions();
    }
    public void setVariant(int val){
        this.entityData.set(DATA_ID_TYPE_VARIANT, val);
        this.refreshDimensions();
    }
    @Override
    public void onSyncedDataUpdated(List<SynchedEntityData.DataValue<?>> values) {
        super.onSyncedDataUpdated(values);
        if (values.equals(DATA_ID_TYPE_VARIANT)){
            this.refreshDimensions();
        }
    }
    protected SoundEvent getAmbientSound() {
        return Ssounds.CHARGER_AMBIENT.value();
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

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> serverLevelBiConsumer) {
        Level level = this.level();
        if (level instanceof ServerLevel serverLevel) {
            serverLevelBiConsumer.accept(this.dynamicGameEventListener, serverLevel);
        }
    }

    @Override
    public @NotNull Data getVibrationData() {
        return vibrationData;
    }

    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.meleeAttackTicksAnimation = 10;
        } else if (value == 6) {
            this.earAnimationTick = 10;
        } else {
            super.handleEntityEvent(value);
        }
    }

    public int getEarAnimationTick(){
        return earAnimationTick;
    }

    @Override
    public @NotNull User getVibrationUser() {
        return vibrationUser;
    }

    @Override
    public void setTargetedLocation(ServerLevel serverLevel, BlockPos blockPos) {
        if (blockPos != BlockPos.ZERO && getVibrationLife() <= 0){
            this.earAnimationTick = 10;
            setVibrationLife(100);
            this.level().broadcastEntityEvent(this, (byte)6);
            this.playSound(Ssounds.CHARGER_EARS.value());
        }
        entityData.set(ATTACK_POSITION, blockPos);
    }

    @Override
    public BlockPos getTargetLocation() {
        return entityData.get(ATTACK_POSITION);
    }

    @Override
    public PositionSource getPositionSource() {
        return new EntityPositionSource(this, this.getEyeHeight());
    }

    @Override
    public boolean hasUsableSlot(EquipmentSlot slot) {
        return slot != EquipmentSlot.FEET;
    }

    public int getMeleeTicks(){
        return meleeAttackTicks;
    }

    public enum CHARGER_WALK_MODIFIER{
        REGULAR(0, EntityDimensions.scalable(0.6f, 2.3f)),
        BEND(1, EntityDimensions.scalable(0.6f, 1.9f)),
        CRAWL(2, EntityDimensions.scalable(0.6f, 0.85f));

        private final int id;
        private final EntityDimensions dimensions;
        private static final CHARGER_WALK_MODIFIER[] BY_ID = Arrays.stream(values())
                .sorted(Comparator.comparingInt(CHARGER_WALK_MODIFIER::getId))
                .toArray(CHARGER_WALK_MODIFIER[]::new);

        CHARGER_WALK_MODIFIER(int id, EntityDimensions dimensions) {
            this.id = id;
            this.dimensions = dimensions;
        }

        public int getId() {
            return this.id;
        }

        public EntityDimensions getDimensions(){
            return dimensions;
        }

        public static CHARGER_WALK_MODIFIER byId(int id) {
            return BY_ID[id % BY_ID.length];
        }
    }


    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return getVariant().getDimensions();
    }

    public static class RushToSoundGoal extends Goal {
        private final Charger charger;
        private int checkTicks;
        private int repathTicks;

        public RushToSoundGoal(Charger charger) {
            this.charger = charger;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return !charger.getTargetLocation().equals(BlockPos.ZERO);
        }

        @Override
        public boolean canContinueToUse() {
            return !charger.getTargetLocation().equals(BlockPos.ZERO) && charger.getVibrationLife() > 0;
        }

        @Override
        public void start() {
            repathTicks = 0;
            checkTicks = 10;
            BlockPos pos = charger.getTargetLocation();

            charger.getNavigation().moveTo(
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    2
            );
        }

        @Override
        public void tick() {
            if (--repathTicks <= 0) {
                repathTicks = 20;
                BlockPos pos = charger.getTargetLocation();
                charger.getNavigation().moveTo(
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5,
                        2
                );
            }

            if (--checkTicks <= 0) {
                checkTicks = 10;
                List<LivingEntity> entities = charger.level().getEntitiesOfClass(
                        LivingEntity.class,
                        charger.getBoundingBox().inflate(2),
                        living -> {return Utilities.TARGET_SELECTOR.Test(living);}
                );

                if (!entities.isEmpty()) {
                    charger.setTarget(entities.getFirst());
                    stop();
                }
            }

            if (charger.blockPosition().closerThan(charger.getTargetLocation(), 2.0)) {
                charger.getEntityData().set(Charger.ATTACK_POSITION, BlockPos.ZERO);
                charger.playMeleeAnimation();
                stop();
            }
        }

        @Override
        public void stop() {
            charger.getNavigation().stop();
            List<LivingEntity> entities = charger.level().getEntitiesOfClass(
                    LivingEntity.class,
                    charger.getBoundingBox().inflate(2),
                    living -> {return Utilities.TARGET_SELECTOR.Test(living);}
            );

            if (!entities.isEmpty()) {
                charger.setTarget(entities.get(0));
            }
        }
    }
}