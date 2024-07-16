package de.germanminer.addon.protocol.translation;

import com.google.gson.JsonElement;
import net.labymod.api.serverapi.KeyedTranslationListener;
import net.labymod.serverapi.api.packet.Direction;
import net.labymod.serverapi.api.packet.Packet;
import packets.GermanMinerPacket;

public class GermanMinerTranslationListener extends KeyedTranslationListener {

  private final Class<? extends GermanMinerPacket> clazz;
  private final Direction direction;

  public GermanMinerTranslationListener(final Class<? extends GermanMinerPacket> clazz,
      final String oldMessageKey, final Direction direction) {
    super(oldMessageKey);
    this.clazz = clazz;
    this.direction = direction;
  }

  @Override
  protected Packet translateIncomingMessage(final JsonElement jsonElement) {
    if (this.direction == Direction.SERVERBOUND) {
      return null;
    }

    System.out.println(jsonElement.toString());
    return super.gson.fromJson(jsonElement, this.clazz);
  }

  @Override
  protected JsonElement translateOutgoingMessage(final Packet packet) {
    if (packet.getClass() != this.clazz) {
      return null;
    }

    if (this.direction == Direction.CLIENTBOUND) {
      return null;
    }

    return super.gson.toJsonTree(packet);
  }

}