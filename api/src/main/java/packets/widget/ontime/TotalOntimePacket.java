package packets.widget.ontime;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Ontimesystem
 */
public class TotalOntimePacket implements GermanMinerPacket {

  @SerializedName("total_ontime")
  private String totalOntime;

  public TotalOntimePacket() {
  }

  public TotalOntimePacket(String totalOntime) {
    this.totalOntime = totalOntime;
  }

  public String getTotalOntime() {
    return totalOntime;
  }
}