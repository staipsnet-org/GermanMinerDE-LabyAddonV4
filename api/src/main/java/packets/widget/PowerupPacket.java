package packets.widget;

import java.time.LocalDateTime;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Powerup-System
 */
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