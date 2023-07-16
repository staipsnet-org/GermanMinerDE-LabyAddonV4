package de.germanminer.addon.api.protocol.packet.widget;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class LevelPacket implements GermanMinerPacket {

  @SerializedName("current_level")
  private Integer currentLevel;
  @SerializedName("level_points")
  private Integer levelPoints;
  @SerializedName("required_level_points")
  private Integer requiredLevelPoints;

  public LevelPacket() {
  }

  public LevelPacket(final Integer currentLevel, final Integer levelPoints, final Integer requiredLevelPoints) {
    this.currentLevel = currentLevel;
    this.levelPoints = levelPoints;
    this.requiredLevelPoints = requiredLevelPoints;
  }

  public Integer getCurrentLevel() {
    return this.currentLevel;
  }

  public Integer getLevelPoints() {
    return this.levelPoints;
  }

  public Integer getRequiredLevelPoints() {
    return this.requiredLevelPoints;
  }

}
