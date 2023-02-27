/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.whitespaces;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class provides all available UTF-8 whitespace characters as test cases.
 * <p>
 * It implements the {@link ArgumentsProvider} and {@link AnnotationConsumer} interfaces for use in JUnit 5 tests.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class WhitespacesProvider implements ArgumentsProvider {
    /**
     * A map that contains various types of whitespace characters as keys and their
     * corresponding Unicode values as values.
     */
    private static final Map<String, String> WHITESPACES;

    static {
        WHITESPACES = new HashMap<>();
        WHITESPACES.put("empty", "");
        WHITESPACES.put("space", "\u0020");
        WHITESPACES.put("en quad", "\u2000");
        WHITESPACES.put("em quad", "\u2001");
        WHITESPACES.put("en space", "\u2002");
        WHITESPACES.put("em space", "\u2003");
        WHITESPACES.put("three-per-em space", "\u2004");
        WHITESPACES.put("four-per-em space", "\u2005");
        WHITESPACES.put("six-per-em space", "\u2006");
        WHITESPACES.put("punctuation space", "\u2008");
        WHITESPACES.put("thin space", "\u2009");
        WHITESPACES.put("hair space", "\u200A");
        WHITESPACES.put("medium mathematical space", "\u205F");
        WHITESPACES.put("ideographic space", "\u3000");
        WHITESPACES.put("ogham space mark", "\u1680");
        WHITESPACES.put("horizontal tabulation", "\t");
        WHITESPACES.put("line feed", "\n");
        WHITESPACES.put("vertical tabulation", "\u000B");
        WHITESPACES.put("form feed", "\f");
        WHITESPACES.put("carriage return", "\r");
        WHITESPACES.put("file separator", "\u001C");
        WHITESPACES.put("group separator", "\u001D");
        WHITESPACES.put("record separator", "\u001E");
        WHITESPACES.put("unit separator", "\u001F");
        WHITESPACES.put("line separator", "\u2028");
        WHITESPACES.put("paragraph separator", "\u2029");
    }

    /**
     * Returns a Named object containing the key and value of a given entry object.
     *
     * @param entry a {@link Map.Entry} object containing a key-value pair
     * @return a {@link Named} object containing the key and value of the given {@link Map.Entry} object
     * @since 0.0.0
     */
    static Named<String> namedOf(Map.Entry<String, String> entry) {
        return Named.of(entry.getKey(), entry.getValue());
    }

    /**
     * Generates a stream of Arguments objects containing the different types of whitespace
     * characters as arguments for JUnit 5 tests.
     *
     * @param context the current extension context for the test; never {@code null}
     * @return a stream of Arguments objects containing the different types of whitespace
     * characters as arguments for JUnit 5 tests
     * @since 0.0.0
     */
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return WHITESPACES.entrySet().stream().map(WhitespacesProvider::namedOf).map(Arguments::of);
    }
}
