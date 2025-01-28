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
import org.typefactory.LongType;
import org.typefactory.LongTypeParser;
import org.typefactory.MessageCode;

public final class ProductId extends LongType {

  @Serial
  private static final long serialVersionUID = 3869245051300150396L;

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.product.id", "must be a 16-digit product-id");

  private static final LongTypeParser TYPE_PARSER =
      LongTypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .minValueInclusive(1_000_000_000_000_000L)
          .maxValueInclusive(9_999_999_999_999_999L)
          .setRadixDecimal()
          .ignoreAllWhitespace()
          .ignoreAllDashesAndHyphens()
          .build();

  private ProductId(final Long value) {
    super(value);
  }

  public static ProductId of(final CharSequence value) {
    return TYPE_PARSER.parse(value, ProductId::new);
  }
}
