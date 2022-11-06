package org.typefactory.numbertypes;

import org.typefactory.IntegerType;
import org.typefactory.ShortType;
import org.typefactory.TypeParser;

public final class InvoiceNumber extends IntegerType {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder()
          .errorMessage("must be a 12-digit number")
          .removeAllWhitespace()
          .acceptDigits0to9()
          .fixedSize(12)
          .build();

  private InvoiceNumber(final Integer value) {
    super(value);
  }

  public static InvoiceNumber of(final CharSequence value) {
    return TYPE_PARSER.parseToIntegerType(value, InvoiceNumber::new);
  }
}
