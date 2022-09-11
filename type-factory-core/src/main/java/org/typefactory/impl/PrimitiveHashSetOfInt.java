package org.typefactory.impl;

import java.util.Arrays;

/**
 * <p>This is a hash-map of integer values mapped to integer-array valuesXXX.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to a sequence of code points.</li>
 *   <li>a unicode category identified by an integer to a sequence of code points.</li>
 * </ul>
 */
class PrimitiveHashSetOfInt<T extends Object> {

  private static final int INITIAL_CAPACITY = 20;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  /**
   * <p>The hashed values.</p>
   *
   * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
   * <pre>
   *        ┌──── hashIndex       - an index to the hash-bucket
   *        │  ┌─ hashBucketIndex - an index to the value within the hash-bucket
   *        │  │
   *   char[ ][ ] values
   * </pre>
   */
  private int[][] values;

  /**
   * We will re-hash the map if its size exceeds this threshold.  The value of this field is (int)(capacity * loadFactor).
   */
  private int threshold;

  private int size;

  PrimitiveHashSetOfInt() {
    this.values = new int[INITIAL_CAPACITY][];
    this.threshold = (int) (this.values.length * LOAD_FACTOR);

    threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
  }

  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of values in this hash map.
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

  boolean contains(final int value) {
    int hashIndex = (value & 0x7FFFFFFF) % values.length;
    int[] bucket = values[hashIndex];
    if (bucket != null) {
      for (int hashBucketIndex = 0; hashBucketIndex < bucket.length; ++hashBucketIndex) {
        if (bucket[hashBucketIndex] == value) {
          return true;
        }
      }
    }
    return false;
  }

  void add(final int value) {
    add(value, this.values);
  }

  void add(final int value, final int [][] values) {
    final int hashIndex = (value & 0x7FFFFFFF) % values.length;
    final int[] bucket = values[hashIndex];
    if (bucket == null) {
      values[hashIndex] = new int[]{value};
      ++size;
    } else {
      int hashBucketIndex = 0;
      while (hashBucketIndex < bucket.length && bucket[hashBucketIndex] != value) {
        ++hashBucketIndex;
      }
      if (hashBucketIndex == bucket.length) {
        values[hashIndex] = Arrays.copyOf(values[hashIndex], hashBucketIndex + 1);
        ++size;
      }
      values[hashIndex][hashBucketIndex] = value;
    }
  }

  private void rehash() {
    final int newCapacity = (int) (values.length * 1.5F + 1);
    threshold = (int) (newCapacity * LOAD_FACTOR);
    final int [][] newValues = new int[newCapacity][];
    for (int i = 0; i < values.length; ++i) {
      if (values[i] != null) {
        for (int j = 0; j < values[i].length; ++j) {
          add(values[i][j], newValues);
        }
      }
    }
    this.values = newValues;
  }
}
