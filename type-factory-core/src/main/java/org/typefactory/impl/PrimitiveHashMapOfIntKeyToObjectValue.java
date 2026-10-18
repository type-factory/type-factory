/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * <p>A hash map with primitive int keys and object values. It is useful for using with Unicode codepoint keys to object values.</p>
 *
 * <p>This interface provides just the immutable methods and is extended by:</p>
 * <ul>
 *   <li>{@link ImmutablePrimitiveHashMapOfIntKeyToObjectValue}.</li>
 *   <li>{@link MutablePrimitiveHashMapOfIntKeyToObjectValue}.</li>
 * </ul>
 *
 * <p>This interface is implemented by:</p>
 * <ul>
 *   <li>{@link ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl}.</li>
 *   <li>{@link MutablePrimitiveHashMapOfIntKeyToObjectValueImpl}.</li>
 * </ul>
 *
 * @param <T> the type of the values
 */
sealed interface PrimitiveHashMapOfIntKeyToObjectValue<T>
    permits ImmutablePrimitiveHashMapOfIntKeyToObjectValue,
    MutablePrimitiveHashMapOfIntKeyToObjectValue {

  /**
   * Returns an empty immutable hash map of primitive int keys to object values.
   *
   * @param <T> the type of the values
   * @return an empty immutable hash map of primitive int keys to object values
   */
  @SuppressWarnings("unchecked")
  static <T extends PrimitiveHashMapOfIntKeyToObjectValue<?>> T empty() {
    return (T) ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl.EMPTY;
  }

  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of keys in this hash map.
   */
  default int size() {
    return keySet().size();
  }

  /**
   * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
   *
   * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
   */
  default boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Returns the set of {@code int} keys in this hash map as an {@link ImmutableSortedSetOfInt}.
   *
   * @return the set of keys in this hash map.
   */
  ImmutableSortedSetOfInt keySet();

  /**
   * Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key
   */
  T get(int key);

  Iterable<T> values();
}

sealed interface MutablePrimitiveHashMapOfIntKeyToObjectValue<T>
    extends PrimitiveHashMapOfIntKeyToObjectValue<T>
    permits MutablePrimitiveHashMapOfIntKeyToObjectValueImpl {

  void put(int key, T value);

  ImmutablePrimitiveHashMapOfIntKeyToObjectValue<T> toImmutable();

  <R> ImmutablePrimitiveHashMapOfIntKeyToObjectValue<R> toImmutable(Function<T, R> immutableValueTransformer);
}

sealed interface ImmutablePrimitiveHashMapOfIntKeyToObjectValue<T>
    extends PrimitiveHashMapOfIntKeyToObjectValue<T>
    permits ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl {

}

/**
 * <p>This is a hash-map of integer keys mapped to object values.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to a sequence of code points.</li>
 *   <li>a Unicode category identified by an integer to a sequence of code points.</li>
 * </ul>
 */
