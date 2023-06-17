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
package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_STRING;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.typefactory.Category;
import org.typefactory.MessageCode;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParser.TypeParserBuilder;

final class TypeParserBuilderImpl implements TypeParserBuilder {

  private Class<?> targetTypeClass;
  private MessageCode messageCode;
  private WhiteSpace whiteSpace = WhiteSpace.FORBID_WHITESPACE;
  private NullHandling nullHandling = NullHandling.PRESERVE_NULL_AND_EMPTY;
  private Normalizer.Form targetCharacterNormalizationForm = Form.NFC;
  private int minNumberOfCodePoints = 0;
  private int maxNumberOfCodePoints = 64;
  private TargetCase targetCase = TargetCase.PRESERVE_CASE;
  private Pattern regex = null;
  private Predicate<String> validationFunction = null;
  private MinValueValidator<?> minValueValidator;
  private MaxValueValidator<?> maxValueValidator;

  private final SubsetBuilder rangedSubsetBuilder = Subset.builder();
  private final ConverterBuilder converterBuilder = Converter.builder();


  TypeParserBuilderImpl() {
  }

  public TypeParserBuilder targetTypeClass(final Class<?> targetTypeClass) {
    this.targetTypeClass = targetTypeClass;
    return this;
  }

  public TypeParserBuilder messageCode(final MessageCode messageCode) {
    this.messageCode = messageCode;
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
  private TypeParserBuilder acceptCodePointsInCharSequence(CharSequence charSequence) {
    if (charSequence == null) {
      return this;
    }
    return acceptCodePoints(charSequence.codePoints().toArray());
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
  public TypeParserBuilder convertCharSequence(final CharSequence fromCharSequence, final CharSequence toCharSequence) {
    converterBuilder.addCharSequenceConversion(fromCharSequence, toCharSequence);
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
  public TypeParserBuilder convertAnyInCategory(final Category fromCategory, final char toChar) {
    converterBuilder.addCategoryConversion(fromCategory, toChar);
    return this;
  }

  @Override
  public TypeParserBuilder convertAnyInCategory(final Category fromCategory, final int toCodePoint) {
    converterBuilder.addCategoryConversion(fromCategory, toCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder convertAnyInCategory(final Category fromCategory, final CharSequence toCharSequence) {
    converterBuilder.addCategoryConversion(fromCategory, toCharSequence);
    return this;
  }

  @Override
  public TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final char toChar) {
    converterBuilder.addCodePointConversions(fromSubset, toChar);
    return this;
  }

  @Override
  public TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final int toCodePoint) {
    converterBuilder.addCodePointConversions(fromSubset, toCodePoint);
    return this;
  }

  @Override
  public TypeParserBuilder convertAnyInSubset(final Subset fromSubset, final CharSequence toCharSequence) {
    converterBuilder.addCodePointConversions(fromSubset, toCharSequence);
    return this;
  }

  @Override
  public TypeParserBuilder removeAllChars(final char ch) {
    converterBuilder.addCharConversion(ch, EMPTY_STRING);
    acceptChar(ch);
    return this;
  }

  @Override
  public TypeParserBuilder removeAllChars(final char... chars) {
    if (chars != null) {
      for (int i = 0; i < chars.length; ++i) {
        removeAllChars(chars[i]);
      }
    }
    return this;
  }

  @Override
  public TypeParserBuilder removeAllCharSequences(final CharSequence charSequence) {
    converterBuilder.addCharSequenceConversion(charSequence, EMPTY_STRING);
    acceptCodePointsInCharSequence(charSequence);
    return this;
  }

  @Override
  public TypeParserBuilder removeAllCharSequences(final CharSequence... charSequences) {
    if (charSequences != null) {
      for (int i = 0; i < charSequences.length; ++i) {
        removeAllCharSequences(charSequences[i]);
      }
    }
    return this;
  }

  @Override
  public TypeParserBuilder removeAllCodePoints(final int codePoint) {
    converterBuilder.addCodePointConversion(codePoint, EMPTY_STRING);
    acceptCodePoint(codePoint);
    return this;
  }

  @Override
  public TypeParserBuilder removeAllCodePoints(final int... codePoints) {
    if (codePoints != null) {
      for (int i = 0; i < codePoints.length; ++i) {
        removeAllCodePoints(codePoints[i]);
      }
    }
    return this;
  }

  @Override
  public TypeParserBuilder removeAllDashesAndHyphens() {
    acceptUnicodeCategory(Category.DASH_PUNCTUATION);
    converterBuilder.addCategoryConversion(Category.DASH_PUNCTUATION, EMPTY_STRING);
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
  public TypeParserBuilder forbidWhitespace() {
    whiteSpace = WhiteSpace.FORBID_WHITESPACE;
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
  public TypeParserBuilder customValidator(final Predicate<String> validationFunction) {
    this.validationFunction = validationFunction;
    return this;
  }

  @Override
  public <T> TypeParserBuilder minValue(T minValue, Comparator<T> comparator, ComparisonMethod comparisonMethod) {
    this.minValueValidator = MinValueValidator.of(minValue, comparator, comparisonMethod);
    return this;
  }

  @Override
  public <T> TypeParserBuilder maxValue(T maxValue, Comparator<T> comparator, ComparisonMethod comparisonMethod) {
    this.maxValueValidator = MaxValueValidator.of(maxValue, comparator, comparisonMethod);
    return this;
  }

  @Override
  public TypeParserImpl build() {
    return new TypeParserImpl(
        targetTypeClass, messageCode, targetCase,
        whiteSpace,
        nullHandling,
        targetCharacterNormalizationForm,
        minNumberOfCodePoints, maxNumberOfCodePoints,
        rangedSubsetBuilder.build(),
        converterBuilder.build(),
        regex,
        validationFunction,
        minValueValidator,
        maxValueValidator);
  }
}
