package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.miscellaneous.PowerUpPacket;
import de.germanminer.addon.widgets.WidgetIcon;
import de.germanminer.addon.widgets.WidgetRegistry;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.util.I18n;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;

public class PowerUpWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<PowerUpPacket>, WidgetRegistry<PowerUpPacket> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private final List<PowerUpDelay> delays;

  public PowerUpWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.MISCELLANEOUS_POWER_UP.getIcon();
    this.delays = new ArrayList<>();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.delays.add(new PowerUpDelay(this, null));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.addon.configuration().miscellaneousConfig().powerUp().get();
  }

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final PowerUpPacket packet) {
    if (packet.getCoolDown() != null) {
      if (this.delays.get(0).isActive()) {
        this.delays.add(new PowerUpDelay(this, packet.getCoolDown()));
        return;
      }

      this.delays.get(0).setDelay(packet.getCoolDown());
    }
  }

  @Subscribe
  public void onTick(final boolean isEditorContext) {
    super.onTick(isEditorContext);

    this.delays.forEach(PowerUpDelay::onTick);
  }

  @Override
  public void register(final HudWidgetRegistry registry, final Protocol protocol,
      final Class<PowerUpPacket> packetClass) {
    registry.register(this);
    protocol.registerHandler(packetClass, this);
  }

  static class PowerUpDelay {

    private final PowerUpWidget widget;
    private LocalDateTime delay;
    private int seconds = 0;
    private final TextLine coolDown;
    private String coolDownValue;

    public PowerUpDelay(final PowerUpWidget widget, final String coolDown) {
      this.widget = widget;
      this.delay = coolDown == null ? null : LocalDateTime.parse(coolDown);
      this.coolDown = this.widget.createLine(
          Component.translatable(String.format("germanminer.hudWidget.%s.name", this.widget.getId())),
          this.coolDownValue == null ? Component.translatable(String.format("germanminer.hudWidget.%s.empty", this.widget.getId()))
              : Component.text(this.coolDownValue));
    }

    public void onTick() {
      if (this.delay == null) {
        return;
      }

      final int remainingSeconds = Math.toIntExact(Duration.between(LocalDateTime.now(), this.delay).toSeconds() - 1L);

      if (this.seconds == remainingSeconds) {
        return;
      }

      if (remainingSeconds < 1) {
        this.coolDownValue = I18n.translate(String.format("germanminer.hudWidget.%s.empty", this.widget.getId()));
        this.coolDown.updateAndFlush(this.coolDownValue);
        this.seconds = 0;

        if (this.widget.delays.size() != 1) {
          this.widget.delays.remove(this);
          remove();
        }

        return;
      }

      this.coolDownValue = I18n.translate(String.format("germanminer.hudWidget.%s.remaining",
          this.widget.getId()), remainingSeconds);
      this.coolDown.updateAndFlush(this.coolDownValue);
      this.seconds = remainingSeconds;
    }

    public void remove() {
      this.widget.lines.remove(this.coolDown);
    }

    public boolean isActive() {
      return this.seconds > 0;
    }

    public void setDelay(final String coolDown) {
      this.delay = coolDown == null ? null : LocalDateTime.parse(coolDown);
    }

  }

}
