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

import org.typefactory.ShortType;
import org.typefactory.TypeParser;

public final class DepartmentId extends ShortType {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 4-digit number")
          .removeAllWhitespace()
          .acceptDigits0to9()
          .fixedSize(4)
          .build();

  private DepartmentId(final Short value) {
    super(value);
  }

  public static DepartmentId of(final CharSequence value) {
    return TYPE_PARSER.parseToShortType(value, DepartmentId::new);
  }
}