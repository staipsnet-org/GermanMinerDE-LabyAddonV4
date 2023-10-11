package de.germanminer.addon.api.protocol.packet.balance;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class BankBalancePacket implements GermanMinerPacket {

  private Double bank;

  public BankBalancePacket() {
  }

  public BankBalancePacket(final Double bank) {
    this.bank = bank;
  }

  public Double getBank() {
    return this.bank;
  }

}
