package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.Category;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.TreeNode;
import org.typefactory.impl.Converter.ConverterResults;


class CodePointSequenceToCodePointSequenceConverterTest {

  private static Logger logger = Logger.getLogger(CodePointSequenceToCodePointSequenceConverter.class.getName());

  private PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence;

  @Test
  void constructor_ShouldInitializeFieldsCorrectly() {
    final var rootTreeNode = new RootTreeNode();
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    assertThat(converter.isEmpty()).isTrue();
    assertThat(converter.getMaxConvertedLength()).isZero();
  }

  @Test
  void isEmpty_ShouldReturnTrue_WhenRootTreeNodeIsEmpty() {
    final var rootTreeNode = new RootTreeNode();
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    assertThat(converter.isEmpty()).isTrue();
  }

  @Test
  void isEmpty_ShouldReturnFalse_WhenRootTreeNodeIsNotEmpty() {
    final var rootTreeNode = new RootTreeNode();
    rootTreeNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    assertThat(converter.isEmpty()).isFalse();
  }

  @Test
  void getMaxConvertedLength_ShouldReturnCorrectValue() {
    final var rootTreeNode = new RootTreeNode();
    rootTreeNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    assertThat(converter.getMaxConvertedLength()).isEqualTo(1);
  }

  @Test
  void codePointConversionIsRequired_ShouldReturnTrue_WhenConversionIsIsRequired() {
    final var rootTreeNode = new RootTreeNode();
    rootTreeNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    final ConverterResults results = converter.createConverterResults();
    final boolean result = converter.codePointConversionIsRequired('a', 0, results);
    assertThat(result).isTrue();
    assertThat(results.getConvertFromIndex()).isZero();
    assertThat(results.getConvertToCodePointSequence()).containsExactly('b');
  }

  @Test
  void codePointConversionIsRequired_ShouldReturnFalse_WhenConversionIsNotIsRequired() {
    final var rootTreeNode = new RootTreeNode();
    rootTreeNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    final ConverterResults results = converter.createConverterResults();
    final boolean result = converter.codePointConversionIsRequired('c', 0, results);
    assertThat(result).isFalse();
  }

