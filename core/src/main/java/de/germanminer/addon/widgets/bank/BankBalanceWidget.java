package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.utils.AddonUtils;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.balance.BankBalancePacket;

public class BankBalanceWidget extends GermanMinerWidget<BankBalancePacket> {

  public BankBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_BANK, () -> addon.configuration().isBankEnabled(),
        packet -> AddonUtils.formatNumber(packet.getBank()));
  }
}