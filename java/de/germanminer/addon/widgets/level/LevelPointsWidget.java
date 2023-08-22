package de.germanminer.addon.widgets.level;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.level.LevelPointsPacket;

/**
 * Widget zum Anzeigen von Levelpunkten
 */
public class LevelPointsWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<LevelPointsPacket> {

  private final Icon hudIcon;
  private TextLine levelPoints;

  public LevelPointsWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon","textures/level.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    this.levelPoints = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .getSubSettings().isLevelEnabled();
  }

  @Override
  public void handle(LevelPointsPacket packet) {
    Integer points = packet.getLevelPoints();
    Integer requiredPoints = packet.getRequiredLevelPoints();

    this.levelPoints.updateAndFlush(
        points != null && requiredPoints != null ? points + " / " + requiredPoints
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}