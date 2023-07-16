package de.germanminer.addon.controller;

import de.germanminer.addon.api.vehicle.Vehicle;
import java.util.List;
import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;

@Nullable
@Referenceable
public abstract class VehicleController {

  public abstract void fixVehicles(final List<Vehicle> vehicles);

}
