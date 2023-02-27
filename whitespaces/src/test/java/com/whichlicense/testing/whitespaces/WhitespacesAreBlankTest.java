/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.whitespaces;

import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

class WhitespacesAreBlankTest {
    @ParameterizedTest
    @WhitespacesSource
    void givenWhitespacesWhenCallingIsBlankOnStringThenTrueShouldBeReturned(String whitespace) {
        assertThat(whitespace).isBlank();
    }
}
