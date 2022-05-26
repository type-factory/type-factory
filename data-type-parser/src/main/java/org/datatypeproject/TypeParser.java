package org.datatypeproject;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

public interface TypeParser {

  static TypeParserBuilder builder(Class<?> targetClass) {
    return new TypeParserBuilder(targetClass);
  }

  <T extends StringType> T parseToStringType(CharSequence value, Function<String, T> constructorOrFactoryMethod);

  <T extends ShortType> T parseToShortType(CharSequence value, Function<Short, T> constructorOrFactoryMethod);

  <T extends IntegerType> T parseToIntegerType(CharSequence value, IntFunction<T> constructorOrFactoryMethod);

  <T extends LongType> T parseToLongType(CharSequence value, LongFunction<T> constructorOrFactoryMethod);

  String parseToString(CharSequence value);

  Short parseToShort(CharSequence value);

  Integer parseToInteger(CharSequence value);

  Long parseToLong(CharSequence value);
}
