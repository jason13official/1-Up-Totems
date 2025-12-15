package com.cursee.one_up_totems;

import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.RenderGuiEvent;

public class OneUpTotemsClientForge {

  public OneUpTotemsClientForge() {

    OneUpTotemsClient.init();

    OneUpTotemsForge.eventBus.addListener((Consumer<RenderGuiEvent.Pre>) event -> {
      GuiGraphics guiGraphics = event.getGuiGraphics();
      float partialTick = event.getPartialTick();
      LocalPlayer player = Minecraft.getInstance().player;
      OneUpTotemsClient.renderHUDOverlay(guiGraphics, partialTick, player, Minecraft.getInstance().options.hideGui);
    });
  }
}
