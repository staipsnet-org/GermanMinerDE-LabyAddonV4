package de.germanminer.addon.protocol;

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
import net.labymod.serverapi.api.AbstractProtocolService;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.packet.Direction;
import net.labymod.serverapi.api.payload.PayloadChannelIdentifier;

public class GermanMinerProtocol extends Protocol {

  public GermanMinerProtocol(final AbstractProtocolService service) {
    super(service, PayloadChannelIdentifier.create("labymod", "germanminer"));

    // 0-9: Info Packets
    super.registerPacket(0, AddonInfoPacket.class, Direction.SERVERBOUND);
    super.registerPacket(1, VersionInfoPacket.class, Direction.SERVERBOUND);

    // 10-19: Balance Packets
    super.registerPacket(10, CashBalancePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(11, BankBalancePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(12, CompanyBalancePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(13, ExtraBalancePacket.class, Direction.CLIENTBOUND);

    // 20-29: PlayerLevel Packets
    super.registerPacket(20, LevelPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(21, LevelPointsPacket.class, Direction.CLIENTBOUND);

    // 30-39: PlayTime Packets
    super.registerPacket(30, DailyPlaytimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(31, WeeklyPlaytimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(32, TotalPlaytimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(33, DutyPlaytimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(34, PaydayPacket.class, Direction.CLIENTBOUND);

    // 40-49 Vehicle Packets
    super.registerPacket(40, VehicleDisplayPacket.class, Direction.BOTH);
    super.registerPacket(41, VehiclePositionPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(42, VehicleHotKeyPacket.class, Direction.SERVERBOUND);

    // 50-infinity: Miscellaneous Packets
    super.registerPacket(50, NotificationPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(51, InputPromptPacket.class, Direction.BOTH);
    super.registerPacket(52, CompassPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(53, PowerUpPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(54, VotePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(55, ZonePacket.class, Direction.CLIENTBOUND);
  }

}
