package com.Harbinger.Spore.Sitems;

import net.minecraft.resources.ResourceLocation;

public class SporeHorsePlatedArmor extends SporeHorseArmor implements CustomModelArmorData{
    private static final ResourceLocation LOCATION = ResourceLocation.parse("spore:textures/armor/plated_horse_set.png");
    @Override
    public ResourceLocation getTextureLocation() {
        return LOCATION;
    }
}
