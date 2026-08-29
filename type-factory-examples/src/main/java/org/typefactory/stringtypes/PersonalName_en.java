package org.typefactory.stringtypes;

import java.util.Locale;
import org.typefactory.LocaleData;
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

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
