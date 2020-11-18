package com.store.categories;

import com.store.system.Entity;

/** Category entity */
public class Category extends Entity {
  private String name;

  public Category(String id) {
    super(id);
  }

  public Category(String id, String name) {
    super(id);
    this.name = name;
  }
}
