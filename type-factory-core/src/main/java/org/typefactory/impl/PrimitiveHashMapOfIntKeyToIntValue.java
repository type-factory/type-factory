package org.typefactory.impl;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

interface PrimitiveHashMapOfIntKeyToIntValue {

  static PrimitiveHashMapOfIntKeyToIntValueBuilder builder() {
    return new PrimitiveHashMapOfIntKeyToIntValueBuilder();
  }

  int size();

  /**
   * Returns {@code true} if there are no entries in this hash map and {@code false} otherwise.
   *
   * @return {@code true} if there are no entries in this hash map and {@code false} otherwise.
   */
  boolean isEmpty();

  int[] keySet();

  boolean contains(final int key);

  /**
   * Returns the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   *
   * @param key the key identifying the entry in the map.
   * @return the value for the specified {@code key} or {@link Integer#MIN_VALUE} if no such key exists.
   * @see Integer#MIN_VALUE
   */
  int get(final int key);

  default String toEntryString() {
    return stream(keySet())
        .mapToObj(key -> {
          var value = get(key);
          if (Character.isValidCodePoint(value)) {
            return format("0x%08X ⟶ 0x%08X, %c ⟶ %c", key, value, key, value);
          } else {
            return format("0x%08X ⟶ 0x%08X", key, value);
          }})
        .collect(joining("\n"));
  }

  String toDataStructureString();

}


