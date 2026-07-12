package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Echo extends Projectile {
    private static final double SPEED = 0.25;
    private int life;

    public Echo(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public void shoot(Vec3 direction) {
        setDeltaMovement(direction.normalize().scale(SPEED));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        if (life > 200){
            this.remove(RemovalReason.DISCARDED);
        }else {
            life++;
        }
        if (level().isClientSide  || !isAlive())
            return;

        flyForward();
    }
    public int getLife(){return life;}

    private void flyForward() {
        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            onHit(hit);
            return;
        }
        move(MoverType.SELF, getDeltaMovement());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity != getOwner() && entity instanceof LivingEntity && getOwner() instanceof Charger charger && level() instanceof ServerLevel serverLevel) {
            charger.setTargetedLocation(serverLevel,entity.getOnPos());
        }
        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        discard();
    }

}