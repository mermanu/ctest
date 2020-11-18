package com.store.api;

import com.store.currencies.Currency;
import com.store.system.StoreTestConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class storeTest {
  private static Store store;

  @BeforeAll
  static void setup() {
    store = new StoreImpl(new Currency("eu"), Locale.US, new StoreTestConfig());
  }

  @Test
  void placeOrderProvidedCase0() {
    var input = "";
    assertEquals("€ 0", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase1() {
    var input = "large coffee with extra milk";
    assertEquals("€ 4.18", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase2() {
    var input = "medium coffee with extra milk";
    assertEquals("€ 3.63", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase4() {
    var input = "small coffee with extra milk";
    assertEquals("€ 3.08", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase5() {
    var input = "small coffee with special roast";
    assertEquals("€ 3.74", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase6() {
    var input = "bacon roll";
    assertEquals("€ 4.95", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase7() {
    var input = "orange juice";
    assertEquals("€ 4.34", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase8() {
    var input = "small coffee with foamed milk";
    assertEquals("€ 3.3", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCase() {
    var input =
        "large coffee with extra milk, small coffee with special roast, bacon roll, orange juice";
    assertEquals("€ 17.21", store.placeOrder(input));
  }

  @Test
  void placeOrderProvidedCaseWithNoTaxes() {
    var input =
        "large coffee with extra milk, small coffee with special roast, bacon roll, orange juice";

    assertEquals("€ 17.21", store.placeOrder(input));
  }

  @Test
  void placeOrderAndPrintTicket() {
    var input =
        "large coffee with extra milk, small coffee with special roast, bacon roll, orange juice";
    assertTrue(
        store
            .placeOrderAndPrintTicket(input)
            .contains(
                "  large coffee with extra milk - € 4.18\n"
                    + "  small coffee with special roast - € 3.74\n"
                    + "  bacon roll - € 4.95\n"
                    + "  orange juice - € 4.34\n"
                    + "Total: € 17.21"));
  }
}
