# Is number parsing broken in Java?

There are many times when you need to parse a numeric value from a string. Fortunately, this works most of the time for most locales. But when it doesn't work it can be tricky to debug and fix.

## Why does this happen?

The problem is caused by a mixture of:

- locale-specific decimal separators, also known as decimal points, like periods `.` or commas `,`.
- locale-specific grouping separators, also known as thousands separators, like commas `,` or periods `.`.
- locale-specific negative prefixes and suffixes, like a negative sign `-` or parentheses `()` around a value.
- invisible formatting characters like right-to-left marks, left-to-right marks, and Arabic letter marks.

## The easy fix – use Type Factory's type parsers

The easiest way to parse a numeric value is to use of Type Factory's numeric parsers like:

- `LongTypeParser`
- `IntegerTypeParser`
- `ShortTypeParser`

### Parsing into a `long`

```java
final static LongTypeParser TYPE_PARSER = LongTypeParser.builder()
    .defaultLocale(locale)
    .build();

// Parse for the default locale
final var actual = TYPE_PARSER.parse(value);

// Parse for another locale
final var actual = TYPE_PARSER.parse(value, locale);
```

## Now a deep dive showing why Java number parsing is broken

It turns out that parsing `long` values is slightly less fraught than parsing `double` values in Java – but not without issues. Though you might get lucky if you only parse numeric strings formated for certain locales.

Let's take a look at some examples.

### The problem parsing integral values

We'll look at two examples that parse an integral value from a string into a `Long` for two separate locales. The numeric string values are all created using the following Java formatting methods:

```java
Long.toString();
NumberFormat.format(...);
String.format(...)
```

#### Parsing longs for locale en-US, English, United States

Looking at this code you could assume that using `numberFormat.parse(...)` is the safest method as it successfully parsed all three kinds of numeric string without an exception. You would be right... but only for certain locales.

```java
Long value = -12345678L;
Locale locale = new Locale("en", "US"); // English (United States)
NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

value.toString()                    = "-12345678"
numberFormat.format(value)          = "-12,345,678"
String.format(locale, "%d", value)  = "-12345678"

Long.parseLong(value.toString())                       = Long.parseLong("-12345678"  )     = -12345678L
Long.parseLong(numberFormat.format(value))             = Long.parseLong("-12,345,678")     → NumberFormatException
Long.parseLong(String.format(locale, "%d", value))     = Long.parseLong("-12345678"  )     = -12345678L

Long.valueOf(value.toString())                         = Long.valueOf("-12345678"  )       = -12345678L
Long.valueOf(numberFormat.format(value))               = Long.valueOf("-12,345,678")       → NumberFormatException
Long.valueOf(String.format(locale, "%d", value))       = Long.valueOf("-12345678"  )       = -12345678L

numberFormat.parse(value.toString())                   = numberFormat.parse("-12345678"  ) = -12345678L
numberFormat.parse(numberFormat.format(value))         = numberFormat.parse("-12,345,678") = -12345678L
numberFormat.parse(String.format(locale, "%d", value)) = numberFormat.parse("-12345678"  ) = -12345678L
```

#### Parsing longs for locale ar-EG, Arabic, Egypt

Look at this code for the Arabic Egyptian locale and you can see that using `numberFormat.parse(...)` becomes the worst way to parse a number. For negative values, `NumberFormat` can only parse a number that it created.

While reading this code, keep in mind that Arabic numbers are written left-to-right, even though the Arabic script is written right-to-left.

Also notice that the locale-specific formating now has some curious quirks, with the negative sign sometimes appearing before the number and sometimes after it.

```java
Long value = -12345678L;
Locale locale = new Locale("ar", "EG"); // Arabic (Egypt)
NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

value.toString()                    = "-12345678"
numberFormat.format(value)          = "؜-١٢٬٣٤٥٬٦٧٨"
String.format(locale, "%d", value)  = "-١٢٣٤٥٦٧٨"

value.toString().codePoints()                   = "[45, 49, 50, 51, 52, 53, 54, 55, 56]"
numberFormat.format(value).codePoints()         = "[1564, 45, 1633, 1634, 1644, 1635, 1636, 1637, 1644, 1638, 1639, 1640]"
String.format(locale, "%d", value).codePoints() = "[45, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640]"

Long.parseLong(value.toString())                       = Long.parseLong("-12345678"  )     = -12345678L
Long.parseLong(numberFormat.format(value))             = Long.parseLong("؜-١٢٬٣٤٥٬٦٧٨")     → NumberFormatException
Long.parseLong(String.format(locale, "%d", value))     = Long.parseLong("-١٢٣٤٥٦٧٨"  )     = -12345678L

Long.valueOf(value.toString())                         = Long.valueOf("-12345678"  )       = -12345678L
Long.valueOf(numberFormat.format(value))               = Long.valueOf("؜-١٢٬٣٤٥٬٦٧٨")       → NumberFormatException
Long.valueOf(String.format(locale, "%d", value))       = Long.valueOf("-١٢٣٤٥٦٧٨"  )       = -12345678L

numberFormat.parse(value.toString())                   = numberFormat.parse("-12345678"  ) → ParseException
numberFormat.parse(numberFormat.format(value))         = numberFormat.parse("؜-١٢٬٣٤٥٬٦٧٨") = -12345678L
numberFormat.parse(String.format(locale, "%d", value)) = numberFormat.parse("-١٢٣٤٥٦٧٨"  ) → ParseException
```

