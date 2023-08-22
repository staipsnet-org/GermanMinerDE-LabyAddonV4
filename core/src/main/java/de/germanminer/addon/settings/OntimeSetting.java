package de.germanminer.addon.settings;

import de.germanminer.addon.widgets.ontime.OntimeFormatSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

public class OntimeSetting extends Config {

  @SwitchSetting
  private final ConfigProperty<Boolean> dailyOntime = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> weeklyOntime = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> dutyOntime = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> totalOntime = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> payday = new ConfigProperty<>(true);
  @DropdownSetting
  private final ConfigProperty<OntimeFormatSetting> ontimeFormat = new ConfigProperty<>(
      OntimeFormatSetting.DHM);

  public boolean isDailyOntimeEnabled() {
    return dailyOntime.get();
  }

  public boolean isWeeklyOntimeEnabled() {
    return weeklyOntime.get();
  }

  public boolean isDutyOntimeEnabled() {
    return dutyOntime.get();
  }

  public boolean isTotalOntimeEnabled() {
    return totalOntime.get();
  }

  public boolean isPaydayEnabled() {
    return payday.get();
  }

  public OntimeFormatSetting getOntimeFormat() {
    return ontimeFormat.get();
  }
}