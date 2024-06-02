package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.LongTypeParser;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.testutils.CodePointArrayConverter;

class LongTypeParserBuilderImplTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      CUSTOM_BASE_CODEPOINTS | EXPECTED_MESSAGE
      [0, 0]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [0, 1, 1]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [0, 1, 1, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [0, 1, 2, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [a, a]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [a, b, b]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [a, b, b, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      [a, b, c, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void allowCustomBaseNumbers_duplicateBaseCharactersThrowsException(
      @ConvertWith(CodePointArrayConverter.class) final int [] customBaseCodePoints,
      final String expectedMessage) {

    final var builder = LongTypeParser.builder()
        .allowCustomBaseNumbers(customBaseCodePoints);

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(builder::build)
        .withMessageStartingWith(expectedMessage);
  }

}
