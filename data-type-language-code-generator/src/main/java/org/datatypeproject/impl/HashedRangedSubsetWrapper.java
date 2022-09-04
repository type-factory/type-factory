package org.datatypeproject.impl;

public class HashedRangedSubsetWrapper implements SubsetWrapper, HashedRangedSubset {

  private final HashedRangedSubsetImpl wrapped;

  public HashedRangedSubsetWrapper(HashedRangedSubsetImpl wrapped) {
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
  public char[][] getBlockKeys() {
    return wrapped.getBlockKeys();
  }

  @Override
  public char[][][] getCodePointRangesByBlock() {
    return wrapped.getCodePointRangesByBlock();
  }
}
