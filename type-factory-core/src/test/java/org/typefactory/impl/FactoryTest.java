package org.typefactory.impl;

import static org.typefactory.assertions.TypeFactoryAssertions.assertThat;
import static org.typefactory.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;

import org.junit.jupiter.api.Test;

class FactoryTest {

  @Test
  void emptySubset_returnsEmptySubset() {
    final var actual = Factory.emptySubset();

    assertThat(actual)
        .isNotNull()
        .isEmpty();

    assertThat(actual.ranges())
        .isEmpty();
  }

  @Test
  void subsetBuilder_returnsSubsetBuilderImpl() {
    final var actual = Factory.subsetBuilder();

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(SubsetBuilderImpl.class);
  }

  @Test
  void typeParserBuilder_returnsTypeParserBuilderImpl() {
    final var actual = Factory.typeParserBuilder();

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(TypeParserBuilderImpl.class);
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyCharArray() {
    final var actual = Factory.rangedSubset(
        EMPTY_CHAR_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyCharAndIntArrays() {
    final var actual = Factory.rangedSubset(
        EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyCharAndLongArrays() {
    final var actual = Factory.rangedSubset(
        EMPTY_CHAR_ARRAY, EMPTY_LONG_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyCharAndIntAndLongArrays() {
    final var actual = Factory.rangedSubset(
        EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyIntArray() {
    final var actual = Factory.rangedSubset(
        EMPTY_INT_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }


  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyIntAndLongArrays() {
    final var actual = Factory.rangedSubset(
        EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }

  @Test
  void rangedSubset_returnsRangedSubsetImplForEmptyLongArray() {
    final var actual = Factory.rangedSubset(
        EMPTY_LONG_ARRAY, 0, 0);

    assertThat(actual)
        .isNotNull()
        .isInstanceOf(RangedSubsetImpl.class)
        .isEmpty();
  }


}
