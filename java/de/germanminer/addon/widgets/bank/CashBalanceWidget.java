package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.balance.CashBalancePacket;

/**
 * Widget zum Anzeigen vom Bargeld
 */
public class CashBalanceWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<CashBalancePacket> {

  private final Icon icon;
  private TextLine cash;

  public CashBalanceWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.icon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/euro.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    cash = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(icon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .isBankEnabled();
  }

  @Override
  public void handle(CashBalancePacket packet) {
    cash.updateAndFlush(packet.getCash() != null ? GermanMinerAddon.getInstance().getAddonUtils()
        .formatAmount(packet.getCash())
        : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}