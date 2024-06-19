package de.germanminer.addon;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;
import de.germanminer.addon.api.protocol.packet.balance.BankBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.CashBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.CompanyBalancePacket;
import de.germanminer.addon.api.protocol.packet.balance.ExtraBalancePacket;
import de.germanminer.addon.api.protocol.packet.level.LevelPacket;
import de.germanminer.addon.api.protocol.packet.level.LevelPointsPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.CompassPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.InputPromptPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.NotificationPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.PowerUpPacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.VotePacket;
import de.germanminer.addon.api.protocol.packet.miscellaneous.ZonePacket;
import de.germanminer.addon.api.protocol.packet.playtime.DailyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.DutyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.PaydayPacket;
import de.germanminer.addon.api.protocol.packet.playtime.TotalPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.playtime.WeeklyPlaytimePacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehicleDisplayPacket;
import de.germanminer.addon.api.protocol.packet.vehicle.VehiclePositionPacket;
import de.germanminer.addon.config.GermanMinerConfig;
import de.germanminer.addon.controller.HotKeyController;
import de.germanminer.addon.controller.VehicleController;
import de.germanminer.addon.core.generated.DefaultReferenceStorage;
import de.germanminer.addon.protocol.GermanMinerProtocol;
import de.germanminer.addon.protocol.handler.InputPromptPacketHandler;
import de.germanminer.addon.protocol.handler.NotificationPacketHandler;
import de.germanminer.addon.protocol.handler.VehiclePositionPacketHandler;
import de.germanminer.addon.protocol.translation.TranslationPacket;
import de.germanminer.addon.widgets.balance.BankBalanceWidget;
import de.germanminer.addon.widgets.balance.CashBalanceWidget;
import de.germanminer.addon.widgets.balance.CompanyBalanceWidget;
import de.germanminer.addon.widgets.balance.ExtraBalanceWidget;
import de.germanminer.addon.widgets.level.LevelPointsWidget;
import de.germanminer.addon.widgets.level.LevelWidget;
import de.germanminer.addon.widgets.miscellaneous.CalenderWidget;
import de.germanminer.addon.widgets.miscellaneous.CompassWidget;
import de.germanminer.addon.widgets.miscellaneous.PowerUpWidget;
import de.germanminer.addon.widgets.miscellaneous.VoteWidget;
import de.germanminer.addon.widgets.miscellaneous.ZoneWidget;
import de.germanminer.addon.widgets.playtime.DailyPlaytimeWidget;
import de.germanminer.addon.widgets.playtime.DutyPlaytimeWidget;
import de.germanminer.addon.widgets.playtime.PaydayWidget;
import de.germanminer.addon.widgets.playtime.TotalPlaytimeWidget;
import de.germanminer.addon.widgets.playtime.WeeklyPlaytimeWidget;
import de.germanminer.addon.widgets.vehicle.VehicleDisplayWidget;
import java.util.Arrays;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.serverapi.LabyModProtocolService;
import net.labymod.api.serverapi.TranslationProtocol;
import net.labymod.serverapi.api.AbstractProtocolService;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.payload.PayloadChannelIdentifier;
import org.jetbrains.annotations.Nullable;

@AddonMain
public class GermanMinerAddon extends LabyAddon<GermanMinerConfig> {

  private static GermanMinerAddon instance;
  private boolean online = false;
  private GermanMinerProtocol protocol;
  private HudWidgetCategory category;
  private VehicleDisplayWidget vehicleWidget;

  public static GermanMinerAddon getInstance() {
    return instance;
  }

  public static int getVersion() {
    return 2;
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
    this.category = new HudWidgetCategory(this, "gmGlobal");
    registry.categoryRegistry().register(this.category);

    new CashBalanceWidget(this, "gmCash").register(registry, this.protocol, CashBalancePacket.class);
    new BankBalanceWidget(this, "gmBank").register(registry, this.protocol, BankBalancePacket.class);
    new CompanyBalanceWidget(this, "gmCompany").register(registry, this.protocol, CompanyBalancePacket.class);
    new ExtraBalanceWidget(this, "gmExtra").register(registry, this.protocol, ExtraBalancePacket.class);

    new LevelWidget(this, "gmLevel").register(registry, this.protocol, LevelPacket.class);
    new LevelPointsWidget(this, "gmLevelPoints").register(registry, this.protocol, LevelPointsPacket.class);

    new DailyPlaytimeWidget(this, "gmPlayTimeDaily").register(registry, this.protocol, DailyPlaytimePacket.class);
    new WeeklyPlaytimeWidget(this, "gmPlayTimeWeekly").register(registry, this.protocol, WeeklyPlaytimePacket.class);
    new TotalPlaytimeWidget(this, "gmPlayTimeTotal").register(registry, this.protocol, TotalPlaytimePacket.class);
    new DutyPlaytimeWidget(this, "gmPlayTimeDuty").register(registry, this.protocol, DutyPlaytimePacket.class);
    new PaydayWidget(this, "gmPayday").register(registry, this.protocol, PaydayPacket.class);

    new CompassWidget(this, "gmCompass").register(registry, this.protocol, CompassPacket.class);
    new PowerUpWidget(this, "gmPowerUp").register(registry, this.protocol, PowerUpPacket.class);
    new VoteWidget(this, "gmVote").register(registry, this.protocol, VotePacket.class);
    new ZoneWidget(this, "gmZone").register(registry, this.protocol, ZonePacket.class);

    registry.register(new CalenderWidget(this, "gmCalender"));

    this.vehicleWidget = new VehicleDisplayWidget(this, "gmVehicle");
    this.vehicleWidget.register(registry, this.protocol, VehicleDisplayPacket.class);

    this.logger().info("[GermanMiner] Registering Features...");

    this.protocol.registerHandler(NotificationPacket.class, new NotificationPacketHandler());
    this.protocol.registerHandler(VehiclePositionPacket.class, new VehiclePositionPacketHandler());
    this.protocol.registerHandler(InputPromptPacket.class, new InputPromptPacketHandler());

    new HotKeyController(this);

    this.logger().info("[GermanMiner] Finished initialization.");
  }

  @Override
  protected Class<GermanMinerConfig> configurationClass() {
    return GermanMinerConfig.class;
  }

  public void sendPacket(final GermanMinerPacket packet) {
    this.protocol.sendPacket(AbstractProtocolService.DUMMY_UUID, packet);
  }

  public boolean enabled() {
    return this.online && this.configuration().enabled().get();
  }

  public void setOnline(final boolean online) {
    this.online = online;
  }

  public Protocol getProtocol() {
    return this.protocol;
  }

  public HudWidgetCategory getCategory() {
    return this.category;
  }

  public VehicleDisplayWidget getVehicleWidget() {
    return this.vehicleWidget;
  }

  @Nullable
  public VehicleController getVehicleController() {
    final DefaultReferenceStorage references = this.referenceStorageAccessor();
    return references.getVehicleController();
  }

}
