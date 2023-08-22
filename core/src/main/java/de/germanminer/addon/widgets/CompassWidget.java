package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.CompassPacket;

public class CompassWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<CompassPacket> {

  private final Icon hudIcon;
  private TextLine compassPoint;

  public CompassWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/compass.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    compassPoint = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.notset", super.getId())));

    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(CompassPacket packet) {
    compassPoint.updateAndFlush(
        !packet.getTarget().equals("notset") && !packet.getDistance().equals("notset") ?
            packet.getTarget() + ", " + packet.getDistance() + " Meter"
            : Component.translatable(
                String.format("germanmineraddon.hudWidget.%s.notset", super.getId())));
  }
}