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
import net.labymod.serverapi.protocol.packet.protocol.Protocol;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;

public class GermanMinerProtocol extends Protocol {

  public GermanMinerProtocol() {
    super(PayloadChannelIdentifier.create("labymod", "germanminer"),
        PayloadChannelIdentifier.create("labymod3", "main"));

    // 0-9: Info Packets
    super.registerPacket(0, new AddonInfoPacket());
    super.registerPacket(1, new VersionInfoPacket());

    // 10-19: Balance Packets
    super.registerPacket(10, new CashBalancePacket());
    super.registerPacket(11, new BankBalancePacket());
    super.registerPacket(12, new CompanyBalancePacket());
    super.registerPacket(13, new ExtraBalancePacket());

    // 20-29: PlayerLevel Packets
    super.registerPacket(20, new LevelPacket());
    super.registerPacket(21, new LevelPointsPacket());

    // 30-39: PlayTime Packets
    super.registerPacket(30, new DailyPlaytimePacket());
    super.registerPacket(31, new WeeklyPlaytimePacket());
    super.registerPacket(32, new TotalPlaytimePacket());
    super.registerPacket(33, new DutyPlaytimePacket());
    super.registerPacket(34, new PaydayPacket());

    // 40-49 Vehicle Packets
    super.registerPacket(40, new VehicleDisplayPacket());
    super.registerPacket(41, new VehiclePositionPacket());
    super.registerPacket(42, new VehicleHotKeyPacket());

    // 50-infinity: Miscellaneous Packets
    super.registerPacket(50, new NotificationPacket());
    super.registerPacket(51, new InputPromptPacket());
    super.registerPacket(52, new CompassPacket());
    super.registerPacket(53, new PowerUpPacket());
    super.registerPacket(54, new VotePacket());
    super.registerPacket(55, new ZonePacket());

  }

}
