package protocol.packet.vehicle;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;
import vehicle.HotKey;

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