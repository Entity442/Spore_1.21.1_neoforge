package com.Harbinger.Spore.Sentities.BaseEntities.IkUtil;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.Sentities.BaseEntities.RideableCalamityPart;
import com.Harbinger.Spore.Sentities.Calamities.Grakensenker;
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
    private final Vec3 MouthPosition = new Vec3(2, 2.5, 0);
    protected int hitValues = 0;
    protected final RideableCalamityPart calamityMultipart;
    public IkKrakenArm(Grakensenker owner, RideableCalamityPart multipart, int amount, Vec3 defaultBodyOffset, Vec3 defaultLimbOffset, float maxDistance, boolean rightArm) {
        super(owner, amount, defaultBodyOffset, defaultLimbOffset, maxDistance);
        this.rightArm = rightArm;
        calamityMultipart = multipart;
    }

    @Override
    public void refreshLegStandingPoint() {
        sitPosition = this.target == null ? getLegBasePos() : this.target.position().add(0, this.target.getBbHeight() * 0.5, 0);
        sitPosition = calamityMultipart.isVehicle() ? getMouthPosition() : sitPosition;
        lastSitPosition = sitPosition;
        if (owner.tickCount % 10 == 0 && hitValues <= 0 && !calamityMultipart.isVehicle()){
            setTarget();
        }
    }
    @Override
    protected void moveTipTowards(Vec3 value) {
        int val = entities.length - 1;
        Vec3 currentPos = entities[val];
        Vec3 newPos = currentPos.lerp(value, 0.2f);
        entities[val] = newPos;
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
                owner.getX() - 16, owner.getY(),  owner.getZ() - 16,
                owner.getX() + 16, owner.getY() + owner.getExtendedHeight() + 4,  owner.getZ() + 16
        ).move(pivot);
        return level.getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                e -> e.isAlive() && e != owner && !(e.getVehicle() instanceof CalamityMultipart) && Utilities.TARGET_SELECTOR.Test(e) && TargetingConditions.forCombat().test(owner,e)
        ).stream().findFirst();
    }
    public Vec3 getMidSecPivot(){
        Vec3 pivot = applyYaw(rightArm ? RightMidVec : LeftMidVec);
        return owner.position().add(pivot).add(0, owner.getExtendedHeight(), 0);
    }
    public Vec3 getMouthPosition(){
        Vec3 pivot = applyYaw(MouthPosition);
        return owner.position().add(pivot).add(0, owner.getExtendedHeight(), 0);
    }

    protected void moveMidSegmentTowards(int index, Vec3 target) {
        if (this.target != null){
            return;
        }
        Vec3 currentPos = entities[index];
        Vec3 newPos = currentPos.lerp(target, 0.1f);
        entities[index] = newPos;
    }

    public void armHasBeenHit(){
        hitValues = 60;
        target = null;
    }

    @Override
    public void applyIK() {
        super.applyIK();
        moveMidSegmentTowards(entities.length/4,getMidSecPivot());
        if (hitValues > 0){
            hitValues--;
        }
        float x = (float) entities[entities.length-1].x();
        float y = (float) entities[entities.length-1].y();
        float z = (float) entities[entities.length-1].z();
        if (rightArm){
            owner.setRightArm(new Vector3f(x,y,z));
        }else {
            owner.setLeftArm(new Vector3f(x,y,z));
        }
    }
}