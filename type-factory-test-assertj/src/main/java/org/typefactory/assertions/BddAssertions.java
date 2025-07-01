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
  public static IntegerTypeAssert then(IntegerType actual) {
    return new IntegerTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static InvalidValueExceptionAssert then(InvalidValueException actual) {
    return new InvalidValueExceptionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static LongTypeAssert then(LongType actual) {
    return new LongTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static MessageCodeAssert then(MessageCode actual) {
    return new MessageCodeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ShortTypeAssert then(ShortType actual) {
    return new ShortTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static StringTypeAssert then(StringType actual) {
    return new StringTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static SubsetAssert then(Subset actual) {
    return new SubsetAssert(actual);
  }

  /**
   * Creates a new <code>{@link BddAssertions}</code>.
   */
  protected BddAssertions() {
    // empty
  }
}
