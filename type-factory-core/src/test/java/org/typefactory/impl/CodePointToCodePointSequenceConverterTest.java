package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CodePointToCodePointSequenceConverterTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      NULL_CODE_POINT_TO_CODE_POINT_SEQUENCE      | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | true
      EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE     | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | true
      NON_EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE | NULL_CATEGORY_TO_CODE_POINT_SEQUENCE      | false
      NULL_CODE_POINT_TO_CODE_POINT_SEQUENCE      | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | true
      EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE     | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | true
      NON_EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE | EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE     | false
      NULL_CODE_POINT_TO_CODE_POINT_SEQUENCE      | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE     | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      NON_EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE | NON_EMPTY_CATEGORY_TO_CODE_POINT_SEQUENCE | false
      """, delimiter = '|')
  void isEmpty_returnsAsExpected(
      final CodePointToCodePointSequenceEnum codePointToCodePointSequenceEnum,
      final CategoryToCodePointSequenceEnum categoryToCodePointSequenceEnum,
      final boolean expected) {

    final CodePointToCodePointSequenceConverter converter =
        new CodePointToCodePointSequenceConverter(
            codePointToCodePointSequenceEnum.getCodePointToCodePointSequence(),
            categoryToCodePointSequenceEnum.getCategoryToCodePointSequence());

    assertThat(converter.isEmpty()).isEqualTo(expected);
  }


  private static PrimitiveHashMapOfIntKeyToIntArrayValue createNonEmptyCodePointToCodePointSequence() {
    final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    codePointToCodePointSequence.put('a', "bc".codePoints().toArray());
    return codePointToCodePointSequence;
  }

  private static PrimitiveHashMapOfIntKeyToIntArrayValue createNonEmptyCategoryToCodePointSequence() {
    final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence = new PrimitiveHashMapOfIntKeyToIntArrayValue();
    codePointToCodePointSequence.put(Character.LOWERCASE_LETTER, "#".codePoints().toArray());
    return codePointToCodePointSequence;
  }

  private enum CodePointToCodePointSequenceEnum {
    NULL_CODE_POINT_TO_CODE_POINT_SEQUENCE(null),
    EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE(new PrimitiveHashMapOfIntKeyToIntArrayValue()),
    NON_EMPTY_CODE_POINT_TO_CODE_POINT_SEQUENCE(createNonEmptyCodePointToCodePointSequence());
    private final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence;

    CodePointToCodePointSequenceEnum(final PrimitiveHashMapOfIntKeyToIntArrayValue codePointToCodePointSequence) {
      this.codePointToCodePointSequence = codePointToCodePointSequence;
    }

    public PrimitiveHashMapOfIntKeyToIntArrayValue getCodePointToCodePointSequence() {
      return codePointToCodePointSequence;
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
