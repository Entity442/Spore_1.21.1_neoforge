package com.Harbinger.Spore.Sentities.Utility;

import com.Harbinger.Spore.ExtremelySusThings.SporeSavedData;
import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.HybridPathNavigation;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.UtilityEntity;
import com.Harbinger.Spore.Sentities.MovementControls.InfectedWallMovementControl;
import com.Harbinger.Spore.core.SConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;

public class Reaper extends UtilityEntity implements Enemy, ArmorPersentageBypass {
    private int attackAnimationTick;
    public Reaper(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new InfectedWallMovementControl(this);
        this.navigation = new HybridPathNavigation(this,this.level());
    }
    @Override
    public boolean removeWhenFarAway(double value) {
        if (this.level() instanceof ServerLevel serverLevel){
            SporeSavedData data = SporeSavedData.getDataLocation(serverLevel);
            return data != null && data.getAmountOfHiveminds() >= SConfig.SERVER.proto_spawn_world_mod.get() && value > 256;
        }
        return false;
    }
    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return super.calculateFallDamage(p_21237_, p_21238_) - 15;
    }
    public int getAttackAnimationTick(){
        return attackAnimationTick;
    }
    @Override
    protected void registerGoals() {
        addTargettingGoals();
        this.goalSelector.addGoal(3, new CustomMeleeAttackGoal(this, 1.25, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}});

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }
    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity living){
            living.addEffect(new MobEffectInstance(MobEffects.POISON,200,0));
            living.addEffect(new MobEffectInstance(MobEffects.CONFUSION,200,0));
        }
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        return super.doHurtTarget(entity);
    }
    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
        }else {
            super.handleEntityEvent(value);
        }
    }
    @Override
    public float amountOfDamage(float value) {
        return (float) ((SConfig.SERVER.specter_damage.get() * SConfig.SERVER.global_damage.get())/4f);
    }

    @Override
    public void tick() {
        super.tick();
        if (attackAnimationTick > 0){
            attackAnimationTick--;
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE)){
            amount = amount/2;
        }
        return super.hurt(source, amount);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.specter_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.specter_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.specter_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 3);

    }
}
