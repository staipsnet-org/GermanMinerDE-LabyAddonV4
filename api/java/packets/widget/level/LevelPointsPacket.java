package packets.widget.level;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Levelsystem
 */
public class LevelPointsPacket implements GermanMinerPacket {

  @SerializedName("level_points")
  private Integer levelPoints;
  @SerializedName("required_level_points")
  private Integer requiredLevelPoints;

  public LevelPointsPacket() {
  }

  public LevelPointsPacket(Integer levelPoints, Integer requiredLevelPoints) {
    this.levelPoints = levelPoints;
    this.requiredLevelPoints = requiredLevelPoints;
  }

  public Integer getLevelPoints() {
    return levelPoints;
  }

  public Integer getRequiredLevelPoints() {
    return requiredLevelPoints;
  }
}