package com.cursee.one_up_totems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class OneUpTotemsClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    OneUpTotemsClient.init();

    HudRenderCallback.EVENT.register((guiGraphics, partialTick) -> {
      LocalPlayer player = Minecraft.getInstance().player;
      OneUpTotemsClient.renderHUDOverlay(guiGraphics, partialTick, player, Minecraft.getInstance().options.hideGui);
    });
  }
}
