# Alphabets, scripts, blocks and categories

Before going further let us define what we mean by alphabets, scripts, blocks and categories.

## Alphabets

An alphabet is made up letters, letter-modifiers and ideograms that a native speaker would be immediately understand to be a part of forming words in
their native language in everyday use. They are letters, letter-modifiers and ideograms that would be valid for entering the fields on a form if you
were applying for a driver's license or a bank account.

An alphabet does not contain archaic characters that have fallen from use. Nor does it contain numerals or punctuation characters – we can group and
discuss these separately.

In software, you need to define what you believe to be valid alphabet characters for data-type field validation - for example:

```java
"Nicholas".matches("[a-zA-Z]+");      // returns true
"Νικόλαος".matches("[ΆΈΉΊΌΎ-ΡΣ-ώ]+"); // returns true

"Ŋʅʗƕᴑꝲɐƨ".matches("[a-zA-Z]+");      // returns false
"ͶͱϏϖϡἇϙϟ".matches("[ΆΈΉΊΌΎ-ΡΣ-ώ]+"); // returns false
```

## Scripts

A script is a construct of the Unicode Consortium. All Unicode characters/code-points are designated as belonging to exactly one script. It has been
standardised with the creation of the
<a href='https://unicode.org/iso15924/iso15924-codes.html' target='_blank'>ISO 15924 codes</a>
document.

They can already be used in Java regular expressions prefixing their ISO code or alias with `Is` - for example:

```java
"Nicholas".matches("\\p{IsLatin}+"); // returns true
"Νικόλαος".matches("\\p{IsGreek}+"); // returns true
```

Unfortunately, scripts also contain many characters that a native speaker would *not*
recognise to be an accepted way, when used, to form words in their native language in for official use.
They are characters/code-points that would *not* be valid for use when 
entering the fields on a form if you were applying for a driver's license or a bank account.

For example:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{IsLatin}+"); // returns true – ouch!
"ͶͱϏϖϡἇϙϟ".matches("\\p{IsGreek}+"); // returns true – ouch!
```

See also – <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeScript.html">Character.UnicodeScript
(JavaDocs)</a> for the complete set of Unicode scripts recognised by Java.


## Blocks

A block is a construct of the Unicode Consortium. It is a contiguous range of characters/code-point values that are always assigned in multiples of 16
values. All Unicode characters/code-points belong to exactly one block.

A block may contain characters/code-points from multiple scripts. A script may contain characters/code-points from multiple blocks.

They can already be used in Java regular expressions prefixing their block name or alias with `In` - for example:

```java
"Nicholas".matches("\\p{InBasicLatin}+");     // returns true
"Νικόλαος".matches("\\p{InGreekAndCoptic}+"); // returns true
```

Unfortunately, blocks also contain many characters that a native speaker would *not*
recognise to be an accepted way, when used, to form words in their native language in for official use. 
They are characters/code-points that would *not* be valid for use when
entering the fields on a form if you were applying for a driver's license or a bank account.

For example:

```java
"N1(<0L@$".matches("\\p{InBasicLatin}+");     // returns true – ouch!
"ͶͱϏϖϡϫϙϟ".matches("\\p{InGreekAndCoptic}+"); // returns true – ouch!
```

See also – <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.UnicodeBlock.html">Character.UnicodeBlock 
(JavaDocs)</a> for the complete set of Unicode blocks recognised by Java.

## General categories

A general category is a construct of the Unicode Consortium. It is a way to categorise, into sets, kinds of characters/code-points
across the entire Unicode range. 

All Unicode characters/code-points belong to exactly one general category. There are
also "union" categories that combine general categories. For example the “Letter” category is a union of the 
categories “UppercaseLetter”, “LowerCaseLetter”, “TitlecaseLetter”, “ModifierLetter”, and “OtherLetter”.

A category contains characters/code-points from across multiple blocks and scripts. Likewise,
blocks and scripts contain characters/code-points from categories.

They can already be used in Java regular expressions prefixing using their category name or alias - for example:

```java
"Nicholas".matches("\\p{L}+"); // returns true
"Νικόλαος".matches("\\p{L}+"); // returns true
```

Unfortunately, categories also contain many characters that a native speaker would *not*
recognise to be an accepted way, when used, to form words in their native language in for official use.
They are characters/code-points that would *not* be valid for use when
entering the fields on a form if you were applying for a driver's license or a bank account.

For example:

```java
"Ŋʅʗƕᴑꝲɐƨ".matches("\\p{L}+"); // returns true – ouch!
"ͶͱϏϖϡἇϙϟ".matches("\\p{L}+"); // returns true – ouch!
```

See also – <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html">Character
(JavaDocs)</a> where you find `int` constants for all the Unicode general categories that are recognised by Java.
