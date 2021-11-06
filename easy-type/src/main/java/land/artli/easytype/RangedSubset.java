package land.artli.easytype;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;

class RangedSubset implements Subset {

  private static final char[] EMPTY_CHAR_ARRAY = new char[0];
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final long[] EMPTY_LONG_ARRAY = new long[0];

  protected final String name;
  protected final String alias;
  protected final char[] singleByteCodePointRanges;
  protected final int[] doubleByteCodePointRanges;
  protected final long[] tripleByteCodePointRanges;
  protected final int size;

  protected RangedSubset(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this("", "", singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
  }

  private RangedSubset(
      final String name,
      final String alias,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this.name = name;
    this.alias = alias;
    this.singleByteCodePointRanges = singleByteCodePointRanges == null ? EMPTY_CHAR_ARRAY : singleByteCodePointRanges;
    this.doubleByteCodePointRanges = doubleByteCodePointRanges == null ? EMPTY_INT_ARRAY : doubleByteCodePointRanges;
    this.tripleByteCodePointRanges = tripleByteCodePointRanges == null ? EMPTY_LONG_ARRAY : tripleByteCodePointRanges;
    this.size = this.singleByteCodePointRanges.length
        + this.doubleByteCodePointRanges.length
        + this.tripleByteCodePointRanges.length;
  }

  public static RangedSubsetBuilder builder() {
    return new RangedSubsetBuilder();
  }

  int size() {
    return size;
  }

  boolean isEmpty() {
    return size() == 0;
  }

  boolean isNotEmpty() {
    return !isEmpty();
  }

  /**
   * Returns an array of 2-byte code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to value. The
   * most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high.
   */
  char[] getSingleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
  }

