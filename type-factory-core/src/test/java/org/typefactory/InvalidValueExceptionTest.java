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

import static org.mockito.Mockito.when;
import static org.typefactory.assertions.Assertions.assertThat;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.impl.Factory;

@ExtendWith(MockitoExtension.class)
class InvalidValueExceptionTest {

  static final String INVALID_VALUE = "Some invalid value";
  static final MessageCode ERROR_MESSAGE_CODE = MessageCode.of("some.error.code", "some default error message.");

  static final ParserMessageCode PARSER_ERROR_MESSAGE_CODE = Factory.parserMessageCode("some.parser.error.code",
      "some default parser error message.");

  static final LinkedHashMap<String, Serializable> parserErrorArgs = new LinkedHashMap<>(Map.of("invalidCodePoint", "A"));

  @Mock
  Exception cause;


  @Test
  void constructor_constructsExceptionWithoutParserErrorArgs() {

    final var expectedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage_fr = ERROR_MESSAGE_CODE.message(Locale.FRENCH) + ' ' + PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH);

    final var actual = new InvalidValueException(cause, INVALID_VALUE, SomeClass.class, ERROR_MESSAGE_CODE, PARSER_ERROR_MESSAGE_CODE, null);

    assertThat(actual)
        .hasCause(cause)
        .hasInvalidValue(INVALID_VALUE)
        .hasTargetTypeClass(SomeClass.class)
        .hasMessage(expectedMessage)
        .hasLocalizedMessage(expectedLocalizedMessage)
        .hasLocalizedMessage(Locale.FRENCH, expectedLocalizedMessage_fr)
        .hasErrorCode(ERROR_MESSAGE_CODE)
        .hasErrorMessage(ERROR_MESSAGE_CODE.defaultMessage())
        .hasErrorMessage(Locale.FRENCH, ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .hasParserErrorMessage(PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorMessage(Locale.FRENCH, PARSER_ERROR_MESSAGE_CODE.defaultMessage())
        .hasNoParserErrorProperties();
  }

  @Test
  void constructor_constructsExceptionWithParserErrorArgs() {

    final var expectedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage_fr = ERROR_MESSAGE_CODE.message(Locale.FRENCH) + ' ' + PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH);

    final var actual = new InvalidValueException(cause, INVALID_VALUE, SomeClass.class, ERROR_MESSAGE_CODE, PARSER_ERROR_MESSAGE_CODE,
        parserErrorArgs);

    assertThat(actual)
        .hasCause(cause)
        .hasInvalidValue(INVALID_VALUE)
        .hasTargetTypeClass(SomeClass.class)
        .hasMessage(expectedMessage)
        .hasLocalizedMessage(expectedLocalizedMessage)
        .hasLocalizedMessage(Locale.FRENCH, expectedLocalizedMessage_fr)
        .hasErrorCode(ERROR_MESSAGE_CODE)
        .hasErrorMessage(ERROR_MESSAGE_CODE.defaultMessage())
        .hasErrorMessage(Locale.FRENCH, ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .hasParserErrorMessage(PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorMessage(Locale.FRENCH, PARSER_ERROR_MESSAGE_CODE.defaultMessage())
        .hasParserErrorPropertiesContainingAllEntriesOf(parserErrorArgs)
        .hasParserErrorPropertiesContainingExactlyEntriesOf(parserErrorArgs);
  }

  @Test
  void builder_constructsExceptionWithoutParserErrorArgs() {

    final var expectedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage_fr = ERROR_MESSAGE_CODE.message(Locale.FRENCH) + ' ' + PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH);

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_MESSAGE_CODE)
        .parserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .build();

    assertThat(actual)
        .hasCause(cause)
        .hasInvalidValue(INVALID_VALUE)
        .hasTargetTypeClass(SomeClass.class)
        .hasMessage(expectedMessage)
        .hasLocalizedMessage(expectedLocalizedMessage)
        .hasLocalizedMessage(Locale.FRENCH, expectedLocalizedMessage_fr)
        .hasErrorCode(ERROR_MESSAGE_CODE)
        .hasErrorMessage(ERROR_MESSAGE_CODE.defaultMessage())
        .hasErrorMessage(Locale.FRENCH, ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .hasParserErrorMessage(PARSER_ERROR_MESSAGE_CODE.defaultMessage())
        .hasParserErrorMessage(Locale.FRENCH, PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasNoParserErrorProperties();
  }

  @Test
  void builder_constructsExceptionWithParserErrorArgs() {

    final var expectedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage = ERROR_MESSAGE_CODE.defaultMessage() + ' ' + PARSER_ERROR_MESSAGE_CODE.defaultMessage();
    final var expectedLocalizedMessage_fr = ERROR_MESSAGE_CODE.message(Locale.FRENCH) + ' ' + PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH);

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_MESSAGE_CODE)
        .parserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .addParserErrorCodeArg("invalidCodePoint", "A")
        .build();

    assertThat(actual)
        .hasCause(cause)
        .hasInvalidValue(INVALID_VALUE)
        .hasTargetTypeClass(SomeClass.class)
        .hasMessage(expectedMessage)
        .hasLocalizedMessage(expectedLocalizedMessage)
        .hasLocalizedMessage(Locale.FRENCH, expectedLocalizedMessage_fr)
        .hasErrorCode(ERROR_MESSAGE_CODE)
        .hasErrorMessage(ERROR_MESSAGE_CODE.defaultMessage())
        .hasErrorMessage(Locale.FRENCH, ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .hasParserErrorMessage(PARSER_ERROR_MESSAGE_CODE.defaultMessage())
        .hasParserErrorMessage(Locale.FRENCH, PARSER_ERROR_MESSAGE_CODE.message(Locale.FRENCH))
        .hasParserErrorPropertiesContainingAllEntriesOf(parserErrorArgs)
        .hasParserErrorPropertiesContainingExactlyEntriesOf(parserErrorArgs);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void builder_constructsExceptionIgnoringNullOrBlankArgKey(final String argKey) {

    final var actual = InvalidValueException.builder()
        .addParserErrorCodeArg(argKey, "A")
        .build();

    assertThat(actual).hasNoParserErrorProperties();
  }

  @Test
  void builder_constructsExceptionWithNonBlankArgKey() {

    final var actual = InvalidValueException.builder()
        .addParserErrorCodeArg("A", "B")
        .build();

    assertThat(actual).hasParserErrorPropertiesContainingAllEntriesOf(Map.of("A", "B"));
  }

  @Test
  void builder_constructsExceptionWithNullInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue(null)
        .build();

    assertThat(actual).hasInvalidValue(null);
  }

  @Test
  void builder_constructsExceptionWithStringInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue("AABB")
        .build();

    assertThat(actual).hasInvalidValue("AABB");
  }

