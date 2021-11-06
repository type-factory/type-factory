package land.artli.easytype;

import java.util.function.Function;

public interface TypeParser {

  static TypeParserBuilder builder(Class<? extends CharType<?>> targetClass) {
    return new TypeParserBuilder(targetClass);
  }

  <T extends CharType<?>> T parse(CharSequence value, Function<String, T> constructorOrFactoryMethod);

  String parse(CharSequence value);

}
