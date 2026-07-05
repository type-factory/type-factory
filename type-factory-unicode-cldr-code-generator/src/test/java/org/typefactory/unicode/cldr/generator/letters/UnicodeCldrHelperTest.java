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
package org.typefactory.unicode.cldr.generator.letters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Locale;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UnicodeCldrHelperTest {

  @Test
  void getCldrLocaleXmlFilePaths_loadsFromClasspathResources() {
    final Path cldrMainDirectory = Path.of("target", "classes", "cldr-common", "common", "main");
    org.junit.jupiter.api.Assumptions.assumeTrue(cldrMainDirectory.toFile().isDirectory(), "CLDR resources must be unpacked for this test");

    final List<Path> actual = UnicodeCldrHelper.getCldrLocaleXmlFilePaths();

    assertThat(actual)
        .isNotEmpty()
        .anySatisfy(path -> assertThat(path.getFileName().toString()).isEqualTo("af.xml"));
    assertThat(actual)
        .anySatisfy(path -> assertThat(path.getFileName().toString()).isEqualTo("be_TARASK.xml"));
  }

  @Test
  void getCldrLocaleParsedXmlDocument_loadsClasspathResource() {
    final Path localeXml = Path.of("target", "classes", "cldr-common", "common", "main", "af.xml");
    org.junit.jupiter.api.Assumptions.assumeTrue(localeXml.toFile().isFile(), "CLDR resources must be unpacked for this test");

    final CldrLocaleXmlDocument actual = UnicodeCldrHelper.getCldrLocaleParsedXmlDocument(localeXml);

    assertThat(actual.getLocale().getLanguage()).isEqualTo("af");
    assertThat(actual.getStandardExemplarCharacters().isEmpty()).isFalse();
  }

  @Test
  void getCldrLocaleXmlFilePaths_returnsEmptyListWhenResourceDirectoryMissing() throws Exception {
    final ClassLoader original = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader(new ClassLoader(original) {
      @Override
      public URL getResource(final String name) {
        return null;
      }
    });

    try {
      assertThat(UnicodeCldrHelper.getCldrLocaleXmlFilePaths()).isEmpty();
    } finally {
      Thread.currentThread().setContextClassLoader(original);
    }
  }

  @Test
  void getCldrLocaleXmlFilePaths_wrapsPathResolutionErrors() throws Exception {
    final ClassLoader original = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader(new ClassLoader(original) {
      @Override
      public URL getResource(final String name) {
        try {
          return new URL("file", "", "/tmp|bad");
        } catch (final Exception e) {
          throw new IllegalStateException(e);
        }
      }
    });

    try {
      assertThatThrownBy(UnicodeCldrHelper::getCldrLocaleXmlFilePaths)
          .isInstanceOf(RuntimeException.class);
    } finally {
      Thread.currentThread().setContextClassLoader(original);
    }
  }

  @Test
  void getCldrLocaleParsedXmlDocument_wrapsResourceLookupErrors() {
    final Path missingPath = Path.of("target", "missing-" + System.nanoTime() + ".xml");

    assertThatThrownBy(() -> UnicodeCldrHelper.getCldrLocaleParsedXmlDocument(missingPath))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void isIso639Language_distinguishesKnownLanguages() {
    assertThat(UnicodeCldrHelper.isIso639Language(Locale.forLanguageTag("af"))).isTrue();
    assertThat(UnicodeCldrHelper.isIso639Language(Locale.forLanguageTag("zz"))).isFalse();
    assertThat(UnicodeCldrHelper.isIso639Language(Locale.forLanguageTag(""))).isFalse();
  }
}
