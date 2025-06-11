package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.testutils.IntArrayConverter;

class ParseResultImplTest extends AbstractTypeParserTest {


  @ParameterizedTest(name = "name = [{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE   | EXPECTED_VALUE
      null    | null
      ''      | ''
      ' '     | ' '
      'hello' | 'hello'
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void parsedValue_containsExpectedValue(final String value, final String expectedValue ) {
    final var parseResult = new ParseResultImpl();
    parseResult.setParsedValue(value);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
    assertThat(parseResult.parsedValueWasValid()).isTrue();
    assertThat(parseResult.parsedValueWasInvalid()).isFalse();
  }

  @ParameterizedTest(name = "name = [{index}] {arguments}")
  @CsvSource(textBlock = """
      INVALID_CODE_POINTS | EXPECTS_INVALID_TO_STRING
      [\u0000]            | [U+0000 NULL]
      [\b]                | [U+0008 BACKSPACE]
      [']                 | [' U+0027 APOSTROPHE]
      [a]                 | ['a' U+0061 LATIN SMALL LETTER A]
      [\u200B]            | [U+200B ZERO WIDTH SPACE]
      [a, \b, !, \u200B]  | [U+0008 BACKSPACE, '!' U+0021 EXCLAMATION MARK, 'a' U+0061 LATIN SMALL LETTER A, U+200B ZERO WIDTH SPACE]
      """, delimiter = '|', nullValues = "null", quoteCharacter = '`', useHeadersInDisplayName = true)
  void invalidCodePoints_containsExpectedValues(
      @ConvertWith(IntArrayConverter.class) final int [] values,
      final String expectedInvalidValuesToString) {

    final var parseResult = new ParseResultImpl();
    for (int value : values) {
      parseResult.addInvalidCodePoint(value);
    }
    assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(values);
    assertThat(parseResult.invalidCodePointsToString()).isEqualTo(expectedInvalidValuesToString);
    assertThat(parseResult.parsedValueWasValid()).isFalse();
    assertThat(parseResult.parsedValueWasInvalid()).isTrue();
  }

  @Test
  void invalidCodePoints_returnsEmptyWhenNoInvalidCodePoints() {

    final var parseResult = new ParseResultImpl();
    assertThat(parseResult.invalidCodePoints()).isEmpty();
    assertThat(parseResult.invalidCodePointsToString()).isEqualTo("[]");
    assertThat(parseResult.parsedValueWasValid()).isTrue();
    assertThat(parseResult.parsedValueWasInvalid()).isFalse();
  }

  @Test
  void invalidCodePoints_containsExpectedValuesForWhitespace() {

    final var parseResult = new ParseResultImpl();
    parseResult.addInvalidCodePoint(' ');  // space
    parseResult.addInvalidCodePoint('\n'); // newline
    parseResult.addInvalidCodePoint('\t'); // tab
    parseResult.addInvalidCodePoint('\r'); // carriage return
    parseResult.addInvalidCodePoint('\f'); // form feed
    parseResult.addInvalidCodePoint('\u000B'); // vertical tab

    assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(' ', '\n', '\t', '\r', '\f', '\u000B');
    assertThat(parseResult.invalidCodePointsToString())
        .isEqualTo("[U+0009 CHARACTER TABULATION, U+000A LINE FEED (LF), U+000B LINE TABULATION, U+000C FORM FEED (FF), U+000D CARRIAGE RETURN (CR), U+0020 SPACE]");
    assertThat(parseResult.parsedValueWasValid()).isFalse();
    assertThat(parseResult.parsedValueWasInvalid()).isTrue();
  }

  @ParameterizedTest(name = "name = [{index}] {arguments}")
  @CsvSource(textBlock = """
      PARSED_VALUE  | INVALID_CODE_POINTS | EXPECTS_TO_STRING
      null          | [\u0000]            | {parsedValue: null, parsedValueWasValid: false, invalidCodePoints: [U+0000 NULL]}
      ``            | [\b]                | {parsedValue: , parsedValueWasValid: false, invalidCodePoints: [U+0008 BACKSPACE]}
      ` `           | [']                 | {parsedValue:  , parsedValueWasValid: false, invalidCodePoints: [' U+0027 APOSTROPHE]}
      a             | []                  | {parsedValue: a, parsedValueWasValid: true, invalidCodePoints: []}
      hello         | [a]                 | {parsedValue: hello, parsedValueWasValid: false, invalidCodePoints: ['a' U+0061 LATIN SMALL LETTER A]}
      xyz XYZ       | [\u200B]            | {parsedValue: xyz XYZ, parsedValueWasValid: false, invalidCodePoints: [U+200B ZERO WIDTH SPACE]}
      xyz-XYZ       | [a, \b, !, \u200B]  | {parsedValue: xyz-XYZ, parsedValueWasValid: false, invalidCodePoints: [U+0008 BACKSPACE, '!' U+0021 EXCLAMATION MARK, 'a' U+0061 LATIN SMALL LETTER A, U+200B ZERO WIDTH SPACE]}
      """, delimiter = '|', nullValues = "null", quoteCharacter = '`', useHeadersInDisplayName = true)
  void toString_containsExpectedValues(
      final String value,
      @ConvertWith(IntArrayConverter.class) final int [] invalidCodePoints,
      final String expectedToString) {

    final var parseResult = new ParseResultImpl();
    parseResult.setParsedValue(value);
    for (int codePoint : invalidCodePoints) {
      parseResult.addInvalidCodePoint(codePoint);
    }

    assertThat(parseResult.toString()).isEqualTo(expectedToString);
  }

}
