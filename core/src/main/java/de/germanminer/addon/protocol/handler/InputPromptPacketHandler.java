package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import de.germanminer.addon.controller.InputPromptController;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class InputPromptPacketHandler implements PacketHandler<InputPromptPacket> {

  @Override
  public void handle(final InputPromptPacket packet) {
    final InputPromptController controller = GermanMinerAddon.getInstance().getInputPromptController();

    if (controller != null && packet.getMessage() != null
        && packet.getButtonSubmit() != null && packet.getButtonCancel() != null) {
      controller.openInputPrompt(packet.getMessage(), packet.getValue(), packet.getButtonSubmit(), packet.getButtonCancel());
    }
  }

}
