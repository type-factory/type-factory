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

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

/**
 * Provides Type Factory subsets for the Punjabi language as defined
 * by the Unicode Common Locale Data Repository (CLDR).
 */
@Generated(
    comments = "This file is generated from the Unicode Common Locale Data Repository (CLDR) datasets.",
    value = "org.typefactory:type-factory-unicode-cldr-code-generator")
public final class pa extends AbstractCldrResourceBundle {

  public pa() {
    super(
        STANDARD_CHARACTERS_SUBSET,
        AUXILIARY_CHARACTERS_SUBSET,
        PUNCTUATION_CHARACTERS_SUBSET);
  }

  /**
   * <p>The standard characters for the Punjabi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters>}
   *    element in the CLDR dataset.</p>
   */
  static final Subset STANDARD_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0a05_0a0a, //  ਅ ਆ ਇ ਈ ਉ ਊ
          0x0a0f_0a10, //  ਏ ਐ
          0x0a13_0a28, //  ਓ ਔ ਕ ਖ ਗ ਘ ਙ ਚ ਛ ਜ ਝ ਞ ਟ ਠ ਡ ਢ ਣ ਤ ਥ ਦ ਧ ਨ
          0x0a2a_0a30, //  ਪ ਫ ਬ ਭ ਮ ਯ ਰ
          0x0a32_0a32, //  ਲ
          0x0a35_0a35, //  ਵ
          0x0a38_0a39, //  ਸ ਹ
          0x0a3c_0a3c, //  ਼
          0x0a3e_0a42, //  ਾ ਿ ੀ ੁ ੂ
          0x0a47_0a48, //  ੇ ੈ
          0x0a4b_0a4d, //  ੋ ੌ ੍
          0x0a5c_0a5c, //  ੜ
          0x0a66_0a74, //  ੦ ੧ ੨ ੩ ੪ ੫ ੬ ੭ ੮ ੯ ੰ ੱ ੲ ੳ ੴ
      },
      13, 68);


  /**
   * <p>The auxiliary characters for the Punjabi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="auxiliary">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset AUXILIARY_CHARACTERS_SUBSET = Factory.rangedSubset(

        new int[]{
          0x0a02_0a03, //  ਂ ਃ
          0x0a32_0a32, //  ਲ
          0x0a3c_0a3c, //  ਼
          0x200c_200d, //  ‌ ‍
      },
      4, 6);


  /**
   * <p>The punctuation characters for the Punjabi language as defined by the
   *    Unicode Common Locale Data Repository (CLDR).</p>
   *
   * <p>These are the characters in the {@code <exemplarCharacters type="punctuation">}
   *    element in the CLDR dataset.</p>
   */
  static final Subset PUNCTUATION_CHARACTERS_SUBSET = Factory.rangedSubset(

        new char[]{
          0x21_22, //  ! "
          0x26_29, //  & ' ( )
          0x2c_2f, //  , - . /
          0x3a_3b, //  : ;
          0x3f_3f, //  ?
          0x5b_5b, //  [
          0x5d_5d, //  ]
      },
        new int[]{
          0x2010_2011, //  ‐ ‑
          0x2013_2014, //  – —
          0x2018_2019, //  ‘ ’
          0x201c_201d, //  “ ”
          0x2032_2033, //  ′ ″
      },
      12, 25);


}
