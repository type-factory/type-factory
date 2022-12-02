package org.typefactory.testutils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class RangeAsLongConverter implements ArgumentConverter {

  @Override
  public Long convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
      return Long.parseLong(value, 16);
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining a range in format `0x00000000_00000000`: " + source, e);
    }
  }
}
