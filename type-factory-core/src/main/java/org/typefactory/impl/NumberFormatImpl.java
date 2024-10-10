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

import java.math.RoundingMode;
import java.util.Arrays;
import org.typefactory.NumberFormat;

final class NumberFormatImpl implements NumberFormat {

  private final int[] decimalSeparators;
  private final int[] groupingSeparators;
  private final int groupingSize;
  private final int minimumIntegerDigits;
  private final int maximumIntegerDigits;
  private final int minimumFractionDigits;
  private final int maximumFractionDigits;
  private final String negativePrefix;
  private final int [] negativePrefixCodePoints;
  private final String negativeSuffix;
  private final String positivePrefix;
  private final int [] positivePrefixCodePoints;
  private final String positiveSuffix;
  private final RoundingMode roundingMode;
  private final int zeroDigit;

  NumberFormatImpl(
      final int[] decimalSeparators,
      final int[] groupingSeparators,
      final int groupingSize,
      final int minimumIntegerDigits,
      final int maximumIntegerDigits,
      final int minimumFractionDigits,
      final int maximumFractionDigits,
      final String negativePrefix,
      final String negativeSuffix,
      final String positivePrefix,
      final String positiveSuffix,
      final RoundingMode roundingMode,
      final int zeroDigit) {
    this.decimalSeparators = decimalSeparators == null ? Constants.EMPTY_INT_ARRAY : decimalSeparators;
    this.groupingSeparators = groupingSeparators == null ? Constants.EMPTY_INT_ARRAY : groupingSeparators;
    this.groupingSize = groupingSize;
    this.minimumIntegerDigits = minimumIntegerDigits;
    this.maximumIntegerDigits = maximumIntegerDigits;
    this.minimumFractionDigits = minimumFractionDigits;
    this.maximumFractionDigits = maximumFractionDigits;
    this.negativePrefix = negativePrefix == null || negativePrefix.isEmpty() ? "-" : negativePrefix;
    this.negativePrefixCodePoints = getCodePointsWithoutDirectionalMarks(this.negativePrefix);
    this.negativeSuffix = negativeSuffix;
    this.positivePrefix = positivePrefix == null || positivePrefix.isEmpty() ? "+" : positivePrefix;
    this.positivePrefixCodePoints = getCodePointsWithoutDirectionalMarks(this.positivePrefix);
    this.positiveSuffix = positiveSuffix;
    this.roundingMode = roundingMode;
    this.zeroDigit = zeroDigit;
  }

  private int [] getCodePointsWithoutDirectionalMarks(final String value) {
    return value.replaceAll("[\u061c\u200e\u200f]", "").codePoints().toArray();
  }

  @Override
  public int getPrimaryDecimalSeparator() {
    return decimalSeparators[0];
  }

  @Override
  public int[] getDecimalSeparators() {
    return Arrays.copyOf(decimalSeparators, decimalSeparators.length);
  }

  @Override
  public boolean isDecimalSeparator(char c) {
    return isDecimalSeparator((int) c);
  }

  @Override
  public boolean isDecimalSeparator(int c) {
    for (int decimalSeparator : decimalSeparators) {
      if (c == decimalSeparator) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int[] getGroupingSeparators() {
    return Arrays.copyOf(groupingSeparators, groupingSeparators.length);
  }

  @Override
  public boolean isGroupingSeparator(char c) {
    return isGroupingSeparator((int) c);
  }

  @Override
  public boolean isGroupingSeparator(int c) {
    for (int groupingSeparator : groupingSeparators) {
      if (c == groupingSeparator) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int getGroupingSize() {
    return groupingSize;
  }

  @Override
  public int getMinimumIntegerDigits() {
    return minimumIntegerDigits;
  }

  @Override
  public int getMaximumIntegerDigits() {
    return maximumIntegerDigits;
  }

  @Override
  public int getMinimumFractionDigits() {
    return minimumFractionDigits;
  }

  @Override
  public int getMaximumFractionDigits() {
    return maximumFractionDigits;
  }

  @Override
  public String getNegativePrefix() {
    return negativePrefix;
  }

  @Override
  public int getNegativePrefixCodePointAt(final int index) {
    if (index < 0 || index >= negativePrefixCodePoints.length) {
      return -1;
    }
    return negativePrefixCodePoints[index];
  }

  @Override
  public int getNegativePrefixCodePointsLength() {
    return negativePrefixCodePoints.length;
  }

  @Override
  public String getNegativeSuffix() {
    return negativeSuffix;
  }

  @Override
  public String getPositivePrefix() {
    return positivePrefix;
  }

  @Override
  public int getPositivePrefixCodePointAt(final int index) {
    if (index < 0 || index >= positivePrefixCodePoints.length) {
      return -1;
    }
    return positivePrefixCodePoints[index];
  }

  @Override
  public int getPositivePrefixCodePointsLength() {
    return positivePrefixCodePoints.length;
  }

  @Override
  public String getPositiveSuffix() {
    return positiveSuffix;
  }

  @Override
  public RoundingMode getRoundingMode() {
    return roundingMode;
  }

  @Override
  public int getZeroDigit() {
    return zeroDigit;
  }
}
