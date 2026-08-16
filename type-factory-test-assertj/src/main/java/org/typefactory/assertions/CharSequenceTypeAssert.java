/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.assertions;

import org.assertj.core.annotation.CheckReturnValue;
import org.typefactory.CharSequenceType;
import org.typefactory.StringType;

/**
 * <p>{@link StringType} specific assertions.</p>
 */
public class CharSequenceTypeAssert extends AbstractCharSequenceTypeAssert<CharSequenceTypeAssert, CharSequenceType<?>> {

  /**
   * Creates a new <code>{@link CharSequenceTypeAssert}</code> to make assertions on actual StringType.
   * @param actual the StringType we want to make assertions on.
   */
  public CharSequenceTypeAssert(final CharSequenceType<?> actual) {
    super(actual, CharSequenceTypeAssert.class);
  }

  /**
   * An entry point for StringTypeAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myStringType)</code> and get specific assertion with code completion.
   * @param actual the StringType we want to make assertions on.
   * @return a new <code>{@link CharSequenceTypeAssert}</code>
   */
  @CheckReturnValue
  public static <T extends CharSequenceType<T>> CharSequenceTypeAssert assertThat(T actual) {
    return new CharSequenceTypeAssert(actual);
  }
}
