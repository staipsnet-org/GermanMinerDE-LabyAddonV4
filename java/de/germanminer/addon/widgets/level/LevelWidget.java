package de.germanminer.addon.widgets.level;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.level.LevelPacket;

/**
 * Widget zum Anzeigen vom aktuellen Level
 */
public class LevelWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<LevelPacket> {

  private final Icon hudIcon;
  private TextLine level;

  public LevelWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon","textures/level.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    this.level = super.createLine(
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
  public void handle(LevelPacket packet) {
    this.level.updateAndFlush(
        packet.getCurrentLevel() != null ? packet.getCurrentLevel()
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}
