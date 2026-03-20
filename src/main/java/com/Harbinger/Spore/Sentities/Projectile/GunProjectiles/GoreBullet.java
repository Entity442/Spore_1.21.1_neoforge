package com.Harbinger.Spore.Sentities.Projectile.GunProjectiles;

import com.Harbinger.Spore.Sentities.Projectile.AbstractGunProjectile;
import com.Harbinger.Spore.core.Sparticles;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class GoreBullet extends AbstractGunProjectile {
    public GoreBullet(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public SoundEvent blockImpactSound() {
        return Ssounds.INFECTED_WEAPON_THROW.value();
    }

    @Override
    public SoundEvent entityImpactSound() {
        return Ssounds.INFECTED_WEAPON_THROW.value();
    }

    @Override
    public float getMaxBlockRange() {
        return 8;
    }

    @Override
    public float getProDamage() {
        return 0.05f;
    }

    @Override
    public float getConfigDamage() {
        return 5;
    }

    @Override
    public void doHitAfterEffects(LivingEntity living, LivingEntity owner) {
        living.hurtTime = 0;
        living.invulnerableTime = 0;
    }

    @Override
    public ParticleOptions getParticle() {
        return Sparticles.GORE_BULLET.get();
    }
}
