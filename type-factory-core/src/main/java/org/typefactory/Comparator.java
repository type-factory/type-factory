package org.typefactory;

import org.typefactory.impl.NumericCharSequenceComparator;

public interface Comparator<T> extends java.util.Comparator<T> {

  /**
   * <p>Returns a numerical order comparator that compares two {@link CharSequence}s containing numerical values.
   * All characters in the char-sequence should be the decimal digits and/or the letters a-zA-Z allowing for numbers to use any base from base-2
   * through to base-36. It will ignore all whitespace, underscores and leading zeroes in the sequence.</p>
   *
   * <p>The decimal digits may be any (Unicode code point) that is considered to be a decimal digit by
   * the Java {@link Character#digit(int, int)} and {@link Character#isDigit(int)} methods.</p>
   *
   * <p><b>Note</b>, the returned comparator:</p>
   * <ul>
   *   <li>is serializable.</li>
   *   <li>throws {@link NullPointerException} when comparing a {@code null} value.</li>
   *   <li>considers empty and blank values to be equal to each other but less-than zero.</li>
   * </ul>
   *
   * <p>If you need a null-safe comparator consider creating something like:</p>
   * <pre>{@code
   * import org.typefactory.Comparator;
   * ...
   * static Comparator<CharSequence> NUMERIC_ORDER_COMPARATOR = (o1, o2) ->
   *     Comparator.<CharSequence>nullsFirst(Comparator.numericalOrder()).compare(o1, o2);
   * }</pre>
   *
   * <p>Some of the Unicode character ranges that contain decimal digits are shown below – note, there are also many others:</p>
   * <ul>
   *   <li>{@code '\u005Cu0030'} through {@code '\u005Cu0039'}, ISO-LATIN-1 digits ({@code '0'} through {@code '9'})</li>
   *   <li>{@code '\u005Cu0660'} through {@code '\u005Cu0669'}, Arabic-Indic digits</li>
   *   <li>{@code '\u005Cu06F0'} through {@code '\u005Cu06F9'}, Extended Arabic-Indic digits</li>
   *   <li>{@code '\u005Cu0966'} through {@code '\u005Cu096F'}, Devanagari digits</li>
   *   <li>{@code '\u005CuFF10'} through {@code '\u005CuFF19'}, Fullwidth digits</li>
   * </ul>
   *
   * @param <T> any type that extends {@link CharSequence}, for example the Java {@link String} as well as the Type Factory {@link StringType}
   *            classes.
   * @return a numerical order comparator.
   */
  static <T extends CharSequence> java.util.Comparator<T> numericalOrder() {
    return new NumericCharSequenceComparator<T>();
  }

}
