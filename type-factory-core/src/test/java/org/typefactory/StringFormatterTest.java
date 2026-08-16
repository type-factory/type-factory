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
package org.typefactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ObjIntConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringFormatterTest {

  @Test
  void constructors_initializeAnEmptyBuilder() {
    assertThatObject(new StringFormatter()).hasToString("");
    assertThatObject(new StringFormatter(32)).hasToString("");
    assertThatObject(new StringFormatter((Locale) null)).hasToString("");
    assertThatObject(new StringFormatter(32, Locale.GERMANY)).hasToString("");
    assertThatObject(new StringFormatter(32, null)).hasToString("");
  }

  private static void addDashedLineAndLineSeparator(final StringFormatter builder) {
    builder.append("----------------").appendLineSeparator();
  }

  @Test
  void apply_invokesTheConsumerUnconditionally() {

    final var builder = new StringFormatter()
        .apply(sb -> sb.append("a"))
        .appendLineSeparator()
        .apply(StringFormatterTest::addDashedLineAndLineSeparator);

    assertThatObject(builder)
        .hasToString("a" + System.lineSeparator() + "----------------" + System.lineSeparator());
  }

  @Test
  void apply_requiresAConsumer() {

    final var builder = new StringFormatter();

    assertThatThrownBy(() -> builder.apply(null))
        .isInstanceOf(NullPointerException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "true  | a",
      "false | ''",
  }, delimiter = '|')
  void when_appliesTheConsumerOnlyWhenTheConditionIsTrue(final boolean condition, final String expected) {

    final var builder = new StringFormatter()
        .when(condition, sb -> sb.append("a"));

    assertThatObject(builder).hasToString(expected);
  }

  @Test
  void when_appliesAppliesAMethodAsTheConsumer() {

    final var builder = new StringFormatter()
        .when(true, sb -> sb.append("a"))
        .appendLineSeparator()
        .when(true, StringFormatterTest::addDashedLineAndLineSeparator);

    assertThatObject(builder)
        .hasToString("a" + System.lineSeparator() + "----------------" + System.lineSeparator());
  }

  @Test
  void when_falseConditionDoesNotRequireAConsumer() {
    final var builder = new StringFormatter();

    builder.when(false, null);

    assertThatObject(builder).hasToString("");
  }

  @Test
  void when_trueConditionRequiresAConsumer() {
    final var builder = new StringFormatter();

    assertThatThrownBy(() -> builder.when(true, null))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void repeat_supportsCountZeroWithoutInvokingTheConsumer() {

    final var builder = new StringFormatter();
    final var consumerCount = new AtomicInteger();
    final var indexedCount = new AtomicInteger();

    builder.repeat(0, (sb, index) -> indexedCount.incrementAndGet());
    builder.repeat(0, (ObjIntConsumer<StringFormatter>) null);

    assertThat(consumerCount.get()).isZero();
    assertThat(indexedCount.get()).isZero();
    assertThatObject(builder).hasToString("");
  }

  @Test
  void repeat_rejectsNegativeCounts() {
    final var builder = new StringFormatter();

    assertThatThrownBy(() -> builder.repeat(-1, (sb, index) -> sb.append("x")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("count must be >= 0");
  }

  @Test
  void appendFill_andPadding_andSeparators_workAsExpected() {

    final var builder = new StringFormatter();

    builder
        .appendFill('-', 3)
        .appendFill('?', 2)
        .appendPadding(1)
        .appendTilde()
        .appendTab()
        .appendPipe()
        .appendComma()
        .appendLineSeparator()
        .appendSpace();

    assertThatObject(builder)
        .hasToString("---?? " + "~\t|," + System.lineSeparator() + " ");
  }

  @Test
  void appendPadding_usesChunkedSpacesForLargeWidths() {
    final var builder = new StringFormatter();

    builder.appendPadding(81);

    assertThat(builder.length()).isEqualTo(81);
    assertThatObject(builder).hasToString(" ".repeat(81));
  }

  @Test
  void appendPaddingToDistanceFromLastNewline_padsFromTheLastNewline() {
    final var builder = new StringFormatter()
        .append("abc")
        .appendNewline()
        .append("de")
        .appendPaddingToDistanceFromLastNewline(4);

    assertThatObject(builder).hasToString("abc" + System.lineSeparator() + "de  ");
  }

  @Test
  void appendPaddingToDistanceFromLastLineSeparator_padsFromTheLastLineSeparator() {
    final var builder = new StringFormatter()
        .append("abc")
        .appendLineSeparator()
        .append("de")
        .appendPaddingToDistanceFromLastLineSeparator(4);

    assertThatObject(builder).hasToString("abc" + System.lineSeparator() + "de  ");
  }

  @Test
  void appendFill_supportsCodePointsAndZeroWidth() {
    final var builder = new StringFormatter();

    builder
        .appendFill(0x2605, 2)
        .appendSpace()
        .appendFill(0x2605, 0);

    assertThatObject(builder).hasToString("★★ ");
  }

  @Test
  void appendFill_rejectsNegativeWidths() {
    final var builder = new StringFormatter();

    assertThatThrownBy(() -> builder.appendFill('-', -1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("width must be >= 0");
  }

  @Test
  void append_treatsNullValuesAsEmptyStrings() {
    final var builder = new StringFormatter();

    builder
        .append((String) null)
        .append((CharSequence) null)
        .append((StringBuffer) null)
        .append((Object) null)
        .append((char[]) null)
        .append((char[]) null, 0, 0)
        .append((CharSequence) null, 0, 0);

    assertThatObject(builder).hasToString("");
  }

  @Test
  void append_formatsNumbersUsingTheConfiguredLocale() {
    final var builder = new StringFormatter(Locale.GERMANY);

    builder
        .append(1234)
        .appendSpace()
        .append(1234L)
        .appendSpace()
        .append(1234.5f)
        .appendSpace()
        .append(1234.5d);

    assertThatObject(builder).hasToString("1234 1234 1234,5 1234,5");
  }

  @Test
  void setLocale_changesFormattingForSubsequentNumberOperations() {
    final var builder = new StringFormatter(Locale.US);

    builder
        .appendPipe()
        .append(1234)
        .appendPipe()
        .append(1234.567d)
        .appendPipe()
        .setLocale(Locale.GERMANY)
        .append(1234)
        .appendPipe()
        .append(1234.567d)
        .appendPipe()
        .leftAppend(1234L, 8)
        .appendPipe()
        .rightAppend(1234L, 8)
        .appendPipe();

    assertThatObject(builder).hasToString(
        "|1234|1234.567|1234|1234,567|1234    |    1234|");
  }

  @Test
  void numericAlignment_supportsPrimitiveIntAndNullBoxedVariants() {
    final var builder = new StringFormatter(Locale.GERMANY);

    builder
        .formatNullsAs("missing")
        .appendPipe()
        .leftAppend(1234, 8)
        .appendPipe()
        .rightAppend(1234, 8)
        .appendPipe()
        .leftAppend((Long) null, 8)
        .appendPipe()
        .rightAppend((Long) null, 8)
        .appendPipe()
        .leftAppend((BigInteger) null, 8)
        .appendPipe()
        .rightAppend((BigInteger) null, 8)
        .appendPipe()
        .rightAppend((BigDecimal) null, 8, 2)
        .appendPipe();

    assertThatObject(builder)
        .hasToString("|1234    |    1234|missing | missing|missing | missing| missing|");
  }

  @Test
  void formatNullsAs_changesNullHandlingForAppends() {

    final var builder = new StringFormatter()
        .formatNullsAs("missing")
        .append((String) null)
        .appendSpace()
        .append((CharSequence) null)
        .appendSpace()
        .append((StringBuffer) null)
        .appendSpace()
        .append((Object) null)
        .appendSpace()
        .append((CharSequence) null, 0, 0)
        .appendSpace()
        .append((char[]) null)
        .appendSpace()
        .append((char[]) null, 0, 0);

    assertThatObject(builder).hasToString(
        "missing missing missing missing missing missing missing");
  }

  @Test
  void formatNullsAs_acceptsNullAndRestoresEmptyNullHandling() {
    final var builder = new StringFormatter()
        .append('/')
        .formatNullsAs("missing")
        .append((String) null)
        .append('/')
        .formatNullsAs("")
        .append((String) null)
        .append('/')
        .formatNullsAs(null)
        .append((String) null)
        .append('/');

    assertThatObject(builder).hasToString("/missing//null/");
  }

  @Test
  void formatNullsAs_changesNullHandlingForAlignment() {

    final var builder = new StringFormatter()
        .formatNullsAs("missing")
        .appendPipe()
        .leftAppend((CharSequence) null, 10)
        .appendPipe()
        .rightAppend((CharSequence) null, 10)
        .appendPipe();

    assertThatObject(builder).hasToString("|missing   |   missing|");
  }

  @Test
  void formatNullsAs_changesNullHandlingForInserts() {

    final var inserted = new StringFormatter()
        .formatNullsAs("missing")
        .insert(0, (String) null)
        .insert(7, (CharSequence) null)
        .insert(14, (char[]) null)
        .insert(21, (Object) null)
        .insert(28, (CharSequence) null, 0, 0);

    assertThatObject(inserted).hasToString("missingmissingmissingmissingmissing");
  }

  @Test
  void formatNullsAsEmptyString_restoresEmptyNullHandling() {

    final var builder = new StringFormatter()
        .formatNullsAs("missing")
        .append((String) null)
        .formatNullsAsEmptyString()
        .append((String) null)
        .insert(0, (String) null);

    assertThatObject(builder).hasToString("missing");
  }

  @ParameterizedTest
  @CsvSource(value = {
      "1.2 | 1 | 1,2",
      "1.2 | 2 | 1,20",
      "12.345 | 0 | 12",
  }, delimiter = '|')
  void append_floatWithPrecision_formatsWithTheConfiguredLocale(
      final float value,
      final int precision,
      final String expected) {

    final var builder = new StringFormatter(Locale.GERMANY)
        .append(value, precision);

    assertThatObject(builder).hasToString(expected);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "1.2 | 1 | 1,2",
      "1.2 | 2 | 1,20",
      "12.345 | 0 | 12",
  }, delimiter = '|')
  void append_doubleWithPrecision_formatsWithTheConfiguredLocale(
      final double value,
      final int precision,
      final String expected) {

    final var builder = new StringFormatter(Locale.GERMANY)
        .append(value, precision);

    assertThatObject(builder).hasToString(expected);
  }

  @Test
  void append_formatsBooleanCharAndSequences() {

    final var builder = new StringFormatter(Locale.US)
        .append(true)
        .append(' ')
        .append((Object) new StringBuilder("obj"))
        .append(' ')
        .append((StringBuffer) new StringBuffer("buf"))
        .append(' ')
        .append((CharSequence) "chars")
        .append(' ')
        .append((CharSequence) "abcdef", 2, 5)
        .append(' ')
        .append(new char[]{'x', 'y'})
        .append(' ')
        .append(new char[]{'p', 'q', 'r'}, 1, 2);

    assertThatObject(builder).hasToString("true obj buf chars cde xy qr");
  }

  @Test
  void fluentHelpers_allowConditionalAndRepeatedContinuation() {

    final var builder = new StringFormatter()
        .apply(sb -> sb.append("a"))
        .when(false, sb -> sb.append("b"))
        .forEach(List.of("c", "d"), StringFormatter::append)
        .forEach(new String[]{"ee", "ff"}, StringFormatter::append)
        .repeat(3, StringFormatter::append)
        .appendFill('-', 3)
        .appendFill('x', 2)
        .appendPadding(1)
        .appendLineSeparator()
        .appendSpace()
        .append("z");

    assertThatObject(builder).hasToString("acdeeff012---xx " + System.lineSeparator() + " z");
  }

  @Test
  void forEach_treatsNullIterableAsEmpty() {

    final var builder = new StringFormatter()
        .forEach((Iterable<?>)null, StringFormatter::append);

    assertThatObject(builder).hasToString("");
  }

  @Test
  void forEach_iteratesOverValuesInOrder() {

    final var builder = new StringFormatter();
    final var visited = new ArrayList<String>();

    builder.forEach(Arrays.asList("a", "b", "c"), (sb, value) -> {
      visited.add(value);
      sb.append(value);
    });

    assertThat(visited).containsExactly("a", "b", "c");
    assertThatObject(builder).hasToString("abc");
  }

  @Test
  <T> void forEach_treatsNullArrayAsEmpty() {

    final var builder = new StringFormatter()
        .forEach((T [])null, StringFormatter::append);

    assertThatObject(builder).hasToString("");
  }

  @Test
  void forEach_iteratesOverArrayValuesInOrder() {

    final var builder = new StringFormatter();
    final var visited = new ArrayList<String>();

    builder.forEach(new String[]{"a", "b", "c"}, (sb, value) -> {
      visited.add(value);
      sb.append(value);
    });

    assertThat(visited).containsExactly("a", "b", "c");
    assertThatObject(builder).hasToString("abc");
  }

  @Test
  void leftAndRightAppend_padStringsAndNumbers() {

    final var builder = new StringFormatter(Locale.US)
        .leftAppend("ab", 5)
        .rightAppend("cd", 5)
        .leftAppend(12L, 5)
        .rightAppend(34L, 5)
        .leftAppend(new BigInteger("56"), 5)
        .rightAppend(new BigInteger("78"), 5);

    assertThatObject(builder).hasToString("ab   " + "   cd" + "12   " + "   34" + "56   " + "   78");
  }

  @Test
  void leftAndRightAppend_usesCharSequenceDirectly() {

    final var builder = new StringFormatter();

    final CharSequence value = new CharSequence() {
      private final String text = "xy";

      @Override
      public int length() {
        return text.length();
      }

      @Override
      public char charAt(final int index) {
        return text.charAt(index);
      }

      @Override
      public CharSequence subSequence(final int start, final int end) {
        return text.subSequence(start, end);
      }

      @Override
      public String toString() {
        throw new AssertionError("toString() should not be called");
      }
    };

    builder
        .leftAppend(value, 4)
        .rightAppend(value, 4);

    assertThatObject(builder).hasToString("xy  " + "  xy");
  }

  @Test
  void leftAndRightAppend_treatsNullCharSequenceAsEmpty() {

    final var builder = new StringFormatter()
        .leftAppend((CharSequence) null, 3)
        .rightAppend((CharSequence) null, 3);

    assertThatObject(builder).hasToString("   " + "   ");
  }

  @Test
  void boxedNumericAlignment_usesLocaleFormatting() {

    final var builder = new StringFormatter(Locale.GERMANY)
        .appendPipe()
        .leftAppend(Long.valueOf(1234L), 8)
        .appendPipe()
        .rightAppend(Long.valueOf(1234L), 8)
        .appendPipe()
        .leftAppend(new BigInteger("1234"), 8)
        .appendPipe()
        .rightAppend(new BigInteger("1234"), 8)
        .appendPipe()
        .leftAppend(Double.valueOf(1.5d), 8, 1)
        .appendPipe()
        .rightAppend(Double.valueOf(1.5d), 8, 1)
        .appendPipe()
        .leftAppend(new BigDecimal("1.5"), 8, 1)
        .appendPipe()
        .rightAppend(new BigDecimal("1.5"), 8, 1)
        .appendPipe();

    assertThatObject(builder).hasToString("|1234    |    1234|1234    |    1234|1,5     |     1,5|1,5     |     1,5|");
  }

  @Test
  void decimalFormatting_usesLocaleAndAlwaysShowsLeadingZero() {

    final var builder = new StringFormatter(Locale.GERMANY)
        .appendPipe()
        .leftAppend(1.5d, 6, 2)
        .appendPipe()
        .rightAppend(0.5d, 6, 2)
        .appendPipe()
        .leftAppend(new BigDecimal("0.25"), 6, 2)
        .appendPipe()
        .rightAppend((Double) null, 6, 2)
        .appendPipe()
        .leftAppend((BigDecimal) null, 6, 2)
        .appendPipe()
        .leftAppend((Double) null, 6, 2)
        .appendPipe();

    assertThatObject(builder).hasToString("|1,50  |  0,50|0,25  |      |      |      |");
  }

  @Test
  void insert_supportsAllOverloads() {

    final var builder = new StringFormatter()
        .insert(0, "a")
        .insert(1, (Object) "b")
        .insert(2, new char[]{'c', 'd'})
        .insert(4, new char[]{'e', 'f', 'g'}, 1, 2)
        .insert(6, (CharSequence) "hij")
        .insert(9, (CharSequence) "klmn", 1, 3);

    assertThatObject(builder).hasToString("abcdfghijlm");
  }

  @Test
  void insert_supportsBooleanCharacterAndNumberOverloads() {

    final var builder = new StringFormatter(Locale.US)
        .insert(0, true)
        .insert(4, '!')
        .insert(5, 1234)
        .insert(9, 1234L)
        .insert(13, 1.5f)
        .insert(16, 2.5d);

    assertThatObject(builder).hasToString("true!123412341.52.5");
  }

  @Test
  void wrapsStandardStringBuilderOperations() {

    final var builder = new StringFormatter(8)
        .append("abc")
        .insert(1, "x")
        .delete(2, 3)
        .replace(1, 2, "y")
        .reverse()
        .appendCodePoint('z');

    assertThat(builder.length()).isEqualTo(4);
    assertThat(builder.capacity()).isGreaterThanOrEqualTo(8);
    assertThat(builder.charAt(0)).isEqualTo('c');
    assertThat(builder.substring(0, 2)).isEqualTo("cy");
    assertThat(builder.indexOf("y")).isEqualTo(1);
    assertThat(builder.lastIndexOf("c")).isZero();
    assertThat(builder.codePointCount(0, builder.length())).isEqualTo(builder.length());
    assertThatObject(builder).hasToString("cyaz");
  }

  @Test
  void standardBuilderStyleUtilityMethods_areDelegated() {

    final var builder = new StringFormatter();

    assertThat(builder.isEmpty()).isTrue();

    builder.append("hello");
    builder.ensureCapacity(64);
    builder.trimToSize();
    builder.setLength(4);
    builder.setCharAt(1, 'a');

    final var chars = new char[4];
    builder.getChars(0, 4, chars, 0);

    assertThat(builder.isEmpty()).isFalse();
    assertThat(builder.length()).isEqualTo(4);
    assertThat(builder.capacity()).isGreaterThanOrEqualTo(4);
    assertThat(builder.charAt(0)).isEqualTo('h');
    assertThat(builder.substring(0)).isEqualTo("hall");
    assertThat(builder.substring(0, 4)).isEqualTo("hall");
    assertThat(builder.subSequence(0, 2)).hasToString("ha");
    assertThat(builder.offsetByCodePoints(0, 2)).isEqualTo(2);
    assertThat(builder.codePointAt(0)).isEqualTo('h');
    assertThat(builder.codePointBefore(2)).isEqualTo('a');
    assertThat(builder.codePointCount(0, 4)).isEqualTo(4);
    assertThat(chars).containsExactly('h', 'a', 'l', 'l');
    assertThat(builder.chars().toArray()).containsExactly('h', 'a', 'l', 'l');
    assertThat(builder.codePoints().toArray()).containsExactly('h', 'a', 'l', 'l');
    assertThat(builder.compareTo(new StringFormatter().append("hall"))).isZero();
  }

  @Test
  void deleteCharAt_andSearchOverloads_areDelegated() {

    final var builder = new StringFormatter()
        .append("ababa")
        .deleteCharAt(1);

    assertThatObject(builder).hasToString("aaba");
    assertThat(builder.indexOf("ba", 1)).isEqualTo(2);
    assertThat(builder.lastIndexOf("ba", 3)).isEqualTo(2);
  }

  @Test
  void privateAlignmentHelpers_treatNullAsTheConfiguredNullText() throws Exception {

    final var builder = new StringFormatter().formatNullsAs("missing");
    final Method leftAppend = StringFormatter.class.getDeclaredMethod(
        "appendAlignedLeft", CharSequence.class, int.class);
    final Method rightAppend = StringFormatter.class.getDeclaredMethod(
        "appendAlignedRight", CharSequence.class, int.class);
    leftAppend.setAccessible(true);
    rightAppend.setAccessible(true);

    leftAppend.invoke(builder, new Object[]{null, 0});
    rightAppend.invoke(builder, new Object[]{null, 0});

    assertThatObject(builder).hasToString("missingmissing");
  }

  @Test
  void privatePaddingHelper_measuresDistanceFromTheLastStringOrTheBeginningWhenMissing() throws Exception {

    final var builder = new StringFormatter()
        .append("abc")
        .append("XX")
        .append("de");
    final Method appendPaddingToDistanceFromLastString = StringFormatter.class.getDeclaredMethod(
        "appendPaddingToDistanceFromLastString", int.class, String.class);
    appendPaddingToDistanceFromLastString.setAccessible(true);

    appendPaddingToDistanceFromLastString.invoke(builder, 4, "XX");
    assertThatObject(builder).hasToString("abcXXde  ");

    final var missingDelimiterBuilder = new StringFormatter().append("abc");
    appendPaddingToDistanceFromLastString.invoke(missingDelimiterBuilder, 4, "XX");
    assertThatObject(missingDelimiterBuilder).hasToString("abc ");
  }
}
