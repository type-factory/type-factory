package org.typefactory.impl;

public class OptimalHashedRangedSubsetWrapper implements SubsetWrapper, OptimalHashedRangedSubset {

  private final OptimalHashedRangedSubsetImpl wrapped;

  public OptimalHashedRangedSubsetWrapper(OptimalHashedRangedSubsetImpl wrapped) {
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
  public char[] getBlockKeySet() {
    return wrapped.getBlockKeySet();
  }

  @Override
  public char[] getBlockKeys() {
    return wrapped.getBlockKeys();
  }

  @Override
  public char[][] getCodePointRangesByBlock() {
    return wrapped.getCodePointRangesByBlock();
  }
}
