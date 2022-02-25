package land.artli.easytype;

import java.util.Locale;
import javax.annotation.processing.Generated;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the easy-type-language-generator module.",
    value = "land.artli:easy-type-language-generator")
public interface Language extends Subset {

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
   *
   * <pre>
   *    0621..063A   ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
   *    0641..064A   ف ق ك ل م ن ه و ى ي
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   *
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Arabic_script" target="_blank">Arabic
   * Script – Wikipedia</a> provided information about the Arabic script.
   * @see <a href="https://en.wikipedia.org/wiki/Arabic_alphabet" target="_blank">Arabic
   * Alphabet – Wikipedia</a> provided information about the Arabic alphabet.
   * @see <a href="https://unicode.org/charts/PDF/U0600.pdf" target="_blank">Arabic
   * Unicode Chart – Unicode Standard v14</a> provided information about the Unicode encodings for characters in the Arabic script.
   */
  Language ARABIC_ar = new LanguageImpl(
      new Locale("ar", "", ""),
      new int[]{
          0x0621_063a, //  ء آ أ ؤ إ ئ ا ب ة ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ
          0x0641_064a, //  ف ق ك ل م ن ه و ى ي
      });

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
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
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
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Danish_orthography" target="_blank">Danish
   * Orthography – Wikipedia</a> provided information about the Danish alphabet and what diacritics are supported.
   * @see <a href="https://www.danishnet.com/culture/danish-alphabet" target="_blank">Danish
   * Alphabet – Danishnet.com</a> provided information about the Danish alphabet.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
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
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
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
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/German_orthography" target="_blank">German
   * Orthography – Wikipedia</a> provided information about the German alphabet and what diacritics are supported.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
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
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
   *
   * <pre>
   *    0386         Ά
   *    0388..038A   Έ Ή Ί
   *    038C         Ό
   *    038E..03A1   Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
   *    03A3..03CE   Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   *
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Greek_alphabet" target="_blank">Greek
   * Alphabet – Wikipedia</a> provided information about the Greek alphabet and what diacritics are supported.
   * @see <a href="https://unicode.org/charts/PDF/U0370.pdf" target="_blank">Greek and Coptic
   * Unicode Chart – Unicode Standard v14</a> provided information about the Unicode encodings for characters in the Greek and Coptic scripts.
   */
  Language GREEK_el = new LanguageImpl(
      new Locale("el", "", ""),
      new int[]{
          0x0386_0386, //  Ά
          0x0388_038a, //  Έ Ή Ί
          0x038c_038c, //  Ό
          0x038e_03a1, //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
          0x03a3_03ce, //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
      });

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
   *
   * <pre>
   *    0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
   *    0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
   * </pre>
   *
   * <b>Not included in this alphabet</b>
   *
   * <p>Letters, diacritics or modifiers that are not part of the official language are
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/English_alphabet" target="_blank">English
   * Alphabet – Wikipedia</a> provided information about the English alphabet.
   * @see <a href="https://unicode.org/charts/PDF/U0000.pdf" target="_blank">Controls and Basic Latin
   * Unicode Chart – Unicode Standard v14</a> provided information about the Unicode encodings for characters in the Basic Latin scripts.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
   */
  Language ENGLISH_en = new LanguageImpl(
      new Locale("en", "", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      });

  /**
   * <p>The primary aim of this language alphabet is to assist in the creation of
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
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
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Spanish_orthography" target="_blank">Spanish
   * Orthography – Wikipedia</a> provided information about the Spanish alphabet and what diacritics are supported.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
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
   * <p>This language alphabet should capture the official
   *    standard alphabet for words or names as would be accepted by government
   *    institutions and corporate organizations that must adhere to statutory
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
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
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
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/French_orthography" target="_blank">French
   * Orthography – Wikipedia</a> provided information about the French alphabet and what diacritics are supported.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
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
   * <p>This language alphabet should capture the official
   *    standard alphabet for words or names as would be accepted by government
   *    institutions and corporate organizations that must adhere to statutory
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
          0x0531_0556, //  Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ Տ Ր Ց Ւ Փ Ք Օ Ֆ
          0x0561_0586, //  ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ տ ր ց ւ փ ք օ ֆ
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

  Language JAPANESE_ja_Hira = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Hira").build(),
      new int[]{
          0x3041_3096, //  ぁ あ ぃ い ぅ う ぇ え ぉ お か が き ぎ く ぐ け げ こ ご さ ざ し じ す ず せ ぜ そ ぞ た だ ち ぢ っ つ づ て で と ど な に ぬ ね の は ば ぱ ひ び ぴ ふ ぶ ぷ へ べ ぺ ほ ぼ ぽ ま み む め も ゃ や ゅ ゆ ょ よ ら り る れ ろ ゎ わ ゐ ゑ を ん ゔ ゕ ゖ
      });

  Language JAPANESE_ja_Kana = new LanguageImpl(
      new Locale.Builder().setLanguage("ja").setRegion("").setVariant("").setScript("Kana").build(),
      new int[]{
          0x30a1_30fa,
          //  ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ タ ダ チ ヂ ッ ツ ヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ バ パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ ポ マ ミ ム メ モ ャ ヤ ュ ユ ョ ヨ ラ リ ル レ ロ ヮ ワ ヰ ヱ ヲ ン ヴ ヵ ヶ ヷ ヸ ヹ ヺ
      });

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
   * software data-types.<p>
   *
   * <b>Included in this alphabet</b>
   *
   * <p>This language alphabet should capture the official
   * standard alphabet for words or names as would be accepted by government institutions and corporate organizations that must adhere to statutory
   * regulations.</p>
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
   * not contained in this alphabet. Even if they appear occasionally in loan-words or foreign names.</p>
   *
   * <p>Punctuation characters are also not part of this alphabet – they are considered
   * to be a structural component to forming sentences in a language.</p>
   *
   * <b>Do you see a mistake?</b>
   *
   * <p>If you believe there are errors in this alphabet please reach out
   * to us and provide sources / references that support what you think should be added to, or removed from, the alphabet.</p>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Swedish_orthography" target="_blank">Swedish
   * Orthography – Wikipedia</a> provided information about the Swedish alphabet and what diacritics are supported.
   * @see <a href="https://learningswedish.se/courses/1/pages/the-alphabet" target="_blank">Swedish
   * Alphabet– Swedish Institute</a> provided by the Svenska institutet (Swedish Institute) as part of their Learning Swedish course.
   * @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
   * target="_blank">Alphabets, Letters and Diacritics in European Languages – Vigleik Leira</a> provides a good primer, or starting point, for
   * learning about the European language alphabets that use the the Latin script.
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
          0x1ea0_1ef9, //  Ạ ạ Ả ả Ấ ấ Ầ ầ Ẩ ẩ Ẫ ẫ Ậ ậ Ắ ắ Ằ ằ Ẳ ẳ Ẵ ẵ Ặ ặ Ẹ ẹ Ẻ ẻ Ẽ ẽ Ế ế Ề ề Ể ể Ễ ễ Ệ ệ Ỉ ỉ Ị ị Ọ ọ Ỏ ỏ Ố ố Ồ ồ Ổ ổ Ỗ ỗ Ộ ộ Ớ ớ Ờ ờ Ở ở Ỡ ỡ Ợ ợ Ụ ụ Ủ ủ Ứ ứ Ừ ừ Ử ử Ữ ữ Ự ự Ỳ ỳ Ỵ ỵ Ỷ ỷ Ỹ ỹ
          0x20ab_20ab, //  ₫
      });
}
