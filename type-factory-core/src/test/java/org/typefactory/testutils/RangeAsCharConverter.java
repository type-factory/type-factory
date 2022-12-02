package org.typefactory.testutils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class RangeAsCharConverter implements ArgumentConverter {

  @Override
  public Character convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      final String value = ((String) source).replaceFirst("0x", "").replaceFirst("_", "");
      return (char) Integer.parseInt(value, 16);
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining a range in format `0x00_00`: " + source, e);
    }
  }
}
