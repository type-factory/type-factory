package land.artli.easytype.generator.language;

import static land.artli.easytype.generator.language.JavadocFragments.LANGUAGE_ALPHABET_AIM_JAVADOC;
import static land.artli.easytype.generator.language.JavadocFragments.LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC;
import static land.artli.easytype.generator.language.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static land.artli.easytype.generator.language.JavadocFragments.LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC;
import static land.artli.easytype.generator.language.JavadocFragments.SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES;
import static land.artli.easytype.generator.language.JavadocFragments.SEE_ALSO_COMMON_ARABIC_SCRIPT_REFERENCES;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.ULocale;

public enum LanguageData {

  LETTERS_ARABIC_AR(locale("ar"),
      new char[]{
          'ء', 'آ', 'أ', 'ؤ', 'إ', 'ئ', 'ا', 'ب', 'ة', 'ت', 'ث', 'ج', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز',
          'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'ل', 'م', 'ن', 'ه', 'و', 'ى', 'ي'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      SEE_ALSO_COMMON_ARABIC_SCRIPT_REFERENCES),

  LETTERS_AZERI_AZ_LATN(locale("az", "", "", "LATN"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'Ç', 'ç', 'D', 'd', 'E', 'e', 'Ə', 'ə', 'F', 'f', 'G',
          'g', 'Ğ', 'ğ', 'H', 'h', 'X', 'x', 'I', 'ı', 'İ', 'i', 'J', 'j', 'K', 'k', 'Q', 'q', 'L',
          'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ö', 'ö', 'P', 'p', 'R', 'r', 'S', 's', 'Ş', 'ş', 'T',
          't', 'U', 'u', 'Ü', 'ü', 'V', 'v', 'Y', 'y', 'Z', 'z'}),

  LETTERS_DANISH_DA(locale("da"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f',
          'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
          'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
          'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z',
          'Æ', 'æ', 'Ø', 'ø', 'Å', 'å',
          // Letters with acute accent
          'Á', 'á', 'É', 'é', 'Í', 'í', 'Ó', 'ó', 'Ú', 'ú',},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Danish_orthography" target="_blank">Danish
               Orthography – Wikipedia</a> provided information about the Danish alphabet and what
               diacritics are supported.
               
          @see <a href="https://www.danishnet.com/culture/danish-alphabet" target="_blank">Danish
               Alphabet – Danishnet.com</a> provided information about the Danish alphabet.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_GERMAN_DE(locale("de"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g',
          'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o',
          'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'ẞ', 'ß', 'T', 't', 'U', 'u', 'V', 'v',
          'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z',
          // Letters with diaeresis
          'Ä', 'ä', 'Ö', 'ö', 'Ü', 'ü'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/German_orthography" target="_blank">German
               Orthography – Wikipedia</a> provided information about the German alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),


  LETTERS_GREEK_EL(locale("el"),
      new char[]{
          'Ά', 'Έ', 'Ή', 'Ί', 'Ό', 'Ύ', 'Ώ', 'ΐ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ',
          'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω',
          'Ϊ', 'Ϋ', 'ά', 'έ', 'ή', 'ί', 'ΰ', 'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'θ', 'ι',
          'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'π', 'ρ', 'ς', 'σ', 'τ', 'υ', 'φ', 'χ', 'ψ', 'ω',
          'ϊ', 'ϋ', 'ό', 'ύ', 'ώ'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Greek_alphabet" target="_blank">Greek
               Alphabet – Wikipedia</a> provided information about the Greek alphabet and what
               diacritics are supported.
               
          @see <a href="https://unicode.org/charts/PDF/U0370.pdf" target="_blank">Greek and Coptic
               Unicode Chart – Unicode Standard v14</a> provided information about
               the Unicode encodings for characters in the Greek and Coptic scripts."""),

  LETTERS_ENGLISH_EN(locale("en"),
      new CharRange[]{
          range('A', 'Z'), range('a', 'z')},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/English_alphabet" target="_blank">English
               Alphabet – Wikipedia</a> provided information about the English alphabet.
               
          @see <a href="https://unicode.org/charts/PDF/U0000.pdf" target="_blank">Controls and Basic Latin
               Unicode Chart – Unicode Standard v14</a> provided information about
               the Unicode encodings for characters in the Basic Latin scripts.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_SPANISH_ES(locale("es"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f',
          'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
          'N', 'n', 'Ñ', 'ñ', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's',
          'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
          'Y', 'y', 'Z', 'z',
          // Letters with acute accent
          'Á', 'á', 'É', 'é', 'Í', 'í', 'Ó', 'ó', 'Ú', 'ú', 'Ý', 'ý',
          // Letters with diaeresis
          'Ü', 'ü'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Spanish_orthography" target="_blank">Spanish
               Orthography – Wikipedia</a> provided information about the Spanish alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_FINNISH_FI(locale("fi"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
          'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'Š', 'š', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w',
          'X', 'x', 'Y', 'y', 'Z', 'z', 'Ž', 'ž', 'Å', 'å', 'Ä', 'ä', 'Ö', 'ö'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Finnish_orthography" target="_blank">Finnish
               Orthography – Wikipedia</a> provided information about the Finnish alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_FRENCH_FR(locale("fr"),
      new char[]{
          'A', 'a', 'À', 'à', 'Â', 'â', 'Æ', 'æ', 'B', 'b', 'C', 'c', 'Ç', 'ç',
          'D', 'd', 'E', 'e', 'É', 'é', 'È', 'è', 'Ê', 'ê', 'Ë', 'ë', 'F', 'f',
          'G', 'g', 'H', 'h', 'I', 'i', 'Î', 'î', 'Ï', 'ï', 'J', 'j', 'K', 'k',
          'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ô', 'ô', 'Œ', 'œ', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'Ù', 'ù', 'Û', 'û', 'Ü', 'ü',
          'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Ÿ', 'ÿ', 'Z', 'z'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/French_orthography" target="_blank">French
               Orthography – Wikipedia</a> provided information about the French alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_HINDI_HI(locale("hi"),
      new char[]{
          // Hindi vowels
          'अ', 'आ', 'इ', 'ई', 'उ', 'ऊ', 'ऋ', 'ए', 'ऐ', 'ओ', 'औ',
          // Hindi consonants
          'क', 'ख', 'ग', 'घ', 'ङ', 'च', 'छ', 'ज', 'झ', 'ञ', 'ट', 'ठ', 'ड', 'ढ', 'ण', 'त', 'थ', 'द', 'ध', 'न', 'प', 'फ', 'ब', 'भ', 'म', 'य', 'र', 'ल',
          'व', 'श', 'ष', 'स', 'ह',
          // Virama/halant
          '\u094d'},
      new CharRange[]{
          range('\u0902', '\u0903'), // Anusvara/bindu, Visarga
          range('\u093c', '\u093d'), // Nukta, Avagraha
          range('\u093e', '\u094c')  // Hindi dependant vowel signs
      },
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Hindi" target="_blank">Hindi
               – Wikipedia</a> provided information about the Hindi alphabet.
                    
          @see <a href="https://en.wikipedia.org/wiki/Devanagari" target="_blank">Devanagari
               – Wikipedia</a> provided information about the Devanagari script and what
               dependant vowel sounds and diacritic signs are used.
                    
          @see <a href="https://www.careerpower.in/hindi-alphabet-varnamala.html" target="_blank">Hindi
               Alphabet – Career Power, India</a> provided information about the Hindi alphabet and what
               dependant vowel sounds and diacritic signs are used.
               
          @see <a href="https://unicode.org/charts/PDF/U0900.pdf" target="_blank">Devanagari
               Unicode Chart – Unicode Standard v14</a> provided information about
               the Unicode encodings for characters in the Devanagari script."""),

  LETTERS_ARMENIAN_HY(locale("hy"),
      new char[]{
          'Ա', 'Բ', 'Գ', 'Դ', 'Ե', 'Զ', 'Է', 'Ը', 'Թ', 'Ժ', 'Ի', 'Լ', 'Խ', 'Ծ', 'Կ',
          'Հ', 'Ձ', 'Ղ', 'Ճ', 'Մ', 'Յ', 'Ն', 'Շ', 'Ո', 'Չ', 'Պ', 'Ջ', 'Ռ', 'Ս', 'Վ', 'Տ',
          'Ր', 'Ց', 'Ւ', 'Փ', 'Ք', 'Օ', 'Ֆ', 'ա', 'բ', 'գ', 'դ', 'ե', 'զ', 'է', 'ը', 'թ',
          'ժ', 'ի', 'լ', 'խ', 'ծ', 'կ', 'հ', 'ձ', 'ղ', 'ճ', 'մ', 'յ', 'ն', 'շ', 'ո', 'չ',
          'պ', 'ջ', 'ռ', 'ս', 'վ', 'տ', 'ր', 'ց', 'ւ', 'փ', 'ք', 'օ', 'ֆ'}),

  LETTERS_ICELANDIC_IS(locale("is"),
      new char[]{
          'A', 'a', 'Á', 'á', 'B', 'b', 'D', 'd', 'Ð', 'ð', 'E', 'e', 'É', 'é',
          'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'Í', 'í', 'J', 'j', 'K', 'k',
          'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ó', 'ó', 'P', 'p', 'R', 'r',
          'S', 's', 'T', 't', 'U', 'u', 'Ú', 'ú', 'V', 'v', 'X', 'x', 'Y', 'y',
          'Ý', 'ý', 'Z', 'z', 'Þ', 'þ', 'Æ', 'æ', 'Ö', 'ö'}),


  LETTERS_JAPANESE_JA_HIRA(locale("ja", "", "", "Hira"),
      new CharRange[]{
          // Hiragana letters
          range('\u3041', '\u3094'),
          // Small letters – should these be included – ゕ Ka, ゖ Ke
          range('\u3095', '\u3096')}),

  LETTERS_JAPANESE_JA_Kana(locale("ja", "", "", "Kana"),
      new CharRange[]{
          // Katakana letters
          range('\u30A1', '\u30FA')}),

  // For Japanese information on Kun'yomi (native reading) and On'yomi (Sino-Japanese reading) reading of
  // Kanji see:
  // - https://en.wikipedia.org/wiki/Kanji
  // – https://www.thoughtco.com/learning-japanese-4070947
  // - https://speechling.com/blog/the-best-way-to-learn-japanese-onyomi-and-kunyomi/
  //
  // CJK Han Unification
  // - https://en.wikipedia.org/wiki/Han_unification
  // - https://unicode.org/charts/PDF/U4E00.pdf CJK tables
  //
  // How to read the CJK attributes in the above link
  // - https://www.unicode.org/reports/tr38/tr38-31.html
  // - https://www.unicode.org/reports/tr38/tr38-31.html#N10106
  // -  IRG source fields:
  //    - kIRG_GSource (China and Singapore),
  //    - kIRG_HSource (Hong Kong SAR),
  //    - kIRG_JSource (Japan),
  //    - kIRG_KPSource (North Korea),
  //    - kIRG_KSource (South Korea),
  //    - kIRG_MSource (Macao SAR),
  //    - kIRG_VSource (Vietnam)
  //

  /**
   * Requires someone with intimate knowledge of the Norwegian Bokmål language to verify if the letters with diacritics (acute accent, grave accent
   * and circumflex) are considered a valid part of the language alphabet. Letters with macron, I believe, are only used in Norwegian Bokmål.
   */
  LETTERS_NORWEGIAN_BOKMÅL_NB(
      locale("nb"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
          'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
          'Y', 'y', 'Z', 'z', 'Æ', 'æ', 'Ø', 'ø', 'Å', 'å',
          // Letters with grave accent, acute accent and circumflex
          'À', 'à', 'Á', 'á', 'Â', 'â',
          'È', 'è', 'É', 'é', 'Ê', 'ê',
          'Ì', 'ì', 'Í', 'í', 'Î', 'î',
          'Ò', 'ò', 'Ó', 'ó', 'Ô', 'ô',
          'Ù', 'ù', 'Ú', 'ú', 'Û', 'û',
          // Letters with macron, I believe, are only used in Norwegian Bokmål and not in Norwegian Nynorsk
          'Ā', 'ā', 'Ē', 'ē', 'Ī', 'ī', 'Ō', 'ō', 'Ū', 'ū'}
  ),

  /**
   * Requires someone with intimate knowledge of the Norwegian Nynorsk language to verify if the letters with diacritics (acute accent, grave accent
   * and circumflex) are considered a valid part of the language alphabet.
   */
  LETTERS_NORWEGIAN_NYNORSK_NN(locale("nn"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
          'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
          'Y', 'y', 'Z', 'z', 'Æ', 'æ', 'Ø', 'ø', 'Å', 'å',
          // Letters with grave accent, acute accent and circumflex
          'À', 'à', 'Á', 'á', 'Â', 'â',
          'È', 'è', 'É', 'é', 'Ê', 'ê',
          'Ì', 'ì', 'Í', 'í', 'Î', 'î',
          'Ò', 'ò', 'Ó', 'ó', 'Ô', 'ô',
          'Ù', 'ù', 'Ú', 'ú', 'Û', 'û'}),

  /**
   * <p>Requires someone with intimate knowledge of the Dutch language to the alphabet.
   * Especially the validity of the letters that have an acute accent or diaeresis.</p>
   * <p>Sources:</p>
   * <ul>
   *   <li>Wikipedia – <a href="https://en.wikipedia.org/wiki/Dutch_orthography">Dutch
   *   Orthography</a> <small>(as at 2022-02-24)</small></li>
   * </ul>
   */
  LETTERS_DUTCH_NL(locale("nl"),
      new CharRange[]{
          range('A', 'Z'), range('a', 'z')},
      new char[]{
          // Letters with acute accent and diaeresis
          'Á', 'á', 'Ä', 'ä',
          'É', 'é', 'Ë', 'ë',
          'Í', 'í', 'Ï', 'ï',
          'Ó', 'ó', 'Ö', 'ö',
          'Ú', 'ú', 'Ü', 'ü'}),

  LETTERS_DUTCH_BELGIUM_NL(locale("nl", "BE"),
      new CharRange[]{
          range('A', 'Z'), range('a', 'z')}),

  LETTERS_PORTUGUESE_PT(locale("pt"),
      new char[]{
          'A', 'a', 'á', 'â', 'ã', 'à', 'B', 'b', 'C', 'c', 'ç', 'D', 'd', 'E', 'e', 'é', 'ê',
          'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'í', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
          'N', 'n', 'O', 'o', 'ó', 'ô', 'õ', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
          'U', 'u', 'ú', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'}),

  LETTERS_SWEDISH_SV(locale("sv"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
          'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
          'Y', 'y', 'Z', 'z', 'Å', 'å', 'Ä', 'ä', 'Ö', 'ö'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Swedish_orthography" target="_blank">Swedish
               Orthography – Wikipedia</a> provided information about the Swedish alphabet and what
               diacritics are supported.
               
          @see <a href="https://learningswedish.se/courses/1/pages/the-alphabet" target="_blank">Swedish
               Alphabet– Swedish Institute</a> provided by the Svenska institutet (Swedish 
               Institute) as part of their Learning Swedish course.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_VIETNAMESE_VI(locale("vi"),
      new char[]{
          'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú',
          'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú',
          'ý', 'Ỳ', 'Ỹ', 'ỳ', 'ỹ', 'Ỷ', 'ỷ', 'Ỵ', 'ỵ', 'ự', 'Ự', 'ử', 'Ử', 'ữ', 'Ữ', 'ừ',
          'Ừ', 'ứ', 'Ứ', 'ư', 'Ư', 'ụ', 'Ụ', 'ủ', 'Ủ', 'ũ', 'Ũ', 'ợ', 'Ợ', 'ở', 'Ở', 'ỡ',
          'Ỡ', 'ờ', 'Ờ', 'ớ', 'Ớ', 'ơ', 'Ơ', 'ộ', 'Ộ', 'ổ', 'Ổ', 'ỗ', 'Ỗ', 'ồ', 'Ồ', 'ố',
          'Ố', 'ọ', 'Ọ', 'ỏ', 'Ỏ', 'ị', 'Ị', 'ỉ', 'Ỉ', 'ĩ', 'Ĩ', 'ệ', 'Ệ', 'ể', 'Ể', 'ễ',
          'Ễ', 'ề', 'Ề', 'ế', 'Ế', 'ẹ', 'Ẹ', 'ẻ', 'Ẻ', 'ẽ', 'Ẽ', 'ặ', 'Ặ', 'ẳ', 'Ẳ', 'ẵ',
          'Ẵ', 'ằ', 'Ằ', 'ắ', 'Ắ', 'ă', 'Ă', 'ậ', 'Ậ', 'ẩ', 'Ẩ', 'ẫ', 'Ẫ', 'ầ', 'Ầ', 'ấ',
          'Ấ', 'ạ', 'Ạ', 'ả', 'Ả', 'đ', '₫', 'Đ', 'A', 'B', 'C', 'D', 'E', 'G', 'H', 'I',
          'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'a', 'b',
          'c', 'd', 'e', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
          'u', 'v', 'x', 'y'}),

//  NUMBERS(new CharRange[]{
//      range('0', '9')}),
//
//  NUMBERS_ARABIC(new char[]{
//      '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'}),
//
//  NUMBERS_DEVANAGARI(new char[]{
//      '०', '१', '२', '३', '४', '५', '६', '७', '८', '९'}),
  ;

  public static final char DOTTED_CIRCLE_COMBINING_MARK_PLACEHOLDER = '◌';


  private final ULocale locale;
  private final UnicodeSet unicodeSet;
  private final String[] javadoc;

  LanguageData(final ULocale locale, final char[] chars, String... javadoc) {
    this(locale, chars, null, javadoc);
  }

  LanguageData(final ULocale locale, final CharRange[] charRanges, String... javadoc) {
    this(locale, null, charRanges, javadoc);
  }

  LanguageData(final ULocale locale, final CharRange[] charRanges, final char[] chars, String... javadoc) {
    this(locale, chars, charRanges, javadoc);
  }

  LanguageData(final ULocale locale, final char[] chars, final CharRange[] charRanges, String... javadoc) {
    this.locale = locale;
    this.javadoc = javadoc;
    final UnicodeSet set = new UnicodeSet();
    if (chars != null) {
      for (char ch : chars) {
        set.add(ch);
      }
    }
    if (charRanges != null) {
      for (CharRange range : charRanges) {
        set.add(range.fromCodePoint, range.toCodePoint);
      }
    }
    set.compact();
    set.freeze();
    this.unicodeSet = set;
  }

  public ULocale getLocale() {
    return locale;
  }

  public boolean hasJavadoc() {
    return javadoc != null && javadoc.length > 0 && !javadoc[0].isBlank();
  }

  public String[] getJavadoc() {
    return javadoc;
  }

  public UnicodeSet getUnicodeSet() {
    return unicodeSet;
  }

  static ULocale locale(String language) {
    return locale(language, "", "", "");
  }

  static ULocale locale(String language, String country) {
    return locale(language, country, "", "");
  }

  static ULocale locale(String language, String country, String variant) {
    return locale(language, country, variant, "");
  }

  static ULocale locale(String language, String country, String variant, String script) {
    return new ULocale.Builder()
        .setLanguage(language)
        .setRegion(country)
        .setVariant(variant)
        .setScript(script)
        .build();
  }

  static CharRange range(char fromChar, char toChar) {
    return new CharRange((int) fromChar, (int) toChar);
  }

  static CharRange range(int fromCodePoint, int toCodePoint) {
    return new CharRange(fromCodePoint, toCodePoint);
  }

  static class CharRange {

    final int fromCodePoint;
    final int toCodePoint;

    CharRange(int fromCodePoint, int toCodePoint) {
      this.fromCodePoint = fromCodePoint;
      this.toCodePoint = toCodePoint;
    }
  }
}