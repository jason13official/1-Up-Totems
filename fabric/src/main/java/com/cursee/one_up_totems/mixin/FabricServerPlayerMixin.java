package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class FabricServerPlayerMixin {

  @Inject(at = @At("TAIL"), method = "restoreFrom")
  private void one_up_totems(ServerPlayer that, boolean keepEverything, CallbackInfo ci) {

    ServerPlayer self = (ServerPlayer) (Object) this;

    IEntityDataSaver oldSaver = (IEntityDataSaver) that;
    IEntityDataSaver newSaver = (IEntityDataSaver) self;

    newSaver.one_up_totems$setPersistentData(oldSaver.one_up_totems$getPersistentData());
  }
}
