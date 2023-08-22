package protocol.packet.widget;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class CompassPacket implements GermanMinerPacket {

  @SerializedName("compass_target")
  private String target;
  @SerializedName("compass_distance")
  private String distance;

  public CompassPacket() {
  }

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