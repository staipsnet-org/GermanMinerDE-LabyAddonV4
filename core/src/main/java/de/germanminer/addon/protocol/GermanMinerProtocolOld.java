package de.germanminer.addon.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import de.germanminer.addon.api.protocol.packet.special.NotificationPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleHotKeyPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.api.protocol.packet.widget.BalancePacket;
import de.germanminer.addon.api.protocol.packet.widget.LevelPacket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import net.labymod.serverapi.protocol.packet.Packet;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import net.labymod.serverapi.protocol.packet.protocol.execption.ProtocolException;
import net.labymod.serverapi.protocol.payload.io.PayloadReader;
import net.labymod.serverapi.protocol.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

@Deprecated(since = "3.8.0")
public class GermanMinerProtocolOld {

  private final Map<String, Class<? extends GermanMinerPacket>> packets;
  private final Gson gson;

  public GermanMinerProtocolOld() {
    this.packets = new HashMap<>();
    this.gson = new GsonBuilder().create();

    // Info Packets
    registerPacket("gmde-addon-info", AddonInfoPacket.class);

    // Widget Packets
    registerPacket("gmde-balance", BalancePacket.class);
    registerPacket("gmde-level", LevelPacket.class);

    // Vehicle Packets
    registerPacket("gmde-vehicle-display", VehicleDisplayPacket.class);
    registerPacket("gmde-vehicle-position", VehiclePositionPacket.class);
    registerPacket("gmde-vehicle-hotkey", VehicleHotKeyPacket.class);

    // Special Packets
    registerPacket("gmde-notification", NotificationPacket.class);
    registerPacket("gmde-input-prompt", InputPromptPacket.class);

  }

  public void registerPacket(final String id, final Class<? extends GermanMinerPacket> packet) {
    final Class<? extends GermanMinerPacket> anotherPacket = this.packets.get(id);

    if (anotherPacket != null) {
      throw new ProtocolException(String.format("The %s packet has already been registered with this %s ID.",
              anotherPacket.getName(), id));
    }

    this.packets.put(id, packet);
  }

  public byte[] writePacketToBinary(final @NotNull Packet packet) {
    final PayloadWriter writer = new PayloadWriter();

    final Optional<String> packetId = this.packets.entrySet().stream()
        .filter(entry -> entry.getValue() == packet.getClass()).map(Entry::getKey).findAny();

    if (packetId.isEmpty()) {
      return null;
    }

    writer.writeString(packetId.get());
    writer.writeString(this.gson.toJson(packet));

    return writer.toByteArray();
  }

}
