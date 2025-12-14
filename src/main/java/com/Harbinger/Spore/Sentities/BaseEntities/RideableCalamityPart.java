package com.Harbinger.Spore.Sentities.BaseEntities;

import com.Harbinger.Spore.ExtremelySusThings.Utilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RideableCalamityPart extends CalamityMultipart {
    public RideableCalamityPart(Calamity parent, String name, float w, float h) {
        super(parent, name, w, h);
    }


    @Override
    public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
    }

    @Override
    public void positionRider(Entity passenger, MoveFunction move) {
        if (!this.hasPassenger(passenger)) return;

        Vec3 base = this.position();

        double yOffset =
                this.getDimensions(Pose.STANDING).height() * 0.5
                        + passenger.getBbHeight() * 0.5;

        Vec3 riderPos = base.add(0, yOffset, 0);

        move.accept(passenger, riderPos.x, riderPos.y, riderPos.z);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) return;
        if (tickCount % 10 == 0){
            Optional<LivingEntity> target = findAndSetTarget();
            if (target.isPresent() && canAddPassenger(target.get())){
                target.get().startRiding(this,true);
            }
        }
    }

    public Optional<LivingEntity> findAndSetTarget() {
        return level().getEntitiesOfClass(
                LivingEntity.class,
                this.getBoundingBox().inflate(2),
                e -> e.isAlive() && e != parentMob && !(e.getVehicle() instanceof CalamityMultipart) && Utilities.TARGET_SELECTOR.Test(e) && TargetingConditions.forCombat().test(parentMob,e)
        ).stream().findFirst();
    }
}
