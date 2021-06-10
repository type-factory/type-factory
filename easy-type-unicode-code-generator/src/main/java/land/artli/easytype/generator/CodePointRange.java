package land.artli.easytype.generator;

import java.util.Comparator;

public class CodePointRange {

  static final Comparator<CodePointRange> COMPARATOR =
      Comparator.nullsFirst(Comparator
          .comparing(CodePointRange::getInclusiveFrom)
          .thenComparing(CodePointRange::getInclusiveTo));

  final int inclusiveFrom;
  final int inclusiveTo;

  CodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    this.inclusiveFrom = Math.max(Character.MIN_CODE_POINT, Math.min(inclusiveFrom, inclusiveTo));
    this.inclusiveTo = Math.min(Character.MAX_CODE_POINT, Math.max(inclusiveFrom, inclusiveTo));
  }

  int getInclusiveFrom() {
    return inclusiveFrom;
  }

  int getInclusiveTo() {
    return inclusiveTo;
  }
}
