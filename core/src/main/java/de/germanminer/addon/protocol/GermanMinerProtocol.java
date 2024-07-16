package de.germanminer.addon.protocol;

import net.labymod.serverapi.api.AbstractProtocolService;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.packet.Direction;
import net.labymod.serverapi.api.payload.PayloadChannelIdentifier;
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
    super.registerPacket(30, DailyOntimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(31, WeeklyOntimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(32, TotalOntimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(33, DutyOntimePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(34, PaydayPacket.class, Direction.CLIENTBOUND);

    // 40-49 Vehicle Packets
    super.registerPacket(40, VehicleDisplayPacket.class, Direction.BOTH);
    super.registerPacket(41, VehiclePositionPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(42, VehicleHotKeyPacket.class, Direction.SERVERBOUND);

    // 50-infinity: Miscellaneous Packets
    super.registerPacket(50, NotificationPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(51, InputPromptPacket.class, Direction.BOTH);
    super.registerPacket(52, CompassPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(53, PowerupPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(54, VotePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(55, ZonePacket.class, Direction.CLIENTBOUND);
    super.registerPacket(56, RespawnPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(57, PepperCooldownPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(58, WantedPacket.class, Direction.CLIENTBOUND);
    super.registerPacket(59, RegionOwnerPacket.class, Direction.CLIENTBOUND);
  }

}