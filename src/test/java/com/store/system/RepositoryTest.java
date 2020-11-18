package com.store.system;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RepositoryTest {

  private static DummyRepository dummyRepository;
  private static DummyRepository0 dummyRepository0;

  @BeforeAll
  static void setup() {
    var testEntity0 = new DummyEntity("id0");
    var testEntity2 = new DummyEntity("id2");
    var testEntity3 = new DummyEntity("id3");
    var testEntity4 = new DummyEntity0("id9");
    dummyRepository = new DummyRepository();
    dummyRepository0 = new DummyRepository0();
    dummyRepository.save(testEntity0);
    dummyRepository.save(testEntity2);
    dummyRepository.save(testEntity3);
    dummyRepository0.save(testEntity4);
  }

  @Test
  void save() {
    var testEntity = new DummyEntity("id1");
    dummyRepository.save(testEntity);
    var findResult = dummyRepository.find(testEntity);
    assertNotNull(findResult);
    assertEquals("id1", findResult.getId());
  }

  @Test
  void find() {
    var testEntity3 = new DummyEntity("id3");
    var findResult = dummyRepository.find(testEntity3);
    assertNotNull(findResult);
    assertEquals("id3", findResult.getId());
  }

  @Test
  void findIsNull() {
    var testEntity3 = new DummyEntity("id6");
    var findResult = dummyRepository.find(testEntity3);
    assertNull(findResult);
  }

  @Test
  void remove() {
    var testEntity2 = new DummyEntity("id2");
    dummyRepository.remove(testEntity2);
    assertNull(dummyRepository.find(testEntity2));
  }

  @Test
  void removeWithValueNotStored() {
    var testEntity2 = new DummyEntity("id8");
    dummyRepository.remove(testEntity2);
    assertNull(dummyRepository.find(testEntity2));
  }

  @Test
  void findAll() {
    assertFalse(dummyRepository.findAll().isEmpty());
    assertTrue(dummyRepository.findAll().size() >= 2);
  }

  @Test
  void checkRepositoryReference() {
    var testEntity10 = new DummyEntity0("id10");
    var testEntity9 = new DummyEntity0("id9");
    assertNull(dummyRepository0.find(testEntity10));
    assertNotNull(dummyRepository0.find(testEntity9));
  }
}
