package org.typefactory.impl;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.typefactory.Subset.CodePointRange;

final class SubsetUtils {

  private SubsetUtils() {
  }

  /**
   * <p>Extracts the 8-bit 'inclusive-from' value from a code-point range comprised of two 8-bit values stored in a {@code char} primitive. The
   * 'inclusive-from' is stored in the 8 most-significant bits of the {@code char} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToChar(int, int)} to create a code-point range.</p>
   *
   * @param codePointRange a code-point range specifying two 8-bit values representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from'
   *                       occupies the 8 most-significant bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @return the 8-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToChar(int, int)
   */
  static int getInclusiveFrom(final char codePointRange) {
    return codePointRange >>> 8;
  }

  /**
   * <p>Extracts the 8-bit 'inclusive-to' value from a code-point range comprised of two 8-bit values stored in a {@code char} primitive. The
   * 'inclusive-to' is stored in the 8 least-significant bits of the {@code char} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToChar(int, int)} to create a code-point range.</p>
   *
   * @param codePointRange a code-point range specifying two 8-bit values representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from'
   *                       occupies the 8 most-significant bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @return the 8-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToChar(int, int)
   */
  static int getInclusiveTo(final char codePointRange) {
    return codePointRange & 0x00_ff;
  }

  /**
   * <p>Extracts the 16-bit 'inclusive-from' value from a code-point range comprised of two 16-bit values stored in an {@code int} primitive. The
   * 'inclusive-from' is stored in the 16 most-significant bits of the {@code int} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToInt(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since an {@code int} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code int} primitive as a house for data. We are never using the {@code int} directly, we are only ever using the two smaller values
   * stored within it.</p>
   *
   * @param codePointRange a code-point range specifying two 16-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 16 most-significant bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @return the 16-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToInt(int, int)
   */
  static int getInclusiveFrom(final int codePointRange) {
    return codePointRange >>> 16;
  }

  /**
   * <p>Extracts the 16-bit 'inclusive-to' value from a code-point range comprised of two 16-bit values stored in a {@code int} primitive. The
   * 'inclusive-to' is stored in the 16 least-significant bits of the {@code int} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToInt(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since an {@code int} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code int} primitive as a house for data. We are never using the {@code int} directly, we are only ever using the two smaller values
   * stored within it.</p>
   *
   * @param codePointRange a code-point range specifying two 16-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 16 most-significant bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @return the 16-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToInt(int, int)
   */
  static int getInclusiveTo(final int codePointRange) {
    return codePointRange & 0x0000_ffff;
  }

  /**
   * <p>Extracts the 24-bit 'inclusive-from' value from a code-point range comprised of two 24-bit values stored in an {@code long} primitive. The
   * 'inclusive-from' is stored in the 32 most-significant bits of the {@code long} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToLong(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since a {@code long} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code long} primitive as a house for data. Our range values are only 24 bits and not 32 bits, so we will never have to worry about the
   * sign bit - it will always be zero.</p>
   *
   * @param codePointRange a code-point range specifying two 24-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 32 most-significant bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return the 24-bit 'inclusive-from' code-point value as an {@code int} primitive.
   * @see #rangeToLong(int, int)
   */
  static int getInclusiveFrom(final long codePointRange) {
    return (int) (codePointRange >>> 32);
  }

  /**
   * <p>Extracts the 24-bit 'inclusive-to' value from a code-point range comprised of two 24-bit values stored in a {@code long} primitive. The
   * 'inclusive-to' is stored in the 32 least-significant bits of the {@code long} primitive.</p>
   *
   * <p>The complement to this method is the {@link #rangeToLong(int, int)} to create a code-point range.</p>
   *
   * <p>Note: you may may be wondering if this is possible since a {@code long} primitive is a 'signed' value in Java. Let go of that and think
   * of the {@code long} primitive as a house for data. Our range values are only 24 bits and not 32 bits, so we will never have to worry about the
   * sign bit - it will always be zero.</p>
   *
   * @param codePointRange a code-point range specifying two 24-bit values representing an 'inclusive-from' and an 'inclusive-to'. The
   *                       'inclusive-from' occupies the 32 most-significant bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return the 24-bit 'inclusive-to' code-point value as an {@code int} primitive.
   * @see #rangeToLong(int, int)
   */
  static int getInclusiveTo(final long codePointRange) {
    return (int) (codePointRange & 0x00000000_ffffffffL);
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 16-bit {@code char} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(char)} and {@link #getInclusiveTo(char)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 16-bit {@code char} value.
   */
  static char rangeToChar(final int inclusiveFrom, final int inclusiveTo) {
    return (char) ((min(inclusiveFrom, inclusiveTo) << 8) | (max(inclusiveFrom, inclusiveTo) & 0x00_ff));
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 32-bit {@code int} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(int)} and {@link #getInclusiveTo(int)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 32-bit {@code int} value.
   */
  static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
    return (min(inclusiveFrom, inclusiveTo) << 16) | (max(inclusiveFrom, inclusiveTo) & 0x0000_ffff);
  }

  /**
   * <p>Converts an inclusive-from to inclusive-to code-point range to a 64-bit {@code long} value.
   * It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed.</p>
   *
   * <p>The complement to this method are the {@link #getInclusiveFrom(long)} and {@link #getInclusiveTo(long)} methods
   * to extract the individual values from from the range.</p>
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the inclusive-from to inclusive-to code-point range as a 64-bit {@code int} value.
   */
  static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
    return ((long) min(inclusiveFrom, inclusiveTo) << 32) | max(inclusiveFrom, inclusiveTo);
  }

