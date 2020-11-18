package com.store.system;

import java.io.Serializable;

/** Entity abstraction which by default should contain an assigned id to be storeds */
public class Entity implements Serializable {
  /** unique identifier */
  private String id;

  /** @param id */
  public Entity(String id) {
    this.id = id;
  }

  /**
   * Get the unique identifier of this entity
   *
   * @return
   */
  public String getId() {
    return id;
  }
}
