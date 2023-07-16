package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class TempVehicleDisplayPacketHandler implements PacketHandler<VehicleDisplayPacket> {

  @Override
  public void handle(final VehicleDisplayPacket packet) {
    showMessage(packet.getShow());
    showMessage(packet.getSpeed());
    showMessage(packet.getLimiterActive());
    showMessage(packet.getLimiterSpeed());
    showMessage(packet.getFuelPercent());
    showMessage(packet.getGearPosition());
    showMessage(packet.getFlightHeight());
    showMessage(packet.getEngineState());
    showMessage(packet.getDamageState());
    showMessage(packet.getNightMode());
  }

  private <T> void showMessage(final T type) {
    if (type == null) {
      return;
    }

    GermanMinerAddon.getInstance().displayMessage(type.toString());
  }

}
