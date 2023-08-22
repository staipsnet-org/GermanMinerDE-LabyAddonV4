package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.ZonePacket;

public class ZoneWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<ZonePacket> {

  private final Icon hudIcon;
  private TextLine currentZone;

  public ZoneWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/location.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    currentZone = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(this.hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() &&
        GermanMinerAddon.getInstance().getSetting().getSubSettings().isZoneEnabled();
  }

  @Override
  public void handle(ZonePacket packet) {
    currentZone.updateAndFlush(
        packet.getCurrentZone() != null ? packet.getCurrentZone()
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}