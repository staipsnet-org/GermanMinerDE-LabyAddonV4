package packets.widget.level;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Levelsystem
 */
public class LevelPacket implements GermanMinerPacket {

  @SerializedName("current_level")
  private final int currentLevel;

  public LevelPacket(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  public Integer getCurrentLevel() {
    return currentLevel;
  }
}