//package org.datatypeproject.impl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.datatypeproject.Subset;
//import org.datatypeproject.Subset.CodePointRange;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EnumSource;
//import org.junit.jupiter.params.provider.ValueSource;
//
//class HashedRangedSubsetImpl_singleBlockTest {
//
//  @ParameterizedTest
//  @ValueSource(strings = {
//      "\u0000",
//      "\u00FF",
//      "A",
//      "a",
//      "Z",
//      "z",
//      "0",
//      "9",
//      "!",
//      "?",
//  })
//  void includeChar_singleByteTest(final char value) {
//
//    final Subset actual = new SubsetBuilderImpl()
//        .includeChar(value)
//        .build(SubsetTypePreference.HASHED_RANGED);
//
//
//    assertThat(actual).isNotNull().isExactlyInstanceOf(HashedRangedSubsetImpl.class);
//
//    assertThat(actual.isEmpty()).isFalse();
//    assertThat(actual.isNotEmpty()).isTrue();
//    assertThat(actual.contains(value)).isTrue();
//    assertThat(actual.ranges()).flatMap(CodePointRange::copy).containsOnly(new CodePointRange(value, value));
//
//    final HashedRangedSubset hashedRangedSubset = (HashedRangedSubset)actual;
//    assertThat(hashedRangedSubset.getBlockKeySet()).hasSize(1);
//    assertThat(hashedRangedSubset.getBlockKeySet()).containsOnly((char)0x00);
//  }
//
//  enum SingleByteTestSource {
//    SINGLE_CHARACTER(
//        new char[]{'\u0021'},
//        new char[]{0x21_21}),
//    MULTIPLE_ADJACENT_CHARACTERS(
//        new char[]{'\u0021', '\u0022', '\u0023'},
//        new char[]{0x21_23}),
//    MULTIPLE_NON_ADJACENT_CHARACTERS(
//        new char[]{'\u0021', '\u0033', '\u0039'},
//        new char[]{0x21_21, 0x33_33, 0x39_39}),
//    MULTIPLE_NON_ADJACENT_CHARACTERS_NOT_IN_ORDER(
//        new char[]{'\u00FF', '\u0021', '\u0044', '\u0039', '\u0000'},
//        new char[]{0x00_00, 0x21_21, 0x39_39, 0x44_44, 0xFF_FF}),
//    MANY_TO_FORCE_ARRAY_REALLOCATION(
//        new char[]{
//            '\u0031', '\u0033', '\u0035', '\u0037', '\u0039',
//            '\u0041', '\u0043', '\u0045', '\u0047', '\u0049',
//            '\u0051', '\u0053', '\u0055', '\u0057', '\u0059',
//            '\u0061', '\u0063', '\u0065', '\u0067', '\u0069',
//            '\u0071', '\u0073', '\u0075', '\u0077', '\u0079',
//            '\u0081', '\u0083', '\u0085', '\u0087', '\u0089',
//            '\u0091', '\u0093', '\u0095', '\u0097', '\u0099'},
//        new char[]{
//            0x31_31, 0x33_33, 0x35_35, 0x37_37, 0x39_39,
//            0x41_41, 0x43_43, 0x45_45, 0x47_47, 0x49_49,
//            0x51_51, 0x53_53, 0x55_55, 0x57_57, 0x59_59,
//            0x61_61, 0x63_63, 0x65_65, 0x67_67, 0x69_69,
//            0x71_71, 0x73_73, 0x75_75, 0x77_77, 0x79_79,
//            0x81_81, 0x83_83, 0x85_85, 0x87_87, 0x89_89,
//            0x91_91, 0x93_93, 0x95_95, 0x97_97, 0x99_99}),
//    ;
//    final char[] chars;
//    final char[] expectedRanges;
//
//    SingleByteTestSource(char[] chars, char[] expectedRanges) {
//      this.chars = chars;
//      this.expectedRanges = expectedRanges;
//    }
//
//    Iterable<CodePointRange> expectedCodePointRanges() {
//      final List<CodePointRange> result = new ArrayList<>();
//      for (char expectedRange : expectedRanges) {
//        result.add(new CodePointRange(
//            SubsetUtils.getInclusiveFrom(expectedRange),
//            SubsetUtils.getInclusiveTo(expectedRange)));
//      }
//      return result;
//    }
//  }
//
//  @ParameterizedTest
//  @EnumSource(SingleByteTestSource.class)
//  void includeChars_singleByteTest(final SingleByteTestSource testSource) {
//
//    final HashedRangedSubset actual = new HashedRangedSubsetImpl()
//        .includeChars(testSource.chars)
//        .build();
//
//    assertThat(actual).isNotNull().isExactlyInstanceOf(HashedRangedSubsetImpl.class);
//    assertThat(actual.isEmpty()).isFalse();
//    assertThat(actual.isNotEmpty()).isTrue();
//    assertThat(actual.ranges()).flatMap(CodePointRange::copy).containsAll(testSource.expectedCodePointRanges());
//
//    for (char ch : testSource.chars) {
//      assertThat(actual.contains(ch)).isTrue();
//    }
//
//    final HashedRangedSubset hashedRangedSubset = (HashedRangedSubset)actual;
//    assertThat(hashedRangedSubset.getBlockKeySet()).hasSize(1);
//    assertThat(hashedRangedSubset.getBlockKeySet()).containsOnly((char)0x00);
//  }
//}
