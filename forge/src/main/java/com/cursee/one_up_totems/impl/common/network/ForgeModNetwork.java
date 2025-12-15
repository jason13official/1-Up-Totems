package com.cursee.one_up_totems.impl.common.network;

import com.cursee.one_up_totems.Constants;
import com.cursee.one_up_totems.OneUpTotemsClient;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.PacketTarget;
import net.minecraftforge.network.simple.SimpleChannel;

public class ForgeModNetwork {

  private static SimpleChannel INSTANCE;

  private static int packetId = 0;
  private static int id() {
    return packetId++;
  }

  public static void register() {
    SimpleChannel net = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation(Constants.MOD_ID, "messages"))
        .networkProtocolVersion(() -> "1.0")
        .clientAcceptedVersions(s -> true)
        .serverAcceptedVersions(s -> true)
        .simpleChannel();

    INSTANCE = net;

    net.messageBuilder(ForgeLifeCountS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
        .decoder(ForgeLifeCountS2CPacket::new)
        .encoder(ForgeLifeCountS2CPacket::toBytes)
        .consumerMainThread(ForgeLifeCountS2CPacket::handle)
        .add();
  }

  public static <MSG> void sendToServer(MSG message) {
    INSTANCE.sendToServer(message);
  }
  public static <MSG> void sendToPlayer(ServerPlayer serverPlayer, MSG message) {
    INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
  }

  public static class ForgeLifeCountS2CPacket {

    private final int lives;

    public ForgeLifeCountS2CPacket(FriendlyByteBuf friendlyByteBuf) {
      lives = friendlyByteBuf.readVarInt();
    }

    public void toBytes(FriendlyByteBuf friendlyByteBuf) {
      friendlyByteBuf.writeVarInt(lives);
    }

    public boolean handle(Supplier<Context> contextSupplier) {

      Context context = contextSupplier.get();

      context.enqueueWork(() -> {
        int lives = this.lives;
        OneUpTotemsClient.LIVES_DISPLAY_INT.set(lives);

        context.setPacketHandled(true);
      });

      return true;
    }
  }
}
