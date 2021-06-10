package land.artli.easytype.generator;

import java.util.Arrays;

public class CodePointRanges {

  private final CodePointRange[] codePointRanges;

  private CodePointRanges(final CodePointRange[] codePointRanges) {
    this.codePointRanges = codePointRanges;
  }

  static CodePointRangesBuilder builder() {
    return new CodePointRangesBuilder();
  }

  public CodePointRange[] getCodePointRanges() {
    return Arrays.copyOf(codePointRanges, codePointRanges.length);
  }

  static class CodePointRangesBuilder {

    private CodePointRange[] codePointRanges = new CodePointRange[16];
    private int endIndex = 0;

    void addCodePoint(final int codePoint) {
      addCodePointRange(codePoint, codePoint);
    }

    void addCodePoints(final int ... codePoints) {
      if (codePoints != null) {
        ensureCapacity(codePoints.length);
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRange(final long [] codePointRanges) {
      if (codePointRanges != null) {
        ensureCapacity(codePointRanges.length);
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(codePointRanges[i]);
        }
      }
    }

    void addCodePointRange(final long codePointRange) {
      addCodePointRange((int)(codePointRange >>> 32), (int)codePointRange);
    }

    void addCodePointRanges(final CodePointRange ... codePointRanges) {
      if (codePointRanges != null) {
        for (int i = 0; i < codePointRanges.length; ++i) {
          addCodePointRange(codePointRanges[i].inclusiveFrom, codePointRanges[i].inclusiveTo);
        }
      }
    }

    void addCodePointRange(final CodePointRange codePointRange) {
      addCodePointRange(codePointRange.inclusiveFrom, codePointRange.inclusiveTo);
    }

    void addCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
      ensureCapacity();
      codePointRanges[endIndex] = new CodePointRange(inclusiveFrom, inclusiveTo);
      ++endIndex;
    }

    private void removeElement(final int index) {
      System.arraycopy(codePointRanges, index + 1, codePointRanges, index, codePointRanges.length - index - 1);
      --endIndex;
    }

    private void ensureCapacity() {
      if (endIndex == codePointRanges.length) {
        ensureCapacity(16);
      }
    }

    private void ensureCapacity(final int amount) {
      if ((codePointRanges.length - endIndex) < amount) {
        codePointRanges = Arrays.copyOf(codePointRanges, codePointRanges.length + amount);
      }
    }

    CodePointRanges build() {
      Arrays.sort(codePointRanges, 0, endIndex, CodePointRange.COMPARATOR);
      CodePointRange previous;
      CodePointRange current;
      int i = 0;
      int j = 1;
      while (j < endIndex) {
        previous = codePointRanges[i];
        current = codePointRanges[j];
        if (previous.inclusiveTo >= current.inclusiveFrom) {
          if (previous.inclusiveTo <= current.inclusiveTo) {
            codePointRanges[i] = new CodePointRange(previous.inclusiveFrom, current.inclusiveTo);
          }
          removeElement(j);
        } else if ((previous.inclusiveTo + 1) == current.inclusiveFrom) {
          codePointRanges[i] = new CodePointRange(previous.inclusiveFrom, current.inclusiveTo);
          removeElement(j);
        } else {
          ++i;
          ++j;
        }
      }
      return new CodePointRanges(Arrays.copyOf(codePointRanges, endIndex));
    }
  }
}
