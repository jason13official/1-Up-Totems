package com.cursee.one_up_totems;

import com.cursee.one_up_totems.impl.common.util.CachedSupplier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.FastColor;
import net.minecraft.util.FastColor.ARGB32;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OneUpTotemsClient {

  public static final AtomicInteger LIVES_DISPLAY_INT = new AtomicInteger(0);

  private static final CachedSupplier<ItemStack> TOTEM_DISPLAY_STACK = CachedSupplier.singleton(new ItemStack(Items.TOTEM_OF_UNDYING));

  private static final int[] gradient = createColorGradient();

  public static void init() {



  }

  public static void renderHUDOverlay(GuiGraphics guiGraphics, float partialTick, LocalPlayer localPlayer, boolean hideGui) {

    if (hideGui) {
      return;
    }

    int lives = LIVES_DISPLAY_INT.get();

    guiGraphics.renderItem(TOTEM_DISPLAY_STACK.get(), 0, 0);
    guiGraphics.drawString(Minecraft.getInstance().font, String.valueOf(lives), 16, 4, gradient[lives]);
  }

  private static int[] createColorGradient() {

    int[] colors = new int[256];

    int startColor = 0xFFFF0000; // RED
    int endColor = 0xFF00FF00;   // GREEN

    for (int i = 0; i < 256; i++) {
      float delta = i / 255.0f;
      colors[i] = FastColor.ARGB32.lerp(delta, startColor, endColor);
    }

    return colors;
  }
}
