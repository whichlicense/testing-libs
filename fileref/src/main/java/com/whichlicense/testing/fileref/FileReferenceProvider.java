/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.fileref;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class resolves a given resource path.
 * <p>
 * It implements the {@link ArgumentsProvider} and {@link AnnotationConsumer} interfaces for use in JUnit 5 tests.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class FileReferenceProvider implements ArgumentsProvider, AnnotationConsumer<FileReferenceSource> {
    /**
     * The raw referenced resource path.
     */
    private String path = "";

    /**
     * This method sets the raw referenced resource path.
     *
     * @param source the annotation specifying the raw referenced resource path.
     * @since 0.0.0
     */
    @Override
    public void accept(FileReferenceSource source) {
        path = source.path();
    }

    /**
     * This method generates a single element stream of JUnit Jupiter arguments containing the resolved
     * referenced resource path.
     *
     * @param context the current extension context for the test; never {@code null}
     * @return a single element stream of JUnit arguments containing the resolved referenced resource path.
     * @since 0.0.0
     */
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        var reference = Objects.requireNonNull(getClass().getResource(path)).toURI();
        return Stream.of(Arguments.of(Named.of(path, Paths.get(reference))));
    }
}
