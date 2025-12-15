package com.cursee.one_up_totems.api.common.util;

import com.cursee.one_up_totems.OUTConfig;
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

    if (lives < 0) {
      lives = 0;
    }

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }

  default void removeLives(int amount) {
    var compound = this.one_up_totems$getPersistentData().copy();

    var lives = compound.getInt("lives");

    lives -= amount;

    if (lives > OUTConfig.maxAdditionalLives) {
      lives = OUTConfig.maxAdditionalLives;
    }

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }

  default void increment() {
    var compound = this.one_up_totems$getPersistentData().copy();

    var lives = compound.getInt("lives");

    lives += 1;

    if (lives > OUTConfig.maxAdditionalLives) {
      lives = OUTConfig.maxAdditionalLives;
    }

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }

  default void addLives(int amount) {
    var compound = this.one_up_totems$getPersistentData().copy();

    var lives = compound.getInt("lives");

    lives += amount;

    if (lives > OUTConfig.maxAdditionalLives) {
      lives = OUTConfig.maxAdditionalLives;
    }

    compound.putInt("lives", lives);

    this.one_up_totems$setPersistentData(compound.copy());
  }
}
