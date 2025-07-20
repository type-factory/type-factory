package org.typefactory.assertions;

import org.assertj.core.api.AbstractComparableAssert;
import org.typefactory.ShortType;

/**
 * Abstract base class for {@link ShortType} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractShortTypeAssert<
    SELF extends AbstractShortTypeAssert<SELF, ACTUAL>,
    ACTUAL extends ShortType>
    extends AbstractComparableAssert<SELF, ACTUAL> {

  /**
   * Creates a new <code>{@link AbstractShortTypeAssert}</code> to make assertions on actual ShortType.
   *
   * @param actual the ShortType we want to make assertions on.
   */
  protected AbstractShortTypeAssert(ACTUAL actual, Class<SELF> selfType) {
    super(actual, selfType);
  }

  protected String classNameOfActual() {
    return actual == null ? "<? extends ShortType>" : actual.getClass().getSimpleName();
  }

  protected Short valueOfActual() {
    if (actual == null || actual.value() == null) {
      return null;
    }
    return actual.value();
  }

  protected boolean actualIsNull() {
    return actual == null;
  }

  protected boolean actualHasNullValue() {
    return actual != null && actual.value() == null;
  }

  /**
   * Verifies that the actual ShortType's {@code .value()} is equal to the given one.
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | assertion        | result
   * ----------------------------------- | ---------------- | ---------
   * actual = null                       | .hasValue(null)  | fails
   * actual = new SomeShortType(null))   | .hasValue(null)  | succeeds
   * actual = new SomeShortType(null))   | .hasValue(0)     | fails
   * actual = new SomeShortType(null))   | .hasValue(123)   | fails
   * actual = new SomeShortType(0))      | .hasValue(null)  | fails
   * actual = new SomeShortType(0))      | .hasValue(0)     | succeeds
   * actual = new SomeShortType(0))      | .hasValue(123)   | fails
   * actual = new SomeShortType(123))    | .hasValue(null)  | fails
   * actual = new SomeShortType(123))    | .hasValue(0)     | fails
   * actual = new SomeShortType(123))    | .hasValue(123)   | succeeds
   * }</pre>
   *
   * @param expected the given value to compare the actual ShortType's {@code .value()} to.
   * @return this assertion object.
   * @throws AssertionError if the actual ShortType's {@code .value()} is not equal to the given one.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasValue(final short expected) {
    return hasValue(Short.valueOf(expected));
  }

  /**
   * Verifies that the actual ShortType's {@code .value()} is equal to the given one.
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | assertion        | result
   * ----------------------------------- | ---------------- | ---------
   * actual = null                       | .hasValue(null)  | fails
   * actual = new SomeShortType(null))   | .hasValue(null)  | succeeds
   * actual = new SomeShortType(null))   | .hasValue(0)     | fails
   * actual = new SomeShortType(null))   | .hasValue(123)   | fails
   * actual = new SomeShortType(0))      | .hasValue(null)  | fails
   * actual = new SomeShortType(0))      | .hasValue(0)     | succeeds
   * actual = new SomeShortType(0))      | .hasValue(123)   | fails
   * actual = new SomeShortType(123))    | .hasValue(null)  | fails
   * actual = new SomeShortType(123))    | .hasValue(0)     | fails
   * actual = new SomeShortType(123))    | .hasValue(123)   | succeeds
   * }</pre>
   *
   * @param expected the given value to compare the actual ShortType's {@code .value()} to.
   * @return this assertion object.
   * @throws AssertionError if the actual ShortType's {@code .value()} is not equal to the given one.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasValue(final Short expected) {
    if (actualIsNull()) {
      if (expected == null) {
        throw failure(
            "%nExpected actual of type:  %s%nto be an instance with value:  null%nbut actual was:  null%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
            classNameOfActual());
      }
      throw failure("%nExpected actual of type:  %s%nto be an instance with value:  %d%nbut actual was:  null",
          classNameOfActual(), expected);
    }
    if (expected == null) {
      if (actualHasNullValue()) {
        return myself;
      }
    } else if (actual.value() != null && expected.equals(actual.value())) {
      return myself;
    }
    throw failure("%nExpected actual of type:  %s%nto have value:  %d%nbut value was:  %s",
        classNameOfActual(), expected, valueOfActual());
  }

  /**
   * Verifies that the actual ShortType's {@code .value()} is {@code null}.
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ----------------------------------- | --------- | --------------- | -----------------------
   * actual = null                       | succeeds  | fails           | succeeds
   * actual = new SomeShortType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeShortType(0))      | fails     | fails           | fails
   * actual = new SomeShortType(123))    | fails     | fails           | fails
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the actual ShortType's {@code .value()} is not equal to {@code null}.
   * @see #isNotNullAndHasNonNullValue()
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasNullValue() {
    if (actualIsNull()) {
      throw failure("%nExpected actual of type:  %s%nto not be null and have value:   null%nbut actual was:  null.%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
          classNameOfActual());
    }
    if (actualHasNullValue()) {
      return myself;
    }
    throw failure("%nExpected actual of type:  %s%nto have value:  null%nbut value was:  %s",
        classNameOfActual(), valueOfActual());
  }

  /**
   * <p>Verifies that the actual is {@code null}.</p>
   *
   * <p>The actual cannot be an instance of a ShortType with a null internal value.</p>
   *
   * <p>Use {@link #isNullOrHasNullValue()} if you'd like the assertion to also test whether an instance of a custom {@code ShortType} has a
   * null internal value.</p>
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ----------------------------------- | --------- | --------------- | -----------------------
   * actual = null                       | succeeds  | fails           | succeeds
   * actual = new SomeShortType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeShortType(0))      | fails     | fails           | fails
   * actual = new SomeShortType(123))    | fails     | fails           | fails
   * }</pre>
   *
   * @throws AssertionError if the actual value is not {@code null}.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  @Override
  public void isNull() {
    if (actualIsNull()) {
      return;
    }
    if (actualHasNullValue()) {
      throw failure(
          "%nExpected actual of type:  %s%nto be:  null%nbut was non-null with an internal value:  %s%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
          classNameOfActual(), valueOfActual());
    }
    throw failure("%nExpected actual of type:  %s%nto be:  null%nbut was non-null with an internal value:  %s",
        classNameOfActual(), valueOfActual());
  }

  /**
   * Verifies that the {@code actual} is null or the {@code actual} is an instance of {@code ShortType} with an internal value of null.
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ----------------------------------- | --------- | --------------- | -----------------------
   * actual = null                       | succeeds  | fails           | succeeds
   * actual = new SomeShortType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeShortType(0))      | fails     | fails           | fails
   * actual = new SomeShortType(123))    | fails     | fails           | fails
   * }</pre>
   *
   * @throws AssertionError if the {@code actual} is null or the {@code actual} is an instance of {@code ShortType} with an internal value of null.
   * @see #isNull()
   * @see #hasNullValue()
   */
  public void isNullOrHasNullValue() {
    if (actualIsNull() || actualHasNullValue()) {
      return;
    }
    throw failure("%nExpected actual of type: %s%nto be null or have an internal value of null%nbut was:  %s", classNameOfActual(), valueOfActual());
  }

  /**
   * <p>Verifies that the actual is not {@code null}.</p>
   *
   * <p>Use {@link #isNotNullAndHasNonNullValue()} if you'd like the assertion to also test whether an instance of a custom ShortType has a
   * non-null internal value.</p>
   *
   * <pre>{@code
   * Examples for:     assertThat(actual) | .isNotNull() | .isNotNullAndHasNonNullValue()
   * ------------------------------------ | ------------ | ------------------------------
   * actual = null                        | fails        | fails
   * actual = new SomeShortType(null))    | succeeds     | fails
   * actual = new SomeShortType(0))       | succeeds     | succeeds
   * actual = new SomeShortType(123))     | succeeds     | succeeds
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   */
  @Override
  public SELF isNotNull() {
    if (actualIsNull()) {
      throw failure("%nExpected actual of type:  %s%nto not be:  null%nbut was: null", classNameOfActual());
    }
    return myself;
  }

  /**
   * Verifies that the {@code actual} is not null and the {@code actual} is an instance of {@code ShortType} with a non-null internal value.
   *
   * <pre>{@code
   * Examples for:     assertThat(actual) | .isNotNull() | .isNotNullAndHasNonNullValue()
   * ------------------------------------ | ------------ | ------------------------------
   * actual = null                        | fails        | fails
   * actual = new SomeShortType(null))    | succeeds     | fails
   * actual = new SomeShortType(0))       | succeeds     | succeeds
   * actual = new SomeShortType(123))     | succeeds     | succeeds
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the {@code actual} is not null and the {@code actual} is an instance of {@code ShortType} with a non-null internal
   *                        value.
   */
  public SELF isNotNullAndHasNonNullValue() {
    if (actualIsNull()) {
      throw failure("%nExpected actual of type:  %s%nto not be:  null%nbut was:  %s",
          classNameOfActual(), valueOfActual());
    }
    if (actualHasNullValue()) {
      throw failure("%nExpected actual of type:  %s%nto not have value: null%nbut value was:  %s",
          classNameOfActual(), valueOfActual());
    }
    return myself;
  }
}
