package com.store.items;

import com.store.categories.Category;
import com.store.currencies.Currency;
import com.store.taxes.Taxes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  private static Currency currency;
  private static Category category;
  private static Taxes taxes;

  @BeforeAll
  static void setup() {
    currency = new Currency("eu", "Euro", "€");
    category = new Category("cat1", "Products");
    taxes = new Taxes("tax1", "General 21%", 2100);
  }

  @Test
  void getItemTotalBasicProductType() {
    var item =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 1120.55",
            112055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(135586, item.getTotal());

    var item1 =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 120.55",
            12055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(14586, item1.getTotal());
  }

  @Test
  void getItemTotalExtendedProductType() {
    var extras = new HashMap<String, Item>();
    var options = new HashMap<String, Item>();
    var discounts = new HashMap<String, Item>();
    var extra0 =
        new Item(
            "item extra0",
            "Item extra0 test",
            "Just a test extra item with price of 1120.55",
            112055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            category,
            taxes);

    var extra1 =
        new Item(
            "item extra1",
            "Item extra1 test",
            "Just a test extra item with price of 2000.55",
            200055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            category,
            taxes);

    extras.put("e0", extra0);
    extras.put("e1", extra1);

    var option0 =
        new Item(
            "item option0",
            "Item option0 test",
            "Just a test option item with price of 100.00",
            10000,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            category,
            taxes);

    var option1 =
        new Item(
            "item option1",
            "Item option1 test",
            "Just a test option item with price of 200.00",
            20000,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            category,
            taxes);

    options.put("o0", option0);
    options.put("o1", option1);

    var item =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 1120.55",
            112055,
            currency,
            options,
            extras,
            discounts,
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(549538, item.getTotal());
  }

  @Test
  void getItemTotalExtendedProductTypeWithDiscounts() {
    var extras = new HashMap<String, Item>();
    var options = new HashMap<String, Item>();
    var discounts = new HashMap<String, Item>();
    var extra0 =
        new Item(
            "item extra0",
            "Item extra0 test",
            "Just a test extra item with price of 1120.55",
            112055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            category,
            taxes);

    var extra1 =
        new Item(
            "item extra1",
            "Item extra1 test",
            "Just a test extra item with price of 2000.55",
            200055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            category,
            taxes);

    extras.put("e0", extra0);
    extras.put("e1", extra1);

    var option0 =
        new Item(
            "item option0",
            "Item option0 test",
            "Just a test option item with price of 100.00",
            10000,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            category,
            taxes);

    var option1 =
        new Item(
            "item option1",
            "Item option1 test",
            "Just a test option item with price of 200.00",
            20000,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            category,
            taxes);

    options.put("o0", option0);
    options.put("o1", option1);

    var discount0 =
        new Item(
            "item discount0",
            "Item discount0 test",
            "Just a test discount item with price of 1000.00",
            -100000,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.DISCOUNT,
            category,
            new Taxes("tax0", "General 0%", 0));
    discounts.put("d0", discount0);

    var item =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 1120.55",
            112055,
            currency,
            options,
            extras,
            discounts,
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(449538, item.getTotal());
  }

  @Test
  void getTotalWithCurrencyBasicProductType() {
    var item =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 120,55",
            112055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(135586, item.getTotal());
    assertEquals("€ 1,355.86", item.getTotalWithCurrency(Locale.US));
    assertEquals("€ 1 355,86", item.getTotalWithCurrency(Locale.FRANCE));
    assertEquals("€ 1.355,86", item.getTotalWithCurrency(Locale.ITALIAN));

    var item1 =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 120,55",
            20312055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(24577586, item1.getTotal());
    assertEquals("€ 245,775.86", item1.getTotalWithCurrency(Locale.US));
    assertEquals("€ 245 775,86", item1.getTotalWithCurrency(Locale.FRANCE));
    assertEquals("€ 245.775,86", item1.getTotalWithCurrency(Locale.ITALIAN));

    var item2 =
        new Item(
            "item",
            "Item test",
            "Just a test item with price of 120,55",
            200312055,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    assertEquals(199427914, item2.getTotal());
    assertEquals("€ 1,994,279.14", item2.getTotalWithCurrency(Locale.US));
    assertEquals("€ 1 994 279,14", item2.getTotalWithCurrency(Locale.FRANCE));
    assertEquals("€ 1.994.279,14", item2.getTotalWithCurrency(Locale.ITALIAN));
    item2.setCurrency(new Currency("chf", "CHF", "CHF"));
    assertEquals("CHF 1 994 279,14", item2.getTotalWithCurrency(new Locale("fr", "CH")));
  }
}
