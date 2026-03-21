package com.Harbinger.Spore.ExtremelySusThings.Package;

import com.Harbinger.Spore.Sitems.Guns.AbstractSporeGun;
import com.Harbinger.Spore.Spore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SporeGunFireSyncPacket(int playerId, int hand) implements CustomPacketPayload{
    public static final Type<SporeGunFireSyncPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "sync_shoot"));
    public static final StreamCodec<FriendlyByteBuf, SporeGunFireSyncPacket> STREAM_CODEC = StreamCodec.of(
            SporeGunFireSyncPacket::encode,
            SporeGunFireSyncPacket::new
    );
    public SporeGunFireSyncPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(),buffer.readInt());
    }
    public static void encode(FriendlyByteBuf buffer, SporeGunFireSyncPacket packet) {
        buffer.writeInt(packet.playerId);
        buffer.writeInt(packet.hand);
    }
    public static void handle(SporeGunFireSyncPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player sender = context.player();
            Level level = sender.level();
            if (level.isClientSide){
                return;
            }

            Entity entity = level.getEntity(message.playerId);
            if (!(entity instanceof Player player)) return;

            InteractionHand interactionHand = message.hand == 0 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

            ItemStack stack = player.getItemInHand(interactionHand);

            if (stack.getItem() instanceof AbstractSporeGun gun) {
                gun.clientShoot(player, interactionHand);
            }}).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}