package de.germanminer.addon.widgets.ontime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.ontime.PaydayPacket;

public class PaydayWidget extends GermanMinerWidget<PaydayPacket> {

  public PaydayWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_DUTY, () -> addon.configuration().getOntimeSetting().isPaydayEnabled(), PaydayPacket::getNextPayday);
  }

}