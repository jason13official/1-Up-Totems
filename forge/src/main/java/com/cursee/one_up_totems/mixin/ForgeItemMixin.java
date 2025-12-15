package com.cursee.one_up_totems.mixin;

import com.cursee.one_up_totems.api.common.util.IEntityDataSaver;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ForgeItemMixin {

  @Inject(at = @At("HEAD"), method = "use", cancellable = true)
  private void one_up_totems$use(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {

    ItemStack stack = player.getItemInHand(usedHand);

    if (!stack.is(Items.TOTEM_OF_UNDYING)) {
      return;
    }

    IEntityDataSaver saver = (IEntityDataSaver) player;

    if (saver.getLives() < 255) {
      stack.shrink(1);
      player.setItemInHand(usedHand, stack);
      saver.increment();
      cir.setReturnValue(InteractionResultHolder.success(stack));
    }
  }
}
