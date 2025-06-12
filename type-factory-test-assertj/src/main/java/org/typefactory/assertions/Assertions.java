package org.typefactory.assertions;

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
  @org.assertj.core.util.CheckReturnValue
  public static IntegerTypeAssert assertThat(org.typefactory.IntegerType actual) {
    return new IntegerTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link InvalidValueExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static InvalidValueExceptionAssert assertThat(org.typefactory.InvalidValueException actual) {
    return new InvalidValueExceptionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static LongTypeAssert assertThat(org.typefactory.LongType actual) {
    return new LongTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link MessageCodeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static MessageCodeAssert assertThat(org.typefactory.MessageCode actual) {
    return new MessageCodeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static ShortTypeAssert assertThat(org.typefactory.ShortType actual) {
    return new ShortTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link StringTypeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static StringTypeAssert assertThat(org.typefactory.StringType actual) {
    return new StringTypeAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link SubsetAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static SubsetAssert assertThat(org.typefactory.Subset actual) {
    return new SubsetAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}
