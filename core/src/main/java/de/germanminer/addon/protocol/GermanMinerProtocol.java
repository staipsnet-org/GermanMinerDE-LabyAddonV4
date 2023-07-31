package de.germanminer.addon.protocol;

import net.labymod.serverapi.protocol.packet.protocol.Protocol;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import protocol.packet.info.AddonInfoPacket;
import protocol.packet.info.ServerAddonInfoPacket;
import protocol.packet.special.InputPromptPacket;
import protocol.packet.special.NotificationPacket;
import protocol.packet.vehicle.VehicleDisplayPacket;
import protocol.packet.vehicle.VehicleHotKeyPacket;
import protocol.packet.vehicle.VehiclePositionPacket;
import protocol.packet.widget.BalancePacket;
import protocol.packet.widget.LevelPacket;

public class GermanMinerProtocol extends Protocol {

  public GermanMinerProtocol() {
    super(PayloadChannelIdentifier.create("labymod", "germanminer"));

    super.registerPacket(0, new AddonInfoPacket());
    super.registerPacket(1, new ServerAddonInfoPacket());

    super.registerPacket(10, new BalancePacket());
    super.registerPacket(11, new LevelPacket());

    super.registerPacket(20, new VehicleDisplayPacket());
    super.registerPacket(21, new VehiclePositionPacket());
    super.registerPacket(22, new VehicleHotKeyPacket());

    super.registerPacket(30, new NotificationPacket());
    super.registerPacket(31, new InputPromptPacket());

  }
}
