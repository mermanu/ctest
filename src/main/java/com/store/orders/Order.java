package com.store.orders;

import com.store.currencies.Currency;
import com.store.items.Item;
import com.store.system.Entity;
import com.store.system.MoneyUtil;

import java.util.LinkedHashMap;
import java.util.Locale;

/** Order entity */
public final class Order extends Entity {
  private LinkedHashMap<String, Item> items;
  private Currency currency;

  public Order(String id) {
    super(id);
  }

  /**
   * @param id
   * @param items
   * @param currency
   */
  public Order(String id, LinkedHashMap<String, Item> items, Currency currency) {
    super(id);
    this.items = items;
    this.currency = currency;
  }

  public LinkedHashMap<String, Item> getItems() {
    return items;
  }

  public int getTotal() {
    return items.values().stream().mapToInt(Item::getTotal).sum();
  }

  public String getTotalWithCurrency(Locale locale) {
    return currency.getSymbol() + " " + MoneyUtil.format(getTotal(), locale);
  }
}
