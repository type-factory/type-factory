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
