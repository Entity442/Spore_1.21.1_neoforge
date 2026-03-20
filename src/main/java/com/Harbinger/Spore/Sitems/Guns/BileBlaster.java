package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.Client.AnimationTrackers.BileBlasterReloadAnimationTracker;
import com.Harbinger.Spore.Client.AnimationTrackers.BileBlasterShootAnimationTracker;
import com.Harbinger.Spore.ExtremelySusThings.Package.ShootBulletProjectilePacket;
import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import com.Harbinger.Spore.Sitems.CustomModelArmorData;
import com.Harbinger.Spore.core.SConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BileBlaster extends AbstractSporeGun implements CustomModelArmorData {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("spore:textures/item/bile_blaster.png");
    public BileBlaster() {
        super(SConfig.SERVER.pci_durability.get());
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        if (entity.level().isClientSide && entity instanceof Player player && !player.getCooldowns().isOnCooldown(this)) {
            BileBlasterShootAnimationTracker.trigger(player);
            SporePacketHandler.sendToServer(new ShootBulletProjectilePacket(player.getId(),1,hand == InteractionHand.MAIN_HAND ? -1 : 0));
        }
        return super.onEntitySwing(stack, entity, hand);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.level().isClientSide && !player.getCooldowns().isOnCooldown(this)) {
            BileBlasterReloadAnimationTracker.trigger(player);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return TEXTURE;
    }
}
