package org.typefactory;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A custom ResourceBundle.Control that forces the ResourceBundle system to only look for class-based bundles named exactly by their IETF BCP 47
 * language tag (e.g., "en", "fr", "zh-TW") inside a given package.
 */
final class LocaleDataControl extends ResourceBundle.Control {

  static final ResourceBundle.Control RESOURCE_BUNDLE_CONTROL = new LocaleDataControl();

  static final String RESOURCE_BASE_NAME_ORG_TYPEFACTORY_UNICODE_CLDR = "org.typefactory.unicode.cldr";

  /**
   * Restricts the allowed formats strictly to class-based bundles. This bypasses any check or fallback to ".properties" files.
   */
  @Override
  public List<String> getFormats(final String baseName) {
    Objects.requireNonNull(baseName, "Resource bundle baseName cannot be null");
    return FORMAT_CLASS; // Standard equivalent to List.of("java.class")
  }

  /**
   * Constructs the fully-qualified class name. The system expects 'baseName' to be the package path (ending in a dot). It appends the exact language
   * tag instead of the default underscore-separated format.
   */
  @Override
  public String toBundleName(final String baseName, final Locale locale) {
    Objects.requireNonNull(baseName, "Resource bundle baseName cannot be null");
    Objects.requireNonNull(locale, "Resource bundle locale cannot be null");

    // Use the exact IETF BCP 47 language tag (e.g., "zh-TW" instead of "zh_TW")
    final String languageTag = locale.toLanguageTag().replace('-', '_');

    // If baseName already ends with a dot, do not add another one
    return baseName.endsWith(".")
        ? baseName + languageTag
        : baseName + "." + languageTag;
  }
}
