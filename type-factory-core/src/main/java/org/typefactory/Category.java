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
package org.typefactory;

import java.util.Arrays;

/**
 * Provides typesafe versions of the Unicode character categories defined in the {@link Character Character} class for use with the
 * {@link TypeParser.TypeParserBuilder}. The {@link TypeParser} will use a category bit-flags for improved performance.
 *
 * @see Character#COMBINING_SPACING_MARK
 * @see Character#CONNECTOR_PUNCTUATION
 * @see Character#CONTROL
 * @see Character#CURRENCY_SYMBOL
 * @see Character#DASH_PUNCTUATION
 * @see Character#DECIMAL_DIGIT_NUMBER
 * @see Character#ENCLOSING_MARK
 * @see Character#END_PUNCTUATION
 * @see Character#FINAL_QUOTE_PUNCTUATION
 * @see Character#FORMAT
 * @see Character#INITIAL_QUOTE_PUNCTUATION
 * @see Character#LETTER_NUMBER
 * @see Character#LINE_SEPARATOR
 * @see Character#LOWERCASE_LETTER
 * @see Character#MATH_SYMBOL
 * @see Character#MODIFIER_LETTER
 * @see Character#MODIFIER_SYMBOL
 * @see Character#NON_SPACING_MARK
 * @see Character#OTHER_LETTER
 * @see Character#OTHER_NUMBER
 * @see Character#OTHER_PUNCTUATION
 * @see Character#OTHER_SYMBOL
 * @see Character#PARAGRAPH_SEPARATOR
 * @see Character#PRIVATE_USE
 * @see Character#SPACE_SEPARATOR
 * @see Character#START_PUNCTUATION
 * @see Character#SURROGATE
 * @see Character#TITLECASE_LETTER
 * @see Character#UNASSIGNED
 * @see Character#UPPERCASE_LETTER
 */
public enum Category {

  /**
   * Lu, Uppercase_Letter – an uppercase letter.
   *
   * @see Character#UPPERCASE_LETTER
   */
  UPPERCASE_LETTER(Character.UPPERCASE_LETTER, "Lu", "Uppercase_Letter"),

  /**
   * Ll, Lowercase_Letter – a lowercase letter.
   *
   * @see Character#LOWERCASE_LETTER
   */
  LOWERCASE_LETTER(Character.LOWERCASE_LETTER, "Ll", "Lowercase_Letter"),

  /**
   * Lt, Titlecase_Letter – a digraphic character, with first part uppercase.
   *
   * @see Character#TITLECASE_LETTER
   */
  TITLECASE_LETTER(Character.TITLECASE_LETTER, "Lt", "Titlecase_Letter"),

  /**
   * LC, Cased_Letter – Lu | Ll | Lt.
   */
  CASED_LETTER("LC", "Cased_Letter", UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER),

  /**
   * Lm, Modifier_Letter – a modifier letter.
   *
   * @see Character#MODIFIER_LETTER
   */
  MODIFIER_LETTER(Character.MODIFIER_LETTER, "Lm", "Modifier_Letter"),

  /**
   * Lo, Other_Letter – other letters, including syllables and ideographs.
   *
   * @see Character#OTHER_LETTER
   */
  OTHER_LETTER(Character.OTHER_LETTER, "Lo", "Other_Letter"),

  /**
   * L, Letter – Lu | Ll | Lt | Lm | Lo.
   */
  LETTER("L", "Letter", UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER,
      MODIFIER_LETTER, OTHER_LETTER),

  /**
   * Mn, Nonspacing_Mark – a nonspacing combining mark (zero advance width).
   *
   * @see Character#NON_SPACING_MARK
   */
  NON_SPACING_MARK(Character.NON_SPACING_MARK, "Mn", "Nonspacing_Mark"),

  /**
   * Mc, Spacing_Mark – a spacing combining mark (positive advance width).
   *
   * @see Character#COMBINING_SPACING_MARK
   */
  COMBINING_SPACING_MARK(Character.COMBINING_SPACING_MARK, "Mc", "Spacing_Mark"),

  /**
   * Me, Enclosing_Mark – an enclosing combining mark.
   *
   * @see Character#ENCLOSING_MARK
   */
  ENCLOSING_MARK(Character.ENCLOSING_MARK, "Me", "Enclosing_Mark"),

