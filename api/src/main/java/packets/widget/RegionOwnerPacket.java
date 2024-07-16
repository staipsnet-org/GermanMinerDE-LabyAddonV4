package packets.widget;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

public class RegionOwnerPacket implements GermanMinerPacket {

  @SerializedName("region_owner")
  private final String regionOwner;

  public RegionOwnerPacket(String regionOwner) {
    this.regionOwner = regionOwner;
  }

  public String getRegionOwner() {
    return regionOwner;
  }
}