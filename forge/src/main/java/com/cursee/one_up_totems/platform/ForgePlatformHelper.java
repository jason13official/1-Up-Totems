package com.cursee.one_up_totems.platform;

import com.cursee.one_up_totems.platform.services.IPlatformHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

  @Override
  public RecordItem createRecordItem(int analogOutput, SoundEvent sound, Properties properties, int lengthInSeconds) {
    return new RecordItem(analogOutput, () -> sound, properties, lengthInSeconds * 20);
  }
}