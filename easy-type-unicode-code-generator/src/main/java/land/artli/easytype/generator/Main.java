package land.artli.easytype.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;
import land.artli.easytype.generator.Unicode.RangedSubset;
import land.artli.easytype.generator.Unicode.RangedSubset.RangedSubsetBuilder;
import org.unicode.ns._2003.ucd._1.Block;

public class Main {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static void main(final String[] args) {

    final String inputXml = args[0];
    final String outputDirectory = args[1];

    final UnicodeGroupDataLoader loader = new UnicodeGroupDataLoader(inputXml);

    final StringBuilder enumOtherValues = new StringBuilder();
    enumOtherValues
        .append(LINE_SEPARATOR)
        .append("    public static final Other WHITESPACE = new Other(")
        .append(LINE_SEPARATOR)
        .append("\"WHITESPACE\", \"Whitespace\",");
    final RangedSubset whitespaceRangedSubset = loader.getWhitespaceRangedSubset();
    writeCodeRangeArrays(enumOtherValues, whitespaceRangedSubset);
    enumOtherValues.append(");");

    final StringBuilder enumCategoryValues = new StringBuilder();
    for (UnicodeCategory category : UnicodeCategory.values()) {
      enumCategoryValues.append(LINE_SEPARATOR).append("    /** ").append(category.getAbbreviation()).append(" – ")
          .append(category.getDescription()).append(" */")
          .append(LINE_SEPARATOR).append("    public static final Category ").append(category.name())
          .append(" = new Category(")
          .append(LINE_SEPARATOR).append("      \"").append(category.name())
          .append("\", \"").append(category.getFullName())
          .append("\", \"").append(category.getAbbreviation().replace('_', ' '))
          .append("\",");
      final UnicodeCategory[] compositeCategories = category.isComposite() ? category.getCompositeCategories() : new UnicodeCategory[]{category};
      final RangedSubsetBuilder rangedSubsetBuilder = RangedSubset.builder();
      final Map<UnicodeCategory, RangedSubset> categoryRangedSubsets = loader.getCategoryRangedSubsets();
      for (UnicodeCategory compositeCategory : compositeCategories) {
        if (categoryRangedSubsets.containsKey(compositeCategory)) {
          final RangedSubset rangedSubset = categoryRangedSubsets.get(compositeCategory);
          rangedSubsetBuilder.addRangedSubset(rangedSubset);
        }
      }
      final RangedSubset rangedSubset = rangedSubsetBuilder.build();
      writeCodeRangeArrays(enumCategoryValues, rangedSubset);
      enumCategoryValues.append(");");
    }

    final Map<String, RangedSubset> scriptRangedSubsets = loader.getScriptRangedSubsets();
    final StringBuilder enumScriptValues = new StringBuilder();
    for (String script : scriptRangedSubsets.keySet()) {
      enumScriptValues.append(LINE_SEPARATOR).append("    public static final Script ")
          .append(script.toUpperCase()).append(" = new Script(")
          .append(LINE_SEPARATOR).append("      \"")
          .append(script.toUpperCase()).append("\", \"")
          .append(script).append("\",");
      final RangedSubset scriptRangedSubset = scriptRangedSubsets.get(script);
      writeCodeRangeArrays(enumScriptValues, scriptRangedSubset);
      enumScriptValues.append(");");
    }

    final Map<String, RangedSubset> blockRangedSubsets = loader.getBlockRangedSubsets();
    final Map<String, Block> blockMap = loader.getBlocksByTheirAbbreviation();
    final StringBuilder enumBlockValues = new StringBuilder();
    for (String blockAbbreviation : blockRangedSubsets.keySet()) {
      final Block block = blockMap.get(blockAbbreviation);
      final String blockName = block.getName().toUpperCase().replace(' ', '_').replace('-', '_');
      enumBlockValues.append(LINE_SEPARATOR).append("    public static final Block ")
          .append(blockName).append(" = new Block(")
          .append(LINE_SEPARATOR).append("      \"")
          .append(blockAbbreviation.toUpperCase()).append("\", \"")
          .append(blockAbbreviation).append("\",")
          .append(LINE_SEPARATOR).append("      ")
          .append(String.format("0x%08x", Integer.parseInt(block.getFirstCp(), 16))).append(", ")
          .append(String.format("0x%08x", Integer.parseInt(block.getLastCp(), 16))).append(", ");
      final RangedSubset blockRangedSubset = blockRangedSubsets.get(blockAbbreviation);
      writeCodeRangeArrays(enumBlockValues, blockRangedSubset);
      enumBlockValues.append(");");
    }

