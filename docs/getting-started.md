---
layout: page
title: "Getting Started"
nav_order: 2000
---

# Getting Started

Type Factory helps you replace loosely validated `String` values with small, explicit custom types.

Use it when a value has business rules of its own: currency codes, country codes, account numbers, identifiers, names, or any field where callers should not pass "just any string".

Instead of repeating regex constraints, trimming, upper-casing, and validation logic across constructors, setters, controllers, and services, define the rule once in a type. The result is a normal Java object with predictable parsing, formatting, equality, hashing, comparison, and error messages.

## Requirements

Type Factory requires Java 17 or later.

## Add the dependency

Import the Type Factory bill-of-materials (BOM), then add the modules you need.

<details name="import-coordinates" markdown="1">
<summary>Maven</summary>

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.typefactory</groupId>
      <artifactId>type-factory-bom</artifactId>
      <version>1.0.1</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency> <!-- Required: the core Type Factory module. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-core</artifactId>
  </dependency>
  <dependency> <!-- Optional: locale Unicode CLDR module. -->
    <groupId>org.typefactory</groupId>
    <artifactId>type-factory-unicode-cldr</artifactId>
  </dependency>
</dependencies>
```

</details>

<details name="import-coordinates" markdown="1">
<summary>Gradle</summary>

```groovy
dependencies {
  implementation platform("org.typefactory:type-factory-bom:1.0.1")

  // Required: the core Type Factory module. 
  implementation "org.typefactory:type-factory-core"

  // Optional: predefined locale-related Unicode CLDR data.
  implementation "org.typefactory:type-factory-unicode-cldr"
}
```

</details>

## Why use a custom type?

With ordinary strings, the validation rule and cleanup rule often drift apart:

```java
@Pattern(regexp = "\\s*+[a-zA-Z]{3}\\s*+")
private String currencyCode;

public void setCurrencyCode(final String currencyCode) {
  this.currencyCode = currencyCode.toUpperCase().trim();
}
```

This code can work, but every part of your application (or applications) must remember which regex to use, when to trim, when to uppercase, and when the value is safe to pass around.

With Type Factory, the rules move into the type:

```java
private CurrencyCode currencyCode;
```

Instantiating with `CurrencyCode.of(" usd ")` will clean, validate, normalize, and return a `CurrencyCode` value-object that extends `CharSequence` with a value of `USD`. 

Invalid input fails at the application boundary instead of leaking through the application as a raw string.

## Example 1: CurrencyCode

This type accepts exactly three ASCII letters, removes whitespace, converts the value to uppercase, and creates an immutable `CurrencyCode`.

```java
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class CurrencyCode extends StringType {

  public static final CurrencyCode EMPTY_CURRENCY_CODE = new CurrencyCode("");

  private static final MessageCode ERROR_MESSAGE = MessageCode.of(
      "invalid.currency.code",
      "must be a 3-character ISO 4217 currency code");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .messageCode(ERROR_MESSAGE)
      .acceptCharRange('a', 'z') // Accept lowercase letters
      .acceptCharRange('A', 'Z') // Accept uppercase letters
      .toUpperCase()             // Convert to uppercase
      .fixedSize(3)
      .removeAllWhitespace()
      .convertNullToEmpty() // Or you could preserveNullAndEmpty() or convertEmptyToNull()
      .build();

  private CurrencyCode(final String value) {
    super(value);
  }

  public static CurrencyCode of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, CurrencyCode::new);
  }
}
```

Our `CurrencyCode` extends `StringType`, which implements `CharSequence` and `Comparable`, and it provides the value object methods for you: `equals`, `hashCode`, `compareTo`, and `toString`.

Use it like this:

```java
CurrencyCode currencyCode = CurrencyCode.of(" usd ");

currencyCode.toString(); // "USD"
currencyCode.length();   // 3

```

## Example 2: PersonalName_fr

For names, a custom type can centralize locale-specific character rules and Unicode cleanup. This French personal name type accepts characters from the French alphabet, spaces, apostrophes, and hyphens. It also normalizes whitespace, normalizes Unicode to NFC, and converts common apostrophe and dash variants to system-friendly ASCII characters.

```java
import java.util.Locale;
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;
import org.typefactory.unicode.LocaleData;

public class PersonalName_fr extends StringType {

