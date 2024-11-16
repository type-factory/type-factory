package org.typefactory.impl;

import static java.util.Objects.requireNonNullElse;
import static org.typefactory.impl.Constants.ARABIC_LETTER_MARK;
import static org.typefactory.impl.Constants.HEAVY_MINUS;
import static org.typefactory.impl.Constants.LEFT_TO_RIGHT_INDICATOR;
import static org.typefactory.impl.Constants.MATH_MINUS;
import static org.typefactory.impl.Constants.MINUS_CODEPOINTS;
import static org.typefactory.impl.Constants.PLUS_CODEPOINTS;
import static org.typefactory.impl.Constants.RIGHT_TO_LEFT_INDICATOR;
import static org.typefactory.impl.LongTypeParserImpl.WhiteSpace.FORBID_WHITESPACE;
import static org.typefactory.impl.LongTypeParserImpl.WhiteSpace.IGNORE_WHITESPACE;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Locale;
import org.typefactory.Category;
import org.typefactory.LongTypeParser.LongTypeParserBuilder;
import org.typefactory.MessageCode;
import org.typefactory.NumberFormat;
import org.typefactory.NumberFormat.NumberFormatBuilder;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.TypeParserBuilderException.MessageCodes;
import org.typefactory.impl.LongTypeParserImpl.WhiteSpace;

final class LongTypeParserBuilderImpl implements LongTypeParserBuilder {

  private static final int[] DEFAULT_BASE_2_CODE_POINTS = new int[]{
      '0', '1'};
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
  private final SubsetBuilder ignoreCharactersSubsetBuilder = Subset.builder();
  private Class<?> targetTypeClass;
  private MessageCode messageCode;
  private Locale defaultLocale = Locale.getDefault();
  private NumberFormatBuilder defaultNumberFormatBuilder = NumberFormat.builder(defaultLocale);
  private boolean caseSensitive = true;
  private WhiteSpace whiteSpace = IGNORE_WHITESPACE;
  private int[] numericRadixCodePoints = null;
  private long minValue;
  private long maxValue;
  private boolean minValueComparisonInclusive = true;
  private boolean maxValueComparisonInclusive = true;
  private boolean ignoreLeadingNegativeSign = false;
  private boolean ignoreLeadingPositiveSign = false;
  private boolean enableGrouping = true;

  LongTypeParserBuilderImpl() {
    this(Long.MIN_VALUE, Long.MAX_VALUE);
  }

  LongTypeParserBuilderImpl(final long defaultMinValue, final long defaultMaxValue) {
    this.minValue = defaultMinValue;
    this.maxValue = defaultMaxValue;
  }

  private static void addCodePointToRadixCodePointsMap(
      final PrimitiveHashMapOfIntKeyToIntValue.PrimitiveHashMapOfIntKeyToIntValueBuilder numericRadixCodePointsMapBuilder,
      final int codepoint,
      final int numericValue) {

    if (numericRadixCodePointsMapBuilder.contains(codepoint)) {
      throw TypeParserBuilderException.builder()
          .messageCode(MessageCodes.DUPLICATE_CUSTOM_RADIX_CHARACTER_EXCEPTION_MESSAGE)
          .build();
    }
    numericRadixCodePointsMapBuilder.put(codepoint, numericValue);
  }

  @SuppressWarnings({"java:S3012"}) // The suggested SonarCloud fixes all copy arrays of the same primitive type – this method does not.
  private static int[] convertCharArrayToCodePointArray(final char[] characters) {
    final int[] codePoints = new int[characters.length];
    for (int i = 0; i < characters.length; ++i) {
      codePoints[i] = characters[i];
    }
    return codePoints;
  }

  public LongTypeParserImpl build() {

    final var numberFormat = defaultNumberFormatBuilder.build();

    initialiseNumericRadixCodePointsIfNotSupplied(numberFormat);

    final var numericRadixCodePointsMap = createNumericRadixCodePointsMap();
    final boolean allowAllUnicodeDecimalDigits = canAllowAllUnicodeDecimalDigits();

    ignoreCharactersSubsetBuilder.includeCodePoints(ARABIC_LETTER_MARK, LEFT_TO_RIGHT_INDICATOR, RIGHT_TO_LEFT_INDICATOR);

    return new LongTypeParserImpl(
        targetTypeClass, messageCode,
        numberFormat,
        whiteSpace,
        ignoreCharactersSubsetBuilder.build(),
        numericRadixCodePointsMap, numericRadixCodePoints,
        allowAllUnicodeDecimalDigits,
        minValue, maxValue,
        minValueComparisonInclusive, maxValueComparisonInclusive,
        ignoreLeadingNegativeSign, ignoreLeadingPositiveSign);
  }

  private void initialiseNumericRadixCodePointsIfNotSupplied(NumberFormat numberFormat) {
    if (numericRadixCodePoints == null) {
      final int zeroDigitCodePoint = numberFormat.getZeroDigit();
      numericRadixCodePoints = new int[10];
      for (int i = 0; i < 10; ++i) {
        numericRadixCodePoints[i] = zeroDigitCodePoint + i;
      }
    }
  }

