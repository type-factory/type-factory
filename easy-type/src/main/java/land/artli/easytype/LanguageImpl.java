package land.artli.easytype;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

class LanguageImpl implements Language, RangedSubset {

  private static final char[] EMPTY_SINGLE_BYTE_ARRAY = new char[0];
  private static final int[] EMPTY_DOUBLE_BYTE_ARRAY = new int[0];
  private static final long[] EMPTY_TRIPLE_BYTE_ARRAY = new long[0];

  private final Locale locale;

  private final char[] singleByteCodePointRanges;

  private final int[] doubleByteCodePointRanges;

  private final long[] tripleByteCodePointRanges;

  LanguageImpl(
      final Locale locale,
      final char[] singleByteCodePointRanges) {
    this(locale, singleByteCodePointRanges, EMPTY_DOUBLE_BYTE_ARRAY, EMPTY_TRIPLE_BYTE_ARRAY);
  }

  LanguageImpl(
      final Locale locale,
      final int[] doubleByteCodePointRanges) {
    this(locale, EMPTY_SINGLE_BYTE_ARRAY, doubleByteCodePointRanges, EMPTY_TRIPLE_BYTE_ARRAY);
  }

  LanguageImpl(
      final Locale locale,
      final long[] tripleByteCodePointRanges) {
    this(locale, EMPTY_SINGLE_BYTE_ARRAY, EMPTY_DOUBLE_BYTE_ARRAY, tripleByteCodePointRanges);
  }

  LanguageImpl(
      final Locale locale,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges) {
    this(locale, singleByteCodePointRanges, doubleByteCodePointRanges, EMPTY_TRIPLE_BYTE_ARRAY);
  }

  LanguageImpl(
      final Locale locale,
      final char[] singleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this(locale, singleByteCodePointRanges, EMPTY_DOUBLE_BYTE_ARRAY, tripleByteCodePointRanges);
  }

  LanguageImpl(
      final Locale locale,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this(locale, EMPTY_SINGLE_BYTE_ARRAY, doubleByteCodePointRanges, tripleByteCodePointRanges);
  }

  LanguageImpl(
      final Locale locale,
      final char[] singleByteCodePointRanges,
      final int[] doubleByteCodePointRanges,
      final long[] tripleByteCodePointRanges) {
    this.locale = locale;
    this.singleByteCodePointRanges = singleByteCodePointRanges;
    this.doubleByteCodePointRanges = doubleByteCodePointRanges;
    this.tripleByteCodePointRanges = tripleByteCodePointRanges;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(int codePoint) {
    return false;
  }

  public Locale getLocale() {
    return locale;
  }

  @Override
  public char[] getSingleByteCodePointRanges() {
    return singleByteCodePointRanges == EMPTY_SINGLE_BYTE_ARRAY
        ? singleByteCodePointRanges
        : Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
  }

  @Override
  public int[] getDoubleByteCodePointRanges() {
    return doubleByteCodePointRanges == EMPTY_DOUBLE_BYTE_ARRAY
        ? doubleByteCodePointRanges
        : Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
  }

  @Override
  public long[] getTripleByteCodePointRanges() {
    return tripleByteCodePointRanges == EMPTY_TRIPLE_BYTE_ARRAY
        ? tripleByteCodePointRanges
        : Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
  }

  @Override
  public Collection<CodePointRange> ranges() {
    return RangedSubsetUtils.aggregateCodePointRangeData(
        singleByteCodePointRanges,
        doubleByteCodePointRanges,
        tripleByteCodePointRanges);
  }
}
