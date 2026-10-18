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

public final class ArrayUtils {

  private ArrayUtils() {
  }

  public static int[] deepCopy(final int[] original) {
    return original == null
        ? null
        : java.util.Arrays.copyOf(original, original.length);
  }

  public static int[][] deepCopy(final int[][] original) {
    if (original == null) {
      return null;
    }
    final int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; ++i) {
      copy[i] = original[i] == null
          ? null
          : java.util.Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }

  public static int[][][] deepCopy(final int[][][] original) {
    if (original == null) {
      return null;
    }
    final int[][][] copy = new int[original.length][][];
    for (int i = 0; i < original.length; ++i) {
      final var originalI = original[i];
      if (originalI == null) {
        copy[i] = null;
      } else {
        copy[i] = new int[originalI.length][];
        for (int j = 0; j < originalI.length; ++j) {
          final var originalIJ = originalI[j];
          copy[i][j] = originalIJ == null
              ? null
              : java.util.Arrays.copyOf(originalIJ, originalIJ.length);
        }
      }
    }
    return copy;
  }

  public static char[][][] deepCopy(final char[][][] original) {
    if (original == null) {
      return null;
    }
    final char[][][] copy = new char[original.length][][];
    for (int i = 0; i < original.length; ++i) {
      final var originalI = original[i];
      if (originalI == null) {
        copy[i] = null;
      } else {
        copy[i] = new char[originalI.length][];
        for (int j = 0; j < originalI.length; ++j) {
          final var originalIJ = originalI[j];
          copy[i][j] = originalIJ == null
              ? null
              : java.util.Arrays.copyOf(originalIJ, originalIJ.length);
        }
      }
    }
    return copy;
  }

  public static <T> T[][] deepCopy(final T[][] original) {
    if (original == null) {
      return null;
    }
    @SuppressWarnings("unchecked")
    final T[][] copy = (T[][]) new Object[original.length][];
    for (int i = 0; i < original.length; ++i) {
      copy[i] = original[i] == null
          ? null
          : java.util.Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }

}
