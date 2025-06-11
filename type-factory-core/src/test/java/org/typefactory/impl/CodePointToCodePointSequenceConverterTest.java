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
