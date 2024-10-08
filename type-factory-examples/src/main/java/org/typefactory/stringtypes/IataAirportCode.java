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

public final class IataAirportCode extends StringType {

  public static final IataAirportCode EMPTY_IATA_AIRPORT_CODE = new IataAirportCode("");

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.iata.airport.code", "must be a 3-character IATA airport value");

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .acceptLettersAtoZ()
          .fixedSize(3)
          .toUpperCase()
          .convertNullToEmpty()
          .removeAllWhitespace()
          .build();

  private IataAirportCode(final String value) {
    super(value);
  }

  public static IataAirportCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, IataAirportCode::new);
  }
}
