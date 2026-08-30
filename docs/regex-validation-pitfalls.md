---
layout: page
title: "Regex Validation Pitfalls"
nav_order: 3100
---

<details markdown="block">
  <summary>
    Table of contents
  </summary>
  {: .text-delta }
1. TOC
{:toc}
</details>

# Regex Validation Pitfalls

Regular expressions are commonly used to validate values so long as you're familiar with the target alphabets and numeral systems. There is no doubt that regular expressions are powerful.

What if you need to limit values to certain character-types, languages, or alphabets? Reading the documentation for the Java [Pattern](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html) class can give the impression that you only need to specify the target Unicode category, script or block.

For example, you might try using:

- `\\p{L}+` &ndash; restrict a value to one or more characters in the _Unicode Letters Category_.
- `\\p{IsLatin}+` &ndash; restrict a value to one or more characters in the _Unicode Latin Script_.
- `\\p{InBasicLatin}+` &ndash; restrict a value to one or more characters in the _Unicode Basic Latin Block_.

We'll see below why these are not what you want. 

We'll also see some alternatives to help to validate data types for specific alphabets and numeral systems that include:

- Hand coding your regular expression.
- Using ICU4J to help create Locale specific regular expression.
- Using Type Factory to help create Locale specific type parsers that are resilient and robust.

Let's first look at the pitfalls of Unicode categories, scripts, and blocks for regex validation.

# Unicode category, script, and block regex

## Unicode categories

A Unicode general category groups code points by kind, such as letter, decimal digit, punctuation, or symbol. Every Unicode code point belongs to one general category.

Java regular expressions can match categories with `\p{...}` notation.

Some categories are unions. For example, the `Letter` category includes uppercase letters, lowercase letters, titlecase letters, modifier letters, and other letters.

Imagine we have simplistic name field validation that only allows letters using the Unicode `Letter` category:

```java
"Nicholas".matches("\\p{L}+");  // true - ✅ valid English letters
"Νικόλαος".matches("\\p{L}+");  // true - ✅ valid Greek letters
```

Categories are useful, but they are too broad for many fields and can leave you open
to Homoglyph attacks or unwanted and unsearchable characters.

```java
"Νichοlas".matches("\\p{L}+");  // true - ❌ Homoglyphs, looks English, but contains Greek N and o
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{L}+");  // true - ❌ Unicode letters but not from the English alphabet!
"ͶͱϏϖϡἇϙϟ".matches("\\p{L}+");  // true - ❌ Unicode letters but not from the Greek alphabet!
```

See Java's [`Character`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html) constants for the categories recognised by Java.

## Unicode scripts

A Unicode script groups characters used by a writing system, such as Latin, Greek, Arabic. Every Unicode code point belongs to one script.

Java regular expressions can match scripts with `\p{Is...}` notation. Let's continue our simplistic name field validation allowing only characters from the `Latin` or `Greek` scripts:

```java
"Nicholas".matches("\\p{IsLatin}+");  // true  - ✅ valid English letters
"Νichοlas".matches("\\p{IsLatin}+");  // false - ✅ stopped homoglyphs, looks English, contains Greek N and o
"Νικόλαος".matches("\\p{IsGreek}+");  // true  - ✅ valid Greek letters
```

