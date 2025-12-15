package com.cursee.one_up_totems.api.common.util;

import net.minecraft.nbt.CompoundTag;

public interface IEntityDataSaver {

  CompoundTag one_up_totems$getPersistentData();

  void one_up_totems$setPersistentData(CompoundTag compoundTag);
}
