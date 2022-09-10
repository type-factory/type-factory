package org.typefactory.generator.letters;

import static org.typefactory.generator.letters.CodePointRange.range;
import static org.typefactory.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_AIM_JAVADOC;
import static org.typefactory.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC;
import static org.typefactory.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_INCLUDED_JAVADOC;
import static org.typefactory.generator.letters.JavadocFragments.LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC;
import static org.typefactory.generator.letters.JavadocFragments.SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES;
import static org.typefactory.generator.letters.JavadocFragments.SEE_ALSO_COMMON_ARABIC_SCRIPT_REFERENCES;
import static org.typefactory.generator.letters.Utils.loadUnicodeSetFromFile;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSet.EntryRange;
import com.ibm.icu.util.ULocale;

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


public enum LettersData {

  LETTERS_ARABIC_AR(locale("ar"),
      new char[]{
          'ء', 'آ', 'أ', 'ؤ', 'إ', 'ئ', 'ا', 'ب', 'ة', 'ت', 'ث', 'ج', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز',
          'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'ل', 'م', 'ن', 'ه', 'و', 'ى', 'ي'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      SEE_ALSO_COMMON_ARABIC_SCRIPT_REFERENCES),

  LETTERS_AZERBAIJANI_AZ_LATN(locale("az", "", "", "Latn"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'Ç', 'ç', 'D', 'd', 'E', 'e', 'Ə', 'ə', 'F', 'f', 'G',
          'g', 'Ğ', 'ğ', 'H', 'h', 'X', 'x', 'I', 'ı', 'İ', 'i', 'J', 'j', 'K', 'k', 'Q', 'q', 'L',
          'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ö', 'ö', 'P', 'p', 'R', 'r', 'S', 's', 'Ş', 'ş', 'T',
          't', 'U', 'u', 'Ü', 'ü', 'V', 'v', 'Y', 'y', 'Z', 'z'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Azerbaijani_alphabet" target="_blank">Azerbaijani alphabet
               – Wikipedia</a> provided information about the Azerbaijani alphabet and what
               diacritics are supported.
               
          @see <a href="https://azerbaijan.az/en/information/107" target="_blank">Azerbaijani language
               – Azerbaijan.az</a> provided information about the Azerbaijani language and alphabet."""),

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
      new CodePointRange[]{
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
      new CodePointRange[]{
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

  LETTERS_NEPALI_NE(locale("ne"),
      new char[]{
          // Independent Vowels
          'अ', 'आ', 'इ', 'ई', 'उ', 'ऊ', 'ए', 'ऐ', 'ओ', 'औ', 'अ', 'अ', 'ऋ', 'ॠ',
          // Dependent Vowels
          'ा', 'ि', 'ी', 'ु', 'ू', 'ृ', 'ॄ', 'ॅ', 'ॆ', 'े', 'ै', 'ॉ', 'ॊ', 'ो', 'ौ',
          // Nepali Consonants
          'क', 'ख', 'ग', 'घ', 'ङ', 'च', 'छ', 'ज', 'झ', 'ञ', 'ट', 'ठ', 'ड', 'ढ', 'ण',
          'त', 'थ', 'द', 'ध', 'न', 'प', 'फ', 'ब', 'भ', 'म', 'य', 'र', 'ल',
          'व', 'श', 'ष', 'स', 'ह',
          // Hal
          '्',
          // Chandrabindu and Shirbindu
          'ँ', 'ं',
          // Bisarga
          'ः'
      },
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      """
          Nepali is written using a subset of the Devanagari script which is a left-to-right
          syllabic writing system made by combining consonants with vowels. For example,
          combining "क" /k/ and "ि" /i/ to make the "कि" /ki/ syllable.""",
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Nepalese_scripts" target="_blank">Nepalese Scripts
               – Wikipedia</a> provided information about the Nepalese scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/Devanagari" target="_blank">Devanagari
               – Wikipedia</a> provided information about the Devanagari script and what
               dependant vowel sounds and diacritic signs are used.
                    
          @see <a href="https://nepalilanguage.org/alphabet/" target="_blank">Nepali
               Alphabet – Nepali Language Resource Center</a> provided information about the Nepalese alphabet and what
               dependant vowel sounds and diacritic signs are used.
               
          @see <a href="https://www.easynepalityping.com/nepali-alphabet" target="_blank">Nepali
               Alphabet – Easy Nepali Typing</a> provided information about the Nepalese alphabet and what
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

  LETTERS_ITALIAN_IT(locale("it"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
          'I', 'i', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r',
          'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'Z', 'z',
          // Letters with acute accent
          'É', 'é', 'Ó', 'ó',
          // Letters with grave accent
          'À', 'à', 'È', 'è', 'Ì', 'ì', 'Ò', 'ò', 'Ù', 'ù',
          // Letters with circumflex
          'Î', 'î'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      """
          <p>If you would like to extends this subset of letters with the extra letters commonly found in
             loan-words in Italian you could create your own constant:</p>
          <pre>
            public static final Subset LETTERS_ITALIAN_WITH_EXTRAS =
                LETTERS_ITALIAN_it.toBuilder()
                    .addChar('J', 'j', 'K', 'k', 'W', 'w', 'X', 'x', 'Y', 'y')
                    .build();
          </pre>
          """,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Italian_orthography" target="_blank">Italian Orthography
               – Wikipedia</a> provided information about the Italian alphabet and what
               diacritics are supported – it also highlighted which letters are not in the official alphabet
               but are often found in loan-words.

          @see <a href="https://www.thinkinitalian.com/the-italian-alphabet" target="_blank">The Italian alphabet
               – Think in Italian</a> provided information about the Italian alphabet and confirmed Wikipedia
               information about which letters are not in the official alphabet but are often found in loan-words.
               
          @see <a href="https://accademiadellacrusca.it/it/consulenza/denominazione-e-genere-delle-lettere-straniere-j-k-w-x-y/84"
               target="_blank">Denominazione e genere delle lettere straniere (J, K, W, X, Y)
               – Accademia della Crusca</a> provided information about the Italian alphabet and confirmed the official
               21 letters in the Italian Alphabet. But it also left me wondering if perhaps they were accepted letters
               in terms of official institutions and organizations when registering, for example, personal or business names.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  LETTERS_JAPANESE_JA_HIRA(locale("ja", "", "", "Hira"),
      new CodePointRange[]{
          // Hiragana letters
          range('\u3041', '\u3094'),
          // Small letters – should these be included – ゕ Ka, ゖ Ke
          range('\u3095', '\u3096')},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Hiragana" target="_blank">Hiragana
               – Wikipedia</a> provided information about the Hiragana script.
               
          @see <a href="https://unicode.org/charts/PDF/U3040.pdf" target="_blank">Hiragana
               Unicode Chart – Unicode Standard v14</a> provided information about
               the Unicode encodings for characters in the Hiragana script."""),

  LETTERS_JAPANESE_JA_KANA(locale("ja", "", "", "Kana"),
      new CodePointRange[]{
          // Katakana letters
          range('\u30A1', '\u30FA')},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Katakana" target="_blank">Katakana
               – Wikipedia</a> provided information about the Katakana script.
                    
          @see <a href="https://unicode.org/charts/PDF/U30A0.pdf" target="_blank">Katakana
               Unicode Chart – Unicode Standard v14</a> provided information about
               the Unicode encodings for characters in the Katakana script."""),

  // The unified Han (Hanzi, Kanji, Hanja) unicode "letters"
  LETTERS_JAPANESE_JA_HANI(locale("ja", "", "", "Hani"),
      CJKUtils.getCJKLetters(),
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
               – Wikipedia</a> provided information about the Kanji script and its relationship to
               the Chinese family of scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/Han_unification" target="_blank">Han
               Unification – Wikipedia</a> provided information about the unified Unicode
               Hanzi, Kanji, Hanja scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/Chinese_family_of_scripts" target="_blank">Chinese
               family of scripts – Wikipedia</a> provided information about the Chinese family of scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/List_of_CJK_fonts" target="_blank">List of CJK
               fonts – Wikipedia</a> provided information about some of the notable set of fonts for
               rendering CJK fonts.

          @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
               FAQs – Unicode Org</a> provided information about
               the Unicode Unified CJK encodings.
               
          @see <a href="doc-files/JAPANESE_ja_Hani.html" target="_blank">JAPANESE_ja_Hani</a>
               for code points in this language set."""),

  LETTERS_JAPANESE_JA_JINMEIYO(
      locale("ja", "", "", "Hani", "Jinmeiyo"),
      loadUnicodeSetFromFile("japanese-ja-hani-jinmeiyo.psv"),
//      new char[]{
//          // Jinmeiyō kanji not part of the jōyō kanji
//          '丑', '丞', '乃', '之', '乎', '也', '云', '亘', '亙', '些', '亦', '亥', '亨', '亮', '仔', '伊', '伍', '伽', '佃', '佑', '伶', '侃', '侑', '俄', '俠', '俣', '俐', '倭',
//          '俱', '倦', '倖', '偲', '傭', '儲', '允', '兎', '兜', '其', '冴', '凌', '凜', '凛', '凧', '凪', '凰', '凱', '函', '劉', '劫', '勁', '勺', '勿', '匁', '匡', '廿', '卜',
//          '卯', '卿', '厨', '厩', '叉', '叡', '叢', '叶', '只', '吾', '吞', '吻', '哉', '哨', '啄', '哩', '喬', '喧', '喰', '喋', '嘩', '嘉', '嘗', '噌', '噂', '圃', '圭', '坐',
//          '尭', '堯', '坦', '埴', '堰', '堺', '堵', '塙', '壕', '壬', '夷', '奄', '奎', '套', '娃', '姪', '姥', '娩', '嬉', '孟', '宏', '宋', '宕', '宥', '寅', '寓', '寵', '尖',
//          '尤', '屑', '峨', '峻', '崚', '嵯', '嵩', '嶺', '巌', '巖', '巫', '已', '巳', '巴', '巷', '巽', '帖', '幌', '幡', '庄', '庇', '庚', '庵', '廟', '廻', '弘', '弛', '彗',
//          '彦', '彪', '彬', '徠', '忽', '怜', '恢', '恰', '恕', '悌', '惟', '惚', '悉', '惇', '惹', '惺', '惣', '慧', '憐', '戊', '或', '戟', '托', '按', '挺', '挽', '掬', '捲',
//          '捷', '捺', '捧', '掠', '揃', '摑', '摺', '撒', '撰', '撞', '播', '撫', '擢', '孜', '敦', '斐', '斡', '斧', '斯', '於', '旭', '昂', '昊', '昏', '昌', '昴', '晏', '晃',
//          '晄', '晒', '晋', '晟', '晦', '晨', '智', '暉', '暢', '曙', '曝', '曳', '朋', '朔', '杏', '杖', '杜', '李', '杭', '杵', '杷', '枇', '柑', '柴', '柘', '柊', '柏', '柾',
//          '柚', '桧', '檜', '栞', '桔', '桂', '栖', '桐', '栗', '梧', '梓', '梢', '梛', '梯', '桶', '梶', '椛', '梁', '棲', '椋', '椀', '楯', '楚', '楕', '椿', '楠', '楓', '椰',
//          '楢', '楊', '榎', '樺', '榊', '榛', '槙', '槇', '槍', '槌', '樫', '槻', '樟', '樋', '橘', '樽', '橙', '檎', '檀', '櫂', '櫛', '櫓', '欣', '欽', '歎', '此', '殆', '毅',
//          '毘', '毬', '汀', '汝', '汐', '汲', '沌', '沓', '沫', '洸', '洲', '洵', '洛', '浩', '浬', '淵', '淳', '渚', '渚', '淀', '淋', '渥', '渾', '湘', '湊', '湛', '溢', '滉',
//          '溜', '漱', '漕', '漣', '澪', '濡', '瀕', '灘', '灸', '灼', '烏', '焰', '焚', '煌', '煤', '煉', '熙', '燕', '燎', '燦', '燭', '燿', '爾', '牒', '牟', '牡', '牽', '犀',
//          '狼', '猪', '猪', '獅', '玖', '珂', '珈', '珊', '珀', '玲', '琢', '琢', '琉', '瑛', '琥', '琶', '琵', '琳', '瑚', '瑞', '瑶', '瑳', '瓜', '瓢', '甥', '甫', '畠', '畢',
//          '疋', '疏', '皐', '皓', '眸', '瞥', '矩', '砦', '砥', '砧', '硯', '碓', '碗', '碩', '碧', '磐', '磯', '祇', '祢', '禰', '祐', '祐', '祷', '禱', '禄', '祿', '禎', '禎',
//          '禽', '禾', '秦', '秤', '稀', '稔', '稟', '稜', '穣', '穰', '穹', '穿', '窄', '窪', '窺', '竣', '竪', '竺', '竿', '笈', '笹', '笙', '笠', '筈', '筑', '箕', '箔', '篇',
//          '篠', '簞', '簾', '籾', '粥', '粟', '糊', '紘', '紗', '紐', '絃', '紬', '絆', '絢', '綺', '綜', '綴', '緋', '綾', '綸', '縞', '徽', '繫', '繡', '纂', '纏', '羚', '翔',
//          '翠', '耀', '而', '耶', '耽', '聡', '肇', '肋', '肴', '胤', '胡', '脩', '腔', '脹', '膏', '臥', '舜', '舵', '芥', '芹', '芭', '芙', '芦', '苑', '茄', '苔', '苺', '茅',
//          '茉', '茸', '茜', '莞', '荻', '莫', '莉', '菅', '菫', '菖', '萄', '菩', '萌', '萠', '萊', '菱', '葦', '葵', '萱', '葺', '萩', '董', '葡', '蓑', '蒔', '蒐', '蒼', '蒲',
//          '蒙', '蓉', '蓮', '蔭', '蔣', '蔦', '蓬', '蔓', '蕎', '蕨', '蕉', '蕃', '蕪', '薙', '蕾', '蕗', '藁', '薩', '蘇', '蘭', '蝦', '蝶', '螺', '蟬', '蟹', '蠟', '衿', '袈',
//          '袴', '裡', '裟', '裳', '襖', '訊', '訣', '註', '詢', '詫', '誼', '諏', '諄', '諒', '謂', '諺', '讃', '豹', '貰', '賑', '赳', '跨', '蹄', '蹟', '輔', '輯', '輿', '轟',
//          '辰', '辻', '迂', '迄', '辿', '迪', '迦', '這', '逞', '逗', '逢', '遥', '遙', '遁', '遼', '邑', '祁', '郁', '鄭', '酉', '醇', '醐', '醍', '醬', '釉', '釘', '釧', '銑',
//          '鋒', '鋸', '錘', '錐', '錆', '錫', '鍬', '鎧', '閃', '閏', '閤', '阿', '陀', '隈', '隼', '雀', '雁', '雛', '雫', '霞', '靖', '鞄', '鞍', '鞘', '鞠', '鞭', '頁', '頌',
//          '頗', '顚', '颯', '饗', '馨', '馴', '馳', '駕', '駿', '驍', '魁', '魯', '鮎', '鯉', '鯛', '鰯', '鱒', '鱗', '鳩', '鳶', '鳳', '鴨', '鴻', '鵜', '鵬', '鷗', '鷲', '鷺',
//          '鷹', '麒', '麟', '麿', '黎', '黛', '鼎',
//          // Traditional variants of jōyō kanji
//          '亞', '惡', '爲', '逸', '榮', '衞', '謁', '圓', '緣', '薗', '應', '櫻', '奧', '橫', '溫', '價', '禍', '悔', '海', '壞', '懷', '樂', '渴', '卷', '陷', '寬', '漢', '氣',
//          '祈', '器', '僞', '戲', '虛', '峽', '狹', '響', '曉', '勤', '謹', '駈', '勳', '薰', '惠', '揭', '鷄', '藝', '擊', '縣', '儉', '劍', '險', '圈', '檢', '顯', '驗', '嚴',
//          '廣', '恆', '黃', '國', '黑', '穀', '碎', '雜', '祉', '視', '兒', '濕', '實', '社', '者', '煮', '壽', '收', '臭', '從', '澁', '獸', '縱', '祝', '暑', '署', '緖', '諸',
//          '敍', '將', '祥', '涉', '燒', '奬', '條', '狀', '乘', '淨', '剩', '疊', '孃', '讓', '釀', '神', '眞', '寢', '愼', '盡', '粹', '醉', '穗', '瀨', '齊', '靜', '攝', '節',
//          '專', '戰', '纖', '禪', '祖', '壯', '爭', '莊', '搜', '巢', '曾', '裝', '僧', '層', '瘦', '騷', '增', '憎', '藏', '贈', '臟', '卽', '帶', '滯', '瀧', '單', '嘆', '團',
//          '彈', '晝', '鑄', '著', '廳', '徵', '聽', '懲', '鎭', '轉', '傳', '都', '嶋', '燈', '盜', '稻', '德', '突', '難', '拜', '盃', '賣', '梅', '髮', '拔', '繁', '晚', '卑',
//          '祕', '碑', '賓', '敏', '冨', '侮', '福', '拂', '佛', '勉', '步', '峯', '墨', '飜', '每', '萬', '默', '埜', '彌', '藥', '與', '搖', '樣', '謠', '來', '賴', '覽', '欄',
//          '龍', '虜', '凉', '綠', '淚', '壘', '類', '禮', '曆', '歷', '練', '鍊', '郞', '朗', '廊', '錄'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Jinmeiy%C5%8D_kanji" target="_blank">Jinmeiyō 
               kanji – Wikipedia</a> provided information about the Jinmeiyō kanji which are approved
               characters for use in personal names.

          @see <a href="https://www.moj.go.jp/MINJI/minji86.html" target="_blank">Kanji characters 
               that can be used for a child's name – Ministry of Justice</a> provided link to
               document of approved <a href="https://www.moj.go.jp/content/001131003.pdf">list of Kanji 
               for personal names</a>.

          @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
               – Wikipedia</a> provided information about the Kanji script and its relationship to
               the Chinese family of scripts.
                    
          @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
               FAQs – Unicode Org</a> provided information about
               the Unicode Unified CJK encodings.
               
          @see <a href='./doc-files/JAPANESE_ja_Hani_jinmeiyo.txt'>JAPANESE_ja_Hani_jinmeiyo</a>
               for code points in this language set."""
  ),

  LETTERS_JAPANESE_JA_JOYO(
      locale("ja", "", "", "Hani", "Joyo"),
      loadUnicodeSetFromFile("japanese-ja-hani-joyo.psv"),
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/List_of_jōyō_kanji" target="_blank">Jōyō 
               kanji – Wikipedia</a> provided information about the Jōyō kanji.

          @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
               – Wikipedia</a> provided information about the Kanji script and its relationship to
               the Chinese family of scripts.
                    
          @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
               FAQs – Unicode Org</a> provided information about
               the Unicode Unified CJK encodings.
               
          @see <a href='./doc-files/JAPANESE_ja_Hani_joyo.txt'>JAPANESE_ja_Hani_joyo</a>
               for code points in this language set."""
  ),
  LETTERS_JAPANESE_JA_JSOURCE(
      locale("ja", "", "", "Hani", "JSource"),
      CJKUtils.getJapaneseJSourceLetters(),
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      """
          <p>The Japanese characters that are this subset appear in one of the following
          Japanese national standards or lists. They have been identified
          by the Ideographic Research Group (IRG) and are designated
          in the Unicode Group Data via the
          <a href='http://www.unicode.org/reports/tr38/tr38-30.html#kIRG_JSource'><tt>kIRG_JSource</tt> property</a>.</p>
                    
          <ul>      
            <li>J0 JIS X 0208-1990</li>
            <li>J1 JIS X 0212-1990</li>
            <li>J4 JIS X 0213:2004 level-4</li>
            <li>J3 JIS X 0213:2004 level-3</li>
            <li>J3A JIS X 0213:2004 level-3 addendum from JIS X 0213:2000 level-3</li>
            <li>J13 JIS X 0213:2004 level-3 characters replacing J1 characters</li>
            <li>J13A JIS X 0213:2004 level-3 character addendum from JIS X 0213:2000 level-3 replacing J1 characters</li>
            <li>J14 JIS X 0213:2004 level-4 characters replacing J1 characters</li>
            <li>JA Unified Japanese IT Vendors Contemporary Ideographs, 1993</li>
            <li>JA3 JIS X 0213:2004 level-3 characters replacing JA characters</li>
            <li>JA4 JIS X 0213:2004 level-4 characters replacing JA characters</li>
            <li>JARIB Association of Radio Industries and Businesses (ARIB) ARIB STD-B24 Version 5.1, March 14 2007</li>
            <li>JH Hanyo-Denshi Program (汎用電子情報交換環境整備プログラム), 2002-2009</li>
            <li>JK Japanese KOKUJI Collection</li>
            <li>JMJ Moji Joho Kiban Project (文字情報基盤整備事業)</li>
          </ul>""",
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
               – Wikipedia</a> provided information about the Kanji script and its relationship to
               the Chinese family of scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/Han_unification" target="_blank">Han
               Unification – Wikipedia</a> provided information about the unified Unicode
               Hanzi, Kanji, Hanja scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/Chinese_family_of_scripts" target="_blank">Chinese
               family of scripts – Wikipedia</a> provided information about the Chinese family of scripts.
                    
          @see <a href="https://en.wikipedia.org/wiki/List_of_CJK_fonts" target="_blank">List of CJK
               fonts – Wikipedia</a> provided information about some of the notable set of fonts for
               rendering CJK fonts.

          @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
               FAQs – Unicode Org</a> provided information about
               the Unicode Unified CJK encodings.
               
          @see <a href="http://www.unicode.org/reports/tr38/tr38-30.html" target="_blank">Unicode
            Han Database (UNIHAN), Proposed Update Unicode® Standard Annex #38</a>
               for information on Ideographic Research Group (IRG), and in particular the
               <tt>kIRG_JSource</tt> property.

          @see <a href='./doc-files/JAPANESE_ja_Hani_jsource.txt'>JAPANESE_ja_Hani_jsource</a>
               for code points in this language set."""),

  LETTERS_NORWEGIAN_BOKMAL_NB(
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
          'Ā', 'ā', 'Ē', 'ē', 'Ī', 'ī', 'Ō', 'ō', 'Ū', 'ū'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Norwegian_orthography" target="_blank">Norwegian
               orthography – Wikipedia</a> provided information about the Norwegian alphabet and what
               diacritics are supported.
                    
          @see <a href="https://en.wikipedia.org/wiki/Bokmål" target="_blank">Bokmål
               – Wikipedia</a> provided information about the Norwegian Bokmål alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

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
          'Ù', 'ù', 'Ú', 'ú', 'Û', 'û'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Norwegian_orthography" target="_blank">Norwegian
               orthography – Wikipedia</a> provided information about the Norwegian alphabet and what
               diacritics are supported.
                    
          @see <a href="https://en.wikipedia.org/wiki/Nynorsk" target="_blank">Nynorsk
               – Wikipedia</a> provided information about the Norwegian Nynorsk alphabet and what
               diacritics are supported.""",
      SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES),

  /**
   * <p>Requires someone with intimate knowledge of the Dutch letters to the alphabet.
   * Especially the validity of the letters that have an acute accent or diaeresis.</p>
   * <p>Sources:</p>
   * <ul>
   *   <li>Wikipedia – <a href="https://en.wikipedia.org/wiki/Dutch_orthography">Dutch
   *   Orthography</a> <small>(as at 2022-02-24)</small></li>
   * </ul>
   */
  LETTERS_DUTCH_NL(locale("nl"),
      new CodePointRange[]{
          range('A', 'Z'), range('a', 'z')},
      new char[]{
          // Letters with acute accent and diaeresis
          'Á', 'á', 'Ä', 'ä',
          'É', 'é', 'Ë', 'ë',
          'Í', 'í', 'Ï', 'ï',
          'Ó', 'ó', 'Ö', 'ö',
          'Ú', 'ú', 'Ü', 'ü'}),

  LETTERS_DUTCH_BELGIUM_NL(locale("nl", "BE"),
      new CodePointRange[]{
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
          'u', 'v', 'x', 'y'},
      LANGUAGE_ALPHABET_AIM_JAVADOC,
      LANGUAGE_ALPHABET_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC,
      LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC,
      """
          @see <a href="https://en.wikipedia.org/wiki/Vietnamese_alphabet" target="_blank">Vietnamese alphabet
               – Wikipedia</a> provided information about the Vietnamese alphabet and what
               diacritics are supported."""),

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

  LettersData(final ULocale locale, String... javadoc) {
    this(locale, (char[]) null, (CodePointRange[]) null, (UnicodeSet) null, javadoc);
  }

  LettersData(final ULocale locale, final char[] chars, String... javadoc) {
    this(locale, chars, null, null, javadoc);
  }

  LettersData(final ULocale locale, final char[] chars, final CodePointRange[] codePointRanges, String... javadoc) {
    this(locale, chars, codePointRanges, null, javadoc);
  }

  LettersData(final ULocale locale, final CodePointRange[] codePointRanges, String... javadoc) {
    this(locale, null, codePointRanges, null, javadoc);
  }

  LettersData(final ULocale locale, final CodePointRange[] codePointRanges, final char[] chars, String... javadoc) {
    this(locale, chars, codePointRanges, null, javadoc);
  }

  LettersData(final ULocale locale, final UnicodeSet unicodeSet, String... javadoc) {
    this(locale, null, null, unicodeSet, javadoc);
  }

  LettersData(final ULocale locale, final UnicodeSet unicodeSet, final char[] chars, String... javadoc) {
    this(locale, chars, null, unicodeSet, javadoc);
  }

  LettersData(
      final ULocale locale,
      final char[] chars,
      final CodePointRange[] codePointRanges,
      final UnicodeSet unicodeSet,
      final String... javadoc) {

    this.locale = locale;
    this.javadoc = javadoc;
    final UnicodeSet set = new UnicodeSet();
    if (chars != null) {
      for (char ch : chars) {
        set.add(ch);
      }
    }
    if (codePointRanges != null) {
      for (CodePointRange range : codePointRanges) {
        set.add(range.fromCodePoint, range.toCodePoint);
      }
    }
    if (unicodeSet != null) {
      for (EntryRange range : unicodeSet.ranges()) {
        set.add(range.codepoint, range.codepointEnd);
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

  public String getLocaleDisplayLanguage() {
    return locale.getDisplayLanguage();
  }

  public String getLocaleLanguageTag() {
    return locale.toLanguageTag().replaceAll("[\s_-]+", "_");
  }

  public String getTargetEnumName() {
    return String.format("%s_%s",
        getLocaleDisplayLanguage().toUpperCase().replaceAll("[\s_-]+", "_"),
        getLocaleLanguageTag());
  }

  static ULocale locale(String language) {
    return locale(language, "", "", "", "");
  }

  static ULocale locale(String language, String country) {
    return locale(language, country, "", "", "");
  }

  static ULocale locale(String language, String country, String variant) {
    return locale(language, country, variant, "", "");
  }

  static ULocale locale(String language, String country, String variant, String script) {
    return locale(language, country, variant, script, "");
  }

  static ULocale locale(String language, String country, String variant, String script, String privateUseExtension) {
    return new ULocale.Builder()
        .setLanguage(language)
        .setRegion(country)
        .setVariant(variant)
        .setScript(script)
        .setExtension(ULocale.PRIVATE_USE_EXTENSION, privateUseExtension)
        .build();
  }
}
