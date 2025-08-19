package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.StringType;
import org.typefactory.Subset;

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
  public IntegerTypeAssert assertThat(IntegerType actual) {
    return proxy(IntegerTypeAssert.class, IntegerType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public InvalidValueExceptionAssert assertThat(InvalidValueException actual) {
    return proxy(InvalidValueExceptionAssert.class, InvalidValueException.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public LongTypeAssert assertThat(LongType actual) {
    return proxy(LongTypeAssert.class, LongType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public MessageCodeAssert assertThat(MessageCode actual) {
    return proxy(MessageCodeAssert.class, MessageCode.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public ShortTypeAssert assertThat(ShortType actual) {
    return proxy(ShortTypeAssert.class, ShortType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public StringTypeAssert assertThat(StringType actual) {
    return proxy(StringTypeAssert.class, StringType.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public SubsetAssert assertThat(Subset actual) {
    return proxy(SubsetAssert.class, Subset.class, actual);
  }

}
