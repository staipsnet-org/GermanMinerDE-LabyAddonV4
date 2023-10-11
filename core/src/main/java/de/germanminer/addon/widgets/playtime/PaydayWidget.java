package de.germanminer.addon.widgets.playtime;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.playtime.PaydayPacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class PaydayWidget extends GermanMinerWidget<PaydayPacket> {

  public PaydayWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYTIME_PAYDAY, () -> addon.configuration().playTimeConfig().enabled().get()
        && addon.configuration().playTimeConfig().payday().get(), PaydayPacket::getNextPayday);
  }

}
