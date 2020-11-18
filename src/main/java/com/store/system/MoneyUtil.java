package com.store.system;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/** Money format utils */
public class MoneyUtil {
  /**
   * Format the amount from cents to Number with locale reference
   *
   * @param moneyInCents
   * @param locale
   * @return
   */
  public static String format(int moneyInCents, Locale locale) {
    var numberFormat = NumberFormat.getNumberInstance(locale);
    numberFormat.setMaximumFractionDigits(2);
    var decimalFormat = (DecimalFormat) numberFormat;
    var value = moneyInCents % 100 == 0 ? moneyInCents / 100 : moneyInCents / 100.0;
    return decimalFormat.format(value);
  }
}
