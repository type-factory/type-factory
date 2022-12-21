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
package org.typefactory.recordtypes;

import org.typefactory.CharSequenceType;
import org.typefactory.MessageCode;
import org.typefactory.TypeParser;

public record CurrencyCode(String value) implements CharSequenceType<CurrencyCode> {

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.currency.code", "must be a 3-character ISO 4217 alpha currency code");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .removeAllWhitespace()
          .convertNullToEmpty()
          .acceptLettersAtoZ()
          .fixedSize(3)
          .toUpperCase()
          .build();

  public CurrencyCode(String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  @Override
  public String toString() {
    return value();
  }

}
