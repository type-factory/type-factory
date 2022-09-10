package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;

import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.TypeParser;
import org.typefactory.TypeParser.TypeParserBuilder;

/**
 * <p>This class is for internal use – you probably shouldn't use this class. Instead use one of these:</p>
 * <ul>
 *   <li>{@link TypeParser#builder()}</li>
 *   <li>{@link Subset#builder()}</li>
 * </ul>
 *
 * <p>This factory provides a means to directly instantiate concrete implementations of specific
 * kinds of class. It is heavily used by the code generator that creates optimised immutable
 * static subsets.</p>
 */
public class Factory {

  /**
   * Private constructor – don't instantiate. All factory methods are public static implementations.
   */
  private Factory() {
  }

  public static SubsetBuilder subsetBuilder() {
    return new SubsetBuilderImpl();
  }

  public static TypeParserBuilder typeParserBuilder() {
    return new TypeParserBuilderImpl();
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, EMPTY_INT_ARRAY, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, doubleByteCodePointRanges, EMPTY_LONG_ARRAY,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, EMPTY_INT_ARRAY, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        EMPTY_CHAR_ARRAY, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new RangedSubsetImpl(
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset rangedSubset(
      final long unicodeCategoryBitFlags,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    return new RangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges,
        numberOfUnicodeCategories);
  }

  public static Subset hashedRangedSubset(
      final char[][] blockKeys,
      final char[][][] singleByteCodePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new HashedRangedSubsetImpl(
        blockKeys, singleByteCodePointRangesByBlock,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset hashedRangedSubset(
      final long unicodeCategoryBitFlags,
      final char[][] blocks,
      final char[][][] codePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    return new HashedRangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        blocks, codePointRangesByBlock,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges, numberOfUnicodeCategories);
  }


  public static Subset optimalHashedRangedSubset(
      final char[] blockKeys,
      final char[][] singleByteCodePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges) {
    return new OptimalHashedRangedSubsetImpl(
        blockKeys, singleByteCodePointRangesByBlock,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges);
  }

  public static Subset optimalHashedRangedSubset(
      final long unicodeCategoryBitFlags,
      final char[] blocks,
      final char[][] codePointRangesByBlock,
      final int numberOfCodePointRanges,
      final int numberOfCodePointsInCodePointRanges,
      final int numberOfUnicodeCategories) {
    return new OptimalHashedRangedSubsetWithCategoriesImpl(
        unicodeCategoryBitFlags,
        blocks, codePointRangesByBlock,
        numberOfCodePointRanges, numberOfCodePointsInCodePointRanges, numberOfUnicodeCategories);
  }

}
