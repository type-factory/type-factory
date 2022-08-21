package org.datatypeproject.impl;


import org.datatypeproject.TypeParser;

class CodePointToCodePointSequenceConverter implements Converter {

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
    if (converterResults instanceof ConverterResultsImpl) {
      return isCodePointConversionRequired(currentCodePoint, currentIndex, (ConverterResultsImpl)converterResults);
    }
    throw new IllegalArgumentException("Invalid argument - 'converterResults' must be of type " + ConverterResultsImpl.class.getName());
  }

  private boolean isCodePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResultsImpl converterResults) {
    int[] toCodePointSequence = getCodePointConversion(currentCodePoint);
    if (toCodePointSequence != null) {
      converterResults.setConvertFromIndex(currentIndex);
      converterResults.setConvertToCodePointSequence(toCodePointSequence);
      return true;
    }
    return false;
  }

  private int[] getCodePointConversion(final int fromCodePoint) {
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