  @Test
  void codePointConversionIsRequired_ShouldThrowException_WhenInvalidConverterResults() {
    final var rootTreeNode = new RootTreeNode();
    final var converter = new CodePointSequenceToCodePointSequenceConverter(rootTreeNode, categoryToCodePointSequence);
    final var someConverterResults = new SomeConverterResults();
    assertThatThrownBy(() -> converter.codePointConversionIsRequired('a', 0, someConverterResults))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid argument - 'converterResults' must be of type");
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
      CATEGORY             | VALUE | REQUIRES_CONVERSION | CONVERT_TO_CODE_POINT_SEQUENCE
      PUNCTUATION          | a     | false               |
      PUNCTUATION          | 1     | false               |
      PUNCTUATION          | ?     | true                | aaa
      LETTER               | 1     | false               |
      LETTER               | a     | true                | bbb
      LETTER               | 2     | false               |
      DECIMAL_DIGIT_NUMBER | ?     | false               |
      DECIMAL_DIGIT_NUMBER | a     | false               |
      DECIMAL_DIGIT_NUMBER | 1     | true                | x
      """)
  void codePointConversionIsRequired_ShouldReturnTrue_WhenCategoryConversionIsIsRequired(
      final Category category, final char value, final boolean requiresConversion, final String convertToCodePointSequence) {

    categoryToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    for (final int categoryCode : category.getCharacterCategories()) {
      categoryToCodePointSequence.put(categoryCode, convertToCodePointSequence == null ? EMPTY_INT_ARRAY : convertToCodePointSequence.codePoints().toArray());
    }
    final var converter = new CodePointSequenceToCodePointSequenceConverter(new RootTreeNode(), categoryToCodePointSequence);
    final ConverterResults results = converter.createConverterResults();
    final boolean result = converter.codePointConversionIsRequired(value, 0, results);
    assertThat(result).isEqualTo(requiresConversion);
    if (requiresConversion) {
      assertThat(results.getConvertFromIndex()).isZero();
      assertThat(results.getConvertToCodePointSequence()).containsExactly(convertToCodePointSequence.codePoints().toArray());
    }
  }

  @Test
  void nAryTreeIsFormedCorrectly() {
    final var rootTreeNode = new RootTreeNode();
    rootTreeNode.add("abc".codePoints().toArray(), "".codePoints().toArray());
    rootTreeNode.add("abcd".codePoints().toArray(), "woof".codePoints().toArray());
    rootTreeNode.add("abcd".codePoints().toArray(), "xyz".codePoints().toArray()); // Overwrites earlier entry
    rootTreeNode.add("abce".codePoints().toArray(), "moo".codePoints().toArray());
    rootTreeNode.add("abcf".codePoints().toArray(), "meow".codePoints().toArray());
    rootTreeNode.add("abcfg".codePoints().toArray(), "roar".codePoints().toArray());
    rootTreeNode.add("abcg".codePoints().toArray(), "baa".codePoints().toArray());
    rootTreeNode.add("abef".codePoints().toArray(), "tuv".codePoints().toArray());
    rootTreeNode.add("full stop".codePoints().toArray(), "period".codePoints().toArray());
    rootTreeNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    rootTreeNode.add("l".codePoints().toArray(), "n".codePoints().toArray());

    logger.fine(() -> "N-Ary tree size = " + rootTreeNode.size());
    logger.fine(() -> "N-Ary tree" + SYSTEM_LINE_SEPARATOR + rootTreeNode);

    assertThat(rootTreeNode.isEmpty()).isFalse();
    assertThat(rootTreeNode.isLeafNode()).isFalse();
    assertThat(rootTreeNode.codePoints()).contains('a', 'f', 'l');
    assertThat(rootTreeNode.size()).isEqualTo(10); // 10 separate sequences
    assertThat(rootTreeNode.getMaxToSequenceLength()).isEqualTo(6);

    final TreeNode node = rootTreeNode.get('a');
    assertThat(node.isLeafNode()).isFalse();
    assertThat(node.codePoints()).contains('b');

    assertThat(rootTreeNode).hasToString("""
        •
        ├a ⟶ "b"
        │└b
        │ ├c ⟶ ""
        │ │├d ⟶ "xyz"
        │ │├e ⟶ "moo"
        │ │├f ⟶ "meow"
        │ ││└g ⟶ "roar"
        │ │└g ⟶ "baa"
        │ └e
        │  └f ⟶ "tuv"
        ├f
        │└u
        │ └l
        │  └l
        │   └\s
        │    └s
        │     └t
        │      └o
        │       └p ⟶ "period"
        └l ⟶ "n"
        """);
  }

  @ParameterizedTest
  @CsvSource(textBlock = """
      NULL_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE      | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | true
      EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE     | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | true
      NON_EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | false
      NULL_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE      | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | true
      EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE     | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | true
      NON_EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | false
      NULL_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE      | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE     | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      NON_EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      """, delimiter = '|')
  void isEmpty_returnsAsExpected(
      final CodePointSequenceToCodePointSequenceRootTreeNodeEnum codePointSequenceToCodePointSequenceRootTreeNodeEnum,
      final CategoryToCodePointSequenceEnum categoryToCodePointSequenceEnum,
      final boolean expected) {

    final CodePointSequenceToCodePointSequenceConverter converter =
        new CodePointSequenceToCodePointSequenceConverter(
            codePointSequenceToCodePointSequenceRootTreeNodeEnum.getRootTreeNode(),
            categoryToCodePointSequenceEnum.getCategoryToCodePointSequence());

    assertThat(converter.isEmpty()).isEqualTo(expected);
  }

  private static RootTreeNode createNonEmptyRootTreeNode() {
    final RootTreeNode rootNode = new RootTreeNode();
    rootNode.add("abc".codePoints().toArray(), "xzy".codePoints().toArray());
    return rootNode;
  }

  private static PrimitiveHashMapOfIntKeyToIntArrayValue createNonEmptyCategoryToCodePointSequence() {
    final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    codePointToCodePointSequence.put(Character.LOWERCASE_LETTER, "#".codePoints().toArray());
    return codePointToCodePointSequence;
  }

  private enum CodePointSequenceToCodePointSequenceRootTreeNodeEnum {
    NULL_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE(null),
    EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE(new RootTreeNode()),
    NON_EMPTY_CODE_POINT_SEQUENCE_TO_CODE_POINT_SEQUENCE(createNonEmptyRootTreeNode());
    private final RootTreeNode rootTreeNode;

    CodePointSequenceToCodePointSequenceRootTreeNodeEnum(final RootTreeNode rootTreeNode) {
      this.rootTreeNode = rootTreeNode;
    }

    public RootTreeNode getRootTreeNode() {
      return rootTreeNode;
    }
  }

  private enum CategoryToCodePointSequenceEnum {
    NULL_CATEGORY_TO_CODE_POINT_SEQUENCE(null),
    EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE(new PrimitiveHashMapOfIntKeyToIntArrayValue()),
    NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE(createNonEmptyCategoryToCodePointSequence());
    private final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence;

    CategoryToCodePointSequenceEnum(final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence) {
      this.categoryToCodePointSequence = codePointToCodePointSequence;
    }

    public PrimitiveHashMapOfIntKeyToIntArrayValue getCategoryToCodePointSequence() {
      return categoryToCodePointSequence;
    }
  }


  static final class SomeConverterResults implements ConverterResults {

    private int convertFromIndex;
    private int [] convertToCodePointSequence;

    @Override
    public int getConvertFromIndex() {
      return convertFromIndex;
    }

    @Override
    public int [] getConvertToCodePointSequence() {
      return convertToCodePointSequence;
    }
  }
}
