package org.typefactory.impl;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.typefactory.Category;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParser;
import org.typefactory.TypeParser.TypeParserBuilder;

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

  @Override
  public TypeParserBuilder minSize(final int minSize) {
    minNumberOfCodePoints = Math.max(minSize, 0);
    return this;
  }

  @Override
  public TypeParserBuilder maxSize(final int maxSize) {
    maxNumberOfCodePoints = maxSize;
    return this;
  }

  @Override
  public TypeParserBuilder fixedSize(final int size) {
    minSize(size);
    maxSize(size);
    return this;
  }

  @Override
  public TypeParserBuilder preserveNullAndEmpty() {
    nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
    return this;
  }

  @Override
  public TypeParserBuilder convertNullToEmpty() {
    nullHandling = NullHandling.CONVERT_NULL_TO_EMPTY;
    return this;
  }

  @Override
  public TypeParserBuilder convertEmptyToNull() {
    nullHandling = NullHandling.CONVERT_EMPTY_TO_NULL;
    return this;
  }

  @Override
  public TypeParserBuilder preserveCase() {
    targetCase = TargetCase.PRESERVE_CASE;
    return this;
  }

  @Override
  public TypeParserBuilder toUpperCase() {
    targetCase = TargetCase.TO_UPPER_CASE;
    return this;
  }

  @Override
  public TypeParserBuilder toLowerCase() {
    targetCase = TargetCase.TO_LOWER_CASE;
    return this;
  }

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

  @Override
  public TypeParserBuilder acceptCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    rangedSubsetBuilder.includeCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public TypeParserBuilder convertChar(final char fromChar, final char toChar) {
    converterBuilder.addCharConversion(fromChar, toChar);
    acceptChar(fromChar);
    return this;
  }

  @Override
  public TypeParserBuilder convertChar(final char fromChar, final CharSequence toCharSequence) {
    converterBuilder.addCharConversion(fromChar, toCharSequence);
    acceptChar(fromChar);
    return this;
  }

  @Override
  public TypeParserBuilder convertChar(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
    converterBuilder.addCharConversion(fromCharSequence, toCharSequence);
    acceptCodePointsInCharSequence(fromCharSequence);
    return this;
  }

  @Override
  public TypeParserBuilder convertCodePoint(final int fromCodePoint, final int toCodePoint) {
    converterBuilder.addCodePointConversion(fromCodePoint, toCodePoint);
    acceptCodePoint(fromCodePoint);
    return this;
  }

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

  @Override
  public TypeParserBuilder acceptLettersAtoZ() {
    return acceptCharRange('a', 'z')
        .acceptCharRange('A', 'Z');
  }

  @Override
  public TypeParserBuilder acceptDigits0to9() {
    return acceptCharRange('0', '9');
  }

  @Override
  public TypeParserBuilder acceptAllDashes() {
    acceptUnicodeCategory(Category.DASH_PUNCTUATION);
    return this;
  }

  @Override
  public TypeParserBuilder convertAllDashesToHyphen() {
    return convertAllDashesTo('-');
  }

  @Override
  public TypeParserBuilder acceptHyphenAndConvertAllDashesToHyphen() {
    return convertAllDashesToHyphen();
  }

  @Override
  public TypeParserBuilder convertAllDashesTo(final char toChar) {
    return convertAllDashesTo((int) toChar);
  }

  @Override
  public TypeParserBuilder convertAllDashesTo(final int toCodePoint) {
    acceptCodePoint(toCodePoint);
    converterBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder convertAllDashesTo(final CharSequence toCharSequence) {
    acceptCodePointsInCharSequence(toCharSequence);
    converterBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, toCharSequence);
    return this;
  }

  @Override
  public TypeParserBuilder removeAllWhitespace() {
    whiteSpace = WhiteSpace.REMOVE_WHITESPACE;
    return this;
  }

  @Override
  public TypeParserBuilder normalizeWhitespace() {
    whiteSpace = WhiteSpace.NORMALIZE_WHITESPACE;
    return this;
  }

  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final char toChar) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptChar(toChar);
    convertChar(' ', toChar);
    return this;
  }

  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final int toCodePoint) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptCodePoint(toCodePoint);
    convertCodePoint(' ', toCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder normalizeAndConvertWhitespaceTo(final CharSequence toCharSequence) {
    whiteSpace = WhiteSpace.NORMALIZE_AND_CONVERT_WHITESPACE;
    acceptCodePointsInCharSequence(toCharSequence);
    convertCodePoint(' ', toCharSequence);
    return this;
  }

  @Override
  public TypeParserBuilder preserveWhitespace() {
    whiteSpace = WhiteSpace.PRESERVE_WHITESPACE;
    return this;
  }

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

  @Override
  public TypeParserBuilder matchesRegex(final Pattern regex) {
    this.regex = regex;
    return this;
  }

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
