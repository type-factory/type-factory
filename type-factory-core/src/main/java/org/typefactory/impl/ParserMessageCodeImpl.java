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

class ParserMessageCodeImpl extends MessageCodeImpl implements ParserMessageCode {

  @Serial
  private static final long serialVersionUID = -344851934396452075L;

  ParserMessageCodeImpl(final String messageCode, final String defaultMessage) {
    super(messageCode, defaultMessage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ParserMessageCode other)) {
      return false;
    }
    return Objects.equals(messageCode, other.code()) && Objects.equals(defaultMessage, other.defaultMessage());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  static class ParserMessageCodeArgKeys {

    private ParserMessageCodeArgKeys() {
      // don't instantiate me
    }

    public static final String MIN_LENGTH = "minLength";
    public static final String MAX_LENGTH = "maxLength";
    public static final String MIN_VALUE = "minValue";
    public static final String MAX_VALUE = "maxValue";
    public static final String ROUNDING_MODE = "roundingMode";
    public static final String INVALID_CHARACTER_DESCRIPTION = "invalidCharacterDescription";
    public static final String DECIMAL_SEPARATOR = "decimalSeparator";
    public static final String FRACTIONAL_VALUE = "fractionalValue";
    public static final String REGEX_PATTERN = "regexPattern";
  }

}
