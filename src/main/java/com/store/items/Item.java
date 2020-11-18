package com.store.items;

import com.store.categories.Category;
import com.store.currencies.Currency;
import com.store.system.Entity;
import com.store.system.MoneyUtil;
import com.store.taxes.Taxes;
import java.util.Locale;
import java.util.Map;

/**
 * Item class can be any entity which is suitable to be embedded into an order It keeps reference to
 * itself in order to allow options or extras added as part of the main selected item
 */
public final class Item extends Entity {
  private String name;
  private String description;
  private int price;
  private Currency currency;
  private Map<String, Item> options;
  private Map<String, Item> extras;
  private Map<String, Item> discounts;
  private ItemType itemType;
  private Category category;
  private Taxes taxes;

  /** @param id */
  public Item(String id) {
    super(id);
  }

  /**
   * @param id
   * @param name
   * @param description
   * @param price
   * @param currency
   * @param options
   * @param extras
   * @param itemType
   * @param category
   */
  public Item(
      String id,
      String name,
      String description,
      int price,
      Currency currency,
      Map<String, Item> options,
      Map<String, Item> extras,
      Map<String, Item> discounts,
      ItemType itemType,
      Category category,
      Taxes taxes) {
    super(id);
    this.name = name;
    this.description = description;
    this.price = price;
    this.currency = currency;
    this.options = options;
    this.extras = extras;
    this.itemType = itemType;
    this.category = category;
    this.taxes = taxes;
    this.discounts = discounts;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Map<String, Item> getOptions() {
    return options;
  }

  public Map<String, Item> getExtras() {
    return extras;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Map<String, Item> getDiscounts() {
    return discounts;
  }

  public Taxes getTaxes() {
    return taxes;
  }

  public void setTaxes(Taxes taxes) {
    this.taxes = taxes;
  }

  /** @return */
  public int getTotal() {
    return price
        + (price * (taxes.getPercentage() / 100)) / 100
        + options.values().stream().mapToInt(Item::getTotal).sum()
        + extras.values().stream().mapToInt(Item::getTotal).sum()
        + discounts.values().stream().mapToInt(Item::getTotal).sum();
  }

  /**
   * Get the total price of the product with the currency symbol
   *
   * @return
   */
  public String getTotalWithCurrency(Locale locale) {
    return currency.getSymbol() + " " + MoneyUtil.format(getTotal(), locale);
  }
}
