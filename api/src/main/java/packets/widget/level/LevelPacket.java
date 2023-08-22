package protocol.packet.widget.level;

import com.google.gson.annotations.SerializedName;
import protocol.packet.GermanMinerPacket;

public class LevelPacket implements GermanMinerPacket {

  @SerializedName("current_level")
  private Integer currentLevel;

  public LevelPacket() {
  }

  public LevelPacket(Integer currentLevel) {
    this.currentLevel = currentLevel;
  }

  public Integer getCurrentLevel() {
    return currentLevel;
  }
}