package land.artli.easytype;

import java.util.Locale;
import javax.annotation.processing.Generated;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the data-type-language-generator module.",
    value = "land.artli:data-type-language-generator")
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
