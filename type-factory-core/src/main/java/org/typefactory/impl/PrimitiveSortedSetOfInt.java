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

class PrimitiveSortedSetOfInt {

  private static final int CAPACITY_INCREMENT = 4;
  private int[] integers;

  private int size = 0;

  PrimitiveSortedSetOfInt() {
    this.integers = new int[CAPACITY_INCREMENT];
  }

  private void ensureCapacity() {
    if ((integers.length - size) < CAPACITY_INCREMENT) {
      integers = Arrays.copyOf(integers, integers.length + CAPACITY_INCREMENT);
    }
  }

  boolean add(final int value) {
    ensureCapacity();
    if (size < 5) {
      for (int i = 0; i < size; ++i) {
        if (integers[i] == value) {
          return false;
        }
        if (integers[i] > value) {
          System.arraycopy(integers, i, integers, i + 1, size - i);
          integers[i] = value;
          return true;
        }
      }
    } else {
      final int i = Arrays.binarySearch(integers, 0, size, value);
      if (integers[i] == value) {
        return false;
      }
      System.arraycopy(integers, i, integers, i + 1, size - i);
      integers[i] = value;
      return true;
    }
    integers[size++] = value;
    return true;
  }

  public int[] toArray() {
    return Arrays.copyOf(integers, size);
  }
}
