package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.PowerupPacket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class PowerupWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<PowerupPacket> {

  private final Icon hudIcon;
  private final Timer timer;
  private TextLine cooldown;

  public PowerupWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.hudIcon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/powerup.png"))
        .resolution(64, 64);
    this.timer = new Timer();
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    cooldown = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.powerup.notset"));

    super.setIcon(hudIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .getSubSettings().isPowerUpEnabled();
  }

  @Override
  public void handle(PowerupPacket packet) {
    if (packet.getCooldown() == null) {
      cooldown.updateAndFlush(Component.translatable("germanmineraddon.hudWidget.error"));
      return;
    }

    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        int remainingSeconds = Math.toIntExact(
            Duration.between(LocalDateTime.now(), packet.getCooldown()).toSeconds() - 1);

        if (remainingSeconds < 1) {
          cancel();
          cooldown.updateAndFlush(
              Component.translatable("germanmineraddon.hudWidget.powerup.notset"));
          return;
        }

        cooldown.updateAndFlush(Component.text("Noch " + remainingSeconds + " Sekunden"));
      }
    }, 0, 1000);
  }
}
