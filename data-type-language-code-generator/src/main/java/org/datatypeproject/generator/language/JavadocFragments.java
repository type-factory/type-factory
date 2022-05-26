package org.datatypeproject.generator.language;

public final class JavadocFragments {

  private JavadocFragments() {
  }

  public static final String LANGUAGE_ALPHABET_AIM_JAVADOC = """
      <p>The primary aim of this language alphabet is to assist in the creation of
         software data-types.<p>""";

  public static final String LANGUAGE_ALPHABET_INCLUDED_JAVADOC = """
      <b>Included in this alphabet</b>
            
      <p>This language code-point subset should capture the official
         standard alphabet or ideographs for words or names as would be accepted by
         institutions and organizations that are required to adhere to statutory
         regulations.</p>""";

  public static final String LANGUAGE_ALPHABET_NOT_INCLUDED_JAVADOC = """
      <b>Not included in this alphabet</b>
            
      <p>Letters, diacritics or modifiers that are not part of the official language are
         not contained in this alphabet. Even if they appear occasionally in loan-words
         or foreign names.</p>
         
      <p>Punctuation characters are also not part of this alphabet – they are considered
         to be a structural component to forming sentences in a language.</p>""";

  public static final String LANGUAGE_ALPHABET_IF_YOU_SEE_A_MISTAKE_JAVADOC = """
      <b>Do you see a mistake?</b>
            
      <p>If you believe there are errors in this alphabet please reach out
         to us and provide sources / references that support what you think should
         be added to, or removed from, the alphabet.</p>""";

  public static final String SEE_ALSO_ALPHABETS_LETTERS_AND_DIACRITICS_IN_EUROPEAN_LANGUAGES = """
      @see <a href="https://nordendivision.nfi.ku.dk/about_ungegn/romanization/Leira%20Vigleik%20_2008_%20Alphabets%20Letters%20and%20Diacritics%20in%20European%20Languages.pdf"
           target="_blank">Alphabets, Letters and Diacritics in European Languages –
           Vigleik Leira</a> provides a good primer, or starting point, for learning 
           about the European language alphabets that use the the Latin script.""";

  public static final String SEE_ALSO_COMMON_ARABIC_SCRIPT_REFERENCES = """
      @see <a href="https://en.wikipedia.org/wiki/Arabic_script" target="_blank">Arabic
           Script – Wikipedia</a> provided information about the Arabic script.

      @see <a href="https://en.wikipedia.org/wiki/Arabic_alphabet" target="_blank">Arabic
           Alphabet – Wikipedia</a> provided information about the Arabic alphabet.
           
      @see <a href="https://unicode.org/charts/PDF/U0600.pdf" target="_blank">Arabic
           Unicode Chart – Unicode Standard v14</a> provided information about
           the Unicode encodings for characters in the Arabic script.""";

}
