package org.typefactory.impl;

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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import org.typefactory.NumberFormat;
import org.typefactory.NumberFormat.NumberFormatBuilder;

class NumberFormatBuilderImpl implements NumberFormatBuilder {

  private RoundingMode roundingMode;
  private int[] decimalSeparators;
  private int[] groupingSeparators;
  private int groupingSize;
  private String negativePrefix;
  private String negativeSuffix;
  private int minimumIntegerDigits;
  private int maximumIntegerDigits;
  private int minimumFractionDigits;
  private int maximumFractionDigits;
  private String positivePrefix;
  private String positiveSuffix;
  private int zeroDigit;

  NumberFormatBuilderImpl() {
    this(new DecimalFormat());
  }

  NumberFormatBuilderImpl(final Locale locale) {
    this(locale == null ? new DecimalFormat() : java.text.NumberFormat.getNumberInstance(locale));
  }

  NumberFormatBuilderImpl(final java.text.NumberFormat numberFormat) {

    this.roundingMode = numberFormat.getRoundingMode();
    this.minimumIntegerDigits = numberFormat.getMinimumIntegerDigits();
    this.maximumIntegerDigits = numberFormat.getMaximumIntegerDigits();
    this.minimumFractionDigits = numberFormat.getMinimumFractionDigits();
    this.maximumFractionDigits = numberFormat.getMaximumFractionDigits();

    if (numberFormat instanceof DecimalFormat decimalFormat) {
      this.groupingSize = decimalFormat.getGroupingSize();
      this.negativePrefix = decimalFormat.getNegativePrefix();
      this.negativeSuffix = decimalFormat.getNegativeSuffix();
      this.positivePrefix = decimalFormat.getPositivePrefix();
      this.positiveSuffix = decimalFormat.getPositiveSuffix();

      final var decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
      if (decimalFormatSymbols != null) {
        this.groupingSeparators = determineGroupingSeparatorsAndAlternatives(decimalFormatSymbols.getGroupingSeparator());
        this.decimalSeparators = determineDecimalSeparatorsAndAlternatives(decimalFormatSymbols.getDecimalSeparator());
      }
    }
  }

  /**
   * <p>Get an array of grouping separator code-points along with possible alternatives for the given {@code groupingSeparator}. The first grouping separator
   * in the array is the primary grouping separator provided with the {@code groupingSeparator} argument. The remaining grouping
   * separators, if any, are alternatives to enable lenient parsing of numeric values.</p>
   *
   * @param groupingSeparator the grouping separator used to lookup possible alternatives grouping separators.
   * @return an array of primary and alternative grouping separators for the provided {@code groupingSeparator}
   */
  private static int[] determineGroupingSeparatorsAndAlternatives(final int groupingSeparator) {
    return switch (groupingSeparator) {
      case COMMA -> new int[]{COMMA};
      case FULL_STOP -> new int[]{FULL_STOP};
      case NO_BREAK_SPACE -> new int[]{NO_BREAK_SPACE, NARROW_NO_BREAK_SPACE, SPACE};
      case ARABIC_THOUSANDS_SEPARATOR -> new int[]{ARABIC_THOUSANDS_SEPARATOR, APOSTROPHE_SINGLE_QUOTATION_MARK, ARABIC_COMMA};
      case RIGHT_SINGLE_QUOTATION_MARK -> new int[]{RIGHT_SINGLE_QUOTATION_MARK, APOSTROPHE_SINGLE_QUOTATION_MARK};
      case NARROW_NO_BREAK_SPACE -> new int[]{NARROW_NO_BREAK_SPACE, NO_BREAK_SPACE, SPACE};
      default -> new int[]{groupingSeparator};
    };
  }

  /**
   * <p>Get an array of decimal separator code-points along with possible alternatives for the given {@code decimalSeparator}. The first decimal separator
   * in the array is the primary decimal separator provided with the {@code decimalSeparator} argument. The remaining decimal
   * separators, if any, are alternatives to enable lenient parsing of numeric values.</p>
   *
   * @param decimalSeparator the grouping separator used to lookup possible alternatives grouping separators.
   * @return an array of primary and alternative grouping separators for the provided {@code decimalSeparator}
   */
  private static int[] determineDecimalSeparatorsAndAlternatives(final int decimalSeparator) {
    return switch (decimalSeparator) {
      case COMMA -> new int[]{COMMA, ARABIC_DECIMAL_SEPARATOR};
      case FULL_STOP -> new int[]{FULL_STOP};
      case ARABIC_DECIMAL_SEPARATOR -> new int[]{ARABIC_DECIMAL_SEPARATOR, COMMA};
      default -> new int[]{decimalSeparator};
    };
  }