  /**
   * Returns an array of 2-byte code-point ranges stored as integer values. Each range comprises an inclusive-from value and an inclusive-to value.
   * The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high as unsigned-integers.
   */
  int[] getDoubleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
  }

  /**
   * Returns an array of 3-byte code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to value. The
   * most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array must be
   * sorted from low-to-high.
   */
  long[] getTripleByteCodePointRanges() {
    // Protect out data by returning a copy
    return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
  }

  public String getName() {
    return name;
  }

  public String getAlias() {
    return alias;
  }

  public boolean isInSubset(final int codePoint) {
    if (size == 0) {
      return false;
    } else if (codePoint < 0x100) {
      if (singleByteCodePointRanges.length == 0 ||
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
      if (doubleByteCodePointRanges.length == 0 ||
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
      if (tripleByteCodePointRanges.length == 0 ||
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

  @Override
  public String toString() {
    final StringBuilder s = new StringBuilder();
    s.append(name).append('[');
    for (char singleByteCodePointRange : singleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(singleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(singleByteCodePointRange), 16)).append(',');
    }
    for (int doubleByteCodePointRange : doubleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(doubleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(doubleByteCodePointRange), 16)).append(',');
    }
    for (long tripleByteCodePointRange : tripleByteCodePointRanges) {
      s.append(Integer.toString(getInclusiveFrom(tripleByteCodePointRange), 16)).append('.').append('.')
          .append(Integer.toString(getInclusiveTo(tripleByteCodePointRange), 16)).append(',');
    }
    s.setLength(s.length() - 1); // remove final comma
    s.append(']');
    return s.toString();
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

  static class RangedSubsetBuilder {

    private char[] singleByteCodePointRanges = new char[16];
    private int[] doubleByteCodePointRanges = new int[16];
    private long[] tripleByteCodePointRanges = new long[16];
    private int singleByteEndIndex = 0;
    private int doubleByteEndIndex = 0;
    private int tripleByteEndIndex = 0;

    void addChar(final char ch) {
      addCharRange(ch, ch);
    }

    void addChars(final char... chars) {
      if (chars != null) {
        ensureDoubleByteCapacity(chars.length);
        for (int i = 0; i < chars.length; ++i) {
          addChar(chars[i]);
        }
      }
    }

    void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
      addCodePointRange(inclusiveFrom & 0x0000_ffff, inclusiveTo & 0x0000_ffff);
    }

    void addCodePoint(final int codePoint) {
      addCodePointRange(codePoint, codePoint);
    }

    void addCodePoints(final int... codePoints) {
      if (codePoints != null) {
        ensureDoubleByteCapacity(codePoints.length);
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addRangedSubset(final RangedSubset subset) {
      if (subset != null && subset.isNotEmpty()) {
        final char[] singleByteRanges = subset.getSingleByteCodePointRanges();
        ensureSingleByteCapacity(singleByteRanges.length);
        for (char c : singleByteRanges) {
          addCodePointRange(
              RangedSubset.getInclusiveFrom(c),
              RangedSubset.getInclusiveTo(c));
        }
        final int[] doubleByteRanges = subset.getDoubleByteCodePointRanges();
        ensureDoubleByteCapacity(doubleByteRanges.length);
        for (int j : doubleByteRanges) {
          addCodePointRange(
              RangedSubset.getInclusiveFrom(j),
              RangedSubset.getInclusiveTo(j));
        }
        final long[] tripleByteRanges = subset.getTripleByteCodePointRanges();
        ensureTripleByteCapacity(tripleByteRanges.length);
        for (long l : tripleByteRanges) {
          addCodePointRange(
              RangedSubset.getInclusiveFrom(l),
              RangedSubset.getInclusiveTo(l));
        }
      }
    }

    void addCodePointRange(int inclusiveFrom, int inclusiveTo) {
      if (inclusiveTo > 0xffff) {
        if (inclusiveFrom > 0xffff) {
          ensureTripleByteCapacity();
          tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(inclusiveFrom, inclusiveTo);
          ++tripleByteEndIndex;
          return;
        } else {
          ensureTripleByteCapacity();
          tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(0x10000, inclusiveTo);
          ++tripleByteEndIndex;
          inclusiveTo = 0xffff;
        }
      }

      if (inclusiveTo > 0xff) {
        if (inclusiveFrom > 0xff) {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(inclusiveFrom, inclusiveTo);
          ++doubleByteEndIndex;
          return;
        } else {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(0x100, inclusiveTo);
          ++doubleByteEndIndex;
          inclusiveTo = 0xff;
        }
      }

      ensureSingleByteCapacity();
      singleByteCodePointRanges[singleByteEndIndex] = RangedSubset.rangeToChar(inclusiveFrom, inclusiveTo);
      ++singleByteEndIndex;
    }

    private void removeSingleByteElement(final int index) {
      System.arraycopy(singleByteCodePointRanges, index + 1, singleByteCodePointRanges, index, singleByteCodePointRanges.length - index - 1);
      --singleByteEndIndex;
    }

    private void removeDoubleByteElement(final int index) {
      System.arraycopy(doubleByteCodePointRanges, index + 1, doubleByteCodePointRanges, index, doubleByteCodePointRanges.length - index - 1);
      --doubleByteEndIndex;
    }

    private void removeTripleByteElement(final int index) {
      System.arraycopy(tripleByteCodePointRanges, index + 1, tripleByteCodePointRanges, index, tripleByteCodePointRanges.length - index - 1);
      --tripleByteEndIndex;
    }

    private void ensureSingleByteCapacity() {
      if (singleByteEndIndex == singleByteCodePointRanges.length) {
        ensureSingleByteCapacity(16);
      }
    }

    private void ensureSingleByteCapacity(final int amount) {
      if ((singleByteCodePointRanges.length - singleByteEndIndex) < amount) {
        singleByteCodePointRanges = Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length + amount);
      }
    }

    private void ensureDoubleByteCapacity() {
      if (doubleByteEndIndex == doubleByteCodePointRanges.length) {
        ensureDoubleByteCapacity(16);
      }
    }

    private void ensureDoubleByteCapacity(final int amount) {
      if ((doubleByteCodePointRanges.length - doubleByteEndIndex) < amount) {
        doubleByteCodePointRanges = Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length + amount);
      }
    }

    private void ensureTripleByteCapacity() {
      if (tripleByteEndIndex == tripleByteCodePointRanges.length) {
        ensureTripleByteCapacity(16);
      }
    }

    private void ensureTripleByteCapacity(final int amount) {
      if ((tripleByteCodePointRanges.length - tripleByteEndIndex) < amount) {
        tripleByteCodePointRanges = Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length + amount);
      }
    }

    private static void unsignedIntegerBubbleSort(final int[] values, int fromIndex, int toIndex) {
      int temp;
      for (int i = fromIndex; i < toIndex; i++) {
        for (int j = fromIndex; j < toIndex - i - 1; j++) {
          if ((0x00000000_ffffffffL & values[j]) > (0x00000000_ffffffffL & values[j + 1])) {
            // swap the elements
            temp = values[j];
            values[j] = values[j + 1];
            values[j + 1] = temp;
          }
        }
      }
    }

    RangedSubset build() {
      {
        Arrays.sort(singleByteCodePointRanges, 0, singleByteEndIndex);
        int previousInclusiveFrom;
        int previousInclusiveTo;
        int currentInclusiveFrom;
        int currentInclusiveTo;
        int i = 0;
        int j = 1;
        while (j < singleByteEndIndex) {
          previousInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[i]);
          previousInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[i]);
          currentInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[j]);
          currentInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[j]);
          if (previousInclusiveTo >= currentInclusiveFrom) {
            if (previousInclusiveTo <= currentInclusiveTo) {
              singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
            }
            removeSingleByteElement(j);
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
            removeSingleByteElement(j);
          } else {
            ++i;
            ++j;
          }
        }
      }
      {
        unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, doubleByteEndIndex);
        int previousInclusiveFrom;
        int previousInclusiveTo;
        int currentInclusiveFrom;
        int currentInclusiveTo;
        int i = 0;
        int j = 1;
        while (j < doubleByteEndIndex) {
          previousInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[i]);
          previousInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[i]);
          currentInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[j]);
          currentInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[j]);
          if (previousInclusiveTo >= currentInclusiveFrom) {
            if (previousInclusiveTo <= currentInclusiveTo) {
              doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
            }
            removeDoubleByteElement(j);
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
            removeDoubleByteElement(j);
          } else {
            ++i;
            ++j;
          }
        }
      }
      {
        Arrays.sort(tripleByteCodePointRanges, 0, tripleByteEndIndex);
        int previousInclusiveFrom;
        int previousInclusiveTo;
        int currentInclusiveFrom;
        int currentInclusiveTo;
        int i = 0;
        int j = 1;
        while (j < tripleByteEndIndex) {
          previousInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[i]);
          previousInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[i]);
          currentInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[j]);
          currentInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[j]);
          if (previousInclusiveTo >= currentInclusiveFrom) {
            if (previousInclusiveTo <= currentInclusiveTo) {
              tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
            }
            removeTripleByteElement(j);
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
            removeTripleByteElement(j);
          } else {
            ++i;
            ++j;
          }
        }
      }
      return new RangedSubset(
          Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex),
          Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex),
          Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex));
    }
  }
}
