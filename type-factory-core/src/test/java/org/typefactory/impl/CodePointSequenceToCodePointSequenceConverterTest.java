package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.impl.Constants.LINE_SEPARATOR;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;


class CodePointSequenceToCodePointSequenceConverterTest {

  private static Logger logger = Logger.getLogger(CodePointSequenceToCodePointSequenceConverter.class.getName());

  @Test
  void nAryTreeIsFormedCorrectly() {
    final RootTreeNode rootNode = new RootTreeNode();
    rootNode.add("abc".codePoints().toArray(), "".codePoints().toArray());
    rootNode.add("abcd".codePoints().toArray(), "xyz".codePoints().toArray());
    rootNode.add("abef".codePoints().toArray(), "tuv".codePoints().toArray());
    rootNode.add("full stop".codePoints().toArray(), "period".codePoints().toArray());
    rootNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    rootNode.add("l".codePoints().toArray(), "n".codePoints().toArray());

    logger.fine(() -> "N-Ary tree size = " + rootNode.size());
    logger.fine(() -> "N-Ary tree" + LINE_SEPARATOR + rootNode);

    assertThat(rootNode.isEmpty()).isFalse();
    assertThat(rootNode.codePoints()).contains('a', 'f', 'l');
    assertThat(rootNode.size()).isEqualTo(6); // 6 separate sequences

    //TODO add more assertions
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

}
