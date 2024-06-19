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
import net.labymod.api.serverapi.TranslationProtocol;
import net.labymod.serverapi.api.packet.Direction;

public enum TranslationPacket {

  INFO_ADDON(AddonInfoPacket.class, "gmde-addon-info", Direction.SERVERBOUND),
  INFO_ADDON_SERVER(VersionInfoPacket.class, "INFO", Direction.SERVERBOUND),

  BALANCE_CASH(CashBalancePacket.class, "gmde-balance-cash", Direction.CLIENTBOUND),
  BALANCE_BANK(BankBalancePacket.class, "gmde-balance-bank", Direction.CLIENTBOUND),
  BALANCE_BANK_COMPANY(CompanyBalancePacket.class, "gmde-balance-company", Direction.CLIENTBOUND),
  BALANCE_BANK_EXTRA(ExtraBalancePacket.class, "gmde-balance-extra", Direction.CLIENTBOUND),

  PLAYER_LEVEL(LevelPacket.class, "gmde-level", Direction.CLIENTBOUND),
  PLAYER_LEVEL_POINTS(LevelPointsPacket.class, "gmde-levelpoints", Direction.CLIENTBOUND),

  PLAYTIME_DAILY(DailyPlaytimePacket.class, "gmde-ontime-daily", Direction.CLIENTBOUND),
  PLAYTIME_WEEKLY(WeeklyPlaytimePacket.class, "gmde-ontime-weekly", Direction.CLIENTBOUND),
  PLAYTIME_TOTAL(TotalPlaytimePacket.class, "gmde-ontime-total", Direction.CLIENTBOUND),
  PLAYTIME_DUTY(DutyPlaytimePacket.class, "gmde-ontime-duty", Direction.CLIENTBOUND),
  PLAYTIME_PAYDAY(PaydayPacket.class, "gmde-ontime-payday", Direction.CLIENTBOUND),

  VEHICLE_DISPLAY(VehicleDisplayPacket.class, "gmde-vehicle-display", Direction.BOTH),
  VEHICLE_POSITION(VehiclePositionPacket.class, "gmde-vehicle-position", Direction.CLIENTBOUND),
  VEHICLE_HOTKEY(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", Direction.SERVERBOUND),

  MISCELLANEOUS_COMPASS(CompassPacket.class, "gmde-compass", Direction.CLIENTBOUND),
  MISCELLANEOUS_POWER_UP(PowerUpPacket.class, "gmde-powerup", Direction.CLIENTBOUND),
  MISCELLANEOUS_ZONE(ZonePacket.class, "gmde-zone", Direction.CLIENTBOUND),
  MISCELLANEOUS_VOTE(VotePacket.class, "gmde-vote", Direction.CLIENTBOUND),
  MISCELLANEOUS_NOTIFICATION(NotificationPacket.class, "gmde-notification", Direction.CLIENTBOUND),
  MISCELLANEOUS_INPUT_PROMPT(InputPromptPacket.class, "gmde-input-prompt", Direction.BOTH);

  private final Class<? extends GermanMinerPacket> packet;
  private final String oldMessageKey;
  private final Direction side;

  TranslationPacket(final Class<? extends GermanMinerPacket> packet,
      final String oldMessageKey, final Direction side) {
    this.packet = packet;
    this.oldMessageKey = oldMessageKey;
    this.side = side;
  }

  public void register(final TranslationProtocol protocol) {
    protocol.registerListener(new GermanMinerTranslationListener(this.packet, this.oldMessageKey, this.side));
  }

}
