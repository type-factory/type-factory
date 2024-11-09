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

import static java.lang.Math.max;

import java.util.Arrays;
import java.util.Comparator;

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
final class PrimitiveHashMapOfIntKeyToIntValueBuilder implements PrimitiveHashMapOfIntKeyToIntValue {

  static final int DEFAULT_INITIAL_CAPACITY = 64;

  /**
   * The load factor for the hash map.
   */
  private static final float LOAD_FACTOR = 0.75F;

  private final int initialCapacity;

  /**
   * We will re-hash the map if its size exceeds this threshold.  The value of this field is (int)(capacity * loadFactor).
   */
  private int threshold;

  private PrimitiveSortedSetOfInt keySet = new PrimitiveSortedSetOfInt();

  private HashTable hashTable;

  PrimitiveHashMapOfIntKeyToIntValueBuilder() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  PrimitiveHashMapOfIntKeyToIntValueBuilder(final int initialCapacity) {
    this.initialCapacity = initialCapacity;
    clear();
  }

  void clear() {
    this.hashTable = new HashTable(initialCapacity);
    this.keySet = new PrimitiveSortedSetOfInt();
    this.threshold = (int) (initialCapacity * LOAD_FACTOR);

  }

  /**
   * Return the number of entries in this hash map.
   *
   * @return the number of keys in this hash map.
   */
  public int size() {
    return keySet.size();
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
    return keySet.toArray();
  }

