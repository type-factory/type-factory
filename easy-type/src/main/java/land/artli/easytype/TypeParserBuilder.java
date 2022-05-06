package land.artli.easytype;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import land.artli.easytype.CodePointConversions.CodePointConversionsBuilder;

public class TypeParserBuilder {

  private final Class<?> targetClass;
  private String errorMessage;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private int[] convertWhiteSpaceToCodePoints;
  private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
  private Normalizer.Form targetCharacterNormalizationForm = Form.NFC;
  private int minNumberOfCodePoints = 0;
  private int maxNumberOfCodePoints = 64;
  private boolean ignoreModifiersForMinMaxSize = false;
  private TargetCase targetCase = TargetCase.PRESERVE_CASE;
  private final RangedSubsetBuilder rangedSubsetBuilder = RangedSubset.builder();
  private final CodePointConversionsBuilder codePointConversionsBuilder = CodePointConversions.builder();
  private final List<TypeParserBuilder> logicalOr = new ArrayList<>();


  TypeParserBuilder(final Class<?> targetClass) {
    this.targetClass = targetClass;
  }

  public TypeParserBuilder errorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public TypeParserBuilder minSizeNumberOfCodePoints(final int min) {
    minNumberOfCodePoints = Math.max(min, 0);
    return this;
  }

  public TypeParserBuilder maxSizeNumberOfCodePoints(final int max) {
    maxNumberOfCodePoints = max;
    return this;
  }

  public TypeParserBuilder fixedSizeNumberOfCodePoints(final int size) {
    minSizeNumberOfCodePoints(size);
    maxSizeNumberOfCodePoints(size);
    return this;
  }

  /**
   * Configures the typeParser to ensure that both null and empty input values are preserved in the parsed result.
   * <p>
   * For example - assuming a valid parseable value of "ABC":
   * </p>
   * <pre>
   *   parseToString(null)                          ⟶ null
   *   parseToString("")                            ⟶ ""
   *   parseToString("ABC")                         ⟶ "ABC"
   *
   *   parseToStringType(null,  TargetClass::new)   ⟶ null
   *   parseToStringType("",    TargetClass::new)   ⟶ new TargetClass("")
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
   * </pre>
   *
   * @return this builder.
   * @see #convertEmptyToNull()
   * @see #convertNullToEmpty()
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder preserveNullAndEmpty() {
    nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
    return this;
  }

  /**
   * Configures the typeParser to ensure that both null and empty input values are converted to an empty value in the parsed result.
   * <p>
   * For example - assuming a valid parseable value of "ABC":
   * </p>
   * <pre>
   *   parseToString(null)                          ⟶ ""
   *   parseToString("")                            ⟶ ""
   *   parseToString("ABC")                         ⟶ "ABC"
   *
   *   parseToStringType(null,  TargetClass::new)   ⟶ new TargetClass("")
   *   parseToStringType("",    TargetClass::new)   ⟶ new TargetClass("")
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
   * </pre>
   *
   * @return this builder.
   * @see #preserveNullAndEmpty()
   * @see #convertEmptyToNull()
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder convertNullToEmpty() {
    nullHandling = NullHandling.CONVERT_NULL_TO_EMPTY;
    return this;
  }

  /**
   * Configures the typeParser to ensure that both null and empty input values are converted to a null value in the parsed result.
   * <p>
   * For example - assuming a valid parseable value of "ABC":
   * </p>
   * <pre>
   *   parseToString(null)                          ⟶ null
   *   parseToString("")                            ⟶ null
   *   parseToString("ABC")                         ⟶ "ABC"
   *
   *   parseToStringType(null,  TargetClass::new)   ⟶ null
   *   parseToStringType("",    TargetClass::new)   ⟶ null
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
   * </pre>
   *
   * @return this builder.
   * @see #preserveNullAndEmpty()
   * @see #convertNullToEmpty()
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder convertEmptyToNull() {
    nullHandling = NullHandling.CONVERT_EMPTY_TO_NULL;
    return this;
  }

  /**
   * <p>Configures the typeParser to ensure that letter-case is preserved.</p>
   *
   * @return this builder.
   */
  public TypeParserBuilder preserveCase() {
    targetCase = TargetCase.PRESERVE_CASE;
    return this;
  }

