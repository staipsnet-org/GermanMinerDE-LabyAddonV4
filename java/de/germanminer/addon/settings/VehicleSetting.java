package de.germanminer.addon.settings;

import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class VehicleSetting extends Config {

  @KeyBindSetting
  private final ConfigProperty<Key> vehicleEngineHotkey = new ConfigProperty<>(Key.M);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleSirenHotkey = new ConfigProperty<>(Key.H);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleLimiterSwitchHotkey = new ConfigProperty<>(Key.NONE);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleLimiterSetHotkey = new ConfigProperty<>(Key.NONE);

  public ConfigProperty<Key> getVehicleEngineHotkey() {
    return vehicleEngineHotkey;
  }

  public ConfigProperty<Key> getVehicleSirenHotkey() {
    return vehicleSirenHotkey;
  }

  public ConfigProperty<Key> getVehicleLimiterSwitchHotkey() {
    return vehicleLimiterSwitchHotkey;
  }

  public ConfigProperty<Key> getVehicleLimiterSetHotkey() {
    return vehicleLimiterSetHotkey;
  }

}