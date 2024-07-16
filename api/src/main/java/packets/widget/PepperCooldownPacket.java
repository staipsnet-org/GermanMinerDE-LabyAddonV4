package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

public class PepperCooldownPacket implements GermanMinerPacket {

  @SerializedName("pepper_time")
  private final String pepperCooldownTime;

  public PepperCooldownPacket(String pepperCooldownTime) {
    this.pepperCooldownTime = pepperCooldownTime;
  }

  public String getPepperCooldownTime() {
    return pepperCooldownTime;
  }
}