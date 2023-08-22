package protocol.packet.widget.ontime;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class DutyOntimePacket implements GermanMinerPacket {

  @SerializedName("duty_ontime")
  private String dutyOntime;

  public DutyOntimePacket() {
  }

  public DutyOntimePacket(String dutyOntime) {
    this.dutyOntime = dutyOntime;
  }

  public String getDutyOntime() {
    return dutyOntime;
  }
}