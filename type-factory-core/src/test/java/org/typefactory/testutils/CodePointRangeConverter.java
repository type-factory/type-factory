/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.testutils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.Subset.CodePointRange;

public class CodePointRangeConverter implements ArgumentConverter {

  @Override
  public CodePointRange convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      final String rangeString = ((String) source)
          .replaceAll("\\s+", "")
          .replace("0x", "")
          .replace("_", "");
      final long rangeValue = Long.parseLong(rangeString, 16);
        return switch (rangeString.length()) {
          case 4 -> new CodePointRange((int) (rangeValue >>> 8), (char) (rangeValue & 0x00_ff));
          case 8 -> new CodePointRange((int) (rangeValue >>> 16), (int) (rangeValue & 0x0000_ffff));
          case 16 -> new CodePointRange((int) (rangeValue >>> 32), (int) (rangeValue & 0x00000000_ffffffffL));
          default -> throw new IllegalArgumentException(
              "The argument should be a string defining one or more comma-separated ranges in format `0x0000_0000, 0x1111_2222`: " + source);
        };
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string defining one or more comma-separated ranges in format `0x0000_0000, 0x1111_2222`: " + source, e);
    }
  }
}
