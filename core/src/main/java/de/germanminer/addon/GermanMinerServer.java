package de.germanminer.addon;

import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import java.util.Arrays;
import java.util.List;
import net.labymod.api.client.network.server.AbstractServer;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.event.Phase;

public class GermanMinerServer extends AbstractServer {

  // ToDo: remove localhost
  private static final List<ServerAddress> SERVER_ADDRESSES = Arrays.asList(
      ServerAddress.parse("mc.germanminer.de"),
      ServerAddress.parse("germanminer.de"),
      ServerAddress.parse("testserver.germanminer.de:26510"),
      ServerAddress.parse("bauserver.germanminer.de"),
      ServerAddress.parse("51.77.73.236:25565"),
      ServerAddress.parse("51.89.46.236:26510"),
      ServerAddress.parse("51.89.46.236:26511"),
      ServerAddress.parse("51.89.46.236:26512"),
      ServerAddress.parse("localhost")
  );

  private final GermanMinerAddon addon;

  public GermanMinerServer(final GermanMinerAddon addon) {
    super("germanminer");
    this.addon = addon;
  }

  @Override
  public void loginOrSwitch(final LoginPhase phase) {
    this.addon.setOnline(true);
    this.addon.sendPacket(new AddonInfoPacket(GermanMinerAddon.getVersion()));
  }

  @Override
  public void disconnect(final Phase phase) {
    this.addon.setOnline(false);
  }

  @Override
  public boolean isServer(final ServerAddress address) {
    if (address == null) {
      return false;
    }

    final String host = address.getHost();

    if (host == null) {
      return false;
    }

    for (final ServerAddress addresses : SERVER_ADDRESSES) {
      if (address.matches(addresses)) {
        return true;
      }
    }

    return false;
  }

}
