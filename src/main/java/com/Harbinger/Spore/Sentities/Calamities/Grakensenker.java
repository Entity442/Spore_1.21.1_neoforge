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
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

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
    private final CalamityMultipart[] subEntities;
    public final CalamityMultipart Body;
    public final CalamityMultipart RightHand;
    public final CalamityMultipart LeftHand;
    public Grakensenker(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        BackRightTentacle = new IkKrakenLeg(this,7,GrakenLegsModifiers.BACK_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.BACK_RIGHT_TENTACLE.offset, 4);
        BackLeftTentacle = new IkKrakenLeg(this,7,GrakenLegsModifiers.BACK_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.BACK_LEFT_TENTACLE.offset, 4);
        MiddleRightTentacle = new IkKrakenLeg(this,7,GrakenLegsModifiers.MIDDLE_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.MIDDLE_RIGHT_TENTACLE.offset, 6);
        MiddleLeftTentacle = new IkKrakenLeg(this,7,GrakenLegsModifiers.MIDDLE_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.MIDDLE_LEFT_TENTACLE.offset, 6);
        FrontRightTentacle = new IkKrakenLeg(this,10,GrakenLegsModifiers.FRONT_RIGHT_TENTACLE.bodySet, GrakenLegsModifiers.FRONT_RIGHT_TENTACLE.offset, 8);
        FrontLeftTentacle = new IkKrakenLeg(this,10,GrakenLegsModifiers.FRONT_LEFT_TENTACLE.bodySet, GrakenLegsModifiers.FRONT_LEFT_TENTACLE.offset, 8);
        RightArmTentacle = new IkKrakenArm(this,10,GrakenLegsModifiers.LEFT_ARM.bodySet, GrakenLegsModifiers.LEFT_ARM.offset, 4,false);
        LeftArmTentacle = new IkKrakenArm(this,10,GrakenLegsModifiers.RIGHT_ARM.bodySet, GrakenLegsModifiers.RIGHT_ARM.offset, 4,true);
        TickTentacles = new IkKrakenLeg[]{BackRightTentacle,BackLeftTentacle,MiddleRightTentacle,MiddleLeftTentacle,FrontRightTentacle,FrontLeftTentacle,RightArmTentacle,LeftArmTentacle};
        this.Body = new CalamityMultipart(this, "body", 5F, 5F);
        this.RightHand = new CalamityMultipart(this, "right", 1.5F, 1.5F);
        this.LeftHand = new CalamityMultipart(this, "left", 1.5F, 1.5F);
        this.subEntities = new CalamityMultipart[]{ this.Body, this.RightHand,this.LeftHand};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }
    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
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
        value = calamityMultipart == this.Body ? value * 3 : value;
        return this.hurt(source,value);
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
        for(int e = 0;e<TickTentacles.length;e++){
            TickTentacles[e].writeVariants(tag,e);
        }
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(HEIGHT,tag.getFloat("height"));
        entityData.set(WATER_TICKS,tag.getInt("water"));
        for(int e = 0;e<TickTentacles.length;e++){
            TickTentacles[e].readVariants(tag,e);
        }
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        EntityDimensions baseDimensions = super.getDefaultDimensions(pose);
        return baseDimensions.scale(1,1+(getExtendedHeight() * 0.5f));
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
        this.tickPart(this.Body, (double)(f2 * 4.5F), 5.0D+getExtendedHeight(), (double)(-f15 * 4.5F));
        Vec3 rightHandVec = getRightArmTentacle().getEntities()[getRightArmTentacle().getEntities().length-1];
        Vec3 leftHandVec = getLeftArmTentacle().getEntities()[getLeftArmTentacle().getEntities().length-1];
        Vec3 rightHVec3 = rightHandVec == null ? position() : rightHandVec;
        Vec3 leftHVec3 = rightHandVec == null ? position() : leftHandVec;
        this.RightHand.setPos(rightHVec3.x, rightHVec3.y-0.5, rightHVec3.z);
        this.LeftHand.setPos(leftHVec3.x, leftHVec3.y-0.5, leftHVec3.z);
        for(int l = 0; l < this.subEntities.length; ++l) {
            this.subEntities[l].xo = avec3[l].x;
            this.subEntities[l].yo = avec3[l].y;
            this.subEntities[l].zo = avec3[l].z;
            this.subEntities[l].xOld = avec3[l].x;
            this.subEntities[l].yOld = avec3[l].y;
            this.subEntities[l].zOld = avec3[l].z;
        }
        super.aiStep();
    }

    @Override
    public void tick() {
        super.tick();
        updateHeight();
        for (IkKrakenLeg leg : TickTentacles) {
            leg.refreshLegStandingPoint();
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
        boolean wantsLowStance = (wantedY < this.getY() + this.getBbHeight()) && this.horizontalCollision;

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
