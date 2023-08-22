package packets.info;

import packets.GermanMinerPacket;

/**
 * Packet zum Senden der aktuellen Labymod Version an den Server
 */
public class AddonInfoPacket implements GermanMinerPacket {

  private int version;

  public AddonInfoPacket() {
  }

  public AddonInfoPacket(int version) {
    this.version = version;
  }
}