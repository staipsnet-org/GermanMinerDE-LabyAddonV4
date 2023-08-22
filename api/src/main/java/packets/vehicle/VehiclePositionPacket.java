package packets.vehicle;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;
import vehicle.Vehicle;

import java.util.List;

/**
 * Packet zum Verarbeiten der besseren Darstellung (Vehicles)
 */
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