package de.germanminer.addon;

import de.germanminer.addon.controller.VehicleController;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import vehicle.Vehicle;
import java.util.List;

@Singleton
@Implements(VehicleController.class)
public class VersionedVehicleController extends VehicleController {

  @Inject
  public VersionedVehicleController() {
  }

  @Override
  public void fixVehicles(final List<Vehicle> vehicles) {
    vehicles.forEach(vehicle -> {
      final Entity entity = Minecraft.getMinecraft().world.getEntityByID(vehicle.getEntityId());

      if (entity != null) {
        EntityTracker.updateServerPosition(entity, vehicle.getX(), vehicle.getY(), vehicle.getZ());
        entity.setPositionAndRotationDirect(vehicle.getX(), vehicle.getY(), vehicle.getZ(),
            vehicle.getYaw(), vehicle.getPitch(), 3, true);
      }
    });
  }

}