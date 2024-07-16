package packets.widget.balance;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
public class CashBalancePacket implements GermanMinerPacket {

  private final double cash;

  public CashBalancePacket(double cash) {
    this.cash = cash;
  }

  public double getCash() {
    return cash;
  }
}