package de.germanminer.addon.api.protocol.packet.playtime;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class PaydayPacket implements GermanMinerPacket {

  @SerializedName("next_payday")
  private String nextPayday;

  public PaydayPacket() {
  }

  public PaydayPacket(final String nextPayday) {
    this.nextPayday = nextPayday;
  }

  public String getNextPayday() {
    return this.nextPayday;
  }

}
