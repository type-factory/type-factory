package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Category;
import org.typefactory.Subset;

class SubsetAssertTest {

  @Test
  void subsetAssert_hasCustomAssertionMethods() {
    final var someActual = Subset.builder().build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);
    assertThat(subsetAssert.getClass())
        .hasPublicMethods(
            "containsAllCharacters",
            "containsAllCodePoints",
            "containsCharacter",
            "containsCodePoint",
            "containsCategory",
            "containsExactlyCategories",
            "isEmpty",
            "isNotEmpty");
  }

  @Test
  void assertThat_returnsSubsetAssertInstance() {
    final var someActual = Subset.builder().build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);
    assertThat(subsetAssert)
        .isInstanceOf(SubsetAssert.class)
        .isInstanceOf(AbstractSubsetAssert.class);
  }

  @Test
  void isEmpty_throwsAssertionErrorForNullActual() {
    final var someActual = (Subset) null;
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(subsetAssert::isEmpty)
        .withMessage("\nExpecting actual not to be null");
  }

  @Test
  void isEmpty_throwsAssertionErrorForNonEmptySubset() {
    final var someActual = Subset.builder().includeChars('a', 'b', 'c').build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(subsetAssert::isEmpty)
        .withMessage("\nExpecting that actual Subset is empty but it is not.");
  }

  @Test
  void isEmpty_doesNotThrowAssertionErrorForEmptyActual() {
    final var someActual = Subset.builder().build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(subsetAssert::isEmpty);
  }

  @Test
  void isNotEmpty_throwsAssertionErrorForNullActual() {
    final var someActual = (Subset) null;
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(subsetAssert::isNotEmpty)
        .withMessage("\nExpecting actual not to be null");
  }

  @Test
  void isNotEmpty_throwsAssertionErrorForEmptySubset() {
    final var someActual = Subset.builder().build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(subsetAssert::isNotEmpty)
        .withMessage("\nExpecting that actual Subset is not empty but it is.");
  }

  @Test
  void isNotEmpty_doesNotThrowAssertionErrorForNomEmptyActual() {
    final var someActual = Subset.builder().includeChars('a', 'b', 'c').build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(subsetAssert::isNotEmpty);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CHARACTERS | ASSERT_CONTAINS
      abcdΣωπ           | a
      abcdΣωπ           | b
      abcdΣωπ           | c
      abcdΣωπ           | d
      abcdΣωπ           | Σ
      abcdΣωπ           | ω
      abcdΣωπ           | π
      """)
  void containsCharacter_doesNotThrowAssertionError(
      final String subsetCharacters, final char characterToContain) {

    final var someActual = Subset.builder()
        .includeChars(subsetCharacters.toCharArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsCharacter(characterToContain));
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CHARACTERS | ASSERT_CONTAINS | EXPECTED_ERROR_MESSAGE
      abcdΣωπ           | e               | '\nExpected actual Subset: \s\nto contain character:  e\nbut no such character was found in the Subset.'
      abcdΣωπ           | f               | '\nExpected actual Subset: \s\nto contain character:  f\nbut no such character was found in the Subset.'
      abcdΣωπ           | g               | '\nExpected actual Subset: \s\nto contain character:  g\nbut no such character was found in the Subset.'
      abcdΣωπ           | h               | '\nExpected actual Subset: \s\nto contain character:  h\nbut no such character was found in the Subset.'
      abcdΣωπ           | μ               | '\nExpected actual Subset: \s\nto contain character:  μ\nbut no such character was found in the Subset.'
      abcdΣωπ           | θ               | '\nExpected actual Subset: \s\nto contain character:  θ\nbut no such character was found in the Subset.'
      abcdΣωπ           | β               | '\nExpected actual Subset: \s\nto contain character:  β\nbut no such character was found in the Subset.'
      abcdΣωπ           | '\n'            | '\nExpected actual Subset: \s\nto contain character:  U+000A\nbut no such character was found in the Subset.'
      """)
  void containsCharacter_throwsAssertionError(
      final String subsetCharacters, final char characterToContain, final String expectedMessage) {

    final var someActual = Subset.builder()
        .includeChars(subsetCharacters.toCharArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> subsetAssert.containsCharacter(characterToContain))
        .withMessage(expectedMessage);
  }


  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CHARACTERS | ASSERT_CONTAINS
      abcd              | abcd
      abcd              | dbac
      abcd              | abc
      abcd              | bcd
      abcd              | ac
      abcd              | bd
      abcd              | cd
      """)
  void containsAllCharacters_doesNotThrowAssertionError(
      final String subsetCharacters, final String charactersToContain) {

    final var charactersToContainArray = charactersToContain.toCharArray();
    final var someActual = Subset.builder()
        .includeChars(subsetCharacters.toCharArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsAllCharacters(charactersToContainArray));
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CHARACTERS | ASSERT_CONTAINS | EXPECTED_ERROR_MESSAGE
      abcd              | abce            | '\nExpected actual Subset:  \nto contain all characters:  [a,b,c,e]\ncontained expected characters:  [a,b,c]\nmissing expected characters:  [e]'
      abcd              | bcxy            | '\nExpected actual Subset:  \nto contain all characters:  [b,c,x,y]\ncontained expected characters:  [b,c]\nmissing expected characters:  [x,y]'
      abcd              | e               | '\nExpected actual Subset:  \nto contain all characters:  [e]\nmissing expected characters:  [e]'
      abcd              | efg             | '\nExpected actual Subset:  \nto contain all characters:  [e,f,g]\nmissing expected characters:  [e,f,g]'
      """)
  void containsAllCharacters_throwsAssertionError(
      final String subsetCharacters, final String charactersToContain, final String expectedMessage) {

    final var charactersToContainArray = charactersToContain.toCharArray();
    final var someActual = Subset.builder()
        .includeChars(subsetCharacters.toCharArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> subsetAssert.containsAllCharacters(charactersToContainArray))
        .withMessage(expectedMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CODEPOINTS | ASSERT_CONTAINS
      abcdΣωπ           | a
      abcdΣωπ           | b
      abcdΣωπ           | c
      abcdΣωπ           | d
      abcdΣωπ           | Σ
      abcdΣωπ           | ω
      abcdΣωπ           | π
      abcdΣωπ😀         | 😀
      """)
  void containsCodePoint_doesNotThrowAssertionError(
      final String subsetCodePoints, final String codePointToContain) {

    final var someActual = Subset.builder()
        .includeCodePoints(subsetCodePoints.codePoints().toArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsCodePoint(codePointToContain.codePointAt(0)));
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CHARACTERS | ASSERT_CONTAINS | EXPECTED_ERROR_MESSAGE
      abcdΣωπ           | e               | '\nExpected actual Subset: \s\nto contain code-point:  e\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | f               | '\nExpected actual Subset: \s\nto contain code-point:  f\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | g               | '\nExpected actual Subset: \s\nto contain code-point:  g\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | h               | '\nExpected actual Subset: \s\nto contain code-point:  h\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | μ               | '\nExpected actual Subset: \s\nto contain code-point:  μ\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | θ               | '\nExpected actual Subset: \s\nto contain code-point:  θ\nbut no such code-point was found in the Subset.'
      abcdΣωπ           | β               | '\nExpected actual Subset: \s\nto contain code-point:  β\nbut no such code-point was found in the Subset.'
      abcdΣωπ😀         | 😜              | '\nExpected actual Subset: \s\nto contain code-point:  😜\nbut no such code-point was found in the Subset.'
      """)
  void containsCodePoint_throwsAssertionError(
      final String subsetCharacters, final String codePointToContain, final String expectedMessage) {

    final var someActual = Subset.builder()
        .includeChars(subsetCharacters.toCharArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> subsetAssert.containsCodePoint(codePointToContain.codePointAt(0)))
        .withMessage(expectedMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CODEPOINTS | ASSERT_CONTAINS
      abcd              | abcd
      abcd              | dbac
      abcd              | abc
      abcd              | bcd
      abcd              | ac
      abcd              | bd
      abcd              | cd
      """)
  void containsAllCodePoints_doesNotThrowAssertionError(
      final String subsetCodePoints, final String codePointsToContain) {

    final var codePointsToContainArray = codePointsToContain.codePoints().toArray();
    final var someActual = Subset.builder()
        .includeCodePoints(subsetCodePoints.codePoints().toArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsAllCodePoints(codePointsToContainArray));
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CODEPOINTS | ASSERT_CONTAINS | EXPECTED_ERROR_MESSAGE
      abcd              | abce            | '\nExpected actual Subset:  \nto contain all code-points:  [a,b,c,e]\ncontained expected code-points:  [a,b,c]\nmissing expected code-points:  [e]'
      abcd              | bcxy            | '\nExpected actual Subset:  \nto contain all code-points:  [b,c,x,y]\ncontained expected code-points:  [b,c]\nmissing expected code-points:  [x,y]'
      abcd              | e               | '\nExpected actual Subset:  \nto contain all code-points:  [e]\nmissing expected code-points:  [e]'
      abcd              | efg             | '\nExpected actual Subset:  \nto contain all code-points:  [e,f,g]\nmissing expected code-points:  [e,f,g]'
      """)
  void containsAllCodePoints_throwsAssertionError(
      final String subsetCodePoints, final String codePointsToContain, final String expectedMessage) {

    final var codePointsToContainArray = codePointsToContain.codePoints().toArray();
    final var someActual = Subset.builder()
        .includeCodePoints(subsetCodePoints.codePoints().toArray())
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> subsetAssert.containsAllCodePoints(codePointsToContainArray))
        .withMessage(expectedMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CATEGORIES | ASSERT_CONTAINS_CATEGORY
      CASED_LETTER      | UPPERCASE_LETTER
      CASED_LETTER      | LOWERCASE_LETTER
      CASED_LETTER      | TITLECASE_LETTER
      LETTER            | CASED_LETTER
      LETTER            | UPPERCASE_LETTER
      LETTER            | LOWERCASE_LETTER
      LETTER            | TITLECASE_LETTER
      LETTER            | MODIFIER_LETTER
      LETTER            | OTHER_LETTER
      """)
  void containsCategory_doesNotThrowAssertionError(
      final Category subsetCategory, final Category categoryToContain) {

    final var someActual = Subset.builder()
        .includeUnicodeCategory(subsetCategory)
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsCategory(categoryToContain));
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CATEGORIES | ASSERT_CONTAINS_CATEGORY | EXPECTED_ERROR_MESSAGE
      CASED_LETTER      | NON_SPACING_MARK         | '\nExpected actual Subset: \s\nto contain category:  NON_SPACING_MARK\nbut no such category was found in the Subset.'
      CASED_LETTER      | COMBINING_SPACING_MARK   | '\nExpected actual Subset: \s\nto contain category:  COMBINING_SPACING_MARK\nbut no such category was found in the Subset.'
      CASED_LETTER      | DECIMAL_DIGIT_NUMBER     | '\nExpected actual Subset: \s\nto contain category:  DECIMAL_DIGIT_NUMBER\nbut no such category was found in the Subset.'
      LETTER            | NON_SPACING_MARK         | '\nExpected actual Subset: \s\nto contain category:  NON_SPACING_MARK\nbut no such category was found in the Subset.'
      LETTER            | COMBINING_SPACING_MARK   | '\nExpected actual Subset: \s\nto contain category:  COMBINING_SPACING_MARK\nbut no such category was found in the Subset.'
      LETTER            | DECIMAL_DIGIT_NUMBER     | '\nExpected actual Subset: \s\nto contain category:  DECIMAL_DIGIT_NUMBER\nbut no such category was found in the Subset.'
      LETTER            | LETTER_NUMBER            | '\nExpected actual Subset: \s\nto contain category:  LETTER_NUMBER\nbut no such category was found in the Subset.'
      LETTER            | OTHER_NUMBER             | '\nExpected actual Subset: \s\nto contain category:  OTHER_NUMBER\nbut no such category was found in the Subset.'
      """)
  void containsCategory_throwsAssertionError(
      final Category subsetCategory, final Category categoryToContain, final String expectedMessage) {

    final var someActual = Subset.builder()
        .includeUnicodeCategory(subsetCategory)
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> subsetAssert.containsCategory(categoryToContain))
        .withMessage(expectedMessage);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      SUBSET_CATEGORIES                                        | ASSERT_CONTAINS_CATEGORY
      [CASED_LETTER]                                           | [CASED_LETTER]
      [CASED_LETTER]                                           | [UPPERCASE_LETTER,LOWERCASE_LETTER,TITLECASE_LETTER]
      [LETTER,DECIMAL_DIGIT_NUMBER]                            | [LETTER,DECIMAL_DIGIT_NUMBER]
      [LETTER,DECIMAL_DIGIT_NUMBER]                            | [CASED_LETTER,UPPERCASE_LETTER,LOWERCASE_LETTER,TITLECASE_LETTER,MODIFIER_LETTER,OTHER_LETTER,DECIMAL_DIGIT_NUMBER]
      [DECIMAL_DIGIT_NUMBER]                                   | [DECIMAL_DIGIT_NUMBER]
      [DECIMAL_DIGIT_NUMBER,UPPERCASE_LETTER,LOWERCASE_LETTER] | [DECIMAL_DIGIT_NUMBER,UPPERCASE_LETTER,LOWERCASE_LETTER]
      """)
  void containsExactlyCategories_doesNotThrowAssertionError(
      @ConvertWith(CategoryArrayConverter.class) final Category[] subsetCategories,
      @ConvertWith(CategoryArrayConverter.class) final Category[] categoriesToContain) {

    final var someActual = Subset.builder()
        .includeUnicodeCategories(subsetCategories)
        .build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);

    assertThatNoException()
        .isThrownBy(() -> subsetAssert.containsExactlyCategories(categoriesToContain));
  }

}
