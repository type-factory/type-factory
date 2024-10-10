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


import org.typefactory.TypeParser;

final class CodePointToCodePointSequenceConverter implements Converter {

  /**
   * Hash-map of Character categories to code-point arrays:
   */
  private final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence;

  /**
   * Hash-map of code-points to code-point arrays:
   */
  private final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence;

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
  public ConverterResults createConverterResults() {
    return new ConverterResultsImpl();
  }

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  public int getMaxConvertedLength() {
    return maxConvertedLength;
  }

  CodePointToCodePointSequenceConverter(
      final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence,
      final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence) {
    this.codePointToCodePointSequence = codePointToCodePointSequence;
    this.categoryToCodePointSequence = categoryToCodePointSequence;
    int max = 0;
    if (codePointToCodePointSequence != null && !codePointToCodePointSequence.isEmpty()) {
      max = Math.max(max, codePointToCodePointSequence.getMaxValueArrayLength());
    }
    if (categoryToCodePointSequence != null && !categoryToCodePointSequence.isEmpty()) {
      max = Math.max(max, categoryToCodePointSequence.getMaxValueArrayLength());
    }
    this.maxConvertedLength = max;
  }

  public boolean isCodePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults) {
    if (converterResults instanceof ConverterResultsImpl converterResultsImpl) {
      return isCodePointConversionRequired(currentCodePoint, currentIndex, converterResultsImpl);
    }
    throw new IllegalArgumentException("Invalid argument - 'converterResults' must be of type " + ConverterResultsImpl.class.getName());
  }

  private boolean isCodePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResultsImpl converterResults) {
    int[] toCodePointSequence = getCodePointConversion(currentCodePoint);
    if (toCodePointSequence != null) {
      converterResults.convertFromIndex = currentIndex;
      converterResults.convertToCodePointSequence = toCodePointSequence;
      return true;
    }
    return false;
  }

  private int[] getCodePointConversion(final int fromCodePoint) {
    int[] toCodePoints = null;
    if (codePointToCodePointSequence != null) {
      toCodePoints = codePointToCodePointSequence.get(fromCodePoint);
      if (toCodePoints != null) {
        return toCodePoints;
      }
    }
    if (categoryToCodePointSequence != null) {
      toCodePoints = categoryToCodePointSequence.get(Character.getType(fromCodePoint));
    }
    return toCodePoints;
  }

  static class ConverterResultsImpl implements ConverterResults {

    private int convertFromIndex;
    private int[] convertToCodePointSequence;

    public int getConvertFromIndex() {
      return convertFromIndex;
    }

    public void setConvertFromIndex(int convertFromIndex) {
      this.convertFromIndex = convertFromIndex;
    }

    public int[] getConvertToCodePointSequence() {
      return convertToCodePointSequence;
    }

    public void setConvertToCodePointSequence(int[] convertToCodePointSequence) {
      this.convertToCodePointSequence = convertToCodePointSequence;
    }
  }
}
