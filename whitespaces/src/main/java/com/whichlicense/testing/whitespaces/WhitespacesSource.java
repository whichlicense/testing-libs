/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.whitespaces;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

/**
 * This annotation is used to provide whitespace characters to JUnit 5 tests.
 * <p>
 * It is used in conjunction with the {@link WhitespacesProvider} class to provide a test with all
 * UTF-8 whitespace characters.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(WhitespacesProvider.class)
public @interface WhitespacesSource {
}
