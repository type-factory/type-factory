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

import java.util.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.InvalidValueException;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.ShortTypeParser;

class ShortTypeParserImpl_parseToShortTypeTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "  ", "   "})
  void nullEmptyAndBlankResultInNull(final String value) {

    final var actual = SomeShortType.of(value);

    assertThat(actual).isNull();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "  ", "   "})
  void localeSpecificNullEmptyAndBlankResultInNull(final String value) {

    final var actual = SomeShortType.of(value, Locale.US);

    assertThat(actual).isNull();
  }

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

    final var actual = SomeShortType.of(value);

    assertThat(actual).isNotNull().isInstanceOf(ShortType.class);
    assertThat(actual.value()).isEqualTo(Short.parseShort(value));
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
        () -> SomeShortType.of(value));

    assertThat(exception.getMessage()).isEqualTo(SomeShortType.MESSAGE_CODE.message() + " " + expectedParserMessage);
    assertThat(exception.getMessageCode()).isEqualTo(SomeShortType.MESSAGE_CODE.code());
    assertThat(exception.getErrorMessage()).isEqualTo(SomeShortType.MESSAGE_CODE.message());
    assertThat(exception.getParserErrorMessage()).isEqualTo(expectedParserMessage);
    assertThat(exception.getTargetTypeClass()).isEqualTo(SomeShortType.class);
  }

  private static class SomeShortType extends ShortType {

    private SomeShortType(final short value) {
      super(value);
    }

    static final MessageCode MESSAGE_CODE = MessageCode.of("invalid_type", "Must be a 4-digit value.");

    static final ShortTypeParser TYPE_PARSER = ShortTypeParser.builder()
        .messageCode(MESSAGE_CODE)
        .targetTypeClass(SomeShortType.class)
        .allowBase10Numbers()
        .minValueInclusive(1000)
        .maxValueInclusive(9999)
        .ignoreAllWhitespace()
        .build();

    static SomeShortType of(final String value) {
      return TYPE_PARSER.parse(value, SomeShortType::new);
    }

    static SomeShortType of(final String value, final Locale locale) {
      return TYPE_PARSER.parse(value, locale, SomeShortType::new);
    }
  }
}
