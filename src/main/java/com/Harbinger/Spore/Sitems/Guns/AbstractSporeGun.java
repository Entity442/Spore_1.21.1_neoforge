package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.Sitems.BaseItem;
import com.Harbinger.Spore.Sitems.GunHeldItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractSporeGun extends BaseItem implements GunHeldItem {
    public AbstractSporeGun(int durability) {
        super(new Properties().stacksTo(1).durability(durability));
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return true;
    }
}
