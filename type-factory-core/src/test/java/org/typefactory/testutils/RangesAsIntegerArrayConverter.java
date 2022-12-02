package org.typefactory.testutils;

import java.util.Arrays;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.Subset.CodePointRange;

public class RangesAsIntegerArrayConverter implements ArgumentConverter {

  @Override
  public int[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null || ((String) source).isBlank()) {
        return new int[0];
      }
      final String[] doubleByteRangeStrings = ((String) source)
          .replaceAll("\\s+", "")
          .replace("0x", "")
          .replace("_", "")
          .split(",");
      return Arrays.stream(doubleByteRangeStrings)
          .mapToInt(i -> Integer.parseInt(i, 16))
          .toArray();
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining one or more comma-separated ranges in format `0x0000_0000, 0x1111_2222`: " + source, e);
    }
  }
}
