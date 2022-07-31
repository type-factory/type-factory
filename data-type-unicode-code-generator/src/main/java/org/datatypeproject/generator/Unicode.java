package org.datatypeproject.generator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// INSERT-GENERATED-MESSAGE
public final class Unicode {

  private static final char[] EMPTY_CHAR_ARRAY = new char[0];
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final long[] EMPTY_LONG_ARRAY = new long[0];

  private Unicode() {
    // don't instantiate - use static values and methods
  }

  static final class Other extends RangedSubset {
    // INSERT-OTHER-VALUES-HERE

    private Other(
        final String name,
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
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
  static final class Category extends RangedSubset {
    // INSERT-CATEGORY-VALUES-HERE

    private final String abbreviation;

    private Category(
        final String name,
        final String alias,
        final String abbreviation,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
      this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
      return abbreviation;
    }
  }

  static final class Script extends RangedSubset {
    // INSERT-SCRIPT-VALUES-HERE

    private Script(
        final String name,
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
    }
  }

  static final class Block extends RangedSubset {
    // INSERT-BLOCK-VALUES-HERE

    private static final HashMap<Character.UnicodeBlock, Block> blockMap = new HashMap<>();
    private final int blockStart;
    private final int blockEnd;

    private Block(
        final String name,
        final String alias,
        final int blockStart,
        final int blockEnd,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      super(name, alias, singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
      this.blockStart = blockStart;
      this.blockEnd = blockEnd;
      try {
        blockMap.put(Character.UnicodeBlock.of(blockStart), this);
      } catch (final IllegalArgumentException e) {
        // ignore this – it means we have a more recent Unicode data-set that the JDK
      }
    }

    public static Block of(final Character.UnicodeBlock unicodeBlock) {
      return blockMap.get(unicodeBlock);
    }

    public boolean isInBlock(final char ch) {
      return blockStart <= ch && ch <= blockEnd;
    }

    public boolean isInBlock(final int codePoint) {
      return blockStart <= codePoint && codePoint <= blockEnd;
    }

    public int getBlockStart() {
      return blockStart;
    }

    public int getBlockEnd() {
      return blockEnd;
    }
  }

  public interface Subset {

    boolean isInSubset(final int codePoint);

    default boolean isInSubset(final char ch) {
      return isInSubset((int) ch);
    }

    default boolean isNotInSubset(final int codePoint) {
      return !isInSubset(codePoint);
    }

    default boolean isNotInSubset(final char ch) {
      return !isInSubset((int) ch);
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

  static class RangedSubset implements Subset {

    protected final String name;
    protected final String alias;
    protected final char[] singleByteCodePointRanges;
    protected final int[] doubleByteCodePointRanges;
    protected final long[] tripleByteCodePointRanges;
    protected final int size;

    protected RangedSubset(
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this("", "", singleByteCodePointRanges, doubleByteCodePointRanges, tripleByteCodePointRanges);
    }

    private RangedSubset(
        final String name,
        final String alias,
        final char[] singleByteCodePointRanges,
        final int[] doubleByteCodePointRanges,
        final long[] tripleByteCodePointRanges) {
      this.name = name;
      this.alias = alias;
      this.singleByteCodePointRanges = singleByteCodePointRanges == null ? EMPTY_CHAR_ARRAY : singleByteCodePointRanges;
      this.doubleByteCodePointRanges = doubleByteCodePointRanges == null ? EMPTY_INT_ARRAY : doubleByteCodePointRanges;
      this.tripleByteCodePointRanges = tripleByteCodePointRanges == null ? EMPTY_LONG_ARRAY : tripleByteCodePointRanges;
      this.size = this.singleByteCodePointRanges.length
          + this.doubleByteCodePointRanges.length
          + this.tripleByteCodePointRanges.length;
    }

    public static RangedSubsetBuilder builder() {
      return new RangedSubsetBuilder();
    }

    int size() {
      return size;
    }

    boolean isEmpty() {
      return size() == 0;
    }

    boolean isNotEmpty() {
      return !isEmpty();
    }

    /**
     * Returns an array of 2-byte code-point ranges stored as char values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 8 bits hold the inclusive-from value. The least-significant 8 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    char[] getSingleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length);
    }

    /**
     * Returns an array of 2-byte code-point ranges stored as integer values. Each range comprises an inclusive-from value and an inclusive-to value.
     * The most-significant 16 bits hold the inclusive-from value. The least-significant 16 bits hold the inclusive-to value. The returned array must
     * be sorted from low-to-high as unsigned-integers.
     */
    int[] getDoubleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length);
    }

    /**
     * Returns an array of 3-byte code-point ranges stored as long values. Each range comprises an inclusive-from value and an inclusive-to value. The
     * most-significant 32 bits hold the inclusive-from value. The least-significant 32 bits hold the inclusive-to value. The returned array must be
     * sorted from low-to-high.
     */
    long[] getTripleByteCodePointRanges() {
      // Protect out data by returning a copy
      return Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length);
    }

    public String getName() {
      return name;
    }

    public String getAlias() {
      return alias;
    }

    public boolean isInSubset(final int codePoint) {
      if (codePoint < 0x100) {
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
          } else {
            return true; // found
          }
        }
      } else if (codePoint < 0x10000) {
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
          } else {
            return true; // found
          }
        }
      } else {
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
          } else {
            return true; // found
          }
        }
      }
      return false; // not found
    }

    @Override
    public String toString() {
      final StringBuilder s = new StringBuilder();
      s.append(name).append('[');
      for (char singleByteCodePointRange : singleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(singleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(singleByteCodePointRange), 16)).append(',');
      }
      for (int doubleByteCodePointRange : doubleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(doubleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(doubleByteCodePointRange), 16)).append(',');
      }
      for (long tripleByteCodePointRange : tripleByteCodePointRanges) {
        s.append(Integer.toString(getInclusiveFrom(tripleByteCodePointRange), 16)).append('.').append('.')
            .append(Integer.toString(getInclusiveTo(tripleByteCodePointRange), 16)).append(',');
      }
      s.setLength(s.length() - 1); // remove final comma
      s.append(']');
      return s.toString();
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

    static class RangedSubsetBuilder {

      private char[] singleByteCodePointRanges = new char[16];
      private int[] doubleByteCodePointRanges = new int[16];
      private long[] tripleByteCodePointRanges = new long[16];
      private int singleByteEndIndex = 0;
      private int doubleByteEndIndex = 0;
      private int tripleByteEndIndex = 0;

      void addChar(final char ch) {
        addCharRange(ch, ch);
      }

      void addChars(final char... chars) {
        if (chars != null) {
          ensureDoubleByteCapacity(chars.length);
          for (int i = 0; i < chars.length; ++i) {
            addChar(chars[i]);
          }
        }
      }

      void addCharRange(final char inclusiveFrom, final char inclusiveTo) {
        addCodePointRange(inclusiveFrom & 0x0000_ffff, inclusiveTo & 0x0000_ffff);
      }

      void addCodePoint(final int codePoint) {
        addCodePointRange(codePoint, codePoint);
      }

      void addCodePoints(final int... codePoints) {
        if (codePoints != null) {
          ensureDoubleByteCapacity(codePoints.length);
          for (int i = 0; i < codePoints.length; ++i) {
            addCodePoint(codePoints[i]);
          }
        }
      }

      void addRangedSubset(final RangedSubset subset) {
        if (subset != null && subset.isNotEmpty()) {
          final char[] singleByteRanges = subset.getSingleByteCodePointRanges();
          ensureSingleByteCapacity(singleByteRanges.length);
          for (char c : singleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(c),
                RangedSubset.getInclusiveTo(c));
          }
          final int[] doubleByteRanges = subset.getDoubleByteCodePointRanges();
          ensureDoubleByteCapacity(doubleByteRanges.length);
          for (int j : doubleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(j),
                RangedSubset.getInclusiveTo(j));
          }
          final long[] tripleByteRanges = subset.getTripleByteCodePointRanges();
          ensureTripleByteCapacity(tripleByteRanges.length);
          for (long l : tripleByteRanges) {
            addCodePointRange(
                RangedSubset.getInclusiveFrom(l),
                RangedSubset.getInclusiveTo(l));
          }
        }
      }

      void addCodePointRange(int inclusiveFrom, int inclusiveTo) {
        if (inclusiveTo > 0xffff) {
          if (inclusiveFrom > 0xffff) {
            ensureTripleByteCapacity();
            tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(inclusiveFrom, inclusiveTo);
            ++tripleByteEndIndex;
            return;
          } else {
            ensureTripleByteCapacity();
            tripleByteCodePointRanges[tripleByteEndIndex] = RangedSubset.rangeToLong(0x10000, inclusiveTo);
            ++tripleByteEndIndex;
            inclusiveTo = 0xffff;
          }
        }

        if (inclusiveTo > 0xff) {
          if (inclusiveFrom > 0xff) {
            ensureDoubleByteCapacity();
            doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(inclusiveFrom, inclusiveTo);
            ++doubleByteEndIndex;
            return;
          } else {
            ensureDoubleByteCapacity();
            doubleByteCodePointRanges[doubleByteEndIndex] = RangedSubset.rangeToInt(0x100, inclusiveTo);
            ++doubleByteEndIndex;
            inclusiveTo = 0xff;
          }
        }

        ensureSingleByteCapacity();
        singleByteCodePointRanges[singleByteEndIndex] = RangedSubset.rangeToChar(inclusiveFrom, inclusiveTo);
        ++singleByteEndIndex;
      }

      private void removeSingleByteElement(final int index) {
        System.arraycopy(singleByteCodePointRanges, index + 1, singleByteCodePointRanges, index, singleByteCodePointRanges.length - index - 1);
        --singleByteEndIndex;
      }

      private void removeDoubleByteElement(final int index) {
        System.arraycopy(doubleByteCodePointRanges, index + 1, doubleByteCodePointRanges, index, doubleByteCodePointRanges.length - index - 1);
        --doubleByteEndIndex;
      }

      private void removeTripleByteElement(final int index) {
        System.arraycopy(tripleByteCodePointRanges, index + 1, tripleByteCodePointRanges, index, tripleByteCodePointRanges.length - index - 1);
        --tripleByteEndIndex;
      }

      private void ensureSingleByteCapacity() {
        if (singleByteEndIndex == singleByteCodePointRanges.length) {
          ensureSingleByteCapacity(16);
        }
      }

      private void ensureSingleByteCapacity(final int amount) {
        if ((singleByteCodePointRanges.length - singleByteEndIndex) < amount) {
          singleByteCodePointRanges = Arrays.copyOf(singleByteCodePointRanges, singleByteCodePointRanges.length + amount);
        }
      }

      private void ensureDoubleByteCapacity() {
        if (doubleByteEndIndex == doubleByteCodePointRanges.length) {
          ensureDoubleByteCapacity(16);
        }
      }

      private void ensureDoubleByteCapacity(final int amount) {
        if ((doubleByteCodePointRanges.length - doubleByteEndIndex) < amount) {
          doubleByteCodePointRanges = Arrays.copyOf(doubleByteCodePointRanges, doubleByteCodePointRanges.length + amount);
        }
      }

      private void ensureTripleByteCapacity() {
        if (tripleByteEndIndex == tripleByteCodePointRanges.length) {
          ensureTripleByteCapacity(16);
        }
      }

      private void ensureTripleByteCapacity(final int amount) {
        if ((tripleByteCodePointRanges.length - tripleByteEndIndex) < amount) {
          tripleByteCodePointRanges = Arrays.copyOf(tripleByteCodePointRanges, tripleByteCodePointRanges.length + amount);
        }
      }

      private static void unsignedIntegerBubbleSort(final int[] values, int fromIndex, int toIndex) {
        int temp;
        for (int i = fromIndex; i < toIndex; i++) {
          for (int j = fromIndex; j < toIndex - i - 1; j++) {
            if ((0x00000000_ffffffffL & (long) values[j]) > (0x00000000_ffffffffL & (long) values[j + 1])) {
              // swap the elements
              temp = values[j];
              values[j] = values[j + 1];
              values[j + 1] = temp;
            }
          }
        }
      }

      RangedSubset build() {
        {
          Arrays.sort(singleByteCodePointRanges, 0, singleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < singleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
              }
              removeSingleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              singleByteCodePointRanges[i] = RangedSubset.rangeToChar(previousInclusiveFrom, currentInclusiveTo);
              removeSingleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        {
          unsignedIntegerBubbleSort(doubleByteCodePointRanges, 0, doubleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < doubleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              }
              removeDoubleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              doubleByteCodePointRanges[i] = RangedSubset.rangeToInt(previousInclusiveFrom, currentInclusiveTo);
              removeDoubleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        {
          Arrays.sort(tripleByteCodePointRanges, 0, tripleByteEndIndex);
          int previousInclusiveFrom;
          int previousInclusiveTo;
          int currentInclusiveFrom;
          int currentInclusiveTo;
          int i = 0;
          int j = 1;
          while (j < tripleByteEndIndex) {
            previousInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[i]);
            previousInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[i]);
            currentInclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[j]);
            currentInclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[j]);
            if (previousInclusiveTo >= currentInclusiveFrom) {
              if (previousInclusiveTo <= currentInclusiveTo) {
                tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              }
              removeTripleByteElement(j);
            } else if ((previousInclusiveTo + 1) == currentInclusiveFrom) {
              tripleByteCodePointRanges[i] = RangedSubset.rangeToLong(previousInclusiveFrom, currentInclusiveTo);
              removeTripleByteElement(j);
            } else {
              ++i;
              ++j;
            }
          }
        }
        return new RangedSubset(
            Arrays.copyOf(singleByteCodePointRanges, singleByteEndIndex),
            Arrays.copyOf(doubleByteCodePointRanges, doubleByteEndIndex),
            Arrays.copyOf(tripleByteCodePointRanges, tripleByteEndIndex));
      }
    }
  }
}
