package protocol.packet.widget.balance;

import protocol.packet.GermanMinerPacket;

public class CashBalancePacket implements GermanMinerPacket {

  private Double cash;

  public CashBalancePacket() {
  }

  public CashBalancePacket(Double cash) {
    this.cash = cash;
  }

  public Double getCash() {
    return cash;
  }

}