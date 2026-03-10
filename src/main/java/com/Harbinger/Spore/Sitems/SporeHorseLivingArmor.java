package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.core.Seffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SporeHorseLivingArmor extends SporeHorseArmor implements CustomModelArmorData{
    private static final ResourceLocation LOCATION = ResourceLocation.parse("spore:textures/armor/living_horse_set.png");
    @Override
    public ResourceLocation getTextureLocation() {
        return LOCATION;
    }

    @Override
    public void onAnimalArmorTick(ItemStack stack, Level level, Mob horse) {
        super.onAnimalArmorTick(stack, level, horse);
        if (horse.isVehicle() && horse.horizontalCollision){
            Vec3 currentMovement = horse.getDeltaMovement();

            if (currentMovement.y < 0.15D) {
                Vec3 climbVec = new Vec3(currentMovement.x, 0.15D, currentMovement.z);
                horse.setDeltaMovement(climbVec);
            }
        }
        MobEffectInstance instance = horse.getEffect(Seffects.SYMBIOSIS);
        if (horse.tickCount % 20 == 0){
            if (instance != null && instance.getDuration() < 60){
                horse.addEffect(new MobEffectInstance(Seffects.SYMBIOSIS, 200, 0, (false), (false)));
            }
        }
    }
}
