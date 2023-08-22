package packets.widget.ontime;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Ontimesystem
 */
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