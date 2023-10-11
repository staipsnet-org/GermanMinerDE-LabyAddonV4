package de.germanminer.addon.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class CalenderConfig extends Config {

  @TextFieldSetting
  private final ConfigProperty<String> monday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> tuesday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> wednesday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> thursday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> friday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> saturday = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> sunday = new ConfigProperty<>("");

  public ConfigProperty<String> monday() {
    return this.monday;
  }

  public ConfigProperty<String> tuesday() {
    return this.tuesday;
  }

  public ConfigProperty<String> wednesday() {
    return this.wednesday;
  }

  public ConfigProperty<String> thursday() {
    return this.thursday;
  }

  public ConfigProperty<String> friday() {
    return this.friday;
  }

  public ConfigProperty<String> saturday() {
    return this.saturday;
  }

  public ConfigProperty<String> sunday() {
    return this.sunday;
  }

}
