package de.germanminer.addon.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ShowSettingInParent;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class PlayTimeConfig extends Config {

  @SwitchSetting
  @ShowSettingInParent
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> daily = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> weekly = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> total = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> duty = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> payday = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> daily() {
    return this.daily;
  }

  public ConfigProperty<Boolean> weekly() {
    return this.weekly;
  }

  public ConfigProperty<Boolean> total() {
    return this.total;
  }

  public ConfigProperty<Boolean> duty() {
    return this.duty;
  }

  public ConfigProperty<Boolean> payday() {
    return this.payday;
  }

}
