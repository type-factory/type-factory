package org.datatypeproject.generator;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.datatypeproject.generator.letters.LettersClassGenerator;
import org.datatypeproject.generator.unicodedata.UnicodeGroupData;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {

    final Handler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.ALL);
    Logger.getGlobal().addHandler(consoleHandler);
    Logger.getGlobal().setLevel(Level.ALL);
//    Logger.getLogger("").addHandler(consoleHandler);
    Logger.getLogger(Main.class.getPackageName()).setLevel(Level.FINEST);

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

    final UnicodeGroupData unicodeGroupData = UnicodeGroupData.INSTANCE;
    final LettersClassGenerator lettersClassGenerator = new LettersClassGenerator(outputDirectory, unicodeGroupData);

    lettersClassGenerator.generateLanguageClass();
//
//    lettersClassGenerator.organizeIntoBlockRanged(
//        lettersClassGenerator.createJapaneseJSourceSet());
  }
}
