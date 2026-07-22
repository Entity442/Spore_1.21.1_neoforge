package com.Harbinger.Spore.Sentities.Hyper;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.FloatDiveGoal;
import com.Harbinger.Spore.Sentities.AI.LocHiv.BufferAI;
import com.Harbinger.Spore.Sentities.ArmedInfected;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.Hyper;
import com.Harbinger.Spore.Sentities.BasicInfected.InfectedPlayer;
import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
import com.Harbinger.Spore.Sentities.EvolvedInfected.HasUsableSlot;
import com.Harbinger.Spore.Sentities.Projectile.Echo;
import com.Harbinger.Spore.Sentities.SporeVibrationParameters;
import com.Harbinger.Spore.Sentities.SporeVibrationUser;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sentities;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

public class Berserker extends Hyper implements VibrationSystem, SporeVibrationParameters, ArmedInfected, HasUsableSlot, ArmorPersentageBypass {
    private final DynamicGameEventListener<Listener> dynamicGameEventListener = new DynamicGameEventListener<>(new Listener(this));
    private final User vibrationUser = new SporeVibrationUser(this,this);
    private final Data vibrationData = new Data();
    public static final EntityDataAccessor<BlockPos> ATTACK_POSITION = SynchedEntityData.defineId(Berserker.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Integer> CHARGE = SynchedEntityData.defineId(Berserker.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CROUCH = SynchedEntityData.defineId(Berserker.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VIBRATION_LIFE = SynchedEntityData.defineId(Berserker.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MELEE_ATTACK_TICKS = SynchedEntityData.defineId(Berserker.class, EntityDataSerializers.INT);
    private int earAnimationTick;
    private int meleeAttackTicksAnimation;
    public Berserker(EntityType<? extends Hyper> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean hasUsableSlot(EquipmentSlot slot) {
        return slot != EquipmentSlot.FEET;
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        setVibrationLife(0);
        playMeleeAnimation();
        setMeleeAttackTicks(40);
        return super.doHurtTarget(entity);
    }
    private void Locate(int count, float spread) {
        playSound(Ssounds.CHARGER_ECO.value());
        Level level = this.level();
        if (level.isClientSide()) return;
        AABB searchBox = this.getBoundingBox().inflate(8);
        List<LivingEntity> targets = level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                entity -> entity != this && entity.isAlive() && Utilities.TARGET_SELECTOR.Test(entity)
        );
        for (int i = 0; i < count; i++) {
            Echo echo = new Echo(Sentities.ECHO.get(), level);
            float yaw;
            float pitch;
            if (!targets.isEmpty()) {
                LivingEntity target = targets.getFirst();

                double dx = target.getX() - this.getX();
                double dy = target.getY() + target.getEyeHeight() / 2 - (this.getY() + 1.25);
                double dz = target.getZ() - this.getZ();

                float spreadOffset = (this.random.nextFloat() - 0.5F) * spread * 0.5F;
                float yawOffset = (this.random.nextFloat() - 0.5F) * spread * 0.5F;

                double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
                yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) + yawOffset;
                pitch = (float) -Math.toDegrees(Math.atan2(dy, horizontalDistance)) + spreadOffset;

                float radYaw = (float) Math.toRadians(yaw);
                float radPitch = (float) Math.toRadians(pitch);

                double x = Math.cos(radPitch) * Math.cos(radYaw);
                double y = Math.sin(radPitch);
                double z = Math.cos(radPitch) * Math.sin(radYaw);

                Vec3 direction = new Vec3(x, y, z).normalize();
                echo.shoot(direction);
            } else {
                yaw = this.random.nextFloat() * 360.0F;
                pitch = (this.random.nextFloat() - 0.5F) * spread * 2;
                float radYaw = (float) Math.toRadians(yaw);
                float radPitch = (float) Math.toRadians(pitch);

                double x = Math.cos(radPitch) * Math.cos(radYaw);
                double y = Math.sin(radPitch);
                double z = Math.cos(radPitch) * Math.sin(radYaw);

                Vec3 direction = new Vec3(x, y, z).normalize();
                echo.shoot(direction);
            }
            echo.setOwner(this);
            echo.moveTo(this.getX(), this.getY() + 1.25, this.getZ());
            level.addFreshEntity(echo);
        }
    }
    public void playMeleeAnimation(){
        meleeAttackTicksAnimation = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
    }
    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        if (living != null && living.isAlive() && getMeleeTicks() < 20){
            if (level() instanceof ServerLevel serverLevel){
                setTargetedLocation(serverLevel,BlockPos.ZERO);
            }
            setMeleeAttackTicks(40);
        }
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
    public int getDelay() {
        return getMeleeTicks();
    }
    public int getMeleeTicks(){
        return entityData.get(MELEE_ATTACK_TICKS);
    }
    public void setMeleeAttackTicks(int i){
        entityData.set(MELEE_ATTACK_TICKS,i);
    }
    @Override
    public Data getVibrationData() {
        return vibrationData;
    }

    @Override
    public User getVibrationUser() {
        return vibrationUser;
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof LivingEntity living){
            setMeleeAttackTicks(40);
            setTarget(living);
        }
        if (source.is(DamageTypes.IN_WALL)){
            setCrouch(true);
            return false;
        }
        return super.hurt(source, amount);
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
        int meleeAttackTicks = getMeleeTicks();
        if (meleeAttackTicks > 0){
            if (meleeAttackTicks == 1){
                setTarget(null);
            }
            setMeleeAttackTicks(meleeAttackTicks-1);
        }
        if (meleeAttackTicksAnimation > 0){
            meleeAttackTicksAnimation--;
        }
        if (tickCount % 20 == 0){
            if (!level().isClientSide()){
                attackNearby();
            }
            boolean val = horizontalCollision || goesBerserk() || !level().getBlockState(getOnPos().above(3)).isAir();
            setCrouch(val);
            AttributeInstance instance = getAttribute(Attributes.STEP_HEIGHT);
            if (instance != null){
                instance.setBaseValue(goesBerserk() ? 2 : 1);
            }
        }
        if (tickCount % 40 == 0){
            AttributeInstance instance = getAttribute(Attributes.MOVEMENT_SPEED);
            if (instance != null){
                instance.setBaseValue(goesBerserk() ? 0.3 : 0.2);
            }
        }
        if (getCharge()){
            int i = entityData.get(CHARGE);
            entityData.set(CHARGE,i-1);
        }
    }
    public void attackNearby(){
        if (getMeleeTicks() > 0){
            return;
        }
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
    public int getEarAnimationTick(){
        return earAnimationTick;
    }
    public int getMeleeAttackTicks(){return meleeAttackTicksAnimation;}

    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        InfectedPlayer.createName(this, SConfig.DATAGEN.name.get());
        InfectedPlayer.createItems(this,EquipmentSlot.HEAD,SConfig.DATAGEN.player_h.get());
        InfectedPlayer.createItems(this,EquipmentSlot.CHEST,SConfig.DATAGEN.player_c.get());
        InfectedPlayer.createItems(this,EquipmentSlot.LEGS, SConfig.DATAGEN.player_l.get());
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(this.random, instance);
        return super.finalizeSpawn(serverLevelAccessor, instance, p_21436_, p_21437_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_POSITION, BlockPos.ZERO);
        builder.define(CHARGE, 0);
        builder.define(CROUCH, false);
        builder.define(VIBRATION_LIFE, 0);
        builder.define(MELEE_ATTACK_TICKS, 0);
    }

    public void setVibrationLife(int val){
        entityData.set(VIBRATION_LIFE,val);
    }
    public int getVibrationLife(){
        return entityData.get(VIBRATION_LIFE);
    }
    public void setCharge(int val){
        if (entityData.get(CHARGE) < val){
            this.refreshDimensions();
        }
        entityData.set(CHARGE,val);
    }
    public boolean goesBerserk(){
        float val = getMaxHealth()/2;
        return getHealth() <= val;
    }
    public boolean getCharge(){
        return entityData.get(CHARGE) > 0;
    }
    public void setCrouch(boolean val){
        entityData.set(CROUCH,val);
        this.refreshDimensions();
    }
    public boolean getCrouch(){
        return entityData.get(CROUCH);
    }
    public void handleVibrationLife(){
        int i = getVibrationLife();
        if (i>0){
            setVibrationLife(i-1);
        }
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> serverLevelBiConsumer) {
        Level level = this.level();
        if (level instanceof ServerLevel serverLevel) {
            serverLevelBiConsumer.accept(this.dynamicGameEventListener, serverLevel);
        }
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
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.berserker_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.berserker_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.berserker_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true) {
            @Override
            public boolean canUse() {
                return super.canUse() && SConfig.SERVER.higher_thinking.get();
            }
        });
        this.goalSelector.addGoal(2,new AOEMeleeAttackGoal(this ,1.2,true, 2.5 ,5, livingEntity -> {return TARGET_SELECTOR.test(livingEntity);})
        {
            @Override
            public boolean canUse() {
                if (getMeleeTicks() <= 0){
                    return false;
                }
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                if (getMeleeTicks() <= 0){
                    return false;
                }
                return super.canContinueToUse();
            }

            @Override
            public void start() {
                super.start();
                if (Math.random() < 0.5){
                    LivingEntity livingentity = this.mob.getTarget();
                    if (livingentity != null && livingentity.distanceToSqr(mob) > 25){
                        setCharge(40);
                    }
                }
            }

            @Override
            protected void checkAndPerformAttack(LivingEntity entity, double p_25558_) {
                double d0 = this.getAttackReachSqr(entity);
                if (p_25558_ <= d0 && this.ticksUntilNextAttack <= 0 && mob.hasLineOfSight(entity)) {
                    this.resetAttackCooldown();
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    this.mob.doHurtTarget(entity);
                    AABB hitbox = entity.getBoundingBox().inflate(1).move(0,1,0);
                    List<LivingEntity> targets = entity.level().getEntitiesOfClass(LivingEntity.class , hitbox,victims);
                    for (LivingEntity en : targets) {
                        mob.doHurtTarget(en);
                        setCharge(0);
                    }
                    if (goesBerserk()){
                        for (BlockPos pos : BlockPos.betweenClosed(
                                Mth.floor(hitbox.minX), Mth.floor(hitbox.minY), Mth.floor(hitbox.minZ),
                                Mth.floor(hitbox.maxX), Mth.floor(hitbox.maxY), Mth.floor(hitbox.maxZ))) {
                            BlockState state = level().getBlockState(pos);
                            float value = state.getDestroySpeed(mob.level(),pos);
                            if (value > 0 && value <= getBreaking()){
                                interactBlock(pos, level());
                            }
                        }
                    }
                }
            }

            @Override
            public void stop() {
                super.stop();
                mob.setTarget(null);
                path = null;
                setCharge(0);
            }
        });
        this.goalSelector.addGoal(3, new BerserkerRush(this));
        this.goalSelector.addGoal(4 ,new BufferAI(this ));
        this.goalSelector.addGoal(4,new GoBackToTheNest(this){

            @Override
            public boolean canUse() {
                return hyper.getEvoPoints() > 1;
            }

            @Override
            public void tick() {

            }

            @Override
            public void start() {
                tryToLayCorpsesAround();
            }
        });
        this.goalSelector.addGoal(5,new RandomStrollGoal(this,1,200,true){
            @Override
            public void stop() {
                super.stop();
                Locate(random.nextInt(2,7),4);
            }
        });
        this.goalSelector.addGoal(6,new FloatDiveGoal(this));
    }

