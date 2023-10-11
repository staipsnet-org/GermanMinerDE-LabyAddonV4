package de.germanminer.addon.widgets.balance;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.balance.ExtraBalancePacket;
import de.germanminer.addon.widgets.WidgetIcon;
import de.germanminer.addon.widgets.WidgetRegistry;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;

// I'm not very happy with this current solution
public class ExtraBalanceWidget extends TextHudWidget<TextHudWidgetConfig>
    implements PacketHandler<ExtraBalancePacket>, WidgetRegistry<ExtraBalancePacket> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private TextLine firstAccount;
  private String firstAccountValue;
  private TextLine secondAccount;
  private String secondAccountValue;
  private TextLine thirdAccount;
  private String thirdAccountValue;

  public ExtraBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.BALANCE_BANK_EXTRA.getIcon();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    updateName();
    updateVisibility();
    addChangeListener();

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.addon.configuration().balanceConfig().enabled().get()
        && this.addon.configuration().balanceConfig().extra().get();
  }

  @Override
  public void handle(final ExtraBalancePacket packet) {
    final String firstAccount = this.addon.configuration().balanceConfig().firstExtraAccount().get();
    final String secondAccount = this.addon.configuration().balanceConfig().secondExtraAccount().get();
    final String thirdAccount = this.addon.configuration().balanceConfig().thirdExtraAccount().get();

    if (packet.getCash(firstAccount) != null) {
      this.firstAccountValue = BalanceFormatter.getInstance().format(packet.getCash(firstAccount));
      this.firstAccount.updateAndFlush(this.firstAccountValue);
    }

    if (packet.getCash(secondAccount) != null) {
      this.secondAccountValue = BalanceFormatter.getInstance().format(packet.getCash(secondAccount));
      this.secondAccount.updateAndFlush(this.secondAccountValue);
    }

    if (packet.getCash(thirdAccount) != null) {
      this.thirdAccountValue = BalanceFormatter.getInstance().format(packet.getCash(thirdAccount));
      this.thirdAccount.updateAndFlush(this.thirdAccountValue);
    }

    updateVisibility();
  }

  @Override
  public void register(final HudWidgetRegistry registry, final ProtocolService protocol,
      final Class<ExtraBalancePacket> packetClass) {
    registry.register(this);
    protocol.registerPacketHandler(packetClass, this);
  }

  public void updateName() {
    this.lines.remove(this.firstAccount);
    this.lines.remove(this.secondAccount);
    this.lines.remove(this.thirdAccount);

    final Component defaultName = Component.translatable(String.format("germanminer.hudWidget.%s.name", super.getId()));

    final String firstAccount = this.addon.configuration().balanceConfig().firstExtraAccount().get();
    final String secondAccount = this.addon.configuration().balanceConfig().secondExtraAccount().get();
    final String thirdAccount = this.addon.configuration().balanceConfig().thirdExtraAccount().get();

    this.firstAccount = super.createLine(firstAccount.isEmpty() ? defaultName : Component.text(firstAccount),
        this.firstAccountValue == null ? Component.translatable("germanminer.hudWidget.loading")
            : Component.text(this.firstAccountValue));
    this.secondAccount = super.createLine(secondAccount.isEmpty() ? defaultName : Component.text(secondAccount),
        this.secondAccountValue == null ? Component.translatable("germanminer.hudWidget.loading")
            : Component.text(this.secondAccountValue));
    this.thirdAccount = super.createLine(thirdAccount.isEmpty() ? defaultName : Component.text(thirdAccount),
        this.thirdAccountValue == null ? Component.translatable("germanminer.hudWidget.loading")
            : Component.text(this.thirdAccountValue));
  }

  private void updateVisibility() {
    final boolean firstEmpty = this.addon.configuration().balanceConfig()
        .firstExtraAccount().get().isEmpty();
    final boolean secondEmpty = this.addon.configuration().balanceConfig()
        .secondExtraAccount().get().isEmpty();
    final boolean thirdEmpty = this.addon.configuration().balanceConfig()
        .thirdExtraAccount().get().isEmpty();

    this.thirdAccount.setState(thirdEmpty ? State.HIDDEN : State.VISIBLE);
    this.secondAccount.setState(secondEmpty ? State.HIDDEN : State.VISIBLE);
    this.firstAccount.setState(firstEmpty && (!secondEmpty || !thirdEmpty) ? State.HIDDEN : State.VISIBLE);
  }

  private void addChangeListener() {
    this.addon.configuration().balanceConfig().firstExtraAccount()
        .addChangeListener(((type, oldValue, newValue) -> updateName()));
    this.addon.configuration().balanceConfig().secondExtraAccount()
        .addChangeListener(((type, oldValue, newValue) -> updateName()));
    this.addon.configuration().balanceConfig().thirdExtraAccount()
        .addChangeListener(((type, oldValue, newValue) -> updateName()));
  }

}
