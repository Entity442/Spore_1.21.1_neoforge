package com.Harbinger.Spore.Sentities.EvolvedInfected;

import com.Harbinger.Spore.Sentities.AI.CustomMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.BaseEntities.EvolvedInfected;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gorgon extends EvolvedInfected {
    private static final EntityDataAccessor<Integer> TARGET = SynchedEntityData.defineId(Gorgon.class, EntityDataSerializers.INT);
    private int attackAnimationTick;
    private int mouthAnimationTick;
    private int mouthAnimationTimer;
    public Gorgon(EntityType<? extends EvolvedInfected> type, Level level) {
        super(type, level);
    }
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(3, new CustomMeleeAttackGoal(this, 1.1, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 6.0 + entity.getBbWidth() * entity.getBbWidth();}


            @Override
            public void tick() {
                super.tick();
                mob.level().broadcastEntityEvent(mob, (byte)5);
                Gorgon.this.activateMouth();
            }
        });

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        super.registerGoals();
    }
    @Override
    public List<? extends String> getDropList() {
        return SConfig.DATAGEN.inf_knight_loot.get();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.knight_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.knight_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.knight_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1);

    }
    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TARGET, -1);
    }

    public void setTargetId(int e){
        entityData.set(TARGET,e);
    }
    public int getTargetId(){
        return entityData.get(TARGET);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (target == null){
            setTargetId(-1);
        }else {
            setTargetId(target.getId());
        }
    }

    protected SoundEvent getAmbientSound() {
        return Ssounds.WITCH_AMBIENT.value();
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
    public void handleEntityEvent(byte value) {
        if (value == 4) {
            this.attackAnimationTick = 10;
        }else if (value == 5) {
            this.mouthAnimationTimer = 10;
        } else {
            super.handleEntityEvent(value);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte)4);
        return super.doHurtTarget(entity);
    }
    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
        if (this.mouthAnimationTimer > 0) {
            --this.mouthAnimationTimer;
        }
        if (mouthAnimationTimer > 0){
            if (mouthAnimationTick < 10){
                mouthAnimationTick++;
            }
        }else{
            if (mouthAnimationTick > 0){
                mouthAnimationTick--;
            }
        }
    }
    public int getMouthAnimationTick(){
        return mouthAnimationTick;
    }
    public int getAttackAnimationTick(){
        return attackAnimationTick;
    }
    public void activateMouth(){
        mouthAnimationTimer = 10;
    }
}
