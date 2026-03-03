package com.Harbinger.Spore.Sentities.Hyper;


import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.LeapGoal;
import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.BaseEntities.Hyper;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Grober extends Hyper implements ArmorPersentageBypass {
    public static final EntityDataAccessor<Integer> ATTACK_TYPE = SynchedEntityData.defineId(Grober.class, EntityDataSerializers.INT);
    public Grober(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }
    private int attackAnimationTick;
    public AnimationState kickAnimation = new AnimationState();
    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.inquisitor_loot.get();
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_TYPE, 0);
    }

    @Override
    protected int calculateFallDamage(float v1, float v2) {
        if (v1 >= 8){
            damageStomp(level(),this.getOnPos(),7);
        }
        return 0;
    }
    public int getAttackAnimationTick(){
        return attackAnimationTick;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        triggerAnimation(Math.random() < 0.5 ? MELEE_STATES.KICK.getValue() : MELEE_STATES.SMASH.getValue());
        if (getMeleeState() == MELEE_STATES.SMASH){
            damageStomp(level(),entity.getOnPos(),3);
        }
        if (getMeleeState() == MELEE_STATES.KICK && entity instanceof LivingEntity living){
            living.hurtMarked = true;
            living.knockback((3f),  Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(this.getYRot() * ((float) Math.PI / 180F))));
        }
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        return super.doHurtTarget(entity);
    }
    public void handleEntityEvent(byte value) {
        if (value == 4) {
            if (getMeleeState() == MELEE_STATES.KICK){
                kickAnimation.start(this.tickCount);
            }
            this.attackAnimationTick = 10;
        }else {
            super.handleEntityEvent(value);
        }
    }
    @Override
    protected void addRegularGoals() {
        super.addRegularGoals();
        this.goalSelector.addGoal(2, new LeapGoal(this,0.8F){
            @Override
            public void start() {
                triggerAnimation(MELEE_STATES.SMASH.value);
                super.start();
                mob.level().broadcastEntityEvent(mob, (byte)4);
            }
        });
        this.goalSelector.addGoal(3, new AOEMeleeAttackGoal(this ,1.2,true, 1.2 ,3, livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.inquisitor_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.inquisitor_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.inquisitor_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);

    }

    @Override
    public void tick() {
        super.tick();
        if (attackAnimationTick >= 0){
            if (attackAnimationTick == 0){
                kickAnimation.stop();
            }
            attackAnimationTick--;
        }
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.INQUISITOR_AMBIENT.value();
    }

    protected SoundEvent getDeathSound() {
        return Ssounds.INF_DAMAGE.value();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    protected void damageStomp(Level level, BlockPos pos, double range){
        if (level instanceof ServerLevel serverLevel){
        for(int i = 0; i <= 2*range; ++i) {
            for(int j = 0; j <= 2*range; ++j) {
                for(int k = 0; k <= 2*range; ++k) {
                    double distance = Mth.sqrt((float) ((i-range)*(i-range) + (j-range)*(j-range) + (k-range)*(k-range)));
                    if (Math.abs(i) != 2 || Math.abs(j) != 2 || Math.abs(k) != 2) {
                        if (distance<range+(0.5)){
                            BlockPos blockpos = pos.offset( i-(int)range,j-(int)range,k-(int)range);
                            BlockState state = level.getBlockState(blockpos);
                            boolean airBelow = level.getBlockState(blockpos.below()).isAir();
                            double breakSpeed = state.getDestroySpeed(level,pos);
                            if (airBelow && state.getDestroySpeed(level,pos) >= 0 && breakSpeed <= getBreaking() && Math.random() < 0.1){
                                FallingBlockEntity.fall(serverLevel,blockpos,state);
                                serverLevel.removeBlock(blockpos,false);
                            }
                        }}}}}}
        this.playSound(Ssounds.LANDING.value());
    }
    public Grober.MELEE_STATES getMeleeState() {
        return Grober.MELEE_STATES.byId(this.entityData.get(ATTACK_TYPE) & 255);
    }
    public void triggerAnimation(int states){
        entityData.set(ATTACK_TYPE,states);
    }

    @Override
    public float amountOfDamage(float value) {
        return getMeleeState() == MELEE_STATES.KICK ? value/2 : 0;
    }

    public enum MELEE_STATES{
        SMASH(0),
        KICK(1);
        private final int value;
        MELEE_STATES(int value) {
            this.value = value;
        }
        public int getValue(){
            return value;
        }
        private static final Grober.MELEE_STATES[] BY_ID = Arrays.stream(values()).sorted(Comparator.
                comparingInt(Grober.MELEE_STATES::getValue)).toArray(Grober.MELEE_STATES[]::new);
        public static Grober.MELEE_STATES byId(int id) {
            return BY_ID[id % BY_ID.length];
        }
    }
}
