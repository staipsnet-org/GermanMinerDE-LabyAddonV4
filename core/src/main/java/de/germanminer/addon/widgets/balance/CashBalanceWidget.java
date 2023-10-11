package de.germanminer.addon.widgets.balance;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.balance.CashBalancePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class CashBalanceWidget extends GermanMinerWidget<CashBalancePacket> {

  public CashBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_CASH, () -> addon.configuration().balanceConfig().enabled().get(),
        packet -> BalanceFormatter.getInstance().format(packet.getCash()));
  }

}
