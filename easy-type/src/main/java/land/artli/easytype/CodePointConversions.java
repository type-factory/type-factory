package land.artli.easytype;

import static land.artli.easytype.RangedSubsetUtils.getInclusiveFrom;
import static land.artli.easytype.RangedSubsetUtils.getInclusiveTo;

import java.util.ArrayList;
import java.util.List;
import land.artli.easytype.CodePointSequenceToCodePointSequenceConverter.Conversion;
import land.artli.easytype.CodePointSequenceToCodePointSequenceConverter.ConverterResultsImpl;

class CodePointConversions {

  /**
   * Hash-map of Character categories to code-point arrays:
   */
  private final PrimitiveIntHashMap categoryToCodePointSequence;

  /**
   * Hash-map of code-points to code-point arrays:
   */
  private final PrimitiveIntHashMap codePointToCodePointSequence;

  /**
   * Hash-map of code-points to code-point arrays:
   */
  private final CodePointSequenceToCodePointSequenceConverter codePointSequenceToCodePointSequenceConverter;

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  private final int maxConvertedLength;

  /**
   * Creates a {@link ConverterResults} object to be used for the life of a single {@link TypeParser} parse method.
   * <p>
   * The idea is to discourage object creation and encourage reuse of this one instance by passing it to the converter to receive the results into.
   * The alternative would have been for the converter method to instantiate and return a new object for the tuple every time it was called which
   * would be for every character in the parsed character sequence.
   *
   * @return a {@link ConverterResults} object to be used for the life of a single {@link TypeParser} parse method.
   */
  ConverterResults createConverterResults() {
    return new ConverterResultsImpl();
  }

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  public int getMaxConvertedLength() {
    return maxConvertedLength;
  }

  static CodePointConversionsBuilder builder() {
    return new CodePointConversionsBuilder();
  }

  private CodePointConversions(
      final PrimitiveIntHashMap categoryToCodePointSequence,
      final PrimitiveIntHashMap codePointToCodePointSequence,
      final CodePointSequenceToCodePointSequenceConverter codePointSequenceToCodePointSequenceConverter) {
    this.categoryToCodePointSequence = categoryToCodePointSequence;
    this.codePointToCodePointSequence = codePointToCodePointSequence;
    this.codePointSequenceToCodePointSequenceConverter = codePointSequenceToCodePointSequenceConverter;
    int max = 0;
    if (categoryToCodePointSequence != null && !categoryToCodePointSequence.isEmpty()) {
      max = Math.max(max, categoryToCodePointSequence.getMaxValueArrayLength());
    }
    if (codePointToCodePointSequence != null && !codePointToCodePointSequence.isEmpty()) {
      max = Math.max(max, codePointToCodePointSequence.getMaxValueArrayLength());
    }
    this.maxConvertedLength = max;
  }

  boolean codePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults) {
    if (codePointSequenceToCodePointSequenceConverter != null &&
        codePointSequenceToCodePointSequenceConverter.codePointConversionRequired(currentCodePoint, currentIndex, (ConverterResultsImpl) converterResults)) {
      return true;
    }
    int[] toCodePointSequence = getCodePointConversion(currentCodePoint);
    if (toCodePointSequence != null) {
      converterResults.setConvertFromIndex(currentIndex);
      converterResults.setConvertToCodePointSequence(toCodePointSequence);
      return true;
    }
    return false;
  }

  int[] getCodePointConversion(final int fromCodePoint) {
    if (codePointToCodePointSequence != null) {
      final int[] toCodePoints = codePointToCodePointSequence.get(fromCodePoint);
      if (toCodePoints != null) {
        return toCodePoints;
      }
    }
    if (categoryToCodePointSequence != null) {
      final int[] toCodePoints = categoryToCodePointSequence.get(Character.getType(fromCodePoint));
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

    private final PrimitiveIntHashMap categoryToCodePointSequence = new PrimitiveIntHashMap();

    private final PrimitiveIntHashMap codePointToCodePointSequence = new PrimitiveIntHashMap();

    private final List<Conversion> conversions = new ArrayList<>();

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
        categoryToCodePointSequence.put(characterCategory, toCodePoints);
      }
      return this;
    }

    public CodePointConversionsBuilder addCharConversion(final char fromChar, final char toChar) {
      addCodePointConversion(fromChar, new int[]{toChar});
      return this;
    }

    public CodePointConversionsBuilder addCharConversions(final char[] fromChars, final char toChar) {
      if (fromChars != null) {
        for (int i = 0; i < fromChars.length; ++i) {
          addCodePointConversion(fromChars[i], new int[]{toChar});
        }
      }
      return this;
    }

    public CodePointConversionsBuilder addCharConversion(final char fromChar, final CharSequence toCharSequence) {
      addCodePointConversion(fromChar, toCharSequence.codePoints().toArray());
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversion(final int fromCodePoint, final int toCodePoint) {
      addCodePointConversion(fromCodePoint, new int[]{toCodePoint});
      return this;
    }

    public CodePointConversionsBuilder addCharConversion(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
      if (fromCharSequence != null && !fromCharSequence.isEmpty()) {
        final int[] codePointSequence = fromCharSequence.codePoints().toArray();
        addCodePointConversion(codePointSequence, toCharSequence.codePoints().toArray());
      }
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversions(final RangedSubset subset, final CharSequence toCharSequence) {
      addCodePointConversions(subset, toCharSequence.codePoints().toArray());
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversions(final RangedSubset subset, final char toChar) {
      addCodePointConversions(subset, new int[]{toChar});
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversions(final RangedSubset subset, final int toCodePoint) {
      addCodePointConversions(subset, new int[]{toCodePoint});
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversions(final RangedSubset subset, final int[] toCodePoints) {

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
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversion(final int fromCodePoint, final CharSequence toCharSequence) {
      addCodePointConversion(fromCodePoint, toCharSequence.codePoints().toArray());
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversion(final int fromCodePoint, final int[] toCodePointSequence) {
      codePointToCodePointSequence.put(fromCodePoint, toCodePointSequence);
      return this;
    }

    public CodePointConversionsBuilder addCodePointConversion(final int[] fromCodePointSequence, final int[] toCodePointSequence) {
      if (fromCodePointSequence == null || fromCodePointSequence.length == 0) {
        return this;
      }
      if (fromCodePointSequence.length == 1) {
        codePointToCodePointSequence.put(fromCodePointSequence[0], toCodePointSequence);
      } else {
        conversions.add(new Conversion(fromCodePointSequence, toCodePointSequence));
      }
      return this;
    }

    CodePointConversions build() {
      return new CodePointConversions(
          categoryToCodePointSequence.isEmpty() ? null : categoryToCodePointSequence,
          codePointToCodePointSequence.isEmpty() ? null : codePointToCodePointSequence,
          conversions.isEmpty() ? null : new CodePointSequenceToCodePointSequenceConverter(conversions));
    }
  }

  interface ConverterResults {

    int getConvertFromIndex();

    void setConvertFromIndex(int convertFromIndex);

    int[] getConvertToCodePointSequence();

    void setConvertToCodePointSequence(int[] convertToCodePointSequence);
  }

}
