/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OΖ ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_messageCodeTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | MESSAGE_CODE | DEFAULT_MESSAGE | EXPECTED_MESSAGE_CODE | EXPECTED_DEFAULT_MESSAGE
        A11 | null         | null            | ''                    | Invalid value - invalid character 'A'.
        22B | null         | ''              | ''                    | Invalid value - invalid character 'B'.
        3C3 | null         | '  '            | ''                    | Invalid value - invalid character 'C'.
        DDD | null         | Some message    | ''                    | Some message. Invalid value - invalid character 'D'.
        EEE | ''           | null            | ''                    | Invalid value - invalid character 'E'.
        FFF | '  '         | null            | ''                    | Invalid value - invalid character 'F'.
        GGG | msg_code_a   | null            | msg_code_a            | msg_code_a. Invalid value - invalid character 'G'.
        HHH | msg_code_b   | Another message | msg_code_b            | Another message. Invalid value - invalid character 'H'.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parseToShort_throwsException(
      final String value,
      final String messageCode, final String defaultMessage,
      final String expectedMessageCode, final String expectedDefaultMessage) {

    final var shortTypeParser = ShortTypeParser.builder()
        .messageCode(MessageCode.of(messageCode, defaultMessage))
        .targetTypeClass(SomeClass.class)
        .allowBase10Numbers()
        .ignoreAllWhitespace()
        .build();

    final InvalidValueException exception = catchThrowableOfType(
        InvalidValueException.class,
        () -> shortTypeParser.parseToShort(value));

    assertThat(exception.getMessageCode()).isEqualTo(expectedMessageCode);
    assertThat(exception.getMessage()).isEqualTo(expectedDefaultMessage);
    assertThat(exception.getTargetTypeClass()).isEqualTo(SomeClass.class);
  }

  private static class SomeClass {

  }
}
