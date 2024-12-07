package org.typefactory.impl;

import java.util.Arrays;

final class ImmutablePrimitiveHashMapOfIntKeyToIntValue implements PrimitiveHashMapOfIntKeyToIntValue {

  /**
   * 2-dimensional array for the keys which is aligned with the values array:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
   *   <li>second index/dimension to get the key values.</li>
   * </ul>
   */
  private final int[][] keys;

  /**
   * 2-dimensional array for the values which is aligned with the key array:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map values.</li>
   *   <li>second index/dimension to get the value which is an int.</li>
   * </ul>
   */
  private final int[][] values;

  private final int[] keySet;

  ImmutablePrimitiveHashMapOfIntKeyToIntValue(final int[] keySet, final int[][] keys, final int[][] values) {
    this.keySet = keySet;
    this.keys = keys;
    this.values = values;
  }


  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of keys in this hash map.
   */
  public int size() {
    return keySet.length;
  }

  /**
   * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
   *
   * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  public int[] keySet() {
    return Arrays.copyOf(keySet, keySet.length);
  }

  public boolean contains(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % keys.length;
    int[] bucket = keys[hashIndex];
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
   *
   * @param key the key identifying the entry in the map.
   * @return the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @see Integer#MIN_VALUE
   */
  public int get(final int key) {
    int hashIndex = (key & 0x7FFFFFFF) % keys.length;
    int[] bucket = keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return values[hashIndex][bucketIndex];
        }
      }
    }
    // We use Integer.MIN_VALUE as a sentinel value to indicate that no value exists for the key.
    return Integer.MIN_VALUE;
  }

  @Override
  public String toString() {
    return toEntryString();
  }

  @Override
  public String toDataStructureString() {
    return PrimitiveHashMapUtils.toDataStructureStringForMapsOfIntKeyToIntValue(keys, values);
  }

}
