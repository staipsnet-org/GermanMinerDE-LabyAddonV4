package de.germanminer.addon.protocol.translation;

import net.labymod.api.serverapi.TranslationProtocol;
import net.labymod.serverapi.api.packet.Direction;
import packets.GermanMinerPacket;
import packets.info.AddonInfoPacket;
import packets.info.VersionInfoPacket;
import packets.special.InputPromptPacket;
import packets.special.NotificationPacket;
import packets.vehicle.VehicleDisplayPacket;
import packets.vehicle.VehicleHotKeyPacket;
import packets.vehicle.VehiclePositionPacket;
import packets.widget.CompassPacket;
import packets.widget.PepperCooldownPacket;
import packets.widget.PowerupPacket;
import packets.widget.RegionOwnerPacket;
import packets.widget.RespawnPacket;
import packets.widget.VotePacket;
import packets.widget.WantedPacket;
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

public enum TranslationPacket {

  INFO_ADDON(AddonInfoPacket.class, "gmde-addon-info", Direction.SERVERBOUND),
  INFO_ADDON_SERVER(VersionInfoPacket.class, "INFO", Direction.SERVERBOUND),

  BALANCE_CASH(CashBalancePacket.class, "gmde-balance-cash", Direction.CLIENTBOUND),
  BALANCE_BANK(BankBalancePacket.class, "gmde-balance-bank", Direction.CLIENTBOUND),
  BALANCE_BANK_COMPANY(CompanyBalancePacket.class, "gmde-balance-company", Direction.CLIENTBOUND),
  BALANCE_BANK_EXTRA(ExtraBalancePacket.class, "gmde-balance-extra", Direction.CLIENTBOUND),

  PLAYER_LEVEL(LevelPacket.class, "gmde-level", Direction.CLIENTBOUND),
  PLAYER_LEVEL_POINTS(LevelPointsPacket.class, "gmde-levelpoints", Direction.CLIENTBOUND),

  PLAYTIME_DAILY(DailyOntimePacket.class, "gmde-ontime-daily", Direction.CLIENTBOUND),
  PLAYTIME_WEEKLY(WeeklyOntimePacket.class, "gmde-ontime-weekly", Direction.CLIENTBOUND),
  PLAYTIME_TOTAL(TotalOntimePacket.class, "gmde-ontime-total", Direction.CLIENTBOUND),
  PLAYTIME_DUTY(DutyOntimePacket.class, "gmde-ontime-duty", Direction.CLIENTBOUND),
  PLAYTIME_PAYDAY(PaydayPacket.class, "gmde-ontime-payday", Direction.CLIENTBOUND),

  VEHICLE_DISPLAY(VehicleDisplayPacket.class, "gmde-vehicle-display", Direction.BOTH),
  VEHICLE_POSITION(VehiclePositionPacket.class, "gmde-vehicle-position", Direction.CLIENTBOUND),
  VEHICLE_HOTKEY(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", Direction.SERVERBOUND),

  MISCELLANEOUS_COMPASS(CompassPacket.class, "gmde-compass", Direction.CLIENTBOUND),
  MISCELLANEOUS_POWER_UP(PowerupPacket.class, "gmde-powerup", Direction.CLIENTBOUND),
  MISCELLANEOUS_ZONE(ZonePacket.class, "gmde-zone", Direction.CLIENTBOUND),
  MISCELLANEOUS_VOTE(VotePacket.class, "gmde-vote", Direction.CLIENTBOUND),
  MISCELLANEOUS_PEPPER(PepperCooldownPacket.class, "gmde-pepper", Direction.CLIENTBOUND),
  MISCELLANEOUS_RESPAWN(RespawnPacket.class, "gmde-respawn", Direction.CLIENTBOUND),
  MISCELLANEOUS_WANTED(WantedPacket.class, "gmde-wanted", Direction.CLIENTBOUND),
  MISCELLANEOUS_REGION_OWNER(RegionOwnerPacket.class, "gmde-region-owner", Direction.CLIENTBOUND),
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