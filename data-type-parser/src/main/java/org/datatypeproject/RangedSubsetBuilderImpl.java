package org.datatypeproject;

import static org.datatypeproject.Constants.EMPTY_CHAR_ARRAY;
import static org.datatypeproject.Constants.EMPTY_INT_ARRAY;
import static org.datatypeproject.Constants.EMPTY_LONG_ARRAY;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;
import static org.datatypeproject.RangedSubsetUtils.rangeToChar;
import static org.datatypeproject.RangedSubsetUtils.rangeToInt;
import static org.datatypeproject.RangedSubsetUtils.rangeToLong;
import static org.datatypeproject.RangedSubsetUtils.unsignedIntegerBubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.datatypeproject.Subset.CodePointRange;

class RangedSubsetBuilderImpl implements RangedSubsetBuilder {

  /**
   * Code-points to include in the set
   */
  protected Ranges includes = new Ranges();

  /**
   * Code-points to exclude from the set
   */
  protected Ranges excludes = new Ranges();

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long includeUnicodeCategoryBitFlags;

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long excludeUnicodeCategoryBitFlags;

  public RangedSubsetBuilder includeUnicodeCategory(final Category category) {
    includeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public RangedSubsetBuilder includeUnicodeCategories(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        includeUnicodeCategory(category);
      }
    }
    return this;
  }

  public RangedSubsetBuilder excludeUnicodeCategory(final Category category) {
    excludeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public RangedSubsetBuilder excludeUnicodeCategory(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        excludeUnicodeCategory(category);
      }
    }
    return this;
  }

  @Override
  public RangedSubsetBuilder includeChar(final char ch) {
    includes.addChar(ch);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeChars(final char... chars) {
    includes.addChars(chars);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo) {
    includes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeCodePoint(final int codePoint) {
    includes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeCodePoints(final int... codePoints) {
    includes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    includes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeSubset(final Subset subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeSubsets(final Subset... subsets) {
    includes.addSubsets(subsets);
    return this;
  }

  @Override
  public RangedSubsetBuilder includeSubsets(final Collection<Subset> subsets) {
    includes.addSubsets(subsets);
    return this;
  }

  public RangedSubsetBuilder includeSubset(final RangedSubset subset) {
    includes.addSubset(subset);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeChar(final char ch) {
    excludes.addChar(ch);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeChars(final char... chars) {
    excludes.addChars(chars);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo) {
    excludes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeCodePoint(final int codePoint) {
    excludes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeCodePoints(final int... codePoints) {
    excludes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    excludes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeSubset(final Subset subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeSubsets(final Subset... subsets) {
    excludes.addSubsets(subsets);
    return this;
  }

  @Override
  public RangedSubsetBuilder excludeSubsets(final Collection<Subset> subsets) {
    excludes.addSubsets(subsets);
    return this;
  }

  public RangedSubsetBuilder excludeSubset(final RangedSubset subset) {
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
  public RangedSubset build() {
    excludes.compact();
    includes.removeCodePointRanges(excludes);
    includes.removeCodepointCategories(excludeUnicodeCategoryBitFlags);
    includes.compact();
    compactCategories();

//    if (containsExcludes()) {
//      return new RangedSubsetWithCategoriesAndExcludesImpl(
//          includeUnicodeCategoryBitFlags,
//          excludeUnicodeCategoryBitFlags,
//          includes.copyOfSingleByteCodePointRanges(),
//          includes.copyOfDoubleByteCodePointRanges(),
//          includes.copyOfTripleByteCodePointRanges(),
//          excludes.copyOfSingleByteCodePointRanges(),
//          excludes.copyOfDoubleByteCodePointRanges(),
//          excludes.copyOfTripleByteCodePointRanges());
//    }

    if (containsUnicodeCategories()) {
      return new RangedSubsetWithCategoriesImpl(
          includeUnicodeCategoryBitFlags,
          includes.copyOfSingleByteCodePointRanges(),
          includes.copyOfDoubleByteCodePointRanges(),
          includes.copyOfTripleByteCodePointRanges());
    }

    return new RangedSubsetImpl(
        includes.copyOfSingleByteCodePointRanges(),
        includes.copyOfDoubleByteCodePointRanges(),
        includes.copyOfTripleByteCodePointRanges());
  }

  static class Ranges {

    private char[] singleByteCodePointRanges = EMPTY_CHAR_ARRAY;
    private int[] doubleByteCodePointRanges = EMPTY_INT_ARRAY;
    private long[] tripleByteCodePointRanges = EMPTY_LONG_ARRAY;

    private int singleByteEndIndex = 0;
    private int doubleByteEndIndex = 0;
    private int tripleByteEndIndex = 0;

    boolean isEmpty() {
      return singleByteCodePointRanges.length == 0
          && doubleByteCodePointRanges.length == 0
          && tripleByteCodePointRanges.length == 0;
    }

    boolean isNotEmpty() {
      return !isEmpty();
    }

    char[] copyOfSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex);
    }

    int[] copyOfDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex);
    }

    long[] copyOfTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex);
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
          tripleByteCodePointRanges[tripleByteEndIndex] = rangeToLong(inclusiveFrom, inclusiveTo);
          ++tripleByteEndIndex;
          return;
        } else {
          ensureTripleByteCapacity();
          tripleByteCodePointRanges[tripleByteEndIndex] = rangeToLong(0x10000, inclusiveTo);
          ++tripleByteEndIndex;
          inclusiveTo = 0xffff;
        }
      }

      if (inclusiveTo > 0xff) {
        if (inclusiveFrom > 0xff) {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteEndIndex] = rangeToInt(inclusiveFrom, inclusiveTo);
          ++doubleByteEndIndex;
          return;
        } else {
          ensureDoubleByteCapacity();
          doubleByteCodePointRanges[doubleByteEndIndex] = rangeToInt(0x100, inclusiveTo);
          ++doubleByteEndIndex;
          inclusiveTo = 0xff;
        }
      }

      ensureSingleByteCapacity();
      singleByteCodePointRanges[singleByteEndIndex] = rangeToChar(inclusiveFrom, inclusiveTo);
      ++singleByteEndIndex;
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
          removeSingleByteElement(i);
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
          removeDoubleByteElement(i);
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
          removeTripleByteElement(i);
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

    @SuppressWarnings("java:S3776")
    void addSubsets(final Subset... subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubsets(final Collection<Subset> subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubset(final Subset subset) {
      if (subset != null && subset.isNotEmpty()) {
        if (subset instanceof RangedSubset rangedSubset) {
          // More efficiently adds the ranges
          addSubset(rangedSubset);
        } else {
          for (CodePointRange range : subset.ranges()) {
            addCodePointRange(range.getInclusiveFrom(), range.getInclusiveTo());
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
      if (singleByteEndIndex == singleByteCodePointRanges.length) {
        ensureSingleByteCapacity(32);
      }
    }

    private void ensureSingleByteCapacity(final int amount) {
      if ((singleByteCodePointRanges.length - singleByteEndIndex) < amount) {
        singleByteCodePointRanges = Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length + amount);
      }
    }

    private void ensureDoubleByteCapacity() {
      if (doubleByteEndIndex == doubleByteCodePointRanges.length) {
        ensureDoubleByteCapacity(32);
      }
    }

    private void ensureDoubleByteCapacity(final int amount) {
      if ((doubleByteCodePointRanges.length - doubleByteEndIndex) < amount) {
        doubleByteCodePointRanges = Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length + amount);
      }
    }

    private void ensureTripleByteCapacity() {
      if (tripleByteEndIndex == tripleByteCodePointRanges.length) {
        ensureTripleByteCapacity(32);
      }
    }

    private void ensureTripleByteCapacity(final int amount) {
      if ((tripleByteCodePointRanges.length - tripleByteEndIndex) < amount) {
        tripleByteCodePointRanges = Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length + amount);
      }
    }

    private void removeSingleByteElement(final int index) {
      System.arraycopy(singleByteCodePointRanges, index + 1, singleByteCodePointRanges, index, singleByteCodePointRanges.length - index - 1);
      --singleByteEndIndex;
    }

    private void removeDoubleByteElement(final int index) {
      System.arraycopy(doubleByteCodePointRanges, index + 1, doubleByteCodePointRanges, index, doubleByteCodePointRanges.length - index - 1);
      --doubleByteEndIndex;
    }

    private void removeTripleByteElement(final int index) {
      System.arraycopy(tripleByteCodePointRanges, index + 1, tripleByteCodePointRanges, index, tripleByteCodePointRanges.length - index - 1);
      --tripleByteEndIndex;
    }

    @SuppressWarnings({"java:S3776", "java:S1199"})
    void compact() {
      compactSingleByteCodePointRanges();
      compactDoubleByteCodePointRanges();
      compactTripleByteCodePointRanges();
    }

    void compactTripleByteCodePointRanges() {

      Arrays.sort(tripleByteCodePointRanges, 0, tripleByteEndIndex);
      int previousInclusiveFrom;
      int previousInclusiveTo;
      int currentInclusiveFrom;
      int currentInclusiveTo;
      int i = 0;
      int j = 1;
      while (j < tripleByteEndIndex) {
        previousInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[i]);
        previousInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[i]);
        currentInclusiveFrom = getInclusiveFrom(tripleByteCodePointRanges[j]);
        currentInclusiveTo = getInclusiveTo(tripleByteCodePointRanges[j]);
        if (previousInclusiveTo >= currentInclusiveFrom) {
          if (previousInclusiveTo <= currentInclusiveTo) {
            tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
          }
          removeTripleByteElement(j);
        } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
          tripleByteCodePointRanges[i] = rangeToLong(previousInclusiveFrom, currentInclusiveTo);
          removeTripleByteElement(j);
        } else {
          ++i;
          ++j;
        }
      }
    }

    void compactDoubleByteCodePointRanges() {

      unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, doubleByteEndIndex);
      int previousInclusiveFrom;
      int previousInclusiveTo;
      int currentInclusiveFrom;
      int currentInclusiveTo;
      int i = 0;
      int j = 1;
      while (j < doubleByteEndIndex) {
        previousInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[i]);
        previousInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[i]);
        currentInclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[j]);
        currentInclusiveTo = getInclusiveTo(doubleByteCodePointRanges[j]);
        if (previousInclusiveTo >= currentInclusiveFrom) {
          if (previousInclusiveTo <= currentInclusiveTo) {
            doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
          }
          removeDoubleByteElement(j);
        } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
          doubleByteCodePointRanges[i] = rangeToInt(previousInclusiveFrom, currentInclusiveTo);
          removeDoubleByteElement(j);
        } else {
          ++i;
          ++j;
        }
      }
    }

    void compactSingleByteCodePointRanges() {

      Arrays.sort(singleByteCodePointRanges, 0, singleByteEndIndex);
      int previousInclusiveFrom;
      int previousInclusiveTo;
      int currentInclusiveFrom;
      int currentInclusiveTo;
      int i = 0;
      int j = 1;
      while (j < singleByteEndIndex) {
        previousInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[i]);
        previousInclusiveTo = getInclusiveTo(singleByteCodePointRanges[i]);
        currentInclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[j]);
        currentInclusiveTo = getInclusiveTo(singleByteCodePointRanges[j]);
        if (previousInclusiveTo >= currentInclusiveFrom) {
          if (previousInclusiveTo <= currentInclusiveTo) {
            singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
          }
          removeSingleByteElement(j);
        } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
          singleByteCodePointRanges[i] = rangeToChar(previousInclusiveFrom, currentInclusiveTo);
          removeSingleByteElement(j);
        } else {
          ++i;
          ++j;
        }
      }
    }

  }
}
