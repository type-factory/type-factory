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

import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class CurrencyCode extends StringType {

  public static final CurrencyCode EMPTY_CURRENCY_CODE = new CurrencyCode("");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .errorMessage("must be a 3-character ISO 4217 alpha currency code")
      .acceptCharRange('a', 'z')
      .acceptCharRange('A', 'Z')
      .fixedSize(3)
      .removeAllWhitespace()
      .preserveNullAndEmpty()
      .toUpperCase()
      .build();

  private CurrencyCode(final String value) {
    super(value);
  }

  public static CurrencyCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, CurrencyCode::new);
  }

}
