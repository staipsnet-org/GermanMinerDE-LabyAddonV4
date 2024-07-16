package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import java.util.UUID;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;
import packets.GermanMinerPacket;

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
    super.bindCategory(addon.getHudWidgetCategory());

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
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        this.value == null ? Component.translatable("germanmineraddon.hudWidget.loading")
            : Component.text(this.value));
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.visibilityChecker.isVisible();
  }

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final P packet) {
    if (this.valueContainer.getValue(packet) != null) {
      this.value = this.valueContainer.getValue(packet);
      this.textLine.updateAndFlush(this.value);
    }
  }

  @Override
  public void register(final HudWidgetRegistry registry, final Protocol protocol, final Class<P> packetClass) {
    registry.register(this);
    protocol.registerHandler(packetClass, this);
  }

  protected interface VisibilityChecker {

    boolean isVisible();

  }

  protected interface ValueContainer<T extends GermanMinerPacket> {

    String getValue(final T packet);

  }

}