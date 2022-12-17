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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.ErrorCode;
import org.typefactory.testutils.StringArrayConverter;

class ExceptionUtilsTest {

  static Locale savedDefaultLocale;

  @BeforeAll
  static void beforeAll() {
    savedDefaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.ENGLISH);
  }

  @AfterAll
  static void afterAll() {
    Locale.setDefault(savedDefaultLocale);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void getMessage_returnsEmptyStringForNullEmptyOrBlankErrorCode(final String errorCode) {
    final var actual = ExceptionUtils.getMessage(ErrorCode.of(errorCode, ""));
    assertThat(actual)
        .isNotNull()
        .isEmpty();
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ww-w | some parser error message-W with value {0}. | ww-w
      xx_x | some parser error message-X with value {0}. | xx_x
      yyyy | some parser error message-Y with value {0}. | yyyy
      zzzz | some parser error message-Z with value {0}. | zzzz
      """, delimiter = '|', nullValues = "null")
  void getMessage_returnsAsExpectedWhenTheMessageArgumentThrowsExceptionDuringFormatting(
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      final String expectedMessage) {

    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);
    final var parserErrorMessageArgs = new Object[]{new SomeClassWithToStringMethodThrowingException()};

    final var actual = ExceptionUtils.loadMessageFromResourceBundleAndFormat(Locale.getDefault(), parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      aa   | some error message-A. | some error message-A.
      bb   | some error message-B. | some error message-B.
      cc.c | some error message-C. | some error message-C.
      dc.d | some error message-D. | some error message-D.
      ee-e | some error message-E. | some error message-E.
      ff_f | some error message-F. | some error message-F.
      gggg | some error message-G. | some error message-G.
      hhhh | some error message-H. | some error message-H.
      """, delimiter = '|', nullValues = "null")
  void getMessage_returnsAsExpected(
      final String errorCodeString, final String errorCodeMessage,
      final String expectedMessage) {

    final var errorCode = ErrorCode.of(errorCodeString, errorCodeMessage);

    final var actual = ExceptionUtils.getMessage(errorCode);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      xx | aa   | some error message-A. | some error message-A.
      xx | bb   | some error message-B. | some error message-B.
      xx | cc.c | some error message-C. | some error message-C.
      xx | dc.d | some error message-D. | some error message-D.
      xx | ee-e | some error message-E. | some error message-E.
      xx | ff_f | some error message-F. | some error message-F.
      xx | gggg | some error message-G. | some error message-G.
      xx | hhhh | some error message-H. | some error message-H.

      es | aa   | some error message-A. | algún mensaje de error-A.
      es | bb   | some error message-B. | algún mensaje de error-B.
      es | cc.c | some error message-C. | algún mensaje de error-C.
      es | dc.d | some error message-D. | algún mensaje de error-D.
      es | ee-e | some error message-E. | algún mensaje de error-E.
      es | ff_f | some error message-F. | algún mensaje de error-F.
      es | gggg | some error message-G. | algún mensaje de error-G.
      es | hhhh | some error message-H. | algún mensaje de error-H.

      de | aa   | some error message-A. | einige Fehlermeldung-A.
      de | bb   | some error message-B. | einige Fehlermeldung-B.
      de | cc.c | some error message-C. | einige Fehlermeldung-C.
      de | dc.d | some error message-D. | einige Fehlermeldung-D.
      de | ee-e | some error message-E. | einige Fehlermeldung-E.
      de | ff_f | some error message-F. | einige Fehlermeldung-F.
      de | gggg | some error message-G. | einige Fehlermeldung-G.
      de | hhhh | some error message-H. | einige Fehlermeldung-H.
      """, delimiter = '|', nullValues = "null")
  void getMessageWithLocale_returnsAsExpected(
      final String language,
      final String errorCodeString, final String errorCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var errorCode = ErrorCode.of(errorCodeString, errorCodeMessage);

    final var actual = ExceptionUtils.getMessage(locale, errorCode);
    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
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
  void getMessage_returnsAsExpected(
      final String language,
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] parserErrorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);

    final var actual = ExceptionUtils.getMessage(locale, parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      aa   | some error message-A. | ss   | some parser error message-S.                | null   | some error message-A. some parser error message-S.
      bb   | some error message-B. | tt   | some parser error message-T.                | []     | some error message-B. some parser error message-T.
      cc.c | some error message-C. | uu.u | some parser error message-U.                | [null] | some error message-C. some parser error message-U.
      dc.d | some error message-D. | vv.v | some parser error message-V.                | [JJKK] | some error message-D. some parser error message-V.
      ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null   | some error message-E. some parser error message-W with value {0}.
      ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | []     | some error message-F. some parser error message-X with value {0}.
      gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | [null] | some error message-G. some parser error message-Y with value null.
      hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | [JJKK] | some error message-H. some parser error message-Z with value JJKK.
      """, delimiter = '|', nullValues = "null")
  void getCombinedMessage_returnsParserErrorCodeWhenMessageCannotBeFound(
      final String errorCodeString, final String errorCodeMessage,
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] parserErrorMessageArgs,
      final String expectedMessage) {

    final var errorCode = ErrorCode.of(errorCodeString, errorCodeMessage);
    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);

    final var actual = ExceptionUtils.getCombinedMessage(errorCode, parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      xx | aa   | some error message-A. | ss   | some parser error message-S.                | null   | some error message-A. some parser error message-S.
      xx | bb   | some error message-B. | tt   | some parser error message-T.                | []     | some error message-B. some parser error message-T.
      xx | cc.c | some error message-C. | uu.u | some parser error message-U.                | [null] | some error message-C. some parser error message-U.
      xx | dc.d | some error message-D. | vv.v | some parser error message-V.                | [JJKK] | some error message-D. some parser error message-V.
      xx | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null   | some error message-E. some parser error message-W with value {0}.
      xx | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | []     | some error message-F. some parser error message-X with value {0}.
      xx | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | [null] | some error message-G. some parser error message-Y with value null.
      xx | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | [JJKK] | some error message-H. some parser error message-Z with value JJKK.

      es | aa   | some error message-A. | ss   | some parser error message-S.                | null   | algún mensaje de error-A. algún mensaje de error del analizador-S.
      es | bb   | some error message-B. | tt   | some parser error message-T.                | []     | algún mensaje de error-B. algún mensaje de error del analizador-T.
      es | cc.c | some error message-C. | uu.u | some parser error message-U.                | [null] | algún mensaje de error-C. algún mensaje de error del analizador-U.
      es | dc.d | some error message-D. | vv.v | some parser error message-V.                | [JJKK] | algún mensaje de error-D. algún mensaje de error del analizador-V.
      es | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null   | algún mensaje de error-E. algún mensaje de error del analizador-W con valor {0}.
      es | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | []     | algún mensaje de error-F. algún mensaje de error del analizador-X con valor {0}.
      es | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | [null] | algún mensaje de error-G. algún mensaje de error del analizador-Y con valor null.
      es | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | [JJKK] | algún mensaje de error-H. algún mensaje de error del analizador-Z con valor JJKK.

      de | aa   | some error message-A. | ss   | some parser error message-S.                | null   | einige Fehlermeldung-A. einige Parser-Fehlermeldungen-S.
      de | bb   | some error message-B. | tt   | some parser error message-T.                | []     | einige Fehlermeldung-B. einige Parser-Fehlermeldungen-T.
      de | cc.c | some error message-C. | uu.u | some parser error message-U.                | [null] | einige Fehlermeldung-C. einige Parser-Fehlermeldungen-U.
      de | dc.d | some error message-D. | vv.v | some parser error message-V.                | [JJKK] | einige Fehlermeldung-D. einige Parser-Fehlermeldungen-V.
      de | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null   | einige Fehlermeldung-E. einige Parser-Fehlermeldungen-W mit Wert {0}.
      de | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | []     | einige Fehlermeldung-F. einige Parser-Fehlermeldungen-X mit Wert {0}.
      de | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | [null] | einige Fehlermeldung-G. einige Parser-Fehlermeldungen-Y mit Wert null.
      de | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | [JJKK] | einige Fehlermeldung-H. einige Parser-Fehlermeldungen-Z mit Wert JJKK.
      """, delimiter = '|', nullValues = "null")
  void getCombinedMessageWithLocale_returnsParserErrorCodeWhenMessageCannotBeFound(
      final String language,
      final String errorCodeString, final String errorCodeMessage,
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] parserErrorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var errorCode = ErrorCode.of(errorCodeString, errorCodeMessage);
    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);

    final var actual = ExceptionUtils.getCombinedMessage(locale, errorCode, parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      ww-w | some parser error message-W with value {0}. | ww-w
      xx_x | some parser error message-X with value {0}. | xx_x
      yyyy | some parser error message-Y with value {0}. | yyyy
      zzzz | some parser error message-Z with value {0}. | zzzz
      """, delimiter = '|', nullValues = "null")
  void loadMessageFromResourceBundleAndFormat_returnsAsExpectedWhenTheMessageArgumentThrowsExceptionDuringFormatting(
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      final String expectedMessage) {

    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);
    final var parserErrorMessageArgs = new Object[]{new SomeClassWithToStringMethodThrowingException()};

    final var actual = ExceptionUtils.loadMessageFromResourceBundleAndFormat(Locale.getDefault(), parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
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
  void loadMessageFromResourceBundleAndFormat_returnsAsExpected(
      final String language,
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] parserErrorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);

    final var actual = ExceptionUtils.loadMessageFromResourceBundleAndFormat(locale, parserErrorCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
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
            
      es | ss   | some parser error message-S.                | some parser error message-S.
      es | tt   | some parser error message-T.                | some parser error message-T.
      es | uu.u | some parser error message-U.                | some parser error message-U.
      es | vv.v | some parser error message-V.                | some parser error message-V.
      es | ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      es | xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      es | yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      es | zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
            
      de | ss   | some parser error message-S.                | some parser error message-S.
      de | tt   | some parser error message-T.                | some parser error message-T.
      de | uu.u | some parser error message-U.                | some parser error message-U.
      de | vv.v | some parser error message-V.                | some parser error message-V.
      de | ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      de | xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      de | yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      de | zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
      """, delimiter = '|', nullValues = "null")
  void loadMessageFromResourceBundle_returnsAsExpectedWhenResourceBundleNameIsNull(
      final String language,
      final String parserErrorCodeString, final String parserErrorCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserErrorCode = Factory.parserErrorCode(parserErrorCodeString, parserErrorCodeMessage);

    final var actual = ExceptionUtils.loadMessageFromResourceBundle(null, locale, parserErrorCode);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      null        | null               | ''
      null        | null               | ''
      null        | null               | ''
      null        | null               | ''

      a           | null               | a
      a           | null               | a
      a.          | null               | a.
      a.          | null               | a.

      null        | b                  | b
      null        | b.                 | b.
      null        | b                  | b
      null        | b.                 | b.

      a           | b                  | a. b
      a           | b.                 | a. b.
      a.          | b                  | a. b
      a.          | b.                 | a. b.

      a sentence  | another sentence   | a sentence. another sentence
      a sentence  | another sentence.  | a sentence. another sentence.
      a sentence. | another sentence   | a sentence. another sentence
      a sentence. | another sentence.  | a sentence. another sentence.
      """, delimiter = '|', nullValues = "null")
  void combineMessagesIntoSentences_returnsAsExpected(
      final String message1, final String message2, final String expected) {

    final var actual = ExceptionUtils.combineMessagesIntoSentences(message1, message2);

    assertThat(actual).isEqualTo(expected);
  }

  static class SomeClassWithToStringMethodThrowingException {

    @Override
    public String toString() {
      throw new UnsupportedOperationException();
    }
  }
}
