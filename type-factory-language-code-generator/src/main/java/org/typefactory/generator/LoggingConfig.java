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

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

class LoggingConfig {

  private LoggingConfig() {
    // don't instantiate me
  }

  @SuppressWarnings("java:S106")
  static void configureLogging() {
    // must set before the Logger
    // loads logging.properties from the classpath
    try (InputStream loggingProperties = Main.class.getClassLoader().getResourceAsStream("logging.properties")) {
      LogManager.getLogManager().readConfiguration(loggingProperties);
    } catch (final IOException e) {
      System.err.println("Error – cannot load 'logging.properties' from the classpath");
      System.exit(1);
    }
  }

}
