package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.utils.AddonUtils;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.balance.CompanyBalancePacket;

public class CompanyBalanceWidget extends GermanMinerWidget<CompanyBalancePacket> {

  public CompanyBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.BALANCE_BANK_COMPANY, () -> addon.configuration().isBankEnabled()
        && addon.configuration().getBankSetting().isCompanyEnabled(), packet -> AddonUtils.formatNumber(packet.getCompany()));
  }
}