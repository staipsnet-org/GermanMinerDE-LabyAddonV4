package de.germanminer.addon.api.protocol.packet.vehicle;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.vehicle.HotKey;

public class VehicleHotKeyPacket implements GermanMinerPacket {

  @SerializedName("function")
  private HotKey hotKey;

  public VehicleHotKeyPacket() {
  }

  public VehicleHotKeyPacket(final HotKey hotKey) {
    this.hotKey = hotKey;
  }

  public HotKey getHotKey() {
    return this.hotKey;
  }

}
