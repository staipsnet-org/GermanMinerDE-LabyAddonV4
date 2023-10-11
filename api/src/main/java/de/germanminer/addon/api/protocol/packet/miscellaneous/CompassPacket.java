package de.germanminer.addon.api.protocol.packet.miscellaneous;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class CompassPacket implements GermanMinerPacket {

  @SerializedName("compass_target")
  private String target;
  @SerializedName("compass_distance")
  private String distance;

  public CompassPacket() {}

  public CompassPacket(final String target, final String distance) {
    this.target = target;
    this.distance = distance;
  }

  public String getTarget() {
    return this.target;
  }

  public String getDistance() {
    return this.distance;
  }

}
