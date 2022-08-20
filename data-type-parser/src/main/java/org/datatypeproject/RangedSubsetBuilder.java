package org.datatypeproject;

import java.util.Collection;

interface RangedSubsetBuilder extends SubsetBuilder {

  RangedSubsetBuilder includeUnicodeCategory(final Category category);

  RangedSubsetBuilder includeUnicodeCategories(final Category... categories);

  @Override
  RangedSubsetBuilder includeChar(final char ch);

  @Override
  RangedSubsetBuilder includeChars(final char... chars);

  @Override
  RangedSubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo);

  @Override
  RangedSubsetBuilder includeCodePoint(final int codePoint);

  @Override
  RangedSubsetBuilder includeCodePoints(final int... codePoints);

  @Override
  RangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  RangedSubsetBuilder includeSubset(final Subset subset);

  @Override
  RangedSubsetBuilder includeSubsets(final Subset... subsets);

  @Override
  RangedSubsetBuilder includeSubsets(final Collection<Subset> subsets);

  @Override
  RangedSubsetBuilder excludeChar(final char ch);

  @Override
  RangedSubsetBuilder excludeChars(final char... chars);

  @Override
  RangedSubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);

  @Override
  RangedSubsetBuilder excludeCodePoint(final int codePoint);

  @Override
  RangedSubsetBuilder excludeCodePoints(final int... codePoints);

  @Override
  RangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  RangedSubsetBuilder excludeSubset(final Subset subsets);

  @Override
  RangedSubsetBuilder excludeSubsets(final Subset... subsets);

  @Override
  RangedSubsetBuilder excludeSubsets(final Collection<Subset> subsets);

  @Override
  Subset build();
}
