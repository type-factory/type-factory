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

public class CodePointSequenceConverter implements ArgumentConverter {

  @Override
  public int[] convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      final int [] codePoints = ((String) source).codePoints().toArray();
      return codePoints;
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string to be converted into an array of code-points. Invalid value: " + source, e);
    }
  }
}
