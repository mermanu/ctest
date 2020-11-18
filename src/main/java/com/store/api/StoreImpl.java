package com.store.api;

import com.store.currencies.Currency;
import com.store.currencies.CurrencyRepository;
import com.store.items.Item;
import com.store.items.ItemRepository;
import com.store.items.ItemType;
import com.store.orders.Order;
import com.store.orders.OrderRepository;
import com.store.system.Repository;
import com.store.system.StoreConfig;
import com.store.system.TicketUtil;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/** Service implementation of store with main functionality */
public class StoreImpl implements Store {

  private static final String EXTRAS_FLAG = "with";
  private static final String COMMA_SEPARATOR = ",";
  private static final String SPACE_CHAR = " ";
  private static final String REPLACEMENT = "";
  private Repository<Order> orderRepository;
  private Repository<Item> itemRepository;
  private Repository<Currency> currencyRepository;
  private Currency currency;
  private Locale locale;

  /**
   * @param currency
   * @param locale
   * @param storeConfig
   */
  public StoreImpl(Currency currency, Locale locale, StoreConfig storeConfig) {
    this.orderRepository = new OrderRepository();
    this.itemRepository = new ItemRepository();
    this.currencyRepository = new CurrencyRepository();
    this.currency = currency;
    this.locale = locale;
    storeConfig.init();
  }

  @Override
  public String placeOrder(String input) {
    var order = fillOrder(input);
    return order.getTotalWithCurrency(locale);
  }

  @Override
  public String placeOrderAndPrintTicket(String input) {
    var order = fillOrder(input);
    this.orderRepository.save(order);
    return TicketUtil.printTicket(order, locale);
  }

  /**
   * Ideally this should be the used method, the other is just for this test purpose
   *
   * @param order
   * @return
   */
  @Override
  public String placeOrder(Order order) {
    this.orderRepository.save(order);
    return order.getId();
  }

  @Override
  public Order startOrder() {
    var order =
        new Order(
            UUID.randomUUID().toString(), new LinkedHashMap<>(), currencyRepository.find(currency));
    this.orderRepository.save(order);
    return order;
  }

  /**
   * It takes the pattern --> "option product WITH extra" then creates an item which contains the
   * references to the described order, finally it takes the order and add the created items. An
   * order is expressed by comma separated items. Example: "large coffee with extra milk, small
   * coffee with special roast, bacon roll, orange juice" This should produce as output a new order
   * with 4 items, each of them with the references to the options or extras. Ideally it should be
   * added an UI which add the items directly to the order by listing the available items, then this
   * method will be deprecated
   *
   * @param input
   * @return
   */
  private Order fillOrder(String input) {
    if (Objects.nonNull(input)) {
      var order = startOrder();
      Stream.of(input.split(COMMA_SEPARATOR))
          .forEach(
              itemString -> {
                Item itemProduct;
                String itemKey = itemString;
                var itemStringArray = itemString.trim().split(SPACE_CHAR);
                var itemOption = itemRepository.find(new Item(itemStringArray[0]));
                if (Objects.nonNull(itemOption)
                    && ItemType.OPTION.equals(itemOption.getItemType())) {
                  itemString = itemString.replace(itemStringArray[0], REPLACEMENT);
                }
                var trimmedItemString = itemString.trim();
                if (itemString.contains(EXTRAS_FLAG)) {
                  itemProduct =
                      fillItemProductIfAny(itemString.trim(), itemOption, trimmedItemString);
                  if (Objects.nonNull(itemProduct)
                      && ItemType.PRODUCT.equals(itemProduct.getItemType()))
                    fillItemExtraIfAny(itemString.trim(), itemProduct, trimmedItemString);
                } else {
                  itemProduct = itemRepository.find(new Item(trimmedItemString));
                  fillItemOptionIfAny(itemOption, itemProduct);
                }
                if (Objects.nonNull(itemProduct)) order.getItems().put(itemKey, itemProduct);
              });
      return order;
    }
    return null;
  }

  private Item fillItemProductIfAny(String itemString, Item itemOption, String trimmedItemString) {
    var itemProductKey = trimmedItemString.substring(0, itemString.indexOf(EXTRAS_FLAG) - 1).trim();
    var itemProduct = itemRepository.find(new Item(itemProductKey));
    fillItemOptionIfAny(itemOption, itemProduct);
    return itemProduct;
  }

  private void fillItemExtraIfAny(String itemString, Item itemProduct, String trimmedItemString) {
    var extraKey =
        trimmedItemString
            .substring(itemString.indexOf(EXTRAS_FLAG) + 4, itemString.length())
            .trim();
    var itemExtra = itemRepository.find(new Item(extraKey));
    if (Objects.nonNull(itemExtra) && ItemType.EXTRA.equals(itemExtra.getItemType())) {
      itemProduct.getExtras().put(itemExtra.getId(), itemExtra);
    }
  }

  private void fillItemOptionIfAny(Item itemOption, Item itemProduct) {
    if (Objects.nonNull(itemProduct)
        && ItemType.PRODUCT.equals(itemProduct.getItemType())
        && Objects.nonNull(itemOption)
        && ItemType.OPTION.equals(itemOption.getItemType())) {
      itemProduct.getOptions().put(itemOption.getId(), itemOption);
    }
  }
}
