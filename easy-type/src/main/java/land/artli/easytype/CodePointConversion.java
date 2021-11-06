package land.artli.easytype;

import java.util.Arrays;
import java.util.Comparator;

class CodePointConversion {

  static final Comparator<CodePointConversion> CODE_POINT_COMPARATOR =
      Comparator.nullsFirst(Comparator
          .comparing(CodePointConversion::getFromCodePoint)
          .thenComparing((o1, o2) -> Arrays.compare(o1.getToCodePoints(), o2.getToCodePoints())));

  final int fromCodePoint;
  final int[] toCodePoints;

  public CodePointConversion(int fromCodePoint, int[] toCodePoints) {
    this.fromCodePoint = fromCodePoint;
    this.toCodePoints = toCodePoints;
  }

  public int getFromCodePoint() {
    return fromCodePoint;
  }

  public int[] getToCodePoints() {
    return toCodePoints;
  }
}
