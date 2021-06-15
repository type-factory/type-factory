package land.artli.easytype.generator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "land.artli:easy-type-unicode-code-generator",
    comments = "Generated from Unicode file 'ucd.all.grouped.xml'")
public final class Unicode {

  private Unicode() {
    // don't instantiate
  }

  public interface Subset {

    boolean isInSubset(final int codePoint);

    default boolean isNotInSubset(final int codePoint) {
      return !isInSubset(codePoint);
    }

    static CompositeSubset of(final Subset... subsets) {
      if (subsets == null || subsets.length == 0) {
        return Collections::emptyList;
      }
      return () -> List.of(subsets);
    }
  }

  interface CompositeSubset extends Subset {

    Collection<Subset> getSubsets();

    default boolean isInSubset(final int codePoint) {
      for (Subset subset : getSubsets()) {
        if (subset.isInSubset(codePoint)) {
          return true;
        }
      }
      return false;
    }
  }

  interface RangedSubset extends Subset {

    /**
     * Returns an array of 2-byte code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    char[] getSingleByteCodePointRanges();

    /**
     * Returns an array of 2-byte code-point ranges stored as integer values. Each range comprises an inclusive-from value and an inclusive-to value.
     * The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The returned array must
     * be sorted from low-to-high as unsigned-integers.
     */
    int[] getDoubleByteCodePointRanges();

