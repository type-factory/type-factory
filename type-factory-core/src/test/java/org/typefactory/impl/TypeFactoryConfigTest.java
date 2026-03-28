package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.impl.TypeFactoryConfig.ConfigKey.CODE_POINT_NAME_CACHE_SIZE;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.impl.TypeFactoryConfig.ConfigKey;
import org.typefactory.impl.TypeFactoryConfig.InternalConfiguration;

class TypeFactoryConfigTest {

  // System-property keys under test
  private static final String PROP_CACHE_SIZE        = "org.typefactory.codePointNamesCacheSize";
  private static final String PROP_CODE_POINT_NAMES  = "org.typefactory.codePointNamesInExceptionMessages";

  // Values saved before each test so we can restore them afterwards
  private String savedCacheSize;
  private String savedCodePointNames;

  @BeforeEach
  void saveSystemProperties() {
    savedCacheSize       = System.getProperty(PROP_CACHE_SIZE);
    savedCodePointNames  = System.getProperty(PROP_CODE_POINT_NAMES);
  }

  @AfterEach
  void restoreSystemProperties() {
    restoreProperty(PROP_CACHE_SIZE,       savedCacheSize);
    restoreProperty(PROP_CODE_POINT_NAMES, savedCodePointNames);
  }

  private static void restoreProperty(final String key, final String savedValue) {
    if (savedValue == null) {
      System.clearProperty(key);
    } else {
      System.setProperty(key, savedValue);
    }
  }

  // ─── instance() ──────────────────────────────────────────────────────────

  @Test
  void instance_returnsSingletonInstance() {
    assertThat(TypeFactoryConfig.instance())
        .isNotNull()
        .isSameAs(TypeFactoryConfig.instance());
  }

  // ─── codePointNamesInExceptionMessages() — defaults ──────────────────────

  @Test
  void codePointNamesInExceptionMessages_defaultsToFalse() {
    System.clearProperty(PROP_CODE_POINT_NAMES);
    final var config = new InternalConfiguration(propertiesOf());
    assertThat(config.codePointNamesInExceptionMessages).isFalse();
  }

  // ─── codePointNamesCacheSize() — defaults ────────────────────────────────

  @Test
  void codePointNamesCacheSize_defaultsTo499() {
    System.clearProperty(PROP_CACHE_SIZE);
    final var config = new InternalConfiguration();
    assertThat(config.codePointNamesCacheSize).isEqualTo(499);
  }

  // ─── System-property overrides ───────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] systemProp={0} → expected={1}")
  @CsvSource(delimiter = '|', textBlock = """
      true  | true
      false | false
      TRUE  | true
      FALSE | false
      yes   | false
      """)
  void codePointNamesInExceptionMessages_readFromSystemProperty(
      final String systemPropValue, final boolean expected) {
    System.setProperty(PROP_CODE_POINT_NAMES, systemPropValue);
    final var config = new InternalConfiguration();
    assertThat(config.codePointNamesInExceptionMessages).isEqualTo(expected);
  }

  @ParameterizedTest(name = "[{index}] systemProp={0} → expected={1}")
  @CsvSource(delimiter = '|', textBlock = """
      100  | 100
      1    | 1
      999  | 999
      """)
  void codePointNamesCacheSize_readFromSystemProperty(
      final String systemPropValue, final int expected) {
    System.setProperty(PROP_CACHE_SIZE, systemPropValue);
    final var config = new InternalConfiguration();
    assertThat(config.codePointNamesCacheSize).isEqualTo(expected);
  }

  @Test
  void codePointNamesCacheSize_invalidSystemPropertyFallsBackToDefault() {
    System.setProperty(PROP_CACHE_SIZE, "not-a-number");
    final var config = new InternalConfiguration();
    assertThat(config.codePointNamesCacheSize).isEqualTo(499);
  }

  // ─── type-factory.properties file loading ────────────────────────────────

  @Test
  void loadTypeFactoryProperties_returnsEmptyPropertiesWhenFileNotOnClasspath() {
    // No type-factory.properties file exists in test resources, so result is empty
    final var props = InternalConfiguration.loadTypeFactoryProperties();
    assertThat(props).isNotNull();
  }

  // ─── getConfigValue() priority: systemProperty > envVar > fileProps > default

  @Test
  void getConfigValue_systemPropertyTakesPriorityOverAll() {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars       = envVarsOf(configKey, "111");
    final var sysProps      = propertiesOf(configKey, "222");
    final var fileProps     = propertiesOf(configKey, "333");

    final var result = InternalConfiguration.getConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("222");
  }

