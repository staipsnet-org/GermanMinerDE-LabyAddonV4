package protocol.packet.info;

import protocol.packet.GermanMinerPacket;

public class AddonInfoPacket implements GermanMinerPacket {

  private int version;

  public AddonInfoPacket() {}

  public AddonInfoPacket(int version) {
    this.version = version;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}