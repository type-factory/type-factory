package land.artli.easytype;

public enum Language implements Subset {

  LETTERS_ENGLISH(RangedSubset.builder()
      .addCharRange('A', 'Z')
      .addCharRange('a', 'z')
      .build()),


  LETTERS_ARABIC(RangedSubset.builder()
      .addChars('ء', 'آ', 'أ', 'ؤ', 'إ', 'ئ', 'ا', 'ب', 'ة', 'ت', 'ث', 'ج', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز',
          'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ك', 'ل', 'م', 'ن', 'ه', 'و', 'ى', 'ي')
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
