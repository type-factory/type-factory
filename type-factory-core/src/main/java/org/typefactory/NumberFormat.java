package org.typefactory;

import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;
import org.typefactory.impl.Factory;

public interface NumberFormat {

  static NumberFormat of(Locale locale) {
    return builder(locale).build();
  }

  static NumberFormatBuilder builder() {
    return Factory.numberFormatBuilder();
  }

  static NumberFormatBuilder builder(Locale locale) {
    return Factory.numberFormatBuilder(locale);
  }

  int getPrimaryDecimalSeparator();

  int[] getDecimalSeparators();

  boolean isDecimalSeparator(char c);

  boolean isDecimalSeparator(int c);

  int[] getGroupingSeparators();

  boolean isGroupingSeparator(char c);

  boolean isGroupingSeparator(int c);

  int getGroupingSize();

  int getMinimumIntegerDigits();

  int getMaximumIntegerDigits();

  int getMinimumFractionDigits();

  int getMaximumFractionDigits();

  String getNegativePrefix();

  int getNegativePrefixCodePointAt(final int index);

  int getNegativePrefixCodePointsLength();

  String getNegativeSuffix();

  String getPositivePrefix();

  int getPositivePrefixCodePointAt(final int index);

  int getPositivePrefixCodePointsLength();

  String getPositiveSuffix();

  RoundingMode getRoundingMode();

  int getZeroDigit();

  interface NumberFormatBuilder {

    NumberFormat build();

    NumberFormatBuilder decimalSeparator(char decimalSeparator, char... additionalDecimalSeparators);

    NumberFormatBuilder decimalSeparator(int decimalSeparator, int... additionalDecimalSeparators);

    NumberFormatBuilder groupingSeparator(char groupingSeparator, char... additionalGroupingSeparators);

    NumberFormatBuilder groupingSeparator(int groupingSeparator, int... additionalGroupingSeparators);

    NumberFormatBuilder groupingSize(int groupingSize);

    NumberFormatBuilder minimumIntegerDigits(int minimumIntegerDigits);

    NumberFormatBuilder maximumIntegerDigits(int maximumIntegerDigits);

    NumberFormatBuilder minimumFractionDigits(int minimumFractionDigits);

    NumberFormatBuilder maximumFractionDigits(int maximumFractionDigits);

    NumberFormatBuilder negativePrefix(String negativePrefix);

    NumberFormatBuilder negativeSuffix(String negativeSuffix);

    NumberFormatBuilder positivePrefix(String positivePrefix);

    NumberFormatBuilder positiveSuffix(String positiveSuffix);

    /**
     * <p>The rounding mode to use when discarding precision – for example when parsing decimals into integers.
     * The round mode will determine how to calculate the least significant digit of a number when more digits are available.</p>
     *
     * <p>If not set, the type-parser will retrieve a default round mode by calling
     * {@link java.text.NumberFormat#getInstance(Locale) NumberFormat.getInstance(Locale).getRoundingMode()}</p>
     *
     * <p>This will not override the rounding mode when parsing with a specific locale:</p>
     * <ul>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale)}</li>
     *   <li>{@link LongTypeParser#parse(CharSequence, Locale, Function)}</li>
     * </ul>
     *
     * @return this builder.
     * @see RoundingMode
     */
    NumberFormatBuilder roundingMode(RoundingMode roundingMode);

    /**
     * <p>This will set the grouping separator (also known as thousands separator) by calling {@link #groupingSeparator(char, char...)}.</p>
     *
     * @param thousandsSeparator            the grouping separator (also known as thousands separator)
     * @param additionalThousandsSeparators
     * @return
     */
    NumberFormatBuilder thousandsSeparator(char thousandsSeparator, char... additionalThousandsSeparators);

    NumberFormatBuilder thousandsSeparator(int thousandsSeparator, int... additionalThousandsSeparators);

    NumberFormatBuilder zeroDigit(char zeroDigit);

    NumberFormatBuilder zeroDigit(int zeroDigit);
  }
}
