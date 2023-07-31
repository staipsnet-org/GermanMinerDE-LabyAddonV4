package de.germanminer.addon.v1_12_2;

import de.germanminer.addon.controller.VehicleController;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import vehicle.Vehicle;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Implements(VehicleController.class)
public class VersionedVehicleController extends VehicleController {

  @Inject
  public VersionedVehicleController() {}

  @Override
  public void fixVehicles(List<Vehicle> vehicleList) {
       vehicleList.forEach(vehicle -> {
         Entity entity = Minecraft.getMinecraft().world.getEntityByID(vehicle.getEntityId());

         if (entity != null) {
           EntityTracker.updateServerPosition(entity, vehicle.getX(), vehicle.getY(), vehicle.getZ());
           entity.setPositionAndRotationDirect(vehicle.getX(), vehicle.getY(), vehicle.getZ(), vehicle.getYaw(), vehicle.getPitch(), 3, true);
         }
       });
  }
}