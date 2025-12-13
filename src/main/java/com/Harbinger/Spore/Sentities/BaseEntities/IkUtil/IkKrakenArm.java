package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class IkKrakenArm extends IkKrakenLeg {
    protected final boolean rightArm;
    @Nullable
    protected LivingEntity target;
    private final Vec3 RightVec = new Vec3(4,0,-10);
    private final Vec3 LeftVec = new Vec3(4,0,10);
    public IkKrakenArm(Grakensenker owner, int amount, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance, boolean rightArm) {
        super(owner, amount, defaultBodyOffset, defaultLimbOffset, maxDistance);
        this.rightArm = rightArm;
    }

    @Override
    public void refreshLegStandingPoint() {
        sitPosition = this.target == null ? getLegBasePos() : this.target.position().add(0, this.target.getBbHeight() * 0.5, 0);
        lastSitPosition = sitPosition;
        if (owner.tickCount % 10 == 0){
            setTarget();
        }
    }
    protected void moveTipTowards(int index, Vec3 target) {
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.1f);
        entities[index] = newPos;
    }

    public void setTarget(){
        Optional<LivingEntity> targetOp = findAndSetTarget();
        if (targetOp.isPresent()){
            this.target = targetOp.get();
        }else {
            target = null;
        }
    }

    public Optional<LivingEntity> findAndSetTarget() {
        Level level = owner.level();
        Vec3 pivot = applyYaw(rightArm ? RightVec : LeftVec);
        AABB searchBox = new AABB(
                owner.getX() - 10, owner.getY(),  owner.getZ() - 10,
                owner.getX() + 10, owner.getY() + owner.getExtendedHeight() + 4,  owner.getZ() + 10
        ).move(pivot);
        return level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                e -> e.isAlive() && e != owner && Utilities.TARGET_SELECTOR.Test(e)
        ).stream().findFirst();
    }

}