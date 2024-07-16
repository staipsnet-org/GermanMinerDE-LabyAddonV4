package packets.widget.balance;

import packets.GermanMinerPacket;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtraBalancePacket implements GermanMinerPacket {

  private String accounts;

  public ExtraBalancePacket() {
  }

  public ExtraBalancePacket(final String accounts) {
    this.accounts = accounts;
  }

  public Map<String, Double> getExtraAccounts() {
    if (this.accounts == null) {
      return null;
    }

    return Arrays.stream(this.accounts.split(","))
        .map(accountInfo -> accountInfo.split(";;;"))
        .filter(info -> info.length >= 2)
        .collect(Collectors.toMap(info -> info[0], info -> Double.parseDouble(info[1])));
  }

}