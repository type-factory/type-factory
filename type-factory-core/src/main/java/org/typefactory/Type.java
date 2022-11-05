package org.typefactory;

import java.io.Serializable;

/**
 * <p>The interface that custom types may implement to take advantage of default methods, static methods.</p>
 *
 * <p>This interface is part of a set of interfaces and abstract classes that provide
 * a convention-over-configuration way to define your custom types so that they all work in a predictable fashion and provide a similar set of default
 * methods and static methods.</p>
 *
 * <p>It will usually make more sense to actually have your custom types extend one of the following
 * abstract classes that implement this interface:</p>
 * <ul>
 *   <li>{@link StringType} – for custom data types that represent a string or sequence of characters</li>
 *   <li>{@link ShortType} – for custom data types that represent a 2-byte numeric value</li>
 *   <li>{@link IntegerType} – for custom data types that represent a 4-byte numeric value</li>
 *   <li>{@link LongType} – for custom data types that represent an 8-byte numeric value</li>
 * </ul>
 *
 * @param <V> the type of the wrapped value
 * @param <T> the type of the custom data type class
 * @see StringType
 * @see ShortType
 * @see IntegerType
 * @see LongType
 */
public interface Type<V, T extends Type<V, T>> extends Serializable {

  /**
   * Returns the internal value as set in the constructor. Will return {@code null} when internal value is {@code null}.
   *
   * @return the internal value as set in the constructor. Will return {@code null} when internal value is {@code null}.
   */
  V value();

  /**
   * <p>Returns {@code true} if the result of {@link #value()} is {@code null}.
   * Whether this make sense for your use-case depends on your design.</p>
   *
   * <p>If you would like
   * to ensure that the internal value cannot be {@code null} then consider doing either of:</p>
   * <ul>
   *   <li>Throw an {@link NullPointerException} in your public type constructors private and use a factory method, or</li>
   *   <li>Make your type constructors private and use a factory method to instantiate your type values using something like
   *     <pre>{@code
   *     public class SomeType extends StringType {
   *       // The type parser that parses values
   *       private static final TYPE_PARSER = TypeParser.builder()
   *         .preserverNullAndEmpty()
   *         ...
   *         .build();
   *       // private constructor
   *       private SomeType(final String parsedValue) {...}
   *       // public factory method
   *       public static of(final String rawValue) {
   *         // Will simply return null when rawValue is null.
   *         return TYPE_PARSER.parseToStringType(rawValue, SomeType::constructor)
   *       }
   *     }
   *     }</pre>
   *   </li>
   * </ul>
   *
   * @return {@code true} if the result of {@link #value()} is {@code null}, and {@code false} otherwise.
   */
  default boolean isNull() {
    return value() == null;
  }

  /**
   * <p>Provides a null-safe way to test whether the provided {@code type} value is {@code null} or it contains a null value.
   * Returns {@code true} if the provided {@code type} value is null, otherwise returns the result of {@link #isNull() type.isNull()}.</p>
   *
   * @param type the type value that you wish to test is null or contains a null value.
   * @param <T>  the provided {@code type} value must implement implement {@link Type}
   * @return true if the provided {@code type} value is {@code null} or it contains a null value, and {@code false} otherwise.
   * @see #isNull()
   */
  static <T extends Type<?, T>> boolean isNull(final T type) {
    return type == null || type.isNull();
  }


  /**
   * Return the provided value if non-null otherwise returns the provided default value.
   *
   * @param value        the value to check if null and to return if non-null
   * @param defaultValue the default to return if {@code value} was null
   * @param <T>          both the {@code value} and {@code defaultValue} type value must be of the same type and implement {@link Type}
   * @return the provided value if non-null otherwise returns the provided default value.
   */
  static <T extends Type<?, T>> T defaultIfNull(final T value, final T defaultValue) {
    return isNull(value) ? defaultValue : value;
  }

  /**
   * <p>Null-safe comparison of two types returning {@code true} if they are both equal or both {@code null}.</p>
   *
   * @param value1 the first type value, may be {@code null}
   * @param value2 the second type value, may be {@code null}
   * @param <T>    both type value must be of the same type and implement {@link Type}
   * @return true if the two type values are equal, or both {@code null}
   */
  static <T extends Type<?, T>> boolean equals(final T value1, final T value2) {
    if (value1 == value2) {
      return true;
    }
    if (value1 == null || value2 == null) {
      return false;
    }
    return value1.equals(value2);
  }

}
