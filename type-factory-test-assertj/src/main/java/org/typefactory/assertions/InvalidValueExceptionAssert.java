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
import org.typefactory.InvalidValueException;

/**
 * <p>{@link InvalidValueException} specific assertions.</p>
 */
public class InvalidValueExceptionAssert extends AbstractInvalidValueExceptionAssert<InvalidValueExceptionAssert, InvalidValueException> {

  /**
   * Creates a new <code>{@link InvalidValueExceptionAssert}</code> to make assertions on actual InvalidValueException.
   * @param actual the InvalidValueException we want to make assertions on.
   */
  public InvalidValueExceptionAssert(InvalidValueException actual) {
    super(actual, InvalidValueExceptionAssert.class);
  }

  /**
   * An entry point for InvalidValueExceptionAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myInvalidValueException)</code> and get specific assertion with code completion.
   * @param actual the InvalidValueException we want to make assertions on.
   * @return a new <code>{@link InvalidValueExceptionAssert}</code>
   */
  @CheckReturnValue
  public static InvalidValueExceptionAssert assertThat(InvalidValueException actual) {
    return new InvalidValueExceptionAssert(actual);
  }
}
