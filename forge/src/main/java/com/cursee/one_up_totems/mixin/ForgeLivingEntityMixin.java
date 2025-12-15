package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ForgeLivingEntityMixin {

  @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
  private void one_up_totems$checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {

    LivingEntity self = (LivingEntity) (Object) this;

    IEntityDataSaver saver = (IEntityDataSaver) self;

    if (saver.getLives() >= 1) {

      saver.decrement();

      cir.setReturnValue(true);
    }
  }
}
