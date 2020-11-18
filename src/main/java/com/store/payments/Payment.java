package com.store.payments;

import com.store.orders.Order;
import com.store.system.Entity;

/** Payment entity */
public final class Payment extends Entity {
  private Order order;
  private int total;

  public Payment(String id) {
    super(id);
  }
}
