package de.germanminer.addon.widgets.balance;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.balance.CompanyBalancePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class CompanyBalanceWidget extends GermanMinerWidget<CompanyBalancePacket> {

  public CompanyBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_BANK_COMPANY, () -> addon.configuration().balanceConfig().enabled().get()
        && addon.configuration().balanceConfig().company().get(), packet -> BalanceFormatter.getInstance().format(packet.getCompany()));
  }

}
