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
import static org.mockito.ArgumentMatchers.any;
import static org.typefactory.MessageUtils.TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME;

import java.util.Locale;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.typefactory.impl.Factory;
import org.typefactory.testutils.StringArrayConverter;

@ExtendWith(MockitoExtension.class)
class MessageUtilsTest {

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
  void getMessage_returnsEmptyStringForNullEmptyOrBlankMessageCode(final String messageCodeString) {
    final var messageCode = MessageCode.of(messageCodeString, "");
    final var actual = MessageUtils.getMessage(TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, messageCode);
    assertThat(actual)
        .isNotNull()
        .isEmpty();
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
  void getMessage_returnsAsExpectedWhenGettingAnyExceptionOccurs(
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String expectedMessage) {

    try (final MockedStatic<MessageUtils> messageUtils = Mockito.mockStatic(MessageUtils.class)) {

      messageUtils
          .when(() -> MessageUtils.getResourceBundleMessage(any(), any(), any()))
          .thenThrow(new RuntimeException());

      messageUtils
          .when(() -> MessageUtils.getMessage(any(), any(), any(), any()))
          .thenCallRealMethod();

      final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

      final var actual = MessageUtils.getMessage(
          TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, Locale.getDefault(),
          parserMessageCode, null);

      assertThat(actual)
          .isNotNull()
          .isNotEmpty()
          .isEqualTo(expectedMessage);

    }
  }


  @ParameterizedTest
  @CsvSource(textBlock = """
      ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
      """, delimiter = '|', nullValues = "null")
  void getMessage_returnsAsExpectedWhenTheMessageArgumentThrowsExceptionDuringFormatting(
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String expectedMessage) {

    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);
    final var parserErrorMessageArgs = new Object[]{new SomeClassWithToStringMethodThrowingException()};

    final var actual = MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, Locale.getDefault(),
        parserMessageCode, parserErrorMessageArgs);

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
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = MessageUtils.getMessage(TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, messageCode);
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
      final String messageCodeString, final String messageCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var actual = MessageUtils.getMessage(TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale, messageCode);

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
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      @ConvertWith(StringArrayConverter.class) final String[] parserErrorMessageArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

    final var actual = MessageUtils.getMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale,
        parserMessageCode, parserErrorMessageArgs);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      xx | jj   |                                             | jj
      xx | kk   | some parser error message-K.                | some parser error message-K.
      xx | ss   | some parser error message-S.                | some parser error message-S.
      xx | tt   | some parser error message-T.                | some parser error message-T.
      xx | uu.u | some parser error message-U.                | some parser error message-U.
      xx | vv.v | some parser error message-V.                | some parser error message-V.
      xx | ww-w | some parser error message-W with value {0}. | some parser error message-W with value {0}.
      xx | xx_x | some parser error message-X with value {0}. | some parser error message-X with value {0}.
      xx | yyyy | some parser error message-Y with value {0}. | some parser error message-Y with value {0}.
      xx | zzzz | some parser error message-Z with value {0}. | some parser error message-Z with value {0}.
            
      es | jj   |                                             | jj
      es | kk   | some parser error message-K.                | some parser error message-K.
      es | ss   | some parser error message-S.                | algún mensaje de error del analizador-S.
      es | tt   | some parser error message-T.                | algún mensaje de error del analizador-T.
      es | uu.u | some parser error message-U.                | algún mensaje de error del analizador-U.
      es | vv.v | some parser error message-V.                | algún mensaje de error del analizador-V.
      es | ww-w | some parser error message-W with value {0}. | algún mensaje de error del analizador-W con valor {0}.
      es | xx_x | some parser error message-X with value {0}. | algún mensaje de error del analizador-X con valor {0}.
      es | yyyy | some parser error message-Y with value {0}. | algún mensaje de error del analizador-Y con valor {0}.
      es | zzzz | some parser error message-Z with value {0}. | algún mensaje de error del analizador-Z con valor {0}.
            
      de | jj   |                                             | jj
      de | kk   | some parser error message-K.                | some parser error message-K.
      de | ss   | some parser error message-S.                | einige Parser-Fehlermeldungen-S.
      de | tt   | some parser error message-T.                | einige Parser-Fehlermeldungen-T.
      de | uu.u | some parser error message-U.                | einige Parser-Fehlermeldungen-U.
      de | vv.v | some parser error message-V.                | einige Parser-Fehlermeldungen-V.
      de | ww-w | some parser error message-W with value {0}. | einige Parser-Fehlermeldungen-W mit Wert {0}.
      de | xx_x | some parser error message-X with value {0}. | einige Parser-Fehlermeldungen-X mit Wert {0}.
      de | yyyy | some parser error message-Y with value {0}. | einige Parser-Fehlermeldungen-Y mit Wert {0}.
      de | zzzz | some parser error message-Z with value {0}. | einige Parser-Fehlermeldungen-Z mit Wert {0}.
      """, delimiter = '|', nullValues = "null")
  void getResourceBundleMessage_returnsAsExpected(
      final String language,
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

    final var actual = MessageUtils.getResourceBundleMessage(
        TYPE_FACTORY_MESSAGES_RESOURCE_BUNDLE_BASE_NAME, locale, parserMessageCode);

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
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

    final var actual = MessageUtils.getMessage(null, locale, parserMessageCode);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }


  static class SomeClassWithToStringMethodThrowingException {

    @Override
    public String toString() {
      throw new UnsupportedOperationException();
    }
  }
}
