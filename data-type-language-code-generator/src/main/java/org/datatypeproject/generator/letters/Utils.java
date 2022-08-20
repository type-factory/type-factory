package org.datatypeproject.generator.letters;

import com.ibm.icu.text.UnicodeSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.datatypeproject.generator.unicodedata.Error;
import org.datatypeproject.generator.unicodedata.UnicodeException;

public class Utils {

  static UnicodeSet loadUnicodeSetFromFile(final String characterSetFile) {
    try (final BufferedReader reader = new BufferedReader(
        new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("letters/" + characterSetFile)))) {

      final Pattern UNICODE_HEX_AT_LINE_START = Pattern.compile("^0x[0-9a-fA-F]{6}\\|");
      final UnicodeSet unicodeSet = new UnicodeSet();
      reader.lines()
          .filter(line -> UNICODE_HEX_AT_LINE_START.matcher(line).find())
          .forEach(line -> unicodeSet.add(Integer.valueOf(line.substring(2, line.indexOf('|')), 16)));
      return unicodeSet.compact().freeze();
    } catch (final IOException e) {
      throw new UnicodeException(Error.CANNOT_LOAD_UNICODE_LETTERS_FROM_FILE_NAME, characterSetFile, e);
    }

  }

}
