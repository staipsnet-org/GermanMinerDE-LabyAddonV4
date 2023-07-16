package de.germanminer.addon.api.protocol.packet.vehicle;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class VehicleDisplayPacket implements GermanMinerPacket {

  private Boolean moduleEnabled;
  private Boolean show;
  private Integer speed;
  private Boolean limiterActive;
  private Integer limiterSpeed;
  private Integer fuelPercent;
  private String gearPosition;
  private Integer flightHeight;
  private String engineState;
  private Integer damageState;
  private Boolean nightMode;

  public VehicleDisplayPacket() {
  }

  public VehicleDisplayPacket(final Boolean moduleEnabled) {
    this.moduleEnabled = moduleEnabled;
  }
  
  public VehicleDisplayPacket(final Boolean moduleEnabled, final Boolean show, final Integer speed,
      final Boolean limiterActive, final Integer limiterSpeed, final Integer fuelPercent, final String gearPosition,
      final Integer flightHeight, final String engineState, final Integer damageState, final Boolean nightMode) {
    this.moduleEnabled = moduleEnabled;
    this.show = show;
    this.speed = speed;
    this.limiterActive = limiterActive;
    this.limiterSpeed = limiterSpeed;
    this.fuelPercent = fuelPercent;
    this.gearPosition = gearPosition;
    this.flightHeight = flightHeight;
    this.engineState = engineState;
    this.damageState = damageState;
    this.nightMode = nightMode;
  }

  public Boolean getModuleEnabled() {
    return this.moduleEnabled;
  }

  public Boolean getShow() {
    return this.show;
  }

  public Integer getSpeed() {
    return this.speed;
  }

  public Boolean getLimiterActive() {
    return this.limiterActive;
  }

  public Integer getLimiterSpeed() {
    return this.limiterSpeed;
  }

  public Integer getFuelPercent() {
    return this.fuelPercent;
  }

  public String getGearPosition() {
    return this.gearPosition;
  }

  public Integer getFlightHeight() {
    return this.flightHeight;
  }

  public String getEngineState() {
    return this.engineState;
  }

  public Integer getDamageState() {
    return this.damageState;
  }

  public Boolean getNightMode() {
    return this.nightMode;
  }
  
}
