package de.germanminer.addon;

import de.germanminer.addon.controller.HotKeyController;
import de.germanminer.addon.controller.VehicleController;
import de.germanminer.addon.core.generated.DefaultReferenceStorage;
import de.germanminer.addon.protocol.GermanMinerProtocol;
import de.germanminer.addon.protocol.TranslationListenerPackets;
import de.germanminer.addon.protocol.handler.InputPromptPacketHandler;
import de.germanminer.addon.protocol.handler.NotificationPacketHandler;
import de.germanminer.addon.protocol.handler.VehiclePositionPacketHandler;
import de.germanminer.addon.settings.AddonSetting;
import de.germanminer.addon.settings.AddonSubSetting;
import de.germanminer.addon.settings.BankSetting;
import de.germanminer.addon.settings.OntimeSetting;
import de.germanminer.addon.utils.AddonUtils;
import de.germanminer.addon.widgets.CalenderWidget;
import de.germanminer.addon.widgets.CompassWidget;
import de.germanminer.addon.widgets.PowerupWidget;
import de.germanminer.addon.widgets.VoteWidget;
import de.germanminer.addon.widgets.ZoneWidget;
import de.germanminer.addon.widgets.bank.BankBalanceWidget;
import de.germanminer.addon.widgets.bank.CashBalanceWidget;
import de.germanminer.addon.widgets.bank.CompanyBalanceWidget;
import de.germanminer.addon.widgets.bank.ExtraBalanceWidget;
import de.germanminer.addon.widgets.level.LevelPointsWidget;
import de.germanminer.addon.widgets.level.LevelWidget;
import de.germanminer.addon.widgets.ontime.DailyOntimeWidget;
import de.germanminer.addon.widgets.ontime.DutyOntimeWidget;
import de.germanminer.addon.widgets.ontime.PaydayWidget;
import de.germanminer.addon.widgets.ontime.TotalOntimeWidget;
import de.germanminer.addon.widgets.ontime.WeeklyOntimeWidget;
import de.germanminer.addon.widgets.vehicle.VehicleDisplayWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.serverapi.protocol.api.ProtocolApiBridge;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import org.jetbrains.annotations.Nullable;
import packets.GermanMinerPacket;
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
import packets.widget.ontime.DailyOntimePacket;
import packets.widget.ontime.DutyOntimePacket;
import packets.widget.ontime.PaydayPacket;
import packets.widget.ontime.TotalOntimePacket;
import packets.widget.ontime.WeeklyOntimePacket;

@AddonMain
public class GermanMinerAddon extends LabyAddon<AddonSetting> {

  private static final int VERSION = 2; // ToDo Version immer anpassen bei Releases + auf Haupt-Repo hinterlegen
  private static GermanMinerAddon instance;
  private HudWidgetCategory hudWidgetCategory;
  private VehicleDisplayWidget vehicleDisplayWidget;
  private ExtraBalanceWidget extraBalanceWidget;
  private CalenderWidget calenderWidget;
  private AddonUtils addonUtils;
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
    this.addonUtils = new AddonUtils(this);

    this.labyAPI().serverController().registerServer(new GermanMinerServer(this));
    new HotKeyController(this);

    ProtocolService protocolService = ProtocolApiBridge.getProtocolApi().getProtocolService();
    protocolService.registerCustomProtocol(new GermanMinerProtocol());

    HudWidgetRegistry hudWidgetRegistry = this.labyAPI().hudWidgetRegistry();
    this.hudWidgetCategory = new HudWidgetCategory(this, "global");
    hudWidgetRegistry.categoryRegistry().register(this.hudWidgetCategory);

    this.calenderWidget = new CalenderWidget("calender");
    hudWidgetRegistry.register(this.calenderWidget);

    this.registerSettingCategory(); // MUSS IMMER NACH DEM KALENDER WIDGET KOMMEN

    for (TranslationListenerPackets translationListenerPackets : TranslationListenerPackets.values()) {
      translationListenerPackets.registerListener(protocolService);
      //translationListenerProtocol.registerWidget(hudWidgetRegistry, protocolService);
      // ToDo: Debugnachricht ausgeben lassen
    }

