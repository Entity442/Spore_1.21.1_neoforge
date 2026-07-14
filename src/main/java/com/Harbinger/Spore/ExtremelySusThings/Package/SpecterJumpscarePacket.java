package com.Harbinger.Spore.ExtremelySusThings.Package;

import com.Harbinger.Spore.Client.ClientModEvents;
import com.Harbinger.Spore.Client.MusicManager.SporeMusicPlayer;
import com.Harbinger.Spore.Spore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SpecterJumpscarePacket() implements CustomPacketPayload {
    public static final Type<SpecterJumpscarePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "specter_jumpscare"));
    public static final StreamCodec<FriendlyByteBuf, SpecterJumpscarePacket> STREAM_CODEC = StreamCodec.of(
            SpecterJumpscarePacket::encode,
            SpecterJumpscarePacket::new
    );
    public SpecterJumpscarePacket(FriendlyByteBuf buffer) {
        this();
    }
    public static void encode(FriendlyByteBuf buffer, SpecterJumpscarePacket packet) {
    }
    public static void handle(SpecterJumpscarePacket message, IPayloadContext context) {
        context.enqueueWork(ClientModEvents::setSpecterTicks).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}