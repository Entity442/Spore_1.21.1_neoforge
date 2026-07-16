package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Sentities.EvolvedInfected.Charger;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class Echo extends Projectile {
    private static final double SPEED = 0.25;
    public static final int LIFE = 120;
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
        if (life > LIFE){
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
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.setPos(d0, d1, d2);

        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
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