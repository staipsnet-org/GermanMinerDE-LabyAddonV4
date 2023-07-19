package de.germanminer.addon.widgets;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.widget.BalancePacket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.serverapi.protocol.packet.PacketHandler;

public class BalanceWidget extends TextHudWidget<TextHudWidgetConfig> implements PacketHandler<BalancePacket> {

  private final Icon hudWidgetIcon;
  private final DecimalFormat numberFormat;
  private TextLine cash;
  private TextLine bank;

  public BalanceWidget(final String id) {
    super(id);
    super.bindCategory(GermanMinerAddon.getInstance().getCategory());

    this.hudWidgetIcon = Icon.texture(ResourceLocation.create(
        "germanminer",
        "textures/bankcard.png"
    )).resolution(64, 64);

    this.numberFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
    this.numberFormat.applyPattern("#,##0.00");
  }

  public String format(final double amount) {
    return this.numberFormat.format(amount) + " Euro";
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    this.cash = super.createLine(Component.translatable(String.format("germanminer.hudWidget.%s.cash", super.getId())),
        Component.translatable("germanminer.hudWidget.loading"));
    this.bank = super.createLine(Component.translatable(String.format("germanminer.hudWidget.%s.bank", super.getId())),
        Component.translatable("germanminer.hudWidget.loading"));

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return GermanMinerAddon.getInstance().enabled();
  }

  @Override
  public void handle(final BalancePacket packet) {
    this.cash.updateAndFlush(packet.getCash() != null ? format(packet.getCash()) :
        Component.translatable("germanminer.hudWidget.error"));
    this.bank.updateAndFlush(packet.getBank() != null ? format(packet.getBank()) :
        Component.translatable("germanminer.hudWidget.error"));
  }

}
