/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.unicode.cldr;

import java.util.Locale;
import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Sichuan Yi as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Sichuan Yi language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ii extends root {

  public ii() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ii(
          final Subset standardSubset,
          final Subset auxiliarySubset,
          final Subset punctuationSubset,
          final Subset decimalDigitsSubset) {
    super(
        standardSubset == null ? STANDARD_CHARACTERS_SUBSET : standardSubset,
        auxiliarySubset == null ? AUXILIARY_CHARACTERS_SUBSET : auxiliarySubset,
        punctuationSubset == null ? PUNCTUATION_CHARACTERS_SUBSET : punctuationSubset,
        decimalDigitsSubset == null ? DECIMAL_DIGITS_SUBSET : decimalDigitsSubset);
  }

  /**
   * <p>The Locale represented by this resource bundle for the Sichuan Yi language.</p>
   *
   * <p>Language tag: {@code "ii"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ii")
          .setScript("")
          .setRegion("")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Sichuan Yi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the standard characters
   *    are inherited from the superclass.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0xa000_a48c, //  ꀀ ꀁ ꀂ ꀃ ꀄ ꀅ ꀆ ꀇ ꀈ ꀉ ꀊ ꀋ ꀌ ꀍ ꀎ ꀏ ꀐ ꀑ ꀒ ꀓ ꀔ ꀕ ꀖ ꀗ ꀘ ꀙ ꀚ ꀛ ꀜ ꀝ
                       //  ꀞ ꀟ ꀠ ꀡ ꀢ ꀣ ꀤ ꀥ ꀦ ꀧ ꀨ ꀩ ꀪ ꀫ ꀬ ꀭ ꀮ ꀯ ꀰ ꀱ ꀲ ꀳ ꀴ ꀵ ꀶ ꀷ ꀸ ꀹ ꀺ ꀻ
                       //  ꀼ ꀽ ꀾ ꀿ ꁀ ꁁ ꁂ ꁃ ꁄ ꁅ ꁆ ꁇ ꁈ ꁉ ꁊ ꁋ ꁌ ꁍ ꁎ ꁏ ꁐ ꁑ ꁒ ꁓ ꁔ ꁕ ꁖ ꁗ ꁘ ꁙ
                       //  ꁚ ꁛ ꁜ ꁝ ꁞ ꁟ ꁠ ꁡ ꁢ ꁣ ꁤ ꁥ ꁦ ꁧ ꁨ ꁩ ꁪ ꁫ ꁬ ꁭ ꁮ ꁯ ꁰ ꁱ ꁲ ꁳ ꁴ ꁵ ꁶ ꁷ
                       //  ꁸ ꁹ ꁺ ꁻ ꁼ ꁽ ꁾ ꁿ ꂀ ꂁ ꂂ ꂃ ꂄ ꂅ ꂆ ꂇ ꂈ ꂉ ꂊ ꂋ ꂌ ꂍ ꂎ ꂏ ꂐ ꂑ ꂒ ꂓ ꂔ ꂕ
                       //  ꂖ ꂗ ꂘ ꂙ ꂚ ꂛ ꂜ ꂝ ꂞ ꂟ ꂠ ꂡ ꂢ ꂣ ꂤ ꂥ ꂦ ꂧ ꂨ ꂩ ꂪ ꂫ ꂬ ꂭ ꂮ ꂯ ꂰ ꂱ ꂲ ꂳ
                       //  ꂴ ꂵ ꂶ ꂷ ꂸ ꂹ ꂺ ꂻ ꂼ ꂽ ꂾ ꂿ ꃀ ꃁ ꃂ ꃃ ꃄ ꃅ ꃆ ꃇ ꃈ ꃉ ꃊ ꃋ ꃌ ꃍ ꃎ ꃏ ꃐ ꃑ
                       //  ꃒ ꃓ ꃔ ꃕ ꃖ ꃗ ꃘ ꃙ ꃚ ꃛ ꃜ ꃝ ꃞ ꃟ ꃠ ꃡ ꃢ ꃣ ꃤ ꃥ ꃦ ꃧ ꃨ ꃩ ꃪ ꃫ ꃬ ꃭ ꃮ ꃯ
                       //  ꃰ ꃱ ꃲ ꃳ ꃴ ꃵ ꃶ ꃷ ꃸ ꃹ ꃺ ꃻ ꃼ ꃽ ꃾ ꃿ ꄀ ꄁ ꄂ ꄃ ꄄ ꄅ ꄆ ꄇ ꄈ ꄉ ꄊ ꄋ ꄌ ꄍ
                       //  ꄎ ꄏ ꄐ ꄑ ꄒ ꄓ ꄔ ꄕ ꄖ ꄗ ꄘ ꄙ ꄚ ꄛ ꄜ ꄝ ꄞ ꄟ ꄠ ꄡ ꄢ ꄣ ꄤ ꄥ ꄦ ꄧ ꄨ ꄩ ꄪ ꄫ
                       //  ꄬ ꄭ ꄮ ꄯ ꄰ ꄱ ꄲ ꄳ ꄴ ꄵ ꄶ ꄷ ꄸ ꄹ ꄺ ꄻ ꄼ ꄽ ꄾ ꄿ ꅀ ꅁ ꅂ ꅃ ꅄ ꅅ ꅆ ꅇ ꅈ ꅉ
                       //  ꅊ ꅋ ꅌ ꅍ ꅎ ꅏ ꅐ ꅑ ꅒ ꅓ ꅔ ꅕ ꅖ ꅗ ꅘ ꅙ ꅚ ꅛ ꅜ ꅝ ꅞ ꅟ ꅠ ꅡ ꅢ ꅣ ꅤ ꅥ ꅦ ꅧ
                       //  ꅨ ꅩ ꅪ ꅫ ꅬ ꅭ ꅮ ꅯ ꅰ ꅱ ꅲ ꅳ ꅴ ꅵ ꅶ ꅷ ꅸ ꅹ ꅺ ꅻ ꅼ ꅽ ꅾ ꅿ ꆀ ꆁ ꆂ ꆃ ꆄ ꆅ
                       //  ꆆ ꆇ ꆈ ꆉ ꆊ ꆋ ꆌ ꆍ ꆎ ꆏ ꆐ ꆑ ꆒ ꆓ ꆔ ꆕ ꆖ ꆗ ꆘ ꆙ ꆚ ꆛ ꆜ ꆝ ꆞ ꆟ ꆠ ꆡ ꆢ ꆣ
                       //  ꆤ ꆥ ꆦ ꆧ ꆨ ꆩ ꆪ ꆫ ꆬ ꆭ ꆮ ꆯ ꆰ ꆱ ꆲ ꆳ ꆴ ꆵ ꆶ ꆷ ꆸ ꆹ ꆺ ꆻ ꆼ ꆽ ꆾ ꆿ ꇀ ꇁ
                       //  ꇂ ꇃ ꇄ ꇅ ꇆ ꇇ ꇈ ꇉ ꇊ ꇋ ꇌ ꇍ ꇎ ꇏ ꇐ ꇑ ꇒ ꇓ ꇔ ꇕ ꇖ ꇗ ꇘ ꇙ ꇚ ꇛ ꇜ ꇝ ꇞ ꇟ
                       //  ꇠ ꇡ ꇢ ꇣ ꇤ ꇥ ꇦ ꇧ ꇨ ꇩ ꇪ ꇫ ꇬ ꇭ ꇮ ꇯ ꇰ ꇱ ꇲ ꇳ ꇴ ꇵ ꇶ ꇷ ꇸ ꇹ ꇺ ꇻ ꇼ ꇽ
                       //  ꇾ ꇿ ꈀ ꈁ ꈂ ꈃ ꈄ ꈅ ꈆ ꈇ ꈈ ꈉ ꈊ ꈋ ꈌ ꈍ ꈎ ꈏ ꈐ ꈑ ꈒ ꈓ ꈔ ꈕ ꈖ ꈗ ꈘ ꈙ ꈚ ꈛ
                       //  ꈜ ꈝ ꈞ ꈟ ꈠ ꈡ ꈢ ꈣ ꈤ ꈥ ꈦ ꈧ ꈨ ꈩ ꈪ ꈫ ꈬ ꈭ ꈮ ꈯ ꈰ ꈱ ꈲ ꈳ ꈴ ꈵ ꈶ ꈷ ꈸ ꈹ
                       //  ꈺ ꈻ ꈼ ꈽ ꈾ ꈿ ꉀ ꉁ ꉂ ꉃ ꉄ ꉅ ꉆ ꉇ ꉈ ꉉ ꉊ ꉋ ꉌ ꉍ ꉎ ꉏ ꉐ ꉑ ꉒ ꉓ ꉔ ꉕ ꉖ ꉗ
                       //  ꉘ ꉙ ꉚ ꉛ ꉜ ꉝ ꉞ ꉟ ꉠ ꉡ ꉢ ꉣ ꉤ ꉥ ꉦ ꉧ ꉨ ꉩ ꉪ ꉫ ꉬ ꉭ ꉮ ꉯ ꉰ ꉱ ꉲ ꉳ ꉴ ꉵ
                       //  ꉶ ꉷ ꉸ ꉹ ꉺ ꉻ ꉼ ꉽ ꉾ ꉿ ꊀ ꊁ ꊂ ꊃ ꊄ ꊅ ꊆ ꊇ ꊈ ꊉ ꊊ ꊋ ꊌ ꊍ ꊎ ꊏ ꊐ ꊑ ꊒ ꊓ
                       //  ꊔ ꊕ ꊖ ꊗ ꊘ ꊙ ꊚ ꊛ ꊜ ꊝ ꊞ ꊟ ꊠ ꊡ ꊢ ꊣ ꊤ ꊥ ꊦ ꊧ ꊨ ꊩ ꊪ ꊫ ꊬ ꊭ ꊮ ꊯ ꊰ ꊱ
                       //  ꊲ ꊳ ꊴ ꊵ ꊶ ꊷ ꊸ ꊹ ꊺ ꊻ ꊼ ꊽ ꊾ ꊿ ꋀ ꋁ ꋂ ꋃ ꋄ ꋅ ꋆ ꋇ ꋈ ꋉ ꋊ ꋋ ꋌ ꋍ ꋎ ꋏ
                       //  ꋐ ꋑ ꋒ ꋓ ꋔ ꋕ ꋖ ꋗ ꋘ ꋙ ꋚ ꋛ ꋜ ꋝ ꋞ ꋟ ꋠ ꋡ ꋢ ꋣ ꋤ ꋥ ꋦ ꋧ ꋨ ꋩ ꋪ ꋫ ꋬ ꋭ
                       //  ꋮ ꋯ ꋰ ꋱ ꋲ ꋳ ꋴ ꋵ ꋶ ꋷ ꋸ ꋹ ꋺ ꋻ ꋼ ꋽ ꋾ ꋿ ꌀ ꌁ ꌂ ꌃ ꌄ ꌅ ꌆ ꌇ ꌈ ꌉ ꌊ ꌋ
                       //  ꌌ ꌍ ꌎ ꌏ ꌐ ꌑ ꌒ ꌓ ꌔ ꌕ ꌖ ꌗ ꌘ ꌙ ꌚ ꌛ ꌜ ꌝ ꌞ ꌟ ꌠ ꌡ ꌢ ꌣ ꌤ ꌥ ꌦ ꌧ ꌨ ꌩ
                       //  ꌪ ꌫ ꌬ ꌭ ꌮ ꌯ ꌰ ꌱ ꌲ ꌳ ꌴ ꌵ ꌶ ꌷ ꌸ ꌹ ꌺ ꌻ ꌼ ꌽ ꌾ ꌿ ꍀ ꍁ ꍂ ꍃ ꍄ ꍅ ꍆ ꍇ
                       //  ꍈ ꍉ ꍊ ꍋ ꍌ ꍍ ꍎ ꍏ ꍐ ꍑ ꍒ ꍓ ꍔ ꍕ ꍖ ꍗ ꍘ ꍙ ꍚ ꍛ ꍜ ꍝ ꍞ ꍟ ꍠ ꍡ ꍢ ꍣ ꍤ ꍥ
                       //  ꍦ ꍧ ꍨ ꍩ ꍪ ꍫ ꍬ ꍭ ꍮ ꍯ ꍰ ꍱ ꍲ ꍳ ꍴ ꍵ ꍶ ꍷ ꍸ ꍹ ꍺ ꍻ ꍼ ꍽ ꍾ ꍿ ꎀ ꎁ ꎂ ꎃ
                       //  ꎄ ꎅ ꎆ ꎇ ꎈ ꎉ ꎊ ꎋ ꎌ ꎍ ꎎ ꎏ ꎐ ꎑ ꎒ ꎓ ꎔ ꎕ ꎖ ꎗ ꎘ ꎙ ꎚ ꎛ ꎜ ꎝ ꎞ ꎟ ꎠ ꎡ
                       //  ꎢ ꎣ ꎤ ꎥ ꎦ ꎧ ꎨ ꎩ ꎪ ꎫ ꎬ ꎭ ꎮ ꎯ ꎰ ꎱ ꎲ ꎳ ꎴ ꎵ ꎶ ꎷ ꎸ ꎹ ꎺ ꎻ ꎼ ꎽ ꎾ ꎿ
                       //  ꏀ ꏁ ꏂ ꏃ ꏄ ꏅ ꏆ ꏇ ꏈ ꏉ ꏊ ꏋ ꏌ ꏍ ꏎ ꏏ ꏐ ꏑ ꏒ ꏓ ꏔ ꏕ ꏖ ꏗ ꏘ ꏙ ꏚ ꏛ ꏜ ꏝ
                       //  ꏞ ꏟ ꏠ ꏡ ꏢ ꏣ ꏤ ꏥ ꏦ ꏧ ꏨ ꏩ ꏪ ꏫ ꏬ ꏭ ꏮ ꏯ ꏰ ꏱ ꏲ ꏳ ꏴ ꏵ ꏶ ꏷ ꏸ ꏹ ꏺ ꏻ
                       //  ꏼ ꏽ ꏾ ꏿ ꐀ ꐁ ꐂ ꐃ ꐄ ꐅ ꐆ ꐇ ꐈ ꐉ ꐊ ꐋ ꐌ ꐍ ꐎ ꐏ ꐐ ꐑ ꐒ ꐓ ꐔ ꐕ ꐖ ꐗ ꐘ ꐙ
                       //  ꐚ ꐛ ꐜ ꐝ ꐞ ꐟ ꐠ ꐡ ꐢ ꐣ ꐤ ꐥ ꐦ ꐧ ꐨ ꐩ ꐪ ꐫ ꐬ ꐭ ꐮ ꐯ ꐰ ꐱ ꐲ ꐳ ꐴ ꐵ ꐶ ꐷ
                       //  ꐸ ꐹ ꐺ ꐻ ꐼ ꐽ ꐾ ꐿ ꑀ ꑁ ꑂ ꑃ ꑄ ꑅ ꑆ ꑇ ꑈ ꑉ ꑊ ꑋ ꑌ ꑍ ꑎ ꑏ ꑐ ꑑ ꑒ ꑓ ꑔ ꑕ
                       //  ꑖ ꑗ ꑘ ꑙ ꑚ ꑛ ꑜ ꑝ ꑞ ꑟ ꑠ ꑡ ꑢ ꑣ ꑤ ꑥ ꑦ ꑧ ꑨ ꑩ ꑪ ꑫ ꑬ ꑭ ꑮ ꑯ ꑰ ꑱ ꑲ ꑳ
                       //  ꑴ ꑵ ꑶ ꑷ ꑸ ꑹ ꑺ ꑻ ꑼ ꑽ ꑾ ꑿ ꒀ ꒁ ꒂ ꒃ ꒄ ꒅ ꒆ ꒇ ꒈ ꒉ ꒊ ꒋ ꒌ
      },
      1, 1165);


  /**
   * <p>The auxiliary characters for the Sichuan Yi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the auxiliary characters
   *    are inherited from the superclass.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

      new int[]{
          0xa490_a4c6, //  ꒐ ꒑ ꒒ ꒓ ꒔ ꒕ ꒖ ꒗ ꒘ ꒙ ꒚ ꒛ ꒜ ꒝ ꒞ ꒟ ꒠ ꒡ ꒢ ꒣ ꒤ ꒥ ꒦ ꒧ ꒨ ꒩ ꒪ ꒫ ꒬ ꒭
                       //  ꒮ ꒯ ꒰ ꒱ ꒲ ꒳ ꒴ ꒵ ꒶ ꒷ ꒸ ꒹ ꒺ ꒻ ꒼ ꒽ ꒾ ꒿ ꓀ ꓁ ꓂ ꓃ ꓄ ꓅ ꓆
      },
      1, 55);


  /**
   * <p>The punctuation characters for the Sichuan Yi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.optimalHashedRangedSubset(

      // Optimised hashing has one less level of indirection.
      // Hash-buckets contain 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {

        0x0000, 0xffff, 0x00fe, 0x00ff, 0x0020, 0xffff, 0x0030  },

      // Optimised hashing has one less level of indirection.
      //
      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {

        { // 0x0000__ codePoint ranges
          0x21_21, 0x23_23, 0x25_26, 0x28_2a, 0x2c_2f, 0x3a_3b, 0x3f_40, 0x5b_5d,  // ! # % & ( ) * , - . / : ; ? @ [ \ ]
          0x5f_5f, 0x7b_7b, 0x7d_7d, 0xa7_a7, 0xb7_b7 },                           // _ { } § ·
          null,
        { // 0x00fe__ codePoint ranges
          0x30_31, 0x33_44, 0x49_52, 0x54_57, 0x59_61, 0x63_63, 0x68_68, 0x6a_6b },// ︰ ︱ ︳ ︴ ︵ ︶ ︷ ︸ ︹ ︺ ︻ ︼ ︽ ︾ ︿ ﹀ ﹁ ﹂ ﹃ ﹄
                                                                                   // ﹉ ﹊ ﹋ ﹌ ﹍ ﹎ ﹏ ﹐ ﹑ ﹒ ﹔ ﹕ ﹖ ﹗ ﹙ ﹚ ﹛ ﹜ ﹝ ﹞
                                                                                   // ﹟ ﹠ ﹡ ﹣ ﹨ ﹪ ﹫
        { // 0x00ff__ codePoint ranges
          0x01_03, 0x05_0a, 0x0c_0f, 0x1a_1b, 0x1f_20, 0x3b_3d, 0x3f_3f, 0x5b_5b,  // ！ ＂ ＃ ％ ＆ ＇ （ ） ＊ ， － ． ／ ： ； ？ ＠ ［ ＼ ］
                                                                                   // ＿ ｛
          0x5d_5d },                                                               // ｝
        { // 0x0020__ codePoint ranges
          0x10_11, 0x13_16, 0x18_19, 0x1c_1d, 0x25_26, 0x30_30, 0x32_33, 0x35_35,  // ‐ ‑ – — ― ‖ ‘ ’ “ ” ‥ … ‰ ′ ″ ‵
          0x3b_3b },                                                               // ※
          null,
        { // 0x0030__ codePoint ranges
          0x01_03, 0x08_11, 0x14_17, 0x1d_1e } },                                  // 、 。 〃 〈 〉 《 》 「 」 『 』 【 】 〔 〕 〖 〗 〝 〞
        // number of code-point ranges
        43,
        // number of code-points
        129);


  /**
   * <p>The decimal digit characters for the Sichuan Yi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = Factory.rangedSubset(

      new char[]{
          0x30_39, //  0 1 2 3 4 5 6 7 8 9
      },
      1, 10);


}
