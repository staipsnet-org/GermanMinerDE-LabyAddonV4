package de.germanminer.addon.api.vehicle;

public class Vehicle {

  private final int entityId;
  private final double x;
  private final double y;
  private final double z;
  private final float yaw;
  private final float pitch;

  public Vehicle(final int entityId, final double x, final double y, final double z, final float yaw, final float pitch) {
    this.entityId = entityId;
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
  }

  public int getEntityId() {
    return this.entityId;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }

  public float getYaw() {
    return this.yaw;
  }

  public float getPitch() {
    return this.pitch;
  }

}
