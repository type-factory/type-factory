package land.artli.easytype;

public enum Language implements Subset {

  LETTERS_ARABIC_AR(RangedSubset.builder()
      .addChars('ء', 'آ', 'أ', 'ؤ', 'إ', 'ئ', 'ا', 'ب', 'ة', 'ت', 'ث', 'ج', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز',
          'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'ل', 'م', 'ن', 'ه', 'و', 'ى', 'ي')
      .build()),

  LETTERS_ARMENIAN_HY(RangedSubset.builder()
      .addChars('Ա', 'Բ', 'Գ', 'Դ', 'Ե', 'Զ', 'Է', 'Ը', 'Թ', 'Ժ', 'Ի', 'Լ', 'Խ', 'Ծ', 'Կ',
          'Հ', 'Ձ', 'Ղ', 'Ճ', 'Մ', 'Յ', 'Ն', 'Շ', 'Ո', 'Չ', 'Պ', 'Ջ', 'Ռ', 'Ս', 'Վ', 'Տ',
          'Ր', 'Ց', 'Ւ', 'Փ', 'Ք', 'Օ', 'Ֆ', 'ա', 'բ', 'գ', 'դ', 'ե', 'զ', 'է', 'ը', 'թ',
          'ժ', 'ի', 'լ', 'խ', 'ծ', 'կ', 'հ', 'ձ', 'ղ', 'ճ', 'մ', 'յ', 'ն', 'շ', 'ո', 'չ',
          'պ', 'ջ', 'ռ', 'ս', 'վ', 'տ', 'ր', 'ց', 'ւ', 'փ', 'ք', 'օ', 'ֆ')
      .build()),

  LETTERS_AZERI_AZ_LATN(RangedSubset.builder()
      .addChars('A', 'a', 'B', 'b', 'C', 'c', 'Ç', 'ç', 'D', 'd', 'E', 'e', 'Ə', 'ə', 'F', 'f', 'G',
          'g', 'Ğ', 'ğ', 'H', 'h', 'X', 'x', 'I', 'ı', 'İ', 'i', 'J', 'j', 'K', 'k', 'Q', 'q', 'L',
          'l', 'M', 'm', 'N', 'n', 'O', 'o', 'Ö', 'ö', 'P', 'p', 'R', 'r', 'S', 's', 'Ş', 'ş', 'T',
          't', 'U', 'u', 'Ü', 'ü', 'V', 'v', 'Y', 'y', 'Z', 'z')
      .build()),

  LETTERS_ENGLISH_EN(RangedSubset.builder()
      .addCharRange('A', 'Z')
      .addCharRange('a', 'z')
      .build()),

  LETTERS_GREEK_EL(RangedSubset.builder()
      .addChars('Ά', 'Έ', 'Ή', 'Ί', 'Ό', 'Ύ', 'Ώ', 'Α', 'Β', 'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ',
          'Ι', 'Κ', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ο', 'Π', 'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω',
          'Ϊ', 'Ϋ', 'ά', 'έ', 'ή', 'ί', 'ΰ', 'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'θ', 'ι',
          'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'π', 'ρ', 'ς', 'σ', 'τ', 'υ', 'φ', 'χ', 'ψ', 'ω',
          'ϊ', 'ϋ', 'ό', 'ύ', 'ώ')
      .build()),


  NUMBERS(RangedSubset.builder()
      .addCharRange('0', '9')
      .build()),

  NUMBERS_ARABIC(RangedSubset.builder()
      .addChars('١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
      .build()),
  ;

  private final RangedSubset rangedSubset;

  Language(final RangedSubset rangedSubset) {
    this.rangedSubset = rangedSubset;
  }

  @Override
  public boolean isInSubset(int codePoint) {
    return rangedSubset.isInSubset(codePoint);
  }

  RangedSubset getRangedSubset() {
    return rangedSubset;
  }
}
