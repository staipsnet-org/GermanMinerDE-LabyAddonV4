package packets.widget;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Powerup-System
 */
public class PowerupPacket implements GermanMinerPacket {

  private final String powerup;
  private final int cooldown;

  public PowerupPacket(String powerup, int cooldown) {
    this.powerup = powerup;
    this.cooldown = cooldown;
  }

  public String getPowerup() {
    return powerup;
  }

  public int getCooldown() {
    return cooldown;
  }
}