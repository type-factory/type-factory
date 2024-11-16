package org.typefactory;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.impl.Factory;

/**
 * <p>Interface for a type-parser that will create a custom {@link LongType} instance either using
 * a provided numeric value, or by parsing an input {@link CharSequence}, cleaning it up if required, and validating that the provided value conforms
 * to configured rules.</p>
 *
 * <p>Type-parsers created with the {@link LongTypeParser#builder()} are immutable and thread-safe.</p>
 *
 * <p>It is recommended that you create a type-parser once and reuse it wherever required.
 * They are considered expensive to create but cheap to use and reuse. Type-parsers created with {@link LongTypeParser#builder()} method have been
 * designed to do their job efficiently with minimal object creation.</p>
 *
 * <p><b>Example 1 – type-parser for a 9-digit invoice-number that will ignore any whitespace, hyphens or dashes.</b></p>
 * <pre>{@code
 * static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()
 *     .messageCode(MessageCode.of("invalid.invoice.number", "must be a 9-digit invoice-number"))
 *     .minValueInclusive(100_000_000L)
 *     .maxValueInclusive(999_999_999L)
 *     .ignoreAllDashesAndHyphens()
 *     .ignoreAllWhitespace()
 *     .build();
 * }</pre>
 *
 * @see #builder()
 */
public interface LongTypeParser extends NumericTypeParser<Long, LongType> {

  /**
   * <p>Creates a builder with which to create a type-parser for long numeric values.</p>
   *
   * @return a builder with which to create a type-parser for long numeric values.
   */
  static LongTypeParserBuilder builder() {
    return Factory.longTypeParserBuilder();
  }

