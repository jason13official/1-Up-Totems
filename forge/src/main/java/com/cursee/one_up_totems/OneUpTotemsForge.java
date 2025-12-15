package com.cursee.one_up_totems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(Constants.MOD_ID)
public class OneUpTotemsForge {

  public static IEventBus eventBus;

  public OneUpTotemsForge(FMLJavaModLoadingContext context) {

    eventBus = context.getModEventBus();

    OneUpTotems.init();

    if (FMLLoader.getDist() == Dist.CLIENT) {
      new OneUpTotemsClientForge();
    }
  }

  public OneUpTotemsForge() {
    this(FMLJavaModLoadingContext.get());
  }
}