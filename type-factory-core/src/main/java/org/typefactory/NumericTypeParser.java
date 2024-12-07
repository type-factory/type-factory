package org.typefactory;

import java.math.BigInteger;
import java.util.Locale;
import java.util.function.Function;

/**
 * <p>Defines a type parser for numeric types that can parse and validate values of the type {@code V}, where {@code V} is a subclass of
 * {@link Number} and {@link Comparable} – for example, {@link Short}, {@link Integer}, {@link Long}, {@link BigInteger}</p>
 *
 * <p>This interface provides methods for parsing and validating provided values and converting them to instances of the type {@code V} after
 * verifying that they conform to all the type parser rules.</p>
 *
 * <p>No conversion is required when the numeric input type is the same as the output type {@code V}</p>
 *
 * <p>Implementations of this interface are expected to be immutable and thread-safe.</p>
 *
 * @param <V> the subclass type of the {@link Number}&amp;{@link Comparable} instance to be created – for example, {@link Short}, {@link Integer},
 *            {@link Long}, {@link BigInteger}.
 * @param <T> the subclass type of the {@link NumberType} instance to be created which itself extends {@link Number} and implements
 *            {@link Comparable}.
 */
public interface NumericTypeParser<V extends Number & Comparable<V>, T extends NumberType<V, T>> {

  /**
   * Returns the radix (numeric-base) of the type-parser which will be greater-than or equal to 2.
   *
   * @return the radix (numeric-base) of the type-parser.
   */
  int getRadix();

  /**
   * Returns the value of the given short value as an instance of the type {@code V}, a subclass of {@link Number}, after verifying that it conforms
   * to all the type parser rules.
   *
   * @param value the short value to be validated by the type parser.
   * @return the value of the given short value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  V of(Short value) throws InvalidValueException;

  /**
   * Returns the value of the given integer value as an instance of the type {@code V}, a subclass of {@link Number}, after verifying that it conforms
   * to all the type parser rules.
   *
   * @param value the integer value to be validated by the type parser.
   * @return the value of the given integer value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  V of(Integer value) throws InvalidValueException;

  /**
   * Returns the value of the given long value as an instance of the type {@code V}, a subclass of {@link Number}, after verifying that it conforms to
   * all the type parser rules.
   *
   * @param value the long value to be validated by the type parser.
   * @return the value of the given long value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  V of(Long value) throws InvalidValueException;

  /**
   * Returns the value of the given big-integer value as an instance of the type {@code V}, a subclass of {@link Number}, after verifying that it
   * conforms to all the type parser rules.
   *
   * @param value the big-integer value to be validated by the type parser.
   * @return the value of the given big-integer value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  V of(BigInteger value) throws InvalidValueException;

  /**
   * Returns the value of the given short value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after verifying that it
   * conforms to all the type parser rules.
   *
   * @param value                      the short value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given short value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  <S extends T> S of(Short value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given integer value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after verifying that
   * it conforms to all the type parser rules.
   *
   * @param value                      the integer value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given integer value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  <S extends T> S of(Integer value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given long value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after verifying that it
   * conforms to all the type parser rules.
   *
   * @param value                      the long value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given long value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  <S extends T> S of(Long value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given big-integer value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after verifying
   * that it conforms to all the type parser rules.
   *
   * @param value                      the big-integer value to be validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given big-integer value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to conform to the type parser rules.
   */
  <S extends T> S of(BigInteger value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given char-sequence value as an instance of the type {@code V}, a subclass of {@link Number}, after parsing and
   * verifying that it conforms to all the type parser rules.
   *
   * @param value the char-sequence value to be parsed and validated by the type parser.
   * @return the value of the given char-sequence value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to be parsed or does not conform to the type parser rules.
   */
  V parse(CharSequence value) throws InvalidValueException;

  /**
   * Returns the value of the given char-sequence value as an instance of the type {@code V}, a subclass of {@link Number}, after parsing and
   * verifying that it conforms to all the type parser rules.
   *
   * @param value  the char-sequence value to be parsed and validated by the type parser.
   * @param locale the locale to be used for parsing the value. This will determine the locale-specific grouping separators (thousands separators) the
   *               type parser will consider when parsing.
   * @return the value of the given char-sequence value as an instance of the type {@code V}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to be parsed or does not conform to the type parser rules.
   */
  V parse(CharSequence value, final Locale locale) throws InvalidValueException;

  V parse(CharSequence value, final NumberFormat numberFormat) throws InvalidValueException;


  /**
   * Returns the value of the given char-sequence value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after parsing
   * and verifying that it conforms to all the type parser rules.
   *
   * @param value                      the char-sequence value to be parsed and validated by the type parser.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given char-sequence value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to be parsed or does not conform to the type parser rules.
   */
  <S extends T> S parse(CharSequence value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  /**
   * Returns the value of the given char-sequence value as an instance of {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number}, after parsing
   * and verifying that it conforms to all the type parser rules.
   *
   * @param value                      the char-sequence value to be parsed and validated by the type parser.
   * @param locale                     the locale to be used for parsing the value. This will determine the locale-specific grouping separators
   *                                   (thousands separators) the type parser will consider when parsing.
   * @param constructorOrFactoryMethod the constructor or factory method to be used for creating the instance of the type
   *                                   {@code S}, a subclass of {@link NumberType}.
   * @param <S>                        the subclass type of the {@link NumberType} instance to be created.
   * @return the value of the given char-sequence value as an instance of the type {@code S}, a subclass of {@link NumberType}, a subclass of {@link Number},
   * @throws InvalidValueException if the given value fails to be parsed or does not conform to the type parser rules.
   */
  <S extends T> S parse(CharSequence value, final Locale locale, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  <S extends T> S parse(CharSequence value, final NumberFormat numberFormat, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

}
