package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.ZonePacket;

public class ZoneWidget extends GermanMinerWidget<ZonePacket> {

  public ZoneWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.MISCELLANEOUS_ZONE, () -> addon.configuration()
        .getSubSettings().isZoneEnabled(), ZonePacket::getCurrentZone);
  }

}