    enum SIZES{
        DEFAULT(EntityDimensions.scalable(1f, 3.5f)),
        ANIMAL(EntityDimensions.scalable(1f, 1.9f));
        public final EntityDimensions dimensions;

        SIZES(EntityDimensions dimensions) {
            this.dimensions = dimensions;
        }
    }


    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return getCharge() || getCrouch() ? SIZES.ANIMAL.dimensions : SIZES.DEFAULT.dimensions;
    }

    @Override
    public float amountOfDamage(float value) {
        return (float) ((SConfig.SERVER.berserker_damage.get() * SConfig.SERVER.global_damage.get()) * 0.25f);
    }

    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.berserker_loot.get();
    }

    public static class BerserkerRush extends Goal {
        private final Berserker charger;
        private int checkTicks;
        private int repathTicks;

        public BerserkerRush(Berserker charger) {
            this.charger = charger;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (charger.getMeleeTicks() > 0){
                return false;
            }
            return !charger.getTargetLocation().equals(BlockPos.ZERO);
        }

        @Override
        public boolean canContinueToUse() {
            if (charger.getMeleeTicks() > 0){
                return false;
            }
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
                    LivingEntity living = entities.get(0);
                    charger.doHurtTarget(living);
                    charger.setTarget(living);
                    stop();
                }
            }

            if (charger.blockPosition().closerThan(charger.getTargetLocation(), 2.0)) {
                charger.getEntityData().set(Charger.ATTACK_POSITION, BlockPos.ZERO);
                if (Math.random() < 0.3){
                    charger.Locate(3,2);
                }
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
                charger.setTarget(entities.getFirst());
            }
        }
    }
}
