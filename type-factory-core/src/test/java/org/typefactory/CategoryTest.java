/*Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.typefactory.testutils.CategoryArrayConverter;

class CategoryTest {

  @Test
  void allCategories_haveUniqueBitMasks() {
    final var categories = Category.values();
    final var bitMasks = new java.util.HashSet<Long>();
    for (Category category : categories) {
      assertThat(bitMasks.add(category.bitMask))
          .as("Category %s should have a unique bitMask", category)
          .isTrue();
    }
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @EnumSource(Category.class)
  void allCategories_haveSingleBitOrCompositeBitMask(final Category category) {
    assertThat(category.bitMask)
        .as("Category %s bitMask should be greater than zero", category)
        .isGreaterThan(0L);
  }

  @ParameterizedTest(name = "[{index}] category={0} expectedBitMask={1}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY                  | EXPECTED_BIT_MASK
      UNASSIGNED                | 0b00000000_00000000_00000000_00000001
      UPPERCASE_LETTER          | 0b00000000_00000000_00000000_00000010
      LOWERCASE_LETTER          | 0b00000000_00000000_00000000_00000100
      TITLECASE_LETTER          | 0b00000000_00000000_00000000_00001000
      MODIFIER_LETTER           | 0b00000000_00000000_00000000_00010000
      OTHER_LETTER              | 0b00000000_00000000_00000000_00100000
      NON_SPACING_MARK          | 0b00000000_00000000_00000000_01000000
      ENCLOSING_MARK            | 0b00000000_00000000_00000000_10000000
      COMBINING_SPACING_MARK    | 0b00000000_00000000_00000001_00000000
      DECIMAL_DIGIT_NUMBER      | 0b00000000_00000000_00000010_00000000
      LETTER_NUMBER             | 0b00000000_00000000_00000100_00000000
      OTHER_NUMBER              | 0b00000000_00000000_00001000_00000000
      SPACE_SEPARATOR           | 0b00000000_00000000_00010000_00000000
      LINE_SEPARATOR            | 0b00000000_00000000_00100000_00000000
      PARAGRAPH_SEPARATOR       | 0b00000000_00000000_01000000_00000000
      CONTROL                   | 0b00000000_00000000_10000000_00000000
      FORMAT                    | 0b00000000_00000001_00000000_00000000
      PRIVATE_USE               | 0b00000000_00000100_00000000_00000000
      SURROGATE                 | 0b00000000_00001000_00000000_00000000
      DASH_PUNCTUATION          | 0b00000000_00010000_00000000_00000000
      START_PUNCTUATION         | 0b00000000_00100000_00000000_00000000
      END_PUNCTUATION           | 0b00000000_01000000_00000000_00000000
      CONNECTOR_PUNCTUATION     | 0b00000000_10000000_00000000_00000000
      OTHER_PUNCTUATION         | 0b00000001_00000000_00000000_00000000
      MATH_SYMBOL               | 0b00000010_00000000_00000000_00000000
      CURRENCY_SYMBOL           | 0b00000100_00000000_00000000_00000000
      MODIFIER_SYMBOL           | 0b00001000_00000000_00000000_00000000
      OTHER_SYMBOL              | 0b00010000_00000000_00000000_00000000
      INITIAL_QUOTE_PUNCTUATION | 0b00100000_00000000_00000000_00000000
      FINAL_QUOTE_PUNCTUATION   | 0b01000000_00000000_00000000_00000000

      CASED_LETTER              | 0b00000000_00000000_00000000_00001110
      LETTER                    | 0b00000000_00000000_00000000_00111110
      MARK                      | 0b00000000_00000000_00000001_11000000
      NUMBER                    | 0b00000000_00000000_00001110_00000000
      PUNCTUATION               | 0b01100001_11110000_00000000_00000000
      SYMBOL                    | 0b00011110_00000000_00000000_00000000
      SEPARATOR                 | 0b00000000_00000000_01110000_00000000
      OTHER                     | 0b00000000_00001101_10000000_00000001
      """)
  void categories_haveExpectedBitMask(final Category category, final String expectedBitMaskBinaryString) {
    long expectedBitMask = Long.parseUnsignedLong(
        expectedBitMaskBinaryString.substring(2).replace("_", ""), 2);
    assertThat(category.bitMask)
        .as("Category %s should have bitMask %s", category, expectedBitMaskBinaryString)
        .isEqualTo(expectedBitMask);
    assertThat(Category.getCategoryBitFlags(category))
        .as("Category %s should have bitMask %s", category, expectedBitMaskBinaryString)
        .isEqualTo(expectedBitMask);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY        | CONTAINS_CATEGORY
      CASED_LETTER    | UPPERCASE_LETTER
      CASED_LETTER    | LOWERCASE_LETTER
      CASED_LETTER    | TITLECASE_LETTER
      LETTER          | CASED_LETTER
      LETTER          | UPPERCASE_LETTER
      LETTER          | LOWERCASE_LETTER
      LETTER          | TITLECASE_LETTER
      LETTER          | MODIFIER_LETTER
      LETTER          | OTHER_LETTER
      MARK            | NON_SPACING_MARK
      MARK            | COMBINING_SPACING_MARK
      MARK            | ENCLOSING_MARK
      NUMBER          | DECIMAL_DIGIT_NUMBER
      NUMBER          | LETTER_NUMBER
      NUMBER          | OTHER_NUMBER
      PUNCTUATION     | CONNECTOR_PUNCTUATION
      PUNCTUATION     | DASH_PUNCTUATION
      PUNCTUATION     | START_PUNCTUATION
      PUNCTUATION     | END_PUNCTUATION
      PUNCTUATION     | INITIAL_QUOTE_PUNCTUATION
      PUNCTUATION     | FINAL_QUOTE_PUNCTUATION
      PUNCTUATION     | OTHER_PUNCTUATION
      SYMBOL          | MATH_SYMBOL
      SYMBOL          | CURRENCY_SYMBOL
      SYMBOL          | MODIFIER_SYMBOL
      SYMBOL          | OTHER_SYMBOL
      SEPARATOR       | SPACE_SEPARATOR
      SEPARATOR       | LINE_SEPARATOR
      SEPARATOR       | PARAGRAPH_SEPARATOR
      OTHER           | CONTROL
      OTHER           | FORMAT
      OTHER           | PRIVATE_USE
      OTHER           | SURROGATE
      OTHER           | UNASSIGNED
      """)
  void compositeCategory_containsExpectedSubCategory(final Category category, final Category containsCategory) {
    assertThat(category.isCompositeCategory()).isTrue();
    assertThat(category.bitMask & containsCategory.bitMask)
        .as("Category %s bitMask should include %s bitMask", category, containsCategory)
        .isEqualTo(containsCategory.bitMask);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY     | DOES_NOT_CONTAIN_CATEGORY
      CASED_LETTER | MODIFIER_LETTER
      CASED_LETTER | OTHER_LETTER
      CASED_LETTER | NON_SPACING_MARK
      CASED_LETTER | DECIMAL_DIGIT_NUMBER
      LETTER       | NON_SPACING_MARK
      LETTER       | DECIMAL_DIGIT_NUMBER
      LETTER       | MATH_SYMBOL
      NUMBER       | UPPERCASE_LETTER
      NUMBER       | MATH_SYMBOL
      SYMBOL       | UPPERCASE_LETTER
      SYMBOL       | DECIMAL_DIGIT_NUMBER
      SEPARATOR    | UPPERCASE_LETTER
      SEPARATOR    | DECIMAL_DIGIT_NUMBER
      OTHER        | UPPERCASE_LETTER
      OTHER        | DECIMAL_DIGIT_NUMBER
      """)
  void compositeCategory_doesNotContainUnrelatedCategory(final Category category, final Category doesNotContainCategory) {
    assertThat(category.bitMask & doesNotContainCategory.bitMask)
        .as("Category %s bitMask should not include %s bitMask", category, doesNotContainCategory)
        .isZero();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @EnumSource(Category.class)
  void valueOf_returnsCorrectCategory(final Category category) {
    assertThat(Category.valueOf(category.name()))
        .as("Category.valueOf(%s) should return the same category", category.name())
        .isSameAs(category);
  }

  @Test
  void valueOf_throwsIllegalArgumentException_forUnknownCategory() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> Category.valueOf("UNKNOWN_CATEGORY"));
  }

  @Test
  void values_returnsAllCategories() {
    assertThat(Category.values())
        .as("Category.values() should return all categories")
        .isNotEmpty()
        .doesNotContainNull();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY                | EXPECTED_NAME
      UPPERCASE_LETTER        | UPPERCASE_LETTER
      LOWERCASE_LETTER        | LOWERCASE_LETTER
      TITLECASE_LETTER        | TITLECASE_LETTER
      MODIFIER_LETTER         | MODIFIER_LETTER
      OTHER_LETTER            | OTHER_LETTER
      NON_SPACING_MARK        | NON_SPACING_MARK
      COMBINING_SPACING_MARK  | COMBINING_SPACING_MARK
      ENCLOSING_MARK          | ENCLOSING_MARK
      DECIMAL_DIGIT_NUMBER    | DECIMAL_DIGIT_NUMBER
      LETTER_NUMBER           | LETTER_NUMBER
      OTHER_NUMBER            | OTHER_NUMBER
      CASED_LETTER            | CASED_LETTER
      LETTER                  | LETTER
      MARK                    | MARK
      NUMBER                  | NUMBER
      PUNCTUATION             | PUNCTUATION
      SYMBOL                  | SYMBOL
      SEPARATOR               | SEPARATOR
      OTHER                   | OTHER
      """)
  void name_returnsExpectedValue(final Category category, final String expectedName) {
    assertThat(category.name())
        .isEqualTo(expectedName);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      LEAF_CATEGORY           | COMPOSITE_CATEGORY
      UPPERCASE_LETTER        | CASED_LETTER
      LOWERCASE_LETTER        | CASED_LETTER
      TITLECASE_LETTER        | CASED_LETTER
      UPPERCASE_LETTER        | LETTER
      LOWERCASE_LETTER        | LETTER
      TITLECASE_LETTER        | LETTER
      MODIFIER_LETTER         | LETTER
      OTHER_LETTER            | LETTER
      NON_SPACING_MARK        | MARK
      COMBINING_SPACING_MARK  | MARK
      ENCLOSING_MARK          | MARK
      DECIMAL_DIGIT_NUMBER    | NUMBER
      LETTER_NUMBER           | NUMBER
      OTHER_NUMBER            | NUMBER
      SPACE_SEPARATOR         | SEPARATOR
      LINE_SEPARATOR          | SEPARATOR
      PARAGRAPH_SEPARATOR     | SEPARATOR
      CONTROL                 | OTHER
      FORMAT                  | OTHER
      PRIVATE_USE             | OTHER
      SURROGATE               | OTHER
      UNASSIGNED              | OTHER
      """)
  void leafCategory_bitMask_isSubsetOfCompositeCategory_bitMask(
      final Category leafCategory, final Category compositeCategory) {
    assertThat(compositeCategory.bitMask & leafCategory.bitMask)
        .as("Composite category %s bitMask should contain leaf category %s bitMask",
            compositeCategory, leafCategory)
        .isEqualTo(leafCategory.bitMask);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY                  | EXPECTED_CATEGORIES
      UNASSIGNED                | [UNASSIGNED]
      UPPERCASE_LETTER          | [UPPERCASE_LETTER]         
      LOWERCASE_LETTER          | [LOWERCASE_LETTER]         
      TITLECASE_LETTER          | [TITLECASE_LETTER]         
      MODIFIER_LETTER           | [MODIFIER_LETTER]          
      OTHER_LETTER              | [OTHER_LETTER]             
      NON_SPACING_MARK          | [NON_SPACING_MARK]         
      ENCLOSING_MARK            | [ENCLOSING_MARK]           
      COMBINING_SPACING_MARK    | [COMBINING_SPACING_MARK]   
      DECIMAL_DIGIT_NUMBER      | [DECIMAL_DIGIT_NUMBER]     
      LETTER_NUMBER             | [LETTER_NUMBER]            
      OTHER_NUMBER              | [OTHER_NUMBER]             
      SPACE_SEPARATOR           | [SPACE_SEPARATOR]          
      LINE_SEPARATOR            | [LINE_SEPARATOR]           
      PARAGRAPH_SEPARATOR       | [PARAGRAPH_SEPARATOR]      
      CONTROL                   | [CONTROL]                  
      FORMAT                    | [FORMAT]                   
      PRIVATE_USE               | [PRIVATE_USE]              
      SURROGATE                 | [SURROGATE]                
      DASH_PUNCTUATION          | [DASH_PUNCTUATION]         
      START_PUNCTUATION         | [START_PUNCTUATION]        
      END_PUNCTUATION           | [END_PUNCTUATION]          
      CONNECTOR_PUNCTUATION     | [CONNECTOR_PUNCTUATION]    
      OTHER_PUNCTUATION         | [OTHER_PUNCTUATION]        
      MATH_SYMBOL               | [MATH_SYMBOL]              
      CURRENCY_SYMBOL           | [CURRENCY_SYMBOL]          
      MODIFIER_SYMBOL           | [MODIFIER_SYMBOL]          
      OTHER_SYMBOL              | [OTHER_SYMBOL]             
      INITIAL_QUOTE_PUNCTUATION | [INITIAL_QUOTE_PUNCTUATION]
      FINAL_QUOTE_PUNCTUATION   | [FINAL_QUOTE_PUNCTUATION]  
      
      CASED_LETTER              | [UPPERCASE_LETTER,LOWERCASE_LETTER,TITLECASE_LETTER]
      LETTER                    | [UPPERCASE_LETTER,LOWERCASE_LETTER,TITLECASE_LETTER,MODIFIER_LETTER,OTHER_LETTER]
      MARK                      | [NON_SPACING_MARK,COMBINING_SPACING_MARK,ENCLOSING_MARK]
      NUMBER                    | [DECIMAL_DIGIT_NUMBER,LETTER_NUMBER,OTHER_NUMBER]
      PUNCTUATION               | [CONNECTOR_PUNCTUATION,DASH_PUNCTUATION,START_PUNCTUATION,END_PUNCTUATION,INITIAL_QUOTE_PUNCTUATION,FINAL_QUOTE_PUNCTUATION,OTHER_PUNCTUATION]
      SYMBOL                    | [MATH_SYMBOL,CURRENCY_SYMBOL,MODIFIER_SYMBOL,OTHER_SYMBOL]
      SEPARATOR                 | [SPACE_SEPARATOR,LINE_SEPARATOR,PARAGRAPH_SEPARATOR]
      OTHER                     | [CONTROL,FORMAT,PRIVATE_USE,SURROGATE,UNASSIGNED]
      """)
  void getCharacterCategories_forCompositeCategory_returnsExpandedCategories(
      final Category category,
      @ConvertWith(CategoryArrayConverter.class) final Category[] expectedCategories) {

    final int [] expectedCharacterCategories = Arrays.stream(expectedCategories)
        .flatMapToInt(c -> Arrays.stream(c.getCharacterCategories()))
        .toArray();

    assertThat(category.getCharacterCategories())
        .as("Composite category %s should expand to its constituent categories", category)
        .containsExactlyInAnyOrder(expectedCharacterCategories);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY                  | EXPECTED
      UNASSIGNED                | false
      UPPERCASE_LETTER          | false
      LOWERCASE_LETTER          | false
      TITLECASE_LETTER          | false
      MODIFIER_LETTER           | false
      OTHER_LETTER              | false
      NON_SPACING_MARK          | false
      ENCLOSING_MARK            | false
      COMBINING_SPACING_MARK    | false
      DECIMAL_DIGIT_NUMBER      | false
      LETTER_NUMBER             | false
      OTHER_NUMBER              | false
      SPACE_SEPARATOR           | false
      LINE_SEPARATOR            | false
      PARAGRAPH_SEPARATOR       | false
      CONTROL                   | false
      FORMAT                    | false
      PRIVATE_USE               | false
      SURROGATE                 | false
      DASH_PUNCTUATION          | false
      START_PUNCTUATION         | false
      END_PUNCTUATION           | false
      CONNECTOR_PUNCTUATION     | false
      OTHER_PUNCTUATION         | false
      MATH_SYMBOL               | false
      CURRENCY_SYMBOL           | false
      MODIFIER_SYMBOL           | false
      OTHER_SYMBOL              | false
      INITIAL_QUOTE_PUNCTUATION | false
      FINAL_QUOTE_PUNCTUATION   | false
      
      CASED_LETTER              | true
      LETTER                    | true
      MARK                      | true
      NUMBER                    | true
      PUNCTUATION               | true
      SYMBOL                    | true
      SEPARATOR                 | true
      OTHER                     | true
      """)
  void isCompositeCategory_returnsFalseForLeafCategories(final Category category, final boolean expected) {
    assertThat(category.isCompositeCategory()).isEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] codePoint={0} category={1} expected={2}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
    CODE_POINT | CATEGORY          | EXPECTED
    65         | UPPERCASE_LETTER  | true
    97         | LOWERCASE_LETTER  | true
    48         | DECIMAL_DIGIT_NUMBER | true
    65         | LOWERCASE_LETTER  | false
    48         | UPPERCASE_LETTER  | false
    32         | SPACE_SEPARATOR   | true
    32         | UPPERCASE_LETTER  | false
    33         | OTHER_PUNCTUATION | true
    36         | CURRENCY_SYMBOL   | true
    65         | LETTER            | true
    97         | LETTER            | true
    65         | CASED_LETTER      | true
    97         | CASED_LETTER      | true
    48         | NUMBER            | true
    32         | SEPARATOR         | true
    33         | PUNCTUATION       | true
    36         | SYMBOL            | true
    """)
  void codePointIsInOneOfTheCategories_singleCategory(
      final int codePoint, final Category category, final boolean expected) {
    assertThat(Category.codePointIsInOneOfTheCategories(codePoint, category.bitMask))
        .as("codePointIsInOneOfTheCategories(%d, %s) should be %b", codePoint, category, expected)
        .isEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] codePoint={0} categories={1},{2} expected={3}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
    CODE_POINT | CATEGORY_1        | CATEGORY_2          | EXPECTED
    65         | UPPERCASE_LETTER  | DECIMAL_DIGIT_NUMBER | true
    97         | UPPERCASE_LETTER  | LOWERCASE_LETTER     | true
    48         | UPPERCASE_LETTER  | DECIMAL_DIGIT_NUMBER | true
    33         | UPPERCASE_LETTER  | DECIMAL_DIGIT_NUMBER | false
    32         | UPPERCASE_LETTER  | DECIMAL_DIGIT_NUMBER | false
    """)
  void codePointIsInOneOfTheCategories_multipleCategories(
      final int codePoint,
      final Category category1,
      final Category category2,
      final boolean expected) {
    final long combinedBitFlags = category1.bitMask | category2.bitMask;
    assertThat(Category.codePointIsInOneOfTheCategories(codePoint, combinedBitFlags))
        .as("codePointIsInOneOfTheCategories(%d, %s | %s) should be %b",
            codePoint, category1, category2, expected)
        .isEqualTo(expected);
  }

  @Test
  void codePointIsInOneOfTheCategories_zeroBitFlags_returnsFalse() {
    assertThat(Category.codePointIsInOneOfTheCategories('A', 0L))
        .as("codePointIsInOneOfTheCategories with zero bitFlags should always return false")
        .isFalse();
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @EnumSource(Category.class)
  void codePointIsInOneOfTheCategories_allLeafCategories_matchesCharacterGetType(final Category category) {

    // Skip composite categories – their bitMask covers multiple Character.getType values
    if (category.isCompositeCategory()) {
      return;
    }

    // Find a code point whose Character.getType matches the leaf category's single bit position
    final int bitPosition = Long.numberOfTrailingZeros(category.bitMask);

    // Scan BMP for first matching code point
    for (int codePoint = 0; codePoint <= 0xFFFF; codePoint++) {
      if (Character.getType(codePoint) == bitPosition) {
        assertThat(Category.codePointIsInOneOfTheCategories(codePoint, category.bitMask))
            .as("codePoint U+%04X should be in category %s", codePoint, category)
            .isTrue();
        break;
      }
    }
  }

}
