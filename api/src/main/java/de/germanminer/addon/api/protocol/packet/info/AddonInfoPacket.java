package de.germanminer.addon.api.protocol.packet.info;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class AddonInfoPacket implements GermanMinerPacket {

  private int version;

  public AddonInfoPacket() {
  }

  public AddonInfoPacket(final int version) {
    this.version = version;
  }

  public int getVersion() {
    return this.version;
  }

}
