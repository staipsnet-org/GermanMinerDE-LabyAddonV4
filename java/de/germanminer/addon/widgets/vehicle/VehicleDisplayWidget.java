package de.germanminer.addon.widgets.vehicle;

import de.germanminer.addon.GermanMinerAddon;
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
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.gui.hud.HudWidgetDestroyedEvent;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.vehicle.VehicleDisplayPacket;

import java.sql.Date;
import java.time.Instant;

public class VehicleDisplayWidget extends WidgetHudWidget<HudWidgetConfig> implements
    PacketHandler<VehicleDisplayPacket> {

  private static final float SPEED_NEEDLE_MAX = 225F;
  private static final float FUEL_NEEDLE_MAX = -90F;
  private static final float SPEED_NEEDLE_UNIT = SPEED_NEEDLE_MAX / 200F;
  private static final float FUEL_NEEDLE_UNIT = FUEL_NEEDLE_MAX / 100F;

  private final GermanMinerAddon germanMinerAddon;
  private final Icon hudWidgetIcon;
  private VehicleDisplayContent displayContent;

  private boolean show;
  private boolean nightMode;
  private boolean limiterActive;
  private int speed;
  private int fuel;
  private int limiterSpeed;
  private int damageState;

  public VehicleDisplayWidget(GermanMinerAddon germanMinerAddon, String id) {
    super(id, HudWidgetConfig.class);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.germanMinerAddon = germanMinerAddon;
    this.hudWidgetIcon = Icon.texture(
            ResourceLocation.create("germanmineraddon", "textures/vehicle.png"))
        .resolution(64, 64);
  }

  @Subscribe
  public void onDestroy(HudWidgetDestroyedEvent event) {
    if (event.hudWidget() == this) {
      sendInfo();
    }
  }

  @Override
  public void load(HudWidgetConfig config) {
    super.load(config);
    super.setIcon(this.hudWidgetIcon);

    sendInfo();
  }

  @Override
  public void initialize(HudWidgetWidget widget) {
    super.initialize(widget);

    this.displayContent = new VehicleDisplayContent();
    widget.addChild(this.displayContent);
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks, boolean isEditorContext,
      HudSize size) {
    drawLine(stack, size, Math.max(0, Math.min(SPEED_NEEDLE_MAX, this.speed * SPEED_NEEDLE_UNIT)),
        VehicleDisplayTexture.SPEED_NEEDLE.getIcon(this.nightMode), 4F);
    drawLine(stack, size, Math.min(0, Math.max(FUEL_NEEDLE_MAX, this.fuel * FUEL_NEEDLE_UNIT)),
        VehicleDisplayTexture.FUEL_NEEDLE.getIcon(this.nightMode), 4F);

    if (this.limiterSpeed >= 10) {
      Icon icon = this.limiterActive ?
          VehicleDisplayTexture.LIMITER_NEEDLE.getIcon(this.nightMode) :
          VehicleDisplayTexture.LIMITER_NEEDLE_INACTIVE.getIcon(this.nightMode);

      drawLine(stack, size,
          Math.max(0, Math.min(SPEED_NEEDLE_MAX, this.limiterSpeed * SPEED_NEEDLE_UNIT)), icon, 3F);
    }

    if (this.damageState >= 1) {
      boolean visible = ((int) Date.from(Instant.now()).getTime() / 1000) % 2 == 0;

      if (this.displayContent.getWarning().isVisible() != visible) {
        this.displayContent.getWarning().setVisible(visible);
      }
    }

  }

  private void drawLine(Stack stack, HudSize hudSize, float angle, Icon icon, float zIndex) {
    stack.push();
    stack.translate(hudSize.getWidth() / 2F, hudSize.getHeight() / 2F, zIndex);
    stack.rotate(angle, 0F, 0F, 1F);
    stack.translate(-hudSize.getWidth() / 2F, -hudSize.getHeight() / 2F, zIndex);
    icon.render(stack, 0F, 0F, hudSize.getWidth(), hudSize.getHeight());
    stack.translate(0F, 0F, 0F);
    stack.pop();
  }

  @Override
  public boolean isVisibleInGame() {
    return this.germanMinerAddon.enabled() && !Laby.references().chatAccessor().isChatOpen()
        && this.show;
  }

  @Override
  public void handle(VehicleDisplayPacket packet) {
    if (packet.getShow() != null) {
      this.show = packet.getShow();

      if (!this.show) {
        this.displayContent.getSpeedLimiter().setVisible(false);
        this.displayContent.getWarning().setVisible(false);

        this.nightMode = false;
        this.speed = 0;
        this.fuel = 0;
        this.limiterActive = false;
        this.limiterSpeed = 0;
        this.damageState = 0;
      }
    }

    if (packet.getNightModus() != null) {
      this.displayContent.getBackground().icon().set(VehicleDisplayTexture.SPEEDOMETER_FUEL.getIcon(
          packet.getNightModus() != null && packet.getNightModus()));
      this.nightMode = packet.getNightModus();
    }

    if (packet.getSpeed() != null) {
      this.displayContent.getSpeed().setText(String.valueOf(packet.getSpeed()));
      this.speed = packet.getSpeed();
    }

    if (packet.getFuelPercent() != null) {
      this.fuel = packet.getFuelPercent();
    }

    drawInfo(packet);

    if (packet.getLimiterSpeed() != null) {
      drawSpeedLimiter(packet);
    }

    if (packet.getDamageState() != null) {
      drawWarning(packet);
    }
  }

  public void drawInfo(VehicleDisplayPacket packet) {
    TranslatableComponent info;
    if ("STARTING".equals(packet.getEngineState())) {
      info = Component.translatable(
          String.format("germanmineraddon.hudWidget.%s.engine.starting", super.getId()));
    } else if ("OFF".equals(packet.getEngineState())) {
      info = Component.translatable(
          String.format("germanmineraddon.hudWidget.%s.engine.off", super.getId()));
    } else if (packet.getGearPosition() != null) {
      info = Component.translatable(
          String.format("germanmineraddon.hudWidget.%s.gear", super.getId()),
          Component.text(packet.getGearPosition().charAt(0)));
    } else if (packet.getFlightHeight() != null) {
      info = Component.translatable(
          String.format("germanmineraddon.hudWidget.%s.height", super.getId()),
          Component.text(packet.getFlightHeight()));
    } else {
      info = null;
    }

    if (info != null) {
      this.displayContent.getInfo().setComponent(info);
    }
  }

  public void drawSpeedLimiter(VehicleDisplayPacket packet) {
    if (packet.getLimiterSpeed() >= 10) {
      Icon icon = packet.getLimiterActive() != null && packet.getLimiterActive() ?
          VehicleDisplayTexture.SPEED_LIMITER_ACTIVE.getIcon(
              packet.getNightModus() != null && packet.getNightModus()) :
          VehicleDisplayTexture.SPEED_LIMITER_READY.getIcon(
              packet.getNightModus() != null && packet.getNightModus());

      this.displayContent.getSpeedLimiter().icon().set(icon);
      this.displayContent.getSpeedLimiter().setVisible(true);

      if (packet.getLimiterSpeed() != null) {
        this.limiterActive = packet.getLimiterActive();
      }

      this.limiterSpeed = packet.getLimiterSpeed();
    }
  }

  public void drawWarning(VehicleDisplayPacket packet) {
    if (packet.getDamageState() >= 1) {
      Icon icon = packet.getDamageState() == 1 ?
          VehicleDisplayTexture.WARNING_YELLOW.getIcon(
              packet.getNightModus() != null && packet.getNightModus()) :
          VehicleDisplayTexture.WARNING_RED.getIcon(
              packet.getNightModus() != null && packet.getNightModus());

      this.displayContent.getWarning().icon().set(icon);
      this.damageState = packet.getDamageState();
    }
  }

  public void sendInfo() {
    this.germanMinerAddon.sendPacket(
        new VehicleDisplayPacket(this.germanMinerAddon.enabled() && super.isEnabled()));
  }
}
