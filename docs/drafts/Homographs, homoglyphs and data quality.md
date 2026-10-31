Homoglyphs, homographs and data quality
=======================================

Overview
--------

By defining custom data types we can prevent vulnerabilities and attacks in our applications and improve data quality.

Organisations will often need to understand which data can be accepted in any language and which data needs to be collected in the alphabet and language of the country the organisation is operating in. For example, an e-commerce site may have legal, banking and tax obligations that need to be met, transacted and reported in the language of the country the company is operating in.

This is especially true when the data is being reported to a government department or a financial institution. For example, a driver's license application form in Australia may require the applicant's name to be in the English alphabet. A bank account application form in Greece may require the applicant's name to be in the Greek alphabet.

### Homoglyphs versus homographs
In language terms, homographs are words that are spelled the same but have different meanings. In cybersecurity terms, homoglyph and homograph are often used synonymously. The [internationalized domain name (IDN) homograph attack](https://en.wikipedia.org/wiki/IDN_homograph_attack) describes attacks where domain name words look the same but are spelled using different homoglyph characters – for example "apple.com" and "аpple.com", where the "a" in the second domain is the Cyrillic letter "а" but looks like an English "a".


The Common Weakness Enumeration (CWE) site has a category for homoglyph vulnerabilities ["CWE-1007 Insufficient Visual Distinction of Homoglyphs Presented to User"](https://cwe.mitre.org/data/definitions/1007.html)

Some documented homoglyph and homograph vulnerabilities are:

- [CVE-2005-0233 Homoglyph spoofing using punycode in URLs and certificates](https://www.cve.org/CVERecord?id=CVE-2005-0233)
- [CVE-2009-0652 Incomplete denylist does not include homoglyphs of "/" and "?" characters in URLs](https://www.cve.org/CVERecord?id=CVE-2009-0652)
- [CVE-2012-0584 Improper character restriction in URLs in web browser](https://www.cve.org/CVERecord?id=CVE-2012-0584)
- [CVE-2013-7236 Web forum allows impersonation of users with homoglyphs in account names](https://www.cve.org/CVERecord?id=CVE-2013-7236)
- [CVE-2017-5015 Web browser does not convert hyphens to punycode, allowing IDN spoofing in URLs](https://www.cve.org/CVERecord?id=CVE-2017-5015)
- [CVE-2019-10044 Vulnerable to an IDN homograph attack when displaying messages containing URLs](https://www.cve.org/CVERecord?id=CVE-2019-10044)
- [CVE-2019-15237: Mishandles Punycode xn-- domain names, leading to homograph attacks](https://www.cve.org/CVERecord?id=CVE-2019-15237)
- [CVE-2021-42694 Source code identifiers such as function names using homoglyphs that render visually identical to a target identifier](https://www.cve.org/CVERecord?id=CVE-2021-42694)

Hiding in plain sight – do you know your client?
------------------------------------------------

Many companies have a requirement to know their client (KYC) and to report suspicious activity to the authorities. This is especially true for financial institutions.

Unicode applications and databases without strict validation can enable rogue actors to hide in plain sight.

For example, searching your application for an account name of `JOHN SMITH` may not return any results if the account name is stored as `JОΗN SMITΗ` – where the second "O" is the Cyrillic letter "О" and the "H" is the Greek letter "Н".

If you're a global company, you may have to deal with multiple character sets and scripts to properly fulfill your KYC obligations.

How do custom data types help?
------------------------------

By defining custom data types, you can limit what letters, numbers and symbols are permitted. You can also do some cleanup and normalization of the data before it is stored in the database. For example, you could:

- replace all kinds of whitespaces with an ordinary space character `U+0020`.
- replace contiguous blocks of spaces with a single space character and remove all leading and trailing spaces.
- replace all kinds of dashes with an ordinary hyphen-minus character `U+002D`, reducing accidental or intentional use of en-dashes `U+2013`, hyphens `U+2010` and non-breaking hyphens `U+2011`.
- convert underscores to hyphens, or vice versa.

The Type Factory `TypeParser` can simplify this. We can define a custom `PersonalName` type that only allows letters from a single alphabet. If you're a global company then your application might require a locale to be passed in to determine which alphabet to use.

Example of a custom `PersonalName` type for the French alphabet. 

<details>
<summary>Expand to see the set of Unicode letters in the French alphabet...</summary>

```text
0041..005A   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
0061..007A   a b c d e f g h i j k l m n o p q r s t u v w x y z
00C0         À
00C2         Â
00C6..00CB   Æ Ç È É Ê Ë
00CE..00CF   Î Ï
00D4         Ô
00D9         Ù
00DB..00DC   Û Ü
00E0         à
00E2         â
00E6..00EB   æ ç è é ê ë
00EE..00EF   î ï
00F4         ô
00F9         ù
00FB..00FC   û ü
00FF         ÿ
0152..0153   Œ œ
0178         Ÿ
```

</details>

Héloïse d'Aboville
Jean-François De la Croix
Véronique Carrier-Belleuse,

```java
import org.typefactory.StringType;
import org.typefactory.TypeParser;

public final class PersonalName extends StringType {                 // ①

  public static final MessageCode ERROR_MESSAGE = MessageCode.of(    // ②
      "invalid.personal.name.fr", 
      "must be made up of characters in the French alphabet, hyphens, apostrophes or spaces only.");
  
  private static final TypeParser TYPE_PARSER = TypeParser.builder() // ③
      .messageCode(ERROR_MESSAGE)        // ②
      .acceptSubset(Letters.FRENCH_fr)   // ④
      .toCharacterNormalizationFormNFC() // ⑤
      .convertAllDashesToHyphen()        // ⑥
      .minSize(1)                        // ⑦
      .maxSize(50)                       // ⑧
      .acceptChar('\'')                  // ⑨
      .normalizeWhitespace()             // ⑩
      .convertEmptyToNull()              // ⑪
      .build();

  private PersonalName(final String value) { // ⑫
    super(value);
  }

  public static PersonalName of(final CharSequence value) {  // ⑬
    return TYPE_PARSER.parseToStringType(value, PersonalName::new); 
  }
}
```


