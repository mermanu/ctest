package com.store.system;

import com.store.categories.Category;
import com.store.categories.CategoryRepository;
import com.store.currencies.Currency;
import com.store.currencies.CurrencyRepository;
import com.store.items.Item;
import com.store.items.ItemRepository;
import com.store.items.ItemType;
import com.store.taxes.Taxes;
import com.store.taxes.TaxesRepository;

import java.util.HashMap;

public class StoreTestConfig implements StoreConfig {
  @Override
  public void init() {
    var currency = new Currency("eu", "Euro", "€");
    var optionsCategory = new Category("opt", "Options");
    var extrasCategory = new Category("ext", "Extras");
    var beverageCategory = new Category("bev", "Beverages");
    var snackCategory = new Category("snk", "Snaks");
    var taxes = new Taxes("general", "General 10%", 1000);
    var itemRepository = new ItemRepository();
    var currencyRepository = new CurrencyRepository();
    var taxesRepository = new TaxesRepository();
    var categoryRepository = new CategoryRepository();
    var optionSmall =
        new Item(
            "small",
            "Small",
            "Small option",
            50,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            optionsCategory,
            taxes);
    var optionMedium =
        new Item(
            "medium",
            "Medium",
            "Medium option",
            100,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            optionsCategory,
            taxes);
    var optionLarge =
        new Item(
            "large",
            "Large",
            "Large option",
            150,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.OPTION,
            optionsCategory,
            taxes);
    var coffeeProduct =
        new Item(
            "coffee",
            "Coffee",
            "Coffee",
            200,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            beverageCategory,
            taxes);
    var baconRollProduct =
        new Item(
            "bacon roll",
            "Bacon roll",
            "Bacon roll",
            450,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            beverageCategory,
            taxes);
    var orangeJuiceProduct =
        new Item(
            "orange juice",
            "Orange juice (0.25l)",
            "Freshly squeezed orange juice (0.25l)",
            395,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.PRODUCT,
            beverageCategory,
            taxes);
    var extraMilk =
        new Item(
            "extra milk",
            "Extra milk",
            "Extra milk",
            30,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            extrasCategory,
            taxes);

    var extraSpecialRoast =
        new Item(
            "special roast",
            "Special Roast",
            "Special Roast",
            90,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            extrasCategory,
            taxes);

    var extraFoamedMilk =
        new Item(
            "foamed milk",
            "Foamed Milk",
            "Foamed Milk",
            50,
            currency,
            new HashMap<>(),
            new HashMap<>(),
            new HashMap<>(),
            ItemType.EXTRA,
            extrasCategory,
            taxes);

    categoryRepository.save(beverageCategory);
    categoryRepository.save(extrasCategory);
    categoryRepository.save(optionsCategory);
    categoryRepository.save(snackCategory);
    taxesRepository.save(taxes);
    currencyRepository.save(currency);
    itemRepository.save(optionSmall);
    itemRepository.save(optionMedium);
    itemRepository.save(optionLarge);
    itemRepository.save(coffeeProduct);
    itemRepository.save(baconRollProduct);
    itemRepository.save(orangeJuiceProduct);
    itemRepository.save(extraMilk);
    itemRepository.save(extraSpecialRoast);
    itemRepository.save(extraFoamedMilk);
  }
}
