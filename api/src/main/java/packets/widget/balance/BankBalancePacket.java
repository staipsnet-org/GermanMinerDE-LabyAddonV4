package packets.widget.balance;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
public class BankBalancePacket implements GermanMinerPacket {

  private Double bank;

  public BankBalancePacket() {
  }

  public BankBalancePacket(Double bank) {
    this.bank = bank;
  }

  public Double getBank() {
    return bank;
  }
}