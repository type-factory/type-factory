package org.datatypeproject.numbertypes;

import org.datatypeproject.LongType;
import org.datatypeproject.TypeParser;

public final class ProductId extends LongType {

  private static final TypeParser TYPE_PARSER =
      TypeParser.builder(ProductId.class)
          .errorMessage("must be a 16-digit number")
          .removeAllWhitespace()
          .acceptDigits0to9()
          .fixedSize(16)
          .build();

  private ProductId(final Long value) {
    super(value);
  }

  public static ProductId of(final CharSequence value) {
    return TYPE_PARSER.parseToLongType(value, ProductId::new);
  }
}
