package org.typefactory.testutils;

import java.util.Arrays;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.Subset.CodePointRange;

public class RangesAsCharArrayConverter implements ArgumentConverter {

  @Override
  public char[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null || ((String) source).isBlank()) {
        return new char[0];
      }
      final String[] singleByteRangeStrings = ((String)source)
          .replaceAll("\\s+", "")
          .replace("0x", "")
          .replace("_", "")
          .split(",");
      final int[] ints = Arrays.stream(singleByteRangeStrings)
          .mapToInt(i -> Integer.parseInt(i, 16))
          .toArray();
      final char[] singleByteRanges = new char[ints.length];
      for (int i = 0; i < ints.length; ++i) {
        singleByteRanges[i] = (char) ints[i];
      }
      return singleByteRanges;
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining one or more comma-separated ranges in format `0x00_00, 0x11_22`: " + source, e);
    }
  }
}
