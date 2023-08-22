package de.germanminer.addon.widgets.ontime;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.ontime.TotalOntimePacket;

/**
 * Widget zum Anzeigen von der gesamten Spielzeit
 */
public class TotalOntimeWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<TotalOntimePacket> {

  private final Icon hudIcon;
  private TextLine totalOntime;

  public TotalOntimeWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon","textures/ontime.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    totalOntime = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));
    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .isOntimeEnabled() && GermanMinerAddon.getInstance().getSetting().getOntimeSetting()
        .isTotalOntimeEnabled();
  }

  @Override
  public void handle(TotalOntimePacket packet) {
    totalOntime.updateAndFlush(
        packet.getTotalOntime() != null ? packet.getTotalOntime()
            : Component.translatable("germanmineraddon.hudWidget.error")
    );
  }
}