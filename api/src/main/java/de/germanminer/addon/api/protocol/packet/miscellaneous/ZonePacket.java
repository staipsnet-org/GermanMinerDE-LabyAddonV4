package de.germanminer.addon.api.protocol.packet.miscellaneous;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class ZonePacket implements GermanMinerPacket {

  @SerializedName("current_zone")
  private String currentZone;

  public ZonePacket() {}

  public ZonePacket(final String currentZone) {
    this.currentZone = currentZone;
  }

  public String getCurrentZone() {
    return this.currentZone;
  }

}
