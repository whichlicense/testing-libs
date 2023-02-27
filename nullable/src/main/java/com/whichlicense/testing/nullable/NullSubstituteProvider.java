/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.nullable;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;

/**
 * This class generates null substitutions of all params provided by a static field of the test class.
 * <p>
 * It implements the {@link ArgumentsProvider} and {@link AnnotationConsumer} interfaces for use in JUnit 5 tests.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class NullSubstituteProvider implements ArgumentsProvider, AnnotationConsumer<NullSubstituteSource> {
    /**
     * The name of the static field that contains the test parameters.
     */
    private String staticFieldName;

    /**
     * Creates a null-terminated list of the given argument.
     *
     * @param arg the argument to include in the list
     * @return a list containing the argument followed by null
     * @since 0.0.0
     */
    static List<Object> nullTerminatedListOf(Object arg) {
        var array = new ArrayList<>(2);
        array.add(arg);
        array.add(null);
        return array;
    }

    /**
     * Finds all unique substitutions of the given elements, where each element is a list of arguments.
     *
     * @param elements the list of argument lists to find substitutions for
     * @return a list of all unique substitutions of the given elements
     * @since 0.0.0
     */
    static List<List<Object>> uniqueSubstitutions(List<List<Object>> elements) {
        return uniqueSubstitutions(elements, new ArrayList<>());
    }

    /**
     * Finds all unique substitutions of the given items, where each item is a list of arguments, with the given
     * list of arguments prepended to each substitution.
     *
     * @param elements the list of argument lists to find substitutions for
     * @param prepend  the list of arguments to prepend to each substitution
     * @return a list of all unique substitutions of the given elements with the given list of arguments prepended
     * @since 0.0.0
     */
    static List<List<Object>> uniqueSubstitutions(List<List<Object>> elements, List<Object> prepend) {
        if (elements == null || elements.isEmpty()) {
            return new ArrayList<>(singletonList(requireNonNull(prepend)));
        }
        return elements.get(0).stream().map(obj -> {
            var array = new ArrayList<>(prepend);
            array.add(obj);
            return uniqueSubstitutions(elements.subList(1, elements.size()), array);
        }).flatMap(Collection::stream).toList();
    }

    /**
     * Retrieves the test parameters from the static field specified by the {@link NullSubstituteSource}
     * annotation on the test method.
     *
     * @param clazz the test class to look up the static field in
     * @return an {@link Optional} containing the test parameters if the field was found and accessible,
     * or an empty optional otherwise
     * @since 0.0.0
     */
    private Optional<Arguments> retrieveArguments(Class<?> clazz) {
        try {
            var lookup = MethodHandles.privateLookupIn(clazz, lookup());
            var field = lookup.findStaticVarHandle(clazz, staticFieldName, Arguments.class);
            return Optional.ofNullable((Arguments) field.get());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    /**
     * This method sets the static field name to be used for generating the substitutions.
     *
     * @param source the annotation specifying the static field name.
     * @since 0.0.0
     */
    @Override
    public void accept(NullSubstituteSource source) {
        staticFieldName = source.value();
    }

    /**
     * Provides a stream of {@link Arguments} representing all unique null substitution test cases based on
     * the static field specified by the {@link NullSubstituteSource} annotation on the test method.
     *
     * @param context the current extension context for the test; never {@code null}
     * @return a stream of {@link Arguments} representing all unique null substitution test cases
     * @throws IllegalArgumentException if the test parameters could not be retrieved from the static field
     * @since 0.0.0
     */
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return context.getTestClass()
                .flatMap(this::retrieveArguments)
                .map(Arguments::get)
                .map(args -> Arrays.stream(args)
                        .map(NullSubstituteProvider::nullTerminatedListOf)
                        .toList())
                .map(NullSubstituteProvider::uniqueSubstitutions)
                .map(args -> args.stream()
                        .skip(args.size() > 0 ? 1 : 0)
                        .map(Collection::toArray)
                        .map(Arguments::of))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Failed to lookup the test params from the provided static field."));
    }
}
