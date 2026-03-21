package org.typefactory.assertions;

import java.util.Arrays;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.Category;

public class CategoryArrayConverter implements ArgumentConverter {

  private static final Category [] EMPTY_CATEGORY_ARRAY = new Category[0];

  @Override
  public Category[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null) {
        return null;
      }
      String value = ((String) source).trim();
      if (!value.startsWith("[") || !value.endsWith("]")) {
        throw new IllegalArgumentException(
            "The argument should be a written as array of comma separate values all within square brackets. Invalid value: " + source);
      }
      value = value.substring(1, value.length() - 1);
      if (value.isBlank()) {
        return EMPTY_CATEGORY_ARRAY;
      }
      return Arrays.stream(value.split("\\s*,\\s*"))
          .map(v -> "null".equals(v.trim()) ? null : v)
          .map(Category::valueOf)
          .toList()
          .toArray(new Category[0]);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "The argument should be written as array of comma separate values all within square brackets. Invalid value: " + source, e);
    }
  }

}
