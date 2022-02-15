package land.artli.easytype;

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

  RangedSubsetBuilder addRangedSubset(final RangedSubset subset);

  @Override
  RangedSubsetBuilder addCodePointRange(int inclusiveFrom, int inclusiveTo);

  @Override
  RangedSubset build();
}
