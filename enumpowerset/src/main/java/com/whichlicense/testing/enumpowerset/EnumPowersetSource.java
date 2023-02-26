/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.enumpowerset;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

/**
 * This annotation is used to specify the enum class whose powerset is to be generated in JUnit 5 tests.
 * <p>
 * It is used in conjunction with the {@link EnumPowersetProvider} class to provide a test with all
 * possible combinations of the enum values as arguments.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(EnumPowersetProvider.class)
public @interface EnumPowersetSource {
    /**
     * The enum class whose powerset is to be generated.
     *
     * @return the enum class whose powerset is to be generated.
     * @since 0.0.0
     */
    Class<? extends Enum<?>> value();
}
