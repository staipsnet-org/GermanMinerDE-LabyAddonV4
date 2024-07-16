package de.germanminer.addon.widgets;

import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.serverapi.api.Protocol;

public interface WidgetRegistry<T> {

  void register(final HudWidgetRegistry registry, final Protocol protocol, final Class<T> packetClass);

}