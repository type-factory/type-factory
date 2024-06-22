/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.LINE_SEPARATOR;

import java.util.Arrays;

/**
 * <p>This is a hash-map of integer keys mapped to integer values.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to an integer value.</li>
 * </ul>
 *
 * <p>We currently use maps of this type to map code-points (characters)
 * for an arbitrary numeric radix (base) to their corresponding value.</p>
 */
final class PrimitiveHashMapOfIntKeyToIntValue {

  static final int INITIAL_CAPACITY = 20;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  /**
   * Use and internal struct-like class to ensure an atomic transfer after a rehash.
   */
  private static class HashTable {

    public HashTable() {
      this.keys = new int[INITIAL_CAPACITY][];
      this.values = new int[INITIAL_CAPACITY][];
    }

    /**
     * 2-dimensional array for the keys which is aligned with the values array:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
     *   <li>second index/dimension to get the key values.</li>
     * </ul>
     */
    private int[][] keys;

    /**
     * 2-dimensional array for the values which is aligned with the key array:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map values.</li>
     *   <li>second index/dimension to get the value which is an int.</li>
     * </ul>
     */
    private int[][] values;
  }

  /**
   * We will re-hash the map if its size exceeds this threshold.  The value of this field is (int)(capacity * loadFactor).
   */
  private int threshold;

  private HashTable hashTable;

  private PrimitiveSortedSetOfInt keySet;

  PrimitiveHashMapOfIntKeyToIntValue() {
    clear();
  }

  void clear() {
    this.hashTable = new HashTable();
    this.keySet = new PrimitiveSortedSetOfInt();
    this.threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
  }

  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of keys in this hash map.
   */
  int size() {
    return keySet.size();
  }

  /**
   * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
   *
   * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
   */
  boolean isEmpty() {
    return size() == 0;
  }

  int[] keySet() {
    return keySet.toArray();
  }

  boolean contains(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    int[] bucket = hashTable.keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @param key the key identifying the entry in the map.
   * @return the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @see Integer#MIN_VALUE
   */
  int get(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    int[] bucket = hashTable.keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return hashTable.values[hashIndex][bucketIndex];
        }
      }
    }
    // We use Integer.MIN_VALUE as a sentinel value to indicate that no value exists for the key.
    return Integer.MIN_VALUE;
  }

  void put(final int key, final int value) {
    if (threshold < (int) (size() * LOAD_FACTOR)) {
      rehash();
    }
    put(key, value, hashTable);
  }

  private void put(final int key, final int value, final HashTable hashTable) {
    final int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    final int[] bucket = hashTable.keys[hashIndex];
    if (bucket == null) {
      hashTable.keys[hashIndex] = new int[]{key};
      hashTable.values[hashIndex] = new int[]{value};
    } else {
      int bucketIndex = 0;
      while (bucketIndex < bucket.length && bucket[bucketIndex] != key) {
        ++bucketIndex;
      }
      if (bucketIndex == bucket.length) {
        hashTable.keys[hashIndex] = Arrays.copyOf(hashTable.keys[hashIndex], bucketIndex + 1);
        hashTable.values[hashIndex] = Arrays.copyOf(hashTable.values[hashIndex], bucketIndex + 1);
      }
      hashTable.keys[hashIndex][bucketIndex] = key;
      hashTable.values[hashIndex][bucketIndex] = value;
    }
    keySet.add(key);
  }

  private void rehash() {
    final int newCapacity = (int) (hashTable.keys.length * 1.5F + 1);
    threshold = (int) (newCapacity * LOAD_FACTOR);
    final HashTable newHashTable = new HashTable();
    newHashTable.keys = new int[newCapacity][];
    newHashTable.values = new int[newCapacity][];
    for (int i = 0; i < hashTable.keys.length; ++i) {
      if (hashTable.keys[i] != null) {
        for (int j = 0; j < hashTable.keys[i].length; ++j) {
          put(hashTable.keys[i][j], hashTable.values[i][j], newHashTable);
        }
      }
    }
    this.hashTable = newHashTable;
  }

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    int value;
    for (int key : keySet()) {
      value = get(key);
      s.append("0x")
          .append(Integer.toString(key, 16))
          .append(" [")
          .appendCodePoint(key)
          .append("] ⟶ ");
      // We use Integer.MIN_VALUE as a sentinel value to indicate that no value exists for the key.
      if (value > Integer.MIN_VALUE) {
        s.appendCodePoint(value);
      }
      s.append(LINE_SEPARATOR);
    }
    if (s.length() >= LINE_SEPARATOR.length()) {
      s.setLength(s.length() - LINE_SEPARATOR.length());
    }
    return s.toString();
  }

}
