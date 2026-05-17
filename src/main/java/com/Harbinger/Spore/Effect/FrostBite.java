package com.Harbinger.Spore.Effect;

import com.Harbinger.Spore.Sentities.ColdWeakness;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class FrostBite extends MobEffect implements SporeEffectsHandler{
    public FrostBite() {
        super(MobEffectCategory.BENEFICIAL, 8991416);
    }
    public static final TagKey<EntityType<?>> coldWeakness = EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES;

    public boolean isDurationEffectTick(int duration, int intensity) {
        return duration % 80 == 0;
    }

    @Override
    public void triggerEffects(LivingEntity entity, int intense) {
        if (entity.getType().is(coldWeakness)){
            int level = 0;
            double modifier = 0.1;
            if (entity instanceof ColdWeakness coldWeakness){
                level = coldWeakness.getEndurance().getLevel();
                modifier = coldWeakness.getEndurance().getHealthModifier();
            }
            if (intense >= level){
                if (!entity.level().isClientSide ) {
                    double originalMotionX = entity.getDeltaMovement().x;
                    double originalMotionY = entity.getDeltaMovement().y;
                    double originalMotionZ = entity.getDeltaMovement().z;
                    float damage = (float) (entity.getMaxHealth() * modifier + intense);
                    entity.hurt(entity.damageSources().freeze(), damage);
                    entity.setTicksFrozen(entity.getTicksFrozen()+100);
                    entity.setDeltaMovement(originalMotionX, originalMotionY, originalMotionZ);
                }
            }
        }
    }
}