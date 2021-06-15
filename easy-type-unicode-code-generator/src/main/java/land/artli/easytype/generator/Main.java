package land.artli.easytype.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.stream.Collectors;
import land.artli.easytype.generator.CodePointRanges.CodePointRangesBuilder;
import land.artli.easytype.generator.Unicode.RangedSubset;

public class Main {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static void main(final String[] args) {

    final String inputXml = args[0];
    final String outputDirectory = args[1];

    final UnicodeGroupDataLoader loader = new UnicodeGroupDataLoader(inputXml);

    int i = 0;
    final TreeMap<String, CodePointRangesBuilder> blockCodePointRangeBuilders = new TreeMap<>();
    final TreeMap<String, CodePointRangesBuilder> scriptCodePointRangeBuilders = new TreeMap<>();
    final TreeMap<UnicodeCategory, CodePointRangesBuilder> categoryCodePointRangeBuilders = new TreeMap<>();
    final CodePointRangesBuilder whitespaceCodePointRangesBuilder = CodePointRanges.builder();
    for (UnicodeCodePoint c : loader.getUnicodeCodePoints()) {
      ++i;
      if (c.getCodePoint() == null) {
        continue;
      }
      CodePointRangesBuilder codePointRangesBuilder;
      if (c.getBlock() != null) {
        codePointRangesBuilder = blockCodePointRangeBuilders.get(c.getBlock());
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          blockCodePointRangeBuilders.put(c.getBlock(), codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getFirstCodePoint(), c.getLastCodePoint());
        }
      }

      if (c.getScript() != null) {
        codePointRangesBuilder = scriptCodePointRangeBuilders.get(c.getScript());
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          scriptCodePointRangeBuilders.put(c.getScript(), codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getFirstCodePoint(), c.getLastCodePoint());
        }
      }

      final UnicodeCategory unicodeCategory = UnicodeCategory.of(c.getGeneralCategory());
      if (unicodeCategory != null) {
        codePointRangesBuilder = categoryCodePointRangeBuilders.get(unicodeCategory);
        if (codePointRangesBuilder == null) {
          codePointRangesBuilder = CodePointRanges.builder();
          categoryCodePointRangeBuilders.put(unicodeCategory, codePointRangesBuilder);
        }
        if (c.hasCodePoint()) {
          codePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          codePointRangesBuilder.addCodePointRange(c.getFirstCodePoint(), c.getLastCodePoint());
        }
      }

      if (c.isWhitespace()) {
        if (c.hasCodePoint()) {
          whitespaceCodePointRangesBuilder.addCodePoint(c.getCodePoint());
        } else if (c.hasCodePointRange()) {
          whitespaceCodePointRangesBuilder.addCodePointRange(c.getFirstCodePoint(), c.getLastCodePoint());
        }
      }
    }

    final StringBuilder enumOtherValues = new StringBuilder();
    enumOtherValues.append(LINE_SEPARATOR).append("    WHITESPACE(\"Whitespace\",");
    final CodePointRanges whitespaceCodePointRanges = whitespaceCodePointRangesBuilder.build();
    writeCodeRangeArrays(enumOtherValues, whitespaceCodePointRanges);
    enumOtherValues.append(")").append(LINE_SEPARATOR).append(LINE_SEPARATOR);

    final StringBuilder enumCategoryValues = new StringBuilder();
    for (UnicodeCategory category : UnicodeCategory.values()) {
      enumCategoryValues.append(LINE_SEPARATOR).append("    /** ").append(category.getAbbreviation()).append(" – ")
          .append(category.getDescription()).append(" */")
          .append(LINE_SEPARATOR).append("    ").append(category.name())
          .append("(\"").append(category.getAbbreviation())
          .append("\", \"").append(category.getFullName().replace('_', ' '))
          .append("\",");
      final UnicodeCategory[] compositeCategories = category.isComposite() ? category.getCompositeCategories() : new UnicodeCategory[]{category};
      final CodePointRangesBuilder codePointRangesBuilder = CodePointRanges.builder();
      for (UnicodeCategory compositeCategory : compositeCategories) {
        if (categoryCodePointRangeBuilders.containsKey(compositeCategory)) {
          final CodePointRanges codePointRanges = categoryCodePointRangeBuilders.get(compositeCategory).build();
          codePointRangesBuilder.addCodePointRanges(codePointRanges.getSingleByteCodePointRanges());
          codePointRangesBuilder.addCodePointRanges(codePointRanges.getDoubleByteCodePointRanges());
          codePointRangesBuilder.addCodePointRanges(codePointRanges.getTripleByteCodePointRanges());
        }
      }
      final CodePointRanges codePointRanges = codePointRangesBuilder.build();
      writeCodeRangeArrays(enumCategoryValues, codePointRanges);
      enumCategoryValues.append("),");
    }

    final StringBuilder enumScriptValues = new StringBuilder();
    for (String script : scriptCodePointRangeBuilders.keySet()) {
      enumScriptValues.append(LINE_SEPARATOR).append("    ").append(script.toUpperCase()).append("(\"").append(script).append("\",");
      final CodePointRanges scriptCodePointRanges = scriptCodePointRangeBuilders.get(script).build();
      writeCodeRangeArrays(enumScriptValues, scriptCodePointRanges);
      enumScriptValues.append("),");
    }

    final StringBuilder enumBlockValues = new StringBuilder();
    for (String block : blockCodePointRangeBuilders.keySet()) {
      enumBlockValues.append(LINE_SEPARATOR).append("    ").append(block.toUpperCase()).append("(\"").append(block).append("\",");
      final CodePointRanges blockCodePointRanges = blockCodePointRangeBuilders.get(block).build();
      writeCodeRangeArrays(enumBlockValues, blockCodePointRanges);
      enumBlockValues.append("),");
    }

    final InputStream unicodeTemplateStream = Main.class.getClassLoader().getResourceAsStream("Unicode_Template.java");
    final String unicodeTemplate = new BufferedReader(new InputStreamReader(unicodeTemplateStream))
        .lines().collect(Collectors.joining("\n"));

    final String unicodeFile = unicodeTemplate
        .replace("package land.artli.easytype.generator;", "package land.artli.easytype;")
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

    System.out.println("total codepoints=" + i);

  }

  private static void writeCodeRangeArrays(final StringBuilder s, final CodePointRanges codePointRanges) {

    final char[] singleByteCodePointRanges = codePointRanges.getSingleByteCodePointRanges();
    if (singleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        new char [0],");
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
    final int[] doubleByteCodePointRanges = codePointRanges.getDoubleByteCodePointRanges();
    if (doubleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        new int [0],");
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
    final long[] tripleByteCodePointRanges = codePointRanges.getTripleByteCodePointRanges();
    if (tripleByteCodePointRanges.length == 0) {
      s.append(LINE_SEPARATOR).append("        new long [0]");
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