Scripts are broad and can include characters that are restricted to an alphabet. They reduce
the risk for homoglyph attacks but still leave you open to unwanted and unsearchable characters.

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{IsLatin}+");  // true - ❌ Unicode letters but not from the English alphabet!
"ͶͱϏϖϡἇϙϟ".matches("\\p{IsGreek}+");  // true - ❌ Unicode letters but not from the Greek alphabet!
```

See the Unicode Consortium's [ISO 15924 script codes](https://unicode.org/iso15924/iso15924-codes.html) and Java's [`Character.UnicodeScript`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeScript.html).

## Unicode blocks

A Unicode block is a contiguous range of code points. Every Unicode code point belongs to one block.

Blocks are about code point layout, not language rules:

- One block can contain characters from multiple scripts.
- One script can span multiple blocks.

Java regular expressions can match blocks with `\p{In...}`:

```java
"Nicholas".matches("\\p{InBasicLatin}+");      // true  - ✅ valid English letters
"Νichοlas".matches("\\p{InGreekAndCoptic}+");  // false - ✅ stopped homoglyphs, looks English, contains Greek N and o
"Νικόλαος".matches("\\p{InGreekAndCoptic}+");  // true  - ✅ valid Greek letters
```

Blocks often include punctuation, symbols, numerals, or historic characters. Using a block is the worst
choice presented so far for validating a field. They will leave you open to homoglyph attacks and
unwanted or unsearchable characters.

```java
"N1(<0L@$".matches("\\p{InBasicLatin}+");      // true - ❌ All characters in the block, but not English!
"ͶͱϏϖϡϫϙϟ".matches("\\p{InGreekAndCoptic}+");  // true - ❌ All characters in the block, but not Greek!
```

See Java's [`Character.UnicodeBlock`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeBlock.html).

## Combining categories, scripts, and blocks

Aha! What about combining categories, scripts, and blocks to get a more specific match? For example, accepting only letters from a specific script:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("[\\p{L}&&[\\p{IsLatin}]]+"); // true - ❌ All letters and in the script but not English!
"ͶͱϏϖϡἇϙϟ".matches("[\\p{L}&&[\\p{IsGreek}]]+"); // true - ❌ All letters and in the script but not Greek!
```

Again, these are still too broad for many fields and can leave you open to Homoglyph attacks or unwanted and unsearchable characters.

# Language specific regex

Let's look at some alternatives to help you validate data types for specific alphabets and numeral systems:

## Hand-coded alphabet regex

You can hand-code specific alphabets for your field validation – assuming you know the characters
that are required for the target language.

```java
"Nicholas".matches("[a-zA-Z]+");                 // true - ✅ allow English letters only
"Νικόλαος".matches("([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+");  // true - ✅ allow Greek letters only
```

