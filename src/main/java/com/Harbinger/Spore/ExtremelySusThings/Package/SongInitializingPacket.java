package com.Harbinger.Spore.ExtremelySusThings.Package;

import com.Harbinger.Spore.Client.MusicManager.SporeMusicPlayer;
import com.Harbinger.Spore.Spore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SongInitializingPacket(int id , boolean val,boolean pro) implements CustomPacketPayload {
    public static final Type<SongInitializingPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "sync_song"));
    public static final StreamCodec<FriendlyByteBuf, SongInitializingPacket> STREAM_CODEC = StreamCodec.of(
            SongInitializingPacket::encode,
            SongInitializingPacket::new
    );
    public SongInitializingPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(),buffer.readBoolean(),buffer.readBoolean());
    }
    public static void encode(FriendlyByteBuf buffer, SongInitializingPacket packet) {
        buffer.writeInt(packet.id());
        buffer.writeBoolean(packet.val());
        buffer.writeBoolean(packet.pro());
    }
    public static void handle(SongInitializingPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            SporeMusicPlayer.handlePacket(message.pro(), message.id,message.val);
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