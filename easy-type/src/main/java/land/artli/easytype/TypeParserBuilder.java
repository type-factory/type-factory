package land.artli.easytype;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import land.artli.easytype.CodePointConversions.CodePointConversionsBuilder;

public class TypeParserBuilder {

  private final Class<? extends CharType<?>> targetClass;
  private String errorMessage;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private int[] convertWhiteSpaceToCodePoints;
  private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
  private Normalizer.Form targetCharacterNormalizationForm = Form.NFC;
  private int minNumberOfCodePoints = 0;
  private int maxNumberOfCodePoints = 64;
  private TargetCase targetCase = TargetCase.PRESERVE_CASE;
  private long acceptedCategories = 0; // bit values, currently 32-bit 'int' is enough but new categories have been defined, so we use 'long'.
  private final RangedSubsetBuilder codePointRangesBuilder = RangedSubset.builder();
  private final CodePointConversionsBuilder codePointConversionsBuilder = CodePointConversions.builder();
  private final List<TypeParserBuilder> logicalOr = new ArrayList<>();

  TypeParserBuilder(final Class<? extends CharType<?>> targetClass) {
    this.targetClass = targetClass;
  }

  public TypeParserBuilder errorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public TypeParserBuilder minNumberOfCodePoints(final int min) {
    minNumberOfCodePoints = Math.max(min, 0);
    return this;
  }

  public TypeParserBuilder maxNumberOfCodePoints(final int max) {
    maxNumberOfCodePoints = max;
    return this;
  }

  public TypeParserBuilder fixedNumberOfCodePoints(final int size) {
    minNumberOfCodePoints(size);
    maxNumberOfCodePoints(size);
    return this;
  }

  public TypeParserBuilder preserveNullAndEmpty() {
    nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
    return this;
  }

  public TypeParserBuilder convertNullToEmpty() {
    nullHandling = NullHandling.CONVERT_NULL_TO_EMPTY;
    return this;
  }

  public TypeParserBuilder convertEmptyToNull() {
    nullHandling = NullHandling.CONVERT_EMPTY_TO_NULL;
    return this;
  }

  public TypeParserBuilder preserveCase() {
    targetCase = TargetCase.PRESERVE_CASE;
    return this;
  }

  public TypeParserBuilder toUpperCase() {
    targetCase = TargetCase.TO_UPPER_CASE;
    return this;
  }

  public TypeParserBuilder toLowerCase() {
    targetCase = TargetCase.TO_LOWER_CASE;
    return this;
  }

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
    codePointRangesBuilder.addChar(ch);
    return this;
  }

  public TypeParserBuilder acceptChars(final char... chars) {
    codePointRangesBuilder.addChars(chars);
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
    codePointRangesBuilder.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  public TypeParserBuilder acceptCodePoint(final int codePoint) {
    codePointRangesBuilder.addCodePoint(codePoint);
    return this;
  }

  public TypeParserBuilder acceptCodePoints(final int... codePoints) {
    codePointRangesBuilder.addCodePoints(codePoints);
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
  public TypeParserBuilder acceptCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    codePointRangesBuilder.addCodePointRange(inclusiveFrom, inclusiveTo);
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
    codePointRangesBuilder.addRangedSubset(language.getRangedSubset());
    return this;
  }

  public TypeParserBuilder acceptLanguages(final Language... languages) {
    if (languages != null && languages.length > 0) {
      for (int i = 0; i < languages.length; ++i) {
        acceptLanguage(languages[i]);
      }
    }
    return this;
  }

  public TypeParserBuilder acceptUnicodeCategory(final Category category) {
    acceptedCategories |= category.bitMask;
    return this;
  }

  public TypeParserBuilder acceptUnicodeCategory(final Category... categories) {
    if (categories != null && categories.length > 0) {
      for (int i = 0; i < categories.length; ++i) {
        acceptUnicodeCategory(categories[i]);
      }
    }
    return this;
  }

  public TypeParserBuilder acceptAllUnicodeLetters() {
    acceptUnicodeCategory(Category.LETTER);
    return this;
  }

  /**
   * Configures the type parser to accepts all letters 'A' through 'Z' that were originally in the ASCII range.
   * <p>
   * If you want to accept <em>all</em> letters in the Unicode set (this could mean mixed-languages) then use: {@link #acceptAllUnicodeLetters()}
   *
   * @return this {@code TypeParserBuilder}
   * @see #acceptUnicodeCategory(Category)
   * @see Category#LETTER
   */
  public TypeParserBuilder acceptLettersAtoZ() {
    acceptCharRange('A', 'Z');
    return this;
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
    acceptCharRange('0', '9');
    return this;
  }

  public TypeParserBuilder acceptAllDashes() {
    acceptUnicodeCategory(Category.DASH_PUNCTUATION);
    return this;
  }

  public TypeParserBuilder convertAllDashesToHyphen() {
    return convertAllDashesTo((int) '-');
  }

  public TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen() {
    return acceptChar('-').convertAllDashesTo((int) '-');
  }

  public TypeParserBuilder convertAllDashesTo(final char toChar) {
    return convertAllDashesTo((int) toChar);
  }

  public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
    acceptCodePoint(toCodePoint);
    codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCodePoint);
    return this;
  }

  public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
    toCharSequence.codePoints().forEach(this::acceptCodePoint);
    codePointConversionsBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCharSequence);
    return this;
  }

  public TypeParserBuilder removeAllWhitespace() {
    whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
    return this;
  }

  public TypeParserBuilder normalizeWhitespace() {
    whiteSpace = WhiteSpace.NORMALIZE_WHITESPACE;
    return this;
  }

  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toChar};
    return this;
  }

  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
    return this;
  }

  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = toCharSequence.codePoints().toArray();
    return this;
  }

  public TypeParserBuilder preserveWhitespace() {
    whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
    return this;
  }

  public TypeParserBuilder preserveAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toChar};
    return this;
  }

  public TypeParserBuilder preserveAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = new int[]{toCodePoint};
    return this;
  }

  public TypeParserBuilder preserveAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.PRESERVE_AND_CONVERT_WHITESPACE;
    convertWhiteSpaceToCodePoints = toCharSequence.codePoints().toArray();
    return this;
  }

  public TypeParserImpl build() {
    return new TypeParserImpl(
        targetClass, errorMessage, targetCase,
        whiteSpace, convertWhiteSpaceToCodePoints,
        nullHandling,
        targetCharacterNormalizationForm,
        minNumberOfCodePoints, maxNumberOfCodePoints,
        acceptedCategories,
        codePointRangesBuilder.build(),
        codePointConversionsBuilder.build());
  }
}
