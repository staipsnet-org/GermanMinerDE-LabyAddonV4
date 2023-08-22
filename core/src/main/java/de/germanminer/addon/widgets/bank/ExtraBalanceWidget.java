package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.widget.balance.ExtraBalancePacket;

/**
 * Widget zum Anzeigen vom Kontostand von 3 Zusatzkonten
 */
public class ExtraBalanceWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<ExtraBalancePacket> {

  private final Icon icon;
  private TextLine firstAccount;
  private TextLine secondAccount;
  private TextLine thirdAccount;

  public ExtraBalanceWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.icon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/extra.png"))
        .resolution(64, 64);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    firstAccount = super.createLine(
        Component.text(GermanMinerAddon.getInstance().getSetting().getBankSetting()
            .getFirstCustomAccount()),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    secondAccount = super.createLine(
        Component.text(GermanMinerAddon.getInstance().getSetting().getBankSetting()
            .getSecondCustomAccount()),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    thirdAccount = super.createLine(
        Component.text(GermanMinerAddon.getInstance().getSetting().getBankSetting()
            .getThirdCustomAccount()),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(icon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled() && GermanMinerAddon.getInstance().getSetting()
        .getBankSetting().isExtraEnabled();
  }

  @Override
  public void handle(ExtraBalancePacket packet) {
    String firstAccountNumber = GermanMinerAddon.getInstance().getSetting().getBankSetting()
        .getFirstCustomAccount();
    String secondAccountNumber = GermanMinerAddon.getInstance().getSetting().getBankSetting()
        .getSecondCustomAccount();
    String thirdAccountNumber = GermanMinerAddon.getInstance().getSetting().getBankSetting()
        .getThirdCustomAccount();

    firstAccount.updateAndFlush(
        packet.getCash(firstAccountNumber) != null ? GermanMinerAddon.getInstance().getAddonUtils()
            .formatAmount(packet.getCash(firstAccountNumber))
            : Component.translatable("germanmineraddon.hudWidget.error"));

    secondAccount.updateAndFlush(
        packet.getCash(secondAccountNumber) != null ? GermanMinerAddon.getInstance().getAddonUtils()
            .formatAmount(packet.getCash(secondAccountNumber))
            : Component.translatable("germanmineraddon.hudWidget.error"));

    thirdAccount.updateAndFlush(
        packet.getCash(thirdAccountNumber) != null ? GermanMinerAddon.getInstance().getAddonUtils()
            .formatAmount(packet.getCash(thirdAccountNumber))
            : Component.translatable("germanmineraddon.hudWidget.error"));
  }
}