package de.germanminer.addon.widgets.playtime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.playtime.DutyPlaytimePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class DutyPlaytimeWidget extends GermanMinerWidget<DutyPlaytimePacket> {

  public DutyPlaytimeWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_DUTY, () -> addon.configuration().playTimeConfig().enabled().get()
        && addon.configuration().playTimeConfig().duty().get(), DutyPlaytimePacket::getDutyPlaytime);
  }

}
