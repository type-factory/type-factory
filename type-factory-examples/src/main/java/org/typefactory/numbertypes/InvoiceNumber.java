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
package org.typefactory.numbertypes;

import java.io.Serial;
import org.typefactory.IntegerType;
import org.typefactory.IntegerTypeParser;
import org.typefactory.MessageCode;

public final class InvoiceNumber extends IntegerType {

  @Serial
  private static final long serialVersionUID = 8672243696039324825L;

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.invoice.number", "must be a 9-digit invoice-number");

  private static final IntegerTypeParser TYPE_PARSER =
      IntegerTypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .minValueInclusive(100_000_000)
          .maxValueInclusive(999_999_999)
          .allowBase10Numbers()
          .ignoreAllWhitespace()
          .ignoreAllOccurrencesOfChar('-')
          .build();

  private InvoiceNumber(final Integer value) {
    super(value);
  }

  public static InvoiceNumber of(final CharSequence value) {
    return TYPE_PARSER.parse(value, InvoiceNumber::new);
  }
}
