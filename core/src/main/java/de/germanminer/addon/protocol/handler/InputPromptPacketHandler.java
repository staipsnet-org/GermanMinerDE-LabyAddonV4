package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.GermanMinerAddon;
import java.util.UUID;
import de.germanminer.addon.widgets.miscellaneous.InputPromptActivity;
import net.labymod.api.Laby;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;
import packets.special.InputPromptPacket;

public class InputPromptPacketHandler implements PacketHandler<InputPromptPacket> {

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final InputPromptPacket packet) {
    if (packet.getMessage() != null && packet.getButtonSubmit() != null && packet.getButtonCancel() != null) {
      final InputPromptActivity activity = new InputPromptActivity(packet,
          value -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(value)),
          exit -> GermanMinerAddon.getInstance().sendPacket(new InputPromptPacket(exit)));

      Laby.labyAPI().minecraft().minecraftWindow().displayScreen(activity);
    }
  }

}