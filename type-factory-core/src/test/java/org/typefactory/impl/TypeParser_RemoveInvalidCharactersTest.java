package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_RemoveInvalidCharactersTest {

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE                                             | IS_VALID_VALUE | INVALID_CODEPOINTS | EXPECTED_VALUE
      abcdefg                                           | true           |                    | abcdefg
      abc1234                                           | true           |                    | abc1234
      abc-123                                           | false          | -                  | abc123
      123_abc                                           | false          | _                  | 123abc
      $bcdefg                                           | false          | $                  | bcdefg
      abcdef$                                           | false          | $                  | abcdef
      abcd$fg                                           | false          | $                  | abcdfg
      abcd$#%                                           | false          | $#%                | abcd
      abc defg                                          | false          | ' '                | abcdefg
      $(".classname").each(function(){alert("Boo!");}); | false          | $()!".{};          | classnameeachfunctionalertBoo
      """, delimiter = '|', useHeadersInDisplayName = true)
  void parseToStringRemovingInvalidCharacters_returnsAsExpected(
      final String value,
      final boolean isValidValue,
      final String expectedInvalidCodePoints,
      final String expectedValue) {

    final var typeParser = TypeParser.builder()
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits()
        .build();

    final var parseResult = typeParser.parseToStringRemovingInvalidCharacters(value);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isEqualTo(isValidValue);
    assertThat(parseResult.parsedValueWasInvalid()).isNotEqualTo(isValidValue);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
    if (expectedInvalidCodePoints != null) {
      assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(expectedInvalidCodePoints.codePoints().toArray());
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE                                             | IS_VALID_VALUE | INVALID_CODEPOINTS | EXPECTED_VALUE
      abcdefg                                           | true           |                    | abcdefg
      abc1234                                           | true           |                    | abc1234
      abc-123                                           | false          | -                  | abc123
      123_abc                                           | false          | _                  | 123abc
      $bcdefg                                           | false          | $                  | bcdefg
      abcdef$                                           | false          | $                  | abcdef
      abcd$fg                                           | false          | $                  | abcdfg
      abcd$#%                                           | false          | $#%                | abcd
      abc defg                                          | false          | ' '                | abcdefg
      $(".classname").each(function(){alert("Boo!");}); | false          | $()!".{};          | classnameeachfunctionalertBoo
      """, delimiter = '|', useHeadersInDisplayName = true)
  void parseToStringRemovingInvalidCharacters_returnsAsExpectedWithCharacterConversion(
      final String value,
      final boolean isValidValue,
      final String expectedInvalidCodePoints,
      final String expectedValue) {

    final var typeParser = TypeParser.builder()
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits()
        .build();

    final var parseResult = typeParser.parseToStringRemovingInvalidCharacters(value);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isEqualTo(isValidValue);
    assertThat(parseResult.parsedValueWasInvalid()).isNotEqualTo(isValidValue);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
    if (expectedInvalidCodePoints != null) {
      assertThat(parseResult.invalidCodePoints()).containsExactlyInAnyOrder(expectedInvalidCodePoints.codePoints().toArray());
    }
  }

}
