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
import org.typefactory.MessageCode;
import org.typefactory.ShortType;
import org.typefactory.ShortTypeParser;

public final class DepartmentId extends ShortType {

  @Serial
  private static final long serialVersionUID = -7190016498116228650L;

  private static final MessageCode ERROR_MESSAGE =
      MessageCode.of("invalid.department.id", "must be a 4-digit department-id");

  private static final ShortTypeParser TYPE_PARSER =
      ShortTypeParser.builder()
          .messageCode(ERROR_MESSAGE)
          .minValueInclusive(1000)
          .maxValueInclusive(9999)
          .allowBase10Numbers()
          .ignoreAllWhitespace()
          .build();

  private DepartmentId(final Short value) {
    super(value);
  }

  public static DepartmentId of(final CharSequence value) {
    return TYPE_PARSER.parse(value, DepartmentId::new);
  }
}
