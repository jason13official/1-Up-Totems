package com.cursee.one_up_totems;

import eu.midnightdust.lib.config.MidnightConfig;

public class OUTConfig extends MidnightConfig {

  public static final String CLIENT = "CLIENT";
  public static final String COMMON = "COMMON";
  public static final String SERVER = "SERVER";

  @Comment(name = "Modifies the highest amount of additional lives a player can have at one time.")
  @Entry(category = SERVER, name = "Maximum Extra Lives", min = 0) public static int maxAdditionalLives = 8;

  @Comment(name = "Modifies the amount of additional lives a new player starts with.")
  @Entry(category = SERVER, name = "Starting Extra Lives", min = 0) public static int startingLives = 3;
}
