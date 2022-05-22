package land.artli.easytype;

import static org.assertj.core.api.Assertions.assertThat;

import land.artli.easytype.CodePointConversions.ConverterResults;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CodePointConversionsTest {

  @ParameterizedTest
  @CsvSource(value = {
      " abcdefg | abc | xyz | xyzdefg ",
      " abcdefg | bcd | xyz | axyzefg ",
      " abcdefg | efg | xyz | abcdxyz ",
  }, delimiter = '|')
  void convertIsSuccessful(final String input, final String convertFrom, final String convertTo, final String expected) {

    final CodePointConversions codePointConversions = CodePointConversions.builder()
        .addCharConversion(convertFrom, convertTo)
        .build();

    final String actual = convert(input, codePointConversions);

    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      " semi-trailer    | semi       | articulated |              |           |                 |       | articulated-trailer ",
      " semi-trailer    | semi       | articulated | semi-trailer | lorry     |                 |       | lorry               ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   |                 |       | articulated-vehicle ",
      " semi-trailer    | semi       | articulated | trailer      | vehicle   | semi-trailer    | lorry | lorry               ",
      " full stop light | full stop  | period      |              |           |                 |       | period light        ",
      " full stop light | stop light | red light   |              |           |                 |       | full red light      ",
      " full stop light | full stop  | period      | stop light   | red light |                 |       | period light        ",
      " full stop light | full stop  | period      | stop light   | red light | full stop light | skid  | skid                ",
  }, delimiter = '|')
  void convertIsSuccessful(
      final String input,
      final String convertFrom1, final String convertTo1,
      final String convertFrom2, final String convertTo2,
      final String convertFrom3, final String convertTo3,
      final String expected) {

    final CodePointConversions codePointConversions = CodePointConversions.builder()
        .addCharConversion(convertFrom1, convertTo1)
        .addCharConversion(convertFrom2, convertTo2)
        .addCharConversion(convertFrom3, convertTo3)
        .build();

    final String actual = convert(input, codePointConversions);

    assertThat(actual).isEqualTo(expected);
  }


  private String convert(final String input, CodePointConversions codePointConversions) {
    final int [] codePoints = input.codePoints().toArray();
    final StringBuilder s = new StringBuilder();
    final ConverterResults converterResults = codePointConversions.createConverterResults();
    for (int i = 0; i < codePoints.length; ++i) {
      int codePoint = codePoints[i];
      s.appendCodePoint(codePoint);
      if (codePointConversions.codePointConversionRequired(codePoint, s.length() - 1, converterResults)) {
        s.setLength(converterResults.getConvertFromIndex());
        final int [] toCodePointSequence = converterResults.getConvertToCodePointSequence();
        for (int j = 0; j < toCodePointSequence.length; ++j) {
          s.appendCodePoint(toCodePointSequence[j]);
        }
      };
    }
    return s.toString();
  }
}
