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
import static org.typefactory.impl.Constants.APOSTROPHE_SINGLE_QUOTATION_MARK;
import static org.typefactory.impl.Constants.ARABIC_COMMA;
import static org.typefactory.impl.Constants.ARABIC_DECIMAL_SEPARATOR;
import static org.typefactory.impl.Constants.ARABIC_THOUSANDS_SEPARATOR;
import static org.typefactory.impl.Constants.COMMA;
import static org.typefactory.impl.Constants.FULL_STOP;
import static org.typefactory.impl.Constants.NARROW_NO_BREAK_SPACE;
import static org.typefactory.impl.Constants.NO_BREAK_SPACE;
import static org.typefactory.impl.Constants.RIGHT_SINGLE_QUOTATION_MARK;
import static org.typefactory.impl.Constants.SPACE;
import static org.typefactory.impl.Constants.THIN_SPACE;

import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.NumberFormat;

class NumberFormatBuilderImplTest {

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForComma() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(COMMA);
    assertThat(actualSeparators).containsExactly(COMMA);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForFullStop() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(FULL_STOP);
    assertThat(actualSeparators).containsExactly(FULL_STOP);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForSpace() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(SPACE);
    assertThat(actualSeparators).containsExactly(SPACE, NO_BREAK_SPACE, NARROW_NO_BREAK_SPACE, THIN_SPACE);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForNoBreakSpace() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(NO_BREAK_SPACE);
    assertThat(actualSeparators).containsExactly(NO_BREAK_SPACE, NARROW_NO_BREAK_SPACE, SPACE, THIN_SPACE);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForArabicThousandsSeparator() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(ARABIC_THOUSANDS_SEPARATOR);
    assertThat(actualSeparators).containsExactly(ARABIC_THOUSANDS_SEPARATOR, APOSTROPHE_SINGLE_QUOTATION_MARK, ARABIC_COMMA);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForApostropheSingleQuotationMark() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(APOSTROPHE_SINGLE_QUOTATION_MARK);
    assertThat(actualSeparators).containsExactly(APOSTROPHE_SINGLE_QUOTATION_MARK, RIGHT_SINGLE_QUOTATION_MARK);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForRightSingleQuotationMark() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(RIGHT_SINGLE_QUOTATION_MARK);
    assertThat(actualSeparators).containsExactly(RIGHT_SINGLE_QUOTATION_MARK, APOSTROPHE_SINGLE_QUOTATION_MARK);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForThinSpace() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(THIN_SPACE);
    assertThat(actualSeparators).containsExactly(THIN_SPACE, NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE);
  }

  @Test
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForNarrowNoBreakSpace() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(NARROW_NO_BREAK_SPACE);
    assertThat(actualSeparators).containsExactly(NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE, THIN_SPACE);
  }

  @ParameterizedTest
  @ValueSource(ints = {'~', '^'})
  void determineGroupingSeparatorsAndAlternatives_ShouldReturnAsExpectedForOtherValues(final int otherCodePoint) {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineGroupingSeparatorsAndAlternatives(otherCodePoint);
    assertThat(actualSeparators).containsExactly(otherCodePoint);
  }

  @Test
  void determineDecimalSeparatorsAndAlternatives_ShouldReturnAsExpectedForComma() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineDecimalSeparatorsAndAlternatives(COMMA);
    assertThat(actualSeparators).containsExactly(COMMA, ARABIC_DECIMAL_SEPARATOR);
  }

  @Test
  void determineDecimalSeparatorsAndAlternatives_ShouldReturnAsExpectedForFullStop() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineDecimalSeparatorsAndAlternatives(FULL_STOP);
    assertThat(actualSeparators).containsExactly(FULL_STOP);
  }

  @Test
  void determineDecimalSeparatorsAndAlternatives_ShouldReturnAsExpectedForArabicDecimalSeparator() {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineDecimalSeparatorsAndAlternatives(ARABIC_DECIMAL_SEPARATOR);
    assertThat(actualSeparators).containsExactly(ARABIC_DECIMAL_SEPARATOR, COMMA);
  }
  @ParameterizedTest
  @ValueSource(ints = {'~', '^'})
  void determineDecimalSeparatorsAndAlternatives_ShouldReturnAsExpectedForOtherValues(final int otherCodePoint) {
    final int [] actualSeparators = NumberFormatBuilderImpl.determineDecimalSeparatorsAndAlternatives(otherCodePoint);
    assertThat(actualSeparators).containsExactly(otherCodePoint);
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

}
