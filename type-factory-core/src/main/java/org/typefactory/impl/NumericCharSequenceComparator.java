package org.typefactory.impl;

import static java.lang.Character.MAX_RADIX;
import static java.lang.Character.isWhitespace;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.typefactory.InvalidValueException;
import org.typefactory.InvalidValueException.ParserMessageCode;
import org.typefactory.impl.ParserMessageCodeImpl.ParserMessageCodeArgKeys;

public class NumericCharSequenceComparator<T extends CharSequence> implements Comparator<T>, Serializable {

  @Serial
  private static final long serialVersionUID = 4221345598327592216L;

  /**
   * <p>Compares two {@link CharSequence}s containing numerical values.
   * All characters in the char-sequence should be the decimal digits and/or the letters a-zA-Z allowing for numbers to use any base from base-2
   * through to base-36. It will ignore all whitespace, underscores and leading zeroes in the sequence.</p>
   *
   * <p>The decimal digits may be any (Unicode code point) that is considered to be a decimal digit by
   * the Java {@link Character#digit(int, int)} and {@link Character#isDigit(int)} methods.</p>
   *
   * <p><b>Note</b>, the comparison:</p>
   * <ul>
   *   <li>throws {@link NullPointerException} if either {@code value1} or {@code value2} is {@code null}.</li>
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
   * @param value1 the first object to be compared.
   * @param value2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   * @throws NullPointerException if either {@code value1} or {@code value2} is {@code null}.
   * @see org.typefactory.Comparator#numericalOrder()
   */
  @Override
  public int compare(final T value1, final T value2) {
    Objects.requireNonNull(value1);
    Objects.requireNonNull(value2);

    // Determine the end-index by ignoring trailing whitespace.
    final int endIndex1 = endIndex(value1);
    final int endIndex2 = endIndex(value2);

    // Determine the start-index by ignoring leading whitespace and zero digits. Also capture whether there were leading zeroes.
    final long leadingZerosAndStartIndex1 = startIndex(value1, endIndex1);
    final long leadingZerosAndStartIndex2 = startIndex(value2, endIndex2);

    final int startIndex1 = (int) leadingZerosAndStartIndex1;
    final int startIndex2 = (int) leadingZerosAndStartIndex2;

    final boolean hasLeadingZero1 = (leadingZerosAndStartIndex1 >> 32) > 0;
    final boolean hasLeadingZero2 = (leadingZerosAndStartIndex2 >> 32) > 0;

    // Return immediately if both sequences are empty or made up of only whitespace or zeroes?
    if (startIndex1 == endIndex1 && startIndex2 == endIndex2) {
      if (hasLeadingZero1) {
        if (hasLeadingZero2) {
          return 0; // s1 and s2 are equal because they both have a zero value.
        }
        return 1; // we treat the s1 value of zero to be greater than the null-or-blank value s2.
      } else {
        if (hasLeadingZero2) {
          return -1; // we treat the null-or-blank value os s1 to be less than s2 value of zero.
        }
        return 0; // we treat both s1 and s2 as equal because they both have a value of null-or-blank.
      }
    }

    int result = 0;
    int i1 = startIndex1;
    int i2 = startIndex2;
    while (i1 < endIndex1 && i2 < endIndex2) {

      int digit1;
      int digit2;

      while (true) {
        final char c1 = value1.charAt(i1);
        if (Character.isSurrogate(c1)) {
          if (++i1 < endIndex1) {
            final char c1low = value1.charAt(i1);
            if (Character.isSurrogatePair(c1, c1low)) {
              final int codePoint1 = Character.toCodePoint(c1, c1low);
              digit1 = Character.digit(codePoint1, MAX_RADIX);
              if (digit1 < 0) {
                if (isIgnoredCharacter(codePoint1)) {
                  continue;
                }
                throw exceptionForValueMustBeNumericallyComparable(value1, codePoint1);
              }
            } else {
              throw exceptionForValueMustBeNumericallyComparable(value1, c1);
            }
          } else {
            throw exceptionForValueMustBeNumericallyComparable(value1, c1);
          }
        } else {
          digit1 = Character.digit(c1, MAX_RADIX);
          if (digit1 < 0) {
            if (isIgnoredCharacter(c1)) {
              continue;
            }
            throw exceptionForValueMustBeNumericallyComparable(value1, c1);
          }
        }
        break;
      }

      while (true) {
        final char c2 = value2.charAt(i2);
        if (Character.isSurrogate(c2)) {
          if (++i2 < endIndex2) {
            final char c2low = value2.charAt(i2);
            if (Character.isSurrogatePair(c2, c2low)) {
              final int codePoint2 = Character.toCodePoint(c2, c2low);
              digit2 = Character.digit(codePoint2, MAX_RADIX);
              if (digit2 < 0) {
                if (isIgnoredCharacter(codePoint2)) {
                  continue;
                }
                throw exceptionForValueMustBeNumericallyComparable(value2, codePoint2);
              }
            } else {
              throw exceptionForValueMustBeNumericallyComparable(value2, c2);
            }
          } else {
            throw exceptionForValueMustBeNumericallyComparable(value2, c2);
          }
        } else {
          digit2 = Character.digit(c2, MAX_RADIX);
          if (digit2 < 0) {
            if (isIgnoredCharacter(c2)) {
              continue;
            }
            throw exceptionForValueMustBeNumericallyComparable(value2, c2);
          }
        }
        break;
      }

      // If we haven't yet found a difference check this character pair.
      if (result == 0) {
        result = digit1 - digit2;
      }

      // Increment our indexes
      ++i1;
      ++i2;
    }

    // Examine the remaining characters of first char-sequence
    if (i1 < endIndex1) {
      for (; i1 < endIndex1; ++i1) {
        final char c1 = value1.charAt(i1);
        if (Character.isSurrogate(c1)) {
          if (++i1 < endIndex1) {
            final char c1low = value1.charAt(i1);
            if (Character.isSurrogatePair(c1, c1low)) {
              final int codePoint1 = Character.toCodePoint(c1, c1low);
              final int digit1 = Character.digit(codePoint1, MAX_RADIX);
              if (digit1 >= 0) {
                return 1;
              }
              if (isIgnoredCharacter(codePoint1)) {
                continue;
              }
              throw exceptionForValueMustBeNumericallyComparable(value1, codePoint1);
            } else {
              throw exceptionForValueMustBeNumericallyComparable(value1, c1);
            }
          } else {
            throw exceptionForValueMustBeNumericallyComparable(value1, c1);
          }
        } else {
          final int digit1 = Character.digit(c1, MAX_RADIX);
          if (digit1 >= 0) {
            return 1;
          }
          if (isIgnoredCharacter(c1)) {
            continue;
          }
          throw exceptionForValueMustBeNumericallyComparable(value1, c1);
        }
      }
    }

    // Examine the remaining characters of second char-sequence
    if (i2 < endIndex2) {
      for (; i2 < endIndex2; ++i2) {
        final char c2 = value2.charAt(i2);
        if (Character.isSurrogate(c2)) {
          if (++i2 < endIndex2) {
            final char c2low = value2.charAt(i2);
            if (Character.isSurrogatePair(c2, c2low)) {
              final int codePoint2 = Character.toCodePoint(c2, c2low);
              final int digit2 = Character.digit(codePoint2, MAX_RADIX);
              if (digit2 >= 0) {
                return -1;
              }
              if (isIgnoredCharacter(codePoint2)) {
                continue;
              }
              throw exceptionForValueMustBeNumericallyComparable(value2, codePoint2);
            } else {
              throw exceptionForValueMustBeNumericallyComparable(value2, c2);
            }
          } else {
            throw exceptionForValueMustBeNumericallyComparable(value2, c2);
          }
        } else {
          final int digit2 = Character.digit(c2, MAX_RADIX);
          if (digit2 >= 0) {
            return -1;
          }
          if (isIgnoredCharacter(c2)) {
            continue;
          }
          throw exceptionForValueMustBeNumericallyComparable(value2, c2);
        }
      }
    }

    return result;
  }