  /**
   * <p>Check if the numeric radix code points use Unicode decimal digits for the first characters up to the radix where the radix is less than ten,
   * otherwise up to ten.</p>
   *
   * @return true if the numeric radix code points use Unicode decimal digits for the first characters up to the radix where the radix is less than
   * ten, otherwise up to ten.
   */
  private boolean canAllowAllUnicodeDecimalDigits() {
    if (Arrays.equals(numericRadixCodePoints, DEFAULT_BASE_10_CODE_POINTS)) {
      return true;
    }
    for (int i = 0; i < 10 && i < numericRadixCodePoints.length; ++i) {
      if (i != Character.digit(numericRadixCodePoints[i], numericRadixCodePoints.length)) {
        return false;
      }
    }
    return true;
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
    final var numericRadixCodePointsMapBuilder = PrimitiveHashMapOfIntKeyToIntValue.builder();
    for (int i = 0; i < numericRadixCodePoints.length; ++i) {
      final int codepoint = numericRadixCodePoints[i];
      addCodePointToRadixCodePointsMap(numericRadixCodePointsMapBuilder, codepoint, i);
      if (!caseSensitive) { // case-insensitive
        // Upper and lower case characters in Unicode are not always symmetric, so we will consider each separately and individually
        final int lowerCaseCodepoint = Character.toLowerCase(codepoint);
        if (lowerCaseCodepoint != codepoint) {
          addCodePointToRadixCodePointsMap(numericRadixCodePointsMapBuilder, lowerCaseCodepoint, i);
        }
        final int upperCaseCodepoint = Character.toUpperCase(codepoint);
        if (upperCaseCodepoint != codepoint) {
          addCodePointToRadixCodePointsMap(numericRadixCodePointsMapBuilder, upperCaseCodepoint, i);
        }
      }
    }
    return numericRadixCodePointsMapBuilder.build();
  }

