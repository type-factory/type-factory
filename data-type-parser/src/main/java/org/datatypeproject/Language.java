package org.datatypeproject;

import java.util.Locale;
import javax.annotation.processing.Generated;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the data-type-language-code-generator module.",
    value = "org.datatypeproject:data-type-language-code-generator")
public interface Language extends Subset {

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
  Language ARABIC_ar = new LanguageImpl(
      new Locale("ar", "", ""),
      new int[]{
          0x0621_063a, //  ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_064a, //  ف ق ك ل م ن ه و ى ي
      });

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
  Language AZERBAIJANI_az_Latn = new LanguageImpl(
      new Locale.Builder().setLanguage("az").setRegion("").setVariant("").setScript("Latn").build(),
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
      });

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
  Language DANISH_da = new LanguageImpl(
      new Locale("da", "", ""),
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
      });

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
  Language GERMAN_de = new LanguageImpl(
      new Locale("de", "", ""),
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
      });

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
  Language GREEK_el = new LanguageImpl(
      new Locale("el", "", ""),
      new int[]{
          0x0386_0386, //  Ά
          0x0388_038a, //  Έ Ή Ί
          0x038c_038c, //  Ό
          0x038e_03a1, //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
          0x03a3_03ce, //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π
                       //  ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
      });

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
  Language ENGLISH_en = new LanguageImpl(
      new Locale("en", "", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      });

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
  Language SPANISH_es = new LanguageImpl(
      new Locale("es", "", ""),
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
      });

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
  Language FINNISH_fi = new LanguageImpl(
      new Locale("fi", "", ""),
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
      });

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
  Language FRENCH_fr = new LanguageImpl(
      new Locale("fr", "", ""),
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
      });

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
  Language HINDI_hi = new LanguageImpl(
      new Locale("hi", "", ""),
      new int[]{
          0x0902_0903, //  ं ः
          0x0905_090b, //  अ आ इ ई उ ऊ ऋ
          0x090f_0910, //  ए ऐ
          0x0913_0928, //  ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न
          0x092a_0930, //  प फ ब भ म य र
          0x0932_0932, //  ल
          0x0935_0939, //  व श ष स ह
          0x093c_094d, //  ़ ऽ ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
      });

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
  Language NEPALI_ne = new LanguageImpl(
      new Locale("ne", "", ""),
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
      });
  Language ARMENIAN_hy = new LanguageImpl(
      new Locale("hy", "", ""),
      new int[]{
          0x0531_0556, //  Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ
                       //  Տ Ր Ց Ւ Փ Ք Օ Ֆ
          0x0561_0586, //  ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ
                       //  տ ր ց ւ փ ք օ ֆ
      });
  Language ICELANDIC_is = new LanguageImpl(
      new Locale("is", "", ""),
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
      });

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
  Language ITALIAN_it = new LanguageImpl(
      new Locale("it", "", ""),
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
      });

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
  Language JAPANESE_ja_Hira = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hira").build(),
      new int[]{
          0x3041_3096, //  ぁ あ ぃ い ぅ う ぇ え ぉ お か が き ぎ く ぐ け げ こ ご さ ざ し じ す ず せ ぜ そ ぞ
                       //  た だ ち ぢ っ つ づ て で と ど な に ぬ ね の は ば ぱ ひ び ぴ ふ ぶ ぷ へ べ ぺ ほ ぼ
                       //  ぽ ま み む め も ゃ や ゅ ゆ ょ よ ら り る れ ろ ゎ わ ゐ ゑ を ん ゔ ゕ ゖ
      });

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
  Language JAPANESE_ja_Kana = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Kana").build(),
      new int[]{
          0x30a1_30fa, //  ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ
                       //  タ ダ チ ヂ ッ ツ ヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ バ パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ
                       //  ポ マ ミ ム メ モ ャ ヤ ュ ユ ョ ヨ ラ リ ル レ ロ ヮ ワ ヰ ヱ ヲ ン ヴ ヵ ヶ ヷ ヸ ヹ ヺ
      });

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
  Language JAPANESE_ja_Hani = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hani").build(),
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
      });

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
  Language JAPANESE_ja_Hani_jsource = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("JSOURCE").setScript("Hani").build(),
      new int[]{
          0x3400_3400, //  㐀
          0x3402_3402, //  㐂
          0x3404_3406, //  㐄 㐅 㐆
          0x3427_3427, //  㐧
          0x342a_342a, //  㐪
          0x342c_3430, //  㐬 㐭 㐮 㐯 㐰
          0x3436_3436, //  㐶
          0x343a_343a, //  㐺
          0x3441_3442, //  㑁 㑂
          0x3445_3445, //  㑅
          0x3450_3450, //  㑐
          0x3452_3453, //  㑒 㑓
          0x3458_3458, //  㑘
          0x345e_345e, //  㑞
          0x3464_3464, //  㑤
          0x3466_3468, //  㑦 㑧 㑨
          0x346a_346a, //  㑪
          0x3478_3479, //  㑸 㑹
          0x3492_3492, //  㒒
          0x34ab_34ab, //  㒫
          0x34b4_34b6, //  㒴 㒵 㒶
          0x34bc_34bc, //  㒼
          0x34c1_34c1, //  㓁
          0x34c3_34c3, //  㓃
          0x34c7_34c7, //  㓇
          0x34d7_34d7, //  㓗
          0x34db_34dd, //  㓛 㓜 㓝
          0x34ea_34ea, //  㓪
          0x34ec_34ec, //  㓬
          0x34f2_34f2, //  㓲
          0x3501_3501, //  㔁
          0x3513_3513, //  㔓
          0x3515_3515, //  㔕
          0x3517_3517, //  㔗
          0x351a_351a, //  㔚
          0x351c_351c, //  㔜
          0x351f_351f, //  㔟
          0x3526_3526, //  㔦
          0x352b_352c, //  㔫 㔬
          0x3530_3531, //  㔰 㔱
          0x3533_3535, //  㔳 㔴 㔵
          0x353a_353a, //  㔺
          0x353c_353c, //  㔼
          0x353e_353e, //  㔾
          0x3543_3543, //  㕃
          0x3553_3553, //  㕓
          0x355a_355e, //  㕚 㕛 㕜 㕝 㕞
          0x3563_3563, //  㕣
          0x3566_3567, //  㕦 㕧
          0x356e_356f, //  㕮 㕯
          0x3575_3575, //  㕵
          0x3579_3579, //  㕹
          0x357d_357d, //  㕽
          0x3596_3596, //  㖖
          0x35a2_35a2, //  㖢
          0x35a6_35a6, //  㖦
          0x35a8_35a8, //  㖨
          0x35ab_35ab, //  㖫
          0x35b6_35b6, //  㖶
          0x35c1_35c1, //  㗁
          0x35c5_35c7, //  㗅 㗆 㗇
          0x35d4_35d4, //  㗔
          0x35d6_35d6, //  㗖
          0x35da_35db, //  㗚 㗛
          0x35e4_35e4, //  㗤
          0x35eb_35eb, //  㗫
          0x35f4_35f4, //  㗴
          0x3605_3605, //  㘅
          0x361c_361c, //  㘜
          0x3622_3622, //  㘢
          0x362b_362b, //  㘫
          0x3632_3632, //  㘲
          0x3634_3634, //  㘴
          0x3644_3644, //  㙄
          0x364a_364a, //  㙊
          0x367b_367b, //  㙻
          0x3686_3686, //  㚆
          0x3688_3689, //  㚈 㚉
          0x3691_3691, //  㚑
          0x3695_3696, //  㚕 㚖
          0x3699_3699, //  㚙
          0x36a0_36a0, //  㚠
          0x36bb_36bb, //  㚻
          0x36cf_36cf, //  㛏
          0x36d2_36d2, //  㛒
          0x36f7_36f7, //  㛷
          0x373e_373e, //  㜾
          0x3747_3747, //  㝇
          0x374b_374b, //  㝋
          0x3751_3751, //  㝑
          0x3758_3759, //  㝘 㝙
          0x375f_3762, //  㝟 㝠 㝡 㝢
          0x376b_376d, //  㝫 㝬 㝭
          0x3775_3775, //  㝵
          0x3778_3778, //  㝸
          0x378d_378d, //  㞍
          0x37aa_37aa, //  㞪
          0x37ac_37ac, //  㞬
          0x37ae_37ae, //  㞮
          0x37b4_37b4, //  㞴
          0x37b7_37b7, //  㞷
          0x37ba_37bc, //  㞺 㞻 㞼
          0x37bf_37bf, //  㞿
          0x37c1_37c1, //  㟁
          0x37d2_37d2, //  㟒
          0x37db_37dc, //  㟛 㟜
          0x37df_37df, //  㟟
          0x37e1_37e2, //  㟡 㟢
          0x37e7_37e8, //  㟧 㟨
          0x37f4_37f5, //  㟴 㟵
          0x37fb_37fb, //  㟻
          0x37fd_37fd, //  㟽
          0x3800_3800, //  㠀
          0x3809_3809, //  㠉
          0x380c_380c, //  㠌
          0x380e_380e, //  㠎
          0x3810_3810, //  㠐
          0x3815_3817, //  㠕 㠖 㠗
          0x381d_381d, //  㠝
          0x382a_382a, //  㠪
          0x382f_382f, //  㠯
          0x3836_3836, //  㠶
          0x383c_383c, //  㠼
          0x3840_3840, //  㡀
          0x384c_384c, //  㡌
          0x385c_385c, //  㡜
          0x385e_385e, //  㡞
          0x3861_3863, //  㡡 㡢 㡣
          0x386b_386c, //  㡫 㡬
          0x3873_3873, //  㡳
          0x387d_387d, //  㡽
          0x3888_3888, //  㢈
          0x388b_388b, //  㢋
          0x3891_3891, //  㢑
          0x3895_3895, //  㢕
          0x389c_389c, //  㢜
          0x38a1_38a3, //  㢡 㢢 㢣
          0x38a7_38a7, //  㢧
          0x38b4_38b4, //  㢴
          0x38c0_38c0, //  㣀
          0x38c3_38c3, //  㣃
          0x38c9_38c9, //  㣉
          0x38d8_38d8, //  㣘
          0x38de_38de, //  㣞
          0x38e0_38e0, //  㣠
          0x38e2_38e2, //  㣢
          0x38e5_38e5, //  㣥
          0x38ea_38ea, //  㣪
          0x38f1_38f2, //  㣱 㣲
          0x38fa_38fa, //  㣺
          0x38fc_38fc, //  㣼
          0x3905_3905, //  㤅
          0x3917_3917, //  㤗
          0x391a_391a, //  㤚
          0x3920_3920, //  㤠
          0x3922_3922, //  㤢
          0x3929_3929, //  㤩
          0x392b_392b, //  㤫
          0x392f_392f, //  㤯
          0x3935_3935, //  㤵
          0x393a_393a, //  㤺
          0x3940_3941, //  㥀 㥁
          0x394c_394c, //  㥌
          0x3950_3950, //  㥐
          0x3960_3960, //  㥠
          0x3963_3964, //  㥣 㥤
          0x3966_3966, //  㥦
          0x3969_3969, //  㥩
          0x396f_396f, //  㥯
          0x3971_3973, //  㥱 㥲 㥳
          0x3998_3998, //  㦘
          0x399b_399b, //  㦛
          0x399e_399e, //  㦞
          0x39a3_39a5, //  㦣 㦤 㦥
          0x39ae_39ae, //  㦮
          0x39b1_39b2, //  㦱 㦲
          0x39b6_39b6, //  㦶
          0x39be_39be, //  㦾
          0x39c8_39c8, //  㧈
          0x39de_39de, //  㧞
          0x39e8_39e8, //  㧨
          0x39fd_39fd, //  㧽
          0x3a0b_3a0b, //  㨋
          0x3a17_3a17, //  㨗
          0x3a2f_3a2f, //  㨯
          0x3a36_3a36, //  㨶
          0x3a3b_3a3b, //  㨻
          0x3a3f_3a3f, //  㨿
          0x3a4d_3a4d, //  㩍
          0x3a5c_3a5c, //  㩜
          0x3a6e_3a6e, //  㩮
          0x3a73_3a73, //  㩳
          0x3a7d_3a7d, //  㩽
          0x3a85_3a85, //  㪅
          0x3a89_3a89, //  㪉
          0x3a98_3a98, //  㪘
          0x3a9f_3a9f, //  㪟
          0x3ab0_3ab0, //  㪰
          0x3abd_3abd, //  㪽
          0x3abf_3ac0, //  㪿 㫀
          0x3ac4_3ac4, //  㫄
          0x3ad0_3ad1, //  㫐 㫑
          0x3ad5_3ad7, //  㫕 㫖 㫗
          0x3adb_3adb, //  㫛
          0x3ade_3ade, //  㫞
          0x3ae0_3ae0, //  㫠
          0x3ae4_3ae4, //  㫤
          0x3ae6_3ae6, //  㫦
          0x3ae8_3ae8, //  㫨
          0x3aea_3aea, //  㫪
          0x3aef_3aef, //  㫯
          0x3af3_3af4, //  㫳 㫴
          0x3af6_3af7, //  㫶 㫷
          0x3afc_3afc, //  㫼
          0x3b01_3b01, //  㬁
          0x3b0a_3b0a, //  㬊
          0x3b0e_3b0e, //  㬎
          0x3b1a_3b1a, //  㬚
          0x3b1c_3b1d, //  㬜 㬝
          0x3b22_3b22, //  㬢
          0x3b25_3b27, //  㬥 㬦 㬧
          0x3b30_3b30, //  㬰
          0x3b35_3b36, //  㬵 㬶
          0x3b3c_3b3c, //  㬼
          0x3b43_3b43, //  㭃
          0x3b4c_3b4c, //  㭌
          0x3b55_3b55, //  㭕
          0x3b59_3b59, //  㭙
          0x3b5d_3b5d, //  㭝
          0x3b6d_3b6d, //  㭭
          0x3b74_3b74, //  㭴
          0x3b77_3b78, //  㭷 㭸
          0x3b7b_3b7b, //  㭻
          0x3b87_3b88, //  㮇 㮈
          0x3b8a_3b8b, //  㮊 㮋
          0x3b8d_3b8d, //  㮍
          0x3b9b_3b9b, //  㮛
          0x3ba4_3ba4, //  㮤
          0x3bb5_3bb6, //  㮵 㮶
          0x3bbb_3bbb, //  㮻
          0x3bc3_3bc3, //  㯃
          0x3bcd_3bcd, //  㯍
          0x3be2_3be2, //  㯢
          0x3bec_3bec, //  㯬
          0x3bf0_3bf0, //  㯰
          0x3bf3_3bf3, //  㯳
          0x3bf8_3bf8, //  㯸
          0x3c0f_3c0f, //  㰏
          0x3c13_3c13, //  㰓
          0x3c1e_3c1e, //  㰞
          0x3c26_3c26, //  㰦
          0x3c2d_3c2e, //  㰭 㰮
          0x3c31_3c31, //  㰱
          0x3c38_3c38, //  㰸
          0x3c45_3c45, //  㱅
          0x3c4b_3c4b, //  㱋
          0x3c4f_3c4f, //  㱏
          0x3c55_3c55, //  㱕
          0x3c5d_3c5d, //  㱝
          0x3c8a_3c8a, //  㲊
          0x3c9b_3c9b, //  㲛
          0x3c9d_3c9d, //  㲝
          0x3cb8_3cb8, //  㲸
          0x3cbc_3cbc, //  㲼
          0x3cc3_3cc3, //  㳃
          0x3cd1_3cd2, //  㳑 㳒
          0x3cd6_3cd6, //  㳖
          0x3cd9_3cda, //  㳙 㳚
          0x3cdc_3cdc, //  㳜
          0x3cdf_3cdf, //  㳟
          0x3cf5_3cf5, //  㳵
          0x3cfa_3cfa, //  㳺
          0x3d00_3d00, //  㴀
          0x3d11_3d11, //  㴑
          0x3d1e_3d1e, //  㴞
          0x3d20_3d20, //  㴠
          0x3d2c_3d2c, //  㴬
          0x3d35_3d35, //  㴵
          0x3d4e_3d4e, //  㵎
          0x3d52_3d53, //  㵒 㵓
          0x3d5d_3d5d, //  㵝
          0x3d64_3d64, //  㵤
          0x3d67_3d67, //  㵧
          0x3d6e_3d6e, //  㵮
          0x3d76_3d76, //  㵶
          0x3d7c_3d7c, //  㵼
          0x3d93_3d93, //  㶓
          0x3d9a_3d9a, //  㶚
          0x3da6_3da6, //  㶦
          0x3db5_3db5, //  㶵
          0x3dc0_3dc0, //  㷀
          0x3dc9_3dc9, //  㷉
          0x3dd4_3dd4, //  㷔
          0x3dd6_3dd6, //  㷖
          0x3df1_3df1, //  㷱
          0x3e05_3e05, //  㸅
          0x3e0f_3e0f, //  㸏
          0x3e33_3e33, //  㸳
          0x3e3f_3e40, //  㸿 㹀
          0x3e45_3e45, //  㹅
          0x3e5c_3e5c, //  㹜
          0x3e60_3e60, //  㹠
          0x3e63_3e63, //  㹣
          0x3e66_3e66, //  㹦
          0x3e68_3e68, //  㹨
          0x3e72_3e72, //  㹲
          0x3e77_3e77, //  㹷
          0x3e79_3e79, //  㹹
          0x3e83_3e83, //  㺃
          0x3e8a_3e8a, //  㺊
          0x3e94_3e94, //  㺔
          0x3eb2_3eb2, //  㺲
          0x3eda_3eda, //  㻚
          0x3ee1_3ee1, //  㻡
          0x3ee8_3ee8, //  㻨
          0x3f0b_3f0b, //  㼋
          0x3f18_3f18, //  㼘
          0x3f3c_3f3c, //  㼼
          0x3f56_3f57, //  㽖 㽗
          0x3f6a_3f6a, //  㽪
          0x3f72_3f72, //  㽲
          0x3f75_3f75, //  㽵
          0x3f77_3f77, //  㽷
          0x3f97_3f97, //  㾗
          0x3fae_3fae, //  㾮
          0x3fc2_3fc2, //  㿂
          0x3fc9_3fc9, //  㿉
          0x3fcb_3fcb, //  㿋
          0x3fcd_3fcd, //  㿍
          0x3fd7_3fd7, //  㿗
          0x3fdf_3fdf, //  㿟
          0x3fe4_3fe4, //  㿤
          0x4034_4035, //  䀴 䀵
          0x4039_4039, //  䀹
          0x4040_4040, //  䁀
          0x4048_4048, //  䁈
          0x4050_4051, //  䁐 䁑
          0x4058_4058, //  䁘
          0x405f_405f, //  䁟
          0x4071_4071, //  䁱
          0x408a_408a, //  䂊
          0x4093_4093, //  䂓
          0x4096_4096, //  䂖
          0x40a2_40a2, //  䂢
          0x40b2_40b2, //  䂲
          0x40cd_40cd, //  䃍
          0x40dd_40dd, //  䃝
          0x40ef_40ef, //  䃯
          0x40fe_40fe, //  䃾
          0x4100_4100, //  䄀
          0x4102_4103, //  䄂 䄃
          0x4105_4105, //  䄅
          0x4107_4107, //  䄇
          0x4112_4112, //  䄒
          0x4126_4126, //  䄦
          0x412a_412b, //  䄪 䄫
          0x4138_4138, //  䄸
          0x4145_4146, //  䅅 䅆
          0x4148_4148, //  䅈
          0x414b_414b, //  䅋
          0x414f_414f, //  䅏
          0x415e_415e, //  䅞
          0x4162_4163, //  䅢 䅣
          0x4165_4165, //  䅥
          0x4181_4181, //  䆁
          0x4192_4192, //  䆒
          0x41b4_41b4, //  䆴
          0x41bf_41bf, //  䆿
          0x41ca_41cb, //  䇊 䇋
          0x41d2_41d2, //  䇒
          0x41e1_41e1, //  䇡
          0x41e6_41e6, //  䇦
          0x41ee_41ee, //  䇮
          0x41f1_41f1, //  䇱
          0x41f3_41f3, //  䇳
          0x41ff_41ff, //  䇿
          0x4207_4207, //  䈇
          0x420e_420e, //  䈎
          0x4215_4215, //  䈕
          0x421e_421e, //  䈞
          0x4227_4227, //  䈧
          0x4234_4235, //  䈴 䈵
          0x4246_4246, //  䉆
          0x4256_4256, //  䉖
          0x425c_425c, //  䉜
          0x4264_4264, //  䉤
          0x427a_427a, //  䉺
          0x427d_427d, //  䉽
          0x4280_4280, //  䊀
          0x4285_4285, //  䊅
          0x4291_4291, //  䊑
          0x4293_4293, //  䊓
          0x4297_4297, //  䊗
          0x42a2_42a3, //  䊢 䊣
          0x42af_42af, //  䊯
          0x42b6_42b6, //  䊶
          0x42c1_42c1, //  䋁
          0x42c6_42c7, //  䋆 䋇
          0x42d0_42d0, //  䋐
          0x42d6_42d6, //  䋖
          0x42db_42db, //  䋛
          0x42dd_42dd, //  䋝
          0x42e8_42e8, //  䋨
          0x4302_4302, //  䌂
          0x4306_4307, //  䌆 䌇
          0x4313_4313, //  䌓
          0x4316_4316, //  䌖
          0x431e_431e, //  䌞
          0x432b_432c, //  䌫 䌬
          0x4343_4343, //  䍃
          0x434f_434f, //  䍏
          0x436a_436a, //  䍪
          0x43a9_43a9, //  䎩
          0x43ca_43cb, //  䏊 䏋
          0x43d5_43d5, //  䏕
          0x43de_43de, //  䏞
          0x43ec_43ec, //  䏬
          0x43ee_43f0, //  䏮 䏯 䏰
          0x43f7_43f8, //  䏷 䏸
          0x4408_4408, //  䐈
          0x440c_440c, //  䐌
          0x4417_4417, //  䐗
          0x441c_441c, //  䐜
          0x4422_4422, //  䐢
          0x4424_4424, //  䐤
          0x4451_4451, //  䑑
          0x4453_4453, //  䑓
          0x445b_445b, //  䑛
          0x4467_4468, //  䑧 䑨
          0x446d_446d, //  䑭
          0x4476_4476, //  䑶
          0x447a_447a, //  䑺
          0x4491_4491, //  䒑
          0x4494_4494, //  䒔
          0x44a2_44a2, //  䒢
          0x44a9_44a9, //  䒩
          0x44ab_44ab, //  䒫
          0x44b1_44b1, //  䒱
          0x44b3_44b3, //  䒳
          0x44b6_44b6, //  䒶
          0x44b9_44b9, //  䒹
          0x44be_44be, //  䒾
          0x44c1_44c1, //  䓁
          0x44cc_44cc, //  䓌
          0x44d0_44d0, //  䓐
          0x44d3_44d4, //  䓓 䓔
          0x44e6_44e6, //  䓦
          0x44f2_44f2, //  䓲
          0x44f5_44f6, //  䓵 䓶
          0x44fa_44fa, //  䓺
          0x4506_4506, //  䔆
          0x4508_4508, //  䔈
          0x450a_450a, //  䔊
          0x450d_450d, //  䔍
          0x4524_4525, //  䔤 䔥
          0x4535_4535, //  䔵
          0x453b_453c, //  䔻 䔼
          0x4543_4543, //  䕃
          0x454c_454c, //  䕌
          0x455e_455e, //  䕞
          0x456b_456b, //  䕫
          0x4576_4576, //  䕶
          0x457a_457a, //  䕺
          0x457e_457e, //  䕾
          0x4587_4587, //  䖇
          0x458d_458e, //  䖍 䖎
          0x4593_4593, //  䖓
          0x459b_459b, //  䖛
          0x459d_459d, //  䖝
          0x45a3_45a3, //  䖣
          0x45a7_45a7, //  䖧
          0x45b8_45b8, //  䖸
          0x45d9_45d9, //  䗙
          0x45dd_45dd, //  䗝
          0x45df_45df, //  䗟
          0x45e5_45e5, //  䗥
          0x45ea_45ea, //  䗪
          0x45f3_45f3, //  䗳
          0x4600_4600, //  䘀
          0x460d_460d, //  䘍
          0x460f_4610, //  䘏 䘐
          0x4616_4616, //  䘖
          0x4618_4618, //  䘘
          0x4621_4621, //  䘡
          0x4631_4631, //  䘱
          0x463a_463a, //  䘺
          0x4641_4641, //  䙁
          0x465e_465e, //  䙞
          0x4665_4665, //  䙥
          0x4672_4672, //  䙲
          0x4674_4674, //  䙴
          0x4679_467a, //  䙹 䙺
          0x467d_467d, //  䙽
          0x4688_4688, //  䚈
          0x46a1_46a1, //  䚡
          0x46ac_46ac, //  䚬
          0x46ae_46af, //  䚮 䚯
          0x46b1_46b1, //  䚱
          0x46b8_46b8, //  䚸
          0x46d1_46d1, //  䛑
          0x46d5_46d5, //  䛕
          0x46e6_46e6, //  䛦
          0x46ed_46ee, //  䛭 䛮
          0x46f1_46f1, //  䛱
          0x470b_470c, //  䜋 䜌
          0x470f_470f, //  䜏
          0x471f_471f, //  䜟
          0x4722_4722, //  䜢
          0x472c_472c, //  䜬
          0x4749_4749, //  䝉
          0x4764_4764, //  䝤
          0x4768_4769, //  䝨 䝩
          0x476f_476f, //  䝯
          0x4777_4777, //  䝷
          0x477a_477a, //  䝺
          0x477f_477f, //  䝿
          0x47af_47af, //  䞯
          0x47e6_47e6, //  䟦
          0x47fd_47fd, //  䟽
          0x4801_4801, //  䠁
          0x4816_4816, //  䠖
          0x4834_4834, //  䠴
          0x4844_4844, //  䡄
          0x484e_484e, //  䡎
          0x4871_4871, //  䡱
          0x4889_4889, //  䢉
          0x4897_4897, //  䢗
          0x489e_489f, //  䢞 䢟
          0x48a3_48a3, //  䢣
          0x48b5_48b5, //  䢵
          0x48d0_48d0, //  䣐
          0x48db_48db, //  䣛
          0x48e6_48e6, //  䣦
          0x48e9_48e9, //  䣩
          0x48ec_48ec, //  䣬
          0x48f3_48f4, //  䣳 䣴
          0x490e_490e, //  䤎
          0x491b_491b, //  䤛
          0x491d_491e, //  䤝 䤞
          0x4922_4924, //  䤢 䤣 䤤
          0x4929_4929, //  䤩
          0x492b_492b, //  䤫
          0x4930_4931, //  䤰 䤱
          0x4937_4937, //  䤷
          0x4943_4943, //  䥃
          0x494d_494f, //  䥍 䥎 䥏
          0x4962_4962, //  䥢
          0x496c_496c, //  䥬
          0x496e_496e, //  䥮
          0x4976_4976, //  䥶
          0x4987_4987, //  䦇
          0x4993_4993, //  䦓
          0x499a_499a, //  䦚
          0x499c_499c, //  䦜
          0x499e_499e, //  䦞
          0x49ad_49ad, //  䦭
          0x49b0_49b0, //  䦰
          0x49b4_49b4, //  䦴
          0x49bd_49be, //  䦽 䦾
          0x49c4_49c4, //  䧄
          0x49c9_49c9, //  䧉
          0x49cf_49cf, //  䧏
          0x49d4_49d4, //  䧔
          0x49de_49de, //  䧞
          0x49e2_49e2, //  䧢
          0x49e7_49e7, //  䧧
          0x49f6_49f6, //  䧶
          0x49fa_49fa, //  䧺
          0x4a04_4a04, //  䨄
          0x4a16_4a16, //  䨖
          0x4a29_4a29, //  䨩
          0x4a2c_4a2c, //  䨬
          0x4a4d_4a4d, //  䩍
          0x4a57_4a57, //  䩗
          0x4a6b_4a6b, //  䩫
          0x4a84_4a84, //  䪄
          0x4aa9_4aa9, //  䪩
          0x4ab5_4ab5, //  䪵
          0x4ab7_4ab8, //  䪷 䪸
          0x4abc_4abc, //  䪼
          0x4ad3_4ad3, //  䫓
          0x4add_4add, //  䫝
          0x4aeb_4aeb, //  䫫
          0x4b22_4b22, //  䬢
          0x4b3b_4b3b, //  䬻
          0x4b55_4b55, //  䭕
          0x4b70_4b70, //  䭰
          0x4b75_4b75, //  䭵
          0x4b7f_4b7f, //  䭿
          0x4b9e_4b9e, //  䮞
          0x4bb2_4bb2, //  䮲
          0x4bc2_4bc2, //  䯂
          0x4bca_4bca, //  䯊
          0x4bd2_4bd2, //  䯒
          0x4bdb_4bdb, //  䯛
          0x4be8_4be8, //  䯨
          0x4beb_4bec, //  䯫 䯬
          0x4c07_4c07, //  䰇
          0x4c0c_4c0c, //  䰌
          0x4c17_4c17, //  䰗
          0x4c20_4c20, //  䰠
          0x4c35_4c35, //  䰵
          0x4c3a_4c3a, //  䰺
          0x4c4d_4c4d, //  䱍
          0x4c70_4c70, //  䱰
          0x4c76_4c76, //  䱶
          0x4cac_4cac, //  䲬
          0x4cb3_4cb3, //  䲳
          0x4cbd_4cbe, //  䲽 䲾
          0x4cc4_4cc4, //  䳄
          0x4cd1_4cd1, //  䳑
          0x4ce1_4ce1, //  䳡
          0x4d07_4d07, //  䴇
          0x4d0e_4d0e, //  䴎
          0x4d1f_4d1f, //  䴟
          0x4d21_4d21, //  䴡
          0x4d77_4d77, //  䵷
          0x4d91_4d91, //  䶑
          0x4dac_4dac, //  䶬
          0x4daf_4db0, //  䶯 䶰
          0x4e00_4e05, //  一 丁 丂 七 丄 丅
          0x4e07_4e12, //  万 丈 三 上 下 丌 不 与 丏 丐 丑 丒
          0x4e14_4e19, //  且 丕 世 丗 丘 丙
          0x4e1e_4e1f, //  丞 丟
          0x4e21_4e21, //  両
          0x4e23_4e24, //  丣 两
          0x4e26_4e26, //  並
          0x4e28_4e32, //  丨 丩 个 丫 丬 中 丮 丯 丰 丱 串
          0x4e35_4e36, //  丵 丶
          0x4e38_4e39, //  丸 丹
          0x4e3b_4e3c, //  主 丼
          0x4e3f_4e45, //  丿 乀 乁 乂 乃 乄 久
          0x4e47_4e48, //  乇 么
          0x4e4b_4e4b, //  之
          0x4e4d_4e4f, //  乍 乎 乏
          0x4e51_4e51, //  乑
          0x4e55_4e5a, //  乕 乖 乗 乘 乙 乚
          0x4e5c_4e5f, //  乜 九 乞 也
          0x4e62_4e63, //  乢 乣
          0x4e68_4e69, //  乨 乩
          0x4e71_4e71, //  乱
          0x4e73_4e75, //  乳 乴 乵
          0x4e79_4e79, //  乹
          0x4e7e_4e80, //  乾 乿 亀
          0x4e82_4e82, //  亂
          0x4e85_4e86, //  亅 了
          0x4e88_4e8e, //  予 争 亊 事 二 亍 于
          0x4e91_4e92, //  云 互
          0x4e94_4e99, //  五 井 亖 亗 亘 亙
          0x4e9b_4ea2, //  些 亜 亝 亞 亟 亠 亡 亢
          0x4ea4_4ea6, //  交 亥 亦
          0x4ea8_4ea8, //  亨
          0x4eab_4eb0, //  享 京 亭 亮 亯 亰
          0x4eb3_4eb3, //  亳
          0x4eb6_4eb6, //  亶
          0x4eb9_4ebc, //  亹 人 亻 亼
          0x4ec0_4ec4, //  什 仁 仂 仃 仄
          0x4ec6_4ec8, //  仆 仇 仈
          0x4eca_4ecb, //  今 介
          0x4ecd_4ed0, //  仍 从 仏 仐
          0x4ed4_4edb, //  仔 仕 他 仗 付 仙 仚 仛
          0x4edd_4ee5, //  仝 仞 仟 仠 仡 仢 代 令 以
          0x4ee8_4ee8, //  仨
          0x4eeb_4eeb, //  仫
          0x4eed_4ef3, //  仭 仮 仯 仰 仱 仲 仳
          0x4ef5_4ef7, //  仵 件 价
          0x4efb_4efb, //  任
          0x4efd_4f03, //  份 仾 仿 伀 企 伂 伃
          0x4f08_4f12, //  伈 伉 伊 伋 伌 伍 伎 伏 伐 休 伒
          0x4f15_4f17, //  伕 伖 众
          0x4f19_4f1a, //  伙 会
          0x4f1c_4f1d, //  伜 伝
          0x4f2e_4f31, //  伮 伯 估 伱
          0x4f33_4f3e, //  伳 伴 伵 伶 伷 伸 伹 伺 伻 似 伽 伾
          0x4f40_4f40, //  佀
          0x4f42_4f43, //  佂 佃
          0x4f46_4f49, //  但 佇 佈 佉
          0x4f4b_4f60, //  佋 佌 位 低 住 佐 佑 佒 体 佔 何 佖 佗 佘 余 佚 佛 作 佝 佞 佟 你
          0x4f63_4f64, //  佣 佤
          0x4f69_4f6a, //  佩 佪
          0x4f6c_4f6c, //  佬
          0x4f6e_4f71, //  佮 佯 佰 佱
          0x4f73_4f73, //  佳
          0x4f75_4f7f, //  併 佶 佷 佸 佹 佺 佻 佼 佽 佾 使
          0x4f81_4f86, //  侁 侂 侃 侄 侅 來
          0x4f88_4f94, //  侈 侉 侊 例 侌 侍 侎 侏 侐 侑 侒 侓 侔
          0x4f96_4f9b, //  侖 侗 侘 侙 侚 供
          0x4f9d_4fa1, //  依 侞 侟 侠 価
          0x4fab_4fab, //  侫
          0x4fad_4faf, //  侭 侮 侯
          0x4fb2_4fb2, //  侲
          0x4fb5_4fb7, //  侵 侶 侷
          0x4fb9_4fb9, //  侹
          0x4fbb_4fc6, //  侻 侼 侽 侾 便 俀 俁 係 促 俄 俅 俆
          0x4fc8_4fd2, //  俈 俉 俊 俋 俌 俍 俎 俏 俐 俑 俒
          0x4fd4_4fd4, //  俔
          0x4fd7_4fd8, //  俗 俘
          0x4fda_4fdd, //  俚 俛 俜 保
          0x4fdf_4fe6, //  俟 俠 信 俢 俣 俤 俥 俦
          0x4fee_4ff3, //  修 俯 俰 俱 俲 俳
          0x4ff5_4ff6, //  俵 俶
          0x4ff8_4ff8, //  俸
          0x4ffa_4ffa, //  俺
          0x4ffc_5002, //  俼 俽 俾 俿 倀 倁 倂
          0x5004_5007, //  倄 倅 倆 倇
          0x5009_5014, //  倉 倊 個 倌 倍 倎 倏 倐 們 倒 倓 倔
          0x5016_501f, //  倖 倗 倘 候 倚 倛 倜 倝 倞 借
          0x5021_502e, //  倡 倢 倣 値 倥 倦 倧 倨 倩 倪 倫 倬 倭 倮
          0x5030_5030, //  倰
          0x5032_5033, //  倲 倳
          0x5035_5036, //  倵 倶
          0x5039_5039, //  倹
          0x503b_503b, //  倻
          0x5040_5043, //  偀 偁 偂 偃
          0x5045_504a, //  偅 偆 假 偈 偉 偊
          0x504c_504c, //  偌
          0x504e_5053, //  偎 偏 偐 偑 偒 偓
          0x5055_5057, //  偕 偖 偗
          0x5059_505a, //  偙 做
          0x505c_505c, //  停
          0x505f_5060, //  偟 偠
          0x5062_5063, //  偢 偣
          0x5065_5067, //  健 偦 偧
          0x506a_506a, //  偪
          0x506c_506d, //  偬 偭
          0x5070_5072, //  偰 偱 偲
          0x5074_5076, //  側 偵 偶
          0x5078_5078, //  偸
          0x507d_507d, //  偽
          0x5080_5081, //  傀 傁
          0x5083_5086, //  傃 傄 傅 傆
          0x5088_5088, //  傈
          0x508a_508a, //  傊
          0x508d_5096, //  傍 傎 傏 傐 傑 傒 傓 傔 傕 傖
          0x5098_509c, //  傘 備 傚 傛 傜
          0x509e_50a3, //  傞 傟 傠 傡 傢 傣
          0x50aa_50aa, //  傪
          0x50ac_50ad, //  催 傭
          0x50af_50b5, //  傯 傰 傱 傲 傳 傴 債
          0x50b7_50b7, //  傷
          0x50b9_50bb, //  傹 傺 傻
          0x50bd_50be, //  傽 傾
          0x50c0_50c0, //  僀
          0x50c2_50c5, //  僂 僃 僄 僅
          0x50c7_50c7, //  僇
          0x50c9_50ca, //  僉 僊
          0x50cc_50d1, //  僌 働 僎 像 僐 僑
          0x50d3_50d6, //  僓 僔 僕 僖
          0x50d8_50da, //  僘 僙 僚
          0x50dc_50df, //  僜 僝 僞 僟
          0x50e1_50e9, //  僡 僢 僣 僤 僥 僦 僧 僨 僩
          0x50ed_50ef, //  僭 僮 僯
          0x50f1_50f3, //  僱 僲 僳
          0x50f5_50f6, //  僵 僶
          0x50f9_50fb, //  價 僺 僻
          0x50fe_50fe, //  僾
          0x5100_5104, //  儀 儁 儂 儃 億
          0x5106_5109, //  儆 儇 儈 儉
          0x510b_510e, //  儋 儌 儍 儎
          0x5110_5110, //  儐
          0x5112_5112, //  儒
          0x5114_511f, //  儔 儕 儖 儗 儘 儙 儚 儛 儜 儝 儞 償
          0x5121_5121, //  儡
          0x5123_5123, //  儣
          0x5127_5128, //  儧 儨
          0x512a_512a, //  優
          0x512c_512d, //  儬 儭
          0x512f_512f, //  儯
          0x5131_5135, //  儱 儲 儳 儴 儵
          0x5137_513c, //  儷 儸 儹 儺 儻 儼
          0x513f_5150, //  儿 兀 允 兂 元 兄 充 兆 兇 先 光 兊 克 兌 免 兎 兏 児
          0x5152_5155, //  兒 兓 兔 兕
          0x5157_5158, //  兗 兘
          0x515a_515a, //  党
          0x515c_515c, //  兜
          0x515f_5160, //  兟 兠
          0x5162_5162, //  兢
          0x5164_5166, //  兤 入 兦
          0x5168_516e, //  全 兩 兪 八 公 六 兮
          0x5171_5171, //  共
          0x5173_5173, //  关
          0x5175_5178, //  兵 其 具 典
          0x517b_517c, //  养 兼
          0x517e_517e, //  兾
          0x5180_5180, //  冀
          0x5182_5186, //  冂 冃 冄 内 円
          0x5189_5193, //  冉 冊 冋 册 再 冎 冏 冐 冑 冒 冓
          0x5195_5199, //  冕 冖 冗 冘 写
          0x519d_519d, //  冝
          0x51a0_51a6, //  冠 冡 冢 冣 冤 冥 冦
          0x51a8_51ad, //  冨 冩 冪 冫 冬 冭
          0x51b0_51b8, //  冰 冱 冲 决 冴 况 冶 冷 冸
          0x51ba_51ba, //  冺
          0x51bc_51bf, //  冼 冽 冾 冿
          0x51c2_51c6, //  凂 凃 凄 凅 准
          0x51c8_51cd, //  凈 凉 凊 凋 凌 凍
          0x51cf_51cf, //  减
          0x51d1_51d3, //  凑 凒 凓
          0x51d5_51d6, //  凕 凖
          0x51d8_51d8, //  凘
          0x51db_51de, //  凛 凜 凝 凞
          0x51e0_51e2, //  几 凡 凢
          0x51e5_51e7, //  凥 処 凧
          0x51e9_51ea, //  凩 凪
          0x51ed_51ee, //  凭 凮
          0x51f0_51fa, //  凰 凱 凲 凳 凴 凵 凶 凷 凸 凹 出
          0x51fd_51fe, //  函 凾
          0x5200_5208, //  刀 刁 刂 刃 刄 刅 分 切 刈
          0x520a_520b, //  刊 刋
          0x520e_520e, //  刎
          0x5211_5218, //  刑 划 刓 刔 刕 刖 列 刘
          0x521d_521d, //  初
          0x5222_5222, //  刢
          0x5224_5225, //  判 別
          0x5227_522a, //  刧 刨 利 刪
          0x522e_522e, //  刮
          0x5230_5233, //  到 刱 刲 刳
          0x5235_523c, //  刵 制 刷 券 刹 刺 刻 刼
          0x5243_5245, //  剃 剄 剅
          0x5247_5247, //  則
          0x5249_524d, //  剉 削 剋 剌 前
          0x524f_524f, //  剏
          0x5254_5258, //  剔 剕 剖 剗 剘
          0x525a_5261, //  剚 剛 剜 剝 剞 剟 剠 剡
          0x5263_5266, //  剣 剤 剥 剦
          0x5269_526a, //  剩 剪
          0x526c_526c, //  剬
          0x526e_5275, //  剮 副 剰 剱 割 剳 剴 創
          0x5277_5279, //  剷 剸 剹
          0x527d_527d, //  剽
          0x527f_5280, //  剿 劀
          0x5282_5285, //  劂 劃 劄 劅
          0x5287_528a, //  劇 劈 劉 劊
          0x528c_528d, //  劌 劍
          0x5291_5298, //  劑 劒 劓 劔 劕 劖 劗 劘
          0x529a_529c, //  劚 力 劜
          0x529f_52a0, //  功 加
          0x52a3_52a7, //  劣 劤 劥 劦 劧
          0x52a9_52ad, //  助 努 劫 劬 劭
          0x52af_52b1, //  劯 劰 励
          0x52b4_52be, //  労 劵 劶 劷 劸 効 劺 劻 劼 劽 劾
          0x52c0_52c1, //  勀 勁
          0x52c3_52ca, //  勃 勄 勅 勆 勇 勈 勉 勊
          0x52cc_52cd, //  勌 勍
          0x52cf_52d2, //  勏 勐 勑 勒
          0x52d4_52d9, //  勔 動 勖 勗 勘 務
          0x52db_52ea, //  勛 勜 勝 勞 募 勠 勡 勢 勣 勤 勥 勦 勧 勨 勩 勪
          0x52ec_52ec, //  勬
          0x52f0_52fb, //  勰 勱 勲 勳 勴 勵 勶 勷 勸 勹 勺 勻
          0x52fe_5303, //  勾 勿 匀 匁 匂 匃
          0x5305_5308, //  包 匆 匇 匈
          0x530a_530d, //  匊 匋 匌 匍
          0x530f_5311, //  匏 匐 匑
          0x5313_5313, //  匓
          0x5315_5321, //  匕 化 北 匘 匙 匚 匛 匜 匝 匞 匟 匠 匡
          0x5323_5325, //  匣 匤 匥
          0x5327_532d, //  匧 匨 匩 匪 匫 匬 匭
          0x532f_5333, //  匯 匰 匱 匲 匳
          0x5335_5335, //  匵
          0x5338_5343, //  匸 匹 区 医 匼 匽 匾 匿 區 十 卂 千
          0x5345_534d, //  卅 卆 升 午 卉 半 卋 卌 卍
          0x5351_5354, //  卑 卒 卓 協
          0x5357_535c, //  南 単 卙 博 卛 卜
          0x535e_535e, //  卞
          0x5360_5361, //  占 卡
          0x5363_5363, //  卣
          0x5365_5367, //  卥 卦 卧
          0x5369_5369, //  卩
          0x536c_5375, //  卬 卭 卮 卯 印 危 卲 即 却 卵
          0x5377_537b, //  卷 卸 卹 卺 卻
          0x537d_537f, //  卽 卾 卿
          0x5382_5384, //  厂 厃 厄
          0x5387_5388, //  厇 厈
          0x538e_538e, //  厎
          0x5393_5394, //  厓 厔
          0x5396_5396, //  厖
          0x5398_539a, //  厘 厙 厚
          0x539d_539d, //  厝
          0x539f_53a1, //  原 厠 厡
          0x53a4_53a6, //  厤 厥 厦
          0x53a8_53ab, //  厨 厩 厪 厫
          0x53ad_53b0, //  厭 厮 厯 厰
          0x53b2_53b8, //  厲 厳 厴 厵 厶 厷 厸
          0x53ba_53bb, //  厺 去
          0x53bd_53bd, //  厽
          0x53c0_53c0, //  叀
          0x53c2_53c3, //  参 參
          0x53c5_53c5, //  叅
          0x53c8_53cf, //  又 叉 及 友 双 反 収 叏
          0x53d2_53d7, //  叒 叓 叔 叕 取 受
          0x53d9_53db, //  叙 叚 叛
          0x53dd_53f8, //  叝 叞 叟 叠 叡 叢 口 古 句 另 叧 叨 叩 只 叫 召 叭 叮 可 台 叱 史 右 叴 叵 叶 号 司
          0x53fa_53fa, //  叺
          0x5401_5404, //  吁 吂 吃 各
          0x5408_5413, //  合 吉 吊 吋 同 名 后 吏 吐 向 吒 吓
          0x541a_541b, //  吚 君
          0x541d_5421, //  吝 吞 吟 吠 吡
          0x5424_5424, //  吤
          0x5426_542f, //  否 吧 吨 吩 吪 含 听 吭 吮 启
          0x5431_5431, //  吱
          0x5434_5436, //  吴 吵 吶
          0x5438_5439, //  吸 吹
          0x543b_543e, //  吻 吼 吽 吾
          0x5440_5440, //  呀
          0x5442_5444, //  呂 呃 呄
          0x5446_544a, //  呆 呇 呈 呉 告
          0x544d_544f, //  呍 呎 呏
          0x5451_5451, //  呑
          0x5455_5455, //  呕
          0x545e_545f, //  呞 呟
          0x5462_5462, //  呢
          0x5464_5464, //  呤
          0x5466_546e, //  呦 呧 周 呩 呪 呫 呬 呭 呮
          0x5470_5471, //  呰 呱
          0x5473_5477, //  味 呴 呵 呶 呷
          0x547b_547d, //  呻 呼 命
          0x547f_5481, //  呿 咀 咁
          0x5483_5486, //  咃 咄 咅 咆
          0x5488_5492, //  咈 咉 咊 咋 和 咍 咎 咏 咐 咑 咒
          0x5495_5496, //  咕 咖
          0x549c_549c, //  咜
          0x549f_54a2, //  咟 咠 咡 咢
          0x54a4_54af, //  咤 咥 咦 咧 咨 咩 咪 咫 咬 咭 咮 咯
          0x54b1_54b3, //  咱 咲 咳
          0x54b7_54c4, //  咷 咸 咹 咺 咻 咼 咽 咾 咿 哀 品 哂 哃 哄
          0x54c6_54ca, //  哆 哇 哈 哉 哊
          0x54cd_54ce, //  响 哎
          0x54d8_54d8, //  哘
          0x54e0_54e2, //  哠 員 哢
          0x54e5_54e6, //  哥 哦
          0x54e8_54ea, //  哨 哩 哪
          0x54ec_54ef, //  哬 哭 哮 哯
          0x54f1_54f3, //  哱 哲 哳
          0x54f6_54f6, //  哶
          0x54fa_54fa, //  哺
          0x54fc_5501, //  哼 哽 哾 哿 唀 唁
          0x5504_5509, //  唄 唅 唆 唇 唈 唉
          0x550c_5510, //  唌 唍 唎 唏 唐
          0x5514_5516, //  唔 唕 唖
          0x552a_552b, //  唪 唫
          0x552e_552f, //  售 唯
          0x5531_5533, //  唱 唲 唳
          0x5535_5536, //  唵 唶
          0x5538_5539, //  唸 唹
          0x553b_553e, //  唻 唼 唽 唾
          0x5540_5541, //  啀 啁
          0x5544_5547, //  啄 啅 商 啇
          0x5549_554a, //  啉 啊
          0x554c_554d, //  啌 啍
          0x554f_5551, //  問 啐 啑
          0x5553_5553, //  啓
          0x5556_5558, //  啖 啗 啘
          0x555a_555e, //  啚 啛 啜 啝 啞
          0x5560_5561, //  啠 啡
          0x5563_5564, //  啣 啤
          0x5566_5566, //  啦
          0x557b_5584, //  啻 啼 啽 啾 啿 喀 喁 喂 喃 善
          0x5586_558b, //  喆 喇 喈 喉 喊 喋
          0x558e_558f, //  喎 喏
          0x5591_5594, //  喑 喒 喓 喔
          0x5597_559a, //  喗 喘 喙 喚
          0x559c_559f, //  喜 喝 喞 喟
          0x55a3_55a4, //  喣 喤
          0x55a7_55ae, //  喧 喨 喩 喪 喫 喬 喭 單
          0x55b0_55b0, //  喰
          0x55b2_55b2, //  喲
          0x55b6_55b6, //  営
          0x55bf_55bf, //  喿
          0x55c1_55c1, //  嗁
          0x55c3_55c7, //  嗃 嗄 嗅 嗆 嗇
          0x55c9_55c9, //  嗉
          0x55cb_55cc, //  嗋 嗌
          0x55ce_55ce, //  嗎
          0x55d1_55d4, //  嗑 嗒 嗓 嗔
          0x55d7_55d8, //  嗗 嗘
          0x55da_55df, //  嗚 嗛 嗜 嗝 嗞 嗟
          0x55e2_55e4, //  嗢 嗣 嗤
          0x55e9_55e9, //  嗩
          0x55f6_55f7, //  嗶 嗷
          0x55f9_55f9, //  嗹
          0x55fd_55ff, //  嗽 嗾 嗿
          0x5605_560a, //  嘅 嘆 嘇 嘈 嘉 嘊
          0x560d_5612, //  嘍 嘎 嘏 嘐 嘑 嘒
          0x5614_5614, //  嘔
          0x5616_5619, //  嘖 嘗 嘘 嘙
          0x561b_561b, //  嘛
          0x5628_5629, //  嘨 嘩
          0x562c_562c, //  嘬
          0x562f_5639, //  嘯 嘰 嘱 嘲 嘳 嘴 嘵 嘶 嘷 嘸 嘹
          0x563b_563d, //  嘻 嘼 嘽
          0x563f_5644, //  嘿 噀 噁 噂 噃 噄
          0x5646_5647, //  噆 噇
          0x5649_5649, //  噉
          0x564b_5650, //  噋 噌 噍 噎 噏 噐
          0x5653_5654, //  噓 噔
          0x565b_565b, //  噛
          0x565e_565e, //  噞
          0x5660_5664, //  噠 噡 噢 噣 噤
          0x5666_5666, //  噦
          0x5668_566d, //  器 噩 噪 噫 噬 噭
          0x566f_566f, //  噯
          0x5671_5672, //  噱 噲
          0x5674_5676, //  噴 噵 噶
          0x5678_5678, //  噸
          0x567a_567a, //  噺
          0x5680_5680, //  嚀
          0x5684_5688, //  嚄 嚅 嚆 嚇 嚈
          0x568a_568c, //  嚊 嚋 嚌
          0x568f_568f, //  嚏
          0x5694_5695, //  嚔 嚕
          0x5699_569a, //  嚙 嚚
          0x569d_56a0, //  嚝 嚞 嚟 嚠
          0x56a2_56a2, //  嚢
          0x56a5_56a9, //  嚥 嚦 嚧 嚨 嚩
          0x56ab_56ae, //  嚫 嚬 嚭 嚮
          0x56b1_56b4, //  嚱 嚲 嚳 嚴
          0x56b6_56b7, //  嚶 嚷
          0x56bc_56bc, //  嚼
          0x56be_56be, //  嚾
          0x56c0_56c3, //  囀 囁 囂 囃
          0x56c5_56c5, //  囅
          0x56c8_56d1, //  囈 囉 囊 囋 囌 囍 囎 囏 囐 囑
          0x56d3_56d3, //  囓
          0x56d7_56e1, //  囗 囘 囙 囚 四 囜 囝 回 囟 因 囡
          0x56e3_56e8, //  団 囤 囥 囦 囧 囨
          0x56eb_56eb, //  囫
          0x56ed_56ee, //  园 囮
          0x56f0_56f3, //  困 囱 囲 図
          0x56f6_56f7, //  囶 囷
          0x56f9_56fa, //  囹 固
          0x56fd_56fd, //  国
          0x56ff_5704, //  囿 圀 圁 圂 圃 圄
          0x5707_570d, //  圇 圈 圉 圊 國 圌 圍
          0x570f_570f, //  圏
          0x5711_5713, //  圑 園 圓
          0x5715_5716, //  圕 圖
          0x5718_5718, //  團
          0x571a_571d, //  圚 圛 圜 圝
          0x571f_572a, //  土 圠 圡 圢 圣 圤 圥 圦 圧 在 圩 圪
          0x572c_5730, //  圬 圭 圮 圯 地
          0x5733_5734, //  圳 圴
          0x5737_5738, //  圷 圸
          0x573b_573b, //  圻
          0x573d_5740, //  圽 圾 圿 址
          0x5742_5742, //  坂
          0x5745_5747, //  坅 坆 均
          0x574a_574a, //  坊
          0x574c_5752, //  坌 坍 坎 坏 坐 坑 坒
          0x5761_5762, //  坡 坢
          0x5764_576b, //  坤 坥 坦 坧 坨 坩 坪 坫
          0x576d_5771, //  坭 坮 坯 坰 坱
          0x5773_5775, //  坳 坴 坵
          0x5777_5777, //  坷
          0x5779_577c, //  坹 坺 坻 坼
          0x577e_577f, //  坾 坿
          0x5781_5783, //  垁 垂 垃
          0x5788_5789, //  垈 垉
          0x578b_578c, //  型 垌
          0x5793_5795, //  垓 垔 垕
          0x5797_5797, //  垗
          0x5799_579a, //  垙 垚
          0x579c_57a4, //  垜 垝 垞 垟 垠 垡 垢 垣 垤
          0x57a7_57aa, //  垧 垨 垩 垪
          0x57ac_57ac, //  垬
          0x57b0_57b0, //  垰
          0x57b3_57b3, //  垳
          0x57b8_57b8, //  垸
          0x57bd_57bd, //  垽
          0x57c0_57c0, //  埀
          0x57c3_57c3, //  埃
          0x57c6_57c8, //  埆 埇 埈
          0x57cb_57cc, //  埋 埌
          0x57ce_57cf, //  城 埏
          0x57d2_57d7, //  埒 埓 埔 埕 埖 埗
          0x57dc_57e1, //  埜 埝 埞 域 埠 埡
          0x57e3_57e4, //  埣 埤
          0x57e6_57e7, //  埦 埧
          0x57e9_57e9, //  埩
          0x57ed_57ed, //  埭
          0x57f0_57f0, //  埰
          0x57f4_5800, //  埴 埵 埶 執 埸 培 基 埻 埼 埽 埾 埿 堀
          0x5802_5806, //  堂 堃 堄 堅 堆
          0x5808_580d, //  堈 堉 堊 堋 堌 堍
          0x5815_5815, //  堕
          0x5819_5819, //  堙
          0x581b_581b, //  堛
          0x581d_5821, //  堝 堞 堟 堠 堡
          0x5824_5824, //  堤
          0x5826_5827, //  堦 堧
          0x582a_582a, //  堪
          0x582d_582d, //  堭
          0x582f_5832, //  堯 堰 報 堲
          0x5834_5835, //  場 堵
          0x5839_583a, //  堹 堺
          0x583d_583d, //  堽
          0x583f_5841, //  堿 塀 塁
          0x5849_584d, //  塉 塊 塋 塌 塍
          0x584f_5852, //  塏 塐 塑 塒
          0x5854_5855, //  塔 塕
          0x5857_585a, //  塗 塘 塙 塚
          0x585e_585f, //  塞 塟
          0x5861_5862, //  塡 塢
          0x5864_5864, //  塤
          0x5867_5869, //  塧 塨 塩
          0x586b_586b, //  填
          0x5870_5870, //  塰
          0x5872_5872, //  塲
          0x5875_5875, //  塵
          0x5878_5879, //  塸 塹
          0x587c_587c, //  塼
          0x587e_5881, //  塾 塿 墀 墁
          0x5883_5883, //  境
          0x5885_5885, //  墅
          0x5887_588d, //  墇 墈 墉 墊 墋 墌 墍
          0x588f_5890, //  墏 墐
          0x5893_5894, //  墓 墔
          0x5896_5897, //  墖 増
          0x589c_58a2, //  墜 墝 增 墟 墠 墡 墢
          0x58a6_58a6, //  墦
          0x58a8_58ab, //  墨 墩 墪 墫
          0x58ae_58ae, //  墮
          0x58b1_58b3, //  墱 墲 墳
          0x58b8_58bc, //  墸 墹 墺 墻 墼
          0x58be_58be, //  墾
          0x58c1_58c5, //  壁 壂 壃 壄 壅
          0x58c7_58c8, //  壇 壈
          0x58ca_58ca, //  壊
          0x58cc_58ce, //  壌 壍 壎
          0x58d0_58da, //  壐 壑 壒 壓 壔 壕 壖 壗 壘 壙 壚
          0x58dc_58e2, //  壜 壝 壞 壟 壠 壡 壢
          0x58e4_58e5, //  壤 壥
          0x58e9_58e9, //  壩
          0x58eb_58ec, //  士 壬
          0x58ee_58f4, //  壮 壯 声 壱 売 壳 壴
          0x58f7_58f7, //  壷
          0x58f9_58fd, //  壹 壺 壻 壼 壽
          0x5902_5902, //  夂
          0x5905_5906, //  夅 夆
          0x5909_590d, //  変 夊 夋 夌 复
          0x590f_5910, //  夏 夐
          0x5912_5916, //  夒 夓 夔 夕 外
          0x5918_591d, //  夘 夙 多 夛 夜 夝
          0x5921_5925, //  夡 夢 夣 夤 夥
          0x5927_5933, //  大 夨 天 太 夫 夬 夭 央 夯 夰 失 夲 夳
          0x5935_5938, //  夵 夶 夷 夸
          0x593d_593f, //  夽 夾 夿
          0x5943_5944, //  奃 奄
          0x5946_5949, //  奆 奇 奈 奉
          0x594e_5955, //  奎 奏 奐 契 奒 奓 奔 奕
          0x5957_595b, //  套 奘 奙 奚 奛
          0x595d_5963, //  奝 奞 奟 奠 奡 奢 奣
          0x5965_5965, //  奥
          0x5967_596f, //  奧 奨 奩 奪 奫 奬 奭 奮 奯
          0x5972_5976, //  奲 女 奴 奵 奶
          0x5978_5979, //  奸 她
          0x597b_597d, //  奻 奼 好
          0x5981_5984, //  妁 如 妃 妄
          0x598a_598e, //  妊 妋 妌 妍 妎
          0x5992_5993, //  妒 妓
          0x5995_5997, //  妕 妖 妗
          0x5999_5999, //  妙
          0x599b_599b, //  妛
          0x599d_599d, //  妝
          0x599f_599f, //  妟
          0x59a3_59a5, //  妣 妤 妥
          0x59a7_59a8, //  妧 妨
          0x59ac_59b0, //  妬 妭 妮 妯 妰
          0x59b2_59b3, //  妲 妳
          0x59b7_59b7, //  妷
          0x59b9_59bc, //  妹 妺 妻 妼
          0x59be_59be, //  妾
          0x59c1_59c1, //  姁
          0x59c3_59c4, //  姃 姄
          0x59c6_59c6, //  姆
          0x59c8_59cb, //  姈 姉 姊 始
          0x59cd_59cd, //  姍
          0x59d0_59d4, //  姐 姑 姒 姓 委
          0x59d9_59da, //  姙 姚
          0x59dc_59df, //  姜 姝 姞 姟
          0x59e3_59e8, //  姣 姤 姥 姦 姧 姨
          0x59ea_59eb, //  姪 姫
          0x59ee_59ef, //  姮 姯
          0x59f1_59f2, //  姱 姲
          0x59f4_59f4, //  姴
          0x59f6_59f8, //  姶 姷 姸
          0x59fb_59fb, //  姻
          0x59ff_5a01, //  姿 娀 威
          0x5a03_5a04, //  娃 娄
          0x5a09_5a09, //  娉
          0x5a0c_5a0e, //  娌 娍 娎
          0x5a11_5a13, //  娑 娒 娓
          0x5a17_5a18, //  娗 娘
          0x5a1a_5a1a, //  娚
          0x5a1c_5a1c, //  娜
          0x5a1e_5a20, //  娞 娟 娠
          0x5a23_5a25, //  娣 娤 娥
          0x5a27_5a2a, //  娧 娨 娩 娪
          0x5a2d_5a2d, //  娭
          0x5a2f_5a30, //  娯 娰
          0x5a35_5a36, //  娵 娶
          0x5a3c_5a3c, //  娼
          0x5a40_5a41, //  婀 婁
          0x5a44_5a49, //  婄 婅 婆 婇 婈 婉
          0x5a4c_5a4c, //  婌
          0x5a50_5a50, //  婐
          0x5a55_5a55, //  婕
          0x5a5a_5a5a, //  婚
          0x5a5e_5a5e, //  婞
          0x5a62_5a63, //  婢 婣
          0x5a65_5a67, //  婥 婦 婧
          0x5a6a_5a6a, //  婪
          0x5a6c_5a6d, //  婬 婭
          0x5a77_5a77, //  婷
          0x5a7a_5a7b, //  婺 婻
          0x5a7e_5a7f, //  婾 婿
          0x5a84_5a84, //  媄
          0x5a8b_5a8b, //  媋
          0x5a90_5a90, //  媐
          0x5a92_5a93, //  媒 媓
          0x5a96_5a96, //  媖
          0x5a99_5a9c, //  媙 媚 媛 媜
          0x5a9e_5aa0, //  媞 媟 媠
          0x5aa2_5aa2, //  媢
          0x5aa7_5aa7, //  媧
          0x5aac_5aac, //  媬
          0x5ab1_5ab3, //  媱 媲 媳
          0x5ab5_5ab5, //  媵
          0x5ab8_5ab8, //  媸
          0x5aba_5abf, //  媺 媻 媼 媽 媾 媿
          0x5ac1_5ac2, //  嫁 嫂
          0x5ac4_5ac4, //  嫄
          0x5ac6_5ac6, //  嫆
          0x5ac8_5ac9, //  嫈 嫉
          0x5acb_5acc, //  嫋 嫌
          0x5acf_5ad0, //  嫏 嫐
          0x5ad6_5ad7, //  嫖 嫗
          0x5ada_5ada, //  嫚
          0x5adc_5adc, //  嫜
          0x5ae0_5ae1, //  嫠 嫡
          0x5ae3_5ae3, //  嫣
          0x5ae5_5ae6, //  嫥 嫦
          0x5ae9_5aea, //  嫩 嫪
          0x5aee_5aee, //  嫮
          0x5af0_5af0, //  嫰
          0x5af5_5af6, //  嫵 嫶
          0x5afa_5afb, //  嫺 嫻
          0x5afd_5afd, //  嫽
          0x5b00_5b01, //  嬀 嬁
          0x5b08_5b09, //  嬈 嬉
          0x5b0b_5b0c, //  嬋 嬌
          0x5b16_5b17, //  嬖 嬗
          0x5b19_5b19, //  嬙
          0x5b1b_5b1b, //  嬛
          0x5b1d_5b1d, //  嬝
          0x5b21_5b22, //  嬡 嬢
          0x5b25_5b25, //  嬥
          0x5b2a_5b2a, //  嬪
          0x5b2c_5b2d, //  嬬 嬭
          0x5b30_5b30, //  嬰
          0x5b32_5b32, //  嬲
          0x5b34_5b34, //  嬴
          0x5b36_5b36, //  嬶
          0x5b38_5b38, //  嬸
          0x5b3e_5b3e, //  嬾
          0x5b40_5b41, //  孀 孁
          0x5b43_5b43, //  孃
          0x5b45_5b45, //  孅
          0x5b4b_5b4c, //  孋 孌
          0x5b50_5b52, //  子 孑 孒
          0x5b54_5b58, //  孔 孕 孖 字 存
          0x5b5a_5b5f, //  孚 孛 孜 孝 孞 孟
          0x5b63_5b66, //  季 孤 孥 学
          0x5b68_5b69, //  孨 孩
          0x5b6b_5b6b, //  孫
          0x5b6e_5b71, //  孮 孯 孰 孱
          0x5b73_5b73, //  孳
          0x5b75_5b75, //  孵
          0x5b78_5b78, //  學
          0x5b7a_5b7a, //  孺
          0x5b7c_5b81, //  孼 孽 孾 孿 宀 宁
          0x5b83_5b91, //  它 宄 宅 宆 宇 守 安 宊 宋 完 宍 宎 宏 宐 宑
          0x5b93_5b9d, //  宓 宔 宕 宖 宗 官 宙 定 宛 宜 宝
          0x5b9f_5b9f, //  実
          0x5ba2_5ba6, //  客 宣 室 宥 宦
          0x5ba8_5ba9, //  宨 宩
          0x5bac_5bba, //  宬 宭 宮 宯 宰 宱 宲 害 宴 宵 家 宷 宸 容 宺
          0x5bbc_5bbc, //  宼
          0x5bbf_5bc7, //  宿 寀 寁 寂 寃 寄 寅 密 寇
          0x5bc9_5bc9, //  寉
          0x5bcc_5bd0, //  富 寍 寎 寏 寐
          0x5bd2_5bd4, //  寒 寓 寔
          0x5bd6_5bdb, //  寖 寗 寘 寙 寚 寛
          0x5bdd_5be2, //  寝 寞 察 寠 寡 寢
          0x5be4_5be9, //  寤 寥 實 寧 寨 審
          0x5beb_5bec, //  寫 寬
          0x5bee_5bf1, //  寮 寯 寰 寱
          0x5bf3_5bf6, //  寳 寴 寵 寶
          0x5bf8_5bf8, //  寸
          0x5bfa_5bfa, //  寺
          0x5bfd_5bff, //  寽 対 寿
          0x5c01_5c0f, //  封 専 尃 射 尅 将 將 專 尉 尊 尋 尌 對 導 小
          0x5c11_5c13, //  少 尒 尓
          0x5c16_5c17, //  尖 尗
          0x5c1a_5c1a, //  尚
          0x5c1e_5c20, //  尞 尟 尠
          0x5c22_5c24, //  尢 尣 尤
          0x5c26_5c26, //  尦
          0x5c28_5c29, //  尨 尩
          0x5c2b_5c2e, //  尫 尬 尭 尮
          0x5c30_5c32, //  尰 就 尲
          0x5c35_5c36, //  尵 尶
          0x5c38_5c41, //  尸 尹 尺 尻 尼 尽 尾 尿 局 屁
          0x5c45_5c46, //  居 屆
          0x5c48_5c48, //  屈
          0x5c4a_5c4b, //  届 屋
          0x5c4d_5c51, //  屍 屎 屏 屐 屑
          0x5c53_5c53, //  屓
          0x5c55_5c55, //  展
          0x5c59_5c5c, //  屙 屚 屛 屜
          0x5c5e_5c65, //  属 屟 屠 屡 屢 屣 層 履
          0x5c67_5c69, //  屧 屨 屩
          0x5c6c_5c71, //  屬 屭 屮 屯 屰 山
          0x5c74_5c76, //  屴 屵 屶
          0x5c79_5c7d, //  屹 屺 屻 屼 屽
          0x5c87_5c88, //  岇 岈
          0x5c8a_5c8a, //  岊
          0x5c8c_5c8c, //  岌
          0x5c8f_5c92, //  岏 岐 岑 岒
          0x5c94_5c94, //  岔
          0x5c9d_5c9d, //  岝
          0x5c9f_5ca3, //  岟 岠 岡 岢 岣
          0x5ca6_5cad, //  岦 岧 岨 岩 岪 岫 岬 岭
          0x5cb1_5cb8, //  岱 岲 岳 岴 岵 岶 岷 岸
          0x5cba_5cbc, //  岺 岻 岼
          0x5cbe_5cbe, //  岾
          0x5cc5_5cc5, //  峅
          0x5cc7_5cc7, //  峇
          0x5cc9_5cc9, //  峉
          0x5ccb_5ccb, //  峋
          0x5cd0_5cd0, //  峐
          0x5cd2_5cd2, //  峒
          0x5cd7_5cd7, //  峗
          0x5cd9_5cd9, //  峙
          0x5cdd_5cdd, //  峝
          0x5ce0_5ce1, //  峠 峡
          0x5ce8_5cea, //  峨 峩 峪
          0x5ced_5cf2, //  峭 峮 峯 峰 峱 峲
          0x5cf4_5cf4, //  峴
          0x5cf6_5cf6, //  島
          0x5cfa_5cfb, //  峺 峻
          0x5cfd_5cfd, //  峽
          0x5d01_5d01, //  崁
          0x5d06_5d07, //  崆 崇
          0x5d0b_5d0b, //  崋
          0x5d0d_5d0e, //  崍 崎
          0x5d10_5d12, //  崐 崑 崒
          0x5d14_5d1b, //  崔 崕 崖 崗 崘 崙 崚 崛
          0x5d1d_5d1d, //  崝
          0x5d1f_5d20, //  崟 崠
          0x5d22_5d24, //  崢 崣 崤
          0x5d26_5d27, //  崦 崧
          0x5d29_5d29, //  崩
          0x5d2b_5d2b, //  崫
          0x5d31_5d31, //  崱
          0x5d34_5d34, //  崴
          0x5d39_5d39, //  崹
          0x5d3d_5d3d, //  崽
          0x5d3f_5d3f, //  崿
          0x5d42_5d43, //  嵂 嵃
          0x5d46_5d48, //  嵆 嵇 嵈
          0x5d4a_5d4c, //  嵊 嵋 嵌
          0x5d4e_5d4e, //  嵎
          0x5d50_5d53, //  嵐 嵑 嵒 嵓
          0x5d55_5d55, //  嵕
          0x5d59_5d59, //  嵙
          0x5d5c_5d5c, //  嵜
          0x5d5f_5d62, //  嵟 嵠 嵡 嵢
          0x5d64_5d64, //  嵤
          0x5d69_5d6a, //  嵩 嵪
          0x5d6c_5d6d, //  嵬 嵭
          0x5d6f_5d70, //  嵯 嵰
          0x5d73_5d73, //  嵳
          0x5d76_5d76, //  嵶
          0x5d79_5d7a, //  嵹 嵺
          0x5d7e_5d7f, //  嵾 嵿
          0x5d81_5d84, //  嶁 嶂 嶃 嶄
          0x5d87_5d88, //  嶇 嶈
          0x5d8a_5d8c, //  嶊 嶋 嶌
          0x5d90_5d90, //  嶐
          0x5d92_5d95, //  嶒 嶓 嶔 嶕
          0x5d97_5d97, //  嶗
          0x5d99_5d99, //  嶙
          0x5d9b_5d9b, //  嶛
          0x5d9d_5d9d, //  嶝
          0x5d9f_5da0, //  嶟 嶠
          0x5da2_5da2, //  嶢
          0x5da4_5da4, //  嶤
          0x5da7_5da7, //  嶧
          0x5dab_5dac, //  嶫 嶬
          0x5dae_5dae, //  嶮
          0x5db0_5db0, //  嶰
          0x5db2_5db2, //  嶲
          0x5db4_5db4, //  嶴
          0x5db7_5dba, //  嶷 嶸 嶹 嶺
          0x5dbc_5dbd, //  嶼 嶽
          0x5dc3_5dc3, //  巃
          0x5dc7_5dc7, //  巇
          0x5dc9_5dc9, //  巉
          0x5dcb_5dce, //  巋 巌 巍 巎
          0x5dd0_5dd3, //  巐 巑 巒 巓
          0x5dd6_5dd9, //  巖 巗 巘 巙
          0x5ddb_5ddb, //  巛
          0x5ddd_5dde, //  川 州
          0x5de0_5de9, //  巠 巡 巢 巣 巤 工 左 巧 巨 巩
          0x5deb_5deb, //  巫
          0x5dee_5dee, //  差
          0x5df1_5df5, //  己 已 巳 巴 巵
          0x5df7_5df9, //  巷 巸 巹
          0x5dfb_5dfb, //  巻
          0x5dfd_5dfe, //  巽 巾
          0x5e00_5e00, //  帀
          0x5e02_5e03, //  市 布
          0x5e06_5e07, //  帆 帇
          0x5e0b_5e0d, //  帋 希 帍
          0x5e11_5e12, //  帑 帒
          0x5e14_5e16, //  帔 帕 帖
          0x5e18_5e1b, //  帘 帙 帚 帛
          0x5e1d_5e1d, //  帝
          0x5e1f_5e20, //  帟 帠
          0x5e25_5e25, //  帥
          0x5e28_5e28, //  帨
          0x5e2b_5e2b, //  師
          0x5e2d_5e30, //  席 帮 帯 帰
          0x5e32_5e33, //  帲 帳
          0x5e35_5e38, //  帵 帶 帷 常
          0x5e3d_5e3e, //  帽 帾
          0x5e40_5e40, //  幀
          0x5e43_5e45, //  幃 幄 幅
          0x5e47_5e47, //  幇
          0x5e49_5e49, //  幉
          0x5e4b_5e4c, //  幋 幌
          0x5e4e_5e4e, //  幎
          0x5e50_5e51, //  幐 幑
          0x5e54_5e58, //  幔 幕 幖 幗 幘
          0x5e5b_5e5c, //  幛 幜
          0x5e5e_5e5f, //  幞 幟
          0x5e61_5e64, //  幡 幢 幣 幤
          0x5e68_5e68, //  幨
          0x5e6a_5e6e, //  幪 幫 幬 幭 幮
          0x5e70_5e70, //  幰
          0x5e72_5e81, //  干 平 年 幵 并 幷 幸 幹 幺 幻 幼 幽 幾 广 庀 庁
          0x5e83_5e84, //  広 庄
          0x5e87_5e87, //  庇
          0x5e8a_5e8b, //  床 庋
          0x5e8e_5e8f, //  庎 序
          0x5e95_5e97, //  底 庖 店
          0x5e9a_5e9a, //  庚
          0x5e9c_5e9c, //  府
          0x5ea0_5ea0, //  庠
          0x5ea2_5ea2, //  庢
          0x5ea4_5ea8, //  庤 庥 度 座 庨
          0x5eaa_5ead, //  庪 庫 庬 庭
          0x5eb1_5eb1, //  庱
          0x5eb3_5eb3, //  庳
          0x5eb5_5eb9, //  庵 庶 康 庸 庹
          0x5ebd_5ebf, //  庽 庾 庿
          0x5ec1_5ec3, //  廁 廂 廃
          0x5ec6_5ec6, //  廆
          0x5ec8_5ecc, //  廈 廉 廊 廋 廌
          0x5ece_5ed6, //  廎 廏 廐 廑 廒 廓 廔 廕 廖
          0x5ed9_5ee3, //  廙 廚 廛 廜 廝 廞 廟 廠 廡 廢 廣
          0x5ee5_5ee5, //  廥
          0x5ee8_5ee9, //  廨 廩
          0x5eeb_5eec, //  廫 廬
          0x5ef0_5ef1, //  廰 廱
          0x5ef3_5ef4, //  廳 廴
          0x5ef6_5f04, //  延 廷 廸 廹 建 廻 廼 廽 廾 廿 开 弁 异 弃 弄
          0x5f06_5f11, //  弆 弇 弈 弉 弊 弋 弌 弍 弎 式 弐 弑
          0x5f13_5f19, //  弓 弔 引 弖 弗 弘 弙
          0x5f1b_5f1f, //  弛 弜 弝 弞 弟
          0x5f21_5f29, //  弡 弢 弣 弤 弥 弦 弧 弨 弩
          0x5f2b_5f31, //  弫 弬 弭 弮 弯 弰 弱
          0x5f34_5f38, //  弴 張 弶 強 弸
          0x5f3b_5f41, //  弻 弼 弽 弾 弿 彀 彁
          0x5f44_5f45, //  彄 彅
          0x5f47_5f48, //  彇 彈
          0x5f4a_5f4a, //  彊
          0x5f4c_5f4e, //  彌 彍 彎
          0x5f50_5f51, //  彐 彑
          0x5f53_5f54, //  当 彔
          0x5f56_5f59, //  彖 彗 彘 彙
          0x5f5b_5f5d, //  彛 彜 彝
          0x5f60_5f64, //  彠 彡 形 彣 彤
          0x5f66_5f67, //  彦 彧
          0x5f69_5f6d, //  彩 彪 彫 彬 彭
          0x5f6f_5f75, //  彯 彰 影 彲 彳 彴 彵
          0x5f77_5f7a, //  彷 彸 役 彺
          0x5f7c_5f85, //  彼 彽 彾 彿 往 征 徂 徃 径 待
          0x5f87_5f8d, //  徇 很 徉 徊 律 後 徍
          0x5f8f_5f93, //  徏 徐 徑 徒 従
          0x5f96_5f99, //  徖 得 徘 徙
          0x5f9c_5f9e, //  徜 徝 從
          0x5fa0_5fa2, //  徠 御 徢
          0x5fa4_5fa4, //  徤
          0x5fa7_5fb1, //  徧 徨 復 循 徫 徬 徭 微 徯 徰 徱
          0x5fb3_5fb5, //  徳 徴 徵
          0x5fb7_5fb9, //  德 徸 徹
          0x5fbc_5fbd, //  徼 徽
          0x5fc3_5fc5, //  心 忄 必
          0x5fc7_5fc9, //  忇 忈 忉
          0x5fcb_5fcd, //  忋 忌 忍
          0x5fd0_5fd4, //  忐 忑 忒 忓 忔
          0x5fd6_5fd9, //  忖 志 忘 忙
          0x5fdc_5fde, //  応 忝 忞
          0x5fe0_5fe2, //  忠 忡 忢
          0x5fe4_5fe4, //  忤
          0x5fe8_5ff3, //  忨 忩 忪 快 忬 忭 忮 忯 忰 忱 忲 忳
          0x5ff5_5ff6, //  念 忶
          0x5ff8_5ff8, //  忸
          0x5ffa_5ffd, //  忺 忻 忼 忽
          0x5fff_5fff, //  忿
          0x6007_6007, //  怇
          0x600a_600a, //  怊
          0x600d_6010, //  怍 怎 怏 怐
          0x6012_601d, //  怒 怓 怔 怕 怖 怗 怘 怙 怚 怛 怜 思
          0x601f_6022, //  怟 怠 怡 怢
          0x6024_602b, //  怤 急 怦 性 怨 怩 怪 怫
          0x602d_602d, //  怭
          0x602f_602f, //  怯
          0x6031_6031, //  怱
          0x6033_6033, //  怳
          0x6035_6035, //  怵
          0x603a_603a, //  怺
          0x6040_6043, //  恀 恁 恂 恃
          0x6046_604d, //  恆 恇 恈 恉 恊 恋 恌 恍
          0x6050_6052, //  恐 恑 恒
          0x6054_6057, //  恔 恕 恖 恗
          0x6059_605a, //  恙 恚
          0x605d_605d, //  恝
          0x605f_6065, //  恟 恠 恡 恢 恣 恤 恥
          0x6067_606d, //  恧 恨 恩 恪 恫 恬 恭
          0x606f_6071, //  息 恰 恱
          0x6075_6075, //  恵
          0x6077_6077, //  恷
          0x607e_607f, //  恾 恿
          0x6081_6084, //  悁 悂 悃 悄
          0x6086_6086, //  悆
          0x6088_608e, //  悈 悉 悊 悋 悌 悍 悎
          0x6091_6098, //  悑 悒 悓 悔 悕 悖 悗 悘
          0x609a_609b, //  悚 悛
          0x609d_60a0, //  悝 悞 悟 悠
          0x60a2_60aa, //  悢 患 悤 悥 悦 悧 您 悩 悪
          0x60b0_60b8, //  悰 悱 悲 悳 悴 悵 悶 悷 悸
          0x60bb_60be, //  悻 悼 悽 悾
          0x60c2_60c2, //  惂
          0x60c4_60cb, //  惄 情 惆 惇 惈 惉 惊 惋
          0x60ce_60cf, //  惎 惏
          0x60d1_60d1, //  惑
          0x60d3_60d5, //  惓 惔 惕
          0x60d8_60e3, //  惘 惙 惚 惛 惜 惝 惞 惟 惠 惡 惢 惣
          0x60e5_60e5, //  惥
          0x60e7_60e8, //  惧 惨
          0x60ee_60ee, //  惮
          0x60f0_60fd, //  惰 惱 惲 想 惴 惵 惶 惷 惸 惹 惺 惻 惼 惽
          0x6100_6103, //  愀 愁 愂 愃
          0x6106_610a, //  愆 愇 愈 愉 愊
          0x610c_6117, //  愌 愍 愎 意 愐 愑 愒 愓 愔 愕 愖 愗
          0x6119_611c, //  愙 愚 愛 愜
          0x611e_611f, //  愞 感
          0x6121_6122, //  愡 愢
          0x6127_6128, //  愧 愨
          0x612a_612c, //  愪 愫 愬
          0x6130_6131, //  愰 愱
          0x6134_6137, //  愴 愵 愶 愷
          0x6139_613a, //  愹 愺
          0x613c_613f, //  愼 愽 愾 愿
          0x6141_6142, //  慁 慂
          0x6144_614e, //  慄 慅 慆 慇 慈 慉 慊 態 慌 慍 慎
          0x6153_6153, //  慓
          0x6155_6155, //  慕
          0x6158_615a, //  慘 慙 慚
          0x615d_6160, //  慝 慞 慟 慠
          0x6162_6163, //  慢 慣
          0x6165_6165, //  慥
          0x6167_6168, //  慧 慨
          0x616b_616c, //  慫 慬
          0x616e_6178, //  慮 慯 慰 慱 慲 慳 慴 慵 慶 慷 慸
          0x617b_617c, //  慻 慼
          0x617e_6184, //  慾 慿 憀 憁 憂 憃 憄
          0x6187_6187, //  憇
          0x618a_618b, //  憊 憋
          0x618d_618e, //  憍 憎
          0x6190_6194, //  憐 憑 憒 憓 憔
          0x6196_619a, //  憖 憗 憘 憙 憚
          0x619c_619d, //  憜 憝
          0x619f_61a0, //  憟 憠
          0x61a4_61a5, //  憤 憥
          0x61a7_61ae, //  憧 憨 憩 憪 憫 憬 憭 憮
          0x61b2_61b2, //  憲
          0x61b6_61b6, //  憶
          0x61b8_61ba, //  憸 憹 憺
          0x61bc_61bc, //  憼
          0x61be_61be, //  憾
          0x61c0_61c3, //  懀 懁 懂 懃
          0x61c6_61d0, //  懆 懇 懈 應 懊 懋 懌 懍 懎 懏 懐
          0x61d5_61d5, //  懕
          0x61dc_61df, //  懜 懝 懞 懟
          0x61e1_61e3, //  懡 懢 懣
          0x61e5_61e7, //  懥 懦 懧
          0x61e9_61e9, //  懩
          0x61ec_61ed, //  懬 懭
          0x61ef_61ef, //  懯
          0x61f2_61f2, //  懲
          0x61f4_61f8, //  懴 懵 懶 懷 懸
          0x61fa_61fa, //  懺
          0x61fc_6201, //  懼 懽 懾 懿 戀 戁
          0x6203_6204, //  戃 戄
          0x6207_620a, //  戇 戈 戉 戊
          0x620c_620e, //  戌 戍 戎
          0x6210_6216, //  成 我 戒 戓 戔 戕 或
          0x621a_6223, //  戚 戛 戜 戝 戞 戟 戠 戡 戢 戣
          0x6226_6227, //  戦 戧
          0x6229_622b, //  戩 截 戫
          0x622e_6230, //  戮 戯 戰
          0x6232_6234, //  戲 戳 戴
          0x6238_6239, //  戸 戹
          0x623b_623b, //  戻
          0x623d_6244, //  戽 戾 房 所 扁 扂 扃 扄
          0x6246_6249, //  扆 扇 扈 扉
          0x624b_624e, //  手 扌 才 扎
          0x6250_6256, //  扐 扑 扒 打 扔 払 扖
          0x6258_6258, //  托
          0x625a_625c, //  扚 扛 扜
          0x625e_625e, //  扞
          0x6260_6261, //  扠 扡
          0x6263_6264, //  扣 扤
          0x6268_6268, //  扨
          0x626d_626f, //  扭 扮 扯
          0x6271_6271, //  扱
          0x6273_6273, //  扳
          0x6276_6276, //  扶
          0x6279_6280, //  批 扺 扻 扼 扽 找 承 技
          0x6282_6285, //  抂 抃 抄 抅
          0x6289_628a, //  抉 把
          0x628d_6299, //  抍 抎 抏 抐 抑 抒 抓 抔 投 抖 抗 折 抙
          0x629b_629c, //  抛 抜
          0x629e_629e, //  択
          0x62a6_62a6, //  抦
          0x62a8_62a8, //  抨
          0x62ab_62ac, //  披 抬
          0x62b1_62b1, //  抱
          0x62b3_62b3, //  抳
          0x62b5_62b7, //  抵 抶 抷
          0x62b9_62bf, //  抹 抺 抻 押 抽 抾 抿
          0x62c2_62c2, //  拂
          0x62c4_62ca, //  拄 担 拆 拇 拈 拉 拊
          0x62cc_62dd, //  拌 拍 拎 拏 拐 拑 拒 拓 拔 拕 拖 拗 拘 拙 拚 招 拜 拝
          0x62e0_62e1, //  拠 拡
          0x62ea_62ea, //  拪
          0x62ec_62ef, //  括 拭 拮 拯
          0x62f1_62f7, //  拱 拲 拳 拴 拵 拶 拷
          0x62fc_62ff, //  拼 拽 拾 拿
          0x6301_6304, //  持 挂 挃 挄
          0x6307_630d, //  指 挈 按 挊 挋 挌 挍
          0x6310_6311, //  挐 挑
          0x6313_6313, //  挓
          0x6316_6316, //  挖
          0x6318_6319, //  挘 挙
          0x631f_631f, //  挟
          0x6327_632b, //  挧 挨 挩 挪 挫
          0x632d_632d, //  挭
          0x632f_632f, //  振
          0x6332_6332, //  挲
          0x6335_6336, //  挵 挶
          0x6339_633f, //  挹 挺 挻 挼 挽 挾 挿
          0x6341_6344, //  捁 捂 捃 捄
          0x6346_6346, //  捆
          0x6349_6350, //  捉 捊 捋 捌 捍 捎 捏 捐
          0x6352_6355, //  捒 捓 捔 捕
          0x6357_6359, //  捗 捘 捙
          0x635b_635c, //  捛 捜
          0x6365_6369, //  捥 捦 捧 捨 捩
          0x636b_636e, //  捫 捬 捭 据
          0x6371_6372, //  捱 捲
          0x6374_6378, //  捴 捵 捶 捷 捸
          0x637a_637d, //  捺 捻 捼 捽
          0x637f_6380, //  捿 掀
          0x6382_6384, //  掂 掃 掄
          0x6387_638a, //  掇 授 掉 掊
          0x638c_638c, //  掌
          0x638e_6390, //  掎 掏 掐
          0x6392_6392, //  排
          0x6394_6396, //  掔 掕 掖
          0x6398_639b, //  掘 掙 掚 掛
          0x639e_63af, //  掞 掟 掠 採 探 掣 掤 接 掦 控 推 掩 措 掫 掬 掭 掮 掯
          0x63b2_63b2, //  掲
          0x63b4_63b5, //  掴 掵
          0x63bb_63bb, //  掻
          0x63bd_63be, //  掽 掾
          0x63c0_63c1, //  揀 揁
          0x63c3_63c6, //  揃 揄 揅 揆
          0x63c8_63c9, //  揈 揉
          0x63ce_63d6, //  揎 描 提 揑 插 揓 揔 揕 揖
          0x63da_63dc, //  揚 換 揜
          0x63e0_63e1, //  揠 握
          0x63e3_63e3, //  揣
          0x63e5_63e5, //  揥
          0x63e9_63ee, //  揩 揪 揫 揬 揭 揮
          0x63f2_63fa, //  揲 揳 援 揵 揶 揷 揸 揹 揺
          0x6406_6406, //  搆
          0x6409_640a, //  搉 搊
          0x640d_640d, //  損
          0x640f_6410, //  搏 搐
          0x6412_6414, //  搒 搓 搔
          0x6416_6418, //  搖 搗 搘
          0x641c_641c, //  搜
          0x641e_641e, //  搞
          0x6420_6420, //  搠
          0x6422_6422, //  搢
          0x6424_6426, //  搤 搥 搦
          0x6428_642a, //  搨 搩 搪
          0x642c_642d, //  搬 搭
          0x642f_6430, //  搯 搰
          0x6434_6436, //  搴 搵 搶
          0x643a_643a, //  携
          0x643d_643f, //  搽 搾 搿
          0x6442_6442, //  摂
          0x644b_644b, //  摋
          0x644e_644f, //  摎 摏
          0x6451_6454, //  摑 摒 摓 摔
          0x6458_6458, //  摘
          0x645a_645d, //  摚 摛 摜 摝
          0x645f_6461, //  摟 摠 摡
          0x6463_6463, //  摣
          0x6467_6467, //  摧
          0x6469_6469, //  摩
          0x646d_646d, //  摭
          0x646f_646f, //  摯
          0x6473_6474, //  摳 摴
          0x6476_6476, //  摶
          0x6478_647b, //  摸 摹 摺 摻
          0x647d_647d, //  摽
          0x6483_6483, //  撃
          0x6485_6485, //  撅
          0x6487_6488, //  撇 撈
          0x648f_6493, //  撏 撐 撑 撒 撓
          0x6495_6495, //  撕
          0x6498_649b, //  撘 撙 撚 撛
          0x649d_649f, //  撝 撞 撟
          0x64a1_64a1, //  撡
          0x64a3_64a6, //  撣 撤 撥 撦
          0x64a8_64a9, //  撨 撩
          0x64ab_64ae, //  撫 撬 播 撮
          0x64b0_64b0, //  撰
          0x64b2_64b3, //  撲 撳
          0x64b9_64b9, //  撹
          0x64bb_64bf, //  撻 撼 撽 撾 撿
          0x64c1_64c2, //  擁 擂
          0x64c4_64c5, //  擄 擅
          0x64c7_64c7, //  擇
          0x64c9_64ce, //  擉 擊 擋 擌 操 擎
          0x64d0_64d2, //  擐 擑 擒
          0x64d4_64d5, //  擔 擕
          0x64d7_64d8, //  擗 擘
          0x64da_64da, //  據
          0x64e0_64e7, //  擠 擡 擢 擣 擤 擥 擦 擧
          0x64e9_64ea, //  擩 擪
          0x64ec_64ed, //  擬 擭
          0x64ef_64f2, //  擯 擰 擱 擲
          0x64f4_64f7, //  擴 擵 擶 擷
          0x64fa_64fb, //  擺 擻
          0x64fd_6501, //  擽 擾 擿 攀 攁
          0x6504_6505, //  攄 攅
          0x6508_650a, //  攈 攉 攊
          0x650f_650f, //  攏
          0x6513_6514, //  攓 攔
          0x6516_6516, //  攖
          0x6518_6519, //  攘 攙
          0x651b_651f, //  攛 攜 攝 攞 攟
          0x6522_6524, //  攢 攣 攤
          0x6526_6526, //  攦
          0x6529_652c, //  攩 攪 攫 攬
          0x652e_652f, //  攮 支
          0x6531_6532, //  攱 攲
          0x6534_653f, //  攴 攵 收 攷 攸 改 攺 攻 攼 攽 放 政
          0x6543_6545, //  敃 敄 故
          0x6547_6549, //  敇 效 敉
          0x654d_654d, //  敍
          0x654f_6552, //  敏 敐 救 敒
          0x6554_6559, //  敔 敕 敖 敗 敘 教
          0x655d_6560, //  敝 敞 敟 敠
          0x6562_6563, //  敢 散
          0x6566_6567, //  敦 敧
          0x656b_656c, //  敫 敬
          0x6570_6570, //  数
          0x6572_6572, //  敲
          0x6574_6575, //  整 敵
          0x6577_6578, //  敷 數
          0x657a_657a, //  敺
          0x657d_657d, //  敽
          0x6581_6585, //  斁 斂 斃 斄 斅
          0x6587_658a, //  文 斈 斉 斊
          0x658c_658c, //  斌
          0x658e_658e, //  斎
          0x6590_6592, //  斐 斑 斒
          0x6595_6595, //  斕
          0x6597_6599, //  斗 斘 料
          0x659b_659d, //  斛 斜 斝
          0x659f_65a1, //  斟 斠 斡
          0x65a3_65a7, //  斣 斤 斥 斦 斧
          0x65ab_65b0, //  斫 斬 断 斮 斯 新
          0x65b2_65b5, //  斲 斳 斴 斵
          0x65b7_65b9, //  斷 斸 方
          0x65bc_65bd, //  於 施
          0x65bf_65bf, //  斿
          0x65c1_65c6, //  旁 旂 旃 旄 旅 旆
          0x65c8_65c9, //  旈 旉
          0x65cb_65cc, //  旋 旌
          0x65ce_65d0, //  旎 族 旐
          0x65d2_65d2, //  旒
          0x65d4_65d4, //  旔
          0x65d6_65d9, //  旖 旗 旘 旙
          0x65db_65db, //  旛
          0x65df_65e2, //  旟 无 旡 既
          0x65e5_65e9, //  日 旦 旧 旨 早
          0x65ec_65ed, //  旬 旭
          0x65f0_65f2, //  旰 旱 旲
          0x65f4_65f5, //  旴 旵
          0x65f9_65fc, //  旹 旺 旻 旼
          0x65fe_6600, //  旾 旿 昀
          0x6602_6604, //  昂 昃 昄
          0x6606_660a, //  昆 昇 昈 昉 昊
          0x660c_660f, //  昌 昍 明 昏
          0x6611_6616, //  昑 昒 易 昔 昕 昖
          0x661c_6631, //  昜 昝 昞 星 映 昡 昢 昣 昤 春 昦 昧 昨 昩 昪 昫 昬 昭 昮 是 昰 昱
          0x6633_6637, //  昳 昴 昵 昶 昷
          0x6639_663a, //  昹 昺
          0x663c_663c, //  昼
          0x663f_6646, //  昿 晀 晁 時 晃 晄 晅 晆
          0x6648_664c, //  晈 晉 晊 晋 晌
          0x664e_664f, //  晎 晏
          0x6651_6652, //  晑 晒
          0x6657_6670, //  晗 晘 晙 晚 晛 晜 晝 晞 晟 晠 晡 晢 晣 晤 晥 晦 晧 晨 晩 晪 晫 晬 晭 普 景 晰
          0x6673_667c, //  晳 晴 晵 晶 晷 晸 晹 智 晻 晼
          0x667e_6681, //  晾 晿 暀 暁
          0x6683_6684, //  暃 暄
          0x6687_6689, //  暇 暈 暉
          0x668b_668e, //  暋 暌 暍 暎
          0x6690_6692, //  暐 暑 暒
          0x6696_669d, //  暖 暗 暘 暙 暚 暛 暜 暝
          0x669f_66a0, //  暟 暠
          0x66a2_66a2, //  暢
          0x66a4_66a4, //  暤
          0x66a6_66a6, //  暦
          0x66ab_66ab, //  暫
          0x66ad_66ae, //  暭 暮
          0x66b1_66b2, //  暱 暲
          0x66b4_66b5, //  暴 暵
          0x66b8_66b9, //  暸 暹
          0x66bb_66bc, //  暻 暼
          0x66be_66c4, //  暾 暿 曀 曁 曂 曃 曄
          0x66c6_66c9, //  曆 曇 曈 曉
          0x66cc_66cc, //  曌
          0x66ce_66cf, //  曎 曏
          0x66d4_66d4, //  曔
          0x66d6_66d6, //  曖
          0x66d9_66dd, //  曙 曚 曛 曜 曝
          0x66df_66e0, //  曟 曠
          0x66e6_66e6, //  曦
          0x66e8_66e9, //  曨 曩
          0x66eb_66ec, //  曫 曬
          0x66ee_66ee, //  曮
          0x66f0_66f0, //  曰
          0x66f2_66f5, //  曲 曳 更 曵
          0x66f7_6701, //  曷 書 曹 曺 曻 曼 曽 曾 替 最 朁
          0x6703_6703, //  會
          0x6705_6705, //  朅
          0x6707_6709, //  朇 月 有
          0x670b_670b, //  朋
          0x670d_670f, //  服 朎 朏
          0x6712_6717, //  朒 朓 朔 朕 朖 朗
          0x6719_6719, //  朙
          0x671b_6720, //  望 朜 朝 朞 期 朠
          0x6722_6722, //  朢
          0x6726_6728, //  朦 朧 木
          0x672a_672e, //  未 末 本 札 朮
          0x6731_6731, //  朱
          0x6733_6734, //  朳 朴
          0x6736_6738, //  朶 朷 朸
          0x673a_673a, //  机
          0x673d_673f, //  朽 朾 朿
          0x6741_6741, //  杁
          0x6745_6749, //  杅 杆 杇 杈 杉
          0x674c_6751, //  杌 杍 李 杏 材 村
          0x6753_6756, //  杓 杔 杕 杖
          0x6759_6759, //  杙
          0x675c_6766, //  杜 杝 杞 束 杠 条 杢 杣 杤 来 杦
          0x676a_676a, //  杪
          0x676c_6777, //  杬 杭 杮 杯 杰 東 杲 杳 杴 杵 杶 杷
          0x677b_677c, //  杻 杼
          0x677e_677f, //  松 板
          0x6781_6781, //  极
          0x6784_6785, //  构 枅
          0x6787_6787, //  枇
          0x6789_6789, //  枉
          0x678b_678c, //  枋 枌
          0x678e_6793, //  枎 枏 析 枑 枒 枓
          0x6795_679d, //  枕 枖 林 枘 枙 枚 枛 果 枝
          0x67a0_67a2, //  枠 枡 枢
          0x67a6_67a6, //  枦
          0x67a9_67a9, //  枩
          0x67af_67b9, //  枯 枰 枱 枲 枳 枴 枵 架 枷 枸 枹
          0x67bb_67bd, //  枻 枼 枽
          0x67c0_67c6, //  柀 柁 柂 柃 柄 柅 柆
          0x67c8_67ca, //  柈 柉 柊
          0x67ce_67d4, //  柎 柏 某 柑 柒 染 柔
          0x67d7_67de, //  柗 柘 柙 柚 柛 柜 柝 柞
          0x67e1_67e2, //  柡 柢
          0x67e4_67e4, //  柤
          0x67e6_67e7, //  柦 柧
          0x67e9_67e9, //  柩
          0x67ec_67ec, //  柬
          0x67ee_67f7, //  柮 柯 柰 柱 柲 柳 柴 柵 柶 柷
          0x67f9_67f9, //  柹
          0x67fb_67fc, //  査 柼
          0x67fe_67ff, //  柾 柿
          0x6801_6804, //  栁 栂 栃 栄
          0x6810_6810, //  栐
          0x6813_6814, //  栓 栔
          0x6816_6819, //  栖 栗 栘 栙
          0x681d_681f, //  栝 栞 栟
          0x6821_6822, //  校 栢
          0x6827_682d, //  栧 栨 栩 株 栫 栬 栭
          0x682f_6834, //  栯 栰 栱 栲 栳 栴
          0x6838_6839, //  核 根
          0x683b_6846, //  栻 格 栽 栾 栿 桀 桁 桂 桃 桄 桅 框
          0x6848_684a, //  案 桉 桊
          0x684c_684e, //  桌 桍 桎
          0x6850_6855, //  桐 桑 桒 桓 桔 桕
          0x6857_6859, //  桗 桘 桙
          0x685b_685d, //  桛 桜 桝
          0x685f_685f, //  桟
          0x6863_6863, //  档
          0x6867_6867, //  桧
          0x686b_686b, //  桫
          0x686e_6872, //  桮 桯 桰 桱 桲
          0x6874_6877, //  桴 桵 桶 桷
          0x6879_687c, //  桹 桺 桻 桼
          0x687e_687f, //  桾 桿
          0x6881_6886, //  梁 梂 梃 梄 梅 梆
          0x6888_6888, //  梈
          0x688d_688d, //  梍
          0x688f_6890, //  梏 梐
          0x6893_6894, //  梓 梔
          0x6896_689d, //  梖 梗 梘 梙 梚 梛 梜 條
          0x689f_68a3, //  梟 梠 梡 梢 梣
          0x68a5_68ab, //  梥 梦 梧 梨 梩 梪 梫
          0x68ad_68b6, //  梭 梮 梯 械 梱 梲 梳 梴 梵 梶
          0x68b9_68bc, //  梹 梺 梻 梼
          0x68c3_68c6, //  棃 棄 棅 棆
          0x68c8_68cd, //  棈 棉 棊 棋 棌 棍
          0x68cf_68da, //  棏 棐 棑 棒 棓 棔 棕 棖 棗 棘 棙 棚
          0x68dc_68dd, //  棜 棝
          0x68df_68e1, //  棟 棠 棡
          0x68e3_68e5, //  棣 棤 棥
          0x68e7_68e8, //  棧 棨
          0x68ea_68f2, //  棪 棫 棬 棭 森 棯 棰 棱 棲
          0x68f5_68f7, //  棵 棶 棷
          0x68f9_68fd, //  棹 棺 棻 棼 棽
          0x6900_6901, //  椀 椁
          0x6903_6913, //  椃 椄 椅 椆 椇 椈 椉 椊 椋 椌 植 椎 椏 椐 椑 椒 椓
          0x6916_6917, //  椖 椗
          0x6919_691c, //  椙 椚 椛 検
          0x6921_6923, //  椡 椢 椣
          0x6925_6926, //  椥 椦
          0x6928_6928, //  椨
          0x692a_692a, //  椪
          0x6930_6931, //  椰 椱
          0x6933_6936, //  椳 椴 椵 椶
          0x6938_6939, //  椸 椹
          0x693b_693b, //  椻
          0x693d_693d, //  椽
          0x693f_693f, //  椿
          0x6942_6942, //  楂
          0x6945_6946, //  楅 楆
          0x6949_694a, //  楉 楊
          0x694e_694e, //  楎
          0x6953_6955, //  楓 楔 楕
          0x6957_6957, //  楗
          0x6959_695e, //  楙 楚 楛 楜 楝 楞
          0x6960_6966, //  楠 楡 楢 楣 楤 楥 楦
          0x6968_6975, //  楨 楩 楪 楫 楬 業 楮 楯 楰 楱 楲 楳 楴 極
          0x6977_6982, //  楷 楸 楹 楺 楻 楼 楽 楾 楿 榀 榁 概
          0x698a_698a, //  榊
          0x698d_698e, //  榍 榎
          0x6991_6992, //  榑 榒
          0x6994_6996, //  榔 榕 榖
          0x6998_6998, //  榘
          0x699b_699c, //  榛 榜
          0x69a0_69a1, //  榠 榡
          0x69a5_69a8, //  榥 榦 榧 榨
          0x69ab_69ab, //  榫
          0x69ad_69b2, //  榭 榮 榯 榰 榱 榲
          0x69b4_69b4, //  榴
          0x69b7_69b8, //  榷 榸
          0x69ba_69bc, //  榺 榻 榼
          0x69be_69c1, //  榾 榿 槀 槁
          0x69c3_69c3, //  槃
          0x69c5_69c5, //  槅
          0x69c7_69c8, //  槇 槈
          0x69ca_69d1, //  槊 構 槌 槍 槎 槏 槐 槑
          0x69d3_69d3, //  槓
          0x69d6_69d9, //  槖 槗 様 槙
          0x69dd_69de, //  槝 槞
          0x69e2_69e3, //  槢 槣
          0x69e5_69e5, //  槥
          0x69e7_69eb, //  槧 槨 槩 槪 槫
          0x69ed_69ef, //  槭 槮 槯
          0x69f1_69f6, //  槱 槲 槳 槴 槵 槶
          0x69f9_69f9, //  槹
          0x69fb_69fb, //  槻
          0x69fd_6a03, //  槽 槾 槿 樀 樁 樂 樃
          0x6a05_6a05, //  樅
          0x6a0a_6a0c, //  樊 樋 樌
          0x6a0f_6a0f, //  樏
          0x6a11_6a15, //  樑 樒 樓 樔 樕
          0x6a17_6a17, //  樗
          0x6a19_6a1b, //  標 樚 樛
          0x6a1d_6a24, //  樝 樞 樟 樠 模 樢 樣 樤
          0x6a28_6a2b, //  樨 権 横 樫
          0x6a2e_6a2e, //  樮
          0x6a30_6a30, //  樰
          0x6a32_6a3b, //  樲 樳 樴 樵 樶 樷 樸 樹 樺 樻
          0x6a3d_6a3f, //  樽 樾 樿
          0x6a44_6a4b, //  橄 橅 橆 橇 橈 橉 橊 橋
          0x6a4e_6a4e, //  橎
          0x6a50_6a52, //  橐 橑 橒
          0x6a55_6a56, //  橕 橖
          0x6a58_6a59, //  橘 橙
          0x6a5b_6a5b, //  橛
          0x6a5f_6a5f, //  機
          0x6a61_6a62, //  橡 橢
          0x6a64_6a64, //  橤
          0x6a66_6a67, //  橦 橧
          0x6a6a_6a6b, //  橪 橫
          0x6a71_6a73, //  橱 橲 橳
          0x6a78_6a78, //  橸
          0x6a7a_6a7a, //  橺
          0x6a7e_6a81, //  橾 橿 檀 檁
          0x6a83_6a84, //  檃 檄
          0x6a86_6a87, //  檆 檇
          0x6a89_6a89, //  檉
          0x6a8b_6a8b, //  檋
          0x6a8d_6a8e, //  檍 檎
          0x6a90_6a91, //  檐 檑
          0x6a94_6a94, //  檔
          0x6a97_6a97, //  檗
          0x6a9b_6aa3, //  檛 檜 檝 檞 檟 檠 檡 檢 檣
          0x6aa5_6aa5, //  檥
          0x6aaa_6aac, //  檪 檫 檬
          0x6aae_6ab1, //  檮 檯 檰 檱
          0x6ab3_6ab4, //  檳 檴
          0x6ab8_6ab8, //  檸
          0x6abb_6abb, //  檻
          0x6abd_6abf, //  檽 檾 檿
          0x6ac1_6ac3, //  櫁 櫂 櫃
          0x6ac6_6ac6, //  櫆
          0x6ac8_6ac9, //  櫈 櫉
          0x6acc_6acc, //  櫌
          0x6ad0_6ad1, //  櫐 櫑
          0x6ad3_6ad6, //  櫓 櫔 櫕 櫖
          0x6ada_6adf, //  櫚 櫛 櫜 櫝 櫞 櫟
          0x6ae4_6ae4, //  櫤
          0x6ae7_6ae8, //  櫧 櫨
          0x6aea_6aea, //  櫪
          0x6aec_6aec, //  櫬
          0x6af0_6af3, //  櫰 櫱 櫲 櫳
          0x6afa_6afd, //  櫺 櫻 櫼 櫽
          0x6b02_6b07, //  欂 欃 欄 欅 欆 欇
          0x6b09_6b0b, //  欉 權 欋
          0x6b0f_6b12, //  欏 欐 欑 欒
          0x6b16_6b17, //  欖 欗
          0x6b1b_6b1b, //  欛
          0x6b1d_6b21, //  欝 欞 欟 欠 次
          0x6b23_6b24, //  欣 欤
          0x6b27_6b28, //  欧 欨
          0x6b2b_6b2c, //  欫 欬
          0x6b2f_6b2f, //  欯
          0x6b32_6b32, //  欲
          0x6b35_6b3b, //  欵 欶 欷 欸 欹 欺 欻
          0x6b3d_6b3f, //  欽 款 欿
          0x6b43_6b43, //  歃
          0x6b46_6b47, //  歆 歇
          0x6b49_6b4a, //  歉 歊
          0x6b4c_6b4e, //  歌 歍 歎
          0x6b50_6b50, //  歐
          0x6b52_6b54, //  歒 歓 歔
          0x6b56_6b56, //  歖
          0x6b58_6b59, //  歘 歙
          0x6b5b_6b5b, //  歛
          0x6b5d_6b5d, //  歝
          0x6b5f_6b67, //  歟 歠 歡 止 正 此 步 武 歧
          0x6b69_6b6c, //  歩 歪 歫 歬
          0x6b6e_6b70, //  歮 歯 歰
          0x6b73_6b75, //  歳 歴 歵
          0x6b77_6b7b, //  歷 歸 歹 歺 死
          0x6b7d_6b86, //  歽 歾 歿 殀 殁 殂 殃 殄 殅 殆
          0x6b89_6b8b, //  殉 殊 残
          0x6b8d_6b8d, //  殍
          0x6b95_6b98, //  殕 殖 殗 殘
          0x6b9b_6b9b, //  殛
          0x6b9e_6ba0, //  殞 殟 殠
          0x6ba2_6ba4, //  殢 殣 殤
          0x6ba8_6bb5, //  殨 殩 殪 殫 殬 殭 殮 殯 殰 殱 殲 殳 殴 段
          0x6bb7_6bc0, //  殷 殸 殹 殺 殻 殼 殽 殾 殿 毀
          0x6bc3_6bc9, //  毃 毄 毅 毆 毇 毈 毉
          0x6bcb_6bcf, //  毋 毌 母 毎 每
          0x6bd2_6bd4, //  毒 毓 比
          0x6bd6_6bd8, //  毖 毗 毘
          0x6bda_6bdb, //  毚 毛
          0x6bdf_6bdf, //  毟
          0x6be1_6be1, //  毡
          0x6be3_6be3, //  毣
          0x6be6_6be7, //  毦 毧
          0x6beb_6bec, //  毫 毬
          0x6bee_6bef, //  毮 毯
          0x6bf1_6bf1, //  毱
          0x6bf3_6bf3, //  毳
          0x6bf7_6bf7, //  毷
          0x6bf9_6bf9, //  毹
          0x6bff_6bff, //  毿
          0x6c02_6c02, //  氂
          0x6c04_6c05, //  氄 氅
          0x6c08_6c0a, //  氈 氉 氊
          0x6c0d_6c14, //  氍 氎 氏 氐 民 氒 氓 气
          0x6c17_6c17, //  気
          0x6c19_6c19, //  氙
          0x6c1b_6c1b, //  氛
          0x6c1f_6c1f, //  氟
          0x6c23_6c24, //  氣 氤
          0x6c26_6c28, //  氦 氧 氨
          0x6c2c_6c2c, //  氬
          0x6c2e_6c2e, //  氮
          0x6c33_6c38, //  氳 水 氵 氶 氷 永
          0x6c3a_6c3b, //  氺 氻
          0x6c3e_6c42, //  氾 氿 汀 汁 求
          0x6c4a_6c4b, //  汊 汋
          0x6c4d_6c50, //  汍 汎 汏 汐
          0x6c52_6c52, //  汒
          0x6c54_6c55, //  汔 汕
          0x6c57_6c57, //  汗
          0x6c59_6c60, //  汙 汚 汛 汜 汝 汞 江 池
          0x6c62_6c62, //  汢
          0x6c67_6c68, //  汧 汨
          0x6c6a_6c6b, //  汪 汫
          0x6c6d_6c6d, //  汭
          0x6c6f_6c70, //  汯 汰
          0x6c72_6c74, //  汲 汳 汴
          0x6c76_6c76, //  汶
          0x6c78_6c7b, //  汸 汹 決 汻
          0x6c7d_6c7e, //  汽 汾
          0x6c81_6c89, //  沁 沂 沃 沄 沅 沆 沇 沈 沉
          0x6c8c_6c8d, //  沌 沍
          0x6c90_6c90, //  沐
          0x6c92_6c9c, //  沒 沓 沔 沕 沖 沗 沘 沙 沚 沛 沜
          0x6c9f_6c9f, //  沟
          0x6ca1_6ca2, //  没 沢
          0x6caa_6cab, //  沪 沫
          0x6cad_6cae, //  沭 沮
          0x6cb0_6cb4, //  沰 沱 沲 河 沴
          0x6cb8_6cbf, //  沸 油 沺 治 沼 沽 沾 沿
          0x6cc1_6cc2, //  況 泂
          0x6cc4_6cc6, //  泄 泅 泆
          0x6cc9_6cca, //  泉 泊
          0x6ccc_6ccd, //  泌 泍
          0x6ccf_6cd7, //  泏 泐 泑 泒 泓 泔 法 泖 泗
          0x6cd9_6cdd, //  泙 泚 泛 泜 泝
          0x6ce0_6ce3, //  泠 泡 波 泣
          0x6ce5_6ce5, //  泥
          0x6ce7_6cf4, //  泧 注 泩 泪 泫 泬 泭 泮 泯 泰 泱 泲 泳 泴
          0x6cfb_6cfb, //  泻
          0x6d00_6d00, //  洀
          0x6d04_6d04, //  洄
          0x6d07_6d07, //  洇
          0x6d0a_6d0c, //  洊 洋 洌
          0x6d0e_6d0f, //  洎 洏
          0x6d11_6d13, //  洑 洒 洓
          0x6d17_6d17, //  洗
          0x6d19_6d1b, //  洙 洚 洛
          0x6d1e_6d1f, //  洞 洟
          0x6d24_6d2b, //  洤 津 洦 洧 洨 洩 洪 洫
          0x6d2e_6d2f, //  洮 洯
          0x6d31_6d36, //  洱 洲 洳 洴 洵 洶
          0x6d38_6d39, //  洸 洹
          0x6d3b_6d3f, //  活 洼 洽 派 洿
          0x6d41_6d41, //  流
          0x6d44_6d45, //  浄 浅
          0x6d57_6d5c, //  浗 浘 浙 浚 浛 浜
          0x6d5e_6d61, //  浞 浟 浠 浡
          0x6d63_6d67, //  浣 浤 浥 浦 浧
          0x6d69_6d6a, //  浩 浪
          0x6d6c_6d6c, //  浬
          0x6d6e_6d70, //  浮 浯 浰
          0x6d74_6d74, //  浴
          0x6d77_6d79, //  海 浸 浹
          0x6d7c_6d7c, //  浼
          0x6d80_6d82, //  涀 涁 涂
          0x6d85_6d85, //  涅
          0x6d87_6d8a, //  涇 消 涉 涊
          0x6d8c_6d8e, //  涌 涍 涎
          0x6d91_6d99, //  涑 涒 涓 涔 涕 涖 涗 涘 涙
          0x6d9b_6d9c, //  涛 涜
          0x6daa_6dac, //  涪 涫 涬
          0x6dae_6daf, //  涮 涯
          0x6db2_6db2, //  液
          0x6db4_6db5, //  涴 涵
          0x6db7_6db9, //  涷 涸 涹
          0x6dbc_6dbd, //  涼 涽
          0x6dbf_6dc0, //  涿 淀
          0x6dc2_6dc2, //  淂
          0x6dc4_6dc8, //  淄 淅 淆 淇 淈
          0x6dca_6dcc, //  淊 淋 淌
          0x6dce_6dd2, //  淎 淏 淐 淑 淒
          0x6dd5_6dd6, //  淕 淖
          0x6dd8_6ddb, //  淘 淙 淚 淛
          0x6ddd_6de2, //  淝 淞 淟 淠 淡 淢
          0x6de4_6de6, //  淤 淥 淦
          0x6de8_6dec, //  淨 淩 淪 淫 淬
          0x6dee_6df1, //  淮 淯 淰 深
          0x6df3_6df7, //  淳 淴 淵 淶 混
          0x6df9_6dfc, //  淹 淺 添 淼
          0x6e00_6e00, //  渀
          0x6e04_6e05, //  渄 清
          0x6e07_6e0b, //  渇 済 渉 渊 渋
          0x6e13_6e13, //  渓
          0x6e15_6e15, //  渕
          0x6e17_6e17, //  渗
          0x6e19_6e1b, //  渙 渚 減
          0x6e1d_6e27, //  渝 渞 渟 渠 渡 渢 渣 渤 渥 渦 渧
          0x6e29_6e29, //  温
          0x6e2b_6e2f, //  渫 測 渭 渮 港
          0x6e32_6e32, //  渲
          0x6e34_6e34, //  渴
          0x6e36_6e36, //  渶
          0x6e38_6e3c, //  游 渹 渺 渻 渼
          0x6e3e_6e3e, //  渾
          0x6e43_6e45, //  湃 湄 湅
          0x6e48_6e4f, //  湈 湉 湊 湋 湌 湍 湎 湏
          0x6e51_6e54, //  湑 湒 湓 湔
          0x6e56_6e58, //  湖 湗 湘
          0x6e5b_6e5f, //  湛 湜 湝 湞 湟
          0x6e62_6e63, //  湢 湣
          0x6e67_6e68, //  湧 湨
          0x6e6b_6e6b, //  湫
          0x6e6e_6e6f, //  湮 湯
          0x6e72_6e73, //  湲 湳
          0x6e76_6e76, //  湶
          0x6e7b_6e7b, //  湻
          0x6e7d_6e80, //  湽 湾 湿 満
          0x6e82_6e82, //  溂
          0x6e8c_6e8d, //  溌 溍
          0x6e8f_6e90, //  溏 源
          0x6e93_6e93, //  溓
          0x6e96_6e96, //  準
          0x6e98_6e99, //  溘 溙
          0x6e9c_6e9d, //  溜 溝
          0x6e9f_6ea0, //  溟 溠
          0x6ea2_6ea2, //  溢
          0x6ea5_6ea5, //  溥
          0x6ea7_6ea7, //  溧
          0x6eaa_6eab, //  溪 溫
          0x6ead_6eaf, //  溭 溮 溯
          0x6eb1_6eb4, //  溱 溲 溳 溴
          0x6eb6_6eb7, //  溶 溷
          0x6eba_6ebb, //  溺 溻
          0x6ebd_6ebd, //  溽
          0x6ebf_6ec5, //  溿 滀 滁 滂 滃 滄 滅
          0x6ec7_6ecf, //  滇 滈 滉 滊 滋 滌 滍 滎 滏
          0x6ed1_6ed1, //  滑
          0x6ed3_6ed5, //  滓 滔 滕
          0x6ed9_6ed9, //  滙
          0x6edd_6ede, //  滝 滞
          0x6eeb_6eef, //  滫 滬 滭 滮 滯
          0x6ef2_6ef2, //  滲
          0x6ef4_6ef4, //  滴
          0x6ef7_6ef9, //  滷 滸 滹
          0x6efb_6efb, //  滻
          0x6efd_6eff, //  滽 滾 滿
          0x6f01_6f02, //  漁 漂
          0x6f04_6f04, //  漄
          0x6f06_6f06, //  漆
          0x6f08_6f0a, //  漈 漉 漊
          0x6f0c_6f0d, //  漌 漍
          0x6f0f_6f11, //  漏 漐 漑
          0x6f13_6f16, //  漓 演 漕 漖
          0x6f18_6f18, //  漘
          0x6f1a_6f1b, //  漚 漛
          0x6f20_6f20, //  漠
          0x6f22_6f23, //  漢 漣
          0x6f25_6f26, //  漥 漦
          0x6f29_6f2d, //  漩 漪 漫 漬 漭
          0x6f2f_6f33, //  漯 漰 漱 漲 漳
          0x6f35_6f36, //  漵 漶
          0x6f38_6f38, //  漸
          0x6f3b_6f3c, //  漻 漼
          0x6f3e_6f3f, //  漾 漿
          0x6f41_6f41, //  潁
          0x6f45_6f45, //  潅
          0x6f4f_6f4f, //  潏
          0x6f51_6f54, //  潑 潒 潓 潔
          0x6f57_6f62, //  潗 潘 潙 潚 潛 潜 潝 潞 潟 潠 潡 潢
          0x6f64_6f64, //  潤
          0x6f66_6f66, //  潦
          0x6f68_6f68, //  潨
          0x6f6c_6f70, //  潬 潭 潮 潯 潰
          0x6f74_6f74, //  潴
          0x6f78_6f78, //  潸
          0x6f7a_6f7a, //  潺
          0x6f7c_6f7e, //  潼 潽 潾
          0x6f80_6f84, //  澀 澁 澂 澃 澄
          0x6f86_6f88, //  澆 澇 澈
          0x6f8b_6f8e, //  澋 澌 澍 澎
          0x6f90_6f94, //  澐 澑 澒 澓 澔
          0x6f96_6f98, //  澖 澗 澘
          0x6f9a_6f9a, //  澚
          0x6f9f_6fa1, //  澟 澠 澡
          0x6fa3_6fa8, //  澣 澤 澥 澦 澧 澨
          0x6faa_6faa, //  澪
          0x6fae_6fb1, //  澮 澯 澰 澱
          0x6fb3_6fb3, //  澳
          0x6fb5_6fb6, //  澵 澶
          0x6fb9_6fb9, //  澹
          0x6fbc_6fbc, //  澼
          0x6fbe_6fbe, //  澾
          0x6fc0_6fc3, //  激 濁 濂 濃
          0x6fc5_6fca, //  濅 濆 濇 濈 濉 濊
          0x6fd4_6fd5, //  濔 濕
          0x6fd8_6fd8, //  濘
          0x6fda_6fdb, //  濚 濛
          0x6fde_6fe1, //  濞 濟 濠 濡
          0x6fe4_6fe4, //  濤
          0x6fe8_6fe9, //  濨 濩
          0x6feb_6fec, //  濫 濬
          0x6fee_6ff1, //  濮 濯 濰 濱
          0x6ff3_6ff3, //  濳
          0x6ff5_6ff6, //  濵 濶
          0x6ff9_6ffa, //  濹 濺
          0x6ffc_6ffe, //  濼 濽 濾
          0x7000_7001, //  瀀 瀁
          0x7005_7007, //  瀅 瀆 瀇
          0x7009_700b, //  瀉 瀊 瀋
          0x700d_700d, //  瀍
          0x700f_700f, //  瀏
          0x7011_7011, //  瀑
          0x7015_7015, //  瀕
          0x7017_7018, //  瀗 瀘
          0x701a_701b, //  瀚 瀛
          0x701d_7020, //  瀝 瀞 瀟 瀠
          0x7023_7023, //  瀣
          0x7026_7028, //  瀦 瀧 瀨
          0x702c_702c, //  瀬
          0x702f_7030, //  瀯 瀰
          0x7032_7032, //  瀲
          0x7034_7034, //  瀴
          0x7037_7037, //  瀷
          0x7039_703a, //  瀹 瀺
          0x703c_703c, //  瀼
          0x703e_703e, //  瀾
          0x7043_7044, //  灃 灄
          0x7047_704c, //  灇 灈 灉 灊 灋 灌
          0x704e_704e, //  灎
          0x7051_7051, //  灑
          0x7054_7055, //  灔 灕
          0x7058_7058, //  灘
          0x705d_705e, //  灝 灞
          0x7063_7065, //  灣 灤 灥
          0x7069_7069, //  灩
          0x706b_706c, //  火 灬
          0x706e_7070, //  灮 灯 灰
          0x7075_7076, //  灵 灶
          0x7078_7078, //  灸
          0x707c_707e, //  灼 災 灾
          0x7081_7081, //  炁
          0x7085_7086, //  炅 炆
          0x7089_708a, //  炉 炊
          0x708e_708e, //  炎
          0x7092_7092, //  炒
          0x7094_7099, //  炔 炕 炖 炗 炘 炙
          0x709b_709b, //  炛
          0x709f_709f, //  炟
          0x70a4_70a4, //  炤
          0x70ab_70b1, //  炫 炬 炭 炮 炯 炰 炱
          0x70b3_70b4, //  炳 炴
          0x70b7_70bb, //  炷 炸 点 為 炻
          0x70c8_70c8, //  烈
          0x70ca_70cb, //  烊 烋
          0x70cf_70cf, //  烏
          0x70d1_70d1, //  烑
          0x70d3_70d6, //  烓 烔 烕 烖
          0x70d8_70d9, //  烘 烙
          0x70dc_70dd, //  烜 烝
          0x70df_70df, //  烟
          0x70e4_70e4, //  烤
          0x70ec_70ec, //  烬
          0x70f1_70f1, //  烱
          0x70f9_70fa, //  烹 烺
          0x70fd_70fd, //  烽
          0x7103_7109, //  焃 焄 焅 焆 焇 焈 焉
          0x710b_710c, //  焋 焌
          0x710f_710f, //  焏
          0x7114_7114, //  焔
          0x7119_711a, //  焙 焚
          0x711c_711c, //  焜
          0x711e_711e, //  焞
          0x7120_7121, //  焠 無
          0x7126_7126, //  焦
          0x712b_712b, //  焫
          0x712d_7131, //  焭 焮 焯 焰 焱
          0x7136_7136, //  然
          0x7138_7138, //  焸
          0x713c_713c, //  焼
          0x7141_7141, //  煁
          0x7145_7147, //  煅 煆 煇
          0x7149_714c, //  煉 煊 煋 煌
          0x714e_714e, //  煎
          0x7150_7153, //  煐 煑 煒 煓
          0x7155_7157, //  煕 煖 煗
          0x7159_715a, //  煙 煚
          0x715c_715c, //  煜
          0x715e_715e, //  煞
          0x7160_7160, //  煠
          0x7162_7162, //  煢
          0x7164_7169, //  煤 煥 煦 照 煨 煩
          0x716c_716c, //  煬
          0x716e_716e, //  煮
          0x7179_7179, //  煹
          0x717d_717d, //  煽
          0x7180_7180, //  熀
          0x7184_7185, //  熄 熅
          0x7187_7188, //  熇 熈
          0x718a_718a, //  熊
          0x718c_718c, //  熌
          0x718f_718f, //  熏
          0x7192_7192, //  熒
          0x7194_7196, //  熔 熕 熖
          0x7199_719b, //  熙 熚 熛
          0x719f_71a0, //  熟 熠
          0x71a2_71a2, //  熢
          0x71a8_71a8, //  熨
          0x71ac_71ac, //  熬
          0x71ae_71b3, //  熮 熯 熰 熱 熲 熳
          0x71b9_71ba, //  熹 熺
          0x71be_71c1, //  熾 熿 燀 燁
          0x71c3_71c4, //  燃 燄
          0x71c8_71c9, //  燈 燉
          0x71cb_71cc, //  燋 燌
          0x71ce_71ce, //  燎
          0x71d0_71d0, //  燐
          0x71d2_71d7, //  燒 燓 燔 燕 燖 燗
          0x71d9_71da, //  燙 燚
          0x71dc_71dc, //  燜
          0x71df_71e0, //  營 燠
          0x71e5_71e7, //  燥 燦 燧
          0x71ec_71ee, //  燬 燭 燮
          0x71f5_71f5, //  燵
          0x71f8_71f9, //  燸 燹
          0x71fb_71fc, //  燻 燼
          0x71fe_7200, //  燾 燿 爀
          0x7206_7209, //  爆 爇 爈 爉
          0x720d_720d, //  爍
          0x7210_7210, //  爐
          0x7213_7213, //  爓
          0x7215_7215, //  爕
          0x7217_7217, //  爗
          0x721a_721b, //  爚 爛
          0x721d_721d, //  爝
          0x721f_721f, //  爟
          0x7224_7224, //  爤
          0x7228_7228, //  爨
          0x722a_722d, //  爪 爫 爬 爭
          0x722f_7230, //  爯 爰
          0x7232_7232, //  爲
          0x7234_7236, //  爴 爵 父
          0x7238_7243, //  爸 爹 爺 爻 爼 爽 爾 爿 牀 牁 牂 牃
          0x7245_7248, //  牅 牆 片 版
          0x724b_724c, //  牋 牌
          0x724e_7250, //  牎 牏 牐
          0x7252_7253, //  牒 牓
          0x7255_7263, //  牕 牖 牗 牘 牙 牚 牛 牜 牝 牞 牟 牠 牡 牢 牣
          0x7267_7269, //  牧 牨 物
          0x726b_726b, //  牫
          0x726e_726f, //  牮 牯
          0x7271_7272, //  牱 牲
          0x7274_7274, //  牴
          0x7277_7279, //  牷 牸 特
          0x727b_7282, //  牻 牼 牽 牾 牿 犀 犁 犂
          0x7284_7284, //  犄
          0x7287_7287, //  犇
          0x7289_7289, //  犉
          0x728d_728e, //  犍 犎
          0x7292_7293, //  犒 犓
          0x7296_7296, //  犖
          0x729b_729b, //  犛
          0x72a0_72a0, //  犠
          0x72a2_72a2, //  犢
          0x72a7_72a8, //  犧 犨
          0x72ac_72b2, //  犬 犭 犮 犯 犰 犱 犲
          0x72b4_72b4, //  犴
          0x72b6_72b6, //  状
          0x72b9_72b9, //  犹
          0x72be_72be, //  犾
          0x72c0_72c4, //  狀 狁 狂 狃 狄
          0x72c6_72c7, //  狆 狇
          0x72c9_72c9, //  狉
          0x72cc_72cc, //  狌
          0x72ce_72ce, //  狎
          0x72d0_72d0, //  狐
          0x72d2_72d2, //  狒
          0x72d5_72d9, //  狕 狖 狗 狘 狙
          0x72db_72db, //  狛
          0x72df_72e2, //  狟 狠 狡 狢
          0x72e5_72e5, //  狥
          0x72e9_72e9, //  狩
          0x72ec_72ed, //  独 狭
          0x72f3_72f4, //  狳 狴
          0x72f7_72fe, //  狷 狸 狹 狺 狻 狼 狽 狾
          0x7302_7302, //  猂
          0x7304_7305, //  猄 猅
          0x7307_7307, //  猇
          0x730a_730b, //  猊 猋
          0x730d_730d, //  猍
          0x7312_7313, //  猒 猓
          0x7316_7319, //  猖 猗 猘 猙
          0x731b_731f, //  猛 猜 猝 猞 猟
          0x7322_7322, //  猢
          0x7324_7325, //  猤 猥
          0x7327_732c, //  猧 猨 猩 猪 猫 猬
          0x732e_732f, //  献 猯
          0x7331_7337, //  猱 猲 猳 猴 猵 猶 猷
          0x7339_733b, //  猹 猺 猻
          0x733d_733f, //  猽 猾 猿
          0x7343_7345, //  獃 獄 獅
          0x734d_7350, //  獍 獎 獏 獐
          0x7352_7352, //  獒
          0x7356_7358, //  獖 獗 獘
          0x735d_7360, //  獝 獞 獟 獠
          0x7363_7363, //  獣
          0x7366_736c, //  獦 獧 獨 獩 獪 獫 獬
          0x736e_7372, //  獮 獯 獰 獱 獲
          0x7375_7375, //  獵
          0x7377_737c, //  獷 獸 獹 獺 獻 獼
          0x7380_7381, //  玀 玁
          0x7383_7387, //  玃 玄 玅 玆 率
          0x7389_738b, //  玉 玊 王
          0x738e_738e, //  玎
          0x7390_7390, //  玐
          0x7393_7398, //  玓 玔 玕 玖 玗 玘
          0x739c_739c, //  玜
          0x739e_73a0, //  玞 玟 玠
          0x73a2_73a2, //  玢
          0x73a5_73a6, //  玥 玦
          0x73a8_73ab, //  玨 玩 玪 玫
          0x73ad_73ad, //  玭
          0x73b2_73b3, //  玲 玳
          0x73b5_73b5, //  玵
          0x73b7_73b7, //  玷
          0x73b9_73b9, //  玹
          0x73bb_73bd, //  玻 玼 玽
          0x73bf_73c0, //  玿 珀
          0x73c2_73c2, //  珂
          0x73c5_73c6, //  珅 珆
          0x73c8_73cf, //  珈 珉 珊 珋 珌 珍 珎 珏
          0x73d2_73d3, //  珒 珓
          0x73d6_73d6, //  珖
          0x73d9_73d9, //  珙
          0x73dd_73de, //  珝 珞
          0x73e0_73e1, //  珠 珡
          0x73e3_73e3, //  珣
          0x73e5_73e7, //  珥 珦 珧
          0x73e9_73ea, //  珩 珪
          0x73ed_73ee, //  班 珮
          0x73f1_73f1, //  珱
          0x73f4_73f5, //  珴 珵
          0x73f7_73fb, //  珷 珸 珹 珺 珻
          0x73fd_7401, //  珽 現 珿 琀 琁
          0x7403_7407, //  球 琄 琅 理 琇
          0x7409_740a, //  琉 琊
          0x7411_7411, //  琑
          0x7413_7413, //  琓
          0x741a_741b, //  琚 琛
          0x7422_7422, //  琢
          0x7424_7426, //  琤 琥 琦
          0x7428_7436, //  琨 琩 琪 琫 琬 琭 琮 琯 琰 琱 琲 琳 琴 琵 琶
          0x7439_743a, //  琹 琺
          0x743f_7441, //  琿 瑀 瑁
          0x7443_7444, //  瑃 瑄
          0x7446_7447, //  瑆 瑇
          0x744b_744b, //  瑋
          0x744d_744d, //  瑍
          0x7451_7453, //  瑑 瑒 瑓
          0x7455_7455, //  瑕
          0x7457_7457, //  瑗
          0x7459_7460, //  瑙 瑚 瑛 瑜 瑝 瑞 瑟 瑠
          0x7462_7464, //  瑢 瑣 瑤
          0x7466_746b, //  瑦 瑧 瑨 瑩 瑪 瑫
          0x746d_7473, //  瑭 瑮 瑯 瑰 瑱 瑲 瑳
          0x7476_7476, //  瑶
          0x747e_747e, //  瑾
          0x7480_7481, //  璀 璁
          0x7483_7483, //  璃
          0x7485_7489, //  璅 璆 璇 璈 璉
          0x748b_748b, //  璋
          0x748f_7492, //  璏 璐 璑 璒
          0x7497_749a, //  璗 璘 璙 璚
          0x749c_749c, //  璜
          0x749e_74a3, //  璞 璟 璠 璡 璢 璣
          0x74a5_74ab, //  璥 璦 璧 璨 璩 璪 璫
          0x74ae_74b2, //  璮 璯 環 璱 璲
          0x74b5_74b5, //  璵
          0x74b9_74bb, //  璹 璺 璻
          0x74bd_74bd, //  璽
          0x74bf_74bf, //  璿
          0x74c8_74ca, //  瓈 瓉 瓊
          0x74cc_74cc, //  瓌
          0x74cf_74d0, //  瓏 瓐
          0x74d3_74d4, //  瓓 瓔
          0x74d6_74d6, //  瓖
          0x74d8_74d8, //  瓘
          0x74da_74dc, //  瓚 瓛 瓜
          0x74de_74e0, //  瓞 瓟 瓠
          0x74e2_74e4, //  瓢 瓣 瓤
          0x74e6_74eb, //  瓦 瓧 瓨 瓩 瓪 瓫
          0x74ee_74f2, //  瓮 瓯 瓰 瓱 瓲
          0x74f4_74f4, //  瓴
          0x74f6_74f8, //  瓶 瓷 瓸
          0x74fa_74fc, //  瓺 瓻 瓼
          0x74ff_74ff, //  瓿
          0x7501_7501, //  甁
          0x7503_7506, //  甃 甄 甅 甆
          0x750c_750e, //  甌 甍 甎
          0x7511_7513, //  甑 甒 甓
          0x7515_7518, //  甕 甖 甗 甘
          0x751a_751a, //  甚
          0x751c_751c, //  甜
          0x751e_7521, //  甞 生 甠 甡
          0x7523_752c, //  産 甤 甥 甦 甧 用 甩 甪 甫 甬
          0x752f_7533, //  甯 田 由 甲 申
          0x7536_7540, //  甶 男 甸 甹 町 画 甼 甽 甾 甿 畀
          0x7543_7544, //  畃 畄
          0x7546_7552, //  畆 畇 畈 畉 畊 畋 界 畍 畎 畏 畐 畑 畒
          0x7554_7554, //  畔
          0x7557_7557, //  畗
          0x7559_7562, //  留 畚 畛 畜 畝 畞 畟 畠 畡 畢
          0x7564_7567, //  畤 略 畦 畧
          0x7569_756d, //  畩 番 畫 畬 畭
          0x756f_7574, //  畯 異 畱 畲 畳 畴
          0x7576_757f, //  當 畷 畸 畹 畺 畻 畼 畽 畾 畿
          0x7581_7582, //  疁 疂
          0x7585_7587, //  疅 疆 疇
          0x7589_758c, //  疉 疊 疋 疌
          0x758e_7595, //  疎 疏 疐 疑 疒 疓 疔 疕
          0x7599_759a, //  疙 疚
          0x759c_759d, //  疜 疝
          0x75a2_75a5, //  疢 疣 疤 疥
          0x75ab_75ab, //  疫
          0x75b0_75b5, //  疰 疱 疲 疳 疴 疵
          0x75b7_75ba, //  疷 疸 疹 疺
          0x75bc_75c7, //  疼 疽 疾 疿 痀 痁 痂 痃 痄 病 痆 症
          0x75ca_75ca, //  痊
          0x75cc_75cf, //  痌 痍 痎 痏
          0x75d2_75d5, //  痒 痓 痔 痕
          0x75d7_75d9, //  痗 痘 痙
          0x75db_75e4, //  痛 痜 痝 痞 痟 痠 痡 痢 痣 痤
          0x75e7_75e7, //  痧
          0x75e9_75e9, //  痩
          0x75ec_75ec, //  痬
          0x75ee_75f4, //  痮 痯 痰 痱 痲 痳 痴
          0x75f9_75fa, //  痹 痺
          0x75fc_75fc, //  痼
          0x75fe_7604, //  痾 痿 瘀 瘁 瘂 瘃 瘄
          0x7607_760d, //  瘇 瘈 瘉 瘊 瘋 瘌 瘍
          0x760f_760f, //  瘏
          0x7612_7613, //  瘒 瘓
          0x7615_7616, //  瘕 瘖
          0x7618_7619, //  瘘 瘙
          0x761b_7629, //  瘛 瘜 瘝 瘞 瘟 瘠 瘡 瘢 瘣 瘤 瘥 瘦 瘧 瘨 瘩
          0x762d_762d, //  瘭
          0x7630_7630, //  瘰
          0x7632_7635, //  瘲 瘳 瘴 瘵
          0x7638_763c, //  瘸 瘹 瘺 瘻 瘼
          0x7640_764c, //  癀 癁 療 癃 癄 癅 癆 癇 癈 癉 癊 癋 癌
          0x7652_7652, //  癒
          0x7655_7656, //  癕 癖
          0x7658_7659, //  癘 癙
          0x765c_765c, //  癜
          0x765f_765f, //  癟
          0x7661_7662, //  癡 癢
          0x7664_7665, //  癤 癥
          0x7667_766a, //  癧 癨 癩 癪
          0x766c_7672, //  癬 癭 癮 癯 癰 癱 癲
          0x7674_7674, //  癴
          0x7676_7676, //  癶
          0x7678_7678, //  癸
          0x767a_767e, //  発 登 發 白 百
          0x7680_7681, //  皀 皁
          0x7683_7688, //  皃 的 皅 皆 皇 皈
          0x768b_768e, //  皋 皌 皍 皎
          0x7690_7690, //  皐
          0x7693_7693, //  皓
          0x7695_7696, //  皕 皖
          0x7699_76a8, //  皙 皚 皛 皜 皝 皞 皟 皠 皡 皢 皣 皤 皥 皦 皧 皨
          0x76aa_76aa, //  皪
          0x76ad_76b0, //  皭 皮 皯 皰
          0x76b4_76b4, //  皴
          0x76b6_76ba, //  皶 皷 皸 皹 皺
          0x76bd_76bd, //  皽
          0x76bf_76bf, //  皿
          0x76c1_76c3, //  盁 盂 盃
          0x76c5_76c6, //  盅 盆
          0x76c8_76ce, //  盈 盉 益 盋 盌 盍 盎
          0x76d2_76d2, //  盒
          0x76d4_76d4, //  盔
          0x76d6_76d7, //  盖 盗
          0x76d9_76d9, //  盙
          0x76db_76dc, //  盛 盜
          0x76de_76e1, //  盞 盟 盠 盡
          0x76e3_76e8, //  監 盤 盥 盦 盧 盨
          0x76ea_76ea, //  盪
          0x76ec_76ec, //  盬
          0x76ee_76ee, //  目
          0x76f0_76f2, //  盰 盱 盲
          0x76f4_76f4, //  直
          0x76f6_76f6, //  盶
          0x76f8_76f9, //  相 盹
          0x76fb_76fc, //  盻 盼
          0x76fe_76fe, //  盾
          0x7700_7701, //  眀 省
          0x7704_7704, //  眄
          0x7706_770c, //  眆 眇 眈 眉 眊 看 県
          0x770e_770e, //  眎
          0x7712_7712, //  眒
          0x7714_7715, //  眔 眕
          0x7717_7717, //  眗
          0x7719_771c, //  眙 眚 眛 眜
          0x771e_7720, //  眞 真 眠
          0x7722_7722, //  眢
          0x7724_7726, //  眤 眥 眦
          0x7728_7729, //  眨 眩
          0x772d_772f, //  眭 眮 眯
          0x7734_773a, //  眴 眵 眶 眷 眸 眹 眺
          0x773c_773e, //  眼 眽 眾
          0x7740_7740, //  着
          0x7742_7742, //  睂
          0x7745_7747, //  睅 睆 睇
          0x774a_774a, //  睊
          0x774d_774f, //  睍 睎 睏
          0x7752_7752, //  睒
          0x7756_7758, //  睖 睗 睘
          0x775a_775c, //  睚 睛 睜
          0x775e_7768, //  睞 睟 睠 睡 睢 督 睤 睥 睦 睧 睨
          0x776a_776c, //  睪 睫 睬
          0x7770_7770, //  睰
          0x7772_7774, //  睲 睳 睴
          0x7779_777a, //  睹 睺
          0x777c_7780, //  睼 睽 睾 睿 瞀
          0x7784_7784, //  瞄
          0x778b_778e, //  瞋 瞌 瞍 瞎
          0x7791_7791, //  瞑
          0x7794_7796, //  瞔 瞕 瞖
          0x779a_779a, //  瞚
          0x779e_77a0, //  瞞 瞟 瞠
          0x77a2_77a2, //  瞢
          0x77a4_77a5, //  瞤 瞥
          0x77a7_77a7, //  瞧
          0x77a9_77aa, //  瞩 瞪
          0x77ac_77b1, //  瞬 瞭 瞮 瞯 瞰 瞱
          0x77b3_77b3, //  瞳
          0x77b5_77b6, //  瞵 瞶
          0x77b9_77b9, //  瞹
          0x77bb_77bf, //  瞻 瞼 瞽 瞾 瞿
          0x77c3_77c3, //  矃
          0x77c7_77c7, //  矇
          0x77c9_77c9, //  矉
          0x77cd_77cd, //  矍
          0x77d1_77d2, //  矑 矒
          0x77d5_77d5, //  矕
          0x77d7_77d7, //  矗
          0x77d9_77dc, //  矙 矚 矛 矜
          0x77de_77e0, //  矞 矟 矠
          0x77e2_77e7, //  矢 矣 矤 知 矦 矧
          0x77e9_77ea, //  矩 矪
          0x77ec_77f1, //  矬 短 矮 矯 矰 矱
          0x77f3_77f4, //  石 矴
          0x77f8_77f8, //  矸
          0x77fb_77fc, //  矻 矼
          0x7802_7802, //  砂
          0x7805_7806, //  砅 砆
          0x7809_7809, //  砉
          0x780c_780e, //  砌 砍 砎
          0x7811_7812, //  砑 砒
          0x7814_7815, //  研 砕
          0x7819_7819, //  砙
          0x781d_781d, //  砝
          0x7820_7823, //  砠 砡 砢 砣
          0x7825_7827, //  砥 砦 砧
          0x782c_782e, //  砬 砭 砮
          0x7830_7830, //  砰
          0x7832_7832, //  砲
          0x7834_7835, //  破 砵
          0x7837_7837, //  砷
          0x783a_783a, //  砺
          0x783f_783f, //  砿
          0x7843_7845, //  硃 硄 硅
          0x7847_7848, //  硇 硈
          0x784c_784c, //  硌
          0x784e_784f, //  硎 硏
          0x7851_7852, //  硑 硒
          0x785c_785e, //  硜 硝 硞
          0x7860_7861, //  硠 硡
          0x7863_7864, //  硣 硤
          0x7868_7868, //  硨
          0x786a_786c, //  硪 硫 硬
          0x786e_786f, //  确 硯
          0x7872_7872, //  硲
          0x7874_7874, //  硴
          0x787a_787a, //  硺
          0x787c_787c, //  硼
          0x787e_787e, //  硾
          0x7881_7881, //  碁
          0x7886_7887, //  碆 碇
          0x788a_788a, //  碊
          0x788c_788f, //  碌 碍 碎 碏
          0x7891_7891, //  碑
          0x7893_7895, //  碓 碔 碕
          0x7897_7898, //  碗 碘
          0x789a_789a, //  碚
          0x789d_789f, //  碝 碞 碟
          0x78a1_78a1, //  碡
          0x78a3_78a4, //  碣 碤
          0x78a7_78aa, //  碧 碨 碩 碪
          0x78ac_78ad, //  碬 碭
          0x78af_78b3, //  碯 碰 碱 碲 碳
          0x78b5_78b5, //  碵
          0x78ba_78bf, //  確 碻 碼 碽 碾 碿
          0x78c1_78c1, //  磁
          0x78c5_78cc, //  磅 磆 磇 磈 磉 磊 磋 磌
          0x78ce_78ce, //  磎
          0x78d0_78d6, //  磐 磑 磒 磓 磔 磕 磖
          0x78da_78db, //  磚 磛
          0x78df_78e1, //  磟 磠 磡
          0x78e4_78e4, //  磤
          0x78e6_78e8, //  磦 磧 磨
          0x78ea_78ea, //  磪
          0x78ec_78ec, //  磬
          0x78ef_78ef, //  磯
          0x78f2_78f4, //  磲 磳 磴
          0x78f6_78f7, //  磶 磷
          0x78f9_78fb, //  磹 磺 磻
          0x78fd_7901, //  磽 磾 磿 礀 礁
          0x7906_7907, //  礆 礇
          0x790c_790c, //  礌
          0x790e_790e, //  礎
          0x7910_7912, //  礐 礑 礒
          0x7919_791c, //  礙 礚 礛 礜
          0x791e_7920, //  礞 礟 礠
          0x7925_7927, //  礥 礦 礧
          0x7929_792e, //  礩 礪 礫 礬 礭 礮
          0x7930_7931, //  礰 礱
          0x7934_7935, //  礴 礵
          0x793a_7941, //  示 礻 礼 礽 社 礿 祀 祁
          0x7944_794b, //  祄 祅 祆 祇 祈 祉 祊 祋
          0x794f_7951, //  祏 祐 祑
          0x7953_7958, //  祓 祔 祕 祖 祗 祘
          0x795a_7960, //  祚 祛 祜 祝 神 祟 祠
          0x7962_7962, //  祢
          0x7965_7965, //  祥
          0x7967_7969, //  祧 票 祩
          0x796b_796b, //  祫
          0x796d_796d, //  祭
          0x7972_7972, //  祲
          0x7977_7977, //  祷
          0x7979_797c, //  祹 祺 祻 祼
          0x797e_7981, //  祾 祿 禀 禁
          0x7984_7985, //  禄 禅
          0x798a_798f, //  禊 禋 禌 禍 禎 福
          0x7991_7991, //  禑
          0x7993_7996, //  禓 禔 禕 禖
          0x7998_7998, //  禘
          0x799b_799d, //  禛 禜 禝
          0x79a1_79a1, //  禡
          0x79a6_79ab, //  禦 禧 禨 禩 禪 禫
          0x79ae_79b1, //  禮 禯 禰 禱
          0x79b3_79b4, //  禳 禴
          0x79b8_79bb, //  禸 禹 禺 离
          0x79bd_79c2, //  禽 禾 禿 秀 私 秂
          0x79c4_79c4, //  秄
          0x79c7_79cd, //  秇 秈 秉 秊 秋 秌 种
          0x79cf_79cf, //  秏
          0x79d1_79d2, //  科 秒
          0x79d4_79d6, //  秔 秕 秖
          0x79d8_79d8, //  秘
          0x79da_79da, //  秚
          0x79dd_79e7, //  秝 秞 租 秠 秡 秢 秣 秤 秥 秦 秧
          0x79e9_79ed, //  秩 秪 秫 秬 秭
          0x79f0_79f1, //  称 秱
          0x79f8_79f8, //  秸
          0x79fb_79fc, //  移 秼
          0x7a00_7a00, //  稀
          0x7a02_7a03, //  稂 稃
          0x7a07_7a0e, //  稇 稈 稉 稊 程 稌 稍 税
          0x7a11_7a11, //  稑
          0x7a14_7a15, //  稔 稕
          0x7a17_7a1c, //  稗 稘 稙 稚 稛 稜
          0x7a1e_7a21, //  稞 稟 稠 稡
          0x7a27_7a27, //  稧
          0x7a2b_7a2b, //  稫
          0x7a2d_7a32, //  稭 種 稯 稰 稱 稲
          0x7a34_7a35, //  稴 稵
          0x7a37_7a40, //  稷 稸 稹 稺 稻 稼 稽 稾 稿 穀
          0x7a42_7a49, //  穂 穃 穄 穅 穆 穇 穈 穉
          0x7a4c_7a50, //  穌 積 穎 穏 穐
          0x7a55_7a57, //  穕 穖 穗
          0x7a59_7a59, //  穙
          0x7a5c_7a5d, //  穜 穝
          0x7a5f_7a63, //  穟 穠 穡 穢 穣
          0x7a65_7a65, //  穥
          0x7a67_7a67, //  穧
          0x7a69_7a6b, //  穩 穪 穫
          0x7a6d_7a6d, //  穭
          0x7a70_7a70, //  穰
          0x7a74_7a76, //  穴 穵 究
          0x7a78_7a7a, //  穸 穹 空
          0x7a7d_7a86, //  穽 穾 穿 窀 突 窂 窃 窄 窅 窆
          0x7a88_7a88, //  窈
          0x7a8a_7a8b, //  窊 窋
          0x7a90_7a98, //  窐 窑 窒 窓 窔 窕 窖 窗 窘
          0x7a9e_7aa0, //  窞 窟 窠
          0x7aa3_7aa3, //  窣
          0x7aa9_7aaa, //  窩 窪
          0x7aac_7aac, //  窬
          0x7aae_7ab0, //  窮 窯 窰
          0x7ab3_7ab3, //  窳
          0x7ab5_7ab6, //  窵 窶
          0x7ab9_7abc, //  窹 窺 窻 窼
          0x7abe_7abf, //  窾 窿
          0x7ac3_7acf, //  竃 竄 竅 竆 竇 竈 竉 竊 立 竌 竍 竎 竏
          0x7ad1_7ad3, //  竑 竒 竓
          0x7ad5_7ad5, //  竕
          0x7ad9_7add, //  站 竚 竛 竜 竝
          0x7adf_7ae3, //  竟 章 竡 竢 竣
          0x7ae5_7aed, //  童 竦 竧 竨 竩 竪 竫 竬 竭
          0x7aef_7af1, //  端 竰 竱
          0x7af4_7af4, //  竴
          0x7af6_7af6, //  競
          0x7af8_7afb, //  竸 竹 竺 竻
          0x7afd_7aff, //  竽 竾 竿
          0x7b02_7b02, //  笂
          0x7b04_7b04, //  笄
          0x7b06_7b08, //  笆 笇 笈
          0x7b0a_7b0b, //  笊 笋
          0x7b0f_7b0f, //  笏
          0x7b11_7b12, //  笑 笒
          0x7b14_7b14, //  笔
          0x7b18_7b19, //  笘 笙
          0x7b1b_7b1b, //  笛
          0x7b1e_7b20, //  笞 笟 笠
          0x7b23_7b23, //  笣
          0x7b25_7b31, //  笥 符 笧 笨 笩 笪 笫 第 笭 笮 笯 笰 笱
          0x7b33_7b36, //  笳 笴 笵 笶
          0x7b39_7b39, //  笹
          0x7b3b_7b3b, //  笻
          0x7b3d_7b3d, //  笽
          0x7b3f_7b41, //  笿 筀 筁
          0x7b45_7b49, //  筅 筆 筇 筈 等
          0x7b4b_7b52, //  筋 筌 筍 筎 筏 筐 筑 筒
          0x7b54_7b56, //  答 筕 策
          0x7b5d_7b5d, //  筝
          0x7b60_7b60, //  筠
          0x7b64_7b67, //  筤 筥 筦 筧
          0x7b69_7b6a, //  筩 筪
          0x7b6c_7b75, //  筬 筭 筮 筯 筰 筱 筲 筳 筴 筵
          0x7b77_7b77, //  筷
          0x7b79_7b7a, //  筹 筺
          0x7b7f_7b7f, //  筿
          0x7b84_7b84, //  箄
          0x7b86_7b87, //  箆 箇
          0x7b89_7b89, //  箉
          0x7b8b_7b8b, //  箋
          0x7b8d_7b92, //  箍 箎 箏 箐 箑 箒
          0x7b94_7ba1, //  箔 箕 箖 算 箘 箙 箚 箛 箜 箝 箞 箟 箠 管
          0x7ba5_7ba5, //  箥
          0x7baa_7baa, //  箪
          0x7bac_7bad, //  箬 箭
          0x7baf_7bb2, //  箯 箰 箱 箲
          0x7bb4_7bb6, //  箴 箵 箶
          0x7bb8_7bb8, //  箸
          0x7bba_7bbd, //  箺 箻 箼 箽
          0x7bc0_7bc2, //  節 篁 篂
          0x7bc4_7bcc, //  範 篅 篆 篇 篈 築 篊 篋 篌
          0x7bcf_7bcf, //  篏
          0x7bd4_7bd4, //  篔
          0x7bd6_7bd7, //  篖 篗
          0x7bd9_7bdb, //  篙 篚 篛
          0x7bdd_7bdd, //  篝
          0x7be0_7be0, //  篠
          0x7be4_7be6, //  篤 篥 篦
          0x7be8_7bea, //  篨 篩 篪
          0x7bed_7bed, //  篭
          0x7bf0_7bf0, //  篰
          0x7bf2_7bfa, //  篲 篳 篴 篵 篶 篷 篸 篹 篺
          0x7bfc_7bfc, //  篼
          0x7bfe_7bfe, //  篾
          0x7c00_7c04, //  簀 簁 簂 簃 簄
          0x7c06_7c07, //  簆 簇
          0x7c09_7c09, //  簉
          0x7c0b_7c0f, //  簋 簌 簍 簎 簏
          0x7c11_7c14, //  簑 簒 簓 簔
          0x7c17_7c17, //  簗
          0x7c19_7c19, //  簙
          0x7c1b_7c1b, //  簛
          0x7c1e_7c21, //  簞 簟 簠 簡
          0x7c23_7c23, //  簣
          0x7c25_7c28, //  簥 簦 簧 簨
          0x7c2a_7c2c, //  簪 簫 簬
          0x7c31_7c31, //  簱
          0x7c33_7c34, //  簳 簴
          0x7c36_7c3a, //  簶 簷 簸 簹 簺
          0x7c3d_7c40, //  簽 簾 簿 籀
          0x7c43_7c43, //  籃
          0x7c45_7c46, //  籅 籆
          0x7c4a_7c4a, //  籊
          0x7c4c_7c4d, //  籌 籍
          0x7c4f_7c61, //  籏 籐 籑 籒 籓 籔 籕 籖 籗 籘 籙 籚 籛 籜 籝 籞 籟 籠 籡
          0x7c63_7c65, //  籣 籤 籥
          0x7c67_7c67, //  籧
          0x7c69_7c69, //  籩
          0x7c6c_7c70, //  籬 籭 籮 籯 籰
          0x7c72_7c73, //  籲 米
          0x7c75_7c75, //  籵
          0x7c79_7c79, //  籹
          0x7c7c_7c7e, //  籼 籽 籾
          0x7c81_7c83, //  粁 粂 粃
          0x7c86_7c87, //  粆 粇
          0x7c89_7c89, //  粉
          0x7c8b_7c8b, //  粋
          0x7c8d_7c8d, //  粍
          0x7c8f_7c90, //  粏 粐
          0x7c92_7c92, //  粒
          0x7c94_7c95, //  粔 粕
          0x7c97_7c98, //  粗 粘
          0x7c9b_7c9b, //  粛
          0x7c9e_7ca2, //  粞 粟 粠 粡 粢
          0x7ca4_7ca8, //  粤 粥 粦 粧 粨
          0x7cab_7cab, //  粫
          0x7cad_7cae, //  粭 粮
          0x7cb0_7cb3, //  粰 粱 粲 粳
          0x7cb6_7cb7, //  粶 粷
          0x7cb9_7cc0, //  粹 粺 粻 粼 粽 精 粿 糀
          0x7cc2_7cc2, //  糂
          0x7cc4_7cc5, //  糄 糅
          0x7cc7_7cca, //  糇 糈 糉 糊
          0x7ccd_7ccf, //  糍 糎 糏
          0x7cd2_7cda, //  糒 糓 糔 糕 糖 糗 糘 糙 糚
          0x7cdc_7ce0, //  糜 糝 糞 糟 糠
          0x7ce2_7ce2, //  糢
          0x7ce6_7ce7, //  糦 糧
          0x7ce9_7ce9, //  糩
          0x7ceb_7ceb, //  糫
          0x7cef_7cef, //  糯
          0x7cf2_7cf2, //  糲
          0x7cf4_7cf6, //  糴 糵 糶
          0x7cf8_7cf8, //  糸
          0x7cfa_7cfb, //  糺 系
          0x7cfe_7cfe, //  糾
          0x7d00_7d00, //  紀
          0x7d02_7d0b, //  紂 紃 約 紅 紆 紇 紈 紉 紊 紋
          0x7d0d_7d0d, //  納
          0x7d0f_7d1e, //  紏 紐 紑 紒 紓 純 紕 紖 紗 紘 紙 級 紛 紜 紝 紞
          0x7d20_7d23, //  素 紡 索 紣
          0x7d26_7d26, //  紦
          0x7d2a_7d33, //  紪 紫 紬 紭 紮 累 細 紱 紲 紳
          0x7d35_7d35, //  紵
          0x7d39_7d3a, //  紹 紺
          0x7d3c_7d48, //  紼 紽 紾 紿 絀 絁 終 絃 組 絅 絆 絇 絈
          0x7d4b_7d51, //  絋 経 絍 絎 絏 結 絑
          0x7d53_7d53, //  絓
          0x7d56_7d57, //  絖 絗
          0x7d59_7d5e, //  絙 絚 絛 絜 絝 絞
          0x7d61_7d63, //  絡 絢 絣
          0x7d65_7d68, //  絥 給 絧 絨
          0x7d6a_7d6a, //  絪
          0x7d6e_7d6e, //  絮
          0x7d70_7d73, //  絰 統 絲 絳
          0x7d75_7d76, //  絵 絶
          0x7d78_7d7b, //  絸 絹 絺 絻
          0x7d7d_7d7d, //  絽
          0x7d7f_7d7f, //  絿
          0x7d81_7d83, //  綁 綂 綃
          0x7d85_7d86, //  綅 綆
          0x7d88_7d89, //  綈 綉
          0x7d8b_7d8d, //  綋 綌 綍
          0x7d8f_7d8f, //  綏
          0x7d91_7d91, //  綑
          0x7d93_7d93, //  經
          0x7d96_7d97, //  綖 綗
          0x7d99_7da0, //  継 続 綛 綜 綝 綞 綟 綠
          0x7da2_7da3, //  綢 綣
          0x7da6_7da7, //  綦 綧
          0x7daa_7dbb, //  綪 綫 綬 維 綮 綯 綰 綱 網 綳 綴 綵 綶 綷 綸 綹 綺 綻
          0x7dbd_7dc0, //  綽 綾 綿 緀
          0x7dc2_7dc7, //  緂 緃 緄 緅 緆 緇
          0x7dca_7dcf, //  緊 緋 緌 緍 緎 総
          0x7dd1_7dd2, //  緑 緒
          0x7dd5_7dda, //  緕 緖 緗 緘 緙 線
          0x7ddc_7dde, //  緜 緝 緞
          0x7de0_7de6, //  締 緡 緢 緣 緤 緥 緦
          0x7de8_7ded, //  編 緩 緪 緫 緬 緭
          0x7def_7def, //  緯
          0x7df1_7df2, //  緱 緲
          0x7df4_7df6, //  練 緵 緶
          0x7df9_7dfb, //  緹 緺 緻
          0x7e00_7e01, //  縀 縁
          0x7e04_7e05, //  縄 縅
          0x7e08_7e0b, //  縈 縉 縊 縋
          0x7e10_7e12, //  縐 縑 縒
          0x7e15_7e15, //  縕
          0x7e17_7e17, //  縗
          0x7e1b_7e23, //  縛 縜 縝 縞 縟 縠 縡 縢 縣
          0x7e26_7e28, //  縦 縧 縨
          0x7e2b_7e2f, //  縫 縬 縭 縮 縯
          0x7e31_7e33, //  縱 縲 縳
          0x7e35_7e37, //  縵 縶 縷
          0x7e39_7e3b, //  縹 縺 縻
          0x7e3d_7e3f, //  總 績 縿
          0x7e41_7e41, //  繁
          0x7e43_7e47, //  繃 繄 繅 繆 繇
          0x7e4a_7e4b, //  繊 繋
          0x7e4d_7e4e, //  繍 繎
          0x7e50_7e50, //  繐
          0x7e52_7e52, //  繒
          0x7e54_7e56, //  織 繕 繖
          0x7e58_7e5a, //  繘 繙 繚
          0x7e5d_7e5f, //  繝 繞 繟
          0x7e61_7e62, //  繡 繢
          0x7e65_7e67, //  繥 繦 繧
          0x7e69_7e6b, //  繩 繪 繫
          0x7e6d_7e70, //  繭 繮 繯 繰
          0x7e73_7e73, //  繳
          0x7e75_7e75, //  繵
          0x7e78_7e79, //  繸 繹
          0x7e7b_7e7f, //  繻 繼 繽 繾 繿
          0x7e81_7e83, //  纁 纂 纃
          0x7e86_7e8a, //  纆 纇 纈 纉 纊
          0x7e8c_7e96, //  續 纍 纎 纏 纐 纑 纒 纓 纔 纕 纖
          0x7e98_7e98, //  纘
          0x7e9a_7e9e, //  纚 纛 纜 纝 纞
          0x7f36_7f36, //  缶
          0x7f38_7f38, //  缸
          0x7f3a_7f3f, //  缺 缻 缼 缽 缾 缿
          0x7f43_7f45, //  罃 罄 罅
          0x7f47_7f47, //  罇
          0x7f4c_7f55, //  罌 罍 罎 罏 罐 网 罒 罓 罔 罕
          0x7f58_7f58, //  罘
          0x7f5b_7f5d, //  罛 罜 罝
          0x7f5f_7f61, //  罟 罠 罡
          0x7f63_7f6b, //  罣 罤 罥 罦 罧 罨 罩 罪 罫
          0x7f6d_7f6e, //  罭 置
          0x7f70_7f72, //  罰 罱 署
          0x7f75_7f75, //  罵
          0x7f77_7f79, //  罷 罸 罹
          0x7f7d_7f80, //  罽 罾 罿 羀
          0x7f82_7f83, //  羂 羃
          0x7f85_7f88, //  羅 羆 羇 羈
          0x7f8a_7f91, //  羊 羋 羌 羍 美 羏 羐 羑
          0x7f94_7f94, //  羔
          0x7f96_7f97, //  羖 羗
          0x7f9a_7f9a, //  羚
          0x7f9c_7f9e, //  羜 羝 羞
          0x7fa1_7fa4, //  羡 羢 羣 群
          0x7fa6_7fa6, //  羦
          0x7fa8_7faa, //  羨 義 羪
          0x7fad_7faf, //  羭 羮 羯
          0x7fb2_7fb2, //  羲
          0x7fb4_7fb4, //  羴
          0x7fb6_7fb6, //  羶
          0x7fb8_7fb9, //  羸 羹
          0x7fbc_7fbd, //  羼 羽
          0x7fbf_7fc1, //  羿 翀 翁
          0x7fc3_7fc3, //  翃
          0x7fc5_7fc6, //  翅 翆
          0x7fc8_7fc8, //  翈
          0x7fca_7fca, //  翊
          0x7fcc_7fcc, //  翌
          0x7fce_7fcf, //  翎 翏
          0x7fd2_7fd2, //  習
          0x7fd4_7fd5, //  翔 翕
          0x7fdb_7fdb, //  翛
          0x7fdf_7fe1, //  翟 翠 翡
          0x7fe3_7fe3, //  翣
          0x7fe5_7fe6, //  翥 翦
          0x7fe8_7fe9, //  翨 翩
          0x7feb_7fec, //  翫 翬
          0x7fee_7ff0, //  翮 翯 翰
          0x7ff2_7ff3, //  翲 翳
          0x7ff9_8008, //  翹 翺 翻 翼 翽 翾 翿 耀 老 耂 考 耄 者 耆 耇 耈
          0x800a_8019, //  耊 耋 而 耍 耎 耏 耐 耑 耒 耓 耔 耕 耖 耗 耘 耙
          0x801c_8021, //  耜 耝 耞 耟 耠 耡
          0x8024_8024, //  耤
          0x8026_8026, //  耦
          0x8028_8028, //  耨
          0x802c_802c, //  耬
          0x802e_802e, //  耮
          0x8030_8030, //  耰
          0x8033_8037, //  耳 耴 耵 耶 耷
          0x8039_8040, //  耹 耺 耻 耼 耽 耾 耿 聀
          0x8043_8044, //  聃 聄
          0x8046_8046, //  聆
          0x804a_804a, //  聊
          0x8052_8052, //  聒
          0x8056_8056, //  聖
          0x8058_8058, //  聘
          0x805a_805a, //  聚
          0x805e_8062, //  聞 聟 聠 聡 聢
          0x8064_8064, //  聤
          0x8066_8066, //  聦
          0x8068_8068, //  聨
          0x806d_806d, //  聭
          0x806f_8077, //  聯 聰 聱 聲 聳 聴 聵 聶 職
          0x8079_8079, //  聹
          0x807b_807b, //  聻
          0x807d_807f, //  聽 聾 聿
          0x8081_8081, //  肁
          0x8084_8089, //  肄 肅 肆 肇 肈 肉
          0x808b_808c, //  肋 肌
          0x808e_808e, //  肎
          0x8093_8093, //  肓
          0x8096_8096, //  肖
          0x8098_809e, //  肘 肙 肚 肛 肜 肝 肞
          0x80a1_80a2, //  股 肢
          0x80a4_80a7, //  肤 肥 肦 肧
          0x80a9_80ad, //  肩 肪 肫 肬 肭
          0x80af_80af, //  肯
          0x80b1_80b2, //  肱 育
          0x80b4_80b4, //  肴
          0x80b8_80ba, //  肸 肹 肺
          0x80c3_80c6, //  胃 胄 胅 胆
          0x80c8_80c8, //  胈
          0x80ca_80ca, //  胊
          0x80cc_80cf, //  背 胍 胎 胏
          0x80d2_80d2, //  胒
          0x80d4_80db, //  胔 胕 胖 胗 胘 胙 胚 胛
          0x80dd_80de, //  胝 胞
          0x80e0_80e1, //  胠 胡
          0x80e4_80e6, //  胤 胥 胦
          0x80ed_80f6, //  胭 胮 胯 胰 胱 胲 胳 胴 胵 胶
          0x80f8_80fe, //  胸 胹 胺 胻 胼 能 胾
          0x8102_8103, //  脂 脃
          0x8105_810b, //  脅 脆 脇 脈 脉 脊 脋
          0x810d_810d, //  脍
          0x8116_8118, //  脖 脗 脘
          0x811a_811c, //  脚 脛 脜
          0x811e_811e, //  脞
          0x8120_8120, //  脠
          0x8123_8124, //  脣 脤
          0x8127_8127, //  脧
          0x8129_8129, //  脩
          0x812c_812c, //  脬
          0x812f_8131, //  脯 脰 脱
          0x8133_8133, //  脳
          0x8135_8135, //  脵
          0x8139_813a, //  脹 脺
          0x813c_813e, //  脼 脽 脾
          0x8145_8147, //  腅 腆 腇
          0x814a_814c, //  腊 腋 腌
          0x814e_814e, //  腎
          0x8150_8155, //  腐 腑 腒 腓 腔 腕
          0x8157_8157, //  腗
          0x815f_8161, //  腟 腠 腡
          0x8165_8169, //  腥 腦 腧 腨 腩
          0x816b_816b, //  腫
          0x816d_8171, //  腭 腮 腯 腰 腱
          0x8174_8174, //  腴
          0x8177_817a, //  腷 腸 腹 腺
          0x817f_8186, //  腿 膀 膁 膂 膃 膄 膅 膆
          0x8188_8188, //  膈
          0x818a_818b, //  膊 膋
          0x818e_8190, //  膎 膏 膐
          0x8193_8193, //  膓
          0x8195_8196, //  膕 膖
          0x8198_8198, //  膘
          0x819a_819e, //  膚 膛 膜 膝 膞
          0x81a0_81a0, //  膠
          0x81a2_81a4, //  膢 膣 膤
          0x81a8_81a9, //  膨 膩
          0x81ae_81ae, //  膮
          0x81b0_81b0, //  膰
          0x81b2_81b5, //  膲 膳 膴 膵
          0x81b8_81b8, //  膸
          0x81ba_81bb, //  膺 膻
          0x81bd_81c3, //  膽 膾 膿 臀 臁 臂 臃
          0x81c5_81c6, //  臅 臆
          0x81c8_81cb, //  臈 臉 臊 臋
          0x81cd_81cf, //  臍 臎 臏
          0x81d1_81d1, //  臑
          0x81d3_81d3, //  臓
          0x81d5_81db, //  臕 臖 臗 臘 臙 臚 臛
          0x81dd_81e1, //  臝 臞 臟 臠 臡
          0x81e3_81e5, //  臣 臤 臥
          0x81e7_81e8, //  臧 臨
          0x81ea_81ed, //  自 臫 臬 臭
          0x81f0_81f6, //  臰 臱 臲 至 致 臵 臶
          0x81f8_8205, //  臸 臹 臺 臻 臼 臽 臾 臿 舀 舁 舂 舃 舄 舅
          0x8207_820a, //  與 興 舉 舊
          0x820c_8210, //  舌 舍 舎 舏 舐
          0x8212_8214, //  舒 舓 舔
          0x8216_821f, //  舖 舗 舘 舙 舚 舛 舜 舝 舞 舟
          0x8221_8222, //  舡 舢
          0x8228_822c, //  舨 舩 航 舫 般
          0x822e_822e, //  舮
          0x8232_823a, //  舲 舳 舴 舵 舶 舷 舸 船 舺
          0x823c_823c, //  舼
          0x8240_8240, //  艀
          0x8243_8247, //  艃 艄 艅 艆 艇
          0x8249_8249, //  艉
          0x824b_824b, //  艋
          0x824e_824f, //  艎 艏
          0x8251_8251, //  艑
          0x8256_825a, //  艖 艗 艘 艙 艚
          0x825c_825d, //  艜 艝
          0x825f_8260, //  艟 艠
          0x8262_8264, //  艢 艣 艤
          0x8266_8268, //  艦 艧 艨
          0x826a_826b, //  艪 艫
          0x826d_826f, //  艭 艮 良
          0x8271_8272, //  艱 色
          0x8274_8274, //  艴
          0x8276_8279, //  艶 艷 艸 艹
          0x827b_827b, //  艻
          0x827d_8281, //  艽 艾 艿 芀 芁
          0x8283_8284, //  芃 芄
          0x8287_8287, //  芇
          0x8289_828b, //  芉 芊 芋
          0x828d_828e, //  芍 芎
          0x8291_8294, //  芑 芒 芓 芔
          0x8296_8296, //  芖
          0x8298_829b, //  芘 芙 芚 芛
          0x829d_829d, //  芝
          0x829f_82a1, //  芟 芠 芡
          0x82a3_82b4, //  芣 芤 芥 芦 芧 芨 芩 芪 芫 芬 芭 芮 芯 芰 花 芲 芳 芴
          0x82b7_82bf, //  芷 芸 芹 芺 芻 芼 芽 芾 芿
          0x82c5_82c6, //  苅 苆
          0x82d0_82d5, //  苐 苑 苒 苓 苔 苕
          0x82d7_82d7, //  苗
          0x82d9_82dc, //  苙 苚 苛 苜
          0x82de_82e8, //  苞 苟 苠 苡 苢 苣 苤 若 苦 苧 苨
          0x82ea_82eb, //  苪 苫
          0x82ed_82ed, //  苭
          0x82ef_82ef, //  苯
          0x82f1_82f1, //  英
          0x82f3_82f4, //  苳 苴
          0x82f6_82f7, //  苶 苷
          0x82f9_82fb, //  苹 苺 苻
          0x82fd_82fe, //  苽 苾
          0x8300_830c, //  茀 茁 茂 范 茄 茅 茆 茇 茈 茉 茊 茋 茌
          0x830e_830e, //  茎
          0x8316_8318, //  茖 茗 茘
          0x831b_831f, //  茛 茜 茝 茞 茟
          0x8321_8323, //  茡 茢 茣
          0x8328_8328, //  茨
          0x832b_833a, //  茫 茬 茭 茮 茯 茰 茱 茲 茳 茴 茵 茶 茷 茸 茹 茺
          0x833c_833d, //  茼 茽
          0x8340_8340, //  荀
          0x8342_8345, //  荂 荃 荄 荅
          0x8347_8347, //  荇
          0x8349_834a, //  草 荊
          0x834d_8358, //  荍 荎 荏 荐 荑 荒 荓 荔 荕 荖 荗 荘
          0x8362_8363, //  荢 荣
          0x8370_8370, //  荰
          0x8373_8373, //  荳
          0x8375_8375, //  荵
          0x8377_8378, //  荷 荸
          0x837b_837d, //  荻 荼 荽
          0x837f_8380, //  荿 莀
          0x8382_8382, //  莂
          0x8384_8387, //  莄 莅 莆 莇
          0x8389_838a, //  莉 莊
          0x838d_838e, //  莍 莎
          0x8392_8396, //  莒 莓 莔 莕 莖
          0x8398_83a0, //  莘 莙 莚 莛 莜 莝 莞 莟 莠
          0x83a2_83a2, //  莢
          0x83a6_83ad, //  莦 莧 莨 莩 莪 莫 莬 莭
          0x83b1_83b1, //  莱
          0x83b5_83b5, //  莵
          0x83bd_83c1, //  莽 莾 莿 菀 菁
          0x83c5_83c5, //  菅
          0x83c7_83c7, //  菇
          0x83c9_83ca, //  菉 菊
          0x83cc_83cc, //  菌
          0x83ce_83d1, //  菎 菏 菐 菑
          0x83d3_83d4, //  菓 菔
          0x83d6_83d6, //  菖
          0x83d8_83d8, //  菘
          0x83dc_83dd, //  菜 菝
          0x83df_83e1, //  菟 菠 菡
          0x83e5_83e5, //  菥
          0x83e8_83eb, //  菨 菩 菪 菫
          0x83ef_83f2, //  華 菰 菱 菲
          0x83f4_83f4, //  菴
          0x83f6_83f9, //  菶 菷 菸 菹
          0x83fb_83fd, //  菻 菼 菽
          0x8401_8401, //  萁
          0x8403_8404, //  萃 萄
          0x8406_8407, //  萆 萇
          0x840a_840f, //  萊 萋 萌 萍 萎 萏
          0x8411_8411, //  萑
          0x8413_8413, //  萓
          0x8415_8415, //  萕
          0x8417_8417, //  萗
          0x8419_8419, //  萙
          0x8420_8420, //  萠
          0x8422_8422, //  萢
          0x8429_842a, //  萩 萪
          0x842c_842c, //  萬
          0x842f_842f, //  萯
          0x8431_8431, //  萱
          0x8435_8435, //  萵
          0x8438_8439, //  萸 萹
          0x843c_843d, //  萼 落
          0x8445_844a, //  葅 葆 葇 葈 葉 葊
          0x844d_844f, //  葍 葎 葏
          0x8451_8452, //  葑 葒
          0x8456_845c, //  葖 著 葘 葙 葚 葛 葜
          0x845f_8467, //  葟 葠 葡 葢 董 葤 葥 葦 葧
          0x8469_8471, //  葩 葪 葫 葬 葭 葮 葯 葰 葱
          0x8473_847a, //  葳 葴 葵 葶 葷 葸 葹 葺
          0x847c_847d, //  葼 葽
          0x8481_8482, //  蒁 蒂
          0x8484_8485, //  蒄 蒅
          0x848b_848b, //  蒋
          0x8490_8490, //  蒐
          0x8492_8495, //  蒒 蒓 蒔 蒕
          0x8497_8497, //  蒗
          0x8499_8499, //  蒙
          0x849c_849c, //  蒜
          0x849e_849f, //  蒞 蒟
          0x84a1_84a1, //  蒡
          0x84a6_84a6, //  蒦
          0x84a8_84aa, //  蒨 蒩 蒪
          0x84ad_84ad, //  蒭
          0x84af_84af, //  蒯
          0x84b1_84b2, //  蒱 蒲
          0x84b4_84b4, //  蒴
          0x84b8_84c2, //  蒸 蒹 蒺 蒻 蒼 蒽 蒾 蒿 蓀 蓁 蓂
          0x84c4_84c4, //  蓄
          0x84c6_84d1, //  蓆 蓇 蓈 蓉 蓊 蓋 蓌 蓍 蓎 蓏 蓐 蓑
          0x84d3_84d3, //  蓓
          0x84d6_84d6, //  蓖
          0x84d9_84da, //  蓙 蓚
          0x84dc_84dc, //  蓜
          0x84e7_84e7, //  蓧
          0x84ea_84ea, //  蓪
          0x84ec_84ec, //  蓬
          0x84ee_84f2, //  蓮 蓯 蓰 蓱 蓲
          0x84f4_84f4, //  蓴
          0x84f7_84f7, //  蓷
          0x84fa_84fd, //  蓺 蓻 蓼 蓽
          0x84ff_8500, //  蓿 蔀
          0x8502_8503, //  蔂 蔃
          0x8506_8507, //  蔆 蔇
          0x850c_850c, //  蔌
          0x850e_850e, //  蔎
          0x8510_8511, //  蔐 蔑
          0x8513_8515, //  蔓 蔔 蔕
          0x8517_8518, //  蔗 蔘
          0x851a_851c, //  蔚 蔛 蔜
          0x851e_851f, //  蔞 蔟
          0x8521_8527, //  蔡 蔢 蔣 蔤 蔥 蔦 蔧
          0x852a_852d, //  蔪 蔫 蔬 蔭
          0x852f_852f, //  蔯
          0x8532_8536, //  蔲 蔳 蔴 蔵 蔶
          0x853d_8541, //  蔽 蔾 蔿 蕀 蕁
          0x8543_8543, //  蕃
          0x8546_8546, //  蕆
          0x8548_854b, //  蕈 蕉 蕊 蕋
          0x854e_8553, //  蕎 蕏 蕐 蕑 蕒 蕓
          0x8555_855a, //  蕕 蕖 蕗 蕘 蕙 蕚
          0x855c_8564, //  蕜 蕝 蕞 蕟 蕠 蕡 蕢 蕣 蕤
          0x8568_856b, //  蕨 蕩 蕪 蕫
          0x856d_856d, //  蕭
          0x856f_856f, //  蕯
          0x8577_8577, //  蕷
          0x8579_857b, //  蕹 蕺 蕻
          0x857d_8581, //  蕽 蕾 蕿 薀 薁
          0x8584_858c, //  薄 薅 薆 薇 薈 薉 薊 薋 薌
          0x858f_8591, //  薏 薐 薑
          0x8593_8594, //  薓 薔
          0x8597_8599, //  薗 薘 薙
          0x859b_859d, //  薛 薜 薝
          0x859f_85a0, //  薟 薠
          0x85a2_85a2, //  薢
          0x85a4_85b0, //  薤 薥 薦 薧 薨 薩 薪 薫 薬 薭 薮 薯 薰
          0x85b4_85b4, //  薴
          0x85b6_85ba, //  薶 薷 薸 薹 薺
          0x85bc_85bf, //  薼 薽 薾 薿
          0x85c1_85c2, //  藁 藂
          0x85c7_85c7, //  藇
          0x85c9_85cb, //  藉 藊 藋
          0x85cd_85d0, //  藍 藎 藏 藐
          0x85d5_85d5, //  藕
          0x85d8_85da, //  藘 藙 藚
          0x85dc_85dd, //  藜 藝
          0x85df_85e1, //  藟 藠 藡
          0x85e4_85e6, //  藤 藥 藦
          0x85e8_85ea, //  藨 藩 藪
          0x85ed_85ed, //  藭
          0x85f3_85f3, //  藳
          0x85f6_85f7, //  藶 藷
          0x85f9_85fc, //  藹 藺 藻 藼
          0x85fe_8600, //  藾 藿 蘀
          0x8602_8602, //  蘂
          0x8604_8607, //  蘄 蘅 蘆 蘇
          0x860a_860b, //  蘊 蘋
          0x860d_860e, //  蘍 蘎
          0x8610_8613, //  蘐 蘑 蘒 蘓
          0x8616_861b, //  蘖 蘗 蘘 蘙 蘚 蘛
          0x861e_861e, //  蘞
          0x8621_8622, //  蘡 蘢
          0x8624_8624, //  蘤
          0x8627_8627, //  蘧
          0x8629_8629, //  蘩
          0x862d_862d, //  蘭
          0x862f_8630, //  蘯 蘰
          0x8636_8636, //  蘶
          0x8638_863a, //  蘸 蘹 蘺
          0x863c_863d, //  蘼 蘽
          0x863f_8642, //  蘿 虀 虁 虂
          0x8646_8646, //  虆
          0x864d_864e, //  虍 虎
          0x8650_8650, //  虐
          0x8652_8664, //  虒 虓 虔 處 虖 虗 虘 虙 虚 虛 虜 虝 虞 號 虠 虡 虢 虣 虤
          0x8667_8667, //  虧
          0x8669_8669, //  虩
          0x866b_866c, //  虫 虬
          0x866f_866f, //  虯
          0x8671_8671, //  虱
          0x8675_8677, //  虵 虶 虷
          0x8679_867b, //  虹 虺 虻
          0x8687_868d, //  蚇 蚈 蚉 蚊 蚋 蚌 蚍
          0x8691_8691, //  蚑
          0x8693_8693, //  蚓
          0x8695_8696, //  蚕 蚖
          0x8698_8698, //  蚘
          0x869a_869a, //  蚚
          0x869c_869d, //  蚜 蚝
          0x86a1_86a1, //  蚡
          0x86a3_86a4, //  蚣 蚤
          0x86a6_86ab, //  蚦 蚧 蚨 蚩 蚪 蚫
          0x86ad_86ad, //  蚭
          0x86af_86b1, //  蚯 蚰 蚱
          0x86b3_86b9, //  蚳 蚴 蚵 蚶 蚷 蚸 蚹
          0x86bf_86c1, //  蚿 蛀 蛁
          0x86c3_86c7, //  蛃 蛄 蛅 蛆 蛇
          0x86c9_86c9, //  蛉
          0x86cb_86cb, //  蛋
          0x86cd_86ce, //  蛍 蛎
          0x86d1_86d2, //  蛑 蛒
          0x86d4_86d5, //  蛔 蛕
          0x86d7_86d7, //  蛗
          0x86d9_86dc, //  蛙 蛚 蛛 蛜
          0x86de_86e0, //  蛞 蛟 蛠
          0x86e3_86e7, //  蛣 蛤 蛥 蛦 蛧
          0x86e9_86e9, //  蛩
          0x86ec_86ef, //  蛬 蛭 蛮 蛯
          0x86f8_86fe, //  蛸 蛹 蛺 蛻 蛼 蛽 蛾
          0x8700_8700, //  蜀
          0x8702_870b, //  蜂 蜃 蜄 蜅 蜆 蜇 蜈 蜉 蜊 蜋
          0x870d_8714, //  蜍 蜎 蜏 蜐 蜑 蜒 蜓 蜔
          0x8718_871a, //  蜘 蜙 蜚
          0x871c_871c, //  蜜
          0x871e_871f, //  蜞 蜟
          0x8721_8721, //  蜡
          0x8723_8723, //  蜣
          0x8725_8725, //  蜥
          0x8728_8729, //  蜨 蜩
          0x872e_872f, //  蜮 蜯
          0x8731_8732, //  蜱 蜲
          0x8734_8734, //  蜴
          0x8737_8737, //  蜷
          0x8739_8740, //  蜹 蜺 蜻 蜼 蜽 蜾 蜿 蝀
          0x8743_8743, //  蝃
          0x8745_8745, //  蝅
          0x8749_8749, //  蝉
          0x874b_874e, //  蝋 蝌 蝍 蝎
          0x8751_8751, //  蝑
          0x8753_8753, //  蝓
          0x8755_8755, //  蝕
          0x8757_8759, //  蝗 蝘 蝙
          0x875d_875d, //  蝝
          0x875f_8761, //  蝟 蝠 蝡
          0x8763_8766, //  蝣 蝤 蝥 蝦
          0x8768_8768, //  蝨
          0x876a_876a, //  蝪
          0x876e_876f, //  蝮 蝯
          0x8771_8772, //  蝱 蝲
          0x8774_8774, //  蝴
          0x8776_8776, //  蝶
          0x8778_8778, //  蝸
          0x877b_877c, //  蝻 蝼
          0x877f_877f, //  蝿
          0x8782_8789, //  螂 螃 螄 螅 螆 螇 螈 螉
          0x878b_878d, //  螋 螌 融
          0x8790_8790, //  螐
          0x8793_8793, //  螓
          0x8795_8795, //  螕
          0x8797_8799, //  螗 螘 螙
          0x879e_87a0, //  螞 螟 螠
          0x87a2_87a3, //  螢 螣
          0x87a7_87a7, //  螧
          0x87ab_87af, //  螫 螬 螭 螮 螯
          0x87b1_87b1, //  螱
          0x87b3_87b3, //  螳
          0x87b5_87b5, //  螵
          0x87ba_87bb, //  螺 螻
          0x87bd_87c1, //  螽 螾 螿 蟀 蟁
          0x87c4_87c4, //  蟄
          0x87c6_87cb, //  蟆 蟇 蟈 蟉 蟊 蟋
          0x87ce_87ce, //  蟎
          0x87d0_87d0, //  蟐
          0x87d2_87d2, //  蟒
          0x87d5_87d6, //  蟕 蟖
          0x87d9_87da, //  蟙 蟚
          0x87dc_87dc, //  蟜
          0x87df_87e0, //  蟟 蟠
          0x87e2_87e6, //  蟢 蟣 蟤 蟥 蟦
          0x87ea_87ed, //  蟪 蟫 蟬 蟭
          0x87ef_87ef, //  蟯
          0x87f1_87f3, //  蟱 蟲 蟳
          0x87f5_87fb, //  蟵 蟶 蟷 蟸 蟹 蟺 蟻
          0x87fe_87ff, //  蟾 蟿
          0x8801_8801, //  蠁
          0x8803_8803, //  蠃
          0x8805_8806, //  蠅 蠆
          0x8809_880b, //  蠉 蠊 蠋
          0x880d_8816, //  蠍 蠎 蠏 蠐 蠑 蠒 蠓 蠔 蠕 蠖
          0x8818_881c, //  蠘 蠙 蠚 蠛 蠜
          0x881e_881f, //  蠞 蠟
          0x8821_8823, //  蠡 蠢 蠣
          0x8827_8828, //  蠧 蠨
          0x882d_882e, //  蠭 蠮
          0x8830_8832, //  蠰 蠱 蠲
          0x8835_8836, //  蠵 蠶
          0x8839_883c, //  蠹 蠺 蠻 蠼
          0x8840_8846, //  血 衁 衂 衃 衄 衅 衆
          0x8848_884e, //  衈 衉 衊 衋 行 衍 衎
          0x8851_8853, //  衑 衒 術
          0x8855_8864, //  衕 衖 街 衘 衙 衚 衛 衜 衝 衞 衟 衠 衡 衢 衣 衤
          0x8868_8869, //  表 衩
          0x886b_886b, //  衫
          0x886f_8872, //  衯 衰 衱 衲
          0x8875_8875, //  衵
          0x8877_8877, //  衷
          0x8879_8879, //  衹
          0x887b_887b, //  衻
          0x887d_8882, //  衽 衾 衿 袀 袁 袂
          0x8888_8888, //  袈
          0x888b_888b, //  袋
          0x888d_888d, //  袍
          0x8892_8892, //  袒
          0x8896_889c, //  袖 袗 袘 袙 袚 袛 袜
          0x889e_88a0, //  袞 袟 袠
          0x88a2_88a2, //  袢
          0x88a4_88a4, //  袤
          0x88a8_88a8, //  袨
          0x88aa_88ab, //  袪 被
          0x88ae_88ae, //  袮
          0x88b0_88b1, //  袰 袱
          0x88b4_88b5, //  袴 袵
          0x88b7_88b7, //  袷
          0x88ba_88ba, //  袺
          0x88bc_88c5, //  袼 袽 袾 袿 裀 裁 裂 裃 裄 装
          0x88ca_88cf, //  裊 裋 裌 裍 裎 裏
          0x88d1_88d5, //  裑 裒 裓 裔 裕
          0x88d8_88d9, //  裘 裙
          0x88db_88df, //  裛 補 裝 裞 裟
          0x88e1_88e1, //  裡
          0x88e7_88e8, //  裧 裨
          0x88ef_88f5, //  裯 裰 裱 裲 裳 裴 裵
          0x88f7_88f9, //  裷 裸 裹
          0x88fc_88fe, //  裼 製 裾
          0x8901_8902, //  褁 褂
          0x8904_8904, //  褄
          0x8906_8907, //  褆 複
          0x890a_890a, //  褊
          0x890c_8910, //  褌 褍 褎 褏 褐
          0x8912_8913, //  褒 褓
          0x8915_8916, //  褕 褖
          0x8918_891a, //  褘 褙 褚
          0x891c_891e, //  褜 褝 褞
          0x8920_8920, //  褠
          0x8925_8928, //  褥 褦 褧 褨
          0x892a_892b, //  褪 褫
          0x8930_8932, //  褰 褱 褲
          0x8935_893b, //  褵 褶 褷 褸 褹 褺 褻
          0x893e_893e, //  褾
          0x8940_8946, //  襀 襁 襂 襃 襄 襅 襆
          0x8949_8949, //  襉
          0x894c_894d, //  襌 襍
          0x894f_894f, //  襏
          0x8952_8952, //  襒
          0x8956_8957, //  襖 襗
          0x895a_895c, //  襚 襛 襜
          0x895e_8964, //  襞 襟 襠 襡 襢 襣 襤
          0x8966_8966, //  襦
          0x896a_896b, //  襪 襫
          0x896d_8970, //  襭 襮 襯 襰
          0x8972_8975, //  襲 襳 襴 襵
          0x8977_8977, //  襷
          0x897a_8981, //  襺 襻 襼 襽 襾 西 覀 要
          0x8983_8983, //  覃
          0x8986_898b, //  覆 覇 覈 覉 覊 見
          0x898d_898d, //  覍
          0x898f_8990, //  規 覐
          0x8993_8998, //  覓 覔 覕 視 覗 覘
          0x899a_899c, //  覚 覛 覜
          0x899f_89a1, //  覟 覠 覡
          0x89a5_89a7, //  覥 覦 覧
          0x89a9_89aa, //  覩 親
          0x89ac_89ac, //  覬
          0x89af_89b0, //  覯 覰
          0x89b2_89b7, //  覲 観 覴 覵 覶 覷
          0x89ba_89ba, //  覺
          0x89bc_89bd, //  覼 覽
          0x89bf_89c0, //  覿 觀
          0x89d2_89d2, //  角
          0x89d4_89d8, //  觔 觕 觖 觗 觘
          0x89da_89da, //  觚
          0x89dc_89dd, //  觜 觝
          0x89e3_89e3, //  解
          0x89e5_89e7, //  觥 触 觧
          0x89e9_89e9, //  觩
          0x89eb_89eb, //  觫
          0x89ed_89ed, //  觭
          0x89f1_89f1, //  觱
          0x89f3_89f4, //  觳 觴
          0x89f6_89f6, //  觶
          0x89f8_89f9, //  觸 觹
          0x89fd_89fd, //  觽
          0x89ff_8a00, //  觿 言
          0x8a02_8a05, //  訂 訃 訄 訅
          0x8a07_8a08, //  訇 計
          0x8a0a_8a0a, //  訊
          0x8a0c_8a0c, //  訌
          0x8a0e_8a18, //  討 訏 訐 訑 訒 訓 訔 訕 訖 託 記
          0x8a1b_8a1b, //  訛
          0x8a1d_8a26, //  訝 訞 訟 訠 訡 訢 訣 訤 訥 訦
          0x8a2a_8a2d, //  訪 訫 訬 設
          0x8a2f_8a2f, //  訯
          0x8a31_8a31, //  許
          0x8a33_8a37, //  訳 訴 訵 訶 訷
          0x8a3a_8a3e, //  診 註 証 訽 訾
          0x8a40_8a41, //  詀 詁
          0x8a43_8a43, //  詃
          0x8a45_8a49, //  詅 詆 詇 詈 詉
          0x8a4d_8a4e, //  詍 詎
          0x8a50_8a58, //  詐 詑 詒 詓 詔 評 詖 詗 詘
          0x8a5b_8a5e, //  詛 詜 詝 詞
          0x8a60_8a63, //  詠 詡 詢 詣
          0x8a65_8a67, //  詥 試 詧
          0x8a69_8a69, //  詩
          0x8a6b_8a6e, //  詫 詬 詭 詮
          0x8a70_8a73, //  詰 話 該 詳
          0x8a75_8a77, //  詵 詶 詷
          0x8a79_8a7c, //  詹 詺 詻 詼
          0x8a7e_8a80, //  詾 詿 誀
          0x8a82_8a87, //  誂 誃 誄 誅 誆 誇
          0x8a89_8a89, //  誉
          0x8a8b_8a8d, //  誋 誌 認
          0x8a8f_8a93, //  誏 誐 誑 誒 誓
          0x8a95_8a9a, //  誕 誖 誗 誘 誙 誚
          0x8a9e_8aa1, //  語 誟 誠 誡
          0x8aa3_8aa9, //  誣 誤 誥 誦 誧 誨 誩
          0x8aac_8ab0, //  説 読 誮 誯 誰
          0x8ab2_8ab3, //  課 誳
          0x8ab6_8ab7, //  誶 誷
          0x8ab9_8ab9, //  誹
          0x8abb_8abc, //  誻 誼
          0x8abe_8abf, //  誾 調
          0x8ac2_8ac4, //  諂 諃 諄
          0x8ac6_8acd, //  諆 談 諈 諉 諊 請 諌 諍
          0x8acf_8ad7, //  諏 諐 諑 諒 諓 諔 諕 論 諗
          0x8ada_8ae2, //  諚 諛 諜 諝 諞 諟 諠 諡 諢
          0x8ae4_8ae4, //  諤
          0x8ae6_8ae7, //  諦 諧
          0x8aeb_8aee, //  諫 諬 諭 諮
          0x8af0_8af1, //  諰 諱
          0x8af3_8af8, //  諳 諴 諵 諶 諷 諸
          0x8afa_8afa, //  諺
          0x8afc_8afc, //  諼
          0x8afe_8b02, //  諾 諿 謀 謁 謂
          0x8b04_8b07, //  謄 謅 謆 謇
          0x8b0a_8b0e, //  謊 謋 謌 謍 謎
          0x8b10_8b11, //  謐 謑
          0x8b14_8b14, //  謔
          0x8b16_8b17, //  謖 謗
          0x8b19_8b21, //  謙 謚 講 謜 謝 謞 謟 謠 謡
          0x8b26_8b26, //  謦
          0x8b28_8b28, //  謨
          0x8b2b_8b2d, //  謫 謬 謭
          0x8b30_8b30, //  謰
          0x8b33_8b33, //  謳
          0x8b37_8b37, //  謷
          0x8b39_8b39, //  謹
          0x8b3c_8b3c, //  謼
          0x8b3e_8b3e, //  謾
          0x8b41_8b46, //  譁 譂 譃 譄 譅 譆
          0x8b48_8b49, //  譈 證
          0x8b4c_8b4f, //  譌 譍 譎 譏
          0x8b51_8b54, //  譑 譒 譓 譔
          0x8b56_8b56, //  譖
          0x8b58_8b5c, //  識 譙 譚 譛 譜
          0x8b5e_8b5f, //  譞 譟
          0x8b63_8b63, //  譣
          0x8b66_8b66, //  警
          0x8b69_8b69, //  譩
          0x8b6b_8b6d, //  譫 譬 譭
          0x8b6f_8b72, //  譯 議 譱 譲
          0x8b74_8b74, //  譴
          0x8b76_8b79, //  譶 護 譸 譹
          0x8b7c_8b81, //  譼 譽 譾 譿 讀 讁
          0x8b83_8b85, //  讃 讄 讅
          0x8b8a_8b90, //  變 讋 讌 讍 讎 讏 讐
          0x8b92_8b96, //  讒 讓 讔 讕 讖
          0x8b99_8b9a, //  讙 讚
          0x8b9c_8b9f, //  讜 讝 讞 讟
          0x8c37_8c3a, //  谷 谸 谹 谺
          0x8c3d_8c3f, //  谽 谾 谿
          0x8c41_8c41, //  豁
          0x8c45_8c4c, //  豅 豆 豇 豈 豉 豊 豋 豌
          0x8c4e_8c51, //  豎 豏 豐 豑
          0x8c53_8c55, //  豓 豔 豕
          0x8c57_8c5b, //  豗 豘 豙 豚 豛
          0x8c5d_8c5d, //  豝
          0x8c61_8c64, //  象 豢 豣 豤
          0x8c66_8c66, //  豦
          0x8c68_8c6d, //  豨 豩 豪 豫 豬 豭
          0x8c73_8c73, //  豳
          0x8c75_8c76, //  豵 豶
          0x8c78_8c7c, //  豸 豹 豺 豻 豼
          0x8c7e_8c7e, //  豾
          0x8c82_8c82, //  貂
          0x8c85_8c87, //  貅 貆 貇
          0x8c89_8c8e, //  貉 貊 貋 貌 貍 貎
          0x8c90_8c90, //  貐
          0x8c92_8c94, //  貒 貓 貔
          0x8c98_8c99, //  貘 貙
          0x8c9b_8c9e, //  貛 貜 貝 貞
          0x8ca0_8ca2, //  負 財 貢
          0x8ca4_8ca4, //  貤
          0x8ca7_8cb0, //  貧 貨 販 貪 貫 責 貭 貮 貯 貰
          0x8cb2_8cb4, //  貲 貳 貴
          0x8cb6_8cbd, //  貶 買 貸 貹 貺 費 貼 貽
          0x8cbf_8ccb, //  貿 賀 賁 賂 賃 賄 賅 賆 資 賈 賉 賊 賋
          0x8ccd_8ccf, //  賍 賎 賏
          0x8cd1_8cd1, //  賑
          0x8cd3_8cd3, //  賓
          0x8cd5_8cd6, //  賕 賖
          0x8cd9_8cde, //  賙 賚 賛 賜 賝 賞
          0x8ce0_8ce4, //  賠 賡 賢 賣 賤
          0x8ce6_8ce6, //  賦
          0x8ce8_8ce8, //  賨
          0x8cea_8cea, //  質
          0x8cec_8ced, //  賬 賭
          0x8cef_8cf2, //  賯 賰 賱 賲
          0x8cf4_8cf5, //  賴 賵
          0x8cf7_8cf8, //  賷 賸
          0x8cfa_8cff, //  賺 賻 購 賽 賾 賿
          0x8d01_8d01, //  贁
          0x8d03_8d05, //  贃 贄 贅
          0x8d07_8d0b, //  贇 贈 贉 贊 贋
          0x8d0d_8d10, //  贍 贎 贏 贐
          0x8d12_8d14, //  贒 贓 贔
          0x8d16_8d17, //  贖 贗
          0x8d1b_8d1b, //  贛
          0x8d64_8d67, //  赤 赥 赦 赧
          0x8d69_8d69, //  赩
          0x8d6b_8d6e, //  赫 赬 赭 赮
          0x8d70_8d71, //  走 赱
          0x8d73_8d74, //  赳 赴
          0x8d77_8d77, //  起
          0x8d7f_8d7f, //  赿
          0x8d81_8d82, //  趁 趂
          0x8d84_8d85, //  趄 超
          0x8d88_8d88, //  趈
          0x8d8a_8d8a, //  越
          0x8d8d_8d8d, //  趍
          0x8d90_8d91, //  趐 趑
          0x8d95_8d95, //  趕
          0x8d99_8d99, //  趙
          0x8d9e_8da0, //  趞 趟 趠
          0x8da3_8da3, //  趣
          0x8da6_8da6, //  趦
          0x8da8_8da8, //  趨
          0x8dab_8dac, //  趫 趬
          0x8daf_8daf, //  趯
          0x8db2_8db3, //  趲 足
          0x8db5_8db5, //  趵
          0x8db7_8db7, //  趷
          0x8db9_8dbc, //  趹 趺 趻 趼
          0x8dbe_8dbe, //  趾
          0x8dc0_8dc0, //  跀
          0x8dc2_8dc2, //  跂
          0x8dc5_8dc8, //  跅 跆 跇 跈
          0x8dca_8dcc, //  跊 跋 跌
          0x8dce_8dcf, //  跎 跏
          0x8dd1_8dd1, //  跑
          0x8dd4_8dd7, //  跔 跕 跖 跗
          0x8dd9_8ddb, //  跙 跚 跛
          0x8ddd_8ddd, //  距
          0x8ddf_8ddf, //  跟
          0x8de1_8de1, //  跡
          0x8de3_8de5, //  跣 跤 跥
          0x8de7_8de8, //  跧 跨
          0x8dea_8dec, //  跪 跫 跬
          0x8def_8df5, //  路 跰 跱 跲 跳 跴 践
          0x8dfc_8dfd, //  跼 跽
          0x8dff_8dff, //  跿
          0x8e01_8e01, //  踁
          0x8e04_8e06, //  踄 踅 踆
          0x8e08_8e0c, //  踈 踉 踊 踋 踌
          0x8e0f_8e11, //  踏 踐 踑
          0x8e14_8e14, //  踔
          0x8e16_8e16, //  踖
          0x8e1d_8e23, //  踝 踞 踟 踠 踡 踢 踣
          0x8e26_8e27, //  踦 踧
          0x8e2a_8e2a, //  踪
          0x8e30_8e31, //  踰 踱
          0x8e33_8e39, //  踳 踴 踵 踶 踷 踸 踹
          0x8e3d_8e3d, //  踽
          0x8e40_8e42, //  蹀 蹁 蹂
          0x8e44_8e44, //  蹄
          0x8e47_8e50, //  蹇 蹈 蹉 蹊 蹋 蹌 蹍 蹎 蹏 蹐
          0x8e54_8e55, //  蹔 蹕
          0x8e59_8e59, //  蹙
          0x8e5b_8e64, //  蹛 蹜 蹝 蹞 蹟 蹠 蹡 蹢 蹣 蹤
          0x8e69_8e69, //  蹩
          0x8e6c_8e6d, //  蹬 蹭
          0x8e6f_8e72, //  蹯 蹰 蹱 蹲
          0x8e74_8e74, //  蹴
          0x8e76_8e76, //  蹶
          0x8e79_8e7c, //  蹹 蹺 蹻 蹼
          0x8e81_8e85, //  躁 躂 躃 躄 躅
          0x8e87_8e87, //  躇
          0x8e89_8e8b, //  躉 躊 躋
          0x8e8d_8e8d, //  躍
          0x8e90_8e95, //  躐 躑 躒 躓 躔 躕
          0x8e98_8e9b, //  躘 躙 躚 躛
          0x8e9d_8e9e, //  躝 躞
          0x8ea1_8ea2, //  躡 躢
          0x8ea7_8ea7, //  躧
          0x8ea9_8eb1, //  躩 躪 身 躬 躭 躮 躯 躰 躱
          0x8eb3_8eb3, //  躳
          0x8eb5_8eb6, //  躵 躶
          0x8eba_8ebb, //  躺 躻
          0x8ebe_8ebe, //  躾
          0x8ec0_8ec1, //  軀 軁
          0x8ec3_8ec8, //  軃 軄 軅 軆 軇 軈
          0x8eca_8ecd, //  車 軋 軌 軍
          0x8ecf_8ecf, //  軏
          0x8ed1_8ed2, //  軑 軒
          0x8ed4_8ed4, //  軔
          0x8edb_8edc, //  軛 軜
          0x8edf_8edf, //  軟
          0x8ee2_8ee3, //  転 軣
          0x8ee8_8ee8, //  軨
          0x8eeb_8eeb, //  軫
          0x8eed_8eee, //  軭 軮
          0x8ef0_8ef1, //  軰 軱
          0x8ef7_8efe, //  軷 軸 軹 軺 軻 軼 軽 軾
          0x8f00_8f00, //  輀
          0x8f02_8f03, //  輂 較
          0x8f05_8f05, //  輅
          0x8f07_8f0a, //  輇 輈 載 輊
          0x8f0c_8f0c, //  輌
          0x8f0f_8f10, //  輏 輐
          0x8f12_8f19, //  輒 輓 輔 輕 輖 輗 輘 輙
          0x8f1b_8f21, //  輛 輜 輝 輞 輟 輠 輡
          0x8f23_8f23, //  輣
          0x8f25_8f2f, //  輥 輦 輧 輨 輩 輪 輫 輬 輭 輮 輯
          0x8f33_8f3b, //  輳 輴 輵 輶 輷 輸 輹 輺 輻
          0x8f3e_8f47, //  輾 輿 轀 轁 轂 轃 轄 轅 轆 轇
          0x8f49_8f4a, //  轉 轊
          0x8f4c_8f4f, //  轌 轍 轎 轏
          0x8f51_8f55, //  轑 轒 轓 轔 轕
          0x8f57_8f58, //  轗 轘
          0x8f5c_8f5f, //  轜 轝 轞 轟
          0x8f61_8f65, //  轡 轢 轣 轤 轥
          0x8f9b_8fa1, //  辛 辜 辝 辞 辟 辠 辡
          0x8fa3_8fa8, //  辣 辤 辥 辦 辧 辨
          0x8fad_8fb2, //  辭 辮 辯 辰 辱 農
          0x8fb4_8fb8, //  辴 辵 辶 辷 辸
          0x8fba_8fbc, //  辺 辻 込
          0x8fbe_8fc2, //  达 辿 迀 迁 迂
          0x8fc4_8fc6, //  迄 迅 迆
          0x8fca_8fcb, //  迊 迋
          0x8fcd_8fce, //  迍 迎
          0x8fd0_8fd5, //  运 近 迒 迓 返 迕
          0x8fda_8fda, //  迚
          0x8fe0_8fe0, //  迠
          0x8fe2_8fe6, //  迢 迣 迤 迥 迦
          0x8fe8_8feb, //  迨 迩 迪 迫
          0x8fed_8ff1, //  迭 迮 迯 述 迱
          0x8ff4_8ffb, //  迴 迵 迶 迷 迸 迹 迺 迻
          0x8ffd_8ffe, //  追 迾
          0x9000_9006, //  退 送 适 逃 逄 逅 逆
          0x9008_9008, //  逈
          0x900b_9011, //  逋 逌 逍 逎 透 逐 逑
          0x9013_901b, //  逓 途 逕 逖 逗 逘 這 通 逛
          0x901d_9023, //  逝 逞 速 造 逡 逢 連
          0x9027_902a, //  逧 逨 逩 逪
          0x902c_902f, //  逬 逭 逮 逯
          0x9031_9039, //  週 進 逳 逴 逵 逶 逷 逸 逹
          0x903c_903c, //  逼
          0x903e_903f, //  逾 逿
          0x9041_9045, //  遁 遂 遃 遄 遅
          0x9047_9047, //  遇
          0x9049_9056, //  遉 遊 運 遌 遍 過 遏 遐 遑 遒 道 達 違 遖
          0x9058_9059, //  遘 遙
          0x905b_905e, //  遛 遜 遝 遞
          0x9060_9063, //  遠 遡 遢 遣
          0x9065_9069, //  遥 遦 遧 遨 適
          0x906c_9070, //  遬 遭 遮 遯 遰
          0x9072_9072, //  遲
          0x9074_907a, //  遴 遵 遶 遷 選 遹 遺
          0x907c_907d, //  遼 遽
          0x907f_9085, //  避 邀 邁 邂 邃 還 邅
          0x9087_908c, //  邇 邈 邉 邊 邋 邌
          0x908e_9091, //  邎 邏 邐 邑
          0x9095_9095, //  邕
          0x9097_9099, //  邗 邘 邙
          0x909b_909b, //  邛
          0x90a0_90a3, //  邠 邡 邢 那
          0x90a5_90a6, //  邥 邦
          0x90a8_90a8, //  邨
          0x90aa_90aa, //  邪
          0x90af_90b6, //  邯 邰 邱 邲 邳 邴 邵 邶
          0x90b8_90b8, //  邸
          0x90bd_90be, //  邽 邾
          0x90c1_90c1, //  郁
          0x90c3_90c5, //  郃 郄 郅
          0x90c7_90c8, //  郇 郈
          0x90ca_90ca, //  郊
          0x90cc_90cc, //  郌
          0x90ce_90ce, //  郎
          0x90d2_90d2, //  郒
          0x90d5_90d5, //  郕
          0x90d7_90d9, //  郗 郘 郙
          0x90db_90df, //  郛 郜 郝 郞 郟
          0x90e1_90e2, //  郡 郢
          0x90e4_90e5, //  郤 郥
          0x90e8_90e8, //  部
          0x90eb_90eb, //  郫
          0x90ed_90ed, //  郭
          0x90ef_90f0, //  郯 郰
          0x90f2_90f2, //  郲
          0x90f4_90f7, //  郴 郵 郶 郷
          0x90fd_9100, //  都 郾 郿 鄀
          0x9102_9102, //  鄂
          0x9104_9106, //  鄄 鄅 鄆
          0x9108_9108, //  鄈
          0x910d_910d, //  鄍
          0x9110_9110, //  鄐
          0x9112_9112, //  鄒
          0x9114_911a, //  鄔 鄕 鄖 鄗 鄘 鄙 鄚
          0x911c_911c, //  鄜
          0x911e_911e, //  鄞
          0x9120_9120, //  鄠
          0x9122_9123, //  鄢 鄣
          0x9125_9125, //  鄥
          0x9127_9127, //  鄧
          0x9129_9129, //  鄩
          0x912d_9132, //  鄭 鄮 鄯 鄰 鄱 鄲
          0x9134_9134, //  鄴
          0x9136_9137, //  鄶 鄷
          0x9139_913a, //  鄹 鄺
          0x913c_913d, //  鄼 鄽
          0x9143_9143, //  酃
          0x9146_914f, //  酆 酇 酈 酉 酊 酋 酌 配 酎 酏
          0x9152_9154, //  酒 酓 酔
          0x9156_915b, //  酖 酗 酘 酙 酚 酛
          0x9161_9165, //  酡 酢 酣 酤 酥
          0x9167_9167, //  酧
          0x9169_916a, //  酩 酪
          0x916c_916d, //  酬 酭
          0x9172_9175, //  酲 酳 酴 酵
          0x9177_917b, //  酷 酸 酹 酺 酻
          0x9181_9183, //  醁 醂 醃
          0x9185_9187, //  醅 醆 醇
          0x9189_918b, //  醉 醊 醋
          0x918d_918e, //  醍 醎
          0x9190_9195, //  醐 醑 醒 醓 醔 醕
          0x9197_9198, //  醗 醘
          0x919c_919c, //  醜
          0x919e_919e, //  醞
          0x91a1_91a2, //  醡 醢
          0x91a4_91a4, //  醤
          0x91a6_91a6, //  醦
          0x91a8_91a8, //  醨
          0x91aa_91b6, //  醪 醫 醬 醭 醮 醯 醰 醱 醲 醳 醴 醵 醶
          0x91b8_91b8, //  醸
          0x91ba_91bd, //  醺 醻 醼 醽
          0x91bf_91c9, //  醿 釀 釁 釂 釃 釄 釅 釆 采 釈 釉
          0x91cb_91d1, //  釋 里 重 野 量 釐 金
          0x91d3_91d4, //  釓 釔
          0x91d6_91df, //  釖 釗 釘 釙 釚 釛 釜 針 釞 釟
          0x91e1_91e1, //  釡
          0x91e3_91e7, //  釣 釤 釥 釦 釧
          0x91e9_91ea, //  釩 釪
          0x91ec_91f1, //  釬 釭 釮 釯 釰 釱
          0x91f5_91f7, //  釵 釶 釷
          0x91f9_91f9, //  釹
          0x91fb_91fd, //  釻 釼 釽
          0x91ff_9201, //  釿 鈀 鈁
          0x9204_9207, //  鈄 鈅 鈆 鈇
          0x9209_920a, //  鈉 鈊
          0x920c_920e, //  鈌 鈍 鈎
          0x9210_9218, //  鈐 鈑 鈒 鈓 鈔 鈕 鈖 鈗 鈘
          0x921c_921e, //  鈜 鈝 鈞
          0x9223_9226, //  鈣 鈤 鈥 鈦
          0x9228_9229, //  鈨 鈩
          0x922c_922c, //  鈬
          0x922e_9230, //  鈮 鈯 鈰
          0x9233_923a, //  鈳 鈴 鈵 鈶 鈷 鈸 鈹 鈺
          0x923c_923c, //  鈼
          0x923e_9240, //  鈾 鈿 鉀
          0x9242_924b, //  鉂 鉃 鉄 鉅 鉆 鉇 鉈 鉉 鉊 鉋
          0x924d_9251, //  鉍 鉎 鉏 鉐 鉑
          0x9256_925e, //  鉖 鉗 鉘 鉙 鉚 鉛 鉜 鉝 鉞
          0x9260_9262, //  鉠 鉡 鉢
          0x9264_9269, //  鉤 鉥 鉦 鉧 鉨 鉩
          0x926e_9271, //  鉮 鉯 鉰 鉱
          0x9275_9279, //  鉵 鉶 鉷 鉸 鉹
          0x927b_9280, //  鉻 鉼 鉽 鉾 鉿 銀
          0x9283_9283, //  銃
          0x9285_9285, //  銅
          0x9288_928a, //  銈 銉 銊
          0x928d_928e, //  銍 銎
          0x9291_9293, //  銑 銒 銓
          0x9295_929c, //  銕 銖 銗 銘 銙 銚 銛 銜
          0x929f_92a0, //  銟 銠
          0x92a4_92a5, //  銤 銥
          0x92a7_92a8, //  銧 銨
          0x92ab_92ab, //  銫
          0x92ad_92ad, //  銭
          0x92af_92af, //  銯
          0x92b2_92b2, //  銲
          0x92b6_92bd, //  銶 銷 銸 銹 銺 銻 銼 銽
          0x92bf_92c3, //  銿 鋀 鋁 鋂 鋃
          0x92c5_92c8, //  鋅 鋆 鋇 鋈
          0x92cb_92d0, //  鋋 鋌 鋍 鋎 鋏 鋐
          0x92d2_92d3, //  鋒 鋓
          0x92d5_92d5, //  鋕
          0x92d7_92d9, //  鋗 鋘 鋙
          0x92dc_92dd, //  鋜 鋝
          0x92df_92e1, //  鋟 鋠 鋡
          0x92e3_92e5, //  鋣 鋤 鋥
          0x92e7_92ea, //  鋧 鋨 鋩 鋪
          0x92ec_92ee, //  鋬 鋭 鋮
          0x92f0_92f0, //  鋰
          0x92f2_92f3, //  鋲 鋳
          0x92f7_92fc, //  鋷 鋸 鋹 鋺 鋻 鋼
          0x92ff_9300, //  鋿 錀
          0x9302_9302, //  錂
          0x9304_9304, //  錄
          0x9306_9306, //  錆
          0x9308_9308, //  錈
          0x930d_930d, //  錍
          0x930f_9311, //  錏 錐 錑
          0x9314_9315, //  錔 錕
          0x9318_931a, //  錘 錙 錚
          0x931c_932c, //  錜 錝 錞 錟 錠 錡 錢 錣 錤 錥 錦 錧 錨 錩 錪 錫 錬
          0x932e_932f, //  錮 錯
          0x9332_9337, //  録 錳 錴 錵 錶 錷
          0x933a_933b, //  錺 錻
          0x9344_9344, //  鍄
          0x9347_934b, //  鍇 鍈 鍉 鍊 鍋
          0x934d_934d, //  鍍
          0x9350_9352, //  鍐 鍑 鍒
          0x9354_9358, //  鍔 鍕 鍖 鍗 鍘
          0x935a_935c, //  鍚 鍛 鍜
          0x935e_935e, //  鍞
          0x9360_9360, //  鍠
          0x9364_9365, //  鍤 鍥
          0x9367_9367, //  鍧
          0x9369_9371, //  鍩 鍪 鍫 鍬 鍭 鍮 鍯 鍰 鍱
          0x9373_9376, //  鍳 鍴 鍵 鍶
          0x937a_937a, //  鍺
          0x937c_9382, //  鍼 鍽 鍾 鍿 鎀 鎁 鎂
          0x9388_9388, //  鎈
          0x938a_938d, //  鎊 鎋 鎌 鎍
          0x938f_938f, //  鎏
          0x9392_9392, //  鎒
          0x9394_9398, //  鎔 鎕 鎖 鎗 鎘
          0x939a_939b, //  鎚 鎛
          0x939e_939e, //  鎞
          0x93a1_93a1, //  鎡
          0x93a3_93a4, //  鎣 鎤
          0x93a6_93a9, //  鎦 鎧 鎨 鎩
          0x93ab_93ae, //  鎫 鎬 鎭 鎮
          0x93b0_93b0, //  鎰
          0x93b4_93b6, //  鎴 鎵 鎶
          0x93b9_93ba, //  鎹 鎺
          0x93c1_93c1, //  鏁
          0x93c3_93cd, //  鏃 鏄 鏅 鏆 鏇 鏈 鏉 鏊 鏋 鏌 鏍
          0x93d0_93d1, //  鏐 鏑
          0x93d3_93d3, //  鏓
          0x93d6_93d9, //  鏖 鏗 鏘 鏙
          0x93dc_93df, //  鏜 鏝 鏞 鏟
          0x93e1_93e2, //  鏡 鏢
          0x93e4_93e8, //  鏤 鏥 鏦 鏧 鏨
          0x93f1_93f1, //  鏱
          0x93f5_93f5, //  鏵
          0x93f7_93fb, //  鏷 鏸 鏹 鏺 鏻
          0x93fd_93fd, //  鏽
          0x9401_9404, //  鐁 鐂 鐃 鐄
          0x9407_9409, //  鐇 鐈 鐉
          0x940d_9410, //  鐍 鐎 鐏 鐐
          0x9413_941a, //  鐓 鐔 鐕 鐖 鐗 鐘 鐙 鐚
          0x941f_941f, //  鐟
          0x9421_9421, //  鐡
          0x942b_942b, //  鐫
          0x942e_942f, //  鐮 鐯
          0x9431_9436, //  鐱 鐲 鐳 鐴 鐵 鐶
          0x9438_9438, //  鐸
          0x943a_943b, //  鐺 鐻
          0x943d_943d, //  鐽
          0x943f_943f, //  鐿
          0x9441_9441, //  鑁
          0x9443_9445, //  鑃 鑄 鑅
          0x9448_9448, //  鑈
          0x944a_944a, //  鑊
          0x944c_944c, //  鑌
          0x9451_9453, //  鑑 鑒 鑓
          0x9455_9455, //  鑕
          0x9459_945c, //  鑙 鑚 鑛 鑜
          0x945e_9463, //  鑞 鑟 鑠 鑡 鑢 鑣
          0x9468_9468, //  鑨
          0x946a_946b, //  鑪 鑫
          0x946d_9472, //  鑭 鑮 鑯 鑰 鑱 鑲
          0x9475_9475, //  鑵
          0x9477_9477, //  鑷
          0x947c_947f, //  鑼 鑽 鑾 鑿
          0x9481_9481, //  钁
          0x9483_9484, //  钃 钄
          0x9577_9579, //  長 镸 镹
          0x957e_957e, //  镾
          0x9580_9580, //  門
          0x9582_9584, //  閂 閃 閄
          0x9586_958f, //  閆 閇 閈 閉 閊 開 閌 閍 閎 閏
          0x9591_9591, //  閑
          0x9593_9594, //  間 閔
          0x9596_9596, //  閖
          0x9598_9599, //  閘 閙
          0x959d_95a9, //  閝 閞 閟 閠 閡 関 閣 閤 閥 閦 閧 閨 閩
          0x95ab_95ad, //  閫 閬 閭
          0x95b2_95b2, //  閲
          0x95b4_95b4, //  閴
          0x95b6_95b6, //  閶
          0x95b9_95bf, //  閹 閺 閻 閼 閽 閾 閿
          0x95c3_95c3, //  闃
          0x95c6_95cd, //  闆 闇 闈 闉 闊 闋 闌 闍
          0x95d0_95d6, //  闐 闑 闒 闓 闔 闕 闖
          0x95d8_95da, //  闘 闙 闚
          0x95dc_95e2, //  關 闝 闞 闟 闠 闡 闢
          0x95e4_95e6, //  闤 闥 闦
          0x961c_961e, //  阜 阝 阞
          0x9621_9622, //  阡 阢
          0x9624_9626, //  阤 阥 阦
          0x9628_9628, //  阨
          0x962a_962a, //  阪
          0x962c_962c, //  阬
          0x962e_962f, //  阮 阯
          0x9631_9634, //  阱 防 阳 阴
          0x9637_963d, //  阷 阸 阹 阺 阻 阼 阽
          0x963f_9642, //  阿 陀 陁 陂
          0x9644_9644, //  附
          0x964b_964d, //  陋 陌 降
          0x964f_9650, //  陏 限
          0x9652_9652, //  陒
          0x9654_9654, //  陔
          0x9656_9658, //  陖 陗 陘
          0x965b_965f, //  陛 陜 陝 陞 陟
          0x9661_9666, //  陡 院 陣 除 陥 陦
          0x966a_966a, //  陪
          0x966c_966c, //  陬
          0x966e_966e, //  陮
          0x9670_9670, //  陰
          0x9672_9678, //  陲 陳 陴 陵 陶 陷 陸
          0x967a_967f, //  険 陻 陼 陽 陾 陿
          0x9681_9686, //  隁 隂 隃 隄 隅 隆
          0x9688_968b, //  隈 隉 隊 隋
          0x968d_968f, //  隍 階 随
          0x9691_9691, //  隑
          0x9694_969d, //  隔 隕 隖 隗 隘 隙 隚 際 障 隝
          0x969f_96a0, //  隟 隠
          0x96a3_96aa, //  隣 隤 隥 隦 隧 隨 隩 險
          0x96ae_96b4, //  隮 隯 隰 隱 隲 隳 隴
          0x96b6_96bd, //  隶 隷 隸 隹 隺 隻 隼 隽
          0x96c0_96c1, //  雀 雁
          0x96c4_96c7, //  雄 雅 集 雇
          0x96c9_96ce, //  雉 雊 雋 雌 雍 雎
          0x96d1_96d2, //  雑 雒
          0x96d5_96d6, //  雕 雖
          0x96d8_96df, //  雘 雙 雚 雛 雜 雝 雞 雟
          0x96e2_96e3, //  離 難
          0x96e8_96eb, //  雨 雩 雪 雫
          0x96ef_96f2, //  雯 雰 雱 雲
          0x96f6_96f7, //  零 雷
          0x96f9_96fb, //  雹 雺 電
          0x9700_9700, //  需
          0x9702_970a, //  霂 霃 霄 霅 霆 震 霈 霉 霊
          0x970d_970f, //  霍 霎 霏
          0x9711_9711, //  霑
          0x9713_9714, //  霓 霔
          0x9716_9716, //  霖
          0x9719_971e, //  霙 霚 霛 霜 霝 霞
          0x9721_9724, //  霡 霢 霣 霤
          0x9727_9728, //  霧 霨
          0x972a_972a, //  霪
          0x9730_9733, //  霰 霱 露 霳
          0x9736_9736, //  霶
          0x9738_9739, //  霸 霹
          0x973b_973b, //  霻
          0x973d_973e, //  霽 霾
          0x9741_9744, //  靁 靂 靃 靄
          0x9746_974a, //  靆 靇 靈 靉 靊
          0x974d_974f, //  靍 靎 靏
          0x9752_9752, //  青
          0x9755_975c, //  靕 靖 靗 靘 静 靚 靛 靜
          0x975e_975e, //  非
          0x9760_9764, //  靠 靡 面 靣 靤
          0x9766_976b, //  靦 靧 靨 革 靪 靫
          0x976d_976e, //  靭 靮
          0x9771_9771, //  靱
          0x9773_9774, //  靳 靴
          0x9776_977d, //  靶 靷 靸 靹 靺 靻 靼 靽
          0x977f_9781, //  靿 鞀 鞁
          0x9784_9786, //  鞄 鞅 鞆
          0x9789_9789, //  鞉
          0x978b_978b, //  鞋
          0x978d_978d, //  鞍
          0x978f_9790, //  鞏 鞐
          0x9795_979a, //  鞕 鞖 鞗 鞘 鞙 鞚
          0x979c_979c, //  鞜
          0x979e_97a0, //  鞞 鞟 鞠
          0x97a2_97a3, //  鞢 鞣
          0x97a6_97a6, //  鞦
          0x97a8_97a8, //  鞨
          0x97ab_97ae, //  鞫 鞬 鞭 鞮
          0x97b1_97b6, //  鞱 鞲 鞳 鞴 鞵 鞶
          0x97b8_97ba, //  鞸 鞹 鞺
          0x97bc_97bc, //  鞼
          0x97be_97bf, //  鞾 鞿
          0x97c1_97c1, //  韁
          0x97c3_97ce, //  韃 韄 韅 韆 韇 韈 韉 韊 韋 韌 韍 韎
          0x97d0_97d1, //  韐 韑
          0x97d3_97d4, //  韓 韔
          0x97d7_97d9, //  韗 韘 韙
          0x97db_97de, //  韛 韜 韝 韞
          0x97e0_97e1, //  韠 韡
          0x97e4_97e4, //  韤
          0x97ed_97ef, //  韭 韮 韯
          0x97f1_97f8, //  韱 韲 音 韴 韵 韶 韷 韸
          0x97fa_97fb, //  韺 韻
          0x97ff_97ff, //  響
          0x9801_9808, //  頁 頂 頃 頄 項 順 頇 須
          0x980a_980a, //  頊
          0x980c_9814, //  頌 頍 頎 頏 預 頑 頒 頓 頔
          0x9816_981a, //  頖 頗 領 頙 頚
          0x981c_981c, //  頜
          0x981e_981e, //  頞
          0x9820_9821, //  頠 頡
          0x9823_9826, //  頣 頤 頥 頦
          0x982b_9830, //  頫 頬 頭 頮 頯 頰
          0x9832_9835, //  頲 頳 頴 頵
          0x9837_9838, //  頷 頸
          0x983b_983e, //  頻 頼 頽 頾
          0x9844_9844, //  顄
          0x9846_9847, //  顆 顇
          0x984a_984f, //  顊 顋 題 額 顎 顏
          0x9851_985b, //  顑 顒 顓 顔 顕 顖 顗 願 顙 顚 顛
          0x985e_985e, //  類
          0x9862_9863, //  顢 顣
          0x9865_9867, //  顥 顦 顧
          0x986a_986c, //  顪 顫 顬
          0x986f_9871, //  顯 顰 顱
          0x9873_9874, //  顳 顴
          0x98a8_98a8, //  風
          0x98aa_98ab, //  颪 颫
          0x98ad_98b1, //  颭 颮 颯 颰 颱
          0x98b4_98b4, //  颴
          0x98b6_98b8, //  颶 颷 颸
          0x98ba_98bc, //  颺 颻 颼
          0x98bf_98bf, //  颿
          0x98c2_98c8, //  飂 飃 飄 飅 飆 飇 飈
          0x98cb_98cc, //  飋 飌
          0x98db_98dc, //  飛 飜
          0x98df_98e3, //  食 飠 飡 飢 飣
          0x98e5_98e7, //  飥 飦 飧
          0x98e9_98eb, //  飩 飪 飫
          0x98ed_98f4, //  飭 飮 飯 飰 飱 飲 飳 飴
          0x98f6_98f6, //  飶
          0x98fc_98fe, //  飼 飽 飾
          0x9902_9903, //  餂 餃
          0x9905_9905, //  餅
          0x9907_990a, //  餇 餈 餉 養
          0x990c_990c, //  餌
          0x9910_9918, //  餐 餑 餒 餓 餔 餕 餖 餗 餘
          0x991a_9922, //  餚 餛 餜 餝 餞 餟 餠 餡 餢
          0x9924_9924, //  餤
          0x9926_9928, //  餦 餧 館
          0x992b_992c, //  餫 餬
          0x992e_992e, //  餮
          0x9931_9935, //  餱 餲 餳 餴 餵
          0x9939_993e, //  餹 餺 餻 餼 餽 餾
          0x9940_9942, //  饀 饁 饂
          0x9945_9949, //  饅 饆 饇 饈 饉
          0x994b_994e, //  饋 饌 饍 饎
          0x9950_9952, //  饐 饑 饒
          0x9954_9955, //  饔 饕
          0x9957_9959, //  饗 饘 饙
          0x995b_995c, //  饛 饜
          0x995e_9960, //  饞 饟 饠
          0x9996_9999, //  首 馗 馘 香
          0x999b_999b, //  馛
          0x999d_999f, //  馝 馞 馟
          0x99a3_99a3, //  馣
          0x99a5_99a6, //  馥 馦
          0x99a8_99a8, //  馨
          0x99ac_99ae, //  馬 馭 馮
          0x99b0_99b5, //  馰 馱 馲 馳 馴 馵
          0x99b9_99ba, //  馹 馺
          0x99bc_99bd, //  馼 馽
          0x99bf_99bf, //  馿
          0x99c1_99c1, //  駁
          0x99c3_99c6, //  駃 駄 駅 駆
          0x99c8_99c9, //  駈 駉
          0x99d0_99d5, //  駐 駑 駒 駓 駔 駕
          0x99d8_99df, //  駘 駙 駚 駛 駜 駝 駞 駟
          0x99e2_99e2, //  駢
          0x99e7_99e7, //  駧
          0x99ea_99ee, //  駪 駫 駬 駭 駮
          0x99f0_99f2, //  駰 駱 駲
          0x99f4_99f5, //  駴 駵
          0x99f8_99f9, //  駸 駹
          0x99fb_99ff, //  駻 駼 駽 駾 駿
          0x9a01_9a05, //  騁 騂 騃 騄 騅
          0x9a0a_9a0c, //  騊 騋 騌
          0x9a0e_9a13, //  騎 騏 騐 騑 騒 験
          0x9a16_9a16, //  騖
          0x9a19_9a1a, //  騙 騚
          0x9a1e_9a1e, //  騞
          0x9a20_9a20, //  騠
          0x9a22_9a24, //  騢 騣 騤
          0x9a27_9a28, //  騧 騨
          0x9a2b_9a2b, //  騫
          0x9a2d_9a2e, //  騭 騮
          0x9a30_9a31, //  騰 騱
          0x9a33_9a33, //  騳
          0x9a35_9a38, //  騵 騶 騷 騸
          0x9a3e_9a3e, //  騾
          0x9a40_9a45, //  驀 驁 驂 驃 驄 驅
          0x9a47_9a47, //  驇
          0x9a4a_9a4e, //  驊 驋 驌 驍 驎
          0x9a51_9a52, //  驑 驒
          0x9a54_9a58, //  驔 驕 驖 驗 驘
          0x9a5a_9a5b, //  驚 驛
          0x9a5d_9a5d, //  驝
          0x9a5f_9a5f, //  驟
          0x9a62_9a62, //  驢
          0x9a64_9a65, //  驤 驥
          0x9a69_9a6b, //  驩 驪 驫
          0x9aa8_9aa8, //  骨
          0x9aaa_9aaa, //  骪
          0x9aac_9ab0, //  骬 骭 骮 骯 骰
          0x9ab2_9ab2, //  骲
          0x9ab4_9ab9, //  骴 骵 骶 骷 骸 骹
          0x9abb_9abc, //  骻 骼
          0x9abe_9ac1, //  骾 骿 髀 髁
          0x9ac3_9ac4, //  髃 髄
          0x9ac6_9ac6, //  髆
          0x9ac8_9ac8, //  髈
          0x9ace_9ad8, //  髎 髏 髐 髑 髒 髓 體 髕 髖 髗 高
          0x9adb_9adc, //  髛 髜
          0x9ade_9ae0, //  髞 髟 髠
          0x9ae2_9ae7, //  髢 髣 髤 髥 髦 髧
          0x9ae9_9aef, //  髩 髪 髫 髬 髭 髮 髯
          0x9af1_9af5, //  髱 髲 髳 髴 髵
          0x9af7_9af7, //  髷
          0x9af9_9afb, //  髹 髺 髻
          0x9afd_9afd, //  髽
          0x9aff_9b06, //  髿 鬀 鬁 鬂 鬃 鬄 鬅 鬆
          0x9b08_9b09, //  鬈 鬉
          0x9b0b_9b0e, //  鬋 鬌 鬍 鬎
          0x9b10_9b10, //  鬐
          0x9b12_9b12, //  鬒
          0x9b16_9b16, //  鬖
          0x9b18_9b1c, //  鬘 鬙 鬚 鬛 鬜
          0x9b1f_9b20, //  鬟 鬠
          0x9b22_9b23, //  鬢 鬣
          0x9b25_9b2b, //  鬥 鬦 鬧 鬨 鬩 鬪 鬫
          0x9b2d_9b2f, //  鬭 鬮 鬯
          0x9b31_9b35, //  鬱 鬲 鬳 鬴 鬵
          0x9b37_9b37, //  鬷
          0x9b39_9b3d, //  鬹 鬺 鬻 鬼 鬽
          0x9b41_9b45, //  魁 魂 魃 魄 魅
          0x9b48_9b48, //  魈
          0x9b4b_9b4f, //  魋 魌 魍 魎 魏
          0x9b51_9b51, //  魑
          0x9b54_9b58, //  魔 魕 魖 魗 魘
          0x9b5a_9b5b, //  魚 魛
          0x9b5e_9b5e, //  魞
          0x9b61_9b61, //  魡
          0x9b63_9b63, //  魣
          0x9b65_9b66, //  魥 魦
          0x9b68_9b68, //  魨
          0x9b6a_9b6f, //  魪 魫 魬 魭 魮 魯
          0x9b72_9b79, //  魲 魳 魴 魵 魶 魷 魸 魹
          0x9b7f_9b80, //  魿 鮀
          0x9b83_9b87, //  鮃 鮄 鮅 鮆 鮇
          0x9b89_9b8b, //  鮉 鮊 鮋
          0x9b8d_9b94, //  鮍 鮎 鮏 鮐 鮑 鮒 鮓 鮔
          0x9b96_9b97, //  鮖 鮗
          0x9b9a_9b9a, //  鮚
          0x9b9d_9ba0, //  鮝 鮞 鮟 鮠
          0x9ba6_9bae, //  鮦 鮧 鮨 鮩 鮪 鮫 鮬 鮭 鮮
          0x9bb0_9bb2, //  鮰 鮱 鮲
          0x9bb4_9bb4, //  鮴
          0x9bb7_9bb9, //  鮷 鮸 鮹
          0x9bbb_9bbc, //  鮻 鮼
          0x9bbe_9bc1, //  鮾 鮿 鯀 鯁
          0x9bc6_9bca, //  鯆 鯇 鯈 鯉 鯊
          0x9bce_9bd2, //  鯎 鯏 鯐 鯑 鯒
          0x9bd4_9bd4, //  鯔
          0x9bd6_9bd8, //  鯖 鯗 鯘
          0x9bdb_9bdb, //  鯛
          0x9bdd_9bdd, //  鯝
          0x9bdf_9bdf, //  鯟
          0x9be1_9be5, //  鯡 鯢 鯣 鯤 鯥
          0x9be7_9be8, //  鯧 鯨
          0x9bea_9beb, //  鯪 鯫
          0x9bee_9bf3, //  鯮 鯯 鯰 鯱 鯲 鯳
          0x9bf5_9bf5, //  鯵
          0x9bf7_9bfa, //  鯷 鯸 鯹 鯺
          0x9bfd_9bfd, //  鯽
          0x9bff_9c00, //  鯿 鰀
          0x9c02_9c02, //  鰂
          0x9c04_9c04, //  鰄
          0x9c06_9c06, //  鰆
          0x9c08_9c0d, //  鰈 鰉 鰊 鰋 鰌 鰍
          0x9c0f_9c16, //  鰏 鰐 鰑 鰒 鰓 鰔 鰕 鰖
          0x9c18_9c1e, //  鰘 鰙 鰚 鰛 鰜 鰝 鰞
          0x9c21_9c2a, //  鰡 鰢 鰣 鰤 鰥 鰦 鰧 鰨 鰩 鰪
          0x9c2d_9c32, //  鰭 鰮 鰯 鰰 鰱 鰲
          0x9c35_9c37, //  鰵 鰶 鰷
          0x9c39_9c3b, //  鰹 鰺 鰻
          0x9c3d_9c3e, //  鰽 鰾
          0x9c41_9c41, //  鱁
          0x9c43_9c4a, //  鱃 鱄 鱅 鱆 鱇 鱈 鱉 鱊
          0x9c4e_9c50, //  鱎 鱏 鱐
          0x9c52_9c54, //  鱒 鱓 鱔
          0x9c56_9c58, //  鱖 鱗 鱘
          0x9c5a_9c60, //  鱚 鱛 鱜 鱝 鱞 鱟 鱠
          0x9c63_9c63, //  鱣
          0x9c65_9c65, //  鱥
          0x9c67_9c6b, //  鱧 鱨 鱩 鱪 鱫
          0x9c6d_9c6e, //  鱭 鱮
          0x9c70_9c70, //  鱰
          0x9c72_9c72, //  鱲
          0x9c75_9c78, //  鱵 鱶 鱷 鱸
          0x9c7a_9c7b, //  鱺 鱻
          0x9ce5_9ce7, //  鳥 鳦 鳧
          0x9ce9_9ce9, //  鳩
          0x9ceb_9cec, //  鳫 鳬
          0x9cf0_9cf0, //  鳰
          0x9cf2_9cf4, //  鳲 鳳 鳴
          0x9cf6_9cf7, //  鳶 鳷
          0x9cf9_9cf9, //  鳹
          0x9d02_9d03, //  鴂 鴃
          0x9d06_9d09, //  鴆 鴇 鴈 鴉
          0x9d0b_9d0b, //  鴋
          0x9d0e_9d0e, //  鴎
          0x9d11_9d12, //  鴑 鴒
          0x9d15_9d15, //  鴕
          0x9d17_9d18, //  鴗 鴘
          0x9d1b_9d1f, //  鴛 鴜 鴝 鴞 鴟
          0x9d23_9d23, //  鴣
          0x9d26_9d26, //  鴦
          0x9d28_9d28, //  鴨
          0x9d2a_9d2c, //  鴪 鴫 鴬
          0x9d2f_9d30, //  鴯 鴰
          0x9d32_9d34, //  鴲 鴳 鴴
          0x9d3a_9d3f, //  鴺 鴻 鴼 鴽 鴾 鴿
          0x9d41_9d48, //  鵁 鵂 鵃 鵄 鵅 鵆 鵇 鵈
          0x9d4a_9d4a, //  鵊
          0x9d50_9d54, //  鵐 鵑 鵒 鵓 鵔
          0x9d59_9d59, //  鵙
          0x9d5c_9d65, //  鵜 鵝 鵞 鵟 鵠 鵡 鵢 鵣 鵤 鵥
          0x9d69_9d6c, //  鵩 鵪 鵫 鵬
          0x9d6f_9d70, //  鵯 鵰
          0x9d72_9d73, //  鵲 鵳
          0x9d76_9d77, //  鵶 鵷
          0x9d7a_9d7c, //  鵺 鵻 鵼
          0x9d7e_9d7e, //  鵾
          0x9d83_9d84, //  鶃 鶄
          0x9d86_9d87, //  鶆 鶇
          0x9d89_9d8a, //  鶉 鶊
          0x9d8d_9d8f, //  鶍 鶎 鶏
          0x9d92_9d93, //  鶒 鶓
          0x9d95_9d9a, //  鶕 鶖 鶗 鶘 鶙 鶚
          0x9da1_9da1, //  鶡
          0x9da4_9da4, //  鶤
          0x9da9_9dac, //  鶩 鶪 鶫 鶬
          0x9dae_9daf, //  鶮 鶯
          0x9db1_9db2, //  鶱 鶲
          0x9db4_9db5, //  鶴 鶵
          0x9db8_9dbd, //  鶸 鶹 鶺 鶻 鶼 鶽
          0x9dbf_9dc4, //  鶿 鷀 鷁 鷂 鷃 鷄
          0x9dc6_9dc7, //  鷆 鷇
          0x9dc9_9dca, //  鷉 鷊
          0x9dcf_9dcf, //  鷏
          0x9dd3_9dd7, //  鷓 鷔 鷕 鷖 鷗
          0x9dd9_9dda, //  鷙 鷚
          0x9dde_9de0, //  鷞 鷟 鷠
          0x9de3_9de3, //  鷣
          0x9de5_9de7, //  鷥 鷦 鷧
          0x9de9_9de9, //  鷩
          0x9deb_9deb, //  鷫
          0x9ded_9df0, //  鷭 鷮 鷯 鷰
          0x9df2_9df4, //  鷲 鷳 鷴
          0x9df8_9dfa, //  鷸 鷹 鷺
          0x9dfd_9dfe, //  鷽 鷾
          0x9e02_9e02, //  鸂
          0x9e07_9e07, //  鸇
          0x9e0a_9e0a, //  鸊
          0x9e0d_9e0e, //  鸍 鸎
          0x9e10_9e12, //  鸐 鸑 鸒
          0x9e15_9e16, //  鸕 鸖
          0x9e19_9e1e, //  鸙 鸚 鸛 鸜 鸝 鸞
          0x9e75_9e75, //  鹵
          0x9e78_9e7d, //  鹸 鹹 鹺 鹻 鹼 鹽
          0x9e7f_9e85, //  鹿 麀 麁 麂 麃 麄 麅
          0x9e87_9e88, //  麇 麈
          0x9e8b_9e8c, //  麋 麌
          0x9e8e_9e8f, //  麎 麏
          0x9e91_9e93, //  麑 麒 麓
          0x9e95_9e98, //  麕 麖 麗 麘
          0x9e9b_9e9b, //  麛
          0x9e9d_9e9f, //  麝 麞 麟
          0x9ea4_9ea6, //  麤 麥 麦
          0x9ea8_9eaa, //  麨 麩 麪
          0x9eac_9eb0, //  麬 麭 麮 麯 麰
          0x9eb3_9eb5, //  麳 麴 麵
          0x9eb8_9ebf, //  麸 麹 麺 麻 麼 麽 麾 麿
          0x9ec3_9ec4, //  黃 黄
          0x9ec6_9ec6, //  黆
          0x9ec8_9ec8, //  黈
          0x9ecb_9ed2, //  黋 黌 黍 黎 黏 黐 黑 黒
          0x9ed4_9ed5, //  黔 黕
          0x9ed8_9ed9, //  默 黙
          0x9edb_9ee0, //  黛 黜 黝 點 黟 黠
          0x9ee4_9ee5, //  黤 黥
          0x9ee7_9ee8, //  黧 黨
          0x9eec_9ef2, //  黬 黭 黮 黯 黰 黱 黲
          0x9ef4_9ef9, //  黴 黵 黶 黷 黸 黹
          0x9efb_9efd, //  黻 黼 黽
          0x9eff_9eff, //  黿
          0x9f02_9f03, //  鼂 鼃
          0x9f07_9f09, //  鼇 鼈 鼉
          0x9f0e_9f17, //  鼎 鼏 鼐 鼑 鼒 鼓 鼔 鼕 鼖 鼗
          0x9f19_9f1b, //  鼙 鼚 鼛
          0x9f1f_9f22, //  鼟 鼠 鼡 鼢
          0x9f26_9f26, //  鼦
          0x9f2a_9f2c, //  鼪 鼫 鼬
          0x9f2f_9f2f, //  鼯
          0x9f31_9f32, //  鼱 鼲
          0x9f34_9f34, //  鼴
          0x9f37_9f37, //  鼷
          0x9f39_9f3f, //  鼹 鼺 鼻 鼼 鼽 鼾 鼿
          0x9f41_9f41, //  齁
          0x9f43_9f47, //  齃 齄 齅 齆 齇
          0x9f4a_9f4b, //  齊 齋
          0x9f4e_9f4f, //  齎 齏
          0x9f52_9f58, //  齒 齓 齔 齕 齖 齗 齘
          0x9f5a_9f5a, //  齚
          0x9f5d_9f63, //  齝 齞 齟 齠 齡 齢 齣
          0x9f66_9f6a, //  齦 齧 齨 齩 齪
          0x9f6c_9f73, //  齬 齭 齮 齯 齰 齱 齲 齳
          0x9f75_9f77, //  齵 齶 齷
          0x9f7a_9f7a, //  齺
          0x9f7d_9f7d, //  齽
          0x9f8d_9f8d, //  龍
          0x9f8f_9f92, //  龏 龐 龑 龒
          0x9f94_9f97, //  龔 龕 龖 龗
          0x9f9c_9f9e, //  龜 龝 龞
          0x9fa0_9fa3, //  龠 龡 龢 龣
          0x9fa5_9fa5, //  龥
          0x9fc4_9fc6, //  鿄 鿅 鿆
          0x9fee_9fef, //  鿮 鿯
          0xf91d_f91d, //  欄
          0xf928_f929, //  廊 朗
          0xf936_f936, //  虜
          0xf970_f970, //  殺
          0xf9d0_f9d0, //  類
          0xf9dc_f9dc, //  隆
          0xfa0f_fa11, //  﨏 塚 﨑
          0xfa13_fa16, //  﨓 﨔 凞 猪
          0xfa19_fa1b, //  神 祥 福
          0xfa1f_fa24, //  﨟 蘒 﨡 諸 﨣 﨤
          0xfa26_fa26, //  都
          0xfa30_fa6d, //  侮 僧 免 勉 勤 卑 喝 嘆 器 塀 墨 層 屮 悔 慨 憎 懲 敏 既 暑 梅 海 渚 漢 煮 爫 琢 碑 社 祉
                       //  祈 祐 祖 祝 禍 禎 穀 突 節 練 縉 繁 署 者 臭 艹 艹 著 褐 視 謁 謹 賓 贈 辶 逸 難 響 頻 恵 𤋮 舘
      },
      new long[]{
          0x0002000b_0002000bL, //  𠀋
          0x00020089_00020089L, //  𠂉
          0x000200a2_000200a2L, //  𠂢
          0x000200a4_000200a4L, //  𠂤
          0x000201a2_000201a2L, //  𠆢
          0x00020213_00020213L, //  𠈓
          0x0002032b_0002032bL, //  𠌫
          0x00020371_00020371L, //  𠍱
          0x00020381_00020381L, //  𠎁
          0x000203f9_000203f9L, //  𠏹
          0x0002044a_0002044aL, //  𠑊
          0x00020509_00020509L, //  𠔉
          0x000205d6_000205d6L, //  𠗖
          0x00020628_00020628L, //  𠘨
          0x0002074f_0002074fL, //  𠝏
          0x00020807_00020807L, //  𠠇
          0x0002083a_0002083aL, //  𠠺
          0x000208b9_000208b9L, //  𠢹
          0x0002097c_0002097cL, //  𠥼
          0x0002099d_0002099dL, //  𠦝
          0x00020ad3_00020ad3L, //  𠫓
          0x00020b1d_00020b1dL, //  𠬝
          0x00020b9f_00020b9fL, //  𠮟
          0x00020d45_00020d45L, //  𠵅
          0x00020de1_00020de1L, //  𠷡
          0x00020e64_00020e64L, //  𠹤
          0x00020e6d_00020e6dL, //  𠹭
          0x00020e95_00020e95L, //  𠺕
          0x00020f5f_00020f5fL, //  𠽟
          0x00021201_00021201L, //  𡈁
          0x0002123d_0002123dL, //  𡈽
          0x00021255_00021255L, //  𡉕
          0x00021274_00021274L, //  𡉴
          0x0002127b_0002127bL, //  𡉻
          0x000212d7_000212d7L, //  𡋗
          0x000212e4_000212e4L, //  𡋤
          0x000212fd_000212fdL, //  𡋽
          0x0002131b_0002131bL, //  𡌛
          0x00021336_00021336L, //  𡌶
          0x00021344_00021344L, //  𡍄
          0x000213c4_000213c4L, //  𡏄
          0x0002146d_0002146eL, //  𡑭 𡑮
          0x000215d7_000215d7L, //  𡗗
          0x00021647_00021647L, //  𡙇
          0x000216b4_000216b4L, //  𡚴
          0x00021706_00021706L, //  𡜆
          0x00021742_00021742L, //  𡝂
          0x000218bd_000218bdL, //  𡢽
          0x000219c3_000219c3L, //  𡧃
          0x00021c56_00021c56L, //  𡱖
          0x00021d2d_00021d2dL, //  𡴭
          0x00021d45_00021d45L, //  𡵅
          0x00021d62_00021d62L, //  𡵢
          0x00021d78_00021d78L, //  𡵸
          0x00021d92_00021d92L, //  𡶒
          0x00021d9c_00021d9cL, //  𡶜
          0x00021da1_00021da1L, //  𡶡
          0x00021db7_00021db7L, //  𡶷
          0x00021de0_00021de0L, //  𡷠
          0x00021e33_00021e34L, //  𡸳 𡸴
          0x00021f1e_00021f1eL, //  𡼞
          0x00021f76_00021f76L, //  𡽶
          0x00021ffa_00021ffaL, //  𡿺
          0x0002217b_0002217bL, //  𢅻
          0x00022218_00022218L, //  𢈘
          0x0002231e_0002231eL, //  𢌞
          0x000223ad_000223adL, //  𢎭
          0x000226f3_000226f3L, //  𢛳
          0x0002285b_0002285bL, //  𢡛
          0x000228ab_000228abL, //  𢢫
          0x0002298f_0002298fL, //  𢦏
          0x00022ab8_00022ab8L, //  𢪸
          0x00022b46_00022b46L, //  𢭆
          0x00022b4f_00022b50L, //  𢭏 𢭐
          0x00022ba6_00022ba6L, //  𢮦
          0x00022c1d_00022c1dL, //  𢰝
          0x00022c24_00022c24L, //  𢰤
          0x00022de1_00022de1L, //  𢷡
          0x000231b6_000231b6L, //  𣆶
          0x000231c3_000231c4L, //  𣇃 𣇄
          0x000231f5_000231f5L, //  𣇵
          0x00023372_00023372L, //  𣍲
          0x000233d0_000233d0L, //  𣏐
          0x000233d2_000233d3L, //  𣏒 𣏓
          0x000233d5_000233d5L, //  𣏕
          0x000233da_000233daL, //  𣏚
          0x000233df_000233dfL, //  𣏟
          0x000233e4_000233e4L, //  𣏤
          0x0002344a_0002344bL, //  𣑊 𣑋
          0x00023451_00023451L, //  𣑑
          0x00023465_00023465L, //  𣑥
          0x000234e4_000234e4L, //  𣓤
          0x0002355a_0002355aL, //  𣕚
          0x00023594_00023594L, //  𣖔
          0x000235c4_000235c4L, //  𣗄
          0x00023638_0002363aL, //  𣘸 𣘹 𣘺
          0x00023647_00023647L, //  𣙇
          0x0002370c_0002370cL, //  𣜌
          0x0002371c_0002371cL, //  𣜜
          0x0002373f_0002373fL, //  𣜿
          0x00023763_00023764L, //  𣝣 𣝤
          0x000237e7_000237e7L, //  𣟧
          0x000237ff_000237ffL, //  𣟿
          0x00023824_00023824L, //  𣠤
          0x0002383d_0002383dL, //  𣠽
          0x00023a98_00023a98L, //  𣪘
          0x00023c7f_00023c7fL, //  𣱿
          0x00023cfe_00023cfeL, //  𣳾
          0x00023d00_00023d00L, //  𣴀
          0x00023d0e_00023d0eL, //  𣴎
          0x00023d40_00023d40L, //  𣵀
          0x00023dd3_00023dd3L, //  𣷓
          0x00023df9_00023dfaL, //  𣷹 𣷺
          0x00023f7e_00023f7eL, //  𣽾
          0x00024096_00024096L, //  𤂖
          0x00024103_00024103L, //  𤄃
          0x000241c6_000241c6L, //  𤇆
          0x000241fe_000241feL, //  𤇾
          0x000243bc_000243bcL, //  𤎼
          0x00024629_00024629L, //  𤘩
          0x000246a5_000246a5L, //  𤚥
          0x000247f1_000247f1L, //  𤟱
          0x00024896_00024896L, //  𤢖
          0x00024a4d_00024a4dL, //  𤩍
          0x00024b56_00024b56L, //  𤭖
          0x00024b6f_00024b6fL, //  𤭯
          0x00024c16_00024c16L, //  𤰖
          0x00024d14_00024d14L, //  𤴔
          0x00024e0e_00024e0eL, //  𤸎
          0x00024e37_00024e37L, //  𤸷
          0x00024e6a_00024e6aL, //  𤹪
          0x00024e8b_00024e8bL, //  𤺋
          0x0002504a_0002504aL, //  𥁊
          0x00025055_00025055L, //  𥁕
          0x00025122_00025122L, //  𥄢
          0x000251a9_000251a9L, //  𥆩
          0x000251cd_000251cdL, //  𥇍
          0x000251e5_000251e5L, //  𥇥
          0x0002521e_0002521eL, //  𥈞
          0x0002524c_0002524cL, //  𥉌
          0x0002542e_0002542eL, //  𥐮
          0x0002548e_0002548eL, //  𥒎
          0x000254d9_000254d9L, //  𥓙
          0x0002550e_0002550eL, //  𥔎
          0x000255a7_000255a7L, //  𥖧
          0x00025771_00025771L, //  𥝱
          0x000257a9_000257a9L, //  𥞩
          0x000257b4_000257b4L, //  𥞴
          0x000259c4_000259c4L, //  𥧄
          0x000259d4_000259d4L, //  𥧔
          0x00025ae3_00025ae4L, //  𥫣 𥫤
          0x00025af1_00025af1L, //  𥫱
          0x00025bb2_00025bb2L, //  𥮲
          0x00025c4b_00025c4bL, //  𥱋
          0x00025c64_00025c64L, //  𥱤
          0x00025da1_00025da1L, //  𥶡
          0x00025e2e_00025e2eL, //  𥸮
          0x00025e56_00025e56L, //  𥹖
          0x00025e62_00025e62L, //  𥹢
          0x00025e65_00025e65L, //  𥹥
          0x00025ec2_00025ec2L, //  𥻂
          0x00025ed8_00025ed8L, //  𥻘
          0x00025ee8_00025ee8L, //  𥻨
          0x00025f23_00025f23L, //  𥼣
          0x00025f5c_00025f5cL, //  𥽜
          0x00025fd4_00025fd4L, //  𥿔
          0x00025fe0_00025fe0L, //  𥿠
          0x00025ffb_00025ffbL, //  𥿻
          0x0002600c_0002600cL, //  𦀌
          0x00026017_00026017L, //  𦀗
          0x00026060_00026060L, //  𦁠
          0x000260ed_000260edL, //  𦃭
          0x00026270_00026270L, //  𦉰
          0x00026286_00026286L, //  𦊆
          0x0002634c_0002634cL, //  𦍌
          0x00026402_00026402L, //  𦐂
          0x0002667e_0002667eL, //  𦙾
          0x000266b0_000266b0L, //  𦚰
          0x0002671d_0002671dL, //  𦜝
          0x000268dd_000268ddL, //  𦣝
          0x000268ea_000268eaL, //  𦣪
          0x00026951_00026951L, //  𦥑
          0x0002696f_0002696fL, //  𦥯
          0x000269dd_000269ddL, //  𦧝
          0x00026a1e_00026a1eL, //  𦨞
          0x00026a58_00026a58L, //  𦩘
          0x00026a8c_00026a8cL, //  𦪌
          0x00026ab7_00026ab7L, //  𦪷
          0x00026aff_00026affL, //  𦫿
          0x00026c29_00026c29L, //  𦰩
          0x00026c73_00026c73L, //  𦱳
          0x00026cdd_00026cddL, //  𦳝
          0x00026e40_00026e40L, //  𦹀
          0x00026e65_00026e65L, //  𦹥
          0x00026f94_00026f94L, //  𦾔
          0x00026ff6_00026ff8L, //  𦿶 𦿷 𦿸
          0x000270f4_000270f4L, //  𧃴
          0x0002710d_0002710dL, //  𧄍
          0x00027139_00027139L, //  𧄹
          0x000273da_000273dbL, //  𧏚 𧏛
          0x000273fe_000273feL, //  𧏾
          0x00027410_00027410L, //  𧐐
          0x00027449_00027449L, //  𧑉
          0x00027614_00027615L, //  𧘔 𧘕
          0x00027631_00027631L, //  𧘱
          0x00027684_00027684L, //  𧚄
          0x00027693_00027693L, //  𧚓
          0x0002770e_0002770eL, //  𧜎
          0x00027723_00027723L, //  𧜣
          0x00027752_00027752L, //  𧝒
          0x00027985_00027985L, //  𧦅
          0x00027a84_00027a84L, //  𧪄
          0x00027bb3_00027bb3L, //  𧮳
          0x00027bbe_00027bbeL, //  𧮾
          0x00027bc7_00027bc7L, //  𧯇
          0x00027cb8_00027cb8L, //  𧲸
          0x00027da0_00027da0L, //  𧶠
          0x00027e10_00027e10L, //  𧸐
          0x00027fb7_00027fb7L, //  𧾷
          0x0002808a_0002808aL, //  𨂊
          0x000280bb_000280bbL, //  𨂻
          0x00028277_00028277L, //  𨉷
          0x00028282_00028282L, //  𨊂
          0x000282f3_000282f3L, //  𨋳
          0x000283cd_000283cdL, //  𨏍
          0x0002840c_0002840cL, //  𨐌
          0x00028455_00028455L, //  𨑕
          0x0002856b_0002856bL, //  𨕫
          0x000285c8_000285c9L, //  𨗈 𨗉
          0x000286d7_000286d7L, //  𨛗
          0x000286fa_000286faL, //  𨛺
          0x00028946_00028946L, //  𨥆
          0x00028949_00028949L, //  𨥉
          0x0002896b_0002896bL, //  𨥫
          0x00028987_00028988L, //  𨦇 𨦈
          0x000289ba_000289bbL, //  𨦺 𨦻
          0x00028a1e_00028a1eL, //  𨨞
          0x00028a29_00028a29L, //  𨨩
          0x00028a43_00028a43L, //  𨩃
          0x00028a71_00028a71L, //  𨩱
          0x00028a99_00028a99L, //  𨪙
          0x00028acd_00028acdL, //  𨫍
          0x00028add_00028addL, //  𨫝
          0x00028ae4_00028ae4L, //  𨫤
          0x00028bc1_00028bc1L, //  𨯁
          0x00028bef_00028befL, //  𨯯
          0x00028d10_00028d10L, //  𨴐
          0x00028d71_00028d71L, //  𨵱
          0x00028dfb_00028dfbL, //  𨷻
          0x00028e1f_00028e1fL, //  𨸟
          0x00028e36_00028e36L, //  𨸶
          0x00028e89_00028e89L, //  𨺉
          0x00028eeb_00028eebL, //  𨻫
          0x00028f32_00028f32L, //  𨼲
          0x00028ff8_00028ff8L, //  𨿸
          0x000292a0_000292a0L, //  𩊠
          0x000292b1_000292b1L, //  𩊱
          0x00029490_00029490L, //  𩒐
          0x000295cf_000295cfL, //  𩗏
          0x0002967f_0002967fL, //  𩙿
          0x000296f0_000296f0L, //  𩛰
          0x00029719_00029719L, //  𩜙
          0x00029750_00029750L, //  𩝐
          0x000298c6_000298c6L, //  𩣆
          0x00029a72_00029a72L, //  𩩲
          0x00029ddb_00029ddbL, //  𩷛
          0x00029e15_00029e15L, //  𩸕
          0x00029e3d_00029e3dL, //  𩸽
          0x00029e49_00029e49L, //  𩹉
          0x00029e8a_00029e8aL, //  𩺊
          0x00029ec4_00029ec4L, //  𩻄
          0x00029edb_00029edbL, //  𩻛
          0x00029ee9_00029ee9L, //  𩻩
          0x00029fce_00029fceL, //  𩿎
          0x0002a01a_0002a01aL, //  𪀚
          0x0002a02f_0002a02fL, //  𪀯
          0x0002a082_0002a082L, //  𪂂
          0x0002a0f9_0002a0f9L, //  𪃹
          0x0002a190_0002a190L, //  𪆐
          0x0002a38c_0002a38cL, //  𪎌
          0x0002a437_0002a437L, //  𪐷
          0x0002a5f1_0002a5f1L, //  𪗱
          0x0002a602_0002a602L, //  𪘂
          0x0002a61a_0002a61aL, //  𪘚
          0x0002a6b2_0002a6b2L, //  𪚲
          0x0002a708_0002a708L, //  𪜈
          0x0002a70c_0002a70cL, //  𪜌
          0x0002a716_0002a716L, //  𪜖
          0x0002a72a_0002a72aL, //  𪜪
          0x0002a738_0002a738L, //  𪜸
          0x0002a73d_0002a73dL, //  𪜽
          0x0002a746_0002a746L, //  𪝆
          0x0002a758_0002a758L, //  𪝘
          0x0002a75f_0002a75fL, //  𪝟
          0x0002a784_0002a784L, //  𪞄
          0x0002a789_0002a789L, //  𪞉
          0x0002a7b5_0002a7b5L, //  𪞵
          0x0002a7c7_0002a7c7L, //  𪟇
          0x0002a7d7_0002a7d7L, //  𪟗
          0x0002a7e7_0002a7e7L, //  𪟧
          0x0002a7f7_0002a7f7L, //  𪟷
          0x0002a845_0002a845L, //  𪡅
          0x0002a860_0002a861L, //  𪡠 𪡡
          0x0002a868_0002a868L, //  𪡨
          0x0002a879_0002a879L, //  𪡹
          0x0002a882_0002a882L, //  𪢂
          0x0002a88a_0002a88aL, //  𪢊
          0x0002a8a6_0002a8a6L, //  𪢦
          0x0002a8b1_0002a8b1L, //  𪢱
          0x0002a8e8_0002a8e8L, //  𪣨
          0x0002a915_0002a915L, //  𪤕
          0x0002a959_0002a959L, //  𪥙
          0x0002a963_0002a963L, //  𪥣
          0x0002a96e_0002a96eL, //  𪥮
          0x0002a972_0002a972L, //  𪥲
          0x0002a97d_0002a97dL, //  𪥽
          0x0002a98a_0002a98aL, //  𪦊
          0x0002a98f_0002a98fL, //  𪦏
          0x0002a9a6_0002a9a6L, //  𪦦
          0x0002a9a8_0002a9a8L, //  𪦨
          0x0002a9ae_0002a9aeL, //  𪦮
          0x0002a9ba_0002a9baL, //  𪦺
          0x0002aa0e_0002aa0eL, //  𪨎
          0x0002aa21_0002aa22L, //  𪨡 𪨢
          0x0002aa2c_0002aa2cL, //  𪨬
          0x0002aa46_0002aa46L, //  𪩆
          0x0002aa63_0002aa63L, //  𪩣
          0x0002aa69_0002aa69L, //  𪩩
          0x0002aa76_0002aa76L, //  𪩶
          0x0002aa8d_0002aa8dL, //  𪪍
          0x0002aabf_0002aabfL, //  𪪿
          0x0002aac4_0002aac4L, //  𪫄
          0x0002aae7_0002aae7L, //  𪫧
          0x0002aaec_0002aaecL, //  𪫬
          0x0002aaf8_0002aaf8L, //  𪫸
          0x0002ab05_0002ab05L, //  𪬅
          0x0002ab23_0002ab23L, //  𪬣
          0x0002ab3f_0002ab3fL, //  𪬿
          0x0002ab49_0002ab49L, //  𪭉
          0x0002ab71_0002ab71L, //  𪭱
          0x0002ab77_0002ab77L, //  𪭷
          0x0002ab7c_0002ab7cL, //  𪭼
          0x0002ab87_0002ab87L, //  𪮇
          0x0002abac_0002abacL, //  𪮬
          0x0002abb7_0002abb7L, //  𪮷
          0x0002abc6_0002abc6L, //  𪯆
          0x0002ac0c_0002ac0cL, //  𪰌
          0x0002ac12_0002ac12L, //  𪰒
          0x0002ac2a_0002ac2aL, //  𪰪
          0x0002ac40_0002ac40L, //  𪱀
          0x0002ac64_0002ac64L, //  𪱤
          0x0002ac69_0002ac69L, //  𪱩
          0x0002ac71_0002ac73L, //  𪱱 𪱲 𪱳
          0x0002ac76_0002ac76L, //  𪱶
          0x0002ac79_0002ac79L, //  𪱹
          0x0002ac7b_0002ac7bL, //  𪱻
          0x0002ac86_0002ac87L, //  𪲆 𪲇
          0x0002ac92_0002ac93L, //  𪲒 𪲓
          0x0002ac96_0002ac96L, //  𪲖
          0x0002aca2_0002aca2L, //  𪲢
          0x0002aca6_0002aca6L, //  𪲦
          0x0002aca8_0002aca8L, //  𪲨
          0x0002acac_0002acacL, //  𪲬
          0x0002acb3_0002acb3L, //  𪲳
          0x0002acbc_0002acbcL, //  𪲼
          0x0002acc3_0002acc3L, //  𪳃
          0x0002acc9_0002accaL, //  𪳉 𪳊
          0x0002acd2_0002acd3L, //  𪳒 𪳓
          0x0002acd6_0002acd6L, //  𪳖
          0x0002ace1_0002ace2L, //  𪳡 𪳢
          0x0002ace4_0002ace4L, //  𪳤
          0x0002ace7_0002ace7L, //  𪳧
          0x0002acf1_0002acf1L, //  𪳱
          0x0002acf3_0002acf3L, //  𪳳
          0x0002acfa_0002acfaL, //  𪳺
          0x0002ad00_0002ad00L, //  𪴀
          0x0002ad06_0002ad07L, //  𪴆 𪴇
          0x0002ad0e_0002ad0eL, //  𪴎
          0x0002ad10_0002ad10L, //  𪴐
          0x0002ad13_0002ad13L, //  𪴓
          0x0002ad15_0002ad15L, //  𪴕
          0x0002ad1c_0002ad1cL, //  𪴜
          0x0002ad20_0002ad21L, //  𪴠 𪴡
          0x0002ad25_0002ad25L, //  𪴥
          0x0002ad61_0002ad61L, //  𪵡
          0x0002ad89_0002ad89L, //  𪶉
          0x0002adcc_0002adccL, //  𪷌
          0x0002ae09_0002ae09L, //  𪸉
          0x0002ae22_0002ae22L, //  𪸢
          0x0002ae2b_0002ae2bL, //  𪸫
          0x0002ae76_0002ae76L, //  𪹶
          0x0002ae8a_0002ae8aL, //  𪺊
          0x0002ae90_0002ae90L, //  𪺐
          0x0002aeb2_0002aeb2L, //  𪺲
          0x0002aecc_0002aeccL, //  𪻌
          0x0002af2a_0002af2aL, //  𪼪
          0x0002af36_0002af39L, //  𪼶 𪼷 𪼸 𪼹
          0x0002af3b_0002af3cL, //  𪼻 𪼼
          0x0002af3f_0002af3fL, //  𪼿
          0x0002af4b_0002af4bL, //  𪽋
          0x0002af58_0002af58L, //  𪽘
          0x0002af65_0002af65L, //  𪽥
          0x0002af76_0002af76L, //  𪽶
          0x0002af7a_0002af7aL, //  𪽺
          0x0002af97_0002af97L, //  𪾗
          0x0002afa7_0002afa8L, //  𪾧 𪾨
          0x0002afac_0002afacL, //  𪾬
          0x0002afb2_0002afb2L, //  𪾲
          0x0002afb5_0002afb5L, //  𪾵
          0x0002afbb_0002afbcL, //  𪾻 𪾼
          0x0002afbf_0002afbfL, //  𪾿
          0x0002afc8_0002afc8L, //  𪿈
          0x0002afd3_0002afd3L, //  𪿓
          0x0002affc_0002affcL, //  𪿼
          0x0002b003_0002b003L, //  𫀃
          0x0002b016_0002b016L, //  𫀖
          0x0002b027_0002b027L, //  𫀧
          0x0002b039_0002b039L, //  𫀹
          0x0002b041_0002b041L, //  𫁁
          0x0002b046_0002b046L, //  𫁆
          0x0002b048_0002b048L, //  𫁈
          0x0002b051_0002b051L, //  𫁑
          0x0002b056_0002b057L, //  𫁖 𫁗
          0x0002b076_0002b076L, //  𫁶
          0x0002b07d_0002b07dL, //  𫁽
          0x0002b085_0002b085L, //  𫂅
          0x0002b092_0002b092L, //  𫂒
          0x0002b0a3_0002b0a3L, //  𫂣
          0x0002b0a9_0002b0a9L, //  𫂩
          0x0002b0b2_0002b0b2L, //  𫂲
          0x0002b0c4_0002b0c4L, //  𫃄
          0x0002b0c7_0002b0c7L, //  𫃇
          0x0002b0e5_0002b0e5L, //  𫃥
          0x0002b0f2_0002b0f2L, //  𫃲
          0x0002b10a_0002b10aL, //  𫄊
          0x0002b117_0002b117L, //  𫄗
          0x0002b17f_0002b17fL, //  𫅿
          0x0002b193_0002b193L, //  𫆓
          0x0002b19b_0002b19bL, //  𫆛
          0x0002b1a8_0002b1a8L, //  𫆨
          0x0002b1b7_0002b1b7L, //  𫆷
          0x0002b1ba_0002b1baL, //  𫆺
          0x0002b1c5_0002b1c5L, //  𫇅
          0x0002b1dc_0002b1dcL, //  𫇜
          0x0002b1e0_0002b1e0L, //  𫇠
          0x0002b1e8_0002b1e9L, //  𫇨 𫇩
          0x0002b1fb_0002b1fbL, //  𫇻
          0x0002b1ff_0002b1ffL, //  𫇿
          0x0002b202_0002b202L, //  𫈂
          0x0002b206_0002b207L, //  𫈆 𫈇
          0x0002b213_0002b213L, //  𫈓
          0x0002b21e_0002b21eL, //  𫈞
          0x0002b224_0002b224L, //  𫈤
          0x0002b239_0002b239L, //  𫈹
          0x0002b23d_0002b23dL, //  𫈽
          0x0002b240_0002b240L, //  𫉀
          0x0002b247_0002b247L, //  𫉇
          0x0002b251_0002b252L, //  𫉑 𫉒
          0x0002b259_0002b259L, //  𫉙
          0x0002b25c_0002b25cL, //  𫉜
          0x0002b260_0002b260L, //  𫉠
          0x0002b26b_0002b26bL, //  𫉫
          0x0002b270_0002b270L, //  𫉰
          0x0002b27b_0002b27bL, //  𫉻
          0x0002b280_0002b280L, //  𫊀
          0x0002b284_0002b284L, //  𫊄
          0x0002b298_0002b298L, //  𫊘
          0x0002b29c_0002b29cL, //  𫊜
          0x0002b2a6_0002b2a7L, //  𫊦 𫊧
          0x0002b2b0_0002b2b0L, //  𫊰
          0x0002b2b3_0002b2b3L, //  𫊳
          0x0002b2ba_0002b2baL, //  𫊺
          0x0002b2bd_0002b2beL, //  𫊽 𫊾
          0x0002b2c1_0002b2c3L, //  𫋁 𫋂 𫋃
          0x0002b2c5_0002b2c6L, //  𫋅 𫋆
          0x0002b2c9_0002b2c9L, //  𫋉
          0x0002b2cb_0002b2cbL, //  𫋋
          0x0002b2ce_0002b2cfL, //  𫋎 𫋏
          0x0002b2d5_0002b2d6L, //  𫋕 𫋖
          0x0002b2d8_0002b2d8L, //  𫋘
          0x0002b2da_0002b2daL, //  𫋚
          0x0002b2dd_0002b2ddL, //  𫋝
          0x0002b2e2_0002b2e2L, //  𫋢
          0x0002b2e4_0002b2e5L, //  𫋤 𫋥
          0x0002b2ec_0002b2ecL, //  𫋬
          0x0002b2ef_0002b2efL, //  𫋯
          0x0002b2f5_0002b2f5L, //  𫋵
          0x0002b2fc_0002b2fcL, //  𫋼
          0x0002b305_0002b305L, //  𫌅
          0x0002b308_0002b308L, //  𫌈
          0x0002b313_0002b313L, //  𫌓
          0x0002b31d_0002b31dL, //  𫌝
          0x0002b31f_0002b31fL, //  𫌟
          0x0002b325_0002b325L, //  𫌥
          0x0002b327_0002b327L, //  𫌧
          0x0002b337_0002b337L, //  𫌷
          0x0002b344_0002b344L, //  𫍄
          0x0002b346_0002b346L, //  𫍆
          0x0002b34b_0002b34bL, //  𫍋
          0x0002b352_0002b352L, //  𫍒
          0x0002b354_0002b354L, //  𫍔
          0x0002b381_0002b381L, //  𫎁
          0x0002b395_0002b395L, //  𫎕
          0x0002b3ae_0002b3aeL, //  𫎮
          0x0002b3c0_0002b3c0L, //  𫏀
          0x0002b3c2_0002b3c2L, //  𫏂
          0x0002b3c9_0002b3c9L, //  𫏉
          0x0002b3cf_0002b3cfL, //  𫏏
          0x0002b3d4_0002b3d4L, //  𫏔
          0x0002b3e1_0002b3e1L, //  𫏡
          0x0002b3e9_0002b3e9L, //  𫏩
          0x0002b3ee_0002b3eeL, //  𫏮
          0x0002b3f2_0002b3f2L, //  𫏲
          0x0002b41e_0002b41eL, //  𫐞
          0x0002b424_0002b424L, //  𫐤
          0x0002b42f_0002b42fL, //  𫐯
          0x0002b456_0002b456L, //  𫑖
          0x0002b475_0002b475L, //  𫑵
          0x0002b481_0002b481L, //  𫒁
          0x0002b486_0002b486L, //  𫒆
          0x0002b489_0002b489L, //  𫒉
          0x0002b48e_0002b48eL, //  𫒎
          0x0002b492_0002b493L, //  𫒒 𫒓
          0x0002b496_0002b496L, //  𫒖
          0x0002b499_0002b49aL, //  𫒙 𫒚
          0x0002b49c_0002b49cL, //  𫒜
          0x0002b4a0_0002b4a0L, //  𫒠
          0x0002b4a6_0002b4a6L, //  𫒦
          0x0002b4aa_0002b4aaL, //  𫒪
          0x0002b4af_0002b4afL, //  𫒯
          0x0002b4b4_0002b4b4L, //  𫒴
          0x0002b4bc_0002b4bcL, //  𫒼
          0x0002b4c0_0002b4c0L, //  𫓀
          0x0002b4c3_0002b4c3L, //  𫓃
          0x0002b4ca_0002b4caL, //  𫓊
          0x0002b4cd_0002b4cdL, //  𫓍
          0x0002b4d0_0002b4d0L, //  𫓐
          0x0002b4d4_0002b4d4L, //  𫓔
          0x0002b4d8_0002b4d8L, //  𫓘
          0x0002b4da_0002b4daL, //  𫓚
          0x0002b519_0002b51bL, //  𫔙 𫔚 𫔛
          0x0002b51d_0002b51fL, //  𫔝 𫔞 𫔟
          0x0002b52a_0002b52aL, //  𫔪
          0x0002b55f_0002b55fL, //  𫕟
          0x0002b563_0002b563L, //  𫕣
          0x0002b57b_0002b57bL, //  𫕻
          0x0002b58b_0002b58bL, //  𫖋
          0x0002b59d_0002b59dL, //  𫖝
          0x0002b59f_0002b59fL, //  𫖟
          0x0002b5a8_0002b5a8L, //  𫖨
          0x0002b5c6_0002b5c6L, //  𫗆
          0x0002b5db_0002b5dbL, //  𫗛
          0x0002b607_0002b607L, //  𫘇
          0x0002b60b_0002b60bL, //  𫘋
          0x0002b615_0002b615L, //  𫘕
          0x0002b639_0002b639L, //  𫘹
          0x0002b648_0002b648L, //  𫙈
          0x0002b64b_0002b64bL, //  𫙋
          0x0002b64f_0002b64fL, //  𫙏
          0x0002b651_0002b652L, //  𫙑 𫙒
          0x0002b655_0002b656L, //  𫙕 𫙖
          0x0002b65a_0002b65aL, //  𫙚
          0x0002b65c_0002b65cL, //  𫙜
          0x0002b65e_0002b661L, //  𫙞 𫙟 𫙠 𫙡
          0x0002b665_0002b665L, //  𫙥
          0x0002b667_0002b66cL, //  𫙧 𫙨 𫙩 𫙪 𫙫 𫙬
          0x0002b66e_0002b676L, //  𫙮 𫙯 𫙰 𫙱 𫙲 𫙳 𫙴 𫙵 𫙶
          0x0002b678_0002b679L, //  𫙸 𫙹
          0x0002b67b_0002b67cL, //  𫙻 𫙼
          0x0002b67e_0002b67eL, //  𫙾
          0x0002b680_0002b680L, //  𫚀
          0x0002b682_0002b682L, //  𫚂
          0x0002b684_0002b687L, //  𫚄 𫚅 𫚆 𫚇
          0x0002b6b0_0002b6b0L, //  𫚰
          0x0002b6b5_0002b6b5L, //  𫚵
          0x0002b6ba_0002b6bbL, //  𫚺 𫚻
          0x0002b6be_0002b6beL, //  𫚾
          0x0002b6c0_0002b6c0L, //  𫛀
          0x0002b6c4_0002b6c5L, //  𫛄 𫛅
          0x0002b6c7_0002b6c7L, //  𫛇
          0x0002b6c9_0002b6caL, //  𫛉 𫛊
          0x0002b6cd_0002b6cdL, //  𫛍
          0x0002b6d1_0002b6d2L, //  𫛑 𫛒
          0x0002b6d4_0002b6d4L, //  𫛔
          0x0002b6d6_0002b6d9L, //  𫛖 𫛗 𫛘 𫛙
          0x0002b71b_0002b71bL, //  𫜛
          0x0002b724_0002b724L, //  𫜤
          0x0002b740_0002b747L, //  𫝀 𫝁 𫝂 𫝃 𫝄 𫝅 𫝆 𫝇
          0x0002b749_0002b74aL, //  𫝉 𫝊
          0x0002b74c_0002b74dL, //  𫝌 𫝍
          0x0002b750_0002b752L, //  𫝐 𫝑 𫝒
          0x0002b754_0002b757L, //  𫝔 𫝕 𫝖 𫝗
          0x0002b75d_0002b75dL, //  𫝝
          0x0002b75f_0002b764L, //  𫝟 𫝠 𫝡 𫝢 𫝣 𫝤
          0x0002b76f_0002b773L, //  𫝯 𫝰 𫝱 𫝲 𫝳
          0x0002b776_0002b776L, //  𫝶
          0x0002b778_0002b779L, //  𫝸 𫝹
          0x0002b77f_0002b781L, //  𫝿 𫞀 𫞁
          0x0002b783_0002b784L, //  𫞃 𫞄
          0x0002b786_0002b78aL, //  𫞆 𫞇 𫞈 𫞉 𫞊
          0x0002b78c_0002b793L, //  𫞌 𫞍 𫞎 𫞏 𫞐 𫞑 𫞒 𫞓
          0x0002b795_0002b796L, //  𫞕 𫞖
          0x0002b798_0002b799L, //  𫞘 𫞙
          0x0002b79c_0002b79cL, //  𫞜
          0x0002b79e_0002b79fL, //  𫞞 𫞟
          0x0002b7a4_0002b7a4L, //  𫞤
          0x0002b7aa_0002b7abL, //  𫞪 𫞫
          0x0002b7ae_0002b7b2L, //  𫞮 𫞯 𫞰 𫞱 𫞲
          0x0002b7b4_0002b7b6L, //  𫞴 𫞵 𫞶
          0x0002b7b8_0002b7baL, //  𫞸 𫞹 𫞺
          0x0002b7be_0002b7c1L, //  𫞾 𫞿 𫟀 𫟁
          0x0002b7c8_0002b7c8L, //  𫟈
          0x0002b7cb_0002b7d0L, //  𫟋 𫟌 𫟍 𫟎 𫟏 𫟐
          0x0002b7d2_0002b7d4L, //  𫟒 𫟓 𫟔
          0x0002b7d6_0002b7d6L, //  𫟖
          0x0002b7d8_0002b7ddL, //  𫟘 𫟙 𫟚 𫟛 𫟜 𫟝
          0x0002b7e8_0002b7eaL, //  𫟨 𫟩 𫟪
          0x0002b7ef_0002b7f1L, //  𫟯 𫟰 𫟱
          0x0002b803_0002b803L, //  𫠃
          0x0002b809_0002b809L, //  𫠉
          0x0002b813_0002b815L, //  𫠓 𫠔 𫠕
          0x0002b818_0002b818L, //  𫠘
          0x0002b81d_0002b81dL, //  𫠝
          0x0002b864_0002b864L, //  𫡤
          0x0002b877_0002b877L, //  𫡷
          0x0002b88f_0002b88fL, //  𫢏
          0x0002b8bd_0002b8bdL, //  𫢽
          0x0002b8c1_0002b8c1L, //  𫣁
          0x0002b8c4_0002b8c4L, //  𫣄
          0x0002b8d2_0002b8d2L, //  𫣒
          0x0002b8d5_0002b8d5L, //  𫣕
          0x0002b8f5_0002b8f5L, //  𫣵
          0x0002b91c_0002b91cL, //  𫤜
          0x0002b91e_0002b91eL, //  𫤞
          0x0002b960_0002b960L, //  𫥠
          0x0002b972_0002b972L, //  𫥲
          0x0002b97b_0002b97bL, //  𫥻
          0x0002b988_0002b988L, //  𫦈
          0x0002b9a4_0002b9a5L, //  𫦤 𫦥
          0x0002b9ab_0002b9abL, //  𫦫
          0x0002b9b6_0002b9b6L, //  𫦶
          0x0002b9b9_0002b9b9L, //  𫦹
          0x0002b9bf_0002b9bfL, //  𫦿
          0x0002ba2d_0002ba2dL, //  𫨭
          0x0002ba6e_0002ba6eL, //  𫩮
          0x0002ba9b_0002ba9bL, //  𫪛
          0x0002baa4_0002baa4L, //  𫪤
          0x0002bacd_0002bacdL, //  𫫍
          0x0002baed_0002baedL, //  𫫭
          0x0002baf1_0002baf1L, //  𫫱
          0x0002bb4f_0002bb4fL, //  𫭏
          0x0002bb57_0002bb57L, //  𫭗
          0x0002bb71_0002bb71L, //  𫭱
          0x0002bb8e_0002bb8eL, //  𫮎
          0x0002bba4_0002bba4L, //  𫮤
          0x0002bbad_0002bbadL, //  𫮭
          0x0002bbba_0002bbbaL, //  𫮺
          0x0002bbbe_0002bbbeL, //  𫮾
          0x0002bbf8_0002bbf8L, //  𫯸
          0x0002bc00_0002bc01L, //  𫰀 𫰁
          0x0002bc13_0002bc13L, //  𫰓
          0x0002bc1a_0002bc1aL, //  𫰚
          0x0002bc48_0002bc49L, //  𫱈 𫱉
          0x0002bc4b_0002bc4bL, //  𫱋
          0x0002bc63_0002bc63L, //  𫱣
          0x0002bc67_0002bc67L, //  𫱧
          0x0002bc7b_0002bc7bL, //  𫱻
          0x0002bca7_0002bca7L, //  𫲧
          0x0002bca9_0002bca9L, //  𫲩
          0x0002bcae_0002bcaeL, //  𫲮
          0x0002bcb5_0002bcb5L, //  𫲵
          0x0002bccd_0002bccdL, //  𫳍
          0x0002bd34_0002bd34L, //  𫴴
          0x0002bd57_0002bd57L, //  𫵗
          0x0002bd69_0002bd69L, //  𫵩
          0x0002bd74_0002bd74L, //  𫵴
          0x0002bd7d_0002bd7dL, //  𫵽
          0x0002bd99_0002bd99L, //  𫶙
          0x0002bda0_0002bda0L, //  𫶠
          0x0002bda7_0002bda7L, //  𫶧
          0x0002bdd1_0002bdd2L, //  𫷑 𫷒
          0x0002be28_0002be28L, //  𫸨
          0x0002be3e_0002be3eL, //  𫸾
          0x0002be53_0002be53L, //  𫹓
          0x0002be67_0002be68L, //  𫹧 𫹨
          0x0002be8f_0002be8fL, //  𫺏
          0x0002be97_0002be97L, //  𫺗
          0x0002bead_0002beadL, //  𫺭
          0x0002bedb_0002bedbL, //  𫻛
          0x0002bee5_0002bee5L, //  𫻥
          0x0002bee7_0002bee7L, //  𫻧
          0x0002bf0b_0002bf0bL, //  𫼋
          0x0002bf13_0002bf13L, //  𫼓
          0x0002bf33_0002bf33L, //  𫼳
          0x0002bf6a_0002bf6aL, //  𫽪
          0x0002bf6c_0002bf6cL, //  𫽬
          0x0002bf85_0002bf85L, //  𫾅
          0x0002bf92_0002bf92L, //  𫾒
          0x0002bfed_0002bfedL, //  𫿭
          0x0002bffe_0002bffeL, //  𫿾
          0x0002c017_0002c017L, //  𬀗
          0x0002c027_0002c027L, //  𬀧
          0x0002c02c_0002c02cL, //  𬀬
          0x0002c083_0002c083L, //  𬂃
          0x0002c097_0002c097L, //  𬂗
          0x0002c09b_0002c09cL, //  𬂛 𬂜
          0x0002c09f_0002c0a1L, //  𬂟 𬂠 𬂡
          0x0002c0ac_0002c0acL, //  𬂬
          0x0002c0b3_0002c0b3L, //  𬂳
          0x0002c0b5_0002c0b6L, //  𬂵 𬂶
          0x0002c0bf_0002c0bfL, //  𬂿
          0x0002c0cf_0002c0cfL, //  𬃏
          0x0002c0d3_0002c0d3L, //  𬃓
          0x0002c0d7_0002c0d7L, //  𬃗
          0x0002c0dd_0002c0ddL, //  𬃝
          0x0002c0e2_0002c0e3L, //  𬃢 𬃣
          0x0002c0f0_0002c0f0L, //  𬃰
          0x0002c0f5_0002c0f6L, //  𬃵 𬃶
          0x0002c0fa_0002c0faL, //  𬃺
          0x0002c0fe_0002c0feL, //  𬃾
          0x0002c109_0002c109L, //  𬄉
          0x0002c10b_0002c10cL, //  𬄋 𬄌
          0x0002c10e_0002c10eL, //  𬄎
          0x0002c112_0002c112L, //  𬄒
          0x0002c116_0002c117L, //  𬄖 𬄗
          0x0002c11a_0002c11aL, //  𬄚
          0x0002c11f_0002c120L, //  𬄟 𬄠
          0x0002c125_0002c125L, //  𬄥
          0x0002c127_0002c127L, //  𬄧
          0x0002c12b_0002c12bL, //  𬄫
          0x0002c132_0002c132L, //  𬄲
          0x0002c136_0002c136L, //  𬄶
          0x0002c138_0002c138L, //  𬄸
          0x0002c13a_0002c13cL, //  𬄺 𬄻 𬄼
          0x0002c145_0002c145L, //  𬅅
          0x0002c147_0002c147L, //  𬅇
          0x0002c14a_0002c14bL, //  𬅊 𬅋
          0x0002c14e_0002c14eL, //  𬅎
          0x0002c152_0002c152L, //  𬅒
          0x0002c157_0002c157L, //  𬅗
          0x0002c18d_0002c18dL, //  𬆍
          0x0002c1d0_0002c1d0L, //  𬇐
          0x0002c1dc_0002c1dcL, //  𬇜
          0x0002c207_0002c207L, //  𬈇
          0x0002c235_0002c235L, //  𬈵
          0x0002c258_0002c258L, //  𬉘
          0x0002c27a_0002c27aL, //  𬉺
          0x0002c2ce_0002c2ceL, //  𬋎
          0x0002c2e0_0002c2e0L, //  𬋠
          0x0002c2ec_0002c2ecL, //  𬋬
          0x0002c329_0002c329L, //  𬌩
          0x0002c33c_0002c33cL, //  𬌼
          0x0002c354_0002c354L, //  𬍔
          0x0002c356_0002c356L, //  𬍖
          0x0002c36f_0002c36fL, //  𬍯
          0x0002c3a4_0002c3a4L, //  𬎤
          0x0002c3a6_0002c3a6L, //  𬎦
          0x0002c3ab_0002c3abL, //  𬎫
          0x0002c3ae_0002c3b0L, //  𬎮 𬎯 𬎰
          0x0002c3b4_0002c3b4L, //  𬎴
          0x0002c3c8_0002c3c8L, //  𬏈
          0x0002c3d0_0002c3d0L, //  𬏐
          0x0002c3e3_0002c3e3L, //  𬏣
          0x0002c3f6_0002c3f6L, //  𬏶
          0x0002c3fc_0002c3fcL, //  𬏼
          0x0002c424_0002c424L, //  𬐤
          0x0002c448_0002c448L, //  𬑈
          0x0002c44e_0002c44eL, //  𬑎
          0x0002c450_0002c450L, //  𬑐
          0x0002c456_0002c456L, //  𬑖
          0x0002c45c_0002c45dL, //  𬑜 𬑝
          0x0002c462_0002c462L, //  𬑢
          0x0002c468_0002c468L, //  𬑨
          0x0002c46c_0002c46cL, //  𬑬
          0x0002c47d_0002c47dL, //  𬑽
          0x0002c485_0002c485L, //  𬒅
          0x0002c4a8_0002c4a8L, //  𬒨
          0x0002c4ad_0002c4adL, //  𬒭
          0x0002c4c1_0002c4c1L, //  𬓁
          0x0002c4c4_0002c4c4L, //  𬓄
          0x0002c4c7_0002c4c7L, //  𬓇
          0x0002c4d6_0002c4d6L, //  𬓖
          0x0002c4e6_0002c4e6L, //  𬓦
          0x0002c4f3_0002c4f3L, //  𬓳
          0x0002c4f9_0002c4f9L, //  𬓹
          0x0002c500_0002c500L, //  𬔀
          0x0002c50e_0002c50fL, //  𬔎 𬔏
          0x0002c528_0002c528L, //  𬔨
          0x0002c52c_0002c52cL, //  𬔬
          0x0002c530_0002c530L, //  𬔰
          0x0002c538_0002c538L, //  𬔸
          0x0002c541_0002c541L, //  𬕁
          0x0002c54b_0002c54bL, //  𬕋
          0x0002c54e_0002c54eL, //  𬕎
          0x0002c567_0002c567L, //  𬕧
          0x0002c56e_0002c56eL, //  𬕮
          0x0002c576_0002c576L, //  𬕶
          0x0002c587_0002c587L, //  𬖇
          0x0002c592_0002c592L, //  𬖒
          0x0002c5a3_0002c5a3L, //  𬖣
          0x0002c5bb_0002c5bbL, //  𬖻
          0x0002c5d6_0002c5d6L, //  𬗖
          0x0002c5ec_0002c5ecL, //  𬗬
          0x0002c608_0002c608L, //  𬘈
          0x0002c670_0002c670L, //  𬙰
          0x0002c69d_0002c69dL, //  𬚝
          0x0002c6a9_0002c6a9L, //  𬚩
          0x0002c6c2_0002c6c2L, //  𬛂
          0x0002c6cc_0002c6ccL, //  𬛌
          0x0002c6d3_0002c6d3L, //  𬛓
          0x0002c70b_0002c70bL, //  𬜋
          0x0002c716_0002c717L, //  𬜖 𬜗
          0x0002c723_0002c723L, //  𬜣
          0x0002c729_0002c729L, //  𬜩
          0x0002c72d_0002c72eL, //  𬜭 𬜮
          0x0002c730_0002c732L, //  𬜰 𬜱 𬜲
          0x0002c734_0002c734L, //  𬜴
          0x0002c737_0002c737L, //  𬜷
          0x0002c73c_0002c73cL, //  𬜼
          0x0002c742_0002c742L, //  𬝂
          0x0002c74d_0002c74dL, //  𬝍
          0x0002c754_0002c754L, //  𬝔
          0x0002c756_0002c756L, //  𬝖
          0x0002c75d_0002c75eL, //  𬝝 𬝞
          0x0002c765_0002c766L, //  𬝥 𬝦
          0x0002c769_0002c769L, //  𬝩
          0x0002c76d_0002c76dL, //  𬝭
          0x0002c77b_0002c77bL, //  𬝻
          0x0002c77f_0002c77fL, //  𬝿
          0x0002c789_0002c789L, //  𬞉
          0x0002c78d_0002c78dL, //  𬞍
          0x0002c790_0002c790L, //  𬞐
          0x0002c794_0002c794L, //  𬞔
          0x0002c799_0002c799L, //  𬞙
          0x0002c7a1_0002c7a1L, //  𬞡
          0x0002c7b9_0002c7b9L, //  𬞹
          0x0002c7c2_0002c7c2L, //  𬟂
          0x0002c7cb_0002c7cbL, //  𬟋
          0x0002c7d0_0002c7d0L, //  𬟐
          0x0002c7d2_0002c7d2L, //  𬟒
          0x0002c7e2_0002c7e2L, //  𬟢
          0x0002c7e6_0002c7e6L, //  𬟦
          0x0002c7f6_0002c7f8L, //  𬟶 𬟷 𬟸
          0x0002c800_0002c800L, //  𬠀
          0x0002c802_0002c802L, //  𬠂
          0x0002c809_0002c80aL, //  𬠉 𬠊
          0x0002c80d_0002c80eL, //  𬠍 𬠎
          0x0002c812_0002c812L, //  𬠒
          0x0002c818_0002c819L, //  𬠘 𬠙
          0x0002c81d_0002c81fL, //  𬠝 𬠞 𬠟
          0x0002c824_0002c824L, //  𬠤
          0x0002c827_0002c827L, //  𬠧
          0x0002c829_0002c829L, //  𬠩
          0x0002c82c_0002c82fL, //  𬠬 𬠭 𬠮 𬠯
          0x0002c833_0002c834L, //  𬠳 𬠴
          0x0002c83b_0002c83bL, //  𬠻
          0x0002c840_0002c840L, //  𬡀
          0x0002c85b_0002c85bL, //  𬡛
          0x0002c869_0002c869L, //  𬡩
          0x0002c87d_0002c87eL, //  𬡽 𬡾
          0x0002c884_0002c884L, //  𬢄
          0x0002c886_0002c886L, //  𬢆
          0x0002c896_0002c896L, //  𬢖
          0x0002c8b7_0002c8b7L, //  𬢷
          0x0002c8bd_0002c8bdL, //  𬢽
          0x0002c8c4_0002c8c4L, //  𬣄
          0x0002c8d0_0002c8d0L, //  𬣐
          0x0002c8d5_0002c8d7L, //  𬣕 𬣖 𬣗
          0x0002c932_0002c932L, //  𬤲
          0x0002c981_0002c981L, //  𬦁
          0x0002c99c_0002c99cL, //  𬦜
          0x0002c9a6_0002c9a6L, //  𬦦
          0x0002c9ad_0002c9adL, //  𬦭
          0x0002c9b7_0002c9b7L, //  𬦷
          0x0002c9ba_0002c9baL, //  𬦺
          0x0002c9c5_0002c9c5L, //  𬧅
          0x0002c9d3_0002c9d3L, //  𬧓
          0x0002c9e7_0002c9e7L, //  𬧧
          0x0002c9ec_0002c9edL, //  𬧬 𬧭
          0x0002c9f3_0002c9f3L, //  𬧳
          0x0002ca1c_0002ca1cL, //  𬨜
          0x0002ca2f_0002ca2fL, //  𬨯
          0x0002ca56_0002ca56L, //  𬩖
          0x0002caa6_0002caa6L, //  𬪦
          0x0002caac_0002caacL, //  𬪬
          0x0002cabb_0002cabbL, //  𬪻
          0x0002cac4_0002cac4L, //  𬫄
          0x0002cad1_0002cad1L, //  𬫑
          0x0002cad6_0002cad7L, //  𬫖 𬫗
          0x0002cae0_0002cae0L, //  𬫠
          0x0002cae5_0002cae5L, //  𬫥
          0x0002cae7_0002cae7L, //  𬫧
          0x0002caed_0002caedL, //  𬫭
          0x0002caf0_0002caf1L, //  𬫰 𬫱
          0x0002cafb_0002cafbL, //  𬫻
          0x0002cafe_0002cafeL, //  𬫾
          0x0002cb02_0002cb02L, //  𬬂
          0x0002cb08_0002cb0aL, //  𬬈 𬬉 𬬊
          0x0002cb17_0002cb17L, //  𬬗
          0x0002cb22_0002cb23L, //  𬬢 𬬣
          0x0002cb85_0002cb86L, //  𬮅 𬮆
          0x0002cb91_0002cb91L, //  𬮑
          0x0002cb96_0002cb96L, //  𬮖
          0x0002cbc1_0002cbc1L, //  𬯁
          0x0002cbf2_0002cbf3L, //  𬯲 𬯳
          0x0002cbf8_0002cbf8L, //  𬯸
          0x0002cc08_0002cc08L, //  𬰈
          0x0002cc26_0002cc26L, //  𬰦
          0x0002cc28_0002cc28L, //  𬰨
          0x0002cc2c_0002cc2cL, //  𬰬
          0x0002cc4b_0002cc4bL, //  𬱋
          0x0002cc4f_0002cc4fL, //  𬱏
          0x0002cc74_0002cc74L, //  𬱴
          0x0002cc9d_0002cc9dL, //  𬲝
          0x0002ccf0_0002ccf0L, //  𬳰
          0x0002cd27_0002cd27L, //  𬴧
          0x0002cd2c_0002cd2cL, //  𬴬
          0x0002cd3d_0002cd3eL, //  𬴽 𬴾
          0x0002cd41_0002cd42L, //  𬵁 𬵂
          0x0002cd44_0002cd44L, //  𬵄
          0x0002cd46_0002cd48L, //  𬵆 𬵇 𬵈
          0x0002cd4a_0002cd4dL, //  𬵊 𬵋 𬵌 𬵍
          0x0002cd4f_0002cd52L, //  𬵏 𬵐 𬵑 𬵒
          0x0002cd54_0002cd57L, //  𬵔 𬵕 𬵖 𬵗
          0x0002cd5a_0002cd5aL, //  𬵚
          0x0002cd5c_0002cd63L, //  𬵜 𬵝 𬵞 𬵟 𬵠 𬵡 𬵢 𬵣
          0x0002cd66_0002cd6dL, //  𬵦 𬵧 𬵨 𬵩 𬵪 𬵫 𬵬 𬵭
          0x0002cd6f_0002cd77L, //  𬵯 𬵰 𬵱 𬵲 𬵳 𬵴 𬵵 𬵶 𬵷
          0x0002cd79_0002cd7bL, //  𬵹 𬵺 𬵻
          0x0002cd7d_0002cd7eL, //  𬵽 𬵾
          0x0002cdbf_0002cdbfL, //  𬶿
          0x0002cdc4_0002cdc4L, //  𬷄
          0x0002cdc9_0002cdc9L, //  𬷉
          0x0002cdd2_0002cdd2L, //  𬷒
          0x0002cdd4_0002cdd4L, //  𬷔
          0x0002cdd7_0002cdd7L, //  𬷗
          0x0002cdd9_0002cdd9L, //  𬷙
          0x0002cddd_0002cdddL, //  𬷝
          0x0002cde1_0002cde2L, //  𬷡 𬷢
          0x0002cde6_0002cde7L, //  𬷦 𬷧
          0x0002cdeb_0002cdecL, //  𬷫 𬷬
          0x0002cdf0_0002cdf1L, //  𬷰 𬷱
          0x0002cdf3_0002cdf3L, //  𬷳
          0x0002cdf5_0002cdf5L, //  𬷵
          0x0002cdf7_0002cdf7L, //  𬷷
          0x0002cdfa_0002cdfaL, //  𬷺
          0x0002ce3d_0002ce3dL, //  𬸽
          0x0002ceb0_0002ceb1L, //  𬺰 𬺱
          0x0002ceb3_0002ceb3L, //  𬺳
          0x0002ceb5_0002ceb6L, //  𬺵 𬺶
          0x0002ceb8_0002ceb8L, //  𬺸
          0x0002ceba_0002cebbL, //  𬺺 𬺻
          0x0002cebd_0002cebdL, //  𬺽
          0x0002cec0_0002cec2L, //  𬻀 𬻁 𬻂
          0x0002cec6_0002cec9L, //  𬻆 𬻇 𬻈 𬻉
          0x0002cecb_0002cecbL, //  𬻋
          0x0002cece_0002ceceL, //  𬻎
          0x0002ced0_0002ced2L, //  𬻐 𬻑 𬻒
          0x0002ced5_0002ced5L, //  𬻕
          0x0002ced8_0002ced8L, //  𬻘
          0x0002cedb_0002cedeL, //  𬻛 𬻜 𬻝 𬻞
          0x0002cee5_0002cee5L, //  𬻥
          0x0002cef5_0002cef5L, //  𬻵
          0x0002cef7_0002cef8L, //  𬻷 𬻸
          0x0002cefb_0002cefbL, //  𬻻
          0x0002ceff_0002cf02L, //  𬻿 𬼀 𬼁 𬼂
          0x0002cf04_0002cf04L, //  𬼄
          0x0002cf06_0002cf06L, //  𬼆
          0x0002cf08_0002cf08L, //  𬼈
          0x0002cf0c_0002cf0dL, //  𬼌 𬼍
          0x0002cf16_0002cf1aL, //  𬼖 𬼗 𬼘 𬼙 𬼚
          0x0002cf1c_0002cf1cL, //  𬼜
          0x0002cf1f_0002cf1fL, //  𬼟
          0x0002cf2b_0002cf2bL, //  𬼫
          0x0002cf38_0002cf38L, //  𬼸
          0x0002cf3a_0002cf3aL, //  𬼺
          0x0002cf3d_0002cf3fL, //  𬼽 𬼾 𬼿
          0x0002cf43_0002cf43L, //  𬽃
          0x0002cf46_0002cf4dL, //  𬽆 𬽇 𬽈 𬽉 𬽊 𬽋 𬽌 𬽍
          0x0002cf55_0002cf56L, //  𬽕 𬽖
          0x0002cf59_0002cf5aL, //  𬽙 𬽚
          0x0002cf5c_0002cf5dL, //  𬽜 𬽝
          0x0002cf60_0002cf61L, //  𬽠 𬽡
          0x0002cf63_0002cf64L, //  𬽣 𬽤
          0x0002cf66_0002cf67L, //  𬽦 𬽧
          0x0002cf69_0002cf69L, //  𬽩
          0x0002cf6b_0002cf6bL, //  𬽫
          0x0002cf6e_0002cf6eL, //  𬽮
          0x0002cf76_0002cf77L, //  𬽶 𬽷
          0x0002cf79_0002cf79L, //  𬽹
          0x0002cf81_0002cf81L, //  𬾁
          0x0002cf84_0002cf84L, //  𬾄
          0x0002cf8a_0002cf8aL, //  𬾊
          0x0002cf8e_0002cf8eL, //  𬾎
          0x0002cf97_0002cf98L, //  𬾗 𬾘
          0x0002cf9a_0002cf9aL, //  𬾚
          0x0002cfa8_0002cfa8L, //  𬾨
          0x0002cfae_0002cfaeL, //  𬾮
          0x0002cfb9_0002cfb9L, //  𬾹
          0x0002cfbb_0002cfbbL, //  𬾻
          0x0002cfbf_0002cfbfL, //  𬾿
          0x0002cfc2_0002cfc2L, //  𬿂
          0x0002cfdb_0002cfdbL, //  𬿛
          0x0002cfe6_0002cfe6L, //  𬿦
          0x0002cfed_0002cfedL, //  𬿭
          0x0002cff2_0002cff2L, //  𬿲
          0x0002cff6_0002cff6L, //  𬿶
          0x0002cffb_0002cffbL, //  𬿻
          0x0002d000_0002d000L, //  𭀀
          0x0002d009_0002d009L, //  𭀉
          0x0002d017_0002d01aL, //  𭀗 𭀘 𭀙 𭀚
          0x0002d01c_0002d01dL, //  𭀜 𭀝
          0x0002d01f_0002d021L, //  𭀟 𭀠 𭀡
          0x0002d023_0002d025L, //  𭀣 𭀤 𭀥
          0x0002d028_0002d028L, //  𭀨
          0x0002d030_0002d031L, //  𭀰 𭀱
          0x0002d044_0002d044L, //  𭁄
          0x0002d046_0002d049L, //  𭁆 𭁇 𭁈 𭁉
          0x0002d050_0002d050L, //  𭁐
          0x0002d054_0002d055L, //  𭁔 𭁕
          0x0002d057_0002d057L, //  𭁗
          0x0002d060_0002d063L, //  𭁠 𭁡 𭁢 𭁣
          0x0002d066_0002d066L, //  𭁦
          0x0002d068_0002d06bL, //  𭁨 𭁩 𭁪 𭁫
          0x0002d06d_0002d06dL, //  𭁭
          0x0002d06f_0002d06fL, //  𭁯
          0x0002d072_0002d074L, //  𭁲 𭁳 𭁴
          0x0002d076_0002d077L, //  𭁶 𭁷
          0x0002d07a_0002d07cL, //  𭁺 𭁻 𭁼
          0x0002d081_0002d081L, //  𭂁
          0x0002d085_0002d085L, //  𭂅
          0x0002d088_0002d088L, //  𭂈
          0x0002d08e_0002d08eL, //  𭂎
          0x0002d091_0002d091L, //  𭂑
          0x0002d093_0002d094L, //  𭂓 𭂔
          0x0002d097_0002d097L, //  𭂗
          0x0002d09a_0002d09aL, //  𭂚
          0x0002d09c_0002d09cL, //  𭂜
          0x0002d0ab_0002d0acL, //  𭂫 𭂬
          0x0002d0af_0002d0afL, //  𭂯
          0x0002d0b2_0002d0b4L, //  𭂲 𭂳 𭂴
          0x0002d0be_0002d0beL, //  𭂾
          0x0002d0c4_0002d0c6L, //  𭃄 𭃅 𭃆
          0x0002d0cc_0002d0ccL, //  𭃌
          0x0002d0d9_0002d0daL, //  𭃙 𭃚
          0x0002d0e1_0002d0e1L, //  𭃡
          0x0002d0e4_0002d0e5L, //  𭃤 𭃥
          0x0002d0e8_0002d0e8L, //  𭃨
          0x0002d0ed_0002d0edL, //  𭃭
          0x0002d0f0_0002d0f2L, //  𭃰 𭃱 𭃲
          0x0002d0ff_0002d0ffL, //  𭃿
          0x0002d10f_0002d10fL, //  𭄏
          0x0002d118_0002d118L, //  𭄘
          0x0002d11e_0002d11eL, //  𭄞
          0x0002d123_0002d123L, //  𭄣
          0x0002d125_0002d125L, //  𭄥
          0x0002d127_0002d127L, //  𭄧
          0x0002d12b_0002d12bL, //  𭄫
          0x0002d133_0002d134L, //  𭄳 𭄴
          0x0002d139_0002d139L, //  𭄹
          0x0002d13f_0002d143L, //  𭄿 𭅀 𭅁 𭅂 𭅃
          0x0002d145_0002d145L, //  𭅅
          0x0002d14a_0002d14aL, //  𭅊
          0x0002d14d_0002d14dL, //  𭅍
          0x0002d150_0002d152L, //  𭅐 𭅑 𭅒
          0x0002d154_0002d154L, //  𭅔
          0x0002d158_0002d158L, //  𭅘
          0x0002d15d_0002d15dL, //  𭅝
          0x0002d15f_0002d161L, //  𭅟 𭅠 𭅡
          0x0002d169_0002d169L, //  𭅩
          0x0002d16f_0002d170L, //  𭅯 𭅰
          0x0002d172_0002d175L, //  𭅲 𭅳 𭅴 𭅵
          0x0002d179_0002d17aL, //  𭅹 𭅺
          0x0002d17c_0002d17dL, //  𭅼 𭅽
          0x0002d181_0002d184L, //  𭆁 𭆂 𭆃 𭆄
          0x0002d186_0002d187L, //  𭆆 𭆇
          0x0002d189_0002d189L, //  𭆉
          0x0002d18f_0002d190L, //  𭆏 𭆐
          0x0002d192_0002d192L, //  𭆒
          0x0002d194_0002d194L, //  𭆔
          0x0002d199_0002d199L, //  𭆙
          0x0002d19e_0002d19eL, //  𭆞
          0x0002d1a1_0002d1a2L, //  𭆡 𭆢
          0x0002d1a4_0002d1a5L, //  𭆤 𭆥
          0x0002d1aa_0002d1acL, //  𭆪 𭆫 𭆬
          0x0002d1af_0002d1afL, //  𭆯
          0x0002d1b4_0002d1b4L, //  𭆴
          0x0002d1b6_0002d1b7L, //  𭆶 𭆷
          0x0002d1c1_0002d1c1L, //  𭇁
          0x0002d1c6_0002d1c6L, //  𭇆
          0x0002d1ca_0002d1caL, //  𭇊
          0x0002d1d1_0002d1d1L, //  𭇑
          0x0002d1d6_0002d1d7L, //  𭇖 𭇗
          0x0002d1e2_0002d1e2L, //  𭇢
          0x0002d1e5_0002d1e5L, //  𭇥
          0x0002d1ea_0002d1eaL, //  𭇪
          0x0002d206_0002d206L, //  𭈆
          0x0002d21d_0002d21dL, //  𭈝
          0x0002d22c_0002d22cL, //  𭈬
          0x0002d239_0002d239L, //  𭈹
          0x0002d23c_0002d23cL, //  𭈼
          0x0002d247_0002d248L, //  𭉇 𭉈
          0x0002d258_0002d259L, //  𭉘 𭉙
          0x0002d260_0002d260L, //  𭉠
          0x0002d262_0002d262L, //  𭉢
          0x0002d269_0002d269L, //  𭉩
          0x0002d275_0002d275L, //  𭉵
          0x0002d27f_0002d27fL, //  𭉿
          0x0002d289_0002d28aL, //  𭊉 𭊊
          0x0002d28c_0002d28cL, //  𭊌
          0x0002d28f_0002d28fL, //  𭊏
          0x0002d2c6_0002d2c6L, //  𭋆
          0x0002d2d8_0002d2d8L, //  𭋘
          0x0002d2db_0002d2dbL, //  𭋛
          0x0002d30c_0002d30cL, //  𭌌
          0x0002d314_0002d314L, //  𭌔
          0x0002d326_0002d326L, //  𭌦
          0x0002d330_0002d330L, //  𭌰
          0x0002d34f_0002d34fL, //  𭍏
          0x0002d35e_0002d35eL, //  𭍞
          0x0002d360_0002d360L, //  𭍠
          0x0002d365_0002d366L, //  𭍥 𭍦
          0x0002d376_0002d376L, //  𭍶
          0x0002d37b_0002d37cL, //  𭍻 𭍼
          0x0002d380_0002d380L, //  𭎀
          0x0002d385_0002d385L, //  𭎅
          0x0002d388_0002d388L, //  𭎈
          0x0002d38e_0002d38eL, //  𭎎
          0x0002d395_0002d395L, //  𭎕
          0x0002d399_0002d399L, //  𭎙
          0x0002d39b_0002d39cL, //  𭎛 𭎜
          0x0002d3a2_0002d3a2L, //  𭎢
          0x0002d3a4_0002d3a4L, //  𭎤
          0x0002d3ae_0002d3aeL, //  𭎮
          0x0002d3c0_0002d3c0L, //  𭏀
          0x0002d3cd_0002d3cdL, //  𭏍
          0x0002d3d0_0002d3d0L, //  𭏐
          0x0002d3d5_0002d3d5L, //  𭏕
          0x0002d3db_0002d3dbL, //  𭏛
          0x0002d3fe_0002d3feL, //  𭏾
          0x0002d400_0002d400L, //  𭐀
          0x0002d40a_0002d40aL, //  𭐊
          0x0002d412_0002d412L, //  𭐒
          0x0002d415_0002d416L, //  𭐕 𭐖
          0x0002d419_0002d419L, //  𭐙
          0x0002d41d_0002d41dL, //  𭐝
          0x0002d420_0002d420L, //  𭐠
          0x0002d422_0002d423L, //  𭐢 𭐣
          0x0002d425_0002d427L, //  𭐥 𭐦 𭐧
          0x0002d42b_0002d42cL, //  𭐫 𭐬
          0x0002d42e_0002d42eL, //  𭐮
          0x0002d430_0002d431L, //  𭐰 𭐱
          0x0002d433_0002d434L, //  𭐳 𭐴
          0x0002d438_0002d438L, //  𭐸
          0x0002d442_0002d443L, //  𭑂 𭑃
          0x0002d445_0002d445L, //  𭑅
          0x0002d44a_0002d44bL, //  𭑊 𭑋
          0x0002d44d_0002d44dL, //  𭑍
          0x0002d44f_0002d44fL, //  𭑏
          0x0002d451_0002d452L, //  𭑑 𭑒
          0x0002d455_0002d456L, //  𭑕 𭑖
          0x0002d458_0002d458L, //  𭑘
          0x0002d45a_0002d45aL, //  𭑚
          0x0002d45c_0002d45cL, //  𭑜
          0x0002d45e_0002d45fL, //  𭑞 𭑟
          0x0002d463_0002d463L, //  𭑣
          0x0002d468_0002d468L, //  𭑨
          0x0002d46a_0002d46aL, //  𭑪
          0x0002d46e_0002d46fL, //  𭑮 𭑯
          0x0002d475_0002d475L, //  𭑵
          0x0002d47e_0002d480L, //  𭑾 𭑿 𭒀
          0x0002d491_0002d491L, //  𭒑
          0x0002d494_0002d494L, //  𭒔
          0x0002d497_0002d497L, //  𭒗
          0x0002d49e_0002d49eL, //  𭒞
          0x0002d4a1_0002d4a1L, //  𭒡
          0x0002d4ad_0002d4adL, //  𭒭
          0x0002d4b8_0002d4b8L, //  𭒸
          0x0002d4bc_0002d4bdL, //  𭒼 𭒽
          0x0002d4c2_0002d4c2L, //  𭓂
          0x0002d4c7_0002d4c8L, //  𭓇 𭓈
          0x0002d4d4_0002d4d5L, //  𭓔 𭓕
          0x0002d4d7_0002d4d8L, //  𭓗 𭓘
          0x0002d4e6_0002d4e6L, //  𭓦
          0x0002d4e9_0002d4e9L, //  𭓩
          0x0002d4ef_0002d4efL, //  𭓯
          0x0002d4f1_0002d4f1L, //  𭓱
          0x0002d4f3_0002d4f3L, //  𭓳
          0x0002d4fb_0002d4fbL, //  𭓻
          0x0002d4fd_0002d500L, //  𭓽 𭓾 𭓿 𭔀
          0x0002d503_0002d505L, //  𭔃 𭔄 𭔅
          0x0002d50c_0002d50cL, //  𭔌
          0x0002d516_0002d517L, //  𭔖 𭔗
          0x0002d51f_0002d51fL, //  𭔟
          0x0002d52c_0002d52cL, //  𭔬
          0x0002d531_0002d532L, //  𭔱 𭔲
          0x0002d535_0002d539L, //  𭔵 𭔶 𭔷 𭔸 𭔹
          0x0002d53e_0002d53fL, //  𭔾 𭔿
          0x0002d544_0002d544L, //  𭕄
          0x0002d547_0002d547L, //  𭕇
          0x0002d549_0002d549L, //  𭕉
          0x0002d54b_0002d54bL, //  𭕋
          0x0002d54d_0002d54dL, //  𭕍
          0x0002d550_0002d550L, //  𭕐
          0x0002d552_0002d552L, //  𭕒
          0x0002d558_0002d559L, //  𭕘 𭕙
          0x0002d55d_0002d55dL, //  𭕝
          0x0002d565_0002d565L, //  𭕥
          0x0002d56d_0002d56dL, //  𭕭
          0x0002d56f_0002d56fL, //  𭕯
          0x0002d57e_0002d580L, //  𭕾 𭕿 𭖀
          0x0002d583_0002d584L, //  𭖃 𭖄
          0x0002d58c_0002d58cL, //  𭖌
          0x0002d592_0002d593L, //  𭖒 𭖓
          0x0002d5a9_0002d5a9L, //  𭖩
          0x0002d5ab_0002d5acL, //  𭖫 𭖬
          0x0002d5b1_0002d5b1L, //  𭖱
          0x0002d5c3_0002d5c3L, //  𭗃
          0x0002d5c7_0002d5c7L, //  𭗇
          0x0002d5d2_0002d5d5L, //  𭗒 𭗓 𭗔 𭗕
          0x0002d5de_0002d5deL, //  𭗞
          0x0002d5ea_0002d5eaL, //  𭗪
          0x0002d5ed_0002d5edL, //  𭗭
          0x0002d5f6_0002d5f6L, //  𭗶
          0x0002d5fb_0002d5fcL, //  𭗻 𭗼
          0x0002d5fe_0002d600L, //  𭗾 𭗿 𭘀
          0x0002d606_0002d606L, //  𭘆
          0x0002d609_0002d60aL, //  𭘉 𭘊
          0x0002d60d_0002d60dL, //  𭘍
          0x0002d611_0002d612L, //  𭘑 𭘒
          0x0002d618_0002d618L, //  𭘘
          0x0002d61a_0002d61aL, //  𭘚
          0x0002d61e_0002d61eL, //  𭘞
          0x0002d623_0002d623L, //  𭘣
          0x0002d627_0002d627L, //  𭘧
          0x0002d629_0002d629L, //  𭘩
          0x0002d62b_0002d62bL, //  𭘫
          0x0002d634_0002d634L, //  𭘴
          0x0002d63e_0002d63eL, //  𭘾
          0x0002d64c_0002d64dL, //  𭙌 𭙍
          0x0002d652_0002d652L, //  𭙒
          0x0002d654_0002d654L, //  𭙔
          0x0002d657_0002d657L, //  𭙗
          0x0002d661_0002d661L, //  𭙡
          0x0002d663_0002d663L, //  𭙣
          0x0002d667_0002d667L, //  𭙧
          0x0002d66f_0002d66fL, //  𭙯
          0x0002d671_0002d671L, //  𭙱
          0x0002d676_0002d676L, //  𭙶
          0x0002d678_0002d678L, //  𭙸
          0x0002d67a_0002d67bL, //  𭙺 𭙻
          0x0002d67d_0002d67dL, //  𭙽
          0x0002d680_0002d680L, //  𭚀
          0x0002d682_0002d683L, //  𭚂 𭚃
          0x0002d691_0002d693L, //  𭚑 𭚒 𭚓
          0x0002d698_0002d698L, //  𭚘
          0x0002d69f_0002d69fL, //  𭚟
          0x0002d6a1_0002d6a1L, //  𭚡
          0x0002d6a3_0002d6a3L, //  𭚣
          0x0002d6a5_0002d6a5L, //  𭚥
          0x0002d6a8_0002d6a8L, //  𭚨
          0x0002d6aa_0002d6aaL, //  𭚪
          0x0002d6ac_0002d6acL, //  𭚬
          0x0002d6ae_0002d6aeL, //  𭚮
          0x0002d6b4_0002d6b6L, //  𭚴 𭚵 𭚶
          0x0002d6b8_0002d6baL, //  𭚸 𭚹 𭚺
          0x0002d6bc_0002d6bcL, //  𭚼
          0x0002d6bf_0002d6bfL, //  𭚿
          0x0002d6c1_0002d6c2L, //  𭛁 𭛂
          0x0002d6c5_0002d6c5L, //  𭛅
          0x0002d6cb_0002d6cdL, //  𭛋 𭛌 𭛍
          0x0002d6dd_0002d6deL, //  𭛝 𭛞
          0x0002d6e0_0002d6e0L, //  𭛠
          0x0002d6e3_0002d6e3L, //  𭛣
          0x0002d6e7_0002d6e7L, //  𭛧
          0x0002d6e9_0002d6eaL, //  𭛩 𭛪
          0x0002d6f3_0002d6f3L, //  𭛳
          0x0002d702_0002d702L, //  𭜂
          0x0002d708_0002d708L, //  𭜈
          0x0002d70a_0002d70aL, //  𭜊
          0x0002d70d_0002d70eL, //  𭜍 𭜎
          0x0002d710_0002d711L, //  𭜐 𭜑
          0x0002d718_0002d719L, //  𭜘 𭜙
          0x0002d71b_0002d71bL, //  𭜛
          0x0002d721_0002d721L, //  𭜡
          0x0002d723_0002d727L, //  𭜣 𭜤 𭜥 𭜦 𭜧
          0x0002d72e_0002d72eL, //  𭜮
          0x0002d733_0002d736L, //  𭜳 𭜴 𭜵 𭜶
          0x0002d73e_0002d73eL, //  𭜾
          0x0002d745_0002d746L, //  𭝅 𭝆
          0x0002d74c_0002d74cL, //  𭝌
          0x0002d751_0002d751L, //  𭝑
          0x0002d759_0002d759L, //  𭝙
          0x0002d75d_0002d75dL, //  𭝝
          0x0002d760_0002d762L, //  𭝠 𭝡 𭝢
          0x0002d768_0002d768L, //  𭝨
          0x0002d777_0002d777L, //  𭝷
          0x0002d781_0002d781L, //  𭞁
          0x0002d785_0002d785L, //  𭞅
          0x0002d78b_0002d78cL, //  𭞋 𭞌
          0x0002d78f_0002d790L, //  𭞏 𭞐
          0x0002d798_0002d798L, //  𭞘
          0x0002d79e_0002d79eL, //  𭞞
          0x0002d7a5_0002d7a5L, //  𭞥
          0x0002d7b5_0002d7b5L, //  𭞵
          0x0002d7b9_0002d7b9L, //  𭞹
          0x0002d7be_0002d7beL, //  𭞾
          0x0002d7c1_0002d7c1L, //  𭟁
          0x0002d7c4_0002d7c4L, //  𭟄
          0x0002d7dd_0002d7deL, //  𭟝 𭟞
          0x0002d7ee_0002d7efL, //  𭟮 𭟯
          0x0002d7f1_0002d7f2L, //  𭟱 𭟲
          0x0002d7f6_0002d7f6L, //  𭟶
          0x0002d7f9_0002d7f9L, //  𭟹
          0x0002d7fc_0002d7fcL, //  𭟼
          0x0002d7fe_0002d7feL, //  𭟾
          0x0002d802_0002d802L, //  𭠂
          0x0002d805_0002d805L, //  𭠅
          0x0002d809_0002d809L, //  𭠉
          0x0002d80d_0002d80dL, //  𭠍
          0x0002d818_0002d818L, //  𭠘
          0x0002d820_0002d821L, //  𭠠 𭠡
          0x0002d848_0002d848L, //  𭡈
          0x0002d860_0002d860L, //  𭡠
          0x0002d877_0002d878L, //  𭡷 𭡸
          0x0002d885_0002d886L, //  𭢅 𭢆
          0x0002d88f_0002d88fL, //  𭢏
          0x0002d898_0002d898L, //  𭢘
          0x0002d8a7_0002d8a7L, //  𭢧
          0x0002d8ab_0002d8abL, //  𭢫
          0x0002d8af_0002d8afL, //  𭢯
          0x0002d8c4_0002d8c4L, //  𭣄
          0x0002d8d4_0002d8d4L, //  𭣔
          0x0002d8da_0002d8daL, //  𭣚
          0x0002d8e2_0002d8e3L, //  𭣢 𭣣
          0x0002d8e9_0002d8e9L, //  𭣩
          0x0002d8ec_0002d8ecL, //  𭣬
          0x0002d8ef_0002d8f1L, //  𭣯 𭣰 𭣱
          0x0002d8f4_0002d8f5L, //  𭣴 𭣵
          0x0002d8f8_0002d8fbL, //  𭣸 𭣹 𭣺 𭣻
          0x0002d8fe_0002d8feL, //  𭣾
          0x0002d900_0002d904L, //  𭤀 𭤁 𭤂 𭤃 𭤄
          0x0002d90a_0002d90bL, //  𭤊 𭤋
          0x0002d90f_0002d90fL, //  𭤏
          0x0002d913_0002d913L, //  𭤓
          0x0002d915_0002d919L, //  𭤕 𭤖 𭤗 𭤘 𭤙
          0x0002d91b_0002d91bL, //  𭤛
          0x0002d91f_0002d920L, //  𭤟 𭤠
          0x0002d923_0002d923L, //  𭤣
          0x0002d925_0002d925L, //  𭤥
          0x0002d927_0002d927L, //  𭤧
          0x0002d929_0002d92aL, //  𭤩 𭤪
          0x0002d92e_0002d92fL, //  𭤮 𭤯
          0x0002d93d_0002d93dL, //  𭤽
          0x0002d946_0002d946L, //  𭥆
          0x0002d94d_0002d94eL, //  𭥍 𭥎
          0x0002d957_0002d958L, //  𭥗 𭥘
          0x0002d95a_0002d95aL, //  𭥚
          0x0002d95c_0002d95cL, //  𭥜
          0x0002d95f_0002d95fL, //  𭥟
          0x0002d966_0002d967L, //  𭥦 𭥧
          0x0002d96b_0002d96dL, //  𭥫 𭥬 𭥭
          0x0002d978_0002d978L, //  𭥸
          0x0002d980_0002d981L, //  𭦀 𭦁
          0x0002d983_0002d983L, //  𭦃
          0x0002d98c_0002d98cL, //  𭦌
          0x0002d991_0002d991L, //  𭦑
          0x0002d997_0002d997L, //  𭦗
          0x0002d999_0002d99bL, //  𭦙 𭦚 𭦛
          0x0002d9a0_0002d9a0L, //  𭦠
          0x0002d9a8_0002d9a9L, //  𭦨 𭦩
          0x0002d9bd_0002d9bdL, //  𭦽
          0x0002d9c2_0002d9c2L, //  𭧂
          0x0002d9cc_0002d9ccL, //  𭧌
          0x0002d9d1_0002d9d1L, //  𭧑
          0x0002d9db_0002d9dbL, //  𭧛
          0x0002d9df_0002d9e0L, //  𭧟 𭧠
          0x0002d9e9_0002d9e9L, //  𭧩
          0x0002d9f3_0002d9f3L, //  𭧳
          0x0002d9f5_0002d9f5L, //  𭧵
          0x0002d9fd_0002d9fdL, //  𭧽
          0x0002da0d_0002da0dL, //  𭨍
          0x0002da14_0002da14L, //  𭨔
          0x0002da17_0002da18L, //  𭨗 𭨘
          0x0002da30_0002da30L, //  𭨰
          0x0002da39_0002da39L, //  𭨹
          0x0002da3d_0002da3dL, //  𭨽
          0x0002da5c_0002da5cL, //  𭩜
          0x0002da5f_0002da61L, //  𭩟 𭩠 𭩡
          0x0002da65_0002da65L, //  𭩥
          0x0002da67_0002da67L, //  𭩧
          0x0002da69_0002da69L, //  𭩩
          0x0002da71_0002da73L, //  𭩱 𭩲 𭩳
          0x0002da80_0002da81L, //  𭪀 𭪁
          0x0002da88_0002da88L, //  𭪈
          0x0002da92_0002da92L, //  𭪒
          0x0002da95_0002da97L, //  𭪕 𭪖 𭪗
          0x0002da99_0002da9cL, //  𭪙 𭪚 𭪛 𭪜
          0x0002daae_0002daafL, //  𭪮 𭪯
          0x0002dab2_0002dab2L, //  𭪲
          0x0002dab4_0002dab6L, //  𭪴 𭪵 𭪶
          0x0002dabc_0002dabcL, //  𭪼
          0x0002dac8_0002dac8L, //  𭫈
          0x0002dacd_0002daceL, //  𭫍 𭫎
          0x0002dad2_0002dad3L, //  𭫒 𭫓
          0x0002dad5_0002dad5L, //  𭫕
          0x0002dad8_0002dad8L, //  𭫘
          0x0002dada_0002dadaL, //  𭫚
          0x0002dadc_0002dadfL, //  𭫜 𭫝 𭫞 𭫟
          0x0002dae2_0002dae2L, //  𭫢
          0x0002daec_0002daecL, //  𭫬
          0x0002daef_0002daf0L, //  𭫯 𭫰
          0x0002daf3_0002daf3L, //  𭫳
          0x0002daf8_0002daf8L, //  𭫸
          0x0002dafa_0002dafaL, //  𭫺
          0x0002daff_0002daffL, //  𭫿
          0x0002db01_0002db02L, //  𭬁 𭬂
          0x0002db0a_0002db0aL, //  𭬊
          0x0002db10_0002db12L, //  𭬐 𭬑 𭬒
          0x0002db1c_0002db1cL, //  𭬜
          0x0002db24_0002db24L, //  𭬤
          0x0002db28_0002db28L, //  𭬨
          0x0002db2f_0002db2fL, //  𭬯
          0x0002db32_0002db34L, //  𭬲 𭬳 𭬴
          0x0002db3a_0002db3aL, //  𭬺
          0x0002db43_0002db43L, //  𭭃
          0x0002db4a_0002db4bL, //  𭭊 𭭋
          0x0002db4e_0002db4fL, //  𭭎 𭭏
          0x0002db52_0002db55L, //  𭭒 𭭓 𭭔 𭭕
          0x0002db58_0002db58L, //  𭭘
          0x0002db66_0002db66L, //  𭭦
          0x0002db69_0002db69L, //  𭭩
          0x0002db70_0002db70L, //  𭭰
          0x0002db7d_0002db7eL, //  𭭽 𭭾
          0x0002db80_0002db81L, //  𭮀 𭮁
          0x0002db83_0002db83L, //  𭮃
          0x0002db85_0002db85L, //  𭮅
          0x0002db88_0002db88L, //  𭮈
          0x0002db8e_0002db8eL, //  𭮎
          0x0002db91_0002db91L, //  𭮑
          0x0002db96_0002db97L, //  𭮖 𭮗
          0x0002dba3_0002dba3L, //  𭮣
          0x0002dbab_0002dbabL, //  𭮫
          0x0002dbad_0002dbadL, //  𭮭
          0x0002dbb0_0002dbb1L, //  𭮰 𭮱
          0x0002dbb3_0002dbb6L, //  𭮳 𭮴 𭮵 𭮶
          0x0002dbba_0002dbbcL, //  𭮺 𭮻 𭮼
          0x0002dbc3_0002dbc3L, //  𭯃
          0x0002dbc7_0002dbc7L, //  𭯇
          0x0002dbf0_0002dbf0L, //  𭯰
          0x0002dbf4_0002dbf4L, //  𭯴
          0x0002dbf6_0002dbf6L, //  𭯶
          0x0002dc01_0002dc01L, //  𭰁
          0x0002dc09_0002dc0bL, //  𭰉 𭰊 𭰋
          0x0002dc0f_0002dc11L, //  𭰏 𭰐 𭰑
          0x0002dc16_0002dc16L, //  𭰖
          0x0002dc23_0002dc23L, //  𭰣
          0x0002dc26_0002dc26L, //  𭰦
          0x0002dc29_0002dc29L, //  𭰩
          0x0002dc2f_0002dc31L, //  𭰯 𭰰 𭰱
          0x0002dc39_0002dc39L, //  𭰹
          0x0002dc3c_0002dc3cL, //  𭰼
          0x0002dc41_0002dc42L, //  𭱁 𭱂
          0x0002dc44_0002dc46L, //  𭱄 𭱅 𭱆
          0x0002dc4e_0002dc4fL, //  𭱎 𭱏
          0x0002dc5a_0002dc5aL, //  𭱚
          0x0002dc61_0002dc62L, //  𭱡 𭱢
          0x0002dc69_0002dc6aL, //  𭱩 𭱪
          0x0002dc6e_0002dc6eL, //  𭱮
          0x0002dc70_0002dc71L, //  𭱰 𭱱
          0x0002dc74_0002dc74L, //  𭱴
          0x0002dc77_0002dc77L, //  𭱷
          0x0002dc7c_0002dc7dL, //  𭱼 𭱽
          0x0002dc83_0002dc85L, //  𭲃 𭲄 𭲅
          0x0002dc8b_0002dc8dL, //  𭲋 𭲌 𭲍
          0x0002dc91_0002dc94L, //  𭲑 𭲒 𭲓 𭲔
          0x0002dc9e_0002dc9eL, //  𭲞
          0x0002dca0_0002dca0L, //  𭲠
          0x0002dca7_0002dca7L, //  𭲧
          0x0002dcae_0002dcaeL, //  𭲮
          0x0002dcb3_0002dcb3L, //  𭲳
          0x0002dcbe_0002dcbeL, //  𭲾
          0x0002dcc1_0002dcc1L, //  𭳁
          0x0002dcc8_0002dcc8L, //  𭳈
          0x0002dcca_0002dccaL, //  𭳊
          0x0002dccd_0002dccdL, //  𭳍
          0x0002dccf_0002dcd0L, //  𭳏 𭳐
          0x0002dcd3_0002dcd3L, //  𭳓
          0x0002dcd6_0002dcd7L, //  𭳖 𭳗
          0x0002dcda_0002dcdbL, //  𭳚 𭳛
          0x0002dce0_0002dce0L, //  𭳠
          0x0002dce3_0002dce4L, //  𭳣 𭳤
          0x0002dce9_0002dce9L, //  𭳩
          0x0002dcef_0002dcefL, //  𭳯
          0x0002dcfa_0002dcfaL, //  𭳺
          0x0002dcff_0002dcffL, //  𭳿
          0x0002dd11_0002dd11L, //  𭴑
          0x0002dd16_0002dd16L, //  𭴖
          0x0002dd19_0002dd1aL, //  𭴙 𭴚
          0x0002dd2b_0002dd2cL, //  𭴫 𭴬
          0x0002dd37_0002dd37L, //  𭴷
          0x0002dd3a_0002dd3bL, //  𭴺 𭴻
          0x0002dd3d_0002dd3fL, //  𭴽 𭴾 𭴿
          0x0002dd44_0002dd44L, //  𭵄
          0x0002dd47_0002dd48L, //  𭵇 𭵈
          0x0002dd4b_0002dd4bL, //  𭵋
          0x0002dd59_0002dd59L, //  𭵙
          0x0002dd5c_0002dd5dL, //  𭵜 𭵝
          0x0002dd60_0002dd61L, //  𭵠 𭵡
          0x0002dd68_0002dd68L, //  𭵨
          0x0002dd73_0002dd74L, //  𭵳 𭵴
          0x0002dd7a_0002dd7aL, //  𭵺
          0x0002dd7c_0002dd7cL, //  𭵼
          0x0002dd82_0002dd82L, //  𭶂
          0x0002dd89_0002dd8aL, //  𭶉 𭶊
          0x0002dd8c_0002dd8cL, //  𭶌
          0x0002dd92_0002dd92L, //  𭶒
          0x0002dd97_0002dd97L, //  𭶗
          0x0002dda1_0002dda1L, //  𭶡
          0x0002ddaa_0002ddaaL, //  𭶪
          0x0002ddb8_0002ddb8L, //  𭶸
          0x0002ddc4_0002ddc4L, //  𭷄
          0x0002ddd8_0002ddd9L, //  𭷘 𭷙
          0x0002dde3_0002dde3L, //  𭷣
          0x0002ddf0_0002ddf0L, //  𭷰
          0x0002ddfa_0002ddfaL, //  𭷺
          0x0002de08_0002de08L, //  𭸈
          0x0002de0d_0002de0dL, //  𭸍
          0x0002de12_0002de12L, //  𭸒
          0x0002de20_0002de21L, //  𭸠 𭸡
          0x0002de23_0002de23L, //  𭸣
          0x0002de2e_0002de2eL, //  𭸮
          0x0002de35_0002de36L, //  𭸵 𭸶
          0x0002de3c_0002de3dL, //  𭸼 𭸽
          0x0002de44_0002de44L, //  𭹄
          0x0002de55_0002de57L, //  𭹕 𭹖 𭹗
          0x0002de63_0002de64L, //  𭹣 𭹤
          0x0002de72_0002de72L, //  𭹲
          0x0002de77_0002de77L, //  𭹷
          0x0002de7c_0002de7dL, //  𭹼 𭹽
          0x0002de86_0002de86L, //  𭺆
          0x0002de9b_0002de9cL, //  𭺛 𭺜
          0x0002dea3_0002dea3L, //  𭺣
          0x0002dea5_0002dea5L, //  𭺥
          0x0002dea7_0002dea7L, //  𭺧
          0x0002deaa_0002deabL, //  𭺪 𭺫
          0x0002deae_0002deaeL, //  𭺮
          0x0002deb1_0002deb1L, //  𭺱
          0x0002deb3_0002deb3L, //  𭺳
          0x0002deb8_0002deb8L, //  𭺸
          0x0002deba_0002debaL, //  𭺺
          0x0002debe_0002debfL, //  𭺾 𭺿
          0x0002dec9_0002decaL, //  𭻉 𭻊
          0x0002decc_0002deccL, //  𭻌
          0x0002ded0_0002ded0L, //  𭻐
          0x0002ded2_0002ded2L, //  𭻒
          0x0002ded7_0002ded7L, //  𭻗
          0x0002ded9_0002ded9L, //  𭻙
          0x0002dee5_0002dee5L, //  𭻥
          0x0002dee8_0002dee9L, //  𭻨 𭻩
          0x0002deef_0002def0L, //  𭻯 𭻰
          0x0002def4_0002def5L, //  𭻴 𭻵
          0x0002def8_0002def8L, //  𭻸
          0x0002defa_0002defaL, //  𭻺
          0x0002deff_0002deffL, //  𭻿
          0x0002df02_0002df03L, //  𭼂 𭼃
          0x0002df05_0002df05L, //  𭼅
          0x0002df0b_0002df0bL, //  𭼋
          0x0002df0e_0002df0eL, //  𭼎
          0x0002df16_0002df16L, //  𭼖
          0x0002df30_0002df30L, //  𭼰
          0x0002df35_0002df35L, //  𭼵
          0x0002df38_0002df38L, //  𭼸
          0x0002df3c_0002df3eL, //  𭼼 𭼽 𭼾
          0x0002df41_0002df42L, //  𭽁 𭽂
          0x0002df45_0002df47L, //  𭽅 𭽆 𭽇
          0x0002df4b_0002df4bL, //  𭽋
          0x0002df4d_0002df4dL, //  𭽍
          0x0002df4f_0002df4fL, //  𭽏
          0x0002df52_0002df52L, //  𭽒
          0x0002df59_0002df59L, //  𭽙
          0x0002df5c_0002df5cL, //  𭽜
          0x0002df5e_0002df5eL, //  𭽞
          0x0002df6a_0002df6aL, //  𭽪
          0x0002df78_0002df78L, //  𭽸
          0x0002df7c_0002df7cL, //  𭽼
          0x0002df7f_0002df7fL, //  𭽿
          0x0002df82_0002df82L, //  𭾂
          0x0002df86_0002df87L, //  𭾆 𭾇
          0x0002df8a_0002df8bL, //  𭾊 𭾋
          0x0002df8f_0002df90L, //  𭾏 𭾐
          0x0002df9c_0002df9cL, //  𭾜
          0x0002df9f_0002df9fL, //  𭾟
          0x0002dfa6_0002dfa6L, //  𭾦
          0x0002dfa9_0002dfaaL, //  𭾩 𭾪
          0x0002dfb1_0002dfb1L, //  𭾱
          0x0002dfd3_0002dfd3L, //  𭿓
          0x0002dfd7_0002dfd7L, //  𭿗
          0x0002dfdc_0002dfdcL, //  𭿜
          0x0002dfe5_0002dfe6L, //  𭿥 𭿦
          0x0002dfe9_0002dfeaL, //  𭿩 𭿪
          0x0002dff5_0002dff7L, //  𭿵 𭿶 𭿷
          0x0002dfff_0002dfffL, //  𭿿
          0x0002e001_0002e001L, //  𮀁
          0x0002e003_0002e003L, //  𮀃
          0x0002e007_0002e007L, //  𮀇
          0x0002e010_0002e010L, //  𮀐
          0x0002e020_0002e020L, //  𮀠
          0x0002e02f_0002e02fL, //  𮀯
          0x0002e033_0002e033L, //  𮀳
          0x0002e036_0002e036L, //  𮀶
          0x0002e038_0002e038L, //  𮀸
          0x0002e03f_0002e03fL, //  𮀿
          0x0002e043_0002e043L, //  𮁃
          0x0002e04c_0002e04cL, //  𮁌
          0x0002e062_0002e064L, //  𮁢 𮁣 𮁤
          0x0002e068_0002e069L, //  𮁨 𮁩
          0x0002e06e_0002e070L, //  𮁮 𮁯 𮁰
          0x0002e072_0002e074L, //  𮁲 𮁳 𮁴
          0x0002e076_0002e076L, //  𮁶
          0x0002e079_0002e079L, //  𮁹
          0x0002e087_0002e087L, //  𮂇
          0x0002e08a_0002e08aL, //  𮂊
          0x0002e08f_0002e08fL, //  𮂏
          0x0002e094_0002e094L, //  𮂔
          0x0002e097_0002e097L, //  𮂗
          0x0002e09a_0002e09aL, //  𮂚
          0x0002e09c_0002e09cL, //  𮂜
          0x0002e09e_0002e09eL, //  𮂞
          0x0002e0a2_0002e0a2L, //  𮂢
          0x0002e0a7_0002e0a7L, //  𮂧
          0x0002e0af_0002e0afL, //  𮂯
          0x0002e0b2_0002e0b2L, //  𮂲
          0x0002e0ba_0002e0bcL, //  𮂺 𮂻 𮂼
          0x0002e0c1_0002e0c1L, //  𮃁
          0x0002e0c4_0002e0c4L, //  𮃄
          0x0002e0ce_0002e0ceL, //  𮃎
          0x0002e0d6_0002e0d7L, //  𮃖 𮃗
          0x0002e0db_0002e0dcL, //  𮃛 𮃜
          0x0002e0e2_0002e0e3L, //  𮃢 𮃣
          0x0002e0ec_0002e0ecL, //  𮃬
          0x0002e0f0_0002e0f0L, //  𮃰
          0x0002e0f4_0002e0f4L, //  𮃴
          0x0002e0fb_0002e0fbL, //  𮃻
          0x0002e101_0002e102L, //  𮄁 𮄂
          0x0002e112_0002e112L, //  𮄒
          0x0002e11c_0002e11cL, //  𮄜
          0x0002e123_0002e123L, //  𮄣
          0x0002e127_0002e127L, //  𮄧
          0x0002e12d_0002e12fL, //  𮄭 𮄮 𮄯
          0x0002e131_0002e131L, //  𮄱
          0x0002e134_0002e135L, //  𮄴 𮄵
          0x0002e13f_0002e13fL, //  𮄿
          0x0002e143_0002e143L, //  𮅃
          0x0002e149_0002e149L, //  𮅉
          0x0002e14f_0002e14fL, //  𮅏
          0x0002e152_0002e152L, //  𮅒
          0x0002e154_0002e155L, //  𮅔 𮅕
          0x0002e159_0002e159L, //  𮅙
          0x0002e15e_0002e15eL, //  𮅞
          0x0002e161_0002e161L, //  𮅡
          0x0002e16f_0002e170L, //  𮅯 𮅰
          0x0002e176_0002e176L, //  𮅶
          0x0002e183_0002e183L, //  𮆃
          0x0002e186_0002e186L, //  𮆆
          0x0002e1a8_0002e1a8L, //  𮆨
          0x0002e1aa_0002e1aaL, //  𮆪
          0x0002e1af_0002e1afL, //  𮆯
          0x0002e1b4_0002e1b4L, //  𮆴
          0x0002e1b6_0002e1b6L, //  𮆶
          0x0002e1bd_0002e1bdL, //  𮆽
          0x0002e1c0_0002e1c0L, //  𮇀
          0x0002e1c2_0002e1c2L, //  𮇂
          0x0002e1cb_0002e1cbL, //  𮇋
          0x0002e1ce_0002e1ceL, //  𮇎
          0x0002e1d3_0002e1d3L, //  𮇓
          0x0002e1dc_0002e1dcL, //  𮇜
          0x0002e1de_0002e1deL, //  𮇞
          0x0002e1e3_0002e1e3L, //  𮇣
          0x0002e1e9_0002e1e9L, //  𮇩
          0x0002e1ec_0002e1ecL, //  𮇬
          0x0002e1f5_0002e1f5L, //  𮇵
          0x0002e1fb_0002e1fbL, //  𮇻
          0x0002e203_0002e207L, //  𮈃 𮈄 𮈅 𮈆 𮈇
          0x0002e20b_0002e20cL, //  𮈋 𮈌
          0x0002e20f_0002e20fL, //  𮈏
          0x0002e211_0002e211L, //  𮈑
          0x0002e214_0002e215L, //  𮈔 𮈕
          0x0002e21b_0002e21cL, //  𮈛 𮈜
          0x0002e223_0002e223L, //  𮈣
          0x0002e226_0002e226L, //  𮈦
          0x0002e230_0002e231L, //  𮈰 𮈱
          0x0002e235_0002e235L, //  𮈵
          0x0002e237_0002e237L, //  𮈷
          0x0002e248_0002e248L, //  𮉈
          0x0002e257_0002e257L, //  𮉗
          0x0002e25f_0002e25fL, //  𮉟
          0x0002e270_0002e271L, //  𮉰 𮉱
          0x0002e275_0002e275L, //  𮉵
          0x0002e278_0002e278L, //  𮉸
          0x0002e27c_0002e27cL, //  𮉼
          0x0002e281_0002e282L, //  𮊁 𮊂
          0x0002e284_0002e286L, //  𮊄 𮊅 𮊆
          0x0002e28c_0002e28cL, //  𮊌
          0x0002e292_0002e294L, //  𮊒 𮊓 𮊔
          0x0002e296_0002e296L, //  𮊖
          0x0002e298_0002e298L, //  𮊘
          0x0002e2a2_0002e2a2L, //  𮊢
          0x0002e2a5_0002e2a5L, //  𮊥
          0x0002e2aa_0002e2abL, //  𮊪 𮊫
          0x0002e2b5_0002e2b8L, //  𮊵 𮊶 𮊷 𮊸
          0x0002e2ba_0002e2baL, //  𮊺
          0x0002e2be_0002e2c1L, //  𮊾 𮊿 𮋀 𮋁
          0x0002e2ca_0002e2caL, //  𮋊
          0x0002e2d2_0002e2d2L, //  𮋒
          0x0002e2da_0002e2daL, //  𮋚
          0x0002e2dd_0002e2ddL, //  𮋝
          0x0002e2e6_0002e2e6L, //  𮋦
          0x0002e2ee_0002e2eeL, //  𮋮
          0x0002e2f0_0002e2f0L, //  𮋰
          0x0002e2f8_0002e2f9L, //  𮋸 𮋹
          0x0002e2fb_0002e2fbL, //  𮋻
          0x0002e301_0002e302L, //  𮌁 𮌂
          0x0002e304_0002e304L, //  𮌄
          0x0002e306_0002e307L, //  𮌆 𮌇
          0x0002e30b_0002e30bL, //  𮌋
          0x0002e30f_0002e310L, //  𮌏 𮌐
          0x0002e312_0002e312L, //  𮌒
          0x0002e314_0002e316L, //  𮌔 𮌕 𮌖
          0x0002e31a_0002e31cL, //  𮌚 𮌛 𮌜
          0x0002e326_0002e326L, //  𮌦
          0x0002e329_0002e329L, //  𮌩
          0x0002e337_0002e337L, //  𮌷
          0x0002e33a_0002e33aL, //  𮌺
          0x0002e344_0002e344L, //  𮍄
          0x0002e348_0002e349L, //  𮍈 𮍉
          0x0002e34c_0002e34eL, //  𮍌 𮍍 𮍎
          0x0002e350_0002e350L, //  𮍐
          0x0002e352_0002e352L, //  𮍒
          0x0002e35b_0002e35bL, //  𮍛
          0x0002e360_0002e360L, //  𮍠
          0x0002e363_0002e363L, //  𮍣
          0x0002e365_0002e365L, //  𮍥
          0x0002e367_0002e368L, //  𮍧 𮍨
          0x0002e36b_0002e36dL, //  𮍫 𮍬 𮍭
          0x0002e370_0002e370L, //  𮍰
          0x0002e372_0002e372L, //  𮍲
          0x0002e379_0002e379L, //  𮍹
          0x0002e381_0002e381L, //  𮎁
          0x0002e385_0002e386L, //  𮎅 𮎆
          0x0002e38b_0002e38bL, //  𮎋
          0x0002e38d_0002e38dL, //  𮎍
          0x0002e393_0002e393L, //  𮎓
          0x0002e397_0002e397L, //  𮎗
          0x0002e39b_0002e39cL, //  𮎛 𮎜
          0x0002e3a2_0002e3a3L, //  𮎢 𮎣
          0x0002e3a6_0002e3a7L, //  𮎦 𮎧
          0x0002e3aa_0002e3aaL, //  𮎪
          0x0002e3af_0002e3b0L, //  𮎯 𮎰
          0x0002e3b5_0002e3b5L, //  𮎵
          0x0002e3b7_0002e3bbL, //  𮎷 𮎸 𮎹 𮎺 𮎻
          0x0002e3be_0002e3c1L, //  𮎾 𮎿 𮏀 𮏁
          0x0002e3c4_0002e3c4L, //  𮏄
          0x0002e3c7_0002e3c7L, //  𮏇
          0x0002e3cb_0002e3cdL, //  𮏋 𮏌 𮏍
          0x0002e3d0_0002e3d4L, //  𮏐 𮏑 𮏒 𮏓 𮏔
          0x0002e3d6_0002e3d6L, //  𮏖
          0x0002e3de_0002e3deL, //  𮏞
          0x0002e3e3_0002e3e3L, //  𮏣
          0x0002e3e5_0002e3e7L, //  𮏥 𮏦 𮏧
          0x0002e3e9_0002e3e9L, //  𮏩
          0x0002e3f3_0002e3f3L, //  𮏳
          0x0002e3fb_0002e3fcL, //  𮏻 𮏼
          0x0002e3ff_0002e3ffL, //  𮏿
          0x0002e403_0002e403L, //  𮐃
          0x0002e407_0002e407L, //  𮐇
          0x0002e413_0002e413L, //  𮐓
          0x0002e425_0002e426L, //  𮐥 𮐦
          0x0002e429_0002e429L, //  𮐩
          0x0002e42e_0002e42eL, //  𮐮
          0x0002e439_0002e439L, //  𮐹
          0x0002e43b_0002e43cL, //  𮐻 𮐼
          0x0002e44a_0002e44aL, //  𮑊
          0x0002e45a_0002e45dL, //  𮑚 𮑛 𮑜 𮑝
          0x0002e460_0002e460L, //  𮑠
          0x0002e476_0002e476L, //  𮑶
          0x0002e47c_0002e47cL, //  𮑼
          0x0002e48b_0002e48bL, //  𮒋
          0x0002e490_0002e490L, //  𮒐
          0x0002e494_0002e494L, //  𮒔
          0x0002e497_0002e49cL, //  𮒗 𮒘 𮒙 𮒚 𮒛 𮒜
          0x0002e4a8_0002e4a8L, //  𮒨
          0x0002e4ab_0002e4abL, //  𮒫
          0x0002e4b9_0002e4b9L, //  𮒹
          0x0002e4bf_0002e4c0L, //  𮒿 𮓀
          0x0002e4c8_0002e4c8L, //  𮓈
          0x0002e4cb_0002e4cbL, //  𮓋
          0x0002e4d6_0002e4dcL, //  𮓖 𮓗 𮓘 𮓙 𮓚 𮓛 𮓜
          0x0002e4de_0002e4deL, //  𮓞
          0x0002e4e0_0002e4e3L, //  𮓠 𮓡 𮓢 𮓣
          0x0002e4e5_0002e4e6L, //  𮓥 𮓦
          0x0002e4ef_0002e4f1L, //  𮓯 𮓰 𮓱
          0x0002e4f5_0002e4f5L, //  𮓵
          0x0002e4fa_0002e4faL, //  𮓺
          0x0002e4fc_0002e4fdL, //  𮓼 𮓽
          0x0002e501_0002e501L, //  𮔁
          0x0002e507_0002e509L, //  𮔇 𮔈 𮔉
          0x0002e514_0002e514L, //  𮔔
          0x0002e517_0002e517L, //  𮔗
          0x0002e51d_0002e51dL, //  𮔝
          0x0002e530_0002e530L, //  𮔰
          0x0002e542_0002e543L, //  𮕂 𮕃
          0x0002e548_0002e548L, //  𮕈
          0x0002e54b_0002e54bL, //  𮕋
          0x0002e552_0002e552L, //  𮕒
          0x0002e554_0002e554L, //  𮕔
          0x0002e55b_0002e55dL, //  𮕛 𮕜 𮕝
          0x0002e561_0002e561L, //  𮕡
          0x0002e564_0002e565L, //  𮕤 𮕥
          0x0002e569_0002e56bL, //  𮕩 𮕪 𮕫
          0x0002e571_0002e571L, //  𮕱
          0x0002e57a_0002e57aL, //  𮕺
          0x0002e587_0002e587L, //  𮖇
          0x0002e59a_0002e59bL, //  𮖚 𮖛
          0x0002e5a0_0002e5a0L, //  𮖠
          0x0002e5a5_0002e5a5L, //  𮖥
          0x0002e5af_0002e5afL, //  𮖯
          0x0002e5bb_0002e5bbL, //  𮖻
          0x0002e5be_0002e5c3L, //  𮖾 𮖿 𮗀 𮗁 𮗂 𮗃
          0x0002e5c8_0002e5c8L, //  𮗈
          0x0002e5d3_0002e5d3L, //  𮗓
          0x0002e5d6_0002e5d6L, //  𮗖
          0x0002e5d8_0002e5d8L, //  𮗘
          0x0002e5da_0002e5daL, //  𮗚
          0x0002e5dc_0002e5dcL, //  𮗜
          0x0002e5df_0002e5e0L, //  𮗟 𮗠
          0x0002e5e8_0002e5e8L, //  𮗨
          0x0002e5eb_0002e5eeL, //  𮗫 𮗬 𮗭 𮗮
          0x0002e5f2_0002e5f2L, //  𮗲
          0x0002e5f7_0002e5f7L, //  𮗷
          0x0002e5fd_0002e5fdL, //  𮗽
          0x0002e601_0002e602L, //  𮘁 𮘂
          0x0002e608_0002e608L, //  𮘈
          0x0002e60a_0002e60aL, //  𮘊
          0x0002e60e_0002e60eL, //  𮘎
          0x0002e610_0002e610L, //  𮘐
          0x0002e619_0002e619L, //  𮘙
          0x0002e621_0002e621L, //  𮘡
          0x0002e624_0002e626L, //  𮘤 𮘥 𮘦
          0x0002e62a_0002e62aL, //  𮘪
          0x0002e62d_0002e62dL, //  𮘭
          0x0002e638_0002e639L, //  𮘸 𮘹
          0x0002e63d_0002e63eL, //  𮘽 𮘾
          0x0002e644_0002e646L, //  𮙄 𮙅 𮙆
          0x0002e657_0002e658L, //  𮙗 𮙘
          0x0002e65a_0002e660L, //  𮙚 𮙛 𮙜 𮙝 𮙞 𮙟 𮙠
          0x0002e663_0002e663L, //  𮙣
          0x0002e666_0002e666L, //  𮙦
          0x0002e66a_0002e66aL, //  𮙪
          0x0002e66d_0002e66eL, //  𮙭 𮙮
          0x0002e672_0002e672L, //  𮙲
          0x0002e67e_0002e67fL, //  𮙾 𮙿
          0x0002e681_0002e681L, //  𮚁
          0x0002e683_0002e687L, //  𮚃 𮚄 𮚅 𮚆 𮚇
          0x0002e68b_0002e68dL, //  𮚋 𮚌 𮚍
          0x0002e691_0002e691L, //  𮚑
          0x0002e695_0002e696L, //  𮚕 𮚖
          0x0002e69a_0002e69aL, //  𮚚
          0x0002e6a6_0002e6a6L, //  𮚦
          0x0002e6b0_0002e6b0L, //  𮚰
          0x0002e6b4_0002e6b4L, //  𮚴
          0x0002e6b6_0002e6b6L, //  𮚶
          0x0002e6b8_0002e6b8L, //  𮚸
          0x0002e6d9_0002e6d9L, //  𮛙
          0x0002e6e1_0002e6e1L, //  𮛡
          0x0002e6e9_0002e6eaL, //  𮛩 𮛪
          0x0002e700_0002e700L, //  𮜀
          0x0002e709_0002e709L, //  𮜉
          0x0002e712_0002e712L, //  𮜒
          0x0002e715_0002e715L, //  𮜕
          0x0002e72c_0002e72cL, //  𮜬
          0x0002e736_0002e736L, //  𮜶
          0x0002e73f_0002e740L, //  𮜿 𮝀
          0x0002e747_0002e747L, //  𮝇
          0x0002e75d_0002e75eL, //  𮝝 𮝞
          0x0002e769_0002e769L, //  𮝩
          0x0002e76c_0002e76cL, //  𮝬
          0x0002e77b_0002e77cL, //  𮝻 𮝼
          0x0002e77e_0002e77eL, //  𮝾
          0x0002e782_0002e782L, //  𮞂
          0x0002e785_0002e786L, //  𮞅 𮞆
          0x0002e789_0002e789L, //  𮞉
          0x0002e798_0002e79aL, //  𮞘 𮞙 𮞚
          0x0002e79f_0002e79fL, //  𮞟
          0x0002e7ad_0002e7adL, //  𮞭
          0x0002e7b0_0002e7b0L, //  𮞰
          0x0002e7b7_0002e7b7L, //  𮞷
          0x0002e7bd_0002e7beL, //  𮞽 𮞾
          0x0002e7c0_0002e7c0L, //  𮟀
          0x0002e7c3_0002e7c3L, //  𮟃
          0x0002e7c8_0002e7c8L, //  𮟈
          0x0002e7d0_0002e7d0L, //  𮟐
          0x0002e7d7_0002e7d8L, //  𮟗 𮟘
          0x0002e7da_0002e7daL, //  𮟚
          0x0002e7dd_0002e7deL, //  𮟝 𮟞
          0x0002e7e3_0002e7e3L, //  𮟣
          0x0002e7f2_0002e7f2L, //  𮟲
          0x0002e7fb_0002e7fbL, //  𮟻
          0x0002e7fe_0002e7feL, //  𮟾
          0x0002e811_0002e811L, //  𮠑
          0x0002e815_0002e816L, //  𮠕 𮠖
          0x0002e818_0002e818L, //  𮠘
          0x0002e81a_0002e81aL, //  𮠚
          0x0002e82d_0002e82dL, //  𮠭
          0x0002e833_0002e833L, //  𮠳
          0x0002e836_0002e836L, //  𮠶
          0x0002e838_0002e838L, //  𮠸
          0x0002e844_0002e844L, //  𮡄
          0x0002e849_0002e849L, //  𮡉
          0x0002e84c_0002e84cL, //  𮡌
          0x0002e84f_0002e84fL, //  𮡏
          0x0002e857_0002e857L, //  𮡗
          0x0002e864_0002e864L, //  𮡤
          0x0002e866_0002e868L, //  𮡦 𮡧 𮡨
          0x0002e86a_0002e86bL, //  𮡪 𮡫
          0x0002e86e_0002e871L, //  𮡮 𮡯 𮡰 𮡱
          0x0002e874_0002e874L, //  𮡴
          0x0002e878_0002e879L, //  𮡸 𮡹
          0x0002e87c_0002e87cL, //  𮡼
          0x0002e87f_0002e880L, //  𮡿 𮢀
          0x0002e883_0002e883L, //  𮢃
          0x0002e889_0002e88aL, //  𮢉 𮢊
          0x0002e88c_0002e88dL, //  𮢌 𮢍
          0x0002e895_0002e896L, //  𮢕 𮢖
          0x0002e899_0002e89cL, //  𮢙 𮢚 𮢛 𮢜
          0x0002e89f_0002e89fL, //  𮢟
          0x0002e8a1_0002e8a2L, //  𮢡 𮢢
          0x0002e8a4_0002e8a4L, //  𮢤
          0x0002e8a8_0002e8a8L, //  𮢨
          0x0002e8ac_0002e8acL, //  𮢬
          0x0002e8b4_0002e8b4L, //  𮢴
          0x0002e8bb_0002e8bcL, //  𮢻 𮢼
          0x0002e8be_0002e8bfL, //  𮢾 𮢿
          0x0002e8c1_0002e8c1L, //  𮣁
          0x0002e8cd_0002e8cdL, //  𮣍
          0x0002e8cf_0002e8d1L, //  𮣏 𮣐 𮣑
          0x0002e8d5_0002e8d5L, //  𮣕
          0x0002e8d7_0002e8d7L, //  𮣗
          0x0002e8d9_0002e8d9L, //  𮣙
          0x0002e8de_0002e8dfL, //  𮣞 𮣟
          0x0002e8f9_0002e8f9L, //  𮣹
          0x0002e907_0002e907L, //  𮤇
          0x0002e91a_0002e91aL, //  𮤚
          0x0002e91c_0002e91cL, //  𮤜
          0x0002e927_0002e929L, //  𮤧 𮤨 𮤩
          0x0002e93e_0002e93eL, //  𮤾
          0x0002e943_0002e943L, //  𮥃
          0x0002e94c_0002e94cL, //  𮥌
          0x0002e94f_0002e94fL, //  𮥏
          0x0002e953_0002e953L, //  𮥓
          0x0002e959_0002e95aL, //  𮥙 𮥚
          0x0002e974_0002e977L, //  𮥴 𮥵 𮥶 𮥷
          0x0002e979_0002e97aL, //  𮥹 𮥺
          0x0002e97e_0002e980L, //  𮥾 𮥿 𮦀
          0x0002e982_0002e982L, //  𮦂
          0x0002e984_0002e984L, //  𮦄
          0x0002e986_0002e986L, //  𮦆
          0x0002e99c_0002e99cL, //  𮦜
          0x0002e99f_0002e99fL, //  𮦟
          0x0002e9a2_0002e9a2L, //  𮦢
          0x0002e9b7_0002e9b7L, //  𮦷
          0x0002e9c0_0002e9c0L, //  𮧀
          0x0002e9c7_0002e9c7L, //  𮧇
          0x0002e9ca_0002e9caL, //  𮧊
          0x0002e9cc_0002e9ccL, //  𮧌
          0x0002e9d2_0002e9d3L, //  𮧒 𮧓
          0x0002e9d7_0002e9d7L, //  𮧗
          0x0002e9de_0002e9dfL, //  𮧞 𮧟
          0x0002e9ee_0002e9f0L, //  𮧮 𮧯 𮧰
          0x0002e9f2_0002e9f2L, //  𮧲
          0x0002e9fb_0002e9fbL, //  𮧻
          0x0002ea00_0002ea00L, //  𮨀
          0x0002ea07_0002ea08L, //  𮨇 𮨈
          0x0002ea0d_0002ea0dL, //  𮨍
          0x0002ea26_0002ea26L, //  𮨦
          0x0002ea37_0002ea37L, //  𮨷
          0x0002ea3d_0002ea3eL, //  𮨽 𮨾
          0x0002ea41_0002ea41L, //  𮩁
          0x0002ea51_0002ea51L, //  𮩑
          0x0002ea66_0002ea66L, //  𮩦
          0x0002ea73_0002ea73L, //  𮩳
          0x0002ea7a_0002ea7aL, //  𮩺
          0x0002ea7c_0002ea7cL, //  𮩼
          0x0002ea87_0002ea88L, //  𮪇 𮪈
          0x0002ea8d_0002ea8dL, //  𮪍
          0x0002ea9c_0002ea9cL, //  𮪜
          0x0002eaa0_0002eaa0L, //  𮪠
          0x0002eaa6_0002eaa6L, //  𮪦
          0x0002eaac_0002eaacL, //  𮪬
          0x0002eabb_0002eabbL, //  𮪻
          0x0002eac4_0002eac4L, //  𮫄
          0x0002eac7_0002eac7L, //  𮫇
          0x0002eacb_0002eacbL, //  𮫋
          0x0002eace_0002eacfL, //  𮫎 𮫏
          0x0002ead6_0002ead9L, //  𮫖 𮫗 𮫘 𮫙
          0x0002eadf_0002eadfL, //  𮫟
          0x0002eaec_0002eaf0L, //  𮫬 𮫭 𮫮 𮫯 𮫰
          0x0002eaf2_0002eaf5L, //  𮫲 𮫳 𮫴 𮫵
          0x0002eaf9_0002eafaL, //  𮫹 𮫺
          0x0002eafc_0002eafeL, //  𮫼 𮫽 𮫾
          0x0002eb00_0002eb02L, //  𮬀 𮬁 𮬂
          0x0002eb06_0002eb07L, //  𮬆 𮬇
          0x0002eb09_0002eb09L, //  𮬉
          0x0002eb0e_0002eb0fL, //  𮬎 𮬏
          0x0002eb13_0002eb14L, //  𮬓 𮬔
          0x0002eb17_0002eb19L, //  𮬗 𮬘 𮬙
          0x0002eb2c_0002eb2cL, //  𮬬
          0x0002eb30_0002eb32L, //  𮬰 𮬱 𮬲
          0x0002eb39_0002eb39L, //  𮬹
          0x0002eb3c_0002eb3cL, //  𮬼
          0x0002eb4b_0002eb4bL, //  𮭋
          0x0002eb50_0002eb50L, //  𮭐
          0x0002eb58_0002eb58L, //  𮭘
          0x0002eb5d_0002eb5dL, //  𮭝
          0x0002eb5f_0002eb60L, //  𮭟 𮭠
          0x0002eb6d_0002eb6dL, //  𮭭
          0x0002eb71_0002eb71L, //  𮭱
          0x0002eb74_0002eb74L, //  𮭴
          0x0002eb76_0002eb77L, //  𮭶 𮭷
          0x0002eb79_0002eb79L, //  𮭹
          0x0002eb81_0002eb81L, //  𮮁
          0x0002eb84_0002eb84L, //  𮮄
          0x0002eb86_0002eb86L, //  𮮆
          0x0002eb90_0002eb91L, //  𮮐 𮮑
          0x0002eba0_0002eba0L, //  𮮠
          0x0002eba6_0002eba6L, //  𮮦
          0x0002ebb0_0002ebb5L, //  𮮰 𮮱 𮮲 𮮳 𮮴 𮮵
          0x0002ebb9_0002ebbeL, //  𮮹 𮮺 𮮻 𮮼 𮮽 𮮾
          0x0002ebc3_0002ebc5L, //  𮯃 𮯄 𮯅
          0x0002ebc7_0002ebc8L, //  𮯇 𮯈
          0x0002ebcb_0002ebccL, //  𮯋 𮯌
          0x0002ebce_0002ebceL, //  𮯎
          0x0002ebd7_0002ebd7L, //  𮯗
          0x0002ebdb_0002ebe0L, //  𮯛 𮯜 𮯝 𮯞 𮯟 𮯠
      });

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
  Language NORWEGIAN_BOKMÅL_nb = new LanguageImpl(
      new Locale("nb", "", ""),
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
      });

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
  Language NORWEGIAN_NYNORSK_nn = new LanguageImpl(
      new Locale("nn", "", ""),
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
      });
  Language DUTCH_nl = new LanguageImpl(
      new Locale("nl", "", ""),
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
      });
  Language DUTCH_nl_BE = new LanguageImpl(
      new Locale("nl", "BE", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      });
  Language PORTUGUESE_pt = new LanguageImpl(
      new Locale("pt", "", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xe0_e3, //  à á â ã
          0xe7_e7, //  ç
          0xe9_ea, //  é ê
          0xed_ed, //  í
          0xf3_f5, //  ó ô õ
          0xfa_fa, //  ú
      });

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
  Language SWEDISH_sv = new LanguageImpl(
      new Locale("sv", "", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
          0xc4_c5, //  Ä Å
          0xd6_d6, //  Ö
          0xe4_e5, //  ä å
          0xf6_f6, //  ö
      });

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
  Language VIETNAMESE_vi = new LanguageImpl(
      new Locale("vi", "", ""),
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
      });
}
