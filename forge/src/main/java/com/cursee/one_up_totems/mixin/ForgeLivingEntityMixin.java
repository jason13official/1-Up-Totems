package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.OUTConfig;
import com.cursee.one_up_totems.OneUpTotems;
import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
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

      Level level = self.level();
      float mod = OneUpTotems.convertRangeFloat((float) saver.getLives() + 1, 0.0f, (float) OUTConfig.maxAdditionalLives, 0.0f, 0.5f);
      level.playSound(null, self.blockPosition(), SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.PLAYERS, 1.0f - mod, 1.0f - mod);

      cir.setReturnValue(true);
    }
  }
}
