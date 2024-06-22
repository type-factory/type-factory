package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.IntegerTypeParser;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.testutils.CodePointArrayConverter;

class IntegerTypeParserBuilderImplTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      CUSTOM_BASE_CODEPOINTS | EXPECTED_MESSAGE
      [0, 0]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 1]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 1, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [0, 1, 2, 2]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, a]                 | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, b]              | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, b, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      [a, b, c, c]           | Custom numeric-base character-set must not contain duplicates – check both your custom base character-set and case-sensitivity of the parser.
      """,
      delimiter = '|',
      useHeadersInDisplayName = true)
  void allowCustomBaseNumbers_duplicateBaseCharactersThrowsException(
      @ConvertWith(CodePointArrayConverter.class) final int[] customBaseCodePoints,
      final String expectedMessage) {

    final var builder = IntegerTypeParser.builder()
        .allowCustomBaseNumbers(customBaseCodePoints);

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(builder::build)
        .withMessageStartingWith(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      -2147483648
       2147483647
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueInclusive_valueShouldSucceed(final int minValue) {
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().minValueInclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().minValueInclusive((long) minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      -2147483648
       2147483647
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueExclusive_valueShouldSucceed(final int minValue) {
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().minValueExclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().minValueExclusive((long) minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      -2147483648
       2147483647
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueInclusive_valueShouldSucceed(final int minValue) {
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().maxValueInclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().maxValueInclusive((long) minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      -2147483648
       2147483647
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueExclusive_valueShouldSucceed(final int minValue) {
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().maxValueExclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> IntegerTypeParser.builder().maxValueExclusive((long) minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                 MIN_VALUE
               -2147483649
                2147483648
      -9223372036854775808
       9223372036854775807
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueInclusive_shouldThrowException(final long minValue) {

    final String expectedMessage = "An invalid minimum value has been configured for the IntegerTypeParser – minimum value must greater-than or equal to Integer.MIN_VALUE.";

        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> IntegerTypeParser.builder().minValueInclusive(minValue).build())
            .withMessageStartingWith(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                 MIN_VALUE
               -2147483649
                2147483648
      -9223372036854775808
       9223372036854775807
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueExclusive_shouldThrowException(final long minValue) {

    final String expectedMessage = "An invalid minimum value has been configured for the IntegerTypeParser – minimum value must greater-than or equal to Integer.MIN_VALUE.";

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> IntegerTypeParser.builder().minValueExclusive(minValue).build())
        .withMessageStartingWith(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                 MIN_VALUE
               -2147483649
                2147483648
      -9223372036854775808
       9223372036854775807
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueInclusive_shouldThrowException(final long maxValue) {

    final String expectedMessage = "An invalid maximum value has been configured for the IntegerTypeParser – maximum value must be less-than or equal to Integer.MAX_VALUE.";

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> IntegerTypeParser.builder().maxValueInclusive(maxValue).build())
        .withMessageStartingWith(expectedMessage);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
                 MIN_VALUE
               -2147483649
                2147483648
      -9223372036854775808
       9223372036854775807
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueExclusive_shouldThrowException(final long maxValue) {

    final var expectedMessage = "An invalid maximum value has been configured for the IntegerTypeParser – maximum value must be less-than or equal to Integer.MAX_VALUE.";

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> IntegerTypeParser.builder().maxValueExclusive(maxValue).build())
        .withMessageStartingWith(expectedMessage);
  }

}
