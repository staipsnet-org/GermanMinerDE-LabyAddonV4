package de.germanminer.addon.protocol;

import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import de.germanminer.addon.api.protocol.packet.special.NotificationPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleHotKeyPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.api.protocol.packet.widget.BalancePacket;
import de.germanminer.addon.api.protocol.packet.widget.LevelPacket;
import net.labymod.serverapi.protocol.packet.protocol.Protocol;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;

public class GermanMinerProtocol extends Protocol {

  public GermanMinerProtocol() {
    super(PayloadChannelIdentifier.create("labymod", "germanminer"));

    // 0-9: Info Packets
    super.registerPacket(0, new AddonInfoPacket()); // labymod3 = gmde-addon-info

    // 10-19: Widget Packets
    super.registerPacket(10, new BalancePacket()); // labymod3 = gmde-balance
    super.registerPacket(11, new LevelPacket()); // labymod3 = gmde-level

    // 20-29 Vehicle Packets
    super.registerPacket(20, new VehicleDisplayPacket()); // labymod3 = gmde-vehicle-display
    super.registerPacket(21, new VehiclePositionPacket()); // labymod3 = gmde-vehicle-position
    super.registerPacket(22, new VehicleHotKeyPacket()); // labymod3 = gmde-vehicle-hotkey

    // 30-infinity: Special Packets
    super.registerPacket(30, new NotificationPacket()); // labymod3 = gmde-notification
    super.registerPacket(31, new InputPromptPacket()); // labymod3 = gmde-input-prompt

  }

}
