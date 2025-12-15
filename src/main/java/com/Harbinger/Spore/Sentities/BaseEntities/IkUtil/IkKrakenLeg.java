package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
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
    protected Vec3 sitPosition =  null;
    protected Vec3 lastSitPosition = null;
    protected Vec3[] steps = null;
    protected int stepCount = 0;
    public IkKrakenLeg(Grakensenker owner, int amount, Vec3 defaultBodyOffset,
                       Vec3 defaultLimbOffset,
                       float maxDistance) {
        this.owner = owner;
        this.entities = new Vec3[amount];
        this.segmentVar = new int[amount];
        for(int i = 0;i<amount;i++){
            entities[i] = new Vec3(0,0,0);
            segmentVar[i] = randomSource.nextInt(5);
        }
        this.defaultBodyOffset = defaultBodyOffset;
        this.defaultLimbOffset = defaultLimbOffset;
        this.maxDistance = maxDistance;
    }

    public Vec3 getSitPosition() {
        return sitPosition;
    }

    public Vec3[] getEntities() {
        return entities;
    }

    public Vec3 getLastSitPosition() {
        return lastSitPosition;
    }
    public int[] getSegmentVar(){
        return segmentVar;
    }
    public void writeVariants(CompoundTag tag,int ikN){
        tag.putIntArray("variants"+ikN,segmentVar);
    }
    public void readVariants(CompoundTag tag,int ikN){
        segmentVar = tag.getIntArray("variants"+ikN);
    }

    public Vec3 applyYaw(Vec3 offset) {
        return (offset).yRot(-owner.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
    }

    public Vec3 getLegBasePos() {
        Vec3 pivot = owner.position();
        return pivot.add(applyYaw(defaultLimbOffset));
    }

    public Vec3 getBodyOffset() {
        Vec3 pivot = owner.position().add(0, owner.getExtendedHeight(), 0);
        return pivot.add(applyYaw(defaultBodyOffset));
    }

    protected void moveSegmentTowards(int index, Vec3 target,boolean far) {
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.35f);
        entities[index] = (far ? target : newPos);
    }
    protected void moveTipTowards(Vec3 target) {
        int tip = entities.length - 1;

        if (steps != null && stepCount >= 0 && stepCount < steps.length) {
            Vec3 stepTarget = steps[stepCount];

            Vec3 current = entities[tip];
            entities[tip] = current.lerp(stepTarget, 0.35f);

            if (current.distanceToSqr(stepTarget) < 0.01) {
                stepCount--;

                if (stepCount < 0) {
                    steps = null;
                    stepCount = 0;
                    entities[tip] = target;
                    return;
                }
            }
            return;
        }
        Vec3 currentPos = entities[tip];
        entities[tip] = currentPos.lerp(target, 0.35f);
    }


    public void applyIK() {
        if (entities == null || entities.length == 0) return;

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos = sitPosition == null ? getLegBasePos() : sitPosition;
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
        moveSegmentTowards(0, basePos, tooFar);

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
            moveSegmentTowards(i, solvedPos, tooFar);
        }
    }


    public void refreshLegStandingPoint(){
        if (lastSitPosition != null && getLegBasePos().distanceTo(lastSitPosition) < maxDistance){
            return;
        }
        sitPosition = findStableFooting();
        if (!sitPosition.equals(lastSitPosition)) lastSitPosition = sitPosition;
    }

    protected Vec3 findStableFooting() {
        Level level = owner.level();

        if (level.isClientSide()) {
            return getLegBasePos();
        }

        Vec3 worldBasePos = getLegBasePos();
        int searchRadius = 2;
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
                            Vec3 targetPos = new Vec3(
                                    checkPos.getX() + 0.5,
                                    checkPos.getY() - 1.0,
                                    checkPos.getZ() + 0.5
                            );

                            createStepAnimation(worldBasePos, targetPos);
                            return targetPos;
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

    private void createStepAnimation(Vec3 startPos, Vec3 targetPos) {
        float height = 3.0f;

        Vec3 midpoint = startPos.add(targetPos).scale(0.5);
        midpoint = midpoint.add(0, height, 0);

        steps = new Vec3[3];

        steps[0] = startPos.add(0, height/2, 0);

        steps[1] = midpoint;

        steps[2] = targetPos;

        stepCount = steps.length - 1;
    }

}
