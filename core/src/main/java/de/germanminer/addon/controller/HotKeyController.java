package de.germanminer.addon.controller;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.settings.VehicleSetting;
import net.labymod.api.Laby;
import net.labymod.api.client.gui.screen.key.HotkeyService.Type;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import packets.vehicle.VehicleHotKeyPacket;
import vehicle.HotKey;

import javax.inject.Singleton;

@Singleton
public class HotKeyController {

  private final GermanMinerAddon germanMinerAddon;

  public HotKeyController(GermanMinerAddon germanMinerAddon) {
    this.germanMinerAddon = germanMinerAddon;
    VehicleSetting vehicleSetting = new VehicleSetting();

    register("engine", vehicleSetting.getVehicleEngineHotkey(), HotKey.ENGINE_SWITCH);
    register("siren", vehicleSetting.getVehicleSirenHotkey(), HotKey.SIREN_SWITCH);
    register("limiter_set", vehicleSetting.getVehicleLimiterSetHotkey(), HotKey.SPEED_LIMITER_SET);
    register("limiter_switch", vehicleSetting.getVehicleLimiterSwitchHotkey(),
        HotKey.SPEED_LIMITER_SWITCH);
  }

  private void register(String id, ConfigProperty<Key> key, HotKey hotKey) {
    Laby.references().hotkeyService()
        .register("germanminer_" + id, key, () -> Type.TOGGLE, active -> {
          if (this.germanMinerAddon.enabled() && this.germanMinerAddon.getSetting()
              .isHotkeyEnabled()) {
            this.germanMinerAddon.sendPacket(new VehicleHotKeyPacket(hotKey));
          }
        });
  }
}