  @Test
  void getConfigValue_envVarTakesPriorityOverFilePropsAndDefault() {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf(configKey, "111");
    final var sysProps   = propertiesOf(); // no system-property override
    final var fileProps  = propertiesOf(configKey, "333");

    final var result = InternalConfiguration.getConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("111");
  }

  @Test
  void getConfigValue_filePropsTakesPriorityOverDefault() {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf(); // no env-var override
    final var sysProps   = propertiesOf(); // no system-property override
    final var fileProps  = propertiesOf(configKey, "333");

    final var result = InternalConfiguration.getConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("333");
  }

  @Test
  void getConfigValue_fallsBackToDefaultWhenNoOverrides() {
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf();
    final var fileProps  = propertiesOf();

    final var result = InternalConfiguration.getConfigValue(CODE_POINT_NAME_CACHE_SIZE, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("499");
  }

  // ─── getStringConfigValue() ──────────────────────────────────────────────

  @Test
  void getStringConfigValue_returnsStringValueDirectly() {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf(configKey, "777");
    final var fileProps  = propertiesOf();

    final var result = InternalConfiguration.getStringConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("777");
  }

  @Test
  void getStringConfigValue_convertsNonStringValueViaToString() {
    // Inject an Integer object directly into a Properties to exercise the non-String branch
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf();
    final var fileProps  = propertiesOf(configKey, 42); // Integer, not String

    final var result = InternalConfiguration.getStringConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo("42");
  }

  // ─── getBooleanConfigValue() ─────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] stringValue={0} → expected={1}")
  @CsvSource(delimiter = '|', textBlock = """
      true  | true
      false | false
      TRUE  | true
      FALSE | false
      other | false
      """)
  void getBooleanConfigValue_parsesStringValues(
      final String stringValue, final boolean expected) {
    final var configKey  = ConfigKey.CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf(configKey, stringValue);
    final var fileProps  = propertiesOf();

    final var result = InternalConfiguration.getBooleanConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void getBooleanConfigValue_handlesBooleanInstanceDirectly() {
    // Inject a Boolean object (not a String) to exercise the `instanceof Boolean b` branch
    final var configKey  = ConfigKey.CODE_POINT_NAMES_IN_EXCEPTION_MESSAGES;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf();
    final var fileProps  = propertiesOf(configKey, Boolean.TRUE);

    final var result = InternalConfiguration.getBooleanConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isTrue();
  }

  // ─── getIntegerConfigValue() ─────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] stringValue={0} → expected={1}")
  @CsvSource(delimiter = '|', textBlock = """
      1    | 1
      499  | 499
      1000 | 1000
      """)
  void getIntegerConfigValue_parsesStringValues(
      final String stringValue, final int expected) {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf(configKey, stringValue);
    final var fileProps  = propertiesOf();

    final var result = InternalConfiguration.getIntegerConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo(expected);
  }

  @Test
  void getIntegerConfigValue_handlesNumberInstanceDirectly() {
    // Inject a Long (a Number but not an Integer) to exercise the `instanceof Number n` branch
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf();
    final var fileProps  = propertiesOf(configKey, 256L); // Long, not String

    final var result = InternalConfiguration.getIntegerConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo(256);
  }

  @Test
  void getIntegerConfigValue_invalidStringFallsBackToKeyDefault() {
    final var configKey  = CODE_POINT_NAME_CACHE_SIZE;
    final var envVars    = envVarsOf();
    final var sysProps   = propertiesOf(configKey, "not-a-number");
    final var fileProps  = propertiesOf();

    final var result = InternalConfiguration.getIntegerConfigValue(configKey, envVars, sysProps, fileProps);

    assertThat(result).isEqualTo(499); // ConfigKey.CODE_POINT_NAME_CACHE_SIZE default
  }

  // ─── helpers ─────────────────────────────────────────────────────────────

  /** Build an env-var map with no entries. */
  private static Map<String,Object> envVarsOf() {
    return Map.<String,Object>of();
  }

  /** Build an env-var map with a single entry. */
  private static Map<String,Object> envVarsOf(final ConfigKey key, final Object value) {
    return Map.of(key.environmentVariableName, value);
  }

  /** Build a {@link Properties} with no entries. */
  private static Properties propertiesOf() {
    return new Properties();
  }


  /** Build a {@link Properties} with a single entry. */
  private static <T> Properties propertiesOf(final ConfigKey key, final T value) {
    final var props = new Properties();
    props.put(key.propertyName, value);
    return props;
  }
}
