package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.phys.Vec3;

public class IkVortexFunnel extends IkKrakenLeg{
    public IkVortexFunnel(Grakensenker owner) {
        super(owner, 20, new Vec3(-3, 5, 0.25), new Vec3(-12, 9, 0), 0);
    }


    @Override
    public Vec3 getLegBasePos() {
        Vec3 pivot = owner.position().add(0, owner.getExtendedHeight(), 0);
        return pivot.add(applyYaw(defaultLimbOffset));
    }

    @Override
    protected void moveTipTowards(Vec3 target) {
        Vec3 currentPos = entities[entities.length - 1];
        Vec3 newPos = currentPos.lerp(target, 0.35f);
        entities[entities.length - 1] = newPos;
    }

    public void applyIK() {
        if (entities == null || entities.length == 0) return;

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos =  getLegBasePos();
        boolean tooFar = entities[entities.length - 1].distanceToSqr(defaultTipPos) > 225;

        float totalDistance = (float) basePos.distanceTo(defaultTipPos);

        float idealSegmentLength = totalDistance / entities.length;

        int firstElasticSegment = 1;
        int lastElasticSegment = entities.length - 2;

        moveTipTowards(defaultTipPos);

        for (int i = entities.length - 2; i >= 0; i--) {
            Vec3 nextPos = entities[i + 1];
            Vec3 dir = entities[i].subtract(nextPos);

            boolean isElastic = (i >= firstElasticSegment && i <= lastElasticSegment);

            if (dir.lengthSqr() > 0.0001f) {
                if (isElastic) {
                    dir = dir.normalize();
                } else {
                    dir = dir.normalize().scale(idealSegmentLength);
                }
            } else {
                dir = new Vec3(idealSegmentLength, 0, 0);
            }

            Vec3 solvedPos = nextPos.add(dir);
            moveSegmentTowards(i, solvedPos, tooFar);
        }

        moveSegmentTowards(0, basePos, tooFar);

        for (int i = 1; i < entities.length; i++) {
            Vec3 prevPos = entities[i - 1];
            Vec3 dir = entities[i].subtract(prevPos);

            boolean isElastic = i <= lastElasticSegment;

            if (dir.lengthSqr() > 0.0001f) {
                if (isElastic) {
                    dir = dir.normalize();
                } else {
                    dir = dir.normalize().scale(idealSegmentLength);
                }
            } else {
                dir = new Vec3(idealSegmentLength, 0, 0);
            }

            Vec3 solvedPos = prevPos.add(dir);
            moveSegmentTowards(i, solvedPos, tooFar);
        }
    }
}
