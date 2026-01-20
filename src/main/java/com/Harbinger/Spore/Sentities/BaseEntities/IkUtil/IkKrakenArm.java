package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Optional;

public class IkKrakenArm extends IkKrakenLeg {
    protected final boolean rightArm;
    @Nullable
    protected LivingEntity target;
    private final Vec3 RightVec = new Vec3(4,0,-16);
    private final Vec3 LeftVec = new Vec3(4,0,16);
    private final Vec3 RightMidVec = new Vec3(4, 3.5, -5);
    private final Vec3 LeftMidVec = new Vec3(4, 3.5, 5);
    private final Vec3 RightMidVec2 = new Vec3(7, 1.5, -5);
    private final Vec3 LeftMidVec2 = new Vec3(7, 1.5, 5);
    private final Vec3 MouthPosition = new Vec3(0, 1.5, 0);
    private static final Vector3f nullVec = new Vector3f(0);

    // Arm-specific water properties
    private final float armWaterDragSpeed = 0.7f; // Arms drag faster than legs in water
    private final float armWaterLiftAmount = 0.3f; // Less lift for arms

    public IkKrakenArm(Grakensenker owner, int amount, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance, boolean rightArm) {
        super(owner, amount, defaultBodyOffset, defaultLimbOffset, maxDistance);
        this.rightArm = rightArm;
    }

    @Override
    public float getWiggleAmplitude() {
        return owner.isInDeepWater() ? 0.03f : 0.01f; // Larger amplitude in water
    }

    @Override
    public float getWiggleSpeed() {
        return owner.isInDeepWater() ? 0.8f : 0.5f; // Faster wiggle in water
    }

    @Override
    public void refreshLegStandingPoint() {
        int hitValues = rightArm ? owner.getRightArmDelay() : owner.getLeftArmDelay();
        boolean full = rightArm ? owner.isRightArmFull() : owner.isLeftArmFull();
        Vector3f vector3f = rightArm ? owner.getRightArm() : owner.getLeftArm();

        if (owner.level().isClientSide){
            sitPosition = vector3f == nullVec || hitValues > 0 ? getLegBasePos() : new Vec3(vector3f).add(0, 1, 0);
            sitPosition = full ? owner.isInDeepWater() ? sitPosition : getMouthPosition() : sitPosition;
            lastSitPosition = sitPosition;
        } else {
            sitPosition = this.target == null || hitValues > 0 ? getLegBasePos() : this.target.position().add(0, 1, 0);
            sitPosition = full ? owner.isInDeepWater() ? sitPosition : getMouthPosition() : sitPosition;
            lastSitPosition = sitPosition;
            if (owner.tickCount % 10 == 0 && hitValues <= 0 && !full){
                setTarget();
            }
        }
    }

    @Override
    protected void moveTipTowards(Vec3 value) {
        int tip = entities.length - 1;
        Vec3 currentPos = entities[tip];

        if (owner.isInDeepWater()) {
            // In water, arms drag more fluidly
            Vec3 waterTarget = value.add(0, armWaterLiftAmount, 0);
            entities[tip] = currentPos.lerp(waterTarget, armWaterDragSpeed);
        } else {
            // On land, normal movement
            Vec3 newPos = currentPos.lerp(value, owner.level().isClientSide ? 0.35f : 0.2f);
            entities[tip] = newPos;
        }
    }

    @Override
    protected void moveSegmentTowards(int index, Vec3 target, boolean far) {
        Vec3 currentPos = entities[index];

        if (owner.isInDeepWater()) {
            // In water, segments move with variable speed creating a flowing effect
            float lerpSpeed = 0.2f + (index * 0.08f); // Tips drag more than base
            entities[index] = currentPos.lerp(target, lerpSpeed);
        } else {
            Vec3 newPos = currentPos.lerp(target, 0.35f);
            entities[index] = (far ? target : newPos);
        }
    }

    public void setTarget(){
        Optional<LivingEntity> targetOp = findAndSetTarget();
        if (targetOp.isPresent()){
            this.target = targetOp.get();
        } else {
            target = null;
        }
    }

