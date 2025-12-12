package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.Sentities.AI.AOEMeleeAttackGoal;
import com.Harbinger.Spore.Sentities.AI.CalamitiesAI.*;
import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkKrakenArm;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkKrakenLeg;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.Sentities.WaterInfected;
import com.Harbinger.Spore.core.SAttributes;
import com.Harbinger.Spore.core.SConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.List;

public class Grakensenker extends Calamity implements TrueCalamity, WaterInfected {
    public static final EntityDataAccessor<Float> HEIGHT = SynchedEntityData.defineId(Grakensenker.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Integer> WATER_TICKS = SynchedEntityData.defineId(Grakensenker.class, EntityDataSerializers.INT);
    public static final float MIN_HEIGHT = 0f;
    public static final float MAX_HEIGHT = 4f;
    private final IkKrakenLeg BackRightTentacle;
    private final IkKrakenLeg BackLeftTentacle;
    private final IkKrakenLeg MiddleRightTentacle;
    private final IkKrakenLeg MiddleLeftTentacle;
    private final IkKrakenLeg FrontRightTentacle;
    private final IkKrakenLeg FrontLeftTentacle;
    private final IkKrakenArm RightArmTentacle;
    private final IkKrakenArm LeftArmTentacle;
    private final IkKrakenLeg[] TickTentacles;
    public Grakensenker(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        Vec3 BRT1 = new Vec3(0,0,0);Vec3 BRT2 = new Vec3(0,0,0);Vec3 BRT3 = new Vec3(0,0,0);Vec3 BRT4 = new Vec3(0,0,0);Vec3 BRT5 = new Vec3(0,0,0);Vec3 BRT6 = new Vec3(0,0,0);Vec3 BRT7 = new Vec3(0,0,0);
        BackRightTentacle = new IkKrakenLeg(this,new Vec3[]{BRT1,BRT2,BRT3,BRT4,BRT5,BRT6,BRT7},GrakenLegsModifiers.BACK_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.BACK_RIGHT_TENTACLE.offset, 4);
        Vec3 BLT1 = new Vec3(0,0,0);Vec3 BLT2 = new Vec3(0,0,0);Vec3 BLT3 = new Vec3(0,0,0);Vec3 BLT4 = new Vec3(0,0,0);Vec3 BLT5 = new Vec3(0,0,0);Vec3 BLT6 = new Vec3(0,0,0);Vec3 BLT7 = new Vec3(0,0,0);
        BackLeftTentacle = new IkKrakenLeg(this,new Vec3[]{BLT1,BLT2,BLT3,BLT4,BLT5,BLT6,BLT7},GrakenLegsModifiers.BACK_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.BACK_LEFT_TENTACLE.offset, 4);
        Vec3 MRT1 = new Vec3(0,0,0);Vec3 MRT2 = new Vec3(0,0,0);Vec3 MRT3 = new Vec3(0,0,0);Vec3 MRT4 = new Vec3(0,0,0);Vec3 MRT5 = new Vec3(0,0,0);Vec3 MRT6 = new Vec3(0,0,0);Vec3 MRT7 = new Vec3(0,0,0);
        MiddleRightTentacle = new IkKrakenLeg(this,new Vec3[]{MRT1,MRT2,MRT3,MRT4,MRT5,MRT6,MRT7},GrakenLegsModifiers.MIDDLE_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.MIDDLE_RIGHT_TENTACLE.offset, 6);
        Vec3 MLT1 = new Vec3(0,0,0);Vec3 MLT2 = new Vec3(0,0,0);Vec3 MLT3 = new Vec3(0,0,0);Vec3 MLT4 = new Vec3(0,0,0);Vec3 MLT5 = new Vec3(0,0,0);Vec3 MLT6 = new Vec3(0,0,0);Vec3 MLT7 = new Vec3(0,0,0);
        MiddleLeftTentacle = new IkKrakenLeg(this,new Vec3[]{MLT1,MLT2,MLT3,MLT4,MLT5,MLT6,MLT7},GrakenLegsModifiers.MIDDLE_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.MIDDLE_LEFT_TENTACLE.offset, 6);
        Vec3 FRT1 = new Vec3(0,0,0);Vec3 FRT2 = new Vec3(0,0,0);Vec3 FRT3 = new Vec3(0,0,0);Vec3 FRT4 = new Vec3(0,0,0);Vec3 FRT5 = new Vec3(0,0,0);Vec3 FRT6 = new Vec3(0,0,0);Vec3 FRT7 = new Vec3(0,0,0);Vec3 FRT8 = new Vec3(0,0,0);Vec3 FRT9 = new Vec3(0,0,0);Vec3 FRT10 = new Vec3(0,0,0);
        FrontRightTentacle = new IkKrakenLeg(this,new Vec3[]{FRT1,FRT2,FRT3,FRT4,FRT5,FRT6,FRT7,FRT8,FRT9,FRT10},GrakenLegsModifiers.FRONT_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.FRONT_RIGHT_TENTACLE.offset, 8);
        Vec3 FLT1 = new Vec3(0,0,0);Vec3 FLT2 = new Vec3(0,0,0);Vec3 FLT3 = new Vec3(0,0,0);Vec3 FLT4 = new Vec3(0,0,0);Vec3 FLT5 = new Vec3(0,0,0);Vec3 FLT6 = new Vec3(0,0,0);Vec3 FLT7 = new Vec3(0,0,0);Vec3 FLT8 = new Vec3(0,0,0);Vec3 FLT9 = new Vec3(0,0,0);Vec3 FLT10 = new Vec3(0,0,0);
        FrontLeftTentacle = new IkKrakenLeg(this,new Vec3[]{FLT1,FLT2,FLT3,FLT4,FLT5,FLT6,FLT7,FLT8,FLT9,FLT10},GrakenLegsModifiers.FRONT_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.FRONT_LEFT_TENTACLE.offset, 8);

        Vec3 RAT1 = new Vec3(0,0,0);Vec3 RAT2 = new Vec3(0,0,0);Vec3 RAT3 = new Vec3(0,0,0);Vec3 RAT4 = new Vec3(0,0,0);Vec3 RAT5 = new Vec3(0,0,0);Vec3 RAT6 = new Vec3(0,0,0);
        RightArmTentacle = new IkKrakenArm(this,new Vec3[]{RAT1,RAT2,RAT3,RAT4,RAT5,RAT6},GrakenLegsModifiers.LEFT_ARM.bodySet, GrakenLegsModifiers.LEFT_ARM.offset, 8);
        Vec3 LET1 = new Vec3(0,0,0);Vec3 LET2 = new Vec3(0,0,0);Vec3 LET3 = new Vec3(0,0,0);Vec3 LET4 = new Vec3(0,0,0);Vec3 LET5 = new Vec3(0,0,0);Vec3 LET6 = new Vec3(0,0,0);
        LeftArmTentacle = new IkKrakenArm(this,new Vec3[]{LET1,LET2,LET3,LET4,LET5,LET6},GrakenLegsModifiers.RIGHT_ARM.bodySet, GrakenLegsModifiers.RIGHT_ARM.offset, 8);

        TickTentacles = new IkKrakenLeg[]{BackRightTentacle,BackLeftTentacle,MiddleRightTentacle,MiddleLeftTentacle,FrontRightTentacle,FrontLeftTentacle,RightArmTentacle,LeftArmTentacle};
    }
    enum GrakenLegsModifiers{
        BACK_LEFT_TENTACLE(new Vec3(-3,3,1),new Vec3(-6, -1, 6)),
        BACK_RIGHT_TENTACLE(new Vec3(-3,3,-1),new Vec3(-6, -1, -6)),
        MIDDLE_LEFT_TENTACLE(new Vec3(-1,2,1),new Vec3(0, -1, 6)),
        MIDDLE_RIGHT_TENTACLE(new Vec3(-1,2,-1),new Vec3(0, -1, -6)),
        FRONT_LEFT_TENTACLE(new Vec3(-2,3,1),new Vec3(8, -1, 6)),
        FRONT_RIGHT_TENTACLE(new Vec3(-2,3,-1),new Vec3(8, -1, -6)),
        LEFT_ARM(new Vec3(0,3,1),new Vec3(5, 3.5, 4)),
        RIGHT_ARM(new Vec3(0,3,-1),new Vec3(5, 3.5, -4));
        private final Vec3 bodySet;
        private final Vec3 offset;

        GrakenLegsModifiers(Vec3 bodySet, Vec3 offset) {
            this.bodySet = bodySet;
            this.offset = offset;
        }
    }
    public IkKrakenLeg getBackRightTentacle(){
        return BackRightTentacle;
    }
    public IkKrakenLeg getBackLeftTentacle(){
        return BackLeftTentacle;
    }
    public IkKrakenLeg getMiddleRightTentacle(){
        return MiddleRightTentacle;
    }
    public IkKrakenLeg getMiddleLeftTentacle(){
        return MiddleLeftTentacle;
    }
    public IkKrakenLeg getFrontRightTentacle(){
        return FrontRightTentacle;
    }
    public IkKrakenLeg getFrontLeftTentacle(){
        return FrontLeftTentacle;
    }
    public IkKrakenLeg[] getTentacles(){
        return TickTentacles;
    }
    public IkKrakenArm getRightArmTentacle(){
        return RightArmTentacle;
    }
    public IkKrakenArm getLeftArmTentacle(){
        return LeftArmTentacle;
    }

    public void travel(Vec3 vec) {
        if (this.isEffectiveAi() && this.isInFluidType()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95D));
        } else {
            super.travel(vec);
        }
    }
    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        return false;
    }

    @Override
    public int chemicalRange() {
        return 16;
    }

    @Override
    public List<? extends String> buffs() {
        return SConfig.SERVER.gazen_buffs.get();
    }

    @Override
    public List<? extends String> debuffs() {
        return SConfig.SERVER.gazen_debuffs.get();
    }

    public float getExtendedHeight(){
        return entityData.get(HEIGHT);
    }
    public void setHeight(float value){
        entityData.set(HEIGHT,value);
    }
    public int getWaterTicks(){
        return entityData.get(WATER_TICKS);
    }
    public void setWaterTicks(int value){
        entityData.set(WATER_TICKS,value);
    }
    public boolean isInDeepWater(){
        return entityData.get(WATER_TICKS) > 40;
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HEIGHT, 0f);
        builder.define(WATER_TICKS, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("height", entityData.get(HEIGHT));
        tag.putInt("water", entityData.get(WATER_TICKS));
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(HEIGHT,tag.getFloat("height"));
        entityData.set(WATER_TICKS,tag.getInt("water"));
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        EntityDimensions baseDimensions = super.getDefaultDimensions(pose);
        return baseDimensions.scale(1,1+(getExtendedHeight() * 0.5f));
    }


    @Override
    public void tick() {
        super.tick();
        updateHeight();
        if (tickCount % 5 == 0) {
            for (IkKrakenLeg leg : TickTentacles) {
                leg.refreshLegStandingPoint();
            }
        }
        for (IkKrakenLeg leg : TickTentacles) {
            leg.applyIK();
        }

    }
    public void updateHeight() {
        if (level().isClientSide) return;

        float current = getExtendedHeight();
        float target = current;
        if (inDeepWater(getOnPos())){
            if (getWaterTicks() <= 80){
                setWaterTicks(getWaterTicks()+1);
            }
        }else {
            if (getWaterTicks() > 0){
                setWaterTicks(getWaterTicks()-1);
            }
        }
        boolean deepWater = isInDeepWater();
        double wantedY = moveControl.getWantedY() + 2;
        boolean wantsLowStance = (wantedY < this.getY() + this.getBbHeight()) && moveControl.hasWanted();

        if (wantsLowStance || deepWater) {
            target -= 0.05f;
        }else {
            target += 0.08f;
        }
        target = Math.max(MIN_HEIGHT, Math.min(MAX_HEIGHT, target));
        if (Math.abs(target - current) > 0.01f) {
            setHeight(target);
        }
    }

    protected boolean inDeepWater(BlockPos pos){
        BlockPos firstPos = pos.offset(-3,0,-3);
        BlockPos secondPos = pos.offset(3,3,3);
        return BlockPos.betweenClosedStream(firstPos, secondPos).allMatch(this::checkForFluid);
    }
    boolean checkForFluid(BlockPos pos){
        BlockState state = level().getBlockState(pos);
        FluidState fluidstate = state.getFluidState();
        return state.getCollisionShape(this.level(), pos).isEmpty() && fluidstate.is(FluidTags.WATER);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.gazen_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.gazen_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.gazen_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.STEP_HEIGHT, 1.5)
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
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        if (HEIGHT.equals(dataAccessor)){
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(dataAccessor);
    }
    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(4, new AOEMeleeAttackGoal(this, 1.5, false,2.5 ,6, livingEntity -> {return TARGET_SELECTOR.test(livingEntity);}){
            protected double getAttackReachSqr(LivingEntity entity) {
                float f = Grakensenker.this.getBbWidth();
                return (double)(f * 2F * f * 2F + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.2));
        this.goalSelector.addGoal(6,new CalamityInfectedCommand(this));
        this.goalSelector.addGoal(7,new SummonScentInCombat(this));
        this.goalSelector.addGoal(8,new SporeBurstSupport(this));
        super.registerGoals();
    }
}
