package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import protocol.packet.widget.BalancePacket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BankBalanceWidget extends TextHudWidget<TextHudWidgetConfig> implements
    PacketHandler<BalancePacket> {

  private final Icon icon;
  private final DecimalFormat decimalFormat;
  private TextLine bank;

  public BankBalanceWidget(String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getHudWidgetCategory());

    this.icon = Icon.texture(ResourceLocation.create("germanmineraddon", "textures/bankcard.png"))
        .resolution(64, 64);

    this.decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
    this.decimalFormat.applyPattern("#,##0.00");
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    bank = super.createLine(
        Component.translatable(String.format("germanmineraddon.hudWidget.%s.bank", super.getId())),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    super.setIcon(icon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(BalancePacket packet) {
    bank.updateAndFlush(packet.getCash() != null ? format(packet.getBank())
        : Component.translatable("germanmineraddon.hudWidget.error"));
  }

  private String format(double amount) {
    return this.decimalFormat.format(amount) + " Euro";
  }
}