package com.cursee.one_up_totems.impl.common.network;

import com.cursee.one_up_totems.Constants;
import com.cursee.one_up_totems.OneUpTotemsClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class FabricModNetwork {

  public static final ResourceLocation LIFE_COUNT_SYNC = new ResourceLocation(Constants.MOD_ID, "life_count_sync");

  public static void registerS2CReceivers() {
    ClientPlayNetworking.registerGlobalReceiver(LIFE_COUNT_SYNC, FabricLifeCountS2CPacket::handle);
  }

  public static class FabricLifeCountS2CPacket {

    public static void handle(Minecraft minecraft, ClientPacketListener clientPacketListener, FriendlyByteBuf friendlyByteBuf, PacketSender packetSender) {
      Minecraft.getInstance().execute(() -> {
        int lives = friendlyByteBuf.readVarInt();
        OneUpTotemsClient.LIVES_DISPLAY_INT.set(lives);
      });
    }
  }
}
