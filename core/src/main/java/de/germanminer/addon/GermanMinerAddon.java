package de.germanminer.addon;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.protocol.packet.info.AddonInfoPacket;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import de.germanminer.addon.api.protocol.packet.special.NotificationPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleHotKeyPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.api.protocol.packet.widget.BalancePacket;
import de.germanminer.addon.api.protocol.packet.widget.LevelPacket;
import de.germanminer.addon.controller.GermanMinerController;
import de.germanminer.addon.controller.HotKeyController;
import de.germanminer.addon.controller.VehicleController;
import de.germanminer.addon.core.generated.DefaultReferenceStorage;
import de.germanminer.addon.protocol.GermanMinerProtocol;
import de.germanminer.addon.protocol.GermanMinerProtocolOld;
import de.germanminer.addon.protocol.handler.NotificationPacketHandler;
import de.germanminer.addon.protocol.handler.TempVehicleDisplayPacketHandler;
import de.germanminer.addon.protocol.handler.VehiclePositionPacketHandler;
import de.germanminer.addon.protocol.translation.GermanMinerPayloadTranslationListener;
import de.germanminer.addon.protocol.translation.GermanMinerPayloadTranslationListenerOld;
import de.germanminer.addon.protocol.translation.TranslationSide;
import de.germanminer.addon.widgets.BalanceWidget;
import de.germanminer.addon.widgets.LevelWidget;
import java.util.Arrays;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.serverapi.protocol.api.ProtocolApiBridge;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import org.jetbrains.annotations.Nullable;

@AddonMain
public class GermanMinerAddon extends LabyAddon<GermanMinerConfig> {

  private static GermanMinerAddon instance;
  private boolean online = false;
  private HudWidgetCategory category;
  private GermanMinerProtocolOld protocol;

  public static GermanMinerAddon getInstance() {
    return instance;
  }

  public static int getVersion() {
    return 2;
  }

  // ToDo: PacketHandler for InputPrompt, VehicleDisplay,

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.logger().info("[GermanMiner] Initialising...");

    instance = this;
    this.labyAPI().serverController().registerServer(new GermanMinerServer(this));

    final ProtocolService protocolService = ProtocolApiBridge.getProtocolApi().getProtocolService();
    protocolService.registerCustomProtocol(new GermanMinerProtocol());
    this.protocol = new GermanMinerProtocolOld();

    Arrays.asList(
        new GermanMinerPayloadTranslationListener(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),

        new GermanMinerPayloadTranslationListener(BalancePacket.class, "gmde-balance", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(LevelPacket.class, "gmde-level", TranslationSide.INCOMING),

        new GermanMinerPayloadTranslationListener(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
        new GermanMinerPayloadTranslationListener(VehiclePositionPacket.class, "gmde-vehicle-position", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING),

        new GermanMinerPayloadTranslationListener(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListener(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH)
    ).forEach(protocolService::registerTranslationListener);

    Arrays.asList(
        new GermanMinerPayloadTranslationListenerOld(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),

        new GermanMinerPayloadTranslationListenerOld(BalancePacket.class, "gmde-balance", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListenerOld(LevelPacket.class, "gmde-level", TranslationSide.INCOMING),

        new GermanMinerPayloadTranslationListenerOld(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
        new GermanMinerPayloadTranslationListenerOld(VehiclePositionPacket.class, "gmde-vehicle-position", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListenerOld(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING),

        new GermanMinerPayloadTranslationListenerOld(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
        new GermanMinerPayloadTranslationListenerOld(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH)
    ).forEach(protocolService::registerTranslationListener);

    this.logger().info("[GermanMiner] Registering Widgets...");

    final HudWidgetRegistry registry = this.labyAPI().hudWidgetRegistry();
    this.category = new HudWidgetCategory(this, "global");
    registry.categoryRegistry().register(this.category);

    final BalanceWidget balanceWidget = new BalanceWidget("balance");
    protocolService.registerPacketHandler(BalancePacket.class, balanceWidget);
    this.protocol.registerPacketHandler(BalancePacket.class, balanceWidget);
    registry.register(balanceWidget);
    final LevelWidget levelWidget = new LevelWidget("level");
    protocolService.registerPacketHandler(LevelPacket.class, levelWidget);
    this.protocol.registerPacketHandler(LevelPacket.class, levelWidget);
    registry.register(levelWidget);

    // ToDo: remove again later
    protocolService.registerPacketHandler(VehicleDisplayPacket.class, new TempVehicleDisplayPacketHandler());
    this.protocol.registerPacketHandler(VehicleDisplayPacket.class, new TempVehicleDisplayPacketHandler());

    //vehicleDisplayModule = new VehicleDisplayModule(this);
    //this.getApi().registerModule(vehicleDisplayModule);

    this.logger().info("[GermanMiner] Registering Features...");

    protocolService.registerPacketHandler(VehicleDisplayPacket.class, new NotificationPacketHandler());
    this.protocol.registerPacketHandler(VehicleDisplayPacket.class, new NotificationPacketHandler());

    protocolService.registerPacketHandler(VehiclePositionPacket.class, new VehiclePositionPacketHandler());
    this.protocol.registerPacketHandler(VehiclePositionPacket.class, new VehiclePositionPacketHandler());

    new HotKeyController(this);

    //GuiTextboxPrompt.initialize();
    this.logger().info("[GermanMiner] Finished initialization.");
  }

  @Override
  protected Class<GermanMinerConfig> configurationClass() {
    return GermanMinerConfig.class;
  }

  public void sendPacket(final GermanMinerPacket packet) {
    ProtocolApiBridge.getProtocolApi().getProtocolService().getProtocol(
        PayloadChannelIdentifier.create("labymod", "germanminer")).sendPacket(packet);

    final DefaultReferenceStorage references = this.referenceStorageAccessor();
    final GermanMinerController controller = references.getGermanMinerController();

    if (controller == null) {
      return;
    }

    final byte[] bytes = this.protocol.writePacketToBinary(packet);

    if (bytes == null) {
      return;
    }

    controller.sendPayload("LMC", bytes);
  }

  public boolean enabled() {
    return this.online && this.configuration().enabled().get();
  }

  public void setOnline(final boolean online) {
    this.online = online;
  }

  public HudWidgetCategory getCategory() {
    return this.category;
  }

  @Nullable
  public VehicleController getVehicleController() {
    final DefaultReferenceStorage references = this.referenceStorageAccessor();
    return references.getVehicleController();
  }

}
