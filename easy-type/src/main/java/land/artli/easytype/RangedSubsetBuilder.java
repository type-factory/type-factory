package land.artli.easytype;

import java.util.Collection;

interface RangedSubsetBuilder extends SubsetBuilder {

  @Override
  RangedSubsetBuilder addChar(final char ch);

  @Override
  RangedSubsetBuilder addChars(final char... chars);

  @Override
  RangedSubsetBuilder addCharRange(final char inclusiveFrom, final char inclusiveTo);

  @Override
  RangedSubsetBuilder addCodePoint(final int codePoint);

  @Override
  RangedSubsetBuilder addCodePoints(final int... codePoints);

  @Override
  RangedSubsetBuilder addCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  RangedSubsetBuilder addSubset(final Subset... subset);

  @Override
  RangedSubsetBuilder addSubset(final Collection<Subset> subsets);

  @Override
  RangedSubset build();
}
