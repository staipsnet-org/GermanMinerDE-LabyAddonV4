package packets.widget;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Votesystem
 */
public class VotePacket implements GermanMinerPacket {

  private final String votes;

  public VotePacket(String votes) {
    this.votes = votes;
  }

  public String getVotes() {
    return votes;
  }
}