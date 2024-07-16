package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.config.CalenderConfig;
import de.germanminer.addon.widgets.WidgetIcon;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.util.I18n;

public class CalenderWidget extends TextHudWidget<TextHudWidgetConfig> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private TextLine calender;
  private String calenderValue;

  public CalenderWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.MISCELLANEOUS_CALENDAR.getIcon();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.calender = super.createLine(
      Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
      this.calenderValue == null ? String.format("germanmineraddon.hudWidget.%s.empty", super.getId())
        : Component.text(this.calenderValue));

    update();
    addChangeListener();

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled();
  }

  private void update() {
    final String value = getValue();
    this.calenderValue = value.isEmpty() ? I18n.translate(String.format("germanmineraddon.hudWidget.%s.empty", super.getId())) : value;
    this.calender.updateAndFlush(this.calenderValue);
  }

  private String getValue() {
    final LocalDateTime localDateTime = LocalDateTime.now();
    final DayOfWeek currentDay = localDateTime.getDayOfWeek();
    final CalenderConfig config = this.addon.configuration().calenderConfig();

    switch (currentDay) {
      case MONDAY -> {
        return config.monday().get();
      }
      case TUESDAY -> {
        return config.tuesday().get();
      }
      case WEDNESDAY -> {
        return config.wednesday().get();
      }
      case THURSDAY -> {
        return config.thursday().get();
      }
      case FRIDAY -> {
        return config.friday().get();
      }
      case SATURDAY -> {
        return config.saturday().get();
      }
      case SUNDAY -> {
        return config.sunday().get();
      }
    }

    return "";
  }

  private void addChangeListener() {
    this.addon.configuration().calenderConfig().monday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().tuesday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().wednesday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().thursday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().friday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().saturday().addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().calenderConfig().sunday().addChangeListener(((type, oldValue, newValue) -> update()));
  }

}
