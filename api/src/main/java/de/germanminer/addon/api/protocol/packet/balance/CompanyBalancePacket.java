package de.germanminer.addon.api.protocol.packet.balance;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class CompanyBalancePacket implements GermanMinerPacket {

  private Double company;

  public CompanyBalancePacket() {
  }

  public CompanyBalancePacket(final Double company) {
    this.company = company;
  }

  public Double getCompany() {
    return this.company;
  }

}
