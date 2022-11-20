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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class UnsignedIntegerSortTest {

  @Test
  void sort_shouldThrowExceptionWhenIndexesAreOutOfOrder() {
    final var values = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> UnsignedIntegerSort.sort(values, 2, 1))
            .withMessage("fromIndex > toIndex");
  }

  @Test
  void sort_shouldThrowExceptionWhenLowerIndexIsOutOfBounds() {
    final var values = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};

    assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class)
        .isThrownBy(() -> UnsignedIntegerSort.sort(values, -1, 2))
        .withMessage("Array index out of range: -1");
  }

  @Test
  void sort_shouldThrowExceptionWhenUpperIndexIsOutOfBounds() {
    final var values = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};

    assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class)
        .isThrownBy(() -> UnsignedIntegerSort.sort(values, 0, 4))
        .withMessage("Array index out of range: 4");
  }

  @Test
  void sort_shouldHandleEmptyArray() {
    final var values = new int[0];
    final var expected = new int[0];

    UnsignedIntegerSort.sort(values);
    assertThat(values).isEmpty();
  }

  @Test
  void sort_shouldHandleArrayWithOneElement() {
    final var values = new int[]{0x0000_0001};
    final var expected = new int[]{0x0000_0001};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithTwoElementsInOrder() {
    final var values = new int[]{0x0000_0001, 0xffff_ffff};
    final var expected = new int[]{0x0000_0001, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithTwoElementsOutOfOrder() {
    final var values = new int[]{0xffff_ffff, 0x0000_0000};
    final var expected = new int[]{0x0000_0000, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithThreeElementsInOrder() {
    final var values = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};
    final var expected = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithThreeElementsOutOfOrder() {
    final var values = new int[]{0xffff_ffff, 0x0000_0000, 0x8888_8888};
    final var expected = new int[]{0x0000_0000, 0x8888_8888, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithFiveElementsInOrder() {
    final var values = new int[]{0x0000_0000, 0x0000_0001, 0x8888_8888, 0xffff_fffe, 0xffff_ffff};
    final var expected = new int[]{0x0000_0000, 0x0000_0001, 0x8888_8888, 0xffff_fffe, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithFiveElementsOutOfOrder() {
    final var values = new int[]{0x0000_0001, 0xffff_ffff, 0x0000_0000, 0x8888_8888, 0xffff_fffe};
    final var expected = new int[]{0x0000_0000, 0x0000_0001, 0x8888_8888, 0xffff_fffe, 0xffff_ffff};

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }


  @Test
  void sort_shouldHandleArrayWithThirtyElementsInOrder() {
    final var values = new int[]{
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_2200, 0x2222_2211,
        0x8888_8800, 0x8888_8822, 0x8888_8844, 0x8888_8866, 0x8888_8877,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xcccc_cc00, 0xcccc_cc99, 0xcccc_ccaa, 0xcccc_cccb, 0xcccc_cccc,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_ffff,
    };
    final var expected = new int[]{
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_2200, 0x2222_2211,
        0x8888_8800, 0x8888_8822, 0x8888_8844, 0x8888_8866, 0x8888_8877,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xcccc_cc00, 0xcccc_cc99, 0xcccc_ccaa, 0xcccc_cccb, 0xcccc_cccc,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_ffff,
    };

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithThirtyElementsInOppositeOrder() {
    final var values = new int[]{
        0xffff_ffff, 0xffff_fffe, 0xffff_ffaa, 0xffff_ff99, 0xffff_ff00,
        0xcccc_cccc, 0xcccc_cccb, 0xcccc_ccaa, 0xcccc_cc99, 0xcccc_cc00,
        0xaaaa_aa99, 0xaaaa_aa66, 0xaaaa_aa44, 0xaaaa_aa22, 0xaaaa_aa00,
        0x8888_8877, 0x8888_8866, 0x8888_8844, 0x8888_8822, 0x8888_8800,
        0x2222_2211, 0x2222_2200, 0x2222_1111, 0x2222_1100, 0x2222_0000,
        0x0000_0044, 0x0000_0033, 0x0000_0022, 0x0000_0001, 0x0000_0000,
    };
    final var expected = new int[]{
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_2200, 0x2222_2211,
        0x8888_8800, 0x8888_8822, 0x8888_8844, 0x8888_8866, 0x8888_8877,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xcccc_cc00, 0xcccc_cc99, 0xcccc_ccaa, 0xcccc_cccb, 0xcccc_cccc,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_ffff,
    };

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithFortyElementsContainingDoubles() {
    final var values = new int[]{
        0xffff_ffff, 0xffff_fffe, 0xffff_ffaa, 0xffff_ff99, 0xffff_ff00,
        0xaaaa_aa99, 0xaaaa_aa66, 0xaaaa_aa44, 0xaaaa_aa22, 0xaaaa_aa00,
        0x2222_2211, 0x2222_2200, 0x2222_1111, 0x2222_1100, 0x2222_0000,
        0x0000_0044, 0x0000_0033, 0x0000_0022, 0x0000_0001, 0x0000_0000,
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_2200, 0x2222_2211,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_ffff,
    };
    final var expected = new int[]{
        0x0000_0000, 0x0000_0000, 0x0000_0001, 0x0000_0001, 0x0000_0022,
        0x0000_0022, 0x0000_0033, 0x0000_0033, 0x0000_0044, 0x0000_0044,
        0x2222_0000, 0x2222_0000, 0x2222_1100, 0x2222_1100, 0x2222_1111,
        0x2222_1111, 0x2222_2200, 0x2222_2200, 0x2222_2211, 0x2222_2211,
        0xaaaa_aa00, 0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa22, 0xaaaa_aa44,
        0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa66, 0xaaaa_aa99, 0xaaaa_aa99,
        0xffff_ff00, 0xffff_ff00, 0xffff_ff99, 0xffff_ff99, 0xffff_ffaa,
        0xffff_ffaa, 0xffff_fffe, 0xffff_fffe, 0xffff_ffff, 0xffff_ffff,
    };

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithThirtyElementsOutOfOrder() {
    final var values = new int[]{
        0xffff_ffff, 0x8888_8844, 0x2222_1100, 0x0000_0001, 0xaaaa_aa00,
        0x8888_8800, 0x0000_0022, 0x2222_1111, 0xffff_ffaa, 0xcccc_cc99,
        0xaaaa_aa66, 0x2222_1111, 0x8888_8877, 0x2222_0000, 0x0000_0000,
        0xffff_ff00, 0xcccc_ccaa, 0xaaaa_aa44, 0x0000_0033, 0x0000_0044,
        0xcccc_cc00, 0x8888_8822, 0xaaaa_aa99, 0xcccc_cccb, 0xcccc_cccc,
        0x2222_2211, 0x8888_8866, 0xaaaa_aa22, 0xffff_ff99, 0xffff_fffe,
    };
    final var expected = new int[]{
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_1111, 0x2222_2211,
        0x8888_8800, 0x8888_8822, 0x8888_8844, 0x8888_8866, 0x8888_8877,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xcccc_cc00, 0xcccc_cc99, 0xcccc_ccaa, 0xcccc_cccb, 0xcccc_cccc,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_ffff,
    };

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

  @Test
  void sort_shouldHandleArrayWithThirtyOneElementsOutOfOrder() {
    final var values = new int[]{
        0xffff_ffff, 0x8888_8844, 0x2222_1100, 0x0000_0001, 0xaaaa_aa00,
        0x8888_8800, 0x0000_0022, 0x2222_1111, 0xffff_ffaa, 0xcccc_cc99,
        0xaaaa_aa66, 0x2222_1111, 0x8888_8877, 0x2222_0000, 0x0000_0000,
        0xffff_ff00, 0xcccc_ccaa, 0xaaaa_aa44, 0x0000_0033, 0x0000_0044,
        0xcccc_cc00, 0x8888_8822, 0xaaaa_aa99, 0xcccc_cccb, 0xcccc_cccc,
        0x2222_2211, 0x8888_8866, 0xaaaa_aa22, 0xffff_ff99, 0xffff_fffe,
        0xffff_fffe,
    };
    final var expected = new int[]{
        0x0000_0000, 0x0000_0001, 0x0000_0022, 0x0000_0033, 0x0000_0044,
        0x2222_0000, 0x2222_1100, 0x2222_1111, 0x2222_1111, 0x2222_2211,
        0x8888_8800, 0x8888_8822, 0x8888_8844, 0x8888_8866, 0x8888_8877,
        0xaaaa_aa00, 0xaaaa_aa22, 0xaaaa_aa44, 0xaaaa_aa66, 0xaaaa_aa99,
        0xcccc_cc00, 0xcccc_cc99, 0xcccc_ccaa, 0xcccc_cccb, 0xcccc_cccc,
        0xffff_ff00, 0xffff_ff99, 0xffff_ffaa, 0xffff_fffe, 0xffff_fffe,
        0xffff_ffff,
    };

    assertThat(values).containsExactlyInAnyOrder(expected);

    UnsignedIntegerSort.sort(values);
    assertThat(values).containsExactly(expected);
  }

}
