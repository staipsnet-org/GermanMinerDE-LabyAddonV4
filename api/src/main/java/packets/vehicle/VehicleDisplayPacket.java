package protocol.packet.vehicle;

import protocol.packet.GermanMinerPacket;

public class VehicleDisplayPacket implements GermanMinerPacket {

  private Boolean modusEnabled;
  private Boolean show;
  private Boolean limiterActive;
  private Boolean nightModus;
  private Integer speed;
  private Integer limiterSpeed;
  private Integer fuelPercent;
  private Integer flightHeight;
  private Integer damageState;
  private String engineState;
  private String gearPosition;

  public VehicleDisplayPacket() {
  }


  public VehicleDisplayPacket(Boolean moduleEnabled) {
    this.modusEnabled = moduleEnabled;
  }

  public VehicleDisplayPacket(Boolean modusEnabled, Boolean show, Boolean limiterActive,
      Boolean nightModus, Integer speed, Integer limiterSpeed, Integer fuelPercent,
      Integer flightHeight, Integer damageState, String engineState, String gearPosition) {
    this.modusEnabled = modusEnabled;
    this.show = show;
    this.limiterActive = limiterActive;
    this.nightModus = nightModus;
    this.speed = speed;
    this.limiterSpeed = limiterSpeed;
    this.fuelPercent = fuelPercent;
    this.flightHeight = flightHeight;
    this.damageState = damageState;
    this.engineState = engineState;
    this.gearPosition = gearPosition;
  }

  public Boolean getModusEnabled() {
    return modusEnabled;
  }

  public Boolean getShow() {
    return show;
  }

  public Boolean getLimiterActive() {
    return limiterActive;
  }

  public Boolean getNightModus() {
    return nightModus;
  }

  public Integer getSpeed() {
    return speed;
  }

  public Integer getLimiterSpeed() {
    return limiterSpeed;
  }

  public Integer getFuelPercent() {
    return fuelPercent;
  }

  public Integer getFlightHeight() {
    return flightHeight;
  }

  public Integer getDamageState() {
    return damageState;
  }

  public String getEngineState() {
    return engineState;
  }

  public String getGearPosition() {
    return gearPosition;
  }
}