package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.util.Comparator;
import java.util.Objects;

public interface MinValueValidator<T> {

  boolean isValid(final T value);

  default boolean isInvalid(final T value) {
    return !isValid(value);
  }

  String minValueAsString();

  static <T> MinValueValidator<T> of(final T minValue, final Comparator<T> comparator, final ComparisonMethod comparisonMethod) {
    return new MinValueValidatorImpl<>(minValue, comparator, comparisonMethod);
  }

  record MinValueValidatorImpl<T>(
      T minValue,
      Comparator<T> comparator,
      ComparisonMethod comparisonMethod) implements MinValueValidator<T> {

    @Override
    public boolean isValid(final T value) {
      return switch (comparisonMethod) {
        case INCLUSIVE -> comparator.compare(value, minValue) >= 0;
        case EXCLUSIVE -> comparator.compare(value, minValue) > 0;
      };
    }

    @Override
    public String minValueAsString() {
      return Objects.toString(minValue, EMPTY_STRING);
    }
  }

}
