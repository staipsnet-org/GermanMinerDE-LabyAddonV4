package de.germanminer.addon;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class GermanMinerConfig extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SettingSection("hotkey")
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleEngineHotkey = new ConfigProperty<>(Key.M);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleSirenHotkey = new ConfigProperty<>(Key.H);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleLimiterSwitchHotkey = new ConfigProperty<>(Key.NONE);
  @KeyBindSetting
  private final ConfigProperty<Key> vehicleLimiterSetHotkey = new ConfigProperty<>(Key.NONE);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Key> vehicleEngineHotkey() {
    return this.vehicleEngineHotkey;
  }

  public ConfigProperty<Key> vehicleSirenHotkey() {
    return this.vehicleSirenHotkey;
  }

  public ConfigProperty<Key> vehicleLimiterSwitchHotkey() {
    return this.vehicleLimiterSwitchHotkey;
  }

  public ConfigProperty<Key> vehicleLimiterSetHotkey() {
    return this.vehicleLimiterSetHotkey;
  }

}
