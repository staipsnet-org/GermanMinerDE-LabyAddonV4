package de.germanminer.addon;

import de.germanminer.addon.controller.VehicleController;
import de.germanminer.addon.core.generated.DefaultReferenceStorage;
import de.germanminer.addon.controller.InputPromptController;
import de.germanminer.addon.protocol.handler.InputPromptPacketHandler;
import de.germanminer.addon.protocol.handler.NotificationPacketHandler;
import de.germanminer.addon.protocol.handler.VehiclePositionPacketHandler;
import de.germanminer.addon.widgets.BalanceWidget;
import de.germanminer.addon.widgets.LevelWidget;
import de.germanminer.addon.protocol.GermanMinerPayloadTranslationListener;
import de.germanminer.addon.protocol.GermanMinerProtocol;
import de.germanminer.addon.protocol.TranslationSide;
import java.util.Arrays;
import de.germanminer.addon.widgets.vehicle.VehicleDisplayWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.serverapi.protocol.api.ProtocolApiBridge;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import org.jetbrains.annotations.Nullable;
import protocol.packet.GermanMinerPacket;
import protocol.packet.info.AddonInfoPacket;
import protocol.packet.info.ServerAddonInfoPacket;
import protocol.packet.special.InputPromptPacket;
import protocol.packet.special.NotificationPacket;
import protocol.packet.vehicle.VehicleDisplayPacket;
import protocol.packet.vehicle.VehicleHotKeyPacket;
import protocol.packet.vehicle.VehiclePositionPacket;
import protocol.packet.widget.BalancePacket;
import protocol.packet.widget.LevelPacket;

@AddonMain
public class GermanMinerAddon extends LabyAddon<GermanMinerConfiguration> {

  private static final int VERSION = 2; // ToDo Version immer anpassen bei Releases + auf Haupt-Repo hinterlegen
  private static GermanMinerAddon instance;
  private HudWidgetCategory hudWidgetCategory;
  private VehicleDisplayWidget vehicleDisplayWidget;
  private boolean online = false;

  public static GermanMinerAddon getInstance() {
    return instance;
  }

  public static int getVersion() {
    return VERSION;
  }

  @Override
  protected void enable() {
    instance = this;
    this.registerSettingCategory();

    this.labyAPI().serverController().registerServer(new GermanMinerServer(this));

    ProtocolService protocolService = ProtocolApiBridge.getProtocolApi().getProtocolService();
    protocolService.registerCustomProtocol(new GermanMinerProtocol());

    Arrays.asList(
        new GermanMinerPayloadTranslationListener(ServerAddonInfoPacket.class, "INFO", TranslationSide.OUTGOING),
        new GermanMinerPayloadTranslationListener(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),
        new GermanMinerPayloadTranslationListener(BalancePacket.class, "gmde-balance", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(LevelPacket.class, "gmde-level", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH),
        new GermanMinerPayloadTranslationListener(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
        new GermanMinerPayloadTranslationListener(VehiclePositionPacket.class, "gmde-vehicle-position", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING)
    ).forEach(protocolService::registerTranslationListener);

    HudWidgetRegistry hudWidgetRegistry = this.labyAPI().hudWidgetRegistry();
    this.hudWidgetCategory = new HudWidgetCategory(this, "global");
    hudWidgetRegistry.categoryRegistry().register(this.hudWidgetCategory);

    BalanceWidget balanceWidget = new BalanceWidget("balance");
    protocolService.registerPacketHandler(BalancePacket.class, balanceWidget);
    hudWidgetRegistry.register(balanceWidget);

    LevelWidget levelWidget = new LevelWidget("level");
    protocolService.registerPacketHandler(LevelPacket.class, levelWidget);
    hudWidgetRegistry.register(levelWidget);

    this.vehicleDisplayWidget = new VehicleDisplayWidget(this, "vehicle");
    protocolService.registerPacketHandler(VehicleDisplayPacket.class, this.vehicleDisplayWidget);
    hudWidgetRegistry.register(this.vehicleDisplayWidget);

    protocolService.registerPacketHandler(NotificationPacket.class, new NotificationPacketHandler());
    protocolService.registerPacketHandler(InputPromptPacket.class, new InputPromptPacketHandler());
    protocolService.registerPacketHandler(VehiclePositionPacket.class, new VehiclePositionPacketHandler());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<GermanMinerConfiguration> configurationClass() {
    return GermanMinerConfiguration.class;
  }


  public void sendPacket(GermanMinerPacket packet) {
    ProtocolApiBridge.getProtocolApi().getProtocolService()
        .getProtocol(PayloadChannelIdentifier.create("labymod", "germanminer")).sendPacket(packet);
  }

  public boolean enabled() {
    return this.online && this.configuration().enabled().get();
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  @Nullable
  public InputPromptController getInputPromptController() {
    final DefaultReferenceStorage defaultReferenceStorage = this.referenceStorageAccessor();
    return defaultReferenceStorage.getInputPromptController();
  }

  @Nullable
  public VehicleController getVehicleController() {
    DefaultReferenceStorage defaultReferenceStorage = this.referenceStorageAccessor();
    return defaultReferenceStorage.getVehicleController();
  }

  public VehicleDisplayWidget getVehicleDisplayWidget() {
    return vehicleDisplayWidget;
  }

  public HudWidgetCategory getHudWidgetCategory() {
    return hudWidgetCategory;
  }
}
