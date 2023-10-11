package de.germanminer.addon;

import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import de.germanminer.addon.api.protocol.packet.info.VersionInfoPacket;
import de.germanminer.addon.api.protocol.packet.info.VersionInfoPacket.Addons;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.labymod.api.client.network.server.AbstractServer;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.event.Phase;

public class GermanMinerServer extends AbstractServer {

  private static final List<ServerAddress> SERVER_ADDRESSES = Arrays.asList(
      ServerAddress.parse("mc.germanminer.de"),
      ServerAddress.parse("germanminer.de"),
      ServerAddress.parse("testserver.germanminer.de:26511"),
      ServerAddress.parse("testserver.germanminer.de:26512"),
      ServerAddress.parse("testserver.germanminer.de:26513"),
      ServerAddress.parse("bauserver.germanminer.de"),
      ServerAddress.parse("51.77.73.236:25565"),
      ServerAddress.parse("51.89.46.236:26511"),
      ServerAddress.parse("51.89.46.236:26512"),
      ServerAddress.parse("51.89.46.236:26513"),
      ServerAddress.parse("mojang.broke-it.net")
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
    this.addon.sendPacket(new VersionInfoPacket(this.addon.labyAPI().getVersion(),
        Collections.singletonList(new Addons("601ff6ce-1b2c-49ed-ba9a-c09ae49b95ac", "GermanMinerDE"))));
    this.addon.getVehicleWidget().sendInfo();
  }

  @Override
  public void disconnect(final Phase phase) {
    this.addon.setOnline(false);
  }

  @Override
  public boolean isServer(final ServerAddress address) {
    if (address == null || address.getHost() == null) {
      return false;
    }

    return SERVER_ADDRESSES.stream().anyMatch(address::matches);
  }

}
