package org.datatypeproject.impl;

import static org.datatypeproject.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_LONG_ARRAY;

import org.datatypeproject.CodePointRange;
import org.datatypeproject.Subset;
import org.datatypeproject.Subset.SubsetBuilder;
import org.datatypeproject.TypeParser.TypeParserBuilder;

public class Factory {

  public static CodePointRange codePointRange(final int inclusiveFrom, final int inclusiveTo) {
    return new CodePointRangeImpl(inclusiveFrom, inclusiveTo);
  }

  public static SubsetBuilder subsetBuilder() {
    return new RangedSubsetBuilderImpl();
  }

  public static TypeParserBuilder typeParserBuilder(final Class<?> targetClass) {
    return new TypeParserBuilderImpl(targetClass);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final int[] doubleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize);
  }

  public static Subset rangedSubset(
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    return new RangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        rangesSize, codePointsSize,
        categoriesSize);
  }

  public static Subset hashedRangedSubset(
      final char[][] blockKeys,
      final char[][][] singleByteCodePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize) {
    return new HashedRangedSubsetImpl(
        blockKeys, singleByteCodePointRangesByBlock,
        rangesSize, codePointsSize);
  }

  public static Subset hashedRangedSubset(
      final long unicodeCategoryBitFlags,
      final char[][] blocks,
      final char[][][] codePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    return new HashedRangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        blocks, codePointRangesByBlock,
        rangesSize, codePointsSize, categoriesSize);
  }


  public static Subset optimalHashedRangedSubset(
      final char[] blockKeys,
      final char[][] singleByteCodePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize) {
    return new OptimalHashedRangedSubsetImpl(
        blockKeys, singleByteCodePointRangesByBlock,
        rangesSize, codePointsSize);
  }

  public static Subset optimalHashedRangedSubset(
      final long unicodeCategoryBitFlags,
      final char[] blocks,
      final char[][] codePointRangesByBlock,
      final int rangesSize,
      final int codePointsSize,
      final int categoriesSize) {
    return new OptimalHashedRangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        blocks, codePointRangesByBlock,
        rangesSize, codePointsSize, categoriesSize);
  }

}
