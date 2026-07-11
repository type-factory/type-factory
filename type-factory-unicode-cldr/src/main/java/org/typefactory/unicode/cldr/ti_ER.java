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
 * Provides Type Factory subsets for the Tigrinya (Eritrea) as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = """
        This file for the Tigrinya (Eritrea) language is generated from the
        Unicode Common Locale Data Repository (CLDR) datasets.""",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public class ti_ER extends ti {

  public ti_ER() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET,
        DECIMAL_DIGITS_SUBSET);
  }

  protected ti_ER(
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
   * <p>The Locale represented by this resource bundle for the Tigrinya (Eritrea) language.</p>
   *
   * <p>Language tag: {@code "ti-ER"}</p>
   */
  static final Locale LOCALE = new Locale.Builder()
          .setLanguage("ti")
          .setScript("")
          .setRegion("ER")
          .setVariant("")
          .build();

  /**
   * <p>The standard characters for the Tigrinya (Eritrea) language as defined by the
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
          0x1200_1206, //  ሀ ሁ ሂ ሃ ሄ ህ ሆ
          0x1208_121f, //  ለ ሉ ሊ ላ ሌ ል ሎ ሏ ሐ ሑ ሒ ሓ ሔ ሕ ሖ ሗ መ ሙ ሚ ማ ሜ ም ሞ ሟ
          0x1228_1246, //  ረ ሩ ሪ ራ ሬ ር ሮ ሯ ሰ ሱ ሲ ሳ ሴ ስ ሶ ሷ ሸ ሹ ሺ ሻ ሼ ሽ ሾ ሿ ቀ ቁ ቂ ቃ ቄ ቅ ቆ
          0x1248_1248, //  ቈ
          0x124a_124d, //  ቊ ቋ ቌ ቍ
          0x1250_1256, //  ቐ ቑ ቒ ቓ ቔ ቕ ቖ
          0x1258_1258, //  ቘ
          0x125a_125d, //  ቚ ቛ ቜ ቝ
          0x1260_1286, //  በ ቡ ቢ ባ ቤ ብ ቦ ቧ ቨ ቩ ቪ ቫ ቬ ቭ ቮ ቯ ተ ቱ ቲ ታ ቴ ት ቶ ቷ ቸ ቹ ቺ ቻ ቼ ች
                       //  ቾ ቿ ኀ ኁ ኂ ኃ ኄ ኅ ኆ
          0x1288_1288, //  ኈ
          0x128a_128d, //  ኊ ኋ ኌ ኍ
          0x1290_12ae, //  ነ ኑ ኒ ና ኔ ን ኖ ኗ ኘ ኙ ኚ ኛ ኜ ኝ ኞ ኟ አ ኡ ኢ ኣ ኤ እ ኦ ኧ ከ ኩ ኪ ካ ኬ ክ ኮ
          0x12b0_12b0, //  ኰ
          0x12b2_12b5, //  ኲ ኳ ኴ ኵ
          0x12b8_12be, //  ኸ ኹ ኺ ኻ ኼ ኽ ኾ
          0x12c0_12c0, //  ዀ
          0x12c2_12c5, //  ዂ ዃ ዄ ዅ
          0x12c8_12ce, //  ወ ዉ ዊ ዋ ዌ ው ዎ
          0x12d0_12d6, //  ዐ ዑ ዒ ዓ ዔ ዕ ዖ
          0x12d8_12ee, //  ዘ ዙ ዚ ዛ ዜ ዝ ዞ ዟ ዠ ዡ ዢ ዣ ዤ ዥ ዦ ዧ የ ዩ ዪ ያ ዬ ይ ዮ
          0x12f0_12f7, //  ደ ዱ ዲ ዳ ዴ ድ ዶ ዷ
          0x1300_130e, //  ጀ ጁ ጂ ጃ ጄ ጅ ጆ ጇ ገ ጉ ጊ ጋ ጌ ግ ጎ
          0x1310_1310, //  ጐ
          0x1312_1315, //  ጒ ጓ ጔ ጕ
          0x1320_132f, //  ጠ ጡ ጢ ጣ ጤ ጥ ጦ ጧ ጨ ጩ ጪ ጫ ጬ ጭ ጮ ጯ
          0x1338_133f, //  ጸ ጹ ጺ ጻ ጼ ጽ ጾ ጿ
          0x1348_1357, //  ፈ ፉ ፊ ፋ ፌ ፍ ፎ ፏ ፐ ፑ ፒ ፓ ፔ ፕ ፖ ፗ
          0x135f_137c, //  ፟ ፠ ፡ ። ፣ ፤ ፥ ፦ ፧ ፨ ፩ ፪ ፫ ፬ ፭ ፮ ፯ ፰ ፱ ፲ ፳ ፴ ፵ ፶ ፷ ፸ ፹ ፺ ፻ ፼
      },
      28, 306);


  /**
   * <p>The auxiliary characters for the Tigrinya (Eritrea) language as defined by the
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
          0x1207_1207, //  ሇ
          0x1220_1227, //  ሠ ሡ ሢ ሣ ሤ ሥ ሦ ሧ
          0x1247_1247, //  ቇ
          0x1287_1287, //  ኇ
          0x12af_12af, //  ኯ
          0x12cf_12cf, //  ዏ
          0x12ef_12ef, //  ዯ
          0x12f8_12ff, //  ዸ ዹ ዺ ዻ ዼ ዽ ዾ ዿ
          0x130f_130f, //  ጏ
          0x1318_131f, //  ጘ ጙ ጚ ጛ ጜ ጝ ጞ ጟ
          0x1340_1347, //  ፀ ፁ ፂ ፃ ፄ ፅ ፆ ፇ
          0x1358_135a, //  ፘ ፙ ፚ
          0x1380_1399, //  ᎀ ᎁ ᎂ ᎃ ᎄ ᎅ ᎆ ᎇ ᎈ ᎉ ᎊ ᎋ ᎌ ᎍ ᎎ ᎏ ᎐ ᎑ ᎒ ᎓ ᎔ ᎕ ᎖ ᎗ ᎘ ᎙
          0x2d80_2d96, //  ⶀ ⶁ ⶂ ⶃ ⶄ ⶅ ⶆ ⶇ ⶈ ⶉ ⶊ ⶋ ⶌ ⶍ ⶎ ⶏ ⶐ ⶑ ⶒ ⶓ ⶔ ⶕ ⶖ
          0x2da0_2da6, //  ⶠ ⶡ ⶢ ⶣ ⶤ ⶥ ⶦ
          0x2da8_2dae, //  ⶨ ⶩ ⶪ ⶫ ⶬ ⶭ ⶮ
          0x2db0_2db6, //  ⶰ ⶱ ⶲ ⶳ ⶴ ⶵ ⶶ
          0x2db8_2dbe, //  ⶸ ⶹ ⶺ ⶻ ⶼ ⶽ ⶾ
          0x2dc0_2dc6, //  ⷀ ⷁ ⷂ ⷃ ⷄ ⷅ ⷆ
          0x2dc8_2dce, //  ⷈ ⷉ ⷊ ⷋ ⷌ ⷍ ⷎ
          0x2dd0_2dd6, //  ⷐ ⷑ ⷒ ⷓ ⷔ ⷕ ⷖ
          0x2dd8_2dde, //  ⷘ ⷙ ⷚ ⷛ ⷜ ⷝ ⷞ
      },
      22, 147);


  /**
   * <p>The punctuation characters for the Tigrinya (Eritrea) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the punctuation characters
   *    are inherited from the superclass.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = null;


  /**
   * <p>The decimal digit characters for the Tigrinya (Eritrea) language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the decimal digit characters from the {@code <exemplarCharacters type="numbers">}
   *    element in the CLDR dataset.</p>
   *
   * <p>A {@code null} value indicates that the decimal digit characters
   *    are inherited from the superclass.</p>
   */
  static final Subset DECIMAL_DIGITS_SUBSET = null;


}
