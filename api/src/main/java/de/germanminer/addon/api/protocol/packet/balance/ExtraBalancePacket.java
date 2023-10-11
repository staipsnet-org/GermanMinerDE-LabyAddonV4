package de.germanminer.addon.api.protocol.packet.balance;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import java.util.Arrays;

public class ExtraBalancePacket implements GermanMinerPacket {

  private String accounts;

  public ExtraBalancePacket() {
  }

  public ExtraBalancePacket(final String accounts) {
    this.accounts = accounts;
  }

  public Double getCash(final String accountNumber) {
    return Arrays.stream(this.accounts.split(","))
        .map(accountInfo -> accountInfo.split(";;;"))
        .filter(info -> info.length >= 2)
        .filter(info -> info[0].equalsIgnoreCase(accountNumber))
        .map(info -> Double.parseDouble(info[1]))
        .findFirst().orElse(null);
  }

}
