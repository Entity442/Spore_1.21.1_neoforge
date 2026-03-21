package com.Harbinger.Spore.Sitems.Guns;

import com.Harbinger.Spore.Client.AnimationTrackers.MistMakerSawAnimationTracker;
import com.Harbinger.Spore.Client.AnimationTrackers.MistMakerShootAnimationTracker;
import com.Harbinger.Spore.Sentities.Projectile.GunProjectiles.GoreBullet;
import com.Harbinger.Spore.Sitems.CustomModelArmorData;
import com.Harbinger.Spore.core.SConfig;
import com.Harbinger.Spore.core.Sentities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class MistMaker extends AbstractSporeGun implements CustomModelArmorData {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("spore:textures/item/mistmaker.png");
    public MistMaker() {
        super(SConfig.SERVER.pci_durability.get());
    }

    @Override
    public boolean needsToReload() {
        return false;
    }

    @Override
    public int getDefaultTimeBeforeReload() {
        return 0;
    }

    @Override
    public int getTimeBeforeChangingClip() {
        return 20;
    }

    @Override
    public int timeBeforeStomachContentsConvertIntoAmmo() {
        return 0;
    }

    @Override
    public int getClipSize() {
        return 60;
    }

    @Override
    public int getAmmoUsage() {
        return 4;
    }

    @Override
    public int getBaseAmmoShotRequirement() {
        return 4;
    }

    @Override
    public Item getAmmoItem() {
        return null;
    }

    @Override
    public void clientShoot(Player player, InteractionHand interactionHand) {
        MistMakerShootAnimationTracker.trigger(player);
    }

    @Override
    public void serverShoot(ItemStack stack, ServerPlayer player, InteractionHand interactionHand, Vec3 vec3) {
        for (int i = 0;i<4;i++){
            GoreBullet bullet = new GoreBullet(Sentities.GORE_BULLET.get(),player.level());
            bullet.moveTo(player.getX()+vec3.x, player.getY()+1.25D ,player.getZ()+vec3.z);
            bullet.shootFrom(player,1.5f,6);
            player.level().addFreshEntity(bullet);
        }
    }

    @Override
    public void triggerReloadAnimation(Player player) {
        super.triggerReloadAnimation(player);
        MistMakerSawAnimationTracker.trigger(player);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return TEXTURE;
    }
}
