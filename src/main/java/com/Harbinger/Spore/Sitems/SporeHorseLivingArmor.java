package com.Harbinger.Spore.Sitems;

import net.minecraft.resources.ResourceLocation;

public class SporeHorseLivingArmor extends SporeHorseArmor implements CustomModelArmorData{
    private static final ResourceLocation LOCATION = ResourceLocation.parse("spore:textures/armor/living_horse_set.png");
    @Override
    public ResourceLocation getTextureLocation() {
        return LOCATION;
    }
}
