package org.datatypeproject;

import static org.datatypeproject.RangedSubsetUtils.getInclusiveFrom;
import static org.datatypeproject.RangedSubsetUtils.getInclusiveTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BlockRangedSubsetBuilderImpl implements BlockRangedSubsetBuilder {

  final BlockRanges includes = new BlockRanges();

  final BlockRanges excludes = new BlockRanges();

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long includeUnicodeCategoryBitFlags;

  /**
   * Each bit of the following value corresponds to a {@link Category} identified by the {@link Category#bitMask};
   */
  protected long excludeUnicodeCategoryBitFlags;

  public BlockRangedSubsetBuilder includeUnicodeCategory(final Category category) {
    includeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public BlockRangedSubsetBuilder includeUnicodeCategories(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        includeUnicodeCategory(category);
      }
    }
    return this;
  }

  public BlockRangedSubsetBuilder excludeUnicodeCategory(final Category category) {
    excludeUnicodeCategoryBitFlags |= category.bitMask;
    return this;
  }

  public BlockRangedSubsetBuilder excludeUnicodeCategory(final Category... categories) {
    if (categories != null) {
      for (Category category : categories) {
        excludeUnicodeCategory(category);
      }
    }
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeChar(char ch) {
    includes.addChar(ch);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeChars(char... chars) {
    includes.addChars(chars);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeCharRange(char inclusiveFrom, char inclusiveTo) {
    includes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeCodePoint(int codePoint) {
    includes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeCodePoints(int... codePoints) {
    includes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    includes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeSubset(Subset subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeSubsets(Subset... subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder includeSubsets(Collection<Subset> subsets) {
    includes.addSubset(subsets);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeChar(char ch) {
    excludes.addChar(ch);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeChars(char... chars) {
    excludes.addChars(chars);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeCharRange(char inclusiveFrom, char inclusiveTo) {
    excludes.addCharRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeCodePoint(int codePoint) {
    excludes.addCodePoint(codePoint);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeCodePoints(int... codePoints) {
    excludes.addCodePoints(codePoints);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo) {
    excludes.addCodePointRange(inclusiveFrom, inclusiveTo);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeSubset(Subset subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeSubsets(Subset... subsets) {
    excludes.addSubset(subsets);
    return this;
  }

  @Override
  public BlockRangedSubsetBuilder excludeSubsets(Collection<Subset> subsets) {
    excludes.addSubset(subsets);
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
  public BlockRangedSubset build() {
    excludes.compact();
    includes.removeCodePointRanges(excludes);
    includes.removeCodepointCategories(excludeUnicodeCategoryBitFlags);
    includes.compact();
    compactCategories();

//    if (containsExcludes()) {
//      return new BlockRangedSubsetWithCategoriesAndExcludesImpl(
//          includeUnicodeCategoryBitFlags,
//          excludeUnicodeCategoryBitFlags,
//          includes.blocks,
//          includes.singleByteCodePointRangeByBlock,
//          excludes.blocks,
//          excludes.singleByteCodePointRangeByBlock);
//    }

    if (containsUnicodeCategories()) {
      return new BlockRangedSubsetWithCategoriesImpl(
          includeUnicodeCategoryBitFlags,
          includes.blocks,
          includes.singleByteCodePointRangeByBlock);
    }

    return new BlockRangedSubsetImpl(
        includes.blocks,
        includes.singleByteCodePointRangeByBlock);
  }

  static class BlockRanges {

    final static int INITIAL_CAPACITY = 32;

    final int VALUE_MASK = 0x000000FF;

    int[] blocks = new int[INITIAL_CAPACITY];

    int blocksSize = 0;

    char[][] singleByteCodePointRangeByBlock = new char[INITIAL_CAPACITY][];

    int[] singleByteCodePointRangeSizes = new int[INITIAL_CAPACITY];

    boolean isEmpty() {
      return blocksSize == 0;
    }

    boolean isNotEmpty() {
      return !isEmpty();
    }

    private void ensureBlockCapacity() {
      if (blocks.length == blocksSize) {
        blocks = Arrays.copyOf(blocks, blocks.length + INITIAL_CAPACITY);
        singleByteCodePointRangeByBlock = Arrays.copyOf(singleByteCodePointRangeByBlock, singleByteCodePointRangeByBlock.length + INITIAL_CAPACITY);
        singleByteCodePointRangeSizes = Arrays.copyOf(singleByteCodePointRangeSizes, singleByteCodePointRangeSizes.length + INITIAL_CAPACITY);
      }
    }

    private void ensureSingleByteCapacity(final int index) {
      if ((singleByteCodePointRangeByBlock[index].length == singleByteCodePointRangeSizes[index])) {
        singleByteCodePointRangeByBlock[index] = Arrays.copyOf(singleByteCodePointRangeByBlock[index],
            singleByteCodePointRangeByBlock[index].length + INITIAL_CAPACITY);
      }
    }

    void addChar(final char ch) {
      addCharRange(ch, ch);
    }

    void addChars(final char... chars) {
      if (chars != null) {
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
        for (int i = 0; i < codePoints.length; ++i) {
          addCodePoint(codePoints[i]);
        }
      }
    }

    void addCodePointRange(int inclusiveFrom, int inclusiveTo) {

      if (inclusiveFrom > inclusiveTo) {
        final int temp = inclusiveFrom;
        inclusiveFrom = inclusiveTo;
        inclusiveTo = temp;
      }

      int blockKeyFrom = inclusiveFrom >> 8;
      int blockKeyTo = inclusiveTo >> 8;

      for (int blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        final int index = blocksSize == 0 ? 0 : Arrays.binarySearch(blocks, 0, blocksSize, blockKey);
        ensureBlockCapacity();
        if (blocksSize == 0 || blocks[index] != blockKey) {
          System.arraycopy(
              blocks, index,
              blocks, index + 1,
              blocks.length - index - 1);
          System.arraycopy(
              singleByteCodePointRangeByBlock, index,
              singleByteCodePointRangeByBlock, index + 1,
              singleByteCodePointRangeByBlock.length - index - 1);
          System.arraycopy(
              singleByteCodePointRangeSizes, index,
              singleByteCodePointRangeSizes, index + 1,
              singleByteCodePointRangeSizes.length - index - 1);
          blocks[index] = blockKey;
          singleByteCodePointRangeByBlock[index] = new char[INITIAL_CAPACITY];
          singleByteCodePointRangeSizes[index] = 0;
          blocksSize++;
        }
        ensureSingleByteCapacity(index);
        final int from = blockKey == blockKeyFrom ? (inclusiveFrom & VALUE_MASK) : 0x00;
        final int to = blockKey == blockKeyTo ? (inclusiveTo & VALUE_MASK) : 0xFF;
        singleByteCodePointRangeByBlock[index][singleByteCodePointRangeSizes[index]] = RangedSubsetUtils.rangeToChar(from, to);
        singleByteCodePointRangeSizes[index]++;
      }
    }

    void removeCodePointRanges(final BlockRanges blockRanges) {
      for (int index = 0; index < blockRanges.blocksSize; ++index) {
        final int block = blockRanges.blocks[index] << 8;
        final char[] singleByteCodePointRange = blockRanges.singleByteCodePointRangeByBlock[index];
        final int singleByteCodePointRangeSize = blockRanges.singleByteCodePointRangeSizes[index];
        for (int j = 0; j < singleByteCodePointRangeSize; ++j) {
          final int inclusiveFrom = block | getInclusiveFrom(singleByteCodePointRange[j]);
          final int inclusiveTo = block | getInclusiveTo(singleByteCodePointRange[j]);
          removeCodePointRange(inclusiveFrom, inclusiveTo);
        }
      }
    }

    void removeCodePoint(final int codePoint) {
      removeCodePointRange(codePoint, codePoint);
    }

    void removeCodePointRange(int removeInclusiveFrom, int removeInclusiveTo) {

      if (removeInclusiveFrom > removeInclusiveTo) {
        final int temp = removeInclusiveFrom;
        removeInclusiveFrom = removeInclusiveTo;
        removeInclusiveTo = temp;
      }

      final int blockKeyFrom = removeInclusiveFrom >> 8;
      final int blockKeyTo = removeInclusiveTo >> 8;
      removeInclusiveFrom |= VALUE_MASK;
      removeInclusiveTo |= VALUE_MASK;

      for (int blockKey = blockKeyFrom; blockKey <= blockKeyTo; ++blockKey) {
        final int index = blocksSize == 0 ? 0 : Arrays.binarySearch(blocks, 0, blocksSize, blockKey);
        if (blocksSize == 0 || blocks[index] != blockKey) {
          final char[] singleByteCodePointRanges = singleByteCodePointRangeByBlock[index];
          for (int i = singleByteCodePointRanges.length - 1; i >= 0; --i) {
            final char range = singleByteCodePointRanges[i];
            final int inclusiveFrom = getInclusiveFrom(range);
            final int inclusiveTo = getInclusiveTo(range);
            if (removeInclusiveFrom <= inclusiveTo && removeInclusiveTo >= inclusiveTo) {
              removeArrayElement(singleByteCodePointRanges, index);
              singleByteCodePointRangeSizes[index]--;
            } else if (inclusiveFrom < removeInclusiveFrom && inclusiveTo > removeInclusiveTo) {
              singleByteCodePointRanges[i] = RangedSubsetUtils.rangeToChar(inclusiveFrom, removeInclusiveFrom - 1);
              addCodePointRange(removeInclusiveTo + 1, inclusiveTo);
            } else if (removeInclusiveTo >= inclusiveFrom) {
              singleByteCodePointRanges[i] = RangedSubsetUtils.rangeToChar(removeInclusiveTo + 1, inclusiveTo);
            } else if (removeInclusiveFrom <= inclusiveTo) {
              singleByteCodePointRanges[i] = RangedSubsetUtils.rangeToChar(inclusiveTo, removeInclusiveFrom - 1);
            }
          }
        }
      }
    }

    void removeCodepointCategories(final long unicodeCategoryBitFlags) {
      if (unicodeCategoryBitFlags == 0L) {
        return;
      }
      final List<Integer> codepointsToRemove = new ArrayList<>();

      for (int blockIndex = 0; blockIndex < blocksSize; ++blockIndex) {
        final int blockKey = blocks[blockIndex];
        final int blockRange = blockKey << 8;
        final char[] singleByteCodePointRanges = singleByteCodePointRangeByBlock[blockIndex];
        for (int rangeIndex = 0; rangeIndex < singleByteCodePointRanges.length; ++rangeIndex) {
          final char range = singleByteCodePointRanges[rangeIndex];
          final int inclusiveFrom = blockRange | getInclusiveFrom(range);
          final int inclusiveTo = blockRange | getInclusiveTo(range);
          for (int codePoint = inclusiveFrom; codePoint <= inclusiveTo; ++codePoint) {
            if ((unicodeCategoryBitFlags & (0x1L << Character.getType(codePoint))) > 0L) {
              codepointsToRemove.add(codePoint);
            }
          }
        }
      }

      for (int codepoint : codepointsToRemove) {
        removeCodePoint(codepoint);
      }
    }

    @SuppressWarnings("java:S3776")
    void addSubset(final Subset... subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubset(final Collection<Subset> subsets) {
      if (subsets != null) {
        for (Subset subset : subsets) {
          addSubset(subset);
        }
      }
    }

    void addSubset(final Subset subset) {
      if (subset != null && subset.isNotEmpty()) {
        /*if (subset instanceof BlockRangedSubset blockRangedSubset) { // TODO this should BlockedSubset
          // More efficiently adds the ranges
          addSubset(blockRangedSubset);
        } else */
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
      for (char c : subset.getSingleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(c),
            getInclusiveTo(c));
      }
      for (int j : subset.getDoubleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(j),
            getInclusiveTo(j));
      }
      for (long l : subset.getTripleByteCodePointRanges()) {
        addCodePointRange(
            getInclusiveFrom(l),
            getInclusiveTo(l));
      }
    }

    @SuppressWarnings({"java:S3776", "java:S1199"})
    void compact() {
      blocks = Arrays.copyOf(blocks, blocksSize);
      singleByteCodePointRangeByBlock = Arrays.copyOf(singleByteCodePointRangeByBlock, blocksSize);
      singleByteCodePointRangeSizes = Arrays.copyOf(singleByteCodePointRangeSizes, blocksSize);
      for (int blockIndex = 0; blockIndex < blocksSize; ++blockIndex) {
        char[] singleByteCodePointRanges = singleByteCodePointRangeByBlock[blockIndex];
        int singleByteEndIndex = singleByteCodePointRangeSizes[blockIndex];
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
              singleByteCodePointRanges[i] = RangedSubsetUtils.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
            }
            removeArrayElement(singleByteCodePointRanges, j);
            singleByteEndIndex--;
          } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
            singleByteCodePointRanges[i] = RangedSubsetUtils.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
            removeArrayElement(singleByteCodePointRanges, j);
            singleByteEndIndex--;
          } else {
            ++i;
            ++j;
          }
        }
        singleByteCodePointRangeByBlock[blockIndex] = singleByteCodePointRanges;
        singleByteCodePointRangeSizes[blockIndex] = singleByteEndIndex;
        singleByteCodePointRangeByBlock[blockIndex] = Arrays.copyOf(
            singleByteCodePointRangeByBlock[blockIndex],
            singleByteCodePointRangeSizes[blockIndex]);
      }
    }

    private void removeArrayElement(final char[] array, final int index) {
      System.arraycopy(array, index + 1, array, index, array.length - index - 1);
    }

  }


}
