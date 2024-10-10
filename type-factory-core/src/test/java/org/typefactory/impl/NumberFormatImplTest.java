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
package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.typefactory.impl.Constants.ARABIC_DECIMAL_SEPARATOR;
import static org.typefactory.impl.Constants.COMMA;
import static org.typefactory.impl.Constants.FULL_STOP;
import static org.typefactory.impl.Constants.NARROW_NO_BREAK_SPACE;
import static org.typefactory.impl.Constants.NO_BREAK_SPACE;
import static org.typefactory.impl.Constants.SPACE;
import static org.typefactory.impl.Constants.THIN_SPACE;

import java.math.RoundingMode;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.NumberFormat;
import org.typefactory.TypeParserBuilderException;

public class NumberFormatImplTest {

  @Test
  void numberFormatBuilder_ShouldBuildNumberFormatWithDefaultValues() {
    // When
    NumberFormat numberFormat = NumberFormat.builder().build();

    // Then
    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getPrimaryDecimalSeparator()).isEqualTo('.');
    assertThat(numberFormat.getGroupingSize()).isEqualTo(3);
    assertThat(numberFormat.getRoundingMode()).isEqualTo(RoundingMode.HALF_EVEN);
  }

  @Test
  void numberFormatBuilder_ShouldBuildNumberFormatWithCustomValues() {
    // When
    NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator(',')
        .groupingSeparator('.')
        .groupingSize(4)
        .minimumIntegerDigits(1)
        .maximumIntegerDigits(10)
        .minimumFractionDigits(2)
        .maximumFractionDigits(5)
        .negativePrefix("(")
        .negativeSuffix(")")
        .positivePrefix("+")
        .positiveSuffix("!")
        .roundingMode(RoundingMode.DOWN)
        .zeroDigit('0')
        .build();

    // Then
    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getPrimaryDecimalSeparator()).isEqualTo(',');
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(',');
    assertThat(numberFormat.isDecimalSeparator(',')).isTrue();
    assertThat(numberFormat.isDecimalSeparator('.')).isFalse();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly('.');
    assertThat(numberFormat.isGroupingSeparator('.')).isTrue();
    assertThat(numberFormat.isGroupingSeparator(',')).isFalse();
    assertThat(numberFormat.getGroupingSize()).isEqualTo(4);
    assertThat(numberFormat.getMinimumIntegerDigits()).isEqualTo(1);
    assertThat(numberFormat.getMaximumIntegerDigits()).isEqualTo(10);
    assertThat(numberFormat.getMinimumFractionDigits()).isEqualTo(2);
    assertThat(numberFormat.getMaximumFractionDigits()).isEqualTo(5);
    assertThat(numberFormat.getNegativePrefix()).isEqualTo("(");
    assertThat(numberFormat.getNegativePrefixCodePointAt(-1)).isEqualTo(-1);
    assertThat(numberFormat.getNegativePrefixCodePointAt(0)).isEqualTo('(');
    assertThat(numberFormat.getNegativePrefixCodePointAt(1)).isEqualTo(-1);
    assertThat(numberFormat.getNegativeSuffix()).isEqualTo(")");
    assertThat(numberFormat.getPositivePrefix()).isEqualTo("+");
    assertThat(numberFormat.getPositivePrefixCodePointAt(-1)).isEqualTo(-1);
    assertThat(numberFormat.getPositivePrefixCodePointAt(0)).isEqualTo('+');
    assertThat(numberFormat.getPositivePrefixCodePointAt(1)).isEqualTo(-1);
    assertThat(numberFormat.getPositiveSuffix()).isEqualTo("!");
    assertThat(numberFormat.getRoundingMode()).isEqualTo(RoundingMode.DOWN);
    assertThat(numberFormat.getZeroDigit()).isEqualTo('0');
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      en-US | . |  ,     | 3 | HALF_EVEN
      fr-FR | , | \u202f | 3 | HALF_EVEN
      de-DE | , |  .     | 3 | HALF_EVEN
      ja-JP | . |  ,     | 3 | HALF_EVEN
      zh-CN | . |  ,     | 3 | HALF_EVEN
  """, delimiter = '|')
  void numberFormatBuilder_WithLocale_ShouldBuildNumberFormatWithLocaleSpecificValues(
      final String localeStr,
      final char expectedDecimalSeparator, final char expectedGroupingSeparator,
      final int expectedGroupingSize, final RoundingMode expectedRoundingMode) {

    final Locale locale = Locale.forLanguageTag(localeStr);
    final NumberFormat numberFormat = NumberFormat.builder(locale).build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getPrimaryDecimalSeparator()).isEqualTo(expectedDecimalSeparator);
    assertThat(numberFormat.getGroupingSeparators()).contains(expectedGroupingSeparator);
    assertThat(numberFormat.getGroupingSize()).isEqualTo(expectedGroupingSize);
    assertThat(numberFormat.getRoundingMode()).isEqualTo(expectedRoundingMode);
  }

  @Test
  void groupingSeparator_withSingleChar() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator((char)FULL_STOP)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(FULL_STOP);
  }

  @Test
  void groupingSeparator_withSingleCodePoint() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator(FULL_STOP)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(FULL_STOP);
  }

  @Test
  void groupingSeparator_withMultipleChars() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator((char)NARROW_NO_BREAK_SPACE, (char)NO_BREAK_SPACE, (char)SPACE, (char)THIN_SPACE)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE);
  }

  @Test
  void groupingSeparator_withMultipleCodePoints() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE);
  }

  @Test
  void decimalSeparator_withSingleChar() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator((char)FULL_STOP)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(FULL_STOP);
  }

  @Test
  void decimalSeparator_withSingleCodePoint() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator(FULL_STOP)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(FULL_STOP);
  }


  @Test
  void decimalSeparator_withMultipleChars() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator((char)COMMA, (char)ARABIC_DECIMAL_SEPARATOR)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(COMMA, ARABIC_DECIMAL_SEPARATOR);
  }

  @Test
  void decimalSeparator_withMultipleCodePoints() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator(COMMA, ARABIC_DECIMAL_SEPARATOR)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(COMMA, ARABIC_DECIMAL_SEPARATOR);
  }

  @Test
  void zeroDigit_withValidChar_ShouldSetExpectedValue() {
    // When
    NumberFormat numberFormat = NumberFormat.builder()
        .zeroDigit('0')
        .build();

    // Then
    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getZeroDigit()).isEqualTo('0');
  }

  @Test
  void zeroDigit_withValidCodePoint_ShouldSetExpectedValue() {
    // When
    NumberFormat numberFormat = NumberFormat.builder()
        .zeroDigit((int)'0')
        .build();

    // Then
    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getZeroDigit()).isEqualTo(48);
  }

  @Test
  void zeroDigit_withInvalidChar_ShouldThrowException() {
    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> NumberFormat.builder()
            .zeroDigit('A') // 'A' is not a valid zero digit
            .build())
        .withMessageContaining("An invalid zero digit has been configured for the numeric type parser – the zero digit along with the subsequent nine characters must be valid unicode digits.");
  }

  @Test
  void zeroDigit_withInvalidCodePoint_ShouldThrowException() {
    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> NumberFormat.builder()
            .zeroDigit((int)'A') // 'A' is not a valid zero digit
            .build())
        .withMessageContaining("An invalid zero digit has been configured for the numeric type parser – the zero digit along with the subsequent nine characters must be valid unicode digits.");
  }
}
