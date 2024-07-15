package org.typefactory.impl;

import java.math.BigInteger;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.InvalidValueException;
import org.typefactory.ShortType;
import org.typefactory.ShortTypeParser;

final class ShortTypeParserImpl implements ShortTypeParser {

  private final LongTypeParserImpl wrappedParser;

  ShortTypeParserImpl(final LongTypeParserImpl wrappedParser) {
    this.wrappedParser = wrappedParser;
  }

  @Override
  public int getRadix() {
    return wrappedParser.getRadix();
  }

  @Override
  public Short of(final Short value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public Short of(final Integer value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.shortValue();
  }

  @Override
  public Short of(final Long value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.shortValue();
  }

  @Override
  public Short of(final BigInteger value) throws InvalidValueException {
    if (value == null) {
      return null;
    }
    wrappedParser.checkValueIsWithinBounds(value);
    return value.shortValue();
  }

  @Override
  public short of(final short value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return value;
  }

  @Override
  public short of(final int value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return (short)value;
  }

  @Override
  public short of(final long value) throws InvalidValueException {
    wrappedParser.checkValueIsWithinBounds(value);
    return (short)value;
  }

  @Override
  public <S extends ShortType> S of(Short value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends ShortType> S of(Integer value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends ShortType> S of(Long value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public <S extends ShortType> S of(BigInteger value, Function<Short, S> constructorOrFactoryMethod) throws InvalidValueException {
    return value == null
        ? null
        : constructorOrFactoryMethod.apply(of(value));
  }

  @Override
  public Short parse(final CharSequence originalValue) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(originalValue);
    return parsedValue == null
        ? null
        : parsedValue.shortValue();
  }

  @Override
  public Short parse(final CharSequence originalValue, final Locale locale) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(originalValue, locale);
    return parsedValue == null
        ? null
        : parsedValue.shortValue();
  }

  @Override
  public <T extends ShortType> T parse(final CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(value);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue.shortValue());
  }

  @Override
  public <T extends ShortType> T parse(final CharSequence value, final Locale locale, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    final Long parsedValue = wrappedParser.parse(value, locale);
    return parsedValue == null
        ? null
        : constructorOrFactoryMethod.apply(parsedValue.shortValue());
  }
}
