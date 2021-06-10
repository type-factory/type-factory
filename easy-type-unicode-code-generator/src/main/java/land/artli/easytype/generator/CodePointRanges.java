package land.artli.easytype.generator;

import java.util.Arrays;

public class CodePointRanges {
  private final int [] lowerCodePointRanges;
  private final long [] upperCodePointRanges;

  private CodePointRanges(
      final int [] lowerCodePointRanges,
      final long [] upperCodePointRanges) {
    this.lowerCodePointRanges = lowerCodePointRanges;
    this.upperCodePointRanges = upperCodePointRanges;
  }

  static CodePointRangesBuilder builder() {
    return new CodePointRangesBuilder();
  }

  public int[] getLowerCodePointRanges() {
    return lowerCodePointRanges;
  }

  public long[] getUpperCodePointRanges() {
    return upperCodePointRanges;
  }

  boolean isAcceptedCodePoint(final int codePoint) {
    return getCodePointRange(codePoint) != Long.MIN_VALUE;
  }

  boolean isNotAcceptedCodePoint(final int codePoint) {
    return !isAcceptedCodePoint(codePoint);
  }

  private long getCodePointRange(final int codePoint) {
    if (codePoint > 0xffff) {
      if (upperCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(upperCodePointRanges[0]) ||
          codePoint > getInclusiveTo(upperCodePointRanges[upperCodePointRanges.length - 1])) {
        return Long.MIN_VALUE; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = upperCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1;
        final long midVal = upperCodePointRanges[mid];
        final int inclusiveFrom = getInclusiveFrom(midVal);
        final int inclusiveTo = getInclusiveTo(midVal);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
          return midVal; // found
        } else {
          return Long.MIN_VALUE; // not found
        }
      }
    } else {
      if (lowerCodePointRanges.length == 0 ||
          codePoint < getInclusiveFrom(lowerCodePointRanges[0]) ||
          codePoint > getInclusiveTo(lowerCodePointRanges[lowerCodePointRanges.length - 1])) {
        return Long.MIN_VALUE; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = lowerCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1;
        final int inclusiveFrom = getInclusiveFrom(lowerCodePointRanges[mid]);
        final int inclusiveTo = getInclusiveTo(lowerCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
          return rangeToLong(inclusiveFrom, inclusiveTo); // found
        } else {
          return Long.MIN_VALUE; // not found
        }
      }
    }
    return Long.MIN_VALUE; // not found
  }

  public static int getInclusiveFrom(final int codePointRange) {
    return codePointRange >>> 16;
  }

  public static int getInclusiveTo(final int codePointRange) {
    return codePointRange & 0x0000ffff;
  }

  public static int getInclusiveFrom(final long codePointRange) {
    return (int)(codePointRange >>> 32);
  }

  public static int getInclusiveTo(final long codePointRange) {
    return (int)(codePointRange & 0x00000000_ffffffffL);
  }

  private static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
    return (inclusiveFrom << 16) | (inclusiveTo & 0x0000ffff);
  }

  private static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
    return (((long)inclusiveFrom) << 32) | inclusiveTo;
  }

  static class CodePointRangesBuilder {

    private int [] lowerCodePointRanges = new int[16];
    private long [] upperCodePointRanges = new long[4];
    private int lowerEndIndex = 0;
    private int upperEndIndex = 0;

    void addChar(final char ch) {
      addCharRange(ch, ch);
    }

    void addChars(final char... chars) {
      if (chars != null) {
        ensureLowerCapacity(chars.length);
        for (int i = 0; i < chars.length; ++i) {
          addChar(chars[i]);
        }
      }
    }

    void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
      ensureLowerCapacity();
      lowerCodePointRanges[lowerEndIndex] = rangeToInt(inclusiveFrom, inclusiveTo);
      ++lowerEndIndex;
    }

    void addCodePoint(final int codePoint) {
      addCodePointRange(codePoint, codePoint);
    }

    void addCodePoints(final int ... codePoints) {
      if (codePoints != null) {
        ensureLowerCapacity(codePoints.length);
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRanges(final int [] codePointRanges) {
      if (codePointRanges != null) {
        ensureLowerCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(getInclusiveFrom(codePointRanges[i]), getInclusiveTo(codePointRanges[i]));
        }
      }
    }

    void addCodePointRanges(final long [] codePointRanges) {
      if (codePointRanges != null) {
        ensureUpperCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(getInclusiveFrom(codePointRanges[i]), getInclusiveTo(codePointRanges[i]));
        }
      }
    }
    void addCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      if ( inclusiveFrom < 0xffff) {
        ensureLowerCapacity();
        if (inclusiveTo < 0xffff) {
          lowerCodePointRanges[lowerEndIndex] = rangeToInt(inclusiveFrom, inclusiveTo);
          ++lowerEndIndex;
        } else {
          lowerCodePointRanges[lowerEndIndex] = rangeToInt(inclusiveFrom, 0xffff);
          ++lowerEndIndex;
          ensureUpperCapacity();
          upperCodePointRanges[upperEndIndex] = rangeToLong(0x10000, inclusiveTo);
          ++upperEndIndex;
        }
      } else {
        ensureUpperCapacity();
        upperCodePointRanges[upperEndIndex] = rangeToLong(inclusiveFrom, inclusiveTo);
        ++upperEndIndex;
      }
    }

    private void removeLowerElement(final int index) {
      System.arraycopy(lowerCodePointRanges, index + 1, lowerCodePointRanges, index, lowerCodePointRanges.length - index - 1);
      --lowerEndIndex;
    }

    private void removeUpperElement(final int index) {
      System.arraycopy(upperCodePointRanges, index + 1, upperCodePointRanges, index, upperCodePointRanges.length - index - 1);
      --upperEndIndex;
    }

    private void ensureLowerCapacity() {
      if (lowerEndIndex == lowerCodePointRanges.length) {
        ensureLowerCapacity(16);
      }
    }

    private void ensureUpperCapacity() {
      if (upperEndIndex == upperCodePointRanges.length) {
        ensureUpperCapacity(16);
      }
    }

    private void ensureLowerCapacity(final int amount) {
      if ((lowerCodePointRanges.length - lowerEndIndex) < amount) {
        lowerCodePointRanges = Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length + amount);
      }
    }

    private void ensureUpperCapacity(final int amount) {
      if ((upperCodePointRanges.length - upperEndIndex) < amount) {
        upperCodePointRanges = Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length + amount);
      }
    }

    private static void unsignedIntegerBubbleSort(final int [] values, int fromIndex, int toIndex) {
      int temp;
      for (int i = fromIndex; i < toIndex; i++) {
        for (int j = fromIndex; j < toIndex - i - 1; j++) {
          if ((0x00000000_ffffffffL & (long)values[j]) > (0x00000000_ffffffffL & (long)values[j + 1])) {
            // swap the elements
            temp = values[j];
            values[j] = values[j + 1];
            values[j + 1] = temp;
          }
        }
      }
    }

    CodePointRanges build() {
      {
        unsignedIntegerBubbleSort(lowerCodePointRanges, 0, lowerEndIndex);
        int previousInclusiveFrom;
        int previousInclusiveTo;
        int currentInclusiveFrom;
        int currentInclusiveTo;
        int i = 0;
        int j = 1;
        while (j < lowerEndIndex) {
          previousInclusiveFrom = getInclusiveFrom(lowerCodePointRanges[i]);
          previousInclusiveTo = getInclusiveTo(lowerCodePointRanges[i]);
          currentInclusiveFrom = getInclusiveFrom(lowerCodePointRanges[j]);
          currentInclusiveTo = getInclusiveTo(lowerCodePointRanges[j]);
          if (previousInclusiveTo >= currentInclusiveFrom) {
            if (previousInclusiveTo <= currentInclusiveTo) {
              lowerCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
            }
            removeLowerElement(j);
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            lowerCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
            removeLowerElement(j);
          } else {
            ++i;
            ++j;
          }
        }
      }
      {
        Arrays.sort(upperCodePointRanges, 0, upperEndIndex);
        int previousInclusiveFrom;
        int previousInclusiveTo;
        int currentInclusiveFrom;
        int currentInclusiveTo;
        int i = 0;
        int j = 1;
        while (j < upperEndIndex) {
          previousInclusiveFrom = getInclusiveFrom(upperCodePointRanges[i]);
          previousInclusiveTo = getInclusiveTo(upperCodePointRanges[i]);
          currentInclusiveFrom = getInclusiveFrom(upperCodePointRanges[j]);
          currentInclusiveTo = getInclusiveTo(upperCodePointRanges[j]);
          if (previousInclusiveTo >= currentInclusiveFrom) {
            if (previousInclusiveTo <= currentInclusiveTo) {
              upperCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
            }
            removeUpperElement(j);
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            upperCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
            removeUpperElement(j);
          } else {
            ++i;
            ++j;
          }
        }
      }
      return new CodePointRanges(
          Arrays.copyOf(lowerCodePointRanges, lowerEndIndex),
          Arrays.copyOf(upperCodePointRanges, upperEndIndex));
    }
  }
}
