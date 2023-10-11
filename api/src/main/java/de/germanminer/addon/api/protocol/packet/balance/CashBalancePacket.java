package de.germanminer.addon.api.protocol.packet.balance;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class CashBalancePacket implements GermanMinerPacket {

  private Double cash;

  public CashBalancePacket() {
  }

  public CashBalancePacket(final Double cash) {
    this.cash = cash;
  }

  public Double getCash() {
    return this.cash;
  }

}
