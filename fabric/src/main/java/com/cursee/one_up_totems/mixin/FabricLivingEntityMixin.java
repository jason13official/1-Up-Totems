package com.cursee.one_up_totems.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class FabricLivingEntityMixin {

  @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection")
  private void one_up_totems$checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {}
}
