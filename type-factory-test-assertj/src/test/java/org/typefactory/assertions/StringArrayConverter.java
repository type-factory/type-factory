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
package org.typefactory.assertions;


import java.util.Arrays;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class StringArrayConverter implements ArgumentConverter {

  private static final String [] EMPTY_STRING_ARRAY = new String[0];

  @Override
  public String[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      if (source == null) {
        return null;
      }
      String value = ((String) source).trim();
      if (!value.startsWith("[") || !value.endsWith("]")) {
        throw new IllegalArgumentException(
            "The argument should be a string written as array of comma separate values all within square brackets. Invalid value: " + source);
      }
      value = value.substring(1, value.length() - 1);
      if (value.isBlank()) {
        return EMPTY_STRING_ARRAY;
      }
      return Arrays.stream(value.split(",\\s*"))
          .map(v -> "null".equals(v.trim()) ? null : v)
          .toList()
          .toArray(new String[0]);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "The argument should be a string written as array of comma separate values all within square brackets. Invalid value: " + source, e);
    }
  }
}