  /**
   * <p>Returns the value of the given {@link Short} value as a {@link Long} value after verifying that it conforms to all the type
   * parser rules. This method is null-safe, returning {@code null} if the provided {@code value} was null.</p>
   *
   * @param value the short value to be validated by the type parser.
   * @return the value of the given {@link Short} value as a {@link Long} value, or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  Long of(Short value) throws InvalidValueException;

  /**
   * <p>Returns the value of the given {@link Integer} value as a {@link Long} value after verifying that it conforms to all the type
   * parser rules. This method is null-safe, returning {@code null} if the provided {@code value} was null.</p>
   *
   * @param value the integer value to be validated by the type parser.
   * @return the value of the given {@link Integer} value as a {@link Long} value, or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  Long of(Integer value) throws InvalidValueException;

  /**
   * <p>Returns the same instance of the value (no re-boxing) after verifying that it conforms to all the type
   * parser rules. This method is null-safe, returning {@code null} if the provided {@code value} was null.</p>
   *
   * @param value the long value to be validated by the type parser.
   * @return the same instance of the value (no re-boxing), or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  Long of(Long value) throws InvalidValueException;

  /**
   * <p>Returns the value of the given {@link BigInteger} value as a {@link Long} value after verifying that it conforms to all the type
   * parser rules. This method is null-safe, returning {@code null} if the provided {@code value} was null.</p>
   *
   * @param value the {@link BigInteger} value to be validated by the type parser.
   * @return the value of the given {@link BigInteger} value as a {@link Long} value, or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  Long of(BigInteger value) throws InvalidValueException;

  /**
   * <p>Returns the value of the given {@code short} value as a {@code long} value after verifying that it conforms to all the type
   * parser rules.</p>
   *
   * @param value the short value to be validated by the type parser.
   * @return the value of the given {@code short} value as a {@code long} value.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  long of(final short value) throws InvalidValueException;

  /**
   * <p>Returns the value of the given {@code int} value as a {@code long} value after verifying that it conforms to all the type
   * parser rules.</p>
   *
   * @param value the int value to be validated by the type parser.
   * @return the value of the given {@code int} value as a {@code long} value.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  long of(final int value) throws InvalidValueException;

  /**
   * <p>Returns the same {@code long} value after verifying that it conforms to all the type parser rules.</p>
   *
   * @param value the long value to be validated by the type parser.
   * @return the same {@code long} value.
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  long of(final long value) throws InvalidValueException;

  /**
   * Returns the value of the given short value as an instance of {@code S}, a subclass of {@link LongType}, after verifying that it conforms to all
   * the type parser rules.
   *
   * <p><b>Example – create a custom type extending {@link LongType}</b></p>
   *
   * <p>The {@code ProductId} type below provides overloaded factory {@code 'of'} methods which use the type-parser. The first {@code of} method
   * takes a short value and verifies it conforms to all the type parser rules and returns a newly created the {@code ProductId} instance. The second
   * {@code of} method does something similar by first parsing a char-sequence into a long value.</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final Short value) {
   *     return TYPE_PARSER.of(value, ProductId::new);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the short value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type {@code S}, a subclass of
   *                                   {@link LongType}.
   * @param <S>                        the subclass type of the {@link LongType} instance to be created.
   * @return the value of the given short value as an instance of the type {@code S}, a subclass of {@link LongType}
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  <S extends LongType> S of(
      Short value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given integer value as an instance of {@code S}, a subclass of {@link LongType}, after verifying that it conforms to all
   * the type parser rules.
   *
   * <p><b>Example – create a custom type extending {@link LongType}</b></p>
   *
   * <p>The {@code ProductId} type below provides overloaded factory {@code 'of'} methods which use the type-parser. The first {@code of} method
   * takes an integer value and verifies it conforms to all the type parser rules and returns a newly created the {@code ProductId} instance. The
   * second {@code of} method does something similar by first parsing a char-sequence into a long value.</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final Integer value) {
   *     return TYPE_PARSER.of(value, ProductId::new);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the integer value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type {@code S}, a subclass of
   *                                   {@link LongType}.
   * @param <S>                        the subclass type of the {@link LongType} instance to be created.
   * @return the value of the given int value as an instance of the type {@code S}, a subclass of {@link LongType}
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  <S extends LongType> S of(
      Integer value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given long value as an instance of {@code S}, a subclass of {@link LongType}, after verifying that it conforms to all
   * the type parser rules.
   *
   * <p><b>Example – create a custom type extending {@link LongType}</b></p>
   *
   * <p>The {@code ProductId} type below provides overloaded factory {@code 'of'} methods which use the type-parser. The first {@code of} method
   * takes a long value and verifies it conforms to all the type parser rules and returns a newly created the {@code ProductId} instance. The second
   * {@code of} method does something similar by first parsing a char-sequence into a long value.</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final Long value) {
   *     return TYPE_PARSER.of(value, ProductId::new);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the long value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type {@code S}, a subclass of
   *                                   {@link LongType}.
   * @param <S>                        the subclass type of the {@link LongType} instance to be created.
   * @return the value of the given long value as an instance of the type {@code S}, a subclass of {@link LongType}
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  <S extends LongType> S of(
      Long value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given {@link BigInteger} value as an instance of {@code S}, a subclass of {@link LongType}, after verifying that it
   * conforms to all the type parser rules.
   *
   * <p><b>Example – create a custom type extending {@link LongType}</b></p>
   *
   * <p>The {@code ProductId} type below provides overloaded factory {@code 'of'} methods which use the type-parser. The first {@code of} method
   * takes a {@link BigInteger} value and verifies it conforms to all the type parser rules and returns a newly created the {@code ProductId}
   * instance. The second {@code of} method does something similar by first parsing a char-sequence into a long value.</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final BigInteger value) {
   *     return TYPE_PARSER.of(value, ProductId::new);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the {@link BigInteger} value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type {@code S}, a subclass of
   *                                   {@link LongType}.
   * @param <S>                        the subclass type of the {@link LongType} instance to be created.
   * @return the value of the given {@link BigInteger} value as an instance of the type {@code S}, a subclass of {@link LongType}
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'LongFunction<S>'
  <S extends LongType> S of(
      BigInteger value,
      // Using Function<Long, S> instead of LongFunction<S> to allow for method references that accept a Long value that may be null.
      Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Long} value. This method is null-safe,
   * returning {@code null} if the provided {@code value} was null.</p>
   *
   * <p><b>Example – parse to a {@code Long} instance</b></p>
   * <pre>{@code
   * static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   * Long someVariable = TYPE_PARSER.parse(value);
   * }</pre>
   *
   * @param value the string or {@link CharSequence} value parse into a {@link Long}.
   * @return the provided {@code value} parsed into a {@link Long} value, or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Long}.
   * @see #parse(CharSequence, Locale)
   * @see #parse(CharSequence, Function)
   * @see #parse(CharSequence, Locale, Function)
   */
  @Override
  Long parse(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Long} value using the provided {@code locale}. This method is null-safe,
   * returning {@code null} if the provided {@code value} was null.</p>
   *
   * <p><b>Example – parse to a {@code Long} instance using a specific {@code Locale}</b></p>
   * <pre>{@code
   * static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   * static final Locale someLocale = ...;
   *
   * Long someVariable = TYPE_PARSER.parse(value, someLocale);
   * }</pre>
   *
   * @param value  the string or {@link CharSequence} value to parse into a {@link Long}.
   * @param locale the locale to use when parsing the provided {@code value}. This allows the parser to cater for locale specific grouping separators
   *               (e.g. thousands separators).
   * @return the provided {@code value} parsed into a {@link Long} value, or {@code null} if the provided {@code value} was null.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Long}.
   * @see #parse(CharSequence)
   * @see #parse(CharSequence, Function)
   * @see #parse(CharSequence, Locale, Function)
   */
  @Override
  Long parse(CharSequence value, final Locale locale) throws InvalidValueException;

  @Override
  Long parse(CharSequence value, final NumberFormat numberFormat) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into an instance of {@code S}, a subclass of {@link LongType}. This method is null-safe,
   * returning {@code null} if the provided {@code value} was null, empty or blank.</p>
   *
   * <p><b>Example – parse to a custom type extending {@link LongType}</b></p>
   * <p>The {@code ProductId} type below provides a factory {@code 'of'} method which uses the type-parser to parse the provided {@code value}
   * and create the {@code ProductId} instance:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the string or {@link CharSequence} value to parse into a {@link LongType}.
   * @param constructorOrFactoryMethod a constructor or factory method that will be used to create the instance of the type {@code S}, a subclass of.
   * @param <S>                        the subclass type of the {@link LongType} instance that will be created.
   * @return the provided {@code value} parsed into an instance of the type {@code S}, a subclass of {@link LongType}, or {@code null} if the provided
   * {@code value} was null, empty or blank.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed into an instance of the type {@code S}, a subclass of
   *                               {@link LongType}.
   * @see #parse(CharSequence)
   * @see #parse(CharSequence, Locale)
   * @see #parse(CharSequence, Locale, Function)
   */
  @Override
  <S extends LongType> S parse(CharSequence value, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into an instance of {@code S}, a subclass of {@link LongType}, using the provided {@code locale}.
   * This method is null-safe, returning {@code null} if the provided {@code value} was null, empty or blank.</p>
   *
   * <p><b>Example – parse to a custom type extending {@link LongType} using a specific {@code Locale}</b></p>
   * <p>The {@code ProductId} type below provides overloaded factory {@code 'of'} methods which use the type-parser to parse the provided
   * {@code value} and create the {@code ProductId} instance, with the second {@code 'of'} method accepting {@link Locale} to ensure that
   * locale-specific formats are considered when parsing:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   static final LongTypeParser TYPE_PARSER = LongTypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parse(value, ProductId::new);
   *   }
   *
   *   public static ProductId of(final CharSequence value, final Locale locale) {
   *     return TYPE_PARSER.parse(value, locale, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * @param value                      the string or {@link CharSequence} value to parse into a {@link Long}.
   * @param locale                     the locale to use when parsing the provided {@code value}. This allows the parser to cater for locale specific
   *                                   grouping separators (e.g. thousands separators).
   * @param constructorOrFactoryMethod a constructor or factory method that will be used to create the instance of the type {@code S}, a subclass of
   *                                   {@link LongType}.
   * @param <S>                        the subclass type of the {@link LongType} instance to be created.
   * @return the provided {@code value} parsed into a {@link Long} value.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Long}.
   * @see #parse(CharSequence)
   * @see #parse(CharSequence, Locale)
   * @see #parse(CharSequence, Locale, Function)
   */
  @Override
  <S extends LongType> S parse(CharSequence value, final Locale locale, Function<Long, S> constructorOrFactoryMethod) throws InvalidValueException;

  <S extends LongType> S parse(CharSequence value, final NumberFormat numberFormat, Function<Long, S> constructorOrFactoryMethod)
      throws InvalidValueException;

  /**
   * Returns the radix (numeric-base) of the type-parser which will be greater-than or equal to 2.
   *
   * @return the radix (numeric-base) of the type-parser.
   */
  @Override
  int getRadix();

  /**
   * The interface for a builder that can create a {@link LongTypeParser} instance.
   */
  interface LongTypeParserBuilder {

    /**
     * <p>Builds the {@link LongTypeParser} instance using the configuration provided to this builder.</p>
     *
     * <p>Type-parsers created with this method are immutable and thread-safe.</p>
     *
     * <p>It is recommended that you create a type-parser once and reuse it wherever required.
     * They are consider expensive to create but cheap to reuse.</p>
     *
     * @return a new {@link LongTypeParser} instance.
     */
    LongTypeParser build();

    /**
     * <p>Optionally set the target custom-type class that the type-parser will parse values into. If provided, this information will be added to
     * {@link InvalidValueException} instances to capture better information about which custom-type the parsing failed for.</p>
     *
     * @param targetTypeClass the target custom-type class that the type-parser will parse values into.
     * @return this builder
     */
    LongTypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    /**
     * <p>Set a {@link MessageCode} containing a code and default message to be returned in the {@link InvalidValueException} if the
     * type-parser cannot parse a provided value.</p>
     *
     * <p>Error messages can be localized by providing one or more Java resource bundles with a base name
     * of {@code 'org.typefactory.Messages'}. The resource bundles can be either:</p>
     * <ul>
     *   <li>Java properties files.</li>
     *   <li>Java classes that implement {@link java.util.ResourceBundle}.</li>
     * </ul>
     *
     * <p>For example, the following are valid examples of localization properties files and classes for providing localized error messages:</p>
     * <pre>
     *   org/typefactory/Messages.properties
     *   org/typefactory/Messages_en.properties
     *   org/typefactory/Messages_en_US.properties
     *   class org.typefactory.Messages_fr extends java.util.ListResourceBundle
     *   class org.typefactory.Messages_fr_CA extends java.util.ListResourceBundle
     * </pre>
     *
     * @param messageCode a {@link MessageCode} instance containing a code and default message.
     * @return this builder
     * @see java.util.ListResourceBundle
     * @see java.util.ResourceBundle
     */
    LongTypeParserBuilder messageCode(final MessageCode messageCode);

    /**
     * <p>Provide a default {@link Locale} which will be used when parsing {@link CharSequence} values to
     * correctly interpret grouping separators, also known as thousands separators or ten-of-thousands separators depending on the locale.</p>
     *
     * <p>Setting this will override any values previously set by:</p>
     * <ul>
     *   <li></li>
     * </ul>
     *
     * @param locale a default {@link Locale} which will be used when parsing {@link CharSequence} values. If a null {@code locale} value is provided
     *               then the locale will be set to {@link Locale#getDefault()}.
     * @return this builder
     */
    LongTypeParserBuilder defaultLocale(Locale locale);

    LongTypeParserBuilder roundingMode(final RoundingMode roundingMode);

    LongTypeParserBuilder decimalSeparator(final char decimalSeparator, char... alternativeDecimalSeparators);

    LongTypeParserBuilder decimalSeparator(final int decimalSeparator, int... alternativeDecimalSeparators);

    LongTypeParserBuilder groupingSeparator(final char groupingSeparator, char... alternativeGroupingSeparators);

    LongTypeParserBuilder groupingSeparator(final int groupingSeparator, int... alternativeGroupingSeparators);

    /**
     * <p>Only used when formating value – determines whether grouping separators will be added to formatted numbers.</p>
     *
     * <p>Ignored when parsing values from a char-sequence – grouping separators will always be parsed if present.</p>
     *
     * @return this builder
     */
    LongTypeParserBuilder enableGrouping();

    /**
     * <p>Only used when formating value – determines whether grouping separators will be added to formatted numbers.</p>
     *
     * <p>Ignored when parsing values from a char-sequence – grouping separators will always be parsed if present.</p>
     *
     * @return this builder
     */
    LongTypeParserBuilder disableGrouping();

    /**
     * <p>Set the character used for zero. Can be different for different scripts. For example, the Arabic numeral system uses the Arabic-Indic digits
     * ٠١٢٣٤٥٦٧٨٩.</p>
     *
     * <p>This value is only considered if custom-radix characters have not been supplied with {@link #setCustomRadix(char...)}.</p>
     *
     * <p>This value will override any zero digit previously set by calling {@link #defaultLocale(Locale)}.</p>
     *
     * <p>The zero-digit character and subsequent nine characters must be in the Unicode decimal digit category. These return {@code true} when tested
     * with {@link Character#isDigit(char)}.</p>
     *
     * @param zeroDigit the character used for zero
     * @return this builder
     * @see #setCustomRadix(char...)
     * @see #setCustomRadix(int...)
     * @see Character#isDigit(char)
     * @see Character#isDigit(int)
     */
    LongTypeParserBuilder zeroDigit(final char zeroDigit) throws TypeParserBuilderException;

    /**
     * <p>Set the code-point used for zero. Can be different for different scripts. For example, the Arabic numeral system uses the Arabic-Indic digits
     * ٠١٢٣٤٥٦٧٨٩.</p>
     *
     * <p>This value is only considered if custom radix code-points have not been supplied with {@link #setCustomRadix(int...)}.</p>
     *
     * <p>This value will override any zero digit previously set by calling {@link #defaultLocale(Locale)}.</p>
     *
     * <p>The zero-digit code-point and subsequent nine code-points must be in the Unicode decimal digit category. These return {@code true} when tested
     * with {@link Character#isDigit(int)}.</p>
     *
     * @param zeroDigit the character used for zero
     * @return this builder
     * @see #setCustomRadix(char...)
     * @see #setCustomRadix(int...)
     * @see Character#isDigit(char)
     * @see Character#isDigit(int)
     */
    LongTypeParserBuilder zeroDigit(final int zeroDigit) throws TypeParserBuilderException;

    /**
     * Set a minimum value that will be used to validate values are withing a range. Calling this will replace any previously set inclusive or
     * exclusive minimum value.
     *
     * @param minValue the minimum inclusive value allowed.
     * @return this builder
     * @see #maxValueInclusive(long)
     * @see #minValueExclusive(long)
     * @see #maxValueExclusive(long)
     */
    LongTypeParserBuilder minValueInclusive(long minValue);

    /**
     * Set a maximum value that will be used to validate values are withing a range. Calling this will replace any previously set inclusive or
     * exclusive maximum value.
     *
     * @param maxValue the maximum inclusive value allowed.
     * @return this builder
     * @see #minValueInclusive(long)
     * @see #minValueExclusive(long)
     * @see #maxValueExclusive(long)
     */
    LongTypeParserBuilder maxValueInclusive(long maxValue);

    /**
     * Set a minimum value that will be used to validate values are withing a range. Calling this will replace any previously set inclusive or
     * exclusive minimum value.
     *
     * @param minValue the minimum exclusive value allowed.
     * @return this builder
     * @see #minValueExclusive(long)
     * @see #minValueInclusive(long)
     * @see #maxValueInclusive(long)
     */
    LongTypeParserBuilder minValueExclusive(long minValue);

    /**
     * Set a maximum value that will be used to validate values are withing a range. Calling this will replace any previously set inclusive or
     * exclusive maximum value.
     *
     * @param maxValue the maximum exclusive value allowed.
     * @return this builder
     * @see #minValueExclusive(long)
     * @see #minValueInclusive(long)
     * @see #maxValueInclusive(long)
     */
    LongTypeParserBuilder maxValueExclusive(long maxValue);

    /**
     * When parsing numbers with a numeric base (radix) larger than 10, the non-decimal characters that are also valid ‘digits’ will be parsed in a
     * case-sensitive fashion. This would be important, for example, for base-62 numbers using the digits and letters 0-9A-Za-z where the upper and
     * lower case letters represent different numeric values.
     *
     * @return this builder
     * @see #caseInsensitive()
     */
    LongTypeParserBuilder caseSensitive();

    /**
     * When parsing numbers with a base (radix) larger than 10, the non-decimal characters that are also valid ‘digits’ will be parsed in a
     * case-insensitive fashion. This would be important, for example, for base-16 or base-36 numbers that use the digits and letters 0-9A-Fa-f and
     * 0-9A-Za-z respectively, if you would like to accept both upper and lower case letters as the same numeric values.
     *
     * @return this builder
     * @see #caseSensitive()
     */
    LongTypeParserBuilder caseInsensitive();

    /**
     * <p>Configures the type-parser to parse a number using the provided characters as the ‘digits’.
     * The base (or radix) of the number will be determined by the number of characters you provide. For example, if you wanted to parse base-20
     * numbers you may consider configuring the parser with:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J')
     *   .build()
     * }</pre>
     *
     * <p>There is no restriction on which Unicode characters you may want to use.
     * You could consider using characters from any alphabet. For example, for a {@code ProductCode} type in Greece you may choose to have the product
     * codes written as base-34 numbers using the Greek alphabet:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'I', 'Κ',
     *       'Λ', 'Μ', 'Ν', 'Ξ', 'O', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ',
     *       'Φ', 'Χ', 'Ψ', 'Ω')
     *   .build()
     * }</pre>
     *
     * <p>When creating such custom numeric bases, consider that some letters characters may
     * be best omitted if they are easily confused with numbers, for example the number 0 and letter O, or the number 1 and the letter I, which can be
     * hard to distinguish when displayed in certain fonts.</p>
     *
     * <p>It is also be possible to provide a set of characters that are Unicode symbols
     * instead of digits or letters. There is no restriction.</p>
     *
     * @param charactersForCustomNumericBase the characters that you wish to use to represent numbers of any base or radix. The first character will
     *                                       map to zero and all the subsequent characters to the numeric values that follow.
     * @return this builder
     * @throws TypeParserBuilderException if less than two characters are provided.
     * @see #setCustomRadix(int...)
     */
    LongTypeParserBuilder setCustomRadix(char... charactersForCustomNumericBase) throws TypeParserBuilderException;

    /**
     * <p>Configures the type-parser to parse a number using the provided code-points as the ‘digits’.
     * The base (or radix) of the number will be determined by the number of code-points you provide. For example, if you wanted to parse base-20
     * numbers you may consider configuring the parser with:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J')
     *   .build()
     * }</pre>
     *
     * <p>There is no restriction on which Unicode characters you may want to use.
     * You could consider using characters from any alphabet. For example, for a {@code ProductCode} type in Greece you may choose to have the product
     * codes written as base-34 numbers using the Greek alphabet:</p>
     *
     * <pre>{@code
     * LongTypeParser.builder()
     *   .allowCustomBaseNumbers(
     *       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
     *       'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'I', 'Κ',
     *       'Λ', 'Μ', 'Ν', 'Ξ', 'O', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ',
     *       'Φ', 'Χ', 'Ψ', 'Ω')
     *   .build()
     * }</pre>
     *
     * <p>When creating such custom numeric bases, consider that some letters characters may
     * be best omitted if they are easily confused with numbers, for example the number 0 and letter O, or the number 1 and the letter I, which can be
     * hard to distinguish when displayed in certain fonts.</p>
     *
     * <p>It is also be possible to provide a set of code-points that are Unicode symbols
     * instead of digits or letters. There is no restriction.</p>
     *
     * @param codePointsForCustomNumericBase the code-points that you wish to use to represent numbers of any base or radix. The first code-point will
     *                                       map to zero and all the subsequent code-points to the numeric values that follow.
     * @return this builder
     * @throws TypeParserBuilderException if less than two characters are provided.
     * @see #setCustomRadix(int...)
     */
    LongTypeParserBuilder setCustomRadix(int... codePointsForCustomNumericBase) throws TypeParserBuilderException;

    /**
     * Configures the type-parser to accept only the default binary (base-2) digits {@code 0..1}.
     *
     * <p><b>Allowed digits</b></p>
     * <pre>{@code
     * 0, 1
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder setRadixBinary();

    /**
     * <p>Configures the type-parser to accept only the default octal (base-8) digits {@code 0..7} from any Unicode decimal digit in the
     * <a href="https://www.compart.com/en/unicode/category/Nd">Unicode "Decimal Number" category.</a></p>.
     *
     * <p><b>Allowed digits</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder setRadixOctal();

    /**
     * Configures the type-parser to accept only the default decimal (base-10) digits {@code 0..9}.
     *
     * <p><b>Allowed digits</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder setRadixDecimal();

    /**
     * Configures the type-parser to accept only the default base-16 digits and letters {@code 0..9A..F}. This method will automatically set the
     * type-parser to be case-insensitive replacing any prior configuration, thus also allowing values containing {@code a..f}. If you would like the
     * type parser to be case-sensitive then you should call {@link #caseSensitive()} after calling this method.
     *
     * <p><b>Allowed chars</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase16Numbers();

    /**
     * Configures the type-parser to accept only the default base-32 digits and letters {@code 0..9A..V}.  This method will automatically set the
     * type-parser to be case-insensitive replacing any prior configuration, thus also allowing values containing {@code a..v}. If you would like the
     * type parser to be case-sensitive then you should call {@link #caseSensitive()} after calling this method.
     *
     * <p><b>Allowed chars</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
     * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase32Numbers();

    /**
     * Configures the type-parser to accept only the default base-36 digits and letters {@code 0..9A..Z}. This method will automatically set the
     * type-parser to be case-insensitive replacing any prior configuration, thus also allowing values containing {@code a..z}. If you would like the
     * type parser to be case-sensitive then you should call {@link #caseSensitive()} after calling this method.
     *
     * <p><b>Allowed chars</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
     * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
     * W, X, Y, Z
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase36Numbers();

    /**
     * Configures the type-parser to accept only the default base-62 digits and letters {@code 0..9A..Za..z}. This method will automatically set the
     * type-parser to be case-sensitive replacing any prior configuration, thus treating values {@code A..Z} as distinct from {@code a..z}. If you
     * subsequently call {@link #caseInsensitive()} then the parsed values may not be correctly interpreted as Base-62 numbers.
     *
     * <p><b>Allowed chars</b></p>
     * <pre>{@code
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F,
     * G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
     * W, X, Y, Z, a, b, c, d, e, f, g, h, i, j, k, l,
     * m, n, o, p, q, r, s, t, u, v, w, x, y, z
     * }</pre>
     *
     * @return this builder
     */
    LongTypeParserBuilder allowBase62Numbers();


    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified character, {@code 'ch'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with hyphens for readability but then wish to remove any hyphens from
     * these product codes while parsing. So a product code supplied to you as {@code 112-333-789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .ignoreAllOccurrencesOfChar('-') // hyphen
     *   .build();
     * }</pre>
     *
     * @param ch the character you want the type-parser to ignore when parsing the value.
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfChar(char ch);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified characters, {@code 'chars'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all underscores, hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with hyphens for readability but then wish to remove any hyphens from
     * these product codes while parsing. So a product code supplied to you as {@code 112-333-789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .ignoreAllOccurrencesOfChars('_', '-', '–', '—') // underscore, hyphen, en-dash, em-dash
     *   .build();
     * }</pre>
     *
     * @param chars the characters you want the type-parser to ignore when parsing the value. A null or empty array will have no effect.
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfChars(char... chars);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified code-point, {@code 'codePoint'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with hyphens for readability but then wish to remove any hyphens from
     * these product codes while parsing. So a product code supplied to you as {@code 112-333-789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .ignoreAllOccurrencesOfChar('-') // hyphen
     *   .build();
     * }</pre>
     *
     * @param codePoint the code-points you want the type-parser to ignore when parsing the value
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfCodePoint(int codePoint);

    /**
     * <p>This will configure the type-parser to ignore all occurrences of the specified code-points, {@code 'codePoints'}, when
     * parsing the value.</p>
     *
     * <p>This can be useful if you have numbers that are usually printed with formatting to make them easier to read, but then wish to
     * remove any such formatting upon parsing values that are provided back to your application.</p>
     *
     * <p><b>Example – removing all underscores, hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with hyphens for readability but then wish to remove any hyphens from
     * these product codes while parsing. So a product code supplied to you as {@code 112-333-789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .ignoreAllOccurrencesOfChars('_', '-', '–', '—') // underscore, hyphen, en-dash, em-dash
     *   .build();
     * }</pre>
     *
     * @param codePoints the code-points you want the type-parser to ignore when parsing the value. A null or empty array will have no effect.
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     */
    LongTypeParserBuilder ignoreAllOccurrencesOfCodePoints(int... codePoints);

    /**
     * <p>This will configure the type-parser to ignore all whitespace characters in the value to be parsed. It swallows up
     * any whitespace as if it wasn't there. This is the default setting for a type-parser.</p>
     *
     * <p>Whitespace characters are defined as any character that returns {@code true} when passed to {@link Character#isWhitespace(char)}.</p>
     *
     * <p><b>Example – removing all whitespace from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with spaces for readability but then wish to remove any spaces from
     * these product codes while parsing. So a product code supplied to you as {@code 112 333 789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .removeAllWhitespace()
     *   .build();
     * }</pre>
     *
     * @return this builder.
     * @see #forbidWhitespace()
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder ignoreAllWhitespace();

    /**
     * <p>This will configure the type-parser to forbid any whitespace characters in the value to be parsed.</p>
     *
     * <p>Whitespace characters are defined as any character that returns {@code true} when passed to {@link Character#isWhitespace(char)}.</p>
     *
     * <p><b>Example – forbidding whitespace in a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with spaces for readability but then wish to forbid any spaces from
     * these product codes while parsing. So a product code supplied to you as {@code 112 333 789} would, if you wanted to annoy your customers and
     * clients, need to be provided without the spaces to be considered valid and an exception would be thrown otherwise.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code with no spaces"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .forbidWhitespace()
     *   .build();
     * }</pre>
     *
     * <p>Consider allowing and ignoring whitespace by configuring the type-parser with {@link #ignoreAllWhitespace()} instead. This will swallow up
     * any whitespace.</p>
     *
     * @return this builder.
     * @see #ignoreAllWhitespace()
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     */
    LongTypeParserBuilder forbidWhitespace();

    /**
     * <p>This will configure the type-parser to ignore all occurrences of any dashes and hyphens that are found in the
     * <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash Punctuation (Pd) category</a> from the parsed value.</p>
     *
     * <p><b>Example – removing all dashes and hyphens from a parsed value</b></p>
     *
     * <p>On websites, statements or invoices you may print your product-codes with hyphens for readability but then wish to remove any hyphens from
     * these product codes while parsing. So a product code supplied to you as {@code 112-333-789} would actually be parsed into your custom type as
     * {@code 112333789}.</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode(MessageCode.of("invalid.product.code", "must be a 9-digit product code"))
     *   .minValueInclusive(100_000_000L)
     *   .maxValueInclusive(999_999_999L)
     *   .ignoreAllDashesAndHyphens()
     *   .build();
     * }</pre>
     *
     * @return this builder.
     * @see #ignoreAllOccurrencesOfChar(char)
     * @see #ignoreAllOccurrencesOfChars(char...)
     * @see #ignoreAllOccurrencesOfCodePoint(int)
     * @see #ignoreAllOccurrencesOfCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    LongTypeParserBuilder ignoreAllDashesAndHyphens();

    /**
     * <p>This will configure the type-parser to allow a leading negative sign while parsing numbers. This is the default configuration for a type
     * parser.</p>
     *
     * <p>Consider configuring your type parser with {@link #ignoreAllDashesAndHyphens()} or {@link #ignoreLeadingNegativeSign()} if your custom type
     * has no negative values and you wish to remove noise from the incoming value.</p>
     *
     * @return this builder.
     */
    LongTypeParserBuilder allowLeadingNegativeSign();

    /**
     * <p>This will configure the type-parser to ignore any leading negative sign while parsing numbers.</p>
     *
     * <p>Consider configuring your type parser with {@link #allowLeadingNegativeSign()} if your custom type has negative values and you wish to
     * allow them.</p>
     *
     * @return this builder.
     */
    LongTypeParserBuilder ignoreLeadingNegativeSign();

    /**
     * <p>This will configure the type-parser to allow a leading positive sign while parsing numbers. This is the default configuration for a type
     * parser.</p>
     *
     * <p>Consider configuring your type parser with {@link #ignoreLeadingPositiveSign()} if you wish to remove noise from the incoming value.</p>
     *
     * @return this builder.
     */
    LongTypeParserBuilder allowLeadingPositiveSign();

    /**
     * <p>This will configure the type-parser to ignore any leading positive sign while parsing numbers.</p>
     *
     * <p>Consider configuring your type parser with {@link #allowLeadingPositiveSign()} if you wish to allow positive signs in the incoming
     * value.</p>
     *
     * @return this builder.
     */
    LongTypeParserBuilder ignoreLeadingPositiveSign();
  }
}