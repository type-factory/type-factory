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

import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;

import java.util.Arrays;

/**
 * <p>A sorted set of primitive {@code int} values.</p>
 *
 * <p>This sorted set is backed by an array of {@code int}. It is not backed by a tree structure like the typical Java {@link java.util.SortedSet}.
 * It is efficient for small sets of values, and more memory efficient.</p>
 *
 * <p>Being backed by makes it possible to provide a {@link #get(int)} method to get the value at a specified index.</p>
 *
 * <p>This interface provides just the immutable methods, and it is extended by:</p>
 * <ul>
 *   <li>{@link ImmutableSortedSetOfInt}</li>
 *   <li>{@link MutableSortedSetOfInt}</li>
 * </ul>
 */
sealed interface SortedSetOfInt permits ImmutableSortedSetOfInt, MutableSortedSetOfInt {

  /**
   * Returns an empty immutable sorted set of primitive int values.
   *
   * @return an empty immutable sorted set of primitive int values
   */
  static ImmutableSortedSetOfInt empty() {
    return EmptyImmutableSortedSetOfIntImpl.EMPTY;
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
   * Returns true if this set contains the specified value.
   *
   * @param value the value to check for containment
   * @return true if this set contains the specified value, false otherwise
   */
  boolean contains(int value);

  /**
   * <p>Returns the value at the specified index in this sorted set.</p>
   *
   * @param index the index of the value to return
   * @return the value at the specified index
   * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
   */
  int get(int index);

  /**
   * Returns an array containing all the elements in this set in sorted order.
   *
   * @return an array containing all the elements in this set in sorted order
   */
  int[] toArray();
}

sealed interface MutableSortedSetOfInt
    extends SortedSetOfInt
    permits MutableSortedSetOfIntImpl {

  boolean add(int value);

  ImmutableSortedSetOfInt toImmutable();
}

sealed interface ImmutableSortedSetOfInt
    extends SortedSetOfInt
    permits EmptyImmutableSortedSetOfIntImpl,
    ImmutableSortedSetOfIntImpl {

}

final class MutableSortedSetOfIntImpl implements MutableSortedSetOfInt {

  private static final int CAPACITY_INCREMENT = 4;
  private int[] integers;

  private int size = 0;

  MutableSortedSetOfIntImpl() {
    this.integers = new int[CAPACITY_INCREMENT];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(int value) {
    return PrimitiveSortedSetOfIntUtils.contains(integers, size, value);
  }

  @Override
  public int get(int index) {
    return PrimitiveSortedSetOfIntUtils.get(integers, size, index);
  }

  @Override
  public boolean add(final int value) {
    ensureCapacity();
    if (size < 5) {
      for (int i = 0; i < size; ++i) {
        if (integers[i] == value) {
          return false;
        }
        if (integers[i] > value) {
          insertIntoArray(value, i);
          return true;
        }
      }
    } else {
      int i = Arrays.binarySearch(integers, 0, size, value);
      if (i < 0) {
        insertIntoArray(value, -i - 1);
        return true;
      }
      return false;
    }
    integers[size++] = value;
    return true;
  }

  @Override
  public int[] toArray() {
    // Defensive copy to ensure contents are not modified outside of this class
    return Arrays.copyOf(integers, size);
  }

  @Override
  public ImmutableSortedSetOfInt toImmutable() {
    return isEmpty()
        ? SortedSetOfInt.empty()
        : new ImmutableSortedSetOfIntImpl(integers, size);
  }

  private void ensureCapacity() {
    if (integers.length == size) {
      integers = Arrays.copyOf(integers, integers.length + CAPACITY_INCREMENT);
    }
  }

  private void insertIntoArray(final int value, final int index) {
    System.arraycopy(integers, index, integers, index + 1, size - index);
    integers[index] = value;
    ++size;
  }
}

final class EmptyImmutableSortedSetOfIntImpl implements ImmutableSortedSetOfInt {

  static final ImmutableSortedSetOfInt EMPTY = new EmptyImmutableSortedSetOfIntImpl();

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean contains(int value) {
    return false;
  }

  @Override
  public int get(int index) {
    throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
  }

  @Override
  public int[] toArray() {
    return EMPTY_INT_ARRAY;
  }
}

final class ImmutableSortedSetOfIntImpl implements ImmutableSortedSetOfInt {

  private final int[] integers;

  ImmutableSortedSetOfIntImpl(final int[] integers, final int length) {
    // Defensive copy to ensure immutability
    this.integers = Arrays.copyOf(integers, length);
  }

  @Override
  public int size() {
    return integers.length;
  }

  @Override
  public boolean contains(int value) {
    return PrimitiveSortedSetOfIntUtils.contains(integers, integers.length, value);
  }

  @Override
  public int get(int index) {
    return PrimitiveSortedSetOfIntUtils.get(integers, integers.length, index);
  }

  public int[] toArray() {
    // Defensive copy to ensure immutability
    return Arrays.copyOf(integers, integers.length);
  }
}

final class PrimitiveSortedSetOfIntUtils {

  private PrimitiveSortedSetOfIntUtils() {
  }

  static boolean contains(final int[] array, final int size, final int value) {
    return switch (size) {
      case 0 -> false;
      case 1 -> array[0] == value;
      case 2 -> array[0] == value ||
                array[1] == value;
      case 3 -> array[0] == value ||
                array[1] == value ||
                array[2] == value;
      case 4 -> array[0] == value ||
                array[1] == value ||
                array[2] == value ||
                array[3] == value;
      default -> Arrays.binarySearch(array, 0, size, value) >= 0;
    };
  }

  static int get(final int[] array, final int size, final int index) {
    if (index >= 0 && index < size) {
      return array[index];
    }
    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

}

