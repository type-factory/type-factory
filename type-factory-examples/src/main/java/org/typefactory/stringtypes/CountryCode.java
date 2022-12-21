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
package org.typefactory.stringtypes;

import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class CountryCode extends StringType {

  public static final CountryCode EMPTY_COUNTRY_CODE = new CountryCode("");

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.country.code", "must be a 2-character ISO 3166-1 alpha country code");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .acceptLettersAtoZ()
          .fixedSize(2)
          .toUpperCase()
          .preserveNullAndEmpty()
          .removeAllWhitespace()
          .build();

  private CountryCode(final String value) {
    super(value);
  }

  public static CountryCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, CountryCode::new);
  }
}
