package de.germanminer.addon.utils;

import de.germanminer.addon.GermanMinerAddon;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class AddonUtils {

  private final GermanMinerAddon addon;
  private final DecimalFormat decimalFormat;

  public AddonUtils(GermanMinerAddon addon) {
    this.addon = addon;
    this.decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
    this.decimalFormat.applyPattern("#,##0.00");
  }

  public String formatAmount(double amount) {
    return this.decimalFormat.format(amount) + " Euro";
  }
}