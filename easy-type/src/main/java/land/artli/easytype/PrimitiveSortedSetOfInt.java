package land.artli.easytype;

import java.util.Arrays;

class PrimitiveSortedSetOfInt {

  private static final int CAPACITY_INCREMENT = 4;
  private int[] integers;

  private int size = 0;

  PrimitiveSortedSetOfInt() {
    this.integers = new int[CAPACITY_INCREMENT];
  }

  private void ensureCapacity() {
    if ((integers.length - size) < CAPACITY_INCREMENT) {
      integers = Arrays.copyOf(integers, integers.length + CAPACITY_INCREMENT);
    }
  }

  boolean add(final int value) {
    ensureCapacity();
    if (size < 5) {
      for (int i = 0; i < size; ++i) {
        if (integers[i] == value) {
          return false;
        }
        if (integers[i] > value) {
          System.arraycopy(integers, i, integers, i + 1, size - i);
          integers[i] = value;
          return true;
        }
      }
    } else {
      final int i = Arrays.binarySearch(integers, 0, size, value);
      if (integers[i] == value) {
        return false;
      }
      System.arraycopy(integers, i, integers, i + 1, size - i);
      integers[i] = value;
      return true;
    }
    integers[size++] = value;
    return true;
  }

  public int[] toArray() {
    return Arrays.copyOf(integers, size);
  }
}
