package de.germanminer.addon.widgets.level;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.level.LevelPacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class LevelWidget extends GermanMinerWidget<LevelPacket> {

  public LevelWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.PLAYER_LEVEL, () -> addon.configuration().miscellaneousConfig().level().get(),
        packet -> {
          if (packet.getCurrentLevel() != null) {
            return String.valueOf(packet.getCurrentLevel());
          }

          return null;
        });
  }

}
