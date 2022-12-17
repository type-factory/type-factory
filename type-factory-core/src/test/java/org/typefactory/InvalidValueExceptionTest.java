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
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.typefactory.InvalidValueException.ParserErrorCode;
import org.typefactory.impl.Factory;

@ExtendWith(MockitoExtension.class)
class InvalidValueExceptionTest {

  static final String INVALID_VALUE = "Some invalid value";
  static final ErrorCode ERROR_CODE = ErrorCode.of("some.error.code", "some default error message.");

  static final ParserErrorCode PARSER_ERROR_CODE = Factory.parserErrorCode("some.parser.error.code", "some default parser error message.");

  static final LinkedHashMap<String, Serializable> parserErrorArgs = new LinkedHashMap<>(Map.of("invalidCodePoint", "A"));

  @Mock
  Exception cause;


  @Test
  void constructor_constructsExceptionWithoutParserErrorArgs() {

    final var actual = new InvalidValueException(cause, INVALID_VALUE, SomeClass.class, ERROR_CODE, PARSER_ERROR_CODE, null);

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getInvalidValue()).isEqualTo(INVALID_VALUE);
    assertThat(actual.getTargetTypeClass()).isEqualTo(SomeClass.class);
    assertThat(actual.getMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorCode()).isEqualTo(ERROR_CODE.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorCode()).isEqualTo(PARSER_ERROR_CODE.code());
    assertThat(actual.getParserErrorMessage()).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorMessage(Locale.FRENCH)).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorProperties()).isNotNull().isEmpty();
  }

  @Test
  void constructor_constructsExceptionWithParserErrorArgs() {

    final var actual = new InvalidValueException(cause, INVALID_VALUE, SomeClass.class, ERROR_CODE, PARSER_ERROR_CODE, parserErrorArgs);

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getInvalidValue()).isEqualTo(INVALID_VALUE);
    assertThat(actual.getTargetTypeClass()).isEqualTo(SomeClass.class);
    assertThat(actual.getMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorCode()).isEqualTo(ERROR_CODE.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorCode()).isEqualTo(PARSER_ERROR_CODE.code());
    assertThat(actual.getParserErrorMessage()).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorMessage(Locale.FRENCH)).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorProperties()).containsAllEntriesOf(parserErrorArgs);
  }

  @Test
  void builder_constructsExceptionWithoutParserErrorArgs() {

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_CODE)
        .parserErrorCode(PARSER_ERROR_CODE)
        .build();

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getInvalidValue()).isEqualTo(INVALID_VALUE);
    assertThat(actual.getTargetTypeClass()).isEqualTo(SomeClass.class);
    assertThat(actual.getMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorCode()).isEqualTo(ERROR_CODE.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorCode()).isEqualTo(PARSER_ERROR_CODE.code());
    assertThat(actual.getParserErrorMessage()).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorMessage(Locale.FRENCH)).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorProperties()).isNotNull().isEmpty();
  }

  @Test
  void builder_constructsExceptionWithParserErrorArgs() {

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_CODE)
        .parserErrorCode(PARSER_ERROR_CODE)
        .addParserErrorCodeArgs("invalidCodePoint", "A")
        .build();

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getInvalidValue()).isEqualTo(INVALID_VALUE);
    assertThat(actual.getTargetTypeClass()).isEqualTo(SomeClass.class);
    assertThat(actual.getMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_CODE.defaultMessage() + ' ' + PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorCode()).isEqualTo(ERROR_CODE.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorCode()).isEqualTo(PARSER_ERROR_CODE.code());
    assertThat(actual.getParserErrorMessage()).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorMessage(Locale.FRENCH)).isEqualTo(PARSER_ERROR_CODE.defaultMessage());
    assertThat(actual.getParserErrorProperties()).containsAllEntriesOf(parserErrorArgs);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void builder_constructsExceptionIgnoringNullOrBlankArgKey(final String argKey) {

    final var actual = InvalidValueException.builder()
        .addParserErrorCodeArgs(argKey, "A")
        .build();

    assertThat(actual.getParserErrorProperties()).isEmpty();
  }

  @Test
  void builder_constructsExceptionWithNonBlankArgKey() {

    final var actual = InvalidValueException.builder()
        .addParserErrorCodeArgs("A", "B")
        .build();

    assertThat(actual.getParserErrorProperties()).containsAllEntriesOf(Map.of("A", "B"));
  }

  @Test
  void builder_constructsExceptionWithNullInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue(null)
        .build();

    assertThat(actual.getInvalidValue()).isNull();
  }

  @Test
  void builder_constructsExceptionWithStringInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue("AABB")
        .build();

    assertThat(actual.getInvalidValue()).isNotNull().isEqualTo("AABB");
  }

  @Test
  void builder_constructsExceptionWithCharSequenceInvalidValue() {

    final var actual = InvalidValueException.builder()
        .invalidValue(new SomeCharSequence(new char[]{'A', 'Z'}))
        .build();

    assertThat(actual.getInvalidValue()).isNotNull().isEqualTo("AZ");
  }

  @Test
  void toString_createsExpectedString() {

    when(cause.getMessage()).thenReturn("some exception message");

    final var actual = InvalidValueException.builder()
        .cause(cause)
        .invalidValue(INVALID_VALUE)
        .targetTypeClass(SomeClass.class)
        .errorCode(ERROR_CODE)
        .parserErrorCode(PARSER_ERROR_CODE)
        .addParserErrorCodeArgs("invalidCodePoint", "A")
        .build();

    assertThat(actual.toString()).isEqualTo("""
        InvalidValueException{message='some default error message. some default parser error message.', errorCode='some.error.code', defaultErrorMessage='some default error message.', parserErrorCode='some.parser.error.code', defaultParserErrorMessage='some default parser error message.', invalidCodePoint='A', targetTypeClass='class org.typefactory.InvalidValueExceptionTest$SomeClass', cause='Exception - some exception message'}""");
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
      final char [] result = new char [end - start];
      System.arraycopy(chars, start, result, 0, result.length);
      return new SomeCharSequence(result);
    }

    @Override
    public String toString() {
      return new String(chars);
    }
  }
}
