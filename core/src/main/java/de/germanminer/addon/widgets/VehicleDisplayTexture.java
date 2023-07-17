package de.germanminer.addon.widgets;

import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;

public enum VehicleDisplayTexture {

  SPEEDOMETER_BACKGROUND("speedometer-background-black.png", "speedometer-background-black-night.png"),
  WARNING_YELLOW("warning-yellow.png"),
  WARNING_RED("warning-red.png"),
  SPEED_LIMITER_ACTIVE("speed-limiter-green.png"),
  SPEED_LIMITER_READY("speed-limiter-yellow.png"),
  FUEL_OVERLAY("fuel-overlay.png", "fuel-overlay-night.png");

  private final Icon icon;
  private final Icon iconNight;

  VehicleDisplayTexture(final String name, final String nameNight) {
    this.icon = loadIcon(name);
    this.iconNight = loadIcon(nameNight);
  }

  VehicleDisplayTexture(final String name) {
    this.icon = loadIcon(name);
    this.iconNight = null;
  }

  public Icon getIcon(final boolean nightMode) {
    return nightMode && this.iconNight != null ? this.iconNight : this.icon;
  }

  private static Icon loadIcon(final String name) {
    return Icon.texture(ResourceLocation.create(
        "germanminer",
        String.format("textures/vehicle-display/%s", name)
    )).resolution(128, 128);
  }

}
