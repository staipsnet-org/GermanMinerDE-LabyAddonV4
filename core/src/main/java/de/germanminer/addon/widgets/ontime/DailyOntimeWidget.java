package de.germanminer.addon.widgets.ontime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.ontime.DailyOntimePacket;

public class DailyOntimeWidget extends GermanMinerWidget<DailyOntimePacket> {

  public DailyOntimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_DAILY, () -> addon.configuration().getOntimeSetting().isDailyOntimeEnabled(), DailyOntimePacket::getDailyOntime);
  }

}