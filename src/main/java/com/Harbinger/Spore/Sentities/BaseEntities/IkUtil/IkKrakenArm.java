package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class IkKrakenArm extends IkKrakenLeg{
    public IkKrakenArm(Grakensenker owner, Vec3[] entities, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance) {
        super(owner, entities, defaultBodyOffset, defaultLimbOffset, maxDistance);
    }

    @Override
    public void refreshLegStandingPoint() {
        sitPosition = findStableFooting(defaultLimbOffset);
        if (!sitPosition.equals(lastSitPosition)) lastSitPosition = sitPosition;
    }

    @Override
    protected Vec3 findStableFooting(Vec3 tip) {
        LivingEntity target = owner.getTarget();
        return target != null && owner.distanceTo(target) < 32 && target.isAlive() ? target.position() : getLegBasePos();
    }

    @Override
    public Vec3 getLegBasePos() {
        Vec3 pivot = owner.position().add(0, owner.getExtendedHeight(), 0);
        return pivot.add(applyYaw(defaultLimbOffset));
    }
}
