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
import org.typefactory.TypeParser;

public record CountryCode(String value) implements CharSequenceType<CountryCode> {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 2-character ISO 3166-1 alpha country code")
          .convertNullToEmpty()
          .removeAllWhitespace()
          .acceptLettersAtoZ()
          .fixedSize(2)
          .toUpperCase()
          .build();

  public CountryCode(final String value) {
    this.value = TYPE_PARSER.parseToString(value);
  }

  public static final CountryCode EMPTY_COUNTRY_CODE = new CountryCode("");

  @Override
  public String toString() {
    return value();
  }

}
