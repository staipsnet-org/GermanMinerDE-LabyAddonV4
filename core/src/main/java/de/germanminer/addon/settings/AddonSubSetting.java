package de.germanminer.addon.settings;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class GermanMinerSubSetting extends Config {

  @SwitchSetting
  private ConfigProperty<Boolean> jailEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private ConfigProperty<Boolean> vitalEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private ConfigProperty<Boolean> voteEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private ConfigProperty<Boolean> zoneEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private ConfigProperty<Boolean> compassEnabled = new ConfigProperty<>(true);

  public boolean isJailEnabled() {
    return jailEnabled.get();
  }

  public boolean isVitalEnabled() {
    return vitalEnabled.get();
  }

  public boolean isVoteEnabled() {
    return voteEnabled.get();
  }

  public boolean isZoneEnabled() {
    return zoneEnabled.get();
  }

  public boolean isCompassEnabled() {
    return compassEnabled.get();
  }
}