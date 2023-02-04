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

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.StringArrayConverter;

class MessageCodeTest {

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


  @ParameterizedTest
  @CsvSource(textBlock = """
      null | null | true
      null | ''   | false
      null | '  ' | false
      ''   | null | false
      ''   | ''   | true
      '  ' | '  ' | true
      AA   | AA   | true
      AA   | null | false
      AA   | BB   | false
      null | BB   | false
      BB   | AA   | false
      """, delimiter = '|', nullValues = "null")
  void hasSameCodeAs_returnsAsExpected(
      final String code1, final String code2,
      final boolean expected) {

    final var messageCode1 = new SomeMessageCode(code1, "some default message");
    final var messageCode2 = new SomeMessageCode(code2, "another default message");

    assertThat(messageCode1.hasSameCodeAs(messageCode2)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null | null | true
      null | ''   | true
      null | '  ' | true
      ''   | null | true
      ''   | ''   | true
      '  ' | '  ' | true
      AA   | null | false
      AA   | ''   | false
      AA   | '  ' | false
      null | AA   | false
      ''   | AA   | false
      '  ' | AA   | false
      AA   | AA   | false
      AA   | BB   | false
      BB   | AA   | false
      """, delimiter = '|', nullValues = "null")
  void isBlank_returnsAsExpected(
      final String code, final String defaultMessage,
      final boolean expected) {

    final var messageCode = new SomeMessageCode(code, defaultMessage);

    assertThat(messageCode.isBlank()).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null | null | '' | ''
      null | ''   | '' | ''
      null | '  ' | '' | ''
      ''   | null | '' | ''
      ''   | ''   | '' | ''
      '  ' | '  ' | '' | ''
      AA   | null | AA | ''
      AA   | ''   | AA | ''
      AA   | '  ' | AA | ''
      null | AA   | '' | AA
      ''   | AA   | '' | AA
      '  ' | AA   | '' | AA
      AA   | AA   | AA | AA
      AA   | BB   | AA | BB
      BB   | AA   | BB | AA
      """, delimiter = '|', nullValues = "null")
  void of_returnsAsExpected(
      final String code, final String defaultMessage,
      final String expectedCode, final String expectedDefaultMessage) {

    final var messageCode = MessageCode.of(code, defaultMessage);

    assertThat(messageCode.code()).isEqualTo(expectedCode);
    assertThat(messageCode.defaultMessage()).isEqualTo(expectedDefaultMessage);
  }

  record SomeMessageCode(String code, String defaultMessage) implements MessageCode {
  }
}
