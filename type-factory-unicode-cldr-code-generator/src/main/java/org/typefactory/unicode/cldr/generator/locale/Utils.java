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
package org.typefactory.unicode.cldr.generator.locale;

import com.ibm.icu.text.UnicodeSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import org.typefactory.unicode.cldr.generator.unicode.Error;
import org.typefactory.unicode.cldr.generator.unicode.UnicodeException;

public class Utils {

  private Utils() {
    // don't instantiate me
  }

  private static final Set<String> ISO_639_LANGUAGES = Set.of(Locale.getISOLanguages());

  static final Pattern UNICODE_HEX_AT_LINE_START = Pattern.compile("^0x[0-9a-fA-F]{6}\\|");

  static UnicodeSet loadUnicodeSetFromFile(final String characterSetFile) {
    try (final BufferedReader reader = new BufferedReader(
        new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("letters/" + characterSetFile)))) {

      final UnicodeSet unicodeSet = new UnicodeSet();
      reader.lines()
          .filter(line -> UNICODE_HEX_AT_LINE_START.matcher(line).find())
          .forEach(line -> unicodeSet.add(Integer.valueOf(line.substring(2, line.indexOf('|')), 16)));
      return unicodeSet.compact().freeze();
    } catch (final IOException e) {
      throw new UnicodeException(Error.CANNOT_LOAD_UNICODE_LETTERS_FROM_FILE_NAME, characterSetFile, e);
    }

  }

  public static boolean isIso639Language(final Locale locale) {
    try {
      // TODO: The ISO-639 list has the 2-letter languages. Should we also consider 3-letter languages?

      // We're using Java's Locale built-in ISO-639 locale knowledge to help us determine
      // whether we want to process this locale.
      final String language = locale.getLanguage();
      return !language.isEmpty() && ISO_639_LANGUAGES.contains(locale.getLanguage());

    } catch (final Exception e) {
      return false;
    }
  }


  public static String tokenize(final String locale) {
    return locale.replaceAll("\\W+", "_");
  }
}
