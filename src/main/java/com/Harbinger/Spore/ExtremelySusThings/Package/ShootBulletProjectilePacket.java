package com.Harbinger.Spore.ExtremelySusThings.Package;

import com.Harbinger.Spore.Sentities.Projectile.GunProjectiles.AssassinBullet;
import com.Harbinger.Spore.Sentities.Projectile.GunProjectiles.BileBullet;
import com.Harbinger.Spore.Sentities.Projectile.GunProjectiles.GoreBullet;
import com.Harbinger.Spore.Spore;
import com.Harbinger.Spore.core.Sentities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ShootBulletProjectilePacket(int id, int typeW, int hand) implements CustomPacketPayload {

    public static final Type<ShootBulletProjectilePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Spore.MODID, "shoot_assassin"));
    public static final StreamCodec<FriendlyByteBuf, ShootBulletProjectilePacket> STREAM_CODEC = StreamCodec.of(
            ShootBulletProjectilePacket::encode,
            ShootBulletProjectilePacket::new
    );

    public ShootBulletProjectilePacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(),buffer.readInt(),buffer.readInt());
    }

    // Static encode method for StreamCodec
    public static void encode(FriendlyByteBuf buffer, ShootBulletProjectilePacket packet) {
        buffer.writeInt(packet.id);
        buffer.writeInt(packet.typeW);
        buffer.writeInt(packet.hand);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // Handler method
    public static void handle(ShootBulletProjectilePacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Level level = player.level();
            if (level.isClientSide){
                return;
            }
            Entity truePlayer = level.getEntity(message.id);
            if (truePlayer instanceof ServerPlayer playerValue) {
                boolean right = message.hand() == -1;
                Vec3 offset =  right ? new Vec3(-0.2,0,0.3) : new Vec3(-0.2,0,-0.3);
                Vec3 vec3 = (offset).yRot(-playerValue.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
                switch (message.typeW()){
                    case 0:
                        shootGore(playerValue, vec3);
                        break;
                    case 1:
                        shootBile(playerValue, vec3);
                        break;
                    case 2:
                        shootAssassin(playerValue, vec3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static void shootGore(ServerPlayer serverPlayer,Vec3 vec3){
        for (int i = 0;i<4;i++){
            GoreBullet bullet = new GoreBullet(Sentities.GORE_BULLET.get(),serverPlayer.level());
            bullet.moveTo(serverPlayer.getX()+vec3.x, serverPlayer.getY()+1.25D ,serverPlayer.getZ()+vec3.z);
            bullet.shootFrom(serverPlayer,1.5f,6);
            serverPlayer.level().addFreshEntity(bullet);
        }
    }
    public static void shootBile(ServerPlayer serverPlayer,Vec3 vec3){
        BileBullet bullet = new BileBullet(Sentities.BILE_BULLET.get(),serverPlayer.level());
        bullet.moveTo(serverPlayer.getX()+vec3.x, serverPlayer.getY()+1.25D ,serverPlayer.getZ()+vec3.z);
        bullet.shootFrom(serverPlayer,2.5f,2);
        serverPlayer.level().addFreshEntity(bullet);
    }
    public static void shootAssassin(ServerPlayer serverPlayer,Vec3 vec3){
        AssassinBullet bullet = new AssassinBullet(Sentities.ASSASSIN_BULLET.get(),serverPlayer.level());
        bullet.moveTo(serverPlayer.getX()+vec3.x, serverPlayer.getY()+1.25D ,serverPlayer.getZ()+vec3.z);
        bullet.shootFrom(serverPlayer,5,0);
        serverPlayer.level().addFreshEntity(bullet);
    }
}