package de.germanminer.addon.settings;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class GermanMinerSetting extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true).addChangeListener(
      (type, oldValue, newValue) -> GermanMinerAddon.getInstance().getVehicleDisplayWidget()
          .sendInfo());

  @SettingSection("hotkey")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledHotkey = new ConfigProperty<>(true);
  private final VehicleSubSetting vehicleSubSetting = new VehicleSubSetting();

  @SettingSection("banksystem")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledBanksystem = new ConfigProperty<>(true);
  private final BankSubSetting bankSubSetting = new BankSubSetting();
  @SettingSection("ontime")
  @SwitchSetting
  private final ConfigProperty<Boolean> enabledOntime = new ConfigProperty<>(true);
  private final OntimeSubSetting ontimeSubSetting = new OntimeSubSetting();
  @SettingSection("miscellaneous")
  private final GermanMinerSubSetting subSettings = new GermanMinerSubSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> getEnabled() {
    return enabled;
  }

  public VehicleSubSetting getVehicleSubSetting() {
    return vehicleSubSetting;
  }

  public BankSubSetting getBankSubSetting() {
    return bankSubSetting;
  }

  public GermanMinerSubSetting getSubSettings() {
    return subSettings;
  }

  public OntimeSubSetting getOntimeSubSetting() {
    return ontimeSubSetting;
  }
}