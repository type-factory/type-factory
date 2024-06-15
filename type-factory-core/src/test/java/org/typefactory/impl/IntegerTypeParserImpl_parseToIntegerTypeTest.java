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
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;

class IntegerTypeParserImpl_parseTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE
       1000
       1001
       2222
       9998
       9999
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void instantiation_succeeds(final String value) {

    final var actual = SomeIntegerType.of(value);

    assertThat(actual).isNotNull().isInstanceOf(IntegerType.class);
    assertThat(actual.value()).isEqualTo(Integer.parseInt(value));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      VALUE | EXPECTED_MESSAGE
          7 | Invalid value - must be greater than or equal to 1,000.
         88 | Invalid value - must be greater than or equal to 1,000.
        999 | Invalid value - must be greater than or equal to 1,000.
      10000 | Invalid value - must be less than or equal to 9,999.
      22222 | Invalid value - must be less than or equal to 9,999.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void instantiation_throwsException(
      final String value, final String expectedParserMessage) {

    final InvalidValueException exception = catchThrowableOfType(
        InvalidValueException.class,
        () -> SomeIntegerType.of(value));

    assertThat(exception.getMessage()).isEqualTo(SomeIntegerType.MESSAGE_CODE.message() + " " + expectedParserMessage);
    assertThat(exception.getMessageCode()).isEqualTo(SomeIntegerType.MESSAGE_CODE.code());
    assertThat(exception.getErrorMessage()).isEqualTo(SomeIntegerType.MESSAGE_CODE.message());
    assertThat(exception.getParserErrorMessage()).isEqualTo(expectedParserMessage);
    assertThat(exception.getTargetTypeClass()).isEqualTo(SomeIntegerType.class);
  }

  private static class SomeIntegerType extends IntegerType {

    private SomeIntegerType(final int value) {
      super(value);
    }

    static final MessageCode MESSAGE_CODE = MessageCode.of("invalid_type", "Must be a 4-digit value.");

    static final IntegerTypeParser TYPE_PARSER = IntegerTypeParser.builder()
        .messageCode(MESSAGE_CODE)
        .targetTypeClass(SomeIntegerType.class)
        .allowBase10Numbers()
        .minValueInclusive(1000)
        .maxValueInclusive(9999)
        .ignoreAllWhitespace()
        .build();

    static SomeIntegerType of(final String value) {
      return TYPE_PARSER.parse(value, SomeIntegerType::new);
    }
  }
}
