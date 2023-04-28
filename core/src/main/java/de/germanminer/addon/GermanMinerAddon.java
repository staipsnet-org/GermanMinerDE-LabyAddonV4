package de.germanminer.addon;

import de.germanminer.addon.commands.TestCommand;
import de.germanminer.addon.listener.PacketListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class GermanMinerAddon extends LabyAddon<GermanMinerConfiguration> {

  private static GermanMinerAddon instance;

  @Override
  protected void enable() {
    instance = this;
    this.registerCommand(new TestCommand());
    this.registerListener(new PacketListener());
  }

  @Override
  protected Class<GermanMinerConfiguration> configurationClass() {
    return GermanMinerConfiguration.class;
  }

  public static GermanMinerAddon getInstance() {
    return instance;
  }
}