package land.artli.easytype;

import static land.artli.easytype.RangedSubsetUtils.getInclusiveFrom;
import static land.artli.easytype.RangedSubsetUtils.getInclusiveTo;

class CodePointConversions {

  /**
   * Hash-map of Character categories to code-point arrays:
   */
  final IntToIntArrayHashMap categoryConversionToCodePoint;

  /**
   * Hash-map of code-points to code-point arrays:
   */
  final IntToIntArrayHashMap codePointConversions;

  final int maxToCodePointsLength;

  static CodePointConversionsBuilder builder() {
    return new CodePointConversionsBuilder();
  }

  private CodePointConversions(
      final IntToIntArrayHashMap categoryConversionToCodePoint,
      final IntToIntArrayHashMap codePointConversions) {
    this.categoryConversionToCodePoint = categoryConversionToCodePoint;
    this.codePointConversions = codePointConversions;
    int max = 0;
    if (categoryConversionToCodePoint != null && !categoryConversionToCodePoint.isEmpty()) {
      max = Math.max(max, categoryConversionToCodePoint.getMaxValueArrayLength());
    }
    if (codePointConversions != null && !codePointConversions.isEmpty()) {
      max = Math.max(max, codePointConversions.getMaxValueArrayLength());
    }
    this.maxToCodePointsLength = max;
  }

  int[] getCodePointConversion(final int fromCodePoint) {
    if (categoryConversionToCodePoint != null) {
      final int[] toCodePoints = categoryConversionToCodePoint.get(Character.getType(fromCodePoint));
      if (toCodePoints != null) {
        return toCodePoints;
      }
    }
    if (codePointConversions != null) {
      final int[] toCodePoints = codePointConversions.get(fromCodePoint);
      if (toCodePoints != null) {
        return toCodePoints;
      }
    }
    return null;
  }

  int[] convertCodePoint(final int fromCodePoint, final int[] useThisArrayWhenNoConversionRequired) {
    int[] toCodePoints = getCodePointConversion(fromCodePoint);
    if (toCodePoints == null) {
      useThisArrayWhenNoConversionRequired[0] = fromCodePoint;
      return useThisArrayWhenNoConversionRequired;
    }
    return toCodePoints;
  }

  /**
   * A builder to create code-point conversions
   */
  static class CodePointConversionsBuilder {

    private IntToIntArrayHashMap categoryConversionToCodePoint = new IntToIntArrayHashMap();

    private IntToIntArrayHashMap codePointConversions = new IntToIntArrayHashMap();

    private CodePointConversionsBuilder() {
    }

    public CodePointConversionsBuilder addCategoryConversion(final Category category, final char toChar) {
      return addCategoryConversion(category, new int[]{toChar});
    }

    public CodePointConversionsBuilder addCategoryConversion(final Category category, final int toCodePoint) {
      return addCategoryConversion(category, new int[]{toCodePoint});
    }

    public CodePointConversionsBuilder addCategoryConversion(final Category category, final CharSequence toCharSequence) {
      return addCategoryConversion(category, toCharSequence.codePoints().toArray());
    }

    public CodePointConversionsBuilder addCategoryConversion(final Category category, final int[] toCodePoints) {
      for (int characterCategory : category.characterCategories) {
        categoryConversionToCodePoint.put(characterCategory, toCodePoints);
      }
      return this;
    }

    void addCharConversion(final char fromChar, final char toChar) {
      addCodePointConversion(fromChar, new int[]{toChar});
    }

    void addCharConversions(final char[] fromChars, final char toChar) {
      if (fromChars != null) {
        for (int i = 0; i < fromChars.length; ++i) {
          addCodePointConversion(fromChars[i], new int[]{toChar});
        }
      }
    }

    void addCharConversion(final char fromChar, final CharSequence toCharSequence) {
      addCodePointConversion(fromChar, toCharSequence.codePoints().toArray());
    }

    void addCodePointConversion(final int fromCodePoint, final int toCodePoint) {
      addCodePointConversion(fromCodePoint, new int[]{toCodePoint});
    }

    void addCodePointConversions(final RangedSubsetImpl subset, final CharSequence toCharSequence) {
      addCodePointConversions(subset, toCharSequence.codePoints().toArray());
    }

    void addCodePointConversions(final RangedSubsetImpl subset, final char toChar) {
      addCodePointConversions(subset, new int[]{toChar});
    }

    void addCodePointConversions(final RangedSubsetImpl subset, final int toCodePoint) {
      addCodePointConversions(subset, new int[]{toCodePoint});
    }

    void addCodePointConversions(final RangedSubsetImpl subset, final int[] toCodePoints) {

      if (subset != null) {
        final char[] singleByteRangedSubset = subset.getSingleByteCodePointRanges();
        for (char c : singleByteRangedSubset) {
          final int inclusiveFrom = getInclusiveFrom(c);
          final int inclusiveTo = getInclusiveTo(c);
          for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
            addCodePointConversion(j, toCodePoints);
          }
        }
        final int[] doubleByteRangedSubset = subset.getDoubleByteCodePointRanges();
        for (int c : doubleByteRangedSubset) {
          final int inclusiveFrom = getInclusiveFrom(c);
          final int inclusiveTo = getInclusiveTo(c);
          for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
            addCodePointConversion(j, toCodePoints);
          }
        }
        final long[] tripleByteRangedSubset = subset.getTripleByteCodePointRanges();
        for (long c : tripleByteRangedSubset) {
          final int inclusiveFrom = getInclusiveFrom(c);
          final int inclusiveTo = getInclusiveTo(c);
          for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
            addCodePointConversion(j, toCodePoints);
          }
        }
      }
    }

    void addCodePointConversion(final int fromCodePoint, final CharSequence toCharSequence) {
      addCodePointConversion(fromCodePoint, toCharSequence.codePoints().toArray());
    }

    void addCodePointConversion(final int fromCodePoint, final int[] toCodePoints) {
      codePointConversions.put(fromCodePoint, toCodePoints);
    }

    CodePointConversions build() {
      return new CodePointConversions(
          categoryConversionToCodePoint.isEmpty() ? null : categoryConversionToCodePoint,
          codePointConversions.isEmpty() ? null : codePointConversions);
    }
  }
}
