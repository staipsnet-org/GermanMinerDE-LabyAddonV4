package de.germanminer.addon.widgets.ontime;

import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.widget.OntimePacket;
import javax.swing.*;

public class OntimeWidget extends TextHudWidget<TextHudWidgetConfig> implements PacketHandler<OntimePacket> {

  private final Icon hudIcon;
  private TextLine;

  public OntimeWidget(String id) {
    super(id);
  }

  @Override
  public void handle(OntimePacket packet) {

  }
}