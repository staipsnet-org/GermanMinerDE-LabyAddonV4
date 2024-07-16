package packets.widget.balance;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
public class BankBalancePacket implements GermanMinerPacket {

  private final double bank;

  public BankBalancePacket(double bank) {
    this.bank = bank;
  }

  public Double getBank() {
    return bank;
  }
}