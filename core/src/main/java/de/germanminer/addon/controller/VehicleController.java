package de.germanminer.addon.controller;

import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;
import vehicle.Vehicle;
import java.util.List;

@Nullable
@Referenceable
public abstract class VehicleController {

  public abstract void fixVehicles(List<Vehicle> vehicleList);

}