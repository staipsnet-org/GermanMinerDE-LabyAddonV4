package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.activities.InputPromptActivity;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import net.labymod.api.Laby;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class InputPromptPacketHandler implements PacketHandler<InputPromptPacket> {

  @Override
  public void handle(final InputPromptPacket packet) {
    if (packet.getMessage() != null && packet.getButtonSubmit() != null && packet.getButtonCancel() != null) {
      final InputPromptActivity activity = new InputPromptActivity(packet,
          value -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(value)),
          exit -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(exit)));

      Laby.labyAPI().minecraft().minecraftWindow().displayScreen(activity);

    }
  }

}