  public static final MessageCode ERROR_MESSAGE = MessageCode.of(
      "invalid.personal.name.fr",
      "must be made up of characters in the French alphabet, hyphens, apostrophes or spaces only.");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .messageCode(ERROR_MESSAGE)
      .minSize(1)
      .maxSize(60)
      .acceptSubset(LocaleData.getForLocale(Locale.FRENCH).standardCharactersSubset())
      .acceptChars('\'', '-')
      .convertChar('\u2019', '\'') // Convert ’ RIGHT SINGLE QUOTATION to U+0027 APOSTROPHE
      .convertChar('\u2010', '-')  // Convert ‐ HYPHEN                 to U+002D HYPHEN-MINUS
      .convertChar('\u2011', '-')  // Convert ‑ NON-BREAKING HYPHEN    to U+002D HYPHEN-MINUS
      .convertChar('\u2013', '-')  // Convert – EN DASH                to U+002D HYPHEN-MINUS
      .toCharacterNormalizationFormNFC() // Combine diacritics into characters: e + ◌́ → é
      .normalizeWhitespace()
      .convertEmptyToNull()
      .build();

  private PersonalName_fr(final String value) {
    super(value);
  }

  public static PersonalName_fr of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, PersonalName_fr::new);
  }
}
```

Use it like this:

```java
PersonalName_fr name = PersonalName_fr.of("  Jean‑Paul    d’Arcy   ");

name.toString(); // "Jean-Paul d'Arcy"
name.length();   // 16
```

We configured the parser to `convertEmptyToNull()` along with `normalizeWhitespace()` so creating a name instance with empty or blank input results in null. We could have chosen to `preserveNullAndEmpty()` or `convertNullToEmpty()` instead, if that is what we preferred.

```java
PersonalName_fr name1 = PersonalName_fr.of("  "); // name1 is null
PersonalName_fr name2 = PersonalName_fr.of(null); // name2 is null
```

## Example 3: InternationalBankAccountNumber

This IBAN example combines character rules, cleanup, regex format checking, and custom check-digit validation.

```java
import java.util.regex.Pattern;
import org.typefactory.MessageCode;
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class InternationalBankAccountNumber extends StringType {

  public static final InternationalBankAccountNumber EMPTY_IBAN =
      new InternationalBankAccountNumber("");

  private static final MessageCode ERROR_MESSAGE = MessageCode.of(
      "invalid.international.bank.account.number",
      "must be a valid 5..34 character International Bank Account Number (IBAN)");

  private static final Pattern VALID_IBAN_PATTERN =
      Pattern.compile("[A-Z]{2}+[0-9]{2}+[0-9A-Z]{1,30}+");

  private static final TypeParser TYPE_PARSER = TypeParser.builder()
      .messageCode(ERROR_MESSAGE)
      .acceptLettersAtoZ() // convenience method for ASCII a-zA-Z
      .acceptDigits0to9()  // convenience method for ASCII 0-9
      .minSize(5)
      .maxSize(34)
      .removeAllWhitespace()
      .removeAllChars('.', '-', '–', '—') // Remove common visual or UI formatting separators.
      .toUpperCase()
      .matchesRegex(VALID_IBAN_PATTERN)   // Must match the IBAN format.
      .customValidator(InternationalBankAccountNumber::isValidIBAN) // Check digit validation.
      .build();

  private InternationalBankAccountNumber(final String value) {
    super(value);
  }

  public static InternationalBankAccountNumber of(final CharSequence value) {
    return TYPE_PARSER.parseToStringType(value, InternationalBankAccountNumber::new);
  }

  private static final long MAX = 999999999;
  private static final long MODULUS = 97;
  private static final int MAX_ALPHANUMERIC_VALUE = 35;

  private static boolean isValidIBAN(final String value) {
    final int valueLength = value.length();
    long total = 0;

    for (int i = 0; i < valueLength; ++i) {
      final int numericValue = Character.getNumericValue(value.charAt((i + 4) % valueLength));
      if (numericValue < 0 || numericValue > MAX_ALPHANUMERIC_VALUE) {
        return false;
      }

      total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;
      if (total > MAX) {
        total = total % MODULUS;
      }
    }

    return (total % MODULUS) == 1;
  }
}
```

Use it like this:

```java
InternationalBankAccountNumber iban =
    InternationalBankAccountNumber.of("gb82 west 1234 5698 7654 32");

iban.toString(); // "GB82WEST12345698765432"
```

## Next steps

- Keep each custom type small and focused on one value.
- Create one static, immutable `TypeParser` per type and reuse it.
- Put cleanup rules, regex checks, and custom validators inside the type.
- Use normal validation annotations, such as `@NotNull` or `@NotBlank`, to express whether the field is required.
