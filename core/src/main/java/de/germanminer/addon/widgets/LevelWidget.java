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
  private String currentLevelValue;
  private String levelPointsValue;

  public LevelWidget(final String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getCategory());

    this.hudWidgetIcon = Icon.texture(Laby.references().resourceLocationFactory().createMinecraft(
        "textures/items/experience_bottle.png")).resolution(64, 64);
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.currentLevel = super.createLine(
      Component.translatable(String.format("germanminer.hudWidget.%s.level", super.getId())),
      this.currentLevelValue == null ? Component.translatable("germanminer.hudWidget.loading")
        : Component.text(this.currentLevelValue));

    this.levelPoints = super.createLine(
      Component.translatable(String.format("germanminer.hudWidget.%s.points", super.getId())),
      this.levelPointsValue == null ? Component.translatable("germanminer.hudWidget.loading")
        : Component.text(this.levelPointsValue));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(final LevelPacket packet) {
    if (packet.getCurrentLevel() != null) {
      this.currentLevelValue = String.valueOf(packet.getCurrentLevel());
      this.currentLevel.updateAndFlush(this.currentLevelValue);
    }

    if (packet.getLevelPoints() != null && packet.getRequiredLevelPoints() != null) {
      final int points = packet.getLevelPoints();
      final int requiredPoints = packet.getRequiredLevelPoints();

      this.levelPointsValue = points + " / " + requiredPoints;
      this.levelPoints.updateAndFlush(this.levelPointsValue);
    }
  }

}
