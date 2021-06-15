package land.artli.easytype.generator;

import java.util.Arrays;
import land.artli.easytype.generator.Unicode.RangedSubset;

public class CodePointRanges implements RangedSubset {
  private final char [] singleByteCodePointRanges;
  private final int [] doubleByteCodePointRanges;
  private final long [] tripleByteCodePointRanges;

  private CodePointRanges(
      final char [] singleByteCodePointRanges,
      final int [] doubleByteCodePointRanges,
      final long [] tripleByteCodePointRanges) {
    this.singleByteCodePointRanges = singleByteCodePointRanges;
    this.doubleByteCodePointRanges = doubleByteCodePointRanges;
    this.tripleByteCodePointRanges = tripleByteCodePointRanges;
  }

  static CodePointRangesBuilder builder() {
    return new CodePointRangesBuilder();
  }

  public char[] getSingleByteCodePointRanges() {
    return singleByteCodePointRanges;
  }

  public int[] getDoubleByteCodePointRanges() {
    return doubleByteCodePointRanges;
  }

  public long[] getTripleByteCodePointRanges() {
    return tripleByteCodePointRanges;
  }

  boolean isAcceptedCodePoint(final int codePoint) {
    return getCodePointRange(codePoint) != Long.MIN_VALUE;
  }

  boolean isNotAcceptedCodePoint(final int codePoint) {
    return !isAcceptedCodePoint(codePoint);
  }

  private long getCodePointRange(final int codePoint) {
    if (codePoint > 0xffff) {
      if (tripleByteCodePointRanges.length == 0 ||
          codePoint < RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[0]) ||
          codePoint > RangedSubset.getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
        return Long.MIN_VALUE; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = tripleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1;
        final long midVal = tripleByteCodePointRanges[mid];
        final int inclusiveFrom = RangedSubset.getInclusiveFrom(midVal);
        final int inclusiveTo = RangedSubset.getInclusiveTo(midVal);
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
      if (doubleByteCodePointRanges.length == 0 ||
          codePoint < RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[0]) ||
          codePoint > RangedSubset.getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
        return Long.MIN_VALUE; // not found, outside of range
      }
      // Try to find using a binary search
      int low = 0;
      int high = doubleByteCodePointRanges.length - 1;
      while (low <= high) {
        final int mid = (low + high) >>> 1;
        final int inclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[mid]);
        final int inclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[mid]);
        if (codePoint > inclusiveTo) {
          low = mid + 1;
        } else if (codePoint < inclusiveFrom) {
          high = mid - 1;
        } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
          return RangedSubset.rangeToLong(inclusiveFrom, inclusiveTo); // found
        } else {
          return Long.MIN_VALUE; // not found
        }
      }
    }
    return Long.MIN_VALUE; // not found
  }


  static class CodePointRangesBuilder {

    private char [] singleByteCodePointRanges = new char[16];
    private int [] doubleByteCodePointRanges = new int[16];
    private long [] tripleByteCodePointRanges = new long[4];
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

    void addCodePoints(final int ... codePoints) {
      if (codePoints != null) {
        ensureDoubleByteCapacity(codePoints.length);
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRanges(final char [] codePointRanges) {
      if (codePointRanges != null) {
        ensureSingleByteCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
        }
      }
    }

    void addCodePointRanges(final int [] codePointRanges) {
      if (codePointRanges != null) {
        ensureDoubleByteCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
        }
      }
    }

    void addCodePointRanges(final long [] codePointRanges) {
      if (codePointRanges != null) {
        ensureTripleByteCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(RangedSubset.getInclusiveFrom(codePointRanges[i]), RangedSubset.getInclusiveTo(codePointRanges[i]));
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
          previousInclusiveFrom =RangedSubset. getInclusiveFrom(doubleByteCodePointRanges[i]);
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
      return new CodePointRanges(
          Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex),
          Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex),
          Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex));
    }
  }
}
