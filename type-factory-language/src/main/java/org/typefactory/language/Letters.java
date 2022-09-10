package org.typefactory.language;

import java.util.HashMap;
import java.util.Locale;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the type-factory-language-code-generator module.",
    value = "org.typefactory:type-factory-language-code-generator")
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
        new int[]{
          0x0531_0556, //  Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ
                       //  Տ Ր Ց Ւ Փ Ք Օ Ֆ
          0x0561_0586, //  ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ
                       //  տ ր ց ւ փ ք օ ֆ
      },
      2, 76));
  public static final Subset ICELANDIC_is = registerSubset(
      new Locale("is", "", ""),
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
   * <p>There are too many unicode code-points (characters) in this set to display here. See separate 
   *    <a href='./doc-files/JAPANESE_ja_Hani.txt'>JAPANESE_ja_Hani documentation file</a>
   *    for a complete list of the unicode code-points in this set.</p>
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
      Factory.rangedSubset(
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
   * <p>There are too many unicode code-points (characters) in this set to display here. See separate 
   *    <a href='./doc-files/JAPANESE_ja_Hani_x_jinmeiyo.txt'>JAPANESE_ja_Hani_x_jinmeiyo documentation file</a>
   *    for a complete list of the unicode code-points in this set.</p>
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
   * @see <a href='./doc-files/JAPANESE_ja_Hani_jinmeiyo.txt'>JAPANESE_ja_Hani_jinmeiyo</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani_x_jinmeiyo = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hani").build(),
      Letters_Japanese_ja_Hani_x_jinmeiyo.SUBSET);

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
   *    4E00..4E01   一 丁
   *    4E03         七
   *    4E07..4E0B   万 丈 三 上 下
   *    4E0D..4E0E   不 与
   *    4E14         且
   *    4E16         世
   *    4E18..4E19   丘 丙
   *    4E21         両
   *    4E26         並
   *    4E2D         中
   *    4E32         串
   *    4E38..4E39   丸 丹
   *    4E3B..4E3C   主 丼
   *    4E45         久
   *    4E4F         乏
   *    4E57         乗
   *    4E59         乙
   *    4E5D..4E5E   九 乞
   *    4E71         乱
   *    4E73         乳
   *    4E7E         乾
   *    4E80         亀
   *    4E86         了
   *    4E88..4E89   予 争
   *    4E8B..4E8C   事 二
   *    4E92         互
   *    4E94..4E95   五 井
   *    4E9C         亜
   *    4EA1         亡
   *    4EA4         交
   *    4EAB..4EAD   享 京 亭
   *    4EBA         人
   *    4EC1         仁
   *    4ECA..4ECB   今 介
   *    4ECF         仏
   *    4ED5..4ED6   仕 他
   *    4ED8..4ED9   付 仙
   *    4EE3..4EE5   代 令 以
   *    4EEE         仮
   *    4EF0         仰
   *    4EF2         仲
   *    4EF6         件
   *    4EFB         任
   *    4F01         企
   *    4F0E..4F11   伎 伏 伐 休
   *    4F1A         会
   *    4F1D         伝
   *    4F2F         伯
   *    4F34         伴
   *    4F38         伸
   *    4F3A         伺
   *    4F3C         似
   *    4F46         但
   *    4F4D..4F50   位 低 住 佐
   *    4F53         体
   *    4F55         何
   *    4F59         余
   *    4F5C         作
   *    4F73         佳
   *    4F75         併
   *    4F7F         使
   *    4F8B         例
   *    4F8D         侍
   *    4F9B         供
   *    4F9D         依
   *    4FA1         価
   *    4FAE..4FAF   侮 侯
   *    4FB5..4FB6   侵 侶
   *    4FBF         便
   *    4FC2..4FC3   係 促
   *    4FCA         俊
   *    4FD7         俗
   *    4FDD         保
   *    4FE1         信
   *    4FEE         修
   *    4FF3         俳
   *    4FF5         俵
   *    4FF8         俸
   *    4FFA         俺
   *    5009         倉
   *    500B         個
   *    500D         倍
   *    5012         倒
   *    5019         候
   *    501F         借
   *    5023..5024   倣 値
   *    502B         倫
   *    5039         倹
   *    5049         偉
   *    504F         偏
   *    505C         停
   *    5065         健
   *    5074..5076   側 偵 偶
   *    507D         偽
   *    508D         傍
   *    5091         傑
   *    5098..5099   傘 備
   *    50AC         催
   *    50B2         傲
   *    50B5         債
   *    50B7         傷
   *    50BE         傾
   *    50C5         僅
   *    50CD         働
   *    50CF         像
   *    50D5         僕
   *    50DA         僚
   *    50E7         僧
   *    5100         儀
   *    5104         億
   *    5112         儒
   *    511F         償
   *    512A         優
   *    5143..5146   元 兄 充 兆
   *    5148..5149   先 光
   *    514B         克
   *    514D         免
   *    5150         児
   *    515A         党
   *    5165         入
   *    5168         全
   *    516B..516D   八 公 六
   *    5171         共
   *    5175         兵
   *    5177..5178   具 典
   *    517C         兼
   *    5185..5186   内 円
   *    518A         冊
   *    518D         再
   *    5192         冒
   *    5197         冗
   *    5199         写
   *    51A0         冠
   *    51A5         冥
   *    51AC         冬
   *    51B6..51B7   冶 冷
   *    51C4         凄
   *    51C6         准
   *    51CD         凍
   *    51DD         凝
   *    51E1         凡
   *    51E6         処
   *    51F6         凶
   *    51F8..51FA   凸 凹 出
   *    5200         刀
   *    5203         刃
   *    5206..5208   分 切 刈
   *    520A         刊
   *    5211         刑
   *    5217         列
   *    521D         初
   *    5224..5225   判 別
   *    5229         利
   *    5230         到
   *    5236..523B   制 刷 券 刹 刺 刻
   *    5247         則
   *    524A         削
   *    524D         前
   *    5256         剖
   *    525B         剛
   *    525D         剝
   *    5263..5264   剣 剤
   *    526F..5270   副 剰
   *    5272         割
   *    5275         創
   *    5287         劇
   *    529B         力
   *    529F..52A0   功 加
   *    52A3         劣
   *    52A9..52AA   助 努
   *    52B1         励
   *    52B4         労
   *    52B9         効
   *    52BE         劾
   *    52C3         勃
   *    52C5         勅
   *    52C7         勇
   *    52C9         勉
   *    52D5         動
   *    52D8..52D9   勘 務
   *    52DD         勝
   *    52DF         募
   *    52E2         勢
   *    52E4         勤
   *    52E7         勧
   *    52F2         勲
   *    52FE         勾
   *    5302         匂
   *    5305         包
   *    5316..5317   化 北
   *    5320         匠
   *    5339..533B   匹 区 医
   *    533F         匿
   *    5341         十
   *    5343         千
   *    5347..5348   升 午
   *    534A         半
   *    5351..5354   卑 卒 卓 協
   *    5357..5358   南 単
   *    535A         博
   *    5360         占
   *    5370..5371   印 危
   *    5373..5375   即 却 卵
   *    5378         卸
   *    5384         厄
   *    5398         厘
   *    539A         厚
   *    539F         原
   *    53B3         厳
   *    53BB         去
   *    53C2         参
   *    53C8         又
   *    53CA..53CE   及 友 双 反 収
   *    53D4         叔
   *    53D6..53D7   取 受
   *    53D9         叙
   *    53E3..53E5   口 古 句
   *    53EB..53EC   叫 召
   *    53EF..53F0   可 台
   *    53F2..53F3   史 右
   *    53F7..53F8   号 司
   *    5404         各
   *    5408..5409   合 吉
   *    540C..5411   同 名 后 吏 吐 向
   *    541B         君
   *    541F         吟
   *    5426         否
   *    542B         含
   *    5438..5439   吸 吹
   *    5442         呂
   *    5448..544A   呈 呉 告
   *    5468         周
   *    546A         呪
   *    5473         味
   *    547C..547D   呼 命
   *    548C         和
   *    54B2         咲
   *    54BD         咽
   *    54C0..54C1   哀 品
   *    54E1         員
   *    54F2         哲
   *    54FA         哺
   *    5504         唄
   *    5506..5507   唆 唇
   *    5510         唐
   *    552F         唯
   *    5531         唱
   *    553E         唾
   *    5546         商
   *    554F         問
   *    5553         啓
   *    5584         善
   *    5589         喉
   *    559A         喚
   *    559C..559D   喜 喝
   *    55A9..55AB   喩 喪 喫
   *    55B6         営
   *    55C5         嗅
   *    55E3         嗣
   *    5606         嘆
   *    5631..5632   嘱 嘲
   *    5668         器
   *    5674         噴
   *    5687         嚇
   *    56DA..56DB   囚 四
   *    56DE         回
   *    56E0         因
   *    56E3         団
   *    56F0         困
   *    56F2..56F3   囲 図
   *    56FA         固
   *    56FD         国
   *    570F         圏
   *    5712         園
   *    571F         土
   *    5727..5728   圧 在
   *    5730         地
   *    5742         坂
   *    5747         均
   *    574A         坊
   *    5751         坑
   *    576A         坪
   *    5782         垂
   *    578B         型
   *    57A3         垣
   *    57CB         埋
   *    57CE         城
   *    57DF         域
   *    57F7         執
   *    57F9..57FA   培 基
   *    57FC         埼
   *    5800         堀
   *    5802         堂
   *    5805..5806   堅 堆
   *    5815         堕
   *    5824         堤
   *    582A         堪
   *    5831         報
   *    5834         場
   *    5840..5841   塀 塁
   *    584A         塊
   *    5851         塑
   *    5854         塔
   *    5857         塗
   *    585A         塚
   *    585E         塞
   *    5861         塡
   *    5869         塩
   *    587E         塾
   *    5883         境
   *    5893         墓
   *    5897         増
   *    589C         墜
   *    58A8         墨
   *    58B3         墳
   *    58BE         墾
   *    58C1         壁
   *    58C7         壇
   *    58CA         壊
   *    58CC         壌
   *    58EB         士
   *    58EE         壮
   *    58F0..58F2   声 壱 売
   *    5909         変
   *    590F         夏
   *    5915..5916   夕 外
   *    591A         多
   *    591C         夜
   *    5922         夢
   *    5927         大
   *    5929..592B   天 太 夫
   *    592E         央
   *    5931         失
   *    5947..5949   奇 奈 奉
   *    594F         奏
   *    5951         契
   *    5954         奔
   *    5965         奥
   *    5968         奨
   *    596A         奪
   *    596E         奮
   *    5973..5974   女 奴
   *    597D         好
   *    5982..5984   如 妃 妄
   *    598A         妊
   *    5996         妖
   *    5999         妙
   *    59A5         妥
   *    59A8         妨
   *    59AC         妬
   *    59B9         妹
   *    59BB         妻
   *    59C9         姉
   *    59CB         始
   *    59D3..59D4   姓 委
   *    59EB         姫
   *    59FB         姻
   *    59FF         姿
   *    5A01         威
   *    5A18         娘
   *    5A20         娠
   *    5A2F         娯
   *    5A46         婆
   *    5A5A         婚
   *    5A66         婦
   *    5A7F         婿
   *    5A92         媒
   *    5A9B         媛
   *    5AC1         嫁
   *    5AC9         嫉
   *    5ACC         嫌
   *    5AE1         嫡
   *    5B22         嬢
   *    5B50         子
   *    5B54         孔
   *    5B57..5B58   字 存
   *    5B5D         孝
   *    5B63..5B64   季 孤
   *    5B66         学
   *    5B6B         孫
   *    5B85         宅
   *    5B87..5B89   宇 守 安
   *    5B8C         完
   *    5B97..5B9D   宗 官 宙 定 宛 宜 宝
   *    5B9F         実
   *    5BA2..5BA4   客 宣 室
   *    5BAE         宮
   *    5BB0         宰
   *    5BB3..5BB6   害 宴 宵 家
   *    5BB9         容
   *    5BBF         宿
   *    5BC2         寂
   *    5BC4         寄
   *    5BC6         密
   *    5BCC         富
   *    5BD2         寒
   *    5BDB         寛
   *    5BDD         寝
   *    5BDF         察
   *    5BE1         寡
   *    5BE7         寧
   *    5BE9         審
   *    5BEE         寮
   *    5BF8         寸
   *    5BFA         寺
   *    5BFE..5BFF   対 寿
   *    5C01..5C02   封 専
   *    5C04         射
   *    5C06         将
   *    5C09..5C0B   尉 尊 尋
   *    5C0E..5C0F   導 小
   *    5C11         少
   *    5C1A         尚
   *    5C31         就
   *    5C3A..5C40   尺 尻 尼 尽 尾 尿 局
   *    5C45         居
   *    5C48         屈
   *    5C4A..5C4B   届 屋
   *    5C55         展
   *    5C5E         属
   *    5C64..5C65   層 履
   *    5C6F         屯
   *    5C71         山
   *    5C90         岐
   *    5CA1         岡
   *    5CA9         岩
   *    5CAC         岬
   *    5CB3         岳
   *    5CB8         岸
   *    5CE0..5CE1   峠 峡
   *    5CF0         峰
   *    5CF6         島
   *    5D07         崇
   *    5D0E         崎
   *    5D16         崖
   *    5D29         崩
   *    5D50         嵐
   *    5DDD..5DDE   川 州
   *    5DE1         巡
   *    5DE3         巣
   *    5DE5..5DE8   工 左 巧 巨
   *    5DEE         差
   *    5DF1         己
   *    5DFB         巻
   *    5DFE         巾
   *    5E02..5E03   市 布
   *    5E06         帆
   *    5E0C         希
   *    5E1D         帝
   *    5E25         帥
   *    5E2B         師
   *    5E2D         席
   *    5E2F..5E30   帯 帰
   *    5E33         帳
   *    5E38         常
   *    5E3D         帽
   *    5E45         幅
   *    5E55         幕
   *    5E63         幣
   *    5E72..5E74   干 平 年
   *    5E78..5E79   幸 幹
   *    5E7B..5E7E   幻 幼 幽 幾
   *    5E81         庁
   *    5E83         広
   *    5E8A         床
   *    5E8F         序
   *    5E95         底
   *    5E97         店
   *    5E9C         府
   *    5EA6..5EA7   度 座
   *    5EAB         庫
   *    5EAD         庭
   *    5EB6..5EB8   庶 康 庸
   *    5EC3         廃
   *    5EC9..5ECA   廉 廊
   *    5EF6..5EF7   延 廷
   *    5EFA         建
   *    5F01         弁
   *    5F04         弄
   *    5F0A         弊
   *    5F0F..5F10   式 弐
   *    5F13..5F15   弓 弔 引
   *    5F1F         弟
   *    5F25..5F27   弥 弦 弧
   *    5F31         弱
   *    5F35         張
   *    5F37         強
   *    5F3E         弾
   *    5F53         当
   *    5F59         彙
   *    5F62         形
   *    5F69         彩
   *    5F6B         彫
   *    5F70..5F71   彰 影
   *    5F79         役
   *    5F7C         彼
   *    5F80..5F81   往 征
   *    5F84..5F85   径 待
   *    5F8B..5F8C   律 後
   *    5F90         徐
   *    5F92..5F93   徒 従
   *    5F97         得
   *    5FA1         御
   *    5FA9..5FAA   復 循
   *    5FAE         微
   *    5FB3..5FB4   徳 徴
   *    5FB9         徹
   *    5FC3         心
   *    5FC5         必
   *    5FCC..5FCD   忌 忍
   *    5FD7..5FD9   志 忘 忙
   *    5FDC         応
   *    5FE0         忠
   *    5FEB         快
   *    5FF5         念
   *    6012         怒
   *    6016         怖
   *    601D         思
   *    6020         怠
   *    6025         急
   *    6027..6028   性 怨
   *    602A         怪
   *    604B         恋
   *    6050         恐
   *    6052         恒
   *    6063         恣
   *    6065         恥
   *    6068..6069   恨 恩
   *    606D         恭
   *    606F         息
   *    6075         恵
   *    6094         悔
   *    609F..60A0   悟 悠
   *    60A3         患
   *    60A6         悦
   *    60A9..60AA   悩 悪
   *    60B2         悲
   *    60BC         悼
   *    60C5         情
   *    60D1         惑
   *    60DC         惜
   *    60E7..60E8   惧 惨
   *    60F0         惰
   *    60F3         想
   *    6101         愁
   *    6109         愉
   *    610F         意
   *    611A..611B   愚 愛
   *    611F         感
   *    6144         慄
   *    6148         慈
   *    614B..614C   態 慌
   *    614E         慎
   *    6155         慕
   *    6162..6163   慢 慣
   *    6168         慨
   *    616E         慮
   *    6170         慰
   *    6176         慶
   *    6182         憂
   *    618E         憎
   *    61A4         憤
   *    61A7         憧
   *    61A9         憩
   *    61AC         憬
   *    61B2         憲
   *    61B6         憶
   *    61BE         憾
   *    61C7         懇
   *    61D0         懐
   *    61F2         懲
   *    61F8         懸
   *    6210..6212   成 我 戒
   *    621A         戚
   *    6226         戦
   *    622F         戯
   *    6234         戴
   *    6238         戸
   *    623B         戻
   *    623F..6240   房 所
   *    6247         扇
   *    6249         扉
   *    624B         手
   *    624D         才
   *    6253         打
   *    6255         払
   *    6271         扱
   *    6276         扶
   *    6279         批
   *    627F..6280   承 技
   *    6284         抄
   *    628A         把
   *    6291         抑
   *    6295         投
   *    6297..6298   抗 折
   *    629C         抜
   *    629E         択
   *    62AB         披
   *    62B1         抱
   *    62B5         抵
   *    62B9         抹
   *    62BC..62BD   押 抽
   *    62C5         担
   *    62C9         拉
   *    62CD         拍
   *    62D0         拐
   *    62D2..62D3   拒 拓
   *    62D8..62D9   拘 拙
   *    62DB         招
   *    62DD         拝
   *    62E0..62E1   拠 拡
   *    62EC..62ED   括 拭
   *    62F3         拳
   *    62F6..62F7   拶 拷
   *    62FE         拾
   *    6301         持
   *    6307         指
   *    6311         挑
   *    6319         挙
   *    631F         挟
   *    6328         挨
   *    632B         挫
   *    632F         振
   *    633F         挿
   *    6349         捉
   *    6355         捕
   *    6357         捗
   *    635C         捜
   *    6368         捨
   *    636E         据
   *    637B         捻
   *    6383         掃
   *    6388         授
   *    638C         掌
   *    6392         排
   *    6398         掘
   *    639B         掛
   *    63A1..63A2   採 探
   *    63A5         接
   *    63A7..63A8   控 推
   *    63AA         措
   *    63B2         掲
   *    63CF..63D0   描 提
   *    63DA..63DB   揚 換
   *    63E1         握
   *    63EE         揮
   *    63F4         援
   *    63FA         揺
   *    640D         損
   *    642C..642D   搬 搭
   *    643A         携
   *    643E         搾
   *    6442         摂
   *    6458         摘
   *    6469         摩
   *    646F         摯
   *    6483         撃
   *    64A4         撤
   *    64AE         撮
   *    64B2         撲
   *    64C1         擁
   *    64CD         操
   *    64E6         擦
   *    64EC         擬
   *    652F         支
   *    6539         改
   *    653B         攻
   *    653E..653F   放 政
   *    6545         故
   *    654F         敏
   *    6551         救
   *    6557         敗
   *    6559         教
   *    6562..6563   敢 散
   *    656C         敬
   *    6570         数
   *    6574..6575   整 敵
   *    6577         敷
   *    6587         文
   *    6589         斉
   *    658E         斎
   *    6591         斑
   *    6597         斗
   *    6599         料
   *    659C         斜
   *    65A4..65A5   斤 斥
   *    65AC..65AD   斬 断
   *    65B0         新
   *    65B9         方
   *    65BD         施
   *    65C5         旅
   *    65CB         旋
   *    65CF         族
   *    65D7         旗
   *    65E2         既
   *    65E5..65E9   日 旦 旧 旨 早
   *    65EC         旬
   *    65FA         旺
   *    6606..6607   昆 昇
   *    660E         明
   *    6613..6614   易 昔
   *    661F..6620   星 映
   *    6625         春
   *    6627..6628   昧 昨
   *    662D         昭
   *    662F         是
   *    663C         昼
   *    6642         時
   *    6669         晩
   *    666E..666F   普 景
   *    6674         晴
   *    6676         晶
   *    6681         暁
   *    6687         暇
   *    6691         暑
   *    6696..6697   暖 暗
   *    66A6         暦
   *    66AB         暫
   *    66AE         暮
   *    66B4         暴
   *    66C7         曇
   *    66D6         曖
   *    66DC         曜
   *    66F2         曲
   *    66F4         更
   *    66F8..66F9   書 曹
   *    66FD         曽
   *    66FF..6700   替 最
   *    6708..6709   月 有
   *    670D         服
   *    6715         朕
   *    6717         朗
   *    671B         望
   *    671D         朝
   *    671F         期
   *    6728         木
   *    672A..672D   未 末 本 札
   *    6731         朱
   *    6734         朴
   *    673A         机
   *    673D         朽
   *    6749         杉
   *    6750..6751   材 村
   *    675F         束
   *    6761         条
   *    6765         来
   *    676F         杯
   *    6771         東
   *    677E..677F   松 板
   *    6790         析
   *    6795         枕
   *    6797         林
   *    679A         枚
   *    679C..679D   果 枝
   *    67A0         枠
   *    67A2         枢
   *    67AF         枯
   *    67B6         架
   *    67C4         柄
   *    67D0         某
   *    67D3..67D4   染 柔
   *    67F1         柱
   *    67F3         柳
   *    67F5         柵
   *    67FB         査
   *    67FF         柿
   *    6803..6804   栃 栄
   *    6813         栓
   *    6821         校
   *    682A         株
   *    6838..6839   核 根
   *    683C..683D   格 栽
   *    6841         桁
   *    6843         桃
   *    6848         案
   *    6851         桑
   *    685C         桜
   *    685F         桟
   *    6885         梅
   *    6897         梗
   *    68A8         梨
   *    68B0         械
   *    68C4         棄
   *    68CB         棋
   *    68D2         棒
   *    68DA         棚
   *    68DF         棟
   *    68EE         森
   *    68FA         棺
   *    6905         椅
   *    690D..690E   植 椎
   *    691C         検
   *    696D         業
   *    6975         極
   *    6977         楷
   *    697C..697D   楼 楽
   *    6982         概
   *    69CB         構
   *    69D8         様
   *    69FD         槽
   *    6A19         標
   *    6A21         模
   *    6A29..6A2A   権 横
   *    6A39         樹
   *    6A4B         橋
   *    6A5F         機
   *    6B04         欄
   *    6B20..6B21   欠 次
   *    6B27         欧
   *    6B32         欲
   *    6B3A         欺
   *    6B3E         款
   *    6B4C         歌
   *    6B53         歓
   *    6B62..6B63   止 正
   *    6B66         武
   *    6B69         歩
   *    6B6F         歯
   *    6B73..6B74   歳 歴
   *    6B7B         死
   *    6B89..6B8B   殉 殊 残
   *    6B96         殖
   *    6BB4..6BB5   殴 段
   *    6BBA..6BBB   殺 殻
   *    6BBF..6BC0   殿 毀
   *    6BCD..6BCE   母 毎
   *    6BD2         毒
   *    6BD4         比
   *    6BDB         毛
   *    6C0F         氏
   *    6C11         民
   *    6C17         気
   *    6C34         水
   *    6C37..6C38   氷 永
   *    6C3E         氾
   *    6C41..6C42   汁 求
   *    6C4E         汎
   *    6C57         汗
   *    6C5A         汚
   *    6C5F..6C60   江 池
   *    6C70         汰
   *    6C7A         決
   *    6C7D         汽
   *    6C83         沃
   *    6C88         沈
   *    6C96         沖
   *    6C99         沙
   *    6CA1..6CA2   没 沢
   *    6CB3         河
   *    6CB8..6CB9   沸 油
   *    6CBB..6CBC   治 沼
   *    6CBF         沿
   *    6CC1         況
   *    6CC9..6CCA   泉 泊
   *    6CCC         泌
   *    6CD5         法
   *    6CE1..6CE3   泡 波 泣
   *    6CE5         泥
   *    6CE8         注
   *    6CF0         泰
   *    6CF3         泳
   *    6D0B         洋
   *    6D17         洗
   *    6D1E         洞
   *    6D25         津
   *    6D2A         洪
   *    6D3B         活
   *    6D3E         派
   *    6D41         流
   *    6D44..6D45   浄 浅
   *    6D5C         浜
   *    6D66         浦
   *    6D6A         浪
   *    6D6E         浮
   *    6D74         浴
   *    6D77..6D78   海 浸
   *    6D88         消
   *    6D99         涙
   *    6DAF         涯
   *    6DB2         液
   *    6DBC         涼
   *    6DD1         淑
   *    6DE1         淡
   *    6DEB         淫
   *    6DF1         深
   *    6DF7         混
   *    6DFB         添
   *    6E05         清
   *    6E07..6E09   渇 済 渉
   *    6E0B         渋
   *    6E13         渓
   *    6E1B         減
   *    6E21         渡
   *    6E26         渦
   *    6E29         温
   *    6E2C         測
   *    6E2F         港
   *    6E56         湖
   *    6E67         湧
   *    6E6F         湯
   *    6E7E..6E80   湾 湿 満
   *    6E90         源
   *    6E96         準
   *    6E9D         溝
   *    6EB6         溶
   *    6EBA         溺
   *    6EC5         滅
   *    6ECB         滋
   *    6ED1         滑
   *    6EDD..6EDE   滝 滞
   *    6EF4         滴
   *    6F01..6F02   漁 漂
   *    6F06         漆
   *    6F0F         漏
   *    6F14         演
   *    6F20         漠
   *    6F22         漢
   *    6F2B..6F2C   漫 漬
   *    6F38         漸
   *    6F54         潔
   *    6F5C         潜
   *    6F5F         潟
   *    6F64         潤
   *    6F6E         潮
   *    6F70         潰
   *    6F84         澄
   *    6FC0..6FC1   激 濁
   *    6FC3         濃
   *    6FEB         濫
   *    6FEF         濯
   *    702C         瀬
   *    706B         火
   *    706F..7070   灯 灰
   *    707D         災
   *    7089..708A   炉 炊
   *    708E         炎
   *    70AD         炭
   *    70B9..70BA   点 為
   *    70C8         烈
   *    7121         無
   *    7126         焦
   *    7136         然
   *    713C         焼
   *    714E         煎
   *    7159         煙
   *    7167         照
   *    7169         煩
   *    716E         煮
   *    718A         熊
   *    719F         熟
   *    71B1         熱
   *    71C3         燃
   *    71E5         燥
   *    7206         爆
   *    722A         爪
   *    7235..7236   爵 父
   *    723D         爽
   *    7247..7248   片 版
   *    7259         牙
   *    725B         牛
   *    7267         牧
   *    7269         物
   *    7272         牲
   *    7279         特
   *    72A0         犠
   *    72AC         犬
   *    72AF         犯
   *    72B6         状
   *    72C2         狂
   *    72D9         狙
   *    72E9         狩
   *    72EC..72ED   独 狭
   *    731B         猛
   *    731F         猟
   *    732B         猫
   *    732E         献
   *    7336         猶
   *    733F         猿
   *    7344         獄
   *    7363         獣
   *    7372         獲
   *    7384         玄
   *    7387         率
   *    7389         玉
   *    738B         王
   *    73A9         玩
   *    73CD         珍
   *    73E0         珠
   *    73ED         班
   *    73FE         現
   *    7403         球
   *    7406         理
   *    7434         琴
   *    7460         瑠
   *    7483         璃
   *    74A7         璧
   *    74B0         環
   *    74BD         璽
   *    74E6         瓦
   *    74F6         瓶
   *    7518         甘
   *    751A         甚
   *    751F         生
   *    7523         産
   *    7528         用
   *    7530..7533   田 由 甲 申
   *    7537         男
   *    753A..753B   町 画
   *    754C         界
   *    754F         畏
   *    7551         畑
   *    7554         畔
   *    7559         留
   *    755C..755D   畜 畝
   *    7565         略
   *    756A         番
   *    7570         異
   *    7573         畳
   *    757F         畿
   *    758E         疎
   *    7591         疑
   *    75AB         疫
   *    75B2         疲
   *    75BE         疾
   *    75C5         病
   *    75C7         症
   *    75D5         痕
   *    75D8         痘
   *    75DB         痛
   *    75E2         痢
   *    75E9         痩
   *    75F4         痴
   *    760D         瘍
   *    7642         療
   *    7652         癒
   *    7656         癖
   *    767A..767B   発 登
   *    767D..767E   白 百
   *    7684         的
   *    7686..7687   皆 皇
   *    76AE         皮
   *    76BF         皿
   *    76C6         盆
   *    76CA         益
   *    76D7         盗
   *    76DB         盛
   *    76DF         盟
   *    76E3..76E4   監 盤
   *    76EE         目
   *    76F2         盲
   *    76F4         直
   *    76F8         相
   *    76FE         盾
   *    7701         省
   *    7709         眉
   *    770B..770C   看 県
   *    771F..7720   真 眠
   *    773A         眺
   *    773C         眼
   *    7740         着
   *    7761         睡
   *    7763         督
   *    7766         睦
   *    77AC..77AD   瞬 瞭
   *    77B3         瞳
   *    77DB         矛
   *    77E2         矢
   *    77E5         知
   *    77ED         短
   *    77EF         矯
   *    77F3         石
   *    7802         砂
   *    7814..7815   研 砕
   *    7832         砲
   *    7834         破
   *    785D         硝
   *    786B..786C   硫 硬
   *    7881         碁
   *    7891         碑
   *    78BA         確
   *    78C1         磁
   *    78E8         磨
   *    7901         礁
   *    790E         礎
   *    793A         示
   *    793C         礼
   *    793E         社
   *    7948..7949   祈 祉
   *    7956         祖
   *    795D..795E   祝 神
   *    7965         祥
   *    7968         票
   *    796D         祭
   *    7981         禁
   *    7985         禅
   *    798D         禍
   *    798F         福
   *    79C0..79C1   秀 私
   *    79CB         秋
   *    79D1..79D2   科 秒
   *    79D8         秘
   *    79DF         租
   *    79E9         秩
   *    79F0         称
   *    79FB         移
   *    7A0B         程
   *    7A0E         税
   *    7A1A         稚
   *    7A2E         種
   *    7A32         稲
   *    7A3C..7A3D   稼 稽
   *    7A3F..7A40   稿 穀
   *    7A42         穂
   *    7A4D         積
   *    7A4F         穏
   *    7A6B         穫
   *    7A74         穴
   *    7A76         究
   *    7A7A         空
   *    7A81         突
   *    7A83         窃
   *    7A92..7A93   窒 窓
   *    7A9F         窟
   *    7AAE..7AAF   窮 窯
   *    7ACB         立
   *    7ADC         竜
   *    7AE0         章
   *    7AE5         童
   *    7AEF         端
   *    7AF6         競
   *    7AF9         竹
   *    7B11         笑
   *    7B1B         笛
   *    7B26         符
   *    7B2C         第
   *    7B46         筆
   *    7B49         等
   *    7B4B         筋
   *    7B52         筒
   *    7B54         答
   *    7B56         策
   *    7B87         箇
   *    7B8B         箋
   *    7B97         算
   *    7BA1         管
   *    7BB1         箱
   *    7BB8         箸
   *    7BC0         節
   *    7BC4         範
   *    7BC9         築
   *    7BE4         篤
   *    7C21         簡
   *    7C3F         簿
   *    7C4D         籍
   *    7C60         籠
   *    7C73         米
   *    7C89         粉
   *    7C8B         粋
   *    7C92         粒
   *    7C97..7C98   粗 粘
   *    7C9B         粛
   *    7CA7         粧
   *    7CBE         精
   *    7CD6         糖
   *    7CE7         糧
   *    7CF8         糸
   *    7CFB         系
   *    7CFE         糾
   *    7D00         紀
   *    7D04..7D05   約 紅
   *    7D0B         紋
   *    7D0D         納
   *    7D14         純
   *    7D19..7D1B   紙 級 紛
   *    7D20..7D22   素 紡 索
   *    7D2B         紫
   *    7D2F..7D30   累 細
   *    7D33         紳
   *    7D39..7D3A   紹 紺
   *    7D42         終
   *    7D44         組
   *    7D4C         経
   *    7D50         結
   *    7D5E         絞
   *    7D61         絡
   *    7D66         給
   *    7D71         統
   *    7D75..7D76   絵 絶
   *    7D79         絹
   *    7D99..7D9A   継 続
   *    7DAD         維
   *    7DB1..7DB2   綱 網
   *    7DBB         綻
   *    7DBF         綿
   *    7DCA         緊
   *    7DCF         総
   *    7DD1..7DD2   緑 緒
   *    7DDA         線
   *    7DE0         締
   *    7DE8..7DE9   編 緩
   *    7DEF         緯
   *    7DF4         練
   *    7DFB         緻
   *    7E01         縁
   *    7E04         縄
   *    7E1B         縛
   *    7E26         縦
   *    7E2B         縫
   *    7E2E         縮
   *    7E3E         績
   *    7E41         繁
   *    7E4A         繊
   *    7E54..7E55   織 繕
   *    7E6D         繭
   *    7E70         繰
   *    7F36         缶
   *    7F6A         罪
   *    7F6E         置
   *    7F70         罰
   *    7F72         署
   *    7F75         罵
   *    7F77         罷
   *    7F85         羅
   *    7F8A         羊
   *    7F8E         美
   *    7F9E         羞
   *    7FA4         群
   *    7FA8..7FA9   羨 義
   *    7FBD         羽
   *    7FC1         翁
   *    7FCC         翌
   *    7FD2         習
   *    7FFB..7FFC   翻 翼
   *    8001         老
   *    8003         考
   *    8005         者
   *    8010         耐
   *    8015         耕
   *    8017         耗
   *    8033         耳
   *    8056         聖
   *    805E         聞
   *    8074         聴
   *    8077         職
   *    8089         肉
   *    808C         肌
   *    8096         肖
   *    8098         肘
   *    809D         肝
   *    80A1..80A2   股 肢
   *    80A5         肥
   *    80A9..80AA   肩 肪
   *    80AF         肯
   *    80B2         育
   *    80BA         肺
   *    80C3         胃
   *    80C6         胆
   *    80CC         背
   *    80CE         胎
   *    80DE         胞
   *    80F4         胴
   *    80F8         胸
   *    80FD         能
   *    8102         脂
   *    8105         脅
   *    8107..8108   脇 脈
   *    810A         脊
   *    811A         脚
   *    8131         脱
   *    8133         脳
   *    814E         腎
   *    8150         腐
   *    8155         腕
   *    816B         腫
   *    8170         腰
   *    8178..817A   腸 腹 腺
   *    819A         膚
   *    819C..819D   膜 膝
   *    81A8         膨
   *    81B3         膳
   *    81C6         臆
   *    81D3         臓
   *    81E3         臣
   *    81E8         臨
   *    81EA         自
   *    81ED         臭
   *    81F3..81F4   至 致
   *    81FC         臼
   *    8208         興
   *    820C         舌
   *    820E         舎
   *    8217         舗
   *    821E..821F   舞 舟
   *    822A         航
   *    822C         般
   *    8236..8237   舶 舷
   *    8239         船
   *    8247         艇
   *    8266         艦
   *    826F         良
   *    8272         色
   *    8276         艶
   *    828B         芋
   *    829D         芝
   *    82AF         芯
   *    82B1         花
   *    82B3         芳
   *    82B8         芸
   *    82BD         芽
   *    82D7         苗
   *    82DB         苛
   *    82E5..82E6   若 苦
   *    82F1         英
   *    8302         茂
   *    830E         茎
   *    8328         茨
   *    8336         茶
   *    8349         草
   *    8352         荒
   *    8358         荘
   *    8377         荷
   *    83CA         菊
   *    83CC         菌
   *    83D3         菓
   *    83DC         菜
   *    83EF         華
   *    840E         萎
   *    843D         落
   *    8449         葉
   *    8457         著
   *    845B         葛
   *    846C         葬
   *    84B8         蒸
   *    84C4         蓄
   *    84CB         蓋
   *    8511         蔑
   *    8535         蔵
   *    853D         蔽
   *    8584         薄
   *    85A6         薦
   *    85AA..85AC   薪 薫 薬
   *    85CD         藍
   *    85E4         藤
   *    85E9         藩
   *    85FB         藻
   *    864E         虎
   *    8650         虐
   *    865A         虚
   *    865C         虜
   *    865E         虞
   *    866B         虫
   *    8679         虹
   *    868A         蚊
   *    8695         蚕
   *    86C7         蛇
   *    86CD         蛍
   *    86EE         蛮
   *    8702         蜂
   *    871C         蜜
   *    878D         融
   *    8840         血
   *    8846         衆
   *    884C         行
   *    8853         術
   *    8857         街
   *    885B         衛
   *    885D         衝
   *    8861         衡
   *    8863         衣
   *    8868         表
   *    8870         衰
   *    8877         衷
   *    888B         袋
   *    8896         袖
   *    88AB         被
   *    88C1..88C2   裁 裂
   *    88C5         装
   *    88CF         裏
   *    88D5         裕
   *    88DC         補
   *    88F8         裸
   *    88FD..88FE   製 裾
   *    8907         複
   *    8910         褐
   *    8912         褒
   *    895F         襟
   *    8972         襲
   *    897F         西
   *    8981         要
   *    8986..8987   覆 覇
   *    898B         見
   *    898F         規
   *    8996         視
   *    899A         覚
   *    89A7         覧
   *    89AA         親
   *    89B3         観
   *    89D2         角
   *    89E3         解
   *    89E6         触
   *    8A00         言
   *    8A02..8A03   訂 訃
   *    8A08         計
   *    8A0E         討
   *    8A13         訓
   *    8A17..8A18   託 記
   *    8A1F         訟
   *    8A2A         訪
   *    8A2D         設
   *    8A31         許
   *    8A33..8A34   訳 訴
   *    8A3A         診
   *    8A3C         証
   *    8A50         詐
   *    8A54..8A55   詔 評
   *    8A5E         詞
   *    8A60         詠
   *    8A63         詣
   *    8A66         試
   *    8A69         詩
   *    8A6E         詮
   *    8A70..8A73   詰 話 該 詳
   *    8A87         誇
   *    8A89         誉
   *    8A8C..8A8D   誌 認
   *    8A93         誓
   *    8A95         誕
   *    8A98         誘
   *    8A9E         語
   *    8AA0         誠
   *    8AA4         誤
   *    8AAC..8AAD   説 読
   *    8AB0         誰
   *    8AB2         課
   *    8ABF         調
   *    8AC7         談
   *    8ACB         請
   *    8AD6         論
   *    8AE6..8AE7   諦 諧
   *    8AED..8AEE   諭 諮
   *    8AF8         諸
   *    8AFE         諾
   *    8B00..8B01   謀 謁
   *    8B04         謄
   *    8B0E         謎
   *    8B19         謙
   *    8B1B         講
   *    8B1D         謝
   *    8B21         謡
   *    8B39         謹
   *    8B58         識
   *    8B5C         譜
   *    8B66         警
   *    8B70         議
   *    8B72         譲
   *    8B77         護
   *    8C37         谷
   *    8C46         豆
   *    8C4A         豊
   *    8C5A         豚
   *    8C61         象
   *    8C6A         豪
   *    8C8C         貌
   *    8C9D..8C9E   貝 貞
   *    8CA0..8CA2   負 財 貢
   *    8CA7..8CAC   貧 貨 販 貪 貫 責
   *    8CAF         貯
   *    8CB4         貴
   *    8CB7..8CB8   買 貸
   *    8CBB..8CBC   費 貼
   *    8CBF..8CC0   貿 賀
   *    8CC2..8CC4   賂 賃 賄
   *    8CC7         資
   *    8CCA         賊
   *    8CD3         賓
   *    8CDB..8CDC   賛 賜
   *    8CDE         賞
   *    8CE0         賠
   *    8CE2         賢
   *    8CE6         賦
   *    8CEA         質
   *    8CED         賭
   *    8CFC         購
   *    8D08         贈
   *    8D64         赤
   *    8D66         赦
   *    8D70         走
   *    8D74         赴
   *    8D77         起
   *    8D85         超
   *    8D8A         越
   *    8DA3         趣
   *    8DB3         足
   *    8DDD         距
   *    8DE1         跡
   *    8DEF         路
   *    8DF3         跳
   *    8DF5         践
   *    8E0A         踊
   *    8E0F         踏
   *    8E2A         踪
   *    8E74         蹴
   *    8E8D         躍
   *    8EAB         身
   *    8ECA         車
   *    8ECC..8ECD   軌 軍
   *    8ED2         軒
   *    8EDF         軟
   *    8EE2         転
   *    8EF8         軸
   *    8EFD         軽
   *    8F03         較
   *    8F09         載
   *    8F1D         輝
   *    8F29..8F2A   輩 輪
   *    8F38         輸
   *    8F44         轄
   *    8F9B         辛
   *    8F9E         辞
   *    8FA3         辣
   *    8FB1..8FB2   辱 農
   *    8FBA         辺
   *    8FBC         込
   *    8FC5         迅
   *    8FCE         迎
   *    8FD1         近
   *    8FD4         返
   *    8FEB         迫
   *    8FED         迭
   *    8FF0         述
   *    8FF7         迷
   *    8FFD         追
   *    9000..9001   退 送
   *    9003         逃
   *    9006         逆
   *    900F..9010   透 逐
   *    9013..9014   逓 途
   *    901A         通
   *    901D         逝
   *    901F..9020   速 造
   *    9023         連
   *    902E         逮
   *    9031..9032   週 進
   *    9038         逸
   *    9042         遂
   *    9045         遅
   *    9047         遇
   *    904A..904B   遊 運
   *    904D..904E   遍 過
   *    9053..9055   道 達 違
   *    905C         遜
   *    9060..9061   遠 遡
   *    9063         遣
   *    9069         適
   *    906D..906E   遭 遮
   *    9075         遵
   *    9077..9078   遷 選
   *    907A         遺
   *    907F         避
   *    9084         還
   *    90A3         那
   *    90A6         邦
   *    90AA         邪
   *    90B8         邸
   *    90CA         郊
   *    90CE         郎
   *    90E1         郡
   *    90E8         部
   *    90ED         郭
   *    90F5         郵
   *    90F7         郷
   *    90FD         都
   *    914C..914E   酌 配 酎
   *    9152         酒
   *    9154         酔
   *    9162         酢
   *    916A         酪
   *    916C         酬
   *    9175         酵
   *    9177..9178   酷 酸
   *    9192         醒
   *    919C         醜
   *    91B8         醸
   *    91C7..91C8   采 釈
   *    91CC..91CF   里 重 野 量
   *    91D1         金
   *    91DC..91DD   釜 針
   *    91E3         釣
   *    920D         鈍
   *    9234         鈴
   *    9244         鉄
   *    925B         鉛
   *    9262         鉢
   *    9271         鉱
   *    9280         銀
   *    9283         銃
   *    9285         銅
   *    9298         銘
   *    92AD         銭
   *    92ED         鋭
   *    92F3         鋳
   *    92FC         鋼
   *    9320         錠
   *    9326         錦
   *    932C         錬
   *    932E..932F   錮 錯
   *    9332         録
   *    934B         鍋
   *    935B         鍛
   *    9375         鍵
   *    938C         鎌
   *    9396         鎖
   *    93AE         鎮
   *    93E1         鏡
   *    9418         鐘
   *    9451         鑑
   *    9577         長
   *    9580         門
   *    9589         閉
   *    958B         開
   *    9591         閑
   *    9593         間
   *    95A2..95A3   関 閣
   *    95A5         閥
   *    95B2         閲
   *    95C7         闇
   *    95D8         闘
   *    961C         阜
   *    962A         阪
   *    9632         防
   *    963B         阻
   *    9644         附
   *    964D         降
   *    9650         限
   *    965B         陛
   *    9662..9665   院 陣 除 陥
   *    966A         陪
   *    9670         陰
   *    9673         陳
   *    9675..9676   陵 陶
   *    9678         陸
   *    967A         険
   *    967D         陽
   *    9685..9686   隅 隆
   *    968A         隊
   *    968E..968F   階 随
   *    9694         隔
   *    9699         隙
   *    969B..969C   際 障
   *    96A0         隠
   *    96A3         隣
   *    96B7         隷
   *    96BB         隻
   *    96C4..96C7   雄 雅 集 雇
   *    96CC         雌
   *    96D1         雑
   *    96E2..96E3   離 難
   *    96E8         雨
   *    96EA         雪
   *    96F0         雰
   *    96F2         雲
   *    96F6..96F7   零 雷
   *    96FB         電
   *    9700         需
   *    9707         震
   *    970A         霊
   *    971C         霜
   *    9727         霧
   *    9732         露
   *    9752         青
   *    9759         静
   *    975E         非
   *    9762         面
   *    9769         革
   *    9774         靴
   *    97D3         韓
   *    97F3         音
   *    97FB         韻
   *    97FF         響
   *    9802..9803   頂 頃
   *    9805..9806   項 順
   *    9808         須
   *    9810..9813   預 頑 頒 頓
   *    9818         領
   *    982D         頭
   *    9830         頰
   *    983B..983C   頻 頼
   *    984C..984E   題 額 顎
   *    9854..9855   顔 顕
   *    9858         願
   *    985E         類
   *    9867         顧
   *    98A8         風
   *    98DB         飛
   *    98DF         食
   *    98E2         飢
   *    98EF         飯
   *    98F2         飲
   *    98FC..98FE   飼 飽 飾
   *    9905         餅
   *    990A         養
   *    990C         餌
   *    9913         餓
   *    9928         館
   *    9996         首
   *    9999         香
   *    99AC         馬
   *    99C4..99C6   駄 駅 駆
   *    99D0         駐
   *    99D2         駒
   *    9A0E         騎
   *    9A12..9A13   騒 験
   *    9A30         騰
   *    9A5A         驚
   *    9AA8         骨
   *    9AB8         骸
   *    9AC4         髄
   *    9AD8         高
   *    9AEA         髪
   *    9B31         鬱
   *    9B3C         鬼
   *    9B42         魂
   *    9B45         魅
   *    9B54         魔
   *    9B5A         魚
   *    9BAE         鮮
   *    9BE8         鯨
   *    9CE5         鳥
   *    9CF4         鳴
   *    9D8F         鶏
   *    9DB4         鶴
   *    9E7F         鹿
   *    9E93         麓
   *    9E97         麗
   *    9EA6         麦
   *    9EBA..9EBB   麺 麻
   *    9EC4         黄
   *    9ED2         黒
   *    9ED9         黙
   *    9F13         鼓
   *    9F3B         鼻
   *    9F62         齢
   *    20B9F         𠮟
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
   * @see <a href="https://en.wikipedia.org/wiki/List_of_jōyō_kanji" target="_blank">Jōyō
   *      kanji – Wikipedia</a> provided information about the Jōyō kanji.
   * 
   * @see <a href="https://en.wikipedia.org/wiki/Kanji" target="_blank">Kanji
   *      – Wikipedia</a> provided information about the Kanji script and its relationship to
   *      the Chinese family of scripts.
   * 
   * @see <a href="https://unicode.org/faq/han_cjk.html" target="_blank">Chinese and Japanese
   *      FAQs – Unicode Org</a> provided information about
   *      the Unicode Unified CJK encodings.
   * 
   * @see <a href='./doc-files/JAPANESE_ja_Hani_joyo.txt'>JAPANESE_ja_Hani_joyo</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani_x_joyo = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hani").build(),
      Letters_Japanese_ja_Hani_x_joyo.SUBSET);

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
   * <p>There are too many unicode code-points (characters) in this set to display here. See separate 
   *    <a href='./doc-files/JAPANESE_ja_Hani_x_jsource.txt'>JAPANESE_ja_Hani_x_jsource documentation file</a>
   *    for a complete list of the unicode code-points in this set.</p>
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
   * @see <a href='./doc-files/JAPANESE_ja_Hani_jsource.txt'>JAPANESE_ja_Hani_jsource</a>
   *      for code points in this language set.
   */
  public static final Subset JAPANESE_ja_Hani_x_jsource = registerSubset(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hani").build(),
      Letters_Japanese_ja_Hani_x_jsource.SUBSET);

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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
        new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      },
      2, 52));
  public static final Subset PORTUGUESE_pt = registerSubset(
      new Locale("pt", "", ""),
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
      Factory.rangedSubset(
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
