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

import static org.typefactory.impl.Constants.EMPTY_STRING_ARRAY;

import java.util.Arrays;

/**
 * <p>A sorted set of String values.</p>
 *
 * <p>This sorted set is backed by an array of String. It is not backed by a tree structure like the typical Java {@link java.util.SortedSet}.
 * It is efficient for small sets of values, and more memory efficient.</p>
 *
 * <p>Being backed by makes it possible to provide a {@link #get(int)} method to get the value at a specified index.</p>
 *
 * <p>This interface provides just the immutable methods, and it is extended by:</p>
 * <ul>
 *   <li>{@link ImmutableSortedSetOfString}</li>
 *   <li>{@link MutableSortedSetOfString}</li>
 * </ul>
 */
sealed interface SortedSetOfString permits ImmutableSortedSetOfString, MutableSortedSetOfString {

  /**
   * Returns an empty immutable sorted set of String values.
   *
   * @return an empty immutable sorted set of String values
   */
  static ImmutableSortedSetOfString empty() {
    return EmptyImmutableSortedSetOfStringImpl.EMPTY;
  }

  /**
   * Returns the number of elements in this set.
   *
   * @return the number of elements in this set
   */
  int size();

  /**
   * Returns true if this set contains no elements.
   *
   * @return true if this set contains no elements, false otherwise
   */
  default boolean isEmpty() {
    return size() == 0;
  }

  /**
   * <p>Returns true if this set contains the specified value.</p>
   *
   * @param value the value to check for containment
   * @return true if this set contains the specified value, false otherwise
   */
  boolean contains(String value);

  /**
   * <p>Returns true if this set contains the specified value.</p>
   *
   * @param value the value to check for containment
   * @return true if this set contains the specified value, false otherwise
   */
  boolean contains(CharSequence value);

  /**
   * <p>Returns the value at the specified index in this sorted set.</p>
   *
   * @param index the index of the value to return
   * @return the value at the specified index
   * @throws IndexOutOfBoundsException if the index is not in the range {@code 0 <= index < size()}
   */
  String get(int index);

  /**
   * Returns an array containing all the elements in this set in sorted order.
   *
   * @return an array containing all the elements in this set in sorted order
   */
  String[] toArray();
}

sealed interface MutableSortedSetOfString
    extends SortedSetOfString
    permits MutableSortedSetOfStringImpl {

  /**
   * <p>Adds the specified value to this set if it is not already present.
   * Null values will be ignored and not be added to the set.</p>
   *
   * @param value the value to be added to this set. Null values will be ignored and not be added to the set.
   * @return true if this set did not already contain the specified value, false if the value was already present or if the provided value was null.
   */
  boolean add(String value);

  /**
   * Returns an immutable copy of this set.
   *
   * @return an immutable copy of this set
   */
  ImmutableSortedSetOfString toImmutable();
}

sealed interface ImmutableSortedSetOfString
    extends SortedSetOfString
    permits EmptyImmutableSortedSetOfStringImpl,
    ImmutableSortedSetOfStringImpl {

}

final class MutableSortedSetOfStringImpl implements MutableSortedSetOfString {

  private static final int CAPACITY_INCREMENT = 4;
  private String[] strings;

  private int size = 0;

