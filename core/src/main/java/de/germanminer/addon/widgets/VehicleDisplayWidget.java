package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.gui.hud.hudwidget.HudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.widget.WidgetHudWidget;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.widget.widgets.hud.HudWidgetWidget;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class VehicleDisplayWidget extends WidgetHudWidget<HudWidgetConfig> implements PacketHandler<VehicleDisplayPacket> {

  private final Icon hudWidgetIcon;
  private VehicleDisplayContent content;
  private boolean show;

  public VehicleDisplayWidget(final String id) {
    super(id, HudWidgetConfig.class);
    super.bindCategory(GermanMinerAddon.getInstance().getCategory());

    this.hudWidgetIcon = VehicleDisplayTexture.SPEED_LIMITER_ACTIVE.getIcon(true);
  }

  @Override
  public void load(final HudWidgetConfig config) {
    super.load(config);
    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public void initialize(final HudWidgetWidget widget) {
    super.initialize(widget);

    this.content = new VehicleDisplayContent();
    widget.addChild(content);
  }

  @Override
  public boolean isVisibleInGame() {
    return true;
    //return GermanMinerAddon.getInstance().enabled()
    // && !Laby.references().chatAccessor().isChatOpen() && this.show;
  }

  @Override
  public void handle(final VehicleDisplayPacket packet) {
    if (packet.getShow() != null) {
      this.show = packet.getShow();

      if (!this.show) {
        this.content.getSpeedLimiter().setVisible(false);
        this.content.getWarning().setVisible(false);
      }
    }

    drawSpeedometer(packet);

    if (packet.getSpeed() != null) {
      drawSpeed(packet);
    }

    if (packet.getFuelPercent() != null) {
      drawFuel(packet);
    }

    drawInfo(packet);

    if (packet.getLimiterSpeed() != null) {
      drawSpeedLimiter(packet);
    }

    if (packet.getEngineState() != null) {
      drawWarning(packet);
    }
  }

  private void drawSpeedometer(final VehicleDisplayPacket packet)  {
    this.content.getBackground().icon().set(VehicleDisplayTexture.SPEEDOMETER_BACKGROUND
        .getIcon(packet.getNightMode() != null && packet.getNightMode()));
  }

  private void drawSpeed(final VehicleDisplayPacket packet) {
    this.content.getSpeed().setText(String.valueOf(packet.getSpeed()));

    // ToDo: Draw line
  }

  private void drawFuel(final VehicleDisplayPacket packet) {
    this.content.getFuel().icon().set(VehicleDisplayTexture.FUEL_OVERLAY
        .getIcon(packet.getNightMode() != null && packet.getNightMode()));

    // ToDo: Draw line
  }

  private void drawInfo(final VehicleDisplayPacket packet) {
    final TranslatableComponent info;
    if ("STARTING".equals(packet.getEngineState())) {
      info = Component.translatable(String.format("germanminer.hudWidget.%s.engine.starting", super.getId()));
    } else if ("OFF".equals(packet.getEngineState())) {
      info = Component.translatable(String.format("germanminer.hudWidget.%s.engine.off", super.getId()));
    } else if (packet.getGearPosition() != null) {
      info = Component.translatable(String.format("germanminer.hudWidget.%s.gear",
          super.getId()), Component.text(packet.getGearPosition().charAt(0)));
    } else if (packet.getFlightHeight() != -1) {
      info = Component.translatable(String.format("germanminer.hudWidget.%s.height",
          super.getId()), Component.text(packet.getFlightHeight()));
    } else {
      info = null;
    }

    if (info != null) {
      this.content.getInfo().setComponent(info);
    }
  }

  public void drawSpeedLimiter(final VehicleDisplayPacket packet) {
    if (packet.getLimiterSpeed() >= 10) {
      final Icon icon = packet.getLimiterActive() != null && packet.getLimiterActive() ?
          VehicleDisplayTexture.SPEED_LIMITER_ACTIVE
              .getIcon(packet.getNightMode() != null && packet.getNightMode()) :
          VehicleDisplayTexture.SPEED_LIMITER_READY
              .getIcon(packet.getNightMode() != null && packet.getNightMode());

      this.content.getSpeedLimiter().icon().set(icon);
      this.content.getSpeedLimiter().setVisible(true);

      // ToDo: Draw line
    }
  }

  public void drawWarning(final VehicleDisplayPacket packet) {
    if (packet.getDamageState() >= 1) {
      final Icon icon = packet.getDamageState() == 1 ?
          VehicleDisplayTexture.WARNING_YELLOW
              .getIcon(packet.getNightMode() != null && packet.getNightMode()) :
          VehicleDisplayTexture.WARNING_RED
              .getIcon(packet.getNightMode() != null && packet.getNightMode());

      this.content.getWarning().icon().set(icon);
      this.content.getWarning().setVisible(true);

      // ToDo: Blinking
    }
  }

}
