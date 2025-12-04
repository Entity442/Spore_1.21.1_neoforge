package com.Harbinger.Spore.Sentities.Utility;


import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Seffects;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class Vanguard extends UtilityEntity implements CrossbowAttackMob, Enemy {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Vanguard.class, EntityDataSerializers.BOOLEAN);
    private int attackAnimationTick;
    public Vanguard(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.specter_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.specter_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.specter_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ATTACK_KNOCKBACK, 3);

    }

    @Override
    protected void registerGoals() {
        addTargettingGoals();
        this.goalSelector.addGoal(3, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}});

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }
    public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeapon) {
        return projectileWeapon == Items.CROSSBOW;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_CHARGING_CROSSBOW, false);
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(Seffects.MYCELIUM,600,0));
        }
        return super.doHurtTarget(entity);
    }

    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(value);
        }
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_219059_, DifficultyInstance p_219060_) {
        this.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(Items.CROSSBOW));
    }
    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.populateDefaultEquipmentSlots(this.random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public boolean isChargingCrossbow() {
        return this.entityData.get(IS_CHARGING_CROSSBOW);
    }

    public void setChargingCrossbow(boolean p_33302_) {
        this.entityData.set(IS_CHARGING_CROSSBOW, p_33302_);
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }


    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        this.performCrossbowAttack(this, 3.2f);
    }



    public static class CustomCrossbowAttackGoal<T extends Mob & CrossbowAttackMob & RangedAttackMob>
            extends RangedCrossbowAttackGoal<T> {

        private final T mob;
        private final double speed;

        private final double minShootDistSqr;
        private final double maxShootDistSqr;

        public CustomCrossbowAttackGoal(T mob,
                                        double speedModifier,
                                        float attackRadius,
                                        float minShootDist,
                                        float maxShootDist) {

            super(mob, speedModifier, attackRadius);

            this.mob = mob;
            this.speed = speedModifier;


            this.minShootDistSqr = minShootDist * minShootDist;
            this.maxShootDistSqr = maxShootDist * maxShootDist;
        }

        @Override
        public void tick() {
            LivingEntity target = mob.getTarget();
            if (target == null || !target.isAlive()) {
                tryIdleCharging();
                return;
            }

            double distSqr = mob.distanceToSqr(target);

            boolean shouldFire = distSqr >= minShootDistSqr && distSqr <= maxShootDistSqr;

            boolean shouldCharge = distSqr > maxShootDistSqr;

            if (shouldCharge) {
                tryLongRangeCharging(target);
                return;
            }

            super.tick();

            if (shouldFire && isReadyToFire()) {
                mob.performRangedAttack(target, 1.0F);
                forceReset();
            }
        }

        private void tryIdleCharging() {
            if (!mob.isUsingItem() && !mob.isHolding(item -> item.getItem() instanceof CrossbowItem)) return;
            if (isUncharged()) {
                mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(mob, item -> item instanceof CrossbowItem));
            }
        }
        private void tryLongRangeCharging(LivingEntity target) {
            mob.getNavigation().moveTo(target, speed * 0.6);
            mob.getLookControl().setLookAt(target, 30F, 30F);

            if (isUncharged() && mob.hasLineOfSight(target)) {
                mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(mob, item -> item instanceof CrossbowItem));
            }
        }

        private boolean isUncharged() {
            return getState() == CrossbowState.UNCHARGED;
        }

        private boolean isReadyToFire() {
            return getState() == CrossbowState.READY_TO_ATTACK;
        }

        private CrossbowState getState() {
            try {
                Field f = RangedCrossbowAttackGoal.class.getDeclaredField("crossbowState");
                f.setAccessible(true);
                return (CrossbowState) f.get(this);
            } catch (Exception e) {
                return CrossbowState.UNCHARGED;
            }
        }

        private void forceReset() {
            try {
                Field f = RangedCrossbowAttackGoal.class.getDeclaredField("crossbowState");
                f.setAccessible(true);
                f.set(this, CrossbowState.UNCHARGED);
            } catch (Exception ignored) {}
        }
        enum CrossbowState {
            UNCHARGED,
            CHARGING,
            CHARGED,
            READY_TO_ATTACK;

            private CrossbowState() {
            }
        }
    }

}
