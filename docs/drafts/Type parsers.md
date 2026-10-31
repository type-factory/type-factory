Type parsers
=============

Overview
--------

Type parsers are the heart and engine of Type Factory custom data types.

We can create immutable and thread-safe `TypeParser` instances using a builder to configure what characters are acceptable, the min and max size, and what transformations should be applied to the data. 

Unlike regular expressions which only check for conformity to a pattern, type parsers can also perform transformations to ensure data consistency and quality as well as provide meaningful error messages for invalid values.

Like regular expressions, type parsers are relatively expensive to create, so it is recommended to create them once and reuse them.

Type parser transformation operations
-------------------------------------

### Letter case operations

Type parsers can be configured to convert all letters to upper, lower or title case. This is useful when you want your work with and/or store data in a consistent format.

The following letter case operations are available when configuring a type parser:

- `preserveCase()`
- `toLowerCase()`
- `toTitleCase()`
- `toUpperCase()`

If title case is set then the type parser will uppercase the first letter and lowercase the rest. Compound letters like [ǆ](https://www.compart.com/en/unicode/U+01C6) will be correctly converted to [ǅ](https://www.compart.com/en/unicode/U+01C5) instead of [Ǆ](https://www.compart.com/en/unicode/U+01C4). The type parser uses the Java [Character.toTitleCase(...)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#toTitleCase(char)) and [Character.toLowerCase(...)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#toLowerCase(char)) methods to implement title-casing of values.

### Whitespace operations

Type parsers can be configured to forbid, preserve, remove, normalize or convert whitespace characters. Whitespace characters are determined as per the Java [Character.isWhitespace(...)](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#isWhitespace(char)) method.

The following whitespace operations are available when configuring a type parser:

- `forbidWhitespace()`
- `normalizeWhitespace()` — removes all leading and trailing whitespace, then replaces all other contiguous blocks of whitespace with single space ' ' (U+0020) characters.
- `normalizeAndConvertWhitespaceTo(...)` — converts normalized whitespace characters to a char, codepoint or char-sequence (string)
- `preserveWhitespace()`
- `preserveAndConvertWhitespaceTo(...)` — converts all whitespace characters to a char, codepoint or char-sequence (string)
- `removeAllWhitespace()`

Conversion can be useful if you have custom data types that require spaces to be converted to, for example, underscores or hyphens.

### Null and empty operations

Type parsers can be configured to convert empty strings to null, null strings to empty or to preserve null and empty strings.

- `convertEmptyToNull()`
- `convertNullToEmpty()`
- `preserveNullAndEmpty()`

These operations can be combined with the whitespace operations. For example, configuring a type parser with either `normalizeWhitespace` or `removeAllWhitespace` along with:

- `convertEmptyToNull` will ensure a parsed value of `null` for null, empty or blank (whitespace-only) input values.
- `convertNullToEmpty` will ensure a parsed value of `""` (empty string) for null, empty or blank (whitespace-only) input values.

### Character conversion operations

These are created implicitly by the `TypeParser` builder. They are used to define what character or string transformations should be applied to the incoming data whilst parsing. 

For example;

- `convertChar(fromChar, toChar)` 
- `convertChar(fromChar, toCharSequence)`
- `convertCharSequence(fromCharSequence, toCharSequence)`
- `convertCodePoint(fromCodePoint, toCodePoint)`
- `convertCodePoint(fromCodePoint, toCharSequence)`
- `convertAnyInCategory(unicodeCategory, toChar)`
- `convertAnyInCategory(unicodeCategory, toCodePoint)`
- `convertAnyInCategory(unicodeCategory, toCharSequence)`
- `convertAnyInSubset(subset, toChar)`
- `convertAnyInSubset(subset, toCodePoint)`
- `convertAnyInSubset(subset, toCharSequence)`

We also provide some convenience methods that utilise the above methods to convert any dash or hyphen punctuation mark characters that are defined to be in the `Pd`/`Dash_Punctuation` Unicode category to the specified argument:

- `convertAllDashesTo(toChar)`
- `convertAllDashesTo(toCodePoint)`
- `convertAllDashesTo(toCharSequence)`
- `convertAllDashesToHyphen()` — converts any dash characters to the hyphen-minus character `U+002D`

### Character removal operations
