package de.germanminer.addon.widgets.level;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.level.LevelPointsPacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class LevelPointsWidget extends GermanMinerWidget<LevelPointsPacket> {

  public LevelPointsWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYER_LEVEL_POINTS, () -> addon.configuration().miscellaneousConfig().level().get(),
        packet -> {
          if (packet.getLevelPoints() != null || packet.getRequiredLevelPoints() != null) {
            final int points = packet.getLevelPoints();
            final int requiredPoints = packet.getRequiredLevelPoints();

            return points + " / " + requiredPoints;
          }

          return null;
        });
  }

}
