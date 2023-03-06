/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.naming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.whichlicense.testing.naming.GivenWhenThenNameGenerator.space;
import static org.assertj.core.api.Assertions.assertThat;

class ComponentsSpaceTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "test", "test-name"})
    void givenNonUpperCaseInputStringWhenCallingSpaceThenTheUnmodifiedInputShouldBeReturned(String input) {
        assertThat(space(input)).isEqualTo(input);
    }

    @Test
    void givenSingleUpperCaseInputComponentWhenCallingSpaceThenTheSplitComponentWithAddedWhitespaceShouldBeReturned() {
        assertThat(space("firstSecond")).isEqualTo("first second");
    }

    @Test
    void givenMultipleUpperCaseInputComponentsWhenCallingSpaceThenTheSplitComponentsWithAddedWhitespaceShouldBeReturned() {
        assertThat(space("firstSecondThird")).isEqualTo("first second third");
    }
}