    public Optional<LivingEntity> findAndSetTarget() {
        Level level = owner.level();
        Vec3 pivot = applyYaw(rightArm ? RightVec : LeftVec);
        AABB searchBox = new AABB(
                owner.getX() - 16, owner.getY(),  owner.getZ() - 16,
                owner.getX() + 16, owner.getY() + owner.getExtendedHeight() + 4,  owner.getZ() + 16
        ).move(pivot);
        return level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                e -> e.isAlive() && e != owner && !(e.getVehicle() == owner) && Utilities.TARGET_SELECTOR.Test(e) && TargetingConditions.forCombat().test(owner,e)
        ).stream().findFirst();
    }

    public Vec3 getMidSecPivot(){
        Vec3 pivot = applyYaw(rightArm ? RightMidVec : LeftMidVec);
        return owner.position().add(pivot).add(0, owner.getExtendedHeight(), 0);
    }

    public Vec3 getMidSecPivot2(){
        Vec3 pivot = applyYaw(rightArm ? RightMidVec2 : LeftMidVec2);
        return owner.position().add(pivot).add(0, owner.getExtendedHeight(), 0);
    }

    public Vec3 getMouthPosition(){
        Vec3 pivot = applyYaw(MouthPosition);
        return owner.position().add(pivot).add(0, owner.getExtendedHeight(), 0);
    }

    protected void moveMidSegmentTowards(int index, Vec3 target) {
        if (this.target != null || owner.isInDeepWater()){
            return; // Don't move mid segments when targeting or in water
        }
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.2f);
        entities[index] = newPos;
    }

    @Override
    protected void applyEntityMovementToLegs() {
        if (ownerMovementDelta.lengthSqr() < 0.0001) {
            return;
        }

        if (owner.isInDeepWater()) {
            // In water, apply movement with drag effect for arms
            for (int i = 0; i < entities.length; i++) {
                float dragFactor = 0.4f + (i * 0.08f); // Base moves more, tips drag
                dragFactor = Math.min(dragFactor, 1.0f);
                Vec3 draggedMovement = ownerMovementDelta.scale(dragFactor);
                entities[i] = entities[i].add(draggedMovement);
            }
        } else {
            // On land, move all segments equally
            for (int i = 0; i < entities.length; i++) {
                entities[i] = entities[i].add(ownerMovementDelta);
            }
        }

        // Adjust sit positions
        if (sitPosition != null) {
            sitPosition = sitPosition.add(ownerMovementDelta);
        }
        if (lastSitPosition != null) {
            lastSitPosition = lastSitPosition.add(ownerMovementDelta);
        }
    }

    @Override
    public void applyIK() {
        // Update owner movement before IK calculations
        updateOwnerMovementDelta();
        applyEntityMovementToLegs();

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos = sitPosition == null ? getLegBasePos() : sitPosition;

        if (owner.isInDeepWater()) {
            // In water, use fluid dragging IK
            moveTipTowards(defaultTipPos);

            // Simplified IK for water - more fluid and loose
            for (int i = entities.length - 2; i >= 0; i--) {
                Vec3 nextPos = entities[i + 1];
                Vec3 dir = entities[i].subtract(nextPos);

                float segmentLength = 1.0f;
                if (dir.lengthSqr() > 0.0001f) {
                    dir = dir.normalize().scale(segmentLength);
                } else {
                    dir = new Vec3(segmentLength, 0, 0);
                }

                Vec3 solvedPos = nextPos.add(dir);
                moveSegmentTowards(i, solvedPos, false);
            }
        } else {
            // On land, use normal IK with distance constraints
            boolean tooFar = entities[entities.length - 1].distanceToSqr(defaultTipPos) > 225;
            moveTipTowards(defaultTipPos);

            for (int i = entities.length - 2; i >= 0; i--) {
                Vec3 nextPos = entities[i + 1];
                Vec3 dir = entities[i].subtract(nextPos);

                float segmentLength = 1.0f;
                if (dir.lengthSqr() > 0.0001f) {
                    dir = dir.normalize().scale(segmentLength);
                } else {
                    dir = new Vec3(segmentLength, 0, 0);
                }

                Vec3 solvedPos = nextPos.add(dir);
                moveSegmentTowards(i, solvedPos, tooFar);
            }
        }

        // Always connect base to body
        entities[0] = basePos;

        // Apply constraints from base to tip
        for (int i = 1; i < entities.length; i++) {
            Vec3 prevPos = entities[i - 1];
            Vec3 dir = entities[i].subtract(prevPos);

            float segmentLength = 1.0f;
            if (dir.lengthSqr() > 0.0001f) {
                dir = dir.normalize().scale(segmentLength);
            } else {
                dir = new Vec3(segmentLength, 0, 0);
            }

            Vec3 solvedPos = prevPos.add(dir);
            moveSegmentTowards(i, solvedPos, false);
        }

        // Move mid segments only on land when not targeting
        if (!owner.isInDeepWater() && target == null) {
            moveMidSegmentTowards(entities.length/4, getMidSecPivot());
            moveMidSegmentTowards(entities.length/2, getMidSecPivot2());
        }

        applyIdleWiggle();
        updateWiggleTimers();
        if (stepUpTicks > 0) {
            stepUpTicks--;
        }

        // Update owner's arm position
        float x = (float) entities[entities.length-1].x();
        float y = (float) entities[entities.length-1].y();
        float z = (float) entities[entities.length-1].z();
        if (!owner.level().isClientSide){
            if (rightArm){
                owner.setRightArm(new Vector3f(x,y,z));
            } else {
                owner.setLeftArm(new Vector3f(x,y,z));
            }
        }
        entities[0] = basePos;
    }

    @Override
    protected void applyIdleWiggle() {
        RandomSource rand = this.randomSource;

        for (int i = 1; i < entities.length - 1; i++) {
            Vec3 current = entities[i];

            float time = wiggleTimers[i] + wiggleOffsets[i];

            float xWiggle, yWiggle, zWiggle;

            if (owner.isInDeepWater()) {
                // More dramatic, flowing wiggle in water for arms
                xWiggle = (float) Math.sin(time * 1.0f) * wiggleAmplitudes[i] * 1.2f;
                yWiggle = (float) Math.sin(time * 0.7f + 0.8f) * wiggleAmplitudes[i] * 1.5f;
                zWiggle = (float) Math.sin(time * 0.9f + 1.5f) * wiggleAmplitudes[i] * 1.0f;
            } else {
                // Subtle wiggle on land for arms
                xWiggle = (float) Math.sin(time * 0.5f) * wiggleAmplitudes[i] * 0.8f;
                yWiggle = (float) Math.sin(time * 0.8f + 1.0f) * wiggleAmplitudes[i] * 0.5f;
                zWiggle = (float) Math.sin(time * 0.6f + 1.8f) * wiggleAmplitudes[i] * 0.4f;
            }

            if (rand.nextFloat() < 0.05f) {
                xWiggle += (rand.nextFloat() - 0.5f) * 0.02f;
                yWiggle += (rand.nextFloat() - 0.5f) * 0.01f;
                zWiggle += (rand.nextFloat() - 0.5f) * 0.02f;
            }

            entities[i] = current.add(xWiggle, yWiggle, zWiggle);
        }
    }
}