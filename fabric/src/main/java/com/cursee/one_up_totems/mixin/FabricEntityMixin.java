package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class FabricEntityMixin implements IEntityDataSaver {

  @Unique
  private static final String one_up_totems$dataId = "one_up_totems.data";

  @Unique
  private CompoundTag one_up_totems$persistentData;

  @Unique
  public CompoundTag one_up_totems$getPersistentData() {
    if (one_up_totems$persistentData == null) {
      one_up_totems$persistentData = new CompoundTag();
    }
    return one_up_totems$persistentData;
  }

  @Unique
  public void one_up_totems$setPersistentData(CompoundTag compound) {
    this.one_up_totems$persistentData = compound;
  }

  @Inject(at = @At("TAIL"), method = "saveWithoutId")
  private void one_up_totems$saveWithoutId(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
    if (one_up_totems$persistentData != null) {
      compound.put(one_up_totems$dataId, this.one_up_totems$persistentData);
    }
  }

  @Inject(at = @At("TAIL"), method = "load")
  private void one_up_totems$load(CompoundTag compound, CallbackInfo ci) {
    if (compound.contains(one_up_totems$dataId)) {
      this.one_up_totems$persistentData = compound.getCompound(one_up_totems$dataId);
    }
  }
}
