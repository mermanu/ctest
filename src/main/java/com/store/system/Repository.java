package com.store.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository abstraction which expose the 4 methods which allow the basic CRUD operations
 *
 * @param <T>
 */
public class Repository<T extends Entity> {

  public Repository() {
    Storage.data.putIfAbsent(getClass().getName(), new HashMap<>());
  }

  /**
   * Save method allow to store an entity by assigned id/key
   *
   * @param entity
   */
  public void save(T entity) {
    Storage.data.get(getClass().getName()).put(entity.getId(), entity);
  }

  /**
   * Find method allow to find an entity by assigned id/key
   *
   * @param entity
   * @return
   */
  public T find(T entity) {
    return (T) deepCopy(Storage.data.get(getClass().getName()).get(entity.getId()));
  }

  /**
   * Find method allow to remove an entity by assigned id/key
   *
   * @param entity
   */
  public void remove(T entity) {
    Storage.data.get(getClass().getName()).remove(entity.getId());
  }

  /**
   * FindAll method allow to get all available values inn this repository
   *
   * @return
   */
  public List<T> findAll() {
    var deepCopy = (Map<String, Object>) deepCopy(Storage.data.get(getClass().getName()));
    return new ArrayList(deepCopy.values());
  }

  private static Object deepCopy(Object object) {
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
      outputStrm.writeObject(object);
      ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
      ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
      return objInputStream.readObject();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