### How about decimal values?

I mentioned that parsing `double` values is more fraught than for `long` values. Let's take a look.

#### Parsing doubles for locale en-US, English, United States

Notice that for this locale, the `numberFormat.parse(...)` method is, again, the only one that can parse all three kinds of numeric string without an exception. This is the safest method to use for this locale.

```java
Double value = -12345678.503D;
Locale locale = new Locale("en", "US"); // English (United States)
NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

value.toString()                      = "-1.2345678503E7"
numberFormat.format(value)            = "-12,345,678.503"
String.format(locale, "%.3f", value)  = "-12345678.503"

Double.parseDouble(value.toString())                     = Double.parseDouble("-1.2345678503E7") = -12345678.503D
Double.parseDouble(numberFormat.format(value))           = Double.parseDouble("-12,345,678.503") → NumberFormatException
Double.parseDouble(String.format(locale, "%.3f", value)) = Double.parseDouble("-12345678.503"  ) = -12345678.503D

Double.valueOf(value.toString())                         = Double.valueOf("-1.2345678503E7")     = -12345678.503D
Double.valueOf(numberFormat.format(value))               = Double.valueOf("-12,345,678.503")     → NumberFormatException
Double.valueOf(String.format(locale, "%.3f", value))     = Double.valueOf("-12345678.503"  )     = -12345678.503D

numberFormat.parse(value.toString())                     = numberFormat.parse("-1.2345678503E7") = -12345678.503D
numberFormat.parse(numberFormat.format(value))           = numberFormat.parse("-12,345,678.503") = -12345678.503D
numberFormat.parse(String.format(locale, "%.3f", value)) = numberFormat.parse("-12345678.503"  ) = -12345678.503D
```

#### Parsing doubles for locale ar-EG, Arabic, Egypt

Now for another locale we can see how parsing into a `Double` gets much worse for all methods with exceptions thrown for most parsing methods. 

```java
Double value = -12345678.503D;
Locale locale = new Locale("ar", "EG"); // Arabic (Egypt)
NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

value.toString()                      = "-1.2345678503E7"
numberFormat.format(value)            = "؜-١٢٬٣٤٥٬٦٧٨٫٥٠٣"
String.format(locale, "%.3f", value)  = "-١٢٣٤٥٦٧٨٫٥٠٣"

Double.parseDouble(value.toString())                     = Double.parseDouble("-1.2345678503E7") = -12345678.503D
Double.parseDouble(numberFormat.format(value))           = Double.parseDouble("؜-١٢٬٣٤٥٬٦٧٨٫٥٠٣") → NumberFormatException
Double.parseDouble(String.format(locale, "%.3f", value)) = Double.parseDouble("-١٢٣٤٥٦٧٨٫٥٠٣"  ) → NumberFormatException

Double.valueOf(value.toString())                         = Double.valueOf("-1.2345678503E7")     = -12345678.503D
Double.valueOf(numberFormat.format(value))               = Double.valueOf("؜-١٢٬٣٤٥٬٦٧٨٫٥٠٣")     → NumberFormatException
Double.valueOf(String.format(locale, "%.3f", value))     = Double.valueOf("-١٢٣٤٥٦٧٨٫٥٠٣"  )     → NumberFormatException

numberFormat.parse(value.toString())                     = numberFormat.parse("-1.2345678503E7") → ParseException
numberFormat.parse(numberFormat.format(value))           = numberFormat.parse("؜-١٢٬٣٤٥٬٦٧٨٫٥٠٣") = -12345678.503D
numberFormat.parse(String.format(locale, "%.3f", value)) = numberFormat.parse("-١٢٣٤٥٦٧٨٫٥٠٣"  ) → ParseException
```

## Is there a pure Java hack I can use?

For parsing `Long` values, maybe. For parsing `Double` values, not really – the Arabic example above shows that the number formated by `String.format(locale, ...)` cannot be parsed by any of the methods.

If you need to parse `Long` values for many locales reliably with pure Java, you could try creating your own parse method that would try different parse methods in order.

```java
public Long parseLong(final Locale locale, final String value) throws ParseException {
  try {
    // First try parsing with the locale
    final NumberFormat numberFormat = NumberFormat.getIntegerInstance(locale);
    return numberFormat.parse(value).longValue();
  } catch (final ParseException e) {
    try {
      // Now try parsing using the simple valueOf method.
      return Long.valueOf(value);
    } catch (final NumberFormatException ignored) {
      throw e;
    }
  }
}
```