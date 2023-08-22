package packets.info;

import com.google.gson.JsonArray;
import packets.GermanMinerPacket;

/**
 * Packet zum Senden der aktuellen Version + Addons an den Server
 */
@Deprecated // ToDo: Mal entfernen, wenn Labymod 3 sich langsam dem Ende neigt
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