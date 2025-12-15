package com.cursee.one_up_totems;

import com.cursee.one_up_totems.impl.common.network.FabricModNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class OneUpTotemsClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    OneUpTotemsClient.init();

    FabricModNetwork.registerS2CReceivers();

    HudRenderCallback.EVENT.register((guiGraphics, partialTick) -> {
      LocalPlayer player = Minecraft.getInstance().player;
      OneUpTotemsClient.renderHUDOverlay(guiGraphics, partialTick, player, Minecraft.getInstance().options.hideGui);
    });
  }
}
