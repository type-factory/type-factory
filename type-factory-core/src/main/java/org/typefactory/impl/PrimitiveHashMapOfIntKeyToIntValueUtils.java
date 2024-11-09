package org.typefactory.impl;

import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;
import static org.typefactory.impl.StringBuilderUtils.appendHexWithZeroPadding;

final class PrimitiveHashMapOfIntKeyToIntValueUtils {

  /**
   * Returns a string representation of a primitive HashMap of {@code int} keys to {@code int} values.
   *
   * @param keys   2-dimensional array for the keys which is aligned with the values array:
   *               <ul>
   *                 <li>first index/dimension to get the hash bucket containing the map keys.</li>
   *                 <li>second index/dimension to get the key values.</li>
   *               </ul>
   * @param values 2-dimensional array for the values which is aligned with the key array:
   *               <ul>
   *                 <li>first index/dimension to get the hash bucket containing the map values.</li>
   *                 <li>second index/dimension to get the value which is an int.</li>
   *               </ul>
   * @return a string representation of a primitive HashMap of {@code int} keys to {@code int} values.
   */
  static String toDataStructureString(int[][] keys, int[][] values) {
    final StringBuilder s = new StringBuilder();
    s.append("int[][] keys = new int[][]{").append(SYSTEM_LINE_SEPARATOR);
    for (int[] key : keys) {
      if (key == null) {
        s.append("    null,").append(SYSTEM_LINE_SEPARATOR);
      } else {
        s.append("    new int[]{");
        for (int i = 0; i < key.length; ++i) {
          appendHexWithZeroPadding(s, key[i]);
          if (i < key.length - 1) {
            s.append(", ");
          }
        }
        s.append("},").append(SYSTEM_LINE_SEPARATOR);
      }
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    s.append("int[][] values = new int[][]{").append(SYSTEM_LINE_SEPARATOR);
    for (int[] value : values) {
      if (value == null) {
        s.append("    null,").append(SYSTEM_LINE_SEPARATOR);
      } else {
        s.append("    new int[]{");
        for (int i = 0; i < value.length; ++i) {
          appendHexWithZeroPadding(s, value[i]);
          if (i < value.length - 1) {
            s.append(", ");
          }
        }
        s.append("},").append(SYSTEM_LINE_SEPARATOR);
      }
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    return s.toString();
  }

  /**
   * <p>Returns a string representation of an 'optimal' primitive HashMap of {@code int} keys to {@code int} values.</p>
   *
   * <p>An 'optimal' primitive hashmap has at most one key in each hash bucket, which reduces the number of arrays used to store the data.</p>
   *
   * @param keys   Array for the keys which is aligned with the values array:
   *               <ul>
   *                 <li>first index/dimension to get the hash bucket containing the map keys.</li>
   *               </ul>
   * @param values Array for the values which is aligned with the key array:
   *               <ul>
   *                 <li>first index/dimension to get the hash bucket containing the map values.</li>
   *               </ul>
   * @return a string representation of an 'optimal' primitive HashMap of {@code int} keys to {@code int} values.
   */
  static String toDataStructureString(int[] keys, int[] values) {
    final StringBuilder s = new StringBuilder();
    s.append("int[] keys = new int[]{");
    if (keys.length > 0) {
      appendHexWithZeroPadding(s, keys[0]);
    }
    for (int i = 1; i < keys.length; ++i) {
      s.append(", ");
      if (i % 8 == 0) {
        s.append(SYSTEM_LINE_SEPARATOR).append("    ");
      }
      appendHexWithZeroPadding(s, keys[i]);
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    s.append("int[] values = new int[]{");
    if (values.length > 0) {
      appendHexWithZeroPadding(s, values[0]);
    }
    for (int i = 1; i < values.length; ++i) {
      s.append(", ");
      if (i % 8 == 0) {
        s.append(SYSTEM_LINE_SEPARATOR).append("    ");
      }
      appendHexWithZeroPadding(s, values[i]);
      s.append(", ");
    }
    s.append("};").append(SYSTEM_LINE_SEPARATOR);
    return s.toString();
  }
}
