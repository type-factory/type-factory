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

import java.util.Arrays;
import java.util.Objects;

final class PrimitiveListOfInt {

  private static final int INITIAL_CAPACITY = 4;
  private int[] integers;

  private int size = 0;

  PrimitiveListOfInt() {
    this.integers = new int[INITIAL_CAPACITY];
  }

  private void ensureCapacity() {
    ensureCapacity(INITIAL_CAPACITY);
  }

  private void ensureCapacity(final int extraCapacity) {
    if ((integers.length - size) < extraCapacity) {
      integers = Arrays.copyOf(integers, integers.length + extraCapacity);
    }
  }

  boolean add(final int value) {
    ensureCapacity();
    integers[size++] = value;
    return true;
  }

  boolean addAll(final int... values) {
    if (values == null || values.length == 0) {
      return false;
    }
    ensureCapacity(INITIAL_CAPACITY + values.length);
    for (int v : values) {
      integers[size++] = v;
    }
    return true;
  }

  int remove(final int index) {
    Objects.checkIndex(index, size);
    int result = integers[index];
    System.arraycopy(integers, index + 1, integers, index, size-- - index - 1);
    return result;
  }

  int get(final int index) {
    Objects.checkIndex(index, size);
    return integers[index];
  }

  void sort() {
    Arrays.sort(integers, 0, size);
  }

  int [] toArray() {
    final int [] result = new int[size];
    System.arraycopy(integers, 0, result, 0, size);
    return result;
  }

}
