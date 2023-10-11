package de.germanminer.addon.api.protocol.packet.level;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class LevelPointsPacket implements GermanMinerPacket {

  @SerializedName("level_points")
  private Integer levelPoints;
  @SerializedName("required_level_points")
  private Integer requiredLevelPoints;

  public LevelPointsPacket() {
  }

  public LevelPointsPacket(final Integer levelPoints, final Integer requiredLevelPoints) {
    this.levelPoints = levelPoints;
    this.requiredLevelPoints = requiredLevelPoints;
  }

  public Integer getLevelPoints() {
    return this.levelPoints;
  }

  public Integer getRequiredLevelPoints() {
    return this.requiredLevelPoints;
  }

}