    CashBalanceWidget cashBalanceWidget = new CashBalanceWidget("balanceCash");
    BankBalanceWidget bankBalanceWidget = new BankBalanceWidget("balanceBank");
    CompanyBalanceWidget companyBalanceWidget = new CompanyBalanceWidget("balanceCompany");
    hudWidgetRegistry.register(cashBalanceWidget);
    hudWidgetRegistry.register(bankBalanceWidget);
    hudWidgetRegistry.register(companyBalanceWidget);
    protocolService.registerPacketHandler(CashBalancePacket.class, cashBalanceWidget);
    protocolService.registerPacketHandler(BankBalancePacket.class, bankBalanceWidget);
    protocolService.registerPacketHandler(CompanyBalancePacket.class, companyBalanceWidget);

    LevelWidget levelWidget = new LevelWidget("level");
    LevelPointsWidget levelPointsWidget = new LevelPointsWidget("levelPoints");
    hudWidgetRegistry.register(levelWidget);
    hudWidgetRegistry.register(levelPointsWidget);
    protocolService.registerPacketHandler(LevelPacket.class, levelWidget);
    protocolService.registerPacketHandler(LevelPointsPacket.class, levelPointsWidget);

    DailyOntimeWidget dailyOntimeWidget = new DailyOntimeWidget("ontimeDaily");
    DutyOntimeWidget dutyOntimeWidget = new DutyOntimeWidget("ontimeDuty");
    PaydayWidget paydayWidget = new PaydayWidget("ontimePayday");
    TotalOntimeWidget totalOntimeWidget = new TotalOntimeWidget("ontimeTotal");
    WeeklyOntimeWidget weeklyOntimeWidget = new WeeklyOntimeWidget("ontimeWeekly");

    hudWidgetRegistry.register(dailyOntimeWidget);
    hudWidgetRegistry.register(dutyOntimeWidget);
    hudWidgetRegistry.register(paydayWidget);
    hudWidgetRegistry.register(totalOntimeWidget);
    hudWidgetRegistry.register(weeklyOntimeWidget);
    protocolService.registerPacketHandler(DailyOntimePacket.class, dailyOntimeWidget);
    protocolService.registerPacketHandler(DutyOntimePacket.class, dutyOntimeWidget);
    protocolService.registerPacketHandler(PaydayPacket.class, paydayWidget);
    protocolService.registerPacketHandler(TotalOntimePacket.class, totalOntimeWidget);
    protocolService.registerPacketHandler(WeeklyOntimePacket.class, weeklyOntimeWidget);

    CompassWidget compassWidget = new CompassWidget("compass");
    hudWidgetRegistry.register(compassWidget);
    protocolService.registerPacketHandler(CompassPacket.class, compassWidget);

    ZoneWidget zoneWidget = new ZoneWidget("zone");
    hudWidgetRegistry.register(zoneWidget);
    protocolService.registerPacketHandler(ZonePacket.class, zoneWidget);

    this.vehicleDisplayWidget = new VehicleDisplayWidget(this, "vehicle");
    hudWidgetRegistry.register(this.vehicleDisplayWidget);
    protocolService.registerPacketHandler(VehicleDisplayPacket.class, vehicleDisplayWidget);

    this.extraBalanceWidget = new ExtraBalanceWidget("balanceExtra");
    hudWidgetRegistry.register(this.extraBalanceWidget);
    protocolService.registerPacketHandler(ExtraBalancePacket.class, extraBalanceWidget);

    PowerupWidget powerupWidget = new PowerupWidget("powerup");
    hudWidgetRegistry.register(powerupWidget);
    protocolService.registerPacketHandler(PowerupPacket.class, powerupWidget);

    VoteWidget voteWidget = new VoteWidget("vote");
    hudWidgetRegistry.register(voteWidget);
    protocolService.registerPacketHandler(VotePacket.class, voteWidget);

    protocolService.registerPacketHandler(NotificationPacket.class,
        new NotificationPacketHandler());
    protocolService.registerPacketHandler(InputPromptPacket.class, new InputPromptPacketHandler());
    protocolService.registerPacketHandler(VehiclePositionPacket.class,
        new VehiclePositionPacketHandler());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<AddonSetting> configurationClass() {
    return AddonSetting.class;
  }


  public void sendPacket(GermanMinerPacket packet) {
    if (!enabled())
      return;

    ProtocolApiBridge.getProtocolApi().getProtocolService()
        .getProtocol(PayloadChannelIdentifier.create("labymod", "germanminer")).sendPacket(packet);
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

  public AddonUtils getAddonUtils() {
    return this.addonUtils;
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