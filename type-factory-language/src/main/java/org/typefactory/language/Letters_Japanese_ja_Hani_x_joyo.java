package org.typefactory.language;

import javax.annotation.processing.Generated;
import org.typefactory.Subset;
import org.typefactory.impl.Factory;

@Generated(
    comments = "This file is generated from data in the LanguageData class in the type-factory-language-code-generator module.",
    value = "org.typefactory:type-factory-language-code-generator")
class Letters_Japanese_ja_Hani_x_joyo {

  static final Subset SUBSET = Factory.optimalHashedRangedSubset(

      // Hash-buckets with 0..1 keys – 0xffff indicates an empty hash-bucket.
      //
      //       ┌─ hashIndex - an index to the hash-bucket which has at most one key
      //       │
      //  char[ ] blockKeys
      new char[ ] {
        0x005a, 0x005b, 0x005c, 0x005d, 0x005e, 0x005f, 0x0060, 0x0061, 
        0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067, 0x0068, 0x0069, 
        0x006a, 0x006b, 0x006c, 0x006d, 0x006e, 0x006f, 0x0070, 0x0071, 
        0x0072, 0x0073, 0x0074, 0x0075, 0x0076, 0x0077, 0x0078, 0x0079, 
        0x007a, 0x007b, 0x007c, 0x007d, 0x007e, 0x007f, 0x0080, 0x0081, 
        0x0082, 0x0083, 0x0084, 0x0085, 0x0086, 0x0087, 0x0088, 0x0089, 
        0x008a, 0x008b, 0x008c, 0x008d, 0x008e, 0x008f, 0x0090, 0x0091, 
        0x0092, 0x0093, 0x0094, 0x0095, 0x0096, 0x0097, 0x0098, 0x0099, 
        0x009a, 0x009b, 0x009c, 0x009d, 0x009e, 0x009f, 0xffff, 0xffff, 
        0xffff, 0x020b, 0xffff, 0xffff, 0xffff, 0xffff, 0x004e, 0x004f, 
        0x0050, 0x0051, 0x0052, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 
        0x0058, 0x0059  },

      //       ┌──── hashIndex           - an index to the hash-bucket
      //       │  ┌─ codePointRangeIndex - an index to the range within the array of ranges
      //       │  │
      //  char[ ][ ] codePointRanges
      new char[ ][ ] {
        { // 0x005a__ codePoint ranges
          0x01_01, 0x18_18, 0x20_20, 0x2f_2f, 0x46_46, 0x5a_5a, 0x66_66, 0x7f_7f, 
          0x92_92, 0x9b_9b, 0xc1_c1, 0xc9_c9, 0xcc_cc, 0xe1_e1},
        { // 0x005b__ codePoint ranges
          0x22_22, 0x50_50, 0x54_54, 0x57_58, 0x5d_5d, 0x63_64, 0x66_66, 0x6b_6b, 
          0x85_85, 0x87_89, 0x8c_8c, 0x97_9d, 0x9f_9f, 0xa2_a4, 0xae_ae, 0xb0_b0, 
          0xb3_b6, 0xb9_b9, 0xbf_bf, 0xc2_c2, 0xc4_c4, 0xc6_c6, 0xcc_cc, 0xd2_d2, 
          0xdb_db, 0xdd_dd, 0xdf_df, 0xe1_e1, 0xe7_e7, 0xe9_e9, 0xee_ee, 0xf8_f8, 
          0xfa_fa, 0xfe_ff},
        { // 0x005c__ codePoint ranges
          0x01_02, 0x04_04, 0x06_06, 0x09_0b, 0x0e_0f, 0x11_11, 0x1a_1a, 0x31_31, 
          0x3a_40, 0x45_45, 0x48_48, 0x4a_4b, 0x55_55, 0x5e_5e, 0x64_65, 0x6f_6f, 
          0x71_71, 0x90_90, 0xa1_a1, 0xa9_a9, 0xac_ac, 0xb3_b3, 0xb8_b8, 0xe0_e1, 
          0xf0_f0, 0xf6_f6},
        { // 0x005d__ codePoint ranges
          0x07_07, 0x0e_0e, 0x16_16, 0x29_29, 0x50_50, 0xdd_de, 0xe1_e1, 0xe3_e3, 
          0xe5_e8, 0xee_ee, 0xf1_f1, 0xfb_fb, 0xfe_fe},
        { // 0x005e__ codePoint ranges
          0x02_03, 0x06_06, 0x0c_0c, 0x1d_1d, 0x25_25, 0x2b_2b, 0x2d_2d, 0x2f_30, 
          0x33_33, 0x38_38, 0x3d_3d, 0x45_45, 0x55_55, 0x63_63, 0x72_74, 0x78_79, 
          0x7b_7e, 0x81_81, 0x83_83, 0x8a_8a, 0x8f_8f, 0x95_95, 0x97_97, 0x9c_9c, 
          0xa6_a7, 0xab_ab, 0xad_ad, 0xb6_b8, 0xc3_c3, 0xc9_ca, 0xf6_f7, 0xfa_fa},
        { // 0x005f__ codePoint ranges
          0x01_01, 0x04_04, 0x0a_0a, 0x0f_10, 0x13_15, 0x1f_1f, 0x25_27, 0x31_31, 
          0x35_35, 0x37_37, 0x3e_3e, 0x53_53, 0x59_59, 0x62_62, 0x69_69, 0x6b_6b, 
          0x70_71, 0x79_79, 0x7c_7c, 0x80_81, 0x84_85, 0x8b_8c, 0x90_90, 0x92_93, 
          0x97_97, 0xa1_a1, 0xa9_aa, 0xae_ae, 0xb3_b4, 0xb9_b9, 0xc3_c3, 0xc5_c5, 
          0xcc_cd, 0xd7_d9, 0xdc_dc, 0xe0_e0, 0xeb_eb, 0xf5_f5},
        { // 0x0060__ codePoint ranges
          0x12_12, 0x16_16, 0x1d_1d, 0x20_20, 0x25_25, 0x27_28, 0x2a_2a, 0x4b_4b, 
          0x50_50, 0x52_52, 0x63_63, 0x65_65, 0x68_69, 0x6d_6d, 0x6f_6f, 0x75_75, 
          0x94_94, 0x9f_a0, 0xa3_a3, 0xa6_a6, 0xa9_aa, 0xb2_b2, 0xbc_bc, 0xc5_c5, 
          0xd1_d1, 0xdc_dc, 0xe7_e8, 0xf0_f0, 0xf3_f3},
        { // 0x0061__ codePoint ranges
          0x01_01, 0x09_09, 0x0f_0f, 0x1a_1b, 0x1f_1f, 0x44_44, 0x48_48, 0x4b_4c, 
          0x4e_4e, 0x55_55, 0x62_63, 0x68_68, 0x6e_6e, 0x70_70, 0x76_76, 0x82_82, 
          0x8e_8e, 0xa4_a4, 0xa7_a7, 0xa9_a9, 0xac_ac, 0xb2_b2, 0xb6_b6, 0xbe_be, 
          0xc7_c7, 0xd0_d0, 0xf2_f2, 0xf8_f8},
        { // 0x0062__ codePoint ranges
          0x10_12, 0x1a_1a, 0x26_26, 0x2f_2f, 0x34_34, 0x38_38, 0x3b_3b, 0x3f_40, 
          0x47_47, 0x49_49, 0x4b_4b, 0x4d_4d, 0x53_53, 0x55_55, 0x71_71, 0x76_76, 
          0x79_79, 0x7f_80, 0x84_84, 0x8a_8a, 0x91_91, 0x95_95, 0x97_98, 0x9c_9c, 
          0x9e_9e, 0xab_ab, 0xb1_b1, 0xb5_b5, 0xb9_b9, 0xbc_bd, 0xc5_c5, 0xc9_c9, 
          0xcd_cd, 0xd0_d0, 0xd2_d3, 0xd8_d9, 0xdb_db, 0xdd_dd, 0xe0_e1, 0xec_ed, 
          0xf3_f3, 0xf6_f7, 0xfe_fe},
        { // 0x0063__ codePoint ranges
          0x01_01, 0x07_07, 0x11_11, 0x19_19, 0x1f_1f, 0x28_28, 0x2b_2b, 0x2f_2f, 
          0x3f_3f, 0x49_49, 0x55_55, 0x57_57, 0x5c_5c, 0x68_68, 0x6e_6e, 0x7b_7b, 
          0x83_83, 0x88_88, 0x8c_8c, 0x92_92, 0x98_98, 0x9b_9b, 0xa1_a2, 0xa5_a5, 
          0xa7_a8, 0xaa_aa, 0xb2_b2, 0xcf_d0, 0xda_db, 0xe1_e1, 0xee_ee, 0xf4_f4, 
          0xfa_fa},
        { // 0x0064__ codePoint ranges
          0x0d_0d, 0x2c_2d, 0x3a_3a, 0x3e_3e, 0x42_42, 0x58_58, 0x69_69, 0x6f_6f, 
          0x83_83, 0xa4_a4, 0xae_ae, 0xb2_b2, 0xc1_c1, 0xcd_cd, 0xe6_e6, 0xec_ec},
        { // 0x0065__ codePoint ranges
          0x2f_2f, 0x39_39, 0x3b_3b, 0x3e_3f, 0x45_45, 0x4f_4f, 0x51_51, 0x57_57, 
          0x59_59, 0x62_63, 0x6c_6c, 0x70_70, 0x74_75, 0x77_77, 0x87_87, 0x89_89, 
          0x8e_8e, 0x91_91, 0x97_97, 0x99_99, 0x9c_9c, 0xa4_a5, 0xac_ad, 0xb0_b0, 
          0xb9_b9, 0xbd_bd, 0xc5_c5, 0xcb_cb, 0xcf_cf, 0xd7_d7, 0xe2_e2, 0xe5_e9, 
          0xec_ec, 0xfa_fa},
        { // 0x0066__ codePoint ranges
          0x06_07, 0x0e_0e, 0x13_14, 0x1f_20, 0x25_25, 0x27_28, 0x2d_2d, 0x2f_2f, 
          0x3c_3c, 0x42_42, 0x69_69, 0x6e_6f, 0x74_74, 0x76_76, 0x81_81, 0x87_87, 
          0x91_91, 0x96_97, 0xa6_a6, 0xab_ab, 0xae_ae, 0xb4_b4, 0xc7_c7, 0xd6_d6, 
          0xdc_dc, 0xf2_f2, 0xf4_f4, 0xf8_f9, 0xfd_fd, 0xff_ff},
        { // 0x0067__ codePoint ranges
          0x00_00, 0x08_09, 0x0d_0d, 0x15_15, 0x17_17, 0x1b_1b, 0x1d_1d, 0x1f_1f, 
          0x28_28, 0x2a_2d, 0x31_31, 0x34_34, 0x3a_3a, 0x3d_3d, 0x49_49, 0x50_51, 
          0x5f_5f, 0x61_61, 0x65_65, 0x6f_6f, 0x71_71, 0x7e_7f, 0x90_90, 0x95_95, 
          0x97_97, 0x9a_9a, 0x9c_9d, 0xa0_a0, 0xa2_a2, 0xaf_af, 0xb6_b6, 0xc4_c4, 
          0xd0_d0, 0xd3_d4, 0xf1_f1, 0xf3_f3, 0xf5_f5, 0xfb_fb, 0xff_ff},
        { // 0x0068__ codePoint ranges
          0x03_04, 0x13_13, 0x21_21, 0x2a_2a, 0x38_39, 0x3c_3d, 0x41_41, 0x43_43, 
          0x48_48, 0x51_51, 0x5c_5c, 0x5f_5f, 0x85_85, 0x97_97, 0xa8_a8, 0xb0_b0, 
          0xc4_c4, 0xcb_cb, 0xd2_d2, 0xda_da, 0xdf_df, 0xee_ee, 0xfa_fa},
        { // 0x0069__ codePoint ranges
          0x05_05, 0x0d_0e, 0x1c_1c, 0x6d_6d, 0x75_75, 0x77_77, 0x7c_7d, 0x82_82, 
          0xcb_cb, 0xd8_d8, 0xfd_fd},
        { // 0x006a__ codePoint ranges
          0x19_19, 0x21_21, 0x29_2a, 0x39_39, 0x4b_4b, 0x5f_5f},
        { // 0x006b__ codePoint ranges
          0x04_04, 0x20_21, 0x27_27, 0x32_32, 0x3a_3a, 0x3e_3e, 0x4c_4c, 0x53_53, 
          0x62_63, 0x66_66, 0x69_69, 0x6f_6f, 0x73_74, 0x7b_7b, 0x89_8b, 0x96_96, 
          0xb4_b5, 0xba_bb, 0xbf_c0, 0xcd_ce, 0xd2_d2, 0xd4_d4, 0xdb_db},
        { // 0x006c__ codePoint ranges
          0x0f_0f, 0x11_11, 0x17_17, 0x34_34, 0x37_38, 0x3e_3e, 0x41_42, 0x4e_4e, 
          0x57_57, 0x5a_5a, 0x5f_60, 0x70_70, 0x7a_7a, 0x7d_7d, 0x83_83, 0x88_88, 
          0x96_96, 0x99_99, 0xa1_a2, 0xb3_b3, 0xb8_b9, 0xbb_bc, 0xbf_bf, 0xc1_c1, 
          0xc9_ca, 0xcc_cc, 0xd5_d5, 0xe1_e3, 0xe5_e5, 0xe8_e8, 0xf0_f0, 0xf3_f3},
        { // 0x006d__ codePoint ranges
          0x0b_0b, 0x17_17, 0x1e_1e, 0x25_25, 0x2a_2a, 0x3b_3b, 0x3e_3e, 0x41_41, 
          0x44_45, 0x5c_5c, 0x66_66, 0x6a_6a, 0x6e_6e, 0x74_74, 0x77_78, 0x88_88, 
          0x99_99, 0xaf_af, 0xb2_b2, 0xbc_bc, 0xd1_d1, 0xe1_e1, 0xeb_eb, 0xf1_f1, 
          0xf7_f7, 0xfb_fb},
        { // 0x006e__ codePoint ranges
          0x05_05, 0x07_09, 0x0b_0b, 0x13_13, 0x1b_1b, 0x21_21, 0x26_26, 0x29_29, 
          0x2c_2c, 0x2f_2f, 0x56_56, 0x67_67, 0x6f_6f, 0x7e_80, 0x90_90, 0x96_96, 
          0x9d_9d, 0xb6_b6, 0xba_ba, 0xc5_c5, 0xcb_cb, 0xd1_d1, 0xdd_de, 0xf4_f4},
        { // 0x006f__ codePoint ranges
          0x01_02, 0x06_06, 0x0f_0f, 0x14_14, 0x20_20, 0x22_22, 0x2b_2c, 0x38_38, 
          0x54_54, 0x5c_5c, 0x5f_5f, 0x64_64, 0x6e_6e, 0x70_70, 0x84_84, 0xc0_c1, 
          0xc3_c3, 0xeb_eb, 0xef_ef},
        { // 0x0070__ codePoint ranges
          0x2c_2c, 0x6b_6b, 0x6f_70, 0x7d_7d, 0x89_8a, 0x8e_8e, 0xad_ad, 0xb9_ba, 
          0xc8_c8},
        { // 0x0071__ codePoint ranges
          0x21_21, 0x26_26, 0x36_36, 0x3c_3c, 0x4e_4e, 0x59_59, 0x67_67, 0x69_69, 
          0x6e_6e, 0x8a_8a, 0x9f_9f, 0xb1_b1, 0xc3_c3, 0xe5_e5},
        { // 0x0072__ codePoint ranges
          0x06_06, 0x2a_2a, 0x35_36, 0x3d_3d, 0x47_48, 0x59_59, 0x5b_5b, 0x67_67, 
          0x69_69, 0x72_72, 0x79_79, 0xa0_a0, 0xac_ac, 0xaf_af, 0xb6_b6, 0xc2_c2, 
          0xd9_d9, 0xe9_e9, 0xec_ed},
        { // 0x0073__ codePoint ranges
          0x1b_1b, 0x1f_1f, 0x2b_2b, 0x2e_2e, 0x36_36, 0x3f_3f, 0x44_44, 0x63_63, 
          0x72_72, 0x84_84, 0x87_87, 0x89_89, 0x8b_8b, 0xa9_a9, 0xcd_cd, 0xe0_e0, 
          0xed_ed, 0xfe_fe},
        { // 0x0074__ codePoint ranges
          0x03_03, 0x06_06, 0x34_34, 0x60_60, 0x83_83, 0xa7_a7, 0xb0_b0, 0xbd_bd, 
          0xe6_e6, 0xf6_f6},
        { // 0x0075__ codePoint ranges
          0x18_18, 0x1a_1a, 0x1f_1f, 0x23_23, 0x28_28, 0x30_33, 0x37_37, 0x3a_3b, 
          0x4c_4c, 0x4f_4f, 0x51_51, 0x54_54, 0x59_59, 0x5c_5d, 0x65_65, 0x6a_6a, 
          0x70_70, 0x73_73, 0x7f_7f, 0x8e_8e, 0x91_91, 0xab_ab, 0xb2_b2, 0xbe_be, 
          0xc5_c5, 0xc7_c7, 0xd5_d5, 0xd8_d8, 0xdb_db, 0xe2_e2, 0xe9_e9, 0xf4_f4},
        { // 0x0076__ codePoint ranges
          0x0d_0d, 0x42_42, 0x52_52, 0x56_56, 0x7a_7b, 0x7d_7e, 0x84_84, 0x86_87, 
          0xae_ae, 0xbf_bf, 0xc6_c6, 0xca_ca, 0xd7_d7, 0xdb_db, 0xdf_df, 0xe3_e4, 
          0xee_ee, 0xf2_f2, 0xf4_f4, 0xf8_f8, 0xfe_fe},
        { // 0x0077__ codePoint ranges
          0x01_01, 0x09_09, 0x0b_0c, 0x1f_20, 0x3a_3a, 0x3c_3c, 0x40_40, 0x61_61, 
          0x63_63, 0x66_66, 0xac_ad, 0xb3_b3, 0xdb_db, 0xe2_e2, 0xe5_e5, 0xed_ed, 
          0xef_ef, 0xf3_f3},
        { // 0x0078__ codePoint ranges
          0x02_02, 0x14_15, 0x32_32, 0x34_34, 0x5d_5d, 0x6b_6c, 0x81_81, 0x91_91, 
          0xba_ba, 0xc1_c1, 0xe8_e8},
        { // 0x0079__ codePoint ranges
          0x01_01, 0x0e_0e, 0x3a_3a, 0x3c_3c, 0x3e_3e, 0x48_49, 0x56_56, 0x5d_5e, 
          0x65_65, 0x68_68, 0x6d_6d, 0x81_81, 0x85_85, 0x8d_8d, 0x8f_8f, 0xc0_c1, 
          0xcb_cb, 0xd1_d2, 0xd8_d8, 0xdf_df, 0xe9_e9, 0xf0_f0, 0xfb_fb},
        { // 0x007a__ codePoint ranges
          0x0b_0b, 0x0e_0e, 0x1a_1a, 0x2e_2e, 0x32_32, 0x3c_3d, 0x3f_40, 0x42_42, 
          0x4d_4d, 0x4f_4f, 0x6b_6b, 0x74_74, 0x76_76, 0x7a_7a, 0x81_81, 0x83_83, 
          0x92_93, 0x9f_9f, 0xae_af, 0xcb_cb, 0xdc_dc, 0xe0_e0, 0xe5_e5, 0xef_ef, 
          0xf6_f6, 0xf9_f9},
        { // 0x007b__ codePoint ranges
          0x11_11, 0x1b_1b, 0x26_26, 0x2c_2c, 0x46_46, 0x49_49, 0x4b_4b, 0x52_52, 
          0x54_54, 0x56_56, 0x87_87, 0x8b_8b, 0x97_97, 0xa1_a1, 0xb1_b1, 0xb8_b8, 
          0xc0_c0, 0xc4_c4, 0xc9_c9, 0xe4_e4},
        { // 0x007c__ codePoint ranges
          0x21_21, 0x3f_3f, 0x4d_4d, 0x60_60, 0x73_73, 0x89_89, 0x8b_8b, 0x92_92, 
          0x97_98, 0x9b_9b, 0xa7_a7, 0xbe_be, 0xd6_d6, 0xe7_e7, 0xf8_f8, 0xfb_fb, 
          0xfe_fe},
        { // 0x007d__ codePoint ranges
          0x00_00, 0x04_05, 0x0b_0b, 0x0d_0d, 0x14_14, 0x19_1b, 0x20_22, 0x2b_2b, 
          0x2f_30, 0x33_33, 0x39_3a, 0x42_42, 0x44_44, 0x4c_4c, 0x50_50, 0x5e_5e, 
          0x61_61, 0x66_66, 0x71_71, 0x75_76, 0x79_79, 0x99_9a, 0xad_ad, 0xb1_b2, 
          0xbb_bb, 0xbf_bf, 0xca_ca, 0xcf_cf, 0xd1_d2, 0xda_da, 0xe0_e0, 0xe8_e9, 
          0xef_ef, 0xf4_f4, 0xfb_fb},
        { // 0x007e__ codePoint ranges
          0x01_01, 0x04_04, 0x1b_1b, 0x26_26, 0x2b_2b, 0x2e_2e, 0x3e_3e, 0x41_41, 
          0x4a_4a, 0x54_55, 0x6d_6d, 0x70_70},
        { // 0x007f__ codePoint ranges
          0x36_36, 0x6a_6a, 0x6e_6e, 0x70_70, 0x72_72, 0x75_75, 0x77_77, 0x85_85, 
          0x8a_8a, 0x8e_8e, 0x9e_9e, 0xa4_a4, 0xa8_a9, 0xbd_bd, 0xc1_c1, 0xcc_cc, 
          0xd2_d2, 0xfb_fc},
        { // 0x0080__ codePoint ranges
          0x01_01, 0x03_03, 0x05_05, 0x10_10, 0x15_15, 0x17_17, 0x33_33, 0x56_56, 
          0x5e_5e, 0x74_74, 0x77_77, 0x89_89, 0x8c_8c, 0x96_96, 0x98_98, 0x9d_9d, 
          0xa1_a2, 0xa5_a5, 0xa9_aa, 0xaf_af, 0xb2_b2, 0xba_ba, 0xc3_c3, 0xc6_c6, 
          0xcc_cc, 0xce_ce, 0xde_de, 0xf4_f4, 0xf8_f8, 0xfd_fd},
        { // 0x0081__ codePoint ranges
          0x02_02, 0x05_05, 0x07_08, 0x0a_0a, 0x1a_1a, 0x31_31, 0x33_33, 0x4e_4e, 
          0x50_50, 0x55_55, 0x6b_6b, 0x70_70, 0x78_7a, 0x9a_9a, 0x9c_9d, 0xa8_a8, 
          0xb3_b3, 0xc6_c6, 0xd3_d3, 0xe3_e3, 0xe8_e8, 0xea_ea, 0xed_ed, 0xf3_f4, 
          0xfc_fc},
        { // 0x0082__ codePoint ranges
          0x08_08, 0x0c_0c, 0x0e_0e, 0x17_17, 0x1e_1f, 0x2a_2a, 0x2c_2c, 0x36_37, 
          0x39_39, 0x47_47, 0x66_66, 0x6f_6f, 0x72_72, 0x76_76, 0x8b_8b, 0x9d_9d, 
          0xaf_af, 0xb1_b1, 0xb3_b3, 0xb8_b8, 0xbd_bd, 0xd7_d7, 0xdb_db, 0xe5_e6, 
          0xf1_f1},
        { // 0x0083__ codePoint ranges
          0x02_02, 0x0e_0e, 0x28_28, 0x36_36, 0x49_49, 0x52_52, 0x58_58, 0x77_77, 
          0xca_ca, 0xcc_cc, 0xd3_d3, 0xdc_dc, 0xef_ef},
        { // 0x0084__ codePoint ranges
          0x0e_0e, 0x3d_3d, 0x49_49, 0x57_57, 0x5b_5b, 0x6c_6c, 0xb8_b8, 0xc4_c4, 
          0xcb_cb},
        { // 0x0085__ codePoint ranges
          0x11_11, 0x35_35, 0x3d_3d, 0x84_84, 0xa6_a6, 0xaa_ac, 0xcd_cd, 0xe4_e4, 
          0xe9_e9, 0xfb_fb},
        { // 0x0086__ codePoint ranges
          0x4e_4e, 0x50_50, 0x5a_5a, 0x5c_5c, 0x5e_5e, 0x6b_6b, 0x79_79, 0x8a_8a, 
          0x95_95, 0xc7_c7, 0xcd_cd, 0xee_ee},
        { // 0x0087__ codePoint ranges
          0x02_02, 0x1c_1c, 0x8d_8d},
        { // 0x0088__ codePoint ranges
          0x40_40, 0x46_46, 0x4c_4c, 0x53_53, 0x57_57, 0x5b_5b, 0x5d_5d, 0x61_61, 
          0x63_63, 0x68_68, 0x70_70, 0x77_77, 0x8b_8b, 0x96_96, 0xab_ab, 0xc1_c2, 
          0xc5_c5, 0xcf_cf, 0xd5_d5, 0xdc_dc, 0xf8_f8, 0xfd_fe},
        { // 0x0089__ codePoint ranges
          0x07_07, 0x10_10, 0x12_12, 0x5f_5f, 0x72_72, 0x7f_7f, 0x81_81, 0x86_87, 
          0x8b_8b, 0x8f_8f, 0x96_96, 0x9a_9a, 0xa7_a7, 0xaa_aa, 0xb3_b3, 0xd2_d2, 
          0xe3_e3, 0xe6_e6},
        { // 0x008a__ codePoint ranges
          0x00_00, 0x02_03, 0x08_08, 0x0e_0e, 0x13_13, 0x17_18, 0x1f_1f, 0x2a_2a, 
          0x2d_2d, 0x31_31, 0x33_34, 0x3a_3a, 0x3c_3c, 0x50_50, 0x54_55, 0x5e_5e, 
          0x60_60, 0x63_63, 0x66_66, 0x69_69, 0x6e_6e, 0x70_73, 0x87_87, 0x89_89, 
          0x8c_8d, 0x93_93, 0x95_95, 0x98_98, 0x9e_9e, 0xa0_a0, 0xa4_a4, 0xac_ad, 
          0xb0_b0, 0xb2_b2, 0xbf_bf, 0xc7_c7, 0xcb_cb, 0xd6_d6, 0xe6_e7, 0xed_ee, 
          0xf8_f8, 0xfe_fe},
        { // 0x008b__ codePoint ranges
          0x00_01, 0x04_04, 0x0e_0e, 0x19_19, 0x1b_1b, 0x1d_1d, 0x21_21, 0x39_39, 
          0x58_58, 0x5c_5c, 0x66_66, 0x70_70, 0x72_72, 0x77_77},
        { // 0x008c__ codePoint ranges
          0x37_37, 0x46_46, 0x4a_4a, 0x5a_5a, 0x61_61, 0x6a_6a, 0x8c_8c, 0x9d_9e, 
          0xa0_a2, 0xa7_ac, 0xaf_af, 0xb4_b4, 0xb7_b8, 0xbb_bc, 0xbf_c0, 0xc2_c4, 
          0xc7_c7, 0xca_ca, 0xd3_d3, 0xdb_dc, 0xde_de, 0xe0_e0, 0xe2_e2, 0xe6_e6, 
          0xea_ea, 0xed_ed, 0xfc_fc},
        { // 0x008d__ codePoint ranges
          0x08_08, 0x64_64, 0x66_66, 0x70_70, 0x74_74, 0x77_77, 0x85_85, 0x8a_8a, 
          0xa3_a3, 0xb3_b3, 0xdd_dd, 0xe1_e1, 0xef_ef, 0xf3_f3, 0xf5_f5},
        { // 0x008e__ codePoint ranges
          0x0a_0a, 0x0f_0f, 0x2a_2a, 0x74_74, 0x8d_8d, 0xab_ab, 0xca_ca, 0xcc_cd, 
          0xd2_d2, 0xdf_df, 0xe2_e2, 0xf8_f8, 0xfd_fd},
        { // 0x008f__ codePoint ranges
          0x03_03, 0x09_09, 0x1d_1d, 0x29_2a, 0x38_38, 0x44_44, 0x9b_9b, 0x9e_9e, 
          0xa3_a3, 0xb1_b2, 0xba_ba, 0xbc_bc, 0xc5_c5, 0xce_ce, 0xd1_d1, 0xd4_d4, 
          0xeb_eb, 0xed_ed, 0xf0_f0, 0xf7_f7, 0xfd_fd},
        { // 0x0090__ codePoint ranges
          0x00_01, 0x03_03, 0x06_06, 0x0f_10, 0x13_14, 0x1a_1a, 0x1d_1d, 0x1f_20, 
          0x23_23, 0x2e_2e, 0x31_32, 0x38_38, 0x42_42, 0x45_45, 0x47_47, 0x4a_4b, 
          0x4d_4e, 0x53_55, 0x5c_5c, 0x60_61, 0x63_63, 0x69_69, 0x6d_6e, 0x75_75, 
          0x77_78, 0x7a_7a, 0x7f_7f, 0x84_84, 0xa3_a3, 0xa6_a6, 0xaa_aa, 0xb8_b8, 
          0xca_ca, 0xce_ce, 0xe1_e1, 0xe8_e8, 0xed_ed, 0xf5_f5, 0xf7_f7, 0xfd_fd},
        { // 0x0091__ codePoint ranges
          0x4c_4e, 0x52_52, 0x54_54, 0x62_62, 0x6a_6a, 0x6c_6c, 0x75_75, 0x77_78, 
          0x92_92, 0x9c_9c, 0xb8_b8, 0xc7_c8, 0xcc_cf, 0xd1_d1, 0xdc_dd, 0xe3_e3},
        { // 0x0092__ codePoint ranges
          0x0d_0d, 0x34_34, 0x44_44, 0x5b_5b, 0x62_62, 0x71_71, 0x80_80, 0x83_83, 
          0x85_85, 0x98_98, 0xad_ad, 0xed_ed, 0xf3_f3, 0xfc_fc},
        { // 0x0093__ codePoint ranges
          0x20_20, 0x26_26, 0x2c_2c, 0x2e_2f, 0x32_32, 0x4b_4b, 0x5b_5b, 0x75_75, 
          0x8c_8c, 0x96_96, 0xae_ae, 0xe1_e1},
        { // 0x0094__ codePoint ranges
          0x18_18, 0x51_51},
        { // 0x0095__ codePoint ranges
          0x77_77, 0x80_80, 0x89_89, 0x8b_8b, 0x91_91, 0x93_93, 0xa2_a3, 0xa5_a5, 
          0xb2_b2, 0xc7_c7, 0xd8_d8},
        { // 0x0096__ codePoint ranges
          0x1c_1c, 0x2a_2a, 0x32_32, 0x3b_3b, 0x44_44, 0x4d_4d, 0x50_50, 0x5b_5b, 
          0x62_65, 0x6a_6a, 0x70_70, 0x73_73, 0x75_76, 0x78_78, 0x7a_7a, 0x7d_7d, 
          0x85_86, 0x8a_8a, 0x8e_8f, 0x94_94, 0x99_99, 0x9b_9c, 0xa0_a0, 0xa3_a3, 
          0xb7_b7, 0xbb_bb, 0xc4_c7, 0xcc_cc, 0xd1_d1, 0xe2_e3, 0xe8_e8, 0xea_ea, 
          0xf0_f0, 0xf2_f2, 0xf6_f7, 0xfb_fb},
        { // 0x0097__ codePoint ranges
          0x00_00, 0x07_07, 0x0a_0a, 0x1c_1c, 0x27_27, 0x32_32, 0x52_52, 0x59_59, 
          0x5e_5e, 0x62_62, 0x69_69, 0x74_74, 0xd3_d3, 0xf3_f3, 0xfb_fb, 0xff_ff},
        { // 0x0098__ codePoint ranges
          0x02_03, 0x05_06, 0x08_08, 0x10_13, 0x18_18, 0x2d_2d, 0x30_30, 0x3b_3c, 
          0x4c_4e, 0x54_55, 0x58_58, 0x5e_5e, 0x67_67, 0xa8_a8, 0xdb_db, 0xdf_df, 
          0xe2_e2, 0xef_ef, 0xf2_f2, 0xfc_fe},
        { // 0x0099__ codePoint ranges
          0x05_05, 0x0a_0a, 0x0c_0c, 0x13_13, 0x28_28, 0x96_96, 0x99_99, 0xac_ac, 
          0xc4_c6, 0xd0_d0, 0xd2_d2},
        { // 0x009a__ codePoint ranges
          0x0e_0e, 0x12_13, 0x30_30, 0x5a_5a, 0xa8_a8, 0xb8_b8, 0xc4_c4, 0xd8_d8, 
          0xea_ea},
        { // 0x009b__ codePoint ranges
          0x31_31, 0x3c_3c, 0x42_42, 0x45_45, 0x54_54, 0x5a_5a, 0xae_ae, 0xe8_e8},
        { // 0x009c__ codePoint ranges
          0xe5_e5, 0xf4_f4},
        { // 0x009d__ codePoint ranges
          0x8f_8f, 0xb4_b4},
        { // 0x009e__ codePoint ranges
          0x7f_7f, 0x93_93, 0x97_97, 0xa6_a6, 0xba_bb, 0xc4_c4, 0xd2_d2, 0xd9_d9},
        { // 0x009f__ codePoint ranges
          0x13_13, 0x3b_3b, 0x62_62},
          null, null, null,
        { // 0x020b__ codePoint ranges
          0x9f_9f},
          null, null, null, null,
        { // 0x004e__ codePoint ranges
          0x00_01, 0x03_03, 0x07_0b, 0x0d_0e, 0x14_14, 0x16_16, 0x18_19, 0x21_21, 
          0x26_26, 0x2d_2d, 0x32_32, 0x38_39, 0x3b_3c, 0x45_45, 0x4f_4f, 0x57_57, 
          0x59_59, 0x5d_5e, 0x71_71, 0x73_73, 0x7e_7e, 0x80_80, 0x86_86, 0x88_89, 
          0x8b_8c, 0x92_92, 0x94_95, 0x9c_9c, 0xa1_a1, 0xa4_a4, 0xab_ad, 0xba_ba, 
          0xc1_c1, 0xca_cb, 0xcf_cf, 0xd5_d6, 0xd8_d9, 0xe3_e5, 0xee_ee, 0xf0_f0, 
          0xf2_f2, 0xf6_f6, 0xfb_fb},
        { // 0x004f__ codePoint ranges
          0x01_01, 0x0e_11, 0x1a_1a, 0x1d_1d, 0x2f_2f, 0x34_34, 0x38_38, 0x3a_3a, 
          0x3c_3c, 0x46_46, 0x4d_50, 0x53_53, 0x55_55, 0x59_59, 0x5c_5c, 0x73_73, 
          0x75_75, 0x7f_7f, 0x8b_8b, 0x8d_8d, 0x9b_9b, 0x9d_9d, 0xa1_a1, 0xae_af, 
          0xb5_b6, 0xbf_bf, 0xc2_c3, 0xca_ca, 0xd7_d7, 0xdd_dd, 0xe1_e1, 0xee_ee, 
          0xf3_f3, 0xf5_f5, 0xf8_f8, 0xfa_fa},
        { // 0x0050__ codePoint ranges
          0x09_09, 0x0b_0b, 0x0d_0d, 0x12_12, 0x19_19, 0x1f_1f, 0x23_24, 0x2b_2b, 
          0x39_39, 0x49_49, 0x4f_4f, 0x5c_5c, 0x65_65, 0x74_76, 0x7d_7d, 0x8d_8d, 
          0x91_91, 0x98_99, 0xac_ac, 0xb2_b2, 0xb5_b5, 0xb7_b7, 0xbe_be, 0xc5_c5, 
          0xcd_cd, 0xcf_cf, 0xd5_d5, 0xda_da, 0xe7_e7},
        { // 0x0051__ codePoint ranges
          0x00_00, 0x04_04, 0x12_12, 0x1f_1f, 0x2a_2a, 0x43_46, 0x48_49, 0x4b_4b, 
          0x4d_4d, 0x50_50, 0x5a_5a, 0x65_65, 0x68_68, 0x6b_6d, 0x71_71, 0x75_75, 
          0x77_78, 0x7c_7c, 0x85_86, 0x8a_8a, 0x8d_8d, 0x92_92, 0x97_97, 0x99_99, 
          0xa0_a0, 0xa5_a5, 0xac_ac, 0xb6_b7, 0xc4_c4, 0xc6_c6, 0xcd_cd, 0xdd_dd, 
          0xe1_e1, 0xe6_e6, 0xf6_f6, 0xf8_fa},
        { // 0x0052__ codePoint ranges
          0x00_00, 0x03_03, 0x06_08, 0x0a_0a, 0x11_11, 0x17_17, 0x1d_1d, 0x24_25, 
          0x29_29, 0x30_30, 0x36_3b, 0x47_47, 0x4a_4a, 0x4d_4d, 0x56_56, 0x5b_5b, 
          0x5d_5d, 0x63_64, 0x6f_70, 0x72_72, 0x75_75, 0x87_87, 0x9b_9b, 0x9f_a0, 
          0xa3_a3, 0xa9_aa, 0xb1_b1, 0xb4_b4, 0xb9_b9, 0xbe_be, 0xc3_c3, 0xc5_c5, 
          0xc7_c7, 0xc9_c9, 0xd5_d5, 0xd8_d9, 0xdd_dd, 0xdf_df, 0xe2_e2, 0xe4_e4, 
          0xe7_e7, 0xf2_f2, 0xfe_fe},
        { // 0x0053__ codePoint ranges
          0x02_02, 0x05_05, 0x16_17, 0x20_20, 0x39_3b, 0x3f_3f, 0x41_41, 0x43_43, 
          0x47_48, 0x4a_4a, 0x51_54, 0x57_58, 0x5a_5a, 0x60_60, 0x70_71, 0x73_75, 
          0x78_78, 0x84_84, 0x98_98, 0x9a_9a, 0x9f_9f, 0xb3_b3, 0xbb_bb, 0xc2_c2, 
          0xc8_c8, 0xca_ce, 0xd4_d4, 0xd6_d7, 0xd9_d9, 0xe3_e5, 0xeb_ec, 0xef_f0, 
          0xf2_f3, 0xf7_f8},
        { // 0x0054__ codePoint ranges
          0x04_04, 0x08_09, 0x0c_11, 0x1b_1b, 0x1f_1f, 0x26_26, 0x2b_2b, 0x38_39, 
          0x42_42, 0x48_4a, 0x68_68, 0x6a_6a, 0x73_73, 0x7c_7d, 0x8c_8c, 0xb2_b2, 
          0xbd_bd, 0xc0_c1, 0xe1_e1, 0xf2_f2, 0xfa_fa},
        { // 0x0055__ codePoint ranges
          0x04_04, 0x06_07, 0x10_10, 0x2f_2f, 0x31_31, 0x3e_3e, 0x46_46, 0x4f_4f, 
          0x53_53, 0x84_84, 0x89_89, 0x9a_9a, 0x9c_9d, 0xa9_ab, 0xb6_b6, 0xc5_c5, 
          0xe3_e3},
        { // 0x0056__ codePoint ranges
          0x06_06, 0x31_32, 0x68_68, 0x74_74, 0x87_87, 0xda_db, 0xde_de, 0xe0_e0, 
          0xe3_e3, 0xf0_f0, 0xf2_f3, 0xfa_fa, 0xfd_fd},
        { // 0x0057__ codePoint ranges
          0x0f_0f, 0x12_12, 0x1f_1f, 0x27_28, 0x30_30, 0x42_42, 0x47_47, 0x4a_4a, 
          0x51_51, 0x6a_6a, 0x82_82, 0x8b_8b, 0xa3_a3, 0xcb_cb, 0xce_ce, 0xdf_df, 
          0xf7_f7, 0xf9_fa, 0xfc_fc},
        { // 0x0058__ codePoint ranges
          0x00_00, 0x02_02, 0x05_06, 0x15_15, 0x24_24, 0x2a_2a, 0x31_31, 0x34_34, 
          0x40_41, 0x4a_4a, 0x51_51, 0x54_54, 0x57_57, 0x5a_5a, 0x5e_5e, 0x61_61, 
          0x69_69, 0x7e_7e, 0x83_83, 0x93_93, 0x97_97, 0x9c_9c, 0xa8_a8, 0xb3_b3, 
          0xbe_be, 0xc1_c1, 0xc7_c7, 0xca_ca, 0xcc_cc, 0xeb_eb, 0xee_ee, 0xf0_f2},
        { // 0x0059__ codePoint ranges
          0x09_09, 0x0f_0f, 0x15_16, 0x1a_1a, 0x1c_1c, 0x22_22, 0x27_27, 0x29_2b, 
          0x2e_2e, 0x31_31, 0x47_49, 0x4f_4f, 0x51_51, 0x54_54, 0x65_65, 0x68_68, 
          0x6a_6a, 0x6e_6e, 0x73_74, 0x7d_7d, 0x82_84, 0x8a_8a, 0x96_96, 0x99_99, 
          0xa5_a5, 0xa8_a8, 0xac_ac, 0xb9_b9, 0xbb_bb, 0xc9_c9, 0xcb_cb, 0xd3_d4, 
          0xeb_eb, 0xfb_fb, 0xff_ff}},
        // number of code-point ranges
        1744,
        // number of code-points
        2136);
}