  MutableSortedSetOfStringImpl() {
    this.strings = new String[CAPACITY_INCREMENT];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(final String value) {
    return PrimitiveSortedSetOfStringCommon.contains(strings, size, value);
  }

  @Override
  public boolean contains(CharSequence value) {
    for (String string : strings) {
      if (string.contentEquals(value)) {
        return true;
      }
    }
    return false;
  }


  @Override
  public String get(final int index) {
    return PrimitiveSortedSetOfStringCommon.get(strings, size, index);
  }

  @Override
  public boolean add(final String value) {
    if (value == null) {
      return false;
    }
    ensureCapacity();
    if (size < 5) {
      for (int i = 0; i < size; ++i) {
        if (strings[i].equals(value)) {
          return false;
        }
        if (strings[i].compareTo(value) > 0) {
          insertIntoArray(value, i);
          return true;
        }
      }
    } else {
      int i = Arrays.binarySearch(strings, 0, size, value);
      if (i < 0) {
        insertIntoArray(value, -i - 1);
        return true;
      }
      return false;
    }
    strings[size++] = value;
    return true;
  }

  @Override
  public String[] toArray() {
    // Defensive copy to ensure contents are not modified outside of this class
    return Arrays.copyOf(strings, size);
  }

  @Override
  public ImmutableSortedSetOfString toImmutable() {
    return isEmpty()
        ? SortedSetOfString.empty()
        : new ImmutableSortedSetOfStringImpl(strings);
  }

  private void ensureCapacity() {
    if (strings.length == size) {
      strings = Arrays.copyOf(strings, strings.length + CAPACITY_INCREMENT);
    }
  }

  private void insertIntoArray(final String value, final int index) {
    System.arraycopy(strings, index, strings, index + 1, size - index);
    strings[index] = value;
    ++size;
  }
}

final class EmptyImmutableSortedSetOfStringImpl implements ImmutableSortedSetOfString {

  static final ImmutableSortedSetOfString EMPTY = new EmptyImmutableSortedSetOfStringImpl();

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean contains(String value) {
    return false;
  }

  @Override
  public boolean contains(CharSequence value) {
    return false;
  }

  @Override
  public String get(int index) {
    throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
  }

  @Override
  public String[] toArray() {
    return EMPTY_STRING_ARRAY;
  }
}

final class ImmutableSortedSetOfStringImpl implements ImmutableSortedSetOfString {

  private final String[] strings;

  ImmutableSortedSetOfStringImpl(final String[] strings) {
    // Defensive copy to ensure immutability
    this.strings = Arrays.copyOf(strings, strings.length);
  }

  @Override
  public int size() {
    return strings.length;
  }

  @Override
  public boolean contains(String value) {
    return PrimitiveSortedSetOfStringCommon.contains(strings, strings.length, value);
  }

  @Override
  public boolean contains(CharSequence value) {
    return PrimitiveSortedSetOfStringCommon.contains(strings, strings.length, value);
  }

  @Override
  public String get(int index) {
    return PrimitiveSortedSetOfStringCommon.get(strings, strings.length, index);
  }

  public String[] toArray() {
    // Defensive copy to ensure immutability
    return Arrays.copyOf(strings, strings.length);
  }
}

final class PrimitiveSortedSetOfStringCommon {

  private PrimitiveSortedSetOfStringCommon() {
  }

  static boolean contains(final String[] array, final int size, final String value) {
    return switch (size) {
      case 0 -> false;
      case 1 -> array[0].equals(value);
      case 2 -> array[0].equals(value) ||
                array[1].equals(value);
      case 3 -> array[0].equals(value) ||
                array[1].equals(value) ||
                array[2].equals(value);
      case 4 -> array[0].equals(value) ||
                array[1].equals(value) ||
                array[2].equals(value) ||
                array[3].equals(value);
      default -> Arrays.binarySearch(array, 0, size, value) >= 0;
    };
  }

  static boolean contains(final String[] array, final int size, final CharSequence value) {
    if (value instanceof String s) {
      return contains(array, size, s);
    }
    return switch (size) {
      case 0 -> false;
      case 1 -> array[0].contentEquals(value);
      case 2 -> array[0].contentEquals(value) ||
                array[1].contentEquals(value);
      case 3 -> array[0].contentEquals(value) ||
                array[1].contentEquals(value) ||
                array[2].contentEquals(value);
      case 4 -> array[0].contentEquals(value) ||
                array[1].contentEquals(value) ||
                array[2].contentEquals(value) ||
                array[3].contentEquals(value);
      default -> Arrays.binarySearch(array, 0, size, value, CharSequence::compare) >= 0;
    };
  }

  static String get(final String[] array, final int size, final int index) {
    if (index >= 0 && index < size) {
      return array[index];
    }
    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

}

