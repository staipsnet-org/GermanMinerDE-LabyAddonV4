package protocol.packet.widget.balance;

import protocol.packet.GermanMinerPacket;

public class ExtraBalancePacket implements GermanMinerPacket {

  private String accounts;

  public ExtraBalancePacket() {
  }

  public ExtraBalancePacket(String accounts) {
    this.accounts = accounts;
  }

  public Double getCash(String accountNumber) {
    String[] accountsInfo = accounts.split(",");
    for (String accountInfos : accountsInfo) {
      String[] info = accountInfos.split(";;;");

      String accountNumberInfo = info[0];
      Double accountValue = Double.valueOf(info[1]);

      if (accountNumberInfo.equals(accountNumber)) {
        return accountValue;
      }
    }
    return -1D;
  }
}