package packets.info;

import org.jetbrains.annotations.NotNull;
import packets.GermanMinerPacket;

import java.util.Objects;

/**
 * Packet zum Senden der aktuellen Labymod Version an den Server
 */
public class AddonInfoPacket implements GermanMinerPacket {

  private final Integer addonVersion;

  public AddonInfoPacket(@NotNull Integer addonVersion) {
    Objects.requireNonNull(addonVersion, "addonVersion cannot be null");
    this.addonVersion = addonVersion;
  }

  public @NotNull Integer addonVersion() {
    return addonVersion;
  }

  @Override
  public String toString() {
    return "AddonInfoPacket{"
        + "version=" + this.addonVersion +
        "} " + super.toString();
  }
}