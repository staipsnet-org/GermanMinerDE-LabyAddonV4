package de.germanminer.addon.api.protocol.packet.playtime;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class TotalPlaytimePacket implements GermanMinerPacket {

  @SerializedName("total_ontime")
  private String totalPlaytime;

  public TotalPlaytimePacket() {
  }

  public TotalPlaytimePacket(final String totalPlaytime) {
    this.totalPlaytime = totalPlaytime;
  }

  public String getTotalPlaytime() {
    return this.totalPlaytime;
  }

}
