package org.typefactory.impl;

import java.util.Arrays;
import java.util.Objects;

class PrimitiveListOfInt {

  private static final int INITIAL_CAPACITY = 4;
  private int[] integers;

  private int size = 0;

  PrimitiveListOfInt() {
    this.integers = new int[INITIAL_CAPACITY];
  }

  private void ensureCapacity() {
    if ((integers.length - size) < INITIAL_CAPACITY) {
      integers = Arrays.copyOf(integers, integers.length + INITIAL_CAPACITY);
    }
  }

  boolean add(final int value) {
    ensureCapacity();
    integers[size++] = value;
    return true;
  }

  int remove(final int index) {
    Objects.checkIndex(index, size);
    int result = integers[index];
    System.arraycopy(integers, index + 1, integers, index, size-- - index - 1);
    return result;
  }

  public int get(final int index) {
    Objects.checkIndex(index, size);
    return integers[index];
  }

}
