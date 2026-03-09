package com.Harbinger.Spore.Sitems;

import net.minecraft.resources.ResourceLocation;

public class SporeHorseFleshArmor extends SporeHorseArmor implements CustomModelArmorData{
    private static final ResourceLocation LOCATION = ResourceLocation.parse("spore:textures/armor/flesh_horse_set.png");
    @Override
    public ResourceLocation getTextureLocation() {
        return LOCATION;
    }
}
