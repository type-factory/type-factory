package org.typefactory;

record LocaleDataImpl(
    Subset standardSubset,
    Subset auxiliarySubset,
    Subset punctuationSubset)
    implements LocaleData {
}
