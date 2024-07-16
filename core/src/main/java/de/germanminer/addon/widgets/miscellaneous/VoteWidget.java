package de.germanminer.addon.widgets.miscellaneous;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.widgets.GermanMinerWidget;
import de.germanminer.addon.widgets.WidgetIcon;
import packets.widget.VotePacket;

public class VoteWidget extends GermanMinerWidget<VotePacket> {

  public VoteWidget(final GermanMinerAddon addon, final String id) {
    super(addon, id, WidgetIcon.MISCELLANEOUS_VOTE, () -> addon.configuration().getSubSettings().isVoteEnabled(),
        packet -> {
          if (packet.getVotes() != null) {
            return packet.getVotes() + " / 2";
          }

          return null;
        });
  }

}