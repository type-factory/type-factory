//package org.datatypeproject.impl;
//
//import java.util.Collection;
//import org.datatypeproject.Category;
//import org.datatypeproject.Subset;
//import org.datatypeproject.Subset.SubsetBuilder;
//
//interface HashedRangedSubsetBuilder extends SubsetBuilder {
//
//  HashedRangedSubsetBuilder includeUnicodeCategory(final Category category);
//
//  HashedRangedSubsetBuilder includeUnicodeCategories(final Category... categories);
//
//  @Override
//  HashedRangedSubsetBuilder includeChar(final char ch);
//
//  @Override
//  HashedRangedSubsetBuilder includeChars(final char... chars);
//
//  @Override
//  HashedRangedSubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo);
//
//  @Override
//  HashedRangedSubsetBuilder includeCodePoint(final int codePoint);
//
//  @Override
//  HashedRangedSubsetBuilder includeCodePoints(final int... codePoints);
//
//  @Override
//  HashedRangedSubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo);
//
//  @Override
//  HashedRangedSubsetBuilder includeSubset(final Subset subset);
//
//  @Override
//  HashedRangedSubsetBuilder includeSubsets(final Subset... subsets);
//
//  @Override
//  HashedRangedSubsetBuilder includeSubsets(final Collection<Subset> subsets);
//
//  @Override
//  HashedRangedSubsetBuilder excludeChar(final char ch);
//
//  @Override
//  HashedRangedSubsetBuilder excludeChars(final char... chars);
//
//  @Override
//  HashedRangedSubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);
//
//  @Override
//  HashedRangedSubsetBuilder excludeCodePoint(final int codePoint);
//
//  @Override
//  HashedRangedSubsetBuilder excludeCodePoints(final int... codePoints);
//
//  @Override
//  HashedRangedSubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);
//
//  @Override
//  HashedRangedSubsetBuilder excludeSubset(final Subset subsets);
//
//  @Override
//  HashedRangedSubsetBuilder excludeSubsets(final Subset... subsets);
//
//  @Override
//  HashedRangedSubsetBuilder excludeSubsets(final Collection<Subset> subsets);
//
//  @Override
//  HashedRangedSubset build();
//}
