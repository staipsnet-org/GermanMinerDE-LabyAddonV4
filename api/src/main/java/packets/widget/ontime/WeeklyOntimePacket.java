package protocol.packet.widget.ontime;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class WeeklyOntimePacket implements GermanMinerPacket {

  @SerializedName("weekly_ontime")
  private String weeklyOntime;

  public WeeklyOntimePacket() {
  }

  public WeeklyOntimePacket(String weeklyOntime) {
    this.weeklyOntime = weeklyOntime;
  }

  public String getWeeklyOntime() {
    return weeklyOntime;
  }
}