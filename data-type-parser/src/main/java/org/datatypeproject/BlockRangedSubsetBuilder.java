package org.datatypeproject;

import java.util.Collection;

interface BlockRangedSubsetBuilder extends SubsetBuilder {

  BlockRangedSubsetBuilder includeUnicodeCategory(final Category category);

  BlockRangedSubsetBuilder includeUnicodeCategories(final Category... categories);

  @Override
  BlockRangedSubsetBuilder includeChar(final char ch);

  @Override
  BlockRangedSubsetBuilder includeChars(final char... chars);

  @Override
  BlockRangedSubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo);

  @Override
  BlockRangedSubsetBuilder includeCodePoint(final int codePoint);

  @Override
  BlockRangedSubsetBuilder includeCodePoints(final int... codePoints);

  @Override
  BlockRangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  BlockRangedSubsetBuilder includeSubset(final Subset subset);

  @Override
  BlockRangedSubsetBuilder includeSubsets(final Subset... subsets);

  @Override
  BlockRangedSubsetBuilder includeSubsets(final Collection<Subset> subsets);

  @Override
  BlockRangedSubsetBuilder excludeChar(final char ch);

  @Override
  BlockRangedSubsetBuilder excludeChars(final char... chars);

  @Override
  BlockRangedSubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);

  @Override
  BlockRangedSubsetBuilder excludeCodePoint(final int codePoint);

  @Override
  BlockRangedSubsetBuilder excludeCodePoints(final int... codePoints);

  @Override
  BlockRangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  BlockRangedSubsetBuilder excludeSubset(final Subset subsets);

  @Override
  BlockRangedSubsetBuilder excludeSubsets(final Subset... subsets);

  @Override
  BlockRangedSubsetBuilder excludeSubsets(final Collection<Subset> subsets);

  @Override
  BlockRangedSubset build();
}
