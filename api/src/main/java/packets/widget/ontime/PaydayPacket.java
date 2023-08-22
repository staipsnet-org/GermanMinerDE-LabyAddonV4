package protocol.packet.widget.ontime;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class PaydayPacket implements GermanMinerPacket {

  @SerializedName("next_payday")
  private String nextPayday;

  public PaydayPacket() {
  }

  public PaydayPacket(String nextPayday) {
    this.nextPayday = nextPayday;
  }

  public String getNextPayday() {
    return nextPayday;
  }
}