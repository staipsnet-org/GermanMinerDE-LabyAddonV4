package de.germanminer.addon.settings;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class BankSetting extends Config {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabledExtra = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledCompany = new ConfigProperty<>(true);
  @TextFieldSetting
  public final ConfigProperty<String> bankCustomAccount1 = new ConfigProperty<>("DEFXXXXXXXX");
  @TextFieldSetting
  public final ConfigProperty<String> bankCustomAccount2 = new ConfigProperty<>("DEFXXXXXXXX");
  @TextFieldSetting
  public final ConfigProperty<String> bankCustomAccount3 = new ConfigProperty<>("DEFXXXXXXXX");

  public boolean isExtraEnabled() {
    return enabledExtra.get();
  }

  public boolean isCompanyEnabled() { return enabledCompany.get(); }

  public String getFirstCustomAccount() {
    return bankCustomAccount1.get();
  }

  public String getSecondCustomAccount() {
    return bankCustomAccount2.get();
  }

  public String getThirdCustomAccount() {
    return bankCustomAccount3.get();
  }
}