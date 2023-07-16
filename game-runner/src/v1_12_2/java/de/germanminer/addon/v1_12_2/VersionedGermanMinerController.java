package de.germanminer.addon.v1_12_2;

import de.germanminer.addon.controller.GermanMinerController;
import io.netty.buffer.Unpooled;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;

@Deprecated
@Singleton
@Implements(GermanMinerController.class)
public class VersionedGermanMinerController extends GermanMinerController {

  @Inject
  public VersionedGermanMinerController() {
  }

  @Override
  public void sendPayload(final String channel, final byte[] payload) {
    final NetHandlerPlayClient connection = Minecraft.getMinecraft().getConnection();

    if (connection != null) {
      connection.sendPacket(new CPacketCustomPayload(channel, new PacketBuffer(Unpooled.wrappedBuffer(payload))));
    }
  }

}
