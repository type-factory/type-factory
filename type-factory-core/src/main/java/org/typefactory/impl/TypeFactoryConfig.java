package org.typefactory.impl;

import java.util.Map;
import java.util.Properties;

final class TypeFactoryConfig {

  private static final TypeFactoryConfig INSTANCE = new TypeFactoryConfig();

  public static synchronized TypeFactoryConfig instance() {
    return INSTANCE;
  }

  public boolean codePointNamesInExceptionMessages() {
    return internalConfiguration.codePointNamesInExceptionMessages;
  }

  public int codePointNamesCacheSize() {
    return internalConfiguration.codePointNamesCacheSize;
  }

  private final InternalConfiguration internalConfiguration;

  private TypeFactoryConfig() {
    internalConfiguration = new InternalConfiguration();
  }

  enum ConfigKey {
    CODE_POINT_NAME_CACHE_SIZE(
        "org.typefactory.codePointNamesCacheSize",
        "ORG_TYPE_FACTORY_CODE_POINT_NAME_CACHE_SIZE",
        499),
    CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES(
        "org.typefactory.codePointNamesInExceptionMessages",
        "ORG_TYPE_FACTORY_CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES",
        false),
    ;
    final String propertyName;
    final String environmentVariableName;
    final String defaultValue;

    ConfigKey(final String propertyName, final String environmentVariableName, final String defaultValue) {
      this.propertyName = propertyName;
      this.environmentVariableName = environmentVariableName;
      this.defaultValue = defaultValue;
    }

    ConfigKey(final String propertyName, final String environmentVariableName, final int defaultValue) {
      this(propertyName, environmentVariableName, Integer.toString(defaultValue));
    }

    ConfigKey(final String propertyName, final String environmentVariableName, final boolean defaultValue) {
      this(propertyName, environmentVariableName, Boolean.toString(defaultValue));
    }
  }

  static final class InternalConfiguration {

    final boolean codePointNamesInExceptionMessages;

    final int codePointNamesCacheSize;

    InternalConfiguration() {
      this(loadTypeFactoryProperties());
    }

    InternalConfiguration(final Properties typeFactoryProperties) {
      final var environmentVariables = Map.<String, Object>copyOf(System.getenv());
      // Snapshot system properties to get a consistent, immutable view.
      final var systemProperties = new Properties(System.getProperties());
      systemProperties.putAll(System.getProperties());
      codePointNamesCacheSize = getIntegerConfigValue(
          ConfigKey.CODE_POINT_NAME_CACHE_SIZE,
          environmentVariables, systemProperties, typeFactoryProperties);
      codePointNamesInExceptionMessages = getBooleanConfigValue(
          ConfigKey.CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES,
          environmentVariables, systemProperties, typeFactoryProperties);
    }

    static Properties loadTypeFactoryProperties() {
      final var properties = new Properties();
      try (final var inputStream = TypeFactoryConfig.class.getResourceAsStream("/type-factory.properties")) {
        if (inputStream != null) {
          properties.load(inputStream);
        }
      } catch (final Exception e) {
        // Ignore exceptions when loading the properties file and just use the default values.
      }
      return properties;
    }

    static Object getConfigValue(
        final ConfigKey configKey,
        final Map<String, Object> environmentVariables,
        final Properties systemProperties,
        final Properties typeFactoryProperties) {

      return systemProperties.getOrDefault(
          configKey.propertyName,
          environmentVariables.getOrDefault(
              configKey.environmentVariableName,
              typeFactoryProperties.getOrDefault(
                  configKey.propertyName, configKey.defaultValue)));
    }

    static String getStringConfigValue(
        final ConfigKey configKey,
        final Map<String, Object> environmentVariables,
        final Properties systemProperties,
        final Properties typeFactoryProperties) {

      final var value = getConfigValue(configKey, environmentVariables, systemProperties, typeFactoryProperties);
      return value instanceof String s ? s : value.toString();
    }

    static boolean getBooleanConfigValue(
        final ConfigKey configKey,
        final Map<String, Object> environmentVariables,
        final Properties systemProperties,
        final Properties typeFactoryProperties) {

      final var value = getConfigValue(configKey, environmentVariables, systemProperties, typeFactoryProperties);
      return value instanceof Boolean b ? b : Boolean.parseBoolean(value.toString());
    }

    static int getIntegerConfigValue(
        final ConfigKey configKey,
        final Map<String, Object> environmentVariables,
        final Properties systemProperties,
        final Properties typeFactoryProperties) {

      try {
        final var value = getConfigValue(configKey, environmentVariables, systemProperties, typeFactoryProperties);
        return value instanceof Number n ? n.intValue() : Integer.parseInt(value.toString());
      } catch (final NumberFormatException e) {
        return Integer.parseInt(configKey.defaultValue);
      }
    }
  }
}
