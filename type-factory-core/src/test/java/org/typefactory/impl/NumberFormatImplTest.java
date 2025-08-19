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
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.typefactory.NumberFormat;
import org.typefactory.TypeParserBuilderException;

class NumberFormatImplTest {

  @Test
  void numberFormatBuilder_ShouldBuildNumberFormatWithDefaultValues() {
    final NumberFormat numberFormat = NumberFormat.builder().build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly('.');
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(',');
    assertThat(numberFormat.getGroupingSize()).isEqualTo(3);
    assertThat(numberFormat.getRoundingMode()).isEqualTo(RoundingMode.HALF_EVEN);
  }

  @Test
  void constructor_withValidParameters_ShouldInitializeFieldsCorrectly() {
    final int[] decimalSeparators = {'.'};
    final int[] groupingSeparators = {','};
    final int groupingSize = 3;
    final int minimumIntegerDigits = 1;
    final int maximumIntegerDigits = 10;
    final int minimumFractionDigits = 0;
    final int maximumFractionDigits = 2;
    final String negativePrefix = "-";
    final String negativeSuffix = "";
    final String positivePrefix = "+";
    final String positiveSuffix = "";
    final RoundingMode roundingMode = RoundingMode.HALF_UP;
    final int zeroDigit = '0';

    final NumberFormatImpl numberFormat = new NumberFormatImpl(
        decimalSeparators,
        groupingSeparators,
        groupingSize,
        minimumIntegerDigits,
        maximumIntegerDigits,
        minimumFractionDigits,
        maximumFractionDigits,
        negativePrefix,
        negativeSuffix,
        positivePrefix,
        positiveSuffix,
        roundingMode,
        zeroDigit
    );

    assertThat(numberFormat.getDecimalSeparators()).containsExactly(decimalSeparators);
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(groupingSeparators);
    assertThat(numberFormat.getGroupingSize()).isEqualTo(groupingSize);
    assertThat(numberFormat.getMinimumIntegerDigits()).isEqualTo(minimumIntegerDigits);
    assertThat(numberFormat.getMaximumIntegerDigits()).isEqualTo(maximumIntegerDigits);
    assertThat(numberFormat.getMinimumFractionDigits()).isEqualTo(minimumFractionDigits);
    assertThat(numberFormat.getMaximumFractionDigits()).isEqualTo(maximumFractionDigits);
    assertThat(numberFormat.getNegativePrefix()).isEqualTo(negativePrefix);
    assertThat(numberFormat.getNegativeSuffix()).isEqualTo(negativeSuffix);
    assertThat(numberFormat.getPositivePrefix()).isEqualTo(positivePrefix);
    assertThat(numberFormat.getPositiveSuffix()).isEqualTo(positiveSuffix);
    assertThat(numberFormat.getRoundingMode()).isEqualTo(roundingMode);
    assertThat(numberFormat.getZeroDigit()).isEqualTo(zeroDigit);
  }

