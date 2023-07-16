package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.widget.LevelPacket;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class LevelWidget extends TextHudWidget<TextHudWidgetConfig> implements PacketHandler<LevelPacket> {

  private final Icon hudWidgetIcon;
  private TextLine currentLevel;
  private TextLine levelPoints;

  public LevelWidget(final String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getCategory());

    this.hudWidgetIcon = Icon.texture(Laby.references().resourceLocationFactory().createMinecraft(
        "textures/items/experience_bottle.png")).resolution(64, 64);
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.currentLevel = super.createLine(Component.translatable(String.format("germanminer.hudWidget.%s.level", super.getId())),
        Component.translatable("germanminer.hudWidget.loading"));
    this.levelPoints = super.createLine(Component.translatable(String.format("germanminer.hudWidget.%s.points", super.getId())),
        Component.translatable("germanminer.hudWidget.loading"));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(final LevelPacket packet) {
    this.currentLevel.updateAndFlush(packet.getCurrentLevel() != null ? packet.getCurrentLevel()
        : Component.translatable("germanminer.hudWidget.error"));

    final Integer points = packet.getLevelPoints();
    final Integer requiredPoints = packet.getRequiredLevelPoints();

    this.levelPoints.updateAndFlush(points != null && requiredPoints != null ? points + " / " + requiredPoints
        : Component.translatable("germanminer.hudWidget.error"));
  }

}
