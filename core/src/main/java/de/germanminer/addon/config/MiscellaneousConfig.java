package de.germanminer.addon.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class MiscellaneousConfig extends Config {

  @SwitchSetting
  private final ConfigProperty<Boolean> level = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> compass = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> powerUp = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> vote = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> zone = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> level() {
    return this.level;
  }

  public ConfigProperty<Boolean> compass() {
    return this.compass;
  }

  public ConfigProperty<Boolean> powerUp() {
    return this.powerUp;
  }

  public ConfigProperty<Boolean> vote() {
    return this.vote;
  }

  public ConfigProperty<Boolean> zone() {
    return this.zone;
  }

}
