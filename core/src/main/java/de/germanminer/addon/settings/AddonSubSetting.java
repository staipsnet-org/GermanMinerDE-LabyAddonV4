package de.germanminer.addon.settings;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class AddonSubSetting extends Config {

  @SwitchSetting
  private final ConfigProperty<Boolean> voteEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> zoneEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> compassEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> powerUpEnabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> levelEnabled = new ConfigProperty<>(true);

  public boolean isVoteEnabled() {
    return voteEnabled.get();
  }

  public boolean isZoneEnabled() {
    return zoneEnabled.get();
  }

  public boolean isCompassEnabled() {
    return compassEnabled.get();
  }

  public boolean isPowerUpEnabled() {
    return powerUpEnabled.get();
  }

  public boolean isLevelEnabled() { return levelEnabled.get(); }
}