package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.InputPromptActivity;
import net.labymod.api.Laby;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.special.InputPromptPacket;

/**
 * Handler zum Verarbeiten des Eingabefeldes
 */
public class InputPromptPacketHandler implements PacketHandler<InputPromptPacket> {

  @Override
  public void handle(InputPromptPacket packet) {
    if (packet.getMessage() != null && packet.getButtonSubmit() != null
        && packet.getButtonCancel() != null) {
      InputPromptActivity inputPromptActivity = new InputPromptActivity(packet,
          value -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(value)),
          exit -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(exit)));

      Laby.labyAPI().minecraft().minecraftWindow().displayScreen(inputPromptActivity);
    }

  }
}