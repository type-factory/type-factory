package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.typefactory.NumberFormat.NumberFormatBuilder;

class FactoryTest {

  @Test
  void numberFormatBuilder_ShouldReturnNonNullInstance() {
    final NumberFormatBuilder builder = Factory.numberFormatBuilder();

    assertThat(builder)
        .isNotNull()
        .isInstanceOf(NumberFormatBuilder.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"en-AU", "en-US", "fr-FR", "de-DE", "ja-JP", "zh-CN"})
  void numberFormatBuilder_WithLocale_ShouldReturnNonNullInstance(final String localeStr) {
    final Locale locale = Locale.forLanguageTag(localeStr);
    final NumberFormatBuilder builder = Factory.numberFormatBuilder(locale);

    assertThat(builder)
        .isNotNull()
        .isInstanceOf(NumberFormatBuilder.class);
  }

}
