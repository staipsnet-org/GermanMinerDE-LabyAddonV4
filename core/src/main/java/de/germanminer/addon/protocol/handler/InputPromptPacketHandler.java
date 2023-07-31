package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.controller.InputPromptController;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.special.InputPromptPacket;

public class InputPromptPacketHandler implements PacketHandler<InputPromptPacket> {

  @Override
  public void handle(InputPromptPacket packet) {
    InputPromptController inputPromptController = GermanMinerAddon.getInstance().getInputPromptController();

    if (inputPromptController != null && packet.getMessage() != null && packet.getButtonSubmit() != null && packet.getButtonCancel() != null)
      inputPromptController.openInputPrompt(packet.getMessage(), packet.getValue(), packet.getButtonSubmit(), packet.getButtonCancel());

  }
}