package org.typefactory.impl;

import static org.typefactory.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_INT_ARRAY;
import static org.typefactory.impl.Constants.EMPTY_LONG_ARRAY;
import static org.typefactory.impl.SubsetUtils.compactDoubleByteCodePointRanges;
import static org.typefactory.impl.SubsetUtils.compactSingleByteCodePointRanges;
import static org.typefactory.impl.SubsetUtils.compactTripleByteCodePointRanges;
import static org.typefactory.impl.SubsetUtils.getInclusiveFrom;
import static org.typefactory.impl.SubsetUtils.getInclusiveTo;
import static org.typefactory.impl.SubsetUtils.numberOfUnicodeCategoriesFromCategoriesFlags;
import static org.typefactory.impl.SubsetUtils.rangeToChar;
import static org.typefactory.impl.SubsetUtils.rangeToInt;
import static org.typefactory.impl.SubsetUtils.rangeToLong;
import static org.typefactory.impl.SubsetUtils.removeDoubleByteElement;
import static org.typefactory.impl.SubsetUtils.removeSingleByteElement;
import static org.typefactory.impl.SubsetUtils.removeTripleByteElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.typefactory.Category;
import org.typefactory.Subset;
import org.typefactory.Subset.CodePointRange;
import org.typefactory.Subset.SubsetBuilder;
import org.typefactory.SubsetWithCategories;
import org.typefactory.impl.SubsetBuilderImpl.SubsetOptimiser.HashedSubsetOption;

class SubsetBuilderImpl implements SubsetBuilder {

  /**
   * Code-points to include in the set
   */
  protected final Ranges includes = new Ranges();

  /**
   * Code-points to exclude from the set
   */
  protected final Ranges excludes = new Ranges();

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long includeUnicodeCategoryBitFlags;

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long excludeUnicodeCategoryBitFlags;

