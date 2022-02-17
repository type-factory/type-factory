package land.artli.easytype.generator.language;

import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.ULocale;

public enum LanguageData {

  LETTERS_ARABIC_AR(locale("ar"),
      new char[]{
          'ء', 'آ', 'أ', 'ؤ', 'إ', 'ئ', 'ا', 'ب', 'ة', 'ت', 'ث', 'ج', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز',
          'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'ل', 'م', 'ن', 'ه', 'و', 'ى', 'ي'}),

  LETTERS_AZERI_AZ_LATN(locale("az", "", "LATN"),
      new char[]{
          'A', 'a', 'B', 'b', 'C', 'c', 'Ç', 'ç', 'D', 'd', 'E', 'e', 'Ə', 'ə', 'F', 'f', 'G',
          'g', 'Ğ', 'ğ', 'H', 'h', 'X', 'x', 'I', 'ı', 'İ', 'i', 'J', 'j', 'K', 'k', 'Q', 'q', 'L',
          'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ö', 'ö', 'P', 'p', 'R', 'r', 'S', 's', 'Ş', 'ş', 'T',
          't', 'U', 'u', 'Ü', 'ü', 'V', 'v', 'Y', 'y', 'Z', 'z'}),

  LETTERS_GERMAN_DE(locale("de"),
      new char[]{
          'A', 'a', 'Ä', 'ä', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g',
          'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ö', 'ö',
          'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'ß', 'T', 't', 'U', 'u', 'Ü', 'ü', 'V', 'v',
          'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'}),


  LETTERS_GREEK_EL(locale("el"),
      new char[]{
          'Ά', 'Έ', 'Ή', 'Ί', 'Ό', 'Ύ', 'Ώ', 'ΐ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ',
          'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω',
          'Ϊ', 'Ϋ', 'ά', 'έ', 'ή', 'ί', 'ΰ', 'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'θ', 'ι',
          'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'π', 'ρ', 'ς', 'σ', 'τ', 'υ', 'φ', 'χ', 'ψ', 'ω',
          'ϊ', 'ϋ', 'ό', 'ύ', 'ώ'}),

  LETTERS_ENGLISH_EN(locale("en"),
      new CharRange[]{
          range('A', 'Z'), range('a', 'z')}),

  LETTERS_SPANISH_ES(locale("es"),
      new char[]{
          'A', 'a', 'Á', 'á', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'É', 'é', 'F', 'f',
          'G', 'g', 'H', 'h', 'I', 'i', 'Í', 'í', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
          'N', 'n', 'Ñ', 'ñ', 'O', 'o', 'Ó', 'ó', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's',
          'T', 't', 'U', 'u', 'Ú', 'ú', 'Ü', 'ü', 'V', 'v', 'W', 'w', 'X', 'x',
          'Y', 'y', 'Ý', 'ý', 'Z', 'z'}),

  LETTERS_FRENCH_FR(locale("fr"),
      new char[]{
          'A', 'a', 'À', 'à', 'Â', 'â', 'Æ', 'æ', 'B', 'b', 'C', 'c', 'Ç', 'ç',
          'D', 'd', 'E', 'e', 'É', 'é', 'È', 'è', 'Ê', 'ê', 'Ë', 'ë', 'F', 'f',
          'G', 'g', 'H', 'h', 'I', 'i', 'Î', 'î', 'Ï', 'ï', 'J', 'j', 'K', 'k',
          'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ô', 'ô', 'Œ', 'œ', 'P', 'p',
          'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'Ù', 'ù', 'Û', 'û', 'Ü', 'ü',
          'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Ÿ', 'ÿ', 'Z', 'z'}),

  LETTERS_ICELANDIC_IS(locale("is"),
      new char[]{
          'A', 'a', 'Á', 'á', 'B', 'b', 'D', 'd', 'Ð', 'ð', 'E', 'e', 'É', 'é',
          'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'Í', 'í', 'J', 'j', 'K', 'k',
          'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ó', 'ó', 'P', 'p', 'R', 'r',
          'S', 's', 'T', 't', 'U', 'u', 'Ú', 'ú', 'V', 'v', 'X', 'x', 'Y', 'y',
          'Ý', 'ý', 'Z', 'z', 'Þ', 'þ', 'Æ', 'æ', 'Ö', 'ö'}),

  LETTERS_HINDI_HI(locale("hi"),
      new char[]{
          'ऄ', 'अ', 'आ', 'इ', 'ई', 'उ', 'ऊ', 'ऋ', 'ऌ', 'ऍ', 'ऎ', 'ए', 'ऐ', 'ऑ', 'ऒ',
          'ओ', 'औ', 'क', 'ख', 'ग', 'घ', 'ङ', 'च', 'छ', 'ज', 'झ', 'ञ', 'ट', 'ठ', 'ड', 'ढ',
          'ण', 'त', 'थ', 'द', 'ध', 'न', 'ऩ', 'प', 'फ', 'ब', 'भ', 'म', 'य', 'र', 'ऱ', 'ल',
          'ळ', 'ऴ', 'व', 'श', 'ष', 'स', 'ह',
          '\u094d' // Virama / halant
      },
      new CharRange[]{
          range('\u093c', '\u093d'), // Various signs
          range('\u093e', '\u094c') // Dependent vowel signs
      }),

  LETTERS_ARMENIAN_HY(locale("hy"),
      new char[]{
          'Ա', 'Բ', 'Գ', 'Դ', 'Ե', 'Զ', 'Է', 'Ը', 'Թ', 'Ժ', 'Ի', 'Լ', 'Խ', 'Ծ', 'Կ',
          'Հ', 'Ձ', 'Ղ', 'Ճ', 'Մ', 'Յ', 'Ն', 'Շ', 'Ո', 'Չ', 'Պ', 'Ջ', 'Ռ', 'Ս', 'Վ', 'Տ',
          'Ր', 'Ց', 'Ւ', 'Փ', 'Ք', 'Օ', 'Ֆ', 'ա', 'բ', 'գ', 'դ', 'ե', 'զ', 'է', 'ը', 'թ',
          'ժ', 'ի', 'լ', 'խ', 'ծ', 'կ', 'հ', 'ձ', 'ղ', 'ճ', 'մ', 'յ', 'ն', 'շ', 'ո', 'չ',
          'պ', 'ջ', 'ռ', 'ս', 'վ', 'տ', 'ր', 'ց', 'ւ', 'փ', 'ք', 'օ', 'ֆ'}),

  LETTERS_PORTUGUESE_PT(locale("pt"),
      new char[]{
          'A', 'a', 'á', 'â', 'ã', 'à', 'B', 'b', 'C', 'c', 'ç', 'D', 'd', 'E', 'e', 'é', 'ê',
          'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'í', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm',
          'N', 'n', 'O', 'o', 'ó', 'ô', 'õ', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
          'U', 'u', 'ú', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'}),

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

  LanguageData(final ULocale locale, final char[] chars) {
    this(locale, chars, null);
  }

  LanguageData(final ULocale locale, final CharRange[] charRanges) {
    this(locale, null, charRanges);
  }

  LanguageData(final ULocale locale, final char[] chars, final CharRange[] charRanges) {
    this.locale = locale;
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

  public UnicodeSet getUnicodeSet() {
    return unicodeSet;
  }

  static ULocale locale(String language) {
    return locale(language, "", "");
  }

  static ULocale locale(String language, String country) {
    return locale(language, country, "");
  }

  static ULocale locale(String language, String country, String variant) {
    return new ULocale(language, country, variant);
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
