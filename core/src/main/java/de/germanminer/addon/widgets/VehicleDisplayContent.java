package de.germanminer.addon.widgets;

import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.DivWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;

@AutoWidget
@Link("vehicle-display.lss")
public class VehicleDisplayContent extends FlexibleContentWidget {

  private IconWidget background;
  private IconWidget fuel;
  private ComponentWidget speed;
  private ComponentWidget info;
  private IconWidget speedLimiter;
  private IconWidget warning;

  @Override
  public void initialize(final Parent parent) {
    super.initialize(parent);
    this.children.clear();

    final DivWidget div = new DivWidget();

    this.warning = new IconWidget(VehicleDisplayTexture.WARNING_YELLOW.getIcon(false));
    this.warning.setCleanupOnDispose(true);
    this.warning.addId("warning");
    div.addChild(this.warning);

    this.speedLimiter = new IconWidget(VehicleDisplayTexture.SPEED_LIMITER_ACTIVE.getIcon(false));
    this.speedLimiter.setCleanupOnDispose(true);
    this.speedLimiter.addId("speedLimiter");
    div.addChild(this.speedLimiter);

    this.info = ComponentWidget.text("");
    this.info.addId("info");
    div.addChild(this.info);

    this.speed = ComponentWidget.text("0");
    this.speed.addId("speed");
    div.addChild(this.speed);

    this.fuel = new IconWidget(VehicleDisplayTexture.FUEL_OVERLAY.getIcon(false));
    this.fuel.setCleanupOnDispose(true);
    this.fuel.addId("fuel");
    div.addChild(this.fuel);

    this.background = new IconWidget(VehicleDisplayTexture.SPEEDOMETER_BACKGROUND.getIcon(false));
    this.background.setCleanupOnDispose(true);
    this.background.addId("speedometer");
    div.addChild(this.background);

    this.addContent(div);
  }

  public IconWidget getBackground() {
    return this.background;
  }

  public IconWidget getFuel() {
    return this.fuel;
  }

  public ComponentWidget getSpeed() {
    return this.speed;
  }

  public ComponentWidget getInfo() {
    return this.info;
  }

  public IconWidget getSpeedLimiter() {
    return this.speedLimiter;
  }

  public IconWidget getWarning() {
    return this.warning;
  }

}
