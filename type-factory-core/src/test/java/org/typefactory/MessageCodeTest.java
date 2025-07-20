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

import static org.typefactory.assertions.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.StringArrayConverter;

class MessageCodeTest {

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
  void of_returnsAsExpected(
      final String code, final String defaultMessage,
      final boolean expectedIsEmpty, final String expectedCode,
      final String expectedDefaultMessage, final String expectedMessage) {

    final var messageCode = MessageCode.of(code, defaultMessage);

    assertThat(messageCode)
        .hasCode(expectedCode)
        .hasDefaultMessage(expectedDefaultMessage)
        .hasMessage(expectedMessage);

    if (expectedIsEmpty) {
      assertThat(messageCode).isEmpty();
    } else {
      assertThat(messageCode).isNotEmpty();
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      tt   | null                                        | tt
      uu   | ''                                          | uu
      vv   | '  '                                        | vv
      ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
      """, delimiter = '|', nullValues = "null")
  void message_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = messageCode.message();

    assertThat(actual).isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      tt   | null                                        | []     | tt
      uu   | ''                                          | [null] | uu
      vv   | '  '                                        | [JJKK] | vv
      ww-w | some parser error message-W with value {0}. | null   | some parser error message-W with value {0}.
      xx_x | some parser error message-X with value {0}. | []     | some parser error message-X with value {0}.
      yyyy | some parser error message-Y with value {0}. | [null] | some parser error message-Y with value null.
      zzzz | some parser error message-Z with value {0}. | [JJKK] | some parser error message-Z with value JJKK.
      """, delimiter = '|', nullValues = "null")
  void messageWithArgs_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] errorMessageArgs,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = messageCode.message(errorMessageArgs);

    assertThat(actual).isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      yy | pp   | null                                        | pp
      yy | qq.q | ''                                          | qq.q
      yy | rr-r | '  '                                        | rr-r

      xx | ss   | some parser error message-S.                | some parser error message-S.
      xx | tt   | some parser error message-T.                | some parser error message-T.
      xx | uu.u | some parser error message-U.                | some parser error message-U.
      xx | vv.v | some parser error message-V.                | some parser error message-V.
      xx | ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      xx | xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      xx | yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      xx | zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
      
      es | ss   | some parser error message-S.                | algún mensaje de error del analizador-S.
      es | tt   | some parser error message-T.                | algún mensaje de error del analizador-T.
      es | uu.u | some parser error message-U.                | algún mensaje de error del analizador-U.
      es | vv.v | some parser error message-V.                | algún mensaje de error del analizador-V.
      es | ww-w | some parser error message-W with value {0}. | algún mensaje de error del analizador-W con valor {0}.
      es | xx_x | some parser error message-X with value {0}. | algún mensaje de error del analizador-X con valor {0}.
      es | yyyy | some parser error message-Y with value {0}. | algún mensaje de error del analizador-Y con valor {0}.
      es | zzzz | some parser error message-Z with value {0}. | algún mensaje de error del analizador-Z con valor {0}.
      
