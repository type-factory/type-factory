package org.typefactory.impl;

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
  public <T extends ShortType> T parseToShortType(final CharSequence value, Function<Short, T> constructorOrFactoryMethod) throws InvalidValueException {
    return wrappedParser.parseToShortType(value, constructorOrFactoryMethod);
  }

  @Override
  public Short parseToShort(final CharSequence originalValue) throws InvalidValueException {
    return wrappedParser.parseToShort(originalValue);
  }
}