  /**
   * M, Mark – Mn | Mc | Me.
   */
  MARK("M", "Mark", NON_SPACING_MARK, COMBINING_SPACING_MARK, ENCLOSING_MARK),

  /**
   * Nd, Decimal_Number – a decimal digit.
   *
   * @see Character#DECIMAL_DIGIT_NUMBER
   */
  DECIMAL_DIGIT_NUMBER(Character.DECIMAL_DIGIT_NUMBER, "Nd", "Decimal_Number"),

  /**
   * Nl, Letter_Number – a letterlike numeric character.
   *
   * @see Character#LETTER_NUMBER
   */
  LETTER_NUMBER(Character.LETTER_NUMBER, "Nl", "Letter_Number"),

  /**
   * No, Other_Number – a numeric character of other type.
   *
   * @see Character#OTHER_NUMBER
   */
  OTHER_NUMBER(Character.OTHER_NUMBER, "No", "Other_Number"),

  /**
   * N, Number – Nd | Nl | No.
   */
  NUMBER("N", "Number", DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER),

  /**
   * Pc, Connector_Punctuation – a connecting punctuation mark, like a tie.
   *
   * @see Character#CONNECTOR_PUNCTUATION
   */
  CONNECTOR_PUNCTUATION(Character.CONNECTOR_PUNCTUATION, "Pc", "Connector_Punctuation"),

  /**
   * Pd, Dash_Punctuation – a dash or hyphen punctuation mark.
   *
   * @see Character#DASH_PUNCTUATION
   */
  DASH_PUNCTUATION(Character.DASH_PUNCTUATION, "Pd", "Dash_Punctuation"),

  /**
   * Ps, Open_Punctuation – an opening punctuation mark (of a pair).
   *
   * @see Character#START_PUNCTUATION
   */
  START_PUNCTUATION(Character.START_PUNCTUATION, "Ps", "Start_Punctuation"),

  /**
   * Pe, Close_Punctuation – a closing punctuation mark (of a pair).
   *
   * @see Character#END_PUNCTUATION
   */
  END_PUNCTUATION(Character.END_PUNCTUATION, "Pe", "End_Punctuation"),

  /**
   * Pi, Initial_Punctuation – an initial quotation mark.
   *
   * @see Character#INITIAL_QUOTE_PUNCTUATION
   */
  INITIAL_QUOTE_PUNCTUATION(Character.INITIAL_QUOTE_PUNCTUATION, "Pi", "Initial_Punctuation"),

  /**
   * Pf, Final_Punctuation – a final quotation mark.
   *
   * @see Character#FINAL_QUOTE_PUNCTUATION
   */
  FINAL_QUOTE_PUNCTUATION(Character.FINAL_QUOTE_PUNCTUATION, "Pf", "Final_Punctuation"),

  /**
   * Po, Other_Punctuation – a punctuation mark of other type.
   *
   * @see Character#OTHER_PUNCTUATION
   */
  OTHER_PUNCTUATION(Character.OTHER_PUNCTUATION, "Po", "Other_Punctuation"),

  /**
   * P, Punctuation – Pc | Pd | Ps | Pe | Pi | Pf | Po.
   */
  PUNCTUATION("P", "Punctuation",
      CONNECTOR_PUNCTUATION, DASH_PUNCTUATION,
      START_PUNCTUATION, END_PUNCTUATION,
      INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION,
      OTHER_PUNCTUATION),

  /**
   * Sm, Math_Symbol – a symbol of mathematical use.
   *
   * @see Character#MATH_SYMBOL
   */
  MATH_SYMBOL(Character.MATH_SYMBOL, "Sm", "Math_Symbol"),

  /**
   * Sc, Currency_Symbol – a currency sign.
   *
   * @see Character#CURRENCY_SYMBOL
   */
  CURRENCY_SYMBOL(Character.CURRENCY_SYMBOL, "Sc", "Currency_Symbol"),

  /**
   * Sk, Modifier_Symbol – a non-letterlike modifier symbol.
   *
   * @see Character#MODIFIER_SYMBOL
   */
  MODIFIER_SYMBOL(Character.MODIFIER_SYMBOL, "Sk", "Modifier_Symbol"),

  /**
   * So, Other_Symbol – a symbol of other type.
   *
   * @see Character#OTHER_SYMBOL
   */
  OTHER_SYMBOL(Character.OTHER_SYMBOL, "So", "Other_Symbol"),

