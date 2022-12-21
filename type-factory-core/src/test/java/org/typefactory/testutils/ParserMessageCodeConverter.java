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

import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_INVALID_CHARACTER;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_INVALID_CONTROL_CHARACTER;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_INVALID_QUOTE_CHARACTER;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_INVALID_WHITESPACE_CHARACTER;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_TOO_LONG;
import static org.typefactory.InvalidValueException.ParserMessageCode.INVALID_VALUE_TOO_SHORT;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.typefactory.InvalidValueException.ParserMessageCode;

public class ParserMessageCodeConverter implements ArgumentConverter {

  @Override
  public ParserMessageCode convert(Object source, ParameterContext context) throws ArgumentConversionException {
    try {
      return switch ((String) source) {
        case "INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN" -> INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN;
        case "INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION" -> INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION;
        case "INVALID_VALUE_INVALID_CHARACTER" -> INVALID_VALUE_INVALID_CHARACTER;
        case "INVALID_VALUE_INVALID_CONTROL_CHARACTER" -> INVALID_VALUE_INVALID_CONTROL_CHARACTER;
        case "INVALID_VALUE_INVALID_QUOTE_CHARACTER" -> INVALID_VALUE_INVALID_QUOTE_CHARACTER;
        case "INVALID_VALUE_INVALID_WHITESPACE_CHARACTER" -> INVALID_VALUE_INVALID_WHITESPACE_CHARACTER;
        case "INVALID_VALUE_TOO_LONG" -> INVALID_VALUE_TOO_LONG;
        case "INVALID_VALUE_TOO_SHORT" -> INVALID_VALUE_TOO_SHORT;
        default -> throw new IllegalArgumentException("The argument should be a string matching the ParserMessageCode constants – '" + source + "' was not recognised.");
      };
    } catch (Exception e) {
      throw new IllegalArgumentException("The argument should be a string matching the ParserMessageCode constants – '" + source + "' was not recognised.", e);
    }
  }
}
