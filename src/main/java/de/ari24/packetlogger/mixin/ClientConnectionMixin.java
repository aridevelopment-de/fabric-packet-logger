package de.ari24.packetlogger.mixin;

import de.ari24.packetlogger.PacketLogger;
import de.ari24.packetlogger.config.PacketLoggerConfigModel;
import de.ari24.packetlogger.packets.PacketHandler;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Shadow @Final private NetworkSide side;

    @Inject(method="channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V", at=@At(value="INVOKE", target="Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;)V"))
    private void PacketLogger$onPacketRead(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
        if (PacketLogger.CONFIG.logState() == PacketLoggerConfigModel.LogState.LOGGING && side == NetworkSide.CLIENTBOUND) {
            PacketHandler.handlePacket(packet, NetworkSide.CLIENTBOUND);
        }
    }
}
