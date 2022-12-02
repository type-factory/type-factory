package org.typefactory.testutils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class RangeAsIntegerConverter implements ArgumentConverter {

  @Override
  public Integer convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
      return (int) Long.parseLong(value, 16);
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining a range in format `0x0000_0000`: " + source, e);
    }
  }
}
