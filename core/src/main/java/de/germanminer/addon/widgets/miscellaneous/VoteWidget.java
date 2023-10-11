package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.miscellaneous.VotePacket;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;

public class VoteWidget extends GermanMinerWidget<VotePacket> {

  public VoteWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.MISCELLANEOUS_VOTE, () -> addon.configuration().miscellaneousConfig().vote().get(),
        packet -> {
          if (packet.getVotes() != null) {
            return packet.getVotes() + " / 2";
          }

          return null;
        });
  }

}
