package packets.widget.balance;

import net.labymod.serverapi.api.packet.IdentifiablePacket;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import org.jetbrains.annotations.NotNull;
import packets.GermanMinerPacket;

/**
 * Packet zum Verarbeiten vom Banksystem
 */
public class CompanyBalancePacket implements GermanMinerPacket {

  private final double company;

  public CompanyBalancePacket(double company) {
    this.company = company;
  }

  public double getCompany() {
    return company;
  }
}