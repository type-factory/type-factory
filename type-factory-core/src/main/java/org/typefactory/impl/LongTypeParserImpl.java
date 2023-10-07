package org.typefactory.impl;

import java.util.function.LongFunction;
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

  @Override
  public Long parseToLong(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToLong(originalValue);
  }

}
