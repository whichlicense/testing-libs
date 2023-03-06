/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.naming;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;

/**
 * This class generates display names for test methods based on the pattern
 * "given [given], when [when], then [then]".
 * <p>
 * It extends the {@link DisplayNameGenerator.Standard} class and overrides
 * the {@link #generateDisplayNameForMethod(Class, Method)} method to provide
 * custom display names for test methods.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class GivenWhenThenNameGenerator extends DisplayNameGenerator.Standard {
    /**
     * Regular expression pattern used to match the test method names
     * and extract the "given", "when", and "then" components.
     */
    private static final Pattern COMPONENTS, UPPER_CASE;

    static {
        COMPONENTS = compile("(?:given(?<given>[a-zA-Z0-9]*))?when(?<when>[a-zA-Z0-9]*)then(?<then>[a-zA-Z0-9]*)", CASE_INSENSITIVE);
        UPPER_CASE = compile("(?=\\p{Lu})");
    }

    /**
     * Utility method used to convert a string to sentence case by splitting it
     * at uppercase letters and joining the resulting parts with spaces.
     *
     * @param str the string to convert to sentence case
     * @return the sentence-cased version of the input string
     * @since 0.0.0
     */
    static String space(String str) {
        return Stream.of(UPPER_CASE.split(str)).map(String::toLowerCase).collect(joining(" "));
    }

    /**
     * Overrides the {@link DisplayNameGenerator.Standard#generateDisplayNameForMethod(Class, Method)} method to
     * generate display names for test methods based on the pattern "given [given], when [when], then [then]".
     *
     * @param testClass the class the test method is invoked on; never {@code null}
     * @param testMethod method to generate a display name for; never {@code null}
     * @return the generated display name for the test method
     * @since 0.0.0
     */
    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        var matcher = COMPONENTS.matcher(testMethod.getName());
        if (!matcher.matches()) return super.generateDisplayNameForMethod(testClass, testMethod);
        return "given %s, when %s, then %s".formatted(space(matcher.group("given")),
                space(matcher.group("when")), space(matcher.group("then")));
    }
}
