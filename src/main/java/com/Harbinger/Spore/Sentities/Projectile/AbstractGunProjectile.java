package com.Harbinger.Spore.Sentities.Projectile;

import com.Harbinger.Spore.Sentities.BaseEntities.CalamityMultipart;
import com.Harbinger.Spore.core.Sitems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public abstract class AbstractGunProjectile extends AbstractArrow{
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(AbstractGunProjectile.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TRAVEL = SynchedEntityData.defineId(AbstractGunProjectile.class, EntityDataSerializers.FLOAT);
    protected AbstractGunProjectile(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return Sitems.ACID_BALL.toStack();
    }

    public Float getDamage(){return entityData.get(DAMAGE);}
    public void setDamage(Float value){entityData.set(DAMAGE,value);}
    public Float getTravel(){return entityData.get(TRAVEL);}
    public void setTravel(Float value){entityData.set(TRAVEL,value);}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DAMAGE, getConfigDamage());
        builder.define(TRAVEL, 0f);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setDamage(tag.getFloat("damage"));
        this.setTravel(tag.getFloat("travel"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("damage",this.getDamage());
        tag.putFloat("travel",this.getTravel());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if (target instanceof CalamityMultipart multipart){
            target = multipart.getParent();
        }
        if (target instanceof LivingEntity living && this.getOwner() instanceof LivingEntity owner) {
            float calculations =  living.getMaxHealth() * getProDamage();
            float damage = getDamage();
            if (calculations > damage){
                damage = calculations;
            }
            living.hurt(level().damageSources().mobProjectile(this,owner),damage);
            doHitAfterEffects(living,owner);
            playSound(entityImpactSound());
        }
    }

    public abstract SoundEvent blockImpactSound();
    public abstract SoundEvent entityImpactSound();
    public abstract float getMaxBlockRange();
    public abstract float getProDamage();
    public abstract float getConfigDamage();
    public abstract void doHitAfterEffects(LivingEntity living,LivingEntity owner);
    public abstract ParticleOptions getParticle();
    @Override
    protected void onHitBlock(BlockHitResult result) {
        playSound(blockImpactSound());
        discard();
    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public void shootFrom(LivingEntity shooter, float velocity, float inaccuracy) {
        this.setOwner(shooter);

        float xRot = shooter.getXRot();
        float yRot = shooter.getYRot();

        double x = -Math.sin(Math.toRadians(yRot)) * Math.cos(Math.toRadians(xRot));
        double y = -Math.sin(Math.toRadians(xRot));
        double z = Math.cos(Math.toRadians(yRot)) * Math.cos(Math.toRadians(xRot));

        this.shoot(x, y, z, velocity, inaccuracy);

        this.setDamage(getConfigDamage());
    }
    @Override
    public void tick() {
        super.tick();

        double dx = this.getDeltaMovement().x;
        double dy = this.getDeltaMovement().y;
        double dz = this.getDeltaMovement().z;

        float distanceThisTick = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
        float newTravel = this.getTravel() + distanceThisTick;
        this.setTravel(newTravel);

        if (newTravel >= getMaxBlockRange()) {
            this.discard();
        }
        if (level().isClientSide){
            level().addParticle(getParticle(),this.getX(),this.getY(),this.getZ(),0,-0.01,0);
        }
    }
}
