package org.datatypeproject.impl;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.datatypeproject.Category;
import org.datatypeproject.Subset;
import org.datatypeproject.Subset.SubsetBuilder;
import org.datatypeproject.TypeParser;
import org.datatypeproject.TypeParser.TypeParserBuilder;

class TypeParserBuilderImpl implements TypeParserBuilder {

  private Class<?> targetTypeClass;
  private String errorMessage;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
  private Normalizer.Form targetCharacterNormalizationForm = Form.NFC;
  private int minNumberOfCodePoints = 0;
  private int maxNumberOfCodePoints = 64;
  private boolean ignoreModifiersForMinMaxSize = false;
  private TargetCase targetCase = TargetCase.PRESERVE_CASE;

  private Pattern regex = null;

  private Function<String, Boolean> validationFunction = null;
  private final SubsetBuilder rangedSubsetBuilder = Subset.builder();
  private final ConverterBuilder converterBuilder = Converter.builder();

  private final List<TypeParserBuilder> logicalOr = new ArrayList<>();


  TypeParserBuilderImpl() {
  }

  public TypeParserBuilder targetTypeClass(final Class<?> targetTypeClass) {
    this.targetTypeClass = targetTypeClass;
    return this;
  }

  public TypeParserBuilder errorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * <p>The minimum number of Unicode characters (code-points) that the parsed value must contain. </p>
   *
   * <p><b>Note</b>, the {@code minSize} refers to the minimum number of actual Unicode characters (code-points). For Unicode characters in the
   * range:</p>
   * <ul>
   *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all
   *   modern day languages.</li>
   *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
   *   symbols and emoticons.</li>
   * </ul>
   *
   * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
   * blocks in the U+010000..U+10FFFF codepoint range.</p>
   *
   * @param minSize the minimum number of Unicode characters (code-points) that the parsed value must contain.
   * @return this builder.
   * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
   */
  @Override
  public TypeParserBuilder minSize(final int minSize) {
    minNumberOfCodePoints = Math.max(minSize, 0);
    return this;
  }

  /**
   * <p>The maximum number of Unicode characters (code-points) that the parsed value must contain. </p>
   *
   * <p><b>Note</b>, the {@code maxSize} refers to the maximum number of actual Unicode characters (code-points). For Unicode characters in the
   * range:</p>
   * <ul>
   *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all modern
   *   day languages.</li>
   *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
   *   symbols and emoticons.</li>
   * </ul>
   *
   * <p>So be aware that if you create a data-type with {@code maxSize} of, say, 10 and allow for Unicode characters (like emoticons) in
   * the {@code U+010000..U+10FFFF} range that you may end up with a parsed string whose {@link String#length()} is greater than 10.</p>
   *
   * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
   * blocks in the U+010000..U+10FFFF codepoint range.</p>
   *
   * @param maxSize the maximum number of Unicode characters (code-points) that the parsed value must contain.
   * @return this builder.
   * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
   */
  @Override
  public TypeParserBuilder maxSize(final int maxSize) {
    maxNumberOfCodePoints = maxSize;
    return this;
  }

