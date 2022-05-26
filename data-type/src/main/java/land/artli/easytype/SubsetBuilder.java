package land.artli.easytype;

import java.util.Collection;

interface SubsetBuilder {

  SubsetBuilder includeChar(final char ch);

  SubsetBuilder includeChars(final char... chars);

  SubsetBuilder includeCharRange(final char inclusiveFrom, final char inclusiveTo);

  SubsetBuilder includeCodePoint(final int codePoint);

  SubsetBuilder includeCodePoints(final int... codePoints);

  SubsetBuilder includeCodePointRange(int inclusiveFrom, int inclusiveTo);

  SubsetBuilder includeSubset(final Subset... subsets);

  SubsetBuilder includeSubset(final Collection<Subset> subsets);

  SubsetBuilder excludeChar(final char ch);

  SubsetBuilder excludeChars(final char... chars);

  SubsetBuilder excludeCharRange(final char inclusiveFrom, final char inclusiveTo);

  SubsetBuilder excludeCodePoint(final int codePoint);

  SubsetBuilder excludeCodePoints(final int... codePoints);

  SubsetBuilder excludeCodePointRange(int inclusiveFrom, int inclusiveTo);

  SubsetBuilder excludeSubset(final Subset... subset);

  SubsetBuilder excludeSubset(final Collection<Subset> subsets);

  Subset build();
}
