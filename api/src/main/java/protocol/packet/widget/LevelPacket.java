package protocol.packet.widget;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class LevelPacket implements GermanMinerPacket {

  @SerializedName("current_level")
  private Integer currentLevel;

  @SerializedName("level_points")
  private Integer levelPoints;

  @SerializedName("required_level_points")
  private Integer requiredLevelPoints;

  public LevelPacket() {}

  public LevelPacket(Integer currentLevel, Integer levelPoints, Integer requiredLevelPoints) {
    this.currentLevel = currentLevel;
    this.levelPoints = levelPoints;
    this.requiredLevelPoints = requiredLevelPoints;
  }

  public Integer getCurrentLevel() {
    return currentLevel;
  }

  public Integer getLevelPoints() {
    return levelPoints;
  }

  public Integer getRequiredLevelPoints() {
    return requiredLevelPoints;
  }

  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  public void setLevelPoints(int levelPoints) {
    this.levelPoints = levelPoints;
  }

  public void setRequiredLevelPoints(int requiredLevelPoints) {
    this.requiredLevelPoints = requiredLevelPoints;
  }
}