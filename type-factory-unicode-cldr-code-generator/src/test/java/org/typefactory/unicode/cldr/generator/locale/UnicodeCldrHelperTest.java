/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typefactory.unicode.cldr.generator.locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.typefactory.unicode.cldr.generator.unicode.cldr.CldrLocaleXmlDocument;
import org.typefactory.unicode.cldr.generator.unicode.cldr.UnicodeCldrHelper;

class UnicodeCldrHelperTest {

  @Test
  void getCldrLocaleXmlFilePaths_loadsFromClasspathResources() {
    final Path cldrMainDirectory = Path.of("target", "classes", "cldr-common", "common", "main");
    org.junit.jupiter.api.Assumptions.assumeTrue(cldrMainDirectory.toFile().isDirectory(), "CLDR resources must be unpacked for this test");

    final List<String> actual = UnicodeCldrHelper.getCldrLocaleXmlFilePaths();

    assertThat(actual)
        .isNotEmpty()
        .contains("cldr-common/common/main/af.xml", "cldr-common/common/main/be_TARASK.xml");
  }

  @Test
  void getCldrLocaleXmlFilePaths_loadsFromJarClasspathResources() throws Exception {
    final Path jarFile = Path.of(System.getProperty("java.io.tmpdir"), "cldr-test-" + System.nanoTime() + ".jar");
    createJarWithCldrResources(jarFile);

    final ClassLoader original = Thread.currentThread().getContextClassLoader();
    try (URLClassLoader classLoader = new URLClassLoader(new URL[] {jarFile.toUri().toURL()}, null)) {
      Thread.currentThread().setContextClassLoader(classLoader);

      final List<String> actual = UnicodeCldrHelper.getCldrLocaleXmlFilePaths();

      assertThat(actual).containsExactly(
          "cldr-common/common/main/af.xml",
          "cldr-common/common/main/be_TARASK.xml");
    } finally {
      Thread.currentThread().setContextClassLoader(original);
    }
  }

  @Test
  void getCldrLocaleXmlDocument_loadsByResourceName() {
    final CldrLocaleXmlDocument actual =
        UnicodeCldrHelper.getCldrLocaleXmlDocument("cldr-common/common/main/af.xml");

    assertThat(actual.getLocale().getLanguage()).isEqualTo("af");
    assertThat(actual.getStandardExemplarCharacters().isEmpty()).isFalse();
  }

  @Test
  void getCldrLocaleXmlFilePaths_returnsEmptyListWhenResourceDirectoryMissing() {
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
  void getCldrLocaleXmlFilePaths_wrapsPathResolutionErrors() {
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

  private static void createJarWithCldrResources(final Path jarFile) throws IOException {
    try (JarOutputStream jarOutputStream = new JarOutputStream(java.nio.file.Files.newOutputStream(jarFile))) {
      jarOutputStream.putNextEntry(new JarEntry("cldr-common/common/main/"));
      jarOutputStream.closeEntry();
      jarOutputStream.putNextEntry(new JarEntry("cldr-common/common/main/af.xml"));
      jarOutputStream.write("""
          <?xml version="1.0" encoding="UTF-8"?>
          <ldml>
            <identity>
              <language type="af"/>
            </identity>
            <characters>
              <exemplarCharacters>[a]</exemplarCharacters>
            </characters>
          </ldml>
          """.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      jarOutputStream.closeEntry();
      jarOutputStream.putNextEntry(new JarEntry("cldr-common/common/main/be_TARASK.xml"));
      jarOutputStream.write("""
          <?xml version="1.0" encoding="UTF-8"?>
          <ldml>
            <identity>
              <language type="be"/>
            </identity>
            <characters>
              <exemplarCharacters>[б]</exemplarCharacters>
            </characters>
          </ldml>
          """.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      jarOutputStream.closeEntry();
    }
  }
}
