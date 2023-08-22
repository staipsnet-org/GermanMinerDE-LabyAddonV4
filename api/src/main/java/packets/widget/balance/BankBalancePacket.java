package protocol.packet.widget.balance;

import protocol.packet.GermanMinerPacket;

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