  /**
   * <p>Configures the typeParser to ensure that input values are converted to an upper-cased value in the parsed result.</p>
   *
   * <p>For example, the typeParser would parse the following values:</p>
   * <pre>
   *   parseToString("abc")                         ⟶ "ABC"
   *   parseToString("Abc")                         ⟶ "ABC"
   *   parseToString("AbC")                         ⟶ "ABC"
   *   parseToString("ABC")                         ⟶ "ABC"
   *
   *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("ABC")
   *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("ABC")
   *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("ABC")
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("ABC")
   * </pre>
   *
   * @return this builder.
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder toUpperCase() {
    targetCase = TargetCase.TO_UPPER_CASE;
    return this;
  }

  /**
   * <p>Configures the typeParser to ensure that input values are converted to a lower-cased value in the parsed result.</p>
   *
   * <p>For example, the typeParser would parse the following values:</p>
   * <pre>
   *   parseToString("abc")                         ⟶ "abc"
   *   parseToString("Abc")                         ⟶ "abc"
   *   parseToString("AbC")                         ⟶ "abc"
   *   parseToString("ABC")                         ⟶ "abc"
   *
   *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("abc")
   *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("abc")
   *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("abc")
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("abc")
   * </pre>
   *
   * @return this builder.
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder toLowerCase() {
    targetCase = TargetCase.TO_LOWER_CASE;
    return this;
  }

  /**
   * <p>Configures the typeParser to ensure that input values are converted to a title-cased value in the parsed result.</p>
   *
   * <p>For example, the typeParser would parse the following values:</p>
   * <pre>
   *   parseToString("abc")                         ⟶ "Abc"
   *   parseToString("Abc")                         ⟶ "Abc"
   *   parseToString("AbC")                         ⟶ "Abc"
   *   parseToString("ABC")                         ⟶ "Abc"
   *
   *   parseToStringType("abc", TargetClass::new)   ⟶ new TargetClass("Abc")
   *   parseToStringType("Abc", TargetClass::new)   ⟶ new TargetClass("Abc")
   *   parseToStringType("AbC", TargetClass::new)   ⟶ new TargetClass("Abc")
   *   parseToStringType("ABC", TargetClass::new)   ⟶ new TargetClass("Abc")
   * </pre>
   *
   * @return this builder.
   * @see TypeParser#parseToString(CharSequence)
   * @see TypeParser#parseToStringType(CharSequence, Function)
   */
  public TypeParserBuilder toTitleCase() {
    targetCase = TargetCase.TO_TITLE_CASE;
    return this;
  }

  public TypeParserBuilder preserveCharacterNormalizationForm() {
    targetCharacterNormalizationForm = null;
    return this;
  }

  public TypeParserBuilder toCharacterNormalizationFormNFC() {
    targetCharacterNormalizationForm = Form.NFC;
    return this;
  }

  public TypeParserBuilder toCharacterNormalizationFormNFD() {
    targetCharacterNormalizationForm = Form.NFD;
    return this;
  }

  public TypeParserBuilder toCharacterNormalizationFormNFKC() {
    targetCharacterNormalizationForm = Form.NFKC;
    return this;
  }

  public TypeParserBuilder toCharacterNormalizationFormNFKD() {
    targetCharacterNormalizationForm = Form.NFKD;
    return this;
  }

  public TypeParserBuilder acceptChar(final char ch) {
    rangedSubsetBuilder.includeChar(ch);
    return this;
  }

  public TypeParserBuilder acceptChars(final char... chars) {
    rangedSubsetBuilder.includeChars(chars);
    return this;
  }

  /**
   * Configures the type-parser to accept characters inclusively within the range defined by the arguments. It will automatically correct the range if
   * the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed, for example ('9', '0') instead of ('0', '9').
   *
   * @param inclusiveFrom the inclusive 'from' char.
   * @param inclusiveTo   the inclusive 'to' char.
   * @return this {@code TypeParserBuilder}.
   */
  public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
    rangedSubsetBuilder.includeCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  public TypeParserBuilder acceptCodePoint(final int codePoint) {
    rangedSubsetBuilder.includeCodePoint(codePoint);
    return this;
  }

  public TypeParserBuilder acceptCodePoints(final int... codePoints) {
    rangedSubsetBuilder.includeCodePoints(codePoints);
    return this;
  }

  /**
   * <p>Private method that will call {@link #acceptCodePoints(int...)} with all the code-points in the specified {@code charSequence}.</p>
   *
   * <p>It is a convenience method for:</p>
   * <pre>{@code
   *   acceptCodePoints(charSequence.codePoints().toArray())
   * }</pre>
   *
   * <p>This is a private method because I am not convinced there is an adequate use-case for a developer calling this when configuring a builder.
   * I think it would generally be better to be explicit about what you will accept by calling the existing public methods.</p>
   *
   * @param charSequence the character sequence containing the code-points you wish to accept.
   * @return this {@code TypeParserBuilder}.
   */
  private TypeParserBuilder acceptCodePointsInCharSequence(final CharSequence charSequence) {
    if (charSequence == null) {
      return this;
    }
    return acceptCodePoints(charSequence.codePoints().toArray());
  }

