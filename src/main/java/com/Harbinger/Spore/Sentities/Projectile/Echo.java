package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
    private BlockPos pos;
    private boolean returning = false;
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

        if (!returning) {
            flyForward();
        } else {
            returnToOwner();
        }
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

    private void returnToOwner() {
        Entity owner = getOwner();

        if (owner == null || !owner.isAlive()) {
            discard();
            return;
        }
        Vec3 dir = owner.getEyePosition()
                .subtract(position())
                .normalize();

        setDeltaMovement(dir.scale(SPEED));

        move(MoverType.SELF, getDeltaMovement());

        if (owner instanceof Charger charger && level() instanceof ServerLevel serverLevel){
            if (pos != null){
                charger.setTargetedLocation(serverLevel,pos);
            }
            if (this.distanceTo(owner) < 2){
                discard();
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity != getOwner() && entity instanceof LivingEntity) {
            pos = entity.getOnPos();
            returning = true;
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (Math.random() < 0.2){
            pos = result.getBlockPos();
            returning = true;
        }
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        returning = tag.getBoolean("Returning");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putBoolean("Returning", returning);
    }
}