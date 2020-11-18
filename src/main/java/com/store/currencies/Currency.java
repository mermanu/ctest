package com.store.currencies;

import com.store.system.Entity;

/** Currency entity */
public final class Currency extends Entity {
  private String name;
  private String symbol;

  public Currency(String id) {
    super(id);
  }

  public Currency(String id, String name, String symbol) {
    super(id);
    this.name = name;
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