Our hand-coded alphabet regex forbids obsolete characters, punctuation, or symbols allowing us to easily detect invalid field values:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("[a-zA-Z]+");                 // false - ✅ Not English letters
"ͶͱϏϖϡἇϙϟ".matches("([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+");  // false - ✅ Not Greek letters
```

Don't know the alphabet for your field? You can use ICU4J or Type Factory to help you create Locale specific regular expressions.

## ICU4J alphabet regex &ndash; simplistic approach

International Components for Unicode for Java (ICU4J) provide a library for Unicode and globalisation support. Use it to help you find information on the letters, numbers, and punctuation used by a language (and much more).

A simplistic attempt at creating a regex for a name field that only allows letters for a specific language using ICU4J might look like this:

```java
var locale = ULocale.forLanguageTag(localeTag);
var alphabetSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);
var pattern = Pattern.compile(alphabetSet.toPattern(false) + "+");
```

This works most of the time but has problems. The ICU4J exemplar sets for some languages sometimes contain strings representing a single letter. For example, calling `UnicodeSet.toPattern()` on the will result in the following for Greek:

```text
[ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]
```

Why are there curly braces in the pattern? The Greek alphabet contains some letters like `Ϊ́` that are actually a string of three letters `Ι ◌̈ ◌́` &ndash; it doesn't have a single code point representation. A better Greek alphabet regex pattern would be:

```text
([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+
```

The ICU4J `toPattern` method is intended to be used by the ICU4J `UnicodeSet` class and not by the Java `Pattern` class. Better regexes from the ICU4J exemplar sets can be created manually by iterating over the code point ranges and the strings in the set.

The unit tests below show how close we came with a simplistic ICU4J approach to creating a regex for a name field that only allows letters for a specific language.

```java
@ParameterizedTest
@CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
  NAME       | LOCALE | ICU4J_PATTERN                       | MATCHES   | NOTES
  Nicholas   | en-EN  | [A-Za-z]+                           | true      | ✅ English name
  Ŋʅʗƕᴑꝲɐƨ   | en-EN  | [A-Za-z]+                           | false     | ✅ Not English letters
  Νichοlas   | en-EN  | [A-Za-z]+                           | false     | ✅ Homoglyph, English & Greek letters
  Νικόλαος   | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | true      | ✅ Greek name
  ͶͱϏϖϡϫϙϟ   | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | false     | ✅ Not Greek letters
  Νι{κόλ}αος | el-GR  | [ΆΈ-ΊΌΎ-ΡΣ-ώ{Ϊ́}{Ϋ́}{ΐ}{ΰ}]+          | true      | ❌ Invalid curly braces accepted
  François   | fr-FR  | [A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ]+ | true      | ✅ French name
  Begoña     | fr-FR  | [A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ]+ | false     | ✅ Spanish name with ñ
  """)
void alphabet_regex_created_from_ICU4J_locale_exemplar_set(
  final String name, final String localeTag,
  final String expectedIcu4jPattern, final boolean expectedMatch) {

  final var locale = ULocale.forLanguageTag(localeTag);
  final var alphabetSet = LocaleData.getExemplarSet(locale, ADD_CASE_MAPPINGS, ES_STANDARD);
  final var pattern = Pattern.compile(alphabetSet.toPattern(false) + "+");
  final var matcher = pattern.matcher(name);
  
  assertThat(pattern).hasToString(expectedIcu4jPattern);
  assertThat(matcher.matches()).isEqualTo(expectedMatch);
}
```

## ICU4J alphabet regex &ndash; thorough approach

To fix the invalid regex patterns, we need to manually create them from the ICU4J exemplar in a way that correctly handles strings. I have provided an example below &ndash; it is a bit more complex than the simplistic approach.

<details markdown="1">
<summary>Expand to see the code for manually creating a regex from an ICU4J exemplar set</summary>

```java
@ParameterizedTest
@CsvSource(delimiter = '┃', useHeadersInDisplayName = true, textBlock = """
    NAME       ┃ LOCALE ┃ ICU4J_PATTERN                         ┃ MATCHES   ┃ NOTES
    Nicholas   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ true      ┃ ✅ English name
    Ŋʅʗƕᴑꝲɐƨ   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ false     ┃ ✅ Not English letters
    Νichοlas   ┃ en-EN  ┃ ([A-Za-z])+                           ┃ false     ┃ ✅ Homoglyph, English & Greek letters
    Νικόλαος   ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ true      ┃ ✅ Greek name
    ͶͱϏϖϡϫϙϟ   ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ false     ┃ ✅ Not Greek letters
    Νι{κόλ}αος ┃ el-GR  ┃ ([ΆΈ-ΊΌΎ-ΡΣ-ώ]|Ϊ́|Ϋ́|ΐ|ΰ)+              ┃ false     ┃ ✅ Valid pattern and curly braces caught
    François   ┃ fr-FR  ┃ ([A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ])+ ┃ true      ┃ ✅ French name
    Begoña     ┃ fr-FR  ┃ ([A-Za-zÀÂÆ-ËÎÏÔÙÛÜàâæ-ëîïôùûüÿŒœŸ])+ ┃ false     ┃ ✅ Spanish name with ñ
    """)
void alphabetRegexManuallyCreatedFromIcu4jLocaleExemplarSet(
    final String name, final String localeTag,
    final String expectedManualPattern, final boolean expectedMatch) {

  final var locale = ULocale.forLanguageTag(localeTag);
  final var alphabetSet = LocaleData.getExemplarSet(locale, UnicodeSet.ADD_CASE_MAPPINGS, LocaleData.ES_STANDARD);

  // Manually create the regex pattern from the ICU4J exemplar set
  final var regex = new StringBuilder();
  regex.append('(');
  
  // Add the codepoint ranges to the regex pattern
  if (alphabetSet.getRangeCount() > 0) {
    regex.append('[');
    for (var range : alphabetSet.ranges()) {
      regex.append(escapeCodePointIfRequired(range.codepoint));
      if (range.codepoint < range.codepointEnd) {
        if (range.codepoint + 1 != range.codepointEnd) {
          regex.append('-');
        }
        regex.append(escapeCodePointIfRequired(range.codepointEnd));
      }
    }
    regex.append(']');
  }
  
  // Add the codepoint strings to the regex pattern
  if (alphabetSet.hasStrings()) {
    for (var s : alphabetSet.strings()) {
      regex.append('|').append(s);
    }
  }
  regex.append(")+");

  // Finally, we can create the Java Pattern with a valid regex
  final var pattern = Pattern.compile(regex.toString());
  final var matcher = pattern.matcher(name);

  assertThat(pattern).hasToString(expectedManualPattern);
  assertThat(matcher.matches()).isEqualTo(expectedMatch);
}

// Helper method to escape code points for regex if required
static String escapeCodePointIfRequired(final int codePoint) {
  if ((Character.isAlphabetic(codePoint) || Character.isDigit(codePoint))
      && Character.getType(codePoint) != Character.MODIFIER_LETTER) {
    return Character.toString(codePoint);
  } else if (codePoint > 0xFFFF) {
    {% raw %}return String.format("\\x{%06X}", codePoint);{% endraw %}
  } else {
    return String.format("\\u%04X", codePoint);
  }
}
```

</details>

# Type Factory Type Parsers

Type Factory uses a `TypeParser` to define valid values, cleanup rules, and error messages in one place. Type parsers are immutable and thread-safe, so you can create one parser and reuse it across your application.

For example, this parser accepts English letters, hyphens, apostrophes, and spaces for a personal name.

```java
static final TypeParser NAME_PARSER = TypeParser.builder()
    .acceptSubset(LocaleData.getForLocale(Locale.ENGLISH).standardCharactersSubset())
    .acceptChars('\'', '-') // Accept U+0027 (apostrophe) and U+002D (hyphen-minus)
    .build();
```

We could have used `.acceptCharRange('a', 'z')` and `.acceptCharRange('A', 'Z')` to accept English letters. Instead, we used `.acceptSubset(LocaleData.getForLocale(...))`. 

Type Factory provides locale data to assist in getting standard sets of alphabet characters, auxiliary letters (used in loan words), punctuation, and numerals. Locale data is derived from the Unicode Common Locale Data Repository (CLDR) datasets:

```java
var englishAlphabetSet = LocaleData.getForLocale(Locale.ENGLISH).standardCharactersSubset();
var frenchAlphabetSet = LocaleData.getForLocale(Locale.FRENCH).standardCharactersSubset();
var greekAlphabetSet = LocaleData.getForLocale(Locale.of("el")).standardCharactersSubset();
```

Let's see how our `TypeParser` performs against the regular expression test cases we've been using:

```java
@ParameterizedTest
@CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
    NAME       | LOCALE | VALID  | EXCEPTION_MESSAGE_CONTAINS                                          | NOTES
    Nicholas   | en-EN  | true   |                                                                     | ✅ English name
    Ŋʅʗƕᴑꝲɐƨ   | en-EN  | false  | invalid character Ŋ U+014A LATIN CAPITAL LETTER ENG                 | ✅ Not English alphabet letters
    Νichοlas   | en-EN  | false  | invalid character Ν U+039D GREEK CAPITAL LETTER NU                  | ✅ Homoglyph, English & Greek letters
    Νικόλαος   | el-GR  | true   |                                                                     | ✅ Greek name
    ͶͱϏϖϡϫϙϟ   | el-GR  | false  | invalid character Ͷ U+0376 GREEK CAPITAL LETTER PAMPHYLIAN DIGAMMA  | ✅ Not Greek alphabet letters
    Νι{κόλ}αος | el-GR  | false  | invalid character { U+007B LEFT CURLY BRACKET                       | ✅ Invalid curly braces caught
    François   | fr-FR  | true   |                                                                     | ✅ French name
    Begoña     | fr-FR  | false  | invalid character ñ U+00F1 LATIN SMALL LETTER N WITH TILDE          | ✅ Spanish name with ñ
    """)
void alphabetTypeParserCreatedFromLocaleExemplarSet(
    final String name, final String localeTag,
    final boolean expectedValid, final String expectedExceptionMessage) {

  final var locale = Locale.forLanguageTag(localeTag);

  final TypeParser parser = TypeParser.builder()
      .acceptSubset(LocaleData.getForLocale(locale).standardCharactersSubset())
      .acceptChars('\'', '-') // Accept U+0027 (apostrophe) and U+002D (hyphen-minus)
      .build();

  if (expectedValid) {
    assertThat(parser.parseToString(name)).isEqualTo(name);
  } else {
    assertThatExceptionOfType(InvalidValueException.class)
        .isThrownBy(() -> parser.parseToString(name))
        .withMessageContaining(expectedExceptionMessage);
  }
}
```

The `TypeParser` caught all the invalid values and provided meaningful error messages. The error message will always contain the invalid character if it is printable along with the Unicode code point identifier. The Unicode character name (that helps spot homoglyphs) will only be provided if you configure the Type Factory to provide them.

## Normalization and cleanup with Type Factory

Our `TypeParser` example can also take care of normalization and cleanup. It can transform values provided with apostrophes or dashes that are different to what our system expects. We aim for our applications to adhere to the [Robustness Principle or Postel's Law](https://en.wikipedia.org/wiki/Robustness_principle): _"Be lenient in what you expect and strict in what you provide."_:

```java
static final TypeParser NAME_PARSER = TypeParser.builder()
    .messageCode(MessageCode.of( // Custom message key and default message for invalid values
        "invalid.personal.name",
        "must be made up of English letters, hyphens, apostrophes and spaces only.")) 
    .minSize(1)             // Min-length
    .maxSize(60)            // Max-length
    .acceptSubset(LocaleData.getForLocale(Locale.ENGLISH).standardCharactersSubset())
    .acceptChars('\'', '-') // Accept U+0027 (apostrophe) and U+002D (hyphen-minus)
    .convertChar('’', '\'') // Convert U+2019 (right single quotation mark) to U+0027 (apostrophe) for system compatibility
    .convertChar('‐', '-')  // Convert U+2010 (hyphen) to U+002D (hyphen-minus) for system compatibility
    .convertChar('‑', '-')  // Convert U+2011 (non-breaking hyphen) to U+002D (hyphen-minus) for system compatibility
    .convertChar('–', '-')  // Convert U+2013 (en dash) to U+002D (hyphen-minus) for system compatibility
    .normalizeWhitespace()  // Remove leading and trailing whitespace and convert blocks of whitespace to a single space character.
    .convertEmptyToNull()   // Other options are to convertNullToEmpty() or preserveNullAndEmpty().
    .build();
```

This kind of cleanup is often done with the following kind of boilerplate at the application boundaries:

```java
void doSomething(
    @Pattern(
        regexp = "^[a-zA-Z' -]{1,60}$", 
        message="must be made up of English letters, hyphens, apostrophes and spaces only.") 
    final String name) {
  
  final var cleanedName = name.trim()
      .replaceAll("\\s+", " ")
      .replaceAll("[’]", "'")
      .replaceAll("[‐‑–]", "-");
  // do useful work...
}
```

We do away with the boilerplate when we define custom types. By providing serializers/deserializers for JSON and database layers, we can ensure our values are instantiated and validated at the application boundaries. We fail-fast for invalid values and only pass around valid, cleaned, immutable values:

```java
void doSomething(final PersonalName name) {
  // do useful work...
}
```

## Complete example of a personal name custom type

Our `PersonalName` custom type uses the immutable threadsafe `TypeParser` we defined earlier. By extending `StringType`, it gets all the standard value object methods, such as: `equals()`, `hashcode()`, `compareTo()`, `toString()`:

```java
import java.util.Locale;
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;
import org.typefactory.unicode.LocaleData;

public final class PersonalName extends StringType {

  public static final MessageCode ERROR_MESSAGE = MessageCode.of(
      "invalid.personal.name",
      "must be made up of English letters, hyphens, apostrophes and spaces only.");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .messageCode(ERROR_MESSAGE)
      .minSize(1)
      .maxSize(60)
      .acceptSubset(LocaleData.getForLocale(Locale.ENGLISH).standardCharactersSubset())
      .acceptChars('\'', '-')
      .convertChar('’', '\'')
      .convertChar('‐', '-')
      .convertChar('‑', '-')
      .convertChar('–', '-')
      .normalizeWhitespace()
      .convertEmptyToNull()
      .build();

  // Private constructor to enforce the use of the factory method
  private PersonalName(final String value) {
    super(value);
  }

  /**
   * Creates a new PersonalName instance with the given value after parsing and validation.
   * @param value the value to be parsed and validated as a PersonalName
   * @return a new PersonalName instance with the given value or null if the value is null or empty after whitespace normalization
   * @throws org.typefactory.InvalidValidException if the value is invalid, according to the TypeParser rules
   */
  public static PersonalName of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, PersonalName::new);
  }
}
```

# Conclusion

Regular expressions can be useful, but Unicode categories, scripts, and blocks are often too broad for real field validation. Using libraries like ICU4J and Type Factory can help create better validation rules. 

Type Factory will allow you to create custom value object types that validate, normalize and clean your data at the boundary allowing you to pass safe immutable type values instead of raw strings. Any invalid values will always result in consistent error messages.