  static char[] defaultIfNullOrEmpty(final char[] array, final char[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  static int[] defaultIfNullOrEmpty(final int[] array, final int[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  static long[] defaultIfNullOrEmpty(final long[] array, final long[] defaultArray) {
    return array == null || array.length == 0 ? defaultArray : array;
  }

  /**
   * Returns a collection of {@link CodePointRange} objects represent code-point ranges from the raw range data stored in the three specified arrays
   * of primitives.
   *
   * @param singleByteCodePointRanges An array of {@code char} primitives with each value containing a code-point range specifying two 8-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 8 most-significant
   *                                  bits and the 'inclusive-to' occupies the 8 least significant bits.
   * @param doubleByteCodePointRanges An array of {@code int} primitives with each value containing a code-point range specifying two 16-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 16 most-significant
   *                                  bits and the 'inclusive-to' occupies the 16 least significant bits.
   * @param tripleByteCodePointRanges An array of {@code long} primitives with each value containing a code-point range specifying two 24-bit values
   *                                  representing an 'inclusive-from' and an 'inclusive-to'. The 'inclusive-from' occupies the 32 most-significant
   *                                  bits and the 'inclusive-to' occupies the 32 least significant bits.
   * @return a collection of {@link CodePointRange} objects representing code-point ranges.
   */
  static Collection<CodePointRange> aggregateCodePointRangeData(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {

    final ArrayList<CodePointRange> result = new ArrayList<>();
    if (singleByteCodePointRanges != null) {
      for (char range : singleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    if (doubleByteCodePointRanges != null) {
      for (int range : doubleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    if (tripleByteCodePointRanges != null) {
      for (long range : tripleByteCodePointRanges) {
        result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
      }
    }
    return result;
  }

  static boolean contains(
      final int codePoint,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {

    if (codePoint < 0x100) {
      if (singleByteCodePointRanges == null ||
          singleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(singleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(singleByteCodePointRanges[singleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = singleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final int inclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[mid]);
        final int inclusiveTo = getInclusiveTo(singleByteCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    } else if (codePoint < 0x10000) {
      if (doubleByteCodePointRanges == null ||
          doubleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(doubleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = doubleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final int inclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[mid]);
        final int inclusiveTo = getInclusiveTo(doubleByteCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    } else {
      if (tripleByteCodePointRanges == null ||
          tripleByteCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(tripleByteCodePointRanges[0]) ||
          codePoint > getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
        return false; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = tripleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1; // divide by 2
        final long midVal = tripleByteCodePointRanges[mid];
        final int inclusiveFrom = getInclusiveFrom(midVal);
        final int inclusiveTo = getInclusiveTo(midVal);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else {
          return true; // found
        }
      }
    }
    return false; // not found
  }

  static void unsignedIntegerBubbleSort(final int[] values, int fromIndex, int toIndex) {
    int temp;
    for (int i = fromIndex; i < toIndex; i++) {
      for (int j = fromIndex; j < toIndex - i - 1; j++) {
        if ((0x00000000_FFFFFFFFL & values[j]) > (0x00000000_FFFFFFFFL & values[j + 1])) {
          // swap the elements
          temp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = temp;
        }
      }
    }
  }

  static int compactSingleByteCodePointRanges(final char[] singleByteCodePointRanges, final int length) {

    Arrays.sort(singleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(singleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(singleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeSingleByteElement(singleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeSingleByteElement(singleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int compactDoubleByteCodePointRanges(final int[] doubleByteCodePointRanges, final int length) {

    unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeDoubleByteElement(doubleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeDoubleByteElement(doubleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int compactTripleByteCodePointRanges(final long[] tripleByteCodePointRanges, final int length) {

    Arrays.sort(tripleByteCodePointRanges, 0, length);
    int resultLength = length;
    int previousInclusiveFrom;
    int previousInclusiveTo;
    int currentInclusiveFrom;
    int currentInclusiveTo;
    int i = 0;
    int j = 1;
    while (j < resultLength) {
      previousInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[i]);
      previousInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[i]);
      currentInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[j]);
      currentInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[j]);
      if (previousInclusiveTo >= currentInclusiveFrom) {
        if (previousInclusiveTo <= currentInclusiveTo) {
          tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
        }
        resultLength = removeTripleByteElement(tripleByteCodePointRanges, resultLength, j);
      } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
        tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
        resultLength = removeTripleByteElement(tripleByteCodePointRanges, resultLength, j);
      } else {
        ++i;
        ++j;
      }
    }
    return resultLength;
  }

  static int removeSingleByteElement(final char[] singleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        singleByteCodePointRanges,
        indexOfElementToRemove + 1,
        singleByteCodePointRanges,
        indexOfElementToRemove,
        singleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  static int removeDoubleByteElement(final int[] doubleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        doubleByteCodePointRanges,
        indexOfElementToRemove + 1,
        doubleByteCodePointRanges,
        indexOfElementToRemove,
        doubleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  static int removeTripleByteElement(final long[] tripleByteCodePointRanges, final int length, final int indexOfElementToRemove) {
    System.arraycopy(
        tripleByteCodePointRanges,
        indexOfElementToRemove + 1,
        tripleByteCodePointRanges,
        indexOfElementToRemove,
        tripleByteCodePointRanges.length - indexOfElementToRemove - 1);
    return length - 1;
  }

  /**
   * Count how many categories have been specified using category bit flags.
   *
   * @param categoryFlags
   * @return the number categories that have been specified using category bit flags.
   */
  static int numberOfUnicodeCategoriesFromCategoriesFlags(final long categoryFlags) {
    int numberOfUnicodeCategories = 0;
    // Count how many categories have been specified.
    long flags = categoryFlags;
    for (int i = 0; i < 64; ++i) {
      if ((flags & 0x01) == 0x01) {
        ++numberOfUnicodeCategories;
      }
      flags = flags >> 1;
    }
    return numberOfUnicodeCategories;
  }
}
