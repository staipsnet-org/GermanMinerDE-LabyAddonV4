package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.controller.VehicleController;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class VehiclePositionPacketHandler implements PacketHandler<VehiclePositionPacket> {

  @Override
  public void handle(final VehiclePositionPacket packet) {
    final VehicleController controller = GermanMinerAddon.getInstance().getVehicleController();

    if (controller != null) {
      controller.fixVehicles(packet.getVehicles());
    }
  }

}
