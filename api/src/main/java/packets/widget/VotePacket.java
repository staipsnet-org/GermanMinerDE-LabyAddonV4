package protocol.packet.widget;

import protocol.packet.GermanMinerPacket;

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