package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;
import org.typefactory.CharSequenceType;
import org.typefactory.IntegerType;
import org.typefactory.InvalidValueException;
import org.typefactory.LongType;
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.StringType;
import org.typefactory.Subset;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
public class Assertions extends org.assertj.core.api.Assertions {

  /**
   * Creates a new instance of <code>{@link IntegerTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static IntegerTypeAssert assertThat(IntegerType actual) {
    return new IntegerTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static InvalidValueExceptionAssert assertThat(InvalidValueException actual) {
    return new InvalidValueExceptionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static LongTypeAssert assertThat(LongType actual) {
    return new LongTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static MessageCodeAssert assertThat(MessageCode actual) {
    return new MessageCodeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ShortTypeAssert assertThat(ShortType actual) {
    return new ShortTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static StringTypeAssert assertThat(StringType actual) {
    return new StringTypeAssert(actual);
  }

  @CheckReturnValue
  public static <T extends CharSequenceType<T>> CharSequenceTypeAssert assertThat(T actual) {
    return new CharSequenceTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static SubsetAssert assertThat(Subset actual) {
    return new SubsetAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}