    /**
     * Returns an array of 3-byte code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    long[] getTripleByteCodePointRanges();

    default boolean isInSubset(final int codePoint) {
      if (codePoint < 0x100) {
        final char[] singleByteCodePointRanges = getSingleByteCodePointRanges();

        if (singleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(singleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(singleByteCodePointRanges[singleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = singleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final int inclusiveFrom = getInclusiveFrom(singleByteCodePointRanges[mid]);
          final int inclusiveTo = getInclusiveTo(singleByteCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return true; // found
          } else {
            return false; // not found
          }
        }
      } else if (codePoint < 0x10000) {
        final int[] doubleByteCodePointRanges = getDoubleByteCodePointRanges();

        if (doubleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(doubleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(doubleByteCodePointRanges[doubleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = doubleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final int inclusiveFrom = getInclusiveFrom(doubleByteCodePointRanges[mid]);
          final int inclusiveTo = getInclusiveTo(doubleByteCodePointRanges[mid]);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return true; // found
          } else {
            return false; // not found
          }
        }
      } else {
        final long[] tripleByteCodePointRanges = getTripleByteCodePointRanges();
        if (tripleByteCodePointRanges.length == 0 ||
            codePoint < getInclusiveFrom(tripleByteCodePointRanges[0]) ||
            codePoint > getInclusiveTo(tripleByteCodePointRanges[tripleByteCodePointRanges.length - 1])) {
          return false; // not found, outside of range
        }
        // Try to find using a binary search
        int low = 0;
        int high = tripleByteCodePointRanges.length - 1;
        while (low <= high) {
          final int mid = (low + high) >>> 1;
          final long midVal = tripleByteCodePointRanges[mid];
          final int inclusiveFrom = getInclusiveFrom(midVal);
          final int inclusiveTo = getInclusiveTo(midVal);
          if (codePoint > inclusiveTo) {
            low = mid + 1;
          } else if (codePoint < inclusiveFrom) {
            high = mid - 1;
          } else if (codePoint >= inclusiveFrom && codePoint <= inclusiveTo) {
            return true; // found
          } else {
            return false; // not found
          }
        }
      }
      return false; // not found
    }

    static int getInclusiveFrom(final char codePointRange) {
      return codePointRange >>> 8;
    }

    static int getInclusiveTo(final char codePointRange) {
      return codePointRange & 0x00_ff;
    }

    static int getInclusiveFrom(final int codePointRange) {
      return codePointRange >>> 16;
    }

    static int getInclusiveTo(final int codePointRange) {
      return codePointRange & 0x0000_ffff;
    }

    static int getInclusiveFrom(final long codePointRange) {
      return (int) (codePointRange >>> 32);
    }

    static int getInclusiveTo(final long codePointRange) {
      return (int) (codePointRange & 0x00000000_ffffffffL);
    }

    static char rangeToChar(final int inclusiveFrom, final int inclusiveTo) {
      return (char) ((inclusiveFrom << 8) | (inclusiveTo & 0x00_ff));
    }

    static int rangeToInt(final int inclusiveFrom, final int inclusiveTo) {
      return (inclusiveFrom << 16) | (inclusiveTo & 0x0000_ffff);
    }

    static long rangeToLong(final int inclusiveFrom, final int inclusiveTo) {
      return (((long) inclusiveFrom) << 32) | inclusiveTo;
    }
  }


  public enum Other implements RangedSubset {
    // INSERT-OTHER-VALUES-HERE
    ;

    private final String alias;
    private final char[] singleByteCodePointRanges;
    private final int[] doubleByteCodePointRanges;
    private final long[] tripleByteCodePointRanges;

    private Other(
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges;
    }

    public char[] getSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    public int[] getDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    public long[] getTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }
  }

  /**
   * <p>Unicode General Category Values. Categories classify a code point by their primary characteristic.
   * The following is quoted from the
   * <em>"<a href="https://www.unicode.org/reports/tr44/#General_Category_Values">Unicode General Category Values</a>"</em>
   * section of the <a href="https://www.unicode.org/reports/tr44">Unicode Character Database</a> document.</p>
   * <blockquote>
   * <p>For example, is the character a letter, a mark, a number, punctuation, or a symbol, and if so, of what type?</p>
   * <p>...</p>
   * <p>Many characters have multiple uses, and not all such cases can be captured entirely by the
   * General Category value. For example, the General Category value of Latin, Greek, or Hebrew letters does not attempt to cover (or preclude) the
   * numerical use of such letters as Roman numerals or in other numerary systems.<p>
   * <p>...</p>
   * <p class="caption">Table 12. <a name="GC_Values_Table" href="#GC_Values_Table">General_Category Values</a></p>
   * <table class="simple">
   *   <tr><th>Abbr</th><th>Long</th><th>Description</th></tr>
   *   <tr><td>Lu</td><td>Uppercase_Letter</td><td>an uppercase letter</td></tr>
   *   <tr><td>Ll</td><td>Lowercase_Letter</td><td>a lowercase letter</td></tr>
   *   <tr><td>Lt</td><td>Titlecase_Letter</td><td>a digraphic character, with first part uppercase</td></tr>
   *   <tr style='background-color:lightblue;'><td>LC</td><td>Cased_Letter</td><td>Lu | Ll | Lt</td></tr>
   *   <tr><td>Lm</td><td>Modifier_Letter</td><td>a modifier letter</td></tr>
   *   <tr><td>Lo</td><td>Other_Letter</td><td>other letters, including syllables and ideographs</td></tr>
   *   <tr style='background-color:lightblue;'><td>L</td><td>Letter</td><td>Lu | Ll | Lt | Lm | Lo</td></tr>
   *   <tr><td>Mn</td><td>Nonspacing_Mark</td><td>a nonspacing combining mark (zero advance width)</td></tr>
   *   <tr><td>Mc</td><td>Spacing_Mark</td><td>a spacing combining mark (positive advance width)</td></tr>
   *   <tr><td>Me</td><td>Enclosing_Mark</td><td>an enclosing combining mark</td></tr>
   *   <tr style='background-color:lightblue;'><td>M</td><td>Mark</td><td>Mn | Mc | Me</td></tr>
   *   <tr><td>Nd</td><td>Decimal_Number</td><td>a decimal digit</td></tr>
   *   <tr><td>Nl</td><td>Letter_Number</td><td>a letterlike numeric character</td></tr>
   *   <tr><td>No</td><td>Other_Number</td><td>a numeric character of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>N</td><td>Number</td><td>Nd | Nl | No</td></tr>
   *   <tr><td>Pc</td><td>Connector_Punctuation</td><td>a connecting punctuation mark, like a tie</td></tr>
   *   <tr><td>Pd</td><td>Dash_Punctuation</td><td>a dash or hyphen punctuation mark</td></tr>
   *   <tr><td>Ps</td><td>Open_Punctuation</td><td>an opening punctuation mark (of a pair)</td></tr>
   *   <tr><td>Pe</td><td>Close_Punctuation</td><td>a closing punctuation mark (of a pair)</td></tr>
   *   <tr><td>Pi</td><td>Initial_Punctuation</td><td>an initial quotation mark</td></tr>
   *   <tr><td>Pf</td><td>Final_Punctuation</td><td>a final quotation mark</td></tr>
   *   <tr><td>Po</td><td>Other_Punctuation</td><td>a punctuation mark of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>P</td><td>Punctuation</td><td>Pc | Pd | Ps | Pe | Pi | Pf | Po</td></tr>
   *   <tr><td>Sm</td><td>Math_Symbol</td><td>a symbol of mathematical use</td></tr>
   *   <tr><td>Sc</td><td>Currency_Symbol</td><td>a currency sign</td></tr>
   *   <tr><td>Sk</td><td>Modifier_Symbol</td><td>a non-letterlike modifier symbol</td></tr>
   *   <tr><td>So</td><td>Other_Symbol</td><td>a symbol of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>S</td><td>Symbol</td><td>Sm | Sc | Sk | So</td></tr>
   *   <tr><td>Zs</td><td>Space_Separator</td><td>a space character (of various non-zero widths)</td></tr>
   *   <tr><td>Zl</td><td>Line_Separator</td><td>U+2028 LINE SEPARATOR only</td></tr>
   *   <tr><td>Zp</td><td>Paragraph_Separator</td><td>U+2029 PARAGRAPH SEPARATOR only</td></tr>
   *   <tr style='background-color:lightblue;'><td>Z</td><td>Separator</td><td>Zs | Zl | Zp</td></tr>
   *   <tr><td>Cc</td><td>Control</td><td>a C0 or C1 control code</td></tr>
   *   <tr><td>Cf</td><td>Format</td><td>a format control character</td></tr>
   *   <tr><td>Cs</td><td>Surrogate</td><td>a surrogate code point</td></tr>
   *   <tr><td>Co</td><td>Private_Use</td><td>a private-use character</td></tr>
   *   <tr><td>Cn</td><td>Unassigned</td><td>a reserved unassigned code point or a noncharacter</td></tr>
   *   <tr style='background-color:lightblue;'><td>C</td><td>Other</td><td>Cc | Cf | Cs | Co | Cn</td></tr>
   * </table>
   * </blockquote>
   *
   * @see <a href="https://www.unicode.org/reports/tr44/#General_Category_Values">
   * https://www.unicode.org/reports/tr44/#General_Category_Values</a>
   */
  public enum Category implements RangedSubset {
    // INSERT-CATEGORY-VALUES-HERE
    ;

