package com.cursee.one_up_totems;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import com.cursee.one_up_totems.impl.common.network.ForgeModNetwork;
import io.netty.buffer.Unpooled;
import java.util.function.Consumer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.Event;
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

    eventBus.addListener((Consumer<EntityJoinLevelEvent>) event -> {
      if (event.getLevel() instanceof ServerLevel && event.getEntity() instanceof ServerPlayer serverPlayer) {

        IEntityDataSaver saver = (IEntityDataSaver) serverPlayer;

        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        buffer.writeVarInt(saver.getLives());

        ForgeModNetwork.sendToPlayer(serverPlayer, new ForgeModNetwork.ForgeLifeCountS2CPacket(buffer));
      }
    });

    ForgeModNetwork.register();

    if (FMLLoader.getDist() == Dist.CLIENT) {
      new OneUpTotemsClientForge();
    }
  }

  public OneUpTotemsForge() {
    this(FMLJavaModLoadingContext.get());
  }
}