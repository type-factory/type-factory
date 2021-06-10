package land.artli.easytype;

import java.util.Arrays;
import javax.annotation.processing.Generated;

@Generated(
  value="land.artli:easy-type-unicode-code-generator",
  comments="Generated from Unicode file 'ucd.all.grouped.xml'")
public final class Unicode {

  private Unicode() {
    // don't instantiate
  }

  public interface Subset {
    public int [] getLowerCodePointRanges();
    public long [] getUpperCodePointRanges();
  }


  public enum Other implements Subset {
    WHITESPACE("Whitespace",
        new int [] {
          0x0009_000d, 0x0020_0020, 0x0085_0085, 0x00a0_00a0, 0x1680_1680, 0x2000_200a, 0x2028_2029, 0x202f_202f,
          0x205f_205f, 0x3000_3000},
        new long [0]);

    private final String alias;
    private final int [] lowerCodePointRanges;
    private final long [] upperCodePointRanges;
    private Other(final String alias, final int [] lowerCodePointRanges, final long [] upperCodePointRanges) {
      this.alias = alias;
      this.lowerCodePointRanges = lowerCodePointRanges;
      this.upperCodePointRanges = upperCodePointRanges;
    }
    public int [] getLowerCodePointRanges() {
      return Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length);
    }
    public long [] getUpperCodePointRanges() {
      return Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length);
    }
  }

  /**
   * <p>Unicode General Category Values. Categories classify a code point by their primary characteristic.
   * The following is quoted from the 
   * <em>"<a href="https://www.unicode.org/reports/tr44/#General_Category_Values">Unicode General Category Values</a>"</em>
   * section of the <a href="https://www.unicode.org/reports/tr44">Unicode Character Database</a> document.</p>
   * <blockquote>
   * <p>For example, is the character a letter, a mark, a number, punctuation, or a symbol, and if so, of what type?</p>
   * <p>...</p>
   * <p>Many characters have multiple uses, and not all such cases can be captured entirely by the
   * General Category value. For example, the General Category value of Latin, Greek, or Hebrew letters does not attempt to cover (or preclude) the
   * numerical use of such letters as Roman numerals or in other numerary systems.<p>
   * <p>...</p>
   * <p class="caption">Table 12. <a name="GC_Values_Table" href="#GC_Values_Table">General_Category Values</a></p>
   * <table class="simple">
   *   <tr><th>Abbr</th><th>Long</th><th>Description</th></tr>
   *   <tr><td>Lu</td><td>Uppercase_Letter</td><td>an uppercase letter</td></tr>
   *   <tr><td>Ll</td><td>Lowercase_Letter</td><td>a lowercase letter</td></tr>
   *   <tr><td>Lt</td><td>Titlecase_Letter</td><td>a digraphic character, with first part uppercase</td></tr>
   *   <tr style='background-color:lightblue;'><td>LC</td><td>Cased_Letter</td><td>Lu | Ll | Lt</td></tr>
   *   <tr><td>Lm</td><td>Modifier_Letter</td><td>a modifier letter</td></tr>
   *   <tr><td>Lo</td><td>Other_Letter</td><td>other letters, including syllables and ideographs</td></tr>
   *   <tr style='background-color:lightblue;'><td>L</td><td>Letter</td><td>Lu | Ll | Lt | Lm | Lo</td></tr>
   *   <tr><td>Mn</td><td>Nonspacing_Mark</td><td>a nonspacing combining mark (zero advance width)</td></tr>
   *   <tr><td>Mc</td><td>Spacing_Mark</td><td>a spacing combining mark (positive advance width)</td></tr>
   *   <tr><td>Me</td><td>Enclosing_Mark</td><td>an enclosing combining mark</td></tr>
   *   <tr style='background-color:lightblue;'><td>M</td><td>Mark</td><td>Mn | Mc | Me</td></tr>
   *   <tr><td>Nd</td><td>Decimal_Number</td><td>a decimal digit</td></tr>
   *   <tr><td>Nl</td><td>Letter_Number</td><td>a letterlike numeric character</td></tr>
   *   <tr><td>No</td><td>Other_Number</td><td>a numeric character of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>N</td><td>Number</td><td>Nd | Nl | No</td></tr>
   *   <tr><td>Pc</td><td>Connector_Punctuation</td><td>a connecting punctuation mark, like a tie</td></tr>
   *   <tr><td>Pd</td><td>Dash_Punctuation</td><td>a dash or hyphen punctuation mark</td></tr>
   *   <tr><td>Ps</td><td>Open_Punctuation</td><td>an opening punctuation mark (of a pair)</td></tr>
   *   <tr><td>Pe</td><td>Close_Punctuation</td><td>a closing punctuation mark (of a pair)</td></tr>
   *   <tr><td>Pi</td><td>Initial_Punctuation</td><td>an initial quotation mark</td></tr>
   *   <tr><td>Pf</td><td>Final_Punctuation</td><td>a final quotation mark</td></tr>
   *   <tr><td>Po</td><td>Other_Punctuation</td><td>a punctuation mark of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>P</td><td>Punctuation</td><td>Pc | Pd | Ps | Pe | Pi | Pf | Po</td></tr>
   *   <tr><td>Sm</td><td>Math_Symbol</td><td>a symbol of mathematical use</td></tr>
   *   <tr><td>Sc</td><td>Currency_Symbol</td><td>a currency sign</td></tr>
   *   <tr><td>Sk</td><td>Modifier_Symbol</td><td>a non-letterlike modifier symbol</td></tr>
   *   <tr><td>So</td><td>Other_Symbol</td><td>a symbol of other type</td></tr>
   *   <tr style='background-color:lightblue;'><td>S</td><td>Symbol</td><td>Sm | Sc | Sk | So</td></tr>
   *   <tr><td>Zs</td><td>Space_Separator</td><td>a space character (of various non-zero widths)</td></tr>
   *   <tr><td>Zl</td><td>Line_Separator</td><td>U+2028 LINE SEPARATOR only</td></tr>
   *   <tr><td>Zp</td><td>Paragraph_Separator</td><td>U+2029 PARAGRAPH SEPARATOR only</td></tr>
   *   <tr style='background-color:lightblue;'><td>Z</td><td>Separator</td><td>Zs | Zl | Zp</td></tr>
   *   <tr><td>Cc</td><td>Control</td><td>a C0 or C1 control code</td></tr>
   *   <tr><td>Cf</td><td>Format</td><td>a format control character</td></tr>
   *   <tr><td>Cs</td><td>Surrogate</td><td>a surrogate code point</td></tr>
   *   <tr><td>Co</td><td>Private_Use</td><td>a private-use character</td></tr>
   *   <tr><td>Cn</td><td>Unassigned</td><td>a reserved unassigned code point or a noncharacter</td></tr>
   *   <tr style='background-color:lightblue;'><td>C</td><td>Other</td><td>Cc | Cf | Cs | Co | Cn</td></tr>
   * </table>
   * </blockquote>
   *
   * @see <a href="https://www.unicode.org/reports/tr44/#General_Category_Values">
   *   https://www.unicode.org/reports/tr44/#General_Category_Values</a>
   */
  public enum Category implements Subset {

    /** Lu – an uppercase letter */
    UPPERCASE_LETTER("Lu", "Uppercase Letter",
        new int [] {
          0x0041_005a, 0x00c0_00d6, 0x00d8_00de, 0x0100_0100, 0x0102_0102, 0x0104_0104, 0x0106_0106, 0x0108_0108,
          0x010a_010a, 0x010c_010c, 0x010e_010e, 0x0110_0110, 0x0112_0112, 0x0114_0114, 0x0116_0116, 0x0118_0118,
          0x011a_011a, 0x011c_011c, 0x011e_011e, 0x0120_0120, 0x0122_0122, 0x0124_0124, 0x0126_0126, 0x0128_0128,
          0x012a_012a, 0x012c_012c, 0x012e_012e, 0x0130_0130, 0x0132_0132, 0x0134_0134, 0x0136_0136, 0x0139_0139,
          0x013b_013b, 0x013d_013d, 0x013f_013f, 0x0141_0141, 0x0143_0143, 0x0145_0145, 0x0147_0147, 0x014a_014a,
          0x014c_014c, 0x014e_014e, 0x0150_0150, 0x0152_0152, 0x0154_0154, 0x0156_0156, 0x0158_0158, 0x015a_015a,
          0x015c_015c, 0x015e_015e, 0x0160_0160, 0x0162_0162, 0x0164_0164, 0x0166_0166, 0x0168_0168, 0x016a_016a,
          0x016c_016c, 0x016e_016e, 0x0170_0170, 0x0172_0172, 0x0174_0174, 0x0176_0176, 0x0178_0179, 0x017b_017b,
          0x017d_017d, 0x0181_0182, 0x0184_0184, 0x0186_0187, 0x0189_018b, 0x018e_0191, 0x0193_0194, 0x0196_0198,
          0x019c_019d, 0x019f_01a0, 0x01a2_01a2, 0x01a4_01a4, 0x01a6_01a7, 0x01a9_01a9, 0x01ac_01ac, 0x01ae_01af,
          0x01b1_01b3, 0x01b5_01b5, 0x01b7_01b8, 0x01bc_01bc, 0x01c4_01c4, 0x01c7_01c7, 0x01ca_01ca, 0x01cd_01cd,
          0x01cf_01cf, 0x01d1_01d1, 0x01d3_01d3, 0x01d5_01d5, 0x01d7_01d7, 0x01d9_01d9, 0x01db_01db, 0x01de_01de,
          0x01e0_01e0, 0x01e2_01e2, 0x01e4_01e4, 0x01e6_01e6, 0x01e8_01e8, 0x01ea_01ea, 0x01ec_01ec, 0x01ee_01ee,
          0x01f1_01f1, 0x01f4_01f4, 0x01f6_01f8, 0x01fa_01fa, 0x01fc_01fc, 0x01fe_01fe, 0x0200_0200, 0x0202_0202,
          0x0204_0204, 0x0206_0206, 0x0208_0208, 0x020a_020a, 0x020c_020c, 0x020e_020e, 0x0210_0210, 0x0212_0212,
          0x0214_0214, 0x0216_0216, 0x0218_0218, 0x021a_021a, 0x021c_021c, 0x021e_021e, 0x0220_0220, 0x0222_0222,
          0x0224_0224, 0x0226_0226, 0x0228_0228, 0x022a_022a, 0x022c_022c, 0x022e_022e, 0x0230_0230, 0x0232_0232,
          0x023a_023b, 0x023d_023e, 0x0241_0241, 0x0243_0246, 0x0248_0248, 0x024a_024a, 0x024c_024c, 0x024e_024e,
          0x0370_0370, 0x0372_0372, 0x0376_0376, 0x037f_037f, 0x0386_0386, 0x0388_038a, 0x038c_038c, 0x038e_038f,
          0x0391_03a1, 0x03a3_03ab, 0x03cf_03cf, 0x03d2_03d4, 0x03d8_03d8, 0x03da_03da, 0x03dc_03dc, 0x03de_03de,
          0x03e0_03e0, 0x03e2_03e2, 0x03e4_03e4, 0x03e6_03e6, 0x03e8_03e8, 0x03ea_03ea, 0x03ec_03ec, 0x03ee_03ee,
          0x03f4_03f4, 0x03f7_03f7, 0x03f9_03fa, 0x03fd_042f, 0x0460_0460, 0x0462_0462, 0x0464_0464, 0x0466_0466,
          0x0468_0468, 0x046a_046a, 0x046c_046c, 0x046e_046e, 0x0470_0470, 0x0472_0472, 0x0474_0474, 0x0476_0476,
          0x0478_0478, 0x047a_047a, 0x047c_047c, 0x047e_047e, 0x0480_0480, 0x048a_048a, 0x048c_048c, 0x048e_048e,
          0x0490_0490, 0x0492_0492, 0x0494_0494, 0x0496_0496, 0x0498_0498, 0x049a_049a, 0x049c_049c, 0x049e_049e,
          0x04a0_04a0, 0x04a2_04a2, 0x04a4_04a4, 0x04a6_04a6, 0x04a8_04a8, 0x04aa_04aa, 0x04ac_04ac, 0x04ae_04ae,
          0x04b0_04b0, 0x04b2_04b2, 0x04b4_04b4, 0x04b6_04b6, 0x04b8_04b8, 0x04ba_04ba, 0x04bc_04bc, 0x04be_04be,
          0x04c0_04c1, 0x04c3_04c3, 0x04c5_04c5, 0x04c7_04c7, 0x04c9_04c9, 0x04cb_04cb, 0x04cd_04cd, 0x04d0_04d0,
          0x04d2_04d2, 0x04d4_04d4, 0x04d6_04d6, 0x04d8_04d8, 0x04da_04da, 0x04dc_04dc, 0x04de_04de, 0x04e0_04e0,
          0x04e2_04e2, 0x04e4_04e4, 0x04e6_04e6, 0x04e8_04e8, 0x04ea_04ea, 0x04ec_04ec, 0x04ee_04ee, 0x04f0_04f0,
          0x04f2_04f2, 0x04f4_04f4, 0x04f6_04f6, 0x04f8_04f8, 0x04fa_04fa, 0x04fc_04fc, 0x04fe_04fe, 0x0500_0500,
          0x0502_0502, 0x0504_0504, 0x0506_0506, 0x0508_0508, 0x050a_050a, 0x050c_050c, 0x050e_050e, 0x0510_0510,
          0x0512_0512, 0x0514_0514, 0x0516_0516, 0x0518_0518, 0x051a_051a, 0x051c_051c, 0x051e_051e, 0x0520_0520,
          0x0522_0522, 0x0524_0524, 0x0526_0526, 0x0528_0528, 0x052a_052a, 0x052c_052c, 0x052e_052e, 0x0531_0556,
          0x10a0_10c5, 0x10c7_10c7, 0x10cd_10cd, 0x13a0_13f5, 0x1c90_1cba, 0x1cbd_1cbf, 0x1e00_1e00, 0x1e02_1e02,
          0x1e04_1e04, 0x1e06_1e06, 0x1e08_1e08, 0x1e0a_1e0a, 0x1e0c_1e0c, 0x1e0e_1e0e, 0x1e10_1e10, 0x1e12_1e12,
          0x1e14_1e14, 0x1e16_1e16, 0x1e18_1e18, 0x1e1a_1e1a, 0x1e1c_1e1c, 0x1e1e_1e1e, 0x1e20_1e20, 0x1e22_1e22,
          0x1e24_1e24, 0x1e26_1e26, 0x1e28_1e28, 0x1e2a_1e2a, 0x1e2c_1e2c, 0x1e2e_1e2e, 0x1e30_1e30, 0x1e32_1e32,
          0x1e34_1e34, 0x1e36_1e36, 0x1e38_1e38, 0x1e3a_1e3a, 0x1e3c_1e3c, 0x1e3e_1e3e, 0x1e40_1e40, 0x1e42_1e42,
          0x1e44_1e44, 0x1e46_1e46, 0x1e48_1e48, 0x1e4a_1e4a, 0x1e4c_1e4c, 0x1e4e_1e4e, 0x1e50_1e50, 0x1e52_1e52,
          0x1e54_1e54, 0x1e56_1e56, 0x1e58_1e58, 0x1e5a_1e5a, 0x1e5c_1e5c, 0x1e5e_1e5e, 0x1e60_1e60, 0x1e62_1e62,
          0x1e64_1e64, 0x1e66_1e66, 0x1e68_1e68, 0x1e6a_1e6a, 0x1e6c_1e6c, 0x1e6e_1e6e, 0x1e70_1e70, 0x1e72_1e72,
          0x1e74_1e74, 0x1e76_1e76, 0x1e78_1e78, 0x1e7a_1e7a, 0x1e7c_1e7c, 0x1e7e_1e7e, 0x1e80_1e80, 0x1e82_1e82,
          0x1e84_1e84, 0x1e86_1e86, 0x1e88_1e88, 0x1e8a_1e8a, 0x1e8c_1e8c, 0x1e8e_1e8e, 0x1e90_1e90, 0x1e92_1e92,
          0x1e94_1e94, 0x1e9e_1e9e, 0x1ea0_1ea0, 0x1ea2_1ea2, 0x1ea4_1ea4, 0x1ea6_1ea6, 0x1ea8_1ea8, 0x1eaa_1eaa,
          0x1eac_1eac, 0x1eae_1eae, 0x1eb0_1eb0, 0x1eb2_1eb2, 0x1eb4_1eb4, 0x1eb6_1eb6, 0x1eb8_1eb8, 0x1eba_1eba,
          0x1ebc_1ebc, 0x1ebe_1ebe, 0x1ec0_1ec0, 0x1ec2_1ec2, 0x1ec4_1ec4, 0x1ec6_1ec6, 0x1ec8_1ec8, 0x1eca_1eca,
          0x1ecc_1ecc, 0x1ece_1ece, 0x1ed0_1ed0, 0x1ed2_1ed2, 0x1ed4_1ed4, 0x1ed6_1ed6, 0x1ed8_1ed8, 0x1eda_1eda,
          0x1edc_1edc, 0x1ede_1ede, 0x1ee0_1ee0, 0x1ee2_1ee2, 0x1ee4_1ee4, 0x1ee6_1ee6, 0x1ee8_1ee8, 0x1eea_1eea,
          0x1eec_1eec, 0x1eee_1eee, 0x1ef0_1ef0, 0x1ef2_1ef2, 0x1ef4_1ef4, 0x1ef6_1ef6, 0x1ef8_1ef8, 0x1efa_1efa,
          0x1efc_1efc, 0x1efe_1efe, 0x1f08_1f0f, 0x1f18_1f1d, 0x1f28_1f2f, 0x1f38_1f3f, 0x1f48_1f4d, 0x1f59_1f59,
          0x1f5b_1f5b, 0x1f5d_1f5d, 0x1f5f_1f5f, 0x1f68_1f6f, 0x1fb8_1fbb, 0x1fc8_1fcb, 0x1fd8_1fdb, 0x1fe8_1fec,
          0x1ff8_1ffb, 0x2102_2102, 0x2107_2107, 0x210b_210d, 0x2110_2112, 0x2115_2115, 0x2119_211d, 0x2124_2124,
          0x2126_2126, 0x2128_2128, 0x212a_212d, 0x2130_2133, 0x213e_213f, 0x2145_2145, 0x2183_2183, 0x2c00_2c2e,
          0x2c60_2c60, 0x2c62_2c64, 0x2c67_2c67, 0x2c69_2c69, 0x2c6b_2c6b, 0x2c6d_2c70, 0x2c72_2c72, 0x2c75_2c75,
          0x2c7e_2c80, 0x2c82_2c82, 0x2c84_2c84, 0x2c86_2c86, 0x2c88_2c88, 0x2c8a_2c8a, 0x2c8c_2c8c, 0x2c8e_2c8e,
          0x2c90_2c90, 0x2c92_2c92, 0x2c94_2c94, 0x2c96_2c96, 0x2c98_2c98, 0x2c9a_2c9a, 0x2c9c_2c9c, 0x2c9e_2c9e,
          0x2ca0_2ca0, 0x2ca2_2ca2, 0x2ca4_2ca4, 0x2ca6_2ca6, 0x2ca8_2ca8, 0x2caa_2caa, 0x2cac_2cac, 0x2cae_2cae,
          0x2cb0_2cb0, 0x2cb2_2cb2, 0x2cb4_2cb4, 0x2cb6_2cb6, 0x2cb8_2cb8, 0x2cba_2cba, 0x2cbc_2cbc, 0x2cbe_2cbe,
          0x2cc0_2cc0, 0x2cc2_2cc2, 0x2cc4_2cc4, 0x2cc6_2cc6, 0x2cc8_2cc8, 0x2cca_2cca, 0x2ccc_2ccc, 0x2cce_2cce,
          0x2cd0_2cd0, 0x2cd2_2cd2, 0x2cd4_2cd4, 0x2cd6_2cd6, 0x2cd8_2cd8, 0x2cda_2cda, 0x2cdc_2cdc, 0x2cde_2cde,
          0x2ce0_2ce0, 0x2ce2_2ce2, 0x2ceb_2ceb, 0x2ced_2ced, 0x2cf2_2cf2, 0xa640_a640, 0xa642_a642, 0xa644_a644,
          0xa646_a646, 0xa648_a648, 0xa64a_a64a, 0xa64c_a64c, 0xa64e_a64e, 0xa650_a650, 0xa652_a652, 0xa654_a654,
          0xa656_a656, 0xa658_a658, 0xa65a_a65a, 0xa65c_a65c, 0xa65e_a65e, 0xa660_a660, 0xa662_a662, 0xa664_a664,
          0xa666_a666, 0xa668_a668, 0xa66a_a66a, 0xa66c_a66c, 0xa680_a680, 0xa682_a682, 0xa684_a684, 0xa686_a686,
          0xa688_a688, 0xa68a_a68a, 0xa68c_a68c, 0xa68e_a68e, 0xa690_a690, 0xa692_a692, 0xa694_a694, 0xa696_a696,
          0xa698_a698, 0xa69a_a69a, 0xa722_a722, 0xa724_a724, 0xa726_a726, 0xa728_a728, 0xa72a_a72a, 0xa72c_a72c,
          0xa72e_a72e, 0xa732_a732, 0xa734_a734, 0xa736_a736, 0xa738_a738, 0xa73a_a73a, 0xa73c_a73c, 0xa73e_a73e,
          0xa740_a740, 0xa742_a742, 0xa744_a744, 0xa746_a746, 0xa748_a748, 0xa74a_a74a, 0xa74c_a74c, 0xa74e_a74e,
          0xa750_a750, 0xa752_a752, 0xa754_a754, 0xa756_a756, 0xa758_a758, 0xa75a_a75a, 0xa75c_a75c, 0xa75e_a75e,
          0xa760_a760, 0xa762_a762, 0xa764_a764, 0xa766_a766, 0xa768_a768, 0xa76a_a76a, 0xa76c_a76c, 0xa76e_a76e,
          0xa779_a779, 0xa77b_a77b, 0xa77d_a77e, 0xa780_a780, 0xa782_a782, 0xa784_a784, 0xa786_a786, 0xa78b_a78b,
          0xa78d_a78d, 0xa790_a790, 0xa792_a792, 0xa796_a796, 0xa798_a798, 0xa79a_a79a, 0xa79c_a79c, 0xa79e_a79e,
          0xa7a0_a7a0, 0xa7a2_a7a2, 0xa7a4_a7a4, 0xa7a6_a7a6, 0xa7a8_a7a8, 0xa7aa_a7ae, 0xa7b0_a7b4, 0xa7b6_a7b6,
          0xa7b8_a7b8, 0xa7ba_a7ba, 0xa7bc_a7bc, 0xa7be_a7be, 0xa7c2_a7c2, 0xa7c4_a7c7, 0xa7c9_a7c9, 0xa7f5_a7f5,
          0xff21_ff3a},
        new long [] { 
          0x00010400_00010427L, 0x000104b0_000104d3L, 0x00010c80_00010cb2L, 0x000118a0_000118bfL,
          0x00016e40_00016e5fL, 0x0001d400_0001d419L, 0x0001d434_0001d44dL, 0x0001d468_0001d481L,
          0x0001d49c_0001d49cL, 0x0001d49e_0001d49fL, 0x0001d4a2_0001d4a2L, 0x0001d4a5_0001d4a6L,
          0x0001d4a9_0001d4acL, 0x0001d4ae_0001d4b5L, 0x0001d4d0_0001d4e9L, 0x0001d504_0001d505L,
          0x0001d507_0001d50aL, 0x0001d50d_0001d514L, 0x0001d516_0001d51cL, 0x0001d538_0001d539L,
          0x0001d53b_0001d53eL, 0x0001d540_0001d544L, 0x0001d546_0001d546L, 0x0001d54a_0001d550L,
          0x0001d56c_0001d585L, 0x0001d5a0_0001d5b9L, 0x0001d5d4_0001d5edL, 0x0001d608_0001d621L,
          0x0001d63c_0001d655L, 0x0001d670_0001d689L, 0x0001d6a8_0001d6c0L, 0x0001d6e2_0001d6faL,
          0x0001d71c_0001d734L, 0x0001d756_0001d76eL, 0x0001d790_0001d7a8L, 0x0001d7ca_0001d7caL,
          0x0001e900_0001e921L}),
    /** Ll – a lowercase letter */
    LOWERCASE_LETTER("Ll", "Lowercase Letter",
        new int [] {
          0x0061_007a, 0x00b5_00b5, 0x00df_00f6, 0x00f8_00ff, 0x0101_0101, 0x0103_0103, 0x0105_0105, 0x0107_0107,
          0x0109_0109, 0x010b_010b, 0x010d_010d, 0x010f_010f, 0x0111_0111, 0x0113_0113, 0x0115_0115, 0x0117_0117,
          0x0119_0119, 0x011b_011b, 0x011d_011d, 0x011f_011f, 0x0121_0121, 0x0123_0123, 0x0125_0125, 0x0127_0127,
          0x0129_0129, 0x012b_012b, 0x012d_012d, 0x012f_012f, 0x0131_0131, 0x0133_0133, 0x0135_0135, 0x0137_0138,
          0x013a_013a, 0x013c_013c, 0x013e_013e, 0x0140_0140, 0x0142_0142, 0x0144_0144, 0x0146_0146, 0x0148_0149,
          0x014b_014b, 0x014d_014d, 0x014f_014f, 0x0151_0151, 0x0153_0153, 0x0155_0155, 0x0157_0157, 0x0159_0159,
          0x015b_015b, 0x015d_015d, 0x015f_015f, 0x0161_0161, 0x0163_0163, 0x0165_0165, 0x0167_0167, 0x0169_0169,
          0x016b_016b, 0x016d_016d, 0x016f_016f, 0x0171_0171, 0x0173_0173, 0x0175_0175, 0x0177_0177, 0x017a_017a,
          0x017c_017c, 0x017e_0180, 0x0183_0183, 0x0185_0185, 0x0188_0188, 0x018c_018d, 0x0192_0192, 0x0195_0195,
          0x0199_019b, 0x019e_019e, 0x01a1_01a1, 0x01a3_01a3, 0x01a5_01a5, 0x01a8_01a8, 0x01aa_01ab, 0x01ad_01ad,
          0x01b0_01b0, 0x01b4_01b4, 0x01b6_01b6, 0x01b9_01ba, 0x01bd_01bf, 0x01c6_01c6, 0x01c9_01c9, 0x01cc_01cc,
          0x01ce_01ce, 0x01d0_01d0, 0x01d2_01d2, 0x01d4_01d4, 0x01d6_01d6, 0x01d8_01d8, 0x01da_01da, 0x01dc_01dd,
          0x01df_01df, 0x01e1_01e1, 0x01e3_01e3, 0x01e5_01e5, 0x01e7_01e7, 0x01e9_01e9, 0x01eb_01eb, 0x01ed_01ed,
          0x01ef_01f0, 0x01f3_01f3, 0x01f5_01f5, 0x01f9_01f9, 0x01fb_01fb, 0x01fd_01fd, 0x01ff_01ff, 0x0201_0201,
          0x0203_0203, 0x0205_0205, 0x0207_0207, 0x0209_0209, 0x020b_020b, 0x020d_020d, 0x020f_020f, 0x0211_0211,
          0x0213_0213, 0x0215_0215, 0x0217_0217, 0x0219_0219, 0x021b_021b, 0x021d_021d, 0x021f_021f, 0x0221_0221,
          0x0223_0223, 0x0225_0225, 0x0227_0227, 0x0229_0229, 0x022b_022b, 0x022d_022d, 0x022f_022f, 0x0231_0231,
          0x0233_0239, 0x023c_023c, 0x023f_0240, 0x0242_0242, 0x0247_0247, 0x0249_0249, 0x024b_024b, 0x024d_024d,
          0x024f_0293, 0x0295_02af, 0x0371_0371, 0x0373_0373, 0x0377_0377, 0x037b_037d, 0x0390_0390, 0x03ac_03ce,
          0x03d0_03d1, 0x03d5_03d7, 0x03d9_03d9, 0x03db_03db, 0x03dd_03dd, 0x03df_03df, 0x03e1_03e1, 0x03e3_03e3,
          0x03e5_03e5, 0x03e7_03e7, 0x03e9_03e9, 0x03eb_03eb, 0x03ed_03ed, 0x03ef_03f3, 0x03f5_03f5, 0x03f8_03f8,
          0x03fb_03fc, 0x0430_045f, 0x0461_0461, 0x0463_0463, 0x0465_0465, 0x0467_0467, 0x0469_0469, 0x046b_046b,
          0x046d_046d, 0x046f_046f, 0x0471_0471, 0x0473_0473, 0x0475_0475, 0x0477_0477, 0x0479_0479, 0x047b_047b,
          0x047d_047d, 0x047f_047f, 0x0481_0481, 0x048b_048b, 0x048d_048d, 0x048f_048f, 0x0491_0491, 0x0493_0493,
          0x0495_0495, 0x0497_0497, 0x0499_0499, 0x049b_049b, 0x049d_049d, 0x049f_049f, 0x04a1_04a1, 0x04a3_04a3,
          0x04a5_04a5, 0x04a7_04a7, 0x04a9_04a9, 0x04ab_04ab, 0x04ad_04ad, 0x04af_04af, 0x04b1_04b1, 0x04b3_04b3,
          0x04b5_04b5, 0x04b7_04b7, 0x04b9_04b9, 0x04bb_04bb, 0x04bd_04bd, 0x04bf_04bf, 0x04c2_04c2, 0x04c4_04c4,
          0x04c6_04c6, 0x04c8_04c8, 0x04ca_04ca, 0x04cc_04cc, 0x04ce_04cf, 0x04d1_04d1, 0x04d3_04d3, 0x04d5_04d5,
          0x04d7_04d7, 0x04d9_04d9, 0x04db_04db, 0x04dd_04dd, 0x04df_04df, 0x04e1_04e1, 0x04e3_04e3, 0x04e5_04e5,
          0x04e7_04e7, 0x04e9_04e9, 0x04eb_04eb, 0x04ed_04ed, 0x04ef_04ef, 0x04f1_04f1, 0x04f3_04f3, 0x04f5_04f5,
          0x04f7_04f7, 0x04f9_04f9, 0x04fb_04fb, 0x04fd_04fd, 0x04ff_04ff, 0x0501_0501, 0x0503_0503, 0x0505_0505,
          0x0507_0507, 0x0509_0509, 0x050b_050b, 0x050d_050d, 0x050f_050f, 0x0511_0511, 0x0513_0513, 0x0515_0515,
          0x0517_0517, 0x0519_0519, 0x051b_051b, 0x051d_051d, 0x051f_051f, 0x0521_0521, 0x0523_0523, 0x0525_0525,
          0x0527_0527, 0x0529_0529, 0x052b_052b, 0x052d_052d, 0x052f_052f, 0x0560_0588, 0x10d0_10fa, 0x10fd_10ff,
          0x13f8_13fd, 0x1c80_1c88, 0x1d00_1d2b, 0x1d6b_1d77, 0x1d79_1d9a, 0x1e01_1e01, 0x1e03_1e03, 0x1e05_1e05,
          0x1e07_1e07, 0x1e09_1e09, 0x1e0b_1e0b, 0x1e0d_1e0d, 0x1e0f_1e0f, 0x1e11_1e11, 0x1e13_1e13, 0x1e15_1e15,
          0x1e17_1e17, 0x1e19_1e19, 0x1e1b_1e1b, 0x1e1d_1e1d, 0x1e1f_1e1f, 0x1e21_1e21, 0x1e23_1e23, 0x1e25_1e25,
          0x1e27_1e27, 0x1e29_1e29, 0x1e2b_1e2b, 0x1e2d_1e2d, 0x1e2f_1e2f, 0x1e31_1e31, 0x1e33_1e33, 0x1e35_1e35,
          0x1e37_1e37, 0x1e39_1e39, 0x1e3b_1e3b, 0x1e3d_1e3d, 0x1e3f_1e3f, 0x1e41_1e41, 0x1e43_1e43, 0x1e45_1e45,
          0x1e47_1e47, 0x1e49_1e49, 0x1e4b_1e4b, 0x1e4d_1e4d, 0x1e4f_1e4f, 0x1e51_1e51, 0x1e53_1e53, 0x1e55_1e55,
          0x1e57_1e57, 0x1e59_1e59, 0x1e5b_1e5b, 0x1e5d_1e5d, 0x1e5f_1e5f, 0x1e61_1e61, 0x1e63_1e63, 0x1e65_1e65,
          0x1e67_1e67, 0x1e69_1e69, 0x1e6b_1e6b, 0x1e6d_1e6d, 0x1e6f_1e6f, 0x1e71_1e71, 0x1e73_1e73, 0x1e75_1e75,
          0x1e77_1e77, 0x1e79_1e79, 0x1e7b_1e7b, 0x1e7d_1e7d, 0x1e7f_1e7f, 0x1e81_1e81, 0x1e83_1e83, 0x1e85_1e85,
          0x1e87_1e87, 0x1e89_1e89, 0x1e8b_1e8b, 0x1e8d_1e8d, 0x1e8f_1e8f, 0x1e91_1e91, 0x1e93_1e93, 0x1e95_1e9d,
          0x1e9f_1e9f, 0x1ea1_1ea1, 0x1ea3_1ea3, 0x1ea5_1ea5, 0x1ea7_1ea7, 0x1ea9_1ea9, 0x1eab_1eab, 0x1ead_1ead,
          0x1eaf_1eaf, 0x1eb1_1eb1, 0x1eb3_1eb3, 0x1eb5_1eb5, 0x1eb7_1eb7, 0x1eb9_1eb9, 0x1ebb_1ebb, 0x1ebd_1ebd,
          0x1ebf_1ebf, 0x1ec1_1ec1, 0x1ec3_1ec3, 0x1ec5_1ec5, 0x1ec7_1ec7, 0x1ec9_1ec9, 0x1ecb_1ecb, 0x1ecd_1ecd,
          0x1ecf_1ecf, 0x1ed1_1ed1, 0x1ed3_1ed3, 0x1ed5_1ed5, 0x1ed7_1ed7, 0x1ed9_1ed9, 0x1edb_1edb, 0x1edd_1edd,
          0x1edf_1edf, 0x1ee1_1ee1, 0x1ee3_1ee3, 0x1ee5_1ee5, 0x1ee7_1ee7, 0x1ee9_1ee9, 0x1eeb_1eeb, 0x1eed_1eed,
          0x1eef_1eef, 0x1ef1_1ef1, 0x1ef3_1ef3, 0x1ef5_1ef5, 0x1ef7_1ef7, 0x1ef9_1ef9, 0x1efb_1efb, 0x1efd_1efd,
          0x1eff_1f07, 0x1f10_1f15, 0x1f20_1f27, 0x1f30_1f37, 0x1f40_1f45, 0x1f50_1f57, 0x1f60_1f67, 0x1f70_1f7d,
          0x1f80_1f87, 0x1f90_1f97, 0x1fa0_1fa7, 0x1fb0_1fb4, 0x1fb6_1fb7, 0x1fbe_1fbe, 0x1fc2_1fc4, 0x1fc6_1fc7,
          0x1fd0_1fd3, 0x1fd6_1fd7, 0x1fe0_1fe7, 0x1ff2_1ff4, 0x1ff6_1ff7, 0x210a_210a, 0x210e_210f, 0x2113_2113,
          0x212f_212f, 0x2134_2134, 0x2139_2139, 0x213c_213d, 0x2146_2149, 0x214e_214e, 0x2184_2184, 0x2c30_2c5e,
          0x2c61_2c61, 0x2c65_2c66, 0x2c68_2c68, 0x2c6a_2c6a, 0x2c6c_2c6c, 0x2c71_2c71, 0x2c73_2c74, 0x2c76_2c7b,
          0x2c81_2c81, 0x2c83_2c83, 0x2c85_2c85, 0x2c87_2c87, 0x2c89_2c89, 0x2c8b_2c8b, 0x2c8d_2c8d, 0x2c8f_2c8f,
          0x2c91_2c91, 0x2c93_2c93, 0x2c95_2c95, 0x2c97_2c97, 0x2c99_2c99, 0x2c9b_2c9b, 0x2c9d_2c9d, 0x2c9f_2c9f,
          0x2ca1_2ca1, 0x2ca3_2ca3, 0x2ca5_2ca5, 0x2ca7_2ca7, 0x2ca9_2ca9, 0x2cab_2cab, 0x2cad_2cad, 0x2caf_2caf,
          0x2cb1_2cb1, 0x2cb3_2cb3, 0x2cb5_2cb5, 0x2cb7_2cb7, 0x2cb9_2cb9, 0x2cbb_2cbb, 0x2cbd_2cbd, 0x2cbf_2cbf,
          0x2cc1_2cc1, 0x2cc3_2cc3, 0x2cc5_2cc5, 0x2cc7_2cc7, 0x2cc9_2cc9, 0x2ccb_2ccb, 0x2ccd_2ccd, 0x2ccf_2ccf,
          0x2cd1_2cd1, 0x2cd3_2cd3, 0x2cd5_2cd5, 0x2cd7_2cd7, 0x2cd9_2cd9, 0x2cdb_2cdb, 0x2cdd_2cdd, 0x2cdf_2cdf,
          0x2ce1_2ce1, 0x2ce3_2ce4, 0x2cec_2cec, 0x2cee_2cee, 0x2cf3_2cf3, 0x2d00_2d25, 0x2d27_2d27, 0x2d2d_2d2d,
          0xa641_a641, 0xa643_a643, 0xa645_a645, 0xa647_a647, 0xa649_a649, 0xa64b_a64b, 0xa64d_a64d, 0xa64f_a64f,
          0xa651_a651, 0xa653_a653, 0xa655_a655, 0xa657_a657, 0xa659_a659, 0xa65b_a65b, 0xa65d_a65d, 0xa65f_a65f,
          0xa661_a661, 0xa663_a663, 0xa665_a665, 0xa667_a667, 0xa669_a669, 0xa66b_a66b, 0xa66d_a66d, 0xa681_a681,
          0xa683_a683, 0xa685_a685, 0xa687_a687, 0xa689_a689, 0xa68b_a68b, 0xa68d_a68d, 0xa68f_a68f, 0xa691_a691,
          0xa693_a693, 0xa695_a695, 0xa697_a697, 0xa699_a699, 0xa69b_a69b, 0xa723_a723, 0xa725_a725, 0xa727_a727,
          0xa729_a729, 0xa72b_a72b, 0xa72d_a72d, 0xa72f_a731, 0xa733_a733, 0xa735_a735, 0xa737_a737, 0xa739_a739,
          0xa73b_a73b, 0xa73d_a73d, 0xa73f_a73f, 0xa741_a741, 0xa743_a743, 0xa745_a745, 0xa747_a747, 0xa749_a749,
          0xa74b_a74b, 0xa74d_a74d, 0xa74f_a74f, 0xa751_a751, 0xa753_a753, 0xa755_a755, 0xa757_a757, 0xa759_a759,
          0xa75b_a75b, 0xa75d_a75d, 0xa75f_a75f, 0xa761_a761, 0xa763_a763, 0xa765_a765, 0xa767_a767, 0xa769_a769,
          0xa76b_a76b, 0xa76d_a76d, 0xa76f_a76f, 0xa771_a778, 0xa77a_a77a, 0xa77c_a77c, 0xa77f_a77f, 0xa781_a781,
          0xa783_a783, 0xa785_a785, 0xa787_a787, 0xa78c_a78c, 0xa78e_a78e, 0xa791_a791, 0xa793_a795, 0xa797_a797,
          0xa799_a799, 0xa79b_a79b, 0xa79d_a79d, 0xa79f_a79f, 0xa7a1_a7a1, 0xa7a3_a7a3, 0xa7a5_a7a5, 0xa7a7_a7a7,
          0xa7a9_a7a9, 0xa7af_a7af, 0xa7b5_a7b5, 0xa7b7_a7b7, 0xa7b9_a7b9, 0xa7bb_a7bb, 0xa7bd_a7bd, 0xa7bf_a7bf,
          0xa7c3_a7c3, 0xa7c8_a7c8, 0xa7ca_a7ca, 0xa7f6_a7f6, 0xa7fa_a7fa, 0xab30_ab5a, 0xab60_ab68, 0xab70_abbf,
          0xfb00_fb06, 0xfb13_fb17, 0xff41_ff5a},
        new long [] { 
          0x00010428_0001044fL, 0x000104d8_000104fbL, 0x00010cc0_00010cf2L, 0x000118c0_000118dfL,
          0x00016e60_00016e7fL, 0x0001d41a_0001d433L, 0x0001d44e_0001d454L, 0x0001d456_0001d467L,
          0x0001d482_0001d49bL, 0x0001d4b6_0001d4b9L, 0x0001d4bb_0001d4bbL, 0x0001d4bd_0001d4c3L,
          0x0001d4c5_0001d4cfL, 0x0001d4ea_0001d503L, 0x0001d51e_0001d537L, 0x0001d552_0001d56bL,
          0x0001d586_0001d59fL, 0x0001d5ba_0001d5d3L, 0x0001d5ee_0001d607L, 0x0001d622_0001d63bL,
          0x0001d656_0001d66fL, 0x0001d68a_0001d6a5L, 0x0001d6c2_0001d6daL, 0x0001d6dc_0001d6e1L,
          0x0001d6fc_0001d714L, 0x0001d716_0001d71bL, 0x0001d736_0001d74eL, 0x0001d750_0001d755L,
          0x0001d770_0001d788L, 0x0001d78a_0001d78fL, 0x0001d7aa_0001d7c2L, 0x0001d7c4_0001d7c9L,
          0x0001d7cb_0001d7cbL, 0x0001e922_0001e943L}),
    /** Lt – a digraphic character, with first part uppercase */
    TITLECASE_LETTER("Lt", "Titlecase Letter",
        new int [] {
          0x01c5_01c5, 0x01c8_01c8, 0x01cb_01cb, 0x01f2_01f2, 0x1f88_1f8f, 0x1f98_1f9f, 0x1fa8_1faf, 0x1fbc_1fbc,
          0x1fcc_1fcc, 0x1ffc_1ffc},
        new long [0]),
    /** LC – Lu | Ll | Lt */
    CASED_LETTER("LC", "Cased Letter",
        new int [] {
          0x0041_005a, 0x0061_007a, 0x00b5_00b5, 0x00c0_00d6, 0x00d8_00f6, 0x00f8_01ba, 0x01bc_01bf, 0x01c4_0293,
          0x0295_02af, 0x0370_0373, 0x0376_0377, 0x037b_037d, 0x037f_037f, 0x0386_0386, 0x0388_038a, 0x038c_038c,
          0x038e_03a1, 0x03a3_03f5, 0x03f7_0481, 0x048a_052f, 0x0531_0556, 0x0560_0588, 0x10a0_10c5, 0x10c7_10c7,
          0x10cd_10cd, 0x10d0_10fa, 0x10fd_10ff, 0x13a0_13f5, 0x13f8_13fd, 0x1c80_1c88, 0x1c90_1cba, 0x1cbd_1cbf,
          0x1d00_1d2b, 0x1d6b_1d77, 0x1d79_1d9a, 0x1e00_1f15, 0x1f18_1f1d, 0x1f20_1f45, 0x1f48_1f4d, 0x1f50_1f57,
          0x1f59_1f59, 0x1f5b_1f5b, 0x1f5d_1f5d, 0x1f5f_1f7d, 0x1f80_1fb4, 0x1fb6_1fbc, 0x1fbe_1fbe, 0x1fc2_1fc4,
          0x1fc6_1fcc, 0x1fd0_1fd3, 0x1fd6_1fdb, 0x1fe0_1fec, 0x1ff2_1ff4, 0x1ff6_1ffc, 0x2102_2102, 0x2107_2107,
          0x210a_2113, 0x2115_2115, 0x2119_211d, 0x2124_2124, 0x2126_2126, 0x2128_2128, 0x212a_212d, 0x212f_2134,
          0x2139_2139, 0x213c_213f, 0x2145_2149, 0x214e_214e, 0x2183_2184, 0x2c00_2c2e, 0x2c30_2c5e, 0x2c60_2c7b,
          0x2c7e_2ce4, 0x2ceb_2cee, 0x2cf2_2cf3, 0x2d00_2d25, 0x2d27_2d27, 0x2d2d_2d2d, 0xa640_a66d, 0xa680_a69b,
          0xa722_a76f, 0xa771_a787, 0xa78b_a78e, 0xa790_a7bf, 0xa7c2_a7ca, 0xa7f5_a7f6, 0xa7fa_a7fa, 0xab30_ab5a,
          0xab60_ab68, 0xab70_abbf, 0xfb00_fb06, 0xfb13_fb17, 0xff21_ff3a, 0xff41_ff5a},
        new long [] { 
          0x00010400_0001044fL, 0x000104b0_000104d3L, 0x000104d8_000104fbL, 0x00010c80_00010cb2L,
          0x00010cc0_00010cf2L, 0x000118a0_000118dfL, 0x00016e40_00016e7fL, 0x0001d400_0001d454L,
          0x0001d456_0001d49cL, 0x0001d49e_0001d49fL, 0x0001d4a2_0001d4a2L, 0x0001d4a5_0001d4a6L,
          0x0001d4a9_0001d4acL, 0x0001d4ae_0001d4b9L, 0x0001d4bb_0001d4bbL, 0x0001d4bd_0001d4c3L,
          0x0001d4c5_0001d505L, 0x0001d507_0001d50aL, 0x0001d50d_0001d514L, 0x0001d516_0001d51cL,
          0x0001d51e_0001d539L, 0x0001d53b_0001d53eL, 0x0001d540_0001d544L, 0x0001d546_0001d546L,
          0x0001d54a_0001d550L, 0x0001d552_0001d6a5L, 0x0001d6a8_0001d6c0L, 0x0001d6c2_0001d6daL,
          0x0001d6dc_0001d6faL, 0x0001d6fc_0001d714L, 0x0001d716_0001d734L, 0x0001d736_0001d74eL,
          0x0001d750_0001d76eL, 0x0001d770_0001d788L, 0x0001d78a_0001d7a8L, 0x0001d7aa_0001d7c2L,
          0x0001d7c4_0001d7cbL, 0x0001e900_0001e943L}),
    /** Lm – a modifier letter */
    MODIFIER_LETTER("Lm", "Modifier Letter",
        new int [] {
          0x02b0_02c1, 0x02c6_02d1, 0x02e0_02e4, 0x02ec_02ec, 0x02ee_02ee, 0x0374_0374, 0x037a_037a, 0x0559_0559,
          0x0640_0640, 0x06e5_06e6, 0x07f4_07f5, 0x07fa_07fa, 0x081a_081a, 0x0824_0824, 0x0828_0828, 0x0971_0971,
          0x0e46_0e46, 0x0ec6_0ec6, 0x10fc_10fc, 0x17d7_17d7, 0x1843_1843, 0x1aa7_1aa7, 0x1c78_1c7d, 0x1d2c_1d6a,
          0x1d78_1d78, 0x1d9b_1dbf, 0x2071_2071, 0x207f_207f, 0x2090_209c, 0x2c7c_2c7d, 0x2d6f_2d6f, 0x2e2f_2e2f,
          0x3005_3005, 0x3031_3035, 0x303b_303b, 0x309d_309e, 0x30fc_30fe, 0xa015_a015, 0xa4f8_a4fd, 0xa60c_a60c,
          0xa67f_a67f, 0xa69c_a69d, 0xa717_a71f, 0xa770_a770, 0xa788_a788, 0xa7f8_a7f9, 0xa9cf_a9cf, 0xa9e6_a9e6,
          0xaa70_aa70, 0xaadd_aadd, 0xaaf3_aaf4, 0xab5c_ab5f, 0xab69_ab69, 0xff70_ff70, 0xff9e_ff9f},
        new long [] { 
          0x00016b40_00016b43L, 0x00016f93_00016f9fL, 0x00016fe0_00016fe1L, 0x00016fe3_00016fe3L,
          0x0001e137_0001e13dL, 0x0001e94b_0001e94bL}),
    /** Lo – other letters, including syllables and ideographs */
    OTHER_LETTER("Lo", "Other Letter",
        new int [] {
          0x00aa_00aa, 0x00ba_00ba, 0x01bb_01bb, 0x01c0_01c3, 0x0294_0294, 0x05d0_05ea, 0x05ef_05f2, 0x0620_063f,
          0x0641_064a, 0x066e_066f, 0x0671_06d3, 0x06d5_06d5, 0x06ee_06ef, 0x06fa_06fc, 0x06ff_06ff, 0x0710_0710,
          0x0712_072f, 0x074d_07a5, 0x07b1_07b1, 0x07ca_07ea, 0x0800_0815, 0x0840_0858, 0x0860_086a, 0x08a0_08b4,
          0x08b6_08c7, 0x0904_0939, 0x093d_093d, 0x0950_0950, 0x0958_0961, 0x0972_0980, 0x0985_098c, 0x098f_0990,
          0x0993_09a8, 0x09aa_09b0, 0x09b2_09b2, 0x09b6_09b9, 0x09bd_09bd, 0x09ce_09ce, 0x09dc_09dd, 0x09df_09e1,
          0x09f0_09f1, 0x09fc_09fc, 0x0a05_0a0a, 0x0a0f_0a10, 0x0a13_0a28, 0x0a2a_0a30, 0x0a32_0a33, 0x0a35_0a36,
          0x0a38_0a39, 0x0a59_0a5c, 0x0a5e_0a5e, 0x0a72_0a74, 0x0a85_0a8d, 0x0a8f_0a91, 0x0a93_0aa8, 0x0aaa_0ab0,
          0x0ab2_0ab3, 0x0ab5_0ab9, 0x0abd_0abd, 0x0ad0_0ad0, 0x0ae0_0ae1, 0x0af9_0af9, 0x0b05_0b0c, 0x0b0f_0b10,
          0x0b13_0b28, 0x0b2a_0b30, 0x0b32_0b33, 0x0b35_0b39, 0x0b3d_0b3d, 0x0b5c_0b5d, 0x0b5f_0b61, 0x0b71_0b71,
          0x0b83_0b83, 0x0b85_0b8a, 0x0b8e_0b90, 0x0b92_0b95, 0x0b99_0b9a, 0x0b9c_0b9c, 0x0b9e_0b9f, 0x0ba3_0ba4,
          0x0ba8_0baa, 0x0bae_0bb9, 0x0bd0_0bd0, 0x0c05_0c0c, 0x0c0e_0c10, 0x0c12_0c28, 0x0c2a_0c39, 0x0c3d_0c3d,
          0x0c58_0c5a, 0x0c60_0c61, 0x0c80_0c80, 0x0c85_0c8c, 0x0c8e_0c90, 0x0c92_0ca8, 0x0caa_0cb3, 0x0cb5_0cb9,
          0x0cbd_0cbd, 0x0cde_0cde, 0x0ce0_0ce1, 0x0cf1_0cf2, 0x0d04_0d0c, 0x0d0e_0d10, 0x0d12_0d3a, 0x0d3d_0d3d,
          0x0d4e_0d4e, 0x0d54_0d56, 0x0d5f_0d61, 0x0d7a_0d7f, 0x0d85_0d96, 0x0d9a_0db1, 0x0db3_0dbb, 0x0dbd_0dbd,
          0x0dc0_0dc6, 0x0e01_0e30, 0x0e32_0e33, 0x0e40_0e45, 0x0e81_0e82, 0x0e84_0e84, 0x0e86_0e8a, 0x0e8c_0ea3,
          0x0ea5_0ea5, 0x0ea7_0eb0, 0x0eb2_0eb3, 0x0ebd_0ebd, 0x0ec0_0ec4, 0x0edc_0edf, 0x0f00_0f00, 0x0f40_0f47,
          0x0f49_0f6c, 0x0f88_0f8c, 0x1000_102a, 0x103f_103f, 0x1050_1055, 0x105a_105d, 0x1061_1061, 0x1065_1066,
          0x106e_1070, 0x1075_1081, 0x108e_108e, 0x1100_1248, 0x124a_124d, 0x1250_1256, 0x1258_1258, 0x125a_125d,
          0x1260_1288, 0x128a_128d, 0x1290_12b0, 0x12b2_12b5, 0x12b8_12be, 0x12c0_12c0, 0x12c2_12c5, 0x12c8_12d6,
          0x12d8_1310, 0x1312_1315, 0x1318_135a, 0x1380_138f, 0x1401_166c, 0x166f_167f, 0x1681_169a, 0x16a0_16ea,
          0x16f1_16f8, 0x1700_170c, 0x170e_1711, 0x1720_1731, 0x1740_1751, 0x1760_176c, 0x176e_1770, 0x1780_17b3,
          0x17dc_17dc, 0x1820_1842, 0x1844_1878, 0x1880_1884, 0x1887_18a8, 0x18aa_18aa, 0x18b0_18f5, 0x1900_191e,
          0x1950_196d, 0x1970_1974, 0x1980_19ab, 0x19b0_19c9, 0x1a00_1a16, 0x1a20_1a54, 0x1b05_1b33, 0x1b45_1b4b,
          0x1b83_1ba0, 0x1bae_1baf, 0x1bba_1be5, 0x1c00_1c23, 0x1c4d_1c4f, 0x1c5a_1c77, 0x1ce9_1cec, 0x1cee_1cf3,
          0x1cf5_1cf6, 0x1cfa_1cfa, 0x2135_2138, 0x2d30_2d67, 0x2d80_2d96, 0x2da0_2da6, 0x2da8_2dae, 0x2db0_2db6,
          0x2db8_2dbe, 0x2dc0_2dc6, 0x2dc8_2dce, 0x2dd0_2dd6, 0x2dd8_2dde, 0x3006_3006, 0x303c_303c, 0x3041_3096,
          0x309f_309f, 0x30a1_30fa, 0x30ff_30ff, 0x3105_312f, 0x3131_318e, 0x31a0_31bf, 0x31f0_31ff, 0x3400_4dbf,
          0x4e00_9ffc, 0xa000_a014, 0xa016_a48c, 0xa4d0_a4f7, 0xa500_a60b, 0xa610_a61f, 0xa62a_a62b, 0xa66e_a66e,
          0xa6a0_a6e5, 0xa78f_a78f, 0xa7f7_a7f7, 0xa7fb_a801, 0xa803_a805, 0xa807_a80a, 0xa80c_a822, 0xa840_a873,
          0xa882_a8b3, 0xa8f2_a8f7, 0xa8fb_a8fb, 0xa8fd_a8fe, 0xa90a_a925, 0xa930_a946, 0xa960_a97c, 0xa984_a9b2,
          0xa9e0_a9e4, 0xa9e7_a9ef, 0xa9fa_a9fe, 0xaa00_aa28, 0xaa40_aa42, 0xaa44_aa4b, 0xaa60_aa6f, 0xaa71_aa76,
          0xaa7a_aa7a, 0xaa7e_aaaf, 0xaab1_aab1, 0xaab5_aab6, 0xaab9_aabd, 0xaac0_aac0, 0xaac2_aac2, 0xaadb_aadc,
          0xaae0_aaea, 0xaaf2_aaf2, 0xab01_ab06, 0xab09_ab0e, 0xab11_ab16, 0xab20_ab26, 0xab28_ab2e, 0xabc0_abe2,
          0xac00_d7a3, 0xd7b0_d7c6, 0xd7cb_d7fb, 0xf900_fa6d, 0xfa70_fad9, 0xfb1d_fb1d, 0xfb1f_fb28, 0xfb2a_fb36,
          0xfb38_fb3c, 0xfb3e_fb3e, 0xfb40_fb41, 0xfb43_fb44, 0xfb46_fbb1, 0xfbd3_fd3d, 0xfd50_fd8f, 0xfd92_fdc7,
          0xfdf0_fdfb, 0xfe70_fe74, 0xfe76_fefc, 0xff66_ff6f, 0xff71_ff9d, 0xffa0_ffbe, 0xffc2_ffc7, 0xffca_ffcf,
          0xffd2_ffd7, 0xffda_ffdc},
        new long [] { 
          0x00010000_0001000bL, 0x0001000d_00010026L, 0x00010028_0001003aL, 0x0001003c_0001003dL,
          0x0001003f_0001004dL, 0x00010050_0001005dL, 0x00010080_000100faL, 0x00010280_0001029cL,
          0x000102a0_000102d0L, 0x00010300_0001031fL, 0x0001032d_00010340L, 0x00010342_00010349L,
          0x00010350_00010375L, 0x00010380_0001039dL, 0x000103a0_000103c3L, 0x000103c8_000103cfL,
          0x00010450_0001049dL, 0x00010500_00010527L, 0x00010530_00010563L, 0x00010600_00010736L,
          0x00010740_00010755L, 0x00010760_00010767L, 0x00010800_00010805L, 0x00010808_00010808L,
          0x0001080a_00010835L, 0x00010837_00010838L, 0x0001083c_0001083cL, 0x0001083f_00010855L,
          0x00010860_00010876L, 0x00010880_0001089eL, 0x000108e0_000108f2L, 0x000108f4_000108f5L,
          0x00010900_00010915L, 0x00010920_00010939L, 0x00010980_000109b7L, 0x000109be_000109bfL,
          0x00010a00_00010a00L, 0x00010a10_00010a13L, 0x00010a15_00010a17L, 0x00010a19_00010a35L,
          0x00010a60_00010a7cL, 0x00010a80_00010a9cL, 0x00010ac0_00010ac7L, 0x00010ac9_00010ae4L,
          0x00010b00_00010b35L, 0x00010b40_00010b55L, 0x00010b60_00010b72L, 0x00010b80_00010b91L,
          0x00010c00_00010c48L, 0x00010d00_00010d23L, 0x00010e80_00010ea9L, 0x00010eb0_00010eb1L,
          0x00010f00_00010f1cL, 0x00010f27_00010f27L, 0x00010f30_00010f45L, 0x00010fb0_00010fc4L,
          0x00010fe0_00010ff6L, 0x00011003_00011037L, 0x00011083_000110afL, 0x000110d0_000110e8L,
          0x00011103_00011126L, 0x00011144_00011144L, 0x00011147_00011147L, 0x00011150_00011172L,
          0x00011176_00011176L, 0x00011183_000111b2L, 0x000111c1_000111c4L, 0x000111da_000111daL,
          0x000111dc_000111dcL, 0x00011200_00011211L, 0x00011213_0001122bL, 0x00011280_00011286L,
          0x00011288_00011288L, 0x0001128a_0001128dL, 0x0001128f_0001129dL, 0x0001129f_000112a8L,
          0x000112b0_000112deL, 0x00011305_0001130cL, 0x0001130f_00011310L, 0x00011313_00011328L,
          0x0001132a_00011330L, 0x00011332_00011333L, 0x00011335_00011339L, 0x0001133d_0001133dL,
          0x00011350_00011350L, 0x0001135d_00011361L, 0x00011400_00011434L, 0x00011447_0001144aL,
          0x0001145f_00011461L, 0x00011480_000114afL, 0x000114c4_000114c5L, 0x000114c7_000114c7L,
          0x00011580_000115aeL, 0x000115d8_000115dbL, 0x00011600_0001162fL, 0x00011644_00011644L,
          0x00011680_000116aaL, 0x000116b8_000116b8L, 0x00011700_0001171aL, 0x00011800_0001182bL,
          0x000118ff_00011906L, 0x00011909_00011909L, 0x0001190c_00011913L, 0x00011915_00011916L,
          0x00011918_0001192fL, 0x0001193f_0001193fL, 0x00011941_00011941L, 0x000119a0_000119a7L,
          0x000119aa_000119d0L, 0x000119e1_000119e1L, 0x000119e3_000119e3L, 0x00011a00_00011a00L,
          0x00011a0b_00011a32L, 0x00011a3a_00011a3aL, 0x00011a50_00011a50L, 0x00011a5c_00011a89L,
          0x00011a9d_00011a9dL, 0x00011ac0_00011af8L, 0x00011c00_00011c08L, 0x00011c0a_00011c2eL,
          0x00011c40_00011c40L, 0x00011c72_00011c8fL, 0x00011d00_00011d06L, 0x00011d08_00011d09L,
          0x00011d0b_00011d30L, 0x00011d46_00011d46L, 0x00011d60_00011d65L, 0x00011d67_00011d68L,
          0x00011d6a_00011d89L, 0x00011d98_00011d98L, 0x00011ee0_00011ef2L, 0x00011fb0_00011fb0L,
          0x00012000_00012399L, 0x00012480_00012543L, 0x00013000_0001342eL, 0x00014400_00014646L,
          0x00016800_00016a38L, 0x00016a40_00016a5eL, 0x00016ad0_00016aedL, 0x00016b00_00016b2fL,
          0x00016b63_00016b77L, 0x00016b7d_00016b8fL, 0x00016f00_00016f4aL, 0x00016f50_00016f50L,
          0x00017000_000187f7L, 0x00018800_00018cd5L, 0x00018d00_00018d08L, 0x0001b000_0001b11eL,
          0x0001b150_0001b152L, 0x0001b164_0001b167L, 0x0001b170_0001b2fbL, 0x0001bc00_0001bc6aL,
          0x0001bc70_0001bc7cL, 0x0001bc80_0001bc88L, 0x0001bc90_0001bc99L, 0x0001e100_0001e12cL,
          0x0001e14e_0001e14eL, 0x0001e2c0_0001e2ebL, 0x0001e800_0001e8c4L, 0x0001ee00_0001ee03L,
          0x0001ee05_0001ee1fL, 0x0001ee21_0001ee22L, 0x0001ee24_0001ee24L, 0x0001ee27_0001ee27L,
          0x0001ee29_0001ee32L, 0x0001ee34_0001ee37L, 0x0001ee39_0001ee39L, 0x0001ee3b_0001ee3bL,
          0x0001ee42_0001ee42L, 0x0001ee47_0001ee47L, 0x0001ee49_0001ee49L, 0x0001ee4b_0001ee4bL,
          0x0001ee4d_0001ee4fL, 0x0001ee51_0001ee52L, 0x0001ee54_0001ee54L, 0x0001ee57_0001ee57L,
          0x0001ee59_0001ee59L, 0x0001ee5b_0001ee5bL, 0x0001ee5d_0001ee5dL, 0x0001ee5f_0001ee5fL,
          0x0001ee61_0001ee62L, 0x0001ee64_0001ee64L, 0x0001ee67_0001ee6aL, 0x0001ee6c_0001ee72L,
          0x0001ee74_0001ee77L, 0x0001ee79_0001ee7cL, 0x0001ee7e_0001ee7eL, 0x0001ee80_0001ee89L,
          0x0001ee8b_0001ee9bL, 0x0001eea1_0001eea3L, 0x0001eea5_0001eea9L, 0x0001eeab_0001eebbL,
          0x00020000_0002a6ddL, 0x0002a700_0002b734L, 0x0002b740_0002b81dL, 0x0002b820_0002cea1L,
          0x0002ceb0_0002ebe0L, 0x0002f800_0002fa1dL, 0x00030000_0003134aL}),
    /** L – Lu | Ll | Lt | Lm | Lo */
    LETTER("L", "Letter",
        new int [] {
          0x0041_005a, 0x0061_007a, 0x00aa_00aa, 0x00b5_00b5, 0x00ba_00ba, 0x00c0_00d6, 0x00d8_00f6, 0x00f8_02c1,
          0x02c6_02d1, 0x02e0_02e4, 0x02ec_02ec, 0x02ee_02ee, 0x0370_0374, 0x0376_0377, 0x037a_037d, 0x037f_037f,
          0x0386_0386, 0x0388_038a, 0x038c_038c, 0x038e_03a1, 0x03a3_03f5, 0x03f7_0481, 0x048a_052f, 0x0531_0556,
          0x0559_0559, 0x0560_0588, 0x05d0_05ea, 0x05ef_05f2, 0x0620_064a, 0x066e_066f, 0x0671_06d3, 0x06d5_06d5,
          0x06e5_06e6, 0x06ee_06ef, 0x06fa_06fc, 0x06ff_06ff, 0x0710_0710, 0x0712_072f, 0x074d_07a5, 0x07b1_07b1,
          0x07ca_07ea, 0x07f4_07f5, 0x07fa_07fa, 0x0800_0815, 0x081a_081a, 0x0824_0824, 0x0828_0828, 0x0840_0858,
          0x0860_086a, 0x08a0_08b4, 0x08b6_08c7, 0x0904_0939, 0x093d_093d, 0x0950_0950, 0x0958_0961, 0x0971_0980,
          0x0985_098c, 0x098f_0990, 0x0993_09a8, 0x09aa_09b0, 0x09b2_09b2, 0x09b6_09b9, 0x09bd_09bd, 0x09ce_09ce,
          0x09dc_09dd, 0x09df_09e1, 0x09f0_09f1, 0x09fc_09fc, 0x0a05_0a0a, 0x0a0f_0a10, 0x0a13_0a28, 0x0a2a_0a30,
          0x0a32_0a33, 0x0a35_0a36, 0x0a38_0a39, 0x0a59_0a5c, 0x0a5e_0a5e, 0x0a72_0a74, 0x0a85_0a8d, 0x0a8f_0a91,
          0x0a93_0aa8, 0x0aaa_0ab0, 0x0ab2_0ab3, 0x0ab5_0ab9, 0x0abd_0abd, 0x0ad0_0ad0, 0x0ae0_0ae1, 0x0af9_0af9,
          0x0b05_0b0c, 0x0b0f_0b10, 0x0b13_0b28, 0x0b2a_0b30, 0x0b32_0b33, 0x0b35_0b39, 0x0b3d_0b3d, 0x0b5c_0b5d,
          0x0b5f_0b61, 0x0b71_0b71, 0x0b83_0b83, 0x0b85_0b8a, 0x0b8e_0b90, 0x0b92_0b95, 0x0b99_0b9a, 0x0b9c_0b9c,
          0x0b9e_0b9f, 0x0ba3_0ba4, 0x0ba8_0baa, 0x0bae_0bb9, 0x0bd0_0bd0, 0x0c05_0c0c, 0x0c0e_0c10, 0x0c12_0c28,
          0x0c2a_0c39, 0x0c3d_0c3d, 0x0c58_0c5a, 0x0c60_0c61, 0x0c80_0c80, 0x0c85_0c8c, 0x0c8e_0c90, 0x0c92_0ca8,
          0x0caa_0cb3, 0x0cb5_0cb9, 0x0cbd_0cbd, 0x0cde_0cde, 0x0ce0_0ce1, 0x0cf1_0cf2, 0x0d04_0d0c, 0x0d0e_0d10,
          0x0d12_0d3a, 0x0d3d_0d3d, 0x0d4e_0d4e, 0x0d54_0d56, 0x0d5f_0d61, 0x0d7a_0d7f, 0x0d85_0d96, 0x0d9a_0db1,
          0x0db3_0dbb, 0x0dbd_0dbd, 0x0dc0_0dc6, 0x0e01_0e30, 0x0e32_0e33, 0x0e40_0e46, 0x0e81_0e82, 0x0e84_0e84,
          0x0e86_0e8a, 0x0e8c_0ea3, 0x0ea5_0ea5, 0x0ea7_0eb0, 0x0eb2_0eb3, 0x0ebd_0ebd, 0x0ec0_0ec4, 0x0ec6_0ec6,
          0x0edc_0edf, 0x0f00_0f00, 0x0f40_0f47, 0x0f49_0f6c, 0x0f88_0f8c, 0x1000_102a, 0x103f_103f, 0x1050_1055,
          0x105a_105d, 0x1061_1061, 0x1065_1066, 0x106e_1070, 0x1075_1081, 0x108e_108e, 0x10a0_10c5, 0x10c7_10c7,
          0x10cd_10cd, 0x10d0_10fa, 0x10fc_1248, 0x124a_124d, 0x1250_1256, 0x1258_1258, 0x125a_125d, 0x1260_1288,
          0x128a_128d, 0x1290_12b0, 0x12b2_12b5, 0x12b8_12be, 0x12c0_12c0, 0x12c2_12c5, 0x12c8_12d6, 0x12d8_1310,
          0x1312_1315, 0x1318_135a, 0x1380_138f, 0x13a0_13f5, 0x13f8_13fd, 0x1401_166c, 0x166f_167f, 0x1681_169a,
          0x16a0_16ea, 0x16f1_16f8, 0x1700_170c, 0x170e_1711, 0x1720_1731, 0x1740_1751, 0x1760_176c, 0x176e_1770,
          0x1780_17b3, 0x17d7_17d7, 0x17dc_17dc, 0x1820_1878, 0x1880_1884, 0x1887_18a8, 0x18aa_18aa, 0x18b0_18f5,
          0x1900_191e, 0x1950_196d, 0x1970_1974, 0x1980_19ab, 0x19b0_19c9, 0x1a00_1a16, 0x1a20_1a54, 0x1aa7_1aa7,
          0x1b05_1b33, 0x1b45_1b4b, 0x1b83_1ba0, 0x1bae_1baf, 0x1bba_1be5, 0x1c00_1c23, 0x1c4d_1c4f, 0x1c5a_1c7d,
          0x1c80_1c88, 0x1c90_1cba, 0x1cbd_1cbf, 0x1ce9_1cec, 0x1cee_1cf3, 0x1cf5_1cf6, 0x1cfa_1cfa, 0x1d00_1dbf,
          0x1e00_1f15, 0x1f18_1f1d, 0x1f20_1f45, 0x1f48_1f4d, 0x1f50_1f57, 0x1f59_1f59, 0x1f5b_1f5b, 0x1f5d_1f5d,
          0x1f5f_1f7d, 0x1f80_1fb4, 0x1fb6_1fbc, 0x1fbe_1fbe, 0x1fc2_1fc4, 0x1fc6_1fcc, 0x1fd0_1fd3, 0x1fd6_1fdb,
          0x1fe0_1fec, 0x1ff2_1ff4, 0x1ff6_1ffc, 0x2071_2071, 0x207f_207f, 0x2090_209c, 0x2102_2102, 0x2107_2107,
          0x210a_2113, 0x2115_2115, 0x2119_211d, 0x2124_2124, 0x2126_2126, 0x2128_2128, 0x212a_212d, 0x212f_2139,
          0x213c_213f, 0x2145_2149, 0x214e_214e, 0x2183_2184, 0x2c00_2c2e, 0x2c30_2c5e, 0x2c60_2ce4, 0x2ceb_2cee,
          0x2cf2_2cf3, 0x2d00_2d25, 0x2d27_2d27, 0x2d2d_2d2d, 0x2d30_2d67, 0x2d6f_2d6f, 0x2d80_2d96, 0x2da0_2da6,
          0x2da8_2dae, 0x2db0_2db6, 0x2db8_2dbe, 0x2dc0_2dc6, 0x2dc8_2dce, 0x2dd0_2dd6, 0x2dd8_2dde, 0x2e2f_2e2f,
          0x3005_3006, 0x3031_3035, 0x303b_303c, 0x3041_3096, 0x309d_309f, 0x30a1_30fa, 0x30fc_30ff, 0x3105_312f,
          0x3131_318e, 0x31a0_31bf, 0x31f0_31ff, 0x3400_4dbf, 0x4e00_9ffc, 0xa000_a48c, 0xa4d0_a4fd, 0xa500_a60c,
          0xa610_a61f, 0xa62a_a62b, 0xa640_a66e, 0xa67f_a69d, 0xa6a0_a6e5, 0xa717_a71f, 0xa722_a788, 0xa78b_a7bf,
          0xa7c2_a7ca, 0xa7f5_a801, 0xa803_a805, 0xa807_a80a, 0xa80c_a822, 0xa840_a873, 0xa882_a8b3, 0xa8f2_a8f7,
          0xa8fb_a8fb, 0xa8fd_a8fe, 0xa90a_a925, 0xa930_a946, 0xa960_a97c, 0xa984_a9b2, 0xa9cf_a9cf, 0xa9e0_a9e4,
          0xa9e6_a9ef, 0xa9fa_a9fe, 0xaa00_aa28, 0xaa40_aa42, 0xaa44_aa4b, 0xaa60_aa76, 0xaa7a_aa7a, 0xaa7e_aaaf,
          0xaab1_aab1, 0xaab5_aab6, 0xaab9_aabd, 0xaac0_aac0, 0xaac2_aac2, 0xaadb_aadd, 0xaae0_aaea, 0xaaf2_aaf4,
          0xab01_ab06, 0xab09_ab0e, 0xab11_ab16, 0xab20_ab26, 0xab28_ab2e, 0xab30_ab5a, 0xab5c_ab69, 0xab70_abe2,
          0xac00_d7a3, 0xd7b0_d7c6, 0xd7cb_d7fb, 0xf900_fa6d, 0xfa70_fad9, 0xfb00_fb06, 0xfb13_fb17, 0xfb1d_fb1d,
          0xfb1f_fb28, 0xfb2a_fb36, 0xfb38_fb3c, 0xfb3e_fb3e, 0xfb40_fb41, 0xfb43_fb44, 0xfb46_fbb1, 0xfbd3_fd3d,
          0xfd50_fd8f, 0xfd92_fdc7, 0xfdf0_fdfb, 0xfe70_fe74, 0xfe76_fefc, 0xff21_ff3a, 0xff41_ff5a, 0xff66_ffbe,
          0xffc2_ffc7, 0xffca_ffcf, 0xffd2_ffd7, 0xffda_ffdc},
        new long [] { 
          0x00010000_0001000bL, 0x0001000d_00010026L, 0x00010028_0001003aL, 0x0001003c_0001003dL,
          0x0001003f_0001004dL, 0x00010050_0001005dL, 0x00010080_000100faL, 0x00010280_0001029cL,
          0x000102a0_000102d0L, 0x00010300_0001031fL, 0x0001032d_00010340L, 0x00010342_00010349L,
          0x00010350_00010375L, 0x00010380_0001039dL, 0x000103a0_000103c3L, 0x000103c8_000103cfL,
          0x00010400_0001049dL, 0x000104b0_000104d3L, 0x000104d8_000104fbL, 0x00010500_00010527L,
          0x00010530_00010563L, 0x00010600_00010736L, 0x00010740_00010755L, 0x00010760_00010767L,
          0x00010800_00010805L, 0x00010808_00010808L, 0x0001080a_00010835L, 0x00010837_00010838L,
          0x0001083c_0001083cL, 0x0001083f_00010855L, 0x00010860_00010876L, 0x00010880_0001089eL,
          0x000108e0_000108f2L, 0x000108f4_000108f5L, 0x00010900_00010915L, 0x00010920_00010939L,
          0x00010980_000109b7L, 0x000109be_000109bfL, 0x00010a00_00010a00L, 0x00010a10_00010a13L,
          0x00010a15_00010a17L, 0x00010a19_00010a35L, 0x00010a60_00010a7cL, 0x00010a80_00010a9cL,
          0x00010ac0_00010ac7L, 0x00010ac9_00010ae4L, 0x00010b00_00010b35L, 0x00010b40_00010b55L,
          0x00010b60_00010b72L, 0x00010b80_00010b91L, 0x00010c00_00010c48L, 0x00010c80_00010cb2L,
          0x00010cc0_00010cf2L, 0x00010d00_00010d23L, 0x00010e80_00010ea9L, 0x00010eb0_00010eb1L,
          0x00010f00_00010f1cL, 0x00010f27_00010f27L, 0x00010f30_00010f45L, 0x00010fb0_00010fc4L,
          0x00010fe0_00010ff6L, 0x00011003_00011037L, 0x00011083_000110afL, 0x000110d0_000110e8L,
          0x00011103_00011126L, 0x00011144_00011144L, 0x00011147_00011147L, 0x00011150_00011172L,
          0x00011176_00011176L, 0x00011183_000111b2L, 0x000111c1_000111c4L, 0x000111da_000111daL,
          0x000111dc_000111dcL, 0x00011200_00011211L, 0x00011213_0001122bL, 0x00011280_00011286L,
          0x00011288_00011288L, 0x0001128a_0001128dL, 0x0001128f_0001129dL, 0x0001129f_000112a8L,
          0x000112b0_000112deL, 0x00011305_0001130cL, 0x0001130f_00011310L, 0x00011313_00011328L,
          0x0001132a_00011330L, 0x00011332_00011333L, 0x00011335_00011339L, 0x0001133d_0001133dL,
          0x00011350_00011350L, 0x0001135d_00011361L, 0x00011400_00011434L, 0x00011447_0001144aL,
          0x0001145f_00011461L, 0x00011480_000114afL, 0x000114c4_000114c5L, 0x000114c7_000114c7L,
          0x00011580_000115aeL, 0x000115d8_000115dbL, 0x00011600_0001162fL, 0x00011644_00011644L,
          0x00011680_000116aaL, 0x000116b8_000116b8L, 0x00011700_0001171aL, 0x00011800_0001182bL,
          0x000118a0_000118dfL, 0x000118ff_00011906L, 0x00011909_00011909L, 0x0001190c_00011913L,
          0x00011915_00011916L, 0x00011918_0001192fL, 0x0001193f_0001193fL, 0x00011941_00011941L,
          0x000119a0_000119a7L, 0x000119aa_000119d0L, 0x000119e1_000119e1L, 0x000119e3_000119e3L,
          0x00011a00_00011a00L, 0x00011a0b_00011a32L, 0x00011a3a_00011a3aL, 0x00011a50_00011a50L,
          0x00011a5c_00011a89L, 0x00011a9d_00011a9dL, 0x00011ac0_00011af8L, 0x00011c00_00011c08L,
          0x00011c0a_00011c2eL, 0x00011c40_00011c40L, 0x00011c72_00011c8fL, 0x00011d00_00011d06L,
          0x00011d08_00011d09L, 0x00011d0b_00011d30L, 0x00011d46_00011d46L, 0x00011d60_00011d65L,
          0x00011d67_00011d68L, 0x00011d6a_00011d89L, 0x00011d98_00011d98L, 0x00011ee0_00011ef2L,
          0x00011fb0_00011fb0L, 0x00012000_00012399L, 0x00012480_00012543L, 0x00013000_0001342eL,
          0x00014400_00014646L, 0x00016800_00016a38L, 0x00016a40_00016a5eL, 0x00016ad0_00016aedL,
          0x00016b00_00016b2fL, 0x00016b40_00016b43L, 0x00016b63_00016b77L, 0x00016b7d_00016b8fL,
          0x00016e40_00016e7fL, 0x00016f00_00016f4aL, 0x00016f50_00016f50L, 0x00016f93_00016f9fL,
          0x00016fe0_00016fe1L, 0x00016fe3_00016fe3L, 0x00017000_000187f7L, 0x00018800_00018cd5L,
          0x00018d00_00018d08L, 0x0001b000_0001b11eL, 0x0001b150_0001b152L, 0x0001b164_0001b167L,
          0x0001b170_0001b2fbL, 0x0001bc00_0001bc6aL, 0x0001bc70_0001bc7cL, 0x0001bc80_0001bc88L,
          0x0001bc90_0001bc99L, 0x0001d400_0001d454L, 0x0001d456_0001d49cL, 0x0001d49e_0001d49fL,
          0x0001d4a2_0001d4a2L, 0x0001d4a5_0001d4a6L, 0x0001d4a9_0001d4acL, 0x0001d4ae_0001d4b9L,
          0x0001d4bb_0001d4bbL, 0x0001d4bd_0001d4c3L, 0x0001d4c5_0001d505L, 0x0001d507_0001d50aL,
          0x0001d50d_0001d514L, 0x0001d516_0001d51cL, 0x0001d51e_0001d539L, 0x0001d53b_0001d53eL,
          0x0001d540_0001d544L, 0x0001d546_0001d546L, 0x0001d54a_0001d550L, 0x0001d552_0001d6a5L,
          0x0001d6a8_0001d6c0L, 0x0001d6c2_0001d6daL, 0x0001d6dc_0001d6faL, 0x0001d6fc_0001d714L,
          0x0001d716_0001d734L, 0x0001d736_0001d74eL, 0x0001d750_0001d76eL, 0x0001d770_0001d788L,
          0x0001d78a_0001d7a8L, 0x0001d7aa_0001d7c2L, 0x0001d7c4_0001d7cbL, 0x0001e100_0001e12cL,
          0x0001e137_0001e13dL, 0x0001e14e_0001e14eL, 0x0001e2c0_0001e2ebL, 0x0001e800_0001e8c4L,
          0x0001e900_0001e943L, 0x0001e94b_0001e94bL, 0x0001ee00_0001ee03L, 0x0001ee05_0001ee1fL,
          0x0001ee21_0001ee22L, 0x0001ee24_0001ee24L, 0x0001ee27_0001ee27L, 0x0001ee29_0001ee32L,
          0x0001ee34_0001ee37L, 0x0001ee39_0001ee39L, 0x0001ee3b_0001ee3bL, 0x0001ee42_0001ee42L,
          0x0001ee47_0001ee47L, 0x0001ee49_0001ee49L, 0x0001ee4b_0001ee4bL, 0x0001ee4d_0001ee4fL,
          0x0001ee51_0001ee52L, 0x0001ee54_0001ee54L, 0x0001ee57_0001ee57L, 0x0001ee59_0001ee59L,
          0x0001ee5b_0001ee5bL, 0x0001ee5d_0001ee5dL, 0x0001ee5f_0001ee5fL, 0x0001ee61_0001ee62L,
          0x0001ee64_0001ee64L, 0x0001ee67_0001ee6aL, 0x0001ee6c_0001ee72L, 0x0001ee74_0001ee77L,
          0x0001ee79_0001ee7cL, 0x0001ee7e_0001ee7eL, 0x0001ee80_0001ee89L, 0x0001ee8b_0001ee9bL,
          0x0001eea1_0001eea3L, 0x0001eea5_0001eea9L, 0x0001eeab_0001eebbL, 0x00020000_0002a6ddL,
          0x0002a700_0002b734L, 0x0002b740_0002b81dL, 0x0002b820_0002cea1L, 0x0002ceb0_0002ebe0L,
          0x0002f800_0002fa1dL, 0x00030000_0003134aL}),
    /** Mn – a nonspacing combining mark (zero advance width) */
    NONSPACING_MARK("Mn", "Nonspacing Mark",
        new int [] {
          0x0300_036f, 0x0483_0487, 0x0591_05bd, 0x05bf_05bf, 0x05c1_05c2, 0x05c4_05c5, 0x05c7_05c7, 0x0610_061a,
          0x064b_065f, 0x0670_0670, 0x06d6_06dc, 0x06df_06e4, 0x06e7_06e8, 0x06ea_06ed, 0x0711_0711, 0x0730_074a,
          0x07a6_07b0, 0x07eb_07f3, 0x07fd_07fd, 0x0816_0819, 0x081b_0823, 0x0825_0827, 0x0829_082d, 0x0859_085b,
          0x08d3_08e1, 0x08e3_0902, 0x093a_093a, 0x093c_093c, 0x0941_0948, 0x094d_094d, 0x0951_0957, 0x0962_0963,
          0x0981_0981, 0x09bc_09bc, 0x09c1_09c4, 0x09cd_09cd, 0x09e2_09e3, 0x09fe_09fe, 0x0a01_0a02, 0x0a3c_0a3c,
          0x0a41_0a42, 0x0a47_0a48, 0x0a4b_0a4d, 0x0a51_0a51, 0x0a70_0a71, 0x0a75_0a75, 0x0a81_0a82, 0x0abc_0abc,
          0x0ac1_0ac5, 0x0ac7_0ac8, 0x0acd_0acd, 0x0ae2_0ae3, 0x0afa_0aff, 0x0b01_0b01, 0x0b3c_0b3c, 0x0b3f_0b3f,
          0x0b41_0b44, 0x0b4d_0b4d, 0x0b55_0b56, 0x0b62_0b63, 0x0b82_0b82, 0x0bc0_0bc0, 0x0bcd_0bcd, 0x0c00_0c00,
          0x0c04_0c04, 0x0c3e_0c40, 0x0c46_0c48, 0x0c4a_0c4d, 0x0c55_0c56, 0x0c62_0c63, 0x0c81_0c81, 0x0cbc_0cbc,
          0x0cbf_0cbf, 0x0cc6_0cc6, 0x0ccc_0ccd, 0x0ce2_0ce3, 0x0d00_0d01, 0x0d3b_0d3c, 0x0d41_0d44, 0x0d4d_0d4d,
          0x0d62_0d63, 0x0d81_0d81, 0x0dca_0dca, 0x0dd2_0dd4, 0x0dd6_0dd6, 0x0e31_0e31, 0x0e34_0e3a, 0x0e47_0e4e,
          0x0eb1_0eb1, 0x0eb4_0ebc, 0x0ec8_0ecd, 0x0f18_0f19, 0x0f35_0f35, 0x0f37_0f37, 0x0f39_0f39, 0x0f71_0f7e,
          0x0f80_0f84, 0x0f86_0f87, 0x0f8d_0f97, 0x0f99_0fbc, 0x0fc6_0fc6, 0x102d_1030, 0x1032_1037, 0x1039_103a,
          0x103d_103e, 0x1058_1059, 0x105e_1060, 0x1071_1074, 0x1082_1082, 0x1085_1086, 0x108d_108d, 0x109d_109d,
          0x135d_135f, 0x1712_1714, 0x1732_1734, 0x1752_1753, 0x1772_1773, 0x17b4_17b5, 0x17b7_17bd, 0x17c6_17c6,
          0x17c9_17d3, 0x17dd_17dd, 0x180b_180d, 0x1885_1886, 0x18a9_18a9, 0x1920_1922, 0x1927_1928, 0x1932_1932,
          0x1939_193b, 0x1a17_1a18, 0x1a1b_1a1b, 0x1a56_1a56, 0x1a58_1a5e, 0x1a60_1a60, 0x1a62_1a62, 0x1a65_1a6c,
          0x1a73_1a7c, 0x1a7f_1a7f, 0x1ab0_1abd, 0x1abf_1ac0, 0x1b00_1b03, 0x1b34_1b34, 0x1b36_1b3a, 0x1b3c_1b3c,
          0x1b42_1b42, 0x1b6b_1b73, 0x1b80_1b81, 0x1ba2_1ba5, 0x1ba8_1ba9, 0x1bab_1bad, 0x1be6_1be6, 0x1be8_1be9,
          0x1bed_1bed, 0x1bef_1bf1, 0x1c2c_1c33, 0x1c36_1c37, 0x1cd0_1cd2, 0x1cd4_1ce0, 0x1ce2_1ce8, 0x1ced_1ced,
          0x1cf4_1cf4, 0x1cf8_1cf9, 0x1dc0_1df9, 0x1dfb_1dff, 0x20d0_20dc, 0x20e1_20e1, 0x20e5_20f0, 0x2cef_2cf1,
          0x2d7f_2d7f, 0x2de0_2dff, 0x302a_302d, 0x3099_309a, 0xa66f_a66f, 0xa674_a67d, 0xa69e_a69f, 0xa6f0_a6f1,
          0xa802_a802, 0xa806_a806, 0xa80b_a80b, 0xa825_a826, 0xa82c_a82c, 0xa8c4_a8c5, 0xa8e0_a8f1, 0xa8ff_a8ff,
          0xa926_a92d, 0xa947_a951, 0xa980_a982, 0xa9b3_a9b3, 0xa9b6_a9b9, 0xa9bc_a9bd, 0xa9e5_a9e5, 0xaa29_aa2e,
          0xaa31_aa32, 0xaa35_aa36, 0xaa43_aa43, 0xaa4c_aa4c, 0xaa7c_aa7c, 0xaab0_aab0, 0xaab2_aab4, 0xaab7_aab8,
          0xaabe_aabf, 0xaac1_aac1, 0xaaec_aaed, 0xaaf6_aaf6, 0xabe5_abe5, 0xabe8_abe8, 0xabed_abed, 0xfb1e_fb1e,
          0xfe00_fe0f, 0xfe20_fe2f},
        new long [] { 
          0x000101fd_000101fdL, 0x000102e0_000102e0L, 0x00010376_0001037aL, 0x00010a01_00010a03L,
          0x00010a05_00010a06L, 0x00010a0c_00010a0fL, 0x00010a38_00010a3aL, 0x00010a3f_00010a3fL,
          0x00010ae5_00010ae6L, 0x00010d24_00010d27L, 0x00010eab_00010eacL, 0x00010f46_00010f50L,
          0x00011001_00011001L, 0x00011038_00011046L, 0x0001107f_00011081L, 0x000110b3_000110b6L,
          0x000110b9_000110baL, 0x00011100_00011102L, 0x00011127_0001112bL, 0x0001112d_00011134L,
          0x00011173_00011173L, 0x00011180_00011181L, 0x000111b6_000111beL, 0x000111c9_000111ccL,
          0x000111cf_000111cfL, 0x0001122f_00011231L, 0x00011234_00011234L, 0x00011236_00011237L,
          0x0001123e_0001123eL, 0x000112df_000112dfL, 0x000112e3_000112eaL, 0x00011300_00011301L,
          0x0001133b_0001133cL, 0x00011340_00011340L, 0x00011366_0001136cL, 0x00011370_00011374L,
          0x00011438_0001143fL, 0x00011442_00011444L, 0x00011446_00011446L, 0x0001145e_0001145eL,
          0x000114b3_000114b8L, 0x000114ba_000114baL, 0x000114bf_000114c0L, 0x000114c2_000114c3L,
          0x000115b2_000115b5L, 0x000115bc_000115bdL, 0x000115bf_000115c0L, 0x000115dc_000115ddL,
          0x00011633_0001163aL, 0x0001163d_0001163dL, 0x0001163f_00011640L, 0x000116ab_000116abL,
          0x000116ad_000116adL, 0x000116b0_000116b5L, 0x000116b7_000116b7L, 0x0001171d_0001171fL,
          0x00011722_00011725L, 0x00011727_0001172bL, 0x0001182f_00011837L, 0x00011839_0001183aL,
          0x0001193b_0001193cL, 0x0001193e_0001193eL, 0x00011943_00011943L, 0x000119d4_000119d7L,
          0x000119da_000119dbL, 0x000119e0_000119e0L, 0x00011a01_00011a0aL, 0x00011a33_00011a38L,
          0x00011a3b_00011a3eL, 0x00011a47_00011a47L, 0x00011a51_00011a56L, 0x00011a59_00011a5bL,
          0x00011a8a_00011a96L, 0x00011a98_00011a99L, 0x00011c30_00011c36L, 0x00011c38_00011c3dL,
          0x00011c3f_00011c3fL, 0x00011c92_00011ca7L, 0x00011caa_00011cb0L, 0x00011cb2_00011cb3L,
          0x00011cb5_00011cb6L, 0x00011d31_00011d36L, 0x00011d3a_00011d3aL, 0x00011d3c_00011d3dL,
          0x00011d3f_00011d45L, 0x00011d47_00011d47L, 0x00011d90_00011d91L, 0x00011d95_00011d95L,
          0x00011d97_00011d97L, 0x00011ef3_00011ef4L, 0x00016af0_00016af4L, 0x00016b30_00016b36L,
          0x00016f4f_00016f4fL, 0x00016f8f_00016f92L, 0x00016fe4_00016fe4L, 0x0001bc9d_0001bc9eL,
          0x0001d167_0001d169L, 0x0001d17b_0001d182L, 0x0001d185_0001d18bL, 0x0001d1aa_0001d1adL,
          0x0001d242_0001d244L, 0x0001da00_0001da36L, 0x0001da3b_0001da6cL, 0x0001da75_0001da75L,
          0x0001da84_0001da84L, 0x0001da9b_0001da9fL, 0x0001daa1_0001daafL, 0x0001e000_0001e006L,
          0x0001e008_0001e018L, 0x0001e01b_0001e021L, 0x0001e023_0001e024L, 0x0001e026_0001e02aL,
          0x0001e130_0001e136L, 0x0001e2ec_0001e2efL, 0x0001e8d0_0001e8d6L, 0x0001e944_0001e94aL,
          0x000e0100_000e01efL}),
    /** Mc – a spacing combining mark (positive advance width) */
    SPACING_MARK("Mc", "Spacing Mark",
        new int [] {
          0x0903_0903, 0x093b_093b, 0x093e_0940, 0x0949_094c, 0x094e_094f, 0x0982_0983, 0x09be_09c0, 0x09c7_09c8,
          0x09cb_09cc, 0x09d7_09d7, 0x0a03_0a03, 0x0a3e_0a40, 0x0a83_0a83, 0x0abe_0ac0, 0x0ac9_0ac9, 0x0acb_0acc,
          0x0b02_0b03, 0x0b3e_0b3e, 0x0b40_0b40, 0x0b47_0b48, 0x0b4b_0b4c, 0x0b57_0b57, 0x0bbe_0bbf, 0x0bc1_0bc2,
          0x0bc6_0bc8, 0x0bca_0bcc, 0x0bd7_0bd7, 0x0c01_0c03, 0x0c41_0c44, 0x0c82_0c83, 0x0cbe_0cbe, 0x0cc0_0cc4,
          0x0cc7_0cc8, 0x0cca_0ccb, 0x0cd5_0cd6, 0x0d02_0d03, 0x0d3e_0d40, 0x0d46_0d48, 0x0d4a_0d4c, 0x0d57_0d57,
          0x0d82_0d83, 0x0dcf_0dd1, 0x0dd8_0ddf, 0x0df2_0df3, 0x0f3e_0f3f, 0x0f7f_0f7f, 0x102b_102c, 0x1031_1031,
          0x1038_1038, 0x103b_103c, 0x1056_1057, 0x1062_1064, 0x1067_106d, 0x1083_1084, 0x1087_108c, 0x108f_108f,
          0x109a_109c, 0x17b6_17b6, 0x17be_17c5, 0x17c7_17c8, 0x1923_1926, 0x1929_192b, 0x1930_1931, 0x1933_1938,
          0x1a19_1a1a, 0x1a55_1a55, 0x1a57_1a57, 0x1a61_1a61, 0x1a63_1a64, 0x1a6d_1a72, 0x1b04_1b04, 0x1b35_1b35,
          0x1b3b_1b3b, 0x1b3d_1b41, 0x1b43_1b44, 0x1b82_1b82, 0x1ba1_1ba1, 0x1ba6_1ba7, 0x1baa_1baa, 0x1be7_1be7,
          0x1bea_1bec, 0x1bee_1bee, 0x1bf2_1bf3, 0x1c24_1c2b, 0x1c34_1c35, 0x1ce1_1ce1, 0x1cf7_1cf7, 0x302e_302f,
          0xa823_a824, 0xa827_a827, 0xa880_a881, 0xa8b4_a8c3, 0xa952_a953, 0xa983_a983, 0xa9b4_a9b5, 0xa9ba_a9bb,
          0xa9be_a9c0, 0xaa2f_aa30, 0xaa33_aa34, 0xaa4d_aa4d, 0xaa7b_aa7b, 0xaa7d_aa7d, 0xaaeb_aaeb, 0xaaee_aaef,
          0xaaf5_aaf5, 0xabe3_abe4, 0xabe6_abe7, 0xabe9_abea, 0xabec_abec},
        new long [] { 
          0x00011000_00011000L, 0x00011002_00011002L, 0x00011082_00011082L, 0x000110b0_000110b2L,
          0x000110b7_000110b8L, 0x0001112c_0001112cL, 0x00011145_00011146L, 0x00011182_00011182L,
          0x000111b3_000111b5L, 0x000111bf_000111c0L, 0x000111ce_000111ceL, 0x0001122c_0001122eL,
          0x00011232_00011233L, 0x00011235_00011235L, 0x000112e0_000112e2L, 0x00011302_00011303L,
          0x0001133e_0001133fL, 0x00011341_00011344L, 0x00011347_00011348L, 0x0001134b_0001134dL,
          0x00011357_00011357L, 0x00011362_00011363L, 0x00011435_00011437L, 0x00011440_00011441L,
          0x00011445_00011445L, 0x000114b0_000114b2L, 0x000114b9_000114b9L, 0x000114bb_000114beL,
          0x000114c1_000114c1L, 0x000115af_000115b1L, 0x000115b8_000115bbL, 0x000115be_000115beL,
          0x00011630_00011632L, 0x0001163b_0001163cL, 0x0001163e_0001163eL, 0x000116ac_000116acL,
          0x000116ae_000116afL, 0x000116b6_000116b6L, 0x00011720_00011721L, 0x00011726_00011726L,
          0x0001182c_0001182eL, 0x00011838_00011838L, 0x00011930_00011935L, 0x00011937_00011938L,
          0x0001193d_0001193dL, 0x00011940_00011940L, 0x00011942_00011942L, 0x000119d1_000119d3L,
          0x000119dc_000119dfL, 0x000119e4_000119e4L, 0x00011a39_00011a39L, 0x00011a57_00011a58L,
          0x00011a97_00011a97L, 0x00011c2f_00011c2fL, 0x00011c3e_00011c3eL, 0x00011ca9_00011ca9L,
          0x00011cb1_00011cb1L, 0x00011cb4_00011cb4L, 0x00011d8a_00011d8eL, 0x00011d93_00011d94L,
          0x00011d96_00011d96L, 0x00011ef5_00011ef6L, 0x00016f51_00016f87L, 0x00016ff0_00016ff1L,
          0x0001d165_0001d166L, 0x0001d16d_0001d172L}),
    /** Me – an enclosing combining mark */
    ENCLOSING_MARK("Me", "Enclosing Mark",
        new int [] {
          0x0488_0489, 0x1abe_1abe, 0x20dd_20e0, 0x20e2_20e4, 0xa670_a672},
        new long [0]),
    /** M – Mn | Mc | Me */
    MARK("M", "Mark",
        new int [] {
          0x0300_036f, 0x0483_0489, 0x0591_05bd, 0x05bf_05bf, 0x05c1_05c2, 0x05c4_05c5, 0x05c7_05c7, 0x0610_061a,
          0x064b_065f, 0x0670_0670, 0x06d6_06dc, 0x06df_06e4, 0x06e7_06e8, 0x06ea_06ed, 0x0711_0711, 0x0730_074a,
          0x07a6_07b0, 0x07eb_07f3, 0x07fd_07fd, 0x0816_0819, 0x081b_0823, 0x0825_0827, 0x0829_082d, 0x0859_085b,
          0x08d3_08e1, 0x08e3_0903, 0x093a_093c, 0x093e_094f, 0x0951_0957, 0x0962_0963, 0x0981_0983, 0x09bc_09bc,
          0x09be_09c4, 0x09c7_09c8, 0x09cb_09cd, 0x09d7_09d7, 0x09e2_09e3, 0x09fe_09fe, 0x0a01_0a03, 0x0a3c_0a3c,
          0x0a3e_0a42, 0x0a47_0a48, 0x0a4b_0a4d, 0x0a51_0a51, 0x0a70_0a71, 0x0a75_0a75, 0x0a81_0a83, 0x0abc_0abc,
          0x0abe_0ac5, 0x0ac7_0ac9, 0x0acb_0acd, 0x0ae2_0ae3, 0x0afa_0aff, 0x0b01_0b03, 0x0b3c_0b3c, 0x0b3e_0b44,
          0x0b47_0b48, 0x0b4b_0b4d, 0x0b55_0b57, 0x0b62_0b63, 0x0b82_0b82, 0x0bbe_0bc2, 0x0bc6_0bc8, 0x0bca_0bcd,
          0x0bd7_0bd7, 0x0c00_0c04, 0x0c3e_0c44, 0x0c46_0c48, 0x0c4a_0c4d, 0x0c55_0c56, 0x0c62_0c63, 0x0c81_0c83,
          0x0cbc_0cbc, 0x0cbe_0cc4, 0x0cc6_0cc8, 0x0cca_0ccd, 0x0cd5_0cd6, 0x0ce2_0ce3, 0x0d00_0d03, 0x0d3b_0d3c,
          0x0d3e_0d44, 0x0d46_0d48, 0x0d4a_0d4d, 0x0d57_0d57, 0x0d62_0d63, 0x0d81_0d83, 0x0dca_0dca, 0x0dcf_0dd4,
          0x0dd6_0dd6, 0x0dd8_0ddf, 0x0df2_0df3, 0x0e31_0e31, 0x0e34_0e3a, 0x0e47_0e4e, 0x0eb1_0eb1, 0x0eb4_0ebc,
          0x0ec8_0ecd, 0x0f18_0f19, 0x0f35_0f35, 0x0f37_0f37, 0x0f39_0f39, 0x0f3e_0f3f, 0x0f71_0f84, 0x0f86_0f87,
          0x0f8d_0f97, 0x0f99_0fbc, 0x0fc6_0fc6, 0x102b_103e, 0x1056_1059, 0x105e_1060, 0x1062_1064, 0x1067_106d,
          0x1071_1074, 0x1082_108d, 0x108f_108f, 0x109a_109d, 0x135d_135f, 0x1712_1714, 0x1732_1734, 0x1752_1753,
          0x1772_1773, 0x17b4_17d3, 0x17dd_17dd, 0x180b_180d, 0x1885_1886, 0x18a9_18a9, 0x1920_192b, 0x1930_193b,
          0x1a17_1a1b, 0x1a55_1a5e, 0x1a60_1a7c, 0x1a7f_1a7f, 0x1ab0_1ac0, 0x1b00_1b04, 0x1b34_1b44, 0x1b6b_1b73,
          0x1b80_1b82, 0x1ba1_1bad, 0x1be6_1bf3, 0x1c24_1c37, 0x1cd0_1cd2, 0x1cd4_1ce8, 0x1ced_1ced, 0x1cf4_1cf4,
          0x1cf7_1cf9, 0x1dc0_1df9, 0x1dfb_1dff, 0x20d0_20f0, 0x2cef_2cf1, 0x2d7f_2d7f, 0x2de0_2dff, 0x302a_302f,
          0x3099_309a, 0xa66f_a672, 0xa674_a67d, 0xa69e_a69f, 0xa6f0_a6f1, 0xa802_a802, 0xa806_a806, 0xa80b_a80b,
          0xa823_a827, 0xa82c_a82c, 0xa880_a881, 0xa8b4_a8c5, 0xa8e0_a8f1, 0xa8ff_a8ff, 0xa926_a92d, 0xa947_a953,
          0xa980_a983, 0xa9b3_a9c0, 0xa9e5_a9e5, 0xaa29_aa36, 0xaa43_aa43, 0xaa4c_aa4d, 0xaa7b_aa7d, 0xaab0_aab0,
          0xaab2_aab4, 0xaab7_aab8, 0xaabe_aabf, 0xaac1_aac1, 0xaaeb_aaef, 0xaaf5_aaf6, 0xabe3_abea, 0xabec_abed,
          0xfb1e_fb1e, 0xfe00_fe0f, 0xfe20_fe2f},
        new long [] { 
          0x000101fd_000101fdL, 0x000102e0_000102e0L, 0x00010376_0001037aL, 0x00010a01_00010a03L,
          0x00010a05_00010a06L, 0x00010a0c_00010a0fL, 0x00010a38_00010a3aL, 0x00010a3f_00010a3fL,
          0x00010ae5_00010ae6L, 0x00010d24_00010d27L, 0x00010eab_00010eacL, 0x00010f46_00010f50L,
          0x00011000_00011002L, 0x00011038_00011046L, 0x0001107f_00011082L, 0x000110b0_000110baL,
          0x00011100_00011102L, 0x00011127_00011134L, 0x00011145_00011146L, 0x00011173_00011173L,
          0x00011180_00011182L, 0x000111b3_000111c0L, 0x000111c9_000111ccL, 0x000111ce_000111cfL,
          0x0001122c_00011237L, 0x0001123e_0001123eL, 0x000112df_000112eaL, 0x00011300_00011303L,
          0x0001133b_0001133cL, 0x0001133e_00011344L, 0x00011347_00011348L, 0x0001134b_0001134dL,
          0x00011357_00011357L, 0x00011362_00011363L, 0x00011366_0001136cL, 0x00011370_00011374L,
          0x00011435_00011446L, 0x0001145e_0001145eL, 0x000114b0_000114c3L, 0x000115af_000115b5L,
          0x000115b8_000115c0L, 0x000115dc_000115ddL, 0x00011630_00011640L, 0x000116ab_000116b7L,
          0x0001171d_0001172bL, 0x0001182c_0001183aL, 0x00011930_00011935L, 0x00011937_00011938L,
          0x0001193b_0001193eL, 0x00011940_00011940L, 0x00011942_00011943L, 0x000119d1_000119d7L,
          0x000119da_000119e0L, 0x000119e4_000119e4L, 0x00011a01_00011a0aL, 0x00011a33_00011a39L,
          0x00011a3b_00011a3eL, 0x00011a47_00011a47L, 0x00011a51_00011a5bL, 0x00011a8a_00011a99L,
          0x00011c2f_00011c36L, 0x00011c38_00011c3fL, 0x00011c92_00011ca7L, 0x00011ca9_00011cb6L,
          0x00011d31_00011d36L, 0x00011d3a_00011d3aL, 0x00011d3c_00011d3dL, 0x00011d3f_00011d45L,
          0x00011d47_00011d47L, 0x00011d8a_00011d8eL, 0x00011d90_00011d91L, 0x00011d93_00011d97L,
          0x00011ef3_00011ef6L, 0x00016af0_00016af4L, 0x00016b30_00016b36L, 0x00016f4f_00016f4fL,
          0x00016f51_00016f87L, 0x00016f8f_00016f92L, 0x00016fe4_00016fe4L, 0x00016ff0_00016ff1L,
          0x0001bc9d_0001bc9eL, 0x0001d165_0001d169L, 0x0001d16d_0001d172L, 0x0001d17b_0001d182L,
          0x0001d185_0001d18bL, 0x0001d1aa_0001d1adL, 0x0001d242_0001d244L, 0x0001da00_0001da36L,
          0x0001da3b_0001da6cL, 0x0001da75_0001da75L, 0x0001da84_0001da84L, 0x0001da9b_0001da9fL,
          0x0001daa1_0001daafL, 0x0001e000_0001e006L, 0x0001e008_0001e018L, 0x0001e01b_0001e021L,
          0x0001e023_0001e024L, 0x0001e026_0001e02aL, 0x0001e130_0001e136L, 0x0001e2ec_0001e2efL,
          0x0001e8d0_0001e8d6L, 0x0001e944_0001e94aL, 0x000e0100_000e01efL}),
    /** Nd – a decimal digit */
    DECIMAL_NUMBER("Nd", "Decimal Number",
        new int [] {
          0x0030_0039, 0x0660_0669, 0x06f0_06f9, 0x07c0_07c9, 0x0966_096f, 0x09e6_09ef, 0x0a66_0a6f, 0x0ae6_0aef,
          0x0b66_0b6f, 0x0be6_0bef, 0x0c66_0c6f, 0x0ce6_0cef, 0x0d66_0d6f, 0x0de6_0def, 0x0e50_0e59, 0x0ed0_0ed9,
          0x0f20_0f29, 0x1040_1049, 0x1090_1099, 0x17e0_17e9, 0x1810_1819, 0x1946_194f, 0x19d0_19d9, 0x1a80_1a89,
          0x1a90_1a99, 0x1b50_1b59, 0x1bb0_1bb9, 0x1c40_1c49, 0x1c50_1c59, 0xa620_a629, 0xa8d0_a8d9, 0xa900_a909,
          0xa9d0_a9d9, 0xa9f0_a9f9, 0xaa50_aa59, 0xabf0_abf9, 0xff10_ff19},
        new long [] { 
          0x000104a0_000104a9L, 0x00010d30_00010d39L, 0x00011066_0001106fL, 0x000110f0_000110f9L,
          0x00011136_0001113fL, 0x000111d0_000111d9L, 0x000112f0_000112f9L, 0x00011450_00011459L,
          0x000114d0_000114d9L, 0x00011650_00011659L, 0x000116c0_000116c9L, 0x00011730_00011739L,
          0x000118e0_000118e9L, 0x00011950_00011959L, 0x00011c50_00011c59L, 0x00011d50_00011d59L,
          0x00011da0_00011da9L, 0x00016a60_00016a69L, 0x00016b50_00016b59L, 0x0001d7ce_0001d7ffL,
          0x0001e140_0001e149L, 0x0001e2f0_0001e2f9L, 0x0001e950_0001e959L, 0x0001fbf0_0001fbf9L}),
    /** Nl – a letterlike numeric character */
    LETTER_NUMBER("Nl", "Letter Number",
        new int [] {
          0x16ee_16f0, 0x2160_2182, 0x2185_2188, 0x3007_3007, 0x3021_3029, 0x3038_303a, 0xa6e6_a6ef},
        new long [] { 
          0x00010140_00010174L, 0x00010341_00010341L, 0x0001034a_0001034aL, 0x000103d1_000103d5L,
          0x00012400_0001246eL}),
    /** No – a numeric character of other type */
    OTHER_NUMBER("No", "Other Number",
        new int [] {
          0x00b2_00b3, 0x00b9_00b9, 0x00bc_00be, 0x09f4_09f9, 0x0b72_0b77, 0x0bf0_0bf2, 0x0c78_0c7e, 0x0d58_0d5e,
          0x0d70_0d78, 0x0f2a_0f33, 0x1369_137c, 0x17f0_17f9, 0x19da_19da, 0x2070_2070, 0x2074_2079, 0x2080_2089,
          0x2150_215f, 0x2189_2189, 0x2460_249b, 0x24ea_24ff, 0x2776_2793, 0x2cfd_2cfd, 0x3192_3195, 0x3220_3229,
          0x3248_324f, 0x3251_325f, 0x3280_3289, 0x32b1_32bf, 0xa830_a835},
        new long [] { 
          0x00010107_00010133L, 0x00010175_00010178L, 0x0001018a_0001018bL, 0x000102e1_000102fbL,
          0x00010320_00010323L, 0x00010858_0001085fL, 0x00010879_0001087fL, 0x000108a7_000108afL,
          0x000108fb_000108ffL, 0x00010916_0001091bL, 0x000109bc_000109bdL, 0x000109c0_000109cfL,
          0x000109d2_000109ffL, 0x00010a40_00010a48L, 0x00010a7d_00010a7eL, 0x00010a9d_00010a9fL,
          0x00010aeb_00010aefL, 0x00010b58_00010b5fL, 0x00010b78_00010b7fL, 0x00010ba9_00010bafL,
          0x00010cfa_00010cffL, 0x00010e60_00010e7eL, 0x00010f1d_00010f26L, 0x00010f51_00010f54L,
          0x00010fc5_00010fcbL, 0x00011052_00011065L, 0x000111e1_000111f4L, 0x0001173a_0001173bL,
          0x000118ea_000118f2L, 0x00011c5a_00011c6cL, 0x00011fc0_00011fd4L, 0x00016b5b_00016b61L,
          0x00016e80_00016e96L, 0x0001d2e0_0001d2f3L, 0x0001d360_0001d378L, 0x0001e8c7_0001e8cfL,
          0x0001ec71_0001ecabL, 0x0001ecad_0001ecafL, 0x0001ecb1_0001ecb4L, 0x0001ed01_0001ed2dL,
          0x0001ed2f_0001ed3dL, 0x0001f100_0001f10cL}),
    /** N – Nd | Nl | No */
    NUMBER("N", "Number",
        new int [] {
          0x0030_0039, 0x00b2_00b3, 0x00b9_00b9, 0x00bc_00be, 0x0660_0669, 0x06f0_06f9, 0x07c0_07c9, 0x0966_096f,
          0x09e6_09ef, 0x09f4_09f9, 0x0a66_0a6f, 0x0ae6_0aef, 0x0b66_0b6f, 0x0b72_0b77, 0x0be6_0bf2, 0x0c66_0c6f,
          0x0c78_0c7e, 0x0ce6_0cef, 0x0d58_0d5e, 0x0d66_0d78, 0x0de6_0def, 0x0e50_0e59, 0x0ed0_0ed9, 0x0f20_0f33,
          0x1040_1049, 0x1090_1099, 0x1369_137c, 0x16ee_16f0, 0x17e0_17e9, 0x17f0_17f9, 0x1810_1819, 0x1946_194f,
          0x19d0_19da, 0x1a80_1a89, 0x1a90_1a99, 0x1b50_1b59, 0x1bb0_1bb9, 0x1c40_1c49, 0x1c50_1c59, 0x2070_2070,
          0x2074_2079, 0x2080_2089, 0x2150_2182, 0x2185_2189, 0x2460_249b, 0x24ea_24ff, 0x2776_2793, 0x2cfd_2cfd,
          0x3007_3007, 0x3021_3029, 0x3038_303a, 0x3192_3195, 0x3220_3229, 0x3248_324f, 0x3251_325f, 0x3280_3289,
          0x32b1_32bf, 0xa620_a629, 0xa6e6_a6ef, 0xa830_a835, 0xa8d0_a8d9, 0xa900_a909, 0xa9d0_a9d9, 0xa9f0_a9f9,
          0xaa50_aa59, 0xabf0_abf9, 0xff10_ff19},
        new long [] { 
          0x00010107_00010133L, 0x00010140_00010178L, 0x0001018a_0001018bL, 0x000102e1_000102fbL,
          0x00010320_00010323L, 0x00010341_00010341L, 0x0001034a_0001034aL, 0x000103d1_000103d5L,
          0x000104a0_000104a9L, 0x00010858_0001085fL, 0x00010879_0001087fL, 0x000108a7_000108afL,
          0x000108fb_000108ffL, 0x00010916_0001091bL, 0x000109bc_000109bdL, 0x000109c0_000109cfL,
          0x000109d2_000109ffL, 0x00010a40_00010a48L, 0x00010a7d_00010a7eL, 0x00010a9d_00010a9fL,
          0x00010aeb_00010aefL, 0x00010b58_00010b5fL, 0x00010b78_00010b7fL, 0x00010ba9_00010bafL,
          0x00010cfa_00010cffL, 0x00010d30_00010d39L, 0x00010e60_00010e7eL, 0x00010f1d_00010f26L,
          0x00010f51_00010f54L, 0x00010fc5_00010fcbL, 0x00011052_0001106fL, 0x000110f0_000110f9L,
          0x00011136_0001113fL, 0x000111d0_000111d9L, 0x000111e1_000111f4L, 0x000112f0_000112f9L,
          0x00011450_00011459L, 0x000114d0_000114d9L, 0x00011650_00011659L, 0x000116c0_000116c9L,
          0x00011730_0001173bL, 0x000118e0_000118f2L, 0x00011950_00011959L, 0x00011c50_00011c6cL,
          0x00011d50_00011d59L, 0x00011da0_00011da9L, 0x00011fc0_00011fd4L, 0x00012400_0001246eL,
          0x00016a60_00016a69L, 0x00016b50_00016b59L, 0x00016b5b_00016b61L, 0x00016e80_00016e96L,
          0x0001d2e0_0001d2f3L, 0x0001d360_0001d378L, 0x0001d7ce_0001d7ffL, 0x0001e140_0001e149L,
          0x0001e2f0_0001e2f9L, 0x0001e8c7_0001e8cfL, 0x0001e950_0001e959L, 0x0001ec71_0001ecabL,
          0x0001ecad_0001ecafL, 0x0001ecb1_0001ecb4L, 0x0001ed01_0001ed2dL, 0x0001ed2f_0001ed3dL,
          0x0001f100_0001f10cL, 0x0001fbf0_0001fbf9L}),
    /** Pc – a connecting punctuation mark, like a tie */
    CONNECTOR_PUNCTUATION("Pc", "Connector Punctuation",
        new int [] {
          0x005f_005f, 0x203f_2040, 0x2054_2054, 0xfe33_fe34, 0xfe4d_fe4f, 0xff3f_ff3f},
        new long [0]),
    /** Pd – a dash or hyphen punctuation mark */
    DASH_PUNCTUATION("Pd", "Dash Punctuation",
        new int [] {
          0x002d_002d, 0x058a_058a, 0x05be_05be, 0x1400_1400, 0x1806_1806, 0x2010_2015, 0x2e17_2e17, 0x2e1a_2e1a,
          0x2e3a_2e3b, 0x2e40_2e40, 0x301c_301c, 0x3030_3030, 0x30a0_30a0, 0xfe31_fe32, 0xfe58_fe58, 0xfe63_fe63,
          0xff0d_ff0d},
        new long [] { 
          0x00010ead_00010eadL}),
    /** Ps – an opening punctuation mark (of a pair) */
    OPEN_PUNCTUATION("Ps", "Open Punctuation",
        new int [] {
          0x0028_0028, 0x005b_005b, 0x007b_007b, 0x0f3a_0f3a, 0x0f3c_0f3c, 0x169b_169b, 0x201a_201a, 0x201e_201e,
          0x2045_2045, 0x207d_207d, 0x208d_208d, 0x2308_2308, 0x230a_230a, 0x2329_2329, 0x2768_2768, 0x276a_276a,
          0x276c_276c, 0x276e_276e, 0x2770_2770, 0x2772_2772, 0x2774_2774, 0x27c5_27c5, 0x27e6_27e6, 0x27e8_27e8,
          0x27ea_27ea, 0x27ec_27ec, 0x27ee_27ee, 0x2983_2983, 0x2985_2985, 0x2987_2987, 0x2989_2989, 0x298b_298b,
          0x298d_298d, 0x298f_298f, 0x2991_2991, 0x2993_2993, 0x2995_2995, 0x2997_2997, 0x29d8_29d8, 0x29da_29da,
          0x29fc_29fc, 0x2e22_2e22, 0x2e24_2e24, 0x2e26_2e26, 0x2e28_2e28, 0x2e42_2e42, 0x3008_3008, 0x300a_300a,
          0x300c_300c, 0x300e_300e, 0x3010_3010, 0x3014_3014, 0x3016_3016, 0x3018_3018, 0x301a_301a, 0x301d_301d,
          0xfd3f_fd3f, 0xfe17_fe17, 0xfe35_fe35, 0xfe37_fe37, 0xfe39_fe39, 0xfe3b_fe3b, 0xfe3d_fe3d, 0xfe3f_fe3f,
          0xfe41_fe41, 0xfe43_fe43, 0xfe47_fe47, 0xfe59_fe59, 0xfe5b_fe5b, 0xfe5d_fe5d, 0xff08_ff08, 0xff3b_ff3b,
          0xff5b_ff5b, 0xff5f_ff5f, 0xff62_ff62},
        new long [0]),
    /** Pe – a closing punctuation mark (of a pair) */
    CLOSE_PUNCTUATION("Pe", "Close Punctuation",
        new int [] {
          0x0029_0029, 0x005d_005d, 0x007d_007d, 0x0f3b_0f3b, 0x0f3d_0f3d, 0x169c_169c, 0x2046_2046, 0x207e_207e,
          0x208e_208e, 0x2309_2309, 0x230b_230b, 0x232a_232a, 0x2769_2769, 0x276b_276b, 0x276d_276d, 0x276f_276f,
          0x2771_2771, 0x2773_2773, 0x2775_2775, 0x27c6_27c6, 0x27e7_27e7, 0x27e9_27e9, 0x27eb_27eb, 0x27ed_27ed,
          0x27ef_27ef, 0x2984_2984, 0x2986_2986, 0x2988_2988, 0x298a_298a, 0x298c_298c, 0x298e_298e, 0x2990_2990,
          0x2992_2992, 0x2994_2994, 0x2996_2996, 0x2998_2998, 0x29d9_29d9, 0x29db_29db, 0x29fd_29fd, 0x2e23_2e23,
          0x2e25_2e25, 0x2e27_2e27, 0x2e29_2e29, 0x3009_3009, 0x300b_300b, 0x300d_300d, 0x300f_300f, 0x3011_3011,
          0x3015_3015, 0x3017_3017, 0x3019_3019, 0x301b_301b, 0x301e_301f, 0xfd3e_fd3e, 0xfe18_fe18, 0xfe36_fe36,
          0xfe38_fe38, 0xfe3a_fe3a, 0xfe3c_fe3c, 0xfe3e_fe3e, 0xfe40_fe40, 0xfe42_fe42, 0xfe44_fe44, 0xfe48_fe48,
          0xfe5a_fe5a, 0xfe5c_fe5c, 0xfe5e_fe5e, 0xff09_ff09, 0xff3d_ff3d, 0xff5d_ff5d, 0xff60_ff60, 0xff63_ff63},
        new long [0]),
    /** Pi – an initial quotation mark */
    INITIAL_PUNCTUATION("Pi", "Initial Punctuation",
        new int [] {
          0x00ab_00ab, 0x2018_2018, 0x201b_201c, 0x201f_201f, 0x2039_2039, 0x2e02_2e02, 0x2e04_2e04, 0x2e09_2e09,
          0x2e0c_2e0c, 0x2e1c_2e1c, 0x2e20_2e20},
        new long [0]),
    /** Pf – a final quotation mark */
    FINAL_PUNCTUATION("Pf", "Final Punctuation",
        new int [] {
          0x00bb_00bb, 0x2019_2019, 0x201d_201d, 0x203a_203a, 0x2e03_2e03, 0x2e05_2e05, 0x2e0a_2e0a, 0x2e0d_2e0d,
          0x2e1d_2e1d, 0x2e21_2e21},
        new long [0]),
    /** Po – a punctuation mark of other type */
    OTHER_PUNCTUATION("Po", "Other Punctuation",
        new int [] {
          0x0021_0023, 0x0025_0027, 0x002a_002a, 0x002c_002c, 0x002e_002f, 0x003a_003b, 0x003f_0040, 0x005c_005c,
          0x00a1_00a1, 0x00a7_00a7, 0x00b6_00b7, 0x00bf_00bf, 0x037e_037e, 0x0387_0387, 0x055a_055f, 0x0589_0589,
          0x05c0_05c0, 0x05c3_05c3, 0x05c6_05c6, 0x05f3_05f4, 0x0609_060a, 0x060c_060d, 0x061b_061b, 0x061e_061f,
          0x066a_066d, 0x06d4_06d4, 0x0700_070d, 0x07f7_07f9, 0x0830_083e, 0x085e_085e, 0x0964_0965, 0x0970_0970,
          0x09fd_09fd, 0x0a76_0a76, 0x0af0_0af0, 0x0c77_0c77, 0x0c84_0c84, 0x0df4_0df4, 0x0e4f_0e4f, 0x0e5a_0e5b,
          0x0f04_0f12, 0x0f14_0f14, 0x0f85_0f85, 0x0fd0_0fd4, 0x0fd9_0fda, 0x104a_104f, 0x10fb_10fb, 0x1360_1368,
          0x166e_166e, 0x16eb_16ed, 0x1735_1736, 0x17d4_17d6, 0x17d8_17da, 0x1800_1805, 0x1807_180a, 0x1944_1945,
          0x1a1e_1a1f, 0x1aa0_1aa6, 0x1aa8_1aad, 0x1b5a_1b60, 0x1bfc_1bff, 0x1c3b_1c3f, 0x1c7e_1c7f, 0x1cc0_1cc7,
          0x1cd3_1cd3, 0x2016_2017, 0x2020_2027, 0x2030_2038, 0x203b_203e, 0x2041_2043, 0x2047_2051, 0x2053_2053,
          0x2055_205e, 0x2cf9_2cfc, 0x2cfe_2cff, 0x2d70_2d70, 0x2e00_2e01, 0x2e06_2e08, 0x2e0b_2e0b, 0x2e0e_2e16,
          0x2e18_2e19, 0x2e1b_2e1b, 0x2e1e_2e1f, 0x2e2a_2e2e, 0x2e30_2e39, 0x2e3c_2e3f, 0x2e41_2e41, 0x2e43_2e4f,
          0x2e52_2e52, 0x3001_3003, 0x303d_303d, 0x30fb_30fb, 0xa4fe_a4ff, 0xa60d_a60f, 0xa673_a673, 0xa67e_a67e,
          0xa6f2_a6f7, 0xa874_a877, 0xa8ce_a8cf, 0xa8f8_a8fa, 0xa8fc_a8fc, 0xa92e_a92f, 0xa95f_a95f, 0xa9c1_a9cd,
          0xa9de_a9df, 0xaa5c_aa5f, 0xaade_aadf, 0xaaf0_aaf1, 0xabeb_abeb, 0xfe10_fe16, 0xfe19_fe19, 0xfe30_fe30,
          0xfe45_fe46, 0xfe49_fe4c, 0xfe50_fe52, 0xfe54_fe57, 0xfe5f_fe61, 0xfe68_fe68, 0xfe6a_fe6b, 0xff01_ff03,
          0xff05_ff07, 0xff0a_ff0a, 0xff0c_ff0c, 0xff0e_ff0f, 0xff1a_ff1b, 0xff1f_ff20, 0xff3c_ff3c, 0xff61_ff61,
          0xff64_ff65},
        new long [] { 
          0x00010100_00010102L, 0x0001039f_0001039fL, 0x000103d0_000103d0L, 0x0001056f_0001056fL,
          0x00010857_00010857L, 0x0001091f_0001091fL, 0x0001093f_0001093fL, 0x00010a50_00010a58L,
          0x00010a7f_00010a7fL, 0x00010af0_00010af6L, 0x00010b39_00010b3fL, 0x00010b99_00010b9cL,
          0x00010f55_00010f59L, 0x00011047_0001104dL, 0x000110bb_000110bcL, 0x000110be_000110c1L,
          0x00011140_00011143L, 0x00011174_00011175L, 0x000111c5_000111c8L, 0x000111cd_000111cdL,
          0x000111db_000111dbL, 0x000111dd_000111dfL, 0x00011238_0001123dL, 0x000112a9_000112a9L,
          0x0001144b_0001144fL, 0x0001145a_0001145bL, 0x0001145d_0001145dL, 0x000114c6_000114c6L,
          0x000115c1_000115d7L, 0x00011641_00011643L, 0x00011660_0001166cL, 0x0001173c_0001173eL,
          0x0001183b_0001183bL, 0x00011944_00011946L, 0x000119e2_000119e2L, 0x00011a3f_00011a46L,
          0x00011a9a_00011a9cL, 0x00011a9e_00011aa2L, 0x00011c41_00011c45L, 0x00011c70_00011c71L,
          0x00011ef7_00011ef8L, 0x00011fff_00011fffL, 0x00012470_00012474L, 0x00016a6e_00016a6fL,
          0x00016af5_00016af5L, 0x00016b37_00016b3bL, 0x00016b44_00016b44L, 0x00016e97_00016e9aL,
          0x00016fe2_00016fe2L, 0x0001bc9f_0001bc9fL, 0x0001da87_0001da8bL, 0x0001e95e_0001e95fL}),
    /** P – Pc | Pd | Ps | Pe | Pi | Pf | Po */
    PUNCTUATION("P", "Punctuation",
        new int [] {
          0x0021_0023, 0x0025_002a, 0x002c_002f, 0x003a_003b, 0x003f_0040, 0x005b_005d, 0x005f_005f, 0x007b_007b,
          0x007d_007d, 0x00a1_00a1, 0x00a7_00a7, 0x00ab_00ab, 0x00b6_00b7, 0x00bb_00bb, 0x00bf_00bf, 0x037e_037e,
          0x0387_0387, 0x055a_055f, 0x0589_058a, 0x05be_05be, 0x05c0_05c0, 0x05c3_05c3, 0x05c6_05c6, 0x05f3_05f4,
          0x0609_060a, 0x060c_060d, 0x061b_061b, 0x061e_061f, 0x066a_066d, 0x06d4_06d4, 0x0700_070d, 0x07f7_07f9,
          0x0830_083e, 0x085e_085e, 0x0964_0965, 0x0970_0970, 0x09fd_09fd, 0x0a76_0a76, 0x0af0_0af0, 0x0c77_0c77,
          0x0c84_0c84, 0x0df4_0df4, 0x0e4f_0e4f, 0x0e5a_0e5b, 0x0f04_0f12, 0x0f14_0f14, 0x0f3a_0f3d, 0x0f85_0f85,
          0x0fd0_0fd4, 0x0fd9_0fda, 0x104a_104f, 0x10fb_10fb, 0x1360_1368, 0x1400_1400, 0x166e_166e, 0x169b_169c,
          0x16eb_16ed, 0x1735_1736, 0x17d4_17d6, 0x17d8_17da, 0x1800_180a, 0x1944_1945, 0x1a1e_1a1f, 0x1aa0_1aa6,
          0x1aa8_1aad, 0x1b5a_1b60, 0x1bfc_1bff, 0x1c3b_1c3f, 0x1c7e_1c7f, 0x1cc0_1cc7, 0x1cd3_1cd3, 0x2010_2027,
          0x2030_2043, 0x2045_2051, 0x2053_205e, 0x207d_207e, 0x208d_208e, 0x2308_230b, 0x2329_232a, 0x2768_2775,
          0x27c5_27c6, 0x27e6_27ef, 0x2983_2998, 0x29d8_29db, 0x29fc_29fd, 0x2cf9_2cfc, 0x2cfe_2cff, 0x2d70_2d70,
          0x2e00_2e2e, 0x2e30_2e4f, 0x2e52_2e52, 0x3001_3003, 0x3008_3011, 0x3014_301f, 0x3030_3030, 0x303d_303d,
          0x30a0_30a0, 0x30fb_30fb, 0xa4fe_a4ff, 0xa60d_a60f, 0xa673_a673, 0xa67e_a67e, 0xa6f2_a6f7, 0xa874_a877,
          0xa8ce_a8cf, 0xa8f8_a8fa, 0xa8fc_a8fc, 0xa92e_a92f, 0xa95f_a95f, 0xa9c1_a9cd, 0xa9de_a9df, 0xaa5c_aa5f,
          0xaade_aadf, 0xaaf0_aaf1, 0xabeb_abeb, 0xfd3e_fd3f, 0xfe10_fe19, 0xfe30_fe52, 0xfe54_fe61, 0xfe63_fe63,
          0xfe68_fe68, 0xfe6a_fe6b, 0xff01_ff03, 0xff05_ff0a, 0xff0c_ff0f, 0xff1a_ff1b, 0xff1f_ff20, 0xff3b_ff3d,
          0xff3f_ff3f, 0xff5b_ff5b, 0xff5d_ff5d, 0xff5f_ff65},
        new long [] { 
          0x00010100_00010102L, 0x0001039f_0001039fL, 0x000103d0_000103d0L, 0x0001056f_0001056fL,
          0x00010857_00010857L, 0x0001091f_0001091fL, 0x0001093f_0001093fL, 0x00010a50_00010a58L,
          0x00010a7f_00010a7fL, 0x00010af0_00010af6L, 0x00010b39_00010b3fL, 0x00010b99_00010b9cL,
          0x00010ead_00010eadL, 0x00010f55_00010f59L, 0x00011047_0001104dL, 0x000110bb_000110bcL,
          0x000110be_000110c1L, 0x00011140_00011143L, 0x00011174_00011175L, 0x000111c5_000111c8L,
          0x000111cd_000111cdL, 0x000111db_000111dbL, 0x000111dd_000111dfL, 0x00011238_0001123dL,
          0x000112a9_000112a9L, 0x0001144b_0001144fL, 0x0001145a_0001145bL, 0x0001145d_0001145dL,
          0x000114c6_000114c6L, 0x000115c1_000115d7L, 0x00011641_00011643L, 0x00011660_0001166cL,
          0x0001173c_0001173eL, 0x0001183b_0001183bL, 0x00011944_00011946L, 0x000119e2_000119e2L,
          0x00011a3f_00011a46L, 0x00011a9a_00011a9cL, 0x00011a9e_00011aa2L, 0x00011c41_00011c45L,
          0x00011c70_00011c71L, 0x00011ef7_00011ef8L, 0x00011fff_00011fffL, 0x00012470_00012474L,
          0x00016a6e_00016a6fL, 0x00016af5_00016af5L, 0x00016b37_00016b3bL, 0x00016b44_00016b44L,
          0x00016e97_00016e9aL, 0x00016fe2_00016fe2L, 0x0001bc9f_0001bc9fL, 0x0001da87_0001da8bL,
          0x0001e95e_0001e95fL}),
    /** Sm – a symbol of mathematical use */
    MATH_SYMBOL("Sm", "Math Symbol",
        new int [] {
          0x002b_002b, 0x003c_003e, 0x007c_007c, 0x007e_007e, 0x00ac_00ac, 0x00b1_00b1, 0x00d7_00d7, 0x00f7_00f7,
          0x03f6_03f6, 0x0606_0608, 0x2044_2044, 0x2052_2052, 0x207a_207c, 0x208a_208c, 0x2118_2118, 0x2140_2144,
          0x214b_214b, 0x2190_2194, 0x219a_219b, 0x21a0_21a0, 0x21a3_21a3, 0x21a6_21a6, 0x21ae_21ae, 0x21ce_21cf,
          0x21d2_21d2, 0x21d4_21d4, 0x21f4_22ff, 0x2320_2321, 0x237c_237c, 0x239b_23b3, 0x23dc_23e1, 0x25b7_25b7,
          0x25c1_25c1, 0x25f8_25ff, 0x266f_266f, 0x27c0_27c4, 0x27c7_27e5, 0x27f0_27ff, 0x2900_2982, 0x2999_29d7,
          0x29dc_29fb, 0x29fe_2aff, 0x2b30_2b44, 0x2b47_2b4c, 0xfb29_fb29, 0xfe62_fe62, 0xfe64_fe66, 0xff0b_ff0b,
          0xff1c_ff1e, 0xff5c_ff5c, 0xff5e_ff5e, 0xffe2_ffe2, 0xffe9_ffec},
        new long [] { 
          0x0001d6c1_0001d6c1L, 0x0001d6db_0001d6dbL, 0x0001d6fb_0001d6fbL, 0x0001d715_0001d715L,
          0x0001d735_0001d735L, 0x0001d74f_0001d74fL, 0x0001d76f_0001d76fL, 0x0001d789_0001d789L,
          0x0001d7a9_0001d7a9L, 0x0001d7c3_0001d7c3L, 0x0001eef0_0001eef1L}),
    /** Sc – a currency sign */
    CURRENCY_SYMBOL("Sc", "Currency Symbol",
        new int [] {
          0x0024_0024, 0x00a2_00a5, 0x058f_058f, 0x060b_060b, 0x07fe_07ff, 0x09f2_09f3, 0x09fb_09fb, 0x0af1_0af1,
          0x0bf9_0bf9, 0x0e3f_0e3f, 0x17db_17db, 0x20a0_20bf, 0xa838_a838, 0xfdfc_fdfc, 0xfe69_fe69, 0xff04_ff04,
          0xffe0_ffe1, 0xffe5_ffe6},
        new long [] { 
          0x00011fdd_00011fe0L, 0x0001e2ff_0001e2ffL, 0x0001ecb0_0001ecb0L}),
    /** Sk – a non-letterlike modifier symbol */
    MODIFIER_SYMBOL("Sk", "Modifier Symbol",
        new int [] {
          0x005e_005e, 0x0060_0060, 0x00a8_00a8, 0x00af_00af, 0x00b4_00b4, 0x00b8_00b8, 0x02c2_02c5, 0x02d2_02df,
          0x02e5_02eb, 0x02ed_02ed, 0x02ef_02ff, 0x0375_0375, 0x0384_0385, 0x1fbd_1fbd, 0x1fbf_1fc1, 0x1fcd_1fcf,
          0x1fdd_1fdf, 0x1fed_1fef, 0x1ffd_1ffe, 0x309b_309c, 0xa700_a716, 0xa720_a721, 0xa789_a78a, 0xab5b_ab5b,
          0xab6a_ab6b, 0xfbb2_fbc1, 0xff3e_ff3e, 0xff40_ff40, 0xffe3_ffe3},
        new long [] { 
          0x0001f3fb_0001f3ffL}),
    /** So – a symbol of other type */
    OTHER_SYMBOL("So", "Other Symbol",
        new int [] {
          0x00a6_00a6, 0x00a9_00a9, 0x00ae_00ae, 0x00b0_00b0, 0x0482_0482, 0x058d_058e, 0x060e_060f, 0x06de_06de,
          0x06e9_06e9, 0x06fd_06fe, 0x07f6_07f6, 0x09fa_09fa, 0x0b70_0b70, 0x0bf3_0bf8, 0x0bfa_0bfa, 0x0c7f_0c7f,
          0x0d4f_0d4f, 0x0d79_0d79, 0x0f01_0f03, 0x0f13_0f13, 0x0f15_0f17, 0x0f1a_0f1f, 0x0f34_0f34, 0x0f36_0f36,
          0x0f38_0f38, 0x0fbe_0fc5, 0x0fc7_0fcc, 0x0fce_0fcf, 0x0fd5_0fd8, 0x109e_109f, 0x1390_1399, 0x166d_166d,
          0x1940_1940, 0x19de_19ff, 0x1b61_1b6a, 0x1b74_1b7c, 0x2100_2101, 0x2103_2106, 0x2108_2109, 0x2114_2114,
          0x2116_2117, 0x211e_2123, 0x2125_2125, 0x2127_2127, 0x2129_2129, 0x212e_212e, 0x213a_213b, 0x214a_214a,
          0x214c_214d, 0x214f_214f, 0x218a_218b, 0x2195_2199, 0x219c_219f, 0x21a1_21a2, 0x21a4_21a5, 0x21a7_21ad,
          0x21af_21cd, 0x21d0_21d1, 0x21d3_21d3, 0x21d5_21f3, 0x2300_2307, 0x230c_231f, 0x2322_2328, 0x232b_237b,
          0x237d_239a, 0x23b4_23db, 0x23e2_2426, 0x2440_244a, 0x249c_24e9, 0x2500_25b6, 0x25b8_25c0, 0x25c2_25f7,
          0x2600_266e, 0x2670_2767, 0x2794_27bf, 0x2800_28ff, 0x2b00_2b2f, 0x2b45_2b46, 0x2b4d_2b73, 0x2b76_2b95,
          0x2b97_2bff, 0x2ce5_2cea, 0x2e50_2e51, 0x2e80_2e99, 0x2e9b_2ef3, 0x2f00_2fd5, 0x2ff0_2ffb, 0x3004_3004,
          0x3012_3013, 0x3020_3020, 0x3036_3037, 0x303e_303f, 0x3190_3191, 0x3196_319f, 0x31c0_31e3, 0x3200_321e,
          0x322a_3247, 0x3250_3250, 0x3260_327f, 0x328a_32b0, 0x32c0_33ff, 0x4dc0_4dff, 0xa490_a4c6, 0xa828_a82b,
          0xa836_a837, 0xa839_a839, 0xaa77_aa79, 0xfdfd_fdfd, 0xffe4_ffe4, 0xffe8_ffe8, 0xffed_ffee, 0xfffc_fffd},
        new long [] { 
          0x00010137_0001013fL, 0x00010179_00010189L, 0x0001018c_0001018eL, 0x00010190_0001019cL,
          0x000101a0_000101a0L, 0x000101d0_000101fcL, 0x00010877_00010878L, 0x00010ac8_00010ac8L,
          0x0001173f_0001173fL, 0x00011fd5_00011fdcL, 0x00011fe1_00011ff1L, 0x00016b3c_00016b3fL,
          0x00016b45_00016b45L, 0x0001bc9c_0001bc9cL, 0x0001d000_0001d0f5L, 0x0001d100_0001d126L,
          0x0001d129_0001d164L, 0x0001d16a_0001d16cL, 0x0001d183_0001d184L, 0x0001d18c_0001d1a9L,
          0x0001d1ae_0001d1e8L, 0x0001d200_0001d241L, 0x0001d245_0001d245L, 0x0001d300_0001d356L,
          0x0001d800_0001d9ffL, 0x0001da37_0001da3aL, 0x0001da6d_0001da74L, 0x0001da76_0001da83L,
          0x0001da85_0001da86L, 0x0001e14f_0001e14fL, 0x0001ecac_0001ecacL, 0x0001ed2e_0001ed2eL,
          0x0001f000_0001f02bL, 0x0001f030_0001f093L, 0x0001f0a0_0001f0aeL, 0x0001f0b1_0001f0bfL,
          0x0001f0c1_0001f0cfL, 0x0001f0d1_0001f0f5L, 0x0001f10d_0001f1adL, 0x0001f1e6_0001f202L,
          0x0001f210_0001f23bL, 0x0001f240_0001f248L, 0x0001f250_0001f251L, 0x0001f260_0001f265L,
          0x0001f300_0001f3faL, 0x0001f400_0001f6d7L, 0x0001f6e0_0001f6ecL, 0x0001f6f0_0001f6fcL,
          0x0001f700_0001f773L, 0x0001f780_0001f7d8L, 0x0001f7e0_0001f7ebL, 0x0001f800_0001f80bL,
          0x0001f810_0001f847L, 0x0001f850_0001f859L, 0x0001f860_0001f887L, 0x0001f890_0001f8adL,
          0x0001f8b0_0001f8b1L, 0x0001f900_0001f978L, 0x0001f97a_0001f9cbL, 0x0001f9cd_0001fa53L,
          0x0001fa60_0001fa6dL, 0x0001fa70_0001fa74L, 0x0001fa78_0001fa7aL, 0x0001fa80_0001fa86L,
          0x0001fa90_0001faa8L, 0x0001fab0_0001fab6L, 0x0001fac0_0001fac2L, 0x0001fad0_0001fad6L,
          0x0001fb00_0001fb92L, 0x0001fb94_0001fbcaL}),
    /** S – Sm | Sc | Sk | So */
    SYMBOL("S", "Symbol",
        new int [] {
          0x0024_0024, 0x002b_002b, 0x003c_003e, 0x005e_005e, 0x0060_0060, 0x007c_007c, 0x007e_007e, 0x00a2_00a6,
          0x00a8_00a9, 0x00ac_00ac, 0x00ae_00b1, 0x00b4_00b4, 0x00b8_00b8, 0x00d7_00d7, 0x00f7_00f7, 0x02c2_02c5,
          0x02d2_02df, 0x02e5_02eb, 0x02ed_02ed, 0x02ef_02ff, 0x0375_0375, 0x0384_0385, 0x03f6_03f6, 0x0482_0482,
          0x058d_058f, 0x0606_0608, 0x060b_060b, 0x060e_060f, 0x06de_06de, 0x06e9_06e9, 0x06fd_06fe, 0x07f6_07f6,
          0x07fe_07ff, 0x09f2_09f3, 0x09fa_09fb, 0x0af1_0af1, 0x0b70_0b70, 0x0bf3_0bfa, 0x0c7f_0c7f, 0x0d4f_0d4f,
          0x0d79_0d79, 0x0e3f_0e3f, 0x0f01_0f03, 0x0f13_0f13, 0x0f15_0f17, 0x0f1a_0f1f, 0x0f34_0f34, 0x0f36_0f36,
          0x0f38_0f38, 0x0fbe_0fc5, 0x0fc7_0fcc, 0x0fce_0fcf, 0x0fd5_0fd8, 0x109e_109f, 0x1390_1399, 0x166d_166d,
          0x17db_17db, 0x1940_1940, 0x19de_19ff, 0x1b61_1b6a, 0x1b74_1b7c, 0x1fbd_1fbd, 0x1fbf_1fc1, 0x1fcd_1fcf,
          0x1fdd_1fdf, 0x1fed_1fef, 0x1ffd_1ffe, 0x2044_2044, 0x2052_2052, 0x207a_207c, 0x208a_208c, 0x20a0_20bf,
          0x2100_2101, 0x2103_2106, 0x2108_2109, 0x2114_2114, 0x2116_2118, 0x211e_2123, 0x2125_2125, 0x2127_2127,
          0x2129_2129, 0x212e_212e, 0x213a_213b, 0x2140_2144, 0x214a_214d, 0x214f_214f, 0x218a_218b, 0x2190_2307,
          0x230c_2328, 0x232b_2426, 0x2440_244a, 0x249c_24e9, 0x2500_2767, 0x2794_27c4, 0x27c7_27e5, 0x27f0_2982,
          0x2999_29d7, 0x29dc_29fb, 0x29fe_2b73, 0x2b76_2b95, 0x2b97_2bff, 0x2ce5_2cea, 0x2e50_2e51, 0x2e80_2e99,
          0x2e9b_2ef3, 0x2f00_2fd5, 0x2ff0_2ffb, 0x3004_3004, 0x3012_3013, 0x3020_3020, 0x3036_3037, 0x303e_303f,
          0x309b_309c, 0x3190_3191, 0x3196_319f, 0x31c0_31e3, 0x3200_321e, 0x322a_3247, 0x3250_3250, 0x3260_327f,
          0x328a_32b0, 0x32c0_33ff, 0x4dc0_4dff, 0xa490_a4c6, 0xa700_a716, 0xa720_a721, 0xa789_a78a, 0xa828_a82b,
          0xa836_a839, 0xaa77_aa79, 0xab5b_ab5b, 0xab6a_ab6b, 0xfb29_fb29, 0xfbb2_fbc1, 0xfdfc_fdfd, 0xfe62_fe62,
          0xfe64_fe66, 0xfe69_fe69, 0xff04_ff04, 0xff0b_ff0b, 0xff1c_ff1e, 0xff3e_ff3e, 0xff40_ff40, 0xff5c_ff5c,
          0xff5e_ff5e, 0xffe0_ffe6, 0xffe8_ffee, 0xfffc_fffd},
        new long [] { 
          0x00010137_0001013fL, 0x00010179_00010189L, 0x0001018c_0001018eL, 0x00010190_0001019cL,
          0x000101a0_000101a0L, 0x000101d0_000101fcL, 0x00010877_00010878L, 0x00010ac8_00010ac8L,
          0x0001173f_0001173fL, 0x00011fd5_00011ff1L, 0x00016b3c_00016b3fL, 0x00016b45_00016b45L,
          0x0001bc9c_0001bc9cL, 0x0001d000_0001d0f5L, 0x0001d100_0001d126L, 0x0001d129_0001d164L,
          0x0001d16a_0001d16cL, 0x0001d183_0001d184L, 0x0001d18c_0001d1a9L, 0x0001d1ae_0001d1e8L,
          0x0001d200_0001d241L, 0x0001d245_0001d245L, 0x0001d300_0001d356L, 0x0001d6c1_0001d6c1L,
          0x0001d6db_0001d6dbL, 0x0001d6fb_0001d6fbL, 0x0001d715_0001d715L, 0x0001d735_0001d735L,
          0x0001d74f_0001d74fL, 0x0001d76f_0001d76fL, 0x0001d789_0001d789L, 0x0001d7a9_0001d7a9L,
          0x0001d7c3_0001d7c3L, 0x0001d800_0001d9ffL, 0x0001da37_0001da3aL, 0x0001da6d_0001da74L,
          0x0001da76_0001da83L, 0x0001da85_0001da86L, 0x0001e14f_0001e14fL, 0x0001e2ff_0001e2ffL,
          0x0001ecac_0001ecacL, 0x0001ecb0_0001ecb0L, 0x0001ed2e_0001ed2eL, 0x0001eef0_0001eef1L,
          0x0001f000_0001f02bL, 0x0001f030_0001f093L, 0x0001f0a0_0001f0aeL, 0x0001f0b1_0001f0bfL,
          0x0001f0c1_0001f0cfL, 0x0001f0d1_0001f0f5L, 0x0001f10d_0001f1adL, 0x0001f1e6_0001f202L,
          0x0001f210_0001f23bL, 0x0001f240_0001f248L, 0x0001f250_0001f251L, 0x0001f260_0001f265L,
          0x0001f300_0001f6d7L, 0x0001f6e0_0001f6ecL, 0x0001f6f0_0001f6fcL, 0x0001f700_0001f773L,
          0x0001f780_0001f7d8L, 0x0001f7e0_0001f7ebL, 0x0001f800_0001f80bL, 0x0001f810_0001f847L,
          0x0001f850_0001f859L, 0x0001f860_0001f887L, 0x0001f890_0001f8adL, 0x0001f8b0_0001f8b1L,
          0x0001f900_0001f978L, 0x0001f97a_0001f9cbL, 0x0001f9cd_0001fa53L, 0x0001fa60_0001fa6dL,
          0x0001fa70_0001fa74L, 0x0001fa78_0001fa7aL, 0x0001fa80_0001fa86L, 0x0001fa90_0001faa8L,
          0x0001fab0_0001fab6L, 0x0001fac0_0001fac2L, 0x0001fad0_0001fad6L, 0x0001fb00_0001fb92L,
          0x0001fb94_0001fbcaL}),
    /** Zs – a space character (of various non-zero widths) */
    SPACE_SEPARATOR("Zs", "Space Separator",
        new int [] {
          0x0020_0020, 0x00a0_00a0, 0x1680_1680, 0x2000_200a, 0x202f_202f, 0x205f_205f, 0x3000_3000},
        new long [0]),
    /** Zl – U+2028 LINE SEPARATOR only */
    LINE_SEPARATOR("Zl", "Line Separator",
        new int [] {
          0x2028_2028},
        new long [0]),
    /** Zp – U+2029 PARAGRAPH SEPARATOR only */
    PARAGRAPH_SEPARATOR("Zp", "Paragraph Separator",
        new int [] {
          0x2029_2029},
        new long [0]),
    /** Z – Zs | Zl | Zp */
    SEPARATOR("Z", "Separator",
        new int [] {
          0x0020_0020, 0x00a0_00a0, 0x1680_1680, 0x2000_200a, 0x2028_2029, 0x202f_202f, 0x205f_205f, 0x3000_3000},
        new long [0]),
    /** Cc – a C0 or C1 control code */
    CONTROL("Cc", "Control",
        new int [] {
          0x0000_001f, 0x007f_009f},
        new long [0]),
    /** Cf – a format control character */
    FORMAT("Cf", "Format",
        new int [] {
          0x00ad_00ad, 0x0600_0605, 0x061c_061c, 0x06dd_06dd, 0x070f_070f, 0x08e2_08e2, 0x180e_180e, 0x200b_200f,
          0x202a_202e, 0x2060_2064, 0x2066_206f, 0xfeff_feff, 0xfff9_fffb},
        new long [] { 
          0x000110bd_000110bdL, 0x000110cd_000110cdL, 0x00013430_00013438L, 0x0001bca0_0001bca3L,
          0x0001d173_0001d17aL, 0x000e0001_000e0001L, 0x000e0020_000e007fL}),
    /** Cs – a surrogate code point */
    SURROGATE("Cs", "Surrogate",
        new int [0],
        new long [0]),
    /** Co – a private-use character */
    PRIVATE_USE("Co", "Private Use",
        new int [0],
        new long [0]),
    /** Cn – a reserved unassigned code point or a noncharacter */
    UNASSIGNED("Cn", "Unassigned",
        new int [] {
          0x038b_038b, 0x038d_038d, 0x03a2_03a2, 0x0530_0530, 0x0590_0590, 0x061d_061d, 0x070e_070e, 0x083f_083f,
          0x085f_085f, 0x08b5_08b5, 0x0984_0984, 0x09a9_09a9, 0x09b1_09b1, 0x09de_09de, 0x09ff_0a00, 0x0a04_0a04,
          0x0a29_0a29, 0x0a31_0a31, 0x0a34_0a34, 0x0a37_0a37, 0x0a3d_0a3d, 0x0a5d_0a5d, 0x0a80_0a80, 0x0a84_0a84,
          0x0a8e_0a8e, 0x0a92_0a92, 0x0aa9_0aa9, 0x0ab1_0ab1, 0x0ab4_0ab4, 0x0ac6_0ac6, 0x0aca_0aca, 0x0b00_0b00,
          0x0b04_0b04, 0x0b29_0b29, 0x0b31_0b31, 0x0b34_0b34, 0x0b5e_0b5e, 0x0b84_0b84, 0x0b91_0b91, 0x0b9b_0b9b,
          0x0b9d_0b9d, 0x0bc9_0bc9, 0x0c0d_0c0d, 0x0c11_0c11, 0x0c29_0c29, 0x0c45_0c45, 0x0c49_0c49, 0x0c57_0c57,
          0x0c8d_0c8d, 0x0c91_0c91, 0x0ca9_0ca9, 0x0cb4_0cb4, 0x0cc5_0cc5, 0x0cc9_0cc9, 0x0cdf_0cdf, 0x0cf0_0cf0,
          0x0d0d_0d0d, 0x0d11_0d11, 0x0d45_0d45, 0x0d49_0d49, 0x0d80_0d80, 0x0d84_0d84, 0x0db2_0db2, 0x0dbc_0dbc,
          0x0dd5_0dd5, 0x0dd7_0dd7, 0x0e00_0e00, 0x0e80_0e80, 0x0e83_0e83, 0x0e85_0e85, 0x0e8b_0e8b, 0x0ea4_0ea4,
          0x0ea6_0ea6, 0x0ec5_0ec5, 0x0ec7_0ec7, 0x0f48_0f48, 0x0f98_0f98, 0x0fbd_0fbd, 0x0fcd_0fcd, 0x10c6_10c6,
          0x1249_1249, 0x1257_1257, 0x1259_1259, 0x1289_1289, 0x12b1_12b1, 0x12bf_12bf, 0x12c1_12c1, 0x12d7_12d7,
          0x1311_1311, 0x170d_170d, 0x176d_176d, 0x1771_1771, 0x180f_180f, 0x191f_191f, 0x1a5f_1a5f, 0x1dfa_1dfa,
          0x1f58_1f58, 0x1f5a_1f5a, 0x1f5c_1f5c, 0x1f5e_1f5e, 0x1fb5_1fb5, 0x1fc5_1fc5, 0x1fdc_1fdc, 0x1ff5_1ff5,
          0x1fff_1fff, 0x2065_2065, 0x208f_208f, 0x2b96_2b96, 0x2c2f_2c2f, 0x2c5f_2c5f, 0x2d26_2d26, 0x2da7_2da7,
          0x2daf_2daf, 0x2db7_2db7, 0x2dbf_2dbf, 0x2dc7_2dc7, 0x2dcf_2dcf, 0x2dd7_2dd7, 0x2ddf_2ddf, 0x2e9a_2e9a,
          0x3040_3040, 0x3130_3130, 0x318f_318f, 0x321f_321f, 0xa9ce_a9ce, 0xa9ff_a9ff, 0xab00_ab00, 0xab27_ab27,
          0xab2f_ab2f, 0xfb37_fb37, 0xfb3d_fb3d, 0xfb3f_fb3f, 0xfb42_fb42, 0xfb45_fb45, 0xfe53_fe53, 0xfe67_fe67,
          0xfe75_fe75, 0xff00_ff00, 0xffe7_ffe7, 0xffef_ffef},
        new long [] { 
          0x0001000c_0001000cL, 0x00010027_00010027L, 0x0001003b_0001003bL, 0x0001003e_0001003eL,
          0x0001018f_0001018fL, 0x0001039e_0001039eL, 0x00010809_00010809L, 0x00010836_00010836L,
          0x00010856_00010856L, 0x000108f3_000108f3L, 0x00010a04_00010a04L, 0x00010a14_00010a14L,
          0x00010a18_00010a18L, 0x00010e7f_00010e7fL, 0x00010eaa_00010eaaL, 0x00011135_00011135L,
          0x000111e0_000111e0L, 0x00011212_00011212L, 0x00011287_00011287L, 0x00011289_00011289L,
          0x0001128e_0001128eL, 0x0001129e_0001129eL, 0x00011304_00011304L, 0x00011329_00011329L,
          0x00011331_00011331L, 0x00011334_00011334L, 0x0001133a_0001133aL, 0x0001145c_0001145cL,
          0x00011914_00011914L, 0x00011917_00011917L, 0x00011936_00011936L, 0x00011c09_00011c09L,
          0x00011c37_00011c37L, 0x00011ca8_00011ca8L, 0x00011d07_00011d07L, 0x00011d0a_00011d0aL,
          0x00011d3b_00011d3bL, 0x00011d3e_00011d3eL, 0x00011d66_00011d66L, 0x00011d69_00011d69L,
          0x00011d8f_00011d8fL, 0x00011d92_00011d92L, 0x0001246f_0001246fL, 0x0001342f_0001342fL,
          0x00016a5f_00016a5fL, 0x00016b5a_00016b5aL, 0x00016b62_00016b62L, 0x0001d455_0001d455L,
          0x0001d49d_0001d49dL, 0x0001d4ad_0001d4adL, 0x0001d4ba_0001d4baL, 0x0001d4bc_0001d4bcL,
          0x0001d4c4_0001d4c4L, 0x0001d506_0001d506L, 0x0001d515_0001d515L, 0x0001d51d_0001d51dL,
          0x0001d53a_0001d53aL, 0x0001d53f_0001d53fL, 0x0001d545_0001d545L, 0x0001d551_0001d551L,
          0x0001daa0_0001daa0L, 0x0001e007_0001e007L, 0x0001e022_0001e022L, 0x0001e025_0001e025L,
          0x0001ec70_0001ec70L, 0x0001ed00_0001ed00L, 0x0001ee04_0001ee04L, 0x0001ee20_0001ee20L,
          0x0001ee23_0001ee23L, 0x0001ee28_0001ee28L, 0x0001ee33_0001ee33L, 0x0001ee38_0001ee38L,
          0x0001ee3a_0001ee3aL, 0x0001ee48_0001ee48L, 0x0001ee4a_0001ee4aL, 0x0001ee4c_0001ee4cL,
          0x0001ee50_0001ee50L, 0x0001ee53_0001ee53L, 0x0001ee58_0001ee58L, 0x0001ee5a_0001ee5aL,
          0x0001ee5c_0001ee5cL, 0x0001ee5e_0001ee5eL, 0x0001ee60_0001ee60L, 0x0001ee63_0001ee63L,
          0x0001ee6b_0001ee6bL, 0x0001ee73_0001ee73L, 0x0001ee78_0001ee78L, 0x0001ee7d_0001ee7dL,
          0x0001ee7f_0001ee7fL, 0x0001ee8a_0001ee8aL, 0x0001eea4_0001eea4L, 0x0001eeaa_0001eeaaL,
          0x0001f0c0_0001f0c0L, 0x0001f0d0_0001f0d0L, 0x0001f979_0001f979L, 0x0001f9cc_0001f9ccL,
          0x0001fb93_0001fb93L, 0x000e0000_000e0000L}),
    /** C – Cc | Cf | Cs | Co | Cn */
    OTHER("C", "Other",
        new int [] {
          0x0000_001f, 0x007f_009f, 0x00ad_00ad, 0x038b_038b, 0x038d_038d, 0x03a2_03a2, 0x0530_0530, 0x0590_0590,
          0x0600_0605, 0x061c_061d, 0x06dd_06dd, 0x070e_070f, 0x083f_083f, 0x085f_085f, 0x08b5_08b5, 0x08e2_08e2,
          0x0984_0984, 0x09a9_09a9, 0x09b1_09b1, 0x09de_09de, 0x09ff_0a00, 0x0a04_0a04, 0x0a29_0a29, 0x0a31_0a31,
          0x0a34_0a34, 0x0a37_0a37, 0x0a3d_0a3d, 0x0a5d_0a5d, 0x0a80_0a80, 0x0a84_0a84, 0x0a8e_0a8e, 0x0a92_0a92,
          0x0aa9_0aa9, 0x0ab1_0ab1, 0x0ab4_0ab4, 0x0ac6_0ac6, 0x0aca_0aca, 0x0b00_0b00, 0x0b04_0b04, 0x0b29_0b29,
          0x0b31_0b31, 0x0b34_0b34, 0x0b5e_0b5e, 0x0b84_0b84, 0x0b91_0b91, 0x0b9b_0b9b, 0x0b9d_0b9d, 0x0bc9_0bc9,
          0x0c0d_0c0d, 0x0c11_0c11, 0x0c29_0c29, 0x0c45_0c45, 0x0c49_0c49, 0x0c57_0c57, 0x0c8d_0c8d, 0x0c91_0c91,
          0x0ca9_0ca9, 0x0cb4_0cb4, 0x0cc5_0cc5, 0x0cc9_0cc9, 0x0cdf_0cdf, 0x0cf0_0cf0, 0x0d0d_0d0d, 0x0d11_0d11,
          0x0d45_0d45, 0x0d49_0d49, 0x0d80_0d80, 0x0d84_0d84, 0x0db2_0db2, 0x0dbc_0dbc, 0x0dd5_0dd5, 0x0dd7_0dd7,
          0x0e00_0e00, 0x0e80_0e80, 0x0e83_0e83, 0x0e85_0e85, 0x0e8b_0e8b, 0x0ea4_0ea4, 0x0ea6_0ea6, 0x0ec5_0ec5,
          0x0ec7_0ec7, 0x0f48_0f48, 0x0f98_0f98, 0x0fbd_0fbd, 0x0fcd_0fcd, 0x10c6_10c6, 0x1249_1249, 0x1257_1257,
          0x1259_1259, 0x1289_1289, 0x12b1_12b1, 0x12bf_12bf, 0x12c1_12c1, 0x12d7_12d7, 0x1311_1311, 0x170d_170d,
          0x176d_176d, 0x1771_1771, 0x180e_180f, 0x191f_191f, 0x1a5f_1a5f, 0x1dfa_1dfa, 0x1f58_1f58, 0x1f5a_1f5a,
          0x1f5c_1f5c, 0x1f5e_1f5e, 0x1fb5_1fb5, 0x1fc5_1fc5, 0x1fdc_1fdc, 0x1ff5_1ff5, 0x1fff_1fff, 0x200b_200f,
          0x202a_202e, 0x2060_206f, 0x208f_208f, 0x2b96_2b96, 0x2c2f_2c2f, 0x2c5f_2c5f, 0x2d26_2d26, 0x2da7_2da7,
          0x2daf_2daf, 0x2db7_2db7, 0x2dbf_2dbf, 0x2dc7_2dc7, 0x2dcf_2dcf, 0x2dd7_2dd7, 0x2ddf_2ddf, 0x2e9a_2e9a,
          0x3040_3040, 0x3130_3130, 0x318f_318f, 0x321f_321f, 0xa9ce_a9ce, 0xa9ff_a9ff, 0xab00_ab00, 0xab27_ab27,
          0xab2f_ab2f, 0xfb37_fb37, 0xfb3d_fb3d, 0xfb3f_fb3f, 0xfb42_fb42, 0xfb45_fb45, 0xfe53_fe53, 0xfe67_fe67,
          0xfe75_fe75, 0xfeff_ff00, 0xffe7_ffe7, 0xffef_ffef, 0xfff9_fffb},
        new long [] { 
          0x0001000c_0001000cL, 0x00010027_00010027L, 0x0001003b_0001003bL, 0x0001003e_0001003eL,
          0x0001018f_0001018fL, 0x0001039e_0001039eL, 0x00010809_00010809L, 0x00010836_00010836L,
          0x00010856_00010856L, 0x000108f3_000108f3L, 0x00010a04_00010a04L, 0x00010a14_00010a14L,
          0x00010a18_00010a18L, 0x00010e7f_00010e7fL, 0x00010eaa_00010eaaL, 0x000110bd_000110bdL,
          0x000110cd_000110cdL, 0x00011135_00011135L, 0x000111e0_000111e0L, 0x00011212_00011212L,
          0x00011287_00011287L, 0x00011289_00011289L, 0x0001128e_0001128eL, 0x0001129e_0001129eL,
          0x00011304_00011304L, 0x00011329_00011329L, 0x00011331_00011331L, 0x00011334_00011334L,
          0x0001133a_0001133aL, 0x0001145c_0001145cL, 0x00011914_00011914L, 0x00011917_00011917L,
          0x00011936_00011936L, 0x00011c09_00011c09L, 0x00011c37_00011c37L, 0x00011ca8_00011ca8L,
          0x00011d07_00011d07L, 0x00011d0a_00011d0aL, 0x00011d3b_00011d3bL, 0x00011d3e_00011d3eL,
          0x00011d66_00011d66L, 0x00011d69_00011d69L, 0x00011d8f_00011d8fL, 0x00011d92_00011d92L,
          0x0001246f_0001246fL, 0x0001342f_00013438L, 0x00016a5f_00016a5fL, 0x00016b5a_00016b5aL,
          0x00016b62_00016b62L, 0x0001bca0_0001bca3L, 0x0001d173_0001d17aL, 0x0001d455_0001d455L,
          0x0001d49d_0001d49dL, 0x0001d4ad_0001d4adL, 0x0001d4ba_0001d4baL, 0x0001d4bc_0001d4bcL,
          0x0001d4c4_0001d4c4L, 0x0001d506_0001d506L, 0x0001d515_0001d515L, 0x0001d51d_0001d51dL,
          0x0001d53a_0001d53aL, 0x0001d53f_0001d53fL, 0x0001d545_0001d545L, 0x0001d551_0001d551L,
          0x0001daa0_0001daa0L, 0x0001e007_0001e007L, 0x0001e022_0001e022L, 0x0001e025_0001e025L,
          0x0001ec70_0001ec70L, 0x0001ed00_0001ed00L, 0x0001ee04_0001ee04L, 0x0001ee20_0001ee20L,
          0x0001ee23_0001ee23L, 0x0001ee28_0001ee28L, 0x0001ee33_0001ee33L, 0x0001ee38_0001ee38L,
          0x0001ee3a_0001ee3aL, 0x0001ee48_0001ee48L, 0x0001ee4a_0001ee4aL, 0x0001ee4c_0001ee4cL,
          0x0001ee50_0001ee50L, 0x0001ee53_0001ee53L, 0x0001ee58_0001ee58L, 0x0001ee5a_0001ee5aL,
          0x0001ee5c_0001ee5cL, 0x0001ee5e_0001ee5eL, 0x0001ee60_0001ee60L, 0x0001ee63_0001ee63L,
          0x0001ee6b_0001ee6bL, 0x0001ee73_0001ee73L, 0x0001ee78_0001ee78L, 0x0001ee7d_0001ee7dL,
          0x0001ee7f_0001ee7fL, 0x0001ee8a_0001ee8aL, 0x0001eea4_0001eea4L, 0x0001eeaa_0001eeaaL,
          0x0001f0c0_0001f0c0L, 0x0001f0d0_0001f0d0L, 0x0001f979_0001f979L, 0x0001f9cc_0001f9ccL,
          0x0001fb93_0001fb93L, 0x000e0000_000e0001L, 0x000e0020_000e007fL});

    private final String abbreviation;
    private final String alias;
    private final int [] lowerCodePointRanges;
    private final long [] upperCodePointRanges;
    private Category(final String abbreviation, final String alias,
            final int [] lowerCodePointRanges, final long [] upperCodePointRanges) {
        this.abbreviation = abbreviation;
        this.alias = alias;
        this.lowerCodePointRanges = lowerCodePointRanges;
        this.upperCodePointRanges = upperCodePointRanges;
    }
    public String getAbbreviation() {
      return abbreviation;
    }
    public String getAlias() {
      return alias;
    }
    public int [] getLowerCodePointRanges() {
        return Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length);
    }
    public long [] getUpperCodePointRanges() {
        return Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length);
    }
  }


  public enum Script implements Subset {
    ADLM("ADLM",
        new int [0],
        new long [] { 
          0x0001e900_0001e94bL, 0x0001e950_0001e959L, 0x0001e95e_0001e95fL}),
    AGHB("AGHB",
        new int [0],
        new long [] { 
          0x00010530_00010563L, 0x0001056f_0001056fL}),
    AHOM("AHOM",
        new int [0],
        new long [] { 
          0x00011700_0001171aL, 0x0001171d_0001172bL, 0x00011730_0001173fL}),
    ARAB("ARAB",
        new int [] {
          0x0600_0604, 0x0606_060b, 0x060d_061a, 0x061c_061c, 0x061e_061e, 0x0620_063f, 0x0641_064a, 0x0656_066f,
          0x0671_06dc, 0x06de_06ff, 0x0750_077f, 0x08a0_08b4, 0x08b6_08c7, 0x08d3_08e1, 0x08e3_08ff, 0xfb50_fbc1,
          0xfbd3_fd3d, 0xfd50_fd8f, 0xfd92_fdc7, 0xfdf0_fdfd, 0xfe70_fe74, 0xfe76_fefc},
        new long [] { 
          0x00010e60_00010e7eL, 0x0001ee00_0001ee03L, 0x0001ee05_0001ee1fL, 0x0001ee21_0001ee22L,
          0x0001ee24_0001ee24L, 0x0001ee27_0001ee27L, 0x0001ee29_0001ee32L, 0x0001ee34_0001ee37L,
          0x0001ee39_0001ee39L, 0x0001ee3b_0001ee3bL, 0x0001ee42_0001ee42L, 0x0001ee47_0001ee47L,
          0x0001ee49_0001ee49L, 0x0001ee4b_0001ee4bL, 0x0001ee4d_0001ee4fL, 0x0001ee51_0001ee52L,
          0x0001ee54_0001ee54L, 0x0001ee57_0001ee57L, 0x0001ee59_0001ee59L, 0x0001ee5b_0001ee5bL,
          0x0001ee5d_0001ee5dL, 0x0001ee5f_0001ee5fL, 0x0001ee61_0001ee62L, 0x0001ee64_0001ee64L,
          0x0001ee67_0001ee6aL, 0x0001ee6c_0001ee72L, 0x0001ee74_0001ee77L, 0x0001ee79_0001ee7cL,
          0x0001ee7e_0001ee7eL, 0x0001ee80_0001ee89L, 0x0001ee8b_0001ee9bL, 0x0001eea1_0001eea3L,
          0x0001eea5_0001eea9L, 0x0001eeab_0001eebbL, 0x0001eef0_0001eef1L}),
    ARMI("ARMI",
        new int [0],
        new long [] { 
          0x00010840_00010855L, 0x00010857_0001085fL}),
    ARMN("ARMN",
        new int [] {
          0x0531_0556, 0x0559_058a, 0x058d_058f, 0xfb13_fb17},
        new long [0]),
    AVST("AVST",
        new int [0],
        new long [] { 
          0x00010b00_00010b35L, 0x00010b39_00010b3fL}),
    BALI("BALI",
        new int [] {
          0x1b00_1b4b, 0x1b50_1b7c},
        new long [0]),
    BAMU("BAMU",
        new int [] {
          0xa6a0_a6f7},
        new long [] { 
          0x00016800_00016a38L}),
    BASS("BASS",
        new int [0],
        new long [] { 
          0x00016ad0_00016aedL, 0x00016af0_00016af5L}),
    BATK("BATK",
        new int [] {
          0x1bc0_1bf3, 0x1bfc_1bff},
        new long [0]),
    BENG("BENG",
        new int [] {
          0x0980_0983, 0x0985_098c, 0x098f_0990, 0x0993_09a8, 0x09aa_09b0, 0x09b2_09b2, 0x09b6_09b9, 0x09bc_09c4,
          0x09c7_09c8, 0x09cb_09ce, 0x09d7_09d7, 0x09dc_09dd, 0x09df_09e3, 0x09e6_09fe},
        new long [0]),
    BHKS("BHKS",
        new int [0],
        new long [] { 
          0x00011c00_00011c08L, 0x00011c0a_00011c36L, 0x00011c38_00011c45L, 0x00011c50_00011c6cL}),
    BOPO("BOPO",
        new int [] {
          0x02ea_02eb, 0x3105_312f, 0x31a0_31bf},
        new long [0]),
    BRAH("BRAH",
        new int [0],
        new long [] { 
          0x00011000_0001104dL, 0x00011052_0001106fL, 0x0001107f_0001107fL}),
    BRAI("BRAI",
        new int [] {
          0x2800_28ff},
        new long [0]),
    BUGI("BUGI",
        new int [] {
          0x1a00_1a1b, 0x1a1e_1a1f},
        new long [0]),
    BUHD("BUHD",
        new int [] {
          0x1740_1753},
        new long [0]),
    CAKM("CAKM",
        new int [0],
        new long [] { 
          0x00011100_00011134L, 0x00011136_00011147L}),
    CANS("CANS",
        new int [] {
          0x1400_167f, 0x18b0_18f5},
        new long [0]),
    CARI("CARI",
        new int [0],
        new long [] { 
          0x000102a0_000102d0L}),
    CHAM("CHAM",
        new int [] {
          0xaa00_aa36, 0xaa40_aa4d, 0xaa50_aa59, 0xaa5c_aa5f},
        new long [0]),
    CHER("CHER",
        new int [] {
          0x13a0_13f5, 0x13f8_13fd, 0xab70_abbf},
        new long [0]),
    CHRS("CHRS",
        new int [0],
        new long [] { 
          0x00010fb0_00010fcbL}),
    COPT("COPT",
        new int [] {
          0x03e2_03ef, 0x2c80_2cf3, 0x2cf9_2cff},
        new long [0]),
    CPRT("CPRT",
        new int [0],
        new long [] { 
          0x00010800_00010805L, 0x00010808_00010808L, 0x0001080a_00010835L, 0x00010837_00010838L,
          0x0001083c_0001083cL, 0x0001083f_0001083fL}),
    CYRL("CYRL",
        new int [] {
          0x0400_0484, 0x0487_052f, 0x1c80_1c88, 0x1d2b_1d2b, 0x1d78_1d78, 0x2de0_2dff, 0xa640_a69f, 0xfe2e_fe2f},
        new long [0]),
    DEVA("DEVA",
        new int [] {
          0x0900_0950, 0x0955_0963, 0x0966_097f, 0xa8e0_a8ff},
        new long [0]),
    DIAK("DIAK",
        new int [0],
        new long [] { 
          0x00011900_00011906L, 0x00011909_00011909L, 0x0001190c_00011913L, 0x00011915_00011916L,
          0x00011918_00011935L, 0x00011937_00011938L, 0x0001193b_00011946L, 0x00011950_00011959L}),
    DOGR("DOGR",
        new int [0],
        new long [] { 
          0x00011800_0001183bL}),
    DSRT("DSRT",
        new int [0],
        new long [] { 
          0x00010400_0001044fL}),
    DUPL("DUPL",
        new int [0],
        new long [] { 
          0x0001bc00_0001bc6aL, 0x0001bc70_0001bc7cL, 0x0001bc80_0001bc88L, 0x0001bc90_0001bc99L,
          0x0001bc9c_0001bc9fL}),
    EGYP("EGYP",
        new int [0],
        new long [] { 
          0x00013000_0001342eL, 0x00013430_00013438L}),
    ELBA("ELBA",
        new int [0],
        new long [] { 
          0x00010500_00010527L}),
    ELYM("ELYM",
        new int [0],
        new long [] { 
          0x00010fe0_00010ff6L}),
    ETHI("ETHI",
        new int [] {
          0x1200_1248, 0x124a_124d, 0x1250_1256, 0x1258_1258, 0x125a_125d, 0x1260_1288, 0x128a_128d, 0x1290_12b0,
          0x12b2_12b5, 0x12b8_12be, 0x12c0_12c0, 0x12c2_12c5, 0x12c8_12d6, 0x12d8_1310, 0x1312_1315, 0x1318_135a,
          0x135d_137c, 0x1380_1399, 0x2d80_2d96, 0x2da0_2da6, 0x2da8_2dae, 0x2db0_2db6, 0x2db8_2dbe, 0x2dc0_2dc6,
          0x2dc8_2dce, 0x2dd0_2dd6, 0x2dd8_2dde, 0xab01_ab06, 0xab09_ab0e, 0xab11_ab16, 0xab20_ab26, 0xab28_ab2e},
        new long [0]),
    GEOR("GEOR",
        new int [] {
          0x10a0_10c5, 0x10c7_10c7, 0x10cd_10cd, 0x10d0_10fa, 0x10fc_10ff, 0x1c90_1cba, 0x1cbd_1cbf, 0x2d00_2d25,
          0x2d27_2d27, 0x2d2d_2d2d},
        new long [0]),
    GLAG("GLAG",
        new int [] {
          0x2c00_2c2e, 0x2c30_2c5e},
        new long [] { 
          0x0001e000_0001e006L, 0x0001e008_0001e018L, 0x0001e01b_0001e021L, 0x0001e023_0001e024L,
          0x0001e026_0001e02aL}),
    GONG("GONG",
        new int [0],
        new long [] { 
          0x00011d60_00011d65L, 0x00011d67_00011d68L, 0x00011d6a_00011d8eL, 0x00011d90_00011d91L,
          0x00011d93_00011d98L, 0x00011da0_00011da9L}),
    GONM("GONM",
        new int [0],
        new long [] { 
          0x00011d00_00011d06L, 0x00011d08_00011d09L, 0x00011d0b_00011d36L, 0x00011d3a_00011d3aL,
          0x00011d3c_00011d3dL, 0x00011d3f_00011d47L, 0x00011d50_00011d59L}),
    GOTH("GOTH",
        new int [0],
        new long [] { 
          0x00010330_0001034aL}),
    GRAN("GRAN",
        new int [0],
        new long [] { 
          0x00011300_00011303L, 0x00011305_0001130cL, 0x0001130f_00011310L, 0x00011313_00011328L,
          0x0001132a_00011330L, 0x00011332_00011333L, 0x00011335_00011339L, 0x0001133c_00011344L,
          0x00011347_00011348L, 0x0001134b_0001134dL, 0x00011350_00011350L, 0x00011357_00011357L,
          0x0001135d_00011363L, 0x00011366_0001136cL, 0x00011370_00011374L}),
    GREK("GREK",
        new int [] {
          0x0370_0373, 0x0375_0377, 0x037a_037d, 0x037f_037f, 0x0384_0384, 0x0386_0386, 0x0388_038a, 0x038c_038c,
          0x038e_03a1, 0x03a3_03e1, 0x03f0_03ff, 0x1d26_1d2a, 0x1d5d_1d61, 0x1d66_1d6a, 0x1dbf_1dbf, 0x1f00_1f15,
          0x1f18_1f1d, 0x1f20_1f45, 0x1f48_1f4d, 0x1f50_1f57, 0x1f59_1f59, 0x1f5b_1f5b, 0x1f5d_1f5d, 0x1f5f_1f7d,
          0x1f80_1fb4, 0x1fb6_1fc4, 0x1fc6_1fd3, 0x1fd6_1fdb, 0x1fdd_1fef, 0x1ff2_1ff4, 0x1ff6_1ffe, 0x2126_2126,
          0xab65_ab65},
        new long [] { 
          0x00010140_0001018eL, 0x000101a0_000101a0L, 0x0001d200_0001d245L}),
    GUJR("GUJR",
        new int [] {
          0x0a81_0a83, 0x0a85_0a8d, 0x0a8f_0a91, 0x0a93_0aa8, 0x0aaa_0ab0, 0x0ab2_0ab3, 0x0ab5_0ab9, 0x0abc_0ac5,
          0x0ac7_0ac9, 0x0acb_0acd, 0x0ad0_0ad0, 0x0ae0_0ae3, 0x0ae6_0af1, 0x0af9_0aff},
        new long [0]),
    GURU("GURU",
        new int [] {
          0x0a01_0a03, 0x0a05_0a0a, 0x0a0f_0a10, 0x0a13_0a28, 0x0a2a_0a30, 0x0a32_0a33, 0x0a35_0a36, 0x0a38_0a39,
          0x0a3c_0a3c, 0x0a3e_0a42, 0x0a47_0a48, 0x0a4b_0a4d, 0x0a51_0a51, 0x0a59_0a5c, 0x0a5e_0a5e, 0x0a66_0a76},
        new long [0]),
    HANG("HANG",
        new int [] {
          0x1100_11ff, 0x302e_302f, 0x3131_318e, 0x3200_321e, 0x3260_327e, 0xa960_a97c, 0xac00_d7a3, 0xd7b0_d7c6,
          0xd7cb_d7fb, 0xffa0_ffbe, 0xffc2_ffc7, 0xffca_ffcf, 0xffd2_ffd7, 0xffda_ffdc},
        new long [0]),
    HANI("HANI",
        new int [] {
          0x2e80_2e99, 0x2e9b_2ef3, 0x2f00_2fd5, 0x3005_3005, 0x3007_3007, 0x3021_3029, 0x3038_303b, 0x3400_4dbf,
          0x4e00_9ffc, 0xf900_fa6d, 0xfa70_fad9},
        new long [] { 
          0x00016ff0_00016ff1L, 0x00020000_0002a6ddL, 0x0002a700_0002b734L, 0x0002b740_0002b81dL,
          0x0002b820_0002cea1L, 0x0002ceb0_0002ebe0L, 0x0002f800_0002fa1dL, 0x00030000_0003134aL}),
    HANO("HANO",
        new int [] {
          0x1720_1734},
        new long [0]),
    HATR("HATR",
        new int [0],
        new long [] { 
          0x000108e0_000108f2L, 0x000108f4_000108f5L, 0x000108fb_000108ffL}),
    HEBR("HEBR",
        new int [] {
          0x0591_05c7, 0x05d0_05ea, 0x05ef_05f4, 0xfb1d_fb36, 0xfb38_fb3c, 0xfb3e_fb3e, 0xfb40_fb41, 0xfb43_fb44,
          0xfb46_fb4f},
        new long [0]),
    HIRA("HIRA",
        new int [] {
          0x3041_3096, 0x309d_309f},
        new long [] { 
          0x0001b001_0001b11eL, 0x0001b150_0001b152L, 0x0001f200_0001f200L}),
    HLUW("HLUW",
        new int [0],
        new long [] { 
          0x00014400_00014646L}),
    HMNG("HMNG",
        new int [0],
        new long [] { 
          0x00016b00_00016b45L, 0x00016b50_00016b59L, 0x00016b5b_00016b61L, 0x00016b63_00016b77L,
          0x00016b7d_00016b8fL}),
    HMNP("HMNP",
        new int [0],
        new long [] { 
          0x0001e100_0001e12cL, 0x0001e130_0001e13dL, 0x0001e140_0001e149L, 0x0001e14e_0001e14fL}),
    HUNG("HUNG",
        new int [0],
        new long [] { 
          0x00010c80_00010cb2L, 0x00010cc0_00010cf2L, 0x00010cfa_00010cffL}),
    ITAL("ITAL",
        new int [0],
        new long [] { 
          0x00010300_00010323L, 0x0001032d_0001032fL}),
    JAVA("JAVA",
        new int [] {
          0xa980_a9cd, 0xa9d0_a9d9, 0xa9de_a9df},
        new long [0]),
    KALI("KALI",
        new int [] {
          0xa900_a92d, 0xa92f_a92f},
        new long [0]),
    KANA("KANA",
        new int [] {
          0x30a1_30fa, 0x30fd_30ff, 0x31f0_31ff, 0x32d0_32fe, 0x3300_3357, 0xff66_ff6f, 0xff71_ff9d},
        new long [] { 
          0x0001b000_0001b000L, 0x0001b164_0001b167L}),
    KHAR("KHAR",
        new int [0],
        new long [] { 
          0x00010a00_00010a03L, 0x00010a05_00010a06L, 0x00010a0c_00010a13L, 0x00010a15_00010a17L,
          0x00010a19_00010a35L, 0x00010a38_00010a3aL, 0x00010a3f_00010a48L, 0x00010a50_00010a58L}),
    KHMR("KHMR",
        new int [] {
          0x1780_17dd, 0x17e0_17e9, 0x17f0_17f9, 0x19e0_19ff},
        new long [0]),
    KHOJ("KHOJ",
        new int [0],
        new long [] { 
          0x00011200_00011211L, 0x00011213_0001123eL}),
    KITS("KITS",
        new int [0],
        new long [] { 
          0x00016fe4_00016fe4L, 0x00018b00_00018cd5L}),
    KNDA("KNDA",
        new int [] {
          0x0c80_0c8c, 0x0c8e_0c90, 0x0c92_0ca8, 0x0caa_0cb3, 0x0cb5_0cb9, 0x0cbc_0cc4, 0x0cc6_0cc8, 0x0cca_0ccd,
          0x0cd5_0cd6, 0x0cde_0cde, 0x0ce0_0ce3, 0x0ce6_0cef, 0x0cf1_0cf2},
        new long [0]),
    KTHI("KTHI",
        new int [0],
        new long [] { 
          0x00011080_000110c1L, 0x000110cd_000110cdL}),
    LANA("LANA",
        new int [] {
          0x1a20_1a5e, 0x1a60_1a7c, 0x1a7f_1a89, 0x1a90_1a99, 0x1aa0_1aad},
        new long [0]),
    LAOO("LAOO",
        new int [] {
          0x0e81_0e82, 0x0e84_0e84, 0x0e86_0e8a, 0x0e8c_0ea3, 0x0ea5_0ea5, 0x0ea7_0ebd, 0x0ec0_0ec4, 0x0ec6_0ec6,
          0x0ec8_0ecd, 0x0ed0_0ed9, 0x0edc_0edf},
        new long [0]),
    LATN("LATN",
        new int [] {
          0x0041_005a, 0x0061_007a, 0x00aa_00aa, 0x00ba_00ba, 0x00c0_00d6, 0x00d8_00f6, 0x00f8_02b8, 0x02e0_02e4,
          0x1d00_1d25, 0x1d2c_1d5c, 0x1d62_1d65, 0x1d6b_1d77, 0x1d79_1dbe, 0x1e00_1eff, 0x2071_2071, 0x207f_207f,
          0x2090_209c, 0x212a_212b, 0x2132_2132, 0x214e_214e, 0x2160_2188, 0x2c60_2c7f, 0xa722_a787, 0xa78b_a7bf,
          0xa7c2_a7ca, 0xa7f5_a7ff, 0xab30_ab5a, 0xab5c_ab64, 0xab66_ab69, 0xfb00_fb06, 0xff21_ff3a, 0xff41_ff5a},
        new long [0]),
    LEPC("LEPC",
        new int [] {
          0x1c00_1c37, 0x1c3b_1c49, 0x1c4d_1c4f},
        new long [0]),
    LIMB("LIMB",
        new int [] {
          0x1900_191e, 0x1920_192b, 0x1930_193b, 0x1940_1940, 0x1944_194f},
        new long [0]),
    LINA("LINA",
        new int [0],
        new long [] { 
          0x00010600_00010736L, 0x00010740_00010755L, 0x00010760_00010767L}),
    LINB("LINB",
        new int [0],
        new long [] { 
          0x00010000_0001000bL, 0x0001000d_00010026L, 0x00010028_0001003aL, 0x0001003c_0001003dL,
          0x0001003f_0001004dL, 0x00010050_0001005dL, 0x00010080_000100faL}),
    LISU("LISU",
        new int [] {
          0xa4d0_a4ff},
        new long [] { 
          0x00011fb0_00011fb0L}),
    LYCI("LYCI",
        new int [0],
        new long [] { 
          0x00010280_0001029cL}),
    LYDI("LYDI",
        new int [0],
        new long [] { 
          0x00010920_00010939L, 0x0001093f_0001093fL}),
    MAHJ("MAHJ",
        new int [0],
        new long [] { 
          0x00011150_00011176L}),
    MAKA("MAKA",
        new int [0],
        new long [] { 
          0x00011ee0_00011ef8L}),
    MAND("MAND",
        new int [] {
          0x0840_085b, 0x085e_085e},
        new long [0]),
    MANI("MANI",
        new int [0],
        new long [] { 
          0x00010ac0_00010ae6L, 0x00010aeb_00010af6L}),
    MARC("MARC",
        new int [0],
        new long [] { 
          0x00011c70_00011c8fL, 0x00011c92_00011ca7L, 0x00011ca9_00011cb6L}),
    MEDF("MEDF",
        new int [0],
        new long [] { 
          0x00016e40_00016e9aL}),
    MEND("MEND",
        new int [0],
        new long [] { 
          0x0001e800_0001e8c4L, 0x0001e8c7_0001e8d6L}),
    MERC("MERC",
        new int [0],
        new long [] { 
          0x000109a0_000109b7L, 0x000109bc_000109cfL, 0x000109d2_000109ffL}),
    MERO("MERO",
        new int [0],
        new long [] { 
          0x00010980_0001099fL}),
    MLYM("MLYM",
        new int [] {
          0x0d00_0d0c, 0x0d0e_0d10, 0x0d12_0d44, 0x0d46_0d48, 0x0d4a_0d4f, 0x0d54_0d63, 0x0d66_0d7f},
        new long [0]),
    MODI("MODI",
        new int [0],
        new long [] { 
          0x00011600_00011644L, 0x00011650_00011659L}),
    MONG("MONG",
        new int [] {
          0x1800_1801, 0x1804_1804, 0x1806_180e, 0x1810_1819, 0x1820_1878, 0x1880_18aa},
        new long [] { 
          0x00011660_0001166cL}),
    MROO("MROO",
        new int [0],
        new long [] { 
          0x00016a40_00016a5eL, 0x00016a60_00016a69L, 0x00016a6e_00016a6fL}),
    MTEI("MTEI",
        new int [] {
          0xaae0_aaf6, 0xabc0_abed, 0xabf0_abf9},
        new long [0]),
    MULT("MULT",
        new int [0],
        new long [] { 
          0x00011280_00011286L, 0x00011288_00011288L, 0x0001128a_0001128dL, 0x0001128f_0001129dL,
          0x0001129f_000112a9L}),
    MYMR("MYMR",
        new int [] {
          0x1000_109f, 0xa9e0_a9fe, 0xaa60_aa7f},
        new long [0]),
    NAND("NAND",
        new int [0],
        new long [] { 
          0x000119a0_000119a7L, 0x000119aa_000119d7L, 0x000119da_000119e4L}),
    NARB("NARB",
        new int [0],
        new long [] { 
          0x00010a80_00010a9fL}),
    NBAT("NBAT",
        new int [0],
        new long [] { 
          0x00010880_0001089eL, 0x000108a7_000108afL}),
    NEWA("NEWA",
        new int [0],
        new long [] { 
          0x00011400_0001145bL, 0x0001145d_00011461L}),
    NKOO("NKOO",
        new int [] {
          0x07c0_07fa, 0x07fd_07ff},
        new long [0]),
    NSHU("NSHU",
        new int [0],
        new long [] { 
          0x00016fe1_00016fe1L, 0x0001b170_0001b2fbL}),
    OGAM("OGAM",
        new int [] {
          0x1680_169c},
        new long [0]),
    OLCK("OLCK",
        new int [] {
          0x1c50_1c7f},
        new long [0]),
    ORKH("ORKH",
        new int [0],
        new long [] { 
          0x00010c00_00010c48L}),
    ORYA("ORYA",
        new int [] {
          0x0b01_0b03, 0x0b05_0b0c, 0x0b0f_0b10, 0x0b13_0b28, 0x0b2a_0b30, 0x0b32_0b33, 0x0b35_0b39, 0x0b3c_0b44,
          0x0b47_0b48, 0x0b4b_0b4d, 0x0b55_0b57, 0x0b5c_0b5d, 0x0b5f_0b63, 0x0b66_0b77},
        new long [0]),
    OSGE("OSGE",
        new int [0],
        new long [] { 
          0x000104b0_000104d3L, 0x000104d8_000104fbL}),
    OSMA("OSMA",
        new int [0],
        new long [] { 
          0x00010480_0001049dL, 0x000104a0_000104a9L}),
    PALM("PALM",
        new int [0],
        new long [] { 
          0x00010860_0001087fL}),
    PAUC("PAUC",
        new int [0],
        new long [] { 
          0x00011ac0_00011af8L}),
    PERM("PERM",
        new int [0],
        new long [] { 
          0x00010350_0001037aL}),
    PHAG("PHAG",
        new int [] {
          0xa840_a877},
        new long [0]),
    PHLI("PHLI",
        new int [0],
        new long [] { 
          0x00010b60_00010b72L, 0x00010b78_00010b7fL}),
    PHLP("PHLP",
        new int [0],
        new long [] { 
          0x00010b80_00010b91L, 0x00010b99_00010b9cL, 0x00010ba9_00010bafL}),
    PHNX("PHNX",
        new int [0],
        new long [] { 
          0x00010900_0001091bL, 0x0001091f_0001091fL}),
    PLRD("PLRD",
        new int [0],
        new long [] { 
          0x00016f00_00016f4aL, 0x00016f4f_00016f87L, 0x00016f8f_00016f9fL}),
    PRTI("PRTI",
        new int [0],
        new long [] { 
          0x00010b40_00010b55L, 0x00010b58_00010b5fL}),
    RJNG("RJNG",
        new int [] {
          0xa930_a953, 0xa95f_a95f},
        new long [0]),
    ROHG("ROHG",
        new int [0],
        new long [] { 
          0x00010d00_00010d27L, 0x00010d30_00010d39L}),
    RUNR("RUNR",
        new int [] {
          0x16a0_16ea, 0x16ee_16f8},
        new long [0]),
    SAMR("SAMR",
        new int [] {
          0x0800_082d, 0x0830_083e},
        new long [0]),
    SARB("SARB",
        new int [0],
        new long [] { 
          0x00010a60_00010a7fL}),
    SAUR("SAUR",
        new int [] {
          0xa880_a8c5, 0xa8ce_a8d9},
        new long [0]),
    SGNW("SGNW",
        new int [0],
        new long [] { 
          0x0001d800_0001da8bL, 0x0001da9b_0001da9fL, 0x0001daa1_0001daafL}),
    SHAW("SHAW",
        new int [0],
        new long [] { 
          0x00010450_0001047fL}),
    SHRD("SHRD",
        new int [0],
        new long [] { 
          0x00011180_000111dfL}),
    SIDD("SIDD",
        new int [0],
        new long [] { 
          0x00011580_000115b5L, 0x000115b8_000115ddL}),
    SIND("SIND",
        new int [0],
        new long [] { 
          0x000112b0_000112eaL, 0x000112f0_000112f9L}),
    SINH("SINH",
        new int [] {
          0x0d81_0d83, 0x0d85_0d96, 0x0d9a_0db1, 0x0db3_0dbb, 0x0dbd_0dbd, 0x0dc0_0dc6, 0x0dca_0dca, 0x0dcf_0dd4,
          0x0dd6_0dd6, 0x0dd8_0ddf, 0x0de6_0def, 0x0df2_0df4},
        new long [] { 
          0x000111e1_000111f4L}),
    SOGD("SOGD",
        new int [0],
        new long [] { 
          0x00010f30_00010f59L}),
    SOGO("SOGO",
        new int [0],
        new long [] { 
          0x00010f00_00010f27L}),
    SORA("SORA",
        new int [0],
        new long [] { 
          0x000110d0_000110e8L, 0x000110f0_000110f9L}),
    SOYO("SOYO",
        new int [0],
        new long [] { 
          0x00011a50_00011aa2L}),
    SUND("SUND",
        new int [] {
          0x1b80_1bbf, 0x1cc0_1cc7},
        new long [0]),
    SYLO("SYLO",
        new int [] {
          0xa800_a82c},
        new long [0]),
    SYRC("SYRC",
        new int [] {
          0x0700_070d, 0x070f_074a, 0x074d_074f, 0x0860_086a},
        new long [0]),
    TAGB("TAGB",
        new int [] {
          0x1760_176c, 0x176e_1770, 0x1772_1773},
        new long [0]),
    TAKR("TAKR",
        new int [0],
        new long [] { 
          0x00011680_000116b8L, 0x000116c0_000116c9L}),
    TALE("TALE",
        new int [] {
          0x1950_196d, 0x1970_1974},
        new long [0]),
    TALU("TALU",
        new int [] {
          0x1980_19ab, 0x19b0_19c9, 0x19d0_19da, 0x19de_19df},
        new long [0]),
    TAML("TAML",
        new int [] {
          0x0b82_0b83, 0x0b85_0b8a, 0x0b8e_0b90, 0x0b92_0b95, 0x0b99_0b9a, 0x0b9c_0b9c, 0x0b9e_0b9f, 0x0ba3_0ba4,
          0x0ba8_0baa, 0x0bae_0bb9, 0x0bbe_0bc2, 0x0bc6_0bc8, 0x0bca_0bcd, 0x0bd0_0bd0, 0x0bd7_0bd7, 0x0be6_0bfa},
        new long [] { 
          0x00011fc0_00011ff1L, 0x00011fff_00011fffL}),
    TANG("TANG",
        new int [0],
        new long [] { 
          0x00016fe0_00016fe0L, 0x00017000_000187f7L, 0x00018800_00018affL, 0x00018d00_00018d08L}),
    TAVT("TAVT",
        new int [] {
          0xaa80_aac2, 0xaadb_aadf},
        new long [0]),
    TELU("TELU",
        new int [] {
          0x0c00_0c0c, 0x0c0e_0c10, 0x0c12_0c28, 0x0c2a_0c39, 0x0c3d_0c44, 0x0c46_0c48, 0x0c4a_0c4d, 0x0c55_0c56,
          0x0c58_0c5a, 0x0c60_0c63, 0x0c66_0c6f, 0x0c77_0c7f},
        new long [0]),
    TFNG("TFNG",
        new int [] {
          0x2d30_2d67, 0x2d6f_2d70, 0x2d7f_2d7f},
        new long [0]),
    TGLG("TGLG",
        new int [] {
          0x1700_170c, 0x170e_1714},
        new long [0]),
    THAA("THAA",
        new int [] {
          0x0780_07b1},
        new long [0]),
    THAI("THAI",
        new int [] {
          0x0e01_0e3a, 0x0e40_0e5b},
        new long [0]),
    TIBT("TIBT",
        new int [] {
          0x0f00_0f47, 0x0f49_0f6c, 0x0f71_0f97, 0x0f99_0fbc, 0x0fbe_0fcc, 0x0fce_0fd4, 0x0fd9_0fda},
        new long [0]),
    TIRH("TIRH",
        new int [0],
        new long [] { 
          0x00011480_000114c7L, 0x000114d0_000114d9L}),
    UGAR("UGAR",
        new int [0],
        new long [] { 
          0x00010380_0001039dL, 0x0001039f_0001039fL}),
    VAII("VAII",
        new int [] {
          0xa500_a62b},
        new long [0]),
    WARA("WARA",
        new int [0],
        new long [] { 
          0x000118a0_000118f2L, 0x000118ff_000118ffL}),
    WCHO("WCHO",
        new int [0],
        new long [] { 
          0x0001e2c0_0001e2f9L, 0x0001e2ff_0001e2ffL}),
    XPEO("XPEO",
        new int [0],
        new long [] { 
          0x000103a0_000103c3L, 0x000103c8_000103d5L}),
    XSUX("XSUX",
        new int [0],
        new long [] { 
          0x00012000_00012399L, 0x00012400_0001246eL, 0x00012470_00012474L, 0x00012480_00012543L}),
    YEZI("YEZI",
        new int [0],
        new long [] { 
          0x00010e80_00010ea9L, 0x00010eab_00010eadL, 0x00010eb0_00010eb1L}),
    YIII("YIII",
        new int [] {
          0xa000_a48c, 0xa490_a4c6},
        new long [0]),
    ZANB("ZANB",
        new int [0],
        new long [] { 
          0x00011a00_00011a47L}),
    ZINH("ZINH",
        new int [] {
          0x0300_036f, 0x0485_0486, 0x064b_0655, 0x0670_0670, 0x0951_0954, 0x1ab0_1ac0, 0x1cd0_1cd2, 0x1cd4_1ce0,
          0x1ce2_1ce8, 0x1ced_1ced, 0x1cf4_1cf4, 0x1cf8_1cf9, 0x1dc0_1df9, 0x1dfb_1dff, 0x200c_200d, 0x20d0_20f0,
          0x302a_302d, 0x3099_309a, 0xfe00_fe0f, 0xfe20_fe2d},
        new long [] { 
          0x000101fd_000101fdL, 0x000102e0_000102e0L, 0x0001133b_0001133bL, 0x0001d167_0001d169L,
          0x0001d17b_0001d182L, 0x0001d185_0001d18bL, 0x0001d1aa_0001d1adL, 0x000e0100_000e01efL}),
    ZYYY("ZYYY",
        new int [] {
          0x0000_0040, 0x005b_0060, 0x007b_00a9, 0x00ab_00b9, 0x00bb_00bf, 0x00d7_00d7, 0x00f7_00f7, 0x02b9_02df,
          0x02e5_02e9, 0x02ec_02ff, 0x0374_0374, 0x037e_037e, 0x0385_0385, 0x0387_0387, 0x0605_0605, 0x060c_060c,
          0x061b_061b, 0x061f_061f, 0x0640_0640, 0x06dd_06dd, 0x08e2_08e2, 0x0964_0965, 0x0e3f_0e3f, 0x0fd5_0fd8,
          0x10fb_10fb, 0x16eb_16ed, 0x1735_1736, 0x1802_1803, 0x1805_1805, 0x1cd3_1cd3, 0x1ce1_1ce1, 0x1ce9_1cec,
          0x1cee_1cf3, 0x1cf5_1cf7, 0x1cfa_1cfa, 0x2000_200b, 0x200e_2064, 0x2066_2070, 0x2074_207e, 0x2080_208e,
          0x20a0_20bf, 0x2100_2125, 0x2127_2129, 0x212c_2131, 0x2133_214d, 0x214f_215f, 0x2189_218b, 0x2190_2426,
          0x2440_244a, 0x2460_27ff, 0x2900_2b73, 0x2b76_2b95, 0x2b97_2bff, 0x2e00_2e52, 0x2ff0_2ffb, 0x3000_3004,
          0x3006_3006, 0x3008_3020, 0x3030_3037, 0x303c_303f, 0x309b_309c, 0x30a0_30a0, 0x30fb_30fc, 0x3190_319f,
          0x31c0_31e3, 0x3220_325f, 0x327f_32cf, 0x32ff_32ff, 0x3358_33ff, 0x4dc0_4dff, 0xa700_a721, 0xa788_a78a,
          0xa830_a839, 0xa92e_a92e, 0xa9cf_a9cf, 0xab5b_ab5b, 0xab6a_ab6b, 0xfd3e_fd3f, 0xfe10_fe19, 0xfe30_fe52,
          0xfe54_fe66, 0xfe68_fe6b, 0xfeff_feff, 0xff01_ff20, 0xff3b_ff40, 0xff5b_ff65, 0xff70_ff70, 0xff9e_ff9f,
          0xffe0_ffe6, 0xffe8_ffee, 0xfff9_fffd},
        new long [] { 
          0x00010100_00010102L, 0x00010107_00010133L, 0x00010137_0001013fL, 0x00010190_0001019cL,
          0x000101d0_000101fcL, 0x000102e1_000102fbL, 0x00016fe2_00016fe3L, 0x0001bca0_0001bca3L,
          0x0001d000_0001d0f5L, 0x0001d100_0001d126L, 0x0001d129_0001d166L, 0x0001d16a_0001d17aL,
          0x0001d183_0001d184L, 0x0001d18c_0001d1a9L, 0x0001d1ae_0001d1e8L, 0x0001d2e0_0001d2f3L,
          0x0001d300_0001d356L, 0x0001d360_0001d378L, 0x0001d400_0001d454L, 0x0001d456_0001d49cL,
          0x0001d49e_0001d49fL, 0x0001d4a2_0001d4a2L, 0x0001d4a5_0001d4a6L, 0x0001d4a9_0001d4acL,
          0x0001d4ae_0001d4b9L, 0x0001d4bb_0001d4bbL, 0x0001d4bd_0001d4c3L, 0x0001d4c5_0001d505L,
          0x0001d507_0001d50aL, 0x0001d50d_0001d514L, 0x0001d516_0001d51cL, 0x0001d51e_0001d539L,
          0x0001d53b_0001d53eL, 0x0001d540_0001d544L, 0x0001d546_0001d546L, 0x0001d54a_0001d550L,
          0x0001d552_0001d6a5L, 0x0001d6a8_0001d7cbL, 0x0001d7ce_0001d7ffL, 0x0001ec71_0001ecb4L,
          0x0001ed01_0001ed3dL, 0x0001f000_0001f02bL, 0x0001f030_0001f093L, 0x0001f0a0_0001f0aeL,
          0x0001f0b1_0001f0bfL, 0x0001f0c1_0001f0cfL, 0x0001f0d1_0001f0f5L, 0x0001f100_0001f1adL,
          0x0001f1e6_0001f1ffL, 0x0001f201_0001f202L, 0x0001f210_0001f23bL, 0x0001f240_0001f248L,
          0x0001f250_0001f251L, 0x0001f260_0001f265L, 0x0001f300_0001f6d7L, 0x0001f6e0_0001f6ecL,
          0x0001f6f0_0001f6fcL, 0x0001f700_0001f773L, 0x0001f780_0001f7d8L, 0x0001f7e0_0001f7ebL,
          0x0001f800_0001f80bL, 0x0001f810_0001f847L, 0x0001f850_0001f859L, 0x0001f860_0001f887L,
          0x0001f890_0001f8adL, 0x0001f8b0_0001f8b1L, 0x0001f900_0001f978L, 0x0001f97a_0001f9cbL,
          0x0001f9cd_0001fa53L, 0x0001fa60_0001fa6dL, 0x0001fa70_0001fa74L, 0x0001fa78_0001fa7aL,
          0x0001fa80_0001fa86L, 0x0001fa90_0001faa8L, 0x0001fab0_0001fab6L, 0x0001fac0_0001fac2L,
          0x0001fad0_0001fad6L, 0x0001fb00_0001fb92L, 0x0001fb94_0001fbcaL, 0x0001fbf0_0001fbf9L,
          0x000e0001_000e0001L, 0x000e0020_000e007fL}),
    ZZZZ("ZZZZ",
        new int [] {
          0x038b_038b, 0x038d_038d, 0x03a2_03a2, 0x0530_0530, 0x0590_0590, 0x061d_061d, 0x070e_070e, 0x083f_083f,
          0x085f_085f, 0x08b5_08b5, 0x0984_0984, 0x09a9_09a9, 0x09b1_09b1, 0x09de_09de, 0x09ff_0a00, 0x0a04_0a04,
          0x0a29_0a29, 0x0a31_0a31, 0x0a34_0a34, 0x0a37_0a37, 0x0a3d_0a3d, 0x0a5d_0a5d, 0x0a80_0a80, 0x0a84_0a84,
          0x0a8e_0a8e, 0x0a92_0a92, 0x0aa9_0aa9, 0x0ab1_0ab1, 0x0ab4_0ab4, 0x0ac6_0ac6, 0x0aca_0aca, 0x0b00_0b00,
          0x0b04_0b04, 0x0b29_0b29, 0x0b31_0b31, 0x0b34_0b34, 0x0b5e_0b5e, 0x0b84_0b84, 0x0b91_0b91, 0x0b9b_0b9b,
          0x0b9d_0b9d, 0x0bc9_0bc9, 0x0c0d_0c0d, 0x0c11_0c11, 0x0c29_0c29, 0x0c45_0c45, 0x0c49_0c49, 0x0c57_0c57,
          0x0c8d_0c8d, 0x0c91_0c91, 0x0ca9_0ca9, 0x0cb4_0cb4, 0x0cc5_0cc5, 0x0cc9_0cc9, 0x0cdf_0cdf, 0x0cf0_0cf0,
          0x0d0d_0d0d, 0x0d11_0d11, 0x0d45_0d45, 0x0d49_0d49, 0x0d80_0d80, 0x0d84_0d84, 0x0db2_0db2, 0x0dbc_0dbc,
          0x0dd5_0dd5, 0x0dd7_0dd7, 0x0e00_0e00, 0x0e80_0e80, 0x0e83_0e83, 0x0e85_0e85, 0x0e8b_0e8b, 0x0ea4_0ea4,
          0x0ea6_0ea6, 0x0ec5_0ec5, 0x0ec7_0ec7, 0x0f48_0f48, 0x0f98_0f98, 0x0fbd_0fbd, 0x0fcd_0fcd, 0x10c6_10c6,
          0x1249_1249, 0x1257_1257, 0x1259_1259, 0x1289_1289, 0x12b1_12b1, 0x12bf_12bf, 0x12c1_12c1, 0x12d7_12d7,
          0x1311_1311, 0x170d_170d, 0x176d_176d, 0x1771_1771, 0x180f_180f, 0x191f_191f, 0x1a5f_1a5f, 0x1dfa_1dfa,
          0x1f58_1f58, 0x1f5a_1f5a, 0x1f5c_1f5c, 0x1f5e_1f5e, 0x1fb5_1fb5, 0x1fc5_1fc5, 0x1fdc_1fdc, 0x1ff5_1ff5,
          0x1fff_1fff, 0x2065_2065, 0x208f_208f, 0x2b96_2b96, 0x2c2f_2c2f, 0x2c5f_2c5f, 0x2d26_2d26, 0x2da7_2da7,
          0x2daf_2daf, 0x2db7_2db7, 0x2dbf_2dbf, 0x2dc7_2dc7, 0x2dcf_2dcf, 0x2dd7_2dd7, 0x2ddf_2ddf, 0x2e9a_2e9a,
          0x3040_3040, 0x3130_3130, 0x318f_318f, 0x321f_321f, 0xa9ce_a9ce, 0xa9ff_a9ff, 0xab00_ab00, 0xab27_ab27,
          0xab2f_ab2f, 0xfb37_fb37, 0xfb3d_fb3d, 0xfb3f_fb3f, 0xfb42_fb42, 0xfb45_fb45, 0xfe53_fe53, 0xfe67_fe67,
          0xfe75_fe75, 0xff00_ff00, 0xffe7_ffe7, 0xffef_ffef},
        new long [] { 
          0x0001000c_0001000cL, 0x00010027_00010027L, 0x0001003b_0001003bL, 0x0001003e_0001003eL,
          0x0001018f_0001018fL, 0x0001039e_0001039eL, 0x00010809_00010809L, 0x00010836_00010836L,
          0x00010856_00010856L, 0x000108f3_000108f3L, 0x00010a04_00010a04L, 0x00010a14_00010a14L,
          0x00010a18_00010a18L, 0x00010e7f_00010e7fL, 0x00010eaa_00010eaaL, 0x00011135_00011135L,
          0x000111e0_000111e0L, 0x00011212_00011212L, 0x00011287_00011287L, 0x00011289_00011289L,
          0x0001128e_0001128eL, 0x0001129e_0001129eL, 0x00011304_00011304L, 0x00011329_00011329L,
          0x00011331_00011331L, 0x00011334_00011334L, 0x0001133a_0001133aL, 0x0001145c_0001145cL,
          0x00011914_00011914L, 0x00011917_00011917L, 0x00011936_00011936L, 0x00011c09_00011c09L,
          0x00011c37_00011c37L, 0x00011ca8_00011ca8L, 0x00011d07_00011d07L, 0x00011d0a_00011d0aL,
          0x00011d3b_00011d3bL, 0x00011d3e_00011d3eL, 0x00011d66_00011d66L, 0x00011d69_00011d69L,
          0x00011d8f_00011d8fL, 0x00011d92_00011d92L, 0x0001246f_0001246fL, 0x0001342f_0001342fL,
          0x00016a5f_00016a5fL, 0x00016b5a_00016b5aL, 0x00016b62_00016b62L, 0x0001d455_0001d455L,
          0x0001d49d_0001d49dL, 0x0001d4ad_0001d4adL, 0x0001d4ba_0001d4baL, 0x0001d4bc_0001d4bcL,
          0x0001d4c4_0001d4c4L, 0x0001d506_0001d506L, 0x0001d515_0001d515L, 0x0001d51d_0001d51dL,
          0x0001d53a_0001d53aL, 0x0001d53f_0001d53fL, 0x0001d545_0001d545L, 0x0001d551_0001d551L,
          0x0001daa0_0001daa0L, 0x0001e007_0001e007L, 0x0001e022_0001e022L, 0x0001e025_0001e025L,
          0x0001ec70_0001ec70L, 0x0001ed00_0001ed00L, 0x0001ee04_0001ee04L, 0x0001ee20_0001ee20L,
          0x0001ee23_0001ee23L, 0x0001ee28_0001ee28L, 0x0001ee33_0001ee33L, 0x0001ee38_0001ee38L,
          0x0001ee3a_0001ee3aL, 0x0001ee48_0001ee48L, 0x0001ee4a_0001ee4aL, 0x0001ee4c_0001ee4cL,
          0x0001ee50_0001ee50L, 0x0001ee53_0001ee53L, 0x0001ee58_0001ee58L, 0x0001ee5a_0001ee5aL,
          0x0001ee5c_0001ee5cL, 0x0001ee5e_0001ee5eL, 0x0001ee60_0001ee60L, 0x0001ee63_0001ee63L,
          0x0001ee6b_0001ee6bL, 0x0001ee73_0001ee73L, 0x0001ee78_0001ee78L, 0x0001ee7d_0001ee7dL,
          0x0001ee7f_0001ee7fL, 0x0001ee8a_0001ee8aL, 0x0001eea4_0001eea4L, 0x0001eeaa_0001eeaaL,
          0x0001f0c0_0001f0c0L, 0x0001f0d0_0001f0d0L, 0x0001f979_0001f979L, 0x0001f9cc_0001f9ccL,
          0x0001fb93_0001fb93L, 0x000e0000_000e0000L});

    private final String alias;
    private final int [] lowerCodePointRanges;
    private final long [] upperCodePointRanges;
    private Script(final String alias, final int [] lowerCodePointRanges, final long [] upperCodePointRanges) {
        this.alias = alias;
        this.lowerCodePointRanges = lowerCodePointRanges;
        this.upperCodePointRanges = upperCodePointRanges;
    }
    public int [] getLowerCodePointRanges() {
        return Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length);
    }
    public long [] getUpperCodePointRanges() {
        return Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length);
    }
  }
  public enum Block implements Subset {
    ASCII("ASCII",
        new int [] {
          0x0000_007f},
        new long [0]),
    ADLAM("Adlam",
        new int [0],
        new long [] { 
          0x0001e900_0001e94bL, 0x0001e950_0001e959L, 0x0001e95e_0001e95fL}),
    AEGEAN_NUMBERS("Aegean_Numbers",
        new int [0],
        new long [] { 
          0x00010100_00010102L, 0x00010107_00010133L, 0x00010137_0001013fL}),
    AHOM("Ahom",
        new int [0],
        new long [] { 
          0x00011700_0001171aL, 0x0001171d_0001172bL, 0x00011730_0001173fL}),
    ALCHEMICAL("Alchemical",
        new int [0],
        new long [] { 
          0x0001f700_0001f773L}),
    ALPHABETIC_PF("Alphabetic_PF",
        new int [] {
          0xfb00_fb06, 0xfb13_fb17, 0xfb1d_fb4f},
        new long [0]),
    ANATOLIAN_HIEROGLYPHS("Anatolian_Hieroglyphs",
        new int [0],
        new long [] { 
          0x00014400_00014646L}),
    ANCIENT_GREEK_MUSIC("Ancient_Greek_Music",
        new int [0],
        new long [] { 
          0x0001d200_0001d245L}),
    ANCIENT_GREEK_NUMBERS("Ancient_Greek_Numbers",
        new int [0],
        new long [] { 
          0x00010140_0001018fL}),
    ANCIENT_SYMBOLS("Ancient_Symbols",
        new int [0],
        new long [] { 
          0x00010190_0001019cL, 0x000101a0_000101a0L}),
    ARABIC("Arabic",
        new int [] {
          0x0600_06ff},
        new long [0]),
    ARABIC_EXT_A("Arabic_Ext_A",
        new int [] {
          0x08a0_08c7, 0x08d3_08ff},
        new long [0]),
    ARABIC_MATH("Arabic_Math",
        new int [0],
        new long [] { 
          0x0001ee00_0001ee24L, 0x0001ee27_0001ee3bL, 0x0001ee42_0001ee42L, 0x0001ee47_0001ee54L,
          0x0001ee57_0001ee64L, 0x0001ee67_0001ee9bL, 0x0001eea1_0001eebbL, 0x0001eef0_0001eef1L}),
    ARABIC_PF_A("Arabic_PF_A",
        new int [] {
          0xfb50_fbc1, 0xfbd3_fd3f, 0xfd50_fd8f, 0xfd92_fdc7, 0xfdf0_fdfd},
        new long [0]),
    ARABIC_PF_B("Arabic_PF_B",
        new int [] {
          0xfe70_fefc, 0xfeff_feff},
        new long [0]),
    ARABIC_SUP("Arabic_Sup",
        new int [] {
          0x0750_077f},
        new long [0]),
    ARMENIAN("Armenian",
        new int [] {
          0x0530_0556, 0x0559_058a, 0x058d_058f},
        new long [0]),
    ARROWS("Arrows",
        new int [] {
          0x2190_21ff},
        new long [0]),
    AVESTAN("Avestan",
        new int [0],
        new long [] { 
          0x00010b00_00010b35L, 0x00010b39_00010b3fL}),
    BALINESE("Balinese",
        new int [] {
          0x1b00_1b4b, 0x1b50_1b7c},
        new long [0]),
    BAMUM("Bamum",
        new int [] {
          0xa6a0_a6f7},
        new long [0]),
    BAMUM_SUP("Bamum_Sup",
        new int [0],
        new long [] { 
          0x00016800_00016a38L}),
    BASSA_VAH("Bassa_Vah",
        new int [0],
        new long [] { 
          0x00016ad0_00016aedL, 0x00016af0_00016af5L}),
    BATAK("Batak",
        new int [] {
          0x1bc0_1bf3, 0x1bfc_1bff},
        new long [0]),
    BENGALI("Bengali",
        new int [] {
          0x0980_098c, 0x098f_0990, 0x0993_09b2, 0x09b6_09b9, 0x09bc_09c4, 0x09c7_09c8, 0x09cb_09ce, 0x09d7_09d7,
          0x09dc_09e3, 0x09e6_09ff},
        new long [0]),
    BHAIKSUKI("Bhaiksuki",
        new int [0],
        new long [] { 
          0x00011c00_00011c45L, 0x00011c50_00011c6cL}),
    BLOCK_ELEMENTS("Block_Elements",
        new int [] {
          0x2580_259f},
        new long [0]),
    BOPOMOFO("Bopomofo",
        new int [] {
          0x3105_312f},
        new long [0]),
    BOPOMOFO_EXT("Bopomofo_Ext",
        new int [] {
          0x31a0_31bf},
        new long [0]),
    BOX_DRAWING("Box_Drawing",
        new int [] {
          0x2500_257f},
        new long [0]),
    BRAHMI("Brahmi",
        new int [0],
        new long [] { 
          0x00011000_0001104dL, 0x00011052_0001106fL, 0x0001107f_0001107fL}),
    BRAILLE("Braille",
        new int [] {
          0x2800_28ff},
        new long [0]),
    BUGINESE("Buginese",
        new int [] {
          0x1a00_1a1b, 0x1a1e_1a1f},
        new long [0]),
    BUHID("Buhid",
        new int [] {
          0x1740_1753},
        new long [0]),
    BYZANTINE_MUSIC("Byzantine_Music",
        new int [0],
        new long [] { 
          0x0001d000_0001d0f5L}),
    CJK("CJK",
        new int [] {
          0x4e00_9ffc},
        new long [0]),
    CJK_COMPAT("CJK_Compat",
        new int [] {
          0x3300_33ff},
        new long [0]),
    CJK_COMPAT_FORMS("CJK_Compat_Forms",
        new int [] {
          0xfe30_fe4f},
        new long [0]),
    CJK_COMPAT_IDEOGRAPHS("CJK_Compat_Ideographs",
        new int [] {
          0xf900_fa6d, 0xfa70_fad9},
        new long [0]),
    CJK_COMPAT_IDEOGRAPHS_SUP("CJK_Compat_Ideographs_Sup",
        new int [0],
        new long [] { 
          0x0002f800_0002fa1dL}),
    CJK_EXT_A("CJK_Ext_A",
        new int [] {
          0x3400_4dbf},
        new long [0]),
    CJK_EXT_B("CJK_Ext_B",
        new int [0],
        new long [] { 
          0x00020000_0002a6ddL}),
    CJK_EXT_C("CJK_Ext_C",
        new int [0],
        new long [] { 
          0x0002a700_0002b734L}),
    CJK_EXT_D("CJK_Ext_D",
        new int [0],
        new long [] { 
          0x0002b740_0002b81dL}),
    CJK_EXT_E("CJK_Ext_E",
        new int [0],
        new long [] { 
          0x0002b820_0002cea1L}),
    CJK_EXT_F("CJK_Ext_F",
        new int [0],
        new long [] { 
          0x0002ceb0_0002ebe0L}),
    CJK_EXT_G("CJK_Ext_G",
        new int [0],
        new long [] { 
          0x00030000_0003134aL}),
    CJK_RADICALS_SUP("CJK_Radicals_Sup",
        new int [] {
          0x2e80_2ef3},
        new long [0]),
    CJK_STROKES("CJK_Strokes",
        new int [] {
          0x31c0_31e3},
        new long [0]),
    CJK_SYMBOLS("CJK_Symbols",
        new int [] {
          0x3000_303f},
        new long [0]),
    CARIAN("Carian",
        new int [0],
        new long [] { 
          0x000102a0_000102d0L}),
    CAUCASIAN_ALBANIAN("Caucasian_Albanian",
        new int [0],
        new long [] { 
          0x00010530_00010563L, 0x0001056f_0001056fL}),
    CHAKMA("Chakma",
        new int [0],
        new long [] { 
          0x00011100_00011147L}),
    CHAM("Cham",
        new int [] {
          0xaa00_aa36, 0xaa40_aa4d, 0xaa50_aa59, 0xaa5c_aa5f},
        new long [0]),
    CHEROKEE("Cherokee",
        new int [] {
          0x13a0_13f5, 0x13f8_13fd},
        new long [0]),
    CHEROKEE_SUP("Cherokee_Sup",
        new int [] {
          0xab70_abbf},
        new long [0]),
    CHESS_SYMBOLS("Chess_Symbols",
        new int [0],
        new long [] { 
          0x0001fa00_0001fa53L, 0x0001fa60_0001fa6dL}),
    CHORASMIAN("Chorasmian",
        new int [0],
        new long [] { 
          0x00010fb0_00010fcbL}),
    COMPAT_JAMO("Compat_Jamo",
        new int [] {
          0x3130_318f},
        new long [0]),
    CONTROL_PICTURES("Control_Pictures",
        new int [] {
          0x2400_2426},
        new long [0]),
    COPTIC("Coptic",
        new int [] {
          0x2c80_2cf3, 0x2cf9_2cff},
        new long [0]),
    COPTIC_EPACT_NUMBERS("Coptic_Epact_Numbers",
        new int [0],
        new long [] { 
          0x000102e0_000102fbL}),
    COUNTING_ROD("Counting_Rod",
        new int [0],
        new long [] { 
          0x0001d360_0001d378L}),
    CUNEIFORM("Cuneiform",
        new int [0],
        new long [] { 
          0x00012000_00012399L}),
    CUNEIFORM_NUMBERS("Cuneiform_Numbers",
        new int [0],
        new long [] { 
          0x00012400_00012474L}),
    CURRENCY_SYMBOLS("Currency_Symbols",
        new int [] {
          0x20a0_20bf},
        new long [0]),
    CYPRIOT_SYLLABARY("Cypriot_Syllabary",
        new int [0],
        new long [] { 
          0x00010800_00010805L, 0x00010808_00010838L, 0x0001083c_0001083cL, 0x0001083f_0001083fL}),
    CYRILLIC("Cyrillic",
        new int [] {
          0x0400_04ff},
        new long [0]),
    CYRILLIC_EXT_A("Cyrillic_Ext_A",
        new int [] {
          0x2de0_2dff},
        new long [0]),
    CYRILLIC_EXT_B("Cyrillic_Ext_B",
        new int [] {
          0xa640_a69f},
        new long [0]),
    CYRILLIC_EXT_C("Cyrillic_Ext_C",
        new int [] {
          0x1c80_1c88},
        new long [0]),
    CYRILLIC_SUP("Cyrillic_Sup",
        new int [] {
          0x0500_052f},
        new long [0]),
    DESERET("Deseret",
        new int [0],
        new long [] { 
          0x00010400_0001044fL}),
    DEVANAGARI("Devanagari",
        new int [] {
          0x0900_097f},
        new long [0]),
    DEVANAGARI_EXT("Devanagari_Ext",
        new int [] {
          0xa8e0_a8ff},
        new long [0]),
    DIACRITICALS("Diacriticals",
        new int [] {
          0x0300_036f},
        new long [0]),
    DIACRITICALS_EXT("Diacriticals_Ext",
        new int [] {
          0x1ab0_1ac0},
        new long [0]),
    DIACRITICALS_FOR_SYMBOLS("Diacriticals_For_Symbols",
        new int [] {
          0x20d0_20f0},
        new long [0]),
    DIACRITICALS_SUP("Diacriticals_Sup",
        new int [] {
          0x1dc0_1dff},
        new long [0]),
    DINGBATS("Dingbats",
        new int [] {
          0x2700_27bf},
        new long [0]),
    DIVES_AKURU("Dives_Akuru",
        new int [0],
        new long [] { 
          0x00011900_00011906L, 0x00011909_00011909L, 0x0001190c_00011938L, 0x0001193b_00011946L,
          0x00011950_00011959L}),
    DOGRA("Dogra",
        new int [0],
        new long [] { 
          0x00011800_0001183bL}),
    DOMINO("Domino",
        new int [0],
        new long [] { 
          0x0001f030_0001f093L}),
    DUPLOYAN("Duployan",
        new int [0],
        new long [] { 
          0x0001bc00_0001bc6aL, 0x0001bc70_0001bc7cL, 0x0001bc80_0001bc88L, 0x0001bc90_0001bc99L,
          0x0001bc9c_0001bc9fL}),
    EARLY_DYNASTIC_CUNEIFORM("Early_Dynastic_Cuneiform",
        new int [0],
        new long [] { 
          0x00012480_00012543L}),
    EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS("Egyptian_Hieroglyph_Format_Controls",
        new int [0],
        new long [] { 
          0x00013430_00013438L}),
    EGYPTIAN_HIEROGLYPHS("Egyptian_Hieroglyphs",
        new int [0],
        new long [] { 
          0x00013000_0001342fL}),
    ELBASAN("Elbasan",
        new int [0],
        new long [] { 
          0x00010500_00010527L}),
    ELYMAIC("Elymaic",
        new int [0],
        new long [] { 
          0x00010fe0_00010ff6L}),
    EMOTICONS("Emoticons",
        new int [0],
        new long [] { 
          0x0001f600_0001f64fL}),
    ENCLOSED_ALPHANUM("Enclosed_Alphanum",
        new int [] {
          0x2460_24ff},
        new long [0]),
    ENCLOSED_ALPHANUM_SUP("Enclosed_Alphanum_Sup",
        new int [0],
        new long [] { 
          0x0001f100_0001f1adL, 0x0001f1e6_0001f1ffL}),
    ENCLOSED_CJK("Enclosed_CJK",
        new int [] {
          0x3200_32ff},
        new long [0]),
    ENCLOSED_IDEOGRAPHIC_SUP("Enclosed_Ideographic_Sup",
        new int [0],
        new long [] { 
          0x0001f200_0001f202L, 0x0001f210_0001f23bL, 0x0001f240_0001f248L, 0x0001f250_0001f251L,
          0x0001f260_0001f265L}),
    ETHIOPIC("Ethiopic",
        new int [] {
          0x1200_124d, 0x1250_125d, 0x1260_128d, 0x1290_12b5, 0x12b8_12c5, 0x12c8_1315, 0x1318_135a, 0x135d_137c},
        new long [0]),
    ETHIOPIC_EXT("Ethiopic_Ext",
        new int [] {
          0x2d80_2d96, 0x2da0_2ddf},
        new long [0]),
    ETHIOPIC_EXT_A("Ethiopic_Ext_A",
        new int [] {
          0xab00_ab06, 0xab09_ab0e, 0xab11_ab16, 0xab20_ab2f},
        new long [0]),
    ETHIOPIC_SUP("Ethiopic_Sup",
        new int [] {
          0x1380_1399},
        new long [0]),
    GEOMETRIC_SHAPES("Geometric_Shapes",
        new int [] {
          0x25a0_25ff},
        new long [0]),
    GEOMETRIC_SHAPES_EXT("Geometric_Shapes_Ext",
        new int [0],
        new long [] { 
          0x0001f780_0001f7d8L, 0x0001f7e0_0001f7ebL}),
    GEORGIAN("Georgian",
        new int [] {
          0x10a0_10c7, 0x10cd_10cd, 0x10d0_10ff},
        new long [0]),
    GEORGIAN_EXT("Georgian_Ext",
        new int [] {
          0x1c90_1cba, 0x1cbd_1cbf},
        new long [0]),
    GEORGIAN_SUP("Georgian_Sup",
        new int [] {
          0x2d00_2d27, 0x2d2d_2d2d},
        new long [0]),
    GLAGOLITIC("Glagolitic",
        new int [] {
          0x2c00_2c5f},
        new long [0]),
    GLAGOLITIC_SUP("Glagolitic_Sup",
        new int [0],
        new long [] { 
          0x0001e000_0001e018L, 0x0001e01b_0001e02aL}),
    GOTHIC("Gothic",
        new int [0],
        new long [] { 
          0x00010330_0001034aL}),
    GRANTHA("Grantha",
        new int [0],
        new long [] { 
          0x00011300_0001130cL, 0x0001130f_00011310L, 0x00011313_00011344L, 0x00011347_00011348L,
          0x0001134b_0001134dL, 0x00011350_00011350L, 0x00011357_00011357L, 0x0001135d_00011363L,
          0x00011366_0001136cL, 0x00011370_00011374L}),
    GREEK("Greek",
        new int [] {
          0x0370_0377, 0x037a_037f, 0x0384_03ff},
        new long [0]),
    GREEK_EXT("Greek_Ext",
        new int [] {
          0x1f00_1f15, 0x1f18_1f1d, 0x1f20_1f45, 0x1f48_1f4d, 0x1f50_1f7d, 0x1f80_1fd3, 0x1fd6_1fef, 0x1ff2_1fff},
        new long [0]),
    GUJARATI("Gujarati",
        new int [] {
          0x0a80_0ab9, 0x0abc_0acd, 0x0ad0_0ad0, 0x0ae0_0ae3, 0x0ae6_0af1, 0x0af9_0aff},
        new long [0]),
    GUNJALA_GONDI("Gunjala_Gondi",
        new int [0],
        new long [] { 
          0x00011d60_00011d98L, 0x00011da0_00011da9L}),
    GURMUKHI("Gurmukhi",
        new int [] {
          0x0a00_0a0a, 0x0a0f_0a10, 0x0a13_0a39, 0x0a3c_0a42, 0x0a47_0a48, 0x0a4b_0a4d, 0x0a51_0a51, 0x0a59_0a5e,
          0x0a66_0a76},
        new long [0]),
    HALF_AND_FULL_FORMS("Half_And_Full_Forms",
        new int [] {
          0xff00_ffbe, 0xffc2_ffc7, 0xffca_ffcf, 0xffd2_ffd7, 0xffda_ffdc, 0xffe0_ffef},
        new long [0]),
    HALF_MARKS("Half_Marks",
        new int [] {
          0xfe20_fe2f},
        new long [0]),
    HANGUL("Hangul",
        new int [] {
          0xac00_d7a3},
        new long [0]),
    HANIFI_ROHINGYA("Hanifi_Rohingya",
        new int [0],
        new long [] { 
          0x00010d00_00010d27L, 0x00010d30_00010d39L}),
    HANUNOO("Hanunoo",
        new int [] {
          0x1720_1736},
        new long [0]),
    HATRAN("Hatran",
        new int [0],
        new long [] { 
          0x000108e0_000108f5L, 0x000108fb_000108ffL}),
    HEBREW("Hebrew",
        new int [] {
          0x0590_05c7, 0x05d0_05ea, 0x05ef_05f4},
        new long [0]),
    HIRAGANA("Hiragana",
        new int [] {
          0x3040_3096, 0x3099_309f},
        new long [0]),
    IDC("IDC",
        new int [] {
          0x2ff0_2ffb},
        new long [0]),
    IPA_EXT("IPA_Ext",
        new int [] {
          0x0250_02af},
        new long [0]),
    IDEOGRAPHIC_SYMBOLS("Ideographic_Symbols",
        new int [0],
        new long [] { 
          0x00016fe0_00016fe4L, 0x00016ff0_00016ff1L}),
    IMPERIAL_ARAMAIC("Imperial_Aramaic",
        new int [0],
        new long [] { 
          0x00010840_0001085fL}),
    INDIC_NUMBER_FORMS("Indic_Number_Forms",
        new int [] {
          0xa830_a839},
        new long [0]),
    INDIC_SIYAQ_NUMBERS("Indic_Siyaq_Numbers",
        new int [0],
        new long [] { 
          0x0001ec70_0001ecb4L}),
    INSCRIPTIONAL_PAHLAVI("Inscriptional_Pahlavi",
        new int [0],
        new long [] { 
          0x00010b60_00010b72L, 0x00010b78_00010b7fL}),
    INSCRIPTIONAL_PARTHIAN("Inscriptional_Parthian",
        new int [0],
        new long [] { 
          0x00010b40_00010b55L, 0x00010b58_00010b5fL}),
    JAMO("Jamo",
        new int [] {
          0x1100_11ff},
        new long [0]),
    JAMO_EXT_A("Jamo_Ext_A",
        new int [] {
          0xa960_a97c},
        new long [0]),
    JAMO_EXT_B("Jamo_Ext_B",
        new int [] {
          0xd7b0_d7c6, 0xd7cb_d7fb},
        new long [0]),
    JAVANESE("Javanese",
        new int [] {
          0xa980_a9d9, 0xa9de_a9df},
        new long [0]),
    KAITHI("Kaithi",
        new int [0],
        new long [] { 
          0x00011080_000110c1L, 0x000110cd_000110cdL}),
    KANA_EXT_A("Kana_Ext_A",
        new int [0],
        new long [] { 
          0x0001b100_0001b11eL}),
    KANA_SUP("Kana_Sup",
        new int [0],
        new long [] { 
          0x0001b000_0001b0ffL}),
    KANBUN("Kanbun",
        new int [] {
          0x3190_319f},
        new long [0]),
    KANGXI("Kangxi",
        new int [] {
          0x2f00_2fd5},
        new long [0]),
    KANNADA("Kannada",
        new int [] {
          0x0c80_0cb9, 0x0cbc_0ccd, 0x0cd5_0cd6, 0x0cde_0ce3, 0x0ce6_0cf2},
        new long [0]),
    KATAKANA("Katakana",
        new int [] {
          0x30a0_30ff},
        new long [0]),
    KATAKANA_EXT("Katakana_Ext",
        new int [] {
          0x31f0_31ff},
        new long [0]),
    KAYAH_LI("Kayah_Li",
        new int [] {
          0xa900_a92f},
        new long [0]),
    KHAROSHTHI("Kharoshthi",
        new int [0],
        new long [] { 
          0x00010a00_00010a06L, 0x00010a0c_00010a35L, 0x00010a38_00010a3aL, 0x00010a3f_00010a48L,
          0x00010a50_00010a58L}),
    KHITAN_SMALL_SCRIPT("Khitan_Small_Script",
        new int [0],
        new long [] { 
          0x00018b00_00018cd5L}),
    KHMER("Khmer",
        new int [] {
          0x1780_17dd, 0x17e0_17e9, 0x17f0_17f9},
        new long [0]),
    KHMER_SYMBOLS("Khmer_Symbols",
        new int [] {
          0x19e0_19ff},
        new long [0]),
    KHOJKI("Khojki",
        new int [0],
        new long [] { 
          0x00011200_0001123eL}),
    KHUDAWADI("Khudawadi",
        new int [0],
        new long [] { 
          0x000112b0_000112eaL, 0x000112f0_000112f9L}),
    LAO("Lao",
        new int [] {
          0x0e80_0ebd, 0x0ec0_0ecd, 0x0ed0_0ed9, 0x0edc_0edf},
        new long [0]),
    LATIN_1_SUP("Latin_1_Sup",
        new int [] {
          0x0080_00ff},
        new long [0]),
    LATIN_EXT_A("Latin_Ext_A",
        new int [] {
          0x0100_017f},
        new long [0]),
    LATIN_EXT_ADDITIONAL("Latin_Ext_Additional",
        new int [] {
          0x1e00_1eff},
        new long [0]),
    LATIN_EXT_B("Latin_Ext_B",
        new int [] {
          0x0180_024f},
        new long [0]),
    LATIN_EXT_C("Latin_Ext_C",
        new int [] {
          0x2c60_2c7f},
        new long [0]),
    LATIN_EXT_D("Latin_Ext_D",
        new int [] {
          0xa720_a7bf, 0xa7c2_a7ca, 0xa7f5_a7ff},
        new long [0]),
    LATIN_EXT_E("Latin_Ext_E",
        new int [] {
          0xab30_ab6b},
        new long [0]),
    LEPCHA("Lepcha",
        new int [] {
          0x1c00_1c37, 0x1c3b_1c49, 0x1c4d_1c4f},
        new long [0]),
    LETTERLIKE_SYMBOLS("Letterlike_Symbols",
        new int [] {
          0x2100_214f},
        new long [0]),
    LIMBU("Limbu",
        new int [] {
          0x1900_192b, 0x1930_193b, 0x1940_1940, 0x1944_194f},
        new long [0]),
    LINEAR_A("Linear_A",
        new int [0],
        new long [] { 
          0x00010600_00010736L, 0x00010740_00010755L, 0x00010760_00010767L}),
    LINEAR_B_IDEOGRAMS("Linear_B_Ideograms",
        new int [0],
        new long [] { 
          0x00010080_000100faL}),
    LINEAR_B_SYLLABARY("Linear_B_Syllabary",
        new int [0],
        new long [] { 
          0x00010000_0001004dL, 0x00010050_0001005dL}),
    LISU("Lisu",
        new int [] {
          0xa4d0_a4ff},
        new long [0]),
    LISU_SUP("Lisu_Sup",
        new int [0],
        new long [] { 
          0x00011fb0_00011fb0L}),
    LYCIAN("Lycian",
        new int [0],
        new long [] { 
          0x00010280_0001029cL}),
    LYDIAN("Lydian",
        new int [0],
        new long [] { 
          0x00010920_00010939L, 0x0001093f_0001093fL}),
    MAHAJANI("Mahajani",
        new int [0],
        new long [] { 
          0x00011150_00011176L}),
    MAHJONG("Mahjong",
        new int [0],
        new long [] { 
          0x0001f000_0001f02bL}),
    MAKASAR("Makasar",
        new int [0],
        new long [] { 
          0x00011ee0_00011ef8L}),
    MALAYALAM("Malayalam",
        new int [] {
          0x0d00_0d4f, 0x0d54_0d63, 0x0d66_0d7f},
        new long [0]),
    MANDAIC("Mandaic",
        new int [] {
          0x0840_085b, 0x085e_085f},
        new long [0]),
    MANICHAEAN("Manichaean",
        new int [0],
        new long [] { 
          0x00010ac0_00010ae6L, 0x00010aeb_00010af6L}),
    MARCHEN("Marchen",
        new int [0],
        new long [] { 
          0x00011c70_00011c8fL, 0x00011c92_00011cb6L}),
    MASARAM_GONDI("Masaram_Gondi",
        new int [0],
        new long [] { 
          0x00011d00_00011d36L, 0x00011d3a_00011d47L, 0x00011d50_00011d59L}),
    MATH_ALPHANUM("Math_Alphanum",
        new int [0],
        new long [] { 
          0x0001d400_0001d49fL, 0x0001d4a2_0001d4a2L, 0x0001d4a5_0001d4a6L, 0x0001d4a9_0001d50aL,
          0x0001d50d_0001d546L, 0x0001d54a_0001d6a5L, 0x0001d6a8_0001d7cbL, 0x0001d7ce_0001d7ffL}),
    MATH_OPERATORS("Math_Operators",
        new int [] {
          0x2200_22ff},
        new long [0]),
    MAYAN_NUMERALS("Mayan_Numerals",
        new int [0],
        new long [] { 
          0x0001d2e0_0001d2f3L}),
    MEDEFAIDRIN("Medefaidrin",
        new int [0],
        new long [] { 
          0x00016e40_00016e9aL}),
    MEETEI_MAYEK("Meetei_Mayek",
        new int [] {
          0xabc0_abed, 0xabf0_abf9},
        new long [0]),
    MEETEI_MAYEK_EXT("Meetei_Mayek_Ext",
        new int [] {
          0xaae0_aaf6},
        new long [0]),
    MENDE_KIKAKUI("Mende_Kikakui",
        new int [0],
        new long [] { 
          0x0001e800_0001e8c4L, 0x0001e8c7_0001e8d6L}),
    MEROITIC_CURSIVE("Meroitic_Cursive",
        new int [0],
        new long [] { 
          0x000109a0_000109b7L, 0x000109bc_000109cfL, 0x000109d2_000109ffL}),
    MEROITIC_HIEROGLYPHS("Meroitic_Hieroglyphs",
        new int [0],
        new long [] { 
          0x00010980_0001099fL}),
    MIAO("Miao",
        new int [0],
        new long [] { 
          0x00016f00_00016f4aL, 0x00016f4f_00016f87L, 0x00016f8f_00016f9fL}),
    MISC_ARROWS("Misc_Arrows",
        new int [] {
          0x2b00_2b73, 0x2b76_2bff},
        new long [0]),
    MISC_MATH_SYMBOLS_A("Misc_Math_Symbols_A",
        new int [] {
          0x27c0_27ef},
        new long [0]),
    MISC_MATH_SYMBOLS_B("Misc_Math_Symbols_B",
        new int [] {
          0x2980_29ff},
        new long [0]),
    MISC_PICTOGRAPHS("Misc_Pictographs",
        new int [0],
        new long [] { 
          0x0001f300_0001f5ffL}),
    MISC_SYMBOLS("Misc_Symbols",
        new int [] {
          0x2600_26ff},
        new long [0]),
    MISC_TECHNICAL("Misc_Technical",
        new int [] {
          0x2300_23ff},
        new long [0]),
    MODI("Modi",
        new int [0],
        new long [] { 
          0x00011600_00011644L, 0x00011650_00011659L}),
    MODIFIER_LETTERS("Modifier_Letters",
        new int [] {
          0x02b0_02ff},
        new long [0]),
    MODIFIER_TONE_LETTERS("Modifier_Tone_Letters",
        new int [] {
          0xa700_a71f},
        new long [0]),
    MONGOLIAN("Mongolian",
        new int [] {
          0x1800_1819, 0x1820_1878, 0x1880_18aa},
        new long [0]),
    MONGOLIAN_SUP("Mongolian_Sup",
        new int [0],
        new long [] { 
          0x00011660_0001166cL}),
    MRO("Mro",
        new int [0],
        new long [] { 
          0x00016a40_00016a69L, 0x00016a6e_00016a6fL}),
    MULTANI("Multani",
        new int [0],
        new long [] { 
          0x00011280_000112a9L}),
    MUSIC("Music",
        new int [0],
        new long [] { 
          0x0001d100_0001d126L, 0x0001d129_0001d1e8L}),
    MYANMAR("Myanmar",
        new int [] {
          0x1000_109f},
        new long [0]),
    MYANMAR_EXT_A("Myanmar_Ext_A",
        new int [] {
          0xaa60_aa7f},
        new long [0]),
    MYANMAR_EXT_B("Myanmar_Ext_B",
        new int [] {
          0xa9e0_a9ff},
        new long [0]),
    NKO("NKo",
        new int [] {
          0x07c0_07fa, 0x07fd_07ff},
        new long [0]),
    NABATAEAN("Nabataean",
        new int [0],
        new long [] { 
          0x00010880_0001089eL, 0x000108a7_000108afL}),
    NANDINAGARI("Nandinagari",
        new int [0],
        new long [] { 
          0x000119a0_000119a7L, 0x000119aa_000119d7L, 0x000119da_000119e4L}),
    NEW_TAI_LUE("New_Tai_Lue",
        new int [] {
          0x1980_19ab, 0x19b0_19c9, 0x19d0_19da, 0x19de_19df},
        new long [0]),
    NEWA("Newa",
        new int [0],
        new long [] { 
          0x00011400_00011461L}),
    NUMBER_FORMS("Number_Forms",
        new int [] {
          0x2150_218b},
        new long [0]),
    NUSHU("Nushu",
        new int [0],
        new long [] { 
          0x0001b170_0001b2fbL}),
    NYIAKENG_PUACHUE_HMONG("Nyiakeng_Puachue_Hmong",
        new int [0],
        new long [] { 
          0x0001e100_0001e12cL, 0x0001e130_0001e13dL, 0x0001e140_0001e149L, 0x0001e14e_0001e14fL}),
    OCR("OCR",
        new int [] {
          0x2440_244a},
        new long [0]),
    OGHAM("Ogham",
        new int [] {
          0x1680_169c},
        new long [0]),
    OL_CHIKI("Ol_Chiki",
        new int [] {
          0x1c50_1c7f},
        new long [0]),
    OLD_HUNGARIAN("Old_Hungarian",
        new int [0],
        new long [] { 
          0x00010c80_00010cb2L, 0x00010cc0_00010cf2L, 0x00010cfa_00010cffL}),
    OLD_ITALIC("Old_Italic",
        new int [0],
        new long [] { 
          0x00010300_00010323L, 0x0001032d_0001032fL}),
    OLD_NORTH_ARABIAN("Old_North_Arabian",
        new int [0],
        new long [] { 
          0x00010a80_00010a9fL}),
    OLD_PERMIC("Old_Permic",
        new int [0],
        new long [] { 
          0x00010350_0001037aL}),
    OLD_PERSIAN("Old_Persian",
        new int [0],
        new long [] { 
          0x000103a0_000103c3L, 0x000103c8_000103d5L}),
    OLD_SOGDIAN("Old_Sogdian",
        new int [0],
        new long [] { 
          0x00010f00_00010f27L}),
    OLD_SOUTH_ARABIAN("Old_South_Arabian",
        new int [0],
        new long [] { 
          0x00010a60_00010a7fL}),
    OLD_TURKIC("Old_Turkic",
        new int [0],
        new long [] { 
          0x00010c00_00010c48L}),
    ORIYA("Oriya",
        new int [] {
          0x0b00_0b0c, 0x0b0f_0b10, 0x0b13_0b39, 0x0b3c_0b44, 0x0b47_0b48, 0x0b4b_0b4d, 0x0b55_0b57, 0x0b5c_0b63,
          0x0b66_0b77},
        new long [0]),
    ORNAMENTAL_DINGBATS("Ornamental_Dingbats",
        new int [0],
        new long [] { 
          0x0001f650_0001f67fL}),
    OSAGE("Osage",
        new int [0],
        new long [] { 
          0x000104b0_000104d3L, 0x000104d8_000104fbL}),
    OSMANYA("Osmanya",
        new int [0],
        new long [] { 
          0x00010480_0001049dL, 0x000104a0_000104a9L}),
    OTTOMAN_SIYAQ_NUMBERS("Ottoman_Siyaq_Numbers",
        new int [0],
        new long [] { 
          0x0001ed00_0001ed3dL}),
    PAHAWH_HMONG("Pahawh_Hmong",
        new int [0],
        new long [] { 
          0x00016b00_00016b45L, 0x00016b50_00016b77L, 0x00016b7d_00016b8fL}),
    PALMYRENE("Palmyrene",
        new int [0],
        new long [] { 
          0x00010860_0001087fL}),
    PAU_CIN_HAU("Pau_Cin_Hau",
        new int [0],
        new long [] { 
          0x00011ac0_00011af8L}),
    PHAGS_PA("Phags_Pa",
        new int [] {
          0xa840_a877},
        new long [0]),
    PHAISTOS("Phaistos",
        new int [0],
        new long [] { 
          0x000101d0_000101fdL}),
    PHOENICIAN("Phoenician",
        new int [0],
        new long [] { 
          0x00010900_0001091bL, 0x0001091f_0001091fL}),
    PHONETIC_EXT("Phonetic_Ext",
        new int [] {
          0x1d00_1d7f},
        new long [0]),
    PHONETIC_EXT_SUP("Phonetic_Ext_Sup",
        new int [] {
          0x1d80_1dbf},
        new long [0]),
    PLAYING_CARDS("Playing_Cards",
        new int [0],
        new long [] { 
          0x0001f0a0_0001f0aeL, 0x0001f0b1_0001f0f5L}),
    PSALTER_PAHLAVI("Psalter_Pahlavi",
        new int [0],
        new long [] { 
          0x00010b80_00010b91L, 0x00010b99_00010b9cL, 0x00010ba9_00010bafL}),
    PUNCTUATION("Punctuation",
        new int [] {
          0x2000_206f},
        new long [0]),
    REJANG("Rejang",
        new int [] {
          0xa930_a953, 0xa95f_a95f},
        new long [0]),
    RUMI("Rumi",
        new int [0],
        new long [] { 
          0x00010e60_00010e7fL}),
    RUNIC("Runic",
        new int [] {
          0x16a0_16f8},
        new long [0]),
    SAMARITAN("Samaritan",
        new int [] {
          0x0800_082d, 0x0830_083f},
        new long [0]),
    SAURASHTRA("Saurashtra",
        new int [] {
          0xa880_a8c5, 0xa8ce_a8d9},
        new long [0]),
    SHARADA("Sharada",
        new int [0],
        new long [] { 
          0x00011180_000111dfL}),
    SHAVIAN("Shavian",
        new int [0],
        new long [] { 
          0x00010450_0001047fL}),
    SHORTHAND_FORMAT_CONTROLS("Shorthand_Format_Controls",
        new int [0],
        new long [] { 
          0x0001bca0_0001bca3L}),
    SIDDHAM("Siddham",
        new int [0],
        new long [] { 
          0x00011580_000115b5L, 0x000115b8_000115ddL}),
    SINHALA("Sinhala",
        new int [] {
          0x0d80_0d96, 0x0d9a_0dbd, 0x0dc0_0dc6, 0x0dca_0dca, 0x0dcf_0ddf, 0x0de6_0def, 0x0df2_0df4},
        new long [0]),
    SINHALA_ARCHAIC_NUMBERS("Sinhala_Archaic_Numbers",
        new int [0],
        new long [] { 
          0x000111e0_000111f4L}),
    SMALL_FORMS("Small_Forms",
        new int [] {
          0xfe50_fe6b},
        new long [0]),
    SMALL_KANA_EXT("Small_Kana_Ext",
        new int [0],
        new long [] { 
          0x0001b150_0001b152L, 0x0001b164_0001b167L}),
    SOGDIAN("Sogdian",
        new int [0],
        new long [] { 
          0x00010f30_00010f59L}),
    SORA_SOMPENG("Sora_Sompeng",
        new int [0],
        new long [] { 
          0x000110d0_000110e8L, 0x000110f0_000110f9L}),
    SOYOMBO("Soyombo",
        new int [0],
        new long [] { 
          0x00011a50_00011aa2L}),
    SPECIALS("Specials",
        new int [] {
          0xfff9_fffd},
        new long [0]),
    SUNDANESE("Sundanese",
        new int [] {
          0x1b80_1bbf},
        new long [0]),
    SUNDANESE_SUP("Sundanese_Sup",
        new int [] {
          0x1cc0_1cc7},
        new long [0]),
    SUP_ARROWS_A("Sup_Arrows_A",
        new int [] {
          0x27f0_27ff},
        new long [0]),
    SUP_ARROWS_B("Sup_Arrows_B",
        new int [] {
          0x2900_297f},
        new long [0]),
    SUP_ARROWS_C("Sup_Arrows_C",
        new int [0],
        new long [] { 
          0x0001f800_0001f80bL, 0x0001f810_0001f847L, 0x0001f850_0001f859L, 0x0001f860_0001f887L,
          0x0001f890_0001f8adL, 0x0001f8b0_0001f8b1L}),
    SUP_MATH_OPERATORS("Sup_Math_Operators",
        new int [] {
          0x2a00_2aff},
        new long [0]),
    SUP_PUNCTUATION("Sup_Punctuation",
        new int [] {
          0x2e00_2e52},
        new long [0]),
    SUP_SYMBOLS_AND_PICTOGRAPHS("Sup_Symbols_And_Pictographs",
        new int [0],
        new long [] { 
          0x0001f900_0001f9ffL}),
    SUPER_AND_SUB("Super_And_Sub",
        new int [] {
          0x2070_2071, 0x2074_209c},
        new long [0]),
    SUTTON_SIGNWRITING("Sutton_SignWriting",
        new int [0],
        new long [] { 
          0x0001d800_0001da8bL, 0x0001da9b_0001daafL}),
    SYLOTI_NAGRI("Syloti_Nagri",
        new int [] {
          0xa800_a82c},
        new long [0]),
    SYMBOLS_AND_PICTOGRAPHS_EXT_A("Symbols_And_Pictographs_Ext_A",
        new int [0],
        new long [] { 
          0x0001fa70_0001fa74L, 0x0001fa78_0001fa7aL, 0x0001fa80_0001fa86L, 0x0001fa90_0001faa8L,
          0x0001fab0_0001fab6L, 0x0001fac0_0001fac2L, 0x0001fad0_0001fad6L}),
    SYMBOLS_FOR_LEGACY_COMPUTING("Symbols_For_Legacy_Computing",
        new int [0],
        new long [] { 
          0x0001fb00_0001fbcaL, 0x0001fbf0_0001fbf9L}),
    SYRIAC("Syriac",
        new int [] {
          0x0700_074a, 0x074d_074f},
        new long [0]),
    SYRIAC_SUP("Syriac_Sup",
        new int [] {
          0x0860_086a},
        new long [0]),
    TAGALOG("Tagalog",
        new int [] {
          0x1700_1714},
        new long [0]),
    TAGBANWA("Tagbanwa",
        new int [] {
          0x1760_1773},
        new long [0]),
    TAGS("Tags",
        new int [0],
        new long [] { 
          0x000e0000_000e0001L, 0x000e0020_000e007fL}),
    TAI_LE("Tai_Le",
        new int [] {
          0x1950_196d, 0x1970_1974},
        new long [0]),
    TAI_THAM("Tai_Tham",
        new int [] {
          0x1a20_1a7c, 0x1a7f_1a89, 0x1a90_1a99, 0x1aa0_1aad},
        new long [0]),
    TAI_VIET("Tai_Viet",
        new int [] {
          0xaa80_aac2, 0xaadb_aadf},
        new long [0]),
    TAI_XUAN_JING("Tai_Xuan_Jing",
        new int [0],
        new long [] { 
          0x0001d300_0001d356L}),
    TAKRI("Takri",
        new int [0],
        new long [] { 
          0x00011680_000116b8L, 0x000116c0_000116c9L}),
    TAMIL("Tamil",
        new int [] {
          0x0b82_0b8a, 0x0b8e_0b95, 0x0b99_0b9f, 0x0ba3_0ba4, 0x0ba8_0baa, 0x0bae_0bb9, 0x0bbe_0bc2, 0x0bc6_0bcd,
          0x0bd0_0bd0, 0x0bd7_0bd7, 0x0be6_0bfa},
        new long [0]),
    TAMIL_SUP("Tamil_Sup",
        new int [0],
        new long [] { 
          0x00011fc0_00011ff1L, 0x00011fff_00011fffL}),
    TANGUT("Tangut",
        new int [0],
        new long [] { 
          0x00017000_000187f7L}),
    TANGUT_COMPONENTS("Tangut_Components",
        new int [0],
        new long [] { 
          0x00018800_00018affL}),
    TANGUT_SUP("Tangut_Sup",
        new int [0],
        new long [] { 
          0x00018d00_00018d08L}),
    TELUGU("Telugu",
        new int [] {
          0x0c00_0c39, 0x0c3d_0c4d, 0x0c55_0c5a, 0x0c60_0c63, 0x0c66_0c6f, 0x0c77_0c7f},
        new long [0]),
    THAANA("Thaana",
        new int [] {
          0x0780_07b1},
        new long [0]),
    THAI("Thai",
        new int [] {
          0x0e00_0e3a, 0x0e3f_0e5b},
        new long [0]),
    TIBETAN("Tibetan",
        new int [] {
          0x0f00_0f6c, 0x0f71_0fda},
        new long [0]),
    TIFINAGH("Tifinagh",
        new int [] {
          0x2d30_2d67, 0x2d6f_2d70, 0x2d7f_2d7f},
        new long [0]),
    TIRHUTA("Tirhuta",
        new int [0],
        new long [] { 
          0x00011480_000114c7L, 0x000114d0_000114d9L}),
    TRANSPORT_AND_MAP("Transport_And_Map",
        new int [0],
        new long [] { 
          0x0001f680_0001f6d7L, 0x0001f6e0_0001f6ecL, 0x0001f6f0_0001f6fcL}),
    UCAS("UCAS",
        new int [] {
          0x1400_167f},
        new long [0]),
    UCAS_EXT("UCAS_Ext",
        new int [] {
          0x18b0_18f5},
        new long [0]),
    UGARITIC("Ugaritic",
        new int [0],
        new long [] { 
          0x00010380_0001039fL}),
    VS("VS",
        new int [] {
          0xfe00_fe0f},
        new long [0]),
    VS_SUP("VS_Sup",
        new int [0],
        new long [] { 
          0x000e0100_000e01efL}),
    VAI("Vai",
        new int [] {
          0xa500_a62b},
        new long [0]),
    VEDIC_EXT("Vedic_Ext",
        new int [] {
          0x1cd0_1cfa},
        new long [0]),
    VERTICAL_FORMS("Vertical_Forms",
        new int [] {
          0xfe10_fe19},
        new long [0]),
    WANCHO("Wancho",
        new int [0],
        new long [] { 
          0x0001e2c0_0001e2f9L, 0x0001e2ff_0001e2ffL}),
    WARANG_CITI("Warang_Citi",
        new int [0],
        new long [] { 
          0x000118a0_000118f2L, 0x000118ff_000118ffL}),
    YEZIDI("Yezidi",
        new int [0],
        new long [] { 
          0x00010e80_00010eadL, 0x00010eb0_00010eb1L}),
    YI_RADICALS("Yi_Radicals",
        new int [] {
          0xa490_a4c6},
        new long [0]),
    YI_SYLLABLES("Yi_Syllables",
        new int [] {
          0xa000_a48c},
        new long [0]),
    YIJING("Yijing",
        new int [] {
          0x4dc0_4dff},
        new long [0]),
    ZANABAZAR_SQUARE("Zanabazar_Square",
        new int [0],
        new long [] { 
          0x00011a00_00011a47L});

    private final String alias;
    private final int [] lowerCodePointRanges;
    private final long [] upperCodePointRanges;
    private Block(final String alias, final int [] lowerCodePointRanges, final long [] upperCodePointRanges) {
        this.alias = alias;
        this.lowerCodePointRanges = lowerCodePointRanges;
        this.upperCodePointRanges = upperCodePointRanges;
    }
    public int [] getLowerCodePointRanges() {
        return Arrays.copyOf(lowerCodePointRanges, lowerCodePointRanges.length);
    }
    public long [] getUpperCodePointRanges() {
        return Arrays.copyOf(upperCodePointRanges, upperCodePointRanges.length);
    }
  }

}
