package de.germanminer.addon.widgets.playtime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.playtime.TotalPlaytimePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class TotalPlaytimeWidget extends GermanMinerWidget<TotalPlaytimePacket> {

  public TotalPlaytimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_TOTAL, () -> addon.configuration().playTimeConfig().enabled().get()
        && addon.configuration().playTimeConfig().total().get(), TotalPlaytimePacket::getTotalPlaytime);
  }

}
