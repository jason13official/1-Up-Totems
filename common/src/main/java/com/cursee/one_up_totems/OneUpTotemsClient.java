package com.cursee.one_up_totems;

import com.cursee.one_up_totems.impl.common.util.CachedSupplier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OneUpTotemsClient {

  public static final AtomicInteger LIVES_DISPLAY_INT = new AtomicInteger(9999);

  private static final CachedSupplier<ItemStack> TOTEM_DISPLAY_STACK = CachedSupplier.singleton(new ItemStack(Items.TOTEM_OF_UNDYING));

  public static void init() {
  }

  public static void renderHUDOverlay(GuiGraphics guiGraphics, float partialTick, LocalPlayer localPlayer, boolean hideGui) {

    if (hideGui) {
      return;
    }

    guiGraphics.renderItem(TOTEM_DISPLAY_STACK.get(), 0, 0);
    guiGraphics.drawString(Minecraft.getInstance().font, "3", 16, 4, 0xFFFF0000);
  }
}
