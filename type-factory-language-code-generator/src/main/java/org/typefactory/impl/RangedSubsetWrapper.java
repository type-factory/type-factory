package org.typefactory.impl;

public class RangedSubsetWrapper implements SubsetWrapper, RangedSubset {

  private final RangedSubsetImpl wrapped;

  public RangedSubsetWrapper(RangedSubsetImpl wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public boolean isEmpty() {
    return wrapped.isEmpty();
  }

  @Override
  public boolean contains(int codePoint) {
    return wrapped.contains(codePoint);
  }

  @Override
  public Iterable<CodePointRange> ranges() {
    return wrapped.ranges();
  }

  @Override
  public int numberOfCodePointRanges() {
    return wrapped.numberOfCodePointRanges();
  }

  @Override
  public int numberOfCodePointsInCodePointRanges() {
    return wrapped.numberOfCodePointsInCodePointRanges();
  }

  @Override
  public char[] getSingleByteCodePointRanges() {
    return wrapped.getSingleByteCodePointRanges();
  }

  @Override
  public int[] getDoubleByteCodePointRanges() {
    return wrapped.getDoubleByteCodePointRanges();
  }

  @Override
  public long[] getTripleByteCodePointRanges() {
    return wrapped.getTripleByteCodePointRanges();
  }
}
