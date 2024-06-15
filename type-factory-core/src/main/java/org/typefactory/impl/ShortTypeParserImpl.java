package org.typefactory.impl;

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
  public <T extends ShortType> T parse(final CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToShortType(value, constructorOrFactoryMethod);
  }

  @Override
  public <T extends ShortType> T parse(final CharSequence value, final Locale locale, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToShortType(value, locale, constructorOrFactoryMethod);
  }

  @Override
  public Short parse(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToShort(originalValue);
  }

  @Override
  public Short parse(final CharSequence originalValue, final Locale locale) throws InvalidValueException {
    return wrappedParser.parseToShort(originalValue, locale);
  }
}
