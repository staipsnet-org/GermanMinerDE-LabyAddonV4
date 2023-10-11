package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;

public abstract class GermanMinerWidget<P extends GermanMinerPacket>
    extends TextHudWidget<TextHudWidgetConfig> implements PacketHandler<P>, WidgetRegistry<P> {

  protected final GermanMinerAddon addon;
  protected final Icon hudWidgetIcon;
  protected final VisibilityChecker visibilityChecker;
  protected final ValueContainer<P> valueContainer;
  protected TextLine textLine;
  protected String value;

  protected GermanMinerWidget(final GermanMinerAddon addon, final String id, final WidgetIcon icon,
      final VisibilityChecker visibilityChecker, final ValueContainer<P> valueContainer) {
    super(id);
    super.bindCategory(addon.getCategory());

    this.addon = addon;
    this.hudWidgetIcon = icon.getIcon();
    this.visibilityChecker = visibilityChecker;
    this.valueContainer = valueContainer;
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.load();

    super.setIcon(this.hudWidgetIcon);
  }

  public void load() {
    this.textLine = super.createLine(
        Component.translatable(String.format("germanminer.hudWidget.%s.name", super.getId())),
        this.value == null ? Component.translatable("germanminer.hudWidget.loading")
            : Component.text(this.value));
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.visibilityChecker.isVisible();
  }

  @Override
  public void handle(final P packet) {
    if (this.valueContainer.getValue(packet) != null) {
      this.value = this.valueContainer.getValue(packet);
      this.textLine.updateAndFlush(this.value);
    }
  }

  @Override
  public void register(final HudWidgetRegistry registry, final ProtocolService protocol, final Class<P> packetClass) {
    registry.register(this);
    protocol.registerPacketHandler(packetClass, this);
  }

  protected interface VisibilityChecker {

    boolean isVisible();

  }

  protected interface ValueContainer<T extends GermanMinerPacket> {

    String getValue(final T packet);

  }

}
