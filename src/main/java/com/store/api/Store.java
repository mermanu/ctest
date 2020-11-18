package com.store.api;

import com.store.orders.Order;

public interface Store {

  /**
   * Service case in assigment, it receives the input in text and then produce as output the order
   * amount
   *
   * @param input
   * @return
   */
  String placeOrder(String input);

  /**
   * Service case in assigment, it receives the input in text and then produce the output as receipt
   *
   * @param input
   * @return
   */
  String placeOrderAndPrintTicket(String input);

  /**
   * Initiate an order
   *
   * @return
   */
  Order startOrder();

  /**
   * Place an order returns the order Id, this should be the preferred manner.
   * This will require an UI which handle the add or removal of items from the order
   *
   * @param order
   * @return
   */
  String placeOrder(Order order);
}
