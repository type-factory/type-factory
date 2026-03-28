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

import java.io.Serial;
import java.util.Objects;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.MessageCode;

class ParserMessageCodeImpl extends MessageCodeImpl implements ParserMessageCode {

  @Serial
  private static final long serialVersionUID = -344851934396452075L;

  ParserMessageCodeImpl(final String code, final String defaultMessage) {
    super(code, defaultMessage);
  }

  /**
   * <p>Returns {@code true} if the message code instances are both instances
   * of {@code ParserMessageCode} and are equal by code – that is,
   * when {@code this.}{@link #code()} is equal to {@code other.}{@link #code()}.</p>
   *
   * @return {@code true} if {@code this.}{@link #code()} is equal to {@code other.}{@link #code()}, otherwise returns {@code false}.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof ParserMessageCode parserMessageCode) {
      return equals(parserMessageCode);
    }
    return false;
  }

  /**
   * <p>Returns {@code true} if the message code instances are both instances
   * of {@code ParserMessageCode} and are equal by code – that is,
   * when {@code this.}{@link #code()} is equal to {@code other.}{@link #code()}.</p>
   *
   * @return {@code true} if {@code this.}{@link #code()} is equal to {@code other.}{@link #code()}, otherwise returns {@code false}.
   */
  @Override
  public boolean equals(MessageCode other) {
    if (other instanceof ParserMessageCode parserMessageCode) {
      return equals(parserMessageCode);
    }
    return false;
  }

  /**
   * <p>Returns {@code true} if the parser message code instances are equal by code – that is,
   * when {@code this.}{@link #code()} is equal to {@code this.}{@link #code()}.</p>
   *
   * @return {@code true} if {@code this.}{@link #code()} is equal to {@code other.}{@link #code()}, otherwise returns {@code false}.
   */
  public boolean equals(final ParserMessageCode other) {
    return other != null && Objects.equals(code, other.code());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
