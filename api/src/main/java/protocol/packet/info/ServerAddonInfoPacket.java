package protocol.packet.info;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import protocol.packet.GermanMinerPacket;

public class ServerAddonInfoPacket implements GermanMinerPacket {

  private String version;
  private JsonArray addons;

  public ServerAddonInfoPacket() {

  }

  public ServerAddonInfoPacket(String version, JsonArray jsonObject) {
    this.version = version;
    this.addons = jsonObject;
  }
}