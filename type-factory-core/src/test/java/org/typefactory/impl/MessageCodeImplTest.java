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
package org.typefactory.impl;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.typefactory.CharSequenceUtils.isBlank;
import static org.typefactory.assertions.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MessageCodeImplTest {

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | IS_EMPTY | EXPECTED_CODE | EXPECTED_DEFAULT_MESSAGE | EXPECTED_MESSAGE
      null      | null            | true     | ''            | ''                       | ''
      null      | ''              | true     | ''            | ''                       | ''
      null      | '  '            | true     | ''            | ''                       | ''
      null      | some message    | true     | ''            | ''                       | ''
      ''        | null            | true     | ''            | ''                       | ''
      ''        | ''              | true     | ''            | ''                       | ''
      '  '      | '  '            | true     | ''            | ''                       | ''
      '  '      | some message    | true     | ''            | ''                       | ''
      some.code | null            | false    | some.code     | ''                       | some.code
      some.code | ''              | false    | some.code     | ''                       | some.code
      some.code | '  '            | false    | some.code     | ''                       | some.code
      some.code | some message    | false    | some.code     | some message             | some message
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void constructor_instantiatesAsExpected(
      final String code, final String defaultMessage,
      final boolean expectedIsEmpty, final String expectedCode,
      final String expectedDefaultMessage, final String expectedMessage) {

    final var actual = new MessageCodeImpl(code, defaultMessage);

    assertThat(actual)
        .hasCode(expectedCode)
        .hasDefaultMessage(expectedDefaultMessage)
        .hasMessage(expectedMessage)
        .hasToString(expectedCode);

    if (expectedIsEmpty) {
      assertThat(actual).isEmpty();
    } else {
      assertThat(actual).isNotEmpty();
    }
  }

  @Test
  void equal_returnsTrueForSameInstance() {

    final var actual = new MessageCodeImpl("AA", "BB");

    assertThat(actual).isEqualTo(actual);
  }

  @Test
  void equal_returnsFalseForAnotherType() {

    final var actual = new MessageCodeImpl("AA", "BB");
    final var notAMessageCode = (Object)"CC";

    assertThat(actual).isNotEqualTo(notAMessageCode);
  }

  private static Stream<Arguments> provideCodeAndMessageValuesToTestEqualsAndHashCode() {
    final String [] codes = { null, "", "  ", "some.code", "other.code"};
    final String [] messages = { null, "", "  ", "some message", "other message"};

    return Stream.of(codes)
        .flatMap(code1 -> Stream.of(messages)
            .map(message1 -> arguments(code1, message1)))
        .flatMap(args1 -> Stream.of(codes)
            .flatMap(code2 -> Stream.of(messages)
                .map(message2 -> {
                  final String code1 = (String)args1.get()[0];
                  final String message1 = (String)args1.get()[1];
                  final boolean expectedEquals = isBlank(code1) && isBlank(code2) || (code1 != null && code1.equals(code2));
                  return arguments(code1, message1, code2, message2, expectedEquals);
                })));
  }

  @ParameterizedTest
  @MethodSource("provideCodeAndMessageValuesToTestEqualsAndHashCode")
  void equalsAndHashCode_returnsAsExpected(
      final String code1, final String defaultMessage1,
      final String code2, final String defaultMessage2,
      final boolean expectedEquals) {

    final var actual1 = new MessageCodeImpl(code1, defaultMessage1);
    final var actual2 = new MessageCodeImpl(code2, defaultMessage2);

    if (expectedEquals) {
      assertThat(actual1).isEqualTo(actual2);
      assertThat(actual1).hasSameHashCodeAs(actual2);
    } else {
      assertThat(actual1).isNotEqualTo(actual2);
      assertThat(actual1).doesNotHaveSameHashCodeAs(actual2);
    }
  }
}
