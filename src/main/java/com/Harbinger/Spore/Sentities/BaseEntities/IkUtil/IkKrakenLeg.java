package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class IkKrakenLeg {
    protected final RandomSource randomSource = RandomSource.create();
    protected final Grakensenker owner;
    protected final Vec3[] entities;
    protected int[] segmentVar;
    protected final Vec3 defaultBodyOffset;
    protected final Vec3 defaultLimbOffset;
    protected final float maxDistance;
    protected final float[] wiggleTimers;
    protected final float[] wiggleSpeeds;
    protected final float[] wiggleAmplitudes;
    protected final float[] wiggleOffsets;
    protected Vec3 sitPosition = null;
    protected Vec3 lastSitPosition = null;
    protected int stepUpTicks = 0;
    protected Vec3 lastOwnerPosition = Vec3.ZERO;
    protected Vec3 ownerMovementDelta = Vec3.ZERO;

    // Water-specific properties
    protected float waterDragSpeed = 0.8f; // How quickly legs drag behind in water
    protected float waterLiftAmount = 0.5f; // How much legs lift up in water
    protected float waterWiggleMultiplier = 2.0f; // Increased wiggle in water

    public IkKrakenLeg(Grakensenker owner, int amount, Vec3 defaultBodyOffset,
                       Vec3 defaultLimbOffset,
                       float maxDistance) {
        this.owner = owner;
        this.entities = new Vec3[amount];
        this.segmentVar = new int[amount];
        this.wiggleTimers = new float[amount];
        this.wiggleSpeeds = new float[amount];
        this.wiggleAmplitudes = new float[amount];
        this.wiggleOffsets = new float[amount];
        for (int i = 0; i < amount; i++) {
            entities[i] = new Vec3(0, 0, 0);
            segmentVar[i] = randomSource.nextInt(12);
            wiggleSpeeds[i] = 0.5f + randomSource.nextFloat() * getWiggleSpeed();
            wiggleAmplitudes[i] = 0.02f + randomSource.nextFloat() * getWiggleAmplitude();
            wiggleOffsets[i] = randomSource.nextFloat() * (float) Math.PI * 2;
            wiggleTimers[i] = randomSource.nextFloat() * 100;
        }
        this.defaultBodyOffset = defaultBodyOffset;
        this.defaultLimbOffset = defaultLimbOffset;
        this.maxDistance = maxDistance;
        this.lastOwnerPosition = owner.position();
    }

    public float getWiggleSpeed() {
        return owner.isInDeepWater() ? 1.5f : 0.75f; // Faster wiggle in water
    }

    public float getWiggleAmplitude() {
        return owner.isInDeepWater() ? 0.06f : 0.03f; // Larger amplitude in water
    }

    protected void updateWiggleTimers() {
        for (int i = 0; i < wiggleTimers.length; i++) {
            float speedMultiplier = owner.isInDeepWater() ? waterWiggleMultiplier : 1.0f;
            wiggleTimers[i] += 0.05f * wiggleSpeeds[i] * speedMultiplier;
            if (wiggleTimers[i] > 1000) wiggleTimers[i] -= 1000;
        }
    }

    public Vec3 getSitPosition() {
        return sitPosition;
    }

    protected void applyIdleWiggle() {
        RandomSource rand = this.randomSource;

        for (int i = 1; i < entities.length - 1; i++) {
            Vec3 current = entities[i];

            float time = wiggleTimers[i] + wiggleOffsets[i];

            // Different wiggle patterns for water vs land
            float xWiggle, yWiggle, zWiggle;

            if (owner.isInDeepWater()) {
                // More fluid, flowing wiggle in water
                xWiggle = (float) Math.sin(time * 1.2f) * wiggleAmplitudes[i] * 1.5f;
                yWiggle = (float) Math.sin(time * 0.8f + 1.0f) * wiggleAmplitudes[i] * 2.0f;
                zWiggle = (float) Math.sin(time * 1.0f + 2.0f) * wiggleAmplitudes[i] * 1.2f;
            } else {
                // Normal wiggle on land
                xWiggle = (float) Math.sin(time * 0.7f) * wiggleAmplitudes[i];
                yWiggle = (float) Math.sin(time * 1.2f + 1.5f) * wiggleAmplitudes[i] * 0.8f;
                zWiggle = (float) Math.sin(time * 0.9f + 2.0f) * wiggleAmplitudes[i] * 0.6f;
            }

            if (rand.nextFloat() < 0.05f) {
                xWiggle += (rand.nextFloat() - 0.5f) * 0.02f;
                yWiggle += (rand.nextFloat() - 0.5f) * 0.01f;
                zWiggle += (rand.nextFloat() - 0.5f) * 0.02f;
            }

            entities[i] = current.add(xWiggle, yWiggle, zWiggle);
        }
    }

    public Vec3[] getEntities() {
        return entities;
    }

    public Vec3 getLastSitPosition() {
        return lastSitPosition;
    }

    public int[] getSegmentVar() {
        return segmentVar;
    }

    public void writeVariants(CompoundTag tag, int ikN) {
        tag.putIntArray("variants" + ikN, segmentVar);
    }

    public void readVariants(CompoundTag tag, int ikN) {
        segmentVar = tag.getIntArray("variants" + ikN);
    }

    public Vec3 applyYaw(Vec3 offset) {
        float yawRad = owner.getYRot() * Mth.DEG_TO_RAD;
        float spinRad = owner.getWaterTicks() * 0.05f;

        return offset.yRot(-yawRad - Mth.HALF_PI + spinRad);
    }

    public Vec3 getLegBasePos() {
        Vec3 pivot = owner.position();
        return pivot.add(applyYaw(defaultLimbOffset));
    }

    public Vec3 getBodyOffset() {
        Vec3 pivot = owner.position().add(0, owner.getExtendedHeight(), 0);
        return pivot.add(applyYaw(defaultBodyOffset));
    }

    protected void moveSegmentTowards(int index, Vec3 target, boolean far) {
        Vec3 currentPos = entities[index];
        if (owner.isInDeepWater()) {
            // In water, segments move more slowly, creating a dragging effect
            float lerpSpeed = 0.15f + (index * 0.05f); // Tips drag more than base
            entities[index] = currentPos.lerp(target, lerpSpeed);
        } else {
            Vec3 newPos = currentPos.lerp(target, 0.35f);
            entities[index] = (far ? target : newPos);
        }
    }

    protected void moveTipTowards(Vec3 target) {
        int tip = entities.length - 1;
        Vec3 currentPos = entities[tip];

        if (owner.isInDeepWater()) {
            // In water, tip follows but with delay and water lift
            Vec3 waterTarget = target.add(0, waterLiftAmount, 0);
            entities[tip] = currentPos.lerp(waterTarget, waterDragSpeed);
            return;
        }

        float jumpVal = 3.5f;
        boolean val = stepUpTicks > 0 && isOwnerMoving();
        entities[tip] = currentPos.lerp(target.add(0, val ? jumpVal : -1, 0), 0.15f);
        if (val) {
            for (int i = 1; i < entities.length - 1; i++) {
                entities[tip] = currentPos.lerp(target.add(0, jumpVal, 0), 0.05f);
            }
        }
    }

    protected boolean isOwnerMoving() {
        return owner.getDeltaMovement().lengthSqr() > 0.005;
    }

    protected void updateOwnerMovementDelta() {
        Vec3 currentOwnerPos = owner.position();
        ownerMovementDelta = currentOwnerPos.subtract(lastOwnerPosition);
        lastOwnerPosition = currentOwnerPos;
    }

    protected void applyEntityMovementToLegs() {
        if (ownerMovementDelta.lengthSqr() < 0.0001) {
            return;
        }

        if (owner.isInDeepWater()) {
            // In water, apply movement with drag effect - tips move less than base
            for (int i = 0; i < entities.length; i++) {
                float dragFactor = 0.3f + (i * 0.1f); // Base moves more, tips drag
                dragFactor = Math.min(dragFactor, 1.0f);
                Vec3 draggedMovement = ownerMovementDelta.scale(dragFactor);
                entities[i] = entities[i].add(draggedMovement);
            }
        } else {
            // On land, move all segments equally
            for (int i = 0; i < entities.length-1; i++) {
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

    public void applyIK() {
        if (entities == null || entities.length == 0) return;

        // Update owner movement before IK calculations
        updateOwnerMovementDelta();
        applyEntityMovementToLegs();

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos = sitPosition == null ? getLegBasePos() : sitPosition;

        if (owner.isInDeepWater()) {
            // In water, no strict distance constraint - allow legs to drag
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
            // On land, use normal IK constraints
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
        for (int i = 1; i < entities.length; i++) {
            Vec3 prevPos = entities[i - 1];
            Vec3 dir = entities[i].subtract(prevPos);

            if (dir.lengthSqr() > 5f) {
                entities[i] = prevPos;
            }
        }

        applyIdleWiggle();
        updateWiggleTimers();
        if (stepUpTicks > 0) {
            stepUpTicks--;
        }
    }

    public void refreshLegStandingPoint() {
        if (owner.isInDeepWater()) {
            // In water, no need for stable footing - just follow the base position
            sitPosition = getLegBasePos().add(0, -2.0f, 0); // Slightly below in water
            return;
        }

        if (lastSitPosition != null && getLegBasePos().distanceTo(lastSitPosition) < maxDistance) {
            return;
        }
        sitPosition = findStableFooting();
        if (!sitPosition.equals(lastSitPosition)) {
            stepUpTicks = 10;
            lastSitPosition = sitPosition;
        }
    }

    protected Vec3 findStableFooting() {
        Level level = owner.level();

        if (level.isClientSide()) {
            return getLegBasePos();
        }

        Vec3 worldBasePos = getLegBasePos();
        int searchRadius = 6;
        int maxSearchDown = 12;
        int maxSearchUp = 6;

        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

        for (int y = 0; y >= -maxSearchDown; y--) {
            checkPos.set(worldBasePos.x, worldBasePos.y + y, worldBasePos.z);

            if (isSolidGround(level, checkPos)) {
                return new Vec3(
                        checkPos.getX() + 0.5,
                        checkPos.getY() - 1.0,
                        checkPos.getZ() + 0.5
                );
            }
        }

        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int z = -searchRadius; z <= searchRadius; z++) {
                for (int y = maxSearchUp; y >= -maxSearchDown; y--) {
                    checkPos.set(
                            worldBasePos.x + x,
                            worldBasePos.y + y,
                            worldBasePos.z + z
                    );

                    if (isSolidGround(level, checkPos)) {
                        if (level.isEmptyBlock(checkPos.above())) {
                            return new Vec3(
                                    checkPos.getX() + 0.5,
                                    checkPos.getY() - 1.0,
                                    checkPos.getZ() + 0.5
                            );
                        }
                    }
                }
            }
        }
        return worldBasePos;
    }

    private boolean isSolidGround(Level level, BlockPos pos) {
        return level.getBlockState(pos).isSolid() ||
                !level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
    }
}