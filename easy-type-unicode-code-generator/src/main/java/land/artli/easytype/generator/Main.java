package land.artli.easytype.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import land.artli.easytype.generator.CodePointRanges.CodePointRangesBuilder;
import org.unicode.ns._2003.ucd._1.Group;

public class Main {

  public static void main(final String[] args) {

    final String inputXml = args[0];
    final String outputDirectory = args[1];

    final UnicodeGroupDataLoader loader = new UnicodeGroupDataLoader(inputXml);

    final List<Group> groups = loader.getGroups();
    int i = 0;
    final TreeMap<String, CodePointRangesBuilder> blockCodePointRanges = new TreeMap<>();
    final TreeMap<String, CodePointRangesBuilder> scriptCodePointRanges = new TreeMap<>();
    final TreeMap<UnicodeCategory, CodePointRangesBuilder> categoryCodePointRanges = new TreeMap<>();
    for (UnicodeCodePoint c : loader.getUnicodeCodePoints()) {
      ++i;
      if (c.getCodePoint() == null) {
        continue;
      }
      CodePointRangesBuilder codePointRangesBuilder;
      if (c.getBlock() != null) {
        codePointRangesBuilder = blockCodePointRanges.get(c.getBlock());
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          blockCodePointRanges.put(c.getBlock(), codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getCodePointRange());
        }
      }

      if (c.getScript() != null) {
        codePointRangesBuilder = scriptCodePointRanges.get(c.getScript());
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          scriptCodePointRanges.put(c.getScript(), codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getCodePointRange());
        }
      }

      final UnicodeCategory unicodeCategory = UnicodeCategory.of(c.getGeneralCategory());
      if (unicodeCategory != null) {
        codePointRangesBuilder = categoryCodePointRanges.get(unicodeCategory);
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          categoryCodePointRanges.put(unicodeCategory, codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getCodePointRange());
        }
      }
    }

    final String lineSeparator = System.lineSeparator();
    final StringBuilder s = new StringBuilder();
    s.append("""
        package land.artli.easytype;
                
        import java.util.Arrays;
        import javax.annotation.processing.Generated;
                
        @Generated(
          value="land.artli:easy-type-unicode-code-generator",
          comments="Generated from Unicode file 'ucd.all.grouped.xml'")
        public final class Unicode {

          private Unicode() {
            // don't instantiate
          }
        """).append(lineSeparator);
    s.append("""
          /**
           * <p>Unicode General Category Values. Categories classify a code point by their primary characteristic.
           * The following is quoted from the\s
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
           *   https://www.unicode.org/reports/tr44/#General_Category_Values</a>
           */
          public enum Category {
        """);
    for (UnicodeCategory category : UnicodeCategory.values()) {
      s.append(lineSeparator).append("      /** ").append(category.getAbbreviation()).append(" – ")
          .append(category.getDescription()).append(" */")
          .append(lineSeparator).append("      ").append(category.name())
          .append("(\"").append(category.getAbbreviation())
          .append("\", \"").append(category.getFullName().replace('_', ' '))
          .append("\", new long [] { ");
      final UnicodeCategory[] compositeCategories = category.isComposite() ? category.getCompositeCategories() : new UnicodeCategory[]{category};
      final CodePointRangesBuilder codePointRangesBuilder = CodePointRanges.builder();
      for (UnicodeCategory compositeCategory : compositeCategories) {
        if (categoryCodePointRanges.containsKey(compositeCategory)) {
          codePointRangesBuilder.addCodePointRanges(categoryCodePointRanges.get(compositeCategory).build().getCodePointRanges());
        }
      }
      final CodePointRange [] codePointRanges = codePointRangesBuilder.build().getCodePointRanges();
      for (int j =0; j < codePointRanges.length; ++j) {
        if ((j % 4) == 0) {
          s.append(lineSeparator).append("         ");
        }
        s.append(String.format(" 0x%08x_%08xL,", codePointRanges[j].inclusiveFrom, codePointRanges[j].inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("}),");
    }
    s.setLength(s.length() - 1);
    s.append(";").append(lineSeparator).append(lineSeparator);
    s.append("""
              private final String abbreviation;
              private final String alias;
              private final long [] codePointRanges;
              private Category(final String abbreviation, final String alias, final long [] codePointRanges) {
                  this.abbreviation = abbreviation;
                  this.alias = alias;
                  this.codePointRanges = codePointRanges;
              }
              public String getAbbreviation() {
                return abbreviation;
              }
              public String getAlias() {
                return alias;
              }
              public final long [] getCodePointRanges() {
                  return Arrays.copyOf(codePointRanges, codePointRanges.length);
              }
          }
        """).append(lineSeparator).append(lineSeparator);

    s.append("  public enum Script {");
    for (String script : scriptCodePointRanges.keySet()) {
      s.append(lineSeparator).append("      ").append(script.toUpperCase()).append("(\"").append(script).append("\", new long [] {");
      final CodePointRange [] codePointRanges = scriptCodePointRanges.get(script).build().getCodePointRanges();
      for (int j =0; j < codePointRanges.length; ++j) {
        if ((j % 4) == 0) {
          s.append(lineSeparator).append("         ");
        }
        s.append(String.format(" 0x%08x_%08xL,", codePointRanges[j].inclusiveFrom, codePointRanges[j].inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("}),");
    }
    s.setLength(s.length() - 1);
    s.append(";").append(lineSeparator).append(lineSeparator);
    s.append("""
              private final String alias;
              private final long [] codePointRanges;
              private Script(final String alias, final long [] codePointRanges) {
                  this.alias = alias;
                  this.codePointRanges = codePointRanges;
              }
              public final long [] getCodePointRanges() {
                  return Arrays.copyOf(codePointRanges, codePointRanges.length);
              }
          }
        """);

    s.append("  public enum Block {");
    for (String block : blockCodePointRanges.keySet()) {
      s.append(lineSeparator).append("      ").append(block.toUpperCase()).append("(\"").append(block).append("\", new long [] {");
      final CodePointRange [] codePointRanges = blockCodePointRanges.get(block).build().getCodePointRanges();
      for (int j =0; j < codePointRanges.length; ++j) {
        if ((j % 4) == 0) {
          s.append(lineSeparator).append("         ");
        }
        s.append(String.format(" 0x%08x_%08xL,", codePointRanges[j].inclusiveFrom, codePointRanges[j].inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("}),");
    }
    s.setLength(s.length() - 1);
    s.append(";").append(lineSeparator).append(lineSeparator);
    s.append("""
              private final String alias;
              private final long [] codePointRanges;
              private Block(final String alias, final long [] codePointRanges) {
                  this.alias = alias;
                  this.codePointRanges = codePointRanges;
              }
              public final long [] getCodePointRanges() {
                  return Arrays.copyOf(codePointRanges, codePointRanges.length);
              }
          }
        """);
    s.append(lineSeparator).append("}").append(lineSeparator);

    try (final FileWriter fileWriter = new FileWriter(outputDirectory + File.separator + "Unicode.java")) {
      fileWriter.append(s.toString());
      fileWriter.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    System.out.println("total codepoints=" + i);

  }
}
