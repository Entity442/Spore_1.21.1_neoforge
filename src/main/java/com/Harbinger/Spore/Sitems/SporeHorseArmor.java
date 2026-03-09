package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Sitems.BaseWeapons.SporeArmorData;
import com.Harbinger.Spore.core.Sitems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public abstract class SporeHorseArmor extends AnimalArmorItem implements SporeArmorData {
    public SporeHorseArmor() {
        super(ArmorMaterials.LEATHER, BodyType.EQUESTRIAN, false, new Properties().stacksTo(1).durability(200));
        Sitems.TINTABLE_ITEMS.add(this);
        Sitems.BIOLOGICAL_ITEMS.add(this);
    }


    @Override
    public ResourceLocation getTexture() {
        return ResourceLocation.parse("spore:textures/entity/empty.png");
    }
}
