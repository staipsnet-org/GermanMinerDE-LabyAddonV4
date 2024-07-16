package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.WidgetIcon;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import de.germanminer.addon.widgets.WidgetRegistry;
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
import packets.widget.PowerupPacket;

public class PowerupWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<PowerupPacket>, WidgetRegistry<PowerupPacket> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private final List<PowerUpDelay> delays;

  public PowerupWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getHudWidgetCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.MISCELLANEOUS_POWER_UP.getIcon();
    this.delays = new CopyOnWriteArrayList<>();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.delays.add(new PowerUpDelay(this,null, 0));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.addon.configuration().getSubSettings().isPowerUpEnabled();
  }

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final PowerupPacket packet) {
    if (packet.getPowerup() != null) {
      if (this.delays.get(0).isActive()) {
        this.delays.add(new PowerUpDelay(this, packet.getPowerup(), packet.getCooldown()));
        return;
      }

      this.delays.get(0).setPowerUp(packet.getPowerup());
    }
  }

  @Subscribe
  public void onTick(final boolean isEditorContext) {
    super.onTick(isEditorContext);

    this.delays.forEach(PowerUpDelay::onTick);
  }

  @Override
  public void register(final HudWidgetRegistry registry, final Protocol protocol,
      final Class<PowerupPacket> packetClass) {
    registry.register(this);
    protocol.registerHandler(packetClass, this);
  }

  static class PowerUpDelay {

    private final PowerupWidget widget;
    private int seconds;
    private final TextLine coolDown;
    private String coolDownValue;
    private String powerUpValue;

    public PowerUpDelay(final PowerupWidget widget, final String powerup, final int cooldown) {
      this.widget = widget;;
      this.seconds = (cooldown * 20);
      this.powerUpValue = (powerup == null ? "Unbekannt: " : powerup + ": " );
      this.coolDown = this.widget.createLine(
          Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", this.widget.getId())),
          this.coolDownValue == null ? Component.translatable(String.format("germanmineraddon.hudWidget.%s.notset", this.widget.getId()))
              : Component.text(this.powerUpValue + this.coolDownValue));
    }

    public void onTick() {
      if (seconds < 1) {
        this.coolDownValue = I18n.translate(String.format("germanmineraddon.hudWidget.%s.notset", this.widget.getId()));
        this.coolDown.updateAndFlush(this.coolDownValue);
        this.seconds = 0;

        if (this.widget.delays.size() != 1) {
          this.widget.delays.remove(this);
          remove();
        }

        return;
      }

      seconds--;
      this.coolDownValue = I18n.translate(String.format("germanmineraddon.hudWidget.%s.remaining",
          this.widget.getId()), (seconds / 20));
      this.coolDown.updateAndFlush(this.powerUpValue + this.coolDownValue);
    }

    public void remove() {
      this.widget.lines.remove(this.coolDown);
    }

    public boolean isActive() {
      return this.seconds > 0;
    }

    public void setPowerUp(final String powerUp) {
      this.powerUpValue = powerUp + ": ";
    }

  }

}