package org.datatypeproject;

import java.util.HashMap;
import java.util.Locale;
import javax.annotation.processing.Generated;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the data-type-language-code-generator module.",
    value = "org.datatypeproject:data-type-language-code-generator")
public class Letters {

  private static final Index index = new Index();

  private Letters() {
    // don't instantiate
  }

  private static class Index {
    private HashMap<Locale, Subset> localeSubsets = new HashMap();
    private void put(final Locale locale, final Subset subset) {
      localeSubsets.put(locale, subset);
    }
    private Subset get(final Locale locale) {
      return localeSubsets.get(locale);
    }
  }

  private static Subset registerSubset(final Locale locale, final Subset subset) {
    index.put(locale, subset);
    return subset;
  }


  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0621..063A   ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
   *    0641..064A   ف ق ك ل م ن ه و ى ي
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Arabic_script" target="_blank">Arabic
   *      Script – Wikipedia</a> provided information about the Arabic script.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Arabic_alphabet" target="_blank">Arabic
   *      Alphabet – Wikipedia</a> provided information about the Arabic alphabet.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U0600.pdf" target="_blank">Arabic
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Arabic script.
   */
  public static final Subset ARABIC_ar = registerSubset(
      new Locale("ar", "", ""),
      new RangedSubsetImpl(
        new int[]{
          0x0621_063a, //  ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_064a, //  ف ق ك ل م ن ه و ى ي
      },
      2, 36));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..0056   A B C D E F G H I J K L M N O P Q R S T U V
   *    0058..005A   X Y Z
   *    0061..0076   a b c d e f g h i j k l m n o p q r s t u v
   *    0078..007A   x y z
   *    00C7         Ç
   *    00D6         Ö
   *    00DC         Ü
   *    00E7         ç
   *    00F6         ö
   *    00FC         ü
   *    011E..011F   Ğ ğ
   *    0130..0131   İ ı
   *    015E..015F   Ş ş
   *    018F         Ə
   *    0259         ə
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Azerbaijani_alphabet" target="_blank">Azerbaijani alphabet
   *      – Wikipedia</a> provided information about the Azerbaijani alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://azerbaijan.az/en/information/107" target="_blank">Azerbaijani language
   *      – Azerbaijan.az</a> provided information about the Azerbaijani language and alphabet.
   */
  public static final Subset AZERBAIJANI_az_Latn = registerSubset(
      new Locale.Builder().setLanguage("az").setRegion("").setVariant("").setScript("Latn").build(),
      new RangedSubsetImpl(
        new char[]{
          0x41_56, //  A B C D E F G H I J K L M N O P Q R S T U V
          0x58_5a, //  X Y Z
          0x61_76, //  a b c d e f g h i j k l m n o p q r s t u v
          0x78_7a, //  x y z
          0xc7_c7, //  Ç
          0xd6_d6, //  Ö
          0xdc_dc, //  Ü
          0xe7_e7, //  ç
          0xf6_f6, //  ö
          0xfc_fc, //  ü
      },
        new int[]{
          0x011e_011f, //  Ğ ğ
          0x0130_0131, //  İ ı
          0x015e_015f, //  Ş ş
          0x018f_018f, //  Ə
          0x0259_0259, //  ə
      },
      15, 64));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C1         Á
   *    00C5..00C6   Å Æ
   *    00C9         É
   *    00CD         Í
   *    00D3         Ó
   *    00D8         Ø
   *    00DA         Ú
   *    00E1         á
   *    00E5..00E6   å æ
   *    00E9         é
   *    00ED         í
   *    00F3         ó
   *    00F8         ø
   *    00FA         ú
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Danish_orthography" target="_blank">Danish
   *      Orthography – Wikipedia</a> provided information about the Danish alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://www.danishnet.com/culture/danish-alphabet" target="_blank">Danish
   *      Alphabet – Danishnet.com</a> provided information about the Danish alphabet.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset DANISH_da = registerSubset(
      new Locale("da", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc1_c1, //  Á
          0xc5_c6, //  Å Æ
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd3_d3, //  Ó
          0xd8_d8, //  Ø
          0xda_da, //  Ú
          0xe1_e1, //  á
          0xe5_e6, //  å æ
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf3_f3, //  ó
          0xf8_f8, //  ø
          0xfa_fa, //  ú
      },
      16, 68));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C4         Ä
   *    00D6         Ö
   *    00DC         Ü
   *    00DF         ß
   *    00E4         ä
   *    00F6         ö
   *    00FC         ü
   *    1E9E         ẞ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/German_orthography" target="_blank">German
   *      Orthography – Wikipedia</a> provided information about the German alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset GERMAN_de = registerSubset(
      new Locale("de", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c4, //  Ä
          0xd6_d6, //  Ö
          0xdc_dc, //  Ü
          0xdf_df, //  ß
          0xe4_e4, //  ä
          0xf6_f6, //  ö
          0xfc_fc, //  ü
      },
        new int[]{
          0x1e9e_1e9e, //  ẞ
      },
      10, 60));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0386         Ά
   *    0388..038A   Έ Ή Ί
   *    038C         Ό
   *    038E..03A1   Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
   *    03A3..03CE   Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π
   *                 ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Greek_alphabet" target="_blank">Greek
   *      Alphabet – Wikipedia</a> provided information about the Greek alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U0370.pdf" target="_blank">Greek and Coptic
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Greek and Coptic scripts.
   */
  public static final Subset GREEK_el = registerSubset(
      new Locale("el", "", ""),
      new RangedSubsetImpl(
        new int[]{
          0x0386_0386, //  Ά
          0x0388_038a, //  Έ Ή Ί
          0x038c_038c, //  Ό
          0x038e_03a1, //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
          0x03a3_03ce, //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π
                       //  ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
      },
      5, 69));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/English_alphabet" target="_blank">English
   *      Alphabet – Wikipedia</a> provided information about the English alphabet.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf" target="_blank">Controls and Basic Latin
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Basic Latin scripts.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset ENGLISH_en = registerSubset(
      new Locale("en", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      },
      2, 52));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C1         Á
   *    00C9         É
   *    00CD         Í
   *    00D1         Ñ
   *    00D3         Ó
   *    00DA         Ú
   *    00DC..00DD   Ü Ý
   *    00E1         á
   *    00E9         é
   *    00ED         í
   *    00F1         ñ
   *    00F3         ó
   *    00FA         ú
   *    00FC..00FD   ü ý
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Spanish_orthography" target="_blank">Spanish
   *      Orthography – Wikipedia</a> provided information about the Spanish alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset SPANISH_es = registerSubset(
      new Locale("es", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc1_c1, //  Á
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd1_d1, //  Ñ
          0xd3_d3, //  Ó
          0xda_da, //  Ú
          0xdc_dd, //  Ü Ý
          0xe1_e1, //  á
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf1_f1, //  ñ
          0xf3_f3, //  ó
          0xfa_fa, //  ú
          0xfc_fd, //  ü ý
      },
      16, 68));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C4..00C5   Ä Å
   *    00D6         Ö
   *    00E4..00E5   ä å
   *    00F6         ö
   *    0160..0161   Š š
   *    017D..017E   Ž ž
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Finnish_orthography" target="_blank">Finnish
   *      Orthography – Wikipedia</a> provided information about the Finnish alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset FINNISH_fi = registerSubset(
      new Locale("fi", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c5, //  Ä Å
          0xd6_d6, //  Ö
          0xe4_e5, //  ä å
          0xf6_f6, //  ö
      },
        new int[]{
          0x0160_0161, //  Š š
          0x017d_017e, //  Ž ž
      },
      8, 62));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C0         À
   *    00C2         Â
   *    00C6..00CB   Æ Ç È É Ê Ë
   *    00CE..00CF   Î Ï
   *    00D4         Ô
   *    00D9         Ù
   *    00DB..00DC   Û Ü
   *    00E0         à
   *    00E2         â
   *    00E6..00EB   æ ç è é ê ë
   *    00EE..00EF   î ï
   *    00F4         ô
   *    00F9         ù
   *    00FB..00FC   û ü
   *    00FF         ÿ
   *    0152..0153   Œ œ
   *    0178         Ÿ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/French_orthography" target="_blank">French
   *      Orthography – Wikipedia</a> provided information about the French alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset FRENCH_fr = registerSubset(
      new Locale("fr", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc0_c0, //  À
          0xc2_c2, //  Â
          0xc6_cb, //  Æ Ç È É Ê Ë
          0xce_cf, //  Î Ï
          0xd4_d4, //  Ô
          0xd9_d9, //  Ù
          0xdb_dc, //  Û Ü
          0xe0_e0, //  à
          0xe2_e2, //  â
          0xe6_eb, //  æ ç è é ê ë
          0xee_ef, //  î ï
          0xf4_f4, //  ô
          0xf9_f9, //  ù
          0xfb_fc, //  û ü
          0xff_ff, //  ÿ
      },
        new int[]{
          0x0152_0153, //  Œ œ
          0x0178_0178, //  Ÿ
      },
      19, 84));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0902..0903   ं ः
   *    0905..090B   अ आ इ ई उ ऊ ऋ
   *    090F..0910   ए ऐ
   *    0913..0928   ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
   *    092A..0930   प फ ब भ म य र
   *    0932         ल
   *    0935..0939   व श ष स ह
   *    093C..094D   ़ ऽ ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Hindi" target="_blank">Hindi
   *      – Wikipedia</a> provided information about the Hindi alphabet.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Devanagari" target="_blank">Devanagari
   *      – Wikipedia</a> provided information about the Devanagari script and what
   *      dependant vowel sounds and diacritic signs are used.
   * 
   * @see <a href="https://www.careerpower.in/hindi-alphabet-varnamala.html" target="_blank">Hindi
   *      Alphabet – Career Power, India</a> provided information about the Hindi alphabet and what
   *      dependant vowel sounds and diacritic signs are used.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U0900.pdf" target="_blank">Devanagari
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Devanagari script.
   */
  public static final Subset HINDI_hi = registerSubset(
      new Locale("hi", "", ""),
      new RangedSubsetImpl(
        new int[]{
          0x0902_0903, //  ं ः
          0x0905_090b, //  अ आ इ ई उ ऊ ऋ
          0x090f_0910, //  ए ऐ
          0x0913_0928, //  ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0932, //  ल
          0x0935_0939, //  व श ष स ह
          0x093c_094d, //  ़ ऽ ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
      },
      8, 64));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * Nepali is written using a subset of the Devanagari script which is a left-to-right
   * syllabic writing system made by combining consonants with vowels. For example,
   * combining "क" /k/ and "ि" /i/ to make the "कि" /ki/ syllable.
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0901..0903   ँ ं ः
   *    0905..090B   अ आ इ ई उ ऊ ऋ
   *    090F..0910   ए ऐ
   *    0913..0928   ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
   *    092A..0930   प फ ब भ म य र
   *    0932         ल
   *    0935..0939   व श ष स ह
   *    093E..094D   ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
   *    0960         ॠ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Nepalese_scripts" target="_blank">Nepalese Scripts
   *      – Wikipedia</a> provided information about the Nepalese scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Devanagari" target="_blank">Devanagari
   *      – Wikipedia</a> provided information about the Devanagari script and what
   *      dependant vowel sounds and diacritic signs are used.
   * 
   * @see <a href="https://nepalilanguage.org/alphabet/" target="_blank">Nepali
   *      Alphabet – Nepali Language Resource Center</a> provided information about the Nepalese alphabet and what
   *      dependant vowel sounds and diacritic signs are used.
   * 
   * @see <a href="https://www.easynepalityping.com/nepali-alphabet" target="_blank">Nepali
   *      Alphabet – Easy Nepali Typing</a> provided information about the Nepalese alphabet and what
   *      dependant vowel sounds and diacritic signs are used.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U0900.pdf" target="_blank">Devanagari
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Devanagari script.
   */
  public static final Subset NEPALI_ne = registerSubset(
      new Locale("ne", "", ""),
      new RangedSubsetImpl(
        new int[]{
          0x0901_0903, //  ँ ं ः
          0x0905_090b, //  अ आ इ ई उ ऊ ऋ
          0x090f_0910, //  ए ऐ
          0x0913_0928, //  ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0932, //  ल
          0x0935_0939, //  व श ष स ह
          0x093e_094d, //  ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
          0x0960_0960, //  ॠ
      },
      9, 64));
  public static final Subset ARMENIAN_hy = registerSubset(
      new Locale("hy", "", ""),
      new RangedSubsetImpl(
        new int[]{
          0x0531_0556, //  Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ
                       //  Տ Ր Ց Ւ Փ Ք Օ Ֆ
          0x0561_0586, //  ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ
                       //  տ ր ց ւ փ ք օ ֆ
      },
      2, 76));
  public static final Subset ICELANDIC_is = registerSubset(
      new Locale("is", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_42, //  A B
          0x44_50, //  D E F G H I J K L M N O P
          0x52_56, //  R S T U V
          0x58_5a, //  X Y Z
          0x61_62, //  a b
          0x64_70, //  d e f g h i j k l m n o p
          0x72_76, //  r s t u v
          0x78_7a, //  x y z
          0xc1_c1, //  Á
          0xc6_c6, //  Æ
          0xc9_c9, //  É
          0xcd_cd, //  Í
          0xd0_d0, //  Ð
          0xd3_d3, //  Ó
          0xd6_d6, //  Ö
          0xda_da, //  Ú
          0xdd_de, //  Ý Þ
          0xe1_e1, //  á
          0xe6_e6, //  æ
          0xe9_e9, //  é
          0xed_ed, //  í
          0xf0_f0, //  ð
          0xf3_f3, //  ó
          0xf6_f6, //  ö
          0xfa_fa, //  ú
          0xfd_fe, //  ý þ
      },
      26, 66));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..0049   A B C D E F G H I
   *    004C..0056   L M N O P Q R S T U V
   *    005A         Z
   *    0061..0069   a b c d e f g h i
   *    006C..0076   l m n o p q r s t u v
   *    007A         z
   *    00C0         À
   *    00C8..00C9   È É
   *    00CC         Ì
   *    00CE         Î
   *    00D2..00D3   Ò Ó
   *    00D9         Ù
   *    00E0         à
   *    00E8..00E9   è é
   *    00EC         ì
   *    00EE         î
   *    00F2..00F3   ò ó
   *    00F9         ù
   * </pre>
   *
   * <p>If you would like to extends this subset of letters with the extra letters commonly found in
   *    loan-words in Italian you could create your own constant:</p>
   * <pre>
   *   public static final Subset LETTERS_ITALIAN_WITH_EXTRAS =
   *       LETTERS_ITALIAN_it.toBuilder()
   *           .addChar('J', 'j', 'K', 'k', 'W', 'w', 'X', 'x', 'Y', 'y')
   *           .build();
   * </pre>
   * 
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Italian_orthography" target="_blank">Italian Orthography
   *      – Wikipedia</a> provided information about the Italian alphabet and what
   *      diacritics are supported – it also highlighted which letters are not in the official alphabet
   *      but are often found in loan-words.
   * 
   * @see <a href="https://www.thinkinitalian.com/the-italian-alphabet" target="_blank">The Italian alphabet
   *      – Think in Italian</a> provided information about the Italian alphabet and confirmed Wikipedia
   *      information about which letters are not in the official alphabet but are often found in loan-words.
   * 
   * @see <a href="https://accademiadellacrusca.it/it/consulenza/denominazione-e-genere-delle-lettere-straniere-j-k-w-x-y/84"
   *      target="_blank">Denominazione e genere delle lettere straniere (J, K, W, X, Y)
   *      – Accademia della Crusca</a> provided information about the Italian alphabet and confirmed the official
   *      21 letters in the Italian Alphabet. But it also left me wondering if perhaps they were accepted letters
   *      in terms of official institutions and organizations when registering, for example, personal or business names.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset ITALIAN_it = registerSubset(
      new Locale("it", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_49, //  A B C D E F G H I
          0x4c_56, //  L M N O P Q R S T U V
          0x5a_5a, //  Z
          0x61_69, //  a b c d e f g h i
          0x6c_76, //  l m n o p q r s t u v
          0x7a_7a, //  z
          0xc0_c0, //  À
          0xc8_c9, //  È É
          0xcc_cc, //  Ì
          0xce_ce, //  Î
          0xd2_d3, //  Ò Ó
          0xd9_d9, //  Ù
          0xe0_e0, //  à
          0xe8_e9, //  è é
          0xec_ec, //  ì
          0xee_ee, //  î
          0xf2_f3, //  ò ó
          0xf9_f9, //  ù
      },
      18, 58));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    3041..3096   ぁ あ ぃ い ぅ う ぇ え ぉ お か が き ぎ く ぐ け げ こ ご さ ざ し じ す ず せ ぜ そ ぞ
   *                 た だ ち ぢ っ つ づ て で と ど な に ぬ ね の は ば ぱ ひ び ぴ ふ ぶ ぷ へ べ ぺ ほ ぼ
   *                 ぽ ま み む め も ゃ や ゅ ゆ ょ よ ら り る れ ろ ゎ わ ゐ ゑ を ん ゔ ゕ ゖ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Hiragana" target="_blank">Hiragana
   *      – Wikipedia</a> provided information about the Hiragana script.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U3040.pdf" target="_blank">Hiragana
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Hiragana script.
   */
  public static final Subset JAPANESE_ja_Hira = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hira").build(),
      new RangedSubsetImpl(
        new int[]{
          0x3041_3096, //  ぁ あ ぃ い ぅ う ぇ え ぉ お か が き ぎ く ぐ け げ こ ご さ ざ し じ す ず せ ぜ そ ぞ
                       //  た だ ち ぢ っ つ づ て で と ど な に ぬ ね の は ば ぱ ひ び ぴ ふ ぶ ぷ へ べ ぺ ほ ぼ
                       //  ぽ ま み む め も ゃ や ゅ ゆ ょ よ ら り る れ ろ ゎ わ ゐ ゑ を ん ゔ ゕ ゖ
      },
      1, 86));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    30A1..30FA   ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ
   *                 タ ダ チ ヂ ッ ツ ヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ バ パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ
   *                 ポ マ ミ ム メ モ ャ ヤ ュ ユ ョ ヨ ラ リ ル レ ロ ヮ ワ ヰ ヱ ヲ ン ヴ ヵ ヶ ヷ ヸ ヹ ヺ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Katakana" target="_blank">Katakana
   *      – Wikipedia</a> provided information about the Katakana script.
   * 
   * @see <a href="https://unicode.org/charts/PDF/U30A0.pdf" target="_blank">Katakana
   *      Unicode Chart – Unicode Standard v14</a> provided information about
   *      the Unicode encodings for characters in the Katakana script.
   */
  public static final Subset JAPANESE_ja_Kana = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Kana").build(),
      new RangedSubsetImpl(
        new int[]{
          0x30a1_30fa, //  ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ
                       //  タ ダ チ ヂ ッ ツ ヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ バ パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ
                       //  ポ マ ミ ム メ モ ャ ヤ ュ ユ ョ ヨ ラ リ ル レ ロ ヮ ワ ヰ ヱ ヲ ン ヴ ヵ ヶ ヷ ヸ ヹ ヺ
      },
      1, 90));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <p>There are too many unicode code-points in this set to display here. See separate 
   *    <a href='./doc-files/JAPANESE_ja_Hani.html'>JAPANESE_ja_Hani</a>
   *    file for a complete list of the unicode code-points or in this language set.</p>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
   *      – Wikipedia</a> provided information about the Kanji script and its relationship to
   *      the Chinese family of scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Han_unification" target="_blank">Han
   *      Unification – Wikipedia</a> provided information about the unified Unicode
   *      Hanzi, Kanji, Hanja scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Chinese_family_of_scripts" target="_blank">Chinese
   *      family of scripts – Wikipedia</a> provided information about the Chinese family of scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/List_of_CJK_fonts" target="_blank">List of CJK
   *      fonts – Wikipedia</a> provided information about some of the notable set of fonts for
   *      rendering CJK fonts.
   * 
   * @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
   *      FAQs – Unicode Org</a> provided information about
   *      the Unicode Unified CJK encodings.
   * 
   * @see <a href="doc-files/JAPANESE_ja_Hani.html" target="_blank">JAPANESE_ja_Hani</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hani").build(),
      new RangedSubsetImpl(
      // See Javadoc for full set of unicode code points in the following ranges.
        new int[]{
          0x3400_4dbf, //  㐀 㐁 㐂 㐃 㐄 㐅 㐆 㐇 㐈 㐉 㐊 㐋 㐌 㐍 㐎 㐏 㐐 㐑 㐒 㐓 㐔 ...
          0x4e00_9fff, //  一 丁 丂 七 丄 丅 丆 万 丈 三 上 下 丌 不 与 丏 丐 丑 丒 专 且 ...
          0xf900_fa6d, //  豈 更 車 賈 滑 串 句 龜 龜 契 金 喇 奈 懶 癩 羅 蘿 螺 裸 邏 樂 ...
          0xfa70_fad9, //  並 况 全 侀 充 冀 勇 勺 喝 啕 喙 嗢 塚 墳 奄 奔 婢 嬨 廒 廙 彩 ...
      },
      // See Javadoc for full set of unicode code points in the following ranges.
        new long[]{
          0x00020000_0002a6dfL, //  𠀀 𠀁 𠀂 𠀃 𠀄 𠀅 𠀆 𠀇 𠀈 𠀉 𠀊 𠀋 𠀌 𠀍 𠀎 𠀏 𠀐 𠀑 𠀒 𠀓 𠀔 ...
          0x0002a700_0002b738L, //  𪜀 𪜁 𪜂 𪜃 𪜄 𪜅 𪜆 𪜇 𪜈 𪜉 𪜊 𪜋 𪜌 𪜍 𪜎 𪜏 𪜐 𪜑 𪜒 𪜓 𪜔 ...
          0x0002b740_0002b81dL, //  𫝀 𫝁 𫝂 𫝃 𫝄 𫝅 𫝆 𫝇 𫝈 𫝉 𫝊 𫝋 𫝌 𫝍 𫝎 𫝏 𫝐 𫝑 𫝒 𫝓 𫝔 ...
          0x0002b820_0002cea1L, //  𫠠 𫠡 𫠢 𫠣 𫠤 𫠥 𫠦 𫠧 𫠨 𫠩 𫠪 𫠫 𫠬 𫠭 𫠮 𫠯 𫠰 𫠱 𫠲 𫠳 𫠴 ...
          0x0002ceb0_0002ebe0L, //  𬺰 𬺱 𬺲 𬺳 𬺴 𬺵 𬺶 𬺷 𬺸 𬺹 𬺺 𬺻 𬺼 𬺽 𬺾 𬺿 𬻀 𬻁 𬻂 𬻃 𬻄 ...
          0x00030000_0003134aL, //  𰀀 𰀁 𰀂 𰀃 𰀄 𰀅 𰀆 𰀇 𰀈 𰀉 𰀊 𰀋 𰀌 𰀍 𰀎 𰀏 𰀐 𰀑 𰀒 𰀓 𰀔 ...
      },
      10, 93325));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    4E11         丑
   *    4E1E         丞
   *    4E43         乃
   *    4E4B         之
   *    4E4E         乎
   *    4E58         乘
   *    4E5F         也
   *    4E91         云
   *    4E98..4E99   亘 亙
   *    4E9B         些
   *    4E9E         亞
   *    4EA5..4EA6   亥 亦
   *    4EA8         亨
   *    4EAE         亮
   *    4ED4         仔
   *    4F0A         伊
   *    4F0D         伍
   *    4F36         伶
   *    4F3D         伽
   *    4F43         佃
   *    4F51         佑
   *    4F5B         佛
   *    4F83         侃
   *    4F86         來
   *    4F91         侑
   *    4FAE         侮
   *    4FC4         俄
   *    4FD0         俐
   *    4FE0         俠
   *    4FE3         俣
   *    4FF1         俱
   *    5016         倖
   *    5026         倦
   *    502D         倭
   *    5072         偲
   *    50AD         傭
   *    50B3         傳
   *    50DE         僞
   *    50E7         僧
   *    50F9         價
   *    5109         儉
   *    5132         儲
   *    5141         允
   *    514E         兎
   *    5152         兒
   *    515C         兜
   *    5176         其
   *    51A8         冨
   *    51B4         冴
   *    51C9         凉
   *    51CC         凌
   *    51DB..51DC   凛 凜
   *    51E7         凧
   *    51EA         凪
   *    51F0..51F1   凰 凱
   *    51FD         函
   *    5269         剩
   *    5289         劉
   *    528D         劍
   *    52AB         劫
   *    52C1         勁
   *    52C9         勉
   *    52E4         勤
   *    52F3         勳
   *    52FA         勺
   *    52FF         勿
   *    5301         匁
   *    5321         匡
   *    5351         卑
   *    535C         卜
   *    536F         卯
   *    5377         卷
   *    537D         卽
   *    537F         卿
   *    53A8..53A9   厨 厩
   *    53C9         叉
   *    53E1..53E2   叡 叢
   *    53EA         只
   *    53F6         叶
   *    541E         吞
   *    543B         吻
   *    543E         吾
   *    54C9         哉
   *    54E8..54E9   哨 哩
   *    5544         啄
   *    558B         喋
   *    55A7         喧
   *    55AC         喬
   *    55AE         單
   *    55B0         喰
   *    5606         嘆
   *    5609         嘉
   *    5617         嘗
   *    5629         嘩
   *    5642         噂
   *    564C         噌
   *    5668         器
   *    56B4         嚴
   *    5703         圃
   *    5708         圈
   *    570B         國
   *    5713         圓
   *    5718         團
   *    572D         圭
   *    5750         坐
   *    5766         坦
   *    57DC         埜
   *    57F4         埴
   *    582F..5830   堯 堰
   *    5835         堵
   *    583A         堺
   *    5859         塙
   *    589E         增
   *    58A8         墨
   *    58D5         壕
   *    58D8         壘
   *    58DE         壞
   *    58EC         壬
   *    58EF         壯
   *    58FD         壽
   *    5937         夷
   *    5944         奄
   *    594E         奎
   *    5957         套
   *    5967         奧
   *    596C         奬
   *    59E5         姥
   *    59EA         姪
   *    5A03         娃
   *    5A29         娩
   *    5B09         嬉
   *    5B43         孃
   *    5B5C         孜
   *    5B5F         孟
   *    5B8B         宋
   *    5B8F         宏
   *    5B95         宕
   *    5BA5         宥
   *    5BC5         寅
   *    5BD3         寓
   *    5BE2         寢
   *    5BE6         實
   *    5BEC         寬
   *    5BF5         寵
   *    5C07..5C08   將 專
   *    5C16         尖
   *    5C24         尤
   *    5C2D         尭
   *    5C51         屑
   *    5C64         層
   *    5CE8         峨
   *    5CEF         峯
   *    5CFB         峻
   *    5CFD         峽
   *    5D1A         崚
   *    5D69         嵩
   *    5D6F         嵯
   *    5D8B         嶋
   *    5DBA         嶺
   *    5DCC         巌
   *    5DD6         巖
   *    5DE2         巢
   *    5DEB         巫
   *    5DF2..5DF4   已 巳 巴
   *    5DF7         巷
   *    5DFD         巽
   *    5E16         帖
   *    5E36         帶
   *    5E4C         幌
   *    5E61         幡
   *    5E84         庄
   *    5E87         庇
   *    5E9A         庚
   *    5EB5         庵
   *    5ECA         廊
   *    5EDF         廟
   *    5EE3         廣
   *    5EF3         廳
   *    5EFB         廻
   *    5EFF         廿
   *    5F18         弘
   *    5F1B         弛
   *    5F48         彈
   *    5F4C         彌
   *    5F57         彗
   *    5F66         彦
   *    5F6A         彪
   *    5F6C         彬
   *    5F9E         從
   *    5FA0         徠
   *    5FB5         徵
   *    5FB7         德
   *    5FBD         徽
   *    5FFD         忽
   *    601C         怜
   *    6046         恆
   *    6055         恕
   *    6062         恢
   *    6070         恰
   *    6089         悉
   *    608C         悌
   *    60C7         惇
   *    60DA         惚
   *    60DF..60E1   惟 惠 惡
   *    60E3         惣
   *    60F9..60FA   惹 惺
   *    613C         愼
   *    6167         慧
   *    618E         憎
   *    6190         憐
   *    61C9         應
   *    61F2         懲
   *    61F7         懷
   *    620A         戊
   *    6216         或
   *    621F         戟
   *    6230         戰
   *    6232         戲
   *    6258         托
   *    62C2         拂
   *    62D4         拔
   *    62DC         拜
   *    6309         按
   *    633A         挺
   *    633D         挽
   *    6367         捧
   *    6372         捲
   *    6377         捷
   *    637A         捺
   *    63A0         掠
   *    63AC         掬
   *    63C3         揃
   *    63ED         揭
   *    6416         搖
   *    641C         搜
   *    6451         摑
   *    647A         摺
   *    6492         撒
   *    649E         撞
   *    64AB         撫
   *    64AD         播
   *    64B0         撰
   *    64CA         擊
   *    64E2         擢
   *    651D         攝
   *    6536         收
   *    654D         敍
   *    654F         敏
   *    6566         敦
   *    6590         斐
   *    65A1         斡
   *    65A7         斧
   *    65AF         斯
   *    65BC         於
   *    65ED         旭
   *    6602         昂
   *    660A         昊
   *    660C         昌
   *    660F         昏
   *    6634         昴
   *    6643..6644   晃 晄
   *    664B         晋
   *    664F         晏
   *    6652         晒
   *    665A         晚
   *    665D         晝
   *    665F         晟
   *    6666         晦
   *    6668         晨
   *    667A         智
   *    6689         暉
   *    6691         暑
   *    66A2         暢
   *    66C6         曆
   *    66C9         曉
   *    66D9         曙
   *    66DD         曝
   *    66F3         曳
   *    66FE         曾
   *    670B         朋
   *    6714         朔
   *    6717         朗
   *    674E..674F   李 杏
   *    6756         杖
   *    675C         杜
   *    676D         杭
   *    6775         杵
   *    6777         杷
   *    6787         枇
   *    67CA         柊
   *    67CF         柏
   *    67D1         柑
   *    67D8         柘
   *    67DA         柚
   *    67F4         柴
   *    67FE         柾
   *    6816..6817   栖 栗
   *    681E         栞
   *    6842         桂
   *    6850         桐
   *    6854         桔
   *    6867         桧
   *    6876         桶
   *    6881         梁
   *    6885         梅
   *    6893         梓
   *    689B         梛
   *    689D         條
   *    68A2         梢
   *    68A7         梧
   *    68AF         梯
   *    68B6         梶
   *    68F2         棲
   *    6900         椀
   *    690B         椋
   *    691B         椛
   *    6930         椰
   *    693F         椿
   *    694A         楊
   *    6953         楓
   *    6955         楕
   *    695A         楚
   *    6960         楠
   *    6962         楢
   *    696F         楯
   *    698A         榊
   *    698E         榎
   *    699B         榛
   *    69AE         榮
   *    69C7         槇
   *    69CC..69CD   槌 槍
   *    69D9         槙
   *    69FB         槻
   *    6A02         樂
   *    6A0B         樋
   *    6A1F         樟
   *    6A23         樣
   *    6A2B         樫
   *    6A3A         樺
   *    6A3D         樽
   *    6A58..6A59   橘 橙
   *    6A6B         橫
   *    6A80         檀
   *    6A8E         檎
   *    6A9C         檜
   *    6AA2         檢
   *    6AC2         櫂
   *    6AD3         櫓
   *    6ADB         櫛
   *    6AFB         櫻
   *    6B04         欄
   *    6B23         欣
   *    6B3D         欽
   *    6B4E         歎
   *    6B64..6B65   此 步
   *    6B77         歷
   *    6B86         殆
   *    6BC5         毅
   *    6BCF         每
   *    6BD8         毘
   *    6BEC         毬
   *    6C23         氣
   *    6C40         汀
   *    6C50         汐
   *    6C5D         汝
   *    6C72         汲
   *    6C8C         沌
   *    6C93         沓
   *    6CAB         沫
   *    6D1B         洛
   *    6D32         洲
   *    6D35         洵
   *    6D38         洸
   *    6D69         浩
   *    6D6C         浬
   *    6D77         海
   *    6D89         涉
   *    6DC0         淀
   *    6DCB         淋
   *    6DDA         淚
   *    6DE8         淨
   *    6DF3         淳
   *    6DF5         淵
   *    6E1A         渚
   *    6E25         渥
   *    6E34         渴
   *    6E3E         渾
   *    6E4A         湊
   *    6E58         湘
   *    6E5B         湛
   *    6E9C         溜
   *    6EA2         溢
   *    6EAB         溫
   *    6EC9         滉
   *    6EEF         滯
   *    6F15         漕
   *    6F22..6F23   漢 漣
   *    6F31         漱
   *    6F81         澁
   *    6FAA         澪
   *    6FD5         濕
   *    6FE1         濡
   *    7015         瀕
   *    7027..7028   瀧 瀨
   *    7058         灘
   *    7078         灸
   *    707C         灼
   *    70CF         烏
   *    711A         焚
   *    7130         焰
   *    7149         煉
   *    714C         煌
   *    7164         煤
   *    716E         煮
   *    7199         熙
   *    71C8         燈
   *    71CE         燎
   *    71D2         燒
   *    71D5         燕
   *    71E6         燦
   *    71ED         燭
   *    71FF         燿
   *    722D         爭
   *    7232         爲
   *    723E         爾
   *    7252         牒
   *    725F         牟
   *    7261         牡
   *    727D         牽
   *    7280         犀
   *    72C0         狀
   *    72F9         狹
   *    72FC         狼
   *    732A         猪
   *    7345         獅
   *    7378         獸
   *    7396         玖
   *    73B2         玲
   *    73C0         珀
   *    73C2         珂
   *    73C8         珈
   *    73CA         珊
   *    7409         琉
   *    7422         琢
   *    7425         琥
   *    7433         琳
   *    7435..7436   琵 琶
   *    745A..745B   瑚 瑛
   *    745E         瑞
   *    7473         瑳
   *    7476         瑶
   *    74DC         瓜
   *    74E2         瓢
   *    7525         甥
   *    752B         甫
   *    7560         畠
   *    7562         畢
   *    758A..758B   疊 疋
   *    758F         疏
   *    7626         瘦
   *    7690         皐
   *    7693         皓
   *    76C3         盃
   *    76DC         盜
   *    76E1         盡
   *    771E         眞
   *    7738         眸
   *    77A5         瞥
   *    77E9         矩
   *    7825..7827   砥 砦 砧
   *    786F         硯
   *    788E         碎
   *    7891         碑
   *    7893         碓
   *    7897         碗
   *    78A7         碧
   *    78A9         碩
   *    78D0         磐
   *    78EF         磯
   *    793E         社
   *    7941         祁
   *    7947..7949   祇 祈 祉
   *    7950         祐
   *    7955..7956   祕 祖
   *    795D..795E   祝 神
   *    7962         祢
   *    7965         祥
   *    7977         祷
   *    797F         祿
   *    7984         禄
   *    798E..798F   禎 福
   *    79AA         禪
   *    79AE         禮
   *    79B0..79B1   禰 禱
   *    79BD..79BE   禽 禾
   *    79E4         秤
   *    79E6         秦
   *    7A00         稀
   *    7A14         稔
   *    7A1C         稜
   *    7A1F         稟
   *    7A3B         稻
   *    7A40         穀
   *    7A57         穗
   *    7A63         穣
   *    7A70         穰
   *    7A79         穹
   *    7A7F         穿
   *    7A81         突
   *    7A84         窄
   *    7AAA         窪
   *    7ABA         窺
   *    7AE3         竣
   *    7AEA         竪
   *    7AFA         竺
   *    7AFF         竿
   *    7B08         笈
   *    7B19         笙
   *    7B20         笠
   *    7B39         笹
   *    7B48         筈
   *    7B51         筑
   *    7B94..7B95   箔 箕
   *    7BC0         節
   *    7BC7         篇
   *    7BE0         篠
   *    7C1E         簞
   *    7C3E         簾
   *    7C7E         籾
   *    7C9F         粟
   *    7CA5         粥
   *    7CB9         粹
   *    7CCA         糊
   *    7D10         紐
   *    7D17..7D18   紗 紘
   *    7D2C         紬
   *    7D43         絃
   *    7D46         絆
   *    7D62         絢
   *    7D9C         綜
   *    7DA0         綠
   *    7DB4         綴
   *    7DB8         綸
   *    7DBA         綺
   *    7DBE         綾
   *    7DCB         緋
   *    7DD6         緖
   *    7DE3         緣
   *    7DF4         練
   *    7E1E         縞
   *    7E23         縣
   *    7E31         縱
   *    7E41         繁
   *    7E61         繡
   *    7E6B         繫
   *    7E82         纂
   *    7E8F         纏
   *    7E96         纖
   *    7F72         署
   *    7F9A         羚
   *    7FD4         翔
   *    7FE0         翠
   *    8000         耀
   *    8005         者
   *    800C         而
   *    8036         耶
   *    803D         耽
   *    8061         聡
   *    807D         聽
   *    8087         肇
   *    808B         肋
   *    80B4         肴
   *    80E1         胡
   *    80E4         胤
   *    8129         脩
   *    8139         脹
   *    8154         腔
   *    818F         膏
   *    81DF         臟
   *    81E5         臥
   *    81ED         臭
   *    8207         與
   *    821C         舜
   *    8235         舵
   *    8299         芙
   *    82A5..82A6   芥 芦
   *    82AD         芭
   *    82B9         芹
   *    82D1         苑
   *    82D4         苔
   *    82FA         苺
   *    8304..8305   茄 茅
   *    8309         茉
   *    831C         茜
   *    8338         茸
   *    837B         荻
   *    8389..838A   莉 莊
   *    839E         莞
   *    83AB         莫
   *    83C5         菅
   *    83D6         菖
   *    83E9         菩
   *    83EB         菫
   *    83F1         菱
   *    8404         萄
   *    840A         萊
   *    840C         萌
   *    8420         萠
   *    8429         萩
   *    842C         萬
   *    8431         萱
   *    8457         著
   *    8461         葡
   *    8463         董
   *    8466         葦
   *    8475         葵
   *    847A         葺
   *    8490         蒐
   *    8494         蒔
   *    8499         蒙
   *    84B2         蒲
   *    84BC         蒼
   *    84C9         蓉
   *    84D1         蓑
   *    84EC         蓬
   *    84EE         蓮
   *    8513         蔓
   *    8523         蔣
   *    8526         蔦
   *    852D         蔭
   *    8543         蕃
   *    8549         蕉
   *    854E         蕎
   *    8557         蕗
   *    8568         蕨
   *    856A         蕪
   *    857E         蕾
   *    8597         薗
   *    8599         薙
   *    85A9         薩
   *    85B0         薰
   *    85C1         藁
   *    85CF         藏
   *    85DD         藝
   *    85E5         藥
   *    8607         蘇
   *    862D         蘭
   *    865B..865C   虛 虜
   *    8766         蝦
   *    8776         蝶
   *    87BA         螺
   *    87EC         蟬
   *    87F9         蟹
   *    881F         蠟
   *    885E         衞
   *    887F         衿
   *    8888         袈
   *    88B4         袴
   *    88DD         裝
   *    88DF         裟
   *    88E1         裡
   *    88F3         裳
   *    8956         襖
   *    8996         視
   *    89BD         覽
   *    8A0A         訊
   *    8A23         訣
   *    8A3B         註
   *    8A62         詢
   *    8A6B         詫
   *    8ABC         誼
   *    8AC4         諄
   *    8ACF         諏
   *    8AD2         諒
   *    8AF8         諸
   *    8AFA         諺
   *    8B02         謂
   *    8B20         謠
   *    8B39         謹
   *    8B83         讃
   *    8B93         讓
   *    8C79         豹
   *    8CB0         貰
   *    8CD1         賑
   *    8CD3         賓
   *    8CE3         賣
   *    8CF4         賴
   *    8D08         贈
   *    8D73         赳
   *    8DE8         跨
   *    8E44         蹄
   *    8E5F         蹟
   *    8F14         輔
   *    8F2F         輯
   *    8F3F         輿
   *    8F49         轉
   *    8F5F         轟
   *    8FB0         辰
   *    8FBB         辻
   *    8FBF         辿
   *    8FC2         迂
   *    8FC4         迄
   *    8FE6         迦
   *    8FEA         迪
   *    9017         逗
   *    9019         這
   *    901E         逞
   *    9022         逢
   *    9041         遁
   *    9059         遙
   *    9065         遥
   *    907C         遼
   *    9091         邑
   *    90C1         郁
   *    90DE         郞
   *    90FD         都
   *    912D         鄭
   *    9149         酉
   *    9187         醇
   *    9189         醉
   *    918D         醍
   *    9190         醐
   *    91AC         醬
   *    91C0         釀
   *    91C9         釉
   *    91D8         釘
   *    91E7         釧
   *    9291         銑
   *    92D2         鋒
   *    92F8         鋸
   *    9304         錄
   *    9306         錆
   *    9310         錐
   *    9318         錘
   *    932B         錫
   *    934A         鍊
   *    936C         鍬
   *    93A7         鎧
   *    93AD         鎭
   *    9444         鑄
   *    9583         閃
   *    958F         閏
   *    95A4         閤
   *    963F..9640   阿 陀
   *    9677         陷
   *    9688         隈
   *    96AA         險
   *    96BC         隼
   *    96C0..96C1   雀 雁
   *    96DB..96DC   雛 雜
   *    96E3         難
   *    96EB         雫
   *    971E         霞
   *    9756         靖
   *    975C         靜
   *    9784         鞄
   *    978D         鞍
   *    9798         鞘
   *    97A0         鞠
   *    97AD         鞭
   *    97FF         響
   *    9801         頁
   *    980C         頌
   *    9817         頗
   *    985A         顚
   *    985E         類
   *    986F         顯
   *    98AF         颯
   *    98DC         飜
   *    9957         饗
   *    99A8         馨
   *    99B3..99B4   馳 馴
   *    99C8         駈
   *    99D5         駕
   *    99FF         駿
   *    9A37         騷
   *    9A4D         驍
   *    9A57         驗
   *    9AEE         髮
   *    9B41         魁
   *    9B6F         魯
   *    9B8E         鮎
   *    9BC9         鯉
   *    9BDB         鯛
   *    9C2F         鰯
   *    9C52         鱒
   *    9C57         鱗
   *    9CE9         鳩
   *    9CF3         鳳
   *    9CF6         鳶
   *    9D28         鴨
   *    9D3B         鴻
   *    9D5C         鵜
   *    9D6C         鵬
   *    9DC4         鷄
   *    9DD7         鷗
   *    9DF2         鷲
   *    9DF9..9DFA   鷹 鷺
   *    9E92         麒
   *    9E9F         麟
   *    9EBF         麿
   *    9EC3         黃
   *    9ECE         黎
   *    9ED1         黑
   *    9ED8         默
   *    9EDB         黛
   *    9F0E         鼎
   *    9F4A         齊
   *    9F8D         龍
   *    FA16         猪
   *    FA3D         悔
   *    FA46         渚
   *    FA4A         琢
   *    FA4F         祐
   *    FA52..FA53   禍 禎
   *    FA62         謁
   *    FA67         逸
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Jinmeiy%C5%8D_kanji" target="_blank">Jinmeiyō
   *      kanji – Wikipedia</a> provided information about the Jinmeiyō kanji which are approved
   *      characters for use in personal names.
   * 
   * @see <a href="https://www.moj.go.jp/MINJI/minji86.html" target="_blank">Kanji characters
   *      that can be used for a child's name – Ministry of Justice</a> provided link to
   *      document of approved <a href="https://www.moj.go.jp/content/001131003.pdf">list of Kanji
   *      for personal names</a>.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
   *      – Wikipedia</a> provided information about the Kanji script and its relationship to
   *      the Chinese family of scripts.
   * 
   * @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
   *      FAQs – Unicode Org</a> provided information about
   *      the Unicode Unified CJK encodings.
   * 
   * @see <a href="doc-files/JAPANESE_ja_Hani.html" target="_blank">JAPANESE_ja_Hani</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani_jinmeiyo = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("JINMEIYO").setScript("Hani").build(),
      Letters_Japanese_ja_Hani_jinmeiyo.SUBSET);

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    3400         㐀
   *    3402         㐂
   *    3404..3406   㐄 㐅 㐆
   *    3427         㐧
   *    342A         㐪
   *    342C..3430   㐬 㐭 㐮 㐯 㐰
   *    3436         㐶
   *    343A         㐺
   *    3441..3442   㑁 㑂
   *    3445         㑅
   *    3450         㑐
   *    3452..3453   㑒 㑓
   *    3458         㑘
   *    345E         㑞
   *    3464         㑤
   *    3466..3468   㑦 㑧 㑨
   *    346A         㑪
   *    3478..3479   㑸 㑹
   *    3492         㒒
   *    34AB         㒫
   *    34B4..34B6   㒴 㒵 㒶
   *    34BC         㒼
   *    34C1         㓁
   *    34C3         㓃
   *    34C7         㓇
   *    34D7         㓗
   *    34DB..34DD   㓛 㓜 㓝
   *    34EA         㓪
   *    34EC         㓬
   *    34F2         㓲
   *    3501         㔁
   *    3513         㔓
   *    3515         㔕
   *    3517         㔗
   *    351A         㔚
   *    351C         㔜
   *    351F         㔟
   *    3526         㔦
   *    352B..352C   㔫 㔬
   *    3530..3531   㔰 㔱
   *    3533..3535   㔳 㔴 㔵
   *    353A         㔺
   *    353C         㔼
   *    353E         㔾
   *    3543         㕃
   *    3553         㕓
   *    355A..355E   㕚 㕛 㕜 㕝 㕞
   *    3563         㕣
   *    3566..3567   㕦 㕧
   *    356E..356F   㕮 㕯
   *    3575         㕵
   *    3579         㕹
   *    357D         㕽
   *    3596         㖖
   *    35A2         㖢
   *    35A6         㖦
   *    35A8         㖨
   *    35AB         㖫
   *    35B6         㖶
   *    35C1         㗁
   *    35C5..35C7   㗅 㗆 㗇
   *    35D4         㗔
   *    35D6         㗖
   *    35DA..35DB   㗚 㗛
   *    35E4         㗤
   *    35EB         㗫
   *    35F4         㗴
   *    3605         㘅
   *    361C         㘜
   *    3622         㘢
   *    362B         㘫
   *    3632         㘲
   *    3634         㘴
   *    3644         㙄
   *    364A         㙊
   *    367B         㙻
   *    3686         㚆
   *    3688..3689   㚈 㚉
   *    3691         㚑
   *    3695..3696   㚕 㚖
   *    3699         㚙
   *    36A0         㚠
   *    36BB         㚻
   *    36CF         㛏
   *    36D2         㛒
   *    36F7         㛷
   *    373E         㜾
   *    3747         㝇
   *    374B         㝋
   *    3751         㝑
   *    3758..3759   㝘 㝙
   *    375F..3762   㝟 㝠 㝡 㝢
   *    376B..376D   㝫 㝬 㝭
   *    3775         㝵
   *    3778         㝸
   *    378D         㞍
   *    37AA         㞪
   *    37AC         㞬
   *    37AE         㞮
   *    37B4         㞴
   *    37B7         㞷
   *    37BA..37BC   㞺 㞻 㞼
   *    37BF         㞿
   *    37C1         㟁
   *    37D2         㟒
   *    37DB..37DC   㟛 㟜
   *    37DF         㟟
   *    37E1..37E2   㟡 㟢
   *    37E7..37E8   㟧 㟨
   *    37F4..37F5   㟴 㟵
   *    37FB         㟻
   *    37FD         㟽
   *    3800         㠀
   *    3809         㠉
   *    380C         㠌
   *    380E         㠎
   *    3810         㠐
   *    3815..3817   㠕 㠖 㠗
   *    381D         㠝
   *    382A         㠪
   *    382F         㠯
   *    3836         㠶
   *    383C         㠼
   *    3840         㡀
   *    384C         㡌
   *    385C         㡜
   *    385E         㡞
   *    3861..3863   㡡 㡢 㡣
   *    386B..386C   㡫 㡬
   *    3873         㡳
   *    387D         㡽
   *    3888         㢈
   *    388B         㢋
   *    3891         㢑
   *    3895         㢕
   *    389C         㢜
   *    38A1..38A3   㢡 㢢 㢣
   *    38A7         㢧
   *    38B4         㢴
   *    38C0         㣀
   *    38C3         㣃
   *    38C9         㣉
   *    38D8         㣘
   *    38DE         㣞
   *    38E0         㣠
   *    38E2         㣢
   *    38E5         㣥
   *    38EA         㣪
   *    38F1..38F2   㣱 㣲
   *    38FA         㣺
   *    38FC         㣼
   *    3905         㤅
   *    3917         㤗
   *    391A         㤚
   *    3920         㤠
   *    3922         㤢
   *    3929         㤩
   *    392B         㤫
   *    392F         㤯
   *    3935         㤵
   *    393A         㤺
   *    3940..3941   㥀 㥁
   *    394C         㥌
   *    3950         㥐
   *    3960         㥠
   *    3963..3964   㥣 㥤
   *    3966         㥦
   *    3969         㥩
   *    396F         㥯
   *    3971..3973   㥱 㥲 㥳
   *    3998         㦘
   *    399B         㦛
   *    399E         㦞
   *    39A3..39A5   㦣 㦤 㦥
   *    39AE         㦮
   *    39B1..39B2   㦱 㦲
   *    39B6         㦶
   *    39BE         㦾
   *    39C8         㧈
   *    39DE         㧞
   *    39E8         㧨
   *    39FD         㧽
   *    3A0B         㨋
   *    3A17         㨗
   *    3A2F         㨯
   *    3A36         㨶
   *    3A3B         㨻
   *    3A3F         㨿
   *    3A4D         㩍
   *    3A5C         㩜
   *    3A6E         㩮
   *    3A73         㩳
   *    3A7D         㩽
   *    3A85         㪅
   *    3A89         㪉
   *    3A98         㪘
   *    3A9F         㪟
   *    3AB0         㪰
   *    3ABD         㪽
   *    3ABF..3AC0   㪿 㫀
   *    3AC4         㫄
   *    3AD0..3AD1   㫐 㫑
   *    3AD5..3AD7   㫕 㫖 㫗
   *    3ADB         㫛
   *    3ADE         㫞
   *    3AE0         㫠
   *    3AE4         㫤
   *    3AE6         㫦
   *    3AE8         㫨
   *    3AEA         㫪
   *    3AEF         㫯
   *    3AF3..3AF4   㫳 㫴
   *    3AF6..3AF7   㫶 㫷
   *    3AFC         㫼
   *    3B01         㬁
   *    3B0A         㬊
   *    3B0E         㬎
   *    3B1A         㬚
   *    3B1C..3B1D   㬜 㬝
   *    3B22         㬢
   *    3B25..3B27   㬥 㬦 㬧
   *    3B30         㬰
   *    3B35..3B36   㬵 㬶
   *    3B3C         㬼
   *    3B43         㭃
   *    3B4C         㭌
   *    3B55         㭕
   *    3B59         㭙
   *    3B5D         㭝
   *    3B6D         㭭
   *    3B74         㭴
   *    3B77..3B78   㭷 㭸
   *    3B7B         㭻
   *    3B87..3B88   㮇 㮈
   *    3B8A..3B8B   㮊 㮋
   *    3B8D         㮍
   *    3B9B         㮛
   *    3BA4         㮤
   *    3BB5..3BB6   㮵 㮶
   *    3BBB         㮻
   *    3BC3         㯃
   *    3BCD         㯍
   *    3BE2         㯢
   *    3BEC         㯬
   *    3BF0         㯰
   *    3BF3         㯳
   *    3BF8         㯸
   *    3C0F         㰏
   *    3C13         㰓
   *    3C1E         㰞
   *    3C26         㰦
   *    3C2D..3C2E   㰭 㰮
   *    3C31         㰱
   *    3C38         㰸
   *    3C45         㱅
   *    3C4B         㱋
   *    3C4F         㱏
   *    3C55         㱕
   *    3C5D         㱝
   *    3C8A         㲊
   *    3C9B         㲛
   *    3C9D         㲝
   *    3CB8         㲸
   *    3CBC         㲼
   *    3CC3         㳃
   *    3CD1..3CD2   㳑 㳒
   *    3CD6         㳖
   *    3CD9..3CDA   㳙 㳚
   *    3CDC         㳜
   *    3CDF         㳟
   *    3CF5         㳵
   *    3CFA         㳺
   *    3D00         㴀
   *    3D11         㴑
   *    3D1E         㴞
   *    3D20         㴠
   *    3D2C         㴬
   *    3D35         㴵
   *    3D4E         㵎
   *    3D52..3D53   㵒 㵓
   *    3D5D         㵝
   *    3D64         㵤
   *    3D67         㵧
   *    3D6E         㵮
   *    3D76         㵶
   *    3D7C         㵼
   *    3D93         㶓
   *    3D9A         㶚
   *    3DA6         㶦
   *    3DB5         㶵
   *    3DC0         㷀
   *    3DC9         㷉
   *    3DD4         㷔
   *    3DD6         㷖
   *    3DF1         㷱
   *    3E05         㸅
   *    3E0F         㸏
   *    3E33         㸳
   *    3E3F..3E40   㸿 㹀
   *    3E45         㹅
   *    3E5C         㹜
   *    3E60         㹠
   *    3E63         㹣
   *    3E66         㹦
   *    3E68         㹨
   *    3E72         㹲
   *    3E77         㹷
   *    3E79         㹹
   *    3E83         㺃
   *    3E8A         㺊
   *    3E94         㺔
   *    3EB2         㺲
   *    3EDA         㻚
   *    3EE1         㻡
   *    3EE8         㻨
   *    3F0B         㼋
   *    3F18         㼘
   *    3F3C         㼼
   *    3F56..3F57   㽖 㽗
   *    3F6A         㽪
   *    3F72         㽲
   *    3F75         㽵
   *    3F77         㽷
   *    3F97         㾗
   *    3FAE         㾮
   *    3FC2         㿂
   *    3FC9         㿉
   *    3FCB         㿋
   *    3FCD         㿍
   *    3FD7         㿗
   *    3FDF         㿟
   *    3FE4         㿤
   *    4034..4035   䀴 䀵
   *    4039         䀹
   *    4040         䁀
   *    4048         䁈
   *    4050..4051   䁐 䁑
   *    4058         䁘
   *    405F         䁟
   *    4071         䁱
   *    408A         䂊
   *    4093         䂓
   *    4096         䂖
   *    40A2         䂢
   *    40B2         䂲
   *    40CD         䃍
   *    40DD         䃝
   *    40EF         䃯
   *    40FE         䃾
   *    4100         䄀
   *    4102..4103   䄂 䄃
   *    4105         䄅
   *    4107         䄇
   *    4112         䄒
   *    4126         䄦
   *    412A..412B   䄪 䄫
   *    4138         䄸
   *    4145..4146   䅅 䅆
   *    4148         䅈
   *    414B         䅋
   *    414F         䅏
   *    415E         䅞
   *    4162..4163   䅢 䅣
   *    4165         䅥
   *    4181         䆁
   *    4192         䆒
   *    41B4         䆴
   *    41BF         䆿
   *    41CA..41CB   䇊 䇋
   *    41D2         䇒
   *    41E1         䇡
   *    41E6         䇦
   *    41EE         䇮
   *    41F1         䇱
   *    41F3         䇳
   *    41FF         䇿
   *    4207         䈇
   *    420E         䈎
   *    4215         䈕
   *    421E         䈞
   *    4227         䈧
   *    4234..4235   䈴 䈵
   *    4246         䉆
   *    4256         䉖
   *    425C         䉜
   *    4264         䉤
   *    427A         䉺
   *    427D         䉽
   *    4280         䊀
   *    4285         䊅
   *    4291         䊑
   *    4293         䊓
   *    4297         䊗
   *    42A2..42A3   䊢 䊣
   *    42AF         䊯
   *    42B6         䊶
   *    42C1         䋁
   *    42C6..42C7   䋆 䋇
   *    42D0         䋐
   *    42D6         䋖
   *    42DB         䋛
   *    42DD         䋝
   *    42E8         䋨
   *    4302         䌂
   *    4306..4307   䌆 䌇
   *    4313         䌓
   *    4316         䌖
   *    431E         䌞
   *    432B..432C   䌫 䌬
   *    4343         䍃
   *    434F         䍏
   *    436A         䍪
   *    43A9         䎩
   *    43CA..43CB   䏊 䏋
   *    43D5         䏕
   *    43DE         䏞
   *    43EC         䏬
   *    43EE..43F0   䏮 䏯 䏰
   *    43F7..43F8   䏷 䏸
   *    4408         䐈
   *    440C         䐌
   *    4417         䐗
   *    441C         䐜
   *    4422         䐢
   *    4424         䐤
   *    4451         䑑
   *    4453         䑓
   *    445B         䑛
   *    4467..4468   䑧 䑨
   *    446D         䑭
   *    4476         䑶
   *    447A         䑺
   *    4491         䒑
   *    4494         䒔
   *    44A2         䒢
   *    44A9         䒩
   *    44AB         䒫
   *    44B1         䒱
   *    44B3         䒳
   *    44B6         䒶
   *    44B9         䒹
   *    44BE         䒾
   *    44C1         䓁
   *    44CC         䓌
   *    44D0         䓐
   *    44D3..44D4   䓓 䓔
   *    44E6         䓦
   *    44F2         䓲
   *    44F5..44F6   䓵 䓶
   *    44FA         䓺
   *    4506         䔆
   *    4508         䔈
   *    450A         䔊
   *    450D         䔍
   *    4524..4525   䔤 䔥
   *    4535         䔵
   *    453B..453C   䔻 䔼
   *    4543         䕃
   *    454C         䕌
   *    455E         䕞
   *    456B         䕫
   *    4576         䕶
   *    457A         䕺
   *    457E         䕾
   *    4587         䖇
   *    458D..458E   䖍 䖎
   *    4593         䖓
   *    459B         䖛
   *    459D         䖝
   *    45A3         䖣
   *    45A7         䖧
   *    45B8         䖸
   *    45D9         䗙
   *    45DD         䗝
   *    45DF         䗟
   *    45E5         䗥
   *    45EA         䗪
   *    45F3         䗳
   *    4600         䘀
   *    460D         䘍
   *    460F..4610   䘏 䘐
   *    4616         䘖
   *    4618         䘘
   *    4621         䘡
   *    4631         䘱
   *    463A         䘺
   *    4641         䙁
   *    465E         䙞
   *    4665         䙥
   *    4672         䙲
   *    4674         䙴
   *    4679..467A   䙹 䙺
   *    467D         䙽
   *    4688         䚈
   *    46A1         䚡
   *    46AC         䚬
   *    46AE..46AF   䚮 䚯
   *    46B1         䚱
   *    46B8         䚸
   *    46D1         䛑
   *    46D5         䛕
   *    46E6         䛦
   *    46ED..46EE   䛭 䛮
   *    46F1         䛱
   *    470B..470C   䜋 䜌
   *    470F         䜏
   *    471F         䜟
   *    4722         䜢
   *    472C         䜬
   *    4749         䝉
   *    4764         䝤
   *    4768..4769   䝨 䝩
   *    476F         䝯
   *    4777         䝷
   *    477A         䝺
   *    477F         䝿
   *    47AF         䞯
   *    47E6         䟦
   *    47FD         䟽
   *    4801         䠁
   *    4816         䠖
   *    4834         䠴
   *    4844         䡄
   *    484E         䡎
   *    4871         䡱
   *    4889         䢉
   *    4897         䢗
   *    489E..489F   䢞 䢟
   *    48A3         䢣
   *    48B5         䢵
   *    48D0         䣐
   *    48DB         䣛
   *    48E6         䣦
   *    48E9         䣩
   *    48EC         䣬
   *    48F3..48F4   䣳 䣴
   *    490E         䤎
   *    491B         䤛
   *    491D..491E   䤝 䤞
   *    4922..4924   䤢 䤣 䤤
   *    4929         䤩
   *    492B         䤫
   *    4930..4931   䤰 䤱
   *    4937         䤷
   *    4943         䥃
   *    494D..494F   䥍 䥎 䥏
   *    4962         䥢
   *    496C         䥬
   *    496E         䥮
   *    4976         䥶
   *    4987         䦇
   *    4993         䦓
   *    499A         䦚
   *    499C         䦜
   *    499E         䦞
   *    49AD         䦭
   *    49B0         䦰
   *    49B4         䦴
   *    49BD..49BE   䦽 䦾
   *    49C4         䧄
   *    49C9         䧉
   *    49CF         䧏
   *    49D4         䧔
   *    49DE         䧞
   *    49E2         䧢
   *    49E7         䧧
   *    49F6         䧶
   *    49FA         䧺
   *    4A04         䨄
   *    4A16         䨖
   *    4A29         䨩
   *    4A2C         䨬
   *    4A4D         䩍
   *    4A57         䩗
   *    4A6B         䩫
   *    4A84         䪄
   *    4AA9         䪩
   *    4AB5         䪵
   *    4AB7..4AB8   䪷 䪸
   *    4ABC         䪼
   *    4AD3         䫓
   *    4ADD         䫝
   *    4AEB         䫫
   *    4B22         䬢
   *    4B3B         䬻
   *    4B55         䭕
   *    4B70         䭰
   *    4B75         䭵
   *    4B7F         䭿
   *    4B9E         䮞
   *    4BB2         䮲
   *    4BC2         䯂
   *    4BCA         䯊
   *    4BD2         䯒
   *    4BDB         䯛
   *    4BE8         䯨
   *    4BEB..4BEC   䯫 䯬
   *    4C07         䰇
   *    4C0C         䰌
   *    4C17         䰗
   *    4C20         䰠
   *    4C35         䰵
   *    4C3A         䰺
   *    4C4D         䱍
   *    4C70         䱰
   *    4C76         䱶
   *    4CAC         䲬
   *    4CB3         䲳
   *    4CBD..4CBE   䲽 䲾
   *    4CC4         䳄
   *    4CD1         䳑
   *    4CE1         䳡
   *    4D07         䴇
   *    4D0E         䴎
   *    4D1F         䴟
   *    4D21         䴡
   *    4D77         䵷
   *    4D91         䶑
   *    4DAC         䶬
   *    4DAF..4DB0   䶯 䶰
   *    4E00..4E05   一 丁 丂 七 丄 丅
   *    4E07..4E12   万 丈 三 上 下 丌 不 与 丏 丐 丑 丒
   *    4E14..4E19   且 丕 世 丗 丘 丙
   *    4E1E..4E1F   丞 丟
   *    4E21         両
   *    4E23..4E24   丣 两
   *    4E26         並
   *    4E28..4E32   丨 丩 个 丫 丬 中 丮 丯 丰 丱 串
   *    4E35..4E36   丵 丶
   *    4E38..4E39   丸 丹
   *    4E3B..4E3C   主 丼
   *    4E3F..4E45   丿 乀 乁 乂 乃 乄 久
   *    4E47..4E48   乇 么
   *    4E4B         之
   *    4E4D..4E4F   乍 乎 乏
   *    4E51         乑
   *    4E55..4E5A   乕 乖 乗 乘 乙 乚
   *    4E5C..4E5F   乜 九 乞 也
   *    4E62..4E63   乢 乣
   *    4E68..4E69   乨 乩
   *    4E71         乱
   *    4E73..4E75   乳 乴 乵
   *    4E79         乹
   *    4E7E..4E80   乾 乿 亀
   *    4E82         亂
   *    4E85..4E86   亅 了
   *    4E88..4E8E   予 争 亊 事 二 亍 于
   *    4E91..4E92   云 互
   *    4E94..4E99   五 井 亖 亗 亘 亙
   *    4E9B..4EA2   些 亜 亝 亞 亟 亠 亡 亢
   *    4EA4..4EA6   交 亥 亦
   *    4EA8         亨
   *    4EAB..4EB0   享 京 亭 亮 亯 亰
   *    4EB3         亳
   *    4EB6         亶
   *    4EB9..4EBC   亹 人 亻 亼
   *    4EC0..4EC4   什 仁 仂 仃 仄
   *    4EC6..4EC8   仆 仇 仈
   *    4ECA..4ECB   今 介
   *    4ECD..4ED0   仍 从 仏 仐
   *    4ED4..4EDB   仔 仕 他 仗 付 仙 仚 仛
   *    4EDD..4EE5   仝 仞 仟 仠 仡 仢 代 令 以
   *    4EE8         仨
   *    4EEB         仫
   *    4EED..4EF3   仭 仮 仯 仰 仱 仲 仳
   *    4EF5..4EF7   仵 件 价
   *    4EFB         任
   *    4EFD..4F03   份 仾 仿 伀 企 伂 伃
   *    4F08..4F12   伈 伉 伊 伋 伌 伍 伎 伏 伐 休 伒
   *    4F15..4F17   伕 伖 众
   *    4F19..4F1A   伙 会
   *    4F1C..4F1D   伜 伝
   *    4F2E..4F31   伮 伯 估 伱
   *    4F33..4F3E   伳 伴 伵 伶 伷 伸 伹 伺 伻 似 伽 伾
   *    4F40         佀
   *    4F42..4F43   佂 佃
   *    4F46..4F49   但 佇 佈 佉
   *    4F4B..4F60   佋 佌 位 低 住 佐 佑 佒 体 佔 何 佖 佗 佘 余 佚 佛 作 佝 佞 佟 你
   *    4F63..4F64   佣 佤
   *    4F69..4F6A   佩 佪
   *    4F6C         佬
   *    4F6E..4F71   佮 佯 佰 佱
   *    4F73         佳
   *    4F75..4F7F   併 佶 佷 佸 佹 佺 佻 佼 佽 佾 使
   *    4F81..4F86   侁 侂 侃 侄 侅 來
   *    4F88..4F94   侈 侉 侊 例 侌 侍 侎 侏 侐 侑 侒 侓 侔
   *    4F96..4F9B   侖 侗 侘 侙 侚 供
   *    4F9D..4FA1   依 侞 侟 侠 価
   *    4FAB         侫
   *    4FAD..4FAF   侭 侮 侯
   *    4FB2         侲
   *    4FB5..4FB7   侵 侶 侷
   *    4FB9         侹
   *    4FBB..4FC6   侻 侼 侽 侾 便 俀 俁 係 促 俄 俅 俆
   *    4FC8..4FD2   俈 俉 俊 俋 俌 俍 俎 俏 俐 俑 俒
   *    4FD4         俔
   *    4FD7..4FD8   俗 俘
   *    4FDA..4FDD   俚 俛 俜 保
   *    4FDF..4FE6   俟 俠 信 俢 俣 俤 俥 俦
   *    4FEE..4FF3   修 俯 俰 俱 俲 俳
   *    4FF5..4FF6   俵 俶
   *    4FF8         俸
   *    4FFA         俺
   *    4FFC..5002   俼 俽 俾 俿 倀 倁 倂
   *    5004..5007   倄 倅 倆 倇
   *    5009..5014   倉 倊 個 倌 倍 倎 倏 倐 們 倒 倓 倔
   *    5016..501F   倖 倗 倘 候 倚 倛 倜 倝 倞 借
   *    5021..502E   倡 倢 倣 値 倥 倦 倧 倨 倩 倪 倫 倬 倭 倮
   *    5030         倰
   *    5032..5033   倲 倳
   *    5035..5036   倵 倶
   *    5039         倹
   *    503B         倻
   *    5040..5043   偀 偁 偂 偃
   *    5045..504A   偅 偆 假 偈 偉 偊
   *    504C         偌
   *    504E..5053   偎 偏 偐 偑 偒 偓
   *    5055..5057   偕 偖 偗
   *    5059..505A   偙 做
   *    505C         停
   *    505F..5060   偟 偠
   *    5062..5063   偢 偣
   *    5065..5067   健 偦 偧
   *    506A         偪
   *    506C..506D   偬 偭
   *    5070..5072   偰 偱 偲
   *    5074..5076   側 偵 偶
   *    5078         偸
   *    507D         偽
   *    5080..5081   傀 傁
   *    5083..5086   傃 傄 傅 傆
   *    5088         傈
   *    508A         傊
   *    508D..5096   傍 傎 傏 傐 傑 傒 傓 傔 傕 傖
   *    5098..509C   傘 備 傚 傛 傜
   *    509E..50A3   傞 傟 傠 傡 傢 傣
   *    50AA         傪
   *    50AC..50AD   催 傭
   *    50AF..50B5   傯 傰 傱 傲 傳 傴 債
   *    50B7         傷
   *    50B9..50BB   傹 傺 傻
   *    50BD..50BE   傽 傾
   *    50C0         僀
   *    50C2..50C5   僂 僃 僄 僅
   *    50C7         僇
   *    50C9..50CA   僉 僊
   *    50CC..50D1   僌 働 僎 像 僐 僑
   *    50D3..50D6   僓 僔 僕 僖
   *    50D8..50DA   僘 僙 僚
   *    50DC..50DF   僜 僝 僞 僟
   *    50E1..50E9   僡 僢 僣 僤 僥 僦 僧 僨 僩
   *    50ED..50EF   僭 僮 僯
   *    50F1..50F3   僱 僲 僳
   *    50F5..50F6   僵 僶
   *    50F9..50FB   價 僺 僻
   *    50FE         僾
   *    5100..5104   儀 儁 儂 儃 億
   *    5106..5109   儆 儇 儈 儉
   *    510B..510E   儋 儌 儍 儎
   *    5110         儐
   *    5112         儒
   *    5114..511F   儔 儕 儖 儗 儘 儙 儚 儛 儜 儝 儞 償
   *    5121         儡
   *    5123         儣
   *    5127..5128   儧 儨
   *    512A         優
   *    512C..512D   儬 儭
   *    512F         儯
   *    5131..5135   儱 儲 儳 儴 儵
   *    5137..513C   儷 儸 儹 儺 儻 儼
   *    513F..5150   儿 兀 允 兂 元 兄 充 兆 兇 先 光 兊 克 兌 免 兎 兏 児
   *    5152..5155   兒 兓 兔 兕
   *    5157..5158   兗 兘
   *    515A         党
   *    515C         兜
   *    515F..5160   兟 兠
   *    5162         兢
   *    5164..5166   兤 入 兦
   *    5168..516E   全 兩 兪 八 公 六 兮
   *    5171         共
   *    5173         关
   *    5175..5178   兵 其 具 典
   *    517B..517C   养 兼
   *    517E         兾
   *    5180         冀
   *    5182..5186   冂 冃 冄 内 円
   *    5189..5193   冉 冊 冋 册 再 冎 冏 冐 冑 冒 冓
   *    5195..5199   冕 冖 冗 冘 写
   *    519D         冝
   *    51A0..51A6   冠 冡 冢 冣 冤 冥 冦
   *    51A8..51AD   冨 冩 冪 冫 冬 冭
   *    51B0..51B8   冰 冱 冲 决 冴 况 冶 冷 冸
   *    51BA         冺
   *    51BC..51BF   冼 冽 冾 冿
   *    51C2..51C6   凂 凃 凄 凅 准
   *    51C8..51CD   凈 凉 凊 凋 凌 凍
   *    51CF         减
   *    51D1..51D3   凑 凒 凓
   *    51D5..51D6   凕 凖
   *    51D8         凘
   *    51DB..51DE   凛 凜 凝 凞
   *    51E0..51E2   几 凡 凢
   *    51E5..51E7   凥 処 凧
   *    51E9..51EA   凩 凪
   *    51ED..51EE   凭 凮
   *    51F0..51FA   凰 凱 凲 凳 凴 凵 凶 凷 凸 凹 出
   *    51FD..51FE   函 凾
   *    5200..5208   刀 刁 刂 刃 刄 刅 分 切 刈
   *    520A..520B   刊 刋
   *    520E         刎
   *    5211..5218   刑 划 刓 刔 刕 刖 列 刘
   *    521D         初
   *    5222         刢
   *    5224..5225   判 別
   *    5227..522A   刧 刨 利 刪
   *    522E         刮
   *    5230..5233   到 刱 刲 刳
   *    5235..523C   刵 制 刷 券 刹 刺 刻 刼
   *    5243..5245   剃 剄 剅
   *    5247         則
   *    5249..524D   剉 削 剋 剌 前
   *    524F         剏
   *    5254..5258   剔 剕 剖 剗 剘
   *    525A..5261   剚 剛 剜 剝 剞 剟 剠 剡
   *    5263..5266   剣 剤 剥 剦
   *    5269..526A   剩 剪
   *    526C         剬
   *    526E..5275   剮 副 剰 剱 割 剳 剴 創
   *    5277..5279   剷 剸 剹
   *    527D         剽
   *    527F..5280   剿 劀
   *    5282..5285   劂 劃 劄 劅
   *    5287..528A   劇 劈 劉 劊
   *    528C..528D   劌 劍
   *    5291..5298   劑 劒 劓 劔 劕 劖 劗 劘
   *    529A..529C   劚 力 劜
   *    529F..52A0   功 加
   *    52A3..52A7   劣 劤 劥 劦 劧
   *    52A9..52AD   助 努 劫 劬 劭
   *    52AF..52B1   劯 劰 励
   *    52B4..52BE   労 劵 劶 劷 劸 効 劺 劻 劼 劽 劾
   *    52C0..52C1   勀 勁
   *    52C3..52CA   勃 勄 勅 勆 勇 勈 勉 勊
   *    52CC..52CD   勌 勍
   *    52CF..52D2   勏 勐 勑 勒
   *    52D4..52D9   勔 動 勖 勗 勘 務
   *    52DB..52EA   勛 勜 勝 勞 募 勠 勡 勢 勣 勤 勥 勦 勧 勨 勩 勪
   *    52EC         勬
   *    52F0..52FB   勰 勱 勲 勳 勴 勵 勶 勷 勸 勹 勺 勻
   *    52FE..5303   勾 勿 匀 匁 匂 匃
   *    5305..5308   包 匆 匇 匈
   *    530A..530D   匊 匋 匌 匍
   *    530F..5311   匏 匐 匑
   *    5313         匓
   *    5315..5321   匕 化 北 匘 匙 匚 匛 匜 匝 匞 匟 匠 匡
   *    5323..5325   匣 匤 匥
   *    5327..532D   匧 匨 匩 匪 匫 匬 匭
   *    532F..5333   匯 匰 匱 匲 匳
   *    5335         匵
   *    5338..5343   匸 匹 区 医 匼 匽 匾 匿 區 十 卂 千
   *    5345..534D   卅 卆 升 午 卉 半 卋 卌 卍
   *    5351..5354   卑 卒 卓 協
   *    5357..535C   南 単 卙 博 卛 卜
   *    535E         卞
   *    5360..5361   占 卡
   *    5363         卣
   *    5365..5367   卥 卦 卧
   *    5369         卩
   *    536C..5375   卬 卭 卮 卯 印 危 卲 即 却 卵
   *    5377..537B   卷 卸 卹 卺 卻
   *    537D..537F   卽 卾 卿
   *    5382..5384   厂 厃 厄
   *    5387..5388   厇 厈
   *    538E         厎
   *    5393..5394   厓 厔
   *    5396         厖
   *    5398..539A   厘 厙 厚
   *    539D         厝
   *    539F..53A1   原 厠 厡
   *    53A4..53A6   厤 厥 厦
   *    53A8..53AB   厨 厩 厪 厫
   *    53AD..53B0   厭 厮 厯 厰
   *    53B2..53B8   厲 厳 厴 厵 厶 厷 厸
   *    53BA..53BB   厺 去
   *    53BD         厽
   *    53C0         叀
   *    53C2..53C3   参 參
   *    53C5         叅
   *    53C8..53CF   又 叉 及 友 双 反 収 叏
   *    53D2..53D7   叒 叓 叔 叕 取 受
   *    53D9..53DB   叙 叚 叛
   *    53DD..53F8   叝 叞 叟 叠 叡 叢 口 古 句 另 叧 叨 叩 只 叫 召 叭 叮 可 台 叱 史 右 叴 叵 叶 号 司
   *    53FA         叺
   *    5401..5404   吁 吂 吃 各
   *    5408..5413   合 吉 吊 吋 同 名 后 吏 吐 向 吒 吓
   *    541A..541B   吚 君
   *    541D..5421   吝 吞 吟 吠 吡
   *    5424         吤
   *    5426..542F   否 吧 吨 吩 吪 含 听 吭 吮 启
   *    5431         吱
   *    5434..5436   吴 吵 吶
   *    5438..5439   吸 吹
   *    543B..543E   吻 吼 吽 吾
   *    5440         呀
   *    5442..5444   呂 呃 呄
   *    5446..544A   呆 呇 呈 呉 告
   *    544D..544F   呍 呎 呏
   *    5451         呑
   *    5455         呕
   *    545E..545F   呞 呟
   *    5462         呢
   *    5464         呤
   *    5466..546E   呦 呧 周 呩 呪 呫 呬 呭 呮
   *    5470..5471   呰 呱
   *    5473..5477   味 呴 呵 呶 呷
   *    547B..547D   呻 呼 命
   *    547F..5481   呿 咀 咁
   *    5483..5486   咃 咄 咅 咆
   *    5488..5492   咈 咉 咊 咋 和 咍 咎 咏 咐 咑 咒
   *    5495..5496   咕 咖
   *    549C         咜
   *    549F..54A2   咟 咠 咡 咢
   *    54A4..54AF   咤 咥 咦 咧 咨 咩 咪 咫 咬 咭 咮 咯
   *    54B1..54B3   咱 咲 咳
   *    54B7..54C4   咷 咸 咹 咺 咻 咼 咽 咾 咿 哀 品 哂 哃 哄
   *    54C6..54CA   哆 哇 哈 哉 哊
   *    54CD..54CE   响 哎
   *    54D8         哘
   *    54E0..54E2   哠 員 哢
   *    54E5..54E6   哥 哦
   *    54E8..54EA   哨 哩 哪
   *    54EC..54EF   哬 哭 哮 哯
   *    54F1..54F3   哱 哲 哳
   *    54F6         哶
   *    54FA         哺
   *    54FC..5501   哼 哽 哾 哿 唀 唁
   *    5504..5509   唄 唅 唆 唇 唈 唉
   *    550C..5510   唌 唍 唎 唏 唐
   *    5514..5516   唔 唕 唖
   *    552A..552B   唪 唫
   *    552E..552F   售 唯
   *    5531..5533   唱 唲 唳
   *    5535..5536   唵 唶
   *    5538..5539   唸 唹
   *    553B..553E   唻 唼 唽 唾
   *    5540..5541   啀 啁
   *    5544..5547   啄 啅 商 啇
   *    5549..554A   啉 啊
   *    554C..554D   啌 啍
   *    554F..5551   問 啐 啑
   *    5553         啓
   *    5556..5558   啖 啗 啘
   *    555A..555E   啚 啛 啜 啝 啞
   *    5560..5561   啠 啡
   *    5563..5564   啣 啤
   *    5566         啦
   *    557B..5584   啻 啼 啽 啾 啿 喀 喁 喂 喃 善
   *    5586..558B   喆 喇 喈 喉 喊 喋
   *    558E..558F   喎 喏
   *    5591..5594   喑 喒 喓 喔
   *    5597..559A   喗 喘 喙 喚
   *    559C..559F   喜 喝 喞 喟
   *    55A3..55A4   喣 喤
   *    55A7..55AE   喧 喨 喩 喪 喫 喬 喭 單
   *    55B0         喰
   *    55B2         喲
   *    55B6         営
   *    55BF         喿
   *    55C1         嗁
   *    55C3..55C7   嗃 嗄 嗅 嗆 嗇
   *    55C9         嗉
   *    55CB..55CC   嗋 嗌
   *    55CE         嗎
   *    55D1..55D4   嗑 嗒 嗓 嗔
   *    55D7..55D8   嗗 嗘
   *    55DA..55DF   嗚 嗛 嗜 嗝 嗞 嗟
   *    55E2..55E4   嗢 嗣 嗤
   *    55E9         嗩
   *    55F6..55F7   嗶 嗷
   *    55F9         嗹
   *    55FD..55FF   嗽 嗾 嗿
   *    5605..560A   嘅 嘆 嘇 嘈 嘉 嘊
   *    560D..5612   嘍 嘎 嘏 嘐 嘑 嘒
   *    5614         嘔
   *    5616..5619   嘖 嘗 嘘 嘙
   *    561B         嘛
   *    5628..5629   嘨 嘩
   *    562C         嘬
   *    562F..5639   嘯 嘰 嘱 嘲 嘳 嘴 嘵 嘶 嘷 嘸 嘹
   *    563B..563D   嘻 嘼 嘽
   *    563F..5644   嘿 噀 噁 噂 噃 噄
   *    5646..5647   噆 噇
   *    5649         噉
   *    564B..5650   噋 噌 噍 噎 噏 噐
   *    5653..5654   噓 噔
   *    565B         噛
   *    565E         噞
   *    5660..5664   噠 噡 噢 噣 噤
   *    5666         噦
   *    5668..566D   器 噩 噪 噫 噬 噭
   *    566F         噯
   *    5671..5672   噱 噲
   *    5674..5676   噴 噵 噶
   *    5678         噸
   *    567A         噺
   *    5680         嚀
   *    5684..5688   嚄 嚅 嚆 嚇 嚈
   *    568A..568C   嚊 嚋 嚌
   *    568F         嚏
   *    5694..5695   嚔 嚕
   *    5699..569A   嚙 嚚
   *    569D..56A0   嚝 嚞 嚟 嚠
   *    56A2         嚢
   *    56A5..56A9   嚥 嚦 嚧 嚨 嚩
   *    56AB..56AE   嚫 嚬 嚭 嚮
   *    56B1..56B4   嚱 嚲 嚳 嚴
   *    56B6..56B7   嚶 嚷
   *    56BC         嚼
   *    56BE         嚾
   *    56C0..56C3   囀 囁 囂 囃
   *    56C5         囅
   *    56C8..56D1   囈 囉 囊 囋 囌 囍 囎 囏 囐 囑
   *    56D3         囓
   *    56D7..56E1   囗 囘 囙 囚 四 囜 囝 回 囟 因 囡
   *    56E3..56E8   団 囤 囥 囦 囧 囨
   *    56EB         囫
   *    56ED..56EE   园 囮
   *    56F0..56F3   困 囱 囲 図
   *    56F6..56F7   囶 囷
   *    56F9..56FA   囹 固
   *    56FD         国
   *    56FF..5704   囿 圀 圁 圂 圃 圄
   *    5707..570D   圇 圈 圉 圊 國 圌 圍
   *    570F         圏
   *    5711..5713   圑 園 圓
   *    5715..5716   圕 圖
   *    5718         團
   *    571A..571D   圚 圛 圜 圝
   *    571F..572A   土 圠 圡 圢 圣 圤 圥 圦 圧 在 圩 圪
   *    572C..5730   圬 圭 圮 圯 地
   *    5733..5734   圳 圴
   *    5737..5738   圷 圸
   *    573B         圻
   *    573D..5740   圽 圾 圿 址
   *    5742         坂
   *    5745..5747   坅 坆 均
   *    574A         坊
   *    574C..5752   坌 坍 坎 坏 坐 坑 坒
   *    5761..5762   坡 坢
   *    5764..576B   坤 坥 坦 坧 坨 坩 坪 坫
   *    576D..5771   坭 坮 坯 坰 坱
   *    5773..5775   坳 坴 坵
   *    5777         坷
   *    5779..577C   坹 坺 坻 坼
   *    577E..577F   坾 坿
   *    5781..5783   垁 垂 垃
   *    5788..5789   垈 垉
   *    578B..578C   型 垌
   *    5793..5795   垓 垔 垕
   *    5797         垗
   *    5799..579A   垙 垚
   *    579C..57A4   垜 垝 垞 垟 垠 垡 垢 垣 垤
   *    57A7..57AA   垧 垨 垩 垪
   *    57AC         垬
   *    57B0         垰
   *    57B3         垳
   *    57B8         垸
   *    57BD         垽
   *    57C0         埀
   *    57C3         埃
   *    57C6..57C8   埆 埇 埈
   *    57CB..57CC   埋 埌
   *    57CE..57CF   城 埏
   *    57D2..57D7   埒 埓 埔 埕 埖 埗
   *    57DC..57E1   埜 埝 埞 域 埠 埡
   *    57E3..57E4   埣 埤
   *    57E6..57E7   埦 埧
   *    57E9         埩
   *    57ED         埭
   *    57F0         埰
   *    57F4..5800   埴 埵 埶 執 埸 培 基 埻 埼 埽 埾 埿 堀
   *    5802..5806   堂 堃 堄 堅 堆
   *    5808..580D   堈 堉 堊 堋 堌 堍
   *    5815         堕
   *    5819         堙
   *    581B         堛
   *    581D..5821   堝 堞 堟 堠 堡
   *    5824         堤
   *    5826..5827   堦 堧
   *    582A         堪
   *    582D         堭
   *    582F..5832   堯 堰 報 堲
   *    5834..5835   場 堵
   *    5839..583A   堹 堺
   *    583D         堽
   *    583F..5841   堿 塀 塁
   *    5849..584D   塉 塊 塋 塌 塍
   *    584F..5852   塏 塐 塑 塒
   *    5854..5855   塔 塕
   *    5857..585A   塗 塘 塙 塚
   *    585E..585F   塞 塟
   *    5861..5862   塡 塢
   *    5864         塤
   *    5867..5869   塧 塨 塩
   *    586B         填
   *    5870         塰
   *    5872         塲
   *    5875         塵
   *    5878..5879   塸 塹
   *    587C         塼
   *    587E..5881   塾 塿 墀 墁
   *    5883         境
   *    5885         墅
   *    5887..588D   墇 墈 墉 墊 墋 墌 墍
   *    588F..5890   墏 墐
   *    5893..5894   墓 墔
   *    5896..5897   墖 増
   *    589C..58A2   墜 墝 增 墟 墠 墡 墢
   *    58A6         墦
   *    58A8..58AB   墨 墩 墪 墫
   *    58AE         墮
   *    58B1..58B3   墱 墲 墳
   *    58B8..58BC   墸 墹 墺 墻 墼
   *    58BE         墾
   *    58C1..58C5   壁 壂 壃 壄 壅
   *    58C7..58C8   壇 壈
   *    58CA         壊
   *    58CC..58CE   壌 壍 壎
   *    58D0..58DA   壐 壑 壒 壓 壔 壕 壖 壗 壘 壙 壚
   *    58DC..58E2   壜 壝 壞 壟 壠 壡 壢
   *    58E4..58E5   壤 壥
   *    58E9         壩
   *    58EB..58EC   士 壬
   *    58EE..58F4   壮 壯 声 壱 売 壳 壴
   *    58F7         壷
   *    58F9..58FD   壹 壺 壻 壼 壽
   *    5902         夂
   *    5905..5906   夅 夆
   *    5909..590D   変 夊 夋 夌 复
   *    590F..5910   夏 夐
   *    5912..5916   夒 夓 夔 夕 外
   *    5918..591D   夘 夙 多 夛 夜 夝
   *    5921..5925   夡 夢 夣 夤 夥
   *    5927..5933   大 夨 天 太 夫 夬 夭 央 夯 夰 失 夲 夳
   *    5935..5938   夵 夶 夷 夸
   *    593D..593F   夽 夾 夿
   *    5943..5944   奃 奄
   *    5946..5949   奆 奇 奈 奉
   *    594E..5955   奎 奏 奐 契 奒 奓 奔 奕
   *    5957..595B   套 奘 奙 奚 奛
   *    595D..5963   奝 奞 奟 奠 奡 奢 奣
   *    5965         奥
   *    5967..596F   奧 奨 奩 奪 奫 奬 奭 奮 奯
   *    5972..5976   奲 女 奴 奵 奶
   *    5978..5979   奸 她
   *    597B..597D   奻 奼 好
   *    5981..5984   妁 如 妃 妄
   *    598A..598E   妊 妋 妌 妍 妎
   *    5992..5993   妒 妓
   *    5995..5997   妕 妖 妗
   *    5999         妙
   *    599B         妛
   *    599D         妝
   *    599F         妟
   *    59A3..59A5   妣 妤 妥
   *    59A7..59A8   妧 妨
   *    59AC..59B0   妬 妭 妮 妯 妰
   *    59B2..59B3   妲 妳
   *    59B7         妷
   *    59B9..59BC   妹 妺 妻 妼
   *    59BE         妾
   *    59C1         姁
   *    59C3..59C4   姃 姄
   *    59C6         姆
   *    59C8..59CB   姈 姉 姊 始
   *    59CD         姍
   *    59D0..59D4   姐 姑 姒 姓 委
   *    59D9..59DA   姙 姚
   *    59DC..59DF   姜 姝 姞 姟
   *    59E3..59E8   姣 姤 姥 姦 姧 姨
   *    59EA..59EB   姪 姫
   *    59EE..59EF   姮 姯
   *    59F1..59F2   姱 姲
   *    59F4         姴
   *    59F6..59F8   姶 姷 姸
   *    59FB         姻
   *    59FF..5A01   姿 娀 威
   *    5A03..5A04   娃 娄
   *    5A09         娉
   *    5A0C..5A0E   娌 娍 娎
   *    5A11..5A13   娑 娒 娓
   *    5A17..5A18   娗 娘
   *    5A1A         娚
   *    5A1C         娜
   *    5A1E..5A20   娞 娟 娠
   *    5A23..5A25   娣 娤 娥
   *    5A27..5A2A   娧 娨 娩 娪
   *    5A2D         娭
   *    5A2F..5A30   娯 娰
   *    5A35..5A36   娵 娶
   *    5A3C         娼
   *    5A40..5A41   婀 婁
   *    5A44..5A49   婄 婅 婆 婇 婈 婉
   *    5A4C         婌
   *    5A50         婐
   *    5A55         婕
   *    5A5A         婚
   *    5A5E         婞
   *    5A62..5A63   婢 婣
   *    5A65..5A67   婥 婦 婧
   *    5A6A         婪
   *    5A6C..5A6D   婬 婭
   *    5A77         婷
   *    5A7A..5A7B   婺 婻
   *    5A7E..5A7F   婾 婿
   *    5A84         媄
   *    5A8B         媋
   *    5A90         媐
   *    5A92..5A93   媒 媓
   *    5A96         媖
   *    5A99..5A9C   媙 媚 媛 媜
   *    5A9E..5AA0   媞 媟 媠
   *    5AA2         媢
   *    5AA7         媧
   *    5AAC         媬
   *    5AB1..5AB3   媱 媲 媳
   *    5AB5         媵
   *    5AB8         媸
   *    5ABA..5ABF   媺 媻 媼 媽 媾 媿
   *    5AC1..5AC2   嫁 嫂
   *    5AC4         嫄
   *    5AC6         嫆
   *    5AC8..5AC9   嫈 嫉
   *    5ACB..5ACC   嫋 嫌
   *    5ACF..5AD0   嫏 嫐
   *    5AD6..5AD7   嫖 嫗
   *    5ADA         嫚
   *    5ADC         嫜
   *    5AE0..5AE1   嫠 嫡
   *    5AE3         嫣
   *    5AE5..5AE6   嫥 嫦
   *    5AE9..5AEA   嫩 嫪
   *    5AEE         嫮
   *    5AF0         嫰
   *    5AF5..5AF6   嫵 嫶
   *    5AFA..5AFB   嫺 嫻
   *    5AFD         嫽
   *    5B00..5B01   嬀 嬁
   *    5B08..5B09   嬈 嬉
   *    5B0B..5B0C   嬋 嬌
   *    5B16..5B17   嬖 嬗
   *    5B19         嬙
   *    5B1B         嬛
   *    5B1D         嬝
   *    5B21..5B22   嬡 嬢
   *    5B25         嬥
   *    5B2A         嬪
   *    5B2C..5B2D   嬬 嬭
   *    5B30         嬰
   *    5B32         嬲
   *    5B34         嬴
   *    5B36         嬶
   *    5B38         嬸
   *    5B3E         嬾
   *    5B40..5B41   孀 孁
   *    5B43         孃
   *    5B45         孅
   *    5B4B..5B4C   孋 孌
   *    5B50..5B52   子 孑 孒
   *    5B54..5B58   孔 孕 孖 字 存
   *    5B5A..5B5F   孚 孛 孜 孝 孞 孟
   *    5B63..5B66   季 孤 孥 学
   *    5B68..5B69   孨 孩
   *    5B6B         孫
   *    5B6E..5B71   孮 孯 孰 孱
   *    5B73         孳
   *    5B75         孵
   *    5B78         學
   *    5B7A         孺
   *    5B7C..5B81   孼 孽 孾 孿 宀 宁
   *    5B83..5B91   它 宄 宅 宆 宇 守 安 宊 宋 完 宍 宎 宏 宐 宑
   *    5B93..5B9D   宓 宔 宕 宖 宗 官 宙 定 宛 宜 宝
   *    5B9F         実
   *    5BA2..5BA6   客 宣 室 宥 宦
   *    5BA8..5BA9   宨 宩
   *    5BAC..5BBA   宬 宭 宮 宯 宰 宱 宲 害 宴 宵 家 宷 宸 容 宺
   *    5BBC         宼
   *    5BBF..5BC7   宿 寀 寁 寂 寃 寄 寅 密 寇
   *    5BC9         寉
   *    5BCC..5BD0   富 寍 寎 寏 寐
   *    5BD2..5BD4   寒 寓 寔
   *    5BD6..5BDB   寖 寗 寘 寙 寚 寛
   *    5BDD..5BE2   寝 寞 察 寠 寡 寢
   *    5BE4..5BE9   寤 寥 實 寧 寨 審
   *    5BEB..5BEC   寫 寬
   *    5BEE..5BF1   寮 寯 寰 寱
   *    5BF3..5BF6   寳 寴 寵 寶
   *    5BF8         寸
   *    5BFA         寺
   *    5BFD..5BFF   寽 対 寿
   *    5C01..5C0F   封 専 尃 射 尅 将 將 專 尉 尊 尋 尌 對 導 小
   *    5C11..5C13   少 尒 尓
   *    5C16..5C17   尖 尗
   *    5C1A         尚
   *    5C1E..5C20   尞 尟 尠
   *    5C22..5C24   尢 尣 尤
   *    5C26         尦
   *    5C28..5C29   尨 尩
   *    5C2B..5C2E   尫 尬 尭 尮
   *    5C30..5C32   尰 就 尲
   *    5C35..5C36   尵 尶
   *    5C38..5C41   尸 尹 尺 尻 尼 尽 尾 尿 局 屁
   *    5C45..5C46   居 屆
   *    5C48         屈
   *    5C4A..5C4B   届 屋
   *    5C4D..5C51   屍 屎 屏 屐 屑
   *    5C53         屓
   *    5C55         展
   *    5C59..5C5C   屙 屚 屛 屜
   *    5C5E..5C65   属 屟 屠 屡 屢 屣 層 履
   *    5C67..5C69   屧 屨 屩
   *    5C6C..5C71   屬 屭 屮 屯 屰 山
   *    5C74..5C76   屴 屵 屶
   *    5C79..5C7D   屹 屺 屻 屼 屽
   *    5C87..5C88   岇 岈
   *    5C8A         岊
   *    5C8C         岌
   *    5C8F..5C92   岏 岐 岑 岒
   *    5C94         岔
   *    5C9D         岝
   *    5C9F..5CA3   岟 岠 岡 岢 岣
   *    5CA6..5CAD   岦 岧 岨 岩 岪 岫 岬 岭
   *    5CB1..5CB8   岱 岲 岳 岴 岵 岶 岷 岸
   *    5CBA..5CBC   岺 岻 岼
   *    5CBE         岾
   *    5CC5         峅
   *    5CC7         峇
   *    5CC9         峉
   *    5CCB         峋
   *    5CD0         峐
   *    5CD2         峒
   *    5CD7         峗
   *    5CD9         峙
   *    5CDD         峝
   *    5CE0..5CE1   峠 峡
   *    5CE8..5CEA   峨 峩 峪
   *    5CED..5CF2   峭 峮 峯 峰 峱 峲
   *    5CF4         峴
   *    5CF6         島
   *    5CFA..5CFB   峺 峻
   *    5CFD         峽
   *    5D01         崁
   *    5D06..5D07   崆 崇
   *    5D0B         崋
   *    5D0D..5D0E   崍 崎
   *    5D10..5D12   崐 崑 崒
   *    5D14..5D1B   崔 崕 崖 崗 崘 崙 崚 崛
   *    5D1D         崝
   *    5D1F..5D20   崟 崠
   *    5D22..5D24   崢 崣 崤
   *    5D26..5D27   崦 崧
   *    5D29         崩
   *    5D2B         崫
   *    5D31         崱
   *    5D34         崴
   *    5D39         崹
   *    5D3D         崽
   *    5D3F         崿
   *    5D42..5D43   嵂 嵃
   *    5D46..5D48   嵆 嵇 嵈
   *    5D4A..5D4C   嵊 嵋 嵌
   *    5D4E         嵎
   *    5D50..5D53   嵐 嵑 嵒 嵓
   *    5D55         嵕
   *    5D59         嵙
   *    5D5C         嵜
   *    5D5F..5D62   嵟 嵠 嵡 嵢
   *    5D64         嵤
   *    5D69..5D6A   嵩 嵪
   *    5D6C..5D6D   嵬 嵭
   *    5D6F..5D70   嵯 嵰
   *    5D73         嵳
   *    5D76         嵶
   *    5D79..5D7A   嵹 嵺
   *    5D7E..5D7F   嵾 嵿
   *    5D81..5D84   嶁 嶂 嶃 嶄
   *    5D87..5D88   嶇 嶈
   *    5D8A..5D8C   嶊 嶋 嶌
   *    5D90         嶐
   *    5D92..5D95   嶒 嶓 嶔 嶕
   *    5D97         嶗
   *    5D99         嶙
   *    5D9B         嶛
   *    5D9D         嶝
   *    5D9F..5DA0   嶟 嶠
   *    5DA2         嶢
   *    5DA4         嶤
   *    5DA7         嶧
   *    5DAB..5DAC   嶫 嶬
   *    5DAE         嶮
   *    5DB0         嶰
   *    5DB2         嶲
   *    5DB4         嶴
   *    5DB7..5DBA   嶷 嶸 嶹 嶺
   *    5DBC..5DBD   嶼 嶽
   *    5DC3         巃
   *    5DC7         巇
   *    5DC9         巉
   *    5DCB..5DCE   巋 巌 巍 巎
   *    5DD0..5DD3   巐 巑 巒 巓
   *    5DD6..5DD9   巖 巗 巘 巙
   *    5DDB         巛
   *    5DDD..5DDE   川 州
   *    5DE0..5DE9   巠 巡 巢 巣 巤 工 左 巧 巨 巩
   *    5DEB         巫
   *    5DEE         差
   *    5DF1..5DF5   己 已 巳 巴 巵
   *    5DF7..5DF9   巷 巸 巹
   *    5DFB         巻
   *    5DFD..5DFE   巽 巾
   *    5E00         帀
   *    5E02..5E03   市 布
   *    5E06..5E07   帆 帇
   *    5E0B..5E0D   帋 希 帍
   *    5E11..5E12   帑 帒
   *    5E14..5E16   帔 帕 帖
   *    5E18..5E1B   帘 帙 帚 帛
   *    5E1D         帝
   *    5E1F..5E20   帟 帠
   *    5E25         帥
   *    5E28         帨
   *    5E2B         師
   *    5E2D..5E30   席 帮 帯 帰
   *    5E32..5E33   帲 帳
   *    5E35..5E38   帵 帶 帷 常
   *    5E3D..5E3E   帽 帾
   *    5E40         幀
   *    5E43..5E45   幃 幄 幅
   *    5E47         幇
   *    5E49         幉
   *    5E4B..5E4C   幋 幌
   *    5E4E         幎
   *    5E50..5E51   幐 幑
   *    5E54..5E58   幔 幕 幖 幗 幘
   *    5E5B..5E5C   幛 幜
   *    5E5E..5E5F   幞 幟
   *    5E61..5E64   幡 幢 幣 幤
   *    5E68         幨
   *    5E6A..5E6E   幪 幫 幬 幭 幮
   *    5E70         幰
   *    5E72..5E81   干 平 年 幵 并 幷 幸 幹 幺 幻 幼 幽 幾 广 庀 庁
   *    5E83..5E84   広 庄
   *    5E87         庇
   *    5E8A..5E8B   床 庋
   *    5E8E..5E8F   庎 序
   *    5E95..5E97   底 庖 店
   *    5E9A         庚
   *    5E9C         府
   *    5EA0         庠
   *    5EA2         庢
   *    5EA4..5EA8   庤 庥 度 座 庨
   *    5EAA..5EAD   庪 庫 庬 庭
   *    5EB1         庱
   *    5EB3         庳
   *    5EB5..5EB9   庵 庶 康 庸 庹
   *    5EBD..5EBF   庽 庾 庿
   *    5EC1..5EC3   廁 廂 廃
   *    5EC6         廆
   *    5EC8..5ECC   廈 廉 廊 廋 廌
   *    5ECE..5ED6   廎 廏 廐 廑 廒 廓 廔 廕 廖
   *    5ED9..5EE3   廙 廚 廛 廜 廝 廞 廟 廠 廡 廢 廣
   *    5EE5         廥
   *    5EE8..5EE9   廨 廩
   *    5EEB..5EEC   廫 廬
   *    5EF0..5EF1   廰 廱
   *    5EF3..5EF4   廳 廴
   *    5EF6..5F04   延 廷 廸 廹 建 廻 廼 廽 廾 廿 开 弁 异 弃 弄
   *    5F06..5F11   弆 弇 弈 弉 弊 弋 弌 弍 弎 式 弐 弑
   *    5F13..5F19   弓 弔 引 弖 弗 弘 弙
   *    5F1B..5F1F   弛 弜 弝 弞 弟
   *    5F21..5F29   弡 弢 弣 弤 弥 弦 弧 弨 弩
   *    5F2B..5F31   弫 弬 弭 弮 弯 弰 弱
   *    5F34..5F38   弴 張 弶 強 弸
   *    5F3B..5F41   弻 弼 弽 弾 弿 彀 彁
   *    5F44..5F45   彄 彅
   *    5F47..5F48   彇 彈
   *    5F4A         彊
   *    5F4C..5F4E   彌 彍 彎
   *    5F50..5F51   彐 彑
   *    5F53..5F54   当 彔
   *    5F56..5F59   彖 彗 彘 彙
   *    5F5B..5F5D   彛 彜 彝
   *    5F60..5F64   彠 彡 形 彣 彤
   *    5F66..5F67   彦 彧
   *    5F69..5F6D   彩 彪 彫 彬 彭
   *    5F6F..5F75   彯 彰 影 彲 彳 彴 彵
   *    5F77..5F7A   彷 彸 役 彺
   *    5F7C..5F85   彼 彽 彾 彿 往 征 徂 徃 径 待
   *    5F87..5F8D   徇 很 徉 徊 律 後 徍
   *    5F8F..5F93   徏 徐 徑 徒 従
   *    5F96..5F99   徖 得 徘 徙
   *    5F9C..5F9E   徜 徝 從
   *    5FA0..5FA2   徠 御 徢
   *    5FA4         徤
   *    5FA7..5FB1   徧 徨 復 循 徫 徬 徭 微 徯 徰 徱
   *    5FB3..5FB5   徳 徴 徵
   *    5FB7..5FB9   德 徸 徹
   *    5FBC..5FBD   徼 徽
   *    5FC3..5FC5   心 忄 必
   *    5FC7..5FC9   忇 忈 忉
   *    5FCB..5FCD   忋 忌 忍
   *    5FD0..5FD4   忐 忑 忒 忓 忔
   *    5FD6..5FD9   忖 志 忘 忙
   *    5FDC..5FDE   応 忝 忞
   *    5FE0..5FE2   忠 忡 忢
   *    5FE4         忤
   *    5FE8..5FF3   忨 忩 忪 快 忬 忭 忮 忯 忰 忱 忲 忳
   *    5FF5..5FF6   念 忶
   *    5FF8         忸
   *    5FFA..5FFD   忺 忻 忼 忽
   *    5FFF         忿
   *    6007         怇
   *    600A         怊
   *    600D..6010   怍 怎 怏 怐
   *    6012..601D   怒 怓 怔 怕 怖 怗 怘 怙 怚 怛 怜 思
   *    601F..6022   怟 怠 怡 怢
   *    6024..602B   怤 急 怦 性 怨 怩 怪 怫
   *    602D         怭
   *    602F         怯
   *    6031         怱
   *    6033         怳
   *    6035         怵
   *    603A         怺
   *    6040..6043   恀 恁 恂 恃
   *    6046..604D   恆 恇 恈 恉 恊 恋 恌 恍
   *    6050..6052   恐 恑 恒
   *    6054..6057   恔 恕 恖 恗
   *    6059..605A   恙 恚
   *    605D         恝
   *    605F..6065   恟 恠 恡 恢 恣 恤 恥
   *    6067..606D   恧 恨 恩 恪 恫 恬 恭
   *    606F..6071   息 恰 恱
   *    6075         恵
   *    6077         恷
   *    607E..607F   恾 恿
   *    6081..6084   悁 悂 悃 悄
   *    6086         悆
   *    6088..608E   悈 悉 悊 悋 悌 悍 悎
   *    6091..6098   悑 悒 悓 悔 悕 悖 悗 悘
   *    609A..609B   悚 悛
   *    609D..60A0   悝 悞 悟 悠
   *    60A2..60AA   悢 患 悤 悥 悦 悧 您 悩 悪
   *    60B0..60B8   悰 悱 悲 悳 悴 悵 悶 悷 悸
   *    60BB..60BE   悻 悼 悽 悾
   *    60C2         惂
   *    60C4..60CB   惄 情 惆 惇 惈 惉 惊 惋
   *    60CE..60CF   惎 惏
   *    60D1         惑
   *    60D3..60D5   惓 惔 惕
   *    60D8..60E3   惘 惙 惚 惛 惜 惝 惞 惟 惠 惡 惢 惣
   *    60E5         惥
   *    60E7..60E8   惧 惨
   *    60EE         惮
   *    60F0..60FD   惰 惱 惲 想 惴 惵 惶 惷 惸 惹 惺 惻 惼 惽
   *    6100..6103   愀 愁 愂 愃
   *    6106..610A   愆 愇 愈 愉 愊
   *    610C..6117   愌 愍 愎 意 愐 愑 愒 愓 愔 愕 愖 愗
   *    6119..611C   愙 愚 愛 愜
   *    611E..611F   愞 感
   *    6121..6122   愡 愢
   *    6127..6128   愧 愨
   *    612A..612C   愪 愫 愬
   *    6130..6131   愰 愱
   *    6134..6137   愴 愵 愶 愷
   *    6139..613A   愹 愺
   *    613C..613F   愼 愽 愾 愿
   *    6141..6142   慁 慂
   *    6144..614E   慄 慅 慆 慇 慈 慉 慊 態 慌 慍 慎
   *    6153         慓
   *    6155         慕
   *    6158..615A   慘 慙 慚
   *    615D..6160   慝 慞 慟 慠
   *    6162..6163   慢 慣
   *    6165         慥
   *    6167..6168   慧 慨
   *    616B..616C   慫 慬
   *    616E..6178   慮 慯 慰 慱 慲 慳 慴 慵 慶 慷 慸
   *    617B..617C   慻 慼
   *    617E..6184   慾 慿 憀 憁 憂 憃 憄
   *    6187         憇
   *    618A..618B   憊 憋
   *    618D..618E   憍 憎
   *    6190..6194   憐 憑 憒 憓 憔
   *    6196..619A   憖 憗 憘 憙 憚
   *    619C..619D   憜 憝
   *    619F..61A0   憟 憠
   *    61A4..61A5   憤 憥
   *    61A7..61AE   憧 憨 憩 憪 憫 憬 憭 憮
   *    61B2         憲
   *    61B6         憶
   *    61B8..61BA   憸 憹 憺
   *    61BC         憼
   *    61BE         憾
   *    61C0..61C3   懀 懁 懂 懃
   *    61C6..61D0   懆 懇 懈 應 懊 懋 懌 懍 懎 懏 懐
   *    61D5         懕
   *    61DC..61DF   懜 懝 懞 懟
   *    61E1..61E3   懡 懢 懣
   *    61E5..61E7   懥 懦 懧
   *    61E9         懩
   *    61EC..61ED   懬 懭
   *    61EF         懯
   *    61F2         懲
   *    61F4..61F8   懴 懵 懶 懷 懸
   *    61FA         懺
   *    61FC..6201   懼 懽 懾 懿 戀 戁
   *    6203..6204   戃 戄
   *    6207..620A   戇 戈 戉 戊
   *    620C..620E   戌 戍 戎
   *    6210..6216   成 我 戒 戓 戔 戕 或
   *    621A..6223   戚 戛 戜 戝 戞 戟 戠 戡 戢 戣
   *    6226..6227   戦 戧
   *    6229..622B   戩 截 戫
   *    622E..6230   戮 戯 戰
   *    6232..6234   戲 戳 戴
   *    6238..6239   戸 戹
   *    623B         戻
   *    623D..6244   戽 戾 房 所 扁 扂 扃 扄
   *    6246..6249   扆 扇 扈 扉
   *    624B..624E   手 扌 才 扎
   *    6250..6256   扐 扑 扒 打 扔 払 扖
   *    6258         托
   *    625A..625C   扚 扛 扜
   *    625E         扞
   *    6260..6261   扠 扡
   *    6263..6264   扣 扤
   *    6268         扨
   *    626D..626F   扭 扮 扯
   *    6271         扱
   *    6273         扳
   *    6276         扶
   *    6279..6280   批 扺 扻 扼 扽 找 承 技
   *    6282..6285   抂 抃 抄 抅
   *    6289..628A   抉 把
   *    628D..6299   抍 抎 抏 抐 抑 抒 抓 抔 投 抖 抗 折 抙
   *    629B..629C   抛 抜
   *    629E         択
   *    62A6         抦
   *    62A8         抨
   *    62AB..62AC   披 抬
   *    62B1         抱
   *    62B3         抳
   *    62B5..62B7   抵 抶 抷
   *    62B9..62BF   抹 抺 抻 押 抽 抾 抿
   *    62C2         拂
   *    62C4..62CA   拄 担 拆 拇 拈 拉 拊
   *    62CC..62DD   拌 拍 拎 拏 拐 拑 拒 拓 拔 拕 拖 拗 拘 拙 拚 招 拜 拝
   *    62E0..62E1   拠 拡
   *    62EA         拪
   *    62EC..62EF   括 拭 拮 拯
   *    62F1..62F7   拱 拲 拳 拴 拵 拶 拷
   *    62FC..62FF   拼 拽 拾 拿
   *    6301..6304   持 挂 挃 挄
   *    6307..630D   指 挈 按 挊 挋 挌 挍
   *    6310..6311   挐 挑
   *    6313         挓
   *    6316         挖
   *    6318..6319   挘 挙
   *    631F         挟
   *    6327..632B   挧 挨 挩 挪 挫
   *    632D         挭
   *    632F         振
   *    6332         挲
   *    6335..6336   挵 挶
   *    6339..633F   挹 挺 挻 挼 挽 挾 挿
   *    6341..6344   捁 捂 捃 捄
   *    6346         捆
   *    6349..6350   捉 捊 捋 捌 捍 捎 捏 捐
   *    6352..6355   捒 捓 捔 捕
   *    6357..6359   捗 捘 捙
   *    635B..635C   捛 捜
   *    6365..6369   捥 捦 捧 捨 捩
   *    636B..636E   捫 捬 捭 据
   *    6371..6372   捱 捲
   *    6374..6378   捴 捵 捶 捷 捸
   *    637A..637D   捺 捻 捼 捽
   *    637F..6380   捿 掀
   *    6382..6384   掂 掃 掄
   *    6387..638A   掇 授 掉 掊
   *    638C         掌
   *    638E..6390   掎 掏 掐
   *    6392         排
   *    6394..6396   掔 掕 掖
   *    6398..639B   掘 掙 掚 掛
   *    639E..63AF   掞 掟 掠 採 探 掣 掤 接 掦 控 推 掩 措 掫 掬 掭 掮 掯
   *    63B2         掲
   *    63B4..63B5   掴 掵
   *    63BB         掻
   *    63BD..63BE   掽 掾
   *    63C0..63C1   揀 揁
   *    63C3..63C6   揃 揄 揅 揆
   *    63C8..63C9   揈 揉
   *    63CE..63D6   揎 描 提 揑 插 揓 揔 揕 揖
   *    63DA..63DC   揚 換 揜
   *    63E0..63E1   揠 握
   *    63E3         揣
   *    63E5         揥
   *    63E9..63EE   揩 揪 揫 揬 揭 揮
   *    63F2..63FA   揲 揳 援 揵 揶 揷 揸 揹 揺
   *    6406         搆
   *    6409..640A   搉 搊
   *    640D         損
   *    640F..6410   搏 搐
   *    6412..6414   搒 搓 搔
   *    6416..6418   搖 搗 搘
   *    641C         搜
   *    641E         搞
   *    6420         搠
   *    6422         搢
   *    6424..6426   搤 搥 搦
   *    6428..642A   搨 搩 搪
   *    642C..642D   搬 搭
   *    642F..6430   搯 搰
   *    6434..6436   搴 搵 搶
   *    643A         携
   *    643D..643F   搽 搾 搿
   *    6442         摂
   *    644B         摋
   *    644E..644F   摎 摏
   *    6451..6454   摑 摒 摓 摔
   *    6458         摘
   *    645A..645D   摚 摛 摜 摝
   *    645F..6461   摟 摠 摡
   *    6463         摣
   *    6467         摧
   *    6469         摩
   *    646D         摭
   *    646F         摯
   *    6473..6474   摳 摴
   *    6476         摶
   *    6478..647B   摸 摹 摺 摻
   *    647D         摽
   *    6483         撃
   *    6485         撅
   *    6487..6488   撇 撈
   *    648F..6493   撏 撐 撑 撒 撓
   *    6495         撕
   *    6498..649B   撘 撙 撚 撛
   *    649D..649F   撝 撞 撟
   *    64A1         撡
   *    64A3..64A6   撣 撤 撥 撦
   *    64A8..64A9   撨 撩
   *    64AB..64AE   撫 撬 播 撮
   *    64B0         撰
   *    64B2..64B3   撲 撳
   *    64B9         撹
   *    64BB..64BF   撻 撼 撽 撾 撿
   *    64C1..64C2   擁 擂
   *    64C4..64C5   擄 擅
   *    64C7         擇
   *    64C9..64CE   擉 擊 擋 擌 操 擎
   *    64D0..64D2   擐 擑 擒
   *    64D4..64D5   擔 擕
   *    64D7..64D8   擗 擘
   *    64DA         據
   *    64E0..64E7   擠 擡 擢 擣 擤 擥 擦 擧
   *    64E9..64EA   擩 擪
   *    64EC..64ED   擬 擭
   *    64EF..64F2   擯 擰 擱 擲
   *    64F4..64F7   擴 擵 擶 擷
   *    64FA..64FB   擺 擻
   *    64FD..6501   擽 擾 擿 攀 攁
   *    6504..6505   攄 攅
   *    6508..650A   攈 攉 攊
   *    650F         攏
   *    6513..6514   攓 攔
   *    6516         攖
   *    6518..6519   攘 攙
   *    651B..651F   攛 攜 攝 攞 攟
   *    6522..6524   攢 攣 攤
   *    6526         攦
   *    6529..652C   攩 攪 攫 攬
   *    652E..652F   攮 支
   *    6531..6532   攱 攲
   *    6534..653F   攴 攵 收 攷 攸 改 攺 攻 攼 攽 放 政
   *    6543..6545   敃 敄 故
   *    6547..6549   敇 效 敉
   *    654D         敍
   *    654F..6552   敏 敐 救 敒
   *    6554..6559   敔 敕 敖 敗 敘 教
   *    655D..6560   敝 敞 敟 敠
   *    6562..6563   敢 散
   *    6566..6567   敦 敧
   *    656B..656C   敫 敬
   *    6570         数
   *    6572         敲
   *    6574..6575   整 敵
   *    6577..6578   敷 數
   *    657A         敺
   *    657D         敽
   *    6581..6585   斁 斂 斃 斄 斅
   *    6587..658A   文 斈 斉 斊
   *    658C         斌
   *    658E         斎
   *    6590..6592   斐 斑 斒
   *    6595         斕
   *    6597..6599   斗 斘 料
   *    659B..659D   斛 斜 斝
   *    659F..65A1   斟 斠 斡
   *    65A3..65A7   斣 斤 斥 斦 斧
   *    65AB..65B0   斫 斬 断 斮 斯 新
   *    65B2..65B5   斲 斳 斴 斵
   *    65B7..65B9   斷 斸 方
   *    65BC..65BD   於 施
   *    65BF         斿
   *    65C1..65C6   旁 旂 旃 旄 旅 旆
   *    65C8..65C9   旈 旉
   *    65CB..65CC   旋 旌
   *    65CE..65D0   旎 族 旐
   *    65D2         旒
   *    65D4         旔
   *    65D6..65D9   旖 旗 旘 旙
   *    65DB         旛
   *    65DF..65E2   旟 无 旡 既
   *    65E5..65E9   日 旦 旧 旨 早
   *    65EC..65ED   旬 旭
   *    65F0..65F2   旰 旱 旲
   *    65F4..65F5   旴 旵
   *    65F9..65FC   旹 旺 旻 旼
   *    65FE..6600   旾 旿 昀
   *    6602..6604   昂 昃 昄
   *    6606..660A   昆 昇 昈 昉 昊
   *    660C..660F   昌 昍 明 昏
   *    6611..6616   昑 昒 易 昔 昕 昖
   *    661C..6631   昜 昝 昞 星 映 昡 昢 昣 昤 春 昦 昧 昨 昩 昪 昫 昬 昭 昮 是 昰 昱
   *    6633..6637   昳 昴 昵 昶 昷
   *    6639..663A   昹 昺
   *    663C         昼
   *    663F..6646   昿 晀 晁 時 晃 晄 晅 晆
   *    6648..664C   晈 晉 晊 晋 晌
   *    664E..664F   晎 晏
   *    6651..6652   晑 晒
   *    6657..6670   晗 晘 晙 晚 晛 晜 晝 晞 晟 晠 晡 晢 晣 晤 晥 晦 晧 晨 晩 晪 晫 晬 晭 普 景 晰
   *    6673..667C   晳 晴 晵 晶 晷 晸 晹 智 晻 晼
   *    667E..6681   晾 晿 暀 暁
   *    6683..6684   暃 暄
   *    6687..6689   暇 暈 暉
   *    668B..668E   暋 暌 暍 暎
   *    6690..6692   暐 暑 暒
   *    6696..669D   暖 暗 暘 暙 暚 暛 暜 暝
   *    669F..66A0   暟 暠
   *    66A2         暢
   *    66A4         暤
   *    66A6         暦
   *    66AB         暫
   *    66AD..66AE   暭 暮
   *    66B1..66B2   暱 暲
   *    66B4..66B5   暴 暵
   *    66B8..66B9   暸 暹
   *    66BB..66BC   暻 暼
   *    66BE..66C4   暾 暿 曀 曁 曂 曃 曄
   *    66C6..66C9   曆 曇 曈 曉
   *    66CC         曌
   *    66CE..66CF   曎 曏
   *    66D4         曔
   *    66D6         曖
   *    66D9..66DD   曙 曚 曛 曜 曝
   *    66DF..66E0   曟 曠
   *    66E6         曦
   *    66E8..66E9   曨 曩
   *    66EB..66EC   曫 曬
   *    66EE         曮
   *    66F0         曰
   *    66F2..66F5   曲 曳 更 曵
   *    66F7..6701   曷 書 曹 曺 曻 曼 曽 曾 替 最 朁
   *    6703         會
   *    6705         朅
   *    6707..6709   朇 月 有
   *    670B         朋
   *    670D..670F   服 朎 朏
   *    6712..6717   朒 朓 朔 朕 朖 朗
   *    6719         朙
   *    671B..6720   望 朜 朝 朞 期 朠
   *    6722         朢
   *    6726..6728   朦 朧 木
   *    672A..672E   未 末 本 札 朮
   *    6731         朱
   *    6733..6734   朳 朴
   *    6736..6738   朶 朷 朸
   *    673A         机
   *    673D..673F   朽 朾 朿
   *    6741         杁
   *    6745..6749   杅 杆 杇 杈 杉
   *    674C..6751   杌 杍 李 杏 材 村
   *    6753..6756   杓 杔 杕 杖
   *    6759         杙
   *    675C..6766   杜 杝 杞 束 杠 条 杢 杣 杤 来 杦
   *    676A         杪
   *    676C..6777   杬 杭 杮 杯 杰 東 杲 杳 杴 杵 杶 杷
   *    677B..677C   杻 杼
   *    677E..677F   松 板
   *    6781         极
   *    6784..6785   构 枅
   *    6787         枇
   *    6789         枉
   *    678B..678C   枋 枌
   *    678E..6793   枎 枏 析 枑 枒 枓
   *    6795..679D   枕 枖 林 枘 枙 枚 枛 果 枝
   *    67A0..67A2   枠 枡 枢
   *    67A6         枦
   *    67A9         枩
   *    67AF..67B9   枯 枰 枱 枲 枳 枴 枵 架 枷 枸 枹
   *    67BB..67BD   枻 枼 枽
   *    67C0..67C6   柀 柁 柂 柃 柄 柅 柆
   *    67C8..67CA   柈 柉 柊
   *    67CE..67D4   柎 柏 某 柑 柒 染 柔
   *    67D7..67DE   柗 柘 柙 柚 柛 柜 柝 柞
   *    67E1..67E2   柡 柢
   *    67E4         柤
   *    67E6..67E7   柦 柧
   *    67E9         柩
   *    67EC         柬
   *    67EE..67F7   柮 柯 柰 柱 柲 柳 柴 柵 柶 柷
   *    67F9         柹
   *    67FB..67FC   査 柼
   *    67FE..67FF   柾 柿
   *    6801..6804   栁 栂 栃 栄
   *    6810         栐
   *    6813..6814   栓 栔
   *    6816..6819   栖 栗 栘 栙
   *    681D..681F   栝 栞 栟
   *    6821..6822   校 栢
   *    6827..682D   栧 栨 栩 株 栫 栬 栭
   *    682F..6834   栯 栰 栱 栲 栳 栴
   *    6838..6839   核 根
   *    683B..6846   栻 格 栽 栾 栿 桀 桁 桂 桃 桄 桅 框
   *    6848..684A   案 桉 桊
   *    684C..684E   桌 桍 桎
   *    6850..6855   桐 桑 桒 桓 桔 桕
   *    6857..6859   桗 桘 桙
   *    685B..685D   桛 桜 桝
   *    685F         桟
   *    6863         档
   *    6867         桧
   *    686B         桫
   *    686E..6872   桮 桯 桰 桱 桲
   *    6874..6877   桴 桵 桶 桷
   *    6879..687C   桹 桺 桻 桼
   *    687E..687F   桾 桿
   *    6881..6886   梁 梂 梃 梄 梅 梆
   *    6888         梈
   *    688D         梍
   *    688F..6890   梏 梐
   *    6893..6894   梓 梔
   *    6896..689D   梖 梗 梘 梙 梚 梛 梜 條
   *    689F..68A3   梟 梠 梡 梢 梣
   *    68A5..68AB   梥 梦 梧 梨 梩 梪 梫
   *    68AD..68B6   梭 梮 梯 械 梱 梲 梳 梴 梵 梶
   *    68B9..68BC   梹 梺 梻 梼
   *    68C3..68C6   棃 棄 棅 棆
   *    68C8..68CD   棈 棉 棊 棋 棌 棍
   *    68CF..68DA   棏 棐 棑 棒 棓 棔 棕 棖 棗 棘 棙 棚
   *    68DC..68DD   棜 棝
   *    68DF..68E1   棟 棠 棡
   *    68E3..68E5   棣 棤 棥
   *    68E7..68E8   棧 棨
   *    68EA..68F2   棪 棫 棬 棭 森 棯 棰 棱 棲
   *    68F5..68F7   棵 棶 棷
   *    68F9..68FD   棹 棺 棻 棼 棽
   *    6900..6901   椀 椁
   *    6903..6913   椃 椄 椅 椆 椇 椈 椉 椊 椋 椌 植 椎 椏 椐 椑 椒 椓
   *    6916..6917   椖 椗
   *    6919..691C   椙 椚 椛 検
   *    6921..6923   椡 椢 椣
   *    6925..6926   椥 椦
   *    6928         椨
   *    692A         椪
   *    6930..6931   椰 椱
   *    6933..6936   椳 椴 椵 椶
   *    6938..6939   椸 椹
   *    693B         椻
   *    693D         椽
   *    693F         椿
   *    6942         楂
   *    6945..6946   楅 楆
   *    6949..694A   楉 楊
   *    694E         楎
   *    6953..6955   楓 楔 楕
   *    6957         楗
   *    6959..695E   楙 楚 楛 楜 楝 楞
   *    6960..6966   楠 楡 楢 楣 楤 楥 楦
   *    6968..6975   楨 楩 楪 楫 楬 業 楮 楯 楰 楱 楲 楳 楴 極
   *    6977..6982   楷 楸 楹 楺 楻 楼 楽 楾 楿 榀 榁 概
   *    698A         榊
   *    698D..698E   榍 榎
   *    6991..6992   榑 榒
   *    6994..6996   榔 榕 榖
   *    6998         榘
   *    699B..699C   榛 榜
   *    69A0..69A1   榠 榡
   *    69A5..69A8   榥 榦 榧 榨
   *    69AB         榫
   *    69AD..69B2   榭 榮 榯 榰 榱 榲
   *    69B4         榴
   *    69B7..69B8   榷 榸
   *    69BA..69BC   榺 榻 榼
   *    69BE..69C1   榾 榿 槀 槁
   *    69C3         槃
   *    69C5         槅
   *    69C7..69C8   槇 槈
   *    69CA..69D1   槊 構 槌 槍 槎 槏 槐 槑
   *    69D3         槓
   *    69D6..69D9   槖 槗 様 槙
   *    69DD..69DE   槝 槞
   *    69E2..69E3   槢 槣
   *    69E5         槥
   *    69E7..69EB   槧 槨 槩 槪 槫
   *    69ED..69EF   槭 槮 槯
   *    69F1..69F6   槱 槲 槳 槴 槵 槶
   *    69F9         槹
   *    69FB         槻
   *    69FD..6A03   槽 槾 槿 樀 樁 樂 樃
   *    6A05         樅
   *    6A0A..6A0C   樊 樋 樌
   *    6A0F         樏
   *    6A11..6A15   樑 樒 樓 樔 樕
   *    6A17         樗
   *    6A19..6A1B   標 樚 樛
   *    6A1D..6A24   樝 樞 樟 樠 模 樢 樣 樤
   *    6A28..6A2B   樨 権 横 樫
   *    6A2E         樮
   *    6A30         樰
   *    6A32..6A3B   樲 樳 樴 樵 樶 樷 樸 樹 樺 樻
   *    6A3D..6A3F   樽 樾 樿
   *    6A44..6A4B   橄 橅 橆 橇 橈 橉 橊 橋
   *    6A4E         橎
   *    6A50..6A52   橐 橑 橒
   *    6A55..6A56   橕 橖
   *    6A58..6A59   橘 橙
   *    6A5B         橛
   *    6A5F         機
   *    6A61..6A62   橡 橢
   *    6A64         橤
   *    6A66..6A67   橦 橧
   *    6A6A..6A6B   橪 橫
   *    6A71..6A73   橱 橲 橳
   *    6A78         橸
   *    6A7A         橺
   *    6A7E..6A81   橾 橿 檀 檁
   *    6A83..6A84   檃 檄
   *    6A86..6A87   檆 檇
   *    6A89         檉
   *    6A8B         檋
   *    6A8D..6A8E   檍 檎
   *    6A90..6A91   檐 檑
   *    6A94         檔
   *    6A97         檗
   *    6A9B..6AA3   檛 檜 檝 檞 檟 檠 檡 檢 檣
   *    6AA5         檥
   *    6AAA..6AAC   檪 檫 檬
   *    6AAE..6AB1   檮 檯 檰 檱
   *    6AB3..6AB4   檳 檴
   *    6AB8         檸
   *    6ABB         檻
   *    6ABD..6ABF   檽 檾 檿
   *    6AC1..6AC3   櫁 櫂 櫃
   *    6AC6         櫆
   *    6AC8..6AC9   櫈 櫉
   *    6ACC         櫌
   *    6AD0..6AD1   櫐 櫑
   *    6AD3..6AD6   櫓 櫔 櫕 櫖
   *    6ADA..6ADF   櫚 櫛 櫜 櫝 櫞 櫟
   *    6AE4         櫤
   *    6AE7..6AE8   櫧 櫨
   *    6AEA         櫪
   *    6AEC         櫬
   *    6AF0..6AF3   櫰 櫱 櫲 櫳
   *    6AFA..6AFD   櫺 櫻 櫼 櫽
   *    6B02..6B07   欂 欃 欄 欅 欆 欇
   *    6B09..6B0B   欉 權 欋
   *    6B0F..6B12   欏 欐 欑 欒
   *    6B16..6B17   欖 欗
   *    6B1B         欛
   *    6B1D..6B21   欝 欞 欟 欠 次
   *    6B23..6B24   欣 欤
   *    6B27..6B28   欧 欨
   *    6B2B..6B2C   欫 欬
   *    6B2F         欯
   *    6B32         欲
   *    6B35..6B3B   欵 欶 欷 欸 欹 欺 欻
   *    6B3D..6B3F   欽 款 欿
   *    6B43         歃
   *    6B46..6B47   歆 歇
   *    6B49..6B4A   歉 歊
   *    6B4C..6B4E   歌 歍 歎
   *    6B50         歐
   *    6B52..6B54   歒 歓 歔
   *    6B56         歖
   *    6B58..6B59   歘 歙
   *    6B5B         歛
   *    6B5D         歝
   *    6B5F..6B67   歟 歠 歡 止 正 此 步 武 歧
   *    6B69..6B6C   歩 歪 歫 歬
   *    6B6E..6B70   歮 歯 歰
   *    6B73..6B75   歳 歴 歵
   *    6B77..6B7B   歷 歸 歹 歺 死
   *    6B7D..6B86   歽 歾 歿 殀 殁 殂 殃 殄 殅 殆
   *    6B89..6B8B   殉 殊 残
   *    6B8D         殍
   *    6B95..6B98   殕 殖 殗 殘
   *    6B9B         殛
   *    6B9E..6BA0   殞 殟 殠
   *    6BA2..6BA4   殢 殣 殤
   *    6BA8..6BB5   殨 殩 殪 殫 殬 殭 殮 殯 殰 殱 殲 殳 殴 段
   *    6BB7..6BC0   殷 殸 殹 殺 殻 殼 殽 殾 殿 毀
   *    6BC3..6BC9   毃 毄 毅 毆 毇 毈 毉
   *    6BCB..6BCF   毋 毌 母 毎 每
   *    6BD2..6BD4   毒 毓 比
   *    6BD6..6BD8   毖 毗 毘
   *    6BDA..6BDB   毚 毛
   *    6BDF         毟
   *    6BE1         毡
   *    6BE3         毣
   *    6BE6..6BE7   毦 毧
   *    6BEB..6BEC   毫 毬
   *    6BEE..6BEF   毮 毯
   *    6BF1         毱
   *    6BF3         毳
   *    6BF7         毷
   *    6BF9         毹
   *    6BFF         毿
   *    6C02         氂
   *    6C04..6C05   氄 氅
   *    6C08..6C0A   氈 氉 氊
   *    6C0D..6C14   氍 氎 氏 氐 民 氒 氓 气
   *    6C17         気
   *    6C19         氙
   *    6C1B         氛
   *    6C1F         氟
   *    6C23..6C24   氣 氤
   *    6C26..6C28   氦 氧 氨
   *    6C2C         氬
   *    6C2E         氮
   *    6C33..6C38   氳 水 氵 氶 氷 永
   *    6C3A..6C3B   氺 氻
   *    6C3E..6C42   氾 氿 汀 汁 求
   *    6C4A..6C4B   汊 汋
   *    6C4D..6C50   汍 汎 汏 汐
   *    6C52         汒
   *    6C54..6C55   汔 汕
   *    6C57         汗
   *    6C59..6C60   汙 汚 汛 汜 汝 汞 江 池
   *    6C62         汢
   *    6C67..6C68   汧 汨
   *    6C6A..6C6B   汪 汫
   *    6C6D         汭
   *    6C6F..6C70   汯 汰
   *    6C72..6C74   汲 汳 汴
   *    6C76         汶
   *    6C78..6C7B   汸 汹 決 汻
   *    6C7D..6C7E   汽 汾
   *    6C81..6C89   沁 沂 沃 沄 沅 沆 沇 沈 沉
   *    6C8C..6C8D   沌 沍
   *    6C90         沐
   *    6C92..6C9C   沒 沓 沔 沕 沖 沗 沘 沙 沚 沛 沜
   *    6C9F         沟
   *    6CA1..6CA2   没 沢
   *    6CAA..6CAB   沪 沫
   *    6CAD..6CAE   沭 沮
   *    6CB0..6CB4   沰 沱 沲 河 沴
   *    6CB8..6CBF   沸 油 沺 治 沼 沽 沾 沿
   *    6CC1..6CC2   況 泂
   *    6CC4..6CC6   泄 泅 泆
   *    6CC9..6CCA   泉 泊
   *    6CCC..6CCD   泌 泍
   *    6CCF..6CD7   泏 泐 泑 泒 泓 泔 法 泖 泗
   *    6CD9..6CDD   泙 泚 泛 泜 泝
   *    6CE0..6CE3   泠 泡 波 泣
   *    6CE5         泥
   *    6CE7..6CF4   泧 注 泩 泪 泫 泬 泭 泮 泯 泰 泱 泲 泳 泴
   *    6CFB         泻
   *    6D00         洀
   *    6D04         洄
   *    6D07         洇
   *    6D0A..6D0C   洊 洋 洌
   *    6D0E..6D0F   洎 洏
   *    6D11..6D13   洑 洒 洓
   *    6D17         洗
   *    6D19..6D1B   洙 洚 洛
   *    6D1E..6D1F   洞 洟
   *    6D24..6D2B   洤 津 洦 洧 洨 洩 洪 洫
   *    6D2E..6D2F   洮 洯
   *    6D31..6D36   洱 洲 洳 洴 洵 洶
   *    6D38..6D39   洸 洹
   *    6D3B..6D3F   活 洼 洽 派 洿
   *    6D41         流
   *    6D44..6D45   浄 浅
   *    6D57..6D5C   浗 浘 浙 浚 浛 浜
   *    6D5E..6D61   浞 浟 浠 浡
   *    6D63..6D67   浣 浤 浥 浦 浧
   *    6D69..6D6A   浩 浪
   *    6D6C         浬
   *    6D6E..6D70   浮 浯 浰
   *    6D74         浴
   *    6D77..6D79   海 浸 浹
   *    6D7C         浼
   *    6D80..6D82   涀 涁 涂
   *    6D85         涅
   *    6D87..6D8A   涇 消 涉 涊
   *    6D8C..6D8E   涌 涍 涎
   *    6D91..6D99   涑 涒 涓 涔 涕 涖 涗 涘 涙
   *    6D9B..6D9C   涛 涜
   *    6DAA..6DAC   涪 涫 涬
   *    6DAE..6DAF   涮 涯
   *    6DB2         液
   *    6DB4..6DB5   涴 涵
   *    6DB7..6DB9   涷 涸 涹
   *    6DBC..6DBD   涼 涽
   *    6DBF..6DC0   涿 淀
   *    6DC2         淂
   *    6DC4..6DC8   淄 淅 淆 淇 淈
   *    6DCA..6DCC   淊 淋 淌
   *    6DCE..6DD2   淎 淏 淐 淑 淒
   *    6DD5..6DD6   淕 淖
   *    6DD8..6DDB   淘 淙 淚 淛
   *    6DDD..6DE2   淝 淞 淟 淠 淡 淢
   *    6DE4..6DE6   淤 淥 淦
   *    6DE8..6DEC   淨 淩 淪 淫 淬
   *    6DEE..6DF1   淮 淯 淰 深
   *    6DF3..6DF7   淳 淴 淵 淶 混
   *    6DF9..6DFC   淹 淺 添 淼
   *    6E00         渀
   *    6E04..6E05   渄 清
   *    6E07..6E0B   渇 済 渉 渊 渋
   *    6E13         渓
   *    6E15         渕
   *    6E17         渗
   *    6E19..6E1B   渙 渚 減
   *    6E1D..6E27   渝 渞 渟 渠 渡 渢 渣 渤 渥 渦 渧
   *    6E29         温
   *    6E2B..6E2F   渫 測 渭 渮 港
   *    6E32         渲
   *    6E34         渴
   *    6E36         渶
   *    6E38..6E3C   游 渹 渺 渻 渼
   *    6E3E         渾
   *    6E43..6E45   湃 湄 湅
   *    6E48..6E4F   湈 湉 湊 湋 湌 湍 湎 湏
   *    6E51..6E54   湑 湒 湓 湔
   *    6E56..6E58   湖 湗 湘
   *    6E5B..6E5F   湛 湜 湝 湞 湟
   *    6E62..6E63   湢 湣
   *    6E67..6E68   湧 湨
   *    6E6B         湫
   *    6E6E..6E6F   湮 湯
   *    6E72..6E73   湲 湳
   *    6E76         湶
   *    6E7B         湻
   *    6E7D..6E80   湽 湾 湿 満
   *    6E82         溂
   *    6E8C..6E8D   溌 溍
   *    6E8F..6E90   溏 源
   *    6E93         溓
   *    6E96         準
   *    6E98..6E99   溘 溙
   *    6E9C..6E9D   溜 溝
   *    6E9F..6EA0   溟 溠
   *    6EA2         溢
   *    6EA5         溥
   *    6EA7         溧
   *    6EAA..6EAB   溪 溫
   *    6EAD..6EAF   溭 溮 溯
   *    6EB1..6EB4   溱 溲 溳 溴
   *    6EB6..6EB7   溶 溷
   *    6EBA..6EBB   溺 溻
   *    6EBD         溽
   *    6EBF..6EC5   溿 滀 滁 滂 滃 滄 滅
   *    6EC7..6ECF   滇 滈 滉 滊 滋 滌 滍 滎 滏
   *    6ED1         滑
   *    6ED3..6ED5   滓 滔 滕
   *    6ED9         滙
   *    6EDD..6EDE   滝 滞
   *    6EEB..6EEF   滫 滬 滭 滮 滯
   *    6EF2         滲
   *    6EF4         滴
   *    6EF7..6EF9   滷 滸 滹
   *    6EFB         滻
   *    6EFD..6EFF   滽 滾 滿
   *    6F01..6F02   漁 漂
   *    6F04         漄
   *    6F06         漆
   *    6F08..6F0A   漈 漉 漊
   *    6F0C..6F0D   漌 漍
   *    6F0F..6F11   漏 漐 漑
   *    6F13..6F16   漓 演 漕 漖
   *    6F18         漘
   *    6F1A..6F1B   漚 漛
   *    6F20         漠
   *    6F22..6F23   漢 漣
   *    6F25..6F26   漥 漦
   *    6F29..6F2D   漩 漪 漫 漬 漭
   *    6F2F..6F33   漯 漰 漱 漲 漳
   *    6F35..6F36   漵 漶
   *    6F38         漸
   *    6F3B..6F3C   漻 漼
   *    6F3E..6F3F   漾 漿
   *    6F41         潁
   *    6F45         潅
   *    6F4F         潏
   *    6F51..6F54   潑 潒 潓 潔
   *    6F57..6F62   潗 潘 潙 潚 潛 潜 潝 潞 潟 潠 潡 潢
   *    6F64         潤
   *    6F66         潦
   *    6F68         潨
   *    6F6C..6F70   潬 潭 潮 潯 潰
   *    6F74         潴
   *    6F78         潸
   *    6F7A         潺
   *    6F7C..6F7E   潼 潽 潾
   *    6F80..6F84   澀 澁 澂 澃 澄
   *    6F86..6F88   澆 澇 澈
   *    6F8B..6F8E   澋 澌 澍 澎
   *    6F90..6F94   澐 澑 澒 澓 澔
   *    6F96..6F98   澖 澗 澘
   *    6F9A         澚
   *    6F9F..6FA1   澟 澠 澡
   *    6FA3..6FA8   澣 澤 澥 澦 澧 澨
   *    6FAA         澪
   *    6FAE..6FB1   澮 澯 澰 澱
   *    6FB3         澳
   *    6FB5..6FB6   澵 澶
   *    6FB9         澹
   *    6FBC         澼
   *    6FBE         澾
   *    6FC0..6FC3   激 濁 濂 濃
   *    6FC5..6FCA   濅 濆 濇 濈 濉 濊
   *    6FD4..6FD5   濔 濕
   *    6FD8         濘
   *    6FDA..6FDB   濚 濛
   *    6FDE..6FE1   濞 濟 濠 濡
   *    6FE4         濤
   *    6FE8..6FE9   濨 濩
   *    6FEB..6FEC   濫 濬
   *    6FEE..6FF1   濮 濯 濰 濱
   *    6FF3         濳
   *    6FF5..6FF6   濵 濶
   *    6FF9..6FFA   濹 濺
   *    6FFC..6FFE   濼 濽 濾
   *    7000..7001   瀀 瀁
   *    7005..7007   瀅 瀆 瀇
   *    7009..700B   瀉 瀊 瀋
   *    700D         瀍
   *    700F         瀏
   *    7011         瀑
   *    7015         瀕
   *    7017..7018   瀗 瀘
   *    701A..701B   瀚 瀛
   *    701D..7020   瀝 瀞 瀟 瀠
   *    7023         瀣
   *    7026..7028   瀦 瀧 瀨
   *    702C         瀬
   *    702F..7030   瀯 瀰
   *    7032         瀲
   *    7034         瀴
   *    7037         瀷
   *    7039..703A   瀹 瀺
   *    703C         瀼
   *    703E         瀾
   *    7043..7044   灃 灄
   *    7047..704C   灇 灈 灉 灊 灋 灌
   *    704E         灎
   *    7051         灑
   *    7054..7055   灔 灕
   *    7058         灘
   *    705D..705E   灝 灞
   *    7063..7065   灣 灤 灥
   *    7069         灩
   *    706B..706C   火 灬
   *    706E..7070   灮 灯 灰
   *    7075..7076   灵 灶
   *    7078         灸
   *    707C..707E   灼 災 灾
   *    7081         炁
   *    7085..7086   炅 炆
   *    7089..708A   炉 炊
   *    708E         炎
   *    7092         炒
   *    7094..7099   炔 炕 炖 炗 炘 炙
   *    709B         炛
   *    709F         炟
   *    70A4         炤
   *    70AB..70B1   炫 炬 炭 炮 炯 炰 炱
   *    70B3..70B4   炳 炴
   *    70B7..70BB   炷 炸 点 為 炻
   *    70C8         烈
   *    70CA..70CB   烊 烋
   *    70CF         烏
   *    70D1         烑
   *    70D3..70D6   烓 烔 烕 烖
   *    70D8..70D9   烘 烙
   *    70DC..70DD   烜 烝
   *    70DF         烟
   *    70E4         烤
   *    70EC         烬
   *    70F1         烱
   *    70F9..70FA   烹 烺
   *    70FD         烽
   *    7103..7109   焃 焄 焅 焆 焇 焈 焉
   *    710B..710C   焋 焌
   *    710F         焏
   *    7114         焔
   *    7119..711A   焙 焚
   *    711C         焜
   *    711E         焞
   *    7120..7121   焠 無
   *    7126         焦
   *    712B         焫
   *    712D..7131   焭 焮 焯 焰 焱
   *    7136         然
   *    7138         焸
   *    713C         焼
   *    7141         煁
   *    7145..7147   煅 煆 煇
   *    7149..714C   煉 煊 煋 煌
   *    714E         煎
   *    7150..7153   煐 煑 煒 煓
   *    7155..7157   煕 煖 煗
   *    7159..715A   煙 煚
   *    715C         煜
   *    715E         煞
   *    7160         煠
   *    7162         煢
   *    7164..7169   煤 煥 煦 照 煨 煩
   *    716C         煬
   *    716E         煮
   *    7179         煹
   *    717D         煽
   *    7180         熀
   *    7184..7185   熄 熅
   *    7187..7188   熇 熈
   *    718A         熊
   *    718C         熌
   *    718F         熏
   *    7192         熒
   *    7194..7196   熔 熕 熖
   *    7199..719B   熙 熚 熛
   *    719F..71A0   熟 熠
   *    71A2         熢
   *    71A8         熨
   *    71AC         熬
   *    71AE..71B3   熮 熯 熰 熱 熲 熳
   *    71B9..71BA   熹 熺
   *    71BE..71C1   熾 熿 燀 燁
   *    71C3..71C4   燃 燄
   *    71C8..71C9   燈 燉
   *    71CB..71CC   燋 燌
   *    71CE         燎
   *    71D0         燐
   *    71D2..71D7   燒 燓 燔 燕 燖 燗
   *    71D9..71DA   燙 燚
   *    71DC         燜
   *    71DF..71E0   營 燠
   *    71E5..71E7   燥 燦 燧
   *    71EC..71EE   燬 燭 燮
   *    71F5         燵
   *    71F8..71F9   燸 燹
   *    71FB..71FC   燻 燼
   *    71FE..7200   燾 燿 爀
   *    7206..7209   爆 爇 爈 爉
   *    720D         爍
   *    7210         爐
   *    7213         爓
   *    7215         爕
   *    7217         爗
   *    721A..721B   爚 爛
   *    721D         爝
   *    721F         爟
   *    7224         爤
   *    7228         爨
   *    722A..722D   爪 爫 爬 爭
   *    722F..7230   爯 爰
   *    7232         爲
   *    7234..7236   爴 爵 父
   *    7238..7243   爸 爹 爺 爻 爼 爽 爾 爿 牀 牁 牂 牃
   *    7245..7248   牅 牆 片 版
   *    724B..724C   牋 牌
   *    724E..7250   牎 牏 牐
   *    7252..7253   牒 牓
   *    7255..7263   牕 牖 牗 牘 牙 牚 牛 牜 牝 牞 牟 牠 牡 牢 牣
   *    7267..7269   牧 牨 物
   *    726B         牫
   *    726E..726F   牮 牯
   *    7271..7272   牱 牲
   *    7274         牴
   *    7277..7279   牷 牸 特
   *    727B..7282   牻 牼 牽 牾 牿 犀 犁 犂
   *    7284         犄
   *    7287         犇
   *    7289         犉
   *    728D..728E   犍 犎
   *    7292..7293   犒 犓
   *    7296         犖
   *    729B         犛
   *    72A0         犠
   *    72A2         犢
   *    72A7..72A8   犧 犨
   *    72AC..72B2   犬 犭 犮 犯 犰 犱 犲
   *    72B4         犴
   *    72B6         状
   *    72B9         犹
   *    72BE         犾
   *    72C0..72C4   狀 狁 狂 狃 狄
   *    72C6..72C7   狆 狇
   *    72C9         狉
   *    72CC         狌
   *    72CE         狎
   *    72D0         狐
   *    72D2         狒
   *    72D5..72D9   狕 狖 狗 狘 狙
   *    72DB         狛
   *    72DF..72E2   狟 狠 狡 狢
   *    72E5         狥
   *    72E9         狩
   *    72EC..72ED   独 狭
   *    72F3..72F4   狳 狴
   *    72F7..72FE   狷 狸 狹 狺 狻 狼 狽 狾
   *    7302         猂
   *    7304..7305   猄 猅
   *    7307         猇
   *    730A..730B   猊 猋
   *    730D         猍
   *    7312..7313   猒 猓
   *    7316..7319   猖 猗 猘 猙
   *    731B..731F   猛 猜 猝 猞 猟
   *    7322         猢
   *    7324..7325   猤 猥
   *    7327..732C   猧 猨 猩 猪 猫 猬
   *    732E..732F   献 猯
   *    7331..7337   猱 猲 猳 猴 猵 猶 猷
   *    7339..733B   猹 猺 猻
   *    733D..733F   猽 猾 猿
   *    7343..7345   獃 獄 獅
   *    734D..7350   獍 獎 獏 獐
   *    7352         獒
   *    7356..7358   獖 獗 獘
   *    735D..7360   獝 獞 獟 獠
   *    7363         獣
   *    7366..736C   獦 獧 獨 獩 獪 獫 獬
   *    736E..7372   獮 獯 獰 獱 獲
   *    7375         獵
   *    7377..737C   獷 獸 獹 獺 獻 獼
   *    7380..7381   玀 玁
   *    7383..7387   玃 玄 玅 玆 率
   *    7389..738B   玉 玊 王
   *    738E         玎
   *    7390         玐
   *    7393..7398   玓 玔 玕 玖 玗 玘
   *    739C         玜
   *    739E..73A0   玞 玟 玠
   *    73A2         玢
   *    73A5..73A6   玥 玦
   *    73A8..73AB   玨 玩 玪 玫
   *    73AD         玭
   *    73B2..73B3   玲 玳
   *    73B5         玵
   *    73B7         玷
   *    73B9         玹
   *    73BB..73BD   玻 玼 玽
   *    73BF..73C0   玿 珀
   *    73C2         珂
   *    73C5..73C6   珅 珆
   *    73C8..73CF   珈 珉 珊 珋 珌 珍 珎 珏
   *    73D2..73D3   珒 珓
   *    73D6         珖
   *    73D9         珙
   *    73DD..73DE   珝 珞
   *    73E0..73E1   珠 珡
   *    73E3         珣
   *    73E5..73E7   珥 珦 珧
   *    73E9..73EA   珩 珪
   *    73ED..73EE   班 珮
   *    73F1         珱
   *    73F4..73F5   珴 珵
   *    73F7..73FB   珷 珸 珹 珺 珻
   *    73FD..7401   珽 現 珿 琀 琁
   *    7403..7407   球 琄 琅 理 琇
   *    7409..740A   琉 琊
   *    7411         琑
   *    7413         琓
   *    741A..741B   琚 琛
   *    7422         琢
   *    7424..7426   琤 琥 琦
   *    7428..7436   琨 琩 琪 琫 琬 琭 琮 琯 琰 琱 琲 琳 琴 琵 琶
   *    7439..743A   琹 琺
   *    743F..7441   琿 瑀 瑁
   *    7443..7444   瑃 瑄
   *    7446..7447   瑆 瑇
   *    744B         瑋
   *    744D         瑍
   *    7451..7453   瑑 瑒 瑓
   *    7455         瑕
   *    7457         瑗
   *    7459..7460   瑙 瑚 瑛 瑜 瑝 瑞 瑟 瑠
   *    7462..7464   瑢 瑣 瑤
   *    7466..746B   瑦 瑧 瑨 瑩 瑪 瑫
   *    746D..7473   瑭 瑮 瑯 瑰 瑱 瑲 瑳
   *    7476         瑶
   *    747E         瑾
   *    7480..7481   璀 璁
   *    7483         璃
   *    7485..7489   璅 璆 璇 璈 璉
   *    748B         璋
   *    748F..7492   璏 璐 璑 璒
   *    7497..749A   璗 璘 璙 璚
   *    749C         璜
   *    749E..74A3   璞 璟 璠 璡 璢 璣
   *    74A5..74AB   璥 璦 璧 璨 璩 璪 璫
   *    74AE..74B2   璮 璯 環 璱 璲
   *    74B5         璵
   *    74B9..74BB   璹 璺 璻
   *    74BD         璽
   *    74BF         璿
   *    74C8..74CA   瓈 瓉 瓊
   *    74CC         瓌
   *    74CF..74D0   瓏 瓐
   *    74D3..74D4   瓓 瓔
   *    74D6         瓖
   *    74D8         瓘
   *    74DA..74DC   瓚 瓛 瓜
   *    74DE..74E0   瓞 瓟 瓠
   *    74E2..74E4   瓢 瓣 瓤
   *    74E6..74EB   瓦 瓧 瓨 瓩 瓪 瓫
   *    74EE..74F2   瓮 瓯 瓰 瓱 瓲
   *    74F4         瓴
   *    74F6..74F8   瓶 瓷 瓸
   *    74FA..74FC   瓺 瓻 瓼
   *    74FF         瓿
   *    7501         甁
   *    7503..7506   甃 甄 甅 甆
   *    750C..750E   甌 甍 甎
   *    7511..7513   甑 甒 甓
   *    7515..7518   甕 甖 甗 甘
   *    751A         甚
   *    751C         甜
   *    751E..7521   甞 生 甠 甡
   *    7523..752C   産 甤 甥 甦 甧 用 甩 甪 甫 甬
   *    752F..7533   甯 田 由 甲 申
   *    7536..7540   甶 男 甸 甹 町 画 甼 甽 甾 甿 畀
   *    7543..7544   畃 畄
   *    7546..7552   畆 畇 畈 畉 畊 畋 界 畍 畎 畏 畐 畑 畒
   *    7554         畔
   *    7557         畗
   *    7559..7562   留 畚 畛 畜 畝 畞 畟 畠 畡 畢
   *    7564..7567   畤 略 畦 畧
   *    7569..756D   畩 番 畫 畬 畭
   *    756F..7574   畯 異 畱 畲 畳 畴
   *    7576..757F   當 畷 畸 畹 畺 畻 畼 畽 畾 畿
   *    7581..7582   疁 疂
   *    7585..7587   疅 疆 疇
   *    7589..758C   疉 疊 疋 疌
   *    758E..7595   疎 疏 疐 疑 疒 疓 疔 疕
   *    7599..759A   疙 疚
   *    759C..759D   疜 疝
   *    75A2..75A5   疢 疣 疤 疥
   *    75AB         疫
   *    75B0..75B5   疰 疱 疲 疳 疴 疵
   *    75B7..75BA   疷 疸 疹 疺
   *    75BC..75C7   疼 疽 疾 疿 痀 痁 痂 痃 痄 病 痆 症
   *    75CA         痊
   *    75CC..75CF   痌 痍 痎 痏
   *    75D2..75D5   痒 痓 痔 痕
   *    75D7..75D9   痗 痘 痙
   *    75DB..75E4   痛 痜 痝 痞 痟 痠 痡 痢 痣 痤
   *    75E7         痧
   *    75E9         痩
   *    75EC         痬
   *    75EE..75F4   痮 痯 痰 痱 痲 痳 痴
   *    75F9..75FA   痹 痺
   *    75FC         痼
   *    75FE..7604   痾 痿 瘀 瘁 瘂 瘃 瘄
   *    7607..760D   瘇 瘈 瘉 瘊 瘋 瘌 瘍
   *    760F         瘏
   *    7612..7613   瘒 瘓
   *    7615..7616   瘕 瘖
   *    7618..7619   瘘 瘙
   *    761B..7629   瘛 瘜 瘝 瘞 瘟 瘠 瘡 瘢 瘣 瘤 瘥 瘦 瘧 瘨 瘩
   *    762D         瘭
   *    7630         瘰
   *    7632..7635   瘲 瘳 瘴 瘵
   *    7638..763C   瘸 瘹 瘺 瘻 瘼
   *    7640..764C   癀 癁 療 癃 癄 癅 癆 癇 癈 癉 癊 癋 癌
   *    7652         癒
   *    7655..7656   癕 癖
   *    7658..7659   癘 癙
   *    765C         癜
   *    765F         癟
   *    7661..7662   癡 癢
   *    7664..7665   癤 癥
   *    7667..766A   癧 癨 癩 癪
   *    766C..7672   癬 癭 癮 癯 癰 癱 癲
   *    7674         癴
   *    7676         癶
   *    7678         癸
   *    767A..767E   発 登 發 白 百
   *    7680..7681   皀 皁
   *    7683..7688   皃 的 皅 皆 皇 皈
   *    768B..768E   皋 皌 皍 皎
   *    7690         皐
   *    7693         皓
   *    7695..7696   皕 皖
   *    7699..76A8   皙 皚 皛 皜 皝 皞 皟 皠 皡 皢 皣 皤 皥 皦 皧 皨
   *    76AA         皪
   *    76AD..76B0   皭 皮 皯 皰
   *    76B4         皴
   *    76B6..76BA   皶 皷 皸 皹 皺
   *    76BD         皽
   *    76BF         皿
   *    76C1..76C3   盁 盂 盃
   *    76C5..76C6   盅 盆
   *    76C8..76CE   盈 盉 益 盋 盌 盍 盎
   *    76D2         盒
   *    76D4         盔
   *    76D6..76D7   盖 盗
   *    76D9         盙
   *    76DB..76DC   盛 盜
   *    76DE..76E1   盞 盟 盠 盡
   *    76E3..76E8   監 盤 盥 盦 盧 盨
   *    76EA         盪
   *    76EC         盬
   *    76EE         目
   *    76F0..76F2   盰 盱 盲
   *    76F4         直
   *    76F6         盶
   *    76F8..76F9   相 盹
   *    76FB..76FC   盻 盼
   *    76FE         盾
   *    7700..7701   眀 省
   *    7704         眄
   *    7706..770C   眆 眇 眈 眉 眊 看 県
   *    770E         眎
   *    7712         眒
   *    7714..7715   眔 眕
   *    7717         眗
   *    7719..771C   眙 眚 眛 眜
   *    771E..7720   眞 真 眠
   *    7722         眢
   *    7724..7726   眤 眥 眦
   *    7728..7729   眨 眩
   *    772D..772F   眭 眮 眯
   *    7734..773A   眴 眵 眶 眷 眸 眹 眺
   *    773C..773E   眼 眽 眾
   *    7740         着
   *    7742         睂
   *    7745..7747   睅 睆 睇
   *    774A         睊
   *    774D..774F   睍 睎 睏
   *    7752         睒
   *    7756..7758   睖 睗 睘
   *    775A..775C   睚 睛 睜
   *    775E..7768   睞 睟 睠 睡 睢 督 睤 睥 睦 睧 睨
   *    776A..776C   睪 睫 睬
   *    7770         睰
   *    7772..7774   睲 睳 睴
   *    7779..777A   睹 睺
   *    777C..7780   睼 睽 睾 睿 瞀
   *    7784         瞄
   *    778B..778E   瞋 瞌 瞍 瞎
   *    7791         瞑
   *    7794..7796   瞔 瞕 瞖
   *    779A         瞚
   *    779E..77A0   瞞 瞟 瞠
   *    77A2         瞢
   *    77A4..77A5   瞤 瞥
   *    77A7         瞧
   *    77A9..77AA   瞩 瞪
   *    77AC..77B1   瞬 瞭 瞮 瞯 瞰 瞱
   *    77B3         瞳
   *    77B5..77B6   瞵 瞶
   *    77B9         瞹
   *    77BB..77BF   瞻 瞼 瞽 瞾 瞿
   *    77C3         矃
   *    77C7         矇
   *    77C9         矉
   *    77CD         矍
   *    77D1..77D2   矑 矒
   *    77D5         矕
   *    77D7         矗
   *    77D9..77DC   矙 矚 矛 矜
   *    77DE..77E0   矞 矟 矠
   *    77E2..77E7   矢 矣 矤 知 矦 矧
   *    77E9..77EA   矩 矪
   *    77EC..77F1   矬 短 矮 矯 矰 矱
   *    77F3..77F4   石 矴
   *    77F8         矸
   *    77FB..77FC   矻 矼
   *    7802         砂
   *    7805..7806   砅 砆
   *    7809         砉
   *    780C..780E   砌 砍 砎
   *    7811..7812   砑 砒
   *    7814..7815   研 砕
   *    7819         砙
   *    781D         砝
   *    7820..7823   砠 砡 砢 砣
   *    7825..7827   砥 砦 砧
   *    782C..782E   砬 砭 砮
   *    7830         砰
   *    7832         砲
   *    7834..7835   破 砵
   *    7837         砷
   *    783A         砺
   *    783F         砿
   *    7843..7845   硃 硄 硅
   *    7847..7848   硇 硈
   *    784C         硌
   *    784E..784F   硎 硏
   *    7851..7852   硑 硒
   *    785C..785E   硜 硝 硞
   *    7860..7861   硠 硡
   *    7863..7864   硣 硤
   *    7868         硨
   *    786A..786C   硪 硫 硬
   *    786E..786F   确 硯
   *    7872         硲
   *    7874         硴
   *    787A         硺
   *    787C         硼
   *    787E         硾
   *    7881         碁
   *    7886..7887   碆 碇
   *    788A         碊
   *    788C..788F   碌 碍 碎 碏
   *    7891         碑
   *    7893..7895   碓 碔 碕
   *    7897..7898   碗 碘
   *    789A         碚
   *    789D..789F   碝 碞 碟
   *    78A1         碡
   *    78A3..78A4   碣 碤
   *    78A7..78AA   碧 碨 碩 碪
   *    78AC..78AD   碬 碭
   *    78AF..78B3   碯 碰 碱 碲 碳
   *    78B5         碵
   *    78BA..78BF   確 碻 碼 碽 碾 碿
   *    78C1         磁
   *    78C5..78CC   磅 磆 磇 磈 磉 磊 磋 磌
   *    78CE         磎
   *    78D0..78D6   磐 磑 磒 磓 磔 磕 磖
   *    78DA..78DB   磚 磛
   *    78DF..78E1   磟 磠 磡
   *    78E4         磤
   *    78E6..78E8   磦 磧 磨
   *    78EA         磪
   *    78EC         磬
   *    78EF         磯
   *    78F2..78F4   磲 磳 磴
   *    78F6..78F7   磶 磷
   *    78F9..78FB   磹 磺 磻
   *    78FD..7901   磽 磾 磿 礀 礁
   *    7906..7907   礆 礇
   *    790C         礌
   *    790E         礎
   *    7910..7912   礐 礑 礒
   *    7919..791C   礙 礚 礛 礜
   *    791E..7920   礞 礟 礠
   *    7925..7927   礥 礦 礧
   *    7929..792E   礩 礪 礫 礬 礭 礮
   *    7930..7931   礰 礱
   *    7934..7935   礴 礵
   *    793A..7941   示 礻 礼 礽 社 礿 祀 祁
   *    7944..794B   祄 祅 祆 祇 祈 祉 祊 祋
   *    794F..7951   祏 祐 祑
   *    7953..7958   祓 祔 祕 祖 祗 祘
   *    795A..7960   祚 祛 祜 祝 神 祟 祠
   *    7962         祢
   *    7965         祥
   *    7967..7969   祧 票 祩
   *    796B         祫
   *    796D         祭
   *    7972         祲
   *    7977         祷
   *    7979..797C   祹 祺 祻 祼
   *    797E..7981   祾 祿 禀 禁
   *    7984..7985   禄 禅
   *    798A..798F   禊 禋 禌 禍 禎 福
   *    7991         禑
   *    7993..7996   禓 禔 禕 禖
   *    7998         禘
   *    799B..799D   禛 禜 禝
   *    79A1         禡
   *    79A6..79AB   禦 禧 禨 禩 禪 禫
   *    79AE..79B1   禮 禯 禰 禱
   *    79B3..79B4   禳 禴
   *    79B8..79BB   禸 禹 禺 离
   *    79BD..79C2   禽 禾 禿 秀 私 秂
   *    79C4         秄
   *    79C7..79CD   秇 秈 秉 秊 秋 秌 种
   *    79CF         秏
   *    79D1..79D2   科 秒
   *    79D4..79D6   秔 秕 秖
   *    79D8         秘
   *    79DA         秚
   *    79DD..79E7   秝 秞 租 秠 秡 秢 秣 秤 秥 秦 秧
   *    79E9..79ED   秩 秪 秫 秬 秭
   *    79F0..79F1   称 秱
   *    79F8         秸
   *    79FB..79FC   移 秼
   *    7A00         稀
   *    7A02..7A03   稂 稃
   *    7A07..7A0E   稇 稈 稉 稊 程 稌 稍 税
   *    7A11         稑
   *    7A14..7A15   稔 稕
   *    7A17..7A1C   稗 稘 稙 稚 稛 稜
   *    7A1E..7A21   稞 稟 稠 稡
   *    7A27         稧
   *    7A2B         稫
   *    7A2D..7A32   稭 種 稯 稰 稱 稲
   *    7A34..7A35   稴 稵
   *    7A37..7A40   稷 稸 稹 稺 稻 稼 稽 稾 稿 穀
   *    7A42..7A49   穂 穃 穄 穅 穆 穇 穈 穉
   *    7A4C..7A50   穌 積 穎 穏 穐
   *    7A55..7A57   穕 穖 穗
   *    7A59         穙
   *    7A5C..7A5D   穜 穝
   *    7A5F..7A63   穟 穠 穡 穢 穣
   *    7A65         穥
   *    7A67         穧
   *    7A69..7A6B   穩 穪 穫
   *    7A6D         穭
   *    7A70         穰
   *    7A74..7A76   穴 穵 究
   *    7A78..7A7A   穸 穹 空
   *    7A7D..7A86   穽 穾 穿 窀 突 窂 窃 窄 窅 窆
   *    7A88         窈
   *    7A8A..7A8B   窊 窋
   *    7A90..7A98   窐 窑 窒 窓 窔 窕 窖 窗 窘
   *    7A9E..7AA0   窞 窟 窠
   *    7AA3         窣
   *    7AA9..7AAA   窩 窪
   *    7AAC         窬
   *    7AAE..7AB0   窮 窯 窰
   *    7AB3         窳
   *    7AB5..7AB6   窵 窶
   *    7AB9..7ABC   窹 窺 窻 窼
   *    7ABE..7ABF   窾 窿
   *    7AC3..7ACF   竃 竄 竅 竆 竇 竈 竉 竊 立 竌 竍 竎 竏
   *    7AD1..7AD3   竑 竒 竓
   *    7AD5         竕
   *    7AD9..7ADD   站 竚 竛 竜 竝
   *    7ADF..7AE3   竟 章 竡 竢 竣
   *    7AE5..7AED   童 竦 竧 竨 竩 竪 竫 竬 竭
   *    7AEF..7AF1   端 竰 竱
   *    7AF4         竴
   *    7AF6         競
   *    7AF8..7AFB   竸 竹 竺 竻
   *    7AFD..7AFF   竽 竾 竿
   *    7B02         笂
   *    7B04         笄
   *    7B06..7B08   笆 笇 笈
   *    7B0A..7B0B   笊 笋
   *    7B0F         笏
   *    7B11..7B12   笑 笒
   *    7B14         笔
   *    7B18..7B19   笘 笙
   *    7B1B         笛
   *    7B1E..7B20   笞 笟 笠
   *    7B23         笣
   *    7B25..7B31   笥 符 笧 笨 笩 笪 笫 第 笭 笮 笯 笰 笱
   *    7B33..7B36   笳 笴 笵 笶
   *    7B39         笹
   *    7B3B         笻
   *    7B3D         笽
   *    7B3F..7B41   笿 筀 筁
   *    7B45..7B49   筅 筆 筇 筈 等
   *    7B4B..7B52   筋 筌 筍 筎 筏 筐 筑 筒
   *    7B54..7B56   答 筕 策
   *    7B5D         筝
   *    7B60         筠
   *    7B64..7B67   筤 筥 筦 筧
   *    7B69..7B6A   筩 筪
   *    7B6C..7B75   筬 筭 筮 筯 筰 筱 筲 筳 筴 筵
   *    7B77         筷
   *    7B79..7B7A   筹 筺
   *    7B7F         筿
   *    7B84         箄
   *    7B86..7B87   箆 箇
   *    7B89         箉
   *    7B8B         箋
   *    7B8D..7B92   箍 箎 箏 箐 箑 箒
   *    7B94..7BA1   箔 箕 箖 算 箘 箙 箚 箛 箜 箝 箞 箟 箠 管
   *    7BA5         箥
   *    7BAA         箪
   *    7BAC..7BAD   箬 箭
   *    7BAF..7BB2   箯 箰 箱 箲
   *    7BB4..7BB6   箴 箵 箶
   *    7BB8         箸
   *    7BBA..7BBD   箺 箻 箼 箽
   *    7BC0..7BC2   節 篁 篂
   *    7BC4..7BCC   範 篅 篆 篇 篈 築 篊 篋 篌
   *    7BCF         篏
   *    7BD4         篔
   *    7BD6..7BD7   篖 篗
   *    7BD9..7BDB   篙 篚 篛
   *    7BDD         篝
   *    7BE0         篠
   *    7BE4..7BE6   篤 篥 篦
   *    7BE8..7BEA   篨 篩 篪
   *    7BED         篭
   *    7BF0         篰
   *    7BF2..7BFA   篲 篳 篴 篵 篶 篷 篸 篹 篺
   *    7BFC         篼
   *    7BFE         篾
   *    7C00..7C04   簀 簁 簂 簃 簄
   *    7C06..7C07   簆 簇
   *    7C09         簉
   *    7C0B..7C0F   簋 簌 簍 簎 簏
   *    7C11..7C14   簑 簒 簓 簔
   *    7C17         簗
   *    7C19         簙
   *    7C1B         簛
   *    7C1E..7C21   簞 簟 簠 簡
   *    7C23         簣
   *    7C25..7C28   簥 簦 簧 簨
   *    7C2A..7C2C   簪 簫 簬
   *    7C31         簱
   *    7C33..7C34   簳 簴
   *    7C36..7C3A   簶 簷 簸 簹 簺
   *    7C3D..7C40   簽 簾 簿 籀
   *    7C43         籃
   *    7C45..7C46   籅 籆
   *    7C4A         籊
   *    7C4C..7C4D   籌 籍
   *    7C4F..7C61   籏 籐 籑 籒 籓 籔 籕 籖 籗 籘 籙 籚 籛 籜 籝 籞 籟 籠 籡
   *    7C63..7C65   籣 籤 籥
   *    7C67         籧
   *    7C69         籩
   *    7C6C..7C70   籬 籭 籮 籯 籰
   *    7C72..7C73   籲 米
   *    7C75         籵
   *    7C79         籹
   *    7C7C..7C7E   籼 籽 籾
   *    7C81..7C83   粁 粂 粃
   *    7C86..7C87   粆 粇
   *    7C89         粉
   *    7C8B         粋
   *    7C8D         粍
   *    7C8F..7C90   粏 粐
   *    7C92         粒
   *    7C94..7C95   粔 粕
   *    7C97..7C98   粗 粘
   *    7C9B         粛
   *    7C9E..7CA2   粞 粟 粠 粡 粢
   *    7CA4..7CA8   粤 粥 粦 粧 粨
   *    7CAB         粫
   *    7CAD..7CAE   粭 粮
   *    7CB0..7CB3   粰 粱 粲 粳
   *    7CB6..7CB7   粶 粷
   *    7CB9..7CC0   粹 粺 粻 粼 粽 精 粿 糀
   *    7CC2         糂
   *    7CC4..7CC5   糄 糅
   *    7CC7..7CCA   糇 糈 糉 糊
   *    7CCD..7CCF   糍 糎 糏
   *    7CD2..7CDA   糒 糓 糔 糕 糖 糗 糘 糙 糚
   *    7CDC..7CE0   糜 糝 糞 糟 糠
   *    7CE2         糢
   *    7CE6..7CE7   糦 糧
   *    7CE9         糩
   *    7CEB         糫
   *    7CEF         糯
   *    7CF2         糲
   *    7CF4..7CF6   糴 糵 糶
   *    7CF8         糸
   *    7CFA..7CFB   糺 系
   *    7CFE         糾
   *    7D00         紀
   *    7D02..7D0B   紂 紃 約 紅 紆 紇 紈 紉 紊 紋
   *    7D0D         納
   *    7D0F..7D1E   紏 紐 紑 紒 紓 純 紕 紖 紗 紘 紙 級 紛 紜 紝 紞
   *    7D20..7D23   素 紡 索 紣
   *    7D26         紦
   *    7D2A..7D33   紪 紫 紬 紭 紮 累 細 紱 紲 紳
   *    7D35         紵
   *    7D39..7D3A   紹 紺
   *    7D3C..7D48   紼 紽 紾 紿 絀 絁 終 絃 組 絅 絆 絇 絈
   *    7D4B..7D51   絋 経 絍 絎 絏 結 絑
   *    7D53         絓
   *    7D56..7D57   絖 絗
   *    7D59..7D5E   絙 絚 絛 絜 絝 絞
   *    7D61..7D63   絡 絢 絣
   *    7D65..7D68   絥 給 絧 絨
   *    7D6A         絪
   *    7D6E         絮
   *    7D70..7D73   絰 統 絲 絳
   *    7D75..7D76   絵 絶
   *    7D78..7D7B   絸 絹 絺 絻
   *    7D7D         絽
   *    7D7F         絿
   *    7D81..7D83   綁 綂 綃
   *    7D85..7D86   綅 綆
   *    7D88..7D89   綈 綉
   *    7D8B..7D8D   綋 綌 綍
   *    7D8F         綏
   *    7D91         綑
   *    7D93         經
   *    7D96..7D97   綖 綗
   *    7D99..7DA0   継 続 綛 綜 綝 綞 綟 綠
   *    7DA2..7DA3   綢 綣
   *    7DA6..7DA7   綦 綧
   *    7DAA..7DBB   綪 綫 綬 維 綮 綯 綰 綱 網 綳 綴 綵 綶 綷 綸 綹 綺 綻
   *    7DBD..7DC0   綽 綾 綿 緀
   *    7DC2..7DC7   緂 緃 緄 緅 緆 緇
   *    7DCA..7DCF   緊 緋 緌 緍 緎 総
   *    7DD1..7DD2   緑 緒
   *    7DD5..7DDA   緕 緖 緗 緘 緙 線
   *    7DDC..7DDE   緜 緝 緞
   *    7DE0..7DE6   締 緡 緢 緣 緤 緥 緦
   *    7DE8..7DED   編 緩 緪 緫 緬 緭
   *    7DEF         緯
   *    7DF1..7DF2   緱 緲
   *    7DF4..7DF6   練 緵 緶
   *    7DF9..7DFB   緹 緺 緻
   *    7E00..7E01   縀 縁
   *    7E04..7E05   縄 縅
   *    7E08..7E0B   縈 縉 縊 縋
   *    7E10..7E12   縐 縑 縒
   *    7E15         縕
   *    7E17         縗
   *    7E1B..7E23   縛 縜 縝 縞 縟 縠 縡 縢 縣
   *    7E26..7E28   縦 縧 縨
   *    7E2B..7E2F   縫 縬 縭 縮 縯
   *    7E31..7E33   縱 縲 縳
   *    7E35..7E37   縵 縶 縷
   *    7E39..7E3B   縹 縺 縻
   *    7E3D..7E3F   總 績 縿
   *    7E41         繁
   *    7E43..7E47   繃 繄 繅 繆 繇
   *    7E4A..7E4B   繊 繋
   *    7E4D..7E4E   繍 繎
   *    7E50         繐
   *    7E52         繒
   *    7E54..7E56   織 繕 繖
   *    7E58..7E5A   繘 繙 繚
   *    7E5D..7E5F   繝 繞 繟
   *    7E61..7E62   繡 繢
   *    7E65..7E67   繥 繦 繧
   *    7E69..7E6B   繩 繪 繫
   *    7E6D..7E70   繭 繮 繯 繰
   *    7E73         繳
   *    7E75         繵
   *    7E78..7E79   繸 繹
   *    7E7B..7E7F   繻 繼 繽 繾 繿
   *    7E81..7E83   纁 纂 纃
   *    7E86..7E8A   纆 纇 纈 纉 纊
   *    7E8C..7E96   續 纍 纎 纏 纐 纑 纒 纓 纔 纕 纖
   *    7E98         纘
   *    7E9A..7E9E   纚 纛 纜 纝 纞
   *    7F36         缶
   *    7F38         缸
   *    7F3A..7F3F   缺 缻 缼 缽 缾 缿
   *    7F43..7F45   罃 罄 罅
   *    7F47         罇
   *    7F4C..7F55   罌 罍 罎 罏 罐 网 罒 罓 罔 罕
   *    7F58         罘
   *    7F5B..7F5D   罛 罜 罝
   *    7F5F..7F61   罟 罠 罡
   *    7F63..7F6B   罣 罤 罥 罦 罧 罨 罩 罪 罫
   *    7F6D..7F6E   罭 置
   *    7F70..7F72   罰 罱 署
   *    7F75         罵
   *    7F77..7F79   罷 罸 罹
   *    7F7D..7F80   罽 罾 罿 羀
   *    7F82..7F83   羂 羃
   *    7F85..7F88   羅 羆 羇 羈
   *    7F8A..7F91   羊 羋 羌 羍 美 羏 羐 羑
   *    7F94         羔
   *    7F96..7F97   羖 羗
   *    7F9A         羚
   *    7F9C..7F9E   羜 羝 羞
   *    7FA1..7FA4   羡 羢 羣 群
   *    7FA6         羦
   *    7FA8..7FAA   羨 義 羪
   *    7FAD..7FAF   羭 羮 羯
   *    7FB2         羲
   *    7FB4         羴
   *    7FB6         羶
   *    7FB8..7FB9   羸 羹
   *    7FBC..7FBD   羼 羽
   *    7FBF..7FC1   羿 翀 翁
   *    7FC3         翃
   *    7FC5..7FC6   翅 翆
   *    7FC8         翈
   *    7FCA         翊
   *    7FCC         翌
   *    7FCE..7FCF   翎 翏
   *    7FD2         習
   *    7FD4..7FD5   翔 翕
   *    7FDB         翛
   *    7FDF..7FE1   翟 翠 翡
   *    7FE3         翣
   *    7FE5..7FE6   翥 翦
   *    7FE8..7FE9   翨 翩
   *    7FEB..7FEC   翫 翬
   *    7FEE..7FF0   翮 翯 翰
   *    7FF2..7FF3   翲 翳
   *    7FF9..8008   翹 翺 翻 翼 翽 翾 翿 耀 老 耂 考 耄 者 耆 耇 耈
   *    800A..8019   耊 耋 而 耍 耎 耏 耐 耑 耒 耓 耔 耕 耖 耗 耘 耙
   *    801C..8021   耜 耝 耞 耟 耠 耡
   *    8024         耤
   *    8026         耦
   *    8028         耨
   *    802C         耬
   *    802E         耮
   *    8030         耰
   *    8033..8037   耳 耴 耵 耶 耷
   *    8039..8040   耹 耺 耻 耼 耽 耾 耿 聀
   *    8043..8044   聃 聄
   *    8046         聆
   *    804A         聊
   *    8052         聒
   *    8056         聖
   *    8058         聘
   *    805A         聚
   *    805E..8062   聞 聟 聠 聡 聢
   *    8064         聤
   *    8066         聦
   *    8068         聨
   *    806D         聭
   *    806F..8077   聯 聰 聱 聲 聳 聴 聵 聶 職
   *    8079         聹
   *    807B         聻
   *    807D..807F   聽 聾 聿
   *    8081         肁
   *    8084..8089   肄 肅 肆 肇 肈 肉
   *    808B..808C   肋 肌
   *    808E         肎
   *    8093         肓
   *    8096         肖
   *    8098..809E   肘 肙 肚 肛 肜 肝 肞
   *    80A1..80A2   股 肢
   *    80A4..80A7   肤 肥 肦 肧
   *    80A9..80AD   肩 肪 肫 肬 肭
   *    80AF         肯
   *    80B1..80B2   肱 育
   *    80B4         肴
   *    80B8..80BA   肸 肹 肺
   *    80C3..80C6   胃 胄 胅 胆
   *    80C8         胈
   *    80CA         胊
   *    80CC..80CF   背 胍 胎 胏
   *    80D2         胒
   *    80D4..80DB   胔 胕 胖 胗 胘 胙 胚 胛
   *    80DD..80DE   胝 胞
   *    80E0..80E1   胠 胡
   *    80E4..80E6   胤 胥 胦
   *    80ED..80F6   胭 胮 胯 胰 胱 胲 胳 胴 胵 胶
   *    80F8..80FE   胸 胹 胺 胻 胼 能 胾
   *    8102..8103   脂 脃
   *    8105..810B   脅 脆 脇 脈 脉 脊 脋
   *    810D         脍
   *    8116..8118   脖 脗 脘
   *    811A..811C   脚 脛 脜
   *    811E         脞
   *    8120         脠
   *    8123..8124   脣 脤
   *    8127         脧
   *    8129         脩
   *    812C         脬
   *    812F..8131   脯 脰 脱
   *    8133         脳
   *    8135         脵
   *    8139..813A   脹 脺
   *    813C..813E   脼 脽 脾
   *    8145..8147   腅 腆 腇
   *    814A..814C   腊 腋 腌
   *    814E         腎
   *    8150..8155   腐 腑 腒 腓 腔 腕
   *    8157         腗
   *    815F..8161   腟 腠 腡
   *    8165..8169   腥 腦 腧 腨 腩
   *    816B         腫
   *    816D..8171   腭 腮 腯 腰 腱
   *    8174         腴
   *    8177..817A   腷 腸 腹 腺
   *    817F..8186   腿 膀 膁 膂 膃 膄 膅 膆
   *    8188         膈
   *    818A..818B   膊 膋
   *    818E..8190   膎 膏 膐
   *    8193         膓
   *    8195..8196   膕 膖
   *    8198         膘
   *    819A..819E   膚 膛 膜 膝 膞
   *    81A0         膠
   *    81A2..81A4   膢 膣 膤
   *    81A8..81A9   膨 膩
   *    81AE         膮
   *    81B0         膰
   *    81B2..81B5   膲 膳 膴 膵
   *    81B8         膸
   *    81BA..81BB   膺 膻
   *    81BD..81C3   膽 膾 膿 臀 臁 臂 臃
   *    81C5..81C6   臅 臆
   *    81C8..81CB   臈 臉 臊 臋
   *    81CD..81CF   臍 臎 臏
   *    81D1         臑
   *    81D3         臓
   *    81D5..81DB   臕 臖 臗 臘 臙 臚 臛
   *    81DD..81E1   臝 臞 臟 臠 臡
   *    81E3..81E5   臣 臤 臥
   *    81E7..81E8   臧 臨
   *    81EA..81ED   自 臫 臬 臭
   *    81F0..81F6   臰 臱 臲 至 致 臵 臶
   *    81F8..8205   臸 臹 臺 臻 臼 臽 臾 臿 舀 舁 舂 舃 舄 舅
   *    8207..820A   與 興 舉 舊
   *    820C..8210   舌 舍 舎 舏 舐
   *    8212..8214   舒 舓 舔
   *    8216..821F   舖 舗 舘 舙 舚 舛 舜 舝 舞 舟
   *    8221..8222   舡 舢
   *    8228..822C   舨 舩 航 舫 般
   *    822E         舮
   *    8232..823A   舲 舳 舴 舵 舶 舷 舸 船 舺
   *    823C         舼
   *    8240         艀
   *    8243..8247   艃 艄 艅 艆 艇
   *    8249         艉
   *    824B         艋
   *    824E..824F   艎 艏
   *    8251         艑
   *    8256..825A   艖 艗 艘 艙 艚
   *    825C..825D   艜 艝
   *    825F..8260   艟 艠
   *    8262..8264   艢 艣 艤
   *    8266..8268   艦 艧 艨
   *    826A..826B   艪 艫
   *    826D..826F   艭 艮 良
   *    8271..8272   艱 色
   *    8274         艴
   *    8276..8279   艶 艷 艸 艹
   *    827B         艻
   *    827D..8281   艽 艾 艿 芀 芁
   *    8283..8284   芃 芄
   *    8287         芇
   *    8289..828B   芉 芊 芋
   *    828D..828E   芍 芎
   *    8291..8294   芑 芒 芓 芔
   *    8296         芖
   *    8298..829B   芘 芙 芚 芛
   *    829D         芝
   *    829F..82A1   芟 芠 芡
   *    82A3..82B4   芣 芤 芥 芦 芧 芨 芩 芪 芫 芬 芭 芮 芯 芰 花 芲 芳 芴
   *    82B7..82BF   芷 芸 芹 芺 芻 芼 芽 芾 芿
   *    82C5..82C6   苅 苆
   *    82D0..82D5   苐 苑 苒 苓 苔 苕
   *    82D7         苗
   *    82D9..82DC   苙 苚 苛 苜
   *    82DE..82E8   苞 苟 苠 苡 苢 苣 苤 若 苦 苧 苨
   *    82EA..82EB   苪 苫
   *    82ED         苭
   *    82EF         苯
   *    82F1         英
   *    82F3..82F4   苳 苴
   *    82F6..82F7   苶 苷
   *    82F9..82FB   苹 苺 苻
   *    82FD..82FE   苽 苾
   *    8300..830C   茀 茁 茂 范 茄 茅 茆 茇 茈 茉 茊 茋 茌
   *    830E         茎
   *    8316..8318   茖 茗 茘
   *    831B..831F   茛 茜 茝 茞 茟
   *    8321..8323   茡 茢 茣
   *    8328         茨
   *    832B..833A   茫 茬 茭 茮 茯 茰 茱 茲 茳 茴 茵 茶 茷 茸 茹 茺
   *    833C..833D   茼 茽
   *    8340         荀
   *    8342..8345   荂 荃 荄 荅
   *    8347         荇
   *    8349..834A   草 荊
   *    834D..8358   荍 荎 荏 荐 荑 荒 荓 荔 荕 荖 荗 荘
   *    8362..8363   荢 荣
   *    8370         荰
   *    8373         荳
   *    8375         荵
   *    8377..8378   荷 荸
   *    837B..837D   荻 荼 荽
   *    837F..8380   荿 莀
   *    8382         莂
   *    8384..8387   莄 莅 莆 莇
   *    8389..838A   莉 莊
   *    838D..838E   莍 莎
   *    8392..8396   莒 莓 莔 莕 莖
   *    8398..83A0   莘 莙 莚 莛 莜 莝 莞 莟 莠
   *    83A2         莢
   *    83A6..83AD   莦 莧 莨 莩 莪 莫 莬 莭
   *    83B1         莱
   *    83B5         莵
   *    83BD..83C1   莽 莾 莿 菀 菁
   *    83C5         菅
   *    83C7         菇
   *    83C9..83CA   菉 菊
   *    83CC         菌
   *    83CE..83D1   菎 菏 菐 菑
   *    83D3..83D4   菓 菔
   *    83D6         菖
   *    83D8         菘
   *    83DC..83DD   菜 菝
   *    83DF..83E1   菟 菠 菡
   *    83E5         菥
   *    83E8..83EB   菨 菩 菪 菫
   *    83EF..83F2   華 菰 菱 菲
   *    83F4         菴
   *    83F6..83F9   菶 菷 菸 菹
   *    83FB..83FD   菻 菼 菽
   *    8401         萁
   *    8403..8404   萃 萄
   *    8406..8407   萆 萇
   *    840A..840F   萊 萋 萌 萍 萎 萏
   *    8411         萑
   *    8413         萓
   *    8415         萕
   *    8417         萗
   *    8419         萙
   *    8420         萠
   *    8422         萢
   *    8429..842A   萩 萪
   *    842C         萬
   *    842F         萯
   *    8431         萱
   *    8435         萵
   *    8438..8439   萸 萹
   *    843C..843D   萼 落
   *    8445..844A   葅 葆 葇 葈 葉 葊
   *    844D..844F   葍 葎 葏
   *    8451..8452   葑 葒
   *    8456..845C   葖 著 葘 葙 葚 葛 葜
   *    845F..8467   葟 葠 葡 葢 董 葤 葥 葦 葧
   *    8469..8471   葩 葪 葫 葬 葭 葮 葯 葰 葱
   *    8473..847A   葳 葴 葵 葶 葷 葸 葹 葺
   *    847C..847D   葼 葽
   *    8481..8482   蒁 蒂
   *    8484..8485   蒄 蒅
   *    848B         蒋
   *    8490         蒐
   *    8492..8495   蒒 蒓 蒔 蒕
   *    8497         蒗
   *    8499         蒙
   *    849C         蒜
   *    849E..849F   蒞 蒟
   *    84A1         蒡
   *    84A6         蒦
   *    84A8..84AA   蒨 蒩 蒪
   *    84AD         蒭
   *    84AF         蒯
   *    84B1..84B2   蒱 蒲
   *    84B4         蒴
   *    84B8..84C2   蒸 蒹 蒺 蒻 蒼 蒽 蒾 蒿 蓀 蓁 蓂
   *    84C4         蓄
   *    84C6..84D1   蓆 蓇 蓈 蓉 蓊 蓋 蓌 蓍 蓎 蓏 蓐 蓑
   *    84D3         蓓
   *    84D6         蓖
   *    84D9..84DA   蓙 蓚
   *    84DC         蓜
   *    84E7         蓧
   *    84EA         蓪
   *    84EC         蓬
   *    84EE..84F2   蓮 蓯 蓰 蓱 蓲
   *    84F4         蓴
   *    84F7         蓷
   *    84FA..84FD   蓺 蓻 蓼 蓽
   *    84FF..8500   蓿 蔀
   *    8502..8503   蔂 蔃
   *    8506..8507   蔆 蔇
   *    850C         蔌
   *    850E         蔎
   *    8510..8511   蔐 蔑
   *    8513..8515   蔓 蔔 蔕
   *    8517..8518   蔗 蔘
   *    851A..851C   蔚 蔛 蔜
   *    851E..851F   蔞 蔟
   *    8521..8527   蔡 蔢 蔣 蔤 蔥 蔦 蔧
   *    852A..852D   蔪 蔫 蔬 蔭
   *    852F         蔯
   *    8532..8536   蔲 蔳 蔴 蔵 蔶
   *    853D..8541   蔽 蔾 蔿 蕀 蕁
   *    8543         蕃
   *    8546         蕆
   *    8548..854B   蕈 蕉 蕊 蕋
   *    854E..8553   蕎 蕏 蕐 蕑 蕒 蕓
   *    8555..855A   蕕 蕖 蕗 蕘 蕙 蕚
   *    855C..8564   蕜 蕝 蕞 蕟 蕠 蕡 蕢 蕣 蕤
   *    8568..856B   蕨 蕩 蕪 蕫
   *    856D         蕭
   *    856F         蕯
   *    8577         蕷
   *    8579..857B   蕹 蕺 蕻
   *    857D..8581   蕽 蕾 蕿 薀 薁
   *    8584..858C   薄 薅 薆 薇 薈 薉 薊 薋 薌
   *    858F..8591   薏 薐 薑
   *    8593..8594   薓 薔
   *    8597..8599   薗 薘 薙
   *    859B..859D   薛 薜 薝
   *    859F..85A0   薟 薠
   *    85A2         薢
   *    85A4..85B0   薤 薥 薦 薧 薨 薩 薪 薫 薬 薭 薮 薯 薰
   *    85B4         薴
   *    85B6..85BA   薶 薷 薸 薹 薺
   *    85BC..85BF   薼 薽 薾 薿
   *    85C1..85C2   藁 藂
   *    85C7         藇
   *    85C9..85CB   藉 藊 藋
   *    85CD..85D0   藍 藎 藏 藐
   *    85D5         藕
   *    85D8..85DA   藘 藙 藚
   *    85DC..85DD   藜 藝
   *    85DF..85E1   藟 藠 藡
   *    85E4..85E6   藤 藥 藦
   *    85E8..85EA   藨 藩 藪
   *    85ED         藭
   *    85F3         藳
   *    85F6..85F7   藶 藷
   *    85F9..85FC   藹 藺 藻 藼
   *    85FE..8600   藾 藿 蘀
   *    8602         蘂
   *    8604..8607   蘄 蘅 蘆 蘇
   *    860A..860B   蘊 蘋
   *    860D..860E   蘍 蘎
   *    8610..8613   蘐 蘑 蘒 蘓
   *    8616..861B   蘖 蘗 蘘 蘙 蘚 蘛
   *    861E         蘞
   *    8621..8622   蘡 蘢
   *    8624         蘤
   *    8627         蘧
   *    8629         蘩
   *    862D         蘭
   *    862F..8630   蘯 蘰
   *    8636         蘶
   *    8638..863A   蘸 蘹 蘺
   *    863C..863D   蘼 蘽
   *    863F..8642   蘿 虀 虁 虂
   *    8646         虆
   *    864D..864E   虍 虎
   *    8650         虐
   *    8652..8664   虒 虓 虔 處 虖 虗 虘 虙 虚 虛 虜 虝 虞 號 虠 虡 虢 虣 虤
   *    8667         虧
   *    8669         虩
   *    866B..866C   虫 虬
   *    866F         虯
   *    8671         虱
   *    8675..8677   虵 虶 虷
   *    8679..867B   虹 虺 虻
   *    8687..868D   蚇 蚈 蚉 蚊 蚋 蚌 蚍
   *    8691         蚑
   *    8693         蚓
   *    8695..8696   蚕 蚖
   *    8698         蚘
   *    869A         蚚
   *    869C..869D   蚜 蚝
   *    86A1         蚡
   *    86A3..86A4   蚣 蚤
   *    86A6..86AB   蚦 蚧 蚨 蚩 蚪 蚫
   *    86AD         蚭
   *    86AF..86B1   蚯 蚰 蚱
   *    86B3..86B9   蚳 蚴 蚵 蚶 蚷 蚸 蚹
   *    86BF..86C1   蚿 蛀 蛁
   *    86C3..86C7   蛃 蛄 蛅 蛆 蛇
   *    86C9         蛉
   *    86CB         蛋
   *    86CD..86CE   蛍 蛎
   *    86D1..86D2   蛑 蛒
   *    86D4..86D5   蛔 蛕
   *    86D7         蛗
   *    86D9..86DC   蛙 蛚 蛛 蛜
   *    86DE..86E0   蛞 蛟 蛠
   *    86E3..86E7   蛣 蛤 蛥 蛦 蛧
   *    86E9         蛩
   *    86EC..86EF   蛬 蛭 蛮 蛯
   *    86F8..86FE   蛸 蛹 蛺 蛻 蛼 蛽 蛾
   *    8700         蜀
   *    8702..870B   蜂 蜃 蜄 蜅 蜆 蜇 蜈 蜉 蜊 蜋
   *    870D..8714   蜍 蜎 蜏 蜐 蜑 蜒 蜓 蜔
   *    8718..871A   蜘 蜙 蜚
   *    871C         蜜
   *    871E..871F   蜞 蜟
   *    8721         蜡
   *    8723         蜣
   *    8725         蜥
   *    8728..8729   蜨 蜩
   *    872E..872F   蜮 蜯
   *    8731..8732   蜱 蜲
   *    8734         蜴
   *    8737         蜷
   *    8739..8740   蜹 蜺 蜻 蜼 蜽 蜾 蜿 蝀
   *    8743         蝃
   *    8745         蝅
   *    8749         蝉
   *    874B..874E   蝋 蝌 蝍 蝎
   *    8751         蝑
   *    8753         蝓
   *    8755         蝕
   *    8757..8759   蝗 蝘 蝙
   *    875D         蝝
   *    875F..8761   蝟 蝠 蝡
   *    8763..8766   蝣 蝤 蝥 蝦
   *    8768         蝨
   *    876A         蝪
   *    876E..876F   蝮 蝯
   *    8771..8772   蝱 蝲
   *    8774         蝴
   *    8776         蝶
   *    8778         蝸
   *    877B..877C   蝻 蝼
   *    877F         蝿
   *    8782..8789   螂 螃 螄 螅 螆 螇 螈 螉
   *    878B..878D   螋 螌 融
   *    8790         螐
   *    8793         螓
   *    8795         螕
   *    8797..8799   螗 螘 螙
   *    879E..87A0   螞 螟 螠
   *    87A2..87A3   螢 螣
   *    87A7         螧
   *    87AB..87AF   螫 螬 螭 螮 螯
   *    87B1         螱
   *    87B3         螳
   *    87B5         螵
   *    87BA..87BB   螺 螻
   *    87BD..87C1   螽 螾 螿 蟀 蟁
   *    87C4         蟄
   *    87C6..87CB   蟆 蟇 蟈 蟉 蟊 蟋
   *    87CE         蟎
   *    87D0         蟐
   *    87D2         蟒
   *    87D5..87D6   蟕 蟖
   *    87D9..87DA   蟙 蟚
   *    87DC         蟜
   *    87DF..87E0   蟟 蟠
   *    87E2..87E6   蟢 蟣 蟤 蟥 蟦
   *    87EA..87ED   蟪 蟫 蟬 蟭
   *    87EF         蟯
   *    87F1..87F3   蟱 蟲 蟳
   *    87F5..87FB   蟵 蟶 蟷 蟸 蟹 蟺 蟻
   *    87FE..87FF   蟾 蟿
   *    8801         蠁
   *    8803         蠃
   *    8805..8806   蠅 蠆
   *    8809..880B   蠉 蠊 蠋
   *    880D..8816   蠍 蠎 蠏 蠐 蠑 蠒 蠓 蠔 蠕 蠖
   *    8818..881C   蠘 蠙 蠚 蠛 蠜
   *    881E..881F   蠞 蠟
   *    8821..8823   蠡 蠢 蠣
   *    8827..8828   蠧 蠨
   *    882D..882E   蠭 蠮
   *    8830..8832   蠰 蠱 蠲
   *    8835..8836   蠵 蠶
   *    8839..883C   蠹 蠺 蠻 蠼
   *    8840..8846   血 衁 衂 衃 衄 衅 衆
   *    8848..884E   衈 衉 衊 衋 行 衍 衎
   *    8851..8853   衑 衒 術
   *    8855..8864   衕 衖 街 衘 衙 衚 衛 衜 衝 衞 衟 衠 衡 衢 衣 衤
   *    8868..8869   表 衩
   *    886B         衫
   *    886F..8872   衯 衰 衱 衲
   *    8875         衵
   *    8877         衷
   *    8879         衹
   *    887B         衻
   *    887D..8882   衽 衾 衿 袀 袁 袂
   *    8888         袈
   *    888B         袋
   *    888D         袍
   *    8892         袒
   *    8896..889C   袖 袗 袘 袙 袚 袛 袜
   *    889E..88A0   袞 袟 袠
   *    88A2         袢
   *    88A4         袤
   *    88A8         袨
   *    88AA..88AB   袪 被
   *    88AE         袮
   *    88B0..88B1   袰 袱
   *    88B4..88B5   袴 袵
   *    88B7         袷
   *    88BA         袺
   *    88BC..88C5   袼 袽 袾 袿 裀 裁 裂 裃 裄 装
   *    88CA..88CF   裊 裋 裌 裍 裎 裏
   *    88D1..88D5   裑 裒 裓 裔 裕
   *    88D8..88D9   裘 裙
   *    88DB..88DF   裛 補 裝 裞 裟
   *    88E1         裡
   *    88E7..88E8   裧 裨
   *    88EF..88F5   裯 裰 裱 裲 裳 裴 裵
   *    88F7..88F9   裷 裸 裹
   *    88FC..88FE   裼 製 裾
   *    8901..8902   褁 褂
   *    8904         褄
   *    8906..8907   褆 複
   *    890A         褊
   *    890C..8910   褌 褍 褎 褏 褐
   *    8912..8913   褒 褓
   *    8915..8916   褕 褖
   *    8918..891A   褘 褙 褚
   *    891C..891E   褜 褝 褞
   *    8920         褠
   *    8925..8928   褥 褦 褧 褨
   *    892A..892B   褪 褫
   *    8930..8932   褰 褱 褲
   *    8935..893B   褵 褶 褷 褸 褹 褺 褻
   *    893E         褾
   *    8940..8946   襀 襁 襂 襃 襄 襅 襆
   *    8949         襉
   *    894C..894D   襌 襍
   *    894F         襏
   *    8952         襒
   *    8956..8957   襖 襗
   *    895A..895C   襚 襛 襜
   *    895E..8964   襞 襟 襠 襡 襢 襣 襤
   *    8966         襦
   *    896A..896B   襪 襫
   *    896D..8970   襭 襮 襯 襰
   *    8972..8975   襲 襳 襴 襵
   *    8977         襷
   *    897A..8981   襺 襻 襼 襽 襾 西 覀 要
   *    8983         覃
   *    8986..898B   覆 覇 覈 覉 覊 見
   *    898D         覍
   *    898F..8990   規 覐
   *    8993..8998   覓 覔 覕 視 覗 覘
   *    899A..899C   覚 覛 覜
   *    899F..89A1   覟 覠 覡
   *    89A5..89A7   覥 覦 覧
   *    89A9..89AA   覩 親
   *    89AC         覬
   *    89AF..89B0   覯 覰
   *    89B2..89B7   覲 観 覴 覵 覶 覷
   *    89BA         覺
   *    89BC..89BD   覼 覽
   *    89BF..89C0   覿 觀
   *    89D2         角
   *    89D4..89D8   觔 觕 觖 觗 觘
   *    89DA         觚
   *    89DC..89DD   觜 觝
   *    89E3         解
   *    89E5..89E7   觥 触 觧
   *    89E9         觩
   *    89EB         觫
   *    89ED         觭
   *    89F1         觱
   *    89F3..89F4   觳 觴
   *    89F6         觶
   *    89F8..89F9   觸 觹
   *    89FD         觽
   *    89FF..8A00   觿 言
   *    8A02..8A05   訂 訃 訄 訅
   *    8A07..8A08   訇 計
   *    8A0A         訊
   *    8A0C         訌
   *    8A0E..8A18   討 訏 訐 訑 訒 訓 訔 訕 訖 託 記
   *    8A1B         訛
   *    8A1D..8A26   訝 訞 訟 訠 訡 訢 訣 訤 訥 訦
   *    8A2A..8A2D   訪 訫 訬 設
   *    8A2F         訯
   *    8A31         許
   *    8A33..8A37   訳 訴 訵 訶 訷
   *    8A3A..8A3E   診 註 証 訽 訾
   *    8A40..8A41   詀 詁
   *    8A43         詃
   *    8A45..8A49   詅 詆 詇 詈 詉
   *    8A4D..8A4E   詍 詎
   *    8A50..8A58   詐 詑 詒 詓 詔 評 詖 詗 詘
   *    8A5B..8A5E   詛 詜 詝 詞
   *    8A60..8A63   詠 詡 詢 詣
   *    8A65..8A67   詥 試 詧
   *    8A69         詩
   *    8A6B..8A6E   詫 詬 詭 詮
   *    8A70..8A73   詰 話 該 詳
   *    8A75..8A77   詵 詶 詷
   *    8A79..8A7C   詹 詺 詻 詼
   *    8A7E..8A80   詾 詿 誀
   *    8A82..8A87   誂 誃 誄 誅 誆 誇
   *    8A89         誉
   *    8A8B..8A8D   誋 誌 認
   *    8A8F..8A93   誏 誐 誑 誒 誓
   *    8A95..8A9A   誕 誖 誗 誘 誙 誚
   *    8A9E..8AA1   語 誟 誠 誡
   *    8AA3..8AA9   誣 誤 誥 誦 誧 誨 誩
   *    8AAC..8AB0   説 読 誮 誯 誰
   *    8AB2..8AB3   課 誳
   *    8AB6..8AB7   誶 誷
   *    8AB9         誹
   *    8ABB..8ABC   誻 誼
   *    8ABE..8ABF   誾 調
   *    8AC2..8AC4   諂 諃 諄
   *    8AC6..8ACD   諆 談 諈 諉 諊 請 諌 諍
   *    8ACF..8AD7   諏 諐 諑 諒 諓 諔 諕 論 諗
   *    8ADA..8AE2   諚 諛 諜 諝 諞 諟 諠 諡 諢
   *    8AE4         諤
   *    8AE6..8AE7   諦 諧
   *    8AEB..8AEE   諫 諬 諭 諮
   *    8AF0..8AF1   諰 諱
   *    8AF3..8AF8   諳 諴 諵 諶 諷 諸
   *    8AFA         諺
   *    8AFC         諼
   *    8AFE..8B02   諾 諿 謀 謁 謂
   *    8B04..8B07   謄 謅 謆 謇
   *    8B0A..8B0E   謊 謋 謌 謍 謎
   *    8B10..8B11   謐 謑
   *    8B14         謔
   *    8B16..8B17   謖 謗
   *    8B19..8B21   謙 謚 講 謜 謝 謞 謟 謠 謡
   *    8B26         謦
   *    8B28         謨
   *    8B2B..8B2D   謫 謬 謭
   *    8B30         謰
   *    8B33         謳
   *    8B37         謷
   *    8B39         謹
   *    8B3C         謼
   *    8B3E         謾
   *    8B41..8B46   譁 譂 譃 譄 譅 譆
   *    8B48..8B49   譈 證
   *    8B4C..8B4F   譌 譍 譎 譏
   *    8B51..8B54   譑 譒 譓 譔
   *    8B56         譖
   *    8B58..8B5C   識 譙 譚 譛 譜
   *    8B5E..8B5F   譞 譟
   *    8B63         譣
   *    8B66         警
   *    8B69         譩
   *    8B6B..8B6D   譫 譬 譭
   *    8B6F..8B72   譯 議 譱 譲
   *    8B74         譴
   *    8B76..8B79   譶 護 譸 譹
   *    8B7C..8B81   譼 譽 譾 譿 讀 讁
   *    8B83..8B85   讃 讄 讅
   *    8B8A..8B90   變 讋 讌 讍 讎 讏 讐
   *    8B92..8B96   讒 讓 讔 讕 讖
   *    8B99..8B9A   讙 讚
   *    8B9C..8B9F   讜 讝 讞 讟
   *    8C37..8C3A   谷 谸 谹 谺
   *    8C3D..8C3F   谽 谾 谿
   *    8C41         豁
   *    8C45..8C4C   豅 豆 豇 豈 豉 豊 豋 豌
   *    8C4E..8C51   豎 豏 豐 豑
   *    8C53..8C55   豓 豔 豕
   *    8C57..8C5B   豗 豘 豙 豚 豛
   *    8C5D         豝
   *    8C61..8C64   象 豢 豣 豤
   *    8C66         豦
   *    8C68..8C6D   豨 豩 豪 豫 豬 豭
   *    8C73         豳
   *    8C75..8C76   豵 豶
   *    8C78..8C7C   豸 豹 豺 豻 豼
   *    8C7E         豾
   *    8C82         貂
   *    8C85..8C87   貅 貆 貇
   *    8C89..8C8E   貉 貊 貋 貌 貍 貎
   *    8C90         貐
   *    8C92..8C94   貒 貓 貔
   *    8C98..8C99   貘 貙
   *    8C9B..8C9E   貛 貜 貝 貞
   *    8CA0..8CA2   負 財 貢
   *    8CA4         貤
   *    8CA7..8CB0   貧 貨 販 貪 貫 責 貭 貮 貯 貰
   *    8CB2..8CB4   貲 貳 貴
   *    8CB6..8CBD   貶 買 貸 貹 貺 費 貼 貽
   *    8CBF..8CCB   貿 賀 賁 賂 賃 賄 賅 賆 資 賈 賉 賊 賋
   *    8CCD..8CCF   賍 賎 賏
   *    8CD1         賑
   *    8CD3         賓
   *    8CD5..8CD6   賕 賖
   *    8CD9..8CDE   賙 賚 賛 賜 賝 賞
   *    8CE0..8CE4   賠 賡 賢 賣 賤
   *    8CE6         賦
   *    8CE8         賨
   *    8CEA         質
   *    8CEC..8CED   賬 賭
   *    8CEF..8CF2   賯 賰 賱 賲
   *    8CF4..8CF5   賴 賵
   *    8CF7..8CF8   賷 賸
   *    8CFA..8CFF   賺 賻 購 賽 賾 賿
   *    8D01         贁
   *    8D03..8D05   贃 贄 贅
   *    8D07..8D0B   贇 贈 贉 贊 贋
   *    8D0D..8D10   贍 贎 贏 贐
   *    8D12..8D14   贒 贓 贔
   *    8D16..8D17   贖 贗
   *    8D1B         贛
   *    8D64..8D67   赤 赥 赦 赧
   *    8D69         赩
   *    8D6B..8D6E   赫 赬 赭 赮
   *    8D70..8D71   走 赱
   *    8D73..8D74   赳 赴
   *    8D77         起
   *    8D7F         赿
   *    8D81..8D82   趁 趂
   *    8D84..8D85   趄 超
   *    8D88         趈
   *    8D8A         越
   *    8D8D         趍
   *    8D90..8D91   趐 趑
   *    8D95         趕
   *    8D99         趙
   *    8D9E..8DA0   趞 趟 趠
   *    8DA3         趣
   *    8DA6         趦
   *    8DA8         趨
   *    8DAB..8DAC   趫 趬
   *    8DAF         趯
   *    8DB2..8DB3   趲 足
   *    8DB5         趵
   *    8DB7         趷
   *    8DB9..8DBC   趹 趺 趻 趼
   *    8DBE         趾
   *    8DC0         跀
   *    8DC2         跂
   *    8DC5..8DC8   跅 跆 跇 跈
   *    8DCA..8DCC   跊 跋 跌
   *    8DCE..8DCF   跎 跏
   *    8DD1         跑
   *    8DD4..8DD7   跔 跕 跖 跗
   *    8DD9..8DDB   跙 跚 跛
   *    8DDD         距
   *    8DDF         跟
   *    8DE1         跡
   *    8DE3..8DE5   跣 跤 跥
   *    8DE7..8DE8   跧 跨
   *    8DEA..8DEC   跪 跫 跬
   *    8DEF..8DF5   路 跰 跱 跲 跳 跴 践
   *    8DFC..8DFD   跼 跽
   *    8DFF         跿
   *    8E01         踁
   *    8E04..8E06   踄 踅 踆
   *    8E08..8E0C   踈 踉 踊 踋 踌
   *    8E0F..8E11   踏 踐 踑
   *    8E14         踔
   *    8E16         踖
   *    8E1D..8E23   踝 踞 踟 踠 踡 踢 踣
   *    8E26..8E27   踦 踧
   *    8E2A         踪
   *    8E30..8E31   踰 踱
   *    8E33..8E39   踳 踴 踵 踶 踷 踸 踹
   *    8E3D         踽
   *    8E40..8E42   蹀 蹁 蹂
   *    8E44         蹄
   *    8E47..8E50   蹇 蹈 蹉 蹊 蹋 蹌 蹍 蹎 蹏 蹐
   *    8E54..8E55   蹔 蹕
   *    8E59         蹙
   *    8E5B..8E64   蹛 蹜 蹝 蹞 蹟 蹠 蹡 蹢 蹣 蹤
   *    8E69         蹩
   *    8E6C..8E6D   蹬 蹭
   *    8E6F..8E72   蹯 蹰 蹱 蹲
   *    8E74         蹴
   *    8E76         蹶
   *    8E79..8E7C   蹹 蹺 蹻 蹼
   *    8E81..8E85   躁 躂 躃 躄 躅
   *    8E87         躇
   *    8E89..8E8B   躉 躊 躋
   *    8E8D         躍
   *    8E90..8E95   躐 躑 躒 躓 躔 躕
   *    8E98..8E9B   躘 躙 躚 躛
   *    8E9D..8E9E   躝 躞
   *    8EA1..8EA2   躡 躢
   *    8EA7         躧
   *    8EA9..8EB1   躩 躪 身 躬 躭 躮 躯 躰 躱
   *    8EB3         躳
   *    8EB5..8EB6   躵 躶
   *    8EBA..8EBB   躺 躻
   *    8EBE         躾
   *    8EC0..8EC1   軀 軁
   *    8EC3..8EC8   軃 軄 軅 軆 軇 軈
   *    8ECA..8ECD   車 軋 軌 軍
   *    8ECF         軏
   *    8ED1..8ED2   軑 軒
   *    8ED4         軔
   *    8EDB..8EDC   軛 軜
   *    8EDF         軟
   *    8EE2..8EE3   転 軣
   *    8EE8         軨
   *    8EEB         軫
   *    8EED..8EEE   軭 軮
   *    8EF0..8EF1   軰 軱
   *    8EF7..8EFE   軷 軸 軹 軺 軻 軼 軽 軾
   *    8F00         輀
   *    8F02..8F03   輂 較
   *    8F05         輅
   *    8F07..8F0A   輇 輈 載 輊
   *    8F0C         輌
   *    8F0F..8F10   輏 輐
   *    8F12..8F19   輒 輓 輔 輕 輖 輗 輘 輙
   *    8F1B..8F21   輛 輜 輝 輞 輟 輠 輡
   *    8F23         輣
   *    8F25..8F2F   輥 輦 輧 輨 輩 輪 輫 輬 輭 輮 輯
   *    8F33..8F3B   輳 輴 輵 輶 輷 輸 輹 輺 輻
   *    8F3E..8F47   輾 輿 轀 轁 轂 轃 轄 轅 轆 轇
   *    8F49..8F4A   轉 轊
   *    8F4C..8F4F   轌 轍 轎 轏
   *    8F51..8F55   轑 轒 轓 轔 轕
   *    8F57..8F58   轗 轘
   *    8F5C..8F5F   轜 轝 轞 轟
   *    8F61..8F65   轡 轢 轣 轤 轥
   *    8F9B..8FA1   辛 辜 辝 辞 辟 辠 辡
   *    8FA3..8FA8   辣 辤 辥 辦 辧 辨
   *    8FAD..8FB2   辭 辮 辯 辰 辱 農
   *    8FB4..8FB8   辴 辵 辶 辷 辸
   *    8FBA..8FBC   辺 辻 込
   *    8FBE..8FC2   达 辿 迀 迁 迂
   *    8FC4..8FC6   迄 迅 迆
   *    8FCA..8FCB   迊 迋
   *    8FCD..8FCE   迍 迎
   *    8FD0..8FD5   运 近 迒 迓 返 迕
   *    8FDA         迚
   *    8FE0         迠
   *    8FE2..8FE6   迢 迣 迤 迥 迦
   *    8FE8..8FEB   迨 迩 迪 迫
   *    8FED..8FF1   迭 迮 迯 述 迱
   *    8FF4..8FFB   迴 迵 迶 迷 迸 迹 迺 迻
   *    8FFD..8FFE   追 迾
   *    9000..9006   退 送 适 逃 逄 逅 逆
   *    9008         逈
   *    900B..9011   逋 逌 逍 逎 透 逐 逑
   *    9013..901B   逓 途 逕 逖 逗 逘 這 通 逛
   *    901D..9023   逝 逞 速 造 逡 逢 連
   *    9027..902A   逧 逨 逩 逪
   *    902C..902F   逬 逭 逮 逯
   *    9031..9039   週 進 逳 逴 逵 逶 逷 逸 逹
   *    903C         逼
   *    903E..903F   逾 逿
   *    9041..9045   遁 遂 遃 遄 遅
   *    9047         遇
   *    9049..9056   遉 遊 運 遌 遍 過 遏 遐 遑 遒 道 達 違 遖
   *    9058..9059   遘 遙
   *    905B..905E   遛 遜 遝 遞
   *    9060..9063   遠 遡 遢 遣
   *    9065..9069   遥 遦 遧 遨 適
   *    906C..9070   遬 遭 遮 遯 遰
   *    9072         遲
   *    9074..907A   遴 遵 遶 遷 選 遹 遺
   *    907C..907D   遼 遽
   *    907F..9085   避 邀 邁 邂 邃 還 邅
   *    9087..908C   邇 邈 邉 邊 邋 邌
   *    908E..9091   邎 邏 邐 邑
   *    9095         邕
   *    9097..9099   邗 邘 邙
   *    909B         邛
   *    90A0..90A3   邠 邡 邢 那
   *    90A5..90A6   邥 邦
   *    90A8         邨
   *    90AA         邪
   *    90AF..90B6   邯 邰 邱 邲 邳 邴 邵 邶
   *    90B8         邸
   *    90BD..90BE   邽 邾
   *    90C1         郁
   *    90C3..90C5   郃 郄 郅
   *    90C7..90C8   郇 郈
   *    90CA         郊
   *    90CC         郌
   *    90CE         郎
   *    90D2         郒
   *    90D5         郕
   *    90D7..90D9   郗 郘 郙
   *    90DB..90DF   郛 郜 郝 郞 郟
   *    90E1..90E2   郡 郢
   *    90E4..90E5   郤 郥
   *    90E8         部
   *    90EB         郫
   *    90ED         郭
   *    90EF..90F0   郯 郰
   *    90F2         郲
   *    90F4..90F7   郴 郵 郶 郷
   *    90FD..9100   都 郾 郿 鄀
   *    9102         鄂
   *    9104..9106   鄄 鄅 鄆
   *    9108         鄈
   *    910D         鄍
   *    9110         鄐
   *    9112         鄒
   *    9114..911A   鄔 鄕 鄖 鄗 鄘 鄙 鄚
   *    911C         鄜
   *    911E         鄞
   *    9120         鄠
   *    9122..9123   鄢 鄣
   *    9125         鄥
   *    9127         鄧
   *    9129         鄩
   *    912D..9132   鄭 鄮 鄯 鄰 鄱 鄲
   *    9134         鄴
   *    9136..9137   鄶 鄷
   *    9139..913A   鄹 鄺
   *    913C..913D   鄼 鄽
   *    9143         酃
   *    9146..914F   酆 酇 酈 酉 酊 酋 酌 配 酎 酏
   *    9152..9154   酒 酓 酔
   *    9156..915B   酖 酗 酘 酙 酚 酛
   *    9161..9165   酡 酢 酣 酤 酥
   *    9167         酧
   *    9169..916A   酩 酪
   *    916C..916D   酬 酭
   *    9172..9175   酲 酳 酴 酵
   *    9177..917B   酷 酸 酹 酺 酻
   *    9181..9183   醁 醂 醃
   *    9185..9187   醅 醆 醇
   *    9189..918B   醉 醊 醋
   *    918D..918E   醍 醎
   *    9190..9195   醐 醑 醒 醓 醔 醕
   *    9197..9198   醗 醘
   *    919C         醜
   *    919E         醞
   *    91A1..91A2   醡 醢
   *    91A4         醤
   *    91A6         醦
   *    91A8         醨
   *    91AA..91B6   醪 醫 醬 醭 醮 醯 醰 醱 醲 醳 醴 醵 醶
   *    91B8         醸
   *    91BA..91BD   醺 醻 醼 醽
   *    91BF..91C9   醿 釀 釁 釂 釃 釄 釅 釆 采 釈 釉
   *    91CB..91D1   釋 里 重 野 量 釐 金
   *    91D3..91D4   釓 釔
   *    91D6..91DF   釖 釗 釘 釙 釚 釛 釜 針 釞 釟
   *    91E1         釡
   *    91E3..91E7   釣 釤 釥 釦 釧
   *    91E9..91EA   釩 釪
   *    91EC..91F1   釬 釭 釮 釯 釰 釱
   *    91F5..91F7   釵 釶 釷
   *    91F9         釹
   *    91FB..91FD   釻 釼 釽
   *    91FF..9201   釿 鈀 鈁
   *    9204..9207   鈄 鈅 鈆 鈇
   *    9209..920A   鈉 鈊
   *    920C..920E   鈌 鈍 鈎
   *    9210..9218   鈐 鈑 鈒 鈓 鈔 鈕 鈖 鈗 鈘
   *    921C..921E   鈜 鈝 鈞
   *    9223..9226   鈣 鈤 鈥 鈦
   *    9228..9229   鈨 鈩
   *    922C         鈬
   *    922E..9230   鈮 鈯 鈰
   *    9233..923A   鈳 鈴 鈵 鈶 鈷 鈸 鈹 鈺
   *    923C         鈼
   *    923E..9240   鈾 鈿 鉀
   *    9242..924B   鉂 鉃 鉄 鉅 鉆 鉇 鉈 鉉 鉊 鉋
   *    924D..9251   鉍 鉎 鉏 鉐 鉑
   *    9256..925E   鉖 鉗 鉘 鉙 鉚 鉛 鉜 鉝 鉞
   *    9260..9262   鉠 鉡 鉢
   *    9264..9269   鉤 鉥 鉦 鉧 鉨 鉩
   *    926E..9271   鉮 鉯 鉰 鉱
   *    9275..9279   鉵 鉶 鉷 鉸 鉹
   *    927B..9280   鉻 鉼 鉽 鉾 鉿 銀
   *    9283         銃
   *    9285         銅
   *    9288..928A   銈 銉 銊
   *    928D..928E   銍 銎
   *    9291..9293   銑 銒 銓
   *    9295..929C   銕 銖 銗 銘 銙 銚 銛 銜
   *    929F..92A0   銟 銠
   *    92A4..92A5   銤 銥
   *    92A7..92A8   銧 銨
   *    92AB         銫
   *    92AD         銭
   *    92AF         銯
   *    92B2         銲
   *    92B6..92BD   銶 銷 銸 銹 銺 銻 銼 銽
   *    92BF..92C3   銿 鋀 鋁 鋂 鋃
   *    92C5..92C8   鋅 鋆 鋇 鋈
   *    92CB..92D0   鋋 鋌 鋍 鋎 鋏 鋐
   *    92D2..92D3   鋒 鋓
   *    92D5         鋕
   *    92D7..92D9   鋗 鋘 鋙
   *    92DC..92DD   鋜 鋝
   *    92DF..92E1   鋟 鋠 鋡
   *    92E3..92E5   鋣 鋤 鋥
   *    92E7..92EA   鋧 鋨 鋩 鋪
   *    92EC..92EE   鋬 鋭 鋮
   *    92F0         鋰
   *    92F2..92F3   鋲 鋳
   *    92F7..92FC   鋷 鋸 鋹 鋺 鋻 鋼
   *    92FF..9300   鋿 錀
   *    9302         錂
   *    9304         錄
   *    9306         錆
   *    9308         錈
   *    930D         錍
   *    930F..9311   錏 錐 錑
   *    9314..9315   錔 錕
   *    9318..931A   錘 錙 錚
   *    931C..932C   錜 錝 錞 錟 錠 錡 錢 錣 錤 錥 錦 錧 錨 錩 錪 錫 錬
   *    932E..932F   錮 錯
   *    9332..9337   録 錳 錴 錵 錶 錷
   *    933A..933B   錺 錻
   *    9344         鍄
   *    9347..934B   鍇 鍈 鍉 鍊 鍋
   *    934D         鍍
   *    9350..9352   鍐 鍑 鍒
   *    9354..9358   鍔 鍕 鍖 鍗 鍘
   *    935A..935C   鍚 鍛 鍜
   *    935E         鍞
   *    9360         鍠
   *    9364..9365   鍤 鍥
   *    9367         鍧
   *    9369..9371   鍩 鍪 鍫 鍬 鍭 鍮 鍯 鍰 鍱
   *    9373..9376   鍳 鍴 鍵 鍶
   *    937A         鍺
   *    937C..9382   鍼 鍽 鍾 鍿 鎀 鎁 鎂
   *    9388         鎈
   *    938A..938D   鎊 鎋 鎌 鎍
   *    938F         鎏
   *    9392         鎒
   *    9394..9398   鎔 鎕 鎖 鎗 鎘
   *    939A..939B   鎚 鎛
   *    939E         鎞
   *    93A1         鎡
   *    93A3..93A4   鎣 鎤
   *    93A6..93A9   鎦 鎧 鎨 鎩
   *    93AB..93AE   鎫 鎬 鎭 鎮
   *    93B0         鎰
   *    93B4..93B6   鎴 鎵 鎶
   *    93B9..93BA   鎹 鎺
   *    93C1         鏁
   *    93C3..93CD   鏃 鏄 鏅 鏆 鏇 鏈 鏉 鏊 鏋 鏌 鏍
   *    93D0..93D1   鏐 鏑
   *    93D3         鏓
   *    93D6..93D9   鏖 鏗 鏘 鏙
   *    93DC..93DF   鏜 鏝 鏞 鏟
   *    93E1..93E2   鏡 鏢
   *    93E4..93E8   鏤 鏥 鏦 鏧 鏨
   *    93F1         鏱
   *    93F5         鏵
   *    93F7..93FB   鏷 鏸 鏹 鏺 鏻
   *    93FD         鏽
   *    9401..9404   鐁 鐂 鐃 鐄
   *    9407..9409   鐇 鐈 鐉
   *    940D..9410   鐍 鐎 鐏 鐐
   *    9413..941A   鐓 鐔 鐕 鐖 鐗 鐘 鐙 鐚
   *    941F         鐟
   *    9421         鐡
   *    942B         鐫
   *    942E..942F   鐮 鐯
   *    9431..9436   鐱 鐲 鐳 鐴 鐵 鐶
   *    9438         鐸
   *    943A..943B   鐺 鐻
   *    943D         鐽
   *    943F         鐿
   *    9441         鑁
   *    9443..9445   鑃 鑄 鑅
   *    9448         鑈
   *    944A         鑊
   *    944C         鑌
   *    9451..9453   鑑 鑒 鑓
   *    9455         鑕
   *    9459..945C   鑙 鑚 鑛 鑜
   *    945E..9463   鑞 鑟 鑠 鑡 鑢 鑣
   *    9468         鑨
   *    946A..946B   鑪 鑫
   *    946D..9472   鑭 鑮 鑯 鑰 鑱 鑲
   *    9475         鑵
   *    9477         鑷
   *    947C..947F   鑼 鑽 鑾 鑿
   *    9481         钁
   *    9483..9484   钃 钄
   *    9577..9579   長 镸 镹
   *    957E         镾
   *    9580         門
   *    9582..9584   閂 閃 閄
   *    9586..958F   閆 閇 閈 閉 閊 開 閌 閍 閎 閏
   *    9591         閑
   *    9593..9594   間 閔
   *    9596         閖
   *    9598..9599   閘 閙
   *    959D..95A9   閝 閞 閟 閠 閡 関 閣 閤 閥 閦 閧 閨 閩
   *    95AB..95AD   閫 閬 閭
   *    95B2         閲
   *    95B4         閴
   *    95B6         閶
   *    95B9..95BF   閹 閺 閻 閼 閽 閾 閿
   *    95C3         闃
   *    95C6..95CD   闆 闇 闈 闉 闊 闋 闌 闍
   *    95D0..95D6   闐 闑 闒 闓 闔 闕 闖
   *    95D8..95DA   闘 闙 闚
   *    95DC..95E2   關 闝 闞 闟 闠 闡 闢
   *    95E4..95E6   闤 闥 闦
   *    961C..961E   阜 阝 阞
   *    9621..9622   阡 阢
   *    9624..9626   阤 阥 阦
   *    9628         阨
   *    962A         阪
   *    962C         阬
   *    962E..962F   阮 阯
   *    9631..9634   阱 防 阳 阴
   *    9637..963D   阷 阸 阹 阺 阻 阼 阽
   *    963F..9642   阿 陀 陁 陂
   *    9644         附
   *    964B..964D   陋 陌 降
   *    964F..9650   陏 限
   *    9652         陒
   *    9654         陔
   *    9656..9658   陖 陗 陘
   *    965B..965F   陛 陜 陝 陞 陟
   *    9661..9666   陡 院 陣 除 陥 陦
   *    966A         陪
   *    966C         陬
   *    966E         陮
   *    9670         陰
   *    9672..9678   陲 陳 陴 陵 陶 陷 陸
   *    967A..967F   険 陻 陼 陽 陾 陿
   *    9681..9686   隁 隂 隃 隄 隅 隆
   *    9688..968B   隈 隉 隊 隋
   *    968D..968F   隍 階 随
   *    9691         隑
   *    9694..969D   隔 隕 隖 隗 隘 隙 隚 際 障 隝
   *    969F..96A0   隟 隠
   *    96A3..96AA   隣 隤 隥 隦 隧 隨 隩 險
   *    96AE..96B4   隮 隯 隰 隱 隲 隳 隴
   *    96B6..96BD   隶 隷 隸 隹 隺 隻 隼 隽
   *    96C0..96C1   雀 雁
   *    96C4..96C7   雄 雅 集 雇
   *    96C9..96CE   雉 雊 雋 雌 雍 雎
   *    96D1..96D2   雑 雒
   *    96D5..96D6   雕 雖
   *    96D8..96DF   雘 雙 雚 雛 雜 雝 雞 雟
   *    96E2..96E3   離 難
   *    96E8..96EB   雨 雩 雪 雫
   *    96EF..96F2   雯 雰 雱 雲
   *    96F6..96F7   零 雷
   *    96F9..96FB   雹 雺 電
   *    9700         需
   *    9702..970A   霂 霃 霄 霅 霆 震 霈 霉 霊
   *    970D..970F   霍 霎 霏
   *    9711         霑
   *    9713..9714   霓 霔
   *    9716         霖
   *    9719..971E   霙 霚 霛 霜 霝 霞
   *    9721..9724   霡 霢 霣 霤
   *    9727..9728   霧 霨
   *    972A         霪
   *    9730..9733   霰 霱 露 霳
   *    9736         霶
   *    9738..9739   霸 霹
   *    973B         霻
   *    973D..973E   霽 霾
   *    9741..9744   靁 靂 靃 靄
   *    9746..974A   靆 靇 靈 靉 靊
   *    974D..974F   靍 靎 靏
   *    9752         青
   *    9755..975C   靕 靖 靗 靘 静 靚 靛 靜
   *    975E         非
   *    9760..9764   靠 靡 面 靣 靤
   *    9766..976B   靦 靧 靨 革 靪 靫
   *    976D..976E   靭 靮
   *    9771         靱
   *    9773..9774   靳 靴
   *    9776..977D   靶 靷 靸 靹 靺 靻 靼 靽
   *    977F..9781   靿 鞀 鞁
   *    9784..9786   鞄 鞅 鞆
   *    9789         鞉
   *    978B         鞋
   *    978D         鞍
   *    978F..9790   鞏 鞐
   *    9795..979A   鞕 鞖 鞗 鞘 鞙 鞚
   *    979C         鞜
   *    979E..97A0   鞞 鞟 鞠
   *    97A2..97A3   鞢 鞣
   *    97A6         鞦
   *    97A8         鞨
   *    97AB..97AE   鞫 鞬 鞭 鞮
   *    97B1..97B6   鞱 鞲 鞳 鞴 鞵 鞶
   *    97B8..97BA   鞸 鞹 鞺
   *    97BC         鞼
   *    97BE..97BF   鞾 鞿
   *    97C1         韁
   *    97C3..97CE   韃 韄 韅 韆 韇 韈 韉 韊 韋 韌 韍 韎
   *    97D0..97D1   韐 韑
   *    97D3..97D4   韓 韔
   *    97D7..97D9   韗 韘 韙
   *    97DB..97DE   韛 韜 韝 韞
   *    97E0..97E1   韠 韡
   *    97E4         韤
   *    97ED..97EF   韭 韮 韯
   *    97F1..97F8   韱 韲 音 韴 韵 韶 韷 韸
   *    97FA..97FB   韺 韻
   *    97FF         響
   *    9801..9808   頁 頂 頃 頄 項 順 頇 須
   *    980A         頊
   *    980C..9814   頌 頍 頎 頏 預 頑 頒 頓 頔
   *    9816..981A   頖 頗 領 頙 頚
   *    981C         頜
   *    981E         頞
   *    9820..9821   頠 頡
   *    9823..9826   頣 頤 頥 頦
   *    982B..9830   頫 頬 頭 頮 頯 頰
   *    9832..9835   頲 頳 頴 頵
   *    9837..9838   頷 頸
   *    983B..983E   頻 頼 頽 頾
   *    9844         顄
   *    9846..9847   顆 顇
   *    984A..984F   顊 顋 題 額 顎 顏
   *    9851..985B   顑 顒 顓 顔 顕 顖 顗 願 顙 顚 顛
   *    985E         類
   *    9862..9863   顢 顣
   *    9865..9867   顥 顦 顧
   *    986A..986C   顪 顫 顬
   *    986F..9871   顯 顰 顱
   *    9873..9874   顳 顴
   *    98A8         風
   *    98AA..98AB   颪 颫
   *    98AD..98B1   颭 颮 颯 颰 颱
   *    98B4         颴
   *    98B6..98B8   颶 颷 颸
   *    98BA..98BC   颺 颻 颼
   *    98BF         颿
   *    98C2..98C8   飂 飃 飄 飅 飆 飇 飈
   *    98CB..98CC   飋 飌
   *    98DB..98DC   飛 飜
   *    98DF..98E3   食 飠 飡 飢 飣
   *    98E5..98E7   飥 飦 飧
   *    98E9..98EB   飩 飪 飫
   *    98ED..98F4   飭 飮 飯 飰 飱 飲 飳 飴
   *    98F6         飶
   *    98FC..98FE   飼 飽 飾
   *    9902..9903   餂 餃
   *    9905         餅
   *    9907..990A   餇 餈 餉 養
   *    990C         餌
   *    9910..9918   餐 餑 餒 餓 餔 餕 餖 餗 餘
   *    991A..9922   餚 餛 餜 餝 餞 餟 餠 餡 餢
   *    9924         餤
   *    9926..9928   餦 餧 館
   *    992B..992C   餫 餬
   *    992E         餮
   *    9931..9935   餱 餲 餳 餴 餵
   *    9939..993E   餹 餺 餻 餼 餽 餾
   *    9940..9942   饀 饁 饂
   *    9945..9949   饅 饆 饇 饈 饉
   *    994B..994E   饋 饌 饍 饎
   *    9950..9952   饐 饑 饒
   *    9954..9955   饔 饕
   *    9957..9959   饗 饘 饙
   *    995B..995C   饛 饜
   *    995E..9960   饞 饟 饠
   *    9996..9999   首 馗 馘 香
   *    999B         馛
   *    999D..999F   馝 馞 馟
   *    99A3         馣
   *    99A5..99A6   馥 馦
   *    99A8         馨
   *    99AC..99AE   馬 馭 馮
   *    99B0..99B5   馰 馱 馲 馳 馴 馵
   *    99B9..99BA   馹 馺
   *    99BC..99BD   馼 馽
   *    99BF         馿
   *    99C1         駁
   *    99C3..99C6   駃 駄 駅 駆
   *    99C8..99C9   駈 駉
   *    99D0..99D5   駐 駑 駒 駓 駔 駕
   *    99D8..99DF   駘 駙 駚 駛 駜 駝 駞 駟
   *    99E2         駢
   *    99E7         駧
   *    99EA..99EE   駪 駫 駬 駭 駮
   *    99F0..99F2   駰 駱 駲
   *    99F4..99F5   駴 駵
   *    99F8..99F9   駸 駹
   *    99FB..99FF   駻 駼 駽 駾 駿
   *    9A01..9A05   騁 騂 騃 騄 騅
   *    9A0A..9A0C   騊 騋 騌
   *    9A0E..9A13   騎 騏 騐 騑 騒 験
   *    9A16         騖
   *    9A19..9A1A   騙 騚
   *    9A1E         騞
   *    9A20         騠
   *    9A22..9A24   騢 騣 騤
   *    9A27..9A28   騧 騨
   *    9A2B         騫
   *    9A2D..9A2E   騭 騮
   *    9A30..9A31   騰 騱
   *    9A33         騳
   *    9A35..9A38   騵 騶 騷 騸
   *    9A3E         騾
   *    9A40..9A45   驀 驁 驂 驃 驄 驅
   *    9A47         驇
   *    9A4A..9A4E   驊 驋 驌 驍 驎
   *    9A51..9A52   驑 驒
   *    9A54..9A58   驔 驕 驖 驗 驘
   *    9A5A..9A5B   驚 驛
   *    9A5D         驝
   *    9A5F         驟
   *    9A62         驢
   *    9A64..9A65   驤 驥
   *    9A69..9A6B   驩 驪 驫
   *    9AA8         骨
   *    9AAA         骪
   *    9AAC..9AB0   骬 骭 骮 骯 骰
   *    9AB2         骲
   *    9AB4..9AB9   骴 骵 骶 骷 骸 骹
   *    9ABB..9ABC   骻 骼
   *    9ABE..9AC1   骾 骿 髀 髁
   *    9AC3..9AC4   髃 髄
   *    9AC6         髆
   *    9AC8         髈
   *    9ACE..9AD8   髎 髏 髐 髑 髒 髓 體 髕 髖 髗 高
   *    9ADB..9ADC   髛 髜
   *    9ADE..9AE0   髞 髟 髠
   *    9AE2..9AE7   髢 髣 髤 髥 髦 髧
   *    9AE9..9AEF   髩 髪 髫 髬 髭 髮 髯
   *    9AF1..9AF5   髱 髲 髳 髴 髵
   *    9AF7         髷
   *    9AF9..9AFB   髹 髺 髻
   *    9AFD         髽
   *    9AFF..9B06   髿 鬀 鬁 鬂 鬃 鬄 鬅 鬆
   *    9B08..9B09   鬈 鬉
   *    9B0B..9B0E   鬋 鬌 鬍 鬎
   *    9B10         鬐
   *    9B12         鬒
   *    9B16         鬖
   *    9B18..9B1C   鬘 鬙 鬚 鬛 鬜
   *    9B1F..9B20   鬟 鬠
   *    9B22..9B23   鬢 鬣
   *    9B25..9B2B   鬥 鬦 鬧 鬨 鬩 鬪 鬫
   *    9B2D..9B2F   鬭 鬮 鬯
   *    9B31..9B35   鬱 鬲 鬳 鬴 鬵
   *    9B37         鬷
   *    9B39..9B3D   鬹 鬺 鬻 鬼 鬽
   *    9B41..9B45   魁 魂 魃 魄 魅
   *    9B48         魈
   *    9B4B..9B4F   魋 魌 魍 魎 魏
   *    9B51         魑
   *    9B54..9B58   魔 魕 魖 魗 魘
   *    9B5A..9B5B   魚 魛
   *    9B5E         魞
   *    9B61         魡
   *    9B63         魣
   *    9B65..9B66   魥 魦
   *    9B68         魨
   *    9B6A..9B6F   魪 魫 魬 魭 魮 魯
   *    9B72..9B79   魲 魳 魴 魵 魶 魷 魸 魹
   *    9B7F..9B80   魿 鮀
   *    9B83..9B87   鮃 鮄 鮅 鮆 鮇
   *    9B89..9B8B   鮉 鮊 鮋
   *    9B8D..9B94   鮍 鮎 鮏 鮐 鮑 鮒 鮓 鮔
   *    9B96..9B97   鮖 鮗
   *    9B9A         鮚
   *    9B9D..9BA0   鮝 鮞 鮟 鮠
   *    9BA6..9BAE   鮦 鮧 鮨 鮩 鮪 鮫 鮬 鮭 鮮
   *    9BB0..9BB2   鮰 鮱 鮲
   *    9BB4         鮴
   *    9BB7..9BB9   鮷 鮸 鮹
   *    9BBB..9BBC   鮻 鮼
   *    9BBE..9BC1   鮾 鮿 鯀 鯁
   *    9BC6..9BCA   鯆 鯇 鯈 鯉 鯊
   *    9BCE..9BD2   鯎 鯏 鯐 鯑 鯒
   *    9BD4         鯔
   *    9BD6..9BD8   鯖 鯗 鯘
   *    9BDB         鯛
   *    9BDD         鯝
   *    9BDF         鯟
   *    9BE1..9BE5   鯡 鯢 鯣 鯤 鯥
   *    9BE7..9BE8   鯧 鯨
   *    9BEA..9BEB   鯪 鯫
   *    9BEE..9BF3   鯮 鯯 鯰 鯱 鯲 鯳
   *    9BF5         鯵
   *    9BF7..9BFA   鯷 鯸 鯹 鯺
   *    9BFD         鯽
   *    9BFF..9C00   鯿 鰀
   *    9C02         鰂
   *    9C04         鰄
   *    9C06         鰆
   *    9C08..9C0D   鰈 鰉 鰊 鰋 鰌 鰍
   *    9C0F..9C16   鰏 鰐 鰑 鰒 鰓 鰔 鰕 鰖
   *    9C18..9C1E   鰘 鰙 鰚 鰛 鰜 鰝 鰞
   *    9C21..9C2A   鰡 鰢 鰣 鰤 鰥 鰦 鰧 鰨 鰩 鰪
   *    9C2D..9C32   鰭 鰮 鰯 鰰 鰱 鰲
   *    9C35..9C37   鰵 鰶 鰷
   *    9C39..9C3B   鰹 鰺 鰻
   *    9C3D..9C3E   鰽 鰾
   *    9C41         鱁
   *    9C43..9C4A   鱃 鱄 鱅 鱆 鱇 鱈 鱉 鱊
   *    9C4E..9C50   鱎 鱏 鱐
   *    9C52..9C54   鱒 鱓 鱔
   *    9C56..9C58   鱖 鱗 鱘
   *    9C5A..9C60   鱚 鱛 鱜 鱝 鱞 鱟 鱠
   *    9C63         鱣
   *    9C65         鱥
   *    9C67..9C6B   鱧 鱨 鱩 鱪 鱫
   *    9C6D..9C6E   鱭 鱮
   *    9C70         鱰
   *    9C72         鱲
   *    9C75..9C78   鱵 鱶 鱷 鱸
   *    9C7A..9C7B   鱺 鱻
   *    9CE5..9CE7   鳥 鳦 鳧
   *    9CE9         鳩
   *    9CEB..9CEC   鳫 鳬
   *    9CF0         鳰
   *    9CF2..9CF4   鳲 鳳 鳴
   *    9CF6..9CF7   鳶 鳷
   *    9CF9         鳹
   *    9D02..9D03   鴂 鴃
   *    9D06..9D09   鴆 鴇 鴈 鴉
   *    9D0B         鴋
   *    9D0E         鴎
   *    9D11..9D12   鴑 鴒
   *    9D15         鴕
   *    9D17..9D18   鴗 鴘
   *    9D1B..9D1F   鴛 鴜 鴝 鴞 鴟
   *    9D23         鴣
   *    9D26         鴦
   *    9D28         鴨
   *    9D2A..9D2C   鴪 鴫 鴬
   *    9D2F..9D30   鴯 鴰
   *    9D32..9D34   鴲 鴳 鴴
   *    9D3A..9D3F   鴺 鴻 鴼 鴽 鴾 鴿
   *    9D41..9D48   鵁 鵂 鵃 鵄 鵅 鵆 鵇 鵈
   *    9D4A         鵊
   *    9D50..9D54   鵐 鵑 鵒 鵓 鵔
   *    9D59         鵙
   *    9D5C..9D65   鵜 鵝 鵞 鵟 鵠 鵡 鵢 鵣 鵤 鵥
   *    9D69..9D6C   鵩 鵪 鵫 鵬
   *    9D6F..9D70   鵯 鵰
   *    9D72..9D73   鵲 鵳
   *    9D76..9D77   鵶 鵷
   *    9D7A..9D7C   鵺 鵻 鵼
   *    9D7E         鵾
   *    9D83..9D84   鶃 鶄
   *    9D86..9D87   鶆 鶇
   *    9D89..9D8A   鶉 鶊
   *    9D8D..9D8F   鶍 鶎 鶏
   *    9D92..9D93   鶒 鶓
   *    9D95..9D9A   鶕 鶖 鶗 鶘 鶙 鶚
   *    9DA1         鶡
   *    9DA4         鶤
   *    9DA9..9DAC   鶩 鶪 鶫 鶬
   *    9DAE..9DAF   鶮 鶯
   *    9DB1..9DB2   鶱 鶲
   *    9DB4..9DB5   鶴 鶵
   *    9DB8..9DBD   鶸 鶹 鶺 鶻 鶼 鶽
   *    9DBF..9DC4   鶿 鷀 鷁 鷂 鷃 鷄
   *    9DC6..9DC7   鷆 鷇
   *    9DC9..9DCA   鷉 鷊
   *    9DCF         鷏
   *    9DD3..9DD7   鷓 鷔 鷕 鷖 鷗
   *    9DD9..9DDA   鷙 鷚
   *    9DDE..9DE0   鷞 鷟 鷠
   *    9DE3         鷣
   *    9DE5..9DE7   鷥 鷦 鷧
   *    9DE9         鷩
   *    9DEB         鷫
   *    9DED..9DF0   鷭 鷮 鷯 鷰
   *    9DF2..9DF4   鷲 鷳 鷴
   *    9DF8..9DFA   鷸 鷹 鷺
   *    9DFD..9DFE   鷽 鷾
   *    9E02         鸂
   *    9E07         鸇
   *    9E0A         鸊
   *    9E0D..9E0E   鸍 鸎
   *    9E10..9E12   鸐 鸑 鸒
   *    9E15..9E16   鸕 鸖
   *    9E19..9E1E   鸙 鸚 鸛 鸜 鸝 鸞
   *    9E75         鹵
   *    9E78..9E7D   鹸 鹹 鹺 鹻 鹼 鹽
   *    9E7F..9E85   鹿 麀 麁 麂 麃 麄 麅
   *    9E87..9E88   麇 麈
   *    9E8B..9E8C   麋 麌
   *    9E8E..9E8F   麎 麏
   *    9E91..9E93   麑 麒 麓
   *    9E95..9E98   麕 麖 麗 麘
   *    9E9B         麛
   *    9E9D..9E9F   麝 麞 麟
   *    9EA4..9EA6   麤 麥 麦
   *    9EA8..9EAA   麨 麩 麪
   *    9EAC..9EB0   麬 麭 麮 麯 麰
   *    9EB3..9EB5   麳 麴 麵
   *    9EB8..9EBF   麸 麹 麺 麻 麼 麽 麾 麿
   *    9EC3..9EC4   黃 黄
   *    9EC6         黆
   *    9EC8         黈
   *    9ECB..9ED2   黋 黌 黍 黎 黏 黐 黑 黒
   *    9ED4..9ED5   黔 黕
   *    9ED8..9ED9   默 黙
   *    9EDB..9EE0   黛 黜 黝 點 黟 黠
   *    9EE4..9EE5   黤 黥
   *    9EE7..9EE8   黧 黨
   *    9EEC..9EF2   黬 黭 黮 黯 黰 黱 黲
   *    9EF4..9EF9   黴 黵 黶 黷 黸 黹
   *    9EFB..9EFD   黻 黼 黽
   *    9EFF         黿
   *    9F02..9F03   鼂 鼃
   *    9F07..9F09   鼇 鼈 鼉
   *    9F0E..9F17   鼎 鼏 鼐 鼑 鼒 鼓 鼔 鼕 鼖 鼗
   *    9F19..9F1B   鼙 鼚 鼛
   *    9F1F..9F22   鼟 鼠 鼡 鼢
   *    9F26         鼦
   *    9F2A..9F2C   鼪 鼫 鼬
   *    9F2F         鼯
   *    9F31..9F32   鼱 鼲
   *    9F34         鼴
   *    9F37         鼷
   *    9F39..9F3F   鼹 鼺 鼻 鼼 鼽 鼾 鼿
   *    9F41         齁
   *    9F43..9F47   齃 齄 齅 齆 齇
   *    9F4A..9F4B   齊 齋
   *    9F4E..9F4F   齎 齏
   *    9F52..9F58   齒 齓 齔 齕 齖 齗 齘
   *    9F5A         齚
   *    9F5D..9F63   齝 齞 齟 齠 齡 齢 齣
   *    9F66..9F6A   齦 齧 齨 齩 齪
   *    9F6C..9F73   齬 齭 齮 齯 齰 齱 齲 齳
   *    9F75..9F77   齵 齶 齷
   *    9F7A         齺
   *    9F7D         齽
   *    9F8D         龍
   *    9F8F..9F92   龏 龐 龑 龒
   *    9F94..9F97   龔 龕 龖 龗
   *    9F9C..9F9E   龜 龝 龞
   *    9FA0..9FA3   龠 龡 龢 龣
   *    9FA5         龥
   *    9FC4..9FC6   鿄 鿅 鿆
   *    9FEE..9FEF   鿮 鿯
   *    F91D         欄
   *    F928..F929   廊 朗
   *    F936         虜
   *    F970         殺
   *    F9D0         類
   *    F9DC         隆
   *    FA0F..FA11   﨏 塚 﨑
   *    FA13..FA16   﨓 﨔 凞 猪
   *    FA19..FA1B   神 祥 福
   *    FA1F..FA24   﨟 蘒 﨡 諸 﨣 﨤
   *    FA26         都
   *    FA30..FA6D   侮 僧 免 勉 勤 卑 喝 嘆 器 塀 墨 層 屮 悔 慨 憎 懲 敏 既 暑 梅 海 渚 漢 煮 爫 琢 碑 社 祉
   *                 祈 祐 祖 祝 禍 禎 穀 突 節 練 縉 繁 署 者 臭 艹 艹 著 褐 視 謁 謹 賓 贈 辶 逸 難 響 頻 恵 𤋮 舘
   *    2000B         𠀋
   *    20089         𠂉
   *    200A2         𠂢
   *    200A4         𠂤
   *    201A2         𠆢
   *    20213         𠈓
   *    2032B         𠌫
   *    20371         𠍱
   *    20381         𠎁
   *    203F9         𠏹
   *    2044A         𠑊
   *    20509         𠔉
   *    205D6         𠗖
   *    20628         𠘨
   *    2074F         𠝏
   *    20807         𠠇
   *    2083A         𠠺
   *    208B9         𠢹
   *    2097C         𠥼
   *    2099D         𠦝
   *    20AD3         𠫓
   *    20B1D         𠬝
   *    20B9F         𠮟
   *    20D45         𠵅
   *    20DE1         𠷡
   *    20E64         𠹤
   *    20E6D         𠹭
   *    20E95         𠺕
   *    20F5F         𠽟
   *    21201         𡈁
   *    2123D         𡈽
   *    21255         𡉕
   *    21274         𡉴
   *    2127B         𡉻
   *    212D7         𡋗
   *    212E4         𡋤
   *    212FD         𡋽
   *    2131B         𡌛
   *    21336         𡌶
   *    21344         𡍄
   *    213C4         𡏄
   *    2146D..2146E   𡑭 𡑮
   *    215D7         𡗗
   *    21647         𡙇
   *    216B4         𡚴
   *    21706         𡜆
   *    21742         𡝂
   *    218BD         𡢽
   *    219C3         𡧃
   *    21C56         𡱖
   *    21D2D         𡴭
   *    21D45         𡵅
   *    21D62         𡵢
   *    21D78         𡵸
   *    21D92         𡶒
   *    21D9C         𡶜
   *    21DA1         𡶡
   *    21DB7         𡶷
   *    21DE0         𡷠
   *    21E33..21E34   𡸳 𡸴
   *    21F1E         𡼞
   *    21F76         𡽶
   *    21FFA         𡿺
   *    2217B         𢅻
   *    22218         𢈘
   *    2231E         𢌞
   *    223AD         𢎭
   *    226F3         𢛳
   *    2285B         𢡛
   *    228AB         𢢫
   *    2298F         𢦏
   *    22AB8         𢪸
   *    22B46         𢭆
   *    22B4F..22B50   𢭏 𢭐
   *    22BA6         𢮦
   *    22C1D         𢰝
   *    22C24         𢰤
   *    22DE1         𢷡
   *    231B6         𣆶
   *    231C3..231C4   𣇃 𣇄
   *    231F5         𣇵
   *    23372         𣍲
   *    233D0         𣏐
   *    233D2..233D3   𣏒 𣏓
   *    233D5         𣏕
   *    233DA         𣏚
   *    233DF         𣏟
   *    233E4         𣏤
   *    2344A..2344B   𣑊 𣑋
   *    23451         𣑑
   *    23465         𣑥
   *    234E4         𣓤
   *    2355A         𣕚
   *    23594         𣖔
   *    235C4         𣗄
   *    23638..2363A   𣘸 𣘹 𣘺
   *    23647         𣙇
   *    2370C         𣜌
   *    2371C         𣜜
   *    2373F         𣜿
   *    23763..23764   𣝣 𣝤
   *    237E7         𣟧
   *    237FF         𣟿
   *    23824         𣠤
   *    2383D         𣠽
   *    23A98         𣪘
   *    23C7F         𣱿
   *    23CFE         𣳾
   *    23D00         𣴀
   *    23D0E         𣴎
   *    23D40         𣵀
   *    23DD3         𣷓
   *    23DF9..23DFA   𣷹 𣷺
   *    23F7E         𣽾
   *    24096         𤂖
   *    24103         𤄃
   *    241C6         𤇆
   *    241FE         𤇾
   *    243BC         𤎼
   *    24629         𤘩
   *    246A5         𤚥
   *    247F1         𤟱
   *    24896         𤢖
   *    24A4D         𤩍
   *    24B56         𤭖
   *    24B6F         𤭯
   *    24C16         𤰖
   *    24D14         𤴔
   *    24E0E         𤸎
   *    24E37         𤸷
   *    24E6A         𤹪
   *    24E8B         𤺋
   *    2504A         𥁊
   *    25055         𥁕
   *    25122         𥄢
   *    251A9         𥆩
   *    251CD         𥇍
   *    251E5         𥇥
   *    2521E         𥈞
   *    2524C         𥉌
   *    2542E         𥐮
   *    2548E         𥒎
   *    254D9         𥓙
   *    2550E         𥔎
   *    255A7         𥖧
   *    25771         𥝱
   *    257A9         𥞩
   *    257B4         𥞴
   *    259C4         𥧄
   *    259D4         𥧔
   *    25AE3..25AE4   𥫣 𥫤
   *    25AF1         𥫱
   *    25BB2         𥮲
   *    25C4B         𥱋
   *    25C64         𥱤
   *    25DA1         𥶡
   *    25E2E         𥸮
   *    25E56         𥹖
   *    25E62         𥹢
   *    25E65         𥹥
   *    25EC2         𥻂
   *    25ED8         𥻘
   *    25EE8         𥻨
   *    25F23         𥼣
   *    25F5C         𥽜
   *    25FD4         𥿔
   *    25FE0         𥿠
   *    25FFB         𥿻
   *    2600C         𦀌
   *    26017         𦀗
   *    26060         𦁠
   *    260ED         𦃭
   *    26270         𦉰
   *    26286         𦊆
   *    2634C         𦍌
   *    26402         𦐂
   *    2667E         𦙾
   *    266B0         𦚰
   *    2671D         𦜝
   *    268DD         𦣝
   *    268EA         𦣪
   *    26951         𦥑
   *    2696F         𦥯
   *    269DD         𦧝
   *    26A1E         𦨞
   *    26A58         𦩘
   *    26A8C         𦪌
   *    26AB7         𦪷
   *    26AFF         𦫿
   *    26C29         𦰩
   *    26C73         𦱳
   *    26CDD         𦳝
   *    26E40         𦹀
   *    26E65         𦹥
   *    26F94         𦾔
   *    26FF6..26FF8   𦿶 𦿷 𦿸
   *    270F4         𧃴
   *    2710D         𧄍
   *    27139         𧄹
   *    273DA..273DB   𧏚 𧏛
   *    273FE         𧏾
   *    27410         𧐐
   *    27449         𧑉
   *    27614..27615   𧘔 𧘕
   *    27631         𧘱
   *    27684         𧚄
   *    27693         𧚓
   *    2770E         𧜎
   *    27723         𧜣
   *    27752         𧝒
   *    27985         𧦅
   *    27A84         𧪄
   *    27BB3         𧮳
   *    27BBE         𧮾
   *    27BC7         𧯇
   *    27CB8         𧲸
   *    27DA0         𧶠
   *    27E10         𧸐
   *    27FB7         𧾷
   *    2808A         𨂊
   *    280BB         𨂻
   *    28277         𨉷
   *    28282         𨊂
   *    282F3         𨋳
   *    283CD         𨏍
   *    2840C         𨐌
   *    28455         𨑕
   *    2856B         𨕫
   *    285C8..285C9   𨗈 𨗉
   *    286D7         𨛗
   *    286FA         𨛺
   *    28946         𨥆
   *    28949         𨥉
   *    2896B         𨥫
   *    28987..28988   𨦇 𨦈
   *    289BA..289BB   𨦺 𨦻
   *    28A1E         𨨞
   *    28A29         𨨩
   *    28A43         𨩃
   *    28A71         𨩱
   *    28A99         𨪙
   *    28ACD         𨫍
   *    28ADD         𨫝
   *    28AE4         𨫤
   *    28BC1         𨯁
   *    28BEF         𨯯
   *    28D10         𨴐
   *    28D71         𨵱
   *    28DFB         𨷻
   *    28E1F         𨸟
   *    28E36         𨸶
   *    28E89         𨺉
   *    28EEB         𨻫
   *    28F32         𨼲
   *    28FF8         𨿸
   *    292A0         𩊠
   *    292B1         𩊱
   *    29490         𩒐
   *    295CF         𩗏
   *    2967F         𩙿
   *    296F0         𩛰
   *    29719         𩜙
   *    29750         𩝐
   *    298C6         𩣆
   *    29A72         𩩲
   *    29DDB         𩷛
   *    29E15         𩸕
   *    29E3D         𩸽
   *    29E49         𩹉
   *    29E8A         𩺊
   *    29EC4         𩻄
   *    29EDB         𩻛
   *    29EE9         𩻩
   *    29FCE         𩿎
   *    2A01A         𪀚
   *    2A02F         𪀯
   *    2A082         𪂂
   *    2A0F9         𪃹
   *    2A190         𪆐
   *    2A38C         𪎌
   *    2A437         𪐷
   *    2A5F1         𪗱
   *    2A602         𪘂
   *    2A61A         𪘚
   *    2A6B2         𪚲
   *    2A708         𪜈
   *    2A70C         𪜌
   *    2A716         𪜖
   *    2A72A         𪜪
   *    2A738         𪜸
   *    2A73D         𪜽
   *    2A746         𪝆
   *    2A758         𪝘
   *    2A75F         𪝟
   *    2A784         𪞄
   *    2A789         𪞉
   *    2A7B5         𪞵
   *    2A7C7         𪟇
   *    2A7D7         𪟗
   *    2A7E7         𪟧
   *    2A7F7         𪟷
   *    2A845         𪡅
   *    2A860..2A861   𪡠 𪡡
   *    2A868         𪡨
   *    2A879         𪡹
   *    2A882         𪢂
   *    2A88A         𪢊
   *    2A8A6         𪢦
   *    2A8B1         𪢱
   *    2A8E8         𪣨
   *    2A915         𪤕
   *    2A959         𪥙
   *    2A963         𪥣
   *    2A96E         𪥮
   *    2A972         𪥲
   *    2A97D         𪥽
   *    2A98A         𪦊
   *    2A98F         𪦏
   *    2A9A6         𪦦
   *    2A9A8         𪦨
   *    2A9AE         𪦮
   *    2A9BA         𪦺
   *    2AA0E         𪨎
   *    2AA21..2AA22   𪨡 𪨢
   *    2AA2C         𪨬
   *    2AA46         𪩆
   *    2AA63         𪩣
   *    2AA69         𪩩
   *    2AA76         𪩶
   *    2AA8D         𪪍
   *    2AABF         𪪿
   *    2AAC4         𪫄
   *    2AAE7         𪫧
   *    2AAEC         𪫬
   *    2AAF8         𪫸
   *    2AB05         𪬅
   *    2AB23         𪬣
   *    2AB3F         𪬿
   *    2AB49         𪭉
   *    2AB71         𪭱
   *    2AB77         𪭷
   *    2AB7C         𪭼
   *    2AB87         𪮇
   *    2ABAC         𪮬
   *    2ABB7         𪮷
   *    2ABC6         𪯆
   *    2AC0C         𪰌
   *    2AC12         𪰒
   *    2AC2A         𪰪
   *    2AC40         𪱀
   *    2AC64         𪱤
   *    2AC69         𪱩
   *    2AC71..2AC73   𪱱 𪱲 𪱳
   *    2AC76         𪱶
   *    2AC79         𪱹
   *    2AC7B         𪱻
   *    2AC86..2AC87   𪲆 𪲇
   *    2AC92..2AC93   𪲒 𪲓
   *    2AC96         𪲖
   *    2ACA2         𪲢
   *    2ACA6         𪲦
   *    2ACA8         𪲨
   *    2ACAC         𪲬
   *    2ACB3         𪲳
   *    2ACBC         𪲼
   *    2ACC3         𪳃
   *    2ACC9..2ACCA   𪳉 𪳊
   *    2ACD2..2ACD3   𪳒 𪳓
   *    2ACD6         𪳖
   *    2ACE1..2ACE2   𪳡 𪳢
   *    2ACE4         𪳤
   *    2ACE7         𪳧
   *    2ACF1         𪳱
   *    2ACF3         𪳳
   *    2ACFA         𪳺
   *    2AD00         𪴀
   *    2AD06..2AD07   𪴆 𪴇
   *    2AD0E         𪴎
   *    2AD10         𪴐
   *    2AD13         𪴓
   *    2AD15         𪴕
   *    2AD1C         𪴜
   *    2AD20..2AD21   𪴠 𪴡
   *    2AD25         𪴥
   *    2AD61         𪵡
   *    2AD89         𪶉
   *    2ADCC         𪷌
   *    2AE09         𪸉
   *    2AE22         𪸢
   *    2AE2B         𪸫
   *    2AE76         𪹶
   *    2AE8A         𪺊
   *    2AE90         𪺐
   *    2AEB2         𪺲
   *    2AECC         𪻌
   *    2AF2A         𪼪
   *    2AF36..2AF39   𪼶 𪼷 𪼸 𪼹
   *    2AF3B..2AF3C   𪼻 𪼼
   *    2AF3F         𪼿
   *    2AF4B         𪽋
   *    2AF58         𪽘
   *    2AF65         𪽥
   *    2AF76         𪽶
   *    2AF7A         𪽺
   *    2AF97         𪾗
   *    2AFA7..2AFA8   𪾧 𪾨
   *    2AFAC         𪾬
   *    2AFB2         𪾲
   *    2AFB5         𪾵
   *    2AFBB..2AFBC   𪾻 𪾼
   *    2AFBF         𪾿
   *    2AFC8         𪿈
   *    2AFD3         𪿓
   *    2AFFC         𪿼
   *    2B003         𫀃
   *    2B016         𫀖
   *    2B027         𫀧
   *    2B039         𫀹
   *    2B041         𫁁
   *    2B046         𫁆
   *    2B048         𫁈
   *    2B051         𫁑
   *    2B056..2B057   𫁖 𫁗
   *    2B076         𫁶
   *    2B07D         𫁽
   *    2B085         𫂅
   *    2B092         𫂒
   *    2B0A3         𫂣
   *    2B0A9         𫂩
   *    2B0B2         𫂲
   *    2B0C4         𫃄
   *    2B0C7         𫃇
   *    2B0E5         𫃥
   *    2B0F2         𫃲
   *    2B10A         𫄊
   *    2B117         𫄗
   *    2B17F         𫅿
   *    2B193         𫆓
   *    2B19B         𫆛
   *    2B1A8         𫆨
   *    2B1B7         𫆷
   *    2B1BA         𫆺
   *    2B1C5         𫇅
   *    2B1DC         𫇜
   *    2B1E0         𫇠
   *    2B1E8..2B1E9   𫇨 𫇩
   *    2B1FB         𫇻
   *    2B1FF         𫇿
   *    2B202         𫈂
   *    2B206..2B207   𫈆 𫈇
   *    2B213         𫈓
   *    2B21E         𫈞
   *    2B224         𫈤
   *    2B239         𫈹
   *    2B23D         𫈽
   *    2B240         𫉀
   *    2B247         𫉇
   *    2B251..2B252   𫉑 𫉒
   *    2B259         𫉙
   *    2B25C         𫉜
   *    2B260         𫉠
   *    2B26B         𫉫
   *    2B270         𫉰
   *    2B27B         𫉻
   *    2B280         𫊀
   *    2B284         𫊄
   *    2B298         𫊘
   *    2B29C         𫊜
   *    2B2A6..2B2A7   𫊦 𫊧
   *    2B2B0         𫊰
   *    2B2B3         𫊳
   *    2B2BA         𫊺
   *    2B2BD..2B2BE   𫊽 𫊾
   *    2B2C1..2B2C3   𫋁 𫋂 𫋃
   *    2B2C5..2B2C6   𫋅 𫋆
   *    2B2C9         𫋉
   *    2B2CB         𫋋
   *    2B2CE..2B2CF   𫋎 𫋏
   *    2B2D5..2B2D6   𫋕 𫋖
   *    2B2D8         𫋘
   *    2B2DA         𫋚
   *    2B2DD         𫋝
   *    2B2E2         𫋢
   *    2B2E4..2B2E5   𫋤 𫋥
   *    2B2EC         𫋬
   *    2B2EF         𫋯
   *    2B2F5         𫋵
   *    2B2FC         𫋼
   *    2B305         𫌅
   *    2B308         𫌈
   *    2B313         𫌓
   *    2B31D         𫌝
   *    2B31F         𫌟
   *    2B325         𫌥
   *    2B327         𫌧
   *    2B337         𫌷
   *    2B344         𫍄
   *    2B346         𫍆
   *    2B34B         𫍋
   *    2B352         𫍒
   *    2B354         𫍔
   *    2B381         𫎁
   *    2B395         𫎕
   *    2B3AE         𫎮
   *    2B3C0         𫏀
   *    2B3C2         𫏂
   *    2B3C9         𫏉
   *    2B3CF         𫏏
   *    2B3D4         𫏔
   *    2B3E1         𫏡
   *    2B3E9         𫏩
   *    2B3EE         𫏮
   *    2B3F2         𫏲
   *    2B41E         𫐞
   *    2B424         𫐤
   *    2B42F         𫐯
   *    2B456         𫑖
   *    2B475         𫑵
   *    2B481         𫒁
   *    2B486         𫒆
   *    2B489         𫒉
   *    2B48E         𫒎
   *    2B492..2B493   𫒒 𫒓
   *    2B496         𫒖
   *    2B499..2B49A   𫒙 𫒚
   *    2B49C         𫒜
   *    2B4A0         𫒠
   *    2B4A6         𫒦
   *    2B4AA         𫒪
   *    2B4AF         𫒯
   *    2B4B4         𫒴
   *    2B4BC         𫒼
   *    2B4C0         𫓀
   *    2B4C3         𫓃
   *    2B4CA         𫓊
   *    2B4CD         𫓍
   *    2B4D0         𫓐
   *    2B4D4         𫓔
   *    2B4D8         𫓘
   *    2B4DA         𫓚
   *    2B519..2B51B   𫔙 𫔚 𫔛
   *    2B51D..2B51F   𫔝 𫔞 𫔟
   *    2B52A         𫔪
   *    2B55F         𫕟
   *    2B563         𫕣
   *    2B57B         𫕻
   *    2B58B         𫖋
   *    2B59D         𫖝
   *    2B59F         𫖟
   *    2B5A8         𫖨
   *    2B5C6         𫗆
   *    2B5DB         𫗛
   *    2B607         𫘇
   *    2B60B         𫘋
   *    2B615         𫘕
   *    2B639         𫘹
   *    2B648         𫙈
   *    2B64B         𫙋
   *    2B64F         𫙏
   *    2B651..2B652   𫙑 𫙒
   *    2B655..2B656   𫙕 𫙖
   *    2B65A         𫙚
   *    2B65C         𫙜
   *    2B65E..2B661   𫙞 𫙟 𫙠 𫙡
   *    2B665         𫙥
   *    2B667..2B66C   𫙧 𫙨 𫙩 𫙪 𫙫 𫙬
   *    2B66E..2B676   𫙮 𫙯 𫙰 𫙱 𫙲 𫙳 𫙴 𫙵 𫙶
   *    2B678..2B679   𫙸 𫙹
   *    2B67B..2B67C   𫙻 𫙼
   *    2B67E         𫙾
   *    2B680         𫚀
   *    2B682         𫚂
   *    2B684..2B687   𫚄 𫚅 𫚆 𫚇
   *    2B6B0         𫚰
   *    2B6B5         𫚵
   *    2B6BA..2B6BB   𫚺 𫚻
   *    2B6BE         𫚾
   *    2B6C0         𫛀
   *    2B6C4..2B6C5   𫛄 𫛅
   *    2B6C7         𫛇
   *    2B6C9..2B6CA   𫛉 𫛊
   *    2B6CD         𫛍
   *    2B6D1..2B6D2   𫛑 𫛒
   *    2B6D4         𫛔
   *    2B6D6..2B6D9   𫛖 𫛗 𫛘 𫛙
   *    2B71B         𫜛
   *    2B724         𫜤
   *    2B740..2B747   𫝀 𫝁 𫝂 𫝃 𫝄 𫝅 𫝆 𫝇
   *    2B749..2B74A   𫝉 𫝊
   *    2B74C..2B74D   𫝌 𫝍
   *    2B750..2B752   𫝐 𫝑 𫝒
   *    2B754..2B757   𫝔 𫝕 𫝖 𫝗
   *    2B75D         𫝝
   *    2B75F..2B764   𫝟 𫝠 𫝡 𫝢 𫝣 𫝤
   *    2B76F..2B773   𫝯 𫝰 𫝱 𫝲 𫝳
   *    2B776         𫝶
   *    2B778..2B779   𫝸 𫝹
   *    2B77F..2B781   𫝿 𫞀 𫞁
   *    2B783..2B784   𫞃 𫞄
   *    2B786..2B78A   𫞆 𫞇 𫞈 𫞉 𫞊
   *    2B78C..2B793   𫞌 𫞍 𫞎 𫞏 𫞐 𫞑 𫞒 𫞓
   *    2B795..2B796   𫞕 𫞖
   *    2B798..2B799   𫞘 𫞙
   *    2B79C         𫞜
   *    2B79E..2B79F   𫞞 𫞟
   *    2B7A4         𫞤
   *    2B7AA..2B7AB   𫞪 𫞫
   *    2B7AE..2B7B2   𫞮 𫞯 𫞰 𫞱 𫞲
   *    2B7B4..2B7B6   𫞴 𫞵 𫞶
   *    2B7B8..2B7BA   𫞸 𫞹 𫞺
   *    2B7BE..2B7C1   𫞾 𫞿 𫟀 𫟁
   *    2B7C8         𫟈
   *    2B7CB..2B7D0   𫟋 𫟌 𫟍 𫟎 𫟏 𫟐
   *    2B7D2..2B7D4   𫟒 𫟓 𫟔
   *    2B7D6         𫟖
   *    2B7D8..2B7DD   𫟘 𫟙 𫟚 𫟛 𫟜 𫟝
   *    2B7E8..2B7EA   𫟨 𫟩 𫟪
   *    2B7EF..2B7F1   𫟯 𫟰 𫟱
   *    2B803         𫠃
   *    2B809         𫠉
   *    2B813..2B815   𫠓 𫠔 𫠕
   *    2B818         𫠘
   *    2B81D         𫠝
   *    2B864         𫡤
   *    2B877         𫡷
   *    2B88F         𫢏
   *    2B8BD         𫢽
   *    2B8C1         𫣁
   *    2B8C4         𫣄
   *    2B8D2         𫣒
   *    2B8D5         𫣕
   *    2B8F5         𫣵
   *    2B91C         𫤜
   *    2B91E         𫤞
   *    2B960         𫥠
   *    2B972         𫥲
   *    2B97B         𫥻
   *    2B988         𫦈
   *    2B9A4..2B9A5   𫦤 𫦥
   *    2B9AB         𫦫
   *    2B9B6         𫦶
   *    2B9B9         𫦹
   *    2B9BF         𫦿
   *    2BA2D         𫨭
   *    2BA6E         𫩮
   *    2BA9B         𫪛
   *    2BAA4         𫪤
   *    2BACD         𫫍
   *    2BAED         𫫭
   *    2BAF1         𫫱
   *    2BB4F         𫭏
   *    2BB57         𫭗
   *    2BB71         𫭱
   *    2BB8E         𫮎
   *    2BBA4         𫮤
   *    2BBAD         𫮭
   *    2BBBA         𫮺
   *    2BBBE         𫮾
   *    2BBF8         𫯸
   *    2BC00..2BC01   𫰀 𫰁
   *    2BC13         𫰓
   *    2BC1A         𫰚
   *    2BC48..2BC49   𫱈 𫱉
   *    2BC4B         𫱋
   *    2BC63         𫱣
   *    2BC67         𫱧
   *    2BC7B         𫱻
   *    2BCA7         𫲧
   *    2BCA9         𫲩
   *    2BCAE         𫲮
   *    2BCB5         𫲵
   *    2BCCD         𫳍
   *    2BD34         𫴴
   *    2BD57         𫵗
   *    2BD69         𫵩
   *    2BD74         𫵴
   *    2BD7D         𫵽
   *    2BD99         𫶙
   *    2BDA0         𫶠
   *    2BDA7         𫶧
   *    2BDD1..2BDD2   𫷑 𫷒
   *    2BE28         𫸨
   *    2BE3E         𫸾
   *    2BE53         𫹓
   *    2BE67..2BE68   𫹧 𫹨
   *    2BE8F         𫺏
   *    2BE97         𫺗
   *    2BEAD         𫺭
   *    2BEDB         𫻛
   *    2BEE5         𫻥
   *    2BEE7         𫻧
   *    2BF0B         𫼋
   *    2BF13         𫼓
   *    2BF33         𫼳
   *    2BF6A         𫽪
   *    2BF6C         𫽬
   *    2BF85         𫾅
   *    2BF92         𫾒
   *    2BFED         𫿭
   *    2BFFE         𫿾
   *    2C017         𬀗
   *    2C027         𬀧
   *    2C02C         𬀬
   *    2C083         𬂃
   *    2C097         𬂗
   *    2C09B..2C09C   𬂛 𬂜
   *    2C09F..2C0A1   𬂟 𬂠 𬂡
   *    2C0AC         𬂬
   *    2C0B3         𬂳
   *    2C0B5..2C0B6   𬂵 𬂶
   *    2C0BF         𬂿
   *    2C0CF         𬃏
   *    2C0D3         𬃓
   *    2C0D7         𬃗
   *    2C0DD         𬃝
   *    2C0E2..2C0E3   𬃢 𬃣
   *    2C0F0         𬃰
   *    2C0F5..2C0F6   𬃵 𬃶
   *    2C0FA         𬃺
   *    2C0FE         𬃾
   *    2C109         𬄉
   *    2C10B..2C10C   𬄋 𬄌
   *    2C10E         𬄎
   *    2C112         𬄒
   *    2C116..2C117   𬄖 𬄗
   *    2C11A         𬄚
   *    2C11F..2C120   𬄟 𬄠
   *    2C125         𬄥
   *    2C127         𬄧
   *    2C12B         𬄫
   *    2C132         𬄲
   *    2C136         𬄶
   *    2C138         𬄸
   *    2C13A..2C13C   𬄺 𬄻 𬄼
   *    2C145         𬅅
   *    2C147         𬅇
   *    2C14A..2C14B   𬅊 𬅋
   *    2C14E         𬅎
   *    2C152         𬅒
   *    2C157         𬅗
   *    2C18D         𬆍
   *    2C1D0         𬇐
   *    2C1DC         𬇜
   *    2C207         𬈇
   *    2C235         𬈵
   *    2C258         𬉘
   *    2C27A         𬉺
   *    2C2CE         𬋎
   *    2C2E0         𬋠
   *    2C2EC         𬋬
   *    2C329         𬌩
   *    2C33C         𬌼
   *    2C354         𬍔
   *    2C356         𬍖
   *    2C36F         𬍯
   *    2C3A4         𬎤
   *    2C3A6         𬎦
   *    2C3AB         𬎫
   *    2C3AE..2C3B0   𬎮 𬎯 𬎰
   *    2C3B4         𬎴
   *    2C3C8         𬏈
   *    2C3D0         𬏐
   *    2C3E3         𬏣
   *    2C3F6         𬏶
   *    2C3FC         𬏼
   *    2C424         𬐤
   *    2C448         𬑈
   *    2C44E         𬑎
   *    2C450         𬑐
   *    2C456         𬑖
   *    2C45C..2C45D   𬑜 𬑝
   *    2C462         𬑢
   *    2C468         𬑨
   *    2C46C         𬑬
   *    2C47D         𬑽
   *    2C485         𬒅
   *    2C4A8         𬒨
   *    2C4AD         𬒭
   *    2C4C1         𬓁
   *    2C4C4         𬓄
   *    2C4C7         𬓇
   *    2C4D6         𬓖
   *    2C4E6         𬓦
   *    2C4F3         𬓳
   *    2C4F9         𬓹
   *    2C500         𬔀
   *    2C50E..2C50F   𬔎 𬔏
   *    2C528         𬔨
   *    2C52C         𬔬
   *    2C530         𬔰
   *    2C538         𬔸
   *    2C541         𬕁
   *    2C54B         𬕋
   *    2C54E         𬕎
   *    2C567         𬕧
   *    2C56E         𬕮
   *    2C576         𬕶
   *    2C587         𬖇
   *    2C592         𬖒
   *    2C5A3         𬖣
   *    2C5BB         𬖻
   *    2C5D6         𬗖
   *    2C5EC         𬗬
   *    2C608         𬘈
   *    2C670         𬙰
   *    2C69D         𬚝
   *    2C6A9         𬚩
   *    2C6C2         𬛂
   *    2C6CC         𬛌
   *    2C6D3         𬛓
   *    2C70B         𬜋
   *    2C716..2C717   𬜖 𬜗
   *    2C723         𬜣
   *    2C729         𬜩
   *    2C72D..2C72E   𬜭 𬜮
   *    2C730..2C732   𬜰 𬜱 𬜲
   *    2C734         𬜴
   *    2C737         𬜷
   *    2C73C         𬜼
   *    2C742         𬝂
   *    2C74D         𬝍
   *    2C754         𬝔
   *    2C756         𬝖
   *    2C75D..2C75E   𬝝 𬝞
   *    2C765..2C766   𬝥 𬝦
   *    2C769         𬝩
   *    2C76D         𬝭
   *    2C77B         𬝻
   *    2C77F         𬝿
   *    2C789         𬞉
   *    2C78D         𬞍
   *    2C790         𬞐
   *    2C794         𬞔
   *    2C799         𬞙
   *    2C7A1         𬞡
   *    2C7B9         𬞹
   *    2C7C2         𬟂
   *    2C7CB         𬟋
   *    2C7D0         𬟐
   *    2C7D2         𬟒
   *    2C7E2         𬟢
   *    2C7E6         𬟦
   *    2C7F6..2C7F8   𬟶 𬟷 𬟸
   *    2C800         𬠀
   *    2C802         𬠂
   *    2C809..2C80A   𬠉 𬠊
   *    2C80D..2C80E   𬠍 𬠎
   *    2C812         𬠒
   *    2C818..2C819   𬠘 𬠙
   *    2C81D..2C81F   𬠝 𬠞 𬠟
   *    2C824         𬠤
   *    2C827         𬠧
   *    2C829         𬠩
   *    2C82C..2C82F   𬠬 𬠭 𬠮 𬠯
   *    2C833..2C834   𬠳 𬠴
   *    2C83B         𬠻
   *    2C840         𬡀
   *    2C85B         𬡛
   *    2C869         𬡩
   *    2C87D..2C87E   𬡽 𬡾
   *    2C884         𬢄
   *    2C886         𬢆
   *    2C896         𬢖
   *    2C8B7         𬢷
   *    2C8BD         𬢽
   *    2C8C4         𬣄
   *    2C8D0         𬣐
   *    2C8D5..2C8D7   𬣕 𬣖 𬣗
   *    2C932         𬤲
   *    2C981         𬦁
   *    2C99C         𬦜
   *    2C9A6         𬦦
   *    2C9AD         𬦭
   *    2C9B7         𬦷
   *    2C9BA         𬦺
   *    2C9C5         𬧅
   *    2C9D3         𬧓
   *    2C9E7         𬧧
   *    2C9EC..2C9ED   𬧬 𬧭
   *    2C9F3         𬧳
   *    2CA1C         𬨜
   *    2CA2F         𬨯
   *    2CA56         𬩖
   *    2CAA6         𬪦
   *    2CAAC         𬪬
   *    2CABB         𬪻
   *    2CAC4         𬫄
   *    2CAD1         𬫑
   *    2CAD6..2CAD7   𬫖 𬫗
   *    2CAE0         𬫠
   *    2CAE5         𬫥
   *    2CAE7         𬫧
   *    2CAED         𬫭
   *    2CAF0..2CAF1   𬫰 𬫱
   *    2CAFB         𬫻
   *    2CAFE         𬫾
   *    2CB02         𬬂
   *    2CB08..2CB0A   𬬈 𬬉 𬬊
   *    2CB17         𬬗
   *    2CB22..2CB23   𬬢 𬬣
   *    2CB85..2CB86   𬮅 𬮆
   *    2CB91         𬮑
   *    2CB96         𬮖
   *    2CBC1         𬯁
   *    2CBF2..2CBF3   𬯲 𬯳
   *    2CBF8         𬯸
   *    2CC08         𬰈
   *    2CC26         𬰦
   *    2CC28         𬰨
   *    2CC2C         𬰬
   *    2CC4B         𬱋
   *    2CC4F         𬱏
   *    2CC74         𬱴
   *    2CC9D         𬲝
   *    2CCF0         𬳰
   *    2CD27         𬴧
   *    2CD2C         𬴬
   *    2CD3D..2CD3E   𬴽 𬴾
   *    2CD41..2CD42   𬵁 𬵂
   *    2CD44         𬵄
   *    2CD46..2CD48   𬵆 𬵇 𬵈
   *    2CD4A..2CD4D   𬵊 𬵋 𬵌 𬵍
   *    2CD4F..2CD52   𬵏 𬵐 𬵑 𬵒
   *    2CD54..2CD57   𬵔 𬵕 𬵖 𬵗
   *    2CD5A         𬵚
   *    2CD5C..2CD63   𬵜 𬵝 𬵞 𬵟 𬵠 𬵡 𬵢 𬵣
   *    2CD66..2CD6D   𬵦 𬵧 𬵨 𬵩 𬵪 𬵫 𬵬 𬵭
   *    2CD6F..2CD77   𬵯 𬵰 𬵱 𬵲 𬵳 𬵴 𬵵 𬵶 𬵷
   *    2CD79..2CD7B   𬵹 𬵺 𬵻
   *    2CD7D..2CD7E   𬵽 𬵾
   *    2CDBF         𬶿
   *    2CDC4         𬷄
   *    2CDC9         𬷉
   *    2CDD2         𬷒
   *    2CDD4         𬷔
   *    2CDD7         𬷗
   *    2CDD9         𬷙
   *    2CDDD         𬷝
   *    2CDE1..2CDE2   𬷡 𬷢
   *    2CDE6..2CDE7   𬷦 𬷧
   *    2CDEB..2CDEC   𬷫 𬷬
   *    2CDF0..2CDF1   𬷰 𬷱
   *    2CDF3         𬷳
   *    2CDF5         𬷵
   *    2CDF7         𬷷
   *    2CDFA         𬷺
   *    2CE3D         𬸽
   *    2CEB0..2CEB1   𬺰 𬺱
   *    2CEB3         𬺳
   *    2CEB5..2CEB6   𬺵 𬺶
   *    2CEB8         𬺸
   *    2CEBA..2CEBB   𬺺 𬺻
   *    2CEBD         𬺽
   *    2CEC0..2CEC2   𬻀 𬻁 𬻂
   *    2CEC6..2CEC9   𬻆 𬻇 𬻈 𬻉
   *    2CECB         𬻋
   *    2CECE         𬻎
   *    2CED0..2CED2   𬻐 𬻑 𬻒
   *    2CED5         𬻕
   *    2CED8         𬻘
   *    2CEDB..2CEDE   𬻛 𬻜 𬻝 𬻞
   *    2CEE5         𬻥
   *    2CEF5         𬻵
   *    2CEF7..2CEF8   𬻷 𬻸
   *    2CEFB         𬻻
   *    2CEFF..2CF02   𬻿 𬼀 𬼁 𬼂
   *    2CF04         𬼄
   *    2CF06         𬼆
   *    2CF08         𬼈
   *    2CF0C..2CF0D   𬼌 𬼍
   *    2CF16..2CF1A   𬼖 𬼗 𬼘 𬼙 𬼚
   *    2CF1C         𬼜
   *    2CF1F         𬼟
   *    2CF2B         𬼫
   *    2CF38         𬼸
   *    2CF3A         𬼺
   *    2CF3D..2CF3F   𬼽 𬼾 𬼿
   *    2CF43         𬽃
   *    2CF46..2CF4D   𬽆 𬽇 𬽈 𬽉 𬽊 𬽋 𬽌 𬽍
   *    2CF55..2CF56   𬽕 𬽖
   *    2CF59..2CF5A   𬽙 𬽚
   *    2CF5C..2CF5D   𬽜 𬽝
   *    2CF60..2CF61   𬽠 𬽡
   *    2CF63..2CF64   𬽣 𬽤
   *    2CF66..2CF67   𬽦 𬽧
   *    2CF69         𬽩
   *    2CF6B         𬽫
   *    2CF6E         𬽮
   *    2CF76..2CF77   𬽶 𬽷
   *    2CF79         𬽹
   *    2CF81         𬾁
   *    2CF84         𬾄
   *    2CF8A         𬾊
   *    2CF8E         𬾎
   *    2CF97..2CF98   𬾗 𬾘
   *    2CF9A         𬾚
   *    2CFA8         𬾨
   *    2CFAE         𬾮
   *    2CFB9         𬾹
   *    2CFBB         𬾻
   *    2CFBF         𬾿
   *    2CFC2         𬿂
   *    2CFDB         𬿛
   *    2CFE6         𬿦
   *    2CFED         𬿭
   *    2CFF2         𬿲
   *    2CFF6         𬿶
   *    2CFFB         𬿻
   *    2D000         𭀀
   *    2D009         𭀉
   *    2D017..2D01A   𭀗 𭀘 𭀙 𭀚
   *    2D01C..2D01D   𭀜 𭀝
   *    2D01F..2D021   𭀟 𭀠 𭀡
   *    2D023..2D025   𭀣 𭀤 𭀥
   *    2D028         𭀨
   *    2D030..2D031   𭀰 𭀱
   *    2D044         𭁄
   *    2D046..2D049   𭁆 𭁇 𭁈 𭁉
   *    2D050         𭁐
   *    2D054..2D055   𭁔 𭁕
   *    2D057         𭁗
   *    2D060..2D063   𭁠 𭁡 𭁢 𭁣
   *    2D066         𭁦
   *    2D068..2D06B   𭁨 𭁩 𭁪 𭁫
   *    2D06D         𭁭
   *    2D06F         𭁯
   *    2D072..2D074   𭁲 𭁳 𭁴
   *    2D076..2D077   𭁶 𭁷
   *    2D07A..2D07C   𭁺 𭁻 𭁼
   *    2D081         𭂁
   *    2D085         𭂅
   *    2D088         𭂈
   *    2D08E         𭂎
   *    2D091         𭂑
   *    2D093..2D094   𭂓 𭂔
   *    2D097         𭂗
   *    2D09A         𭂚
   *    2D09C         𭂜
   *    2D0AB..2D0AC   𭂫 𭂬
   *    2D0AF         𭂯
   *    2D0B2..2D0B4   𭂲 𭂳 𭂴
   *    2D0BE         𭂾
   *    2D0C4..2D0C6   𭃄 𭃅 𭃆
   *    2D0CC         𭃌
   *    2D0D9..2D0DA   𭃙 𭃚
   *    2D0E1         𭃡
   *    2D0E4..2D0E5   𭃤 𭃥
   *    2D0E8         𭃨
   *    2D0ED         𭃭
   *    2D0F0..2D0F2   𭃰 𭃱 𭃲
   *    2D0FF         𭃿
   *    2D10F         𭄏
   *    2D118         𭄘
   *    2D11E         𭄞
   *    2D123         𭄣
   *    2D125         𭄥
   *    2D127         𭄧
   *    2D12B         𭄫
   *    2D133..2D134   𭄳 𭄴
   *    2D139         𭄹
   *    2D13F..2D143   𭄿 𭅀 𭅁 𭅂 𭅃
   *    2D145         𭅅
   *    2D14A         𭅊
   *    2D14D         𭅍
   *    2D150..2D152   𭅐 𭅑 𭅒
   *    2D154         𭅔
   *    2D158         𭅘
   *    2D15D         𭅝
   *    2D15F..2D161   𭅟 𭅠 𭅡
   *    2D169         𭅩
   *    2D16F..2D170   𭅯 𭅰
   *    2D172..2D175   𭅲 𭅳 𭅴 𭅵
   *    2D179..2D17A   𭅹 𭅺
   *    2D17C..2D17D   𭅼 𭅽
   *    2D181..2D184   𭆁 𭆂 𭆃 𭆄
   *    2D186..2D187   𭆆 𭆇
   *    2D189         𭆉
   *    2D18F..2D190   𭆏 𭆐
   *    2D192         𭆒
   *    2D194         𭆔
   *    2D199         𭆙
   *    2D19E         𭆞
   *    2D1A1..2D1A2   𭆡 𭆢
   *    2D1A4..2D1A5   𭆤 𭆥
   *    2D1AA..2D1AC   𭆪 𭆫 𭆬
   *    2D1AF         𭆯
   *    2D1B4         𭆴
   *    2D1B6..2D1B7   𭆶 𭆷
   *    2D1C1         𭇁
   *    2D1C6         𭇆
   *    2D1CA         𭇊
   *    2D1D1         𭇑
   *    2D1D6..2D1D7   𭇖 𭇗
   *    2D1E2         𭇢
   *    2D1E5         𭇥
   *    2D1EA         𭇪
   *    2D206         𭈆
   *    2D21D         𭈝
   *    2D22C         𭈬
   *    2D239         𭈹
   *    2D23C         𭈼
   *    2D247..2D248   𭉇 𭉈
   *    2D258..2D259   𭉘 𭉙
   *    2D260         𭉠
   *    2D262         𭉢
   *    2D269         𭉩
   *    2D275         𭉵
   *    2D27F         𭉿
   *    2D289..2D28A   𭊉 𭊊
   *    2D28C         𭊌
   *    2D28F         𭊏
   *    2D2C6         𭋆
   *    2D2D8         𭋘
   *    2D2DB         𭋛
   *    2D30C         𭌌
   *    2D314         𭌔
   *    2D326         𭌦
   *    2D330         𭌰
   *    2D34F         𭍏
   *    2D35E         𭍞
   *    2D360         𭍠
   *    2D365..2D366   𭍥 𭍦
   *    2D376         𭍶
   *    2D37B..2D37C   𭍻 𭍼
   *    2D380         𭎀
   *    2D385         𭎅
   *    2D388         𭎈
   *    2D38E         𭎎
   *    2D395         𭎕
   *    2D399         𭎙
   *    2D39B..2D39C   𭎛 𭎜
   *    2D3A2         𭎢
   *    2D3A4         𭎤
   *    2D3AE         𭎮
   *    2D3C0         𭏀
   *    2D3CD         𭏍
   *    2D3D0         𭏐
   *    2D3D5         𭏕
   *    2D3DB         𭏛
   *    2D3FE         𭏾
   *    2D400         𭐀
   *    2D40A         𭐊
   *    2D412         𭐒
   *    2D415..2D416   𭐕 𭐖
   *    2D419         𭐙
   *    2D41D         𭐝
   *    2D420         𭐠
   *    2D422..2D423   𭐢 𭐣
   *    2D425..2D427   𭐥 𭐦 𭐧
   *    2D42B..2D42C   𭐫 𭐬
   *    2D42E         𭐮
   *    2D430..2D431   𭐰 𭐱
   *    2D433..2D434   𭐳 𭐴
   *    2D438         𭐸
   *    2D442..2D443   𭑂 𭑃
   *    2D445         𭑅
   *    2D44A..2D44B   𭑊 𭑋
   *    2D44D         𭑍
   *    2D44F         𭑏
   *    2D451..2D452   𭑑 𭑒
   *    2D455..2D456   𭑕 𭑖
   *    2D458         𭑘
   *    2D45A         𭑚
   *    2D45C         𭑜
   *    2D45E..2D45F   𭑞 𭑟
   *    2D463         𭑣
   *    2D468         𭑨
   *    2D46A         𭑪
   *    2D46E..2D46F   𭑮 𭑯
   *    2D475         𭑵
   *    2D47E..2D480   𭑾 𭑿 𭒀
   *    2D491         𭒑
   *    2D494         𭒔
   *    2D497         𭒗
   *    2D49E         𭒞
   *    2D4A1         𭒡
   *    2D4AD         𭒭
   *    2D4B8         𭒸
   *    2D4BC..2D4BD   𭒼 𭒽
   *    2D4C2         𭓂
   *    2D4C7..2D4C8   𭓇 𭓈
   *    2D4D4..2D4D5   𭓔 𭓕
   *    2D4D7..2D4D8   𭓗 𭓘
   *    2D4E6         𭓦
   *    2D4E9         𭓩
   *    2D4EF         𭓯
   *    2D4F1         𭓱
   *    2D4F3         𭓳
   *    2D4FB         𭓻
   *    2D4FD..2D500   𭓽 𭓾 𭓿 𭔀
   *    2D503..2D505   𭔃 𭔄 𭔅
   *    2D50C         𭔌
   *    2D516..2D517   𭔖 𭔗
   *    2D51F         𭔟
   *    2D52C         𭔬
   *    2D531..2D532   𭔱 𭔲
   *    2D535..2D539   𭔵 𭔶 𭔷 𭔸 𭔹
   *    2D53E..2D53F   𭔾 𭔿
   *    2D544         𭕄
   *    2D547         𭕇
   *    2D549         𭕉
   *    2D54B         𭕋
   *    2D54D         𭕍
   *    2D550         𭕐
   *    2D552         𭕒
   *    2D558..2D559   𭕘 𭕙
   *    2D55D         𭕝
   *    2D565         𭕥
   *    2D56D         𭕭
   *    2D56F         𭕯
   *    2D57E..2D580   𭕾 𭕿 𭖀
   *    2D583..2D584   𭖃 𭖄
   *    2D58C         𭖌
   *    2D592..2D593   𭖒 𭖓
   *    2D5A9         𭖩
   *    2D5AB..2D5AC   𭖫 𭖬
   *    2D5B1         𭖱
   *    2D5C3         𭗃
   *    2D5C7         𭗇
   *    2D5D2..2D5D5   𭗒 𭗓 𭗔 𭗕
   *    2D5DE         𭗞
   *    2D5EA         𭗪
   *    2D5ED         𭗭
   *    2D5F6         𭗶
   *    2D5FB..2D5FC   𭗻 𭗼
   *    2D5FE..2D600   𭗾 𭗿 𭘀
   *    2D606         𭘆
   *    2D609..2D60A   𭘉 𭘊
   *    2D60D         𭘍
   *    2D611..2D612   𭘑 𭘒
   *    2D618         𭘘
   *    2D61A         𭘚
   *    2D61E         𭘞
   *    2D623         𭘣
   *    2D627         𭘧
   *    2D629         𭘩
   *    2D62B         𭘫
   *    2D634         𭘴
   *    2D63E         𭘾
   *    2D64C..2D64D   𭙌 𭙍
   *    2D652         𭙒
   *    2D654         𭙔
   *    2D657         𭙗
   *    2D661         𭙡
   *    2D663         𭙣
   *    2D667         𭙧
   *    2D66F         𭙯
   *    2D671         𭙱
   *    2D676         𭙶
   *    2D678         𭙸
   *    2D67A..2D67B   𭙺 𭙻
   *    2D67D         𭙽
   *    2D680         𭚀
   *    2D682..2D683   𭚂 𭚃
   *    2D691..2D693   𭚑 𭚒 𭚓
   *    2D698         𭚘
   *    2D69F         𭚟
   *    2D6A1         𭚡
   *    2D6A3         𭚣
   *    2D6A5         𭚥
   *    2D6A8         𭚨
   *    2D6AA         𭚪
   *    2D6AC         𭚬
   *    2D6AE         𭚮
   *    2D6B4..2D6B6   𭚴 𭚵 𭚶
   *    2D6B8..2D6BA   𭚸 𭚹 𭚺
   *    2D6BC         𭚼
   *    2D6BF         𭚿
   *    2D6C1..2D6C2   𭛁 𭛂
   *    2D6C5         𭛅
   *    2D6CB..2D6CD   𭛋 𭛌 𭛍
   *    2D6DD..2D6DE   𭛝 𭛞
   *    2D6E0         𭛠
   *    2D6E3         𭛣
   *    2D6E7         𭛧
   *    2D6E9..2D6EA   𭛩 𭛪
   *    2D6F3         𭛳
   *    2D702         𭜂
   *    2D708         𭜈
   *    2D70A         𭜊
   *    2D70D..2D70E   𭜍 𭜎
   *    2D710..2D711   𭜐 𭜑
   *    2D718..2D719   𭜘 𭜙
   *    2D71B         𭜛
   *    2D721         𭜡
   *    2D723..2D727   𭜣 𭜤 𭜥 𭜦 𭜧
   *    2D72E         𭜮
   *    2D733..2D736   𭜳 𭜴 𭜵 𭜶
   *    2D73E         𭜾
   *    2D745..2D746   𭝅 𭝆
   *    2D74C         𭝌
   *    2D751         𭝑
   *    2D759         𭝙
   *    2D75D         𭝝
   *    2D760..2D762   𭝠 𭝡 𭝢
   *    2D768         𭝨
   *    2D777         𭝷
   *    2D781         𭞁
   *    2D785         𭞅
   *    2D78B..2D78C   𭞋 𭞌
   *    2D78F..2D790   𭞏 𭞐
   *    2D798         𭞘
   *    2D79E         𭞞
   *    2D7A5         𭞥
   *    2D7B5         𭞵
   *    2D7B9         𭞹
   *    2D7BE         𭞾
   *    2D7C1         𭟁
   *    2D7C4         𭟄
   *    2D7DD..2D7DE   𭟝 𭟞
   *    2D7EE..2D7EF   𭟮 𭟯
   *    2D7F1..2D7F2   𭟱 𭟲
   *    2D7F6         𭟶
   *    2D7F9         𭟹
   *    2D7FC         𭟼
   *    2D7FE         𭟾
   *    2D802         𭠂
   *    2D805         𭠅
   *    2D809         𭠉
   *    2D80D         𭠍
   *    2D818         𭠘
   *    2D820..2D821   𭠠 𭠡
   *    2D848         𭡈
   *    2D860         𭡠
   *    2D877..2D878   𭡷 𭡸
   *    2D885..2D886   𭢅 𭢆
   *    2D88F         𭢏
   *    2D898         𭢘
   *    2D8A7         𭢧
   *    2D8AB         𭢫
   *    2D8AF         𭢯
   *    2D8C4         𭣄
   *    2D8D4         𭣔
   *    2D8DA         𭣚
   *    2D8E2..2D8E3   𭣢 𭣣
   *    2D8E9         𭣩
   *    2D8EC         𭣬
   *    2D8EF..2D8F1   𭣯 𭣰 𭣱
   *    2D8F4..2D8F5   𭣴 𭣵
   *    2D8F8..2D8FB   𭣸 𭣹 𭣺 𭣻
   *    2D8FE         𭣾
   *    2D900..2D904   𭤀 𭤁 𭤂 𭤃 𭤄
   *    2D90A..2D90B   𭤊 𭤋
   *    2D90F         𭤏
   *    2D913         𭤓
   *    2D915..2D919   𭤕 𭤖 𭤗 𭤘 𭤙
   *    2D91B         𭤛
   *    2D91F..2D920   𭤟 𭤠
   *    2D923         𭤣
   *    2D925         𭤥
   *    2D927         𭤧
   *    2D929..2D92A   𭤩 𭤪
   *    2D92E..2D92F   𭤮 𭤯
   *    2D93D         𭤽
   *    2D946         𭥆
   *    2D94D..2D94E   𭥍 𭥎
   *    2D957..2D958   𭥗 𭥘
   *    2D95A         𭥚
   *    2D95C         𭥜
   *    2D95F         𭥟
   *    2D966..2D967   𭥦 𭥧
   *    2D96B..2D96D   𭥫 𭥬 𭥭
   *    2D978         𭥸
   *    2D980..2D981   𭦀 𭦁
   *    2D983         𭦃
   *    2D98C         𭦌
   *    2D991         𭦑
   *    2D997         𭦗
   *    2D999..2D99B   𭦙 𭦚 𭦛
   *    2D9A0         𭦠
   *    2D9A8..2D9A9   𭦨 𭦩
   *    2D9BD         𭦽
   *    2D9C2         𭧂
   *    2D9CC         𭧌
   *    2D9D1         𭧑
   *    2D9DB         𭧛
   *    2D9DF..2D9E0   𭧟 𭧠
   *    2D9E9         𭧩
   *    2D9F3         𭧳
   *    2D9F5         𭧵
   *    2D9FD         𭧽
   *    2DA0D         𭨍
   *    2DA14         𭨔
   *    2DA17..2DA18   𭨗 𭨘
   *    2DA30         𭨰
   *    2DA39         𭨹
   *    2DA3D         𭨽
   *    2DA5C         𭩜
   *    2DA5F..2DA61   𭩟 𭩠 𭩡
   *    2DA65         𭩥
   *    2DA67         𭩧
   *    2DA69         𭩩
   *    2DA71..2DA73   𭩱 𭩲 𭩳
   *    2DA80..2DA81   𭪀 𭪁
   *    2DA88         𭪈
   *    2DA92         𭪒
   *    2DA95..2DA97   𭪕 𭪖 𭪗
   *    2DA99..2DA9C   𭪙 𭪚 𭪛 𭪜
   *    2DAAE..2DAAF   𭪮 𭪯
   *    2DAB2         𭪲
   *    2DAB4..2DAB6   𭪴 𭪵 𭪶
   *    2DABC         𭪼
   *    2DAC8         𭫈
   *    2DACD..2DACE   𭫍 𭫎
   *    2DAD2..2DAD3   𭫒 𭫓
   *    2DAD5         𭫕
   *    2DAD8         𭫘
   *    2DADA         𭫚
   *    2DADC..2DADF   𭫜 𭫝 𭫞 𭫟
   *    2DAE2         𭫢
   *    2DAEC         𭫬
   *    2DAEF..2DAF0   𭫯 𭫰
   *    2DAF3         𭫳
   *    2DAF8         𭫸
   *    2DAFA         𭫺
   *    2DAFF         𭫿
   *    2DB01..2DB02   𭬁 𭬂
   *    2DB0A         𭬊
   *    2DB10..2DB12   𭬐 𭬑 𭬒
   *    2DB1C         𭬜
   *    2DB24         𭬤
   *    2DB28         𭬨
   *    2DB2F         𭬯
   *    2DB32..2DB34   𭬲 𭬳 𭬴
   *    2DB3A         𭬺
   *    2DB43         𭭃
   *    2DB4A..2DB4B   𭭊 𭭋
   *    2DB4E..2DB4F   𭭎 𭭏
   *    2DB52..2DB55   𭭒 𭭓 𭭔 𭭕
   *    2DB58         𭭘
   *    2DB66         𭭦
   *    2DB69         𭭩
   *    2DB70         𭭰
   *    2DB7D..2DB7E   𭭽 𭭾
   *    2DB80..2DB81   𭮀 𭮁
   *    2DB83         𭮃
   *    2DB85         𭮅
   *    2DB88         𭮈
   *    2DB8E         𭮎
   *    2DB91         𭮑
   *    2DB96..2DB97   𭮖 𭮗
   *    2DBA3         𭮣
   *    2DBAB         𭮫
   *    2DBAD         𭮭
   *    2DBB0..2DBB1   𭮰 𭮱
   *    2DBB3..2DBB6   𭮳 𭮴 𭮵 𭮶
   *    2DBBA..2DBBC   𭮺 𭮻 𭮼
   *    2DBC3         𭯃
   *    2DBC7         𭯇
   *    2DBF0         𭯰
   *    2DBF4         𭯴
   *    2DBF6         𭯶
   *    2DC01         𭰁
   *    2DC09..2DC0B   𭰉 𭰊 𭰋
   *    2DC0F..2DC11   𭰏 𭰐 𭰑
   *    2DC16         𭰖
   *    2DC23         𭰣
   *    2DC26         𭰦
   *    2DC29         𭰩
   *    2DC2F..2DC31   𭰯 𭰰 𭰱
   *    2DC39         𭰹
   *    2DC3C         𭰼
   *    2DC41..2DC42   𭱁 𭱂
   *    2DC44..2DC46   𭱄 𭱅 𭱆
   *    2DC4E..2DC4F   𭱎 𭱏
   *    2DC5A         𭱚
   *    2DC61..2DC62   𭱡 𭱢
   *    2DC69..2DC6A   𭱩 𭱪
   *    2DC6E         𭱮
   *    2DC70..2DC71   𭱰 𭱱
   *    2DC74         𭱴
   *    2DC77         𭱷
   *    2DC7C..2DC7D   𭱼 𭱽
   *    2DC83..2DC85   𭲃 𭲄 𭲅
   *    2DC8B..2DC8D   𭲋 𭲌 𭲍
   *    2DC91..2DC94   𭲑 𭲒 𭲓 𭲔
   *    2DC9E         𭲞
   *    2DCA0         𭲠
   *    2DCA7         𭲧
   *    2DCAE         𭲮
   *    2DCB3         𭲳
   *    2DCBE         𭲾
   *    2DCC1         𭳁
   *    2DCC8         𭳈
   *    2DCCA         𭳊
   *    2DCCD         𭳍
   *    2DCCF..2DCD0   𭳏 𭳐
   *    2DCD3         𭳓
   *    2DCD6..2DCD7   𭳖 𭳗
   *    2DCDA..2DCDB   𭳚 𭳛
   *    2DCE0         𭳠
   *    2DCE3..2DCE4   𭳣 𭳤
   *    2DCE9         𭳩
   *    2DCEF         𭳯
   *    2DCFA         𭳺
   *    2DCFF         𭳿
   *    2DD11         𭴑
   *    2DD16         𭴖
   *    2DD19..2DD1A   𭴙 𭴚
   *    2DD2B..2DD2C   𭴫 𭴬
   *    2DD37         𭴷
   *    2DD3A..2DD3B   𭴺 𭴻
   *    2DD3D..2DD3F   𭴽 𭴾 𭴿
   *    2DD44         𭵄
   *    2DD47..2DD48   𭵇 𭵈
   *    2DD4B         𭵋
   *    2DD59         𭵙
   *    2DD5C..2DD5D   𭵜 𭵝
   *    2DD60..2DD61   𭵠 𭵡
   *    2DD68         𭵨
   *    2DD73..2DD74   𭵳 𭵴
   *    2DD7A         𭵺
   *    2DD7C         𭵼
   *    2DD82         𭶂
   *    2DD89..2DD8A   𭶉 𭶊
   *    2DD8C         𭶌
   *    2DD92         𭶒
   *    2DD97         𭶗
   *    2DDA1         𭶡
   *    2DDAA         𭶪
   *    2DDB8         𭶸
   *    2DDC4         𭷄
   *    2DDD8..2DDD9   𭷘 𭷙
   *    2DDE3         𭷣
   *    2DDF0         𭷰
   *    2DDFA         𭷺
   *    2DE08         𭸈
   *    2DE0D         𭸍
   *    2DE12         𭸒
   *    2DE20..2DE21   𭸠 𭸡
   *    2DE23         𭸣
   *    2DE2E         𭸮
   *    2DE35..2DE36   𭸵 𭸶
   *    2DE3C..2DE3D   𭸼 𭸽
   *    2DE44         𭹄
   *    2DE55..2DE57   𭹕 𭹖 𭹗
   *    2DE63..2DE64   𭹣 𭹤
   *    2DE72         𭹲
   *    2DE77         𭹷
   *    2DE7C..2DE7D   𭹼 𭹽
   *    2DE86         𭺆
   *    2DE9B..2DE9C   𭺛 𭺜
   *    2DEA3         𭺣
   *    2DEA5         𭺥
   *    2DEA7         𭺧
   *    2DEAA..2DEAB   𭺪 𭺫
   *    2DEAE         𭺮
   *    2DEB1         𭺱
   *    2DEB3         𭺳
   *    2DEB8         𭺸
   *    2DEBA         𭺺
   *    2DEBE..2DEBF   𭺾 𭺿
   *    2DEC9..2DECA   𭻉 𭻊
   *    2DECC         𭻌
   *    2DED0         𭻐
   *    2DED2         𭻒
   *    2DED7         𭻗
   *    2DED9         𭻙
   *    2DEE5         𭻥
   *    2DEE8..2DEE9   𭻨 𭻩
   *    2DEEF..2DEF0   𭻯 𭻰
   *    2DEF4..2DEF5   𭻴 𭻵
   *    2DEF8         𭻸
   *    2DEFA         𭻺
   *    2DEFF         𭻿
   *    2DF02..2DF03   𭼂 𭼃
   *    2DF05         𭼅
   *    2DF0B         𭼋
   *    2DF0E         𭼎
   *    2DF16         𭼖
   *    2DF30         𭼰
   *    2DF35         𭼵
   *    2DF38         𭼸
   *    2DF3C..2DF3E   𭼼 𭼽 𭼾
   *    2DF41..2DF42   𭽁 𭽂
   *    2DF45..2DF47   𭽅 𭽆 𭽇
   *    2DF4B         𭽋
   *    2DF4D         𭽍
   *    2DF4F         𭽏
   *    2DF52         𭽒
   *    2DF59         𭽙
   *    2DF5C         𭽜
   *    2DF5E         𭽞
   *    2DF6A         𭽪
   *    2DF78         𭽸
   *    2DF7C         𭽼
   *    2DF7F         𭽿
   *    2DF82         𭾂
   *    2DF86..2DF87   𭾆 𭾇
   *    2DF8A..2DF8B   𭾊 𭾋
   *    2DF8F..2DF90   𭾏 𭾐
   *    2DF9C         𭾜
   *    2DF9F         𭾟
   *    2DFA6         𭾦
   *    2DFA9..2DFAA   𭾩 𭾪
   *    2DFB1         𭾱
   *    2DFD3         𭿓
   *    2DFD7         𭿗
   *    2DFDC         𭿜
   *    2DFE5..2DFE6   𭿥 𭿦
   *    2DFE9..2DFEA   𭿩 𭿪
   *    2DFF5..2DFF7   𭿵 𭿶 𭿷
   *    2DFFF         𭿿
   *    2E001         𮀁
   *    2E003         𮀃
   *    2E007         𮀇
   *    2E010         𮀐
   *    2E020         𮀠
   *    2E02F         𮀯
   *    2E033         𮀳
   *    2E036         𮀶
   *    2E038         𮀸
   *    2E03F         𮀿
   *    2E043         𮁃
   *    2E04C         𮁌
   *    2E062..2E064   𮁢 𮁣 𮁤
   *    2E068..2E069   𮁨 𮁩
   *    2E06E..2E070   𮁮 𮁯 𮁰
   *    2E072..2E074   𮁲 𮁳 𮁴
   *    2E076         𮁶
   *    2E079         𮁹
   *    2E087         𮂇
   *    2E08A         𮂊
   *    2E08F         𮂏
   *    2E094         𮂔
   *    2E097         𮂗
   *    2E09A         𮂚
   *    2E09C         𮂜
   *    2E09E         𮂞
   *    2E0A2         𮂢
   *    2E0A7         𮂧
   *    2E0AF         𮂯
   *    2E0B2         𮂲
   *    2E0BA..2E0BC   𮂺 𮂻 𮂼
   *    2E0C1         𮃁
   *    2E0C4         𮃄
   *    2E0CE         𮃎
   *    2E0D6..2E0D7   𮃖 𮃗
   *    2E0DB..2E0DC   𮃛 𮃜
   *    2E0E2..2E0E3   𮃢 𮃣
   *    2E0EC         𮃬
   *    2E0F0         𮃰
   *    2E0F4         𮃴
   *    2E0FB         𮃻
   *    2E101..2E102   𮄁 𮄂
   *    2E112         𮄒
   *    2E11C         𮄜
   *    2E123         𮄣
   *    2E127         𮄧
   *    2E12D..2E12F   𮄭 𮄮 𮄯
   *    2E131         𮄱
   *    2E134..2E135   𮄴 𮄵
   *    2E13F         𮄿
   *    2E143         𮅃
   *    2E149         𮅉
   *    2E14F         𮅏
   *    2E152         𮅒
   *    2E154..2E155   𮅔 𮅕
   *    2E159         𮅙
   *    2E15E         𮅞
   *    2E161         𮅡
   *    2E16F..2E170   𮅯 𮅰
   *    2E176         𮅶
   *    2E183         𮆃
   *    2E186         𮆆
   *    2E1A8         𮆨
   *    2E1AA         𮆪
   *    2E1AF         𮆯
   *    2E1B4         𮆴
   *    2E1B6         𮆶
   *    2E1BD         𮆽
   *    2E1C0         𮇀
   *    2E1C2         𮇂
   *    2E1CB         𮇋
   *    2E1CE         𮇎
   *    2E1D3         𮇓
   *    2E1DC         𮇜
   *    2E1DE         𮇞
   *    2E1E3         𮇣
   *    2E1E9         𮇩
   *    2E1EC         𮇬
   *    2E1F5         𮇵
   *    2E1FB         𮇻
   *    2E203..2E207   𮈃 𮈄 𮈅 𮈆 𮈇
   *    2E20B..2E20C   𮈋 𮈌
   *    2E20F         𮈏
   *    2E211         𮈑
   *    2E214..2E215   𮈔 𮈕
   *    2E21B..2E21C   𮈛 𮈜
   *    2E223         𮈣
   *    2E226         𮈦
   *    2E230..2E231   𮈰 𮈱
   *    2E235         𮈵
   *    2E237         𮈷
   *    2E248         𮉈
   *    2E257         𮉗
   *    2E25F         𮉟
   *    2E270..2E271   𮉰 𮉱
   *    2E275         𮉵
   *    2E278         𮉸
   *    2E27C         𮉼
   *    2E281..2E282   𮊁 𮊂
   *    2E284..2E286   𮊄 𮊅 𮊆
   *    2E28C         𮊌
   *    2E292..2E294   𮊒 𮊓 𮊔
   *    2E296         𮊖
   *    2E298         𮊘
   *    2E2A2         𮊢
   *    2E2A5         𮊥
   *    2E2AA..2E2AB   𮊪 𮊫
   *    2E2B5..2E2B8   𮊵 𮊶 𮊷 𮊸
   *    2E2BA         𮊺
   *    2E2BE..2E2C1   𮊾 𮊿 𮋀 𮋁
   *    2E2CA         𮋊
   *    2E2D2         𮋒
   *    2E2DA         𮋚
   *    2E2DD         𮋝
   *    2E2E6         𮋦
   *    2E2EE         𮋮
   *    2E2F0         𮋰
   *    2E2F8..2E2F9   𮋸 𮋹
   *    2E2FB         𮋻
   *    2E301..2E302   𮌁 𮌂
   *    2E304         𮌄
   *    2E306..2E307   𮌆 𮌇
   *    2E30B         𮌋
   *    2E30F..2E310   𮌏 𮌐
   *    2E312         𮌒
   *    2E314..2E316   𮌔 𮌕 𮌖
   *    2E31A..2E31C   𮌚 𮌛 𮌜
   *    2E326         𮌦
   *    2E329         𮌩
   *    2E337         𮌷
   *    2E33A         𮌺
   *    2E344         𮍄
   *    2E348..2E349   𮍈 𮍉
   *    2E34C..2E34E   𮍌 𮍍 𮍎
   *    2E350         𮍐
   *    2E352         𮍒
   *    2E35B         𮍛
   *    2E360         𮍠
   *    2E363         𮍣
   *    2E365         𮍥
   *    2E367..2E368   𮍧 𮍨
   *    2E36B..2E36D   𮍫 𮍬 𮍭
   *    2E370         𮍰
   *    2E372         𮍲
   *    2E379         𮍹
   *    2E381         𮎁
   *    2E385..2E386   𮎅 𮎆
   *    2E38B         𮎋
   *    2E38D         𮎍
   *    2E393         𮎓
   *    2E397         𮎗
   *    2E39B..2E39C   𮎛 𮎜
   *    2E3A2..2E3A3   𮎢 𮎣
   *    2E3A6..2E3A7   𮎦 𮎧
   *    2E3AA         𮎪
   *    2E3AF..2E3B0   𮎯 𮎰
   *    2E3B5         𮎵
   *    2E3B7..2E3BB   𮎷 𮎸 𮎹 𮎺 𮎻
   *    2E3BE..2E3C1   𮎾 𮎿 𮏀 𮏁
   *    2E3C4         𮏄
   *    2E3C7         𮏇
   *    2E3CB..2E3CD   𮏋 𮏌 𮏍
   *    2E3D0..2E3D4   𮏐 𮏑 𮏒 𮏓 𮏔
   *    2E3D6         𮏖
   *    2E3DE         𮏞
   *    2E3E3         𮏣
   *    2E3E5..2E3E7   𮏥 𮏦 𮏧
   *    2E3E9         𮏩
   *    2E3F3         𮏳
   *    2E3FB..2E3FC   𮏻 𮏼
   *    2E3FF         𮏿
   *    2E403         𮐃
   *    2E407         𮐇
   *    2E413         𮐓
   *    2E425..2E426   𮐥 𮐦
   *    2E429         𮐩
   *    2E42E         𮐮
   *    2E439         𮐹
   *    2E43B..2E43C   𮐻 𮐼
   *    2E44A         𮑊
   *    2E45A..2E45D   𮑚 𮑛 𮑜 𮑝
   *    2E460         𮑠
   *    2E476         𮑶
   *    2E47C         𮑼
   *    2E48B         𮒋
   *    2E490         𮒐
   *    2E494         𮒔
   *    2E497..2E49C   𮒗 𮒘 𮒙 𮒚 𮒛 𮒜
   *    2E4A8         𮒨
   *    2E4AB         𮒫
   *    2E4B9         𮒹
   *    2E4BF..2E4C0   𮒿 𮓀
   *    2E4C8         𮓈
   *    2E4CB         𮓋
   *    2E4D6..2E4DC   𮓖 𮓗 𮓘 𮓙 𮓚 𮓛 𮓜
   *    2E4DE         𮓞
   *    2E4E0..2E4E3   𮓠 𮓡 𮓢 𮓣
   *    2E4E5..2E4E6   𮓥 𮓦
   *    2E4EF..2E4F1   𮓯 𮓰 𮓱
   *    2E4F5         𮓵
   *    2E4FA         𮓺
   *    2E4FC..2E4FD   𮓼 𮓽
   *    2E501         𮔁
   *    2E507..2E509   𮔇 𮔈 𮔉
   *    2E514         𮔔
   *    2E517         𮔗
   *    2E51D         𮔝
   *    2E530         𮔰
   *    2E542..2E543   𮕂 𮕃
   *    2E548         𮕈
   *    2E54B         𮕋
   *    2E552         𮕒
   *    2E554         𮕔
   *    2E55B..2E55D   𮕛 𮕜 𮕝
   *    2E561         𮕡
   *    2E564..2E565   𮕤 𮕥
   *    2E569..2E56B   𮕩 𮕪 𮕫
   *    2E571         𮕱
   *    2E57A         𮕺
   *    2E587         𮖇
   *    2E59A..2E59B   𮖚 𮖛
   *    2E5A0         𮖠
   *    2E5A5         𮖥
   *    2E5AF         𮖯
   *    2E5BB         𮖻
   *    2E5BE..2E5C3   𮖾 𮖿 𮗀 𮗁 𮗂 𮗃
   *    2E5C8         𮗈
   *    2E5D3         𮗓
   *    2E5D6         𮗖
   *    2E5D8         𮗘
   *    2E5DA         𮗚
   *    2E5DC         𮗜
   *    2E5DF..2E5E0   𮗟 𮗠
   *    2E5E8         𮗨
   *    2E5EB..2E5EE   𮗫 𮗬 𮗭 𮗮
   *    2E5F2         𮗲
   *    2E5F7         𮗷
   *    2E5FD         𮗽
   *    2E601..2E602   𮘁 𮘂
   *    2E608         𮘈
   *    2E60A         𮘊
   *    2E60E         𮘎
   *    2E610         𮘐
   *    2E619         𮘙
   *    2E621         𮘡
   *    2E624..2E626   𮘤 𮘥 𮘦
   *    2E62A         𮘪
   *    2E62D         𮘭
   *    2E638..2E639   𮘸 𮘹
   *    2E63D..2E63E   𮘽 𮘾
   *    2E644..2E646   𮙄 𮙅 𮙆
   *    2E657..2E658   𮙗 𮙘
   *    2E65A..2E660   𮙚 𮙛 𮙜 𮙝 𮙞 𮙟 𮙠
   *    2E663         𮙣
   *    2E666         𮙦
   *    2E66A         𮙪
   *    2E66D..2E66E   𮙭 𮙮
   *    2E672         𮙲
   *    2E67E..2E67F   𮙾 𮙿
   *    2E681         𮚁
   *    2E683..2E687   𮚃 𮚄 𮚅 𮚆 𮚇
   *    2E68B..2E68D   𮚋 𮚌 𮚍
   *    2E691         𮚑
   *    2E695..2E696   𮚕 𮚖
   *    2E69A         𮚚
   *    2E6A6         𮚦
   *    2E6B0         𮚰
   *    2E6B4         𮚴
   *    2E6B6         𮚶
   *    2E6B8         𮚸
   *    2E6D9         𮛙
   *    2E6E1         𮛡
   *    2E6E9..2E6EA   𮛩 𮛪
   *    2E700         𮜀
   *    2E709         𮜉
   *    2E712         𮜒
   *    2E715         𮜕
   *    2E72C         𮜬
   *    2E736         𮜶
   *    2E73F..2E740   𮜿 𮝀
   *    2E747         𮝇
   *    2E75D..2E75E   𮝝 𮝞
   *    2E769         𮝩
   *    2E76C         𮝬
   *    2E77B..2E77C   𮝻 𮝼
   *    2E77E         𮝾
   *    2E782         𮞂
   *    2E785..2E786   𮞅 𮞆
   *    2E789         𮞉
   *    2E798..2E79A   𮞘 𮞙 𮞚
   *    2E79F         𮞟
   *    2E7AD         𮞭
   *    2E7B0         𮞰
   *    2E7B7         𮞷
   *    2E7BD..2E7BE   𮞽 𮞾
   *    2E7C0         𮟀
   *    2E7C3         𮟃
   *    2E7C8         𮟈
   *    2E7D0         𮟐
   *    2E7D7..2E7D8   𮟗 𮟘
   *    2E7DA         𮟚
   *    2E7DD..2E7DE   𮟝 𮟞
   *    2E7E3         𮟣
   *    2E7F2         𮟲
   *    2E7FB         𮟻
   *    2E7FE         𮟾
   *    2E811         𮠑
   *    2E815..2E816   𮠕 𮠖
   *    2E818         𮠘
   *    2E81A         𮠚
   *    2E82D         𮠭
   *    2E833         𮠳
   *    2E836         𮠶
   *    2E838         𮠸
   *    2E844         𮡄
   *    2E849         𮡉
   *    2E84C         𮡌
   *    2E84F         𮡏
   *    2E857         𮡗
   *    2E864         𮡤
   *    2E866..2E868   𮡦 𮡧 𮡨
   *    2E86A..2E86B   𮡪 𮡫
   *    2E86E..2E871   𮡮 𮡯 𮡰 𮡱
   *    2E874         𮡴
   *    2E878..2E879   𮡸 𮡹
   *    2E87C         𮡼
   *    2E87F..2E880   𮡿 𮢀
   *    2E883         𮢃
   *    2E889..2E88A   𮢉 𮢊
   *    2E88C..2E88D   𮢌 𮢍
   *    2E895..2E896   𮢕 𮢖
   *    2E899..2E89C   𮢙 𮢚 𮢛 𮢜
   *    2E89F         𮢟
   *    2E8A1..2E8A2   𮢡 𮢢
   *    2E8A4         𮢤
   *    2E8A8         𮢨
   *    2E8AC         𮢬
   *    2E8B4         𮢴
   *    2E8BB..2E8BC   𮢻 𮢼
   *    2E8BE..2E8BF   𮢾 𮢿
   *    2E8C1         𮣁
   *    2E8CD         𮣍
   *    2E8CF..2E8D1   𮣏 𮣐 𮣑
   *    2E8D5         𮣕
   *    2E8D7         𮣗
   *    2E8D9         𮣙
   *    2E8DE..2E8DF   𮣞 𮣟
   *    2E8F9         𮣹
   *    2E907         𮤇
   *    2E91A         𮤚
   *    2E91C         𮤜
   *    2E927..2E929   𮤧 𮤨 𮤩
   *    2E93E         𮤾
   *    2E943         𮥃
   *    2E94C         𮥌
   *    2E94F         𮥏
   *    2E953         𮥓
   *    2E959..2E95A   𮥙 𮥚
   *    2E974..2E977   𮥴 𮥵 𮥶 𮥷
   *    2E979..2E97A   𮥹 𮥺
   *    2E97E..2E980   𮥾 𮥿 𮦀
   *    2E982         𮦂
   *    2E984         𮦄
   *    2E986         𮦆
   *    2E99C         𮦜
   *    2E99F         𮦟
   *    2E9A2         𮦢
   *    2E9B7         𮦷
   *    2E9C0         𮧀
   *    2E9C7         𮧇
   *    2E9CA         𮧊
   *    2E9CC         𮧌
   *    2E9D2..2E9D3   𮧒 𮧓
   *    2E9D7         𮧗
   *    2E9DE..2E9DF   𮧞 𮧟
   *    2E9EE..2E9F0   𮧮 𮧯 𮧰
   *    2E9F2         𮧲
   *    2E9FB         𮧻
   *    2EA00         𮨀
   *    2EA07..2EA08   𮨇 𮨈
   *    2EA0D         𮨍
   *    2EA26         𮨦
   *    2EA37         𮨷
   *    2EA3D..2EA3E   𮨽 𮨾
   *    2EA41         𮩁
   *    2EA51         𮩑
   *    2EA66         𮩦
   *    2EA73         𮩳
   *    2EA7A         𮩺
   *    2EA7C         𮩼
   *    2EA87..2EA88   𮪇 𮪈
   *    2EA8D         𮪍
   *    2EA9C         𮪜
   *    2EAA0         𮪠
   *    2EAA6         𮪦
   *    2EAAC         𮪬
   *    2EABB         𮪻
   *    2EAC4         𮫄
   *    2EAC7         𮫇
   *    2EACB         𮫋
   *    2EACE..2EACF   𮫎 𮫏
   *    2EAD6..2EAD9   𮫖 𮫗 𮫘 𮫙
   *    2EADF         𮫟
   *    2EAEC..2EAF0   𮫬 𮫭 𮫮 𮫯 𮫰
   *    2EAF2..2EAF5   𮫲 𮫳 𮫴 𮫵
   *    2EAF9..2EAFA   𮫹 𮫺
   *    2EAFC..2EAFE   𮫼 𮫽 𮫾
   *    2EB00..2EB02   𮬀 𮬁 𮬂
   *    2EB06..2EB07   𮬆 𮬇
   *    2EB09         𮬉
   *    2EB0E..2EB0F   𮬎 𮬏
   *    2EB13..2EB14   𮬓 𮬔
   *    2EB17..2EB19   𮬗 𮬘 𮬙
   *    2EB2C         𮬬
   *    2EB30..2EB32   𮬰 𮬱 𮬲
   *    2EB39         𮬹
   *    2EB3C         𮬼
   *    2EB4B         𮭋
   *    2EB50         𮭐
   *    2EB58         𮭘
   *    2EB5D         𮭝
   *    2EB5F..2EB60   𮭟 𮭠
   *    2EB6D         𮭭
   *    2EB71         𮭱
   *    2EB74         𮭴
   *    2EB76..2EB77   𮭶 𮭷
   *    2EB79         𮭹
   *    2EB81         𮮁
   *    2EB84         𮮄
   *    2EB86         𮮆
   *    2EB90..2EB91   𮮐 𮮑
   *    2EBA0         𮮠
   *    2EBA6         𮮦
   *    2EBB0..2EBB5   𮮰 𮮱 𮮲 𮮳 𮮴 𮮵
   *    2EBB9..2EBBE   𮮹 𮮺 𮮻 𮮼 𮮽 𮮾
   *    2EBC3..2EBC5   𮯃 𮯄 𮯅
   *    2EBC7..2EBC8   𮯇 𮯈
   *    2EBCB..2EBCC   𮯋 𮯌
   *    2EBCE         𮯎
   *    2EBD7         𮯗
   *    2EBDB..2EBE0   𮯛 𮯜 𮯝 𮯞 𮯟 𮯠
   * </pre>
   *
   * <p>The Japanese characters that are this subset appear in one of the following
   * Japanese national standards or lists. They have been identified
   * by the Ideographic Research Group (IRG) and are designated
   * in the Unicode Group Data via the
   * <a href='http://www.unicode.org/reports/tr38/tr38-30.html#kIRG_JSource'><tt>kIRG_JSource</tt> property</a>.</p>
   * 
   * <ul>
   *   <li>J0 JIS X 0208-1990</li>
   *   <li>J1 JIS X 0212-1990</li>
   *   <li>J4 JIS X 0213:2004 level-4</li>
   *   <li>J3 JIS X 0213:2004 level-3</li>
   *   <li>J3A JIS X 0213:2004 level-3 addendum from JIS X 0213:2000 level-3</li>
   *   <li>J13 JIS X 0213:2004 level-3 characters replacing J1 characters</li>
   *   <li>J13A JIS X 0213:2004 level-3 character addendum from JIS X 0213:2000 level-3 replacing J1 characters</li>
   *   <li>J14 JIS X 0213:2004 level-4 characters replacing J1 characters</li>
   *   <li>JA Unified Japanese IT Vendors Contemporary Ideographs, 1993</li>
   *   <li>JA3 JIS X 0213:2004 level-3 characters replacing JA characters</li>
   *   <li>JA4 JIS X 0213:2004 level-4 characters replacing JA characters</li>
   *   <li>JARIB Association of Radio Industries and Businesses (ARIB) ARIB STD-B24 Version 5.1, March 14 2007</li>
   *   <li>JH Hanyo-Denshi Program (汎用電子情報交換環境整備プログラム), 2002-2009</li>
   *   <li>JK Japanese KOKUJI Collection</li>
   *   <li>JMJ Moji Joho Kiban Project (文字情報基盤整備事業)</li>
   * </ul>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
   *      – Wikipedia</a> provided information about the Kanji script and its relationship to
   *      the Chinese family of scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Han_unification" target="_blank">Han
   *      Unification – Wikipedia</a> provided information about the unified Unicode
   *      Hanzi, Kanji, Hanja scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Chinese_family_of_scripts" target="_blank">Chinese
   *      family of scripts – Wikipedia</a> provided information about the Chinese family of scripts.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/List_of_CJK_fonts" target="_blank">List of CJK
   *      fonts – Wikipedia</a> provided information about some of the notable set of fonts for
   *      rendering CJK fonts.
   * 
   * @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
   *      FAQs – Unicode Org</a> provided information about
   *      the Unicode Unified CJK encodings.
   * 
   * @see <a href="http://www.unicode.org/reports/tr38/tr38-30.html" target="_blank">Unicode
   *   Han Database (UNIHAN), Proposed Update Unicode® Standard Annex #38</a>
   *      for information on Ideographic Research Group (IRG), and in particular the
   *      <tt>kIRG_JSource</tt> property.
   * 
   * @see <a href="doc-files/JAPANESE_ja_Hani.html" target="_blank">JAPANESE_ja_Hani</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani_jsource = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("JSOURCE").setScript("Hani").build(),
      Letters_Japanese_ja_Hani_jsource.SUBSET);

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C0..00C2   À Á Â
   *    00C5..00C6   Å Æ
   *    00C8..00CA   È É Ê
   *    00CC..00CE   Ì Í Î
   *    00D2..00D4   Ò Ó Ô
   *    00D8..00DB   Ø Ù Ú Û
   *    00E0..00E2   à á â
   *    00E5..00E6   å æ
   *    00E8..00EA   è é ê
   *    00EC..00EE   ì í î
   *    00F2..00F4   ò ó ô
   *    00F8..00FB   ø ù ú û
   *    0100..0101   Ā ā
   *    0112..0113   Ē ē
   *    012A..012B   Ī ī
   *    014C..014D   Ō ō
   *    016A..016B   Ū ū
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Norwegian_orthography" target="_blank">Norwegian
   *      orthography – Wikipedia</a> provided information about the Norwegian alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Bokmål" target="_blank">Bokmål
   *      – Wikipedia</a> provided information about the Norwegian Bokmål alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset NORWEGIAN_BOKMÅL_nb = registerSubset(
      new Locale("nb", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc0_c2, //  À Á Â
          0xc5_c6, //  Å Æ
          0xc8_ca, //  È É Ê
          0xcc_ce, //  Ì Í Î
          0xd2_d4, //  Ò Ó Ô
          0xd8_db, //  Ø Ù Ú Û
          0xe0_e2, //  à á â
          0xe5_e6, //  å æ
          0xe8_ea, //  è é ê
          0xec_ee, //  ì í î
          0xf2_f4, //  ò ó ô
          0xf8_fb, //  ø ù ú û
      },
        new int[]{
          0x0100_0101, //  Ā ā
          0x0112_0113, //  Ē ē
          0x012a_012b, //  Ī ī
          0x014c_014d, //  Ō ō
          0x016a_016b, //  Ū ū
      },
      19, 98));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C0..00C2   À Á Â
   *    00C5..00C6   Å Æ
   *    00C8..00CA   È É Ê
   *    00CC..00CE   Ì Í Î
   *    00D2..00D4   Ò Ó Ô
   *    00D8..00DB   Ø Ù Ú Û
   *    00E0..00E2   à á â
   *    00E5..00E6   å æ
   *    00E8..00EA   è é ê
   *    00EC..00EE   ì í î
   *    00F2..00F4   ò ó ô
   *    00F8..00FB   ø ù ú û
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Norwegian_orthography" target="_blank">Norwegian
   *      orthography – Wikipedia</a> provided information about the Norwegian alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Nynorsk" target="_blank">Nynorsk
   *      – Wikipedia</a> provided information about the Norwegian Nynorsk alphabet and what
   *      diacritics are supported.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset NORWEGIAN_NYNORSK_nn = registerSubset(
      new Locale("nn", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc0_c2, //  À Á Â
          0xc5_c6, //  Å Æ
          0xc8_ca, //  È É Ê
          0xcc_ce, //  Ì Í Î
          0xd2_d4, //  Ò Ó Ô
          0xd8_db, //  Ø Ù Ú Û
          0xe0_e2, //  à á â
          0xe5_e6, //  å æ
          0xe8_ea, //  è é ê
          0xec_ee, //  ì í î
          0xf2_f4, //  ò ó ô
          0xf8_fb, //  ø ù ú û
      },
      14, 88));
  public static final Subset DUTCH_nl = registerSubset(
      new Locale("nl", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc1_c1, //  Á
          0xc4_c4, //  Ä
          0xc9_c9, //  É
          0xcb_cb, //  Ë
          0xcd_cd, //  Í
          0xcf_cf, //  Ï
          0xd3_d3, //  Ó
          0xd6_d6, //  Ö
          0xda_da, //  Ú
          0xdc_dc, //  Ü
          0xe1_e1, //  á
          0xe4_e4, //  ä
          0xe9_e9, //  é
          0xeb_eb, //  ë
          0xed_ed, //  í
          0xef_ef, //  ï
          0xf3_f3, //  ó
          0xf6_f6, //  ö
          0xfa_fa, //  ú
          0xfc_fc, //  ü
      },
      22, 72));
  public static final Subset DUTCH_nl_BE = registerSubset(
      new Locale("nl", "BE", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      },
      2, 52));
  public static final Subset PORTUGUESE_pt = registerSubset(
      new Locale("pt", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xe0_e3, //  à á â ã
          0xe7_e7, //  ç
          0xe9_ea, //  é ê
          0xed_ed, //  í
          0xf3_f5, //  ó ô õ
          0xfa_fa, //  ú
      },
      8, 64));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   *    00C4..00C5   Ä Å
   *    00D6         Ö
   *    00E4..00E5   ä å
   *    00F6         ö
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Swedish_orthography" target="_blank">Swedish
   *      Orthography – Wikipedia</a> provided information about the Swedish alphabet and what
   *      diacritics are supported.
   * 
   * @see <a href="https://learningswedish.se/courses/1/pages/the-alphabet" target="_blank">Swedish
   *      Alphabet– Swedish Institute</a> provided by the Svenska institutet (Swedish
   *      Institute) as part of their Learning Swedish course.
   *
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   *      target="_blank">Alphabets, Letters and Diacritics in European Languages –
   *      Vigleik Leira</a> provides a good primer, or starting point, for learning
   *      about the European language alphabets that use the the Latin script.
   */
  public static final Subset SWEDISH_sv = registerSubset(
      new Locale("sv", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c5, //  Ä Å
          0xd6_d6, //  Ö
          0xe4_e5, //  ä å
          0xf6_f6, //  ö
      },
      6, 58));

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   *    software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   * 
   * <p>This language code-point subset should capture the official
   *    standard alphabet or ideographs for words or names as would be accepted by
   *    institutions and organizations that are required to adhere to statutory
   *    regulations.</p>
   *
   * <pre>
   *    0041..0045   A B C D E
   *    0047..0049   G H I
   *    004B..0056   K L M N O P Q R S T U V
   *    0058..0059   X Y
   *    0061..0065   a b c d e
   *    0067..0069   g h i
   *    006B..0076   k l m n o p q r s t u v
   *    0078..0079   x y
   *    00C0..00C3   À Á Â Ã
   *    00C8..00CA   È É Ê
   *    00CC..00CD   Ì Í
   *    00D2..00D5   Ò Ó Ô Õ
   *    00D9..00DA   Ù Ú
   *    00DD         Ý
   *    00E0..00E3   à á â ã
   *    00E8..00EA   è é ê
   *    00EC..00ED   ì í
   *    00F2..00F5   ò ó ô õ
   *    00F9..00FA   ù ú
   *    00FD         ý
   *    0102..0103   Ă ă
   *    0110..0111   Đ đ
   *    0128..0129   Ĩ ĩ
   *    0168..0169   Ũ ũ
   *    01A0..01A1   Ơ ơ
   *    01AF..01B0   Ư ư
   *    1EA0..1EF9   Ạ ạ Ả ả Ấ ấ Ầ ầ Ẩ ẩ Ẫ ẫ Ậ ậ Ắ ắ Ằ ằ Ẳ ẳ Ẵ ẵ Ặ ặ Ẹ ẹ Ẻ ẻ Ẽ ẽ
   *                 Ế ế Ề ề Ể ể Ễ ễ Ệ ệ Ỉ ỉ Ị ị Ọ ọ Ỏ ỏ Ố ố Ồ ồ Ổ ổ Ỗ ỗ Ộ ộ Ớ ớ
   *                 Ờ ờ Ở ở Ỡ ỡ Ợ ợ Ụ ụ Ủ ủ Ứ ứ Ừ ừ Ử ử Ữ ữ Ự ự Ỳ ỳ Ỵ ỵ Ỷ ỷ Ỹ ỹ
   *    20AB         ₫
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   * 
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   *    not contained in this alphabet. Even if they appear occasionally in loan-words
   *    or foreign names.</p>
   * 
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   *    to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   * 
   * <p>If you believe there are errors in this alphabet please reach out
   *    to us and provide sources / references that support what you think should
   *    be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Vietnamese_alphabet" target="_blank">Vietnamese alphabet
   *      – Wikipedia</a> provided information about the Vietnamese alphabet and what
   *      diacritics are supported.
   */
  public static final Subset VIETNAMESE_vi = registerSubset(
      new Locale("vi", "", ""),
      new RangedSubsetImpl(
        new char[]{
          0x41_45, //  A B C D E
          0x47_49, //  G H I
          0x4b_56, //  K L M N O P Q R S T U V
          0x58_59, //  X Y
          0x61_65, //  a b c d e
          0x67_69, //  g h i
          0x6b_76, //  k l m n o p q r s t u v
          0x78_79, //  x y
          0xc0_c3, //  À Á Â Ã
          0xc8_ca, //  È É Ê
          0xcc_cd, //  Ì Í
          0xd2_d5, //  Ò Ó Ô Õ
          0xd9_da, //  Ù Ú
          0xdd_dd, //  Ý
          0xe0_e3, //  à á â ã
          0xe8_ea, //  è é ê
          0xec_ed, //  ì í
          0xf2_f5, //  ò ó ô õ
          0xf9_fa, //  ù ú
          0xfd_fd, //  ý
      },
        new int[]{
          0x0102_0103, //  Ă ă
          0x0110_0111, //  Đ đ
          0x0128_0129, //  Ĩ ĩ
          0x0168_0169, //  Ũ ũ
          0x01a0_01a1, //  Ơ ơ
          0x01af_01b0, //  Ư ư
          0x1ea0_1ef9, //  Ạ ạ Ả ả Ấ ấ Ầ ầ Ẩ ẩ Ẫ ẫ Ậ ậ Ắ ắ Ằ ằ Ẳ ẳ Ẵ ẵ Ặ ặ Ẹ ẹ Ẻ ẻ Ẽ ẽ
                       //  Ế ế Ề ề Ể ể Ễ ễ Ệ ệ Ỉ ỉ Ị ị Ọ ọ Ỏ ỏ Ố ố Ồ ồ Ổ ổ Ỗ ỗ Ộ ộ Ớ ớ
                       //  Ờ ờ Ở ở Ỡ ỡ Ợ ợ Ụ ụ Ủ ủ Ứ ứ Ừ ừ Ử ử Ữ ữ Ự ự Ỳ ỳ Ỵ ỵ Ỷ ỷ Ỹ ỹ
          0x20ab_20ab, //  ₫
      },
      28, 179));
}