  /**
   * Configures the type-parser to accept characters inclusively within the range defined by the arguments. It will automatically correct the range if
   * the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed, for example ('9', '0') instead of ('0', '9').
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return this {@code TypeParserBuilder}.
   */
  public TypeParserBuilder acceptCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    rangedSubsetBuilder.includeCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  public TypeParserBuilder convertChar(final char fromChar, final char toChar) {
    codePointConversionsBuilder.addCharConversion(fromChar, toChar);
    return this;
  }

  public TypeParserBuilder convertChar(final char fromChar, final CharSequence toCharSequence) {
    codePointConversionsBuilder.addCharConversion(fromChar, toCharSequence);
    return this;
  }


  public TypeParserBuilder convertCodePoint(final int fromCodePoint, final int toCodePoint) {
    codePointConversionsBuilder.addCodePointConversion(fromCodePoint, toCodePoint);
    return this;
  }

  public TypeParserBuilder convertCodePoint(final int fromCodePoint, final CharSequence toCharSequence) {
    codePointConversionsBuilder.addCodePointConversion(fromCodePoint, toCharSequence);
    return this;
  }

  public TypeParserBuilder acceptLanguage(final Language language) {
    rangedSubsetBuilder.includeSubset(language);
    return this;
  }

  public TypeParserBuilder acceptLanguages(final Language... languages) {
    rangedSubsetBuilder.includeSubset(languages);
    return this;
  }

  public TypeParserBuilder acceptUnicodeCategory(final Category category) {
    rangedSubsetBuilder.includeUnicodeCategory(category);
    return this;
  }

  public TypeParserBuilder acceptUnicodeCategory(final Category... categories) {
    rangedSubsetBuilder.includeUnicodeCategories(categories);
    return this;
  }

  public TypeParserBuilder acceptAllUnicodeLetters() {
    return acceptUnicodeCategory(Category.LETTER);
  }

  /**
   * Configures the type parser to accepts all letters [a-zA-Z] that are in the ASCII portion of the Unicode character set.
   * <p>
   * If you wish for the letters to be converted to upper or lower case then remember to configure the {@link TypeParser} by calling {@link
   * #toLowerCase()} or {@link #toUpperCase()} in the {@link TypeParserBuilder}
   * <p>
   * If you want to accept <em>all</em> letters in the Unicode set (this could mean mixed-languages) then use: {@link #acceptAllUnicodeLetters()}
   * <p>
   * This method is a convenience method that invokes {@code acceptCharRange('a', 'z').acceptCharRange('A', 'Z');} on this builder.
   *
   * @return this {@code TypeParserBuilder}
   * @see #acceptUnicodeCategory(Category)
   * @see Category#LETTER
   * @see #toLowerCase()
   * @see #toUpperCase()
   */
  public TypeParserBuilder acceptLettersAtoZ() {
    return acceptCharRange('a', 'z')
        .acceptCharRange('A', 'Z');
  }

  /**
   * Configures the type parser to accepts all digits number '0' through '9' that were originally in the ASCII range.
   * <p>
   * If you want to accept all decimal digits in another language range then use the following:
   * <pre>
   *   acceptCharRange('०', '९') // Devanagari decimal digit range
   *   acceptCharRange('൦', '൯') // Malayalam decimal digit range
   * </pre>
   * <p>
   * If you want to accept <em>all</em> decimal digits in the Unicode set (and I don't think you do) then use the following:
   * <pre>
   *   acceptUnicodeCategory(Category.DECIMAL_DIGIT_NUMBER)
   * </pre>
   *
   * @return this {@code TypeParserBuilder}
   * @see #acceptUnicodeCategory(Category)
   * @see Category#DECIMAL_DIGIT_NUMBER
   */
  public TypeParserBuilder acceptDigits0to9() {
    return acceptCharRange('0', '9');
  }

