package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class IkKrakenArm extends IkKrakenLeg {
    protected Vec3 targetPosition;
    protected int targetCooldown = 0;
    public IkKrakenArm(Grakensenker owner, int amount, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance) {
        super(owner, amount, defaultBodyOffset, defaultLimbOffset, maxDistance);
    }

    @Override
    public void refreshLegStandingPoint() {
        sitPosition = getLegBasePos();
    }

    @Override
    public Vec3 getLegBasePos() {
        if (targetCooldown > 0) {
            targetCooldown--;
        } else {
            targetPosition = null;
        }
        return targetPosition == null ? super.getLegBasePos() : targetPosition;
    }

    public void setTarget(Entity target){
        if (target != null && !owner.level().isClientSide && targetCooldown <= 0){
            targetPosition = target.getPosition(owner.tickCount).add(0, (target.getBbHeight() / 2), 0);
            targetCooldown = 40;
        }
    }
}