  public boolean contains(final int key) {
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
   *
   * @param key the key identifying the entry in the map.
   * @return the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @see Integer#MIN_VALUE
   */
  public int get(final int key) {
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


  /**
   * Associates the specified {@code key} with the specified {@code value} in this hash map.
   *
   * @param key   the key identifying the entry in the map.
   * @param value the value to associate with the specified {@code key}.
   */
  void put(final int key, final int value) {
    if (value == Integer.MIN_VALUE) {
      remove(key);
      return;
    }
    if (threshold < (int) (size() * LOAD_FACTOR)) {
      rehash();
    }
    put(key, value, hashTable);
  }

  void putAll(final HashTable sourceHashTable, final HashTable targetHashTable) {
    for (int i = 0; i < sourceHashTable.keys.length; ++i) {
      if (sourceHashTable.keys[i] != null) {
        for (int j = 0; j < sourceHashTable.keys[i].length; ++j) {
          put(sourceHashTable.keys[i][j], sourceHashTable.values[i][j], targetHashTable);
        }
      }
    }
  }

  /**
   * Associates the specified {@code key} with the specified {@code value} in the specified {@code hashTable}.
   *
   * @param key   the key identifying the entry in the map.
   * @param value the value to associate with the specified {@code key}.
   * @return the index of the key-value pair in the hash bucket.
   */
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

  /**
   * Removes the entry for the specified {@code key} from this hash map if it is present.
   *
   * @param key the key identifying the entry in the map.
   * @return the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @see Integer#MIN_VALUE
   */
  int remove(final int key) {
    final int hashIndex = (key & 0x7FFFFFFF) % hashTable.keys.length;
    final int[] bucketKeys = hashTable.keys[hashIndex];
    final int[] bucketValues = hashTable.values[hashIndex];
    if (bucketKeys != null) {
      for (int i = 0; i < bucketKeys.length; i++) {
        if (bucketKeys[i] == key) {
          final int valueToRemove = bucketValues[i];
          final int[] newBucketKeys = new int[bucketKeys.length - 1];
          final int[] newBucketValues = new int[bucketValues.length - 1];
          System.arraycopy(bucketKeys, 0, newBucketKeys, 0, i);
          System.arraycopy(bucketKeys, i + 1, newBucketKeys, i, bucketKeys.length - i - 1);
          System.arraycopy(bucketValues, 0, newBucketValues, 0, i);
          System.arraycopy(bucketValues, i + 1, newBucketValues, i, bucketValues.length - i - 1);
          hashTable.keys[hashIndex] = newBucketKeys;
          hashTable.values[hashIndex] = newBucketValues;
          keySet.remove(key);
          return valueToRemove;
        }
      }
    }
    return Integer.MIN_VALUE; // Key not found
  }


  private void rehash() {
    final int newCapacity = (int) (hashTable.keys.length * 1.5F + 1);
    threshold = (int) (newCapacity * LOAD_FACTOR);
    final HashTable newHashTable = new HashTable(newCapacity);
    putAll(hashTable, newHashTable);
    this.hashTable = newHashTable;
  }

  PrimitiveHashMapOfIntKeyToIntValue build() {
    final int minBuckets = max(size(), 1);
    final int maxBuckets = minBuckets * 4;
    final HashOption[] hashOptions = new HashOption[maxBuckets - minBuckets];
    for (int numberOfBuckets = minBuckets, optionIndex = 0; numberOfBuckets < maxBuckets; ++numberOfBuckets, ++optionIndex) {
      hashOptions[optionIndex] = new HashOption(keySet(), numberOfBuckets);
      if (hashOptions[optionIndex].containsHashBucketsWithAtMostOneKey()) {
        break;
      }
    }
    Arrays.sort(hashOptions, HashOption.HASH_OPTION_COMPARATOR);
    final HashOption optimalHashOption = hashOptions[0];
    if (optimalHashOption.containsHashBucketsWithAtMostOneKey()) {
      final int[] newKeys = new int[optimalHashOption.numberOfHashBuckets];
      final int[] newValues = new int[optimalHashOption.numberOfHashBuckets];
      Arrays.fill(newKeys, Integer.MIN_VALUE);
      Arrays.fill(newValues, Integer.MIN_VALUE);
      for (int i = 0; i < hashTable.keys.length; ++i) {
        if (hashTable.keys[i] != null) {
          for (int j = 0; j < hashTable.keys[i].length; ++j) {
            final int key = hashTable.keys[i][j];
            final int value = hashTable.values[i][j];
            final int hashIndex = (key & 0x7FFFFFFF) % newKeys.length;
            newKeys[hashIndex] = key;
            newValues[hashIndex] = value;
          }
        }
      }
      return new OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue(keySet(), newKeys, newValues);
    } else {
      final HashTable newHashTable = new HashTable(optimalHashOption.numberOfHashBuckets);
      putAll(hashTable, newHashTable);
      return new ImmutablePrimitiveHashMapOfIntKeyToIntValue(keySet(), newHashTable.keys, newHashTable.values);
    }
  }

  @Override
  public String toString() {
    return toEntryString();
  }

  @Override
  public String toDataStructureString() {
    return PrimitiveHashMapUtils.toDataStructureStringForMapsOfIntKeyToIntValue(hashTable.keys, hashTable.values);
  }

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
    private final int[][] keys;

    /**
     * 2-dimensional array for the values which is aligned with the key array:
     * <ul>
     *   <li>first index/dimension to get the hash bucket containing the map values.</li>
     *   <li>second index/dimension to get the value which is an int.</li>
     * </ul>
     */
    private final int[][] values;

    public HashTable(final int initialCapacity) {
      this.keys = new int[initialCapacity][];
      this.values = new int[initialCapacity][];
    }
  }

  private static class HashOption {

    static final Comparator<HashOption> HASH_OPTION_COMPARATOR = Comparator.nullsLast(Comparator
        .comparingLong(HashOption::getWeighting)
        .thenComparing(HashOption::getNumberOfHashBuckets));

    /**
     * the number of hash-buckets that this hashing option explores.
     */
    private final int numberOfHashBuckets;
    /**
     * The number of keys in a hash bucket.
     */
    private final int[] hashBucketCounts;
    /**
     * The byte use to hold references to arrays – assuming 8 bytes (64 bits) per reference.
     */
    private long byteSizeOfArrayReferences = 0;

    private long penaltyForMultipleEntriesInHashBucket = 0;

    private HashOption(
        final int[] keySet,
        final int numberOfHashBuckets) {
      this.numberOfHashBuckets = numberOfHashBuckets;
      this.hashBucketCounts = new int[numberOfHashBuckets];
      for (int key : keySet) {
        addKey(key);
      }
      byteSizeOfArrayReferences = 8L; // keys array reference
      byteSizeOfArrayReferences += 8L; // values array reference
      if (containsHashBucketsWithMultipleKeys()) {
        byteSizeOfArrayReferences += numberOfHashBuckets * 8L;   // number of hash-bucket * 8 byte array object references for keys
        byteSizeOfArrayReferences += numberOfHashBuckets * 8L;   // number of hash-bucket * 8 byte array object references for values
        for (int i : hashBucketCounts) {
          if (i > 0) {
            penaltyForMultipleEntriesInHashBucket += (long) i * i;
          }
        }
      }
    }

    private void addKey(final int key) {
      int hashIndex = (key & 0x7FFFFFFF) % hashBucketCounts.length;
      hashBucketCounts[hashIndex]++;
    }

    private boolean containsHashBucketsWithMultipleKeys() {
      for (int hashBucketCount : hashBucketCounts) {
        if (hashBucketCount > 1) {
          return true;
        }
      }
      return false;
    }

    private boolean containsHashBucketsWithAtMostOneKey() {
      return !containsHashBucketsWithMultipleKeys();
    }

    private int getNumberOfHashBuckets() {
      return numberOfHashBuckets;
    }

    public long getWeighting() {
      return byteSizeOfArrayReferences + (numberOfHashBuckets * penaltyForMultipleEntriesInHashBucket);
    }
  }

}
