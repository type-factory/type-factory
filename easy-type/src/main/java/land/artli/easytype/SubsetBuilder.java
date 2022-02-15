package land.artli.easytype;

interface SubsetBuilder {

  SubsetBuilder addChar(final char ch);

  SubsetBuilder addChars(final char... chars);

  SubsetBuilder addCharRange(final char inclusiveFrom, final char inclusiveTo);

  SubsetBuilder addCodePoint(final int codePoint);

  SubsetBuilder addCodePoints(final int... codePoints);

  SubsetBuilder addCodePointRange(int inclusiveFrom, int inclusiveTo);

  Subset build();

//  B addChar(final char ch);
//
//  B addChars(final char... chars);
//
//  B addCharRange(final char inclusiveFrom, final char inclusiveTo);
//
//  B addCodePoint(final int codePoint);
//
//  B addCodePoints(final int... codePoints);
//
//  B addCodePointRange(int inclusiveFrom, int inclusiveTo);
//
//  S build();
}
