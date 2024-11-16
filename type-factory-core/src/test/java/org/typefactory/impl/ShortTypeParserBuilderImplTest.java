package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.ShortTypeParser;
import org.typefactory.TypeParserBuilderException;
import org.typefactory.testutils.CodePointArrayConverter;

class ShortTypeParserBuilderImplTest {

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

    final var builder = ShortTypeParser.builder()
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
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueInclusive_valueShouldSucceed(final short minValue) {
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueInclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueInclusive((int)minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueInclusive((long)minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueExclusive_valueShouldSucceed(final short minValue) {
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueExclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueExclusive((int)minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().minValueExclusive((long)minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueInclusive_valueShouldSucceed(final short minValue) {
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueInclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueInclusive((int)minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueInclusive((long)minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
        MIN_VALUE
           -32768
            32767
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueExclusive_valueShouldSucceed(final short minValue) {
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueExclusive(minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueExclusive((int)minValue).build());
    assertThatNoException().isThrownBy(() -> ShortTypeParser.builder().maxValueExclusive((long)minValue).build());
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |            MIN_VALUE |  EXPECTED_MESSAGE
      Integer |               -32769 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |                32768 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |          -2147483648 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |           2147483647 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Long    | -9223372036854775808 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Long    |  9223372036854775807 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueInclusive_shouldThrowException(
      final String type, final long minValue, final String expectedMessage) {

    final var builder = ShortTypeParser.builder();

    switch (type) {
      case "Integer":
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> builder.minValueInclusive((int) minValue))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> ShortTypeParser.builder().minValueInclusive(minValue).build())
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |            MIN_VALUE |  EXPECTED_MESSAGE
      Integer |               -32769 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |                32768 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |          -2147483648 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Integer |           2147483647 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Long    | -9223372036854775808 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      Long    |  9223372036854775807 |  An invalid minimum value has been configured for the ShortTypeParser – minimum value must greater-than or equal to Short.MIN_VALUE.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void minValueExclusive_shouldThrowException(
      final String type, final long minValue, final String expectedMessage) {

    final var builder = ShortTypeParser.builder();

    switch (type) {
      case "Integer":
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> builder.minValueExclusive((int) minValue))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> ShortTypeParser.builder().minValueExclusive(minValue).build())
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |            MIN_VALUE |  EXPECTED_MESSAGE
      Integer |               -32769 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |                32768 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |          -2147483648 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |           2147483647 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Long    | -9223372036854775808 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Long    |  9223372036854775807 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueInclusive_shouldThrowException(
      final String type, final long maxValue, final String expectedMessage) {

    final var builder = ShortTypeParser.builder();

    switch (type) {
      case "Integer":
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> builder.maxValueInclusive((int) maxValue))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> ShortTypeParser.builder().maxValueInclusive(maxValue).build())
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      TYPE    |            MIN_VALUE |  EXPECTED_MESSAGE
      Integer |               -32769 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |                32768 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |          -2147483648 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Integer |           2147483647 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Long    | -9223372036854775808 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      Long    |  9223372036854775807 |  An invalid maximum value has been configured for the ShortTypeParser – maximum value must be less-than or equal to Short.MAX_VALUE.
      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
  void maxValueExclusive_shouldThrowException(
      final String type, final long maxValue, final String expectedMessage) {

    final var builder = ShortTypeParser.builder();

    switch (type) {
      case "Integer":
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> builder.maxValueExclusive((int) maxValue))
            .withMessageStartingWith(expectedMessage);
        // fall-thru
      case "Long":
      default:
        assertThatExceptionOfType(TypeParserBuilderException.class)
            .isThrownBy(() -> ShortTypeParser.builder().maxValueExclusive(maxValue).build())
            .withMessageStartingWith(expectedMessage);
        break;
    }
  }

}
