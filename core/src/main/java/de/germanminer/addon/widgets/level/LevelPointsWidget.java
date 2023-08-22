package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.widget.level.LevelPacket;
import protocol.packet.widget.level.LevelPointsPacket;

public class LevelPointsWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<LevelPointsPacket> {

  private final Icon hudIcon;
  private TextLine levelpoints;

  public LevelPointsWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(Laby.references().resourceLocationFactory()
        .createMinecraft("textures/items/experience_bottle.png")).resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    this.levelpoints = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.points", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(LevelPointsPacket packet) {
    Integer points = packet.getLevelPoints();
    Integer requiredPoints = packet.getRequiredLevelPoints();

    this.levelpoints.updateAndFlush(
        points != null && requiredPoints != null ? points + " / " + requiredPoints
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}