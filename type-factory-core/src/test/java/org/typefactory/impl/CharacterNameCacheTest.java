package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.typefactory.impl.CharacterNameCache.CacheEntry;

class CharacterNameCacheTest {

  // ─── Private constructor ──────────────────────────────────────────────────

  @Test
  void characterNameCache_privateConstructor_isInstantiableViaReflection() throws Exception {
    final Constructor<CharacterNameCache> constructor =
        CharacterNameCache.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    final var instance = constructor.newInstance();
    assertThat(instance).isNotNull();
  }

  // ─── CacheEntry record ────────────────────────────────────────────────────

  @Test
  void cacheEntry_record_storesCodePointAndName() {
    final var entry = new CacheEntry('A', "LATIN CAPITAL LETTER A");

    assertThat(entry.codePoint()).isEqualTo('A');
    assertThat(entry.name()).isEqualTo("LATIN CAPITAL LETTER A");
  }

  @Test
  void cacheEntry_record_equalityAndHashCode_areValueBased() {
    final var entry1 = new CacheEntry(65, "LATIN CAPITAL LETTER A");
    final var entry2 = new CacheEntry(65, "LATIN CAPITAL LETTER A");
    final var entry3 = new CacheEntry(66, "LATIN CAPITAL LETTER B");

    assertThat(entry1)
        .isEqualTo(entry2)
        .hasSameHashCodeAs(entry2)
        .isNotEqualTo(entry3);
  }

  @Test
  void cacheEntry_record_toString_containsFieldValues() {
    final var entry = new CacheEntry(65, "LATIN CAPITAL LETTER A");

    assertThat(entry.toString())
        .contains("65")
        .contains("LATIN CAPITAL LETTER A");
  }

  // ─── getCharacterName – cache miss (first call) ───────────────────────────

  @ParameterizedTest(name = "[{index}] codePoint=U+{0} → expectedName={1}")
  @CsvSource(delimiter = '|', textBlock = """
      65   | LATIN CAPITAL LETTER A
      66   | LATIN CAPITAL LETTER B
      48   | DIGIT ZERO
      57   | DIGIT NINE
      32   | SPACE
      9    | CHARACTER TABULATION
      8364 | EURO SIGN
      """)
  void getCharacterName_cacheMiss_returnsCorrectCharacterName(
      final int codePoint, final String expectedName) {

    final var name = CharacterNameCache.getCharacterName(codePoint);

    assertThat(name).isEqualTo(expectedName);
  }

  // ─── getCharacterName – cache hit (second call) ───────────────────────────

  @Test
  void getCharacterName_cacheHit_returnsSameNameOnSecondCall() {
    // Warm the cache for 'Z'
    final var firstCall = CharacterNameCache.getCharacterName('Z');
    // Second call should return the cached value
    final var secondCall = CharacterNameCache.getCharacterName('Z');

    assertThat(firstCall)
        .isEqualTo("LATIN CAPITAL LETTER Z");

    assertThat(secondCall)
        .isEqualTo("LATIN CAPITAL LETTER Z")
        .isSameAs(firstCall);
  }

  // ─── getCharacterName – cache collision (eviction) ───────────────────────
  //
  // The cache is a simple array of size CACHE_SIZE (default 499).
  // Two code points that satisfy (a % 499 == b % 499) map to the same slot.
  // Calling getCharacterName(a) then getCharacterName(b) exercises the
  // "entry exists but entry.codePoint != codePoint" branch, forcing a re-fetch.

  @Test
  void getCharacterName_cacheCollision_evictsOldEntryAndReturnsCorrectName() {
    // Default CACHE_SIZE is 499.
    // codePoint1 % 499 == codePoint2 % 499  ⟹  use 0 and 499.
    final int codePoint1 = 65;          // 'A'  → slot 65 % 499 = 65
    final int codePoint2 = 65 + 499;    // 564  → slot 564 % 499 = 65  (collision)

    final var name1 = CharacterNameCache.getCharacterName(codePoint1);
    // codePoint2 lands on the same slot → evicts codePoint1
    final var name2 = CharacterNameCache.getCharacterName(codePoint2);
    // Now re-fetch codePoint1 → slot is occupied by codePoint2, so it
    // must re-derive the name (exercises the miss branch again)
    final var name1Again = CharacterNameCache.getCharacterName(codePoint1);

    assertThat(name1).isEqualTo(Character.getName(codePoint1));
    assertThat(name2).isEqualTo(Character.getName(codePoint2));
    assertThat(name1Again).isEqualTo(Character.getName(codePoint1));
  }
}
