package com.cursee.one_up_totems;

import eu.midnightdust.lib.config.MidnightConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneUpTotems {

  public static final Logger LOG = LoggerFactory.getLogger(Constants.MOD_NAME);

  public static void init() {
    MidnightConfig.init(Constants.MOD_ID, OUTConfig.class);
  }
}