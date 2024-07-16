package packets.vehicle;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;
import vehicle.HotKey;

public class VehicleHotKeyPacket implements GermanMinerPacket {

  @SerializedName("function")
  private HotKey hotKey;

  public VehicleHotKeyPacket() {
  }

  public VehicleHotKeyPacket(final HotKey hotKey) {
    this.hotKey = hotKey;
  }

  public HotKey getHotKey() {
    return this.hotKey;
  }

}