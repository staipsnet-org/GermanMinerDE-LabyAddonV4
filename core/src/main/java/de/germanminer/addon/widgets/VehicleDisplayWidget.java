package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TranslatableComponent;
import net.labymod.api.client.gui.hud.hudwidget.HudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.widget.WidgetHudWidget;
import net.labymod.api.client.gui.hud.position.HudSize;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.widget.widgets.hud.HudWidgetWidget;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class VehicleDisplayWidget extends WidgetHudWidget<HudWidgetConfig> implements PacketHandler<VehicleDisplayPacket> {

  private static final float SPEED_NEEDLE_MAX = -225F;
  private static final float FUEL_NEEDLE_MAX = 90F;
  private static final float SPEED_NEEDLE_UNIT = SPEED_NEEDLE_MAX / 200F;
  private static final float FUEL_NEEDLE_UNIT = FUEL_NEEDLE_MAX / 100F;

  private final Icon hudWidgetIcon;
  private VehicleDisplayContent content;

  private boolean show;
  private boolean nightMode;
  private int speed;
  private int fuel;
  private boolean limiterActive;
  private int limiterSpeed;
  private int damageState;
  private int warningLight;

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
  public void render(final Stack stack, final MutableMouse mouse, final float partialTicks,
      final boolean isEditorContext, final HudSize size) {

    drawLine(stack, size, Math.max(0, Math.min(SPEED_NEEDLE_MAX, this.speed * SPEED_NEEDLE_UNIT)),
        VehicleDisplayTexture.SPEED_NEEDLE.getIcon(this.nightMode));
    drawLine(stack, size, Math.max(0, Math.min(FUEL_NEEDLE_MAX, this.fuel * FUEL_NEEDLE_UNIT)),
        VehicleDisplayTexture.FUEL_NEEDLE.getIcon(this.nightMode));

    if (this.limiterSpeed >= 10) {
      final Icon icon = this.limiterActive ?
          VehicleDisplayTexture.LIMITER_NEEDLE.getIcon(this.nightMode) :
          VehicleDisplayTexture.LIMITER_NEEDLE_INACTIVE.getIcon(this.nightMode);

      drawLine(stack, size, Math.max(0, Math.min(SPEED_NEEDLE_MAX, this.limiterSpeed * SPEED_NEEDLE_UNIT)), icon);
    }

    if (this.damageState >= 1) {
      final boolean visible = this.warningLight++ < 40;

      if (this.content.getWarning().isVisible() != visible) {
        this.content.getWarning().setVisible(visible);
      }

      if (this.warningLight == 80) {
        this.warningLight = 0;
      }
    }
  }

  private void drawLine(final Stack stack, final HudSize size, final float angle, final Icon icon) {
    stack.push();
    stack.translate(size.getWidth() / 2F, size.getHeight() / 2F, 3);
    stack.rotate(angle, 0F, 0F, 1F);
    stack.translate(-size.getWidth() / 2F, -size.getHeight() / 2F, 3);
    icon.render(stack, 0F, 0F, size.getWidth(), size.getHeight());
    stack.translate(0F, 0F, 0F);
    stack.pop();
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled()
     && !Laby.references().chatAccessor().isChatOpen() && this.show;
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

    // update night mode
    if (packet.getNightMode() != null) {
      this.content.getBackground().icon().set(VehicleDisplayTexture.SPEEDOMETER_FUEL
          .getIcon(packet.getNightMode() != null && packet.getNightMode()));
      this.nightMode = packet.getNightMode();
    }

    // update speed
    if (packet.getSpeed() != null) {
      this.content.getSpeed().setText(String.valueOf(packet.getSpeed()));
      this.speed = packet.getSpeed();
    }

    // update fuel
    if (packet.getFuelPercent() != null) {
      this.fuel = packet.getFuelPercent();
    }

    // update info text
    drawInfo(packet);

    // update speed limiter
    if (packet.getLimiterSpeed() != null) {
      drawSpeedLimiter(packet);
    }

    // update engine state
    if (packet.getEngineState() != null) {
      drawWarning(packet);
    }
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

      if (packet.getLimiterSpeed() != null) {
        this.limiterActive = packet.getLimiterActive();
      }

      this.limiterSpeed = packet.getLimiterSpeed();
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
      this.damageState = packet.getDamageState();
      this.warningLight = 0;
    }
  }

}
