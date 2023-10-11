package de.germanminer.addon.api.protocol.packet.miscellaneous;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class VotePacket implements GermanMinerPacket {

  private String votes;

  public VotePacket() {}

  public VotePacket(final String votes) {
    this.votes = votes;
  }

  public String getVotes() {
    return this.votes;
  }

}
