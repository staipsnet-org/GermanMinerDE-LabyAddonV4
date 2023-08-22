package protocol.packet.widget.balance;

import protocol.packet.GermanMinerPacket;

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