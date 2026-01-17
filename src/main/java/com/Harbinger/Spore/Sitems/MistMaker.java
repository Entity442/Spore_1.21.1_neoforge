package com.Harbinger.Spore.Sitems;

import com.Harbinger.Spore.Client.AnimationTrackers.MistMakerSawAnimationTracker;
import com.Harbinger.Spore.Client.AnimationTrackers.MistMakerShootAnimationTracker;
import com.Harbinger.Spore.core.SConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MistMaker extends BaseItem implements CustomModelArmorData{
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("spore:textures/item/mistmaker.png");
    public MistMaker() {
        super(new Properties().stacksTo(1).durability(SConfig.SERVER.pci_durability.get()));
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        if (entity.level().isClientSide && entity instanceof Player player && !player.getCooldowns().isOnCooldown(this)) {
            MistMakerSawAnimationTracker.trigger(player);
        }
        return super.onEntitySwing(stack, entity, hand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.level().isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            MistMakerShootAnimationTracker.trigger(player);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return TEXTURE;
    }
}
