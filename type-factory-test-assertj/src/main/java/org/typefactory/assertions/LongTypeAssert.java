package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;
import org.typefactory.LongType;

/**
 * <p>{@link LongType} specific assertions.</p>
 */
public class LongTypeAssert extends AbstractLongTypeAssert<LongTypeAssert, LongType> {

  /**
   * Creates a new <code>{@link LongTypeAssert}</code> to make assertions on actual LongType.
   * @param actual the LongType we want to make assertions on.
   */
  public LongTypeAssert(LongType actual) {
    super(actual, LongTypeAssert.class);
  }

  /**
   * An entry point for LongTypeAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myLongType)</code> and get specific assertion with code completion.
   * @param actual the LongType we want to make assertions on.
   * @return a new <code>{@link LongTypeAssert}</code>
   */
  @CheckReturnValue
  public static LongTypeAssert assertThat(LongType actual) {
    return new LongTypeAssert(actual);
  }
}
