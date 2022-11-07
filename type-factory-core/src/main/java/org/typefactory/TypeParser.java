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
   * <pre>
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
   * </pre>
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
   * <pre>
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
   * </pre>
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
   * <pre>
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
   * </pre>
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
   * <pre>
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
   * </pre>
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
   * <pre>
   * String someVariable = TYPE_PARSER.parseToString(value);
   * </pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code CurrencyCode} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code CurrencyCode} instance:</p>
   *
   * <pre>
   * public final class CurrencyCode extends StringType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public CurrencyCode(final String value) {
   *     super(TYPE_PARSER.parseToString(value));
   *   }
   * }
   * </pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for null, empty or blank internal values using
   * {@link StringType#isNull() isNull()}, {@link StringType#isEmpty() isEmpty()} or {@link StringType#isBlank() isBlank()}.
   * An alternative is to use a factory method using the example shown in {@link #parseToStringType(CharSequence, Function)} instead.</p>
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
   * <pre>
   * Short someVariable = TYPE_PARSER.parseToShort(value);
   * </pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code DepartmentId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code DepartmentId} instance:</p>
   *
   * <pre>
   * public final class DepartmentId extends ShortType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public DepartmentId(final Short value) {
   *     super(TYPE_PARSER.parseToShortType(value));
   *   }
   * }
   * </pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using
   * {@link ShortType#isNull() isNull()}. An alternative is to use a factory method using the example
   * shown in {@link #parseToShortType(CharSequence, Function)} instead.</p>
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
   * <pre>
   * Integer someVariable = TYPE_PARSER.parseToInteger(value);
   * </pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code InvoiceNumber} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code InvoiceNumber} instance:</p>
   *
   * <pre>
   * public final class InvoiceNumber extends IntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public InvoiceNumber(final Integer value) {
   *     super(TYPE_PARSER.parseToIntegerType(value));
   *   }
   * }
   * </pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using
   * {@link IntegerType#isNull() isNull()}. An alternative is to use a factory method using the example
   * shown in {@link #parseToIntegerType(CharSequence, IntFunction)} instead.</p>
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
   * <pre>
   * Integer someVariable = TYPE_PARSER.parseToInteger(value);
   * </pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code ProductId} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code ProductId} instance:</p>
   *
   * <pre>
   * public final class ProductId extends LongType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public ProductId(final Long value) {
   *     super(TYPE_PARSER.parseToLongType(value));
   *   }
   * }
   * </pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using
   * {@link LongType#isNull() isNull()}. An alternative is to use a factory method using the example
   * shown in {@link #parseToIntegerType(CharSequence, IntFunction)} instead.</p>
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

    TypeParserBuilder targetTypeClass(final Class<?> targetTypeClass);

    TypeParserBuilder errorMessage(final String errorMessage);

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
     * Configures the typeParser to ensure that both null and empty input values are preserved in the parsed result.
     * <p>
     * For example - assuming a valid parseable value of "ABC":
     * </p>
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
     * Configures the typeParser to ensure that both null and empty input values are converted to an empty value in the parsed result.
     * <p>
     * For example - assuming a valid parseable value of "ABC":
     * </p>
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
     * Configures the typeParser to ensure that both null and empty input values are converted to a null value in the parsed result.
     * <p>
     * For example - assuming a valid parseable value of "ABC":
     * </p>
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

    TypeParserBuilder preserveCharacterNormalizationForm();

    TypeParserBuilder toCharacterNormalizationFormNFC();

    TypeParserBuilder toCharacterNormalizationFormNFD();

    TypeParserBuilder toCharacterNormalizationFormNFKC();

    TypeParserBuilder toCharacterNormalizationFormNFKD();

    TypeParserBuilder acceptChar(char ch);

    TypeParserBuilder acceptChars(char... chars);

    /**
     * Configures the type-parser to accept characters inclusively within the range defined by the arguments. It will automatically correct the range
     * if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed, for example ('9', '0') instead of ('0', '9').
     *
     * @param inclusiveFrom the inclusive 'from' char.
     * @param inclusiveTo   the inclusive 'to' char.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder acceptCharRange(char inclusiveFrom, char inclusiveTo);

    TypeParserBuilder acceptCodePoint(int codePoint);

    TypeParserBuilder acceptCodePoints(int... codePoints);


    /**
     * Configures the type-parser to accept characters inclusively within the range defined by the arguments. It will automatically correct the range
     * if the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed, for example ('9', '0') instead of ('0', '9').
     *
     * @param inclusiveFrom the inclusive 'from' code-point.
     * @param inclusiveTo   the inclusive 'to' code-point.
     * @return this {@code TypeParserBuilder}.
     */
    TypeParserBuilder acceptCodePointRange(int inclusiveFrom, int inclusiveTo);

    /**
     * <p>Configures the type-parser to convert any {@code fromChar} in the input sequence to a {@code toChar}.</p>
     *
     * <p>By default, this method also configures the type-parser to accept {@code fromChar} characters in the input
     * sequence. It does this using the {@link #acceptChar(char)} method.</p>
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
    TypeParserBuilder convertChar(CharSequence fromCharSequence, CharSequence toCharSequence);

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

    /**
     * <p>This will configure the type-parser to remove all occurrences of the specified character, {@code 'ch'}, from the
     * parsed value.</p>
     *
     * <p>Specifying a character to remove automatically adds it to the set of accepted characters – so there is no need
     * to specifically configure the type-parser to accept the character using {@link #acceptChar(char)}.</p>
     *
     * <p><b>Example – removing all hyphens from a parsed value</b></p>
     *
     * <pre>
     * TypeParser.builder()
     *   .errorMessage("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-')
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * </pre>
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
     * to specifically configure the type-parser to accept the characters using {@link #acceptChars(char...)}.</p>
     *
     * <p><b>Example – removing all hyphens, en-dashes and em-dashes from a parsed value</b></p>
     *
     * <pre>
     * TypeParser.builder()
     *   .errorMessage("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('a', 'z')
     *   .acceptCharRange('A', 'Z')
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * </pre>
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
     * to the set of accepted characters – so there is no need to specifically configure the type-parser
     * to accept the characters using {@link #acceptChars(char...)}.</p>
     *
     * <p><b>Example – removing a char-sequence from the parsed value</b></p>
     *
     * <p>To ensure product codes are parsed with the undesired prefix "sku" removed then you could configure the
     * type-parser as follows:</p>
     *
     * <pre>
     * TypeParser.builder()
     *   .errorMessage("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('0', '9')
     *   .removeAllCharSequences("sku") // "sku" will be removed from parsed values
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * </pre>
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
     * to the set of accepted characters – so there is no need to specifically configure the type-parser
     * to accept the characters using {@link #acceptChars(char...)}.</p>
     *
     * <p><b>Example – removing char-sequences from the parsed value</b></p>
     *
     * <p>To ensure product codes are parsed with the undesired prefix of "sku" or "item" removed then you could configure the
     * type-parser as follows:</p>
     *
     * <pre>
     * TypeParser.builder()
     *   .errorMessage("must be an 8-character product code")
     *   .fixedSize(8)
     *   .acceptCharRange('0', '9')
     *   .removeAllCharSequences("sku", "item") // "sku" and "item" will be removed from parsed values
     *   .removeAllChar('-', '–', '—') // hyphen, en-dash, em-dash
     *   .removeAllWhitespace()
     *   .toUpperCase()
     *   .build();
     * </pre>
     *
     * @param charSequences the char-sequences you want the type-parser to remove from the parsed value
     * @return this {@code TypeParserBuilder}.
     * @see #removeAllCharSequences(CharSequence)
     */
    TypeParserBuilder removeAllCharSequences(CharSequence... charSequences);

    TypeParserBuilder removeAllCodePoints(int codePoint);

    TypeParserBuilder removeAllCodePoints(int... codePoints);

    TypeParserBuilder acceptSubset(Subset subset);

    TypeParserBuilder acceptSubsets(Subset... subsets);

    TypeParserBuilder acceptUnicodeCategory(Category category);

    TypeParserBuilder acceptUnicodeCategory(Category... categories);

    TypeParserBuilder acceptAllUnicodeLetters();

    /**
     * Configures the type parser to accepts all letters [a-zA-Z] that are in the <a href="https://unicode.org/charts/PDF/U0000.pdf">Basic Latin</a>
     * Unicode block which is equivalent to the ASCII character set.
     * <p>
     * If you wish for the letters to be converted to upper or lower case then remember to configure the {@link TypeParser} by calling
     * {@link #toLowerCase()} or {@link #toUpperCase()} in the {@link TypeParserBuilder}
     * <p>
     * If you want to accept <em>all</em> letters in the Unicode set (this could mean mixed-languages) then use: {@link #acceptAllUnicodeLetters()}
     * <p>
     * This method is a convenience method that invokes {@code acceptCharRange('a', 'z').acceptCharRange('A', 'Z');} on this builder.
     *
     * @return this {@code TypeParserBuilder}
     * @see #acceptUnicodeCategory(Category)
     * @see Category#LETTER
     * @see #toLowerCase()
     * @see #toUpperCase()
     */
    TypeParserBuilder acceptLettersAtoZ();

    /**
     * Configures the type parser to accepts all digits number [0-9] that are in the <a href="https://unicode.org/charts/PDF/U0000.pdf">Basic
     * Latin</a> Unicode block which is equivalent to the ASCII character set.
     * <p>
     * If you want to accept all decimal digits in another letters range then use the following:
     * <pre>
     *   acceptCharRange('०', '९') // Devanagari decimal digit range
     *   acceptCharRange('൦', '൯') // Malayalam decimal digit range
     * </pre>
     * <p>
     * If you want to accept <em>all</em> decimal digits in the Unicode set (and I don't think you do) then use the following:
     * <pre>
     *   acceptUnicodeCategory(Category.DECIMAL_DIGIT_NUMBER)
     * </pre>
     *
     * @return this {@code TypeParserBuilder}
     * @see #acceptUnicodeCategory(Category)
     * @see Category#DECIMAL_DIGIT_NUMBER
     */
    TypeParserBuilder acceptDigits0to9();

    /**
     * Accepts all a dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category.
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
     * <p><b>Note:</b> This conversion might make sense for certain data-types and not others. For example, in letters specific data-types you may
     * may wish to preserve letters specific dashes. For example, for an Armenian data-type, perhaps preserving the Armenian Hyphen
     * {@code '֊' (U+058A)} makes more sense than converting it to a hyphen.</p>
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
     * Imagine some data-type that is required to start with two alpha characters and is followed by 8 numeric digits. We could declare a regular
     * expression shown at ① and then configure the type parser to use the regular expression at ②:
     * <pre>
     *   private static final Pattern VALID_DATA_TYPE_PATTERN = Pattern.compile("[A-Z]{2}[0-9]{8}"); ①
     *
     *   private static final TypeParser TYPE_PARSER =
     *     TypeParser.builder(ExampleDataType.class)
     *       .errorMessage("must be a valid 10 character example code")
     *       .acceptLettersAtoZ()
     *       .acceptDigits0to9()
     *       .fixedSize(10)
     *       .removeAllWhitespace()
     *       .toUpperCase()
     *       .matchesRegex(VALID_DATA_TYPE_PATTERN) ②
     *       .build();
     * </pre>
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
     * <p>Example usage:</p>
     * <pre>
     *   private static final TypeParser TYPE_PARSER =
     *     TypeParser.builder(ExampleDataType.class)
     *       .errorMessage("must be a valid 6..8 character example code")
     *       .acceptLettersAtoZ()
     *       .acceptDigits0to9()
     *       .minSize(6)
     *       .maxSize(8)
     *       .removeAllWhitespace()
     *       .toUpperCase()
     *       .customValidator(ExampleDataType::isValidExampleCode)  ①
     *       .build();
     * </pre>
     * where the custom validation function ① is defined as:
     * <pre>
     *   private static boolean isValidExampleCode(final String value) {
     *     boolean isValid = false;
     *     // ... your validation code
     *     if (isValid) {
     *       return Boolean.TRUE;
     *     }
     *     return Boolean.FALSE;
     *   }
     * </pre>
     * <p>where:</p>
     *
     * @param validationFunction a static function, or a functional interface, that accepts the fully parsed value as a {@link String} argument and
     *                           returns a boolean value indicating {@code true} if the provided value is <em>valid</em>. It can either return
     *                           {@code false} or throw an exception to indicate that the provided value is <em>invalid</em>.
     * @return this {@code TypeParserBuilder}
     * @see Predicate
     */
    TypeParserBuilder customValidator(Predicate<String> validationFunction);

    /**
     * Create an immutable, threadsafe {@link TypeParser} instance.
     * @return an immutable, threadsafe {@link TypeParser} instance.
     */
    TypeParser build();
  }
}
