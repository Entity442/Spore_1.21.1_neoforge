package com.Harbinger.Spore.Sentities.Projectile.GunProjectiles;

import com.Harbinger.Spore.Sentities.ArmorPersentageBypass;
import com.Harbinger.Spore.Sentities.Projectile.AbstractGunProjectile;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Seffects;
import com.Harbinger.Spore.core.Sparticles;
import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class AssassinBullet extends AbstractGunProjectile implements ArmorPersentageBypass {
    public AssassinBullet(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public SoundEvent blockImpactSound() {
        return Ssounds.ASSASSIN_BULLET_BLOCK.value();
    }

    @Override
    public SoundEvent entityImpactSound() {
        return Ssounds.ASSASSIN_BULLET_ENTITY.value();
    }

    @Override
    public float getMaxBlockRange() {
        return 200;
    }

    @Override
    public float getProDamage() {
        return 0.2f;
    }

    @Override
    public float getConfigDamage() {
        return SConfig.SERVER.acidic_assassin_damage.get();
    }

    @Override
    public void doHitAfterEffects(LivingEntity living, LivingEntity owner) {
        living.addEffect(new MobEffectInstance(Seffects.CORROSION,200,1));
    }

    @Override
    public ParticleOptions getParticle() {
        return Sparticles.ACID_BULLET.get();
    }

    @Override
    public float amountOfDamage(float value) {
        return value * 0.5f;
    }
}
