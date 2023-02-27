/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.nullable;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

/**
 * This annotation is used to specify the static field containing the params to be substituted by null in JUnit 5 tests.
 * <p>
 * It is used in conjunction with the {@link NullSubstituteProvider} class to provide a test with all
 * possible null substitutions of the static field test params as arguments.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(NullSubstituteProvider.class)
public @interface NullSubstituteSource {
    /**
     * The static field name containing test params whose substitutions are to be generated.
     *
     * @return the static field name containing test params whose substitutions are to be generated.
     * @since 0.0.0
     */
    String value();
}
