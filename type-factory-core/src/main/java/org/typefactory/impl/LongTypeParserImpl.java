package org.typefactory.impl;

import java.util.function.IntFunction;
import java.util.function.LongFunction;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;

final class LongTypeParserImpl implements LongTypeParser {

  private final IntegralNumericTypeParserImpl wrappedParser;

  LongTypeParserImpl(final IntegralNumericTypeParserImpl wrappedParser) {
    this.wrappedParser = wrappedParser;
  }

  @Override
  public <T extends LongType> T parseToLongType(final CharSequence value, LongFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToLongType(value, constructorOrFactoryMethod);
  }

  <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToIntegerType(value, constructorOrFactoryMethod);
  }

  @Override
  public Long parseToLong(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToLong(originalValue);
  }

  Integer parseToInteger(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToInteger(originalValue);
  }
}
