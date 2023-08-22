package packets.widget.balance;

import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
public class CompanyBalancePacket implements GermanMinerPacket {

  private Double company;

  public CompanyBalancePacket() {
  }

  public CompanyBalancePacket(Double companyCash) {
    this.company = companyCash;
  }

  public Double getCompany() {
    return company;
  }
}