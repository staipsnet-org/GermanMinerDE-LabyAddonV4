package packets.widget;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Votesystem
 */
public class VotePacket implements GermanMinerPacket {

  private String votes;

  public VotePacket() {
  }

  public VotePacket(String votes) {
    this.votes = votes;
  }

  public String getVotes() {
    return votes;
  }
}