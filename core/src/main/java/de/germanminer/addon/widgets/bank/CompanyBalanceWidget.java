package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.balance.CompanyBalancePacket;

/**
 * Widget zum Anzeigen vom Kontostand der Firma
 */
public class CompanyBalanceWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<CompanyBalancePacket> {

  private final Icon icon;
  private TextLine companyCash;

  public CompanyBalanceWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.icon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/company.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    companyCash = super.createLine(
        Component.translatable(
            String.format("germanmineraddon.hudWidget.%s.name", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(icon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .isBankEnabled() && GermanMinerAddon.getInstance().getSetting().getBankSetting()
        .isCompanyEnabled();
  }

  @Override
  public void handle(CompanyBalancePacket packet) {
    companyCash.updateAndFlush(
        packet.getCompany() != null ? GermanMinerAddon.getInstance().getAddonUtils()
            .formatAmount(packet.getCompany())
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}