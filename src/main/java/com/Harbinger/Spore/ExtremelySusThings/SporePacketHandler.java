package com.Harbinger.Spore.ExtremelySusThings;

import com.Harbinger.Spore.ExtremelySusThings.Package.*;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class SporePacketHandler {

    public static void registerPackets(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("spore");

        // Register server-bound packets (client -> server)
        registrar.playToServer(RequestAdvancementPacket.TYPE, RequestAdvancementPacket.STREAM_CODEC, RequestAdvancementPacket::handle);
        registrar.playToServer(AdvancementGivingPackage.TYPE, AdvancementGivingPackage.STREAM_CODEC, AdvancementGivingPackage::handle);
        registrar.playToServer(OpenSurgeryScreenPacket.TYPE, OpenSurgeryScreenPacket.STREAM_CODEC, OpenSurgeryScreenPacket::handle);
        registrar.playToServer(OpenGraftingScreenPacket.TYPE, OpenGraftingScreenPacket.STREAM_CODEC, OpenGraftingScreenPacket::handle);
        registrar.playToServer(SporeGunFirePacket.TYPE, SporeGunFirePacket.STREAM_CODEC, SporeGunFirePacket::handle);

        // Register client-bound packets (server -> client)
        registrar.playToClient(SyncAdvancementPacket.TYPE, SyncAdvancementPacket.STREAM_CODEC, SyncAdvancementPacket::handle);
        registrar.playToClient(SporeGunFireSyncPacket.TYPE, SporeGunFireSyncPacket.STREAM_CODEC, SporeGunFireSyncPacket::handle);
    }

    public static void sendToServer(net.minecraft.network.protocol.common.custom.CustomPacketPayload packet) {
        ClientPacketListener clientPacketListener = net.minecraft.client.Minecraft.getInstance().getConnection();
        if (clientPacketListener == null){
            return;
        }
        clientPacketListener.send(packet);
    }

    public static void sendToClient(net.minecraft.network.protocol.common.custom.CustomPacketPayload packet, ServerPlayer player) {
        player.connection.send(packet);
    }
}