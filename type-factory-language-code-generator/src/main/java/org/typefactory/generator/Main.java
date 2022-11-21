/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.generator;

import static org.typefactory.generator.LoggingConfig.configureLogging;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.typefactory.generator.letters.LettersClassGenerator;
import org.typefactory.generator.unicodedata.UnicodeGroupData;

public class Main {

  static {
    configureLogging();
  }


  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {

    if (args.length != 2) {
      logger.severe("""
          You must specify the following command line parameters:
          - The path the license header file.
          - Output directory to generate the Letters class.
          """);
      System.exit(1);
    }

    final String licenseHeader = getLicenseHeader(args[0]);
    final File outputDirectory = new File(args[1]);

    if (!outputDirectory.exists() || !outputDirectory.isDirectory()) {
      logger.severe("""
          The output directory to generate the Language class in must exist.
          """);
      System.exit(1);
    }

    final UnicodeGroupData unicodeGroupData = UnicodeGroupData.INSTANCE;
    final LettersClassGenerator lettersClassGenerator =
        new LettersClassGenerator(licenseHeader, outputDirectory, unicodeGroupData);

    lettersClassGenerator.generateLanguageClass();
  }

  private static String getLicenseHeader(final String licenseFilePath) {
    try {
      return Files.readString(Paths.get(licenseFilePath), StandardCharsets.UTF_8).trim() + "\n";
    } catch (final IOException e) {
      logger.severe("""
          The license header file must exist.
          """);
      System.exit(1);
    }
    return null;
  }
}
