package org.typefactory.testutils;

import java.util.Arrays;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class RangesAsLongArrayConverter implements ArgumentConverter {

  @Override
  public long[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null || ((String) source).isBlank()) {
        return new long[0];
      }
      final String[] tripleByteRangeStrings = ((String) source)
          .replaceAll("\\s+", "")
          .replace("0x", "")
          .replace("_", "")
          .split(",");
      return Arrays.stream(tripleByteRangeStrings)
          .mapToLong(i -> Long.parseLong(i, 16))
          .toArray();
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining one or more comma-separated ranges in format `0x00000000_00000000, 0x00111111_00222222`: " + source, e);
    }
  }
}
