package org.typefactory.impl;

import java.math.BigInteger;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;
import org.typefactory.NumberFormat;

final class IntegerTypeParserImpl implements IntegerTypeParser {

  private final LongTypeParserImpl wrappedParser;

  IntegerTypeParserImpl(final LongTypeParserImpl wrappedParser) {
    this.wrappedParser = wrappedParser;
  }

  @Override
  public int getRadix() {
    return wrappedParser.getRadix();
  }

  @Override
  public Integer of(final Short value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.intValue();
  }

  @Override
  public Integer of(final Integer value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public Integer of(final Long value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.intValue();
  }

  @Override
  public Integer of(final BigInteger value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.intValue();
  }

  @Override
  public int of(final short value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public int of(final int value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public int of(final long value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return (int)value;
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <S extends IntegerType> S of(
      final Short value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <S extends IntegerType> S of(
      final Integer value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <S extends IntegerType> S of(
      final Long value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <S extends IntegerType> S of(
      final BigInteger value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, S> constructorOrFactoryMethod) throws InvalidValueException {

    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public Integer parse(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(originalValue);
    return parsedValue == null
        ? null
        : parsedValue.intValue();
  }

  @Override
  public Integer parse(final CharSequence originalValue, final Locale locale) throws InvalidValueException {
    return parse(originalValue, NumberFormat.of(locale));
  }

  @Override
  public Integer parse(final CharSequence originalValue, final NumberFormat numberFormat) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(originalValue, numberFormat);
    return parsedValue == null
        ? null
        : parsedValue.intValue();
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <T extends IntegerType> T parse(
      final CharSequence value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, T> constructorOrFactoryMethod) throws InvalidValueException {

    final Long parsedValue = wrappedParser.parse(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue.intValue());
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <T extends IntegerType> T parse(
      final CharSequence value,
      final Locale locale,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final Function<Integer, T> constructorOrFactoryMethod) throws InvalidValueException {

    return parse(value, NumberFormat.of(locale), constructorOrFactoryMethod);
  }

  @Override
  @SuppressWarnings("java:S4276") // SonarQube Refactor this code to use the more specialised Functional Interface 'IntFunction<S>'
  public <T extends IntegerType> T parse(
      final CharSequence value,
      // Using Function<Integer, S> instead of IntFunction<S> to allow for method references that accept a Integer value that may be null.
      final NumberFormat numberFormat, final Function<Integer, T> constructorOrFactoryMethod) throws InvalidValueException {

    final Long parsedValue = wrappedParser.parse(value, numberFormat);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue.intValue());
  }

}
