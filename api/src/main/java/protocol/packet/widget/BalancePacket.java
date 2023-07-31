package protocol.packet.widget;

import protocol.packet.GermanMinerPacket;

public class BalancePacket implements GermanMinerPacket {

  private Double cash;
  private Double bank;

  public BalancePacket() {
  }

  public BalancePacket(Double cash, Double bank) {
    this.cash = cash;
    this.bank = bank;
  }

  public Double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
  }

  public Double getBank() {
    return bank;
  }

  public void setBank(double bank) {
    this.bank = bank;
  }
}
