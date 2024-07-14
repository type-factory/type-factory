package org.typefactory.impl;

import java.math.BigInteger;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;

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
  public <T extends IntegerType> T parse(final CharSequence value, Function<Integer, T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToIntegerType(value, constructorOrFactoryMethod);
  }

  @Override
  public <T extends IntegerType> T parse(final CharSequence value, final Locale locale, Function<Integer, T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToIntegerType(value, locale, constructorOrFactoryMethod);
  }

  @Override
  public Integer parse(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToInteger(originalValue);
  }

  @Override
  public Integer parse(final CharSequence originalValue, final Locale locale) throws InvalidValueException {
    return wrappedParser.parseToInteger(originalValue, locale);
  }

}
