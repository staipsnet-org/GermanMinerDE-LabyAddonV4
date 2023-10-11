package de.germanminer.addon.widgets.balance;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BalanceFormatter {

  private static BalanceFormatter instance;

  public static BalanceFormatter getInstance() {
    if (instance == null) {
      instance = new BalanceFormatter();
    }

    return instance;
  }

  private final DecimalFormat decimalFormat;

  private BalanceFormatter() {
    this.decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
    this.decimalFormat.applyPattern("#,##0.00");
  }

  public String format(final Double amount) {
    if (amount == null) {
      return null;
    }

    return this.decimalFormat.format(amount) + " Euro";
  }


}
