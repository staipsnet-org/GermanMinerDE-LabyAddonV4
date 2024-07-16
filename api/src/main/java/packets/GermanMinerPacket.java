package packets;

import net.labymod.api.util.GsonUtil;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

public interface GermanMinerPacket extends Packet {

  @Override
  default void write(@NotNull PayloadWriter writer) {
    writer.writeString(GsonUtil.DEFAULT_GSON.toJson(this));
  }
}