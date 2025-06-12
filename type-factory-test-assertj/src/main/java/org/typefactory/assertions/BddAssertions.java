package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;

/**
 * Entry point for BDD assertions of different data types.
 */
public class BddAssertions {

  /**
   * Creates a new instance of <code>{@link IntegerTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static IntegerTypeAssert then(org.typefactory.IntegerType actual) {
    return new IntegerTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static InvalidValueExceptionAssert then(org.typefactory.InvalidValueException actual) {
    return new InvalidValueExceptionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static LongTypeAssert then(org.typefactory.LongType actual) {
    return new LongTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static MessageCodeAssert then(org.typefactory.MessageCode actual) {
    return new MessageCodeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ShortTypeAssert then(org.typefactory.ShortType actual) {
    return new ShortTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static StringTypeAssert then(org.typefactory.StringType actual) {
    return new StringTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static SubsetAssert then(org.typefactory.Subset actual) {
    return new SubsetAssert(actual);
  }

  /**
   * Creates a new <code>{@link BddAssertions}</code>.
   */
  protected BddAssertions() {
    // empty
  }
}
