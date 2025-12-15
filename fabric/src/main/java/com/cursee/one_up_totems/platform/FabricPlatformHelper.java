package com.cursee.one_up_totems.platform;

import com.cursee.one_up_totems.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.RecordItem;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

  @Override
  public RecordItem createRecordItem(int analogOutput, SoundEvent sound, Properties properties, int lengthInSeconds) {
    return new RecordItem(analogOutput, sound, properties, lengthInSeconds);
  }
}
