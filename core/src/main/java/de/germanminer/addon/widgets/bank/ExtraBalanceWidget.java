package de.germanminer.addon.widgets.bank;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.utils.AddonUtils;
import de.germanminer.addon.widgets.WidgetIcon;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import de.germanminer.addon.widgets.WidgetRegistry;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.hud.HudWidgetRegistry;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.serverapi.api.Protocol;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;
import packets.widget.balance.ExtraBalancePacket;

// I'm not very happy with this current solution
public class ExtraBalanceWidget extends TextHudWidget<TextHudWidgetConfig>
    implements PacketHandler<ExtraBalancePacket>, WidgetRegistry<ExtraBalancePacket> {

  private final GermanMinerAddon addon;
  private final Icon hudWidgetIcon;
  private TextLine firstAccount;
  private TextLine secondAccount;
  private TextLine thirdAccount;
  private Map<String, Double> accountValues = new HashMap<>();

  public ExtraBalanceWidget(final GermanMinerAddon addon, final String id) {
    super(id);
    super.bindCategory(addon.getHudWidgetCategory());

    this.addon = addon;
    this.hudWidgetIcon = WidgetIcon.BALANCE_BANK_EXTRA.getIcon();
  }

  @Override
  public void load(final TextHudWidgetConfig config) {
    super.load(config);

    update();
    addChangeListener();

    super.setIcon(this.hudWidgetIcon);
  }

  @Override
  public boolean isVisibleInGame() {
    return this.addon.enabled() && this.addon.configuration().isBankEnabled()
        && this.addon.configuration().getBankSetting().isExtraEnabled();
  }

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final ExtraBalancePacket packet) {
    if (packet.getExtraAccounts() != null) {
      this.accountValues = packet.getExtraAccounts();

      updateValue();
    }
  }

  @Override
  public void register(final HudWidgetRegistry registry, final Protocol protocol,
      final Class<ExtraBalancePacket> packetClass) {
    registry.register(this);
    protocol.registerHandler(packetClass, this);
  }

  public void update() {
    this.lines.remove(this.firstAccount);
    this.lines.remove(this.secondAccount);
    this.lines.remove(this.thirdAccount);

    final Component defaultName = Component.translatable(String.format("germanmineraddon.hudWidget.%s.name", super.getId()));

    final String firstAccount = this.addon.configuration().getBankSetting().getFirstCustomAccount();
    final String secondAccount = this.addon.configuration().getBankSetting().getSecondCustomAccount();
    final String thirdAccount = this.addon.configuration().getBankSetting().getThirdCustomAccount();

    this.firstAccount = super.createLine(firstAccount.isEmpty() ? defaultName : Component.text(firstAccount),
        Component.translatable("germanmineraddon.hudWidget.loading"));
    this.secondAccount = super.createLine(secondAccount.isEmpty() ? defaultName : Component.text(secondAccount),
        Component.translatable("germanmineraddon.hudWidget.loading"));
    this.thirdAccount = super.createLine(thirdAccount.isEmpty() ? defaultName : Component.text(thirdAccount),
        Component.translatable("germanmineraddon.hudWidget.loading"));

    updateValue();
  }

  private void updateValue() {
    final Double firstValue = this.accountValues.get(this.addon.configuration()
        .getBankSetting().getFirstCustomAccount());
    final Double secondValue = this.accountValues.get(this.addon.configuration()
        .getBankSetting().getSecondCustomAccount());
    final Double thirdValue = this.accountValues.get(this.addon.configuration()
        .getBankSetting().getThirdCustomAccount());

    if (firstValue != null) {
      this.firstAccount.updateAndFlush(AddonUtils.formatNumber(firstValue));
    }

    if (secondValue != null) {
      this.secondAccount.updateAndFlush(AddonUtils.formatNumber(secondValue));
    }

    if (thirdValue != null) {
      this.thirdAccount.updateAndFlush(AddonUtils.formatNumber(thirdValue));
    }

    this.thirdAccount.setState(thirdValue == null ? State.HIDDEN : State.VISIBLE);
    this.secondAccount.setState(secondValue == null ? State.HIDDEN : State.VISIBLE);
    this.firstAccount.setState(firstValue == null && (secondValue != null || thirdValue != null)
        ? State.HIDDEN : State.VISIBLE);
  }

  private void addChangeListener() {
    this.addon.configuration().getBankSetting().bankCustomAccount1
        .addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().getBankSetting().bankCustomAccount2
        .addChangeListener(((type, oldValue, newValue) -> update()));
    this.addon.configuration().getBankSetting().bankCustomAccount3
        .addChangeListener(((type, oldValue, newValue) -> update()));
  }

}