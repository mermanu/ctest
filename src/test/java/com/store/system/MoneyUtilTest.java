package com.store.system;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MoneyUtilTest {

  @Test
  void format() {
    assertEquals("50", MoneyUtil.format(5000, Locale.US));
    assertEquals("50.48", MoneyUtil.format(5048, Locale.US));
    assertEquals("0", MoneyUtil.format(0, Locale.US));
    assertEquals("1,000.48", MoneyUtil.format(100048, Locale.US));
    assertEquals("1,000,000.48", MoneyUtil.format(100000048, Locale.US));
    assertEquals("1 000 000,48", MoneyUtil.format(100000048, Locale.FRANCE));
  }
}
