package de.germanminer.addon.api.protocol.packet.widget;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class BalancePacket implements GermanMinerPacket {

  private Double cash;
  private Double bank;

  public BalancePacket() {
  }

  public BalancePacket(final Double cash, final Double bank) {
    this.cash = cash;
    this.bank = bank;
  }

  public Double getCash() {
    return this.cash;
  }

  public Double getBank() {
    return this.bank;
  }

}
