package packets.widget.ontime;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Ontimesystem
 */
public class WeeklyOntimePacket implements GermanMinerPacket {

  @SerializedName("weekly_ontime")
  private final String weeklyOntime;

  public WeeklyOntimePacket(String weeklyOntime) {
    this.weeklyOntime = weeklyOntime;
  }

  public String getWeeklyOntime() {
    return weeklyOntime;
  }
}