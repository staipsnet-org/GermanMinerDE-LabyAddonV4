package protocol.packet.info;

import protocol.packet.GermanMinerPacket;

public class AddonInfoPacket implements GermanMinerPacket {

  private int version;

  public AddonInfoPacket() {
  }

  public AddonInfoPacket(int version) {
    this.version = version;
  }
}