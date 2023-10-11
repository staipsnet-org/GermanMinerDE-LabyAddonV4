package de.germanminer.addon.widgets.balance;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.balance.BankBalancePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class BankBalanceWidget extends GermanMinerWidget<BankBalancePacket> {

  public BankBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_BANK, () -> addon.configuration().balanceConfig().enabled().get(),
        packet -> BalanceFormatter.getInstance().format(packet.getBank()));
  }

}
