package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.OUTConfig;
import com.cursee.one_up_totems.OneUpTotems;
import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import com.cursee.one_up_totems.impl.common.network.FabricModNetwork;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class FabricLivingEntityMixin {

  @Inject(at = @At("HEAD"), method = "checkTotemDeathProtection", cancellable = true)
  private void one_up_totems$checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {

    LivingEntity self = (LivingEntity) (Object) this;

    IEntityDataSaver saver = (IEntityDataSaver) self;

    ItemStack itemStack = null;
    for(InteractionHand interactionHand : InteractionHand.values()) {
      ItemStack itemStack2 = self.getItemInHand(interactionHand);
      if (itemStack2.is(Items.TOTEM_OF_UNDYING)) {
        itemStack = itemStack2.copy();
        break;
      }
    }

    // if player has enough lives and not holding a totem of undying
    if (saver.getLives() >= 1 && itemStack == null) {

      saver.decrement();

      Level level = self.level();
      float mod = OneUpTotems.convertRangeFloat((float) saver.getLives() + 1, 0.0f, (float) OUTConfig.maxAdditionalLives, 0.0f, 0.5f);
      level.playSound(null, self.blockPosition(), SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.PLAYERS, 1.0f - mod, 1.0f - mod);

      if (self instanceof ServerPlayer) {
        ServerPlayer serverPlayer = (ServerPlayer)self;
        serverPlayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING));
        CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, new ItemStack(Items.TOTEM_OF_UNDYING));

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(saver.getLives());

        ServerPlayNetworking.send(serverPlayer, FabricModNetwork.LIFE_COUNT_SYNC, buf);
      }

      self.setHealth(1.0F);
      self.removeAllEffects();
      self.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
      self.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
      self.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
      self.level().broadcastEntityEvent(self, EntityEvent.TALISMAN_ACTIVATE);

      cir.setReturnValue(true);
    }
  }
}
