package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

public class WantedPacket implements GermanMinerPacket {

  @SerializedName("wanted_points")
  private final String wantedPoints;
  @SerializedName("wanted_time")
  private final String remainingWantedTime;

  public WantedPacket(String wantedPoints, String remainingWantedTime) {
    this.wantedPoints = wantedPoints;
    this.remainingWantedTime = remainingWantedTime;
  }

  public String getWantedPoints() {
    return wantedPoints;
  }

  public String getRemainingWantedTime() {
    return remainingWantedTime;
  }
}