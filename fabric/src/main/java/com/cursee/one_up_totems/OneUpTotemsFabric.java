package com.cursee.one_up_totems;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import com.cursee.one_up_totems.impl.common.network.FabricModNetwork;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class OneUpTotemsFabric implements ModInitializer {

  @Override
  public void onInitialize() {

    OneUpTotems.init();

    // sync server lives to client
    ServerEntityEvents.ENTITY_LOAD.register((entity, serverLevel) -> {
      if (entity instanceof ServerPlayer serverPlayer) {

        IEntityDataSaver saver = (IEntityDataSaver) serverPlayer;

        if (!serverPlayer.getTags().contains(Constants.MOD_ID)) {

          saver.addLives(OUTConfig.startingLives);

          serverPlayer.addTag(Constants.MOD_ID);
        }

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(saver.getLives());

        ServerPlayNetworking.send(serverPlayer, FabricModNetwork.LIFE_COUNT_SYNC, buf);
      }
    });
  }
}
