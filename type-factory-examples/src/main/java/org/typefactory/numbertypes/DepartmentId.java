package org.typefactory.numbertypes;

import org.typefactory.LongType;
import org.typefactory.ShortType;
import org.typefactory.TypeParser;

public final class DepartmentId extends ShortType {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 4-digit number")
          .removeAllWhitespace()
          .acceptDigits0to9()
          .fixedSize(4)
          .build();

  private DepartmentId(final Short value) {
    super(value);
  }

  public static DepartmentId of(final CharSequence value) {
    return TYPE_PARSER.parseToShortType(value, DepartmentId::new);
  }
}
