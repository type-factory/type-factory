package org.datatypeproject.impl;

import static org.datatypeproject.impl.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.impl.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.impl.SubsetUtils.numberOfUnicodeCategoriesFromCategoriesFlags;
import static org.datatypeproject.impl.SubsetUtils.compactDoubleByteCodePointRanges;
import static org.datatypeproject.impl.SubsetUtils.compactSingleByteCodePointRanges;
import static org.datatypeproject.impl.SubsetUtils.compactTripleByteCodePointRanges;
import static org.datatypeproject.impl.SubsetUtils.getInclusiveFrom;
import static org.datatypeproject.impl.SubsetUtils.getInclusiveTo;
import static org.datatypeproject.impl.SubsetUtils.rangeToChar;
import static org.datatypeproject.impl.SubsetUtils.rangeToInt;
import static org.datatypeproject.impl.SubsetUtils.rangeToLong;
import static org.datatypeproject.impl.SubsetUtils.removeDoubleByteElement;
import static org.datatypeproject.impl.SubsetUtils.removeSingleByteElement;
import static org.datatypeproject.impl.SubsetUtils.removeTripleByteElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.datatypeproject.Category;
import org.datatypeproject.Subset;
import org.datatypeproject.Subset.CodePointRange;
import org.datatypeproject.Subset.SubsetBuilder;
import org.datatypeproject.SubsetWithCategories;

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

  private boolean containsExcludes() {
    return excludes.isNotEmpty() || excludeUnicodeCategoryBitFlags > 0;
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

    Subset result;

    if (containsUnicodeCategories()) {
      result = new RangedSubsetWithCategoriesImpl(
          includeUnicodeCategoryBitFlags,
          includes.copyOfSingleByteCodePointRanges(),
          includes.copyOfDoubleByteCodePointRanges(),
          includes.copyOfTripleByteCodePointRanges(),
          includes.numberOfCodePointRanges,
          includes.numberOfCodePointsInCodePointRanges,
          numberOfUnicodeCategoriesFromCategoriesFlags(includeUnicodeCategoryBitFlags));
    } else {
      result = new RangedSubsetImpl(
          includes.copyOfSingleByteCodePointRanges(),
          includes.copyOfDoubleByteCodePointRanges(),
          includes.copyOfTripleByteCodePointRanges(),
          includes.numberOfCodePointRanges,
          includes.numberOfCodePointsInCodePointRanges);
    }

    if (isCandidateForHashedRangeSubset()) {
      final SubsetOptimiser subsetOptimiser = new SubsetOptimiser(result.ranges());
      return subsetOptimiser.getOptimisedSubset();
    } else {
      return result;
    }
  }

  private boolean isCandidateForHashedRangeSubset() {
    final int rangesCount = includes.doubleByteCodePointRangesSize()
        + includes.tripleByteCodePointRangesSize();

    if (rangesCount < 128) { // TODO Revisit 'magic' threshold
      return false;
    }

    final int blocksCount = includes.doubleByteCodePointRangesCountOfBlocks()
        + includes.tripleByteCodePointRangesCountOfBlocks();

    if (blocksCount < 3) { // TODO Revisit 'magic' threshold
      return false;
    }

    return true;
  }

  static class Ranges {

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

    int singleByteCodePointRangesSize() {
      return singleByteCodePointRanges.length;
    }

    int doubleByteCodePointRangesSize() {
      return doubleByteCodePointRanges.length;
    }

    int tripleByteCodePointRangesSize() {
      return tripleByteCodePointRanges.length;
    }

    int doubleByteCodePointRangesCountOfBlocks() {
      if (doubleByteCodePointRanges.length == 0) {
        return 0;
      }
      int result = 1;
      int previousBlockKey = getInclusiveFrom(doubleByteCodePointRanges[0]) >> 8;
      int currentBlockKey;
      for (int i = 0; i < doubleByteCodePointRanges.length; ++i) {
        if (previousBlockKey != (currentBlockKey = (getInclusiveFrom(doubleByteCodePointRanges[i]) >> 8))) {
          previousBlockKey = currentBlockKey;
          ++result;
        }
        if (previousBlockKey != (currentBlockKey = (getInclusiveTo(doubleByteCodePointRanges[i]) >> 8))) {
          previousBlockKey = currentBlockKey;
          ++result;
        }
      }
      return result;
    }

    int tripleByteCodePointRangesCountOfBlocks() {
      if (tripleByteCodePointRanges.length == 0) {
        return 0;
      }
      int result = 1;
      int previous = getInclusiveFrom(tripleByteCodePointRanges[0]) >> 8;
      int current;
      for (int i = 0; i < tripleByteCodePointRanges.length; ++i) {
        if (previous != (current = (getInclusiveFrom(tripleByteCodePointRanges[i]) >> 8))) {
          previous = current;
          ++result;
        }
        if (previous != (current = (getInclusiveTo(tripleByteCodePointRanges[i]) >> 8))) {
          previous = current;
          ++result;
        }
      }
      return result;
    }


    boolean isNotEmpty() {
      return !isEmpty();
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

    SubsetBuilder copyToBuilder(final SubsetBuilder builder) {
      for (char range : singleByteCodePointRanges) {
        builder.includeCodePointRange(getInclusiveFrom(range), getInclusiveTo(range));
      }
      for (int range : doubleByteCodePointRanges) {
        builder.includeCodePointRange(getInclusiveFrom(range), getInclusiveTo(range));
      }
      for (long range : tripleByteCodePointRanges) {
        builder.includeCodePointRange(getInclusiveFrom(range), getInclusiveTo(range));
      }
      return builder;
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
}
