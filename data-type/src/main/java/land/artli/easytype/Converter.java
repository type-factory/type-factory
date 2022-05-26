package land.artli.easytype;

interface Converter {

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  int getMaxConvertedLength();

  /**
   * Creates a reusable {@link ConverterResults} instance that should be used with the
   * {@link #isCodePointConversionRequired(int, int, ConverterResults)} method.
   *
   * @return a reusable {@link ConverterResults} instance.
   */
  ConverterResults createConverterResults();

  boolean isCodePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults);

  static ConverterBuilder builder() {
    return new ConverterBuilder();
  }

  interface ConverterResults {

    int getConvertFromIndex();

    void setConvertFromIndex(int convertFromIndex);

    int[] getConvertToCodePointSequence();

    void setConvertToCodePointSequence(int[] convertToCodePointSequence);
  }

}
