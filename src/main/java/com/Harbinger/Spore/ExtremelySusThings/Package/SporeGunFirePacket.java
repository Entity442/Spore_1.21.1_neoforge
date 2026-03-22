package com.Harbinger.Spore.ExtremelySusThings.Package;


import com.Harbinger.Spore.ExtremelySusThings.SporePacketHandler;
import com.Harbinger.Spore.Sitems.Guns.AbstractSporeGun;
import com.Harbinger.Spore.Spore;
import com.Harbinger.Spore.core.SdataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SporeGunFirePacket(int id,int hand) implements CustomPacketPayload{
    public static final CustomPacketPayload.Type<SporeGunFirePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "gun_processing"));
    public static final StreamCodec<FriendlyByteBuf, SporeGunFirePacket> STREAM_CODEC = StreamCodec.of(
            SporeGunFirePacket::encode,
            SporeGunFirePacket::new
    );
    public SporeGunFirePacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(),buffer.readInt());
    }
    public static void encode(FriendlyByteBuf buffer, SporeGunFirePacket packet) {
        buffer.writeInt(packet.id);
        buffer.writeInt(packet.hand);
    }

    public static void handle(SporeGunFirePacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player sender = context.player();
            Level level = sender.level();
            if (level.isClientSide){
                return;
            }
            Entity truePlayer = level.getEntity(message.id);
            if (truePlayer instanceof ServerPlayer playerValue) {
                InteractionHand interactionHand = message.hand == 0 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
                ItemStack stack = playerValue.getItemInHand(interactionHand);
                Vec3 offset = message.hand == 0 ? new Vec3(-0.2, 0, 0.3) : new Vec3(-0.2, 0, -0.3);
                Vec3 vec3 = (offset).yRot(-playerValue.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));

                if (!(stack.getItem() instanceof AbstractSporeGun gun)) return;

                int shootDelay = stack.getOrDefault(SdataComponents.SHOOT_DELAY.get(), 0);
                int reloadDelay = stack.getOrDefault(SdataComponents.RELOAD_DELAY.get(), 0);
                if (shootDelay > 0 || reloadDelay > 0) return;

                if (gun.needsToReload()) {
                    int clip = stack.getOrDefault(SdataComponents.FLESH_AMMO.get(), 0);
                    if (clip < gun.getBaseAmmoShotRequirement()){
                        gun.playEmptyFireSounds(playerValue);
                        return;
                    }

                    stack.set(SdataComponents.FLESH_AMMO.get(), clip - gun.getAmmoUsage());
                } else {
                    int stomach = stack.getOrDefault(SdataComponents.STOMACH_CONTENTS.get(), 0);
                    if (stomach < gun.getBaseAmmoShotRequirement()) {
                        gun.playEmptyFireSounds(playerValue);
                        return;
                    }

                    stack.set(SdataComponents.STOMACH_CONTENTS.get(), stomach - gun.getAmmoUsage());
                }

                stack.set(SdataComponents.SHOOT_DELAY.get(), gun.getTimeBeforeChangingClip());

                gun.serverShoot(stack, playerValue, interactionHand, vec3);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}