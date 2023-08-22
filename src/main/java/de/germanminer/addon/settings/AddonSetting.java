package de.germanminer.addon.settings;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class AddonSetting extends AddonConfig {

  /**
   * Globale Einstellungen
   */
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true).addChangeListener(
      (type, oldValue, newValue) -> GermanMinerAddon.getInstance().getVehicleDisplayWidget().sendInfo());

  /**
   * Einstellungen Vehiclesystem
   */
  @SettingSection("hotkey")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledHotkey = new ConfigProperty<>(true);  @SwitchSetting

  private final VehicleSetting vehicleSetting = new VehicleSetting();

  /**
   * Einstellungen Banksystem
   */
  @SettingSection("banksystem")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledBanksystem = new ConfigProperty<>(true);
  private final BankSetting bankSetting = new BankSetting();

  /**
   * Einstellungen Ontimesystem
   */
  @SettingSection("ontime")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledOntime = new ConfigProperty<>(true);
  private final OntimeSetting ontimeSetting = new OntimeSetting();

  /**
   * Einstellungen Kalender
   */
  @SettingSection("calender")
  private final CalenderSetting calenderSetting = new CalenderSetting();

  /**
   * Einstellungen Sonstiges
   */
  @SettingSection("miscellaneous")
  private final AddonSubSetting subSettings = new AddonSubSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> isEnabled() {
    return enabled;
  }

  public boolean isHotkeyEnabled() {
    return enabledHotkey.get();
  }

  public boolean isBankEnabled() {
    return enabledBanksystem.get();
  }

  public boolean isOntimeEnabled() {
    return enabledOntime.get();
  }

  public VehicleSetting getVehicleSetting() {
    return vehicleSetting;
  }

  public BankSetting getBankSetting() {
    return bankSetting;
  }

  public AddonSubSetting getSubSettings() {
    return subSettings;
  }

  public OntimeSetting getOntimeSetting() {
    return ontimeSetting;
  }

  public CalenderSetting getCalenderSetting() {
    return calenderSetting;
  }
}