/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.stringtypes;

import java.util.Locale;
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;
import org.typefactory.unicode.LocaleData;

public class PersonalName_en extends StringType {

  public static final MessageCode ERROR_MESSAGE = MessageCode.of(
      "invalid.personal.name.en",
      "must be made up of characters in the English alphabet, hyphens, apostrophes or spaces only.");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .messageCode(ERROR_MESSAGE)
      .minSize(1)
      .maxSize(60)
      .acceptSubset(LocaleData.getForLocale(Locale.ENGLISH).standardCharactersSubset())
      .acceptChars('\'', '-') // Accept U+0027 (apostrophe) and U+002D (hyphen-minus)
      .convertChar('’', '\'') // Convert U+2019 (right single quotation mark) to U+0027 (apostrophe) for system compatibility
      .convertChar('‐', '-')  // Convert U+2010 (hyphen) to U+002D (hyphen-minus) for system compatibility
      .convertChar('‑', '-')  // Convert U+2011 (non-breaking hyphen) to U+002D (hyphen-minus) for system compatibility
      .convertChar('–', '-')  // Convert U+2013 (en dash) to U+002D (hyphen-minus) for system compatibility
      .normalizeWhitespace()
      .convertEmptyToNull()
      .build();

  private PersonalName_en(final String value) {
    super(value);
  }

  public static PersonalName_en of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, PersonalName_en::new);
  }
}
