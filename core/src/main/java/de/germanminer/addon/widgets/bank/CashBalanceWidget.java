package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.utils.AddonUtils;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.balance.CashBalancePacket;

/**
 * Widget zum Anzeigen vom Bargeld
 */
public class CashBalanceWidget extends GermanMinerWidget<CashBalancePacket> {

  public CashBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_CASH, () -> addon.configuration().isEnabled().get(),
        packet -> AddonUtils.formatNumber(packet.getCash()));
  }
}