  @Override
  public NumberFormat build() {
    return new NumberFormatImpl(
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
        zeroDigit);
  }

  @Override
  public NumberFormatBuilder decimalSeparator(char decimalSeparator, char... additionalDecimalSeparators) {
    if (additionalDecimalSeparators == null || additionalDecimalSeparators.length == 0) {
      this.decimalSeparators = new int[]{decimalSeparator};
    } else {
      this.decimalSeparators = new int[1 + additionalDecimalSeparators.length];
      this.decimalSeparators[0] = decimalSeparator;
      for (int i = 0; i < additionalDecimalSeparators.length; i++) {
        this.decimalSeparators[i + 1] = additionalDecimalSeparators[i];
      }
    }
    return this;
  }

  @Override
  public NumberFormatBuilder decimalSeparator(int decimalSeparator, int... additionalDecimalSeparators) {
    this.decimalSeparators = new int[1 + (additionalDecimalSeparators == null ? 0 : additionalDecimalSeparators.length)];
    this.decimalSeparators[0] = decimalSeparator;
    System.arraycopy(additionalDecimalSeparators, 0, this.decimalSeparators, 1, additionalDecimalSeparators.length);
    return this;
  }

  @Override
  public NumberFormatBuilder groupingSeparator(char groupingSeparator, char... additionalGroupingSeparators) {
    if (additionalGroupingSeparators == null || additionalGroupingSeparators.length == 0) {
      this.groupingSeparators = new int[]{groupingSeparator};
    } else {
      this.groupingSeparators = new int[1 + additionalGroupingSeparators.length];
      this.groupingSeparators[0] = groupingSeparator;
      for (int i = 0; i < additionalGroupingSeparators.length; i++) {
        this.groupingSeparators[i + 1] = additionalGroupingSeparators[i];
      }
    }
    return this;
  }

  @Override
  public NumberFormatBuilder groupingSeparator(int groupingSeparator, int... additionalGroupingSeparators) {
    this.groupingSeparators = new int[1 + (additionalGroupingSeparators == null ? 0 : additionalGroupingSeparators.length)];
    this.groupingSeparators[0] = groupingSeparator;
    System.arraycopy(additionalGroupingSeparators, 0, this.groupingSeparators, 1, additionalGroupingSeparators.length);
    return this;
  }

  @Override
  public NumberFormatBuilder groupingSize(int groupingSize) {
    this.groupingSize = groupingSize;
    return this;
  }

  @Override
  public NumberFormatBuilder minimumIntegerDigits(int minimumIntegerDigits) {
    this.minimumIntegerDigits = minimumIntegerDigits;
    return this;
  }

  @Override
  public NumberFormatBuilder maximumIntegerDigits(int maximumIntegerDigits) {
    this.maximumIntegerDigits = maximumIntegerDigits;
    return this;
  }

  @Override
  public NumberFormatBuilder minimumFractionDigits(int minimumFractionDigits) {
    this.minimumFractionDigits = minimumFractionDigits;
    return this;
  }

  @Override
  public NumberFormatBuilder maximumFractionDigits(int maximumFractionDigits) {
    this.maximumFractionDigits = maximumFractionDigits;
    return this;
  }

  @Override
  public NumberFormatBuilder negativePrefix(String negativePrefix) {
    this.negativePrefix = negativePrefix;
    return this;
  }

  @Override
  public NumberFormatBuilder negativeSuffix(String negativeSuffix) {
    this.negativeSuffix = negativeSuffix;
    return this;
  }

  @Override
  public NumberFormatBuilder positivePrefix(String positivePrefix) {
    this.positivePrefix = positivePrefix;
    return this;
  }

  @Override
  public NumberFormatBuilder positiveSuffix(String positiveSuffix) {
    this.positiveSuffix = positiveSuffix;
    return this;
  }

  @Override
  public NumberFormatBuilder roundingMode(RoundingMode roundingMode) {
    this.roundingMode = roundingMode;
    return this;
  }

  @Override
  public NumberFormatBuilder thousandsSeparator(char thousandsSeparator, char... additionalThousandsSeparators) {
    return groupingSeparator(thousandsSeparator, additionalThousandsSeparators);
  }

  @Override
  public NumberFormatBuilder thousandsSeparator(int thousandsSeparator, int... additionalThousandsSeparators) {
    return groupingSeparator(thousandsSeparator, additionalThousandsSeparators);
  }

  @Override
  public NumberFormatBuilder zeroDigit(char zeroDigit) {
    this.zeroDigit = zeroDigit;
    return this;
  }

  @Override
  public NumberFormatBuilder zeroDigit(int zeroDigit) {
    this.zeroDigit = zeroDigit;
    return this;
  }


}
