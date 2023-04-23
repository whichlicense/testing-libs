/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.fileref;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation is used to specify the resource path files references to be used by JUnit 5 tests.
 * <p>
 * It is used in conjunction with the {@link FileReferenceProvider} class to provide a test with the
 * resolved resource path as an argument.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
@Target(METHOD)
@Retention(RUNTIME)
@ArgumentsSource(FileReferenceProvider.class)
public @interface FileReferenceSource {
    /**
     * The raw referenced resource path.
     *
     * @return the raw reference resource path.
     * @since 0.0.0
     */
    String path();
}
