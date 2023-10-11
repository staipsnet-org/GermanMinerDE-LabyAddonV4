package de.germanminer.addon.api.protocol.packet.miscellaneous;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class PowerUpPacket implements GermanMinerPacket {

  @SerializedName("cooldown")
  private String coolDown;

  public PowerUpPacket() {}

  public PowerUpPacket(final String coolDown) {
    this.coolDown = coolDown;
  }

  public String getCoolDown() {
    return this.coolDown;
  }

}
