package org.typefactory.assertions;

import org.assertj.core.api.AbstractCharSequenceAssert;
import org.typefactory.CharSequenceType;
import org.typefactory.CharSequenceUtils;

/**
 * Abstract base class for {@link CharSequenceType} specific assertions.
 */
@SuppressWarnings("java:S119") // Generic parameter naming
public abstract class AbstractCharSequenceTypeAssert<
    SELF extends AbstractCharSequenceTypeAssert<SELF, ACTUAL>,
    ACTUAL extends CharSequenceType<?>>
    extends AbstractCharSequenceAssert<SELF, ACTUAL> {

  private static final String NULL_VALUE = "null";
  private static final String EMPTY_VALUE = "\"\" (empty)";

  /**
   * Creates a new <code>{@link AbstractCharSequenceTypeAssert}</code> to make assertions on actual CharSequenceType.
   *
   * @param actual the CharSequenceType we want to make assertions on.
   */
  protected AbstractCharSequenceTypeAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  protected String classNameOfActual() {
    return actual == null ? "<? extends CharSequenceType>" : actual.getClass().getSimpleName();
  }

  protected String valueOfActual() {
    if (actual == null || actual.value() == null) {
      return NULL_VALUE;
    }
    if (actual.isEmpty()) {
      return EMPTY_VALUE;
    }
    if (CharSequenceUtils.isBlank(actual)) {
      return String.format("\"%s\" (blank)", actual.value());
    }
    return '"' + actual.value().toString() + '"';
  }

  protected String valueOfExpected(final String expected) {
    if (expected == null) {
      return NULL_VALUE;
    }
    if (expected.isEmpty()) {
      return EMPTY_VALUE;
    }
    if (CharSequenceUtils.isBlank(expected)) {
      return String.format("\"%s\" (blank)", expected);
    }
    return '"' + expected + '"';
  }

  protected boolean actualIsNull() {
    return actual == null;
  }

  protected boolean actualHasNullValue() {
    return actual != null && actual.value() == null;
  }

  /**
   * Verifies that the actual CharSequenceType's {@code .value()} is equal to the given one.
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | assertion        | result
   * ------------------------------------------ | ---------------- | ---------
   * actual = null                              | .hasValue(null)  | fails
   * actual = new SomeCharSequenceType(null))   | .hasValue(null)  | succeeds
   * actual = new SomeCharSequenceType(null))   | .hasValue("")    | fails
   * actual = new SomeCharSequenceType(null))   | .hasValue("  ")  | fails
   * actual = new SomeCharSequenceType(""))     | .hasValue(null)  | fails
   * actual = new SomeCharSequenceType(""))     | .hasValue("")    | succeeds
   * actual = new SomeCharSequenceType(""))     | .hasValue("  ")  | fails
   * actual = new SomeCharSequenceType("  "))   | .hasValue(null)  | fails
   * actual = new SomeCharSequenceType("  "))   | .hasValue("")    | fails
   * actual = new SomeCharSequenceType("  "))   | .hasValue("  ")  | succeeds
   * actual = new SomeCharSequenceType("abc"))  | .hasValue("abc") | fails
   * }</pre>
   *
   * @param expected the given value to compare the actual CharSequenceType's {@code .value()} to.
   * @return this assertion object.
   * @throws AssertionError if the actual CharSequenceType's {@code .value()} is not equal to the given one.
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasValue(final String expected) {
    if (actualIsNull()) {
      if (expected == null) {
        throw failure("%nExpected actual of type:  %s%nto be an instance with value:  %s%nbut actual was:  null%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
            classNameOfActual(), valueOfExpected(expected));
      }
      throw failure("%nExpected actual of type:  %s%nto be an instance with value:  %s%nbut actual was:  null",
          classNameOfActual(), valueOfExpected(expected));
    }
    if (expected == null) {
      if (actualHasNullValue()) {
        return myself;
      }
    } else if (actual.value() != null && expected.contentEquals(actual.value())) {
      return myself;
    }
    throw failure("%nExpected actual of type:  %s%nto have value:  %s%nbut value was:  %s",
        classNameOfActual(), valueOfExpected(expected), valueOfActual());
  }

  /**
   * Verifies that the actual CharSequenceType's {@code .value()} is {@code null}.
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | .isNull() | .hasNullValue() | .isNullOrHasNullValue()
   * ------------------------------------------ | --------- | --------------- | -----------------------
   * actual = null                              | succeeds  | fails           | succeeds
   * actual = new SomeCharSequenceType(null))   | fails     | succeeds        | succeeds
   * actual = new SomeCharSequenceType(""))     | fails     | fails           | fails
   * actual = new SomeCharSequenceType("  "))   | fails     | fails           | fails
   * actual = new SomeCharSequenceType("abc"))  | fails     | fails           | fails
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the actual CharSequenceType's {@code .value()} is not equal to {@code null}.
   * @see #isNotNullAndHasNonNullValue()
   * @see #isNullOrHasNullValue()
   * @see #hasNullValue()
   */
  public SELF hasNullValue() {
    if (actualIsNull()) {
      throw failure("%nExpected actual of type:  %s%nto not be null and have value:   %s%nbut actual was:  null.%nConsider using '.isNullOrHasNullValue()' if that is what you want to test.",
          classNameOfActual(), NULL_VALUE);
    }
    if (actualHasNullValue()) {
      return myself;
    }
    throw failure("%nExpected actual of type:  %s%nto have value:  %s%nbut value was:  %s",
        classNameOfActual(), NULL_VALUE, valueOfActual());
  }

  /**
   * <p>Verifies that the actual is {@code null}.</p>
   *
   * <p>The actual cannot be an instance of a CharSequenceType with a null internal value.</p>
   *
   * <p>Use {@link #isNullOrHasNullValue()} if you'd like the assertion to also test whether an instance of a custom {@code CharSequenceType} has a
   * null internal value.</p>
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | .isNull() | .isNullOrHasNullValue() | .hasNullValue()
   * ------------------------------------------ | --------- | ----------------------- | ---------------
   * actual = null                              | succeeds  | succeeds                | fails
   * actual = new SomeCharSequenceType(null))   | fails     | succeeds                | succeeds
   * actual = new SomeCharSequenceType(""))     | fails     | fails                   | fails
   * actual = new SomeCharSequenceType("  "))   | fails     | fails                   | fails
   * actual = new SomeCharSequenceType("abc"))  | fails     | fails                   | fails
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
   * Verifies that the {@code actual} is null or the {@code actual} is an instance of {@code CharSequenceType} with an internal value of null.
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | .isNull() | .isNullOrHasNullValue()
   * ------------------------------------------ | --------- | -----------------------
   * actual = null                              | succeeds  | succeeds
   * actual = new SomeCharSequenceType(null))   | fails     | succeeds
   * actual = new SomeCharSequenceType(""))     | fails     | fails
   * actual = new SomeCharSequenceType("  "))   | fails     | fails
   * actual = new SomeCharSequenceType("abc"))  | fails     | fails
   * }</pre>
   *
   * @throws AssertionError if the {@code actual} is null or the {@code actual} is an instance of {@code CharSequenceType} with an internal value of
   *                        null.
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
   * <p>Use {@link #isNotNullAndHasNonNullValue()} if you'd like the assertion to also test whether an instance of a custom CharSequenceType has a
   * non-null internal value.</p>
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | .isNotNull() | .isNotNullAndHasNonNullValue()
   * ------------------------------------------ | ------------ | ------------------------------
   * actual = null                              | fails        | fails
   * actual = new SomeCharSequenceType(null))   | succeeds     | fails
   * actual = new SomeCharSequenceType(""))     | succeeds     | succeeds
   * actual = new SomeCharSequenceType("  "))   | succeeds     | succeeds
   * actual = new SomeCharSequenceType("abc"))  | succeeds     | succeeds
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
   * Verifies that the {@code actual} is not null and the {@code actual} is an instance of {@code CharSequenceType} with a non-null internal value.
   *
   * <pre>{@code
   * Examples for:           assertThat(actual) | .isNull() | .isNullOrHasNullValue()
   * ------------------------------------------ | --------- | -----------------------
   * actual = null                              | succeeds  | succeeds
   * actual = new SomeCharSequenceType(null))   | fails     | succeeds
   * actual = new SomeCharSequenceType(""))     | fails     | fails
   * actual = new SomeCharSequenceType("  "))   | fails     | fails
   * actual = new SomeCharSequenceType("abc"))  | fails     | fails
   * }</pre>
   *
   * @return this assertion object.
   * @throws AssertionError if the {@code actual} is not null and the {@code actual} is an instance of {@code CharSequenceType} with a non-null
   *                        internal value.
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
