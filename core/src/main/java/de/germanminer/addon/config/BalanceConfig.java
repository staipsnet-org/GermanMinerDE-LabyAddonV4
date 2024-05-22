package de.germanminer.addon.config;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ShowSettingInParent;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class BalanceConfig extends Config {

  @SwitchSetting
  @ShowSettingInParent
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> company = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> extra = new ConfigProperty<>(true);
  @TextFieldSetting
  private final ConfigProperty<String> firstExtraAccount = new ConfigProperty<>("DEFXXXXXXXX");
  @TextFieldSetting
  private final ConfigProperty<String> secondExtraAccount = new ConfigProperty<>("");
  @TextFieldSetting
  private final ConfigProperty<String> thirdExtraAccount = new ConfigProperty<>("");

  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> company() {
    return this.company;
  }

  public ConfigProperty<Boolean> extra() {
    return this.extra;
  }

  public ConfigProperty<String> firstExtraAccount() {
    return this.firstExtraAccount;
  }

  public ConfigProperty<String> secondExtraAccount() {
    return this.secondExtraAccount;
  }

  public ConfigProperty<String> thirdExtraAccount() {
    return this.thirdExtraAccount;
  }

}
