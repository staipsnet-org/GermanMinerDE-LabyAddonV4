package de.germanminer.addon.api.protocol.packet.playtime;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class DailyPlaytimePacket implements GermanMinerPacket {

  @SerializedName("daily_ontime")
  private String dailyPlaytime;

  public DailyPlaytimePacket() {
  }

  public DailyPlaytimePacket(final String dailyPlaytime) {
    this.dailyPlaytime = dailyPlaytime;
  }

  public String getDailyPlaytime() {
    return this.dailyPlaytime;
  }

}
