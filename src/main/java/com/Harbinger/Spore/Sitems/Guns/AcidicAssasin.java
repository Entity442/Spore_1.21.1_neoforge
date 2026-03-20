package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.Client.AnimationTrackers.AssassinShootAnimationTracker;
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

public class AcidicAssasin extends AbstractSporeGun implements CustomModelArmorData {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("spore:textures/item/acidic_assasin.png");
    public AcidicAssasin() {
        super(SConfig.SERVER.pci_durability.get());
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        if (entity.level().isClientSide && entity instanceof Player player && !player.getCooldowns().isOnCooldown(this)) {
            AssassinShootAnimationTracker.trigger(player);
            SporePacketHandler.sendToServer(new ShootBulletProjectilePacket(player.getId(),2,hand == InteractionHand.MAIN_HAND ? -1 : 0));
        }
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.level().isClientSide && !player.getCooldowns().isOnCooldown(this)) {
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return TEXTURE;
    }
}
