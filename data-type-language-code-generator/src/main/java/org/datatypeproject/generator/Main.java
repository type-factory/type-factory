package org.datatypeproject.generator;

import java.io.File;
import java.util.logging.Logger;
import org.datatypeproject.generator.language.LanguageClassGenerator;
import org.datatypeproject.generator.unicodedata.UnicodeGroupData;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {

    if (args.length != 1) {
      System.err.println("""
          You must specify the following command line parameters:
          - Output directory to generate the Language class.
          """);
      System.exit(1);
    }

    final File outputDirectory = new File(args[0]);

    if (!outputDirectory.exists() || !outputDirectory.isDirectory()) {
      System.err.println("""
          The output directory to generate the Language class in must exist.
          """);
      System.exit(1);
    }

    final UnicodeGroupData unicodeGroupData = new UnicodeGroupData();
    final LanguageClassGenerator languageClassGenerator = new LanguageClassGenerator(outputDirectory, unicodeGroupData);

    languageClassGenerator.generateLanguageClass();
  }
}
