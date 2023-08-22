package vehicle;

public class Vehicle {

  private final int entityId;
  private final double x;
  private final double y;
  private final double z;
  private final float yaw;
  private final float pitch;

  public Vehicle(int entityId, double x, double y, double z, float yaw, float pitch) {
    this.entityId = entityId;
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
  }

  public int getEntityId() {
    return entityId;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  public float getYaw() {
    return yaw;
  }

  public float getPitch() {
    return pitch;
  }
}