  /**
   * S, Symbol – Sm | Sc | Sk | So.
   */
  SYMBOL("S", "Symbol", MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL),

  /**
   * Zs, Space_Separator – a space character (of various non-zero widths).
   *
   * @see Character#SPACE_SEPARATOR
   */
  SPACE_SEPARATOR(Character.SPACE_SEPARATOR, "Zs", "Space_Separator"),

  /**
   * Zl, Line_Separator – U+2028 LINE SEPARATOR only.
   *
   * @see Character#LINE_SEPARATOR
   */
  LINE_SEPARATOR(Character.LINE_SEPARATOR, "Zl", "Line_Separator"),

  /**
   * Zp, Paragraph_Separator – U+2029 PARAGRAPH SEPARATOR only.
   *
   * @see Character#PARAGRAPH_SEPARATOR
   */
  PARAGRAPH_SEPARATOR(Character.PARAGRAPH_SEPARATOR, "Zp", "Paragraph_Separator"),

  /**
   * Z, Separator – Zs | Zl | Zp.
   */
  SEPARATOR("Z", "Separator", SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR),

  /**
   * Cc, Control – a C0 or C1 control code.
   *
   * @see Character#CONTROL
   */
  CONTROL(Character.CONTROL, "Cc", "Control"),

  /**
   * Cf, Format – a format control character.
   *
   * @see Character#FORMAT
   */
  FORMAT(Character.FORMAT, "Cf", "Format"),

  /**
   * Cs, Surrogate – a surrogate code point.
   *
   * @see Character#SURROGATE
   */
  SURROGATE(Character.SURROGATE, "Cs", "Surrogate"),

  /**
   * Co, Private_Use – a private-use character.
   *
   * @see Character#PRIVATE_USE
   */
  PRIVATE_USE(Character.PRIVATE_USE, "Co", "Private_Use"),

  /**
   * Cn, Unassigned – a reserved unassigned code point or a noncharacter.
   *
   * @see Character#UNASSIGNED
   */
  UNASSIGNED(Character.UNASSIGNED, "Cn", "Unassigned"),

  /**
   * C, Other – Cc | Cf | Cs | Co | Cn.
   */
  OTHER("C", "Other", CONTROL, FORMAT, SURROGATE, PRIVATE_USE, UNASSIGNED);

  final int[] characterCategories;
  final String abbreviation;
  final String alias;
  public final long bitMask;

  Category(final int characterCategory, final String abbreviation, final String alias) {
    this.characterCategories = new int[]{characterCategory};
    this.abbreviation = abbreviation;
    this.alias = alias;
    this.bitMask = 0x1 << characterCategory; // characterCategory values start from zero.
  }

  Category(final String abbreviation, final String alias, final Category... categories) {
    this.abbreviation = abbreviation;
    this.alias = alias;
    final int categoryCount = Arrays.stream(categories)
        .mapToInt(category -> category.characterCategories.length)
        .sum();
    this.characterCategories = new int[categoryCount];
    long tempBitMask = 0;
    int i = 0;
    for (Category category : categories) {
      tempBitMask |= category.bitMask;
      for (int characterCategory : category.characterCategories) {
        this.characterCategories[i++] = characterCategory;
      }
    }
    this.bitMask = tempBitMask;
  }

  public int[] getCharacterCategories() {
    return Arrays.copyOf(characterCategories, characterCategories.length);
  }

  /**
   * <p>Returns the category bit flags in a {@code long} value where each bit represents one Unicode Category.</p>
   * @param categories the categories to get the bit flags for.
   * @return the category bit flags.
   * @see #codePointIsInOneOfTheCategories(int, long)
   */
  public static long getCategoryBitFlags(final Category... categories) {
    long categoryBitFlags = 0;
    for (Category category : categories) {
      categoryBitFlags |= category.bitMask;
    }
    return categoryBitFlags;
  }

  /**
   * <p>Checks if the specified code point is in one of the Unicode categories specified with the {@code categoryBitFlags}.</p>
   * @param codePoint the code point to check.
   * @param categoryBitFlags the category bit flags to check.
   * @return {@code true} if the code point is in one of the Unicode categories specified with the {@code categoryBitFlags}.
   * @see #getCategoryBitFlags(Category...)
   */
  public static boolean codePointIsInOneOfTheCategories(final int codePoint, final long categoryBitFlags) {
    return (categoryBitFlags & (0x1L << Character.getType(codePoint))) > 0;
  }
}
