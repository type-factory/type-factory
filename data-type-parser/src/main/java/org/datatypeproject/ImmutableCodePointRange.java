package org.datatypeproject;

import java.io.Serial;
import java.util.Objects;
import org.datatypeproject.Subset.CodePointRange;

/**
 * An immutable code-point range – a tuple of the inclusive-from and inclusive-to code-points.
 */
public final class ImmutableCodePointRange implements CodePointRange {

  @Serial
  private static final long serialVersionUID = -7777682911722362584L;

  /**
   * The inclusive-from code-point of the range.
   */
  public final int inclusiveFrom;

  /**
   * The inclusive-to code-point of the range.
   */
  public final int inclusiveTo;

  /**
   * Create an immutable code-point range.
   *
   * @param inclusiveFrom the inclusive-from code-point value of the range.
   * @param inclusiveTo   the inclusive-to code-point value of the range.
   */
  public ImmutableCodePointRange(final int inclusiveFrom, final int inclusiveTo) {
    this.inclusiveFrom = inclusiveFrom;
    this.inclusiveTo = inclusiveTo;
  }

  /**
   * <p>Returns the inclusive-from code-point of the range.</p>
   *
   * <p>The {@link #inclusiveFrom} member variable can be accessed directly – it is immutable, {@code public final}. This getter-method
   * has been provided for frameworks that expect this format for properties.</p>
   *
   * @return the inclusive-from code-point of the range.
   */
  @Override
  public int getInclusiveFrom() {
    return inclusiveFrom;
  }

  /**
   * <p>Returns the inclusive-to code-point of the range.</p>
   *
   * <p>The {@link #inclusiveTo} member variable can be accessed directly – it is immutable, {@code public final}. This getter-method
   * has been provided for frameworks that expect this format for properties.</p>
   *
   * @return the inclusive-to code-point of the range.
   */
  @Override
  public int getInclusiveTo() {
    return inclusiveTo;
  }

  /**
   * A {@code CodePointRange} instances are equal if and only if both their inclusive-from and inclusive-to code-points are equal.
   *
   * @param o the other {@code CodePointRange} instance to compare this instance to.
   * @return {@code true} if this {@code CodePointRange} instance and the other instance {@code o} have both their inclusive-from and inclusive-to
   * code-points are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImmutableCodePointRange)) {
      return false;
    }
    final ImmutableCodePointRange that = (ImmutableCodePointRange) o;
    return inclusiveFrom == that.inclusiveFrom && inclusiveTo == that.inclusiveTo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(inclusiveFrom, inclusiveTo);
  }

  /**
   * Null-safe comparison of this {@code CodePointRange} instance to another first comparing the {@code inclusiveFrom} value and then comparing the
   * {@code inclusiveTo} value.
   *
   * @param o the other {@code CodePointRange} instance to compare to. May be {@code null}.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object when first
   * comparing the {@code inclusiveFrom} value and then comparing the {@code inclusiveTo} value. Returns {@code 1} if the other object is
   * {@code null}.
   */
  @Override
  public int compareTo(final CodePointRange o) {
    if (o == null) {
      return 1;
    }
    int result = inclusiveFrom - o.getInclusiveFrom();
    if (result != 0) {
      return result;
    }
    return inclusiveTo - o.getInclusiveTo();
  }

  /**
   * Returns the code-point range as a hexadecimal range in the format: {@code 0x00000000..0x00000000}
   *
   * @return the code-point range as a hexadecimal range in the format: {@code 0x00000000..0x00000000}
   */
  @Override
  public String toString() {
    return String.format("0x%08x..0x%08x", inclusiveFrom, inclusiveTo);
  }

  @Override
  public CodePointRange copy() {
    return new ImmutableCodePointRange(inclusiveFrom, inclusiveTo);
  }
}