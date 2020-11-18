package com.store.system;

import com.store.orders.Order;

import java.util.Locale;

public class TicketUtil {

  public static String printTicket(Order order, Locale locale) {
    var printedTicket = new StringBuilder();
    printedTicket.append("Order id: ").append(order.getId()).append("\n");
    order
        .getItems()
        .forEach(
            (s, item) -> {
              printedTicket
                  .append("  ")
                  .append(s.trim())
                  .append(" - ")
                  .append(item.getTotalWithCurrency(locale))
                  .append("\n");
            });
    printedTicket.append("Total: ").append(order.getTotalWithCurrency(locale));
    return printedTicket.toString();
  }
}
