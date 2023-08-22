package protocol.packet.widget.ontime;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class TotalOntimePacket implements GermanMinerPacket {

  @SerializedName("total_ontime")
  private String totalOntime;

  public TotalOntimePacket() {}

  public TotalOntimePacket(String totalOntime) {
    this.totalOntime = totalOntime;
  }

  public String getTotalOntime() {
    return totalOntime;
  }
}