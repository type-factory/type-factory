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

  private static final String DUPLICATE_RADIX_CHARACTER_EXCEPTION_MESSAGE = "Arbitrary radix characters must not contain duplicates – check both your radix characters and case-insensitivity.";

  private Class<?> targetTypeClass;
  private MessageCode messageCode;
  private Locale defaultLocale = Locale.getDefault();
  private boolean caseSensitive = false;
  private WhiteSpace whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
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

    final var numericRadixCodePointsMap = createNumericRadixCodePointsMap();

    return new IntegralNumericTypeParserImpl(
        targetTypeClass, messageCode, defaultLocale, caseSensitive,
        whiteSpace, nullHandling,
        ignoreCharactersSubsetBuilder.build(),
        numericRadixCodePointsMap, numericRadixCodePoints,
        defaultMinValue, defaultMaxValue,
        minValue, maxValue,
        minValueComparisonInclusive, maxValueComparisonInclusive);
  }

  /**
   * <p>Create a hashmap of letters to their corresponding number for a numeric base of arbitrary radix.</p>
   *
   * <p>Note that upper and lower case characters in Unicode are not always symmetric, so we will consider each separately and individually,
   * if the hashmap is catering to a case-insensitive number base.</p>
   *
   * <p>One example of letter case asymmetry occurs in the Greek alphabet:</p>
   * <ul>
   *   <li>{@code Character.toUpperCase('σ') → 'Σ'} — a lowercase non-final Greek Sigma 'σ' is always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toUpperCase('ς') → 'Σ'} — a lowercase final Greek Sigma 'ς' is also always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toLowerCase('Σ') → 'σ'} — an uppercase Greek Sigma 'Σ' is always converted to a lowercase non-final Greek Sigma 'σ'</li>
   * </ul>
   *
   * <p>This method can't work magic, but it will always consider the codepoint as-is and then consider its lowercase and uppercase counterparts separately and individually.</p>
   *
   * @return a hashmap of letters to their corresponding number for a numeric base of arbitrary radix.
   */
  private PrimitiveHashMapOfIntKeyToIntValue createNumericRadixCodePointsMap() {

    final var numericRadixCodePointsMap = new PrimitiveHashMapOfIntKeyToIntValue();

    for (int i = 0; i < numericRadixCodePoints.length; ++i) {
      final int codepoint = numericRadixCodePoints[i];
      addCodePointToRadixCodePointsMap(numericRadixCodePointsMap, codepoint, i);
      if (!caseSensitive) { // case-insensitive
        // Upper and lower case characters in Unicode are not always symmetric, so we will consider each separately and individually
        final int lowerCaseCodepoint = Character.toLowerCase(codepoint);
        if (lowerCaseCodepoint != codepoint) {
          addCodePointToRadixCodePointsMap(numericRadixCodePointsMap, lowerCaseCodepoint, i);
        }
        final int upperCaseCodepoint = Character.toUpperCase(codepoint);
        if (upperCaseCodepoint != codepoint) {
          addCodePointToRadixCodePointsMap(numericRadixCodePointsMap, upperCaseCodepoint, i);
        }
      }
    }
    return numericRadixCodePointsMap;
  }

  private static void addCodePointToRadixCodePointsMap(
      final PrimitiveHashMapOfIntKeyToIntValue numericRadixCodePointsMap,
      final int codepoint,
      final int numericValue) {

    if (numericRadixCodePointsMap.contains(codepoint)) {
      throw new TypeParserBuilderException(DUPLICATE_RADIX_CHARACTER_EXCEPTION_MESSAGE);
    }
    numericRadixCodePointsMap.put(codepoint, numericValue);
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

  /**
   * <p>Configures the numeric type parser to consider numbers that are also made up of letters to be case-insensitive. For example,
   * you may have a number radix (base) that is greater than 10 and have chosen to use letters for the remaining "digits" as in the standard
   * hexadecimal system (radix/base 16) which employs the digits 0-9 and letters A-F to write numbers.</p>
   *
   * <p>Note that upper and lower case characters in Unicode are not always symmetric. Keep this in mind if you choose
   * to define a type parser for an arbitrary radix and specify your own set of digits/letters using one
   * of the {@link #acceptDigitsToArbitraryRadix(int...)} methods. In this situation, the type parser will
   * always consider the letters you specified as they were provided. It will then also consider
   * the lowercase version and uppercase version of that letter if case-insensitivity has been configured.</p>
   *
   * <p>One example of letter case asymmetry occurs in the Greek alphabet where:</p>
   * <ul>
   *   <li>{@code Character.toUpperCase('σ') → 'Σ'} — a lowercase non-final Greek Sigma 'σ' is always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toUpperCase('ς') → 'Σ'} — a lowercase final Greek Sigma 'ς' is also always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toLowerCase('Σ') → 'σ'} — an uppercase Greek Sigma 'Σ' is always converted to a lowercase non-final Greek Sigma 'σ'</li>
   * </ul>
   */
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