final class MutablePrimitiveHashMapOfIntKeyToObjectValueImpl<T extends Object>
    implements MutablePrimitiveHashMapOfIntKeyToObjectValue<T> {

  static final int INITIAL_CAPACITY = 20;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  /**
   * Use and internal struct-like class to ensure an atomic transfer after a rehash.
   */
  private static class HashTable<T> {

    /**
     * 2-dimensional array for the keys that is aligned with the value array:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
     *   <li>second index/dimension to get the key values.</li>
     * </ul>
     */
    private int[][] keys;

    /**
     * 2-dimensional array for the values that is aligned with the key array. It appears to be 3-dimensional, but that is because the map-values are
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

  private final MutableSortedSetOfIntImpl keySet = new MutableSortedSetOfIntImpl();

  @SuppressWarnings("unchecked")
  MutablePrimitiveHashMapOfIntKeyToObjectValueImpl() {
    this.hashTable = new HashTable<>();
    this.hashTable.keys = new int[INITIAL_CAPACITY][];
    this.hashTable.values = (T[][]) new Object[INITIAL_CAPACITY][];
    this.threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
  }

  @Override
  public ImmutableSortedSetOfInt keySet() {
    return keySet.toImmutable();
  }

  @Override
  public T get(final int key) {
    return PrimitiveHashMapOfIntKeyToObjectValueCommon.get(key, hashTable.keys, hashTable.values);
  }

  @Override
  public void put(final int key, final T value) {
    if (threshold < (int) (size() * LOAD_FACTOR)) {
      rehash();
    }
    put(key, value, hashTable);
  }

  @Override
  public ImmutablePrimitiveHashMapOfIntKeyToObjectValue<T> toImmutable() {
    return isEmpty()
        ? PrimitiveHashMapOfIntKeyToObjectValue.empty()
        : new ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl<>(
            keySet.toImmutable(), hashTable.keys, hashTable.values);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <R> ImmutablePrimitiveHashMapOfIntKeyToObjectValue<R> toImmutable(
      final Function<T, R> immutableValueTransformer) {

    if (isEmpty()) {
      return PrimitiveHashMapOfIntKeyToObjectValue.empty();
    }
    final int[][] keys = hashTable.keys;
    final T[][] values = hashTable.values;
    final R[][] transformedValues = (R[][]) new Object[values.length][];
    for (int i = 0; i < values.length; ++i) {
      if (values[i] != null) {
        transformedValues[i] = (R[]) new Object[values[i].length];
        for (int j = 0; j < values[i].length; ++j) {
          transformedValues[i][j] = immutableValueTransformer.apply(values[i][j]);
        }
      }
    }
    return new ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl<>(
        keySet.toImmutable(), keys, transformedValues);
  }

  @Override
  public Iterable<T> values() {
    return PrimitiveHashMapOfIntKeyToObjectValueCommon.values(hashTable.values);
  }

  private void put(final int key, final T value, final HashTable<T> hashTable) {
    final int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    final int[] bucket = hashTable.keys[hashIndex];
    if (bucket == null) {
      hashTable.keys[hashIndex] = new int[]{key};
      hashTable.values[hashIndex] = (T[]) new Object[]{value};
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

/**
 * <p>This is a hash-map of integer keys mapped to object values.</p>
 *
 * <p>We can use it to map:</p>
 * <ul>
 *   <li>a single code point to a sequence of code points.</li>
 *   <li>a Unicode category identified by an integer to a sequence of code points.</li>
 * </ul>
 */
final class ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl<T>
    implements ImmutablePrimitiveHashMapOfIntKeyToObjectValue<T> {

  static final ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl<?> EMPTY =
      new ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl<>(
          SortedSetOfInt.empty(), new int[0][], new Object[0][]);

  private final ImmutableSortedSetOfInt keySet;

  /**
   * 2-dimensional array for the keys that is aligned with the value array:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
   *   <li>second index/dimension to get the key values.</li>
   * </ul>
   */
  private final int[][] keys;

  /**
   * 2-dimensional array for the values that is aligned with the key array. It appears to be 3-dimensional, but that is because the map-values are
   * actually int-arrays:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map values.</li>
   *   <li>second index/dimension to get the value objects which are objects of type T.</li>
   * </ul>
   */
  private final T[][] values;

  ImmutablePrimitiveHashMapOfIntKeyToObjectValueImpl(
      final ImmutableSortedSetOfInt keyset, final int[][] keys, final T[][] values) {

    this.keySet = keyset;

    // Perform deep copies of the arrays to ensure immutability
    this.keys = ArrayUtils.deepCopy(keys);
    this.values = ArrayUtils.deepCopy(values);
  }

  @Override
  public ImmutableSortedSetOfInt keySet() {
    return keySet;
  }

  @Override
  public T get(final int key) {
    return PrimitiveHashMapOfIntKeyToObjectValueCommon.get(key, keys, values);
  }

  @Override
  public Iterable<T> values() {
    return PrimitiveHashMapOfIntKeyToObjectValueCommon.values(values);
  }
}

final class PrimitiveHashMapOfIntKeyToObjectValueCommon {

  private PrimitiveHashMapOfIntKeyToObjectValueCommon() {
    // Prevent instantiation
  }

  static <T> T get(int key, int[][] keys, T[][] values) {
    int hashIndex = (key & 0x7FFFFFFF) % keys.length;
    int[] bucket = keys[hashIndex];
    if (bucket != null) {
      for (int bucketIndex = 0; bucketIndex < bucket.length; ++bucketIndex) {
        if (bucket[bucketIndex] == key) {
          return values[hashIndex][bucketIndex];
        }
      }
    }
    return null;
  }

  static <T> Iterable<T> values(T[][] values) {
    List<T> allValues = new ArrayList<>();
    for (int i = 0; i < values.length; ++i) {
      final var bucket = values[i];
      if (bucket != null) {
        for (int j = 0; j < bucket.length; ++j) {
          allValues.add(bucket[j]);
        }
      }
    }
    return allValues;
  }
}
