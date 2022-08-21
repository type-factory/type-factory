package org.datatypeproject.impl;

import java.util.Arrays;

/**
 * <p>This is a hash-map of integer keys mapped to integer-array values.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to a sequence of code points.</li>
 *   <li>a unicode category identified by an integer to a sequence of code points.</li>
 * </ul>
 */
class PrimitiveHashMapOfIntKeyToObjectValue<T extends Object> {

  private static final int INITIAL_CAPACITY = 20;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  /**
   * Use and internal struct-like class to ensure an atomic transfer after a rehash.
   */
  private static class HashTable<T extends Object> {

    /**
     * 2-dimensional array for the keys which is aligned with the values array:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
     *   <li>second index/dimension to get the key values.</li>
     * </ul>
     */
    private int[][] keys;

    /**
     * 2-dimensional array for the values which is aligned with the key array. It appears to be 3-dimensional but that is because the map-values are
     * actually int-arrays:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map values.</li>
     *   <li>second index/dimension to get the value objects which are objects of type T.</li>
     * </ul>
     */
    private T[][] values;
  }

  /**
   * We will re-hash the map if its size exceeds this threshold.  The value of this field is (int)(capacity * loadFactor).
   */
  private int threshold;

  private HashTable<T> hashTable;

  private PrimitiveSortedSetOfInt keyset = new PrimitiveSortedSetOfInt();
  private int size;

  PrimitiveHashMapOfIntKeyToObjectValue() {
    this.hashTable = new HashTable();
    this.hashTable.keys = new int[INITIAL_CAPACITY][];
    this.hashTable.values = (T[][]) new Object[INITIAL_CAPACITY][];
    this.threshold = (int) (this.hashTable.keys.length * LOAD_FACTOR);

    threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
  }

  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of keys in this hash map.
   */
  int size() {
    return size;
  }

  /**
   * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
   *
   * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
   */
  boolean isEmpty() {
    return size == 0;
  }

  int[] keys() {
    return keyset.toArray();
  }
  T get(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    int[] bucket = hashTable.keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return (T) hashTable.values[hashIndex][bucketIndex];
        }
      }
    }
    return null;
  }

  void put(final int key, final T value) {
    if (value == null) {
      return;
    }
    if (threshold < (int) (size * LOAD_FACTOR)) {
      rehash();
    }
    put(key, value, hashTable);
  }

  private void put(final int key, final T value, final HashTable<T> hashTable) {
    final int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    final int[] bucket = hashTable.keys[hashIndex];
    if (bucket == null) {
      hashTable.keys[hashIndex] = new int[]{key};
      hashTable.values[hashIndex] = (T[]) new Object[]{value};
      ++size;
    } else {
      int bucketIndex = 0;
      while (bucketIndex < bucket.length && bucket[bucketIndex] != key) {
        ++bucketIndex;
      }
      if (bucketIndex == bucket.length) {
        hashTable.keys[hashIndex] = Arrays.copyOf(hashTable.keys[hashIndex], bucketIndex + 1);
        hashTable.values[hashIndex] = Arrays.copyOf(hashTable.values[hashIndex], bucketIndex + 1);
        ++size;
      }
      hashTable.keys[hashIndex][bucketIndex] = key;
      hashTable.values[hashIndex][bucketIndex] = value;
    }
    keyset.add(key);
  }

  private void rehash() {
    final int newCapacity = (int) (hashTable.keys.length * 1.5F + 1);
    threshold = (int) (newCapacity * LOAD_FACTOR);
    final HashTable<T> newHashTable = new HashTable<>();
    newHashTable.keys = new int[newCapacity][];
    newHashTable.values = (T[][]) new Object[newCapacity][];
    for (int i = 0; i < hashTable.keys.length; ++i) {
      if (hashTable.keys[i] != null) {
        for (int j = 0; j < hashTable.keys[i].length; ++j) {
          put(hashTable.keys[i][j], hashTable.values[i][j], newHashTable);
        }
      }
    }
    this.hashTable = newHashTable;
  }
}
