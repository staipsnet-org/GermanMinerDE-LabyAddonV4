package de.germanminer.addon.widgets.ontime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.ontime.TotalOntimePacket;
import packets.widget.ontime.WeeklyOntimePacket;

public class WeeklyOntimeWidget extends GermanMinerWidget<WeeklyOntimePacket> {

  public WeeklyOntimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_DUTY, () -> addon.configuration().getOntimeSetting().isWeeklyOntimeEnabled(), WeeklyOntimePacket::getWeeklyOntime);
  }

}