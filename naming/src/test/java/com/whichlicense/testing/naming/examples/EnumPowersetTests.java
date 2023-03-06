/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.testing.naming.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

class EnumPowersetTests {
    @Test
    void givenEmptyEnumWhenGeneratingPowersetThenAnEmptySetShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given empty enum, when generating "
                + "powerset, then an empty set should be returned");
    }

    @Test
    void givenOneElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given one element enum, when generating "
                + "powerset, then all valid sets should be returned");
    }

    @Test
    void givenTwoElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given two element enum, when generating "
                + "powerset, then all valid sets should be returned");
    }

    @Test
    void givenThreeElementEnumWhenGeneratingPowersetThenAllValidSetsShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given three element enum, when generating "
                + "powerset, then all valid sets should be returned");
    }

    @Test
    void givenEnumPowersetSourceForEmptyEnumWhenCountingTheStreamOfAllValidSetsThenOneShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given enum powerset source for empty enum, "
                + "when counting the stream of all valid sets, then one should be returned");
    }

    @Test
    void givenEnumPowersetSourceForOneElementEnumWhenCountingTheStreamOfAllValidSetsThenTwoShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given enum powerset source for one element enum, "
                + "when counting the stream of all valid sets, then two should be returned");
    }

    @Test
    void givenEnumPowersetSourceForTwoElementEnumWhenCountingTheStreamOfAllValidSetsThenFourShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given enum powerset source for two element enum, "
                + "when counting the stream of all valid sets, then four should be returned");
    }

    @Test
    void givenEnumPowersetSourceForThreeElementEnumWhenCountingTheStreamOfAllValidSetsThenEightShouldBeReturned(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given enum powerset source for three element enum, "
                + "when counting the stream of all valid sets, then eight should be returned");
    }

    @Test
    void givenEnumClassTypeAndValuesWhenCallingToGenericArrayThenReturnedATypedAndSizedArrayWithTheProvidedValues(TestInfo info) {
        assertThat(info.getDisplayName()).isEqualTo("given enum class type and values, when calling "
                + "to generic array, then returned a typed and sized array with the provided values");
    }
}
