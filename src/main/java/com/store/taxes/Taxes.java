package com.store.taxes;

import com.store.system.Entity;

/** Taxes entity */
public class Taxes extends Entity {
  private String name;
  private int percentage;

  /** @param id */
  public Taxes(String id) {
    super(id);
  }

  /**
   * @param id
   * @param name
   * @param percentage
   */
  public Taxes(String id, String name, int percentage) {
    super(id);
    this.name = name;
    this.percentage = percentage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPercentage() {
    return percentage;
  }

  public void setPercentage(int percentage) {
    this.percentage = percentage;
  }
}
