package org.typefactory;

record LocaleDataImpl(
    Subset standardCharactersSubset,
    Subset auxiliaryCharactersSubset,
    Subset punctuationCharactersSubset,
    Subset decimalDigitsSubset)
    implements LocaleData {
}
