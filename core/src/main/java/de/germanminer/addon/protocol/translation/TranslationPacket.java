package de.germanminer.addon.protocol.translation;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.protocol.packet.balance.BankBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.CashBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.CompanyBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.ExtraBalancePacket;
import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import de.germanminer.addon.api.protocol.packet.info.VersionInfoPacket;
import de.germanminer.addon.api.protocol.packet.level.LevelPacket;
import de.germanminer.addon.api.protocol.packet.level.LevelPointsPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.CompassPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.InputPromptPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.NotificationPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.PowerUpPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.VotePacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.ZonePacket;
import de.germanminer.addon.api.protocol.packet.playtime.DailyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.DutyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.PaydayPacket;
import de.germanminer.addon.api.protocol.packet.playtime.TotalPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.WeeklyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleHotKeyPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;

public enum TranslationPacket {

  INFO_ADDON(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),
  INFO_ADDON_SERVER(VersionInfoPacket.class, "INFO", TranslationSide.OUTGOING),

  BALANCE_CASH(CashBalancePacket.class, "gmde-balance-cash", TranslationSide.INCOMING),
  BALANCE_BANK(BankBalancePacket.class, "gmde-balance-bank", TranslationSide.INCOMING),
  BALANCE_BANK_COMPANY(CompanyBalancePacket.class, "gmde-balance-company", TranslationSide.INCOMING),
  BALANCE_BANK_EXTRA(ExtraBalancePacket.class, "gmde-balance-extra", TranslationSide.INCOMING),

  PLAYER_LEVEL(LevelPacket.class, "gmde-level", TranslationSide.INCOMING),
  PLAYER_LEVEL_POINTS(LevelPointsPacket.class, "gmde-levelpoints", TranslationSide.INCOMING),

  PLAYTIME_DAILY(DailyPlaytimePacket.class, "gmde-ontime-daily", TranslationSide.INCOMING),
  PLAYTIME_WEEKLY(WeeklyPlaytimePacket.class, "gmde-ontime-weekly", TranslationSide.INCOMING),
  PLAYTIME_TOTAL(TotalPlaytimePacket.class, "gmde-ontime-total", TranslationSide.INCOMING),
  PLAYTIME_DUTY(DutyPlaytimePacket.class, "gmde-ontime-duty", TranslationSide.INCOMING),
  PLAYTIME_PAYDAY(PaydayPacket.class, "gmde-ontime-payday", TranslationSide.INCOMING),

  VEHICLE_DISPLAY(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
  VEHICLE_POSITION(VehiclePositionPacket.class, "gmde-vehicle-position", TranslationSide.INCOMING),
  VEHICLE_HOTKEY(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING),

  MISCELLANEOUS_COMPASS(CompassPacket.class, "gmde-compass", TranslationSide.INCOMING),
  MISCELLANEOUS_POWER_UP(PowerUpPacket.class, "gmde-powerup", TranslationSide.INCOMING),
  MISCELLANEOUS_ZONE(ZonePacket.class, "gmde-zone", TranslationSide.INCOMING),
  MISCELLANEOUS_VOTE(VotePacket.class, "gmde-vote", TranslationSide.INCOMING),
  MISCELLANEOUS_NOTIFICATION(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
  MISCELLANEOUS_INPUT_PROMPT(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH);

  private final Class<? extends GermanMinerPacket> packet;
  private final String oldMessageKey;
  private final TranslationSide side;

  TranslationPacket(final Class<? extends GermanMinerPacket> packet,
      final String oldMessageKey, final TranslationSide side) {
    this.packet = packet;
    this.oldMessageKey = oldMessageKey;
    this.side = side;
  }

  public void register(final ProtocolService protocol) {
    protocol.registerTranslationListener(new GermanMinerPayloadTranslationListener(this.packet, this.oldMessageKey, this.side));
  }

}
