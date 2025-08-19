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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TypeParserBuilderExceptionTest {

  static final MessageCode ERROR_MESSAGE_CODE_NO_ARGS = MessageCode.of("some.error.code", "some default error message");

  static final MessageCode ERROR_MESSAGE_CODE_WITH_ARGS = MessageCode.of("some.error.code", "some default error message because of {0}");

  static final LinkedHashMap<String, Serializable> ERROR_MESSAGE_CODE_ARGS = new LinkedHashMap<>(Map.of("invalidCodePoint", "A"));

  @Mock
  Exception cause;

  @Test
  void constructor_withNoMessageCode() {
    final var actual = new TypeParserBuilderException(cause, null, ERROR_MESSAGE_CODE_ARGS);

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getMessage()).isNull();
    assertThat(actual.getLocalizedMessage()).isNotNull().isEmpty();
    assertThat(actual.getMessageCode()).isNotNull().isEmpty();
    assertThat(actual.getErrorMessage()).isNotNull().isEmpty();
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isNotNull().isEmpty();
  }

  @Test
  void constructor_withMessageCodeNoMessageArgs() {
    final var actual = new TypeParserBuilderException(cause, ERROR_MESSAGE_CODE_NO_ARGS, null);

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getMessageCode()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
  }

  @Test
  void builder_withNoMessageCode() {
    final var actual = TypeParserBuilderException.builder()
        .cause(cause)
        .addMessageCodeArg("invalidCodePoint", "A")
        .build();

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getMessage()).isNull();
    assertThat(actual.getLocalizedMessage()).isNotNull().isEmpty();
    assertThat(actual.getMessageCode()).isNotNull().isEmpty();
    assertThat(actual.getErrorMessage()).isNotNull().isEmpty();
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isNotNull().isEmpty();
  }

  @Test
  void builder_withMessageCodeNoMessageArgs() {
    final var actual = TypeParserBuilderException.builder()
        .cause(cause)
        .messageCode(ERROR_MESSAGE_CODE_NO_ARGS)
        .build();

    assertThat(actual.getCause()).isEqualTo(cause);
    assertThat(actual.getMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getLocalizedMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getMessageCode()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.code());
    assertThat(actual.getErrorMessage()).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
    assertThat(actual.getErrorMessage(Locale.FRENCH)).isEqualTo(ERROR_MESSAGE_CODE_NO_ARGS.defaultMessage());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = "  ")
  void builder_constructsExceptionIgnoringNullOrBlankArgKey(final String argKey) {

    final var actual = TypeParserBuilderException.builder()
        .addMessageCodeArg(argKey, "A")
        .build();

    assertThat(actual.getErrorProperties()).isEmpty();
  }

  @Test
  void builder_constructsExceptionWithNonBlankArgKey() {

    final var actual = TypeParserBuilderException.builder()
        .addMessageCodeArg("A", "B")
        .build();

    assertThat(actual.getErrorProperties()).containsAllEntriesOf(Map.of("A", "B"));
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      aa    | some error message-A.                | null | some error message-A.
      bb    | some error message-B.                | ''   | some error message-B.
      cc.c  | some error message-C.                | '  ' | some error message-C.
      dc.d  | some error message-D.                | JJKK | some error message-D.
      ee-e1 | some error message-E with value {0}. | null | some error message-E with value null.
      ff_f1 | some error message-F with value {0}. | ''   | some error message-F with value .
      gggg1 | some error message-G with value {0}. | '  ' | some error message-G with value   .
      hhhh1 | some error message-H with value {0}. | JJKK | some error message-H with value JJKK.
      """, delimiter = '|', nullValues = "null")
  void getMessage_returnsAsExpected(
      final String messageCodeString, final String messageCodeMessage,
      final String errorMessageArg,
      final String expectedMessage) {

    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var typeParserBuilderException = TypeParserBuilderException.builder()
        .messageCode(messageCode)
        .addMessageCodeArg("some-arg", errorMessageArg)
        .build();

    final var actual = typeParserBuilderException.getMessage();

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      xx | aa    | some error message-A.                | null | some error message-A.
      xx | bb    | some error message-B.                | ''   | some error message-B.
      xx | cc.c  | some error message-C.                | '  ' | some error message-C.
      xx | dc.d  | some error message-D.                | JJKK | some error message-D.
      xx | ee-e1 | some error message-E with value {0}. | null | some error message-E with value null.
      xx | ff_f1 | some error message-F with value {0}. | ''   | some error message-F with value .
      xx | gggg1 | some error message-G with value {0}. | '  ' | some error message-G with value   .
      xx | hhhh1 | some error message-H with value {0}. | JJKK | some error message-H with value JJKK.

      es | aa    | some error message-A.                | null | algún mensaje de error-A.
      es | bb    | some error message-B.                | ''   | algún mensaje de error-B.
      es | cc.c  | some error message-C.                | '  ' | algún mensaje de error-C.
      es | dc.d  | some error message-D.                | JJKK | algún mensaje de error-D.
      es | ee-e1 | some error message-E with value {0}. | null | algún mensaje de error-E con valor null.
      es | ff_f1 | some error message-F with value {0}. | ''   | algún mensaje de error-F con valor .
      es | gggg1 | some error message-G with value {0}. | '  ' | algún mensaje de error-G con valor   .
      es | hhhh1 | some error message-H with value {0}. | JJKK | algún mensaje de error-H con valor JJKK.

      de | aa    | some error message-A.                | null | einige Fehlermeldung-A.
      de | bb    | some error message-B.                | ''   | einige Fehlermeldung-B.
      de | cc.c  | some error message-C.                | '  ' | einige Fehlermeldung-C.
      de | dc.d  | some error message-D.                | JJKK | einige Fehlermeldung-D.
      de | ee-e1 | some error message-E with value {0}. | null | einige Fehlermeldung-E mit Wert null.
      de | ff_f1 | some error message-F with value {0}. | ''   | einige Fehlermeldung-F mit Wert .
      de | gggg1 | some error message-G with value {0}. | '  ' | einige Fehlermeldung-G mit Wert   .
      de | hhhh1 | some error message-H with value {0}. | JJKK | einige Fehlermeldung-H mit Wert JJKK.
      """, delimiter = '|', nullValues = "null")
  void getLocalizedMessage_returnsAsExpected(
      final String language,
      final String messageCodeString, final String messageCodeMessage,
      final String messageCodeArgs,
      final String expectedMessage) {

    final var locale = new Locale(language);
    final var messageCode = MessageCode.of(messageCodeString, messageCodeMessage);

    final var typeParserBuilderException = TypeParserBuilderException.builder()
        .messageCode(messageCode)
        .addMessageCodeArg("some-arg", messageCodeArgs)
        .build();

    final var actual = typeParserBuilderException.getLocalizedMessage(locale);

    assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .isEqualTo(expectedMessage);
  }

  @Test
  void toString_withoutCauseCreatesExpectedString() {

    final var actual = TypeParserBuilderException.builder()
        .messageCode(ERROR_MESSAGE_CODE_WITH_ARGS)
        .addMessageCodeArg("invalidCodePoint", "A")
        .build();

    assertThat(actual).hasToString("""
        TypeParserBuilderException{message='some default error message because of A', messageCode='some.error.code', defaultErrorMessage='some default error message because of {0}', invalidCodePoint='A'}""");
  }

  @Test
  void toString_withCauseCreatesExpectedString() {

    when(cause.getMessage()).thenReturn("some exception message");

    final var actual = TypeParserBuilderException.builder()
        .cause(cause)
        .messageCode(ERROR_MESSAGE_CODE_WITH_ARGS)
        .addMessageCodeArg("invalidCodePoint", "A")
        .build();

    assertThat(actual).hasToString("""
        TypeParserBuilderException{message='some default error message because of A', messageCode='some.error.code', defaultErrorMessage='some default error message because of {0}', invalidCodePoint='A', cause='Exception - some exception message'}""");
  }


  private static final class SomeClass {

  }

  private static final class SomeCharSequence implements CharSequence {

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
      return new TypeParserBuilderExceptionTest.SomeCharSequence(result);
    }

    @Override
    public String toString() {
      return new String(chars);
    }
  }
}
