package com.Harbinger.Spore.Sentities.Calamities;

import com.Harbinger.Spore.Sentities.BaseEntities.Calamity;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.HohlMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkKrakenLeg;
import com.Harbinger.Spore.Sentities.BaseEntities.IkUtil.IkLeviLeg;
import com.Harbinger.Spore.Sentities.BaseEntities.LeviathanMultipart;
import com.Harbinger.Spore.Sentities.TrueCalamity;
import com.Harbinger.Spore.core.SAttributes;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sentities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.PartEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Leviathan extends Calamity implements TrueCalamity {
    private static final int SEGMENT_COUNT = 2;
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID =
            SynchedEntityData.defineId(Leviathan.class, EntityDataSerializers.OPTIONAL_UUID);
    private final CalamityMultipart[] subEntities;
    private LeviathanMultipart firstSegment;
    public final CalamityMultipart head;
    private final IkLeviLeg[] legs;
    public final float[] ringBuffer = new float[64];
    public int ringBufferIndex = -1;

    public Leviathan(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        IkLeviLeg frontRightLeg = new IkLeviLeg(this,6,LEG_POSITIONS.FRONT_RIGHT_TENTACLE.bodySet,LEG_POSITIONS.FRONT_RIGHT_TENTACLE.offset,6);
        IkLeviLeg frontLeftLeg = new IkLeviLeg(this,6,LEG_POSITIONS.FRONT_LEFT_TENTACLE.bodySet,LEG_POSITIONS.FRONT_LEFT_TENTACLE.offset,6);
        IkLeviLeg backRightLeg = new IkLeviLeg(this,5,LEG_POSITIONS.BACK_RIGHT_TENTACLE.bodySet,LEG_POSITIONS.BACK_RIGHT_TENTACLE.offset,4);
        IkLeviLeg backLeftLeg = new IkLeviLeg(this,5,LEG_POSITIONS.BACK_LEFT_TENTACLE.bodySet,LEG_POSITIONS.BACK_LEFT_TENTACLE.offset,4);
        legs = new IkLeviLeg[]{frontLeftLeg,frontRightLeg,backLeftLeg,backRightLeg};
        this.head = new CalamityMultipart(this, "head", 3F, 3F);
        this.subEntities = new CalamityMultipart[]{this.head};
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1);
    }

    public IkLeviLeg[] getLegs(){
        return legs;
    }
    /* ---------------- DATA ---------------- */
    public void travel(Vec3 vec) {
        if (this.isEffectiveAi() && this.isInFluidType()) {
            this.moveRelative(0.1F, vec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.7D));
        } else {
            super.travel(vec);
        }
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        for(int e = 0;e<legs.length;e++){
            legs[e].writeVariants(tag,e);
        }
    }
    @Override
    public void setId(int p_20235_) {
        super.setId(p_20235_);
        for (int i = 0; i < this.subEntities.length; i++)
            this.subEntities[i].setId(p_20235_ + i + 1);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        for(int e = 0;e<legs.length;e++){
            legs[e].readVariants(tag,e);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHILD_UUID, Optional.empty());
    }

    @Nullable
    public UUID getChildId() {
        return entityData.get(CHILD_UUID).orElse(null);
    }

    public void setChildId(@Nullable UUID id) {
        entityData.set(CHILD_UUID, Optional.ofNullable(id));
    }

    @Nullable
    public LeviathanMultipart getFirstSegment() {
        if (firstSegment == null && !level().isClientSide) {
            UUID id = getChildId();
            if (id != null) {
                Entity e = ((ServerLevel) level()).getEntity(id);
                if (e instanceof LeviathanMultipart part) {
                    firstSegment = part;
                }
            }
        }
        return firstSegment;
    }

    /* ---------------- ATTRIBUTES ---------------- */

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SConfig.SERVER.sieger_hp.get() * SConfig.SERVER.global_health.get())
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, SConfig.SERVER.sieger_damage.get() * SConfig.SERVER.global_damage.get())
                .add(Attributes.ARMOR, SConfig.SERVER.sieger_armor.get() * SConfig.SERVER.global_armor.get())
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.STEP_HEIGHT, 1.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                .add(SAttributes.TOXICITY, 0.0D);
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
        this.tickPart(this.head, (double)(f2 * -3F), 0.0D, (double)(-f15 * -3F));
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

    public CalamityMultipart[] getSubEntities() {
        return this.subEntities;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @org.jetbrains.annotations.Nullable PartEntity<?>[] getParts() {
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

    @Override
    protected void onEffectAdded(MobEffectInstance instance, @Nullable Entity source) {
        super.onEffectAdded(instance, source);
        if (firstSegment == null) return;
        MobEffectInstance existing = firstSegment.getEffect(instance.getEffect());
        if (existing == null || existing.getDuration() < instance.getDuration() - 5) {
            firstSegment.addEffect(new MobEffectInstance(instance));
        }
    }

    @Override
    protected void onEffectRemoved(MobEffectInstance instance) {
        super.onEffectRemoved(instance);
        if (firstSegment == null){
            return;
        }else {
            firstSegment.removeEffect(instance.getEffect());
        }
    }

    @Override
    public boolean hurt(CalamityMultipart calamityMultipart, DamageSource source, float value) {
        hurt(source,value);
        return false;
    }

    @Override
    public int chemicalRange() {
        return 0;
    }

    @Override
    public List<? extends String> buffs() {
        return List.of();
    }

    @Override
    public List<? extends String> debuffs() {
        return List.of();
    }

    /*----------------- LEG POSITIONS --------*/
    enum LEG_POSITIONS{
        BACK_LEFT_TENTACLE(new Vec3(-2,1,0.75),new Vec3(-4, -1, 6)),
        BACK_RIGHT_TENTACLE(new Vec3(-2,1,-0.75),new Vec3(-4, -1, -6)),
        FRONT_LEFT_TENTACLE(new Vec3(0,1.5,0.75),new Vec3(4, -1, 6)),
        FRONT_RIGHT_TENTACLE(new Vec3(0,1.5,-0.75),new Vec3(4, -1, -6)),
        LEFT_ARM(new Vec3(0,3,1),new Vec3(8, 2.5, 6)),
        RIGHT_ARM(new Vec3(0,3,-1),new Vec3(8, 2.5, -6));
        private final Vec3 bodySet;
        private final Vec3 offset;

        LEG_POSITIONS(Vec3 bodySet, Vec3 offset) {
            this.bodySet = bodySet;
            this.offset = offset;
        }
    }
    /* ---------------- TICK ---------------- */

    @Override
    public void tick() {
        super.tick();
        for (IkLeviLeg leg : legs) {
            leg.refreshLegStandingPoint();
            leg.applyIK();
        }
        // Update rotation buffer
        if (++ringBufferIndex == 64) ringBufferIndex = 0;
        ringBuffer[ringBufferIndex] = getYRot();

        if (!level().isClientSide) {

            if (shouldSpawnChain()) {
                createChain();
            }

            updateChain();
        }
    }

    private boolean shouldSpawnChain() {
        LeviathanMultipart part = getFirstSegment();
        return part == null || !part.isAlive();
    }

    /* ---------------- CHAIN CREATION ---------------- */

    private void createChain() {
        LeviathanMultipart previous = null;

        for (int i = 0; i < SEGMENT_COUNT; i++) {
            LeviathanMultipart part =
                    new LeviathanMultipart(Sentities.LEVIATHAN_SEG.get(), level());

            part.setPos(getX(), getY(), getZ());
            part.setParent(i == 0 ? this : previous);
            part.setColor(this.getMutationColor());
            part.setTail(i == SEGMENT_COUNT - 1);

            level().addFreshEntity(part);

            if (i == 0) {
                setChildId(part.getUUID());
                firstSegment = part;
            } else {
                previous.setChildId(part.getUUID());
            }

            previous = part;
        }
    }

    /* ---------------- POSITION UPDATES ---------------- */

    private void updateChain() {
        LeviathanMultipart part = getFirstSegment();
        if (part == null) return;

        Vec3 anchor = this.position();
        float xRot = this.getXRot();
        float yRot = this.getYRot();

        int index = 0;

        while (part != null) {

            float yaw = getRingBuffer(4 + index * 2, 1.0F);

            anchor = part.tickMultipartPosition(
                    this.getId(),
                    anchor,
                    xRot,
                    yRot,
                    yaw,
                    index == 0
            );

            xRot = part.getXRot();

            Entity child = part.getChild();
            part = child instanceof LeviathanMultipart next ? next : null;

            index++;
        }
    }

    public float getRingBuffer(int offset, float partialTicks) {
        if (isDeadOrDying()) partialTicks = 0.0F;

        partialTicks = 1.0F - partialTicks;

        int i = ringBufferIndex - offset & 63;
        int j = ringBufferIndex - offset - 1 & 63;

        float d0 = ringBuffer[i];
        float d1 = Mth.wrapDegrees(ringBuffer[j] - d0);

        return d0 + d1 * partialTicks;
    }

    /* ---------------- CLEANUP ---------------- */

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        LeviathanMultipart part = getFirstSegment();
        while (part != null) {
            Entity next = part.getChild();
            part.discard();
            part = next instanceof LeviathanMultipart l ? l : null;
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        LeviathanMultipart part = getFirstSegment();
        while (part != null) {
            Entity next = part.getChild();
            part.discard();
            part = next instanceof LeviathanMultipart l ? l : null;
        }
    }
}