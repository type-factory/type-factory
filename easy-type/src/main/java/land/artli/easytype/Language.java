package land.artli.easytype;

import java.util.Locale;
import javax.annotation.processing.Generated;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the easy-type-language-generator module.",
    date = "2022-02-23T20:38:21",
    value = "land.artli:easy-type-language-generator")
public interface Language extends Subset {

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
      });

  Language GREEK_el = new LanguageImpl(
      new Locale("el", "", ""),
      new int[]{
          0x0386_0386, //  Ά
          0x0388_038a, //  Έ Ή Ί
          0x038c_038c, //  Ό
          0x038e_03a1, //  Ύ Ώ ΐ Α Β Γ Δ Ε Ζ Η Θ Ι Κ Λ Μ Ν Ξ Ο Π Ρ
          0x03a3_03ce, //  Σ Τ Υ Φ Χ Ψ Ω Ϊ Ϋ ά έ ή ί ΰ α β γ δ ε ζ η θ ι κ λ μ ν ξ ο π ρ ς σ τ υ φ χ ψ ω ϊ ϋ ό ύ ώ
      });

  Language ENGLISH_en = new LanguageImpl(
      new Locale("en", "", ""),
      new char[]{
          0x41_5a, //  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
          0x61_7a, //  a b c d e f g h i j k l m n o p q r s t u v w x y z
      });

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

  Language HINDI_hi = new LanguageImpl(
      new Locale("hi", "", ""),
      new int[]{
          0x0904_0939, //  ऄ अ आ इ ई उ ऊ ऋ ऌ ऍ ऎ ए ऐ ऑ ऒ ओ औ क ख ग घ ङ च छ ज झ ञ ट ठ ड ढ ण त थ द ध न ऩ प फ ब भ म य र ऱ ल ळ ऴ व श ष स ह
          0x093c_094d, //  ़ ऽ ा ि ी ु ू ृ ॄ ॅ ॆ े ै ॉ ॊ ो ौ ्
      });

  Language ARMENIAN_hy = new LanguageImpl(
      new Locale("hy", "", ""),
      new int[]{
          0x0531_0556, //  Ա Բ Գ Դ Ե Զ Է Ը Թ Ժ Ի Լ Խ Ծ Կ Հ Ձ Ղ Ճ Մ Յ Ն Շ Ո Չ Պ Ջ Ռ Ս Վ Տ Ր Ց Ւ Փ Ք Օ Ֆ
          0x0561_0586, //  ա բ գ դ ե զ է ը թ ժ ի լ խ ծ կ հ ձ ղ ճ մ յ ն շ ո չ պ ջ ռ ս վ տ ր ց ւ փ ք օ ֆ
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
          0x1ea0_1ef9,
          //  Ạ ạ Ả ả Ấ ấ Ầ ầ Ẩ ẩ Ẫ ẫ Ậ ậ Ắ ắ Ằ ằ Ẳ ẳ Ẵ ẵ Ặ ặ Ẹ ẹ Ẻ ẻ Ẽ ẽ Ế ế Ề ề Ể ể Ễ ễ Ệ ệ Ỉ ỉ Ị ị Ọ ọ Ỏ ỏ Ố ố Ồ ồ Ổ ổ Ỗ ỗ Ộ ộ Ớ ớ Ờ ờ Ở ở Ỡ ỡ Ợ ợ Ụ ụ Ủ ủ Ứ ứ Ừ ừ Ử ử Ữ ữ Ự ự Ỳ ỳ Ỵ ỵ Ỷ ỷ Ỹ ỹ
          0x20ab_20ab, //  ₫
      });
}