    final InputStream unicodeTemplateStream = Main.class.getClassLoader().getResourceAsStream("Unicode_Template.java");
    final String unicodeTemplate = new BufferedReader(new InputStreamReader(unicodeTemplateStream))
        .lines().collect(Collectors.joining("\n"));

    final String generatedMessage = String.format("""
        @javax.annotation.processing.Generated(
            comments = "This file is partly generated from the Unicode file 'ucd.all.grouped.xml'",
            date="%tFT%tT",
            value = "land.artli:easy-type-unicode-code-generator")""", LocalDate.now(), LocalTime.now());

    final String unicodeFile = unicodeTemplate
        .replace("package land.artli.easytype.generator;", "package land.artli.easytype;")
        .replace("// INSERT-GENERATED-MESSAGE", generatedMessage)
        .replace("    // INSERT-OTHER-VALUES-HERE", enumOtherValues.toString())
        .replace("    // INSERT-CATEGORY-VALUES-HERE", enumCategoryValues.toString())
        .replace("    // INSERT-SCRIPT-VALUES-HERE", enumScriptValues.toString())
        .replace("    // INSERT-BLOCK-VALUES-HERE", enumBlockValues.toString());

    try (final FileWriter fileWriter = new FileWriter(outputDirectory + File.separator + "Unicode.java")) {
      fileWriter.append(unicodeFile);
      fileWriter.flush();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeCodeRangeArrays(final StringBuilder s, final RangedSubset rangedSubset) {

    final char[] singleByteCodePointRanges = rangedSubset.getSingleByteCodePointRanges();
    if (singleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        EMPTY_CHAR_ARRAY,");
    } else {
      s.append(LINE_SEPARATOR).append("        new char [] {");
      for (int j = 0; j < singleByteCodePointRanges.length; ++j) {
        if ((j % 8) == 0) {
          s.append(LINE_SEPARATOR).append("         ");
        }
        final int inclusiveFrom = RangedSubset.getInclusiveFrom(singleByteCodePointRanges[j]);
        final int inclusiveTo = RangedSubset.getInclusiveTo(singleByteCodePointRanges[j]);
        s.append(String.format(" 0x%02x_%02x,", inclusiveFrom, inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("},");
    }
    final int[] doubleByteCodePointRanges = rangedSubset.getDoubleByteCodePointRanges();
    if (doubleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        EMPTY_INT_ARRAY,");
    } else {
      s.append(LINE_SEPARATOR).append("        new int [] {");
      for (int j = 0; j < doubleByteCodePointRanges.length; ++j) {
        if ((j % 8) == 0) {
          s.append(LINE_SEPARATOR).append("         ");
        }
        final int inclusiveFrom = RangedSubset.getInclusiveFrom(doubleByteCodePointRanges[j]);
        final int inclusiveTo = RangedSubset.getInclusiveTo(doubleByteCodePointRanges[j]);
        s.append(String.format(" 0x%04x_%04x,", inclusiveFrom, inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("},");
    }
    final long[] tripleByteCodePointRanges = rangedSubset.getTripleByteCodePointRanges();
    if (tripleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        EMPTY_LONG_ARRAY");
    } else {
      s.append(LINE_SEPARATOR).append("        new long [] { ");
      for (int j = 0; j < tripleByteCodePointRanges.length; ++j) {
        if ((j % 4) == 0) {
          s.append(LINE_SEPARATOR).append("         ");
        }
        final int inclusiveFrom = RangedSubset.getInclusiveFrom(tripleByteCodePointRanges[j]);
        final int inclusiveTo = RangedSubset.getInclusiveTo(tripleByteCodePointRanges[j]);
        s.append(String.format(" 0x%08x_%08xL,", inclusiveFrom, inclusiveTo));
      }
      s.setLength(s.length() - 1);
      s.append("}");
    }
  }
}