      de | ss   | some parser error message-S.                | einige Parser-Fehlermeldungen-S.
      de | tt   | some parser error message-T.                | einige Parser-Fehlermeldungen-T.
      de | uu.u | some parser error message-U.                | einige Parser-Fehlermeldungen-U.
      de | vv.v | some parser error message-V.                | einige Parser-Fehlermeldungen-V.
      de | ww-w | some parser error message-W with value {0}. | einige Parser-Fehlermeldungen-W mit Wert {0}.
      de | xx_x | some parser error message-X with value {0}. | einige Parser-Fehlermeldungen-X mit Wert {0}.
      de | yyyy | some parser error message-Y with value {0}. | einige Parser-Fehlermeldungen-Y mit Wert {0}.
      de | zzzz | some parser error message-Z with value {0}. | einige Parser-Fehlermeldungen-Z mit Wert {0}.
      """, delimiter = '|', nullValues = "null")
  void messageWithLocale_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = messageCode.message(locale);

    assertThat(actual).isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      yy | pp   | null                                        | null   | pp
      yy | qq.q | ''                                          | []     | qq.q
      yy | rr-r | '  '                                        | [null] | rr-r

      xx | ss   | some parser error message-S.                | null   | some parser error message-S.
      xx | tt   | some parser error message-T.                | []     | some parser error message-T.
      xx | uu.u | some parser error message-U.                | [null] | some parser error message-U.
      xx | vv.v | some parser error message-V.                | [JJKK] | some parser error message-V.
      xx | ww-w | some parser error message-W with value {0}. | null   | some parser error message-W with value {0}.
      xx | xx_x | some parser error message-X with value {0}. | []     | some parser error message-X with value {0}.
      xx | yyyy | some parser error message-Y with value {0}. | [null] | some parser error message-Y with value null.
      xx | zzzz | some parser error message-Z with value {0}. | [JJKK] | some parser error message-Z with value JJKK.
      
      es | ss   | some parser error message-S.                | null   | algún mensaje de error del analizador-S.
      es | tt   | some parser error message-T.                | []     | algún mensaje de error del analizador-T.
      es | uu.u | some parser error message-U.                | [null] | algún mensaje de error del analizador-U.
      es | vv.v | some parser error message-V.                | [JJKK] | algún mensaje de error del analizador-V.
      es | ww-w | some parser error message-W with value {0}. | null   | algún mensaje de error del analizador-W con valor {0}.
      es | xx_x | some parser error message-X with value {0}. | []     | algún mensaje de error del analizador-X con valor {0}.
      es | yyyy | some parser error message-Y with value {0}. | [null] | algún mensaje de error del analizador-Y con valor null.
      es | zzzz | some parser error message-Z with value {0}. | [JJKK] | algún mensaje de error del analizador-Z con valor JJKK.
      
      de | ss   | some parser error message-S.                | null   | einige Parser-Fehlermeldungen-S.
      de | tt   | some parser error message-T.                | []     | einige Parser-Fehlermeldungen-T.
      de | uu.u | some parser error message-U.                | [null] | einige Parser-Fehlermeldungen-U.
      de | vv.v | some parser error message-V.                | [JJKK] | einige Parser-Fehlermeldungen-V.
      de | ww-w | some parser error message-W with value {0}. | null   | einige Parser-Fehlermeldungen-W mit Wert {0}.
      de | xx_x | some parser error message-X with value {0}. | []     | einige Parser-Fehlermeldungen-X mit Wert {0}.
      de | yyyy | some parser error message-Y with value {0}. | [null] | einige Parser-Fehlermeldungen-Y mit Wert null.
      de | zzzz | some parser error message-Z with value {0}. | [JJKK] | einige Parser-Fehlermeldungen-Z mit Wert JJKK.
      """, delimiter = '|', nullValues = "null")
  void messageWithLocaleAndArgs_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] errorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = messageCode.message(locale, errorMessageArgs);

    assertThat(actual).isEqualTo(expectedMessage);
  }

  @Test
  void hasSameCodeAs_returnsFalseWhenOtherIsNull() {
    final var messageCode1 = new SomeMessageCode("come.message.code", "some default message");
    assertThat(messageCode1.hasSameCodeAs(null)).isFalse();
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE_1     | CODE_2     | EXPECTED_EQUALS | NOTES
      null       | null       | true            |
      null       | ''         | true            |
      null       | '  '       | false           | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      null       | some.code  | false           |
      null       | other.code | false           |
      ''         | null       | true            |
      ''         | ''         | true            |
      ''         | '  '       | false           | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      ''         | some.code  | false           |
      ''         | other.code | false           |
      '  '       | null       | false           | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      '  '       | ''         | false           | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      '  '       | '  '       | true            |
      '  '       | some.code  | false           |
      '  '       | other.code | false           |
      some.code  | null       | false           |
      some.code  | ''         | false           |
      some.code  | '  '       | false           |
      some.code  | some.code  | true            |
      some.code  | other.code | false           |
      other.code | null       | false           |
      other.code | ''         | false           |
      other.code | '  '       | false           |
      other.code | some.code  | false           |
      other.code | other.code | true            |
     """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void hasSameCodeAs_returnsAsExpected(
      final String code1, final String code2,
      final boolean expected) {

    final var messageCode1 = new SomeMessageCode(code1, "some default message");
    final var messageCode2 = new SomeMessageCode(code2, "another default message");

    assertThat(messageCode1.hasSameCodeAs(messageCode2)).isEqualTo(expected);
  }

  @Test
  void isBlank_returnsAsExpectedForNull() {
    final MessageCode messageCode = null;
    assertThat(MessageCode.isBlank(messageCode)).isTrue();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED
      null      | null            | true
      null      | ''              | true
      null      | '  '            | true
      null      | some message    | true
      ''        | null            | true
      ''        | ''              | true
      ''        | '  '            | true
      ''        | some message    | true
      '  '      | null            | true
      '  '      | ''              | true
      '  '      | '  '            | true
      '  '      | some message    | true
      some.code | null            | false
      some.code | ''              | false
      some.code | '  '            | false
      some.code | some message    | false
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void isBlank_returnsAsExpected(
      final String code, final String defaultMessage,
      final boolean expected) {

    final var messageCode = new SomeMessageCode(code, defaultMessage);

    assertThat(messageCode.isBlank()).isEqualTo(expected);
    assertThat(MessageCode.isBlank(messageCode)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      CODE      | DEFAULT_MESSAGE | EXPECTED | NOTES
      null      | null            | 0        |
      null      | ''              | 0        |
      null      | '  '            | 0        |
      null      | some message    | 0        |
      ''        | null            | 0        |
      ''        | ''              | 0        |
      ''        | '  '            | 0        |
      ''        | some message    | 0        |
      '  '      | null            | 2        | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      '  '      | ''              | 2        | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      '  '      | '  '            | 2        | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      '  '      | some message    | 2        | Not equal because we are testing default method of some other implementation and not the MessageCodeImpl
      some.code | null            | 9        |
      some.code | ''              | 9        |
      some.code | '  '            | 9        |
      some.code | some message    | 9        |
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void length_returnsAsExpected(
      final String code, final String defaultMessage,
      final int expected) {

    final var messageCode = new SomeMessageCode(code, defaultMessage);

    assertThat(messageCode.length()).isEqualTo(expected);
  }


  record SomeMessageCode(String code, String defaultMessage) implements MessageCode {
  }
}
