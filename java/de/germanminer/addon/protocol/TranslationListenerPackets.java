package de.germanminer.addon.protocol;

import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import packets.GermanMinerPacket;
import packets.info.AddonInfoPacket;
import packets.info.ServerAddonInfoPacket;
import packets.special.InputPromptPacket;
import packets.special.NotificationPacket;
import packets.vehicle.VehicleDisplayPacket;
import packets.vehicle.VehicleHotKeyPacket;
import packets.vehicle.VehiclePositionPacket;
import packets.widget.CompassPacket;
import packets.widget.PowerupPacket;
import packets.widget.VotePacket;
import packets.widget.ZonePacket;
import packets.widget.balance.BankBalancePacket;
import packets.widget.balance.CashBalancePacket;
import packets.widget.balance.CompanyBalancePacket;
import packets.widget.balance.ExtraBalancePacket;
import packets.widget.level.LevelPacket;
import packets.widget.level.LevelPointsPacket;
import packets.widget.ontime.DailyOntimePacket;
import packets.widget.ontime.DutyOntimePacket;
import packets.widget.ontime.PaydayPacket;
import packets.widget.ontime.TotalOntimePacket;
import packets.widget.ontime.WeeklyOntimePacket;

/**
 * Auflistung aller Packets und wie sie verarbeitet werden müssen
 */
public enum TranslationListenerPackets {
  INFO_ADDON_SERVER(ServerAddonInfoPacket.class, "INFO",
      TranslationSide.OUTGOING), // ToDo: Deprecated
  INFO_ADDON(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),
  WIDGET_CASH(CashBalancePacket.class, "gmde-balance-cash", TranslationSide.INCOMING),
  WIDGET_BANK(BankBalancePacket.class, "gmde-balance-bank", TranslationSide.INCOMING),
  WIDGET_BANK_COMPANY(CompanyBalancePacket.class, "gmde-balance-company", TranslationSide.INCOMING),
  WIDGET_BANK_EXTRA(ExtraBalancePacket.class, "gmde-balance-extra", TranslationSide.INCOMING),
  WIDGET_LEVEL(LevelPacket.class, "gmde-level", TranslationSide.INCOMING),
  WIDGET_LEVEL_POINTS(LevelPointsPacket.class, "gmde-levelpoints", TranslationSide.INCOMING),
  WIDGET_DAILY_ONTIME(DailyOntimePacket.class, "gmde-ontime-daily", TranslationSide.INCOMING),
  WIDGET_WEEKLY_ONTIME(WeeklyOntimePacket.class, "gmde-ontime-weekly", TranslationSide.INCOMING),
  WIDGET_TOTAL_ONTIME(TotalOntimePacket.class, "gmde-ontime-total", TranslationSide.INCOMING),
  WIDGET_DUTY_ONTIME(DutyOntimePacket.class, "gmde-ontime-duty", TranslationSide.INCOMING),
  WIDGET_PAYDAY(PaydayPacket.class, "gmde-ontime-payday", TranslationSide.INCOMING),
  WIDGET_VEHICLE_DISPLAY(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
  WIDGET_VEHICLE_POSITION(VehiclePositionPacket.class, "gmde-vehicle-position",
      TranslationSide.INCOMING),
  WIDGET_VEHICLE_HOTKEY(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING),
  WIDGET_COMPASS(CompassPacket.class, "gmde-compass", TranslationSide.INCOMING),
  WIDGET_POWERUP_COOLDOWN(PowerupPacket.class, "gmde-powerup", TranslationSide.INCOMING),
  WIDGET_ZONE(ZonePacket.class, "gmde-zone", TranslationSide.INCOMING),
  WIDGET_VOTE(VotePacket.class, "gmde-vote", TranslationSide.INCOMING),
  OTHER_NOTIFICATION(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
  OTHER_INPUT(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH);

  private final Class<? extends GermanMinerPacket> packet;
  private final String oldMessageKey;
  private final TranslationSide translationSide;

  TranslationListenerPackets(Class<? extends GermanMinerPacket> packet, String oldMessageKey,
      TranslationSide translationSide) {
    this.packet = packet;
    this.oldMessageKey = oldMessageKey;
    this.translationSide = translationSide;
  }

  public void registerListener(ProtocolService protocolService) {
    protocolService.registerTranslationListener(
        new PayloadTranslationListener(packet, oldMessageKey, translationSide));
  }
}