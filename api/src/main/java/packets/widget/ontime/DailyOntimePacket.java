package packets.widget.ontime;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Ontimesystem
 */
public class DailyOntimePacket implements GermanMinerPacket {

  @SerializedName("daily_ontime")
  private String dailyOntime;

  public DailyOntimePacket() {
  }

  public DailyOntimePacket(String dailyOntime) {
    this.dailyOntime = dailyOntime;
  }

  public String getDailyOntime() {
    return dailyOntime;
  }
}