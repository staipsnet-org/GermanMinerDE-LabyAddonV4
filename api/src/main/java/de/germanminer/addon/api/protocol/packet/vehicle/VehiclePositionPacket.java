package de.germanminer.addon.api.protocol.packet.vehicle;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.vehicle.Vehicle;
import java.util.List;

public class VehiclePositionPacket implements GermanMinerPacket {

  @SerializedName("entities")
  private List<Vehicle> vehicles;

  public VehiclePositionPacket() {
  }

  public VehiclePositionPacket(final List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public List<Vehicle> getVehicles() {
    return this.vehicles;
  }

}
