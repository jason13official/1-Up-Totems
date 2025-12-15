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

  @Comment(name = "Determines whether to show additional lives counter.")
  @Entry(category = CLIENT, name = "Display Extra Lives") public static boolean display = true;

  @Comment(name = "Modifies the horizontal offset of the display")
  @Entry(category = CLIENT, name = "Extra Lives Horizontal Offset") public static int displayOffsetX = 0;

  @Comment(name = "Modifies the vertical offset of the display.")
  @Entry(category = CLIENT, name = "Extra Lives Vertical Offset") public static int displayOffsetY = 0;

  @Comment(name = "Determines whether to color the text with a gradient (true) or remain white (false)")
  @Entry(category = CLIENT, name = "Red to Green Text Color") public static boolean displayGradient = true;
}
