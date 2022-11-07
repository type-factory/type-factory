/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import org.typefactory.Subset;
import org.typefactory.Subset.SubsetBuilder;

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
