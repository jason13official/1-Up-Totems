package com.cursee.one_up_totems.api.common.util;

import net.minecraft.nbt.CompoundTag;

public interface IEntityDataSaver {

  CompoundTag one_up_totems$getPersistentData();

  void one_up_totems$setPersistentData(CompoundTag compoundTag);

  default int getLives() {
    return one_up_totems$getPersistentData().getInt("lives");
  }

  default void decrement() {
    var compound = this.one_up_totems$getPersistentData().copy();

    var lives = compound.getInt("lives");

    lives -= 1;

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }

  default void increment() {
    var compound = this.one_up_totems$getPersistentData().copy();

    var lives = compound.getInt("lives");

    lives += 1;

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }
}
