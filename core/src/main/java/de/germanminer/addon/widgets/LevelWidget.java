package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.widget.LevelPacket;

public class LevelWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<LevelPacket> {

  private final Icon hudIcon;
  private TextLine currentLevel;
  private TextLine levelPoints;

  public LevelWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(Laby.references().resourceLocationFactory()
        .createMinecraft("textures/items/experience_bottle.png")).resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    this.currentLevel = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.level", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));
    this.levelPoints = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.points", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(LevelPacket packet) {
    this.currentLevel.updateAndFlush(packet.getCurrentLevel() != null ? packet.getCurrentLevel()
        : Component.translatable("germanmineraddon.hudWidget.error"));
    Integer points = packet.getLevelPoints();
    Integer requiredPoints = packet.getRequiredLevelPoints();

    this.levelPoints.updateAndFlush(
        points != null && requiredPoints != null ? points + " / " + requiredPoints
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}
