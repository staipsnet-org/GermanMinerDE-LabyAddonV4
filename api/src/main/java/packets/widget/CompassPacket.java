package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Kompasssystem
 */
public class CompassPacket implements GermanMinerPacket {

  @SerializedName("compass_target")
  private String target;
  @SerializedName("compass_distance")
  private String distance;

  public CompassPacket(String target, String distance) {
    this.target = target;
    this.distance = distance;
  }

  public String getTarget() {
    return target;
  }

  public String getDistance() {
    return distance;
  }
}