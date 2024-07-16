package de.germanminer.addon.settings;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.miscellaneous.CalenderWidget;
import java.time.LocalDateTime;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class CalenderSetting extends Config {

  public String getCurrentDay() {
    LocalDateTime localDateTime = LocalDateTime.now();
    int currentDay = localDateTime.getDayOfWeek().getValue();

    return switch (currentDay) {
      case 1 -> monday.get();
      case 2 -> tuesday.get();
      case 3 -> wednesday.get();
      case 4 -> thursday.get();
      case 5 -> friday.get();
      case 6 -> saturday.get();
      case 7 -> sunday.get();
      default -> "";
    };
  }

  @TextFieldSetting
  private final ConfigProperty<String> monday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });



  @TextFieldSetting
  private final ConfigProperty<String> tuesday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
  @TextFieldSetting
  private final ConfigProperty<String> wednesday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
  @TextFieldSetting
  private final ConfigProperty<String> thursday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
  @TextFieldSetting
  private final ConfigProperty<String> friday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
  @TextFieldSetting
  private final ConfigProperty<String> saturday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
  @TextFieldSetting
  private final ConfigProperty<String> sunday = new ConfigProperty<>("").addChangeListener(
      (type, oldValue, newValue) -> {
        GermanMinerAddon addon = GermanMinerAddon.getInstance();
        if (addon != null) {
          CalenderWidget calenderWidget = addon.getCalenderWidget();
          if (calenderWidget != null) {
            calenderWidget.update();
          }
        }
      });
}