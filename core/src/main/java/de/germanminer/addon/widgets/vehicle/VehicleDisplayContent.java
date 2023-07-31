package de.germanminer.addon.widgets.vehicle;

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
  private IconWidget speedLimiter;
  private IconWidget warning;
  private ComponentWidget speed;
  private ComponentWidget info;

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    this.children.clear();

    DivWidget divWidget = new DivWidget();

    this.warning = new IconWidget(VehicleDisplayTexture.WARNING_YELLOW.getIcon(false));
    this.warning.setCleanupOnDispose(true);
    this.warning.addId("vehicle_warning");
    divWidget.addChild(this.warning);

    this.speedLimiter = new IconWidget(VehicleDisplayTexture.SPEED_LIMITER_ACTIVE.getIcon(false));
    this.speedLimiter.setCleanupOnDispose(true);
    this.speedLimiter.addId("vehicle_speedLimiter");
    divWidget.addChild(this.speedLimiter);

    this.info = ComponentWidget.text("");
    this.info.addId("vehicle_info");
    divWidget.addChild(this.info);

    this.speed = ComponentWidget.text("0");
    this.speed.addId("vehicle_speed");
    divWidget.addChild(this.speed);

    this.background = new IconWidget(VehicleDisplayTexture.SPEEDOMETER_FUEL.getIcon(false));
    this.background.setCleanupOnDispose(true);
    this.background.addId("background");
    divWidget.addChild(this.background);

    this.addContent(divWidget);
  }

  public IconWidget getBackground() {
    return background;
  }

  public IconWidget getSpeedLimiter() {
    return speedLimiter;
  }

  public IconWidget getWarning() {
    return warning;
  }

  public ComponentWidget getSpeed() {
    return speed;
  }

  public ComponentWidget getInfo() {
    return info;
  }
}