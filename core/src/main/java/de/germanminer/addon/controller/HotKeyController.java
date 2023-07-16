package de.germanminer.addon.controller;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleHotKeyPacket;
import de.germanminer.addon.api.vehicle.HotKey;
import javax.inject.Singleton;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.key.HotkeyService.Type;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@Singleton
public class HotKeyController {

  private final GermanMinerAddon addon;

  public HotKeyController(final GermanMinerAddon addon) {
    this.addon = addon;

    register("engine", this.addon.configuration().vehicleEngineHotkey(), HotKey.ENGINE_SWITCH);
    register("siren", this.addon.configuration().vehicleSirenHotkey(), HotKey.SIREN_SWITCH);
    register("limiter_set", this.addon.configuration().vehicleLimiterSetHotkey(), HotKey.SPEEDLIMITER_SET);
    register("limiter_switch", this.addon.configuration().vehicleLimiterSwitchHotkey(), HotKey.SPEEDLIMITER_SWITCH);
  }

  private void register(final String id, final ConfigProperty<Key> key, final HotKey hotKey) {
    Laby.references().hotkeyService().register("germanminer_" + id, key, () -> Type.TOGGLE, active -> {
      if (this.addon.enabled()) {
        this.addon.sendPacket(new VehicleHotKeyPacket(hotKey));
      }
    });
  }

}
