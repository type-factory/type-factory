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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.testutils.ParserMessageCodeConverter;

class InvalidValueException_ParserMessageCodeTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      INVALID_VALUE_DOES_NOT_MATCH_REGEX_PATTERN    | invalid_value_does_not_match_regex_pattern    | Invalid value - does not match regular-expression pattern {0}
      INVALID_VALUE_DOES_NOT_PASS_CUSTOM_VALIDATION | invalid_value_does_not_pass_custom_validation | Invalid value - does not pass custom validation criteria.
      INVALID_VALUE_INVALID_CHARACTER               | invalid_value_invalid_character               | Invalid value - invalid character ''{0}''.
      INVALID_VALUE_INVALID_CONTROL_CHARACTER       | invalid_value_invalid_control_character       | Invalid value - invalid control character {0}.
      INVALID_VALUE_INVALID_QUOTE_CHARACTER         | invalid_value_invalid_quote_character         | Invalid value - invalid quote character "{0}".
      INVALID_VALUE_INVALID_WHITESPACE_CHARACTER    | invalid_value_invalid_whitespace_character    | Invalid value - invalid white-space character {0}.
      INVALID_VALUE_TOO_LONG                        | invalid_value_too_long                        | Invalid value - too long, maximum length is {0,number,integer}.
      INVALID_VALUE_TOO_SHORT                       | invalid_value_too_short                       | Invalid value - too short, minimum length is {0,number,integer}.
      """, delimiter = '|')
  void parserMessageCode_containsExpectedCodeAndMessage(
      @ConvertWith(ParserMessageCodeConverter.class) final ParserMessageCode messageCode,
      final String expectedCode,
      final String expectedDefaultMessage) {

    assertThat(messageCode).isNotNull();
    assertThat(messageCode.code()).isEqualTo(expectedCode);
    assertThat(messageCode.defaultMessage()).isEqualTo(expectedDefaultMessage);
  }

}
