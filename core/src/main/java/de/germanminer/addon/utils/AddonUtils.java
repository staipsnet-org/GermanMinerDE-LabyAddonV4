package de.germanminer.addon.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class AddonUtils {

  private AddonUtils() {}

  /**
   * Eine Zahl in ein einheitlichen, deutsches Format konvertieren
   *
   * @param number              Zahl, die umgewandelt werden soll
   * @param forceNullAfterComma Gibt an, ob trotz glatter Zahl (z.B. 3,00) zwei Nachkommastellen angezeigt werden sollen
   * @return Gibt den formatierten String zurück
   */
  public static String formatNumber(Number number, boolean forceNullAfterComma) {

    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY); // Deutsches Zahlenformat
    if (forceNullAfterComma) {
      ((DecimalFormat) numberFormat).applyPattern("#,##0.00"); // Immer zwei Dezimalstellen anzeigen
    } else {
      ((DecimalFormat) numberFormat).applyPattern("#,##0.##"); // Maximal zwei Dezimalstellen anzeigen, wenn vorhanden
    }
    return numberFormat.format(number) + " Euro";
  }

  // Zusätztliche Varianten für formatNumber bzw. für Währungen mit formatCurrency oder Strecken mit formatDistance
  public static String formatNumber(Number number) {
    return formatNumber(number, false);
  }

}