package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class IkKrakenLeg {
    protected final Grakensenker owner;
    protected final Vec3[] entities;
    protected final Vec3 defaultBodyOffset;
    protected final Vec3 defaultLimbOffset;
    protected final float maxDistance;
    protected Vec3 sitPosition =  null;
    protected Vec3 lastSitPosition = null;
    public IkKrakenLeg(Grakensenker owner, Vec3[] entities, Vec3 defaultBodyOffset,
                       Vec3 defaultLimbOffset,
                       float maxDistance) {
        this.owner = owner;
        this.entities = entities;
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

    private void moveSegmentTowards(int index, Vec3 target,boolean far) {
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.25f);
        entities[index] = (far ? target : newPos);
    }
    private void moveTipTowards(int index, Vec3 target) {
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.15f);
        entities[index] = newPos;
    }

    public void applyIK() {
        if (entities == null || entities.length == 0) return;

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos = getLegBasePos();
        boolean tooFar = entities[entities.length - 1].distanceToSqr(defaultTipPos) > 100;

        Vec3 targetPos = sitPosition == null ? defaultTipPos : sitPosition;

        entities[0] = basePos;

        moveTipTowards(entities.length - 1, targetPos);

        for (int i = entities.length - 2; i >= 0; i--) {
            Vec3 nextPos = entities[i + 1];
            Vec3 dir = entities[i].subtract(nextPos).normalize();
            Vec3 solvedPos = nextPos.add(dir);
            moveSegmentTowards(i, solvedPos, tooFar);
        }

        // 🦴 Forward pass (base → tip)
        entities[0] = basePos;
        for (int i = 1; i < entities.length; i++) {
            Vec3 prevPos = entities[i - 1];
            Vec3 dir = entities[i].subtract(prevPos).normalize();
            Vec3 solvedPos = prevPos.add(dir);
            moveSegmentTowards(i, solvedPos, tooFar);
        }
    }


    public void refreshLegStandingPoint(){
        if (lastSitPosition != null && getLegBasePos().distanceTo(lastSitPosition) < maxDistance){
            return;
        }
        sitPosition = findStableFooting(defaultLimbOffset);
        if (!sitPosition.equals(lastSitPosition)) lastSitPosition = sitPosition;
    }

    protected Vec3 findStableFooting(Vec3 tip) {
        Level level = owner.level();
        Vec3 legBasePos = getLegBasePos();
        if (level.isClientSide){
            return legBasePos;
        }
        int area = 3;
        for (int x = -area;x < area;x++){
            for (int y = -area;y < area;y++){
                for (int z = -area;z < area;z++){
                    BlockPos checkPos = new BlockPos((int) (tip.x+x), (int) (tip.y+y), (int) (tip.z+z));
                    if (owner.level().getBlockState(checkPos).isSolidRender(owner.level(), checkPos)) {
                        return new Vec3(
                                checkPos.getX() + 0.5,
                                checkPos.getY() - 0.5,
                                checkPos.getZ() + 0.5
                        );
                    }
                }
            }
        }

        return legBasePos;
    }
}