  /**
   * <p>The fixed number of Unicode characters (code-points) that the parsed value must contain.</p>
   *
   * <p>This is a convenience method that simply calls both the {@link #minSize(int)} and {@link #maxSize(int)} methods
   * passing the {@code size} argument to both.</p>
   *
   * <p><b>Note</b>, the {@code maxSize} refers to the maximum number of actual Unicode characters (code-points). For Unicode characters in the
   * range:</p>
   * <ul>
   *   <li>{@code U+0000..U+FFFF} — each Unicode code-point will fit into a single Java {@code char}. This range includes almost all modern
   *   day languages.</li>
   *   <li>{@code U+010000..U+10FFFF} – each Unicode code-point will require two Java {@code char}s. This includes many archaic languages,
   *   symbols and emoticons.</li>
   * </ul>
   *
   * <p>So be aware that if you create a data-type with fixed {@code size} of, say, 10 and allow for Unicode characters (like emoticons) in
   * the {@code U+010000..U+10FFFF} range that you may end up with a parsed string whose {@link String#length()} is greater than 10.</p>
   *
   * <p>See the <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a> reference for a full list of the
   * blocks in the U+010000..U+10FFFF codepoint range.</p>
   *
   *
   * @param size the fixed number of Unicode characters (code-points) that the parsed value must contain.
   * @return this builder.
   * @see <a href='https://www.unicode.org/Public/UCD/latest/ucd/Blocks.txt'>Unicode Consortium blocks</a>
   */
  @Override
  public TypeParserBuilder fixedSize(final int size) {
    minSize(size);
    maxSize(size);
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
  @Override
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
  @Override
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
  @Override
  public TypeParserBuilder convertEmptyToNull() {
    nullHandling = NullHandling.CONVERT_EMPTY_TO_NULL;
    return this;
  }

  /**
   * <p>Configures the typeParser to ensure that letter-case is preserved.</p>
   *
   * @return this builder.
   */
  @Override
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
  @Override
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
  @Override
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
  @Override
  public TypeParserBuilder toTitleCase() {
    targetCase = TargetCase.TO_TITLE_CASE;
    return this;
  }

  @Override
  public TypeParserBuilder preserveCharacterNormalizationForm() {
    targetCharacterNormalizationForm = null;
    return this;
  }

  @Override
  public TypeParserBuilder toCharacterNormalizationFormNFC() {
    targetCharacterNormalizationForm = Form.NFC;
    return this;
  }

  @Override
  public TypeParserBuilder toCharacterNormalizationFormNFD() {
    targetCharacterNormalizationForm = Form.NFD;
    return this;
  }

  @Override
  public TypeParserBuilder toCharacterNormalizationFormNFKC() {
    targetCharacterNormalizationForm = Form.NFKC;
    return this;
  }

  @Override
  public TypeParserBuilder toCharacterNormalizationFormNFKD() {
    targetCharacterNormalizationForm = Form.NFKD;
    return this;
  }

  @Override
  public TypeParserBuilder acceptChar(final char ch) {
    rangedSubsetBuilder.includeChar(ch);
    return this;
  }

  @Override
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
  @Override
  public TypeParserBuilder acceptCharRange(final char inclusiveFrom, final char inclusiveTo) {
    rangedSubsetBuilder.includeCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public TypeParserBuilder acceptCodePoint(final int codePoint) {
    rangedSubsetBuilder.includeCodePoint(codePoint);
    return this;
  }

  @Override
  public TypeParserBuilder acceptCodePoints(final int... codePoints) {
    rangedSubsetBuilder.includeCodePoints(codePoints);
    return this;
  }

  /**
   * Configures the type-parser to accept characters inclusively within the range defined by the arguments. It will automatically correct the range if
   * the {@code inclusiveFrom} and {@code inclusiveTo} values have accidentally been reversed, for example ('9', '0') instead of ('0', '9').
   *
   * @param inclusiveFrom the inclusive 'from' code-point.
   * @param inclusiveTo   the inclusive 'to' code-point.
   * @return this {@code TypeParserBuilder}.
   */
  @Override
  public TypeParserBuilder acceptCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    rangedSubsetBuilder.includeCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  /**
   * <p>Configures the type-parser to convert any {@code fromChar} in the input sequence to a {@code toChar}.</p>
   *
   * <p>By default, this method also configures the type-parser to accept {@code fromChar} characters in the input
   * sequence. It does this using the {@link #acceptChar(char)} method.</p>
   *
   * <p><b>Note</b> it is not necessary for the {@code toChar} to be an “accepted” character in the input value. Conversion
   * occurs after each input character has been vetted as accepted.</p>
   *
   * @param fromChar the character to convert from
   * @param toChar   the character to convert to
   * @return this {@code TypeParserBuilder}.
   * @see #acceptChar(char)
   */
  @Override
  public TypeParserBuilder convertChar(final char fromChar, final char toChar) {
    converterBuilder.addCharConversion(fromChar, toChar);
    acceptChar(fromChar);
    return this;
  }

  /**
   * <p>Configures the type-parser to convert any {@code fromChar} in the input sequence to a {@code toCharSequence}.</p>
   *
   * <p>By default, this method also configures the type-parser to accept {@code fromChar} characters in the input
   * sequence. It does this using the {@link #acceptChar(char)} method.</p>
   *
   * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
   * Conversion occurs after each input character has been vetted as accepted.</p>
   *
   * @param fromChar       the character to convert from
   * @param toCharSequence the character sequence to convert to. May be {@code null} or empty.
   * @return this {@code TypeParserBuilder}.
   * @see #acceptChar(char)
   */
  @Override
  public TypeParserBuilder convertChar(final char fromChar, final CharSequence toCharSequence) {
    converterBuilder.addCharConversion(fromChar, toCharSequence);
    acceptChar(fromChar);
    return this;
  }

  /**
   * <p>Configures the type-parser to convert any {@code fromCharSequence} in the input sequence to a {@code toCharSequence}.</p>
   *
   * <p>Conversion is carried out in the reading order of the input sequence.</p>
   *
   * <p><b>Nested matches</b></p>
   * Preference is given to the longest match when multiple {@code fromCharSequence} could be considered nested. For example, the following table
   * shows how a type-parser configured with 1, 2 or 3 conversions would convert an input value:</p>
   * <pre>
   *   Input value  | conversion 1       | Conversion 2           | Conversion 3         | After Conversion
   *   =============|====================|========================|======================|=====================
   *   semi-trailer | s    ⟶ d           |                        |                      | demi-trailer
   *   semi-trailer | s    ⟶ d           | semi-trailer ⟶ lorry   |                      | lorry
   *   semi-trailer | semi ⟶ articulated |                        |                      | articulated-trailer
   *   semi-trailer | semi ⟶ articulated | semi-trailer ⟶ lorry   |                      | lorry
   *   semi-trailer | semi ⟶ articulated | trailer      ⟶ vehicle |                      | articulated-vehicle
   *   semi-trailer | semi ⟶ articulated | trailer      ⟶ vehicle | semi-trailer ⟶ lorry | lorry
   * </pre>
   *
   * <p><b>Overlapping matches</b></p>
   * <p>Preference is given to the earliest and longest complete match. For example, the following table shows how a type-parser configured with 1, 2
   * or 3 conversions would convert an input value:</p>
   * <pre>
   *   Input value     | conversion 1           | Conversion 2           | Conversion 3           | After Conversion
   *   ================|========================|========================|========================|==================
   *   full stop light | full stop  ⟶ period    |                        |                        | period light
   *   full stop light | stop light ⟶ red light |                        |                        | full red light
   *   full stop light | full stop  ⟶ period    | stop light ⟶ red light |                        | period light
   *   full stop light | full stop  ⟶ period    | stop light ⟶ red light | full stop light ⟶ skid | skid
   * </pre>
   *
   * <p>By default, this method also configures the type-parser to accept all the code-points found in
   * the {@code fromCharSequence} in the input sequence. This would be the same calling the {@link #acceptCodePoints(int...)} method.</p>
   *
   * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
   * Conversion occurs after each input character has been vetted as accepted.</p>
   *
   * @param fromCharSequence the character sequence to convert from
   * @param toCharSequence   the character sequence to convert to. May be {@code null} or empty.
   * @return this {@code TypeParserBuilder}.
   * @see #acceptCodePoints(int...)
   */
  @Override
  public TypeParserBuilder convertChar(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
    converterBuilder.addCharConversion(fromCharSequence, toCharSequence);
    acceptCodePointsInCharSequence(fromCharSequence);
    return this;
  }

  /**
   * <p>Configures the type-parser to convert any {@code fromCodePoint} in the input sequence to a {@code toCodePoint}.</p>
   *
   * <p>By default, this method also configures the type-parser to accept {@code fromCodePoint} characters in the input
   * sequence. It does this using the {@link #acceptCodePoint(int)} method.</p>
   *
   * <p><b>Note</b> it is not necessary for the {@code toCodePoint} to be an “accepted” code-point in the input value.
   * Conversion occurs after each input character has been vetted as accepted.</p>
   *
   * @param fromCodePoint the code-point to convert from
   * @param toCodePoint   the code-point to convert to
   * @return this {@code TypeParserBuilder}.
   * @see #acceptCodePoint(int)
   */
  @Override
  public TypeParserBuilder convertCodePoint(final int fromCodePoint, final int toCodePoint) {
    converterBuilder.addCodePointConversion(fromCodePoint, toCodePoint);
    acceptCodePoint(fromCodePoint);
    return this;
  }

  /**
   * <p>Configures the type-parser to convert any {@code fromCodePoint} in the input sequence to a {@code toCodePoint}.</p>
   *
   * <p>By default, this method also configures the type-parser to accept {@code fromCodePoint} characters in the input
   * sequence. It does this using the {@link #acceptCodePoint(int)} method.</p>
   *
   * <p><b>Note</b> it is not necessary for the characters that make up the {@code toCharSequence} to be “accepted” characters in the input value.
   * Conversion occurs after each input character has been vetted as accepted.</p>
   *
   * @param fromCodePoint  the code-point to convert from
   * @param toCharSequence the character sequence to convert to. May be {@code null} or empty.
   * @return this {@code TypeParserBuilder}.
   * @see #acceptCodePoint(int)
   */
  @Override
  public TypeParserBuilder convertCodePoint(final int fromCodePoint, final CharSequence toCharSequence) {
    converterBuilder.addCodePointConversion(fromCodePoint, toCharSequence);
    acceptCodePoint(fromCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder acceptSubset(final Subset subset) {
    rangedSubsetBuilder.includeSubset(subset);
    return this;
  }

  @Override
  public TypeParserBuilder acceptSubsets(final Subset... subsets) {
    rangedSubsetBuilder.includeSubsets(subsets);
    return this;
  }

  @Override
  public TypeParserBuilder acceptUnicodeCategory(final Category category) {
    rangedSubsetBuilder.includeUnicodeCategory(category);
    return this;
  }

  @Override
  public TypeParserBuilder acceptUnicodeCategory(final Category... categories) {
    rangedSubsetBuilder.includeUnicodeCategories(categories);
    return this;
  }

  @Override
  public TypeParserBuilder acceptAllUnicodeLetters() {
    return acceptUnicodeCategory(Category.LETTER);
  }

  /**
   * Configures the type parser to accepts all letters [a-zA-Z] that are in the ASCII portion of the Unicode character set.
   * <p>
   * If you wish for the letters to be converted to upper or lower case then remember to configure the {@link TypeParser} by calling
   * {@link #toLowerCase()} or {@link #toUpperCase()} in the {@link TypeParserBuilder}
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
  @Override
  public TypeParserBuilder acceptLettersAtoZ() {
    return acceptCharRange('a', 'z')
        .acceptCharRange('A', 'Z');
  }

  /**
   * Configures the type parser to accepts all digits number '0' through '9' that were originally in the ASCII range.
   * <p>
   * If you want to accept all decimal digits in another letters range then use the following:
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
  @Override
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
  @Override
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
   * <p><b>Note:</b> This conversion might make sense for certain data-types and not others. For example, in letters specific data-types you may
   * may wish to preserve letters specific dashes. For example, for an Armenian data-type, perhaps preserving the Armenian Hyphen
   * {@code '֊' (U+058A)} makes more sense than converting it to a hyphen.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#DASH_PUNCTUATION
   * @see <a href="https://unicode.org/reports/tr18/#General_Category_Property">General Category Property – unicode.org</a>
   * @see <a href="https://www.compart.com/en/unicode/category/Pd">List of Unicode Characters of Category “Dash Punctuation” – compart.com</a>
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf">C0 Controls and Basic Latin – unicode.org</a>
   */
  @Override
  public TypeParserBuilder convertAllDashesToHyphen() {
    return convertAllDashesTo('-');
  }

  /**
   * <p>Synonym for {@link #convertAllDashesToHyphen()}.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see #convertAllDashesToHyphen()
   */
  @Override
  public TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen() {
    return convertAllDashesToHyphen();
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toChar} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character
   * {@code '_' (U+005F)}.</p>
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
  @Override
  public TypeParserBuilder convertAllDashesTo(final char toChar) {
    return convertAllDashesTo((int) toChar);
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toCodePoint} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some character/code-point of your liking. For example, you may wish to convert any dash or hyphen character to an underscore character
   * {@code '_' (U+005F)}.</p>
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
  @Override
  public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
    acceptCodePoint(toCodePoint);
    converterBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCodePoint);
    return this;
  }

  /**
   * <p>Converts any dash or hyphen punctuation mark characters that are defined to be in the {@code Pd}, {@code Dash_Punctuation} unicode category
   * to the specified {@code toCharSequence} argument.</p>
   *
   * <p>This is useful to ensure that other kinds of dashes, like en-dashes, em-dashes, non-breaking hyphens, etc, are converted to
   * some char-sequence of your liking. For example, you may wish to convert any dash or hyphen character to the sequence
   * {@code '~~~' (U+007E, U+007E, U+007E)}.</p>
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
  @Override
  public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
    acceptCodePointsInCharSequence(toCharSequence);
    converterBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCharSequence);
    return this;
  }

  /**
   * <p>Removes any character or code-point that is designated to be whitespace as per the {@link Character#isWhitespace(int)} method.</p>
   *
   * @return this {@code TypeParserBuilder}
   * @see Character#isWhitespace(char)
   * @see Character#isWhitespace(int)
   */
  @Override
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
  @Override
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
  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptChar(toChar);
    convertChar(' ', toChar);
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
  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptCodePoint(toCodePoint);
    convertCodePoint(' ', toCodePoint);
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
  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptCodePointsInCharSequence(toCharSequence);
    convertCodePoint(' ', toCharSequence);
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
  @Override
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
  @Override
  public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    acceptChar(toChar);
    convertChar(' ', toChar);
    return this;
  }

  @Override
  public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    acceptCodePoint(toCodePoint);
    convertCodePoint(' ', toCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    acceptCodePointsInCharSequence(toCharSequence);
    convertCodePoint(' ', toCharSequence);
    return this;
  }

  /**
   * <p>The type parser will ensure that the fully parsed value matches the provided {@code regex} pattern.</p>
   *
   * <p><b>Example usage:</b></p>
   * Imagine some data-type that is required to start with two alpha characters and is followed by 8 numeric digits. We could declare a regular
   * expression shown at ① and then configure the type parser to use the regular expression at ②:
   * <pre>
   *   private static final Pattern VALID_DATA_TYPE_PATTERN = Pattern.compile("[A-Z]{2}[0-9]{8}"); ①
   *
   *   private static final TypeParser TYPE_PARSER =
   *     TypeParser.builder(ExampleDataType.class)
   *       .errorMessage("must be a valid 10 character example code")
   *       .acceptLettersAtoZ()
   *       .acceptDigits0to9()
   *       .fixedSize(10)
   *       .removeAllWhitespace()
   *       .toUpperCase()
   *       .matchesRegex(VALID_DATA_TYPE_PATTERN) ②
   *       .build();
   * </pre>
   *
   * @param regex the regular expression that the fully parsed value should conform to.
   * @return this {@code TypeParserBuilder}
   * @see Pattern
   */
  @Override
  public TypeParserBuilder matchesRegex(final Pattern regex) {
    this.regex = regex;
    return this;
  }

  /**
   * <p>The type parser will ensure that the fully parsed value passed the provided custom {@code validationFunction}.</p>
   *
   * <p>The custom validation function that you provide should:</p>
   * <ul>
   *   <li>return {@code true} if the fully parsed string value provided to it is <em>valid</em>.</li>
   *   <li>return {@code false} or throw an exception if the fully parsed string value provided to it is <em>invalid</em>.</li>
   * </ul>
   *
   * <p>Example usage:</p>
   * <pre>
   *   private static final TypeParser TYPE_PARSER =
   *     TypeParser.builder(ExampleDataType.class)
   *       .errorMessage("must be a valid 6..8 character example code")
   *       .acceptLettersAtoZ()
   *       .acceptDigits0to9()
   *       .minSize(6)
   *       .maxSize(8)
   *       .removeAllWhitespace()
   *       .toUpperCase()
   *       .customValidator(ExampleDataType::isValidExampleCode)  ①
   *       .build();
   * </pre>
   * where the custom validation function ① is defined as:
   * <pre>
   *   private static boolean isValidExampleCode(final String value) {
   *     boolean isValid = false;
   *     // ... your validation code
   *     if (isValid) {
   *       return Boolean.TRUE;
   *     }
   *     return Boolean.FALSE;
   *   }
   * </pre>
   * <p>where:</p>
   *
   * @param validationFunction a static function, or a functional interface, that accepts the fully parsed value as a {@link String} argument and
   *                           returns a boolean value indicating {@code true} if the provided value is <em>valid</em>. It can either return
   *                           {@code false} or throw an exception to indicate that the provided value is <em>invalid</em>.
   * @return this {@code TypeParserBuilder}
   * @see Function
   */
  @Override
  public TypeParserBuilder customValidator(final Function<String, Boolean> validationFunction) {
    this.validationFunction = validationFunction;
    return this;
  }

  @Override
  public TypeParserImpl build() {
    return new TypeParserImpl(
        targetTypeClass, errorMessage, targetCase,
        whiteSpace,
        nullHandling,
        targetCharacterNormalizationForm,
        minNumberOfCodePoints, maxNumberOfCodePoints,
        regex,
        validationFunction,
        rangedSubsetBuilder.build(),
        converterBuilder.build());
  }
}
