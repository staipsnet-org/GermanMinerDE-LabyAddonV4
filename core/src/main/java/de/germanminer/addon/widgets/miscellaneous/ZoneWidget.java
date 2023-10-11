package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.miscellaneous.ZonePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class ZoneWidget extends GermanMinerWidget<ZonePacket> {

  public ZoneWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.MISCELLANEOUS_ZONE, () -> addon.configuration()
        .miscellaneousConfig().zone().get(), ZonePacket::getCurrentZone);
  }

}