  @Test
  void builder_constructsExceptionWithCharSequenceInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue(new SomeCharSequence(new char[]{'A', 'Z'}))
        .build();

    assertThat(actual).hasInvalidValue("AZ");
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      aa   | some error message-A. | ss   | some parser error message-S.                | null | some error message-A. some parser error message-S.
      bb   | some error message-B. | tt   | some parser error message-T.                | ''   | some error message-B. some parser error message-T.
      cc.c | some error message-C. | uu.u | some parser error message-U.                | '  ' | some error message-C. some parser error message-U.
      dc.d | some error message-D. | vv.v | some parser error message-V.                | JJKK | some error message-D. some parser error message-V.
      ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null | some error message-E. some parser error message-W with value null.
      ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | ''   | some error message-F. some parser error message-X with value .
      gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | '  ' | some error message-G. some parser error message-Y with value   .
      hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | JJKK | some error message-H. some parser error message-Z with value JJKK.
      """, delimiter = '|', nullValues = "null")
  void getMessage_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String parserErrorMessageArg,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);
    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

    final var actual = InvalidValueException.builder()
        .errorCode(messageCode)
        .parserErrorCode(parserMessageCode)
        .addParserErrorCodeArg("some-arg", parserErrorMessageArg)
        .build();

    assertThat(actual).hasMessage(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      xx | aa   | some error message-A. | ss   | some parser error message-S.                | null | some error message-A. some parser error message-S.
      xx | bb   | some error message-B. | tt   | some parser error message-T.                | ''   | some error message-B. some parser error message-T.
      xx | cc.c | some error message-C. | uu.u | some parser error message-U.                | '  ' | some error message-C. some parser error message-U.
      xx | dc.d | some error message-D. | vv.v | some parser error message-V.                | JJKK | some error message-D. some parser error message-V.
      xx | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null | some error message-E. some parser error message-W with value null.
      xx | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | ''   | some error message-F. some parser error message-X with value .
      xx | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | '  ' | some error message-G. some parser error message-Y with value   .
      xx | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | JJKK | some error message-H. some parser error message-Z with value JJKK.
      
      es | aa   | some error message-A. | ss   | some parser error message-S.                | null | algún mensaje de error-A. algún mensaje de error del analizador-S.
      es | bb   | some error message-B. | tt   | some parser error message-T.                | ''   | algún mensaje de error-B. algún mensaje de error del analizador-T.
      es | cc.c | some error message-C. | uu.u | some parser error message-U.                | '  ' | algún mensaje de error-C. algún mensaje de error del analizador-U.
      es | dc.d | some error message-D. | vv.v | some parser error message-V.                | JJKK | algún mensaje de error-D. algún mensaje de error del analizador-V.
      es | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null | algún mensaje de error-E. algún mensaje de error del analizador-W con valor null.
      es | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | ''   | algún mensaje de error-F. algún mensaje de error del analizador-X con valor .
      es | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | '  ' | algún mensaje de error-G. algún mensaje de error del analizador-Y con valor   .
      es | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | JJKK | algún mensaje de error-H. algún mensaje de error del analizador-Z con valor JJKK.
      
      de | aa   | some error message-A. | ss   | some parser error message-S.                | null | einige Fehlermeldung-A. einige Parser-Fehlermeldungen-S.
      de | bb   | some error message-B. | tt   | some parser error message-T.                | ''   | einige Fehlermeldung-B. einige Parser-Fehlermeldungen-T.
      de | cc.c | some error message-C. | uu.u | some parser error message-U.                | '  ' | einige Fehlermeldung-C. einige Parser-Fehlermeldungen-U.
      de | dc.d | some error message-D. | vv.v | some parser error message-V.                | JJKK | einige Fehlermeldung-D. einige Parser-Fehlermeldungen-V.
      de | ee-e | some error message-E. | ww-w | some parser error message-W with value {0}. | null | einige Fehlermeldung-E. einige Parser-Fehlermeldungen-W mit Wert null.
      de | ff_f | some error message-F. | xx_x | some parser error message-X with value {0}. | ''   | einige Fehlermeldung-F. einige Parser-Fehlermeldungen-X mit Wert .
      de | gggg | some error message-G. | yyyy | some parser error message-Y with value {0}. | '  ' | einige Fehlermeldung-G. einige Parser-Fehlermeldungen-Y mit Wert   .
      de | hhhh | some error message-H. | zzzz | some parser error message-Z with value {0}. | JJKK | einige Fehlermeldung-H. einige Parser-Fehlermeldungen-Z mit Wert JJKK.
      """, delimiter = '|', nullValues = "null")
  void getLocalizedMessage_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      final String parserMessageCodeString, final String parserMessageCodeMessage,
      final String parserErrorMessageArg,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);
    final var parserMessageCode = Factory.parserMessageCode(parserMessageCodeString, parserMessageCodeMessage);

    final var actual = InvalidValueException.builder()
        .errorCode(messageCode)
        .parserErrorCode(parserMessageCode)
        .addParserErrorCodeArg("some-arg", parserErrorMessageArg)
        .build();

    assertThat(actual).hasLocalizedMessage(locale, expectedMessage);
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

    final var actual = InvalidValueException.combineMessagesIntoSentences(message1, message2);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void toString_createsExpectedString() {

    when(cause.getMessage()).thenReturn("some exception message");

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_MESSAGE_CODE)
        .parserErrorCode(PARSER_ERROR_MESSAGE_CODE)
        .addParserErrorCodeArg("invalidCodePoint", "A")
        .build();

    assertThat(actual).hasToString("""
        InvalidValueException{message='some default error message. some default parser error message.', messageCode='some.error.code', defaultErrorMessage='some default error message.', parserMessageCode='some.parser.error.code', defaultParserErrorMessage='some default parser error message.', invalidCodePoint='A', targetTypeClass='class org.typefactory.InvalidValueExceptionTest$SomeClass', cause='Exception - some exception message'}""");
  }


  static final class SomeClass {

  }

  static final class SomeCharSequence implements CharSequence {

    private final char[] chars;

    public SomeCharSequence(final char[] chars) {
      this.chars = chars == null ? new char[0] : chars;
    }

    @Override
    public int length() {
      return chars.length;
    }

    @Override
    public char charAt(int index) {
      return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
      final char[] result = new char[end - start];
      System.arraycopy(chars, start, result, 0, result.length);
      return new SomeCharSequence(result);
    }

    @Override
    public String toString() {
      return new String(chars);
    }
  }
}
