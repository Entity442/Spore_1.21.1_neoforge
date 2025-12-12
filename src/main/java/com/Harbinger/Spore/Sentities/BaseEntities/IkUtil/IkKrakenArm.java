package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class IkKrakenArm extends IkKrakenLeg {
    private boolean isTargeting = false;

    public IkKrakenArm(Grakensenker owner, int amount, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance) {
        super(owner, amount, defaultBodyOffset, defaultLimbOffset, maxDistance);
    }

    @Override
    public void refreshLegStandingPoint() {
        LivingEntity target = owner.getTarget();

        if (target != null && owner.distanceTo(target) < maxDistance && target.isAlive()) {
            Vec3 targetPos = target.getBoundingBox().getCenter();
            if (owner.level().random.nextFloat() < 0.1f) {
                float offsetX = (owner.level().random.nextFloat() - 0.5f) * 0.3f;
                float offsetY = (owner.level().random.nextFloat() - 0.5f) * 0.3f;
                float offsetZ = (owner.level().random.nextFloat() - 0.5f) * 0.3f;
                targetPos = targetPos.add(offsetX, offsetY, offsetZ);
            }

            sitPosition = targetPos;
            lastSitPosition = sitPosition;
            isTargeting = true;
        } else {
            if (sitPosition == null || !isTargeting) {
                sitPosition = getLegBasePos();
            } else {
                sitPosition = sitPosition.lerp(getLegBasePos(), 0.1f);
            }
            if (sitPosition.distanceToSqr(getLegBasePos()) < 0.1) {
                isTargeting = false;
            }
            lastSitPosition = sitPosition;
        }
    }

    @Override
    public void applyIK() {
        if (entities.length == 0) return;

        Vec3 basePos = getBodyOffset();
        LivingEntity target = owner.getTarget();

        if (target != null && target.isAlive()) {
            sitPosition = target.getBoundingBox().getCenter();
        }

        Vec3 targetPos = sitPosition == null ? getLegBasePos() : sitPosition;
        entities[0] = basePos;

        for (int i = entities.length - 1; i >= 0; i--) {
            if (i == entities.length - 1) {
                Vec3 newTipPos = entities[i].lerp(targetPos, 0.25f);
                entities[i] = newTipPos;
            } else {
                Vec3 nextPos = entities[i + 1];
                Vec3 dir = entities[i].subtract(nextPos).normalize();
                Vec3 newPos = nextPos.add(dir);
                entities[i] = entities[i].lerp(newPos, 0.5f);
            }
        }
        entities[0] = basePos;
        for (int i = 1; i < entities.length; i++) {
            Vec3 prevPos = entities[i - 1];
            Vec3 dir = entities[i].subtract(prevPos).normalize();
            Vec3 newPos = prevPos.add(dir);
            entities[i] = entities[i].lerp(newPos, 0.5f);
        }
        entities[entities.length - 1] = entities[entities.length - 1].lerp(targetPos, 0.3f);
    }

    public boolean isTargeting() {
        return isTargeting;
    }
}