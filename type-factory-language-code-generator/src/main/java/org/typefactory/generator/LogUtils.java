package org.typefactory.generator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogUtils {

  private LogUtils() {
    // don't instantiate me
  }

  private static final String DEFAULT_LOGGING_PROPERTIES = """
      handlers=java.util.logging.ConsoleHandler
      .level=WARNING
      java.util.logging.ConsoleHandler.level=ALL
      java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
      java.util.logging.SimpleFormatter.format=[%1$tF %1$tT.%1$tL] %4$-7s: %5$s %n
      org.typefactory.level=FINEST
      """;
  private static LogManager getLogManager() {
    final URL resource = LogUtils.class.getClassLoader().getResource("logging.properties");
    final LogManager logManager = LogManager.getLogManager();
    try (InputStream loggingConfig = resource.openStream()) {
      logManager.readConfiguration(loggingConfig);
    } catch (Exception e) {
      try (InputStream defaultConfig = new ByteArrayInputStream(DEFAULT_LOGGING_PROPERTIES.getBytes(StandardCharsets.UTF_8))) {
        logManager.readConfiguration(defaultConfig);
      }
      catch (Exception ignored) {
        /* do nothing */
      }
    }
    return logManager;
  }

  private static Logger getTypeFactoryLogger() {
    final Logger logger = Logger.getLogger("org.typefactory");
    Level logLevel;
    try {
      logLevel = Level.parse(System.getProperty("logging.level.org.typefactory", "FINEST"));
    } catch (Exception ignored) {
      logLevel = Level.FINEST;
    }
    logManager.getLogger("org.typefactory").setLevel(logLevel);
    return logger;
  }

  private static final LogManager logManager = getLogManager();

  public static <T> Logger getLogger(Class<T> clazz) {
    return Logger.getLogger(clazz.getName());
  }
}
