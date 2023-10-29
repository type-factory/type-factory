package org.typefactory;

import java.math.BigInteger;
import java.util.function.Function;

public interface BigIntegerTypeParser extends TypeParser {

  /**
   * <p>Parse the provided {@code value} into a {@link BigInteger} value.</p>
   *
   * <p><b>Example 1 – parse to a {@code BigInteger} variable</b></p>
   *
   * <pre>{@code
   * BigInteger someVariable = TYPE_PARSER.parseToBigInteger(value);
   * }</pre>
   *
   * <p><b>Example 2 – parse to a custom type</b></p>
   *
   * <p>For example, the {@code UniqueToken} custom type below uses the type-parser directly in its
   * constructor to parse the provided {@code value} to create the {@code UniqueToken} instance:</p>
   *
   * <pre>{@code
   * public final class UniqueToken extends BigIntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   public UniqueToken(final BigInteger value) {
   *     super(TYPE_PARSER.parseToBigIntegerType(value));
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> instantiating directly into a constructor as above may create an instance with
   * a null internal value. The instantiated object can be interrogated for a null internal value using {@link BigIntegerType#isNull() isNull()}. An
   * alternative is to use a factory method using the example shown in {@link #parseToBigIntegerType(CharSequence, Function)} instead.</p>
   *
   * @param value the value to parse into a {@link BigInteger}.
   * @return parses the provided {@code value} into a {@link BigInteger} value. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a {@link BigInteger}.
   * @see #parseToBigIntegerType(CharSequence, Function)
   */
  BigInteger parseToBigInteger(CharSequence value) throws InvalidValueException;

  /**
   * <p>Parse the provided {@code value} into a custom type that extends {@link BigIntegerType} using the provided
   * {@code constructorOrFactoryMethod}.</p>
   *
   * <p>For example, the {@code UniqueToken} type below uses a factory method,
   * {@code of(CharSequence value)}, to create instances using the {@code parseToBigIntegerType} method:</p>
   *
   * <pre>{@code
   * public final class UniqueToken extends BigIntegerType {
   *
   *   private static final TypeParser TYPE_PARSER = TypeParser.builder()...build();
   *
   *   private UniqueToken(final BigInteger value) {
   *     super(value);
   *   }
   *
   *   public static UniqueToken of(final CharSequence value) {
   *     return TYPE_PARSER.parseToBigIntegerType(value, UniqueToken::new);
   *   }
   * }
   * }</pre>
   *
   * <p><b>Note:</b> use the {@link #parseToBigInteger(CharSequence)} method to simply parse into a {@link BigInteger} value.</p>
   *
   * @param value                      the value to parse into a custom type.
   * @param constructorOrFactoryMethod the functional interface that will be used to instantiate the custom type using the parsed value.
   * @param <T>                        a custom type that extends {@link BigIntegerType}
   * @return a custom type instance that extends {@link BigIntegerType}. May return {@code null} if the {@link TypeParser} was configured to
   * {@link TypeParserBuilder#preserveNullAndEmpty() preserveNullAndEmpty} or {@link TypeParserBuilder#convertEmptyToNull() convertEmptyToNull}.
   * @throws InvalidValueException if the provided {@code value} cannot be parsed in into a custom type.
   * @see #parseToString(CharSequence)
   */
  <T extends BigIntegerType> T parseToBigIntegerType(CharSequence value, Function<BigInteger, T> constructorOrFactoryMethod) throws InvalidValueException;


  interface BigIntegerTypeParserBuilder extends TypeParserBuilder {
    /**
     * Set a minimum value that will be used to validate values that are parsed to a BigInteger value.
     * @param minValue the minimum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    TypeParserBuilder minValueInclusive(BigInteger minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a BigInteger value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    TypeParserBuilder maxValueInclusive(BigInteger maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a BigInteger value.
     * @param minValue the minimum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    TypeParserBuilder minValueExclusive(BigInteger minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a BigInteger value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    TypeParserBuilder maxValueExclusive(BigInteger maxValue);
  }

}
