package org.typefactory.impl;

import static org.typefactory.impl.Constants.MINUS_CODEPOINTS;
import static org.typefactory.impl.Constants.PLUS_CODEPOINTS;

import java.util.Locale;
import org.typefactory.Category;
import org.typefactory.MessageCode;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.TypeParserBuilderException.MessageCodes;

final class IntegralNumericTypeParserBuilderImpl {

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
  private boolean caseSensitive = true;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private final SubsetBuilder ignoreCharactersSubsetBuilder = Subset.builder();
  private int[] numericRadixCodePoints;
  private final long defaultMinValue;
  private final long defaultMaxValue;
  private long minValue;
  private long maxValue;
  private boolean minValueComparisonInclusive;
  private boolean maxValueComparisonInclusive;
  private boolean ignoreLeadingNegativeSign = false;
  private boolean ignoreLeadingPositiveSign = false;

  IntegralNumericTypeParserBuilderImpl(final long defaultMinValue, final long defaultMaxValue) {
    this.minValue = (this.defaultMinValue = defaultMinValue);
    this.maxValue = (this.defaultMaxValue = defaultMaxValue);
  }

  IntegralNumericTypeParserImpl build() {

    final var numericRadixCodePointsMap = createNumericRadixCodePointsMap();

    return new IntegralNumericTypeParserImpl(
        targetTypeClass, messageCode, defaultLocale, caseSensitive,
        whiteSpace,
        ignoreCharactersSubsetBuilder.build(),
        numericRadixCodePointsMap, numericRadixCodePoints,
        defaultMinValue, defaultMaxValue,
        minValue, maxValue,
        minValueComparisonInclusive, maxValueComparisonInclusive,
        ignoreLeadingNegativeSign, ignoreLeadingPositiveSign);
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
      throw TypeParserBuilderException.builder()
          .messageCode(MessageCodes.DUPLICATE_RADIX_CHARACTER_EXCEPTION_MESSAGE)
          .build();
    }
    numericRadixCodePointsMap.put(codepoint, numericValue);
  }

  public void targetTypeClass(final Class<?> targetTypeClass) {
    this.targetTypeClass = targetTypeClass;
  }

  public void messageCode(final MessageCode messageCode) {
    this.messageCode = messageCode;
  }

  public void defaultLocale(final Locale locale) {
    defaultLocale = locale;
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
   * of the {@link #allowCustomBaseNumbers(int...)} methods. In this situation, the type parser will
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

  @SuppressWarnings({"java:S3012"}) // The suggested SonarCloud fixes all copy arrays of the same primitive type – this method does not.
  private static int[] convertCharArrayToCodePointArray(final char[] characters) {
    final int[] codePoints = new int[characters.length];
    for (int i = 0; i < characters.length; ++i) {
      codePoints[i] = characters[i];
    }
    return codePoints;
  }

  public void allowCustomBaseNumbers(final char... charactersForCustomNumericBase) {
    if (charactersForCustomNumericBase == null) {
      allowCustomBaseNumbers((int[]) null);
    } else {
      allowCustomBaseNumbers(convertCharArrayToCodePointArray(charactersForCustomNumericBase));
    }
  }

  public void allowCustomBaseNumbers(final int... codePointsForCustomNumericBase) {
    if (codePointsForCustomNumericBase == null) {
      numericRadixCodePoints = Constants.EMPTY_INT_ARRAY;
    } else {
      numericRadixCodePoints = codePointsForCustomNumericBase;
    }
  }

  /**
   * <p>Will accept the following digits for base-8 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7
   * }</pre>
   */
  public void allowBase8Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_8_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-10 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
   * }</pre>
   */
  public void allowBase10Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_10_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-16 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
   * }</pre>
   */
  public void allowBase16Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_16_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-32 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V
   * }</pre>
   */
  public void allowBase32Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_32_CODE_POINTS);
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
  public void allowBase36Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_36_CODE_POINTS);
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
  public void allowBase62Numbers() {
    allowCustomBaseNumbers(DEFAULT_BASE_62_CODE_POINTS);
  }

  public void allowLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = false;
  }

  public void ignoreLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = true;
  }

  public void allowLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = false;
  }

  public void ignoreLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = true;
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
    // including the minus sign (U+2212) and the heavy minus sign (U+2796) in the ignore set
    ignoreCharactersSubsetBuilder.includeChars('\u2212', '\u2796');
    ignoreLeadingNegativeSign();
  }

  public void ignoreAllDashesAndHyphensExceptLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
    // excluding the minus sign (U+2212) and the heavy minus sign (U+2796) from the ignore set
    ignoreCharactersSubsetBuilder.excludeChars('\u2212', '\u2796');
    allowLeadingNegativeSign();
  }

}