  private static boolean isIgnoredCharacter(final char ch) {
    return isIgnoredCharacter((int) ch);
  }

  private static boolean isIgnoredCharacter(final int codePoint) {
    return isWhitespace(codePoint) || codePoint == '_' || codePoint == '-';
  }

  private static int endIndex(final CharSequence value) {
    int endIndex = value.length() - 1;
    // Find end index of char-sequence ignoring trailing whitespace.
    while (endIndex >= 0 && isIgnoredCharacter(value.charAt(endIndex))) {
      --endIndex;
    }
    return ++endIndex;
  }

  private static long startIndex(final CharSequence value, final int endIndex) {
    if (endIndex == 0) {
      return 0L;
    }
    boolean hasLeadingZero = false;
    int startIndex = 0;
    for (; startIndex < endIndex; ++startIndex) {
      final char ch = value.charAt(startIndex);
      if (Character.isSurrogate(ch)) {
        if (++startIndex < endIndex) {
          final char chLow = value.charAt(startIndex);
          if (Character.isSurrogatePair(ch, chLow)) {
            final int codePoint = Character.toCodePoint(ch, chLow);
            final int digit = Character.digit(codePoint, MAX_RADIX);
            if (digit > 0) {
              --startIndex;
              break;
            }
            if (digit == 0) {
              hasLeadingZero = true;
              continue;
            }
            if (isIgnoredCharacter(codePoint)) {
              continue;
            }
            throw exceptionForValueMustBeNumericallyComparable(value, codePoint);
          } else {
            throw exceptionForValueMustBeNumericallyComparable(value, ch);
          }
        } else {
          throw exceptionForValueMustBeNumericallyComparable(value, ch);
        }
      } else {
        final int digit = Character.digit(ch, MAX_RADIX);
        if (digit > 0) {
          break;
        }
        if (digit == 0) {
          hasLeadingZero = true;
          continue;
        }
        if (isIgnoredCharacter(ch)) {
          continue;
        }
        throw exceptionForValueMustBeNumericallyComparable(value, ch);
      }
    }
    return hasLeadingZero ? 0x00000001_00000000L | startIndex : startIndex;
  }

  private static InvalidValueException exceptionForValueMustBeNumericallyComparable(
      final CharSequence value,
      final int invalidCodePoint) {

    return InvalidValueException.builder()
        .invalidValue(value)
        .parserMessageCode(ParserMessageCode.INVALID_VALUE_MUST_BE_NUMERICALLY_COMPARABLE)
        .addParserMessageCodeArg(
            ParserMessageCodeArgKeys.INVALID_CODE_POINT,
            invalidCodePoint)
        .build();
  }

}
