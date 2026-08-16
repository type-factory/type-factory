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
package org.typefactory.impl;

class CharacterNameCache {

  private CharacterNameCache() {
    // don't instantiate me
  }

  record CacheEntry(int codePoint, String name) {
  }

  // Cache size is read once at class-load time from TypeFactoryConfig so that
  // the org.typefactory.codePointNamesCacheSize property / environment variable
  // actually takes effect.
  private static final int CACHE_SIZE = TypeFactoryConfig.instance().codePointNamesCacheSize();

  private static final CacheEntry[] cache = new CacheEntry[CACHE_SIZE];

  static String getCharacterName(final int codePoint) {
    final var index = codePoint % CACHE_SIZE;
    final var entry = cache[index];
    if (entry != null && entry.codePoint == codePoint) {
      return entry.name;
    }
    final var name = Character.getName(codePoint);
    cache[index] = new CacheEntry(codePoint, name);
    return name;
  }
}
