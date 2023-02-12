/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.SubsetUtils.getInclusiveFrom;
import static org.typefactory.impl.SubsetUtils.getInclusiveTo;

import org.typefactory.Category;
import org.typefactory.Subset;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;

/**
 * A builder to create code-point conversions
 */
final class ConverterBuilder {

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

  private ConverterBuilder addCategoryConversion(final Category category, final int[] toCodePointSequence) {
    for (int characterCategory : category.getCharacterCategories()) {
      categoryToCodePointSequence.put(characterCategory, toCodePointSequence);
    }
    return this;
  }

  public ConverterBuilder addCharConversion(final char fromChar, final char toChar) {
    return addCodePointConversion(fromChar, new int[]{toChar});
  }

  public ConverterBuilder addCharConversion(final char fromChar, final CharSequence toCharSequence) {
    return addCodePointConversion(fromChar, toCharSequence.codePoints().toArray());
  }

  public ConverterBuilder addCodePointConversion(final int fromCodePoint, final int toCodePoint) {
    return addCodePointConversion(fromCodePoint, new int[]{toCodePoint});
  }

  public ConverterBuilder addCodePointConversion(final int fromCodePoint, final CharSequence toCharSequence) {
    return addCodePointConversion(fromCodePoint, toCharSequence == null ? EMPTY_INT_ARRAY : toCharSequence.codePoints().toArray());
  }

  private ConverterBuilder addCodePointConversion(final int fromCodePoint, final int[] toCodePointSequence) {
    codePointToCodePointSequence.put(fromCodePoint, toCodePointSequence);
    return this;
  }

  public ConverterBuilder addCodePointConversions(final Subset subset, final CharSequence toCharSequence) {
    return addCodePointConversions(subset, toCharSequence.codePoints().toArray());
  }

  public ConverterBuilder addCodePointConversions(final Subset subset, final char toChar) {
    return addCodePointConversions(subset, new int[]{toChar});
  }

  public ConverterBuilder addCodePointConversions(final Subset subset, final int toCodePoint) {
    return addCodePointConversions(subset, new int[]{toCodePoint});
  }

  /**
   * Converts any of the code-points found in the {@code subset} to the {@code toCodePointSequence}
   *
   * @param subset              a subset containing the code-points that you wish to convert. May be null or empty.
   * @param toCodePointSequence the code-point sequence that you wish to convert to.
   * @return this {@link ConverterBuilder}
   */
  public ConverterBuilder addCodePointConversions(final Subset subset, final int[] toCodePointSequence) {
    if (subset instanceof RangedSubset rangedSubset) {
      return addRangedSubsetCodePointConversions(rangedSubset, toCodePointSequence);
    }
    throw new UnsupportedOperationException("subset of type '" + subset.getClass().getSimpleName() + "' not yet supported.");
  }

  /**
   * Converts any of the code-points found in the {@code subset} to the {@code toCodePointSequence}
   *
   * @param subset              a subset containing the code-points that you wish to convert. May be null or empty.
   * @param toCodePointSequence the code-point sequence that you wish to convert to.
   * @return this {@link ConverterBuilder}
   */
  private ConverterBuilder addRangedSubsetCodePointConversions(final RangedSubset subset, final int[] toCodePointSequence) {

    if (subset != null) {
      final char[] singleByteRangedSubset = subset.getSingleByteCodePointRanges();
      for (char c : singleByteRangedSubset) {
        final int inclusiveFrom = getInclusiveFrom(c);
        final int inclusiveTo = getInclusiveTo(c);
        for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
          addCodePointConversion(j, toCodePointSequence);
        }
      }
      final int[] doubleByteRangedSubset = subset.getDoubleByteCodePointRanges();
      for (int c : doubleByteRangedSubset) {
        final int inclusiveFrom = getInclusiveFrom(c);
        final int inclusiveTo = getInclusiveTo(c);
        for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
          addCodePointConversion(j, toCodePointSequence);
        }
      }
      final long[] tripleByteRangedSubset = subset.getTripleByteCodePointRanges();
      for (long c : tripleByteRangedSubset) {
        final int inclusiveFrom = getInclusiveFrom(c);
        final int inclusiveTo = getInclusiveTo(c);
        for (int j = inclusiveFrom; j <= inclusiveTo; ++j) {
          addCodePointConversion(j, toCodePointSequence);
        }
      }
    }
    return this;
  }

  public ConverterBuilder addCharSequenceConversion(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
    if (fromCharSequence != null && !fromCharSequence.isEmpty()) {
      final int[] fromCodePointSequence = fromCharSequence.codePoints().toArray();
      final int[] toCodePointSequence = toCharSequence == null ? EMPTY_INT_ARRAY : toCharSequence.codePoints().toArray();
      addCodePointSequenceConversion(fromCodePointSequence, toCodePointSequence);
    }
    return this;
  }

  public ConverterBuilder addCodePointSequenceConversion(final int[] fromCodePointSequence, final int[] toCodePointSequence) {
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
        for (int key : codePointToCodePointSequence.keySet()) {
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
