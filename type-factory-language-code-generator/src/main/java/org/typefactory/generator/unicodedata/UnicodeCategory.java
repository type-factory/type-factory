package org.typefactory.generator.unicodedata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UnicodeCategory {

  /**
   * Lu – an uppercase letter
   */
  UPPERCASE_LETTER(Character.UPPERCASE_LETTER, "Lu", "Uppercase_Letter", "an uppercase letter"),

  /**
   * Ll – a lowercase letter
   */
  LOWERCASE_LETTER(Character.LOWERCASE_LETTER, "Ll", "Lowercase_Letter", "a lowercase letter"),

  /**
   * Lt – a digraphic character, with first part uppercase
   */
  TITLECASE_LETTER(Character.TITLECASE_LETTER, "Lt", "Titlecase_Letter", "a digraphic character, with first part uppercase"),

  /**
   * LC – Lu | Ll | Lt
   */
  CASED_LETTER("LC", "Cased_Letter", "Lu | Ll | Lt",
      new UnicodeCategory[]{UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER}),

  /**
   * Lm – a modifier letter
   */
  MODIFIER_LETTER(Character.MODIFIER_LETTER, "Lm", "Modifier_Letter", "a modifier letter"),

  /**
   * Lo – other letters, including syllables and ideographs
   */
  OTHER_LETTER(Character.OTHER_LETTER, "Lo", "Other_Letter", "other letters, including syllables and ideographs"),

  /**
   * L – Lu | Ll | Lt | Lm | Lo
   */
  LETTER("L", "Letter", "Lu | Ll | Lt | Lm | Lo",
      new UnicodeCategory[]{UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTER}),

  /**
   * Mn – a nonspacing combining mark (zero advance width)
   */
  NON_SPACING_MARK(Character.NON_SPACING_MARK, "Mn", "Nonspacing_Mark", "a nonspacing combining mark (zero advance width)"),

  /**
   * Mc – a spacing combining mark (positive advance width)
   */
  COMBINING_SPACING_MARK(Character.COMBINING_SPACING_MARK, "Mc", "Spacing_Mark", "a spacing combining mark (positive advance width)"),

  /**
   * Me – an enclosing combining mark
   */
  ENCLOSING_MARK(Character.ENCLOSING_MARK, "Me", "Enclosing_Mark", "an enclosing combining mark"),

  /**
   * M – Mn | Mc | Me
   */
  MARK("M", "Mark", "Mn | Mc | Me",
      new UnicodeCategory[]{NON_SPACING_MARK, COMBINING_SPACING_MARK, ENCLOSING_MARK}),

  /**
   * Nd – a decimal digit
   */
  DECIMAL_DIGIT_NUMBER(Character.DECIMAL_DIGIT_NUMBER, "Nd", "Decimal_Number", "a decimal digit"),

  /**
   * Nl – a letterlike numeric character
   */
  LETTER_NUMBER(Character.LETTER_NUMBER, "Nl", "Letter_Number", "a letterlike numeric character"),

  /**
   * No – a numeric character of other type
   */
  OTHER_NUMBER(Character.OTHER_NUMBER, "No", "Other_Number", "a numeric character of other type"),

  /**
   * N – Nd | Nl | No
   */
  NUMBER("N", "Number", "Nd | Nl | No",
      new UnicodeCategory[]{DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER}),

  /**
   * Pc – a connecting punctuation mark, like a tie
   */
  CONNECTOR_PUNCTUATION(Character.CONNECTOR_PUNCTUATION, "Pc", "Connector_Punctuation", "a connecting punctuation mark, like a tie"),

  /**
   * Pd – a dash or hyphen punctuation mark
   */
  DASH_PUNCTUATION(Character.DASH_PUNCTUATION, "Pd", "Dash_Punctuation", "a dash or hyphen punctuation mark"),

  /** Ps – an opening punctuation mark (of a pair) */
  OPEN_PUNCTUATION(Character.START_PUNCTUATION, "Ps", "Open_Punctuation",  "an opening punctuation mark (of a pair)"),

  /** Pe – a closing punctuation mark (of a pair) */
  CLOSE_PUNCTUATION(Character.END_PUNCTUATION, "Pe", "Close_Punctuation",  "a closing punctuation mark (of a pair)"),

  /**
   * Pi – an initial quotation mark
   */
  INITIAL_PUNCTUATION(Character.INITIAL_QUOTE_PUNCTUATION, "Pi", "Initial_Punctuation", "an initial quotation mark"),

  /**
   * Pf – a final quotation mark
   */
  FINAL_PUNCTUATION(Character.FINAL_QUOTE_PUNCTUATION, "Pf", "Final_Punctuation", "a final quotation mark"),

  /**
   * Po – a punctuation mark of other type
   */
  OTHER_PUNCTUATION(Character.OTHER_PUNCTUATION, "Po", "Other_Punctuation", "a punctuation mark of other type"),

  /**
   * P – Pc | Pd | Ps | Pe | Pi | Pf | Po
   */
  PUNCTUATION("P", "Punctuation", "Pc | Pd | Ps | Pe | Pi | Pf | Po",
      new UnicodeCategory[]{
          CONNECTOR_PUNCTUATION, DASH_PUNCTUATION,
          /*OPEN_PUNCTUATION, CLOSE_PUNCTUATION,*/
          INITIAL_PUNCTUATION, FINAL_PUNCTUATION, OTHER_PUNCTUATION}),
  /**
   * Sm – a symbol of mathematical use
   */
  MATH_SYMBOL(Character.MATH_SYMBOL, "Sm", "Math_Symbol", "a symbol of mathematical use"),

  /**
   * Sc – a currency sign
   */
  CURRENCY_SYMBOL(Character.CURRENCY_SYMBOL, "Sc", "Currency_Symbol", "a currency sign"),

  /**
   * Sk – a non-letterlike modifier symbol
   */
  MODIFIER_SYMBOL(Character.MODIFIER_SYMBOL, "Sk", "Modifier_Symbol", "a non-letterlike modifier symbol"),

  /**
   * So – a symbol of other type
   */
  OTHER_SYMBOL(Character.OTHER_SYMBOL, "So", "Other_Symbol", "a symbol of other type"),

  /**
   * S – Sm | Sc | Sk | So
   */
  SYMBOL("S", "Symbol", "Sm | Sc | Sk | So",
      new UnicodeCategory[]{MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL}),

  /**
   * Zs – a space character (of various non-zero widths)
   */
  SPACE_SEPARATOR(Character.SPACE_SEPARATOR, "Zs", "Space_Separator", "a space character (of various non-zero widths)"),

  /**
   * Zl – U+2028 LINE SEPARATOR only
   */
  LINE_SEPARATOR(Character.LINE_SEPARATOR, "Zl", "Line_Separator", "U+2028 LINE SEPARATOR only"),

  /**
   * Zp – U+2029 PARAGRAPH SEPARATOR only
   */
  PARAGRAPH_SEPARATOR(Character.PARAGRAPH_SEPARATOR, "Zp", "Paragraph_Separator", "U+2029 PARAGRAPH SEPARATOR only"),

  /**
   * Z – Zs | Zl | Zp
   */
  SEPARATOR("Z", "Separator", "Zs | Zl | Zp",
      new UnicodeCategory[]{SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR}),

  /**
   * Cc – a C0 or C1 control code
   */
  CONTROL(Character.CONTROL, "Cc", "Control", "a C0 or C1 control code"),

  /**
   * Cf – a format control character
   */
  FORMAT(Character.FORMAT, "Cf", "Format", "a format control character"),

  /**
   * Cs – a surrogate code point
   */
  SURROGATE(Character.SURROGATE, "Cs", "Surrogate", "a surrogate code point"),

  /**
   * Co – a private-use character
   */
  PRIVATE_USE(Character.PRIVATE_USE, "Co", "Private_Use", "a private-use character"),

  /**
   * Cn – a reserved unassigned code point or a noncharacter
   */
  UNASSIGNED(Character.UNASSIGNED, "Cn", "Unassigned", "a reserved unassigned code point or a noncharacter"),

  /**
   * C – Cc | Cf | Cs | Co | Cn
   */
  OTHER("C", "Other", "Cc | Cf | Cs | Co | Cn",
      new UnicodeCategory[]{CONTROL, FORMAT, SURROGATE, PRIVATE_USE, UNASSIGNED});

  private final int bitMask;
  private final int category;
  private final String abbreviation;
  private final String fullName;
  private final String description;
  private final UnicodeCategory[] compositeCategories;

  private static final Map<String, UnicodeCategory> index = new HashMap<>();

  static {
    Arrays.stream(UnicodeCategory.values())
        .forEach(unicodeCategory -> {
          index.put(unicodeCategory.name().toUpperCase(), unicodeCategory);
          index.put(unicodeCategory.getAbbreviation().toUpperCase(), unicodeCategory);
        });

  }

  public static UnicodeCategory of(final String nameOrAbbreviation) {
    if (nameOrAbbreviation == null || nameOrAbbreviation.isBlank()) {
      return null;
    }
    return index.get(nameOrAbbreviation.toUpperCase());
  }

  UnicodeCategory(
      final int category,
      final String abbreviation, final String fullName,
      final String description) {
    this.bitMask = 0x1 << category;
    this.category = category;
    this.abbreviation = abbreviation;
    this.fullName = fullName;
    this.description = description;
    this.compositeCategories = null;
  }

  UnicodeCategory(
      final String abbreviation, final String fullName,
      final String description, final UnicodeCategory[] compositeCategories) {
    this.abbreviation = abbreviation;
    this.category = -1;
    this.fullName = fullName;
    this.description = description;
    this.compositeCategories = compositeCategories;
    int bitMask = 0;
    for (UnicodeCategory unicodeCategory : compositeCategories) {
      bitMask |= unicodeCategory.bitMask;
    }
    this.bitMask = bitMask;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public String getFullName() {
    return fullName;
  }

  public String getDescription() {
    return description;
  }

  public boolean isComposite() {
    return compositeCategories != null && compositeCategories.length > 0;
  }

  public UnicodeCategory[] getCompositeCategories() {
    return compositeCategories;
  }
}
