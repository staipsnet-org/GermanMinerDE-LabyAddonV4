package de.germanminer.addon;

import de.germanminer.addon.controller.HotKeyController;
import de.germanminer.addon.controller.VehicleController;
import de.germanminer.addon.core.generated.DefaultReferenceStorage;
import de.germanminer.addon.protocol.GermanMinerProtocol;
import de.germanminer.addon.protocol.handler.InputPromptPacketHandler;
import de.germanminer.addon.protocol.handler.NotificationPacketHandler;
import de.germanminer.addon.protocol.handler.VehiclePositionPacketHandler;
import de.germanminer.addon.protocol.translation.TranslationPacket;
import de.germanminer.addon.settings.AddonSetting;
import de.germanminer.addon.widgets.bank.BankBalanceWidget;
import de.germanminer.addon.widgets.bank.CashBalanceWidget;
import de.germanminer.addon.widgets.bank.CompanyBalanceWidget;
import de.germanminer.addon.widgets.bank.ExtraBalanceWidget;
import de.germanminer.addon.widgets.level.LevelPointsWidget;
import de.germanminer.addon.widgets.level.LevelWidget;
import de.germanminer.addon.widgets.miscellaneous.CalenderWidget;
import de.germanminer.addon.widgets.miscellaneous.CompassWidget;
import de.germanminer.addon.widgets.miscellaneous.PowerupWidget;
import de.germanminer.addon.widgets.miscellaneous.VoteWidget;
import de.germanminer.addon.widgets.miscellaneous.ZoneWidget;
import de.germanminer.addon.widgets.ontime.*;
import de.germanminer.addon.widgets.vehicle.VehicleDisplayWidget;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.serverapi.LabyModProtocolService;
import net.labymod.api.serverapi.TranslationProtocol;
import net.labymod.serverapi.api.AbstractProtocolService;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.PayloadChannelIdentifier;
import org.jetbrains.annotations.Nullable;
import packets.special.InputPromptPacket;
import packets.special.NotificationPacket;
import packets.vehicle.VehicleDisplayPacket;
import packets.vehicle.VehiclePositionPacket;
import packets.widget.CompassPacket;
import packets.widget.PowerupPacket;
import packets.widget.VotePacket;
import packets.widget.ZonePacket;
import packets.widget.balance.BankBalancePacket;
import packets.widget.balance.CashBalancePacket;
import packets.widget.balance.CompanyBalancePacket;
import packets.widget.balance.ExtraBalancePacket;
import packets.widget.level.LevelPacket;
import packets.widget.level.LevelPointsPacket;
import packets.widget.ontime.*;
import java.util.Arrays;

@AddonMain
public class GermanMinerAddon extends LabyAddon<AddonSetting> {

  private static final int VERSION = 2; // ToDo Version immer anpassen bei Releases + auf Haupt-Repo hinterlegen
  private static GermanMinerAddon instance;
  private HudWidgetCategory hudWidgetCategory;
  private VehicleDisplayWidget vehicleDisplayWidget;
  private ExtraBalanceWidget extraBalanceWidget;
  private CalenderWidget calenderWidget;
  private GermanMinerProtocol protocol;
  private boolean online = false;

  public static GermanMinerAddon getInstance() {
    return instance;
  }

  public static int getVersion() {
    return VERSION;
  }

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.logger().info("[GermanMiner] Initialising...");

    instance = this;
    this.labyAPI().serverController().registerServer(new GermanMinerServer(this));

    this.logger().info("[GermanMiner] Registering Protocol...");

    final LabyModProtocolService protocolService = Laby.references().labyModProtocolService();

    this.protocol = new GermanMinerProtocol(protocolService);

    final TranslationProtocol translationProtocol = new TranslationProtocol(
        PayloadChannelIdentifier.create("labymod3", "main"),
        this.protocol
    );

    protocolService.registry().registerProtocol(this.protocol);
    protocolService.translationRegistry().register(translationProtocol);

    Arrays.stream(TranslationPacket.values()).forEach(translation -> translation.register(translationProtocol));

    this.logger().info("[GermanMiner] Registering Widgets...");

    final HudWidgetRegistry registry = this.labyAPI().hudWidgetRegistry();
    this.hudWidgetCategory = new HudWidgetCategory(this, "global");
    registry.categoryRegistry().register(this.hudWidgetCategory);

    new CashBalanceWidget(this, "balance.cash").register(registry, this.protocol, CashBalancePacket.class);
    new BankBalanceWidget(this, "balance.bank").register(registry, this.protocol, BankBalancePacket.class);
    new CompanyBalanceWidget(this, "balance.company").register(registry, this.protocol, CompanyBalancePacket.class);
    new ExtraBalanceWidget(this, "balance.gmExtra").register(registry, this.protocol, ExtraBalancePacket.class);

    new LevelWidget(this, "level").register(registry, this.protocol, LevelPacket.class);
    new LevelPointsWidget(this, "levelPoints").register(registry, this.protocol, LevelPointsPacket.class);

    new DailyOntimeWidget(this, "ontimeDaily").register(registry, this.protocol, DailyOntimePacket.class);
    new WeeklyOntimeWidget(this, "ontimeWeekly").register(registry, this.protocol, WeeklyOntimePacket.class);
    new TotalOntimeWidget(this, "ontimeTotal").register(registry, this.protocol, TotalOntimePacket.class);
    new DutyOntimeWidget(this, "ontimeDuty").register(registry, this.protocol, DutyOntimePacket.class);
    new PaydayWidget(this, "ontimePayday").register(registry, this.protocol, PaydayPacket.class);

    new CompassWidget(this, "compass").register(registry, this.protocol, CompassPacket.class);
    new PowerupWidget(this, "powerup").register(registry, this.protocol, PowerupPacket.class);
    new VoteWidget(this, "vote").register(registry, this.protocol, VotePacket.class);
    new ZoneWidget(this, "zone").register(registry, this.protocol, ZonePacket.class);

    registry.register(new CalenderWidget("calender"));

    this.vehicleDisplayWidget = new VehicleDisplayWidget(this, "vehicle");
    this.vehicleDisplayWidget.register(registry, this.protocol, VehicleDisplayPacket.class);

    this.logger().info("[GermanMiner] Registering Features...");

    this.protocol.registerHandler(NotificationPacket.class, new NotificationPacketHandler());
    this.protocol.registerHandler(VehiclePositionPacket.class, new VehiclePositionPacketHandler());
    this.protocol.registerHandler(InputPromptPacket.class, new InputPromptPacketHandler());

    new HotKeyController(this);

    this.logger().info("[GermanMiner] Finished initialization.");
  }

  @Override
  protected Class<AddonSetting> configurationClass() {
    return AddonSetting.class;
  }

  public void sendPacket(Packet packet) {
    if (!enabled()) {
      return;
    }
    this.protocol.sendPacket(AbstractProtocolService.DUMMY_UUID, packet);
  }

  public boolean enabled() {
    return this.online && this.configuration().enabled().get();
  }

  public AddonSetting getSetting() {
    return this.configuration();
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  @Nullable
  public VehicleController getVehicleController() {
    DefaultReferenceStorage defaultReferenceStorage = this.referenceStorageAccessor();
    return defaultReferenceStorage.getVehicleController();
  }

  public VehicleDisplayWidget getVehicleDisplayWidget() {
    return vehicleDisplayWidget;
  }

  public CalenderWidget getCalenderWidget() {
    return calenderWidget;
  }

  public HudWidgetCategory getHudWidgetCategory() {
    return hudWidgetCategory;
  }
}