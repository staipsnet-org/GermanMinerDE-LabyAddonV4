package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.controller.VehicleController;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.vehicle.VehiclePositionPacket;

public class VehiclePositionPacketHandler implements PacketHandler<VehiclePositionPacket> {

  @Override
  public void handle(VehiclePositionPacket packet) {
    VehicleController vehicleController = GermanMinerAddon.getInstance().getVehicleController();

    if (vehicleController != null)
      vehicleController.fixVehicles(packet.getVehicles());
  }
}