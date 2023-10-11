package de.germanminer.addon.api.protocol.packet.level;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class LevelPacket implements GermanMinerPacket {

  @SerializedName("current_level")
  private Integer currentLevel;

  public LevelPacket() {
  }

  public LevelPacket(final Integer currentLevel) {
    this.currentLevel = currentLevel;
  }

  public Integer getCurrentLevel() {
    return this.currentLevel;
  }

}
