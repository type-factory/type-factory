package org.typefactory;

import java.util.function.Function;

public interface ShortTypeParser extends TypeParser {

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

  interface ShortTypeParserBuilder extends TypeParserBuilder {
    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    ShortTypeParserBuilder minValueInclusive(short minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum inclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    ShortTypeParserBuilder maxValueInclusive(short maxValue);

    /**
     * Set a minimum value that will be used to validate values that are parsed to a Short value.
     * @param minValue the minimum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    ShortTypeParserBuilder minValueExclusive(short minValue);

    /**
     * Set a maximum value that will be used to validate values that are parsed to a Short value.
     * @param maxValue the maximum exclusive value allowed.
     * @return this {@code TypeParserBuilder}
     */
    ShortTypeParserBuilder maxValueExclusive(short maxValue);

  }

}
