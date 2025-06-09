package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.TypeParser;

class TypeParser_ReplaceInvalidCharactersTest {

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(textBlock = """
      VALUE                                             | IS_VALID_VALUE | EXPECTED_VALUE
      abcdefg                                           | true           | abcdefg
      abc1234                                           | true           | abc1234
      abc-123                                           | false          | abc�123
      123_abc                                           | false          | 123�abc
      $bcdefg                                           | false          | �bcdefg
      abcdef$                                           | false          | abcdef�
      abcd$fg                                           | false          | abcd�fg
      abcd$#%                                           | false          | abcd���
      abc defg                                          | false          | abc�defg
      $(".classname").each(function(){alert("Boo!");}); | false          | ����classname���each�function���alert��Boo�������
      """, delimiter = '|', useHeadersInDisplayName = true)
  void parseToStringReplacingInvalidCharacters_returnsAsExpected(
      final String value,
      final boolean isValidValue,
      final String expectedValue) {

    final var typeParser = TypeParser.builder()
        .acceptAllUnicodeLetters()
        .acceptAllUnicodeDecimalDigits()
        .build();

    final var parseResult = typeParser.parseToStringReplacingInvalidCharacters(value);
    assertThat(parseResult).isNotNull();
    assertThat(parseResult.parsedValueWasValid()).isEqualTo(isValidValue);
    assertThat(parseResult.parsedValueWasInvalid()).isNotEqualTo(isValidValue);
    assertThat(parseResult.parsedValue()).isEqualTo(expectedValue);
  }
}
