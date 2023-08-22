package packets.widget.balance;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
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