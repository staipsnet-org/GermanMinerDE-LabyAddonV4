package de.germanminer.addon.protocol;

import net.labymod.serverapi.protocol.packet.protocol.Protocol;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
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
import packets.widget.balance.CompanyBalancePacket;
import packets.widget.balance.ExtraBalancePacket;
import packets.widget.level.LevelPacket;
import packets.widget.balance.BankBalancePacket;
import packets.widget.balance.CashBalancePacket;
import packets.widget.level.LevelPointsPacket;
import packets.widget.ontime.DailyOntimePacket;
import packets.widget.ontime.DutyOntimePacket;
import packets.widget.ontime.PaydayPacket;
import packets.widget.ontime.TotalOntimePacket;
import packets.widget.ontime.WeeklyOntimePacket;

public class GermanMinerProtocol extends Protocol {

  public GermanMinerProtocol() {
    super(PayloadChannelIdentifier.create("labymod", "germanminer"));

    // 0 - 9: Informationen für den Server
    super.registerPacket(0, new AddonInfoPacket());
    super.registerPacket(1, new ServerAddonInfoPacket()); // ToDo: Deprecated

    // 10 - 99: Widgets
    super.registerPacket(10, new CashBalancePacket());
    super.registerPacket(11, new BankBalancePacket());
    super.registerPacket(12, new LevelPacket());
    super.registerPacket(13, new LevelPointsPacket());
    super.registerPacket(14, new DailyOntimePacket());
    super.registerPacket(15, new WeeklyOntimePacket());
    super.registerPacket(16, new TotalOntimePacket());
    super.registerPacket(17, new DutyOntimePacket());
    super.registerPacket(18, new PaydayPacket());
    super.registerPacket(19, new ZonePacket());
    super.registerPacket(20, new CompassPacket());
    super.registerPacket(21, new CompanyBalancePacket());
    super.registerPacket(22, new ExtraBalancePacket());
    super.registerPacket(23, new PowerupPacket());
    super.registerPacket(25, new VotePacket());

    super.registerPacket(80, new VehicleDisplayPacket());
    super.registerPacket(81, new VehiclePositionPacket());
    super.registerPacket(82, new VehicleHotKeyPacket());


    // ab 100: Sonstiges
    super.registerPacket(100, new NotificationPacket());
    super.registerPacket(101, new InputPromptPacket());

  }
}
