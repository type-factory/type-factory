package org.typefactory.testutils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.Subset.CodePointRange;

public class CodePointRangeArrayConverter implements ArgumentConverter {

  @Override
  public CodePointRange[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null || ((String) source).isBlank()) {
        return new CodePointRange[0];
      }
      final String[] rangeStrings = ((String) source)
          .replaceAll("\\s+", "")
          .replace("0x", "")
          .replace("_", "")
          .split(",");
      final CodePointRange[] ranges = new CodePointRange[rangeStrings.length];
      for (int i = 0; i < ranges.length; ++i) {
        final long rangeValue = Long.parseLong(rangeStrings[i], 16);
        ranges[i] = switch (rangeStrings[i].length()) {
          case 4 -> new CodePointRange((int) (rangeValue >>> 8), (char) (rangeValue & 0x00_ff));
          case 8 -> new CodePointRange((int) (rangeValue >>> 16), (int) (rangeValue & 0x0000_ffff));
          case 16 -> new CodePointRange((int) (rangeValue >>> 32), (int) (rangeValue & 0x00000000_ffffffffL));
          default -> throw new IllegalArgumentException(
              "The argument should be a string defining one or more comma-separated ranges in format `0x0000_0000, 0x1111_2222`: " + source);
        };
      }
      return ranges;
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "The argument should be a string defining one or more comma-separated ranges in format `0x0000_0000, 0x1111_2222`: " + source, e);
    }
  }
}
