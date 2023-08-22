package de.germanminer.addon.protocol;

import de.germanminer.addon.utils.GMWidget;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import org.reflections.Reflections;
import packets.GermanMinerPacket;
import packets.info.AddonInfoPacket;
import packets.info.ServerAddonInfoPacket;
import packets.special.InputPromptPacket;
import packets.special.NotificationPacket;
import packets.vehicle.VehicleDisplayPacket;
import packets.vehicle.VehicleHotKeyPacket;
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

public enum TranslationListenerProtocol {
  INFO_ADDON_SERVER(ServerAddonInfoPacket.class, "INFO",
      TranslationSide.OUTGOING), // ToDo: Deprecated
  INFO_ADDON_SETTING(AddonSettingsPacket.class, "gmde-addon-settings", TranslationSide.OUTGOING),
  INFO_ADDON(AddonInfoPacket.class, "gmde-addon-info", TranslationSide.OUTGOING),
  WIDGET_CASH(CashBalancePacket.class, "gmde-balance-cash", TranslationSide.INCOMING,
      "balance", "CashBalanceWidget"),
  WIDGET_BANK(BankBalancePacket.class, "gmde-balance-bank", TranslationSide.INCOMING,
      "balance", "BankBalanceWidget"),
  WIDGET_BANK_COMPANY(CompanyBalancePacket.class, "gmde-balance-company", TranslationSide.INCOMING),
  WIDGET_BANK_EXTRA(ExtraBalancePacket.class, "gmde-balance-extra", TranslationSide.INCOMING),
  WIDGET_LEVEL(LevelPacket.class, "gmde-level", TranslationSide.INCOMING, "level", "LevelWidget"),
  WIDGET_LEVEL_POINTS(LevelPointsPacket.class, "gmde-levelpoints", TranslationSide.INCOMING,
      "level", "LevelPointsWidget"),
  WIDGET_DAILY_ONTIME(DailyOntimePacket.class, "gmde-ontime-daily", TranslationSide.INCOMING,
      "ontime", "DailyOntimeWidget"),
  WIDGET_WEEKLY_ONTIME(WeeklyOntimePacket.class, "gmde-ontime-weekly", TranslationSide.INCOMING,
      "ontime", "WeeklyOntimeWidget"),
  WIDGET_TOTAL_ONTIME(TotalOntimePacket.class, "gmde-ontime-total", TranslationSide.INCOMING,
      "ontime", "TotalOntimeWidget"),
  WIDGET_DUTY_ONTIME(DutyOntimePacket.class, "gmde-ontime-duty", TranslationSide.INCOMING,
      "ontime", "DutyOntimeWidget"),
  WIDGET_PAYDAY(PaydayPacket.class, "gmde-ontime-payday", TranslationSide.INCOMING,
      "ontime", "PaydayWidget"),
  WIDGET_VEHICLE_DISPLAY(VehicleDisplayPacket.class, "gmde-vehicle-display", TranslationSide.BOTH),
  WIDGET_VEHICLE_POSITION(VehiclePositionPacket.class, "gmde-vehicle-position",
      TranslationSide.INCOMING),
  WIDGET_VEHICLE_HOTKEY(VehicleHotKeyPacket.class, "gmde-vehicle-hotkey", TranslationSide.OUTGOING),
  WIDGET_COMPASS(CompassPacket.class, "gmde-compass", TranslationSide.INCOMING,
      "compass", "CompassWidget"),
  WIDGET_POWERUP_COOLDOWN(PowerupPacket.class, "gmde-powerup", TranslationSide.INCOMING),
  WIDGET_ZONE(ZonePacket.class, "gmde-zone", TranslationSide.INCOMING, "zone", "ZoneWidget"),
  WIDGET_VOTE(VotePacket.class, "gmde-vote", TranslationSide.INCOMING),
  OTHER_NOTIFICATION(NotificationPacket.class, "gmde-notification", TranslationSide.INCOMING),
  OTHER_INPUT(InputPromptPacket.class, "gmde-input-prompt", TranslationSide.BOTH);

  private final Class<? extends GermanMinerPacket> packet;
  private final String widgetSettingName;
  private final String widgetClassName;
  private final String oldMessageKey;
  private final TranslationSide translationSide;

  TranslationListenerProtocol(Class<? extends GermanMinerPacket> packet, String oldMessageKey,
      TranslationSide translationSide, String widgetSettingName, String widgetClassName) {
    this.packet = packet;
    this.oldMessageKey = oldMessageKey;
    this.translationSide = translationSide;
    this.widgetSettingName = widgetSettingName;
    this.widgetClassName = widgetClassName;
  }

  TranslationListenerProtocol(Class<? extends GermanMinerPacket> packet, String oldMessageKey,
      TranslationSide translationSide) {
    this(packet, oldMessageKey, translationSide, null, null);
  }

  public void registerListener(ProtocolService protocolService) {
    protocolService.registerTranslationListener(
        new PayloadTranslationListener(packet, oldMessageKey, translationSide));
  }

  public void registerWidget(HudWidgetRegistry hudWidgetRegistry, ProtocolService protocolService) {
    Reflections reflections = new Reflections("de.germanminer.addon");
    for (Class<?> gmWidgetClass : reflections.getTypesAnnotatedWith(GMWidget.class)) {
      TextHudWidget<TextHudWidgetConfig> widget = createWidgetInstance(gmWidgetClass,
          this.widgetSettingName);

      hudWidgetRegistry.register(widget);
      protocolService.registerPacketHandler(this.packet, (PacketHandler<?>) widget);
    }
  }

  private TextHudWidget<TextHudWidgetConfig> createWidgetInstance(Class<?> widgetClass,
      String widgetName) {
    try {
      return (TextHudWidget<TextHudWidgetConfig>) widgetClass.getConstructor(String.class)
          .newInstance(widgetName);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}