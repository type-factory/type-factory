package org.typefactory.impl;

import java.util.function.IntFunction;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;

final class IntegerTypeParserImpl implements IntegerTypeParser {

  private final LongTypeParserImpl wrappedParser;

  IntegerTypeParserImpl(final LongTypeParserImpl wrappedParser) {
    this.wrappedParser = wrappedParser;
  }

  @Override
  public <T extends IntegerType> T parseToIntegerType(final CharSequence value, IntFunction<T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToIntegerType(value, constructorOrFactoryMethod);
  }

  @Override
  public Integer parseToInteger(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToInteger(originalValue);
  }

}
