package org.typefactory.impl;

import java.math.BigInteger;
import java.util.Locale;
import org.typefactory.Category;
import org.typefactory.MessageCode;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParserBuilderException;

final class IntegralNumericTypeParserBuilderImpl {

  private static final BigInteger MIN_INT_128 = new BigInteger("-170141183460469231731687303715884105728", 10);
  private static final BigInteger MAX_INT_128 = new BigInteger("170141183460469231731687303715884105727", 10);
  private static final int[] DEFAULT_BASE_8_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7'};
  private static final int[] DEFAULT_BASE_10_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
  private static final int[] DEFAULT_BASE_16_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
  private static final int[] DEFAULT_BASE_32_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V'};
  private static final int[] DEFAULT_BASE_36_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z'};
  private static final int[] DEFAULT_BASE_62_CODE_POINTS = new int[]{
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
      'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
      'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
      'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

  private Class<?> targetTypeClass;
  private MessageCode messageCode;
  private Locale defaultLocale = Locale.getDefault();
  private boolean caseSensitive = false;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private NumericNullHandling nullHandling;
  private final SubsetBuilder ignoreCharactersSubsetBuilder = Subset.builder();
  private int[] numericRadixCodePoints;
  private final long defaultMinValue;
  private final long defaultMaxValue;
  private long minValue;
  private long maxValue;
  private boolean minValueComparisonInclusive;
  private boolean maxValueComparisonInclusive;

  IntegralNumericTypeParserBuilderImpl(final long defaultMinValue, final long defaultMaxValue) {
    this.minValue = (this.defaultMinValue = defaultMinValue);
    this.maxValue = (this.defaultMaxValue = defaultMaxValue);
  }

  IntegralNumericTypeParserImpl build() {

    final var numericRadixCodePointsMap = new PrimitiveHashMapOfIntKeyToIntValue();

    numericRadixCodePointsMap.clear();
    for (int i = 0; i < numericRadixCodePoints.length; ++i) {
      final int codepoint = caseSensitive
          ? numericRadixCodePoints[i]
          : Character.toLowerCase(numericRadixCodePoints[i]);
      if (numericRadixCodePointsMap.contains(codepoint)) {
        throw new TypeParserBuilderException("Arbitrary radix characters must not contain duplicates – check both your radix characters and case-insensitivity.");
      }
      numericRadixCodePointsMap.put(codepoint, i);
    }

    return new IntegralNumericTypeParserImpl(
        targetTypeClass, messageCode, defaultLocale, caseSensitive,
        whiteSpace, nullHandling,
        ignoreCharactersSubsetBuilder.build(),
        numericRadixCodePointsMap, numericRadixCodePoints,
        defaultMinValue, defaultMaxValue,
        minValue, maxValue,
        minValueComparisonInclusive, maxValueComparisonInclusive);
  }

  public void defaultLocale(final Locale locale) {
    defaultLocale = locale;
  }

  public void clearMinValue() {
    minValue = defaultMinValue;
  }

  public void clearMaxValue() {
    maxValue = defaultMaxValue;
  }

  private void minValue(final long minValue, final boolean inclusive) {
    this.minValue = minValue;
    this.minValueComparisonInclusive = inclusive;
  }

  private void maxValue(final long maxValue, final boolean inclusive) {
    this.maxValue = maxValue;
    this.maxValueComparisonInclusive = inclusive;
  }

  public void minValueInclusive(final long minValue) {
    minValue(minValue, true);
  }

  public void maxValueInclusive(final long maxValue) {
    maxValue(maxValue, true);
  }

  public void minValueExclusive(final long minValue) {
    minValue(minValue, false);
  }

  public void maxValueExclusive(final long maxValue) {
    maxValue(maxValue, false);
  }

  public void caseSensitive() {
    this.caseSensitive = true;
  }

  public void caseInsensitive() {
    this.caseSensitive = false;
  }

  public void acceptDigitsToArbitraryRadix(final char... arbitraryRadixChars) {
    if (arbitraryRadixChars == null) {
      acceptDigitsToArbitraryRadix((int[]) null);
    } else {
      final int[] codePoints = new int[arbitraryRadixChars.length];
      for (int i = 0; i < arbitraryRadixChars.length; ++i) {
        codePoints[i] = arbitraryRadixChars[i];
      }
      acceptDigitsToArbitraryRadix(codePoints);
    }
  }

  public void acceptDigitsToArbitraryRadix(final int... arbitraryRadixCodePoints) {
    if (arbitraryRadixCodePoints == null) {
      numericRadixCodePoints = Constants.EMPTY_INT_ARRAY;
    } else {
      numericRadixCodePoints = arbitraryRadixCodePoints;
    }
  }

  public void acceptDigitsToArbitraryRadix(final String digits) {
    acceptDigitsToArbitraryRadix(digits.codePoints().toArray());
  }

  /**
   * <p>Will accept the following digits for base-8 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7
   * }</pre>
   */
  public void acceptDigitsForBase8() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_8_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-10 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
   * }</pre>
   */
  public void acceptDigitsForBase10() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_10_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-16 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
   * }</pre>
   */
  public void acceptDigitsForBase16() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_16_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-32 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V
   * }</pre>
   */
  public void acceptDigitsForBase32() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_32_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-36 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
   * W, X, Y, Z
   * }</pre>
   */
  public void acceptDigitsForBase36() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_36_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-62 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
   * W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l,
   * m, n, o, p, q, r, s, t, u, v, w, x, y, z
   * }</pre>
   */
  public void acceptDigitsForBase62() {
    acceptDigitsToArbitraryRadix(DEFAULT_BASE_62_CODE_POINTS);
  }

  public void ignoreAllOccurrencesOfChar(final char ch) {
    ignoreCharactersSubsetBuilder.includeChar(ch);
  }

  public void ignoreAllOccurrencesOfChars(final char... chars) {
    ignoreCharactersSubsetBuilder.includeChars(chars);
  }

  public void ignoreAllOccurrencesOfCodePoint(final int codePoint) {
    ignoreCharactersSubsetBuilder.includeCodePoint(codePoint);
  }

  public void ignoreAllOccurrencesOfCodePoints(final int... codePoints) {
    ignoreCharactersSubsetBuilder.includeCodePoints(codePoints);
  }

  public void ignoreAllWhitespace() {
    whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
  }

  public void ignoreAllDashesAndHyphens() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
  }

  public void ignoreAllDashesAndHyphensExceptLeadingMinusSign() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
  }

}
