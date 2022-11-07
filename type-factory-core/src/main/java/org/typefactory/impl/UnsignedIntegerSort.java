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

public class UnsignedIntegerSort {

  private UnsignedIntegerSort() {
  }

  public static void sort(int[] a) {
    sort(a, 0, a.length);
  }

  public static void sort(int[] a, int fromIndex, int toIndex) {
    rangeCheck(a.length, fromIndex, toIndex);
    unsignedIntegerDualPivotQuicksort(a, fromIndex, toIndex - 1, 3);
  }

  private static void rangeCheck(int length, int fromIndex, int toIndex) {
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException("fromIndex > toIndex");
    }
    if (fromIndex < 0) {
      throw new ArrayIndexOutOfBoundsException(fromIndex);
    }
    if (toIndex > length) {
      throw new ArrayIndexOutOfBoundsException(toIndex);
    }
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static void unsignedIntegerDualPivotQuicksort(int[] a, int left, int right, int div) {

    int len = right - left;
    if (len < 27) { // insertion sort for tiny array
      for (int i = left + 1; i <= right; i++) {
        for (int j = i;
            j > left && (0x00000000_ffffffffL & a[j]) < (0x00000000_ffffffffL & a[j - 1]);
            j--) {
          swap(a, j, j - 1);
        }
      }
      return;
    }
    int third = len / div;
    // "medians"
    int m1 = left + third;
    int m2 = right - third;
    if (m1 <= left) {
      m1 = left + 1;
    }
    if (m2 >= right) {
      m2 = right - 1;
    }
    if ((0x00000000_ffffffffL & a[m1]) < (0x00000000_ffffffffL & a[m2])) {
      swap(a, m1, left);
      swap(a, m2, right);
    } else {
      swap(a, m1, right);
      swap(a, m2, left);
    }
    // pivots
    long pivot1 = (0x00000000_ffffffffL & a[left]);
    long pivot2 = (0x00000000_ffffffffL & a[right]);
    // pointers
    int less = left + 1;
    int great = right - 1;
    // sorting
    for (int k = less; k <= great; k++) {
      if ((0x00000000_ffffffffL & a[k]) < pivot1) {
        swap(a, k, less++);
      } else if ((0x00000000_ffffffffL & a[k]) > pivot2) {
        while (k < great && (0x00000000_ffffffffL & a[great]) > pivot2) {
          great--;
        }
        swap(a, k, great--);
        if ((0x00000000_ffffffffL & a[k]) < pivot1) {
          swap(a, k, less++);
        }
      }
    }
    // swaps
    int dist = great - less;
    if (dist < 13) {
      div++;
    }
    swap(a, less - 1, left);
    swap(a, great + 1, right);
    // subarrays
    unsignedIntegerDualPivotQuicksort(a, left, less - 2, div);
    unsignedIntegerDualPivotQuicksort(a, great + 2, right, div);
    // equal elements
    if (dist > len - 13 && pivot1 != pivot2) {
      for (int k = less; k <= great; k++) {
        if ((0x00000000_ffffffffL & a[k]) == pivot1) {
          swap(a, k, less++);
        } else if ((0x00000000_ffffffffL & a[k]) == pivot2) {
          swap(a, k, great--);
          if ((0x00000000_ffffffffL & a[k]) == pivot1) {
            swap(a, k, less++);
          }
        }
      }
    }
    // subarray
    if (pivot1 < pivot2) {
      unsignedIntegerDualPivotQuicksort(a, less, great, div);
    }
  }
}