  /**
   * Accepts all a dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category.
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   */
  public TypeParserBuilder acceptAllDashes() {
    acceptUnicodeCategory(Category.DASH_PUNCTUATION);
    return this;
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the {@code '-' (U+002D)} hyphen character defined in the Unicode
   * <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin</a> block. This is also the ordinary ASCII hyphen.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * the {@code '-' (U+002D)} hyphen character. These other kinds of dashes and hyphens are most often encountered when data originates from some kind
   * of formatted or “corrected” source. For example, many user interfaces will auto-correct a double-dash to an en-dash.</p>
   *
   * <p>This method implicitly configures the builder to accept the {@code '-' (U+002D)} hyphen character.</p>
   *
   * <p><b>Note:</b> This conversion might make sense for certain data-types and not others. For example, in language specific data-types you may
   * may wish to preserve language specific dashes. For example, for an Armenian data-type, perhaps preserving the Armenian Hyphen {@code '֊'
   * (U+058A)} makes more sense than converting it to a hyphen.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
   */
  public TypeParserBuilder convertAllDashesToHyphen() {
    return convertAllDashesTo('-');
  }

  /**
   * <p>Synonym for {@link #convertAllDashesToHyphen()}.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see #convertAllDashesToHyphen()
   */
  public TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen() {
    return convertAllDashesToHyphen();
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toChar} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character {@code '_'
   * (U+005F)}.</p>
   *
   * <p>This method implicitly configures the builder to accept the specified {@code toChar} argument.</p>
   *
   * @param toChar the character that you wish to convert any dash or hyphen punctuation mark characters to.
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
   */
  public TypeParserBuilder convertAllDashesTo(final char toChar) {
    return convertAllDashesTo((int) toChar);
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toCodePoint} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character {@code '_'
   * (U+005F)}.</p>
   *
   * <p>This method implicitly configures the builder to accept the specified {@code toCodePoint} argument.</p>
   *
   * @param toCodePoint the code-point that you wish to convert any dash or hyphen punctuation mark characters to.
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
   */
  public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
    acceptCodePoint(toCodePoint);
    codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCodePoint);
    return this;
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toCharSequence} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some char-sequence of your liking. For example, you may wish to convert any dash or hyphen character to the sequence {@code '~~~' (U+007E,
   * U+007E, U+007E)}.</p>
   *
   * <p>This method implicitly configures the builder to accept the all code-points in the specified {@code toCharSequence} argument.</p>
   *
   * <p>This method exists because conversions in some languages will require a sequence of characters if the {@code toCharSequence}
   * is comprised of a letter followed by letter-modifiers. It may look on the screen to be a single character, but is actually a sequence.</p>
   *
   * @param toCharSequence the code-point that you wish to convert any dash or hyphen punctuation mark characters to.
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
   */
  public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
    acceptCodePointsInCharSequence(toCharSequence);
    codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCharSequence);
    return this;
  }

  /**
   * <p>Removes any character or code-point that is designated to be whitespace as per the {@link Character#isWhitespace(int)} method.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder removeAllWhitespace() {
    whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
    return this;
  }

  /**
   * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with a single space {' ' (U+0020)}
   * character.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder normalizeWhitespace() {
    whiteSpace = WhiteSpace.NORMALIZE_WHITESPACE;
    return this;
  }

  /**
   * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
   * {@code toChar} character.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * @param toChar the character that you wish to convert any contiguous blocks of whitespace to.
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toChar};
    return this;
  }

  /**
   * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
   * {@code toCodePoint} character.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * @param toCodePoint the code-point that you wish to convert any contiguous blocks of whitespace to.
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
    return this;
  }

  /**
   * <p>Removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with the single specified
   * {@code toCodePoint} character.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * <p>This method exists because conversions in some languages will require a sequence of characters if the {@code toCharSequence}
   * is comprised of a letter followed by letter-modifiers. It may look on the screen to be a single character, but is actually a sequence.</p>
   *
   * @param toCharSequence the char-sequence that you wish to convert any contiguous blocks of whitespace to.
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = toCharSequence == null ? null : toCharSequence.codePoints().toArray();
    return this;
  }

  /**
   * <p>All leading, trailing, and interspersed whitespace will be preserved.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder preserveWhitespace() {
    whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
    return this;
  }

  /**
   * <p>All leading, trailing, and interspersed whitespace will be converted to the specified {@code toChar} character.</p>
   *
   * <p>This method implicitly configures the builder to the specified {@code toChar} character.</p>
   *
   * <p>Whitespace is determined using the {@link Character#isWhitespace(int)} method.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
    acceptChar(toChar);
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toChar};
    return this;
  }

  public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
    acceptCodePoint(toCodePoint);
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
    return this;
  }

  public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    acceptCodePointsInCharSequence(toCharSequence);
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = toCharSequence == null ? null : toCharSequence.codePoints().toArray();
    return this;
  }

  public TypeParserImpl build() {
    return new TypeParserImpl(
        targetClass, errorMessage, targetCase,
        whiteSpace, convertWhiteSpaceToCodePoints,
        nullHandling,
        targetCharacterNormalizationForm,
        minNumberOfCodePoints, maxNumberOfCodePoints,
        rangedSubsetBuilder.build(),
        codePointConversionsBuilder.build());
  }
}
