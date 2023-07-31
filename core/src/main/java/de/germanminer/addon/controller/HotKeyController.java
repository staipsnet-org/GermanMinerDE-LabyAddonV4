package de.germanminer.addon.controller;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.key.HotkeyService.Type;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import protocol.packet.vehicle.VehicleHotKeyPacket;
import vehicle.HotKey;
import javax.inject.Singleton;

@Singleton
public class HotKeyController {

  private final GermanMinerAddon germanMinerAddon;

  public HotKeyController(GermanMinerAddon germanMinerAddon) {
    this.germanMinerAddon = germanMinerAddon;

    register("engine", this.germanMinerAddon.configuration().getVehicleEngineHotkey(), HotKey.ENGINE_SWITCH);
    register("siren", this.germanMinerAddon.configuration().getVehicleSirenHotkey(), HotKey.SIREN_SWITCH);
    register("limiter_set", this.germanMinerAddon.configuration().getVehicleLimiterSetHotkey(), HotKey.SPEED_LIMITER_SET);
    register("limiter_switch", this.germanMinerAddon.configuration().getVehicleLimiterSwitchHotkey(), HotKey.SPEED_LIMITER_SWITCH);
  }

  private void register(String id, ConfigProperty<Key> key, HotKey hotKey) {
    Laby.references().hotkeyService().register("germanminer_" + id, key, () -> Type.TOGGLE, active -> {
      if (this.germanMinerAddon.enabled()) {
        this.germanMinerAddon.sendPacket(new VehicleHotKeyPacket(hotKey));
      }
    });
  }

}