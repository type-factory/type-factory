/*
 * Copyright © 2021-2026 Evan Toliopoulos (typefactory.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * <p>The {@code org.typefactory} package contains the core classes and interfaces of the TypeFactory library.</p>
 *
 * <p>The TypeFactory library provides a framework for defining and working with strongly-typed values in Java. It allows developers to create custom
 * types that encapsulate specific validation rules, formatting, and parsing logic.</p>
 *
 * <p>Key classes and interfaces in this package include:</p>
 *
 * <ul>
 *   <li>{@link org.typefactory.TypeParser} &mdash; For creating immutable, threadsafe parsers that validate, normalize, and clean input values according
 *   to your configured rules. Create them using the {@link org.typefactory.TypeParser#builder()}.</li>
 *   <li>{@link org.typefactory.MessageCode} &mdash; A class representing error message codes used for validation feedback. Create them
 *   with the {@link org.typefactory.MessageCode#of(java.lang.String, java.lang.String)} factory method proving both the code and a default message.
 *   Message codes implement {@link java.lang.CharSequence} interface. </li>
 *   <li>{@link org.typefactory.StringType} &mdash; An abstract base class for creating strongly typed string values.</li>
 *   <li>{@link org.typefactory.CharSequenceType} &mdash; An interface if you would prefer to use Java records for your strongly typed
 *   string values.</li>
 *   <li>{@link org.typefactory.InvalidValueException} &mdash; The exception thrown by the {@link org.typefactory.TypeParser} when a
 *   value is invalid according to the configured rules.</li>
 *   <li>{@link org.typefactory.TypeParserBuilderException} &mdash; The exception thrown by the {@link org.typefactory.TypeParser#builder()}
 *   when a builder configuration is invalid.</li>
 *   <li>{@link org.typefactory.Types} &mdash; A utility class providing null-safe static methods for working with strongly typed values.</li>
 * </ul>
 *
 * <p>See the Type Factory documentation for more information.</p>
 */
package org.typefactory;