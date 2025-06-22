package org.typefactory.assertions;

import org.assertj.core.util.CheckReturnValue;
import org.typefactory.StringType;

/**
 * <p>Provides specific assertions for {@link StringType}.</p>
 *
 * <p>Provides methods to assist in checking the internal value of a {@code StringType} instance.</p>
 *
 * @see StringTypeAssert#hasValue(String)
 * @see StringTypeAssert#hasNullValue()
 * @see StringTypeAssert#isNullOrHasNullValue()
 * @see StringTypeAssert#isNull()
 * @see StringTypeAssert#isNotNullAndHasNonNullValue()
 * @see StringTypeAssert#isNotNull()
 */
public class StringTypeAssert extends AbstractCharSequenceTypeAssert<StringTypeAssert, StringType> {

  /**
   * Creates a new <code>{@link StringTypeAssert}</code> to make assertions on actual StringType.
   * @param actual the StringType we want to make assertions on.
   */
  public StringTypeAssert(StringType actual) {
    super(actual, StringTypeAssert.class);
  }

  @Override
  protected String classNameOfActual() {
    return actual == null ? "<? extends StringType>" : actual.getClass().getSimpleName();
  }


  /**
   * An entry point for StringTypeAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myStringType)</code> and get specific assertion with code completion.
   * @param actual the StringType we want to make assertions on.
   * @return a new <code>{@link StringTypeAssert}</code>
   */
  @CheckReturnValue
  public static StringTypeAssert assertThat(StringType actual) {
    return new StringTypeAssert(actual);
  }
}
