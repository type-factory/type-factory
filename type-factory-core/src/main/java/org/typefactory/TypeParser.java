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
package org.typefactory;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.typefactory.impl.Factory;

/**
 * <p>Interface for a type-parser that can parse in input {@link CharSequence},
 * clean it up if required, and validate that it conforms to configured rules.</p>
 *
 * <p>Type-parsers created with the {@link TypeParser#builder()} are immutable and thread-safe.</p>
 *
 * <p>It is recommended that you create a type-parser once and reuse it wherever required.
 * They are consider expensive to create but cheap to reuse. Type-parsers created with {@link TypeParser#builder()} method have been designed to do
 * their job efficiently with minimal object creation and no auto-boxing.</p>
 *
 * <p><b>Example 1 – type-parser for an ISO 4217 currency code</b></p>
 * <pre>{@code
 * static final TypeParser TYPE_PARSER = TypeParser.builder()
 *     .messageCode("must be a 3 character ISO 4217 alpha currency code")
 *     .acceptCharRange('a', 'z')
 *     .acceptCharRange('A', 'Z')
 *     .fixedSize(3)
 *     .removeAllWhitespace()
 *     .toUpperCase()
 *     .build();
 * }</pre>
 *
 * <p><b>Example 2 – type-parser for an International Bank Account Number (IBAN)</b></p>
 * <pre>{@code
 * static final TypeParser TYPE_PARSER = TypeParser.builder()
 *     .messageCode("must be a valid 5..34 alpha-numeric character International Bank Account Number (IBAN)")
 *     .acceptLettersAtoZ() // convenience method for a-zA-Z
 *     .acceptDigits0to9()  // convenience method for 0-9
 *     .minSize(5)
 *     .maxSize(34)
 *     .removeAllWhitespace()
 *     .removeAllChars('.', '-', '–', '—') // period, dash, endash, emdash
 *     .toUpperCase()
 *     .matchesRegex(VALID_IBAN_PATTERN)   // IBAN regex pattern
 *     .customValidator(InternationalBankAccountNumber::isValidIBAN) // check digit validation
 *     .build();
 * }</pre>
 *
 * @see #builder()
 */
public interface TypeParser {

