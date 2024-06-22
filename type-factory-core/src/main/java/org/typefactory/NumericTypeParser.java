package org.typefactory;

import java.util.Locale;
import java.util.function.Function;

public interface NumericTypeParser<V extends Number & Comparable<V>, T extends NumberType<V, T>> {

  /**
   * Returns the radix (numeric-base) of the type-parser which will be greater-than or equal to 2.
   * @return the radix (numeric-base) of the type-parser.
   */
  int getRadix();

  V of(Short value) throws InvalidValueException;

  V of(Integer value) throws InvalidValueException;

  V of(Long value) throws InvalidValueException;

  V parse(CharSequence value) throws InvalidValueException;

  V parse(CharSequence value, final Locale locale) throws InvalidValueException;

  <S extends T> S parse(CharSequence value, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

  <S extends T> S parse(CharSequence value, final Locale locale, Function<V, S> constructorOrFactoryMethod) throws InvalidValueException;

}
