package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.Client.AnimationTrackers.BileBlasterReloadAnimationTracker;
import com.Harbinger.Spore.Client.AnimationTrackers.BileBlasterShootAnimationTracker;
import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import com.Harbinger.Spore.Sentities.Projectile.GunProjectiles.BileBullet;
import com.Harbinger.Spore.Sitems.CustomModelArmorData;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sentities;
import com.Harbinger.Spore.core.Sitems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class BileBlaster extends AbstractSporeGun implements CustomModelArmorData {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("spore:textures/item/bile_blaster.png");
    public BileBlaster() {
        super(SConfig.SERVER.pci_durability.get());
    }

    @Override
    public boolean needsToReload() {
        return true;
    }

    @Override
    public int getDefaultTimeBeforeReload() {
        return 40;
    }

    @Override
    public int getTimeBeforeChangingClip() {
        return 10;
    }

    @Override
    public int timeBeforeStomachContentsConvertIntoAmmo() {
        return 80;
    }

    @Override
    public int getClipSize() {
        return 12;
    }

    @Override
    public Item getAmmoItem() {
        return Sitems.CRUSTED_BILE.asItem();
    }

    @Override
    public void clientShoot(Player player, InteractionHand interactionHand) {
        BileBlasterShootAnimationTracker.trigger(player);
    }

    @Override
    public void serverShoot(ItemStack stack, ServerPlayer player, InteractionHand interactionHand, Vec3 vec3) {
        BileBullet bullet = new BileBullet(Sentities.BILE_BULLET.get(),player.level());
        bullet.moveTo(player.getX()+vec3.x, player.getY()+1.25D ,player.getZ()+vec3.z);
        bullet.shootFrom(player,2.5f,2);
        player.level().addFreshEntity(bullet);
    }

    @Override
    public void triggerReloadAnimation(Player player) {
        super.triggerReloadAnimation(player);
        BileBlasterReloadAnimationTracker.trigger(player);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return TEXTURE;
    }
}
