package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import net.labymod.api.client.component.Component;
import net.labymod.api.util.I18n;
import packets.widget.CompassPacket;

public class CompassWidget extends GermanMinerWidget<CompassPacket> {

  public CompassWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.MISCELLANEOUS_COMPASS, () -> addon.configuration().getSubSettings().isCompassEnabled(),
        packet -> {
          if (packet.getTarget() == null || packet.getDistance() == null) {
            return null;
          }

          if (packet.getTarget().equals("notset") || packet.getDistance().equals("notset")) {
            return I18n.translate(String.format("germanmineraddon.hudWidget.%s.notset", id));
          }

          return packet.getTarget() + ", " + packet.getDistance() + " Meter";
        });
  }

  @Override
  public void load() {
    this.textLine = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        this.value == null ? Component.translatable(String.format("germanmineraddon.hudwidget.%s.notset", super.getId()))
            : Component.text(this.value));
  }

}