package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;

/**
 * Entry point for soft assertions of different data types.
 */
public class SoftAssertions extends org.assertj.core.api.SoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link IntegerTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public IntegerTypeAssert assertThat(org.typefactory.IntegerType actual) {
    return proxy(IntegerTypeAssert.class, org.typefactory.IntegerType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public InvalidValueExceptionAssert assertThat(org.typefactory.InvalidValueException actual) {
    return proxy(InvalidValueExceptionAssert.class, org.typefactory.InvalidValueException.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public LongTypeAssert assertThat(org.typefactory.LongType actual) {
    return proxy(LongTypeAssert.class, org.typefactory.LongType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public MessageCodeAssert assertThat(org.typefactory.MessageCode actual) {
    return proxy(MessageCodeAssert.class, org.typefactory.MessageCode.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public ShortTypeAssert assertThat(org.typefactory.ShortType actual) {
    return proxy(ShortTypeAssert.class, org.typefactory.ShortType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public StringTypeAssert assertThat(org.typefactory.StringType actual) {
    return proxy(StringTypeAssert.class, org.typefactory.StringType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public SubsetAssert assertThat(org.typefactory.Subset actual) {
    return proxy(SubsetAssert.class, org.typefactory.Subset.class, actual);
  }

}
