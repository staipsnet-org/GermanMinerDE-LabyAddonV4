package de.germanminer.addon.protocol.translation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import net.labymod.serverapi.protocol.packet.Packet;
import net.labymod.serverapi.protocol.payload.exception.PayloadException;
import net.labymod.serverapi.protocol.payload.exception.PayloadReaderException;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import net.labymod.serverapi.protocol.payload.io.PayloadReader;
import net.labymod.serverapi.protocol.payload.io.PayloadWriter;
import net.labymod.serverapi.protocol.payload.translation.AbstractPayloadTranslationListener;

public class GermanMinerPayloadTranslationListener extends AbstractPayloadTranslationListener {

  private static final Gson GSON = new Gson();

  private final Class<? extends GermanMinerPacket> clazz;
  private final String oldMessageKey;
  private final TranslationSide side;

  public GermanMinerPayloadTranslationListener(final Class<? extends GermanMinerPacket> clazz,
      final String oldMessageKey, final TranslationSide side) {
    super(
        PayloadChannelIdentifier.create("labymod3", "main"),
        PayloadChannelIdentifier.create("labymod", "germanminer")
    );
    this.clazz = clazz;
    this.oldMessageKey = oldMessageKey;
    this.side = side;
  }

  @Override
  public byte[] translateIncomingPayload(final byte[] payload) {
    final PayloadReader reader = new PayloadReader(payload);

    try {
      final String messageKey = reader.readString();

      if (!messageKey.equals(this.oldMessageKey)) {
        return null;
      }
    } catch (PayloadReaderException exception) {
      throw new PayloadException("No message key could be read from the buffer.", exception);
    }

    try {
      final String messageContent = reader.readString();
      return this.translateIncomingPayload(new JsonParser().parse(messageContent));
    } catch (PayloadReaderException exception) {
      throw new PayloadException("No message content could be read from the buffer.", exception);
    }
  }

  public byte[] translateIncomingPayload(final JsonElement messageContent) {
    if (this.side == TranslationSide.OUTGOING) {
      return new byte[0];
    }

    return this.writePacketBinary(GSON.fromJson(messageContent, this.clazz));
  }

  @Override
  public <T extends Packet> byte[] translateOutgoingPayload(final T packet) {
    if (packet.getClass() != this.clazz) {
      return null;
    }

    if (this.side == TranslationSide.INCOMING) {
      return new byte[0];
    }

    final PayloadWriter writer = new PayloadWriter();
    writer.writeString(this.oldMessageKey);
    writer.writeString(GSON.toJson(packet));
    return writer.toByteArray();
  }

}