    private final String abbreviation;
    private final String alias;
    private final char[] singleByteCodePointRanges;
    private final int[] doubleByteCodePointRanges;
    private final long[] tripleByteCodePointRanges;

    private Category(
        final String abbreviation,
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.abbreviation = abbreviation;
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges;
    }

    public String getAbbreviation() {
      return abbreviation;
    }

    public String getAlias() {
      return alias;
    }

    public char[] getSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    public int[] getDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    public long[] getTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }
  }

  public enum Script implements RangedSubset {
    // INSERT-SCRIPT-VALUES-HERE
    ;

    private final String alias;
    private final char[] singleByteCodePointRanges;
    private final int[] doubleByteCodePointRanges;
    private final long[] tripleByteCodePointRanges;

    private Script(
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges;
    }

    public char[] getSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    public int[] getDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    public long[] getTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }
  }

  public enum Block implements RangedSubset {
    // INSERT-SCRIPT-VALUES-HERE
    ;

    private final String alias;
    private final char[] singleByteCodePointRanges;
    private final int[] doubleByteCodePointRanges;
    private final long[] tripleByteCodePointRanges;

    private Block(
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges;
    }

    public char[] getSingleByteCodePointRanges() {
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    public int[] getDoubleByteCodePointRanges() {
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    public long[] getTripleByteCodePointRanges() {
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }
  }
}
