package org.datatypeproject.impl;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import org.datatypeproject.Subset;
import org.datatypeproject.Subset.SubsetBuilder;

public interface SubsetWrapper {

  static SubsetWrapper optimisedSubset(final UnicodeSet unicodeSet) {
    final SubsetBuilder subsetBuilder = Subset.builder();
    for (EntryRange range : unicodeSet.ranges()) {
      subsetBuilder.includeCodePointRange(range.codepoint, range.codepointEnd);
    }
    final Subset subset = subsetBuilder.build();
    if (subset instanceof RangedSubsetImpl rangedSubset) {
      return new RangedSubsetWrapper(rangedSubset);
    }
    if (subset instanceof HashedRangedSubsetImpl hashedRangedSubset) {
      return new HashedRangedSubsetWrapper(hashedRangedSubset);
    }
    if (subset instanceof OptimalHashedRangedSubsetImpl optimalHashedRangedSubset) {
      return new OptimalHashedRangedSubsetWrapper(optimalHashedRangedSubset);
    }
    throw new RuntimeException("Unknown sunset type.");
  }
}
