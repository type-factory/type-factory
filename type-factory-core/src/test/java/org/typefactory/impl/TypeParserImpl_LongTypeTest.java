///*
//   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//*/
//package org.typefactory.impl;
//
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.assertj.core.api.Assertions.assertThatObject;
//
//import java.io.Serial;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.NullAndEmptySource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.typefactory.InvalidValueException;
//import org.typefactory.LongType;
//import org.typefactory.TypeParser;
//
//class TypeParserImpl_LongTypeTest {
//
//  @ParameterizedTest
//  @NullAndEmptySource
//  @ValueSource(strings = "  ")
//  void parseToLongType_withPreserveNullAndEmpty_returnsNullForNullValue(final String value) {
//
//    final var parser = TypeParser.builder()
//        .preserveNullAndEmpty()
//        .removeAllWhitespace()
//        .build();
//
//    final var actual = parser.parseToLongType(value, SomeLongType::new);
//
//    assertThatObject(actual).isNull();
//  }
//
//  @ParameterizedTest
//  @NullAndEmptySource
//  @ValueSource(strings = "  ")
//  void parseToLongType_withConvertEmptyToNull_returnsNullForNullValue(final String value) {
//
//    final var parser = TypeParser.builder()
//        .convertEmptyToNull()
//        .removeAllWhitespace()
//        .build();
//
//    final var actual = parser.parseToLongType(value, SomeLongType::new);
//
//    assertThatObject(actual).isNull();
//  }
//
//  @ParameterizedTest
//  @CsvSource(textBlock = """
//      -9223372036854775808 | -9223372036854775808 | -9223372036854775808
//      -2147483648          | -2147483648          | -2147483648
//      -32768               | -32768               | -32768
//      -55 66               | -5566                | -5566
//      -44                  | -44                  | -44
//      -1                   | -1                   | -1
//      0                    | 0                    | 0
//      ' 0 '                | 0                    | 0
//      1                    | 1                    | 1
//      11                   | 11                   | 11
//      ' 22 '               | 22                   | 22
//      33 44                | 3344                 | 3344
//      32767                | 32767                | 32767
//      2147483647           | 2147483647           | 2147483647
//      9223372036854775807  | 9223372036854775807  | 9223372036854775807
//
//      """, delimiter = '|', nullValues = "null")
//  void parseToLongType_withPreserveNullAndEmpty_returnsAsExpected(
//      final String value, final Long expectedValue, final String expectedString) {
//
//    final var parser = TypeParser.builder()
//        .preserveNullAndEmpty()
//        .removeAllWhitespace()
//        .acceptHyphenAndConvertAllDashesToHyphen()
//        .acceptDigits0to9()
//        .build();
//
//    final var actual = parser.parseToLongType(value, SomeLongType::new);
//
//    assertThatObject(actual).isNotNull();
//    assertThatObject(actual.value()).isEqualTo(expectedValue);
//    assertThatObject(actual).hasToString(expectedString);
//  }
//
//  @ParameterizedTest
//  @CsvSource(textBlock = """
//      minValue | maxValue | value | expectedValue
//          -100 |       -1 |  -100 |          -100
//          -100 |       -1 |   -99 |           -99
//          -100 |       -1 |    -2 |            -2
//          -100 |       -1 |    -1 |            -1
//             0 |        0 |     0 |             0
//             1 |      100 |     1 |             1
//             1 |      100 |     2 |             2
//             1 |      100 |    99 |            99
//             1 |      100 |   100 |           100
//      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
//  void parseToLongType_withMinAndMaxValueAndWithPreserveNullAndEmpty_parsesSuccessfully(
//      final Long minValue, final Long maxValue, final String value, final Long expectedValue) {
//
//    final var parser = TypeParser.builder()
//        .preserveNullAndEmpty()
//        .removeAllWhitespace()
//        .acceptHyphenAndConvertAllDashesToHyphen()
//        .acceptDigits0to9()
//        .minValueInclusive(minValue)
//        .maxValueInclusive(maxValue)
//        .build();
//
//    final var actual = parser.parseToLongType(value, SomeLongType::new);
//
//    assertThatObject(actual).isNotNull();
//    assertThatObject(actual.value()).isEqualTo(expectedValue);
//  }
//
//  @ParameterizedTest
//  @CsvSource(textBlock = """
//      minValue | maxValue | value | expectedMessage
//          -100 |       -1 |  -101 | Invalid value - must be greater than or equal to -100.
//          -100 |       -1 |    -0 | Invalid value - must be less than or equal to -1.
//             0 |        0 |    -1 | Invalid value - must be greater than or equal to 0.
//             0 |        0 |     1 | Invalid value - must be less than or equal to 0.
//             1 |      100 |     0 | Invalid value - must be greater than or equal to 1.
//             1 |      100 |   101 | Invalid value - must be less than or equal to 100.
//      """, delimiter = '|', nullValues = "null", useHeadersInDisplayName = true)
//  void parseToLongType_withMinAndMaxValueAndWithPreserveNullAndEmpty_throwsException(
//      final Long minValue, final Long maxValue, final String value, final String expectedMessage) {
//
//    final var parser = TypeParser.builder()
//        .preserveNullAndEmpty()
//        .removeAllWhitespace()
//        .acceptHyphenAndConvertAllDashesToHyphen()
//        .acceptDigits0to9()
//        .minValueInclusive(minValue)
//        .maxValueInclusive(maxValue)
//        .build();
//
//    assertThatExceptionOfType(InvalidValueException.class)
//        .isThrownBy(() -> parser.parseToLongType(value, SomeLongType::new))
//        .withMessage(expectedMessage);
//  }
//
//  static class SomeLongType extends LongType {
//
//    @Serial
//    private static final long serialVersionUID = -971025988171443056L;
//
//    public SomeLongType(Long value) {
//      super(value);
//    }
//  }
//}
