package org.typefactory.impl;

import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;
import static org.typefactory.impl.StringBuilderUtils.appendHexWithZeroPadding;

interface PrimitiveHashMapOfIntKeyToIntValue {

  static PrimitiveHashMapOfIntKeyToIntValueBuilder builder() {
    return new PrimitiveHashMapOfIntKeyToIntValueBuilderImpl();
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
    final StringBuilder s = new StringBuilder();
    int value;
    for (int key : keySet()) {
      value = get(key);
      appendHexWithZeroPadding(s, key);
      s.append(" ⟶ ");
      appendHexWithZeroPadding(s, value);
      // We use Integer.MIN_VALUE as a sentinel value to indicate that no value exists for the key.
      if (value > Integer.MIN_VALUE) {
        s.append(", ").appendCodePoint(key).append(" ⟶ ").appendCodePoint(value);
      }
      s.append(SYSTEM_LINE_SEPARATOR);
    }
    if (s.length() >= SYSTEM_LINE_SEPARATOR.length()) {
      s.setLength(s.length() - SYSTEM_LINE_SEPARATOR.length());
    }
    return s.toString();
  }

  String toDataStructureString();

  interface PrimitiveHashMapOfIntKeyToIntValueBuilder extends PrimitiveHashMapOfIntKeyToIntValue {

    void clear();

    int size();

    boolean isEmpty();

    int[] keySet();

    boolean contains(int key);

    int get(int key);

    void put(int key, int value);

    int remove(int key);

    PrimitiveHashMapOfIntKeyToIntValue build();

    @Override
    String toString();

    String toDataStructureString();
  }
}


