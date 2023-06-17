package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.util.Comparator;
import java.util.Objects;

public interface MaxValueValidator<T> {

  boolean isValid(final T value);

  default boolean isInvalid(final T value) {
    return !isValid(value);
  }

  String maxValueAsString();

  static <T> MaxValueValidator<T> of(final T maxValue, final Comparator<T> comparator, final ComparisonMethod comparisonMethod) {
    return new MaxValueValidatorImpl<>(maxValue, comparator, comparisonMethod);
  }

  record MaxValueValidatorImpl<T>(
      T maxValue,
      Comparator<T> comparator,
      ComparisonMethod comparisonMethod) implements MaxValueValidator<T> {

    @Override
    public boolean isValid(final T value) {
      return switch (comparisonMethod) {
        case INCLUSIVE -> comparator.compare(value, maxValue) <= 0;
        case EXCLUSIVE -> comparator.compare(value, maxValue) < 0;
      };
    }

    @Override
    public String maxValueAsString() {
      return Objects.toString(maxValue, EMPTY_STRING);
    }
  }

}
