package de.germanminer.addon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.api.Laby;
import net.labymod.api.client.network.server.AbstractServer;
import net.labymod.api.client.network.server.ServerAddress;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Phase;
import protocol.packet.GermanMinerPacket;
import protocol.packet.info.AddonInfoPacket;
import protocol.packet.info.ServerAddonInfoPacket;

import java.util.Arrays;
import java.util.List;

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
      ServerAddress.parse("51.89.46.236:26513")
  );

  private final GermanMinerAddon addon;

  public GermanMinerServer(GermanMinerAddon addon) {
    super("germanminer");
    this.addon = addon;
  }

  @Override
  public void loginOrSwitch(LoginPhase phase) {
    addon.setOnline(true);
    addon.sendPacket(new AddonInfoPacket(GermanMinerAddon.getVersion()));

    JsonArray addonsArray = new JsonArray();

    JsonObject addons = new JsonObject();
    addons.addProperty("uuid", "601ff6ce-1b2c-49ed-ba9a-c09ae49b95ac");
    addons.addProperty("name", "GermanMinerDE");

    addonsArray.add(addons);

    this.addon.sendPacket(new ServerAddonInfoPacket(Laby.labyAPI().getVersion(), addonsArray));
  }

  @Override
  public void disconnect(Phase phase) {
    addon.setOnline(false);
  }
}
