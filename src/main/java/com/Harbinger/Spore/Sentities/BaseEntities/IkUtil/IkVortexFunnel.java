package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class IkVortexFunnel extends IkKrakenLeg{
    private static final Vector3f V0 = new Vector3f();
    public IkVortexFunnel(Grakensenker owner) {
        super(owner, 10,Vec3.ZERO,Vec3.ZERO,Vec3.ZERO, 0);
    }


    @Override
    public Vec3 getLegBasePos() {
        Vec3 pivot = owner.position().add(0, owner.getExtendedHeight(), 0);
        return pivot.add(applyYaw(defaultLimbOffset));
    }

    @Override
    protected void moveTipTowards(Vec3 target) {
        if (!owner.getVortexVector().equals(V0)){
            target = new Vec3(owner.getVortexVector());
        }
        entities[entities.length - 1] = target;
    }

    @Override
    protected void moveSegmentTowards(int index, Vec3 target, boolean far) {
        entities[index] = (target);
    }

    public void applyIK() {
        if (entities == null || entities.length == 0) return;

        Vec3 basePos = getBodyOffset();
        Vec3 defaultTipPos =  getLegBasePos();

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
            moveSegmentTowards(i, solvedPos, false);
        }

        moveSegmentTowards(0, basePos, false);

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
            moveSegmentTowards(i, solvedPos, false);
        }
    }
}