  @Test
  void constructor_withValidParameters_ShouldInitializeFieldsCorrectlyWhenNullValuesSupplied() {
    final int[] decimalSeparators = null;
    final int[] groupingSeparators = null;
    final int groupingSize = 3;
    final int minimumIntegerDigits = 1;
    final int maximumIntegerDigits = 10;
    final int minimumFractionDigits = 0;
    final int maximumFractionDigits = 2;
    final String negativePrefix = null;
    final String negativeSuffix = null;
    final String positivePrefix = null;
    final String positiveSuffix = null;
    final RoundingMode roundingMode = null;
    final int zeroDigit = '0';

    final NumberFormatImpl numberFormat = new NumberFormatImpl(
        decimalSeparators,
        groupingSeparators,
        groupingSize,
        minimumIntegerDigits,
        maximumIntegerDigits,
        minimumFractionDigits,
        maximumFractionDigits,
        negativePrefix,
        negativeSuffix,
        positivePrefix,
        positiveSuffix,
        roundingMode,
        zeroDigit
    );

    assertThat(numberFormat.getDecimalSeparators()).isEmpty();
    assertThat(numberFormat.getGroupingSeparators()).isEmpty();
    assertThat(numberFormat.getGroupingSize()).isEqualTo(groupingSize);
    assertThat(numberFormat.getMinimumIntegerDigits()).isEqualTo(minimumIntegerDigits);
    assertThat(numberFormat.getMaximumIntegerDigits()).isEqualTo(maximumIntegerDigits);
    assertThat(numberFormat.getMinimumFractionDigits()).isEqualTo(minimumFractionDigits);
    assertThat(numberFormat.getMaximumFractionDigits()).isEqualTo(maximumFractionDigits);
    assertThat(numberFormat.getNegativePrefix()).isEqualTo("-");
    assertThat(numberFormat.getNegativeSuffix()).isEqualTo(negativeSuffix);
    assertThat(numberFormat.getPositivePrefix()).isEqualTo("+");
    assertThat(numberFormat.getPositiveSuffix()).isEqualTo(positiveSuffix);
    assertThat(numberFormat.getRoundingMode()).isEqualTo(roundingMode);
    assertThat(numberFormat.getZeroDigit()).isEqualTo(zeroDigit);
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
    assertThat(numberFormat.getDecimalSeparators()).contains(expectedDecimalSeparator);
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

  @ParameterizedTest
  @NullAndEmptySource
  void groupingSeparator_withNullOrEmptyAdditionalGroupingSeparatorCharacters(final char [] additionalGroupingSeparators) {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator((char)NARROW_NO_BREAK_SPACE, additionalGroupingSeparators)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(NARROW_NO_BREAK_SPACE);
  }


  @Test
  void groupingSeparator_withMultipleCodePoints() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void groupingSeparator_withNullOrEmptyAdditionalGroupingSeparatorCodePoints(final int [] additionalGroupingSeparators) {

    final NumberFormat numberFormat = NumberFormat.builder()
        .groupingSeparator(NARROW_NO_BREAK_SPACE, additionalGroupingSeparators)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getGroupingSeparators()).containsExactly(NARROW_NO_BREAK_SPACE);
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

  @ParameterizedTest
  @NullAndEmptySource
  void decimalSeparator_withNullOrEmptyAdditionalDecimalSeparatorCharacters(final char [] additionalDecimalSeparators) {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator((char)COMMA, additionalDecimalSeparators)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(COMMA);
  }

  @Test
  void decimalSeparator_withMultipleCodePoints() {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator(COMMA, ARABIC_DECIMAL_SEPARATOR)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(COMMA, ARABIC_DECIMAL_SEPARATOR);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void decimalSeparator_withNullOrEmptyAdditionalDecimalSeparatorCodePoints(final int [] additionalDecimalSeparators) {

    final NumberFormat numberFormat = NumberFormat.builder()
        .decimalSeparator(COMMA, additionalDecimalSeparators)
        .build();

    assertThat(numberFormat).isNotNull();
    assertThat(numberFormat.getDecimalSeparators()).containsExactly(COMMA);
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

    final var builder = NumberFormat.builder();

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> builder.zeroDigit('A')) // 'A' is not a valid zero digit
        .withMessageContaining("An invalid zero digit has been configured for the type parser – the zero digit character must represent zero and the subsequent nine characters must be valid unicode decimal digits.");
  }

  @Test
  void zeroDigit_withInvalidCodePoint_ShouldThrowException() {

    final var builder = NumberFormat.builder();

    assertThatExceptionOfType(TypeParserBuilderException.class)
        .isThrownBy(() -> builder.zeroDigit((int)'A')) // 'A' is not a valid zero digit
        .withMessageContaining("An invalid zero digit has been configured for the type parser – the zero digit character must represent zero and the subsequent nine characters must be valid unicode decimal digits.");
  }
}
