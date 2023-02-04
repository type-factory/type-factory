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

final class PrimitiveSortedSetOfInt {

  private static final int CAPACITY_INCREMENT = 8;
  private int[] integers;

  private int size = 0;

  PrimitiveSortedSetOfInt() {
    this.integers = new int[CAPACITY_INCREMENT];
  }

  boolean add(final int value) {
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

  public int[] toArray() {
    return Arrays.copyOf(integers, size);
  }
}
