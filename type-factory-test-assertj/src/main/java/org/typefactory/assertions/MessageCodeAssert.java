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
import org.typefactory.MessageCode;

/**
 * <p>{@link MessageCode} specific assertions.</p>
 */
public class MessageCodeAssert extends AbstractMessageCodeAssert<MessageCodeAssert> {

  /**
   * Creates a new <code>{@link MessageCodeAssert}</code> to make assertions on actual MessageCode.
   * @param actual the MessageCode we want to make assertions on.
   */
  public MessageCodeAssert(MessageCode actual) {
    super(actual, MessageCodeAssert.class);
  }

  /**
   * An entry point for MessageCodeAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myMessageCode)</code> and get specific assertion with code completion.
   * @param actual the MessageCode we want to make assertions on.
   * @return a new <code>{@link MessageCodeAssert}</code>
   */
  @CheckReturnValue
  public static MessageCodeAssert assertThat(MessageCode actual) {
    return new MessageCodeAssert(actual);
  }
}
