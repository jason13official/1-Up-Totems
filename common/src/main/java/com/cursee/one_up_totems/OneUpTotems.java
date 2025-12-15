package com.cursee.one_up_totems;

import eu.midnightdust.lib.config.MidnightConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneUpTotems {

  public static final Logger LOG = LoggerFactory.getLogger(Constants.MOD_NAME);

  public static void init() {
    MidnightConfig.init(Constants.MOD_ID, OUTConfig.class);
  }

  public static float convertRangeFloat(float value, float min_orig, float max_orig, float min_new, float max_new) {

    // Calculate the ratio of the value within the original range (0 to 1)
    float ratio = (value - min_orig) / (max_orig - min_orig);

    // Scale and shift the ratio to the new range
    return ratio * (max_new - min_new) + min_new;
  }
}