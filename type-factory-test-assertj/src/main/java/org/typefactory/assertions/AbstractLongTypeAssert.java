package org.typefactory.assertions;

import org.assertj.core.api.AbstractComparableAssert;
import org.typefactory.LongType;

/**
 * Abstract base class for {@link LongType} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractLongTypeAssert<SELF extends AbstractLongTypeAssert<SELF, ACTUAL>, ACTUAL extends LongType> extends
    AbstractComparableAssert<SELF, ACTUAL> {

  /**
   * Creates a new <code>{@link AbstractLongTypeAssert}</code> to make assertions on actual LongType.
   *
   * @param actual the LongType we want to make assertions on.
   */
  protected AbstractLongTypeAssert(ACTUAL actual, Class<SELF> selfType) {
    super(actual, selfType);
  }

  protected String classNameOfActual() {
    return actual == null ? "<? extends LongType>" : actual.getClass().getSimpleName();
  }

  protected Long valueOfActual() {
    if (actual == null || actual.value() == null) {
      return null;
    }
    return actual.value();
  }

  protected Long valueOfExpected(final Long expected) {
    return expected;
  }

  protected boolean actualIsNull() {
    return actual == null;
  }

  protected boolean actualHasNullValue() {
    return actual != null && actual.value() == null;
  }

  /**
   * Verifies that the actual LongType's {@code .value()} is equal to the given one.
   *
   * <pre>{@code
   * Examples for:   assertThat(actual) | assertion        | result
   * ---------------------------------- | ---------------- | ---------
   * actual = null                      | .hasValue(null)  | fails
   * actual = new SomeLongType(null))   | .hasValue(null)  | succeeds
   * actual = new SomeLongType(null))   | .hasValue(0)     | fails
   * actual = new SomeLongType(null))   | .hasValue(123)   | fails
   * actual = new SomeLongType(0))      | .hasValue(null)  | fails
   * actual = new SomeLongType(0))      | .hasValue(0)     | succeeds
   * actual = new SomeLongType(0))      | .hasValue(123)   | fails
   * actual = new SomeLongType(123))    | .hasValue(null)  | fails
   * actual = new SomeLongType(123))    | .hasValue(0)     | fails
   * actual = new SomeLongType(123))    | .hasValue(123)   | succeeds
   * }</pre>
   *
   * @param expected the given value to compare the actual LongType's {@code .value()} to.
   * @return this assertion object.
   * @throws AssertionError if the actual LongType's {@code .value()} is not equal to the given one.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasValue(final long expected) {
    return hasValue(Long.valueOf(expected));
  }

  /**
   * Verifies that the actual LongType's {@code .value()} is equal to the given one.
   *
   * <pre>{@code
   * Examples for:   assertThat(actual) | assertion        | result
   * ---------------------------------- | ---------------- | ---------
   * actual = null                      | .hasValue(null)  | fails
   * actual = new SomeLongType(null))   | .hasValue(null)  | succeeds
   * actual = new SomeLongType(null))   | .hasValue(0)     | fails
   * actual = new SomeLongType(null))   | .hasValue(123)   | fails
   * actual = new SomeLongType(0))      | .hasValue(null)  | fails
   * actual = new SomeLongType(0))      | .hasValue(0)     | succeeds
   * actual = new SomeLongType(0))      | .hasValue(123)   | fails
   * actual = new SomeLongType(123))    | .hasValue(null)  | fails
   * actual = new SomeLongType(123))    | .hasValue(0)     | fails
   * actual = new SomeLongType(123))    | .hasValue(123)   | succeeds
   * }</pre>
   *
   * @param expected the given value to compare the actual LongType's {@code .value()} to.
   * @return this assertion object.
   * @throws AssertionError if the actual LongType's {@code .value()} is not equal to the given one.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasValue(final Long expected) {
    if (actualIsNull()) {
      if (expected == null) {
        throw failure(
            "%nExpected actual of type:  %s%nto be an instance with value:  %s%nbut actual was:  null%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
            classNameOfActual(), valueOfExpected(expected));
      }
      throw failure("%nExpected actual of type:  %s%nto be an instance with value:  %s%nbut actual was:  null",
          classNameOfActual(), valueOfExpected(expected));
    }
    if (expected == null) {
      if (actualHasNullValue()) {
        return myself;
      }
    } else if (actual.value() != null && expected.equals(actual.value())) {
      return myself;
    }
    throw failure("%nExpected actual of type:  %s%nto have value:  %s%nbut value was:  %s",
        classNameOfActual(), valueOfExpected(expected), valueOfActual());
  }

  /**
   * Verifies that the actual LongType's {@code .value()} is {@code null}.
   *
   * <pre>{@code
   * Examples for:   assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ---------------------------------- | --------- | --------------- | -----------------------
   * actual = null                      | succeeds  | fails           | succeeds
   * actual = new SomeLongType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeLongType(0))      | fails     | fails           | fails
   * actual = new SomeLongType(123))    | fails     | fails           | fails
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the actual LongType's {@code .value()} is not equal to {@code null}.
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
   * <p>The actual cannot be an instance of a LongType with a null internal value.</p>
   *
   * <p>Use {@link #isNullOrHasNullValue()} if you'd like the assertion to also test whether an instance of a custom {@code LongType} has a
   * null internal value.</p>
   *
   * <pre>{@code
   * Examples for:   assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ---------------------------------- | --------- | --------------- | -----------------------
   * actual = null                      | succeeds  | fails           | succeeds
   * actual = new SomeLongType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeLongType(0))      | fails     | fails           | fails
   * actual = new SomeLongType(123))    | fails     | fails           | fails
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
   * Verifies that the {@code actual} is null or the {@code actual} is an instance of {@code LongType} with an internal value of null.
   *
   * <pre>{@code
   * Examples for:   assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ---------------------------------- | --------- | --------------- | -----------------------
   * actual = null                      | succeeds  | fails           | succeeds
   * actual = new SomeLongType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeLongType(0))      | fails     | fails           | fails
   * actual = new SomeLongType(123))    | fails     | fails           | fails
   * }</pre>
   *
   * @throws AssertionError if the {@code actual} is null or the {@code actual} is an instance of {@code LongType} with an internal value of null.
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
   * <p>Use {@link #isNotNullAndHasNonNullValue()} if you'd like the assertion to also test whether an instance of a custom LongType has a
   * non-null internal value.</p>
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | .isNotNull() | .isNotNullAndHasNonNullValue()
   * ----------------------------------- | ------------ | ------------------------------
   * actual = null                       | fails        | fails
   * actual = new SomeLongType(null))    | succeeds     | fails
   * actual = new SomeLongType(0))       | succeeds     | succeeds
   * actual = new SomeLongType(123))     | succeeds     | succeeds
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
   * Verifies that the {@code actual} is not null and the {@code actual} is an instance of {@code LongType} with a non-null internal value.
   *
   * <pre>{@code
   * Examples for:    assertThat(actual) | .isNotNull() | .isNotNullAndHasNonNullValue()
   * ----------------------------------- | ------------ | ------------------------------
   * actual = null                       | fails        | fails
   * actual = new SomeLongType(null))    | succeeds     | fails
   * actual = new SomeLongType(0))       | succeeds     | succeeds
   * actual = new SomeLongType(123))     | succeeds     | succeeds
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the {@code actual} is not null and the {@code actual} is an instance of {@code LongType} with a non-null internal
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
