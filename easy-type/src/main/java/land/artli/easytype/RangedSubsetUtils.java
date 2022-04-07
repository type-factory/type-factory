package land.artli.easytype;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Collection;
import land.artli.easytype.Subset.CodePointRange;

class RangedSubsetUtils {

  private RangedSubsetUtils() {
  }

  static final char[] EMPTY_CHAR_ARRAY = new char[0];
  static final int[] EMPTY_INT_ARRAY = new int[0];
  static final long[] EMPTY_LONG_ARRAY = new long[0];

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

  static int getInclusiveFrom(final char codePointRange) {
    return codePointRange >>> 8;
  }

  static int getInclusiveTo(final char codePointRange) {
    return codePointRange & 0x00_ff;
  }

  static int getInclusiveFrom(final int codePointRange) {
    return codePointRange >>> 16;
  }

  static int getInclusiveTo(final int codePointRange) {
    return codePointRange & 0x0000_ffff;
  }

  static int getInclusiveFrom(final long codePointRange) {
    return (int) (codePointRange >>> 32);
  }

  static int getInclusiveTo(final long codePointRange) {
    return (int) (codePointRange & 0x00000000_ffffffffL);
  }

  static Collection<CodePointRange> ranges(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {

    final ArrayList<CodePointRange> result = new ArrayList<>();
    for (char range : singleByteCodePointRanges) {
      result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
    }
    for (int range : doubleByteCodePointRanges) {
      result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
    }
    for (long range : tripleByteCodePointRanges) {
      result.add(new CodePointRange(getInclusiveFrom(range), getInclusiveTo(range)));
    }
    return result;
  }

  /**
   * Converts to to-from range to a 16-bit (char) value. It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo}
   * values have accidentally been reversed.
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the to-from as a 16-bit value.
   */
  static char rangeToChar(final int inclusiveFrom, final int inclusiveTo) {
    return (char) ((min(inclusiveFrom, inclusiveTo) << 8) | (max(inclusiveFrom, inclusiveTo) & 0x00_ff));
  }

  /**
   * Converts to to-from range to a 32-bit (char) value. It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo}
   * values have accidentally been reversed.
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the to-from as a 32-bit value.
   */
  static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
    return (min(inclusiveFrom, inclusiveTo) << 16) | (max(inclusiveFrom, inclusiveTo) & 0x0000_ffff);
  }

  /**
   * Converts to to-from range to a 64-bit (char) value. It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo}
   * values have accidentally been reversed.
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return the to-from as a 64-bit value.
   */
  static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
    return (((long) min(inclusiveFrom, inclusiveTo) << 32)) | max(inclusiveFrom, inclusiveTo);
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

}
