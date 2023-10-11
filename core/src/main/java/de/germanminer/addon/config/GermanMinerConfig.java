package de.germanminer.addon.config;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class GermanMinerConfig extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true).addChangeListener(
      (type, oldValue, newValue) -> GermanMinerAddon.getInstance().getVehicleWidget().sendInfo());

  @SettingSection("vehicle")
  private final VehicleConfig vehicleConfig = new VehicleConfig();

  @SettingSection("balance")
  private final BalanceConfig balanceConfig = new BalanceConfig();

  @SettingSection("playTime")
  private final PlayTimeConfig playTimeConfig = new PlayTimeConfig();

  @SettingSection("calender")
  private final CalenderConfig calenderConfig = new CalenderConfig();

  @SettingSection("miscellaneous")
  private final MiscellaneousConfig miscellaneousConfig = new MiscellaneousConfig();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public VehicleConfig vehicleConfig() {
    return this.vehicleConfig;
  }

  public BalanceConfig balanceConfig() {
    return this.balanceConfig;
  }

  public PlayTimeConfig playTimeConfig() {
    return this.playTimeConfig;
  }

  public CalenderConfig calenderConfig() {
    return this.calenderConfig;
  }

  public MiscellaneousConfig miscellaneousConfig() {
    return this.miscellaneousConfig;
  }

}
