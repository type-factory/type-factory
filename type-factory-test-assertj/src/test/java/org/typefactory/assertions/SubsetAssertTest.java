package org.typefactory.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractComparableAssert;
import org.junit.jupiter.api.Test;
import org.typefactory.Subset;

class SubsetAssertTest {

//  @Test
//  void subsetAssert_hasCustomAssertionMethods() {
//    final var someActual = Subset.builder().build();
//    final var subsetAssert = SubsetAssert.assertThat(someActual);
//    assertThat(subsetAssert.getClass())
//        .hasPublicMethods("hasValue", "hasNullValue",
//            "isNull", "isNullOrHasNullValue",
//            "isNotNull", "isNotNullAndHasNonNullValue");
//  }

  @Test
  void assertThat_returnsSubsetAssertInstance() {
    final var someActual = Subset.builder().build();
    final var subsetAssert = SubsetAssert.assertThat(someActual);
    assertThat(subsetAssert)
        .isInstanceOf(SubsetAssert.class)
        .isInstanceOf(AbstractSubsetAssert.class);
  }

}
