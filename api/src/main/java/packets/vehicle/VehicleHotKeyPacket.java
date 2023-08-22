package packets.vehicle;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;
import vehicle.HotKey;

/**
 * Packet zum Verarbeiten von Hotkeys (Vehicles)
 */
public class VehicleHotKeyPacket implements GermanMinerPacket {

  @SerializedName("function")
  private HotKey hotKey;

  public VehicleHotKeyPacket() {
  }

  public VehicleHotKeyPacket(HotKey hotKey) {
    this.hotKey = hotKey;
  }

  public HotKey getHotKey() {
    return hotKey;
  }
}