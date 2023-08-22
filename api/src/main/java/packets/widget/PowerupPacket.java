package protocol.packet.widget;

import java.time.LocalDateTime;
import protocol.packet.GermanMinerPacket;

public class PowerupPacket implements GermanMinerPacket {

  private String cooldown;

  public PowerupPacket() {
  }

  public PowerupPacket(String cooldown) {
    this.cooldown = cooldown;
  }

  public LocalDateTime getCooldown() {
    return LocalDateTime.parse(cooldown);
  }
}