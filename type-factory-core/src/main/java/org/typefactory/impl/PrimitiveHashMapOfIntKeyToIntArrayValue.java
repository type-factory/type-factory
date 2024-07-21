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
 * <p>This is a hash-map of integer keys mapped to integer-array values.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to a sequence of code points.</li>
 *   <li>a unicode category identified by an integer to a sequence of code points.</li>
 * </ul>
 */
final class PrimitiveHashMapOfIntKeyToIntArrayValue {

  static final int INITIAL_CAPACITY = 20;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  /**
   * Use and internal struct-like class to ensure an atomic transfer after a rehash.
   */
  private static class HashTable {

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
     *   <li>second index/dimension to get the value objects which are int-arrays.</li>
     * </ul>
     */
    private int[][][] values;
  }

  /**
   * We will re-hash the map if its size exceeds this threshold.  The value of this field is (int)(capacity * loadFactor).
   */
  private int threshold;

  private HashTable hashTable;

  private int maxValueArrayLength = 0;

  private final PrimitiveSortedSetOfInt keySet = new PrimitiveSortedSetOfInt();

  PrimitiveHashMapOfIntKeyToIntArrayValue() {
    this.hashTable = new HashTable();
    this.hashTable.keys = new int[INITIAL_CAPACITY][];
    this.hashTable.values = new int[INITIAL_CAPACITY][][];
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

  int getMaxValueArrayLength() {
    return maxValueArrayLength;
  }

  int[] keySet() {
    return keySet.toArray();
  }

  int[] get(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    int[] bucket = hashTable.keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return hashTable.values[hashIndex][bucketIndex];
        }
      }
    }
    return null;
  }

  void put(final int key, final int[] value) {
    if (threshold < (int) (size() * LOAD_FACTOR)) {
      rehash();
    }
    put(key, value, hashTable);
    maxValueArrayLength = Math.max(maxValueArrayLength, value == null ? 0 : value.length);
  }

  private void put(final int key, final int[] value, final HashTable hashTable) {
    final int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    final int[] bucket = hashTable.keys[hashIndex];
    if (bucket == null) {
      hashTable.keys[hashIndex] = new int[]{key};
      hashTable.values[hashIndex] = new int[][]{value};
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
    newHashTable.values = new int[newCapacity][][];
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
    int[] value;
    for (int key : keySet()) {
      value = get(key);
      s.append("0x")
          .append(Integer.toString(key, 16))
          .append(" [")
          .appendCodePoint(key)
          .append("] ⟶ ")
          .append(value == null ? "" : new String(value, 0, value.length))
          .append(LINE_SEPARATOR);
    }
    if (s.length() >= LINE_SEPARATOR.length()) {
      s.setLength(s.length() - LINE_SEPARATOR.length());
    }
    return s.toString();
  }
}
