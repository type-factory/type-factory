package org.datatypeproject.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import org.datatypeproject.Category;
import org.datatypeproject.TypeParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


class TypeParser_UnicodeNormalizationTest extends AbstractTypeParserTest {

  enum WordsWithDiacriticModifiers {
    GREEK_CAFFEINE("καφεΐνη", 'κ', 'α', 'φ', 'ε', 'ι', '̈', '́', 'ν', 'η'),
    GREEK_MONKEY("μαϊμού", 'μ', 'α', 'ι', '̈', 'μ', 'ο', 'υ', '́'),
    GREEK_TIGER("τίγρη", 'τ', 'ι', '́', 'γ', 'ρ', 'η'),
    FRENCH_BEETLE("scarabée", 's', 'c', 'a', 'r', 'a', 'b', 'e', '́', 'e'),
    DANISH_BEAR("får", 'f', 'a', '̊', 'r'),
    VIETNAMESE_GIRAFFE("hươu cao cổ", 'h', 'u', '̛', 'o', '̛', 'u', ' ', 'c', 'a', 'o', ' ', 'c', 'o', '̂', '̉'),
    VIETNAMESE_KANGAROO("chuột túi", 'c', 'h', 'u', 'o', '̣', '̂', 't', ' ', 't', 'u', '́', 'i'),
    RUSSIAN_SQUIRREL("бе́лка", 'б', 'е', '́', 'л', 'к', 'а'),
    RUSSIAN_MONKEY("обезья́на", 'о', 'б', 'е', 'з', 'ь', 'я', '́', 'н', 'а');

    final String valueNFC;
    final String valueNFD;
    final String valueNFKC;
    final String valueNFKD;

    WordsWithDiacriticModifiers(final String value, final char... decomposedChars) {
      valueNFC = Normalizer.normalize(value, Form.NFC);
      valueNFD = Normalizer.normalize(value, Form.NFD);
      valueNFKC = Normalizer.normalize(value, Form.NFKC);
      valueNFKD = Normalizer.normalize(value, Form.NFKD);

      valueNFD.chars().forEach(i -> System.out.print("'" + (char) i + "',"));
      System.out.println();

      assert valueNFD.equals(String.valueOf(decomposedChars));
      assert valueNFKD.equals(String.valueOf(decomposedChars));
    }
  }


  @ParameterizedTest
  @EnumSource(WordsWithDiacriticModifiers.class)
  void should_parse_and_normalise_to_NFC(final WordsWithDiacriticModifiers value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .toCharacterNormalizationFormNFC()
            .acceptAllUnicodeLetters()
            .acceptUnicodeCategory(Category.MODIFIER_SYMBOL)
            .acceptUnicodeCategory(Category.NON_SPACING_MARK)
            .normalizeAndConvertWhitespaceTo(' ')
            .build();

    assertThat(typeParser.parseToString(value.valueNFC)).hasToString(value.valueNFC);
    assertThat(typeParser.parseToString(value.valueNFD)).hasToString(value.valueNFC);
    assertThat(typeParser.parseToString(value.valueNFKC)).hasToString(value.valueNFC);
    assertThat(typeParser.parseToString(value.valueNFKD)).hasToString(value.valueNFC);
  }

  @ParameterizedTest
  @EnumSource(WordsWithDiacriticModifiers.class)
  void should_parse_and_normalise_to_NFD(final WordsWithDiacriticModifiers value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .toCharacterNormalizationFormNFD()
            .acceptAllUnicodeLetters()
            .acceptUnicodeCategory(Category.NON_SPACING_MARK)
            .normalizeAndConvertWhitespaceTo(' ')
            .build();

    assertThat(typeParser.parseToString(value.valueNFC)).hasToString(value.valueNFD);
    assertThat(typeParser.parseToString(value.valueNFD)).hasToString(value.valueNFD);
    assertThat(typeParser.parseToString(value.valueNFKC)).hasToString(value.valueNFD);
    assertThat(typeParser.parseToString(value.valueNFKD)).hasToString(value.valueNFD);
  }

  @ParameterizedTest
  @EnumSource(WordsWithDiacriticModifiers.class)
  void should_parse_and_normalise_to_NFKC(final WordsWithDiacriticModifiers value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .toCharacterNormalizationFormNFKC()
            .acceptAllUnicodeLetters()
            .acceptUnicodeCategory(Category.MODIFIER_SYMBOL)
            .acceptUnicodeCategory(Category.NON_SPACING_MARK)
            .normalizeAndConvertWhitespaceTo(' ')
            .build();

    assertThat(typeParser.parseToString(value.valueNFC)).hasToString(value.valueNFKC);
    assertThat(typeParser.parseToString(value.valueNFD)).hasToString(value.valueNFKC);
    assertThat(typeParser.parseToString(value.valueNFKC)).hasToString(value.valueNFKC);
    assertThat(typeParser.parseToString(value.valueNFKD)).hasToString(value.valueNFKC);
  }

  @ParameterizedTest
  @EnumSource(WordsWithDiacriticModifiers.class)
  void should_parse_and_normalise_to_NFKD(final WordsWithDiacriticModifiers value) {

    final TypeParser typeParser =
        TypeParser.builder()
            .toCharacterNormalizationFormNFKD()
            .acceptAllUnicodeLetters()
            .acceptUnicodeCategory(Category.NON_SPACING_MARK)
            .normalizeAndConvertWhitespaceTo(' ')
            .build();

    assertThat(typeParser.parseToString(value.valueNFC)).hasToString(value.valueNFKD);
    assertThat(typeParser.parseToString(value.valueNFD)).hasToString(value.valueNFKD);
    assertThat(typeParser.parseToString(value.valueNFKC)).hasToString(value.valueNFKD);
    assertThat(typeParser.parseToString(value.valueNFKD)).hasToString(value.valueNFKD);
  }
}