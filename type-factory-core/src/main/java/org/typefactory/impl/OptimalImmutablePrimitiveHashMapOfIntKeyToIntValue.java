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

import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;
import static org.typefactory.impl.StringBuilderUtils.appendHexWithZeroPadding;

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
final class OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue implements PrimitiveHashMapOfIntKeyToIntValue {

  /**
   * Array for the keys which is aligned with the values array:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map keys.</li>
   * </ul>
   */
  private final int[] keys;

  /**
   * Array for the values which is aligned with the key array:
   * <ul>
   *   <li>first index/dimension to get the hash bucket containing the map values.</li>
   * </ul>
   */
  private final int[] values;

  /**
   * The sorted key set for this hash map.
   */
  private final int[] keySet;

  OptimalImmutablePrimitiveHashMapOfIntKeyToIntValue(final int [] keySet, final int[] keys, final int[] values) {
    this.keys = keys;
    this.values = values;
    this.keySet = keySet;
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
    return keys[hashIndex] == key;
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
    if (keys[hashIndex] == key) {
      return values[hashIndex];
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
    final StringBuilder s = new StringBuilder();
    s.append("int[] keys = new int[]{");
    for (int i = 0; i < keys.length; ++i) {
      if (i % 8 == 0) {
        s.append(SYSTEM_LINE_SEPARATOR).append("    ");
      }
      appendHexWithZeroPadding(s, keys[i]);
      s.append(", ");
    }
    if (s.charAt(s.length() - 2) == ',') {
        s.setLength(s.length() - 2);
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    s.append("int[] values = new int[]{");
    for (int i = 0; i < values.length; ++i) {
      if (i % 8 == 0) {
        s.append(SYSTEM_LINE_SEPARATOR).append("    ");
      }
      appendHexWithZeroPadding(s, values[i]);
      s.append(", ");
    }
    if (s.charAt(s.length() - 2) == ',') {
      s.setLength(s.length() - 2);
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    return s.toString();
  }
}
