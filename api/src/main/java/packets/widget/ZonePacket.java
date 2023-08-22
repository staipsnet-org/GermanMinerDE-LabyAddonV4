package protocol.packet.widget;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class ZonePacket implements GermanMinerPacket {

  @SerializedName("current_zone")
  private String currentZone;

  public ZonePacket() {
  }

  public ZonePacket(String currentZone) {
    this.currentZone = currentZone;
  }

  public String getCurrentZone() {
    return currentZone;
  }
}