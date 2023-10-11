package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.miscellaneous.PowerUpPacket;
import de.germanminer.addon.widgets.WidgetIcon;
import de.germanminer.addon.widgets.WidgetRegistry;
import java.time.Duration;
import java.time.LocalDateTime;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.util.I18n;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;

public class PowerUpWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<PowerUpPacket>, WidgetRegistry<PowerUpPacket> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private LocalDateTime delay;
  private int seconds = 0;

  private TextLine coolDown;
  private String coolDownValue;

  public PowerUpWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.MISCELLANEOUS_POWER_UP.getIcon();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.coolDown = super.createLine(
      Component.translatable(String.format("germanminer.hudWidget.%s.name", super.getId())),
      this.coolDownValue == null ? Component.translatable(String.format("germanminer.hudWidget.%s.empty", super.getId()))
        : Component.text(this.coolDownValue));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.addon.configuration().miscellaneousConfig().powerUp().get();
  }

  @Override
  public void handle(final PowerUpPacket packet) {
    if (packet.getCoolDown() != null) {
      this.delay = LocalDateTime.parse(packet.getCoolDown());
    }
  }

  @Subscribe
  public void onTick(final boolean isEditorContext) {
    super.onTick(isEditorContext);

    if (this.delay == null) {
      return;
    }

    final int remainingSeconds = Math.toIntExact(Duration.between(LocalDateTime.now(), this.delay).toSeconds() - 1L);

    if (this.seconds == remainingSeconds) {
      return;
    }

    if (remainingSeconds < 1) {
      this.coolDownValue = I18n.translate(String.format("germanminer.hudWidget.%s.empty", super.getId()));
      this.coolDown.updateAndFlush(this.coolDownValue);
      this.seconds = 0;
      return;
    }

    this.coolDownValue = I18n.translate(String.format("germanminer.hudWidget.%s.remaining",
        super.getId()), remainingSeconds);
    this.coolDown.updateAndFlush(this.coolDownValue);
    this.seconds = remainingSeconds;
  }

  @Override
  public void register(final HudWidgetRegistry registry, final ProtocolService protocol,
      final Class<PowerUpPacket> packetClass) {
    registry.register(this);
    protocol.registerPacketHandler(packetClass, this);
  }

}