  /**
   * <p>Creates a builder with which to create a type-parser.</p>>
   *
   * @return a builder with which to create a type-parser.
   */
  static TypeParserBuilder builder() {
    return Factory.typeParserBuilder();
  }

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link StringType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code CurrencyCode} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToStringType} method:</p>
   *
   * <pre>{@code
   * public final class CurrencyCode extends StringType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private CurrencyCode(final String value) { super(value); }
   *
   *   public static CurrencyCode of(final CharSequence value) {
   *     return TYPE_PARSER.parseToStringType(value, CurrencyCode::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToString(CharSequence)} method to simply parse into a {@link String} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link StringType}
   * @return a custom type instance that extends {@link StringType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   */
  <T extends StringType> T parseToStringType(CharSequence value, Function<String, T> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link ShortType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code DepartmentId} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToShortType} method:</p>
   *
   * <pre>{@code
   * public final class DepartmentId extends ShortType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private DepartmentId(final Short value) {
   *     super(value);
   *   }
   *
   *   public static DepartmentId of(final CharSequence value) {
   *     return TYPE_PARSER.parseToShortType(value, DepartmentId::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToShort(CharSequence)} method to simply parse into a {@link Short} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link ShortType}
   * @return a custom type instance that extends {@link ShortType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   */
  <T extends ShortType> T parseToShortType(CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link IntegerType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code InvoiceNumber} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToIntegerType} method:</p>
   *
   * <pre>{@code
   * public final class InvoiceNumber extends IntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private InvoiceNumber(final Integer value) {
   *     super(value);
   *   }
   *
   *   public static InvoiceNumber of(final CharSequence value) {
   *     return TYPE_PARSER.parseToIntegerType(value, InvoiceNumber::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToInteger(CharSequence)} method to simply parse into a {@link Integer} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link IntegerType}
   * @return a custom type instance that extends {@link IntegerType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   */
  <T extends IntegerType> T parseToIntegerType(CharSequence value, IntFunction<T> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link LongType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code DepartmentId} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToLongType} method:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private ProductId(final Long value) {
   *     super(value);
   *   }
   *
   *   public static ProductId of(final CharSequence value) {
   *     return TYPE_PARSER.parseToLongType(value, ProductId::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToLong(CharSequence)} method to simply parse into a {@link Long} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link LongType}
   * @return a custom type instance that extends {@link LongType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   * @see #parseToString(CharSequence)
   */
  <T extends LongType> T parseToLongType(CharSequence value, LongFunction<T> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link String} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code String} variable</b></p>
   *
   * <pre>{@code
   * String someVariable = TYPE_PARSER.parseToString(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code CurrencyCode} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code CurrencyCode} instance:</p>
   *
   * <pre>{@code
   * public final class CurrencyCode extends StringType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public CurrencyCode(final String value) {
   *     super(TYPE_PARSER.parseToString(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for null, empty or blank internal values using
   * {@link StringType#isNull() isNull()}, {@link StringType#isEmpty() isEmpty()} or {@link StringType#isBlank() isBlank()}. An alternative is to use
   * a factory method using the example shown in {@link #parseToStringType(CharSequence, Function)} instead.</p>
   *
   * @param value the value to parse into a {@link String}.
   * @return parses the provided {@code value} into a {@link String} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link String}.
   * @see #parseToStringType(CharSequence, Function)
   */
  String parseToString(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Short} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code Short} variable</b></p>
   *
   * <pre>{@code
   * Short someVariable = TYPE_PARSER.parseToShort(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code DepartmentId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code DepartmentId} instance:</p>
   *
   * <pre>{@code
   * public final class DepartmentId extends ShortType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public DepartmentId(final Short value) {
   *     super(TYPE_PARSER.parseToShortType(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link ShortType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToShortType(CharSequence, Function)} instead.</p>
   *
   * @param value the value to parse into a {@link Short}.
   * @return parses the provided {@code value} into a {@link Short} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Short}.
   * @see #parseToShortType(CharSequence, Function)
   */
  Short parseToShort(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Integer} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code Integer} variable</b></p>
   *
   * <pre>{@code
   * Integer someVariable = TYPE_PARSER.parseToInteger(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code InvoiceNumber} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code InvoiceNumber} instance:</p>
   *
   * <pre>{@code
   * public final class InvoiceNumber extends IntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public InvoiceNumber(final Integer value) {
   *     super(TYPE_PARSER.parseToIntegerType(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link IntegerType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToIntegerType(CharSequence, IntFunction)} instead.</p>
   *
   * @param value the value to parse into a {@link Integer}.
   * @return parses the provided {@code value} into a {@link Integer} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Integer}.
   * @see #parseToIntegerType(CharSequence, IntFunction)
   */
  Integer parseToInteger(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a {@link Long} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code Long} variable</b></p>
   *
   * <pre>{@code
   * Integer someVariable = TYPE_PARSER.parseToInteger(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code ProductId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code ProductId} instance:</p>
   *
   * <pre>{@code
   * public final class ProductId extends LongType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public ProductId(final Long value) {
   *     super(TYPE_PARSER.parseToLongType(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link LongType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToIntegerType(CharSequence, IntFunction)} instead.</p>
   *
   * @param value the value to parse into a {@link Long}.
   * @return parses the provided {@code value} into a {@link Long} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link Long}.
   * @see #parseToLongType(CharSequence, LongFunction)
   */
  Long parseToLong(CharSequence value) throws InvalidValueException;

  /**
   * A builder that can create immutable, threadsafe {@link TypeParser} instances.
   */
  interface TypeParserBuilder {

    // Todo add Javadoc if still required.
    TypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    /**
     * <p>Set a {@link MessageCode} containing a message code and default message to be returned in the {@link InvalidValueException} if the
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
     * @param messageCode a {@link MessageCode} containing a message code and default message to be.
     * @return this builder
     * @see java.util.ListResourceBundle
     * @see java.util.ResourceBundle
     */
    TypeParserBuilder messageCode(final MessageCode messageCode);

    /**
     * <p>The minimum number of Unicode characters (code-points) that the parsed value must contain. </p>
     *
     * <p><b>Note</b>, the {@code minSize} refers to the minimum number of actual Unicode characters (code-points). For Unicode characters in the
     * range:</p>
     * <ul>
     *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all
     *   modern day languages.</li>
     *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
     *   symbols and emoticons.</li>
     * </ul>
     *
     * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
     * blocks in the U+010000..U+10FFFF codepoint range.</p>
     *
     * @param minSize the minimum number of Unicode characters (code-points) that the parsed value must contain.
     * @return this builder.
     * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
     */
    TypeParserBuilder minSize(int minSize);

    /**
     * <p>The maximum number of Unicode characters (code-points) that the parsed value must contain. </p>
     *
     * <p><b>Note</b>, the {@code maxSize} refers to the maximum number of actual Unicode characters (code-points). For Unicode characters in the
     * range:</p>
     * <ul>
     *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all modern
     *   day languages.</li>
     *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
     *   symbols and emoticons.</li>
     * </ul>
     *
     * <p>If you create a data-type with {@code maxSize} of, say, 10 and allow for Unicode characters (like emoticons) in
     * the {@code U+010000..U+10FFFF} range that you may end up with a parsed string whose {@link String#length()} is greater than 10.</p>
     *
     * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
     * blocks in the U+010000..U+10FFFF codepoint range.</p>
     *
     * @param maxSize the maximum number of Unicode characters (code-points) that the parsed value must contain.
     * @return this builder.
     * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
     */
    TypeParserBuilder maxSize(int maxSize);

    /**
     * <p>The fixed number of Unicode characters (code-points) that the parsed value must contain.</p>
     *
     * <p>This is a convenience method that simply calls both the {@link #minSize(int)} and {@link #maxSize(int)} methods
     * passing the {@code size} argument to both.</p>
     *
     * <p><b>Note</b>, the {@code maxSize} refers to the maximum number of actual Unicode characters (code-points). For Unicode characters in the
     * range:</p>
     * <ul>
     *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all modern
     *   day languages.</li>
     *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
     *   symbols and emoticons.</li>
     * </ul>
     *
     * <p>So be aware that if you create a data-type with fixed {@code size} of, say, 10 and allow for Unicode characters (like emoticons) in
     * the {@code U+010000..U+10FFFF} range that you may end up with a parsed string whose {@link String#length()} is greater than 10.</p>
     *
     * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
     * blocks in the U+010000..U+10FFFF codepoint range.</p>
     *
     * @param size the fixed number of Unicode characters (code-points) that the parsed value must contain.
     * @return this builder.
     * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
     */
    TypeParserBuilder fixedSize(int size);

    /**
     * <p>Configures the typeParser to ensure that both null and empty input values are preserved in the parsed result.</p>
     *
     * <p>For example - assuming a valid parseable value of "ABC":</p>
     *
     * <pre>
     *   parseToString(null)                          ⟶ null
     *   parseToString("")                            ⟶ ""
     *   parseToString("ABC")                         ⟶ "ABC"
     *
     *   parseToStringType(null,  TargetClass::new)   ⟶ null
     *   parseToStringType("",    TargetClass::new)   ⟶ new TargetClass("")
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
     * </pre>
     *
     * @return this builder.
     * @see #convertEmptyToNull()
     * @see #convertNullToEmpty()
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder preserveNullAndEmpty();

    /**
     * <p>Configures the typeParser to ensure that both null and empty input values are converted to an empty value in the parsed result.</p>
     *
     * <p>For example - assuming a valid parseable value of "ABC":</p>
     *
     * <pre>
     *   parseToString(null)                          ⟶ ""
     *   parseToString("")                            ⟶ ""
     *   parseToString("ABC")                         ⟶ "ABC"
     *
     *   parseToStringType(null,  TargetClass::new)   ⟶ new TargetClass("")
     *   parseToStringType("",    TargetClass::new)   ⟶ new TargetClass("")
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
     * </pre>
     *
     * @return this builder.
     * @see #preserveNullAndEmpty()
     * @see #convertEmptyToNull()
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder convertNullToEmpty();

    /**
     * <p>Configures the typeParser to ensure that both null and empty input values are converted to a null value in the parsed result.</p>
     *
     * <p>For example - assuming a valid parseable value of "ABC":</p>
     *
     * <pre>
     *   parseToString(null)                          ⟶ null
     *   parseToString("")                            ⟶ null
     *   parseToString("ABC")                         ⟶ "ABC"
     *
     *   parseToStringType(null,  TargetClass::new)   ⟶ null
     *   parseToStringType("",    TargetClass::new)   ⟶ null
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
     * </pre>
     *
     * @return this builder.
     * @see #preserveNullAndEmpty()
     * @see #convertNullToEmpty()
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder convertEmptyToNull();

    /**
     * <p>Configures the typeParser to ensure that letter-case is preserved.</p>
     *
     * @return this builder.
     */
    TypeParserBuilder preserveCase();

    /**
     * <p>Configures the typeParser to ensure that input values are converted to an upper-cased value in the parsed result.</p>
     *
     * <p>For example, the typeParser would parse the following values:</p>
     *
     * <pre>
     *   parseToString("abc")                         ⟶ "ABC"
     *   parseToString("Abc")                         ⟶ "ABC"
     *   parseToString("AbC")                         ⟶ "ABC"
     *   parseToString("ABC")                         ⟶ "ABC"
     *
     *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("ABC")
     *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("ABC")
     *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("ABC")
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
     * </pre>
     *
     * @return this builder.
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder toUpperCase();

    /**
     * <p>Configures the typeParser to ensure that input values are converted to a lower-cased value in the parsed result.</p>
     *
     * <p>For example, the typeParser would parse the following values:</p>
     *
     * <pre>
     *   parseToString("abc")                         ⟶ "abc"
     *   parseToString("Abc")                         ⟶ "abc"
     *   parseToString("AbC")                         ⟶ "abc"
     *   parseToString("ABC")                         ⟶ "abc"
     *
     *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("abc")
     *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("abc")
     *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("abc")
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("abc")
     * </pre>
     *
     * @return this builder.
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder toLowerCase();

    /**
     * <p>Configures the typeParser to ensure that input values are converted to a title-cased value in the parsed result.</p>
     *
     * <p>For example, the typeParser would parse the following values:</p>
     *
     * <pre>
     *   parseToString("abc")                         ⟶ "Abc"
     *   parseToString("Abc")                         ⟶ "Abc"
     *   parseToString("AbC")                         ⟶ "Abc"
     *   parseToString("ABC")                         ⟶ "Abc"
     *
     *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("Abc")
     *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("Abc")
     *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("Abc")
     *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("Abc")
     * </pre>
     *
     * @return this builder.
     * @see TypeParser#parseToString(CharSequence)
     * @see TypeParser#parseToStringType(CharSequence, Function)
     */
    TypeParserBuilder toTitleCase();

    /**
     * <p>Configure the type-parser to preserve the <a href="https://unicode.org/reports/tr15/">Unicode Normalization Form</a>.</p>
     *
     * <p>The Unicode Normalization Forms are:</p>
     *
     * <pre>
     * Form                         | Description
     * =============================|============================================================
     * Normalization Form D (NFD)   | Canonical Decomposition
     * Normalization Form C (NFC)   | Canonical Decomposition, followed by Canonical Composition
     * Normalization Form KD (NFKD) | Compatibility Decomposition
     * Normalization Form KC (NFKC) | Compatibility Decomposition, followed by Canonical Composition
     * </pre>
     *
     * @return this builder.
     * @see <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>
     */
    TypeParserBuilder preserveCharacterNormalizationForm();

    /**
     * <p>Configure the type-parser to convert the value to <a href="https://unicode.org/reports/tr15/#Norm_Forms">Unicode Normalization
     * Form C (NFC)</a> – Canonical Decomposition, followed by Canonical Composition.</p>
     *
     * <p>This is the normalization form you should prefer most of the time if conversion is required for your type-parser because
     * it is standard practice to accept, provide and store data in NFC form.</p>
     *
     * <p><b>Example</b></p>
     *
     * <pre>
     * Input    |  NFD              |  NFC
     * =========|===================|=======
     * Å        |  A ◌̊              |  Å
     * A ◌̊      |  A ◌̊              |  Å
     * μαϊμού   |  μ α ι ◌̈ μ ο υ ◌́  |  μαϊμού
     * </pre>
     *
     * <cite>Part of example taken from <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>.</cite>
     *
     * @return this builder.
     * @see <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>
     */
    TypeParserBuilder toCharacterNormalizationFormNFC();

    /**
     * <p>Configure the type-parser to convert the value to <a href="https://unicode.org/reports/tr15/#Norm_Forms">Unicode Normalization
     * Form D (NFD)</a> – Canonical Decomposition.</p>
     *
     * <p>The use-case for this would be rare – most of the time you will want to prefer {@link #toCharacterNormalizationFormNFC()} because
     * it is standard practice to accept, provide and store data in NFC form.</p>
     *
     * <p><b>Example</b></p>
     *
     * <pre>
     * Input    |  NFD              |  NFC
     * =========|===================|=======
     * Å        |  A ◌̊              |  Å
     * A ◌̊      |  A ◌̊              |  Å
     * μαϊμού   |  μ α ι ◌̈ μ ο υ ◌́  |  μαϊμού
     * </pre>
     *
     * <cite>Part of example taken from <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>.</cite>
     *
     * @return this builder.
     * @see <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>
     */
    TypeParserBuilder toCharacterNormalizationFormNFD();

    /**
     * <p>Configure the type-parser to convert the value to <a href="https://unicode.org/reports/tr15/#Norm_Forms">Normalization
     * Form KC (NFKC)</a> – Compatibility Decomposition, followed by Canonical Composition.</p>
     *
     * <p>The use-case for this would be rare – most of the time you will want to prefer {@link #toCharacterNormalizationFormNFC()} because
     * it is standard practice to accept, provide and store data in NFC form.</p>
     *
     * <p><b>Example</b></p>
     *
     * <pre>
     * Input    |  NFD    |  NFC  |  NFKD   |  NFKC
     * =========|=========|=======|=========|========
     * ﬁ        |  ﬁ      |  ﬁ    |  f i    |  f i
     * 2⁵       |  2 ⁵    |  2 ⁵  |  2 5    |  2 5
     * ẛ̣        |  ſ ◌̣ ◌̇  |  ẛ ◌̣  |  s ◌̣ ◌̇  |  ṩ
     * </pre>
     *
     * <cite>Example taken from <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>.</cite>
     *
     * @return this builder.
     * @see <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>
     */
    TypeParserBuilder toCharacterNormalizationFormNFKC();

    /**
     * <p>Configure the type-parser to convert the value to <a href="https://unicode.org/reports/tr15/#Norm_Forms">Normalization
     * Form KD (NFKD)</a> – Compatibility Decomposition.</p>
     *
     * <p>The use-case for this would be rare – most of the time you will want to prefer {@link #toCharacterNormalizationFormNFC()} because
     * it is standard practice to accept, provide and store data in NFC form.</p>
     *
     * <p><b>Example</b></p>
     *
     * <pre>
     * Input    |  NFD    |  NFC  |  NFKD   |  NFKC
     * =========|=========|=======|=========|========
     * ﬁ        |  ﬁ      |  ﬁ    |  f i    |  f i
     * 2⁵       |  2 ⁵    |  2 ⁵  |  2 5    |  2 5
     * ẛ̣        |  ſ ◌̣ ◌̇  |  ẛ ◌̣  |  s ◌̣ ◌̇  |  ṩ
     * </pre>
     *
     * <cite>Example taken from <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>.</cite>
     *
     * @return this builder.
     * @see <a href="https://unicode.org/reports/tr15/">Unicode Normalization Forms</a>
     */
    TypeParserBuilder toCharacterNormalizationFormNFKD();

    /**
     * <p>Configures the type-parser to accept the specified character in the values to be parsed by the type-parser. The type-parser will only allow
     * accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * @param ch the character to accept in the values to be parsed by the type-parser.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChars(char...)
     * @see #acceptCharRange(char, char)
     * @see #acceptCodePoint(int)
     * @see #acceptCodePoints(int...)
     * @see #acceptCodePointRange(int, int)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptChar(char ch);

    /**
     * <p>Configures the type-parser to accept the specified characters in the values to be parsed by the type-parser. The type-parser will only
     * allow accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * @param chars the characters to accept in the values to be parsed by the type-parser.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     * @see #acceptCharRange(char, char)
     * @see #acceptCodePoint(int)
     * @see #acceptCodePoints(int...)
     * @see #acceptCodePointRange(int, int)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptChars(char... chars);

    /**
     * <p>Configures the type-parser to accept characters inclusively within the range defined by the arguments.  The type-parser will only allow
     * accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * <p>It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have been accidentally reversed, for
     * example ('Z', 'A') instead of ('A', 'Z').</p>
     *
     * @param inclusiveFrom the inclusive 'from' char.
     * @param inclusiveTo   the inclusive 'to' char.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     * @see #acceptChars(char...)
     * @see #acceptCodePoint(int)
     * @see #acceptCodePoints(int...)
     * @see #acceptCodePointRange(int, int)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptCharRange(char inclusiveFrom, char inclusiveTo);

    /**
     * <p>Configures the type-parser to accept the specified code-point in the values to be parsed by the type-parser. The type-parser will only
     * allow accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * @param codePoint the code-point to accept in the values to be parsed by the type-parser.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     * @see #acceptChars(char...)
     * @see #acceptCharRange(char, char)
     * @see #acceptCodePoints(int...)
     * @see #acceptCodePointRange(int, int)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptCodePoint(int codePoint);

    /**
     * <p>Configures the type-parser to accept the specified code-points in the values to be parsed by the type-parser. The type-parser will only
     * allow accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * @param codePoints the code-point to accept in the values to be parsed by the type-parser.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     * @see #acceptChars(char...)
     * @see #acceptCharRange(char, char)
     * @see #acceptCodePoint(int)
     * @see #acceptCodePointRange(int, int)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptCodePoints(int... codePoints);


    /**
     * <p>Configures the type-parser to accept code-points inclusively within the range defined by the arguments.  The type-parser will only allow
     * accepted characters/code-points or unicode character categories to be present in the value to parsed.</p>
     *
     * <p>It will automatically correct the range if the {@code inclusiveFrom} and {@code inclusiveTo} values have been accidentally reversed, for
     * example ('Z', 'A') instead of ('A', 'Z').</p>
     *
     * @param inclusiveFrom the inclusive 'from' code-point.
     * @param inclusiveTo   the inclusive 'to' code-point.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     * @see #acceptChars(char...)
     * @see #acceptCharRange(char, char)
     * @see #acceptCodePoint(int)
     * @see #acceptCodePoints(int...)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see #acceptDigits0to9()
     * @see #acceptLettersAtoZ()
     * @see #acceptAllDashes()
     * @see #acceptAllUnicodeLetters()
     */
    TypeParserBuilder acceptCodePointRange(int inclusiveFrom, int inclusiveTo);

    /**
     * <p>Configures the type-parser to convert any {@code fromChar} in the input value to a {@code toChar}.</p>
     *
     * <p>This method will automatically configure the type-parser to accept {@code fromChar} characters in the input
     * value. It does this using the {@link #acceptChar(char)} method.</p>
     *
     * <p><b>Example</b></p>
     *
     * <p>Given a type-parser that has been configured to convert hyphen to underscore:</p>
     * <pre>{@code
     * static final TypeParser TYPE_PARSER = TypeParser.builder()
     *     .acceptCharRange('a', 'z')
     *     .acceptChars('-', '_')
     *     .convertChar('-', '_') // convert hyphen to underscore
     *     .build();
     * }</pre>
     *
     * <p>When the type-parser is configured to parse input values to output values:</p>
     * <pre>{@code
     * String outputValue = TYPE_PARSER.parseToString(inputValue);
     * }</pre>
     *
     * <p>Then the type-parser will parse the following input values to the following output values:</p>
     * <pre>
     *   input value | output value
     *   ============|=============
     *   aaa-bbb     | aaa_bbb
     *   aaa-bbb-ccc | aaa_bbb_ccc
     *   aaa-bbb_ccc | aaa_bbb_ccc
     *   aaa_bbb-ccc | aaa_bbb_ccc
     * </pre>
     *
     * <p><b>Note</b> it is not necessary for the {@code toChar} to be an “accepted” character in the input value. Conversion
     * occurs after each input character has been vetted as accepted.</p>
     *
     * @param fromChar the character to convert from
     * @param toChar   the character to convert to
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     */
    TypeParserBuilder convertChar(char fromChar, char toChar);

    /**
     * <p>Configures the type-parser to convert any {@code fromChar} in the input sequence to a {@code toCharSequence}.</p>
     *
     * <p>By default, this method also configures the type-parser to accept {@code fromChar} characters in the input
     * sequence. It does this using the {@link #acceptChar(char)} method.</p>
     *
     * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
     * Conversion occurs after each input character has been vetted as accepted.</p>
     *
     * @param fromChar       the character to convert from
     * @param toCharSequence the character sequence to convert to. May be {@code null} or empty.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptChar(char)
     */
    TypeParserBuilder convertChar(char fromChar, CharSequence toCharSequence);

    /**
     * <p>Configures the type-parser to convert any {@code fromCodePoint} in the input sequence to a {@code toCodePoint}.</p>
     *
     * <p>By default, this method also configures the type-parser to accept {@code fromCodePoint} characters in the input
     * sequence. It does this using the {@link #acceptCodePoint(int)} method.</p>
     *
     * <p><b>Note</b> it is not necessary for the {@code toCodePoint} to be an “accepted” code-point in the input value.
     * Conversion occurs after each input character has been vetted as accepted.</p>
     *
     * @param fromCodePoint the code-point to convert from
     * @param toCodePoint   the code-point to convert to
     * @return this {@code TypeParserBuilder}.
     * @see #acceptCodePoint(int)
     */
    TypeParserBuilder convertCodePoint(int fromCodePoint, int toCodePoint);

    /**
     * <p>Configures the type-parser to convert any {@code fromCodePoint} in the input sequence to a {@code toCodePoint}.</p>
     *
     * <p>By default, this method also configures the type-parser to accept {@code fromCodePoint} characters in the input
     * sequence. It does this using the {@link #acceptCodePoint(int)} method.</p>
     *
     * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
     * Conversion occurs after each input character has been vetted as accepted.</p>
     *
     * @param fromCodePoint  the code-point to convert from
     * @param toCharSequence the character sequence to convert to. May be {@code null} or empty.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptCodePoint(int)
     */
    TypeParserBuilder convertCodePoint(int fromCodePoint, CharSequence toCharSequence);

    default TypeParserBuilder convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(final char zeroDigitChar) {
      return convertAnyDecimalDigitsToDigitsStartingWithZeroDigit((int) zeroDigitChar);
    }

    TypeParserBuilder convertAnyDecimalDigitsToDigitsStartingWithZeroDigit(final int zeroDigitCodePoint);

    /**
     * <p>Configures the type-parser to convert any code-point (character) in the Unicode {@code fromCategory} in the input sequence to a {@code toChar}.</p>
     *
     * @param fromCategory the Unicode {@code fromCategory} to convert from.
     * @param toChar the character to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInCategory(final Category fromCategory, final char toChar);

    /**
     * <p>Configures the type-parser to convert any code-point (character) in the Unicode {@code fromCategory} in the input sequence to a {@code toCodePoint}.</p>
     *
     * @param fromCategory the Unicode {@code fromCategory} to convert from.
     * @param toCodePoint the code-point to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInCategory(final Category fromCategory, final int toCodePoint);

    /**
     * <p>Configures the type-parser to convert any code-point (character) in the Unicode {@code fromCategory} in the input sequence to a {@code toCharSequence}.</p>
     *
     * @param fromCategory the Unicode {@code fromCategory} to convert from.
     * @param toCharSequence the char-sequence to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInCategory(final Category fromCategory, final CharSequence toCharSequence);

    /**
     * <p>Configures the type-parser to convert any code-point (character)  in the {@link Subset} that is found in the input sequence to a {@code toChar}.</p>
     *
     * @param fromSubset the {@link Subset} containing code-points to convert from.
     * @param toChar the character to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final char toChar);

    /**
     * <p>Configures the type-parser to convert any code-point (character) in the {@link Subset} that is found in the input sequence to a {@code toCodePoint}.</p>
     *
     * @param fromSubset the {@link Subset} containing code-points to convert from.
     * @param toCodePoint the code-point to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final int toCodePoint);

    /**
     * <p>Configures the type-parser to convert any code-point (character) in the {@link Subset} that is found in the input sequence to a {@code toCharSequence}.</p>
     *
     * @param fromSubset the {@link Subset} containing code-points to convert from.
     * @param toCharSequence the char-sequence to convert to.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final CharSequence toCharSequence);

    /**
     * <p>Configures the type-parser to convert any {@code fromCharSequence} in the input sequence to a {@code toCharSequence}.</p>
     *
     * <p>Conversion is carried out in the reading order of the input sequence.</p>
     *
     * <p><b>Nested matches</b></p>
     * Preference is given to the longest match when multiple {@code fromCharSequence} could be considered nested. For example, the following table
     * shows how a type-parser configured with 1, 2 or 3 conversions would convert an input value:</p>
     * <pre>
     *   Input value  | conversion 1       | Conversion 2           | Conversion 3         | After Conversion
     *   =============|====================|========================|======================|=====================
     *   semi-trailer | s    ⟶ d           |                        |                      | demi-trailer
     *   semi-trailer | s    ⟶ d           | semi-trailer ⟶ lorry   |                      | lorry
     *   semi-trailer | semi ⟶ articulated |                        |                      | articulated-trailer
     *   semi-trailer | semi ⟶ articulated | semi-trailer ⟶ lorry   |                      | lorry
     *   semi-trailer | semi ⟶ articulated | trailer      ⟶ vehicle |                      | articulated-vehicle
     *   semi-trailer | semi ⟶ articulated | trailer      ⟶ vehicle | semi-trailer ⟶ lorry | lorry
     * </pre>
     *
     * <p><b>Overlapping matches</b></p>
     * <p>Preference is given to the earliest and longest complete match. For example, the following table shows how a type-parser configured with 1,
     * 2 or 3 conversions would convert an input value:</p>
     * <pre>
     *   Input value     | conversion 1           | Conversion 2           | Conversion 3           | After Conversion
     *   ================|========================|========================|========================|==================
     *   full stop light | full stop  ⟶ period    |                        |                        | period light
     *   full stop light | stop light ⟶ red light |                        |                        | full red light
     *   full stop light | full stop  ⟶ period    | stop light ⟶ red light |                        | period light
     *   full stop light | full stop  ⟶ period    | stop light ⟶ red light | full stop light ⟶ skid | skid
     * </pre>
     *
     * <p>By default, this method also configures the type-parser to accept all the code-points found in
     * the {@code fromCharSequence} in the input sequence. This would be the same calling the {@link #acceptCodePoints(int...)} method.</p>
     *
     * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
     * Conversion occurs after each input character has been vetted as accepted.</p>
     *
     * @param fromCharSequence the character sequence to convert from
     * @param toCharSequence   the character sequence to convert to. May be {@code null} or empty.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptCodePoints(int...)
     */
    TypeParserBuilder convertCharSequence(CharSequence fromCharSequence, CharSequence toCharSequence);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified character, {@code 'ch'}, from the
     * parsed value.</p>
     *
     * <p>Specifying a character to remove automatically adds it to the set of accepted characters – so there is no need
     * to specifically configure the type-parser to accept the character using {@link #acceptChar(char)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-')
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param ch the character you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char...)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    TypeParserBuilder removeAllChars(char ch);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified characters, {@code 'chars'}, from the
     * parsed value.</p>
     *
     * <p>Specifying characters to remove automatically adds them to the set of accepted characters – so there is no need
     * to specifically configure the type-parser to accept the characters using {@link #acceptChars(char...)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing all hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param chars the characters you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    TypeParserBuilder removeAllChars(char... chars);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified {@code charSequence} from the
     * parsed value.</p>
     *
     * <p>Specifying a char-sequence to remove automatically adds all character in the char-sequence
     * to the set of accepted characters – so there is no need to specifically configure the type-parser to accept the characters using
     * {@link #acceptChars(char...)}.</p>
     *
     * <p><b>Example – removing a char-sequence from the parsed value</b></p>
     *
     * <p>To ensure product codes are parsed with the undesired prefix "sku" removed then you could configure the
     * type-parser as follows:</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('0', '9')
     *   .removeAllCharSequences("sku") // "sku" will be removed from parsed values
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param charSequence the char-sequence you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllCharSequences(CharSequence...)
     */
    TypeParserBuilder removeAllCharSequences(CharSequence charSequence);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified {@code charSequence}s from the
     * parsed value.</p>
     *
     * <p>Specifying char-sequences to remove automatically adds all character in the char-sequences
     * to the set of accepted characters – so there is no need to specifically configure the type-parser to accept the characters using
     * {@link #acceptChars(char...)}.</p>
     *
     * <p><b>Example – removing char-sequences from the parsed value</b></p>
     *
     * <p>To ensure product codes are parsed with the undesired prefix of "sku" or "item" removed then you could configure the
     * type-parser as follows:</p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('0', '9')
     *   .removeAllCharSequences("sku", "item") // "sku" and "item" will be removed from parsed values
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param charSequences the char-sequences you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllCharSequences(CharSequence)
     */
    TypeParserBuilder removeAllCharSequences(CharSequence... charSequences);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified {@code 'codePoint'}, from the
     * parsed value.</p>
     *
     * <p>Specifying a code-point to remove automatically adds it to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the character using {@link #acceptCodePoint(int)}. If it wasn't considered an accepted
     * code-point then the first occurrence of the code-point in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing grinning face emoticon</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character identifier code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllCodePoints(0x01F600) // 😀 U+01F600 grinning face emoticon
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param codePoint the code-point you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char...)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    TypeParserBuilder removeAllCodePoints(int codePoint);

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified {@code 'codePoints'}, from the
     * parsed value.</p>
     *
     * <p>Specifying code-points to remove automatically adds them to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the characters using {@link #acceptChars(char...)}. If it wasn't considered an accepted
     * character then the first occurrence of the character in the input sequence would result in an {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing 'grinning face' emoticons</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *   .messageCode("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllCodePoints(0x01F600, 0x01F601) // 😀 U+01F600 grinning face, 😁 U+01F601 grinning face with smiling eyes
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * }</pre>
     *
     * @param codePoints the code-points you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     */
    TypeParserBuilder removeAllCodePoints(int... codePoints);

    /**
     * <p>This will configure the type-parser to remove all occurrences of any dashes and hyphens
     * that are found in the <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a> from the parsed
     * value.</p>
     *
     * <p>For more information about categories refer to the <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode
     * General Character Categories</a> documentation.</p>
     *
     * <p>Specifying code-points to remove automatically adds them to the set of accepted code-points – so there is no need
     * to specifically configure the type-parser to accept the hyphens and dashes category {@link #acceptUnicodeCategory(Category)}. If hyphens and
     * dashes weren't considered accepted characters then the first occurrence of the character in the input sequence would result in an
     * {@link InvalidValueException}.</p>
     *
     * <p><b>Example – removing 'grinning face' emoticons</b></p>
     *
     * <pre>{@code
     * TypeParser.builder()
     *     .messageCode("must be a 16-digit product-id")
     *     .removeAllWhitespace()
     *     .removeAllDashesAndHyphens() // removes all hyphens and dashes
     *     .acceptDigits0to9()
     *     .fixedSize(16)
     *     .build();
     * }</pre>
     *
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllChars(char)
     * @see #removeAllCodePoints(int)
     * @see #removeAllCodePoints(int...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">Unicode Dash_Punctuation (Pd) category</a>
     */
    TypeParserBuilder removeAllDashesAndHyphens();

    /**
     * <p>Configures the type-parser to accept all the characters/code-points found in the specified {@code subset}.</p>
     *
     * <p>{@link Subset} instances created using the {@link Subset#builder()} are immutable, thread-safe collections of characters/code-points.
     * They can be defined once and reused where needed.</p>
     *
     * @param subset the collection of characters/code-points to accept in the input sequence.
     * @return this {@code TypeParserBuilder}.
     * @see Subset
     * @see Subset#builder()
     * @see #acceptSubsets(Subset...)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     */
    TypeParserBuilder acceptSubset(Subset subset);

    /**
     * <p>Configures the type-parser to accept all the characters/code-points found in the specified {@code subsets}.</p>
     *
     * <p>{@link Subset} instances created using the {@link Subset#builder()} are immutable, thread-safe collections of characters/code-points.
     * They can be defined once and reused where needed.</p>
     *
     * @param subsets multiple subsets containing the characters/code-points to accept in the input sequence.
     * @return this {@code TypeParserBuilder}.
     * @see Subset
     * @see Subset#builder()
     * @see #acceptSubset(Subset)
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptUnicodeCategory(Category...)
     */
    TypeParserBuilder acceptSubsets(Subset... subsets);

    /**
     * <p>Configures the type-parser to accept all the characters/code-points found in the specified {@code category}.</p>
     *
     * <p>For more information about categories refer to the <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode
     * General Character Categories</a> documentation.</p>
     *
     * <p>See the {@link Category} enum for all the available Unicode categories including the composite categories.</p>
     *
     * <p>The composite categories are those like {@link Category#LETTER} which is the union of code-points in the
     * {@link Category#UPPERCASE_LETTER UPPERCASE_LETTER}, {@link Category#LOWERCASE_LETTER LOWERCASE_LETTER},
     * {@link Category#TITLECASE_LETTER TITLECASE_LETTER}, {@link Category#MODIFIER_LETTER MODIFIER_LETTER},
     * {@link Category#OTHER_LETTER OTHER_LETTER}. In the Unicode documentation this is expressed as {@code L = Lu | Ll | Lt | Lm | Lo}.</p>
     *
     * @param category the Unicode general character category that defines the characters/code-points to accept in the input sequence.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptUnicodeCategory(Category...)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     */
    TypeParserBuilder acceptUnicodeCategory(Category category);

    /**
     * <p>Configures the type-parser to accept all the characters/code-points found in the specified {@code categories}.</p>
     *
     * <p>For more information about categories refer to the <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode
     * General Character Categories</a> documentation.</p>
     *
     * <p>See the {@link Category} enum for all the available Unicode categories including the composite categories.</p>
     *
     * <p>The composite categories are those like {@link Category#LETTER} which is the union of code-points in the
     * {@link Category#UPPERCASE_LETTER UPPERCASE_LETTER}, {@link Category#LOWERCASE_LETTER LOWERCASE_LETTER},
     * {@link Category#TITLECASE_LETTER TITLECASE_LETTER}, {@link Category#MODIFIER_LETTER MODIFIER_LETTER},
     * {@link Category#OTHER_LETTER OTHER_LETTER}. In the Unicode documentation this is expressed as {@code L = Lu | Ll | Lt | Lm | Lo}.</p>
     *
     * @param categories the Unicode general character category that defines the characters/code-points to accept in the input sequence.
     * @return this {@code TypeParserBuilder}.
     * @see #acceptUnicodeCategory(Category)
     * @see #acceptSubset(Subset)
     * @see #acceptSubsets(Subset...)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     */
    TypeParserBuilder acceptUnicodeCategory(Category... categories);

    /**
     * <p>A convenience method that is the same as calling:</p>
     * <pre>{@code
     *   acceptUnicodeCategory(Category.LETTER)
     * }</pre>
     *
     * <p>Essentially it accepts all characters/code-points found in the {@link Category#LETTER LETTER} composite category.</p>
     *
     * <p>The {@code LETTER} category is the union of all character/code-points in the {@link Category#UPPERCASE_LETTER UPPERCASE_LETTER},
     * {@link Category#LOWERCASE_LETTER LOWERCASE_LETTER}, {@link Category#TITLECASE_LETTER TITLECASE_LETTER},
     * {@link Category#MODIFIER_LETTER MODIFIER_LETTER}, and {@link Category#OTHER_LETTER OTHER_LETTER} categories. In the Unicode documentation the
     * {@code LETTER} category is often expressed as {@code L = Lu | Ll | Lt | Lm | Lo}.</p>
     *
     * @return this {@code TypeParserBuilder}.
     * @see #acceptUnicodeCategory(Category)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     */
    TypeParserBuilder acceptAllUnicodeLetters();

    /**
     * <p>A convenience method that is the same as calling:</p>
     * <pre>{@code
     *   acceptUnicodeCategory(Category.DECIMAL_DIGIT_NUMBER)
     * }</pre>
     *
     * <p>Essentially it accepts all characters/code-points found in the {@link Category#DECIMAL_DIGIT_NUMBER} composite category.</p>
     *
     * @return this {@code TypeParserBuilder}.
     * @see #acceptUnicodeCategory(Category)
     * @see <a href="https://unicode.org/reports/tr44/#General_Category_Values">Unicode General Character Categories</a>
     */
    TypeParserBuilder acceptAllUnicodeDecimalDigits();

    /**
     * <p>Configures the type parser to accepts all letters [a-zA-Z] that are in the <a href="https://unicode.org/charts/PDF/U0000.pdf">Basic
     * Latin</a> Unicode block which is equivalent to the ASCII character set.</p>
     *
     * <p>If you wish for the letters to be converted to upper or lower case then remember to configure the {@link TypeParser} to carry out
     * that action by calling {@link #toLowerCase()} or {@link #toUpperCase()} in the {@link TypeParserBuilder}. For example:</p>
     * <pre>{@code
     * TypeParser.builder()
     *   .acceptLettersAtoZ()
     *   .toUpperCase()
     *   .build;
     * }</pre>
     *
     * <p>If you want to accept <em>all</em> letters in the Unicode set (this could mean mixed-languages) then use:
     * {@link #acceptAllUnicodeLetters()}</p>
     *
     * <p>This method is a convenience method that invokes {@code acceptCharRange('a', 'z').acceptCharRange('A', 'Z');} on this builder.</p>
     *
     * <p>This convenience method exists because many of the International Organization for Standardization (ISO) standards specify codes
     * using these characters.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see #acceptUnicodeCategory(Category)
     * @see Category#LETTER
     * @see #toLowerCase()
     * @see #toUpperCase()
     */
    TypeParserBuilder acceptLettersAtoZ();

    /**
     * <p>Configures the type parser to accepts all digits number [0-9] that are in the <a href="https://unicode.org/charts/PDF/U0000.pdf">Basic
     * Latin</a> Unicode block which is equivalent to the ASCII character set.</p>
     *
     * <p>If you want to accept decimal digits from other scripts, like Devanagari and Malayalam, then you could use the following:</p>
     * <pre>{@code
     * TypeParser.builder()
     *   .acceptCharRange('०', '९')  // Devanagari decimal digit range
     *   .acceptCharRange('൦', '൯') // Malayalam decimal digit range
     *   .build;
     * }</pre>
     *
     * <p>If you want to accept <em>all</em> decimal digits from all scripts in Unicode, then use the following:</p>
     * <pre>{@code
     * TypeParser.builder()
     *   .acceptUnicodeCategory(Category.DECIMAL_DIGIT_NUMBER)
     *   .build;
     * }</pre>
     *
     * @return this {@code TypeParserBuilder}
     * @see #acceptUnicodeCategory(Category)
     * @see Category#DECIMAL_DIGIT_NUMBER
     */
    TypeParserBuilder acceptDigits0to9();

    /**
     * <p>Accepts all a dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode
     * category.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#DASH_PUNCTUATION
     * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
     */
    TypeParserBuilder acceptAllDashes();

    /**
     * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
     * to the {@code '-' (U+002D)} hyphen character defined in the Unicode
     * <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin</a> block. This is also the ordinary ASCII hyphen.</p>
     *
     * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
     * the {@code '-' (U+002D)} hyphen character. These other kinds of dashes and hyphens are most often encountered when data originates from some
     * kind of formatted or “corrected” source. For example, many user interfaces will auto-correct a double-dash to an en-dash.</p>
     *
     * <p>This method implicitly configures the builder to accept the {@code '-' (U+002D)} hyphen character.</p>
     *
     * <p><b>Note:</b> This conversion might make sense for certain data-types and not others. For example, in language specific data-types you may
     * may wish to preserve language specific dashes. For example, for an Armenian data-type, perhaps preserving the Armenian Hyphen
     * {@code '֊' (U+058A)} might make more sense than converting it to a hyphen.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#DASH_PUNCTUATION
     * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
     * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
     */
    TypeParserBuilder convertAllDashesToHyphen();

    /**
     * <p>Synonym for {@link #convertAllDashesToHyphen()}.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see #convertAllDashesToHyphen()
     */
    TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen();

    /**
     * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
     * to the specified {@code toChar} argument.</p>
     *
     * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
     * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character
     * {@code '_' (U+005F)}.</p>
     *
     * <p>This method implicitly configures the builder to accept the specified {@code toChar} argument.</p>
     *
     * <p><b>Note:</b> This conversion might make sense for certain data-types and not others. For example, in language specific data-types you may
     * may wish to preserve language specific dashes. For example, for an Armenian data-type, perhaps preserving the Armenian Hyphen
     * {@code '֊' (U+058A)} might make more sense than converting it to a hyphen.</p>
     *
     * @param toChar the character that you wish to convert any dash or hyphen punctuation mark characters to.
     * @return this {@code TypeParserBuilder}
     * @see Character#DASH_PUNCTUATION
     * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
     * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
     */
    TypeParserBuilder convertAllDashesTo(char toChar);

    /**
     * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
     * to the specified {@code toCodePoint} argument.</p>
     *
     * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
     * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character
     * {@code '_' (U+005F)}.</p>
     *
     * <p>This method implicitly configures the builder to accept the specified {@code toCodePoint} argument.</p>
     *
     * @param toCodePoint the code-point that you wish to convert any dash or hyphen punctuation mark characters to.
     * @return this {@code TypeParserBuilder}
     * @see Character#DASH_PUNCTUATION
     * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
     * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
     */
    TypeParserBuilder convertAllDashesTo(int toCodePoint);

    /**
     * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
     * to the specified {@code toCharSequence} argument.</p>
     *
     * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
     * some char-sequence of your liking. For example, you may wish to convert any dash or hyphen character to the sequence
     * {@code '~~~' (U+007E, U+007E, U+007E)}.</p>
     *
     * <p>This method implicitly configures the builder to accept the all code-points in the specified {@code toCharSequence} argument.</p>
     *
     * <p>This method exists because conversions in some languages will require a sequence of characters if the {@code toCharSequence}
     * is comprised of a letter followed by letter-modifiers. It may look on the screen to be a single character, but is actually a sequence.</p>
     *
     * @param toCharSequence the code-point that you wish to convert any dash or hyphen punctuation mark characters to.
     * @return this {@code TypeParserBuilder}
     * @see Character#DASH_PUNCTUATION
     * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
     * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
     * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
     */
    TypeParserBuilder convertAllDashesTo(CharSequence toCharSequence);

    /**
     * <p>Forbids any character is designated to be whitespace as per the {@link Character#isWhitespace(int)} method.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder forbidWhitespace();

    /**
     * <p>Removes any character or code-point that is designated to be whitespace as per the {@link Character#isWhitespace(int)} method.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder removeAllWhitespace();

    /**
     * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with a single space {' ' (U+0020)}
     * character.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder normalizeWhitespace();

    /**
     * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
     * {@code toChar} character.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * @param toChar the character that you wish to convert any contiguous blocks of whitespace to.
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder normalizeAndConvertWhitespaceTo(char toChar);

    /**
     * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
     * {@code toCodePoint} character.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * @param toCodePoint the code-point that you wish to convert any contiguous blocks of whitespace to.
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder normalizeAndConvertWhitespaceTo(int toCodePoint);

    /**
     * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
     * {@code toCodePoint} character.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * <p>This method exists because conversions in some languages will require a sequence of characters if the {@code toCharSequence}
     * is comprised of a letter followed by letter-modifiers. It may look on the screen to be a single character, but is actually a sequence.</p>
     *
     * @param toCharSequence the char-sequence that you wish to convert any contiguous blocks of whitespace to.
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder normalizeAndConvertWhitespaceTo(CharSequence toCharSequence);

    /**
     * <p>All leading, trailing, and interspersed whitespace will be preserved.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder preserveWhitespace();

    /**
     * <p>All leading, trailing, and interspersed whitespace will be converted to the specified {@code toChar} character.</p>
     *
     * <p>This method implicitly configures the builder to the specified {@code toChar} character.</p>
     *
     * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
     *
     * @return this {@code TypeParserBuilder}
     * @see Character#isWhitespace(char)
     * @see Character#isWhitespace(int)
     */
    TypeParserBuilder preserveAndConvertWhitespaceTo(char toChar);

    TypeParserBuilder preserveAndConvertWhitespaceTo(int toCodePoint);

    TypeParserBuilder preserveAndConvertWhitespaceTo(CharSequence toCharSequence);

    /**
     * <p>The type parser will ensure that the fully parsed value matches the provided {@code regex} pattern.</p>
     *
     * <p><b>Example usage:</b></p>
     *
     * <p>Imagine some data-type that is required to start with two alpha characters and is followed by 8 numeric digits. We could declare a regular
     * expression shown at ① and then configure the type parser to use the regular expression at ②:</p>
     *
     * <pre>{@code
     *   private static final Pattern VALID_PRODUCT_CODE_PATTERN = Pattern.compile("[A-Z]{2}[0-9]{8}"); ①
     *
     *   private static final TypeParser TYPE_PARSER = TypeParser.builder()
     *       .messageCode("must be a valid 10 character alphanumeric product code")
     *       .acceptLettersAtoZ()
     *       .acceptDigits0to9()
     *       .fixedSize(10)
     *       .removeAllWhitespace()
     *       .toUpperCase()
     *       .matchesRegex(VALID_PRODUCT_CODE_PATTERN) ②
     *       .build();
     * }</pre>
     *
     * @param regex the regular expression that the fully parsed value should conform to.
     * @return this {@code TypeParserBuilder}
     * @see Pattern
     */
    TypeParserBuilder matchesRegex(Pattern regex);

    /**
     * <p>The type parser will ensure that the fully parsed value passed the provided custom {@code validationFunction}.</p>
     *
     * <p>The custom validation function that you provide should:</p>
     * <ul>
     *   <li>return {@code true} if the fully parsed string value provided to it is <em>valid</em>.</li>
     *   <li>return {@code false} or throw an exception if the fully parsed string value provided to it is <em>invalid</em>.</li>
     * </ul>
     *
     * <p><b>Example:</b></p>
     * <pre>{@code
     *   private static final TypeParser TYPE_PARSER = TypeParser.builder()
     *       .messageCode("must be a valid 6..8 character product code")
     *       .acceptLettersAtoZ()
     *       .acceptDigits0to9()
     *       .minSize(6)
     *       .maxSize(8)
     *       .removeAllWhitespace()
     *       .toUpperCase()
     *       .customValidator(ProductCode::isValidProductCode)  ①
     *       .build();
     * }</pre>
     * <p>where the custom validation function ① is defined as:</p>
     * <pre>{@code
     *   private static boolean isValidProductCode(final String value) {
     *     boolean isValid = false;
     *     // ... your validation code
     *     if (isValid) {
     *       return true;
     *     }
     *     return false;
     *   }
     * }</pre>
     *
     * @param validationFunction a static function, or a functional interface, that accepts the fully parsed value as a {@link String} argument and
     *                           returns a boolean value indicating {@code true} if the provided value is <em>valid</em>. It can either return
     *                           {@code false} or throw an exception to indicate that the provided value is <em>invalid</em>.
     * @return this {@code TypeParserBuilder}
     * @see Predicate
     */
    TypeParserBuilder customValidator(Predicate<String> validationFunction);

    /**
     * Creates an immutable, threadsafe {@link TypeParser} instance.
     *
     * @return an immutable, threadsafe {@link TypeParser} instance.
     */
    TypeParser build();
  }
}
