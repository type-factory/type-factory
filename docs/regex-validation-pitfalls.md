# Regex Validation Pitfalls

Most data types will be composed of alphanumeric characters and perhaps some punctuation or special characters.

Regular expressions are commonly used to validate these data types so long as you're familiar with the target 
alphabets and numeral systems.

Java regular expressions are powerful, and reading the documentation for 
the [Patttern](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html) 
class can give the impression that all you need is to specify the target Unicode category, script or block.
For example, you might try using:

- `\\p{L}+` &ndash; restrict a value to one or more characters in the _Unicode Letters Category_.
- `\\p{IsLatin}+` &ndash; restrict a value to one or more characters in the _Unicode Latin Script_.
- `\\p{InBasicLatin}+` &ndash; restrict a value to one or more characters in the _Unicode Basic Latin Block_.

We'll see below why these are not what you want. 

What are the alternatives to help to validate data types for specific alphabets and numeral systems:

- Hand coding your regular expression.
- Using ICU4J to help create Locale specific regular expression.
- Using Type Factory to help create Locale specific type parsers that are resilient and robust.

Let's first look at the pitfalls of Unicode categories, scripts, and blocks for regex validation.

## Pitfalls of Unicode categories, scripts, and blocks

### Unicode categories

A Unicode general category groups code points by kind, such as letter, decimal digit, punctuation, or symbol. Every Unicode code point belongs to one general category.

Some categories are unions. For example, `Letter` includes uppercase letters, lowercase letters, titlecase letters, modifier letters, and other letters.

Java regular expressions can match categories with `\p{...}` notation. 

Imagine we have simplistic name field validation that only allows letters using the Unicode `Letter` category:

```java
"Nicholas".matches("\\p{L}+");  // true - valid English letters
"Νικόλαος".matches("\\p{L}+");  // true - valid Greek letters
```

Categories are useful, but they are too broad for many fields and can leave you open
to Homoglyph attacks or unwanted and unsearchable characters.

```java
"Νichοlas".matches("\\p{L}+");  // true - Looks like English, but contains Greek N and o
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{L}+");  // true - ouch! All Unicode letters but not from the English alphabet!
"ͶͱϏϖϡἇϙϟ".matches("\\p{L}+");  // true - ouch! All Unicode letters but not from the Greek alphabet!
```

See Java's [`Character`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html) constants for the categories recognised by Java.

### Unicode scripts

A Unicode script groups characters used by a writing system, such as Latin, Greek, Arabic. Every Unicode code point belongs to one script.

Java regular expressions can match scripts with `\p{Is...}` notation. Let's continue our simplistic name field validation allowing only characters from the `Latin` or `Greek` scripts:

```java
"Nicholas".matches("\\p{IsLatin}+");  // true - valid English letters
"Νικόλαος".matches("\\p{IsGreek}+");  // true - valid Greek letters
```

Scripts are broad and can include characters that are restricted to an alphabet. They reduce
the risk for homoglyph attacks but still leave you open to unwanted and unsearchable characters.

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{IsLatin}+");  // true - ouch! All Unicode letters but not from the English alphabet!
"ͶͱϏϖϡἇϙϟ".matches("\\p{IsGreek}+");  // true - ouch! All Unicode letters but not from the Greek alphabet!
```

See the Unicode Consortium's [ISO 15924 script codes](https://unicode.org/iso15924/iso15924-codes.html) and Java's [`Character.UnicodeScript`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeScript.html).

### Unicode blocks

A Unicode block is a contiguous range of code points. Every Unicode code point belongs to one block.

Blocks are about code point layout, not language rules:

- One block can contain characters from multiple scripts.
- One script can span multiple blocks.

Java regular expressions can match blocks with `\p{In...}`:

```java
"Nicholas".matches("\\p{InBasicLatin}+");      // true - valid English letters
"Νικόλαος".matches("\\p{InGreekAndCoptic}+");  // true - valid Greek letters
```

Blocks often include punctuation, symbols, numerals, or historic characters. Using a block is the worst
choice presented so far for validating a field. They will leave you open to homoglyph attacks and
unwanted or unsearchable characters.

```java
"N1(<0L@$".matches("\\p{InBasicLatin}+");      // true - ouch! All Unicode letters but not from the English alphabet!
"ͶͱϏϖϡϫϙϟ".matches("\\p{InGreekAndCoptic}+");  // true - ouch! All Unicode letters but not from the Greek alphabet!
```

See Java's [`Character.UnicodeBlock`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeBlock.html).

### Combining categories, scripts, and blocks

Aha! What about combining categories, scripts, and blocks to get a more specific match? For example, accepting only letters from a specific script:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("[\\p{IsLatin}&&\\p{L}]+"); // true - ouch! All Unicode letters but not from the English alphabet!
"ͶͱϏϖϡἇϙϟ".matches("[\\p{IsGreek}&&\\p{L}]+"); // true - ouch! All Unicode letters but not from the Greek alphabet!
```

Again, these are still too broad for many fields and can leave you open to Homoglyph attacks or unwanted and unsearchable characters.

## The alternatives

Let's look at some alternatives to help you validate data types for specific alphabets and numeral systems:

### Hand-coded alphabet regex

You can hand-code specific alphabets for your field validation – assuming you know the characters
that are required for the target language.

```java
"Nicholas".matches("[a-zA-Z]+");       // true - allow English letters only
"Νικόλαος".matches("[ΆΈΉΊΌΎ-ΡΣ-ώ]+");  // true - allow Greek letters only
```

Our hand-coded alphabet regex forbids obsolete characters, punctuation, or symbols allowing us to easily detect invalid field values:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("[a-zA-Z]+");       // false - hurray! Not English letters
"ͶͱϏϖϡἇϙϟ".matches("[ΆΈΉΊΌΎ-ΡΣ-ώ]+");  // false - hurray! Not Greek letters
```

Don't know the alphabet for your field? You can use ICU4J or Type Factory to help you create Locale specific regular expressions.

### ICU4J to help craft regex

ICU4J is a Java library that provides Unicode and Globalization support. It can help you create Locale specific regular expressions for your field validation.

```java


```


## Validation rule of thumb

Use Unicode scripts, blocks, and categories when they match the rule you actually want. Otherwise, define a narrower alphabet for the field.

For example, `\p{IsLatin}`, `\p{InBasicLatin}`, and `\p{L}` can all be valid tools, but none of them means "characters a person may enter in this specific field".







using First we'll look at the pitfalls of using regular expressions for validation, and then we'll look at how Type Factory can help you create robust and resilient types.

Should you create a custom Type Factory type for a field or just use a regular expression?

Java regular expressions are a powerful tool for validating strings, but they are not always the best choice. Regular expressions can be hard to read and maintain, and they can be too broad or too narrow for your needs, and can cause performance issues if they are complex or allow excessive or catastrophic backtracking.

A custom Type Factory type can provide a clear, explicit, and reusable definition of the valid values for a field
along with a clear error message when validation fails. It can also provide additional functionality, such as parsing, formatting, and comparison.

Let's consider a custom type for a name field that only allows letters for a specific language. We'll first consider how to do this with regular expressions, and then how to do it with a custom Type Factory type.

Let's clarify some terminology first. There are several ways to group characters, actual locale alphabets, Unicode scripts, Unicode blocks, and Unicode general categories:

- An _alphabet_ is a set of characters that are valid for a language.
- Unicode _scripts_, _blocks_, and _categories_ are character groupings. They are often too broad for practical validation. They include characters that are valid Unicode but not generally valid for specific field types like ordinary names or identifiers.
