package de.germanminer.addon.widgets.playtime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.playtime.DailyPlaytimePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class DailyPlaytimeWidget extends GermanMinerWidget<DailyPlaytimePacket> {

  public DailyPlaytimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_DAILY, () -> addon.configuration().playTimeConfig().enabled().get()
        && addon.configuration().playTimeConfig().daily().get(), DailyPlaytimePacket::getDailyPlaytime);
  }

}
