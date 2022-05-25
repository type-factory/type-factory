package land.artli.easytype;

import static land.artli.easytype.Constants.EMPTY_INT_ARRAY;
import static land.artli.easytype.RangedSubsetUtils.getInclusiveFrom;
import static land.artli.easytype.RangedSubsetUtils.getInclusiveTo;

import land.artli.easytype.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;

/**
 * A builder to create code-point conversions
 */
class ConverterBuilder {

  private final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();

  private final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();

  private final RootTreeNode codePointSequenceToCodePointSequence = new RootTreeNode();

  ConverterBuilder() {
  }

  public ConverterBuilder addCategoryConversion(final Category category, final char toChar) {
    return addCategoryConversion(category, new int[]{toChar});
  }

  public ConverterBuilder addCategoryConversion(final Category category, final int toCodePoint) {
    return addCategoryConversion(category, new int[]{toCodePoint});
  }

  public ConverterBuilder addCategoryConversion(final Category category, final CharSequence toCharSequence) {
    return addCategoryConversion(category, toCharSequence.codePoints().toArray());
  }

  public ConverterBuilder addCategoryConversion(final Category category, final int[] toCodePoints) {
    for (int characterCategory : category.characterCategories) {
      categoryToCodePointSequence.put(characterCategory, toCodePoints);
    }
    return this;
  }

  public ConverterBuilder addCharConversion(final char fromChar, final char toChar) {
    addCodePointConversion(fromChar, new int[]{toChar});
    return this;
  }

  public ConverterBuilder addCharConversions(final char[] fromChars, final char toChar) {
    if (fromChars != null) {
      for (int i = 0; i < fromChars.length; ++i) {
        addCodePointConversion(fromChars[i], new int[]{toChar});
      }
    }
    return this;
  }

  public ConverterBuilder addCharConversion(final char fromChar, final CharSequence toCharSequence) {
    addCodePointConversion(fromChar, toCharSequence.codePoints().toArray());
    return this;
  }

  public ConverterBuilder addCodePointConversion(final int fromCodePoint, final int toCodePoint) {
    addCodePointConversion(fromCodePoint, new int[]{toCodePoint});
    return this;
  }

  public ConverterBuilder addCharConversion(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
    if (fromCharSequence != null && !fromCharSequence.isEmpty()) {
      final int[] codePointSequence = fromCharSequence.codePoints().toArray();
      addCodePointConversion(codePointSequence, toCharSequence.codePoints().toArray());
    }
    return this;
  }

  public ConverterBuilder addCodePointConversions(final RangedSubset subset, final CharSequence toCharSequence) {
    addCodePointConversions(subset, toCharSequence.codePoints().toArray());
    return this;
  }

  public ConverterBuilder addCodePointConversions(final RangedSubset subset, final char toChar) {
    addCodePointConversions(subset, new int[]{toChar});
    return this;
  }

  public ConverterBuilder addCodePointConversions(final RangedSubset subset, final int toCodePoint) {
    addCodePointConversions(subset, new int[]{toCodePoint});
    return this;
  }

  public ConverterBuilder addCodePointConversions(final RangedSubset subset, final int[] toCodePoints) {

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

  public ConverterBuilder addCodePointConversion(final int fromCodePoint, final CharSequence toCharSequence) {
    addCodePointConversion(fromCodePoint, toCharSequence == null ? EMPTY_INT_ARRAY : toCharSequence.codePoints().toArray());
    return this;
  }

  public ConverterBuilder addCodePointConversion(final int fromCodePoint, final int[] toCodePointSequence) {
    codePointToCodePointSequence.put(fromCodePoint, toCodePointSequence);
    return this;
  }

  public ConverterBuilder addCodePointConversion(final int[] fromCodePointSequence, final int[] toCodePointSequence) {
    if (fromCodePointSequence == null || fromCodePointSequence.length == 0) {
      return this;
    }
    if (fromCodePointSequence.length == 1) {
      codePointToCodePointSequence.put(fromCodePointSequence[0], toCodePointSequence);
    } else {
      codePointSequenceToCodePointSequence.add(fromCodePointSequence, toCodePointSequence);
    }
    return this;
  }

  Converter build() {
    if (!codePointSequenceToCodePointSequence.isEmpty()) {
      if (!codePointToCodePointSequence.isEmpty()) {
        int[] temp = new int[1];
        for (int key : codePointToCodePointSequence.keys()) {
          temp[0] = key;
          codePointSequenceToCodePointSequence.add(temp, codePointToCodePointSequence.get(key));
        }
      }
      return new CodePointSequenceToCodePointSequenceConverter(
          codePointSequenceToCodePointSequence,
          categoryToCodePointSequence);
    }
    return new CodePointToCodePointSequenceConverter(
        codePointToCodePointSequence.isEmpty() ? null : codePointToCodePointSequence,
        categoryToCodePointSequence.isEmpty() ? null : categoryToCodePointSequence);
  }
}
