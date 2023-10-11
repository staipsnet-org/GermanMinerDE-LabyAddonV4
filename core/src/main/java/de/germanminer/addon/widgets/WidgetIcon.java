package de.germanminer.addon.widgets;

import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;

public enum WidgetIcon {

  BALANCE_CASH("euro.png"),
  BALANCE_BANK("bankcard_white.png"),
  BALANCE_BANK_COMPANY("company.png"),
  BALANCE_BANK_EXTRA("extra.png"),

  PLAYER_LEVEL("level.png"),
  PLAYER_LEVEL_POINTS("level.png"),

  PLAYTIME_DAILY("ontime.png"),
  PLAYTIME_WEEKLY("ontime.png"),
  PLAYTIME_TOTAL("ontime.png"),
  PLAYTIME_DUTY("ontime.png"),
  PLAYTIME_PAYDAY("payday.png"),

  VEHICLE_DISPLAY("vehicle.png"),

  MISCELLANEOUS_CALENDAR("calender.png"),
  MISCELLANEOUS_COMPASS("compass.png"),
  MISCELLANEOUS_POWER_UP("powerup.png"),
  MISCELLANEOUS_ZONE("location.png"),
  MISCELLANEOUS_VOTE("vote.png");

  private final Icon icon;

  WidgetIcon(final String name) {
    this.icon = loadIcon(name);
  }

  public Icon getIcon() {
    return this.icon;
  }

  private static Icon loadIcon(final String name) {
    return Icon.texture(ResourceLocation.create(
        "germanminer",
        String.format("textures/widget/%s", name)
    )).resolution(64, 64);
  }

}
