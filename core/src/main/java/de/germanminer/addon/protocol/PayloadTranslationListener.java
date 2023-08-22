package de.germanminer.addon.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.labymod.serverapi.protocol.packet.Packet;
import net.labymod.serverapi.protocol.payload.exception.PayloadReaderException;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import net.labymod.serverapi.protocol.payload.io.PayloadReader;
import net.labymod.serverapi.protocol.payload.io.PayloadWriter;
import net.labymod.serverapi.protocol.payload.translation.AbstractPayloadTranslationListener;
import packets.GermanMinerPacket;

/**
 * Listener um Packets zu verarbeiten
 */
public class GermanMinerPayloadTranslationListener extends AbstractPayloadTranslationListener {

  private static final Gson GSON = new Gson();

  private final Class<? extends GermanMinerPacket> clazz;
  private final String oldMessageKey;
  private final TranslationSide translationSide;

  public GermanMinerPayloadTranslationListener(Class<? extends GermanMinerPacket> clazz, String oldMessageKey, TranslationSide translationSide) {
    super(PayloadChannelIdentifier.create("labymod3", "main"), PayloadChannelIdentifier.create("labymod", "germanminer"));
    this.clazz = clazz;
    this.oldMessageKey = oldMessageKey;
    this.translationSide = translationSide;
  }

  @Override
  public byte[] translateIncomingPayload(byte[] payload) {
    PayloadReader payloadReader = new PayloadReader(payload);

    try {
      String messageKey = payloadReader.readString();

      if (!messageKey.equals(oldMessageKey)) {
        return null;
      }

    } catch (PayloadReaderException e) {
      throw new PayloadReaderException("", e); // Todo Hurensohn
    }

    try {
      String messageContent = payloadReader.readString();
      return translateIncomingPayload(new JsonParser().parse(messageContent));
    } catch (PayloadReaderException e) {
      throw new PayloadReaderException("", e); // Todo Hurensohn
    }
  }

  @Override
  public <T extends Packet> byte[] translateOutgoingPayload(T packet) {
    if (packet.getClass() != this.clazz)
      return null;

    if (translationSide == TranslationSide.INCOMING)
      return new byte[0];

    PayloadWriter payloadWriter = new PayloadWriter();
    payloadWriter.writeString(this.oldMessageKey);
    payloadWriter.writeString(GSON.toJson(packet));
    return payloadWriter.toByteArray();
  }

  public byte[] translateIncomingPayload(JsonElement jsonElement) {
    if (this.translationSide == TranslationSide.OUTGOING)
      return new byte[0];

    return writePacketBinary(GSON.fromJson(jsonElement, this.clazz));
  }
}
