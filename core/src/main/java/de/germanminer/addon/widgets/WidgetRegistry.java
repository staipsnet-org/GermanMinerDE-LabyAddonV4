package de.germanminer.addon.widgets;

import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;

public interface WidgetRegistry<T> {

  void register(final HudWidgetRegistry registry, final ProtocolService protocol, final Class<T> packetClass);

}
