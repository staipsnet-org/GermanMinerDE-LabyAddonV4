package de.germanminer.addon.api.protocol.packet.playtime;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class DutyPlaytimePacket implements GermanMinerPacket {

  @SerializedName("duty_ontime")
  private String dutyPlaytime;

  public DutyPlaytimePacket() {
  }

  public DutyPlaytimePacket(final String dutyPlaytime) {
    this.dutyPlaytime = dutyPlaytime;
  }

  public String getDutyPlaytime() {
    return this.dutyPlaytime;
  }

}
