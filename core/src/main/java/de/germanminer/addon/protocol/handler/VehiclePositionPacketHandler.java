package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.controller.VehicleController;
import java.util.UUID;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;

public class VehiclePositionPacketHandler implements PacketHandler<VehiclePositionPacket> {

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final VehiclePositionPacket packet) {
    final VehicleController controller = GermanMinerAddon.getInstance().getVehicleController();

    if (controller != null) {
      controller.fixVehicles(packet.getVehicles());
    }
  }

}
