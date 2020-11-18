package com.store.system;

import java.util.HashMap;
import java.util.Map;

/**
 * Storage class which allow efficient storage and retrieval of entities in memory. This can be
 * improved by provide file system persistence
 */
public final class Storage {
  public static final Map<String, Map<String, Object>> data = new HashMap<>();
}
