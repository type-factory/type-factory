package org.typefactory.impl;

import static org.typefactory.impl.Constants.HEAVY_MINUS;
import static org.typefactory.impl.Constants.MATH_MINUS;
import static org.typefactory.impl.Constants.MINUS_CODEPOINTS;
import static org.typefactory.impl.Constants.PLUS_CODEPOINTS;

import java.util.Locale;
import org.typefactory.Category;
import org.typefactory.LongTypeParser.LongTypeParserBuilder;
import org.typefactory.MessageCode;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.TypeParserBuilderException.MessageCodes;

final class LongTypeParserBuilderImpl implements LongTypeParserBuilder {

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

  LongTypeParserBuilderImpl() {
    this(Long.MIN_VALUE, Long.MAX_VALUE);
  }

  LongTypeParserBuilderImpl(final long defaultMinValue, final long defaultMaxValue) {
    this.minValue = (this.defaultMinValue = defaultMinValue);
    this.maxValue = (this.defaultMaxValue = defaultMaxValue);
  }

  public LongTypeParserImpl build() {

    if (numericRadixCodePoints == null || numericRadixCodePoints.length == 0) {
      throw TypeParserBuilderException.builder()
          .messageCode(MessageCodes.NO_RADIX_CONFIGURED_EXCEPTION_MESSAGE)
          .build();
    }

    final var numericRadixCodePointsMap = createNumericRadixCodePointsMap();

    return new LongTypeParserImpl(
        targetTypeClass, messageCode, defaultLocale,
        whiteSpace,
        ignoreCharactersSubsetBuilder.build(),
        numericRadixCodePointsMap, numericRadixCodePoints,
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
          .messageCode(MessageCodes.DUPLICATE_CUSTOM_RADIX_CHARACTER_EXCEPTION_MESSAGE)
          .build();
    }
    numericRadixCodePointsMap.put(codepoint, numericValue);
  }

  public LongTypeParserBuilderImpl targetTypeClass(final Class<?> targetTypeClass) {
    this.targetTypeClass = targetTypeClass;
    return this;
  }

  public LongTypeParserBuilderImpl messageCode(final MessageCode messageCode) {
    this.messageCode = messageCode;
    return this;
  }

  public LongTypeParserBuilderImpl defaultLocale(final Locale locale) {
    defaultLocale = locale;
    return this;
  }

  private LongTypeParserBuilderImpl minValue(final long minValue, final boolean inclusive) {
    this.minValue = minValue;
    this.minValueComparisonInclusive = inclusive;
    return this;
  }

  private LongTypeParserBuilderImpl maxValue(final long maxValue, final boolean inclusive) {
    this.maxValue = maxValue;
    this.maxValueComparisonInclusive = inclusive;
    return this;
  }

  public LongTypeParserBuilderImpl minValueInclusive(final long minValue) {
    return minValue(minValue, true);
  }

  public LongTypeParserBuilderImpl maxValueInclusive(final long maxValue) {
    return maxValue(maxValue, true);
  }

  public LongTypeParserBuilderImpl minValueExclusive(final long minValue) {
    return minValue(minValue, false);
  }

  public LongTypeParserBuilderImpl maxValueExclusive(final long maxValue) {
    return maxValue(maxValue, false);
  }

  public LongTypeParserBuilderImpl caseSensitive() {
    this.caseSensitive = true;
    return this;
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
  public LongTypeParserBuilderImpl caseInsensitive() {
    this.caseSensitive = false;
    return this;
  }

  @SuppressWarnings({"java:S3012"}) // The suggested SonarCloud fixes all copy arrays of the same primitive type – this method does not.
  private static int[] convertCharArrayToCodePointArray(final char[] characters) {
    final int[] codePoints = new int[characters.length];
    for (int i = 0; i < characters.length; ++i) {
      codePoints[i] = characters[i];
    }
    return codePoints;
  }

  public LongTypeParserBuilderImpl allowCustomBaseNumbers(final char... charactersForCustomNumericBase) {
    if (charactersForCustomNumericBase == null) {
      allowCustomBaseNumbers((int[]) null);
    } else {
      allowCustomBaseNumbers(convertCharArrayToCodePointArray(charactersForCustomNumericBase));
    }
    return this;
  }

  public LongTypeParserBuilderImpl allowCustomBaseNumbers(final int... codePointsForCustomNumericBase) {
    if (codePointsForCustomNumericBase == null) {
      numericRadixCodePoints = Constants.EMPTY_INT_ARRAY;
    } else {
      numericRadixCodePoints = codePointsForCustomNumericBase;
    }
    return this;
  }

  /**
   * <p>Will accept the following digits for base-8 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7
   * }</pre>
   */
  public LongTypeParserBuilderImpl allowBase8Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_8_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-10 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
   * }</pre>
   */
  public LongTypeParserBuilderImpl allowBase10Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_10_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-16 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
   * }</pre>
   */
  public LongTypeParserBuilderImpl allowBase16Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_16_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-32 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V
   * }</pre>
   */
  public LongTypeParserBuilderImpl allowBase32Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_32_CODE_POINTS);
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
  public LongTypeParserBuilderImpl allowBase36Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_36_CODE_POINTS);
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
  public LongTypeParserBuilderImpl allowBase62Numbers() {
    return allowCustomBaseNumbers(DEFAULT_BASE_62_CODE_POINTS);
  }

  public LongTypeParserBuilderImpl allowLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = false;
    return this;
  }

  public LongTypeParserBuilderImpl ignoreLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = true;
    return this;
  }

  public LongTypeParserBuilderImpl allowLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = false;
    return this;
  }

  public LongTypeParserBuilderImpl ignoreLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = true;
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfChar(final char ch) {
    ignoreCharactersSubsetBuilder.includeChar(ch);
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfChars(final char... chars) {
    ignoreCharactersSubsetBuilder.includeChars(chars);
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfCodePoint(final int codePoint) {
    ignoreCharactersSubsetBuilder.includeCodePoint(codePoint);
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfCodePoints(final int... codePoints) {
    ignoreCharactersSubsetBuilder.includeCodePoints(codePoints);
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllWhitespace() {
    whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
    return this;
  }

  public LongTypeParserBuilderImpl ignoreAllDashesAndHyphens() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
    // including the math minus sign (U+2212) and the heavy minus sign (U+2796) in the ignore set
    ignoreCharactersSubsetBuilder.includeCodePoints(MATH_MINUS, HEAVY_MINUS);
    return ignoreLeadingNegativeSign();
  }

  public LongTypeParserBuilderImpl ignoreAllDashesAndHyphensExceptLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
    // excluding the minus sign (U+2212) and the heavy minus sign (U+2796) from the ignore set
    ignoreCharactersSubsetBuilder.excludeCodePoints(MATH_MINUS, HEAVY_MINUS);
    return allowLeadingNegativeSign();
  }

}