  @Override
  public LongTypeParserBuilderImpl targetTypeClass(final Class<?> targetTypeClass) {
    this.targetTypeClass = targetTypeClass;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl messageCode(final MessageCode messageCode) {
    this.messageCode = messageCode;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl defaultLocale(final Locale locale) {
    defaultLocale = locale == null ? Locale.getDefault() : locale;
    defaultNumberFormatBuilder = NumberFormat.builder(defaultLocale);
    return this;
  }

  @Override
  public LongTypeParserBuilder roundingMode(final RoundingMode roundingMode) {
    defaultNumberFormatBuilder.roundingMode(roundingMode);
    return this;
  }

  @Override
  public LongTypeParserBuilder decimalSeparator(final char decimalSeparator, final char... alternativeDecimalSeparators) {
    defaultNumberFormatBuilder.decimalSeparator(decimalSeparator, alternativeDecimalSeparators);
    return this;
  }

  @Override
  public LongTypeParserBuilder decimalSeparator(final int decimalSeparator, final int... alternativeDecimalSeparators ) {
    defaultNumberFormatBuilder.decimalSeparator(decimalSeparator, alternativeDecimalSeparators);
    return this;
  }

  @Override
  public LongTypeParserBuilder groupingSeparator(final char groupingSeparator, final char... alternativeGroupingSeparators) {
    defaultNumberFormatBuilder.groupingSeparator(groupingSeparator, alternativeGroupingSeparators);
    return this;
  }

  @Override
  public LongTypeParserBuilder groupingSeparator(final int groupingSeparator, final int... alternativeGroupingSeparators ) {
    defaultNumberFormatBuilder.groupingSeparator(groupingSeparator, alternativeGroupingSeparators);
    return this;
  }

  @Override
  public LongTypeParserBuilder enableGrouping() {
    enableGrouping = true;
    return this;
  }

  @Override
  public LongTypeParserBuilder disableGrouping() {
    enableGrouping = false;
    return this;
  }

  @Override
  public LongTypeParserBuilder zeroDigit(final char zeroDigit) {
    defaultNumberFormatBuilder.zeroDigit(zeroDigit);
    return this;
  }

  @Override
  public LongTypeParserBuilder zeroDigit(final int zeroDigit) {
    defaultNumberFormatBuilder.zeroDigit(zeroDigit);
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

  @Override
  public LongTypeParserBuilderImpl minValueInclusive(final long minValue) {
    return minValue(minValue, true);
  }

  @Override
  public LongTypeParserBuilderImpl maxValueInclusive(final long maxValue) {
    return maxValue(maxValue, true);
  }

  @Override
  public LongTypeParserBuilderImpl minValueExclusive(final long minValue) {
    return minValue(minValue, false);
  }

  @Override
  public LongTypeParserBuilderImpl maxValueExclusive(final long maxValue) {
    return maxValue(maxValue, false);
  }

  @Override
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
   * to define a type parser for an arbitrary radix and specify your own set of digits/letters using one of the
   * {@link #setCustomRadix(int...)} methods. In this situation, the type parser will always consider the letters you specified as they were
   * provided. It will then also consider the lowercase version and uppercase version of that letter if case-insensitivity has been configured.</p>
   *
   * <p>One example of letter case asymmetry occurs in the Greek alphabet where:</p>
   * <ul>
   *   <li>{@code Character.toUpperCase('σ') → 'Σ'} — a lowercase non-final Greek Sigma 'σ' is always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toUpperCase('ς') → 'Σ'} — a lowercase final Greek Sigma 'ς' is also always converted to an uppercase capital Greek Sigma 'Σ'</li>
   *   <li>{@code Character.toLowerCase('Σ') → 'σ'} — an uppercase Greek Sigma 'Σ' is always converted to a lowercase non-final Greek Sigma 'σ'</li>
   * </ul>
   */
  @Override
  public LongTypeParserBuilderImpl caseInsensitive() {
    this.caseSensitive = false;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl setCustomRadix(final char... charactersForCustomNumericBase) {
    if (charactersForCustomNumericBase == null || charactersForCustomNumericBase.length < 2) {
      throw TypeParserBuilderException.builder()
          .messageCode(MessageCodes.INVALID_CUSTOM_RADIX_EXCEPTION_MESSAGE)
          .build();
    }
    setCustomRadix(convertCharArrayToCodePointArray(charactersForCustomNumericBase));
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl setCustomRadix(final int... codePointsForCustomNumericBase) {
    if (codePointsForCustomNumericBase == null || codePointsForCustomNumericBase.length < 2) {
      throw TypeParserBuilderException.builder()
          .messageCode(MessageCodes.INVALID_CUSTOM_RADIX_EXCEPTION_MESSAGE)
          .build();
    }

    numericRadixCodePoints = requireNonNullElse(codePointsForCustomNumericBase, Constants.EMPTY_INT_ARRAY);
    return this;
  }

  @Override
  public LongTypeParserBuilder setRadixBinary() {
      return setCustomRadix(DEFAULT_BASE_2_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-8 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7
   * }</pre>
   */
  @Override
  public LongTypeParserBuilderImpl setRadixOctal() {
    return setCustomRadix(DEFAULT_BASE_8_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-10 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
   * }</pre>
   */
  @Override
  public LongTypeParserBuilderImpl setRadixDecimal() {
    return setCustomRadix(DEFAULT_BASE_10_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-16 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
   * }</pre>
   */
  @Override
  public LongTypeParserBuilderImpl allowBase16Numbers() {
    return caseInsensitive().setCustomRadix(DEFAULT_BASE_16_CODE_POINTS);
  }

  /**
   * <p>Will accept the following digits for base-32 numbers:</p>
   *
   * <pre>{@code
   * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
   * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V
   * }</pre>
   */
  @Override
  public LongTypeParserBuilderImpl allowBase32Numbers() {
    return caseInsensitive().setCustomRadix(DEFAULT_BASE_32_CODE_POINTS);
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
  @Override
  public LongTypeParserBuilderImpl allowBase36Numbers() {
    return caseInsensitive().setCustomRadix(DEFAULT_BASE_36_CODE_POINTS);
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
  @Override
  public LongTypeParserBuilderImpl allowBase62Numbers() {
    return caseSensitive().setCustomRadix(DEFAULT_BASE_62_CODE_POINTS);
  }

  @Override
  public LongTypeParserBuilderImpl allowLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = false;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreLeadingNegativeSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(MINUS_CODEPOINTS);
    ignoreLeadingNegativeSign = true;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl allowLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.excludeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = false;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreLeadingPositiveSign() {
    ignoreCharactersSubsetBuilder.includeCodePoints(PLUS_CODEPOINTS);
    ignoreLeadingPositiveSign = true;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfChar(final char ch) {
    ignoreCharactersSubsetBuilder.includeChar(ch);
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfChars(final char... chars) {
    ignoreCharactersSubsetBuilder.includeChars(chars);
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfCodePoint(final int codePoint) {
    ignoreCharactersSubsetBuilder.includeCodePoint(codePoint);
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllOccurrencesOfCodePoints(final int... codePoints) {
    ignoreCharactersSubsetBuilder.includeCodePoints(codePoints);
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllWhitespace() {
    whiteSpace = IGNORE_WHITESPACE;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl forbidWhitespace() {
    whiteSpace = FORBID_WHITESPACE;
    return this;
  }

  @Override
  public LongTypeParserBuilderImpl ignoreAllDashesAndHyphens() {
    ignoreCharactersSubsetBuilder.includeUnicodeCategory(Category.DASH_PUNCTUATION);
    // including the math minus sign (U+2212) and the heavy minus sign (U+2796) in the ignore set
    ignoreCharactersSubsetBuilder.includeCodePoints(MATH_MINUS, HEAVY_MINUS);
    return ignoreLeadingNegativeSign();
  }
}
