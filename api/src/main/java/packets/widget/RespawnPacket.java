package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

public class RespawnPacket implements GermanMinerPacket {

  @SerializedName("respawn_time")
  private final String respawnTime;

  public RespawnPacket(String respawnTime) {
    this.respawnTime = respawnTime;
  }

  public String getRespawnTime() {
    return respawnTime;
  }
}