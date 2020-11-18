package com.store.orders;

import com.store.categories.Category;
import com.store.currencies.Currency;
import com.store.items.Item;
import com.store.items.ItemType;
import com.store.taxes.Taxes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

  private static Currency currency;
  private static Taxes taxes;
  private static Category category;
  private static LinkedHashMap<String, Item> items;

  @BeforeAll
  static void setup() {
    items = new LinkedHashMap<>();
    currency = new Currency("eu", "Euro", "€");
    category = new Category("cat1", "Products");
    taxes = new Taxes("tax1", "General 21%", 2100);
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
    var item0 =
        new Item(
            "item0",
            "Item test",
            "Just a test item with price of 2200.40",
            220040,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    var item1 =
        new Item(
            "item1",
            "Item test",
            "Just a test item with price of 155.00",
            15500,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            category,
            taxes);
    items.put("item", item);
    items.put("item0", item0);
    items.put("item1", item1);
  }

  @Test
  void getTotal() {
    var order = new Order("order", items, currency);
    assertEquals(420589, order.getTotal());
  }

  @Test
  void getTotalWithCurrency() {
    var order = new Order("order", items, currency);
    assertEquals("€ 4,205.89", order.getTotalWithCurrency(Locale.US));
  }
}