  @Override
  public SubsetBuilderImpl includeUnicodeCategory(final Category category) {
    includeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  @Override
  public SubsetBuilderImpl includeUnicodeCategories(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        includeUnicodeCategory(category);
      }
    }
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeUnicodeCategory(final Category category) {
    excludeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeUnicodeCategory(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        excludeUnicodeCategory(category);
      }
    }
    return this;
  }

  @Override
  public SubsetBuilderImpl includeChar(final char ch) {
    includes.addChar(ch);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeChars(final char... chars) {
    includes.addChars(chars);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeCharRange(final char inclusiveFrom, final char inclusiveTo) {
    includes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeCodePoint(final int codePoint) {
    includes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeCodePoints(final int... codePoints) {
    includes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    includes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public SubsetBuilderImpl includeSubset(final Subset subset) {
    includes.addSubset(subset);
    if (subset instanceof SubsetWithCategories subsetWithCategories) {
      includeUnicodeCategoryBitFlags |= subsetWithCategories.unicodeCategoryBitFlags();
    }
    return this;
  }

  @Override
  public SubsetBuilderImpl includeSubsets(final Subset... subsets) {
    if (subsets != null) {
      for (Subset subset : subsets) {
        includeSubset(subset);
      }
    }
    return this;
  }

  @Override
  public SubsetBuilderImpl includeSubsets(final Collection<Subset> subsets) {
    if (subsets != null) {
      for (Subset subset : subsets) {
        includeSubset(subset);
      }
    }
    return this;
  }

  public SubsetBuilderImpl includeSubset(final RangedSubset subset) {
    includes.addSubset(subset);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeChar(final char ch) {
    excludes.addChar(ch);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeChars(final char... chars) {
    excludes.addChars(chars);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeCharRange(final char inclusiveFrom, final char inclusiveTo) {
    excludes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeCodePoint(final int codePoint) {
    excludes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeCodePoints(final int... codePoints) {
    excludes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    excludes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeSubset(final Subset subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeSubsets(final Subset... subsets) {
    if (subsets != null) {
      for (Subset subset : subsets) {
        excludeSubset(subset);
      }
    }
    return this;
  }

  @Override
  public SubsetBuilderImpl excludeSubsets(final Collection<Subset> subsets) {
    if (subsets != null) {
      for (Subset subset : subsets) {
        excludeSubset(subset);
      }
    }
    return this;
  }

  public SubsetBuilderImpl excludeSubset(final RangedSubset subset) {
    excludes.addSubset(subset);
    return this;
  }

  private boolean containsUnicodeCategories() {
    return includeUnicodeCategoryBitFlags > 0;
  }

  private void compactCategories() {
    // remove excluded categories
    includeUnicodeCategoryBitFlags &= ~excludeUnicodeCategoryBitFlags;
  }

  @Override
  public Subset build() {
    excludes.compact();
    includes.removeCodePointRanges(excludes);
    includes.removeCodepointCategories(excludeUnicodeCategoryBitFlags);
    includes.compact();
    compactCategories();

    RangedSubset rangedSubset;

    if (containsUnicodeCategories()) {
      rangedSubset = new RangedSubsetWithCategoriesImpl(
          includeUnicodeCategoryBitFlags,
          includes.copyOfSingleByteCodePointRanges(),
          includes.copyOfDoubleByteCodePointRanges(),
          includes.copyOfTripleByteCodePointRanges(),
          includes.numberOfCodePointRanges,
          includes.numberOfCodePointsInCodePointRanges,
          numberOfUnicodeCategoriesFromCategoriesFlags(includeUnicodeCategoryBitFlags));
    } else {
      rangedSubset = new RangedSubsetImpl(
          includes.copyOfSingleByteCodePointRanges(),
          includes.copyOfDoubleByteCodePointRanges(),
          includes.copyOfTripleByteCodePointRanges(),
          includes.numberOfCodePointRanges,
          includes.numberOfCodePointsInCodePointRanges);
    }

    final SubsetOptimiser subsetOptimiser = new SubsetOptimiser(rangedSubset);

    System.out.println("\n\nSubset Stats\n\n" + subsetOptimiser.toString() + "\n\n");

    switch (subsetOptimiser.getPreferredSubsetType()) {
      case HASHED: {
        final HashedSubsetOption optimalHashedSubsetOption = (HashedSubsetOption) subsetOptimiser.optimalHashedSubsetOption;
        final HashedRangedSubsetData hashedRangedSubsetData = new HashedRangedSubsetData(optimalHashedSubsetOption.getNumberOfHashBuckets());
        hashedRangedSubsetData.optimiseHashMap(rangedSubset.ranges(), optimalHashedSubsetOption);
        return new HashedRangedSubsetImpl(
            hashedRangedSubsetData.blockKeys,
            hashedRangedSubsetData.codePointRangesByBlock,
            hashedRangedSubsetData.numberOfCodePointRanges,
            hashedRangedSubsetData.numberOfCodePointsInCodePointRanges);
      }
      case OPTIMALLY_HASHED: {
        final HashedSubsetOption optimalHashedSubsetOption = (HashedSubsetOption) subsetOptimiser.optimalHashedSubsetOption;
        final OptimalHashedRangedSubsetData optimalHashedRangedSubsetData = new OptimalHashedRangedSubsetData(
            optimalHashedSubsetOption.getNumberOfHashBuckets());
        optimalHashedRangedSubsetData.optimiseHashMap(rangedSubset.ranges(), optimalHashedSubsetOption);
        return new OptimalHashedRangedSubsetImpl(
            optimalHashedRangedSubsetData.blockKeys,
            optimalHashedRangedSubsetData.codePointRangesByBlock,
            optimalHashedRangedSubsetData.numberOfCodePointRanges,
            optimalHashedRangedSubsetData.numberOfCodePointsInCodePointRanges);
      }
      case RANGED:
      default:
        return rangedSubset;
    }
  }


  static final class Ranges {

    private char[] singleByteCodePointRanges = EMPTY_CHAR_ARRAY;
    private int[] doubleByteCodePointRanges = EMPTY_INT_ARRAY;
    private long[] tripleByteCodePointRanges = EMPTY_LONG_ARRAY;

    private int singleByteCodePointRangesSize = 0;
    private int doubleByteCodePointRangesSize = 0;
    private int tripleByteCodePointRangesSize = 0;

    private int numberOfCodePointRanges = 0;

    private int numberOfCodePointsInCodePointRanges = 0;

    boolean isEmpty() {
      return singleByteCodePointRanges.length == 0
          && doubleByteCodePointRanges.length == 0
          && tripleByteCodePointRanges.length == 0;
    }

    char[] copyOfSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRangesSize);
    }

    int[] copyOfDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRangesSize);
    }

    long[] copyOfTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRangesSize);
    }

    void addChar(final char ch) {
      addCharRange(ch, ch);
    }

    void addChars(final char... chars) {
      if (chars != null) {
        ensureDoubleByteCapacity(chars.length);
        for (int i = 0; i < chars.length; ++i) {
          addChar(chars[i]);
        }
      }
    }

    void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
      addCodePointRange(inclusiveFrom & 0x0000_ffff, inclusiveTo & 0x0000_ffff);
    }

    void addCodePoint(final int codePoint) {
      addCodePointRange(codePoint, codePoint);
    }

    void addCodePoints(final int... codePoints) {
      if (codePoints != null) {
        ensureDoubleByteCapacity(codePoints.length);
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRange(int inclusiveFrom, int inclusiveTo) {
      if (inclusiveFrom > inclusiveTo) {
        int temp = inclusiveFrom;
        inclusiveFrom = inclusiveTo;
        inclusiveTo = temp;
      }

      if (inclusiveTo > 0xffff) {
        if (inclusiveFrom > 0xffff) {
          ensureTripleByteCapacity();
          tripleByteCodePointRanges[tripleByteCodePointRangesSize] = rangeToLong(inclusiveFrom, inclusiveTo);
          ++tripleByteCodePointRangesSize;
          return;
        } else {
          ensureTripleByteCapacity();
          tripleByteCodePointRanges[tripleByteCodePointRangesSize] = rangeToLong(0x10000, inclusiveTo);
          ++tripleByteCodePointRangesSize;
          inclusiveTo = 0xffff;
        }
      }

      if (inclusiveTo > 0xff) {
        if (inclusiveFrom > 0xff) {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteCodePointRangesSize] = rangeToInt(inclusiveFrom, inclusiveTo);
          ++doubleByteCodePointRangesSize;
          return;
        } else {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteCodePointRangesSize] = rangeToInt(0x100, inclusiveTo);
          ++doubleByteCodePointRangesSize;
          inclusiveTo = 0xff;
        }
      }

      ensureSingleByteCapacity();
      singleByteCodePointRanges[singleByteCodePointRangesSize] = rangeToChar(inclusiveFrom, inclusiveTo);
      ++singleByteCodePointRangesSize;
    }

    void removeCodePointRanges(final Ranges ranges) {
      for (char range : ranges.singleByteCodePointRanges) {
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        removeCodePointRange(inclusiveFrom, inclusiveTo);
      }
      for (int range : ranges.doubleByteCodePointRanges) {
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        removeCodePointRange(inclusiveFrom, inclusiveTo);
      }
      for (long range : ranges.tripleByteCodePointRanges) {
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        removeCodePointRange(inclusiveFrom, inclusiveTo);
      }
    }

    void removeCodePoint(int codePoint) {
      removeCodePointRange(codePoint, codePoint);
    }

    void removeCodePointRange(int removeInclusiveFrom, int removeInclusiveTo) {
      if (removeInclusiveFrom > removeInclusiveTo) {
        int temp = removeInclusiveFrom;
        removeInclusiveFrom = removeInclusiveTo;
        removeInclusiveTo = temp;
      }

      if (removeInclusiveFrom < 0x100) {
        for (int i = singleByteCodePointRanges.length - 1; i >= 0; --i) {
          final char range = singleByteCodePointRanges[i];
          final int inclusiveFrom = getInclusiveFrom(range);
          final int inclusiveTo = getInclusiveTo(range);
          if (removeInclusiveFrom <= inclusiveTo && removeInclusiveTo >= inclusiveTo) {
            singleByteCodePointRangesSize = removeSingleByteElement(singleByteCodePointRanges, singleByteCodePointRangesSize, i);
          } else if (inclusiveFrom < removeInclusiveFrom && inclusiveTo > removeInclusiveTo) {
            singleByteCodePointRanges[i] = rangeToChar(inclusiveFrom, removeInclusiveFrom - 1);
            addCodePointRange(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveTo >= inclusiveFrom) {
            singleByteCodePointRanges[i] = rangeToChar(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveFrom <= inclusiveTo) {
            singleByteCodePointRanges[i] = rangeToChar(inclusiveTo, removeInclusiveFrom - 1);
          }
        }
      }

      if (removeInclusiveFrom > 0xff || removeInclusiveTo < 0x10000) {
        for (int i = doubleByteCodePointRanges.length - 1; i >= 0; --i) {
          final int range = doubleByteCodePointRanges[i];
          final int inclusiveFrom = getInclusiveFrom(range);
          final int inclusiveTo = getInclusiveTo(range);
          if (removeInclusiveFrom <= inclusiveTo && removeInclusiveTo >= inclusiveTo) {
            doubleByteCodePointRangesSize = removeDoubleByteElement(doubleByteCodePointRanges, doubleByteCodePointRangesSize, i);
          } else if (inclusiveFrom < removeInclusiveFrom && inclusiveTo > removeInclusiveTo) {
            doubleByteCodePointRanges[i] = rangeToInt(inclusiveFrom, removeInclusiveFrom - 1);
            addCodePointRange(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveTo >= inclusiveFrom) {
            doubleByteCodePointRanges[i] = rangeToInt(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveFrom <= inclusiveTo) {
            doubleByteCodePointRanges[i] = rangeToInt(inclusiveTo, removeInclusiveFrom - 1);
          }
        }
      }

      if (removeInclusiveTo > 0xffff) {
        for (int i = tripleByteCodePointRanges.length - 1; i >= 0; --i) {
          final long range = tripleByteCodePointRanges[i];
          final int inclusiveFrom = getInclusiveFrom(range);
          final int inclusiveTo = getInclusiveTo(range);
          if (removeInclusiveFrom <= inclusiveTo && removeInclusiveTo >= inclusiveTo) {
            tripleByteCodePointRangesSize = removeTripleByteElement(tripleByteCodePointRanges, tripleByteCodePointRangesSize, i);
          } else if (inclusiveFrom < removeInclusiveFrom && inclusiveTo > removeInclusiveTo) {
            tripleByteCodePointRanges[i] = rangeToLong(inclusiveFrom, removeInclusiveFrom - 1);
            addCodePointRange(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveTo >= inclusiveFrom) {
            tripleByteCodePointRanges[i] = rangeToLong(removeInclusiveTo + 1, inclusiveTo);
          } else if (removeInclusiveFrom <= inclusiveTo) {
            tripleByteCodePointRanges[i] = rangeToLong(inclusiveTo, removeInclusiveFrom - 1);
          }
        }
      }
    }

    void removeCodepointCategories(final long unicodeCategoryBitFlags) {
      if (unicodeCategoryBitFlags == 0L) {
        return;
      }
      final List<Integer> codepointsToRemove = new ArrayList<>();
      for (int i = singleByteCodePointRanges.length - 1; i >= 0; --i) {
        final char range = singleByteCodePointRanges[i];
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        for (int codePoint = inclusiveFrom; codePoint <= inclusiveTo; ++codePoint) {
          if ((unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L) {
            codepointsToRemove.add(codePoint);
          }
        }
      }

      for (int i = doubleByteCodePointRanges.length - 1; i >= 0; --i) {
        final int range = doubleByteCodePointRanges[i];
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        for (int codePoint = inclusiveFrom; codePoint <= inclusiveTo; ++codePoint) {
          if ((unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L) {
            codepointsToRemove.add(codePoint);
          }
        }
      }

      for (int i = tripleByteCodePointRanges.length - 1; i >= 0; --i) {
        final long range = tripleByteCodePointRanges[i];
        final int inclusiveFrom = getInclusiveFrom(range);
        final int inclusiveTo = getInclusiveTo(range);
        for (int codePoint = inclusiveFrom; codePoint <= inclusiveTo; ++codePoint) {
          if ((unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L) {
            codepointsToRemove.add(codePoint);
          }
        }
      }

      for (int codepoint : codepointsToRemove) {
        removeCodePoint(codepoint);
      }
    }

    void addSubset(final Subset subset) {
      if (subset != null && subset.isNotEmpty()) {
        if (subset instanceof RangedSubset rangedSubset) {
          // More efficiently adds the ranges
          addSubset(rangedSubset);
        } else {
          for (CodePointRange range : subset.ranges()) {
            addCodePointRange(range.inclusiveFrom, range.inclusiveTo);
          }
        }
      }
    }

    void addSubset(final RangedSubset subset) {
      if (subset == null || subset.isEmpty()) {
        return;
      }
      final char[] singleByteRanges = subset.getSingleByteCodePointRanges();
      ensureSingleByteCapacity(singleByteRanges.length);
      for (char c : singleByteRanges) {
        addCodePointRange(
            getInclusiveFrom(c),
            getInclusiveTo(c));
      }
      final int[] doubleByteRanges = subset.getDoubleByteCodePointRanges();
      ensureDoubleByteCapacity(doubleByteRanges.length);
      for (int j : doubleByteRanges) {
        addCodePointRange(
            getInclusiveFrom(j),
            getInclusiveTo(j));
      }
      final long[] tripleByteRanges = subset.getTripleByteCodePointRanges();
      ensureTripleByteCapacity(tripleByteRanges.length);
      for (long l : tripleByteRanges) {
        addCodePointRange(
            getInclusiveFrom(l),
            getInclusiveTo(l));
      }
    }

    private void ensureSingleByteCapacity() {
      if (singleByteCodePointRangesSize == singleByteCodePointRanges.length) {
        ensureSingleByteCapacity(32);
      }
    }

    private void ensureSingleByteCapacity(final int amount) {
      if ((singleByteCodePointRanges.length - singleByteCodePointRangesSize) < amount) {
        singleByteCodePointRanges = Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length + amount);
      }
    }

    private void ensureDoubleByteCapacity() {
      if (doubleByteCodePointRangesSize == doubleByteCodePointRanges.length) {
        ensureDoubleByteCapacity(32);
      }
    }

    private void ensureDoubleByteCapacity(final int amount) {
      if ((doubleByteCodePointRanges.length - doubleByteCodePointRangesSize) < amount) {
        doubleByteCodePointRanges = Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length + amount);
      }
    }

    private void ensureTripleByteCapacity() {
      if (tripleByteCodePointRangesSize == tripleByteCodePointRanges.length) {
        ensureTripleByteCapacity(32);
      }
    }

    private void ensureTripleByteCapacity(final int amount) {
      if ((tripleByteCodePointRanges.length - tripleByteCodePointRangesSize) < amount) {
        tripleByteCodePointRanges = Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length + amount);
      }
    }

    @SuppressWarnings({"java:S3776", "java:S1199"})
    void compact() {
      singleByteCodePointRangesSize = compactSingleByteCodePointRanges(singleByteCodePointRanges, singleByteCodePointRangesSize);
      doubleByteCodePointRangesSize = compactDoubleByteCodePointRanges(doubleByteCodePointRanges, doubleByteCodePointRangesSize);
      tripleByteCodePointRangesSize = compactTripleByteCodePointRanges(tripleByteCodePointRanges, tripleByteCodePointRangesSize);
      numberOfCodePointRanges = singleByteCodePointRangesSize + doubleByteCodePointRangesSize + tripleByteCodePointRangesSize;
      for (int i = 0; i < singleByteCodePointRangesSize; ++i) {
        numberOfCodePointsInCodePointRanges =
            getInclusiveTo(singleByteCodePointRanges[i]) - getInclusiveFrom(singleByteCodePointRanges[i]) + 1;
      }
      for (int i = 0; i < doubleByteCodePointRangesSize; ++i) {
        numberOfCodePointsInCodePointRanges =
            getInclusiveTo(doubleByteCodePointRanges[i]) - getInclusiveFrom(doubleByteCodePointRanges[i]) + 1;
      }
      for (int i = 0; i < tripleByteCodePointRangesSize; ++i) {
        numberOfCodePointsInCodePointRanges =
            getInclusiveTo(tripleByteCodePointRanges[i]) - getInclusiveFrom(tripleByteCodePointRanges[i]) + 1;
      }
    }
  }

  static final class SubsetOptimiser {

    enum PreferredSubsetType {
      RANGED("Ranged"),
      HASHED("Hashed"),
      OPTIMALLY_HASHED("Opt. Hashed");
      final String description;

      PreferredSubsetType(final String description) {
        this.description = description;
      }

      @Override
      public String toString() {
        return description;
      }
    }

    int numberOfCodePointRanges;
    char[] blockKeys;
    int[] codePointRangesSize;

    int byteSizeOfBlockKeyData;

    int byteSizeOfCodePointRangeData;

    int hashcode;

    SubsetOption[] subsetOptions;

    SubsetOption optimalHashedSubsetOption;

    SubsetOptimiser(final RangedSubset rangedSubset) {
      calculateCommonHashedStats(rangedSubset);
      if (isCandidateForHashedRangeSubset()) {
        calculateHashedSubsetOptions(rangedSubset);
      } else {
        subsetOptions = new SubsetOption[]{new RangedSubsetOption(rangedSubset)};
      }
      optimalHashedSubsetOption = subsetOptions[0];
    }

    PreferredSubsetType getPreferredSubsetType() {
      return optimalHashedSubsetOption.getSubsetType();
    }

    /**
     * Returns {@link SubsetOptimiser} containing the full set of block-keys, how many code-point ranges there are for each block key, as well as
     * some memory stats describing the bytes that will be used to hold the block key data and the code point ranges data.
     *
     * @param rangedSubset the ranged-subset that we want stats from.
     * @return a {@link SubsetOptimiser} tuple containing the full set of block-keys, how many code-point ranges there are for, and some memory
     * usage stats. each block key.
     */
    void calculateCommonHashedStats(final RangedSubset rangedSubset) {
      hashcode = 0;
      numberOfCodePointRanges = 0;
      byteSizeOfBlockKeyData = 0;
      byteSizeOfCodePointRangeData = 0;
      blockKeys = new char[255];
      codePointRangesSize = new int[255];
      int i = -1;
      for (CodePointRange codePointRange : rangedSubset.ranges()) {
        numberOfCodePointRanges++;
        hashcode = 31 * hashcode + codePointRange.inclusiveFrom;
        hashcode = 31 * hashcode + codePointRange.inclusiveTo;
        final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
        final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          if (i < 0 || blockKey != blockKeys[i]) {
            ++i;
            if (i == blockKeys.length) {
              blockKeys = Arrays.copyOf(blockKeys, blockKeys.length + 255);
              codePointRangesSize = Arrays.copyOf(codePointRangesSize, codePointRangesSize.length + 255);
            }
            blockKeys[i] = blockKey;
            codePointRangesSize[i]++;
            byteSizeOfBlockKeyData += 2;       // 2 byte block key
            byteSizeOfCodePointRangeData += 2; // 2 byte code point range
          } else if (blockKey != blockKeyTo) {
            codePointRangesSize[i]++;
            byteSizeOfCodePointRangeData += 2; // 2 byte code point range
          }
        }
      }
      ++i;
      blockKeys = Arrays.copyOf(blockKeys, i);
      codePointRangesSize = Arrays.copyOf(codePointRangesSize, i);
    }

    /**
     * Calculates a selection of possible hashed subset options.
     */
    void calculateHashedSubsetOptions(final RangedSubset rangedSubset) {
      final int numberOfBlockKeys = blockKeys.length;
      final int minHashBuckets = Math.max(3, (int) Math.floor(numberOfBlockKeys * 0.7D));
      final int maxHashBuckets = Math.max(7, (int) Math.ceil(numberOfBlockKeys * 2.6D));
      subsetOptions = new SubsetOption[maxHashBuckets - minHashBuckets + 1 + 1];
      // Add the ranged subset option as the first option.
      subsetOptions[0] = new RangedSubsetOption(rangedSubset);
      boolean foundOptimalHashMap = false;
      int subsetOptionsIndex = 1;
      // Try and find an optimal number of hash buckets with every hash bucket containing at most one key.
      for (int j = numberOfBlockKeys; j < maxHashBuckets; ++j, ++subsetOptionsIndex) {
        final HashedSubsetOption hashedSubsetOption = new HashedSubsetOption(j);
        subsetOptions[subsetOptionsIndex] = hashedSubsetOption;
        if (hashedSubsetOption.containsHashBucketsWithAtMostOneKey()) {
          subsetOptions = Arrays.copyOf(subsetOptions, subsetOptionsIndex + 1);
          foundOptimalHashMap = true;
          break;
        }
      }
      if (!foundOptimalHashMap) {
        // If no optimal hash map available then continue gathering stats on hash map with smaller number of buckets.
        for (int j = minHashBuckets; j < numberOfBlockKeys; ++j, ++subsetOptionsIndex) {
          subsetOptions[subsetOptionsIndex] = new HashedSubsetOption(j);
        }
      }
      // Sort so that the optimal subset option is the first element
      Arrays.sort(subsetOptions, SubsetOption.HASH_MAP_STATS_COMPARATOR);
    }

    boolean isCandidateForHashedRangeSubset() {
      return blockKeys.length > 2 && numberOfCodePointRanges > 32; // TODO Revisit 'magic' thresholds
    }

    @Override
    public String toString() {
      final StringBuilder s = new StringBuilder();
      s.append("\n|=============|=========|========|=======|========|=========|==========|=======|=============|");
      s.append("\n|             |         |    hash buckets containing...     |     memory required (bytes)    |");
      s.append("\n|             |  # hash |-----------------------------------|--------------------------------|");
      s.append("\n| subset type | buckets | 0 keys | 1 key | 2 keys | 3+ keys | obj refs |  data | total bytes |");
      s.append("\n|=============|=========|========|=======|========|=========|==========|=======|=============|");
      int countOfHashedOptions = 0;
      for (SubsetOption subsetOption : subsetOptions) {
        if (subsetOption instanceof RangedSubsetOption rangedSubsetOption) {
          s.append(String.format("%n| %11s |         |        |       |        |         | %8d | %5d | %11d |",
              rangedSubsetOption.getSubsetType().toString(),
              rangedSubsetOption.getByteSizeOfArrayReferences(),
              rangedSubsetOption.getByteSizeOfCodePointRangeData(),
              rangedSubsetOption.getTotalBytes()));
        }
        if (subsetOption instanceof HashedSubsetOption hashedSubsetOption) {
          countOfHashedOptions++;
          if (countOfHashedOptions < 40 || hashedSubsetOption.getSubsetType() == PreferredSubsetType.OPTIMALLY_HASHED) {
            s.append(String.format("%n| %11s | %7d | %6d | %5d | %6d | %7d | %8d | %5d | %11d |",
                hashedSubsetOption.getSubsetType().toString(),
                hashedSubsetOption.getNumberOfHashBuckets(),
                hashedSubsetOption.getCountOfHashBucketsWith0Keys(),
                hashedSubsetOption.getCountOfHashBucketsWith1Key(),
                hashedSubsetOption.getCountOfHashBucketsWith2Keys(),
                hashedSubsetOption.getCountOfHashBucketsWith3OrMoreKeys(),
                hashedSubsetOption.getByteSizeOfArrayReferences(),
                hashedSubsetOption.getByteSizeOfBlockKeyData() + hashedSubsetOption.getByteSizeOfCodePointRangeData(),
                hashedSubsetOption.getTotalBytes()));
          }
        }
      }
      s.append("\n|=============|=========|========|=======|========|=========|==========|=======|=============|");
      if (subsetOptions.length > 20) {
        s.append("\n| subset type |  # hash | 0 keys | 1 key | 2 keys | 3+ keys | obj refs |  data | total bytes |");
        s.append("\n|             | buckets |-----------------------------------|--------------------------------|");
        s.append("\n|             |         |    hash buckets containing...     |     memory required (bytes)    |");
        s.append("\n|=============|=========|========|=======|========|=========|==========|=======|=============|");
      }
      return s.toString();
    }

    interface SubsetOption {

      Comparator<SubsetOption> HASH_MAP_STATS_COMPARATOR =
          Comparator.comparing(SubsetOption::getTotalBytes);

      int getTotalBytes();

      PreferredSubsetType getSubsetType();
    }

    class RangedSubsetOption implements SubsetOption {

      private int byteSizeOfArrayReferences = 0;

      private int byteSizeOfCodePointRangeData = 0;

      RangedSubsetOption(final RangedSubset rangedSubset) {
        int length = rangedSubset.getSingleByteCodePointRanges().length;
        if (length > 0) {
          this.byteSizeOfArrayReferences += 8; // 8 byte array object reference
          this.byteSizeOfCodePointRangeData += length * 2; // 2-byte code-point range
        }
        length = rangedSubset.getDoubleByteCodePointRanges().length;
        if (length > 0) {
          this.byteSizeOfArrayReferences += 8; // 8 byte array object reference
          this.byteSizeOfCodePointRangeData += length * 4; // 4-byte code-point range
        }
        length = rangedSubset.getTripleByteCodePointRanges().length;
        if (length > 0) {
          this.byteSizeOfArrayReferences += 8; // 8 byte array object reference
          this.byteSizeOfCodePointRangeData += length * 8; // 8-byte code-point range
        }
      }

      public int getTotalBytes() {
        return byteSizeOfArrayReferences + byteSizeOfCodePointRangeData;
      }

      public PreferredSubsetType getSubsetType() {
        return PreferredSubsetType.RANGED;
      }

      public int getByteSizeOfArrayReferences() {
        return byteSizeOfArrayReferences;
      }

      public int getByteSizeOfCodePointRangeData() {
        return byteSizeOfCodePointRangeData;
      }
    }

    class HashedSubsetOption implements SubsetOption {

      private final int numberOfHashBuckets;
      private final int[] hashBucketCounts;
      private int countOfHashBucketsWith0Keys;
      private int countOfHashBucketsWith1Key = 0;
      private int countOfHashBucketsWith2Keys = 0;
      private int countOfHashBucketsWith3OrMoreKeys = 0;
      private int byteSizeOfArrayReferences = 0;

      private HashedSubsetOption(
          final int numberOfHashBuckets) {
        this.numberOfHashBuckets = numberOfHashBuckets;
        this.hashBucketCounts = new int[numberOfHashBuckets];
        this.countOfHashBucketsWith0Keys = numberOfHashBuckets;
        for (int blockKey : blockKeys) {
          addKey(blockKey);
        }
        byteSizeOfArrayReferences = 2 * 8; // 2 * 8 byte array object references for blockKeys and codePointRanges arrays.
        if (containsHashBucketsWithMultipleKeys()) {
          byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for blockKeys
          byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for codePointRanges
          for (int i = 0; i < hashBucketCounts.length; ++i) {
            byteSizeOfArrayReferences +=
                hashBucketCounts[i] * 8; // number of codePointRange arrays * 8 byte array object references for codePointRanges
          }
        } else {
          byteSizeOfArrayReferences += numberOfHashBuckets * 8;   // number of hash-bucket * 8 byte array object references for codePointRanges
        }
      }

      private void addKey(final int key) {
        int hashIndex = (key & 0x7FFFFFFF) % hashBucketCounts.length;
        hashBucketCounts[hashIndex]++;
        switch (hashBucketCounts[hashIndex]) {
          case 1:
            countOfHashBucketsWith1Key++;
            countOfHashBucketsWith0Keys--;
            break;
          case 2:
            countOfHashBucketsWith2Keys++;
            countOfHashBucketsWith1Key--;
            break;
          case 3:
            countOfHashBucketsWith3OrMoreKeys++;
            countOfHashBucketsWith2Keys--;
            break;
          default:
            // do nothing – already subtracted from countOfHashBucketsWith2Keys when it got to 3
            break;
        }
      }

      public PreferredSubsetType getSubsetType() {
        return containsHashBucketsWithMultipleKeys() ? PreferredSubsetType.HASHED : PreferredSubsetType.OPTIMALLY_HASHED;
      }

      private boolean containsHashBucketsWithMultipleKeys() {
        return countOfHashBucketsWith2Keys > 0
            || countOfHashBucketsWith3OrMoreKeys > 0;
      }

      private boolean containsHashBucketsWithAtMostOneKey() {
        return !containsHashBucketsWithMultipleKeys();
      }

      private int getNumberOfHashBuckets() {
        return numberOfHashBuckets;
      }

      private int[] getHashBucketCounts() {
        return hashBucketCounts;
      }

      private int getCountOfHashBucketsWith0Keys() {
        return countOfHashBucketsWith0Keys;
      }

      private int getCountOfHashBucketsWith1Key() {
        return countOfHashBucketsWith1Key;
      }

      private int getCountOfHashBucketsWith2Keys() {
        return countOfHashBucketsWith2Keys;
      }

      public int getCountOfHashBucketsWith3OrMoreKeys() {
        return countOfHashBucketsWith3OrMoreKeys;
      }

      public int getByteSizeOfBlockKeyData() {
        return byteSizeOfBlockKeyData;
      }

      public int getByteSizeOfCodePointRangeData() {
        return byteSizeOfCodePointRangeData;
      }

      public int getByteSizeOfArrayReferences() {
        return byteSizeOfArrayReferences;
      }

      public int getTotalBytes() {
        return byteSizeOfArrayReferences + byteSizeOfBlockKeyData + byteSizeOfCodePointRangeData;
      }

      public int getHashCode() {
        return hashcode;
      }
    }
  }

  static final class HashedRangedSubsetData {

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌──── hashIndex       - an index to the hash-bucket
     *        │  ┌─ hashBucketIndex - an index to the key within the hash-bucket
     *        │  │
     *   char[ ][ ] blockKeys
     * </pre>
     */
    private final char[][] blockKeys;

    /**
     * <p>The hashed single-byte code-point ranges. The ranges are actually the least significant bytes of the
     * inclusive-from and inclusive-to code-points combined into a two-byte char.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─────── hashIndex           - an index to the hash-bucket
     *        │  ┌──── hashBucketIndex     - an index to the key within the hash-bucket
     *        │  │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
     *        │  │  │
     *   char[ ][ ][ ] codePointRangesByBlock
     * </pre>
     */
    private final char[][][] codePointRangesByBlock;

    private int numberOfCodePointRanges = 0;
    private int numberOfCodePointsInCodePointRanges = 0;

    public HashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets][];
      codePointRangesByBlock = new char[optimalNumberOfHashBuckets][][];
    }

    public char[][] getBlockKeys() {
      return blockKeys;
    }

    public void optimiseHashMap(final Iterable<CodePointRange> ranges, final HashedSubsetOption optimalHashedSubsetOption) {

      final int[] hashBucketCounts = optimalHashedSubsetOption.getHashBucketCounts();
      final int[] hashBucketSizes = new int[hashBucketCounts.length];
      final int[][] codePointRangesSizes = new int[hashBucketCounts.length][];

      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = null;
          codePointRangesByBlock[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = new char[hashBucketCounts[hashIndex]];
          codePointRangesByBlock[hashIndex] = new char[hashBucketCounts[hashIndex]][];
          codePointRangesSizes[hashIndex] = new int[hashBucketCounts[hashIndex]];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (CodePointRange codePointRange : ranges) {
        final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
        final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final char inclusiveFrom = (char) (blockKey == blockKeyFrom ? (codePointRange.inclusiveFrom & 0xFF) : 0x00);
          final char inclusiveTo = (char) (blockKey == blockKeyTo ? (codePointRange.inclusiveTo & 0xFF) : 0xFF);
          final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
          final char[] hashBucket = blockKeys[hashIndex];
          int hashBucketIndex = 0;
          while (hashBucketIndex < hashBucketSizes[hashIndex]
              && hashBucketIndex < hashBucket.length
              && hashBucket[hashBucketIndex] != blockKey) {
            ++hashBucketIndex;
          }
          if (hashBucketIndex == hashBucketSizes[hashIndex]) {
            hashBucketSizes[hashIndex]++;
          }
          if (codePointRangesByBlock[hashIndex][hashBucketIndex] == null) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = new char[32];
          }
          if (codePointRangesSizes[hashIndex][hashBucketIndex] == codePointRangesByBlock[hashIndex][hashBucketIndex].length) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesByBlock[hashIndex][hashBucketIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex][hashBucketIndex];
          blockKeys[hashIndex][hashBucketIndex] = blockKey;
          codePointRangesByBlock[hashIndex][hashBucketIndex][codePointRangesSize] = rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex][hashBucketIndex]++;
          numberOfCodePointRanges++;
          numberOfCodePointsInCodePointRanges += inclusiveTo - inclusiveFrom + 1;
        }
      }

      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != null) {
          blockKeys[hashIndex] = Arrays.copyOf(blockKeys[hashIndex], hashBucketSizes[hashIndex]);
          codePointRangesByBlock[hashIndex] = Arrays.copyOf(codePointRangesByBlock[hashIndex], hashBucketSizes[hashIndex]);
          for (int hashBucketIndex = 0; hashBucketIndex < codePointRangesByBlock[hashIndex].length; ++hashBucketIndex) {
            codePointRangesByBlock[hashIndex][hashBucketIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex][hashBucketIndex],
                codePointRangesSizes[hashIndex][hashBucketIndex]);
          }
        }
      }
    }
  }

  static final class OptimalHashedRangedSubsetData {

    /**
     * <p>The hashed block-keys. A block-key is the two most significant bytes of a three-byte code-point.</p>
     *
     * <p>In the code of this class the two-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─ hashIndex - an index to the hash-bucket
     *        │
     *   char[ ] blockKeys
     * </pre>
     */
    private final char[] blockKeys;

    /**
     * <p>The hashed single-byte code-point ranges. The ranges are actually the least significant bytes of the
     * inclusive-from and inclusive-to code-points combined into a two-byte char.</p>
     *
     * <p>In the code of this class the three-dimensional indexes will be referred to as:</p>
     * <pre>
     *        ┌─────── hashIndex        - an index to the hash-bucket
     *        │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
     *        │  │
     *   char[ ][ ] codePointRangesByBlock
     * </pre>
     */
    private final char[][] codePointRangesByBlock;

    private int numberOfCodePointRanges = 0;
    private int numberOfCodePointsInCodePointRanges = 0;

    public OptimalHashedRangedSubsetData(final int optimalNumberOfHashBuckets) {
      blockKeys = new char[optimalNumberOfHashBuckets];
      codePointRangesByBlock = new char[optimalNumberOfHashBuckets][];
    }


    public char[] getBlockKeys() {
      return blockKeys;
    }

    public char[][] getCodePointRangesByBlock() {
      return codePointRangesByBlock;
    }

    public int getRangesSize() {
      return numberOfCodePointRanges;
    }

    public int getCodePointsSize() {
      return numberOfCodePointsInCodePointRanges;
    }

    private void optimiseHashMap(final Iterable<CodePointRange> ranges, final HashedSubsetOption optimalHashedSubsetOption) {
      final int[] hashBucketCounts = optimalHashedSubsetOption.getHashBucketCounts();
      final int[] codePointRangesSizes = new int[hashBucketCounts.length];

      // Create the 1st-dimension arrays to the exact optimal size of hash-buckets.
      for (int hashIndex = 0; hashIndex < hashBucketCounts.length; ++hashIndex) {
        if (hashBucketCounts[hashIndex] == 0) {
          blockKeys[hashIndex] = 0xFFFF;
          codePointRangesByBlock[hashIndex] = null;
        } else {
          blockKeys[hashIndex] = 0;
          codePointRangesByBlock[hashIndex] = new char[1];
        }
      }
      // Add all unicode code-point ranges to the hash-map of blocked code-point ranges.
      for (CodePointRange codePointRange : ranges) {
        final char blockKeyFrom = (char) ((codePointRange.inclusiveFrom >> 8) & 0xFFFF);
        final char blockKeyTo = (char) ((codePointRange.inclusiveTo >> 8) & 0xFFFF);
        for (char blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
          final int inclusiveFrom = (char) (blockKey == blockKeyFrom ? (codePointRange.inclusiveFrom & 0xFF) : 0x00);
          final int inclusiveTo = (char) (blockKey == blockKeyTo ? (codePointRange.inclusiveTo & 0xFF) : 0xFF);
          final int hashIndex = (blockKey & 0x7FFFFFFF) % blockKeys.length;
          if (codePointRangesByBlock[hashIndex] == null) {
            codePointRangesByBlock[hashIndex] = new char[32];
          }
          if (codePointRangesSizes[hashIndex] == codePointRangesByBlock[hashIndex].length) {
            codePointRangesByBlock[hashIndex] = Arrays.copyOf(
                codePointRangesByBlock[hashIndex],
                codePointRangesByBlock[hashIndex].length + 32);
          }
          final int codePointRangesSize = codePointRangesSizes[hashIndex];
          blockKeys[hashIndex] = blockKey;
          codePointRangesByBlock[hashIndex][codePointRangesSize] = rangeToChar(inclusiveFrom, inclusiveTo);
          codePointRangesSizes[hashIndex]++;
          numberOfCodePointRanges++;
          numberOfCodePointsInCodePointRanges += (inclusiveTo - inclusiveFrom + 1);
        }
      }
      // Make copies of arrays to exact required sizes to minimize wasted space.
      for (int hashIndex = 0; hashIndex < blockKeys.length; ++hashIndex) {
        if (blockKeys[hashIndex] != 0xFFFF) {
          codePointRangesByBlock[hashIndex] = Arrays.copyOf(
              codePointRangesByBlock[hashIndex],
              codePointRangesSizes[hashIndex]);
        }
      }
    }
  }
}
