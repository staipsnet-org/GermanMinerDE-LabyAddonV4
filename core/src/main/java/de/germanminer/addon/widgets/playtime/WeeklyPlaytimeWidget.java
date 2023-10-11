package de.germanminer.addon.widgets.playtime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.playtime.WeeklyPlaytimePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class WeeklyPlaytimeWidget extends GermanMinerWidget<WeeklyPlaytimePacket> {

  public WeeklyPlaytimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_WEEKLY, () -> addon.configuration().playTimeConfig().enabled().get()
        && addon.configuration().playTimeConfig().weekly().get(), WeeklyPlaytimePacket::getWeeklyPlaytime);
  }

}
