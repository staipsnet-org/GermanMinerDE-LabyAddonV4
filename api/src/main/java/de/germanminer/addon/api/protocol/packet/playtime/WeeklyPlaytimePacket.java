package de.germanminer.addon.api.protocol.packet.playtime;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class WeeklyPlaytimePacket implements GermanMinerPacket {

  @SerializedName("weekly_ontime")
  private String weeklyPlaytime;

  public WeeklyPlaytimePacket() {
  }

  public WeeklyPlaytimePacket(final String weeklyPlaytime) {
    this.weeklyPlaytime = weeklyPlaytime;
  }

  public String getWeeklyPlaytime() {
    return this.weeklyPlaytime;
  }

}
