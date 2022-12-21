/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.io.Serial;
import java.util.Objects;
import org.typefactory.MessageCode;

class MessageCodeImpl implements MessageCode {

  @Serial
  private static final long serialVersionUID = -2182286351618585098L;

  protected final String messageCode;
  protected final String defaultMessage;

  MessageCodeImpl(final String messageCode, final String defaultMessage) {
    this.messageCode = messageCode;
    this.defaultMessage = defaultMessage == null ? EMPTY_STRING : defaultMessage;
  }

  @Override
  public final String code() {
    return messageCode;
  }

  @Override
  public final String defaultMessage() {
    return defaultMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageCode other)) {
      return false;
    }
    return Objects.equals(messageCode, other.code()) && Objects.equals(defaultMessage, other.defaultMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageCode, defaultMessage);
  }
}
