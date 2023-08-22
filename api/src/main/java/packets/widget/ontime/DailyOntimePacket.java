package protocol.packet.widget.ontime;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

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