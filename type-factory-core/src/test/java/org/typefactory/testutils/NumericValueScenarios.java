package org.typefactory.testutils;

import static java.util.function.Predicate.not;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class NumericValueScenarios {

  private NumericValueScenarios() {
    // do not instantiate
  }

  private static final List<BigInteger> INTEGRAL_VALUES = List.of(
      BigInteger.valueOf(Long.MIN_VALUE),
      BigInteger.valueOf(Integer.MIN_VALUE),
      BigInteger.valueOf(Short.MIN_VALUE),
      BigInteger.TEN.negate(),
      BigInteger.valueOf(-5L),
      BigInteger.ZERO,
      BigInteger.valueOf(5L),
      BigInteger.TEN,
      BigInteger.valueOf(Short.MAX_VALUE),
      BigInteger.valueOf(Integer.MAX_VALUE),
      BigInteger.valueOf(Long.MAX_VALUE));

  private static final List<BigInteger> INTEGRAL_DELTAS = List.of(
      BigInteger.ONE.negate(),
      BigInteger.ZERO,
      BigInteger.ONE);

  private static final List<BigInteger> BIG_INTEGER_VALUES_FOR_SHORT =
      INTEGRAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidShort)
          .flatMap(value -> INTEGRAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<BigInteger> BIG_INTEGER_VALUES_FOR_INTEGER =
      INTEGRAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidInteger)
          .flatMap(value -> INTEGRAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<BigInteger> BIG_INTEGER_VALUES_FOR_LONG =
      INTEGRAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidLong)
          .flatMap(value -> INTEGRAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<BigDecimal> DECIMAL_VALUES = List.of(
      BigDecimal.valueOf(Double.MAX_VALUE - 2D).negate(),
      BigDecimal.valueOf(Float.MAX_VALUE - 2F).negate(),
      BigDecimal.valueOf(Long.MIN_VALUE),
      BigDecimal.valueOf(Integer.MIN_VALUE),
      BigDecimal.valueOf(Short.MIN_VALUE),
      BigDecimal.TEN.negate(),
      BigDecimal.ZERO,
      BigDecimal.TEN,
      BigDecimal.valueOf(Short.MAX_VALUE),
      BigDecimal.valueOf(Integer.MAX_VALUE),
      BigDecimal.valueOf(Long.MAX_VALUE),
      BigDecimal.valueOf(Float.MAX_VALUE - 2F),
      BigDecimal.valueOf(Double.MAX_VALUE - 2D)
  );

  private static final List<BigDecimal> DECIMAL_DELTAS = List.of(
      new BigDecimal("-2.0"),
      new BigDecimal("-1.5"),
      new BigDecimal("-1.0"),
      new BigDecimal("-0.99"),
      new BigDecimal("-0.51"),
      new BigDecimal("-0.5"),
      new BigDecimal("-0.49"),
      new BigDecimal("-0.01"),
      new BigDecimal("0.0"),
      new BigDecimal("0.01"),
      new BigDecimal("0.49"),
      new BigDecimal("0.5"),
      new BigDecimal("0.51"),
      new BigDecimal("0.99"),
      new BigDecimal("1.0"),
      new BigDecimal("1.5"),
      new BigDecimal("2.0"));

  private static final List<BigDecimal> BIG_DECIMAL_VALUES_FOR_SHORT =
      DECIMAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidShort)
          .flatMap(value -> DECIMAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<BigDecimal> BIG_DECIMAL_VALUES_FOR_INTEGER =
      DECIMAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidInteger)
          .flatMap(value -> DECIMAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<BigDecimal> BIG_DECIMAL_VALUES_FOR_LONG =
      DECIMAL_VALUES.stream()
          .filter(NumericValueScenarios::isInclusivelyValidLong)
          .flatMap(value -> DECIMAL_DELTAS.stream().map(value::add))
          .toList();

  private static final List<RoundingMode> ROUNDING_MODES = Arrays.stream(RoundingMode.values())
      .filter(roundingMode -> roundingMode != RoundingMode.UNNECESSARY)
      .toList();

  private static final Comparator<DecimalFormatSymbols> DECIMAL_FORMAT_SYMBOLS_COMPARATOR = Comparator
      .comparing(DecimalFormatSymbols::getGroupingSeparator)
      .thenComparing(DecimalFormatSymbols::getDecimalSeparator)
      .thenComparing(DecimalFormatSymbols::getZeroDigit)
      .thenComparing(DecimalFormatSymbols::getMinusSign)
      .thenComparing(DecimalFormatSymbols::getPercent)
      .thenComparing(DecimalFormatSymbols::getPerMill);

  private static final Comparator<DecimalFormat> DECIMAL_FORMAT_COMPARATOR = Comparator
      .comparing(DecimalFormat::getNegativePrefix)
      .thenComparing(DecimalFormat::getNegativeSuffix)
      .thenComparing(DecimalFormat::getPositivePrefix)
      .thenComparing(DecimalFormat::getPositiveSuffix)
      .thenComparing(DecimalFormat::getGroupingSize)
      .thenComparing(DecimalFormat::getMaximumFractionDigits)
      .thenComparing(DecimalFormat::getMinimumFractionDigits)
      .thenComparing(DecimalFormat::getMaximumIntegerDigits)
      .thenComparing(DecimalFormat::getMinimumIntegerDigits);

  private static final Comparator<Locale> LOCALE_BY_UNIQUE_NUMBER_FORMAT_COMPARATOR = (locale1, locale2) -> {
    final var format1 = (DecimalFormat) NumberFormat.getNumberInstance(locale1);
    final var format2 = (DecimalFormat) NumberFormat.getNumberInstance(locale2);
    int result = DECIMAL_FORMAT_COMPARATOR.compare(format1, format2);
    if (result != 0) {
      return result;
    }
    final var formatSymbols1 = format1.getDecimalFormatSymbols();
    final var formatSymbols2 = format2.getDecimalFormatSymbols();
    result = DECIMAL_FORMAT_SYMBOLS_COMPARATOR.compare(formatSymbols1, formatSymbols2);
    return result;
  };

  private static final List<Locale> MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS =
      Arrays.stream(NumberFormat.getAvailableLocales())
          .filter(not(locale -> locale.getCountry().isEmpty()))
          .filter(locale -> NumberFormat.getNumberInstance(locale) instanceof DecimalFormat)
          .collect(Collectors.toCollection(() -> new TreeSet<>(LOCALE_BY_UNIQUE_NUMBER_FORMAT_COMPARATOR)))
          .stream()
          .sorted(Comparator.comparing(Locale::toString))
          .toList();

  private static BigInteger getRoundedValue(final Float value, final RoundingMode roundingMode) {
    return BigDecimal.valueOf(value).setScale(0, roundingMode).toBigIntegerExact();
  }

  private static BigInteger getRoundedValue(final Double value, final RoundingMode roundingMode) {
    return BigDecimal.valueOf(value).setScale(0, roundingMode).toBigIntegerExact();
  }

  private static BigInteger getRoundedValue(final BigDecimal value, final RoundingMode roundingMode) {
    return value.setScale(0, roundingMode).toBigIntegerExact();
  }

  private static NumberFormat getNumberFormatForIntegers(final Locale locale) {
    final var numberFormat = NumberFormat.getNumberInstance(locale);
    numberFormat.setMaximumFractionDigits(0);
    numberFormat.setMaximumIntegerDigits(500);
    return numberFormat;
  }

  private static NumberFormat getNumberFormatForDecimals(final Locale locale) {
    final var numberFormat = NumberFormat.getNumberInstance(locale);
    numberFormat.setMinimumFractionDigits(13);
    numberFormat.setMaximumIntegerDigits(500);
    return numberFormat;
  }

  private static String getFormattedValueUsingJavaToString(final BigInteger value) {
    return value.toString();
  }

  private static String getFormattedValueUsingJavaToString(final BigDecimal value) {
    return value.toPlainString();
  }

  private static String getFormattedValueUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return getRoundedValue(value, roundingMode).toString();
  }

  private static String getFormattedValueUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return getNumberFormatForIntegers(locale).format(value);
  }

  private static String getFormattedValueUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return getNumberFormatForDecimals(locale).format(value);
  }

  private static String getFormattedValueUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return String.format(locale, "%d", value);
  }

  private static String getFormattedValueUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return String.format(locale, "%1.13f", value);
  }

  private static boolean isInclusivelyTooSmallForShort(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Short.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForShort(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Short.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForShort(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Short.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForShort(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Short.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForInteger(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForInteger(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForInteger(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForInteger(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForLong(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForLong(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForLong(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) < 0;
  }

  private static boolean isExclusivelyTooSmallForLong(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) <= 0;
  }

  private static boolean isInclusivelyTooSmallForFloat(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Float.MAX_VALUE).negate()) < 0;
  }

  private static boolean isExclusivelyTooSmallForFloat(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Float.MAX_VALUE).negate()) <= 0;
  }

  private static boolean isInclusivelyTooSmallForDouble(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE).negate()) < 0;
  }

  private static boolean isExclusivelyTooSmallForDouble(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE).negate()) <= 0;
  }

  private static boolean isInclusivelyTooLargeForShort(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Short.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForShort(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Short.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForShort(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Short.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForShort(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Short.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForInteger(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForInteger(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForInteger(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForInteger(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForLong(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForLong(final BigInteger value) {
    return value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForLong(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForLong(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForFloat(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Float.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForFloat(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Float.MAX_VALUE)) >= 0;
  }

  private static boolean isInclusivelyTooLargeForDouble(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0;
  }

  private static boolean isExclusivelyTooLargeForDouble(final BigDecimal value) {
    return value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0;
  }

  private static boolean isInclusivelyValidShort(final BigInteger value) {
    return !isInclusivelyTooSmallForShort(value) && !isInclusivelyTooLargeForShort(value);
  }

  private static boolean isExclusivelyValidShort(final BigInteger value) {
    return !isExclusivelyTooSmallForShort(value) && !isExclusivelyTooLargeForShort(value);
  }

  private static boolean isInclusivelyValidShort(final BigDecimal value) {
    return !isInclusivelyTooSmallForShort(value) && !isInclusivelyTooLargeForShort(value);
  }

  private static boolean isExclusivelyValidShort(final BigDecimal value) {
    return !isExclusivelyTooSmallForShort(value) && !isExclusivelyTooLargeForShort(value);
  }

  private static boolean isInclusivelyValidInteger(final BigInteger value) {
    return !isInclusivelyTooSmallForInteger(value) && !isInclusivelyTooLargeForInteger(value);
  }

  private static boolean isExclusivelyValidInteger(final BigInteger value) {
    return !isExclusivelyTooSmallForInteger(value) && !isExclusivelyTooLargeForInteger(value);
  }

  private static boolean isInclusivelyValidInteger(final BigDecimal value) {
    return !isInclusivelyTooSmallForInteger(value) && !isInclusivelyTooLargeForInteger(value);
  }

  private static boolean isExclusivelyValidInteger(final BigDecimal value) {
    return !isExclusivelyTooSmallForInteger(value) && !isExclusivelyTooLargeForInteger(value);
  }

  private static boolean isInclusivelyValidLong(final BigInteger value) {
    return !isInclusivelyTooSmallForLong(value) && !isInclusivelyTooLargeForLong(value);
  }

  private static boolean isExclusivelyValidLong(final BigInteger value) {
    return !isExclusivelyTooSmallForLong(value) && !isExclusivelyTooLargeForLong(value);
  }

  private static boolean isInclusivelyValidLong(final BigDecimal value) {
    return !isInclusivelyTooSmallForLong(value) && !isInclusivelyTooLargeForLong(value);
  }

  private static boolean isExclusivelyValidLong(final BigDecimal value) {
    return !isExclusivelyTooSmallForLong(value) && !isExclusivelyTooLargeForLong(value);
  }

  private static boolean isInclusivelyValidFloat(final BigDecimal value) {
    return !isInclusivelyTooSmallForFloat(value) && !isInclusivelyTooLargeForFloat(value);
  }

  private static boolean isExclusivelyValidFloat(final BigDecimal value) {
    return !isExclusivelyTooSmallForFloat(value) && !isExclusivelyTooLargeForFloat(value);
  }

  private static boolean isInclusivelyValidDouble(final BigDecimal value) {
    return !isInclusivelyTooSmallForDouble(value) && !isInclusivelyTooLargeForDouble(value);
  }

  private static boolean isExclusivelyValidDouble(final BigDecimal value) {
    return !isExclusivelyTooSmallForDouble(value) && !isExclusivelyTooLargeForDouble(value);
  }

  private static Short getExpectedInclusiveShortValue(final BigInteger value) {
    return isInclusivelyValidShort(value) ? value.shortValue() : null;
  }

  private static Short getExpectedExclusiveShortValue(final BigInteger value) {
    return isExclusivelyValidShort(value) ? value.shortValue() : null;
  }

  private static Short getExpectedInclusiveShortValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidShort(roundedValue) ? roundedValue.shortValue() : null;
  }

  private static Short getExpectedExclusiveShortValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidShort(roundedValue) ? roundedValue.shortValue() : null;
  }

  private static Integer getExpectedInclusiveIntegerValue(final BigInteger value) {
    return isInclusivelyValidInteger(value) ? value.intValue() : null;
  }

  private static Integer getExpectedExclusiveIntegerValue(final BigInteger value) {
    return isExclusivelyValidInteger(value) ? value.intValue() : null;
  }

  private static Integer getExpectedInclusiveIntegerValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidInteger(roundedValue) ? roundedValue.intValue() : null;
  }

  private static Integer getExpectedExclusiveIntegerValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidInteger(roundedValue) ? roundedValue.intValue() : null;
  }

  private static Long getExpectedInclusiveLongValue(final BigInteger value) {
    return isInclusivelyValidLong(value) ? value.longValue() : null;
  }

  private static Long getExpectedExclusiveLongValue(final BigInteger value) {
    return isExclusivelyValidLong(value) ? value.longValue() : null;
  }

  private static Long getExpectedInclusiveLongValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidLong(roundedValue) ? roundedValue.longValue() : null;
  }

  private static Long getExpectedExclusiveLongValue(final BigDecimal value, final RoundingMode roundingMode) {
    final BigInteger roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidLong(roundedValue) ? roundedValue.longValue() : null;
  }

  private static Float getExpectedInclusiveFloatValue(final BigDecimal value) {
    return isInclusivelyValidFloat(value) ? value.floatValue() : null;
  }

  private static Float getExpectedExclusiveFloatValue(final BigDecimal value) {
    return isExclusivelyValidFloat(value) ? value.floatValue() : null;
  }

  private static Double getExpectedInclusiveDoubleValue(final BigDecimal value) {
    return isInclusivelyValidDouble(value) ? value.doubleValue() : null;
  }

  private static Double getExpectedExclusiveDoubleValue(final BigDecimal value) {
    return isExclusivelyValidDouble(value) ? value.doubleValue() : null;
  }

  private static String getExpectedExceptionMessageForInclusiveShort(final BigInteger value) {
    return isInclusivelyTooSmallForShort(value) ? "Invalid value - must be greater than or equal to -32,768." :
        isInclusivelyTooLargeForShort(value) ? "Invalid value - must be less than or equal to 32,767." : null;
  }

  private static String getExpectedExceptionMessageForExclusiveShort(final BigInteger value) {
    return isExclusivelyTooSmallForShort(value) ? "Invalid value - must be greater than -32,768." :
        isExclusivelyTooLargeForShort(value) ? "Invalid value - must be less than 32,767." : null;
  }

  private static String getExpectedExceptionMessageForInclusiveShort(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyTooSmallForShort(roundedValue)
        ? "Invalid value - must be greater than or equal to -32,768 using rounding mode " + roundingMode + "."
        : isInclusivelyTooLargeForShort(roundedValue)
            ? "Invalid value - must be less than or equal to 32,767 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForExclusiveShort(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyTooSmallForShort(roundedValue)
        ? "Invalid value - must be greater than -32,768 using rounding mode " + roundingMode + "."
        : isExclusivelyTooLargeForShort(roundedValue)
            ? "Invalid value - must be less than 32,767 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForInclusiveInteger(final BigInteger value) {
    return isInclusivelyTooSmallForInteger(value) ? "Invalid value - must be greater than or equal to -2,147,483,648." :
        isInclusivelyTooLargeForInteger(value) ? "Invalid value - must be less than or equal to 2,147,483,647." : null;
  }

  private static String getExpectedExceptionMessageForExclusiveInteger(final BigInteger value) {
    return isExclusivelyTooSmallForInteger(value) ? "Invalid value - must be greater than -2,147,483,648." :
        isExclusivelyTooLargeForInteger(value) ? "Invalid value - must be less than 2,147,483,647." : null;
  }

  private static String getExpectedExceptionMessageForInclusiveInteger(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyTooSmallForInteger(roundedValue)
        ? "Invalid value - must be greater than or equal to -2,147,483,648 using rounding mode " + roundingMode + "."
        : isInclusivelyTooLargeForInteger(roundedValue)
            ? "Invalid value - must be less than or equal to 2,147,483,647 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForExclusiveInteger(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyTooSmallForInteger(roundedValue)
        ? "Invalid value - must be greater than -2,147,483,648 using rounding mode " + roundingMode + "."
        : isExclusivelyTooLargeForInteger(roundedValue)
            ? "Invalid value - must be less than 2,147,483,647 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForInclusiveLong(final BigInteger value) {
    return isInclusivelyTooSmallForLong(value) ? "Invalid value - must be greater than or equal to -9,223,372,036,854,775,808." :
        isInclusivelyTooLargeForLong(value) ? "Invalid value - must be less than or equal to 9,223,372,036,854,775,807." : null;
  }

  private static String getExpectedExceptionMessageForExclusiveLong(final BigInteger value) {
    return isExclusivelyTooSmallForLong(value) ? "Invalid value - must be greater than -9,223,372,036,854,775,808." :
        isExclusivelyTooLargeForLong(value) ? "Invalid value - must be less than 9,223,372,036,854,775,807." : null;
  }

  private static String getExpectedExceptionMessageForInclusiveLong(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyTooSmallForLong(roundedValue)
        ? "Invalid value - must be greater than or equal to -9,223,372,036,854,775,808 using rounding mode " + roundingMode + "."
        : isInclusivelyTooLargeForLong(roundedValue)
            ? "Invalid value - must be less than or equal to 9,223,372,036,854,775,807 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForExclusiveLong(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyTooSmallForLong(roundedValue)
        ? "Invalid value - must be greater than -9,223,372,036,854,775,808 using rounding mode " + roundingMode + "."
        : isExclusivelyTooLargeForLong(roundedValue)
            ? "Invalid value - must be less than 9,223,372,036,854,775,807 using rounding mode " + roundingMode + "."
            : null;
  }

  private static String getExpectedExceptionMessageForInclusiveFloat(final BigDecimal value) {
    return isInclusivelyTooSmallForFloat(value) ? "Invalid value - must be greater than or equal to -3.4028235E38." :
        isInclusivelyTooLargeForFloat(value) ? "Invalid value - must be less than or equal to 3.4028235E38." : null;
  }

  private static String getExpectedExceptionMessageForExclusiveFloat(final BigDecimal value) {
    return isExclusivelyTooSmallForFloat(value) ? "Invalid value - must be greater than -3.4028235E38." :
        isExclusivelyTooLargeForFloat(value) ? "Invalid value - must be less than 3.4028235E38." : null;
  }

  private static String getExpectedExceptionMessageForInclusiveDouble(final BigDecimal value) {
    return isInclusivelyTooSmallForDouble(value) ? "Invalid value - must be greater than or equal to -1.7976931348623157E308." :
        isInclusivelyTooLargeForDouble(value) ? "Invalid value - must be less than or equal to 1.7976931348623157E308." : null;
  }

  private static String getExpectedExceptionMessageForExclusiveDouble(final BigDecimal value) {
    return isExclusivelyTooSmallForDouble(value) ? "Invalid value - must be greater than -1.7976931348623157E308." :
        isExclusivelyTooLargeForDouble(value) ? "Invalid value - must be less than 1.7976931348623157E308." : null;
  }

  private static String getExpectedInvalidValueForInclusiveShort(final BigInteger value) {
    return isInclusivelyValidShort(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveShort(final BigInteger value) {
    return isExclusivelyValidShort(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveShort(final Float value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveShort(final Float value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveShort(final Double value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveShort(final Double value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveShort(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveShort(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidShort(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveInteger(final BigInteger value) {
    return isInclusivelyValidInteger(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveInteger(final BigInteger value) {
    return isExclusivelyValidInteger(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveInteger(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidInteger(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveInteger(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidInteger(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveLong(final BigInteger value) {
    return isInclusivelyValidLong(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveLong(final BigInteger value) {
    return isExclusivelyValidLong(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveLong(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isInclusivelyValidLong(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveLong(final BigDecimal value, final RoundingMode roundingMode) {
    final var roundedValue = getRoundedValue(value, roundingMode);
    return isExclusivelyValidLong(roundedValue) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveFloat(final BigDecimal value) {
    return isInclusivelyValidFloat(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveFloat(final BigDecimal value) {
    return isExclusivelyValidFloat(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveDouble(final BigDecimal value) {
    return isInclusivelyValidDouble(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForExclusiveDouble(final BigDecimal value) {
    return isExclusivelyValidDouble(value) ? null : value.toString();
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isInclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isExclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isInclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isExclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isInclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaToString(final BigDecimal value, final RoundingMode roundingMode) {
    return isExclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaToString(value);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidShort(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidShort(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidShort(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidShort(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidLong(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaNumberFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidLong(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidLong(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidLong(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveFloatUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidFloat(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveFloatUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidFloat(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveDoubleUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidDouble(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveDoubleUsingJavaNumberFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidDouble(value) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidShort(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidShort(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidShort(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidShort(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidInteger(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isInclusivelyValidLong(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaStringFormat(final BigInteger value, final Locale locale) {
    return isExclusivelyValidLong(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidLong(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidLong(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveFloatUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isInclusivelyValidFloat(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveFloatUsingJavaStringFormat(final BigDecimal value, final Locale locale) {
    return isExclusivelyValidFloat(value) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveShortUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveShortUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidShort(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidInteger(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidInteger(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveIntegerUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidInteger(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveIntegerUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidInteger(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidLong(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaNumberFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidLong(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaNumberFormat(value, locale);
  }

  private static String getExpectedInvalidValueForInclusiveLongUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isInclusivelyValidLong(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }

  private static String getExpectedInvalidValueForExclusiveLongUsingJavaStringFormat(
      final BigDecimal value, final RoundingMode roundingMode, final Locale locale) {
    return isExclusivelyValidLong(getRoundedValue(value, roundingMode)) ? null : getFormattedValueUsingJavaStringFormat(value, locale);
  }


  public static Stream<Arguments> provideIntegralStringValuesToTestInclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedInclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForInclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegralStringValuesToTestExclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedExclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForExclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestInclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveShortUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestExclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveShortUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestInclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_SHORT.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaNumberFormat(bigIntegerValue, locale),
                getExpectedInclusiveShortValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
                getExpectedInvalidValueForInclusiveShortUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestExclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_SHORT.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaNumberFormat(bigIntegerValue, locale),
                getExpectedExclusiveShortValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
                getExpectedInvalidValueForExclusiveShortUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestInclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_SHORT.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedInclusiveShortValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
                getExpectedInvalidValueForInclusiveShortUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestExclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_SHORT.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedExclusiveShortValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
                getExpectedInvalidValueForExclusiveShortUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestInclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_SHORT.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveShortUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestInclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_SHORT.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveShortUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestExclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_SHORT.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveShortUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestExclusiveShortParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_SHORT.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveShortUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestInclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedInclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForInclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestExclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedExclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForExclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestInclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedInclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForInclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestExclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedExclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForExclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestInclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedInclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForInclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestExclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedExclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForExclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestInclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedInclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForInclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestExclusiveShortParsers() {
    return BIG_INTEGER_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedExclusiveShortValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveShort(bigIntegerValue),
            getExpectedInvalidValueForExclusiveShort(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestInclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveShort(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestExclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveShort(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestInclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveShort(bigDecimalValue.doubleValue(), roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestExclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveShort(bigDecimalValue.doubleValue(), roundingMode))));
  }

  public static Stream<Arguments> provideFloatValuesToTestInclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedInclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveShort(bigDecimalValue.floatValue(), roundingMode))
            ));
  }

  public static Stream<Arguments> provideFloatValuesToTestExclusiveShortParsers() {
    return BIG_DECIMAL_VALUES_FOR_SHORT.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedExclusiveShortValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveShort(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveShort(bigDecimalValue.floatValue(), roundingMode))));
  }

  public static Stream<Arguments> provideIntegralStringValuesToTestInclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedInclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForInclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegralStringValuesToTestExclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedExclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForExclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestInclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveIntegerUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestExclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveIntegerUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestInclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_INTEGER.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getNumberFormatForIntegers(locale).format(bigIntegerValue),
                getExpectedInclusiveIntegerValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
                getExpectedInvalidValueForInclusiveIntegerUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestExclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_INTEGER.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getNumberFormatForIntegers(locale).format(bigIntegerValue),
                getExpectedExclusiveIntegerValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
                getExpectedInvalidValueForExclusiveIntegerUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestInclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_INTEGER.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedInclusiveIntegerValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
                getExpectedInvalidValueForInclusiveIntegerUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestExclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_INTEGER.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedExclusiveIntegerValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
                getExpectedInvalidValueForExclusiveIntegerUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestInclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveIntegerUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestInclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveIntegerUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestExclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveIntegerUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestExclusiveIntegerParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveIntegerUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestInclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedInclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForInclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestExclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedExclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForExclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestInclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedInclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForInclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestExclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedExclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForExclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestInclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedInclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForInclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestExclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedExclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForExclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestInclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedInclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForInclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestExclusiveIntegerParsers() {
    return BIG_INTEGER_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedExclusiveIntegerValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveInteger(bigIntegerValue),
            getExpectedInvalidValueForExclusiveInteger(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestInclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestExclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestInclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestExclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideFloatValuesToTestInclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedInclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideFloatValuesToTestExclusiveIntegerParsers() {
    return BIG_DECIMAL_VALUES_FOR_INTEGER.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedExclusiveIntegerValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveInteger(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveInteger(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideIntegralStringValuesToTestInclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedInclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForInclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegralStringValuesToTestExclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .map(bigIntegerValue -> arguments(
            getFormattedValueUsingJavaToString(bigIntegerValue),
            getExpectedExclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForExclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestInclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveLongUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideDecimalStringValuesToTestExclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                getFormattedValueUsingJavaToString(bigDecimalValue),
                roundingMode,
                getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveLongUsingJavaToString(bigDecimalValue, roundingMode)
            )));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestInclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_LONG.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getNumberFormatForIntegers(locale).format(bigIntegerValue),
                getExpectedInclusiveLongValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
                getExpectedInvalidValueForInclusiveLongUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaNumberFormatToTestExclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_LONG.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getNumberFormatForIntegers(locale).format(bigIntegerValue),
                getExpectedExclusiveLongValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
                getExpectedInvalidValueForExclusiveLongUsingJavaNumberFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestInclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_LONG.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedInclusiveLongValue(bigIntegerValue),
                getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
                getExpectedInvalidValueForInclusiveLongUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificIntegralStringValuesCreatedByJavaStringFormatToTestExclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> BIG_INTEGER_VALUES_FOR_LONG.stream()
            .map(bigIntegerValue -> arguments(
                locale,
                getFormattedValueUsingJavaStringFormat(bigIntegerValue, locale),
                getExpectedExclusiveLongValue(bigIntegerValue),
                getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
                getExpectedInvalidValueForExclusiveLongUsingJavaStringFormat(bigIntegerValue, locale))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestInclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_LONG.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveLongUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestInclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_LONG.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForInclusiveLongUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaNumberFormatToTestExclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_LONG.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaNumberFormat(bigDecimalValue, locale),
                    getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveLongUsingJavaNumberFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideLocaleSpecificDecimalStringValuesCreatedByJavaStringFormatToTestExclusiveLongParsers() {
    return MINIMAL_SET_OF_LOCALES_REPRESENTING_ALL_DISTINCT_NUMBER_FORMATS.stream()
        .flatMap(locale -> ROUNDING_MODES.stream()
            .flatMap(roundingMode -> BIG_DECIMAL_VALUES_FOR_LONG.stream()
                .map(bigDecimalValue -> arguments(
                    locale,
                    roundingMode,
                    getFormattedValueUsingJavaStringFormat(bigDecimalValue, locale),
                    getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                    getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                    getExpectedInvalidValueForExclusiveLongUsingJavaStringFormat(bigDecimalValue, roundingMode, locale)
                ))));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestInclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedInclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForInclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigIntegerValuesToTestExclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .map(bigIntegerValue -> arguments(
            bigIntegerValue,
            getExpectedExclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForExclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestInclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedInclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForInclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideShortValuesToTestExclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidShort)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.shortValue(),
            getExpectedExclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForExclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestInclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedInclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForInclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideIntegerValuesToTestExclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidInteger)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.intValue(),
            getExpectedExclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForExclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestInclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedInclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForInclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForInclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideLongValuesToTestExclusiveLongParsers() {
    return BIG_INTEGER_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidLong)
        .map(bigIntegerValue -> arguments(
            bigIntegerValue.longValue(),
            getExpectedExclusiveLongValue(bigIntegerValue),
            getExpectedExceptionMessageForExclusiveLong(bigIntegerValue),
            getExpectedInvalidValueForExclusiveLong(bigIntegerValue)));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestInclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveLong(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideBigDecimalValuesToTestExclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue,
                roundingMode,
                getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveLong(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestInclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveLong(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideDoubleValuesToTestExclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidDouble)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.doubleValue(),
                roundingMode,
                getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveLong(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideFloatValuesToTestInclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedInclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForInclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForInclusiveLong(bigDecimalValue, roundingMode))));
  }

  public static Stream<Arguments> provideFloatValuesToTestExclusiveLongParsers() {
    return BIG_DECIMAL_VALUES_FOR_LONG.stream()
        .filter(NumericValueScenarios::isInclusivelyValidFloat)
        .flatMap(bigDecimalValue -> ROUNDING_MODES.stream()
            .map(roundingMode -> arguments(
                bigDecimalValue.floatValue(),
                roundingMode,
                getExpectedExclusiveLongValue(bigDecimalValue, roundingMode),
                getExpectedExceptionMessageForExclusiveLong(bigDecimalValue, roundingMode),
                getExpectedInvalidValueForExclusiveLong(bigDecimalValue, roundingMode))));
  }


}
