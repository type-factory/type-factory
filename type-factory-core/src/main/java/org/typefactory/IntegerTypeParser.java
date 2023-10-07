package org.typefactory;

import java.util.function.IntFunction;

public interface IntegerTypeParser extends TypeParser {

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

  interface IntegerTypeParserBuilder extends TypeParserBuilder {
    /**
     * Set a minimum value that will be used to validate values that are parsed to an Integer value.
     * @param minValue the minimum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    IntegerTypeParserBuilder minValueInclusive(int minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to an Integer value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    IntegerTypeParserBuilder maxValueInclusive(int maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to an Integer value.
     * @param minValue the minimum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    IntegerTypeParserBuilder minValueExclusive(int minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to an Integer value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    IntegerTypeParserBuilder maxValueExclusive(int maxValue);

  }

}
