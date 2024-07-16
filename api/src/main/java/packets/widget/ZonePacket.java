package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Ortungszonen-System
 */
public class ZonePacket implements GermanMinerPacket {

  @SerializedName("current_zone")
  private final String currentZone;

  public ZonePacket(String currentZone) {
    this.currentZone = currentZone;
  }

  public String getCurrentZone() {
    return currentZone;
  }
}