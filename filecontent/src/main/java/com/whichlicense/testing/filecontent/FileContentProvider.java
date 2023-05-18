/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.filecontent;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class FileContentProvider implements ArgumentsProvider, AnnotationConsumer<FileContentSource> {
    /**
     * The raw referenced resource path.
     */
    private String path = "";

    @Override
    public void accept(FileContentSource source) {
        path = source.path();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        var reference = Objects.requireNonNull(getClass().getResource(path)).toURI();
        try (var lines = Files.lines(Paths.get(reference))) {
            return Stream.of(Arguments.of(Named.of(path, lines.collect(joining(lineSeparator())))));
        }
    }
}
