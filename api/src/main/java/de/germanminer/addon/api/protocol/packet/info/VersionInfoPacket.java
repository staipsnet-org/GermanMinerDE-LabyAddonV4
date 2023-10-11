package de.germanminer.addon.api.protocol.packet.info;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import java.util.List;

@Deprecated
public class VersionInfoPacket implements GermanMinerPacket {

  private String version;
  private List<Addons> addons;

  public VersionInfoPacket() {
  }

  public VersionInfoPacket(final String version, final List<Addons> addons) {
    this.version = version;
    this.addons = addons;
  }

  public String getVersion() {
    return this.version;
  }

  public List<Addons> getAddons() {
    return this.addons;
  }

  public static class Addons {

    private final String uuid;
    private final String name;

    public Addons(final String uuid, final String name) {
      this.uuid = uuid;
      this.name = name;
    }

    public String getUuid() {
      return this.uuid;
    }

    public String getName() {
      return this.name;
